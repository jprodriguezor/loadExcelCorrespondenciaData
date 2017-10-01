package com.foundation.soaint.massiveloader.web.infrastructure.massiveloader;

import co.com.foundation.soaint.documentmanager.business.comunicacionoficial.CorrespondenciaClient;
import co.com.foundation.soaint.documentmanager.business.comunicacionoficial.RetryMassiveLoader;
import co.com.foundation.soaint.documentmanager.domain.ComunicacionOficialContainerDTO;
import co.com.foundation.soaint.documentmanager.infrastructure.massiveloader.domain.MassiveRecordContext;
import co.com.foundation.soaint.documentmanager.jms.WildFlyJmsQueueSender;
import co.com.foundation.soaint.documentmanager.persistence.entity.CmRegistroCargaMasiva;
import co.com.foundation.soaint.documentmanager.persistence.entity.constants.RegistroCargaMasivaStatus;
import co.com.foundation.soaint.infrastructure.exceptions.BusinessException;
import co.com.foundation.soaint.infrastructure.exceptions.SystemException;
import com.foundation.soaint.massiveloader.web.domain.DocumentVO;
import com.foundation.soaint.massiveloader.web.infrastructure.common.RegistroCargaMasivaDTO;
import com.foundation.soaint.massiveloader.web.infrastructure.common.StatusMassiveLoaderProcessResponseDTO;
import com.foundation.soaint.massiveloader.web.infrastructure.transformer.massiveloader.DocToComOficTransf;
import com.google.gson.Gson;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.jms.JMSException;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.core.Response;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Component
@Log4j2
public class MassiveLoaderRetry {


    static final String NOT_FOUND = "not_found";
    static final String JMS_ERROR = "Failed to connect to any server. Servers tried:";
    static final String INTERNALERROR = ", Internal Server Error";


    @PersistenceContext
    public EntityManager em;

    @Autowired
    public DocToComOficTransf documentToComunicacionOficialTransformer;

    @Autowired
    public WildFlyJmsQueueSender wildFlyJmsQueueSender;
    @Autowired
    public CorrespondenciaClient correspondenciaClient;

    @Autowired
    RetryMassiveLoader retryMassiveLoader;

    public void retryCall() throws ParseException, NamingException, JMSException, BusinessException, SystemException {
        log.info ("Se inicia el procesamiento de los mensajes con errores");

        //TODO en caso de que se realice correctamente
        for (RegistroCargaMasivaDTO registro : obtenerDataEstadoCargaMasivaCOmpletadoConErrores ( )
                ) {
            String mensajeERROR = registro.getMensajes ( );
            RegistroCargaMasivaStatus estado = registro.getEstado ( );
            log.info ("Registro con ID: " + registro.getId ( ) + " y mensaje: " + mensajeERROR);
            int id = registro.getId ( );
            ComunicacionOficialContainerDTO comunicacionOficialContainerDTO = new ComunicacionOficialContainerDTO ( );
            MassiveRecordContext <ComunicacionOficialContainerDTO> container = documentToComunicacionOficialTransformer.transform (fillDocumentVO (registro.getContenido ( )));


            comunicacionOficialContainerDTO.setComunicacionOficialDTO (container.getDomainItem ( ).getComunicacionOficialDTO ( ));
            comunicacionOficialContainerDTO.setEntradaProcesoDTO (container.getDomainItem ( ).getEntradaProcesoDTO ( ));
            Gson gson = new Gson ( );
            log.info ("Entrada proceso JSON: " + comunicacionOficialContainerDTO.getEntradaProcesoDTO ( ).toString ( ));
            String mensajeJMS = gson.toJson (comunicacionOficialContainerDTO.getEntradaProcesoDTO ( ));

            log.info("Estado = " + estado + " y mensaje error = " + mensajeERROR);
            if (mensajeERROR.contains (NOT_FOUND) && (estado != RegistroCargaMasivaStatus.COMPLETADO_GUARDADO_BD)) {
                log.info ("Se llama nuevamente al servicio para realizar la carga masiva");
                if (correspondenciaClient.radicar (comunicacionOficialContainerDTO.getComunicacionOficialDTO ( )).getStatus ( ) == Response.Status.OK.getStatusCode ( )) {
                    log.info ("Correspondencia radicada correctamente con ID = " + id);
                    //Se actualiza el estado a Insertado en Base de Datos
                    retryMassiveLoader.actualizarEstadoExito (id, RegistroCargaMasivaStatus.COMPLETADO_GUARDADO_BD, "Guardado Base de datos");
                    //El metodo que encola llama a actualizar estado, para indicar que se encolo correctamente
                    enviarMensajeColaJMS (id, mensajeJMS);
                }
            } else if (mensajeERROR.contains (JMS_ERROR) || estado == RegistroCargaMasivaStatus.COMPLETADO_CON_ERROR_COLA) {
                log.info ("Se procede a encolar");
                enviarMensajeColaJMS (id, mensajeJMS);
            }
        }
    }

    public void enviarMensajeColaJMS(int id, String mensaje) throws JMSException, NamingException {
        log.info ("Mensaje a encolar: " + mensaje);
        try {
            wildFlyJmsQueueSender.init ( );
            wildFlyJmsQueueSender.send (mensaje);
            wildFlyJmsQueueSender.close ( );
            log.info ("Mensaje encolado correctamente");
            retryMassiveLoader.actualizarEstadoExito (id, RegistroCargaMasivaStatus.COMPLETADO_CORRECTAMENTE, null);
        } catch (JMSException e) {
            retryMassiveLoader.actualizarEstadoExito (id, RegistroCargaMasivaStatus.COMPLETADO_CON_ERROR_COLA, e.toString ( ));
        }
    }


    public DocumentVO fillDocumentVO(String datos) throws ParseException {
        DocumentVO documentVO = new DocumentVO ( );

        String[] parts = datos.split (",");

        documentVO.setNoRadicado (parts[0].substring (parts[0].indexOf ("=") + 1));

        DateFormat format = new SimpleDateFormat ("EEE MMM d HH:mm:ss z yyyy", Locale.ENGLISH);
        Date date = new Date ( );
        try {
            date = format.parse (parts[1].substring (parts[1].indexOf ("=") + 1));
        } catch (ParseException e) {
            log.info ("Error dando formato a la fecha");
        }

        documentVO.setFechaRadicacion (date);
        documentVO.setTipoComunicacion (parts[2].substring (parts[2].indexOf ("=") + 1));
        documentVO.setTipologiaDocumental (parts[3].substring (parts[3].indexOf ("=") + 1));
        double noFolios = Double.parseDouble (parts[4].substring (parts[4].indexOf ("=") + 1));
        documentVO.setNoFolios (noFolios);
        double noAnexos = Double.parseDouble (parts[5].substring (parts[5].indexOf ("=") + 1));
        documentVO.setNoAnexos (noAnexos);
        documentVO.setAsunto (parts[6].substring (parts[6].indexOf ("=") + 1));
        documentVO.setRequiereDigitalizar (parts[7].substring (parts[7].indexOf ("=") + 1));
        documentVO.setRequiereDistribucionFisica (parts[8].substring (parts[8].indexOf ("=") + 1));
        documentVO.setPersonaRemite (parts[9].substring (parts[9].indexOf ("=") + 1));
        documentVO.setRazonSocial (parts[10].substring (parts[10].indexOf ("=") + 1));
        documentVO.setNombre (parts[11].substring (parts[11].indexOf ("=") + 1));
        documentVO.setSedeAdministrativaRemitenteInterno (parts[12].substring (parts[12].indexOf ("=") + 1));
        documentVO.setDependenciaRemitenteInterno (parts[13].substring (parts[13].indexOf ("=") + 1));
        documentVO.setSedeAdministrativaDestinatario (parts[14].substring (parts[14].indexOf ("=") + 1));
        documentVO.setDependenciaDestinatario (parts[15].substring (parts[15].indexOf ("=") + 1));
        documentVO.setSede (parts[16].substring (parts[16].indexOf ("=") + 1));
        documentVO.setDependencia (parts[17].substring (parts[17].indexOf ("=") + 1, parts[17].indexOf (")")));
        return documentVO;
    }


    protected List <RegistroCargaMasivaDTO> obtenerDataEstadoCargaMasivaCOmpletadoConErrores() {
        log.info ("Iniciando obtenerDataEstadoCargaMasivabyEstado con ESTADO = " + RegistroCargaMasivaStatus.COMPLETADO_CON_ERRORES);
        List <RegistroCargaMasivaDTO> listreponse = new ArrayList <> ( );
        List <CmRegistroCargaMasiva> cmRegistroCargaMasivas = em.createNamedQuery ("CmRegistroCargaMasiva.findbyEstado", CmRegistroCargaMasiva.class)
                .setParameter ("ESTADO", RegistroCargaMasivaStatus.COMPLETADO_CON_ERRORES)
                .setParameter ("MENSAJE", INTERNALERROR)
                .getResultList ( );
        for (CmRegistroCargaMasiva cmRegistroCargaMasiva : cmRegistroCargaMasivas) {
            RegistroCargaMasivaDTO registroCargaMasivaDT = new RegistroCargaMasivaDTO ( );
            registroCargaMasivaDT.setId (cmRegistroCargaMasiva.getId ( ).intValue ( ));
            registroCargaMasivaDT.setContenido (cmRegistroCargaMasiva.getContenido ( ));
            registroCargaMasivaDT.setEstado (cmRegistroCargaMasiva.getEstado ( ));
            registroCargaMasivaDT.setMensajes (cmRegistroCargaMasiva.getMensajes ( ));
            listreponse.add (registroCargaMasivaDT);
        }

        return listreponse;
    }
}
