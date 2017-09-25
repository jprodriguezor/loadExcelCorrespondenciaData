package com.foundation.soaint.massiveloader.web.infrastructure.massiveloader;

import co.com.foundation.soaint.documentmanager.business.comunicacionoficial.CorrespondenciaClient;
import co.com.foundation.soaint.documentmanager.business.comunicacionoficial.interfaces.ComunicacionOficialManagerProxy;
import co.com.foundation.soaint.documentmanager.domain.ComunicacionOficialContainerDTO;
import co.com.foundation.soaint.documentmanager.infrastructure.builder.massiveloader.CallerContextBuilder;
import co.com.foundation.soaint.documentmanager.jms.WildFlyJmsQueueSender;
import co.com.foundation.soaint.documentmanager.persistence.entity.CmRegistroCargaMasiva;
import co.com.foundation.soaint.documentmanager.persistence.entity.constants.RegistroCargaMasivaStatus;
import co.com.foundation.soaint.infrastructure.exceptions.BusinessException;
import co.com.foundation.soaint.infrastructure.exceptions.SystemException;
import com.foundation.soaint.massiveloader.web.domain.DocumentVO;
import com.foundation.soaint.massiveloader.web.infrastructure.common.RegistroCargaMasivaDTO;
import com.foundation.soaint.massiveloader.web.infrastructure.common.StatusMassiveLoaderProcessResponseDTO;
import com.foundation.soaint.massiveloader.web.infrastructure.transformer.massiveloader.DocumentToComunicacionOficialTransformer;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.jms.JMSException;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static org.jgroups.conf.ProtocolConfiguration.log;
@Component
public class MassiveLoaderRetry {


    static final String NOT_FOUND="not_found";
    static final String FAILED_TO_CONNECT="Failed to connect to any server. Servers tried:";
    static final String JMS_MESSAGE="jms fail";

    @PersistenceContext
    protected EntityManager em;

    @Autowired
    protected DocumentToComunicacionOficialTransformer documentToComunicacionOficialTransformer;
    @Autowired
    protected MassiveLoaderController massiveLoaderController;
    @Autowired
    WildFlyJmsQueueSender wildFlyJmsQueueSender;
    @Autowired
    protected CorrespondenciaClient correspondenciaClient;

    protected void retryCall() throws ParseException, NamingException, JMSException, BusinessException, SystemException {
        log.info("Se inicia el procesamiento de los mensajes con errores");

        log.info("Se obtienen los registros de cargas masiva");


        //TODO en caso de que se relaice correctamente
        for (RegistroCargaMasivaDTO registro: obtenerDataEstadoCargaMasivaCOmpletadoConErrores()
                ) {

            String mensaje= registro.getMensajes ();
            int id=registro.getId ();
            ComunicacionOficialContainerDTO comunicacionOficialContainerDTO=new ComunicacionOficialContainerDTO ();
            comunicacionOficialContainerDTO.setComunicacionOficialDTO (documentToComunicacionOficialTransformer.transform (fillDocumentVO (registro.getContenido ())).getDomainItem ().getComunicacionOficialDTO ());

            if (mensaje.contains (FAILED_TO_CONNECT)){
                //invocar servicio para carga masiva de nuevo
                log.info("Se llama al servicio para realizar la carga masiva========>");
                CallerContextBuilder ccBuilder = CallerContextBuilder.newBuilder();
                ccBuilder.withBeanName("comunicacionOficialManager");
                ccBuilder.withMethodName("gestionarComunicacionOficial");
                ccBuilder.withServiceInterface(ComunicacionOficialManagerProxy.class);
                correspondenciaClient.radicar (comunicacionOficialContainerDTO.getComunicacionOficialDTO ());


            }
            else if (mensaje.contains (JMS_MESSAGE)){
                //encolar nuevamente
//                    Long ide=Long.valueOf (id);
                log.info("Se encola nuevamente");
                Gson gson = new Gson();
                String message = gson.toJson(comunicacionOficialContainerDTO.getEntradaProcesoDTO());
                log.info("Mensaje a encolar: " + message);
                wildFlyJmsQueueSender.init();
                wildFlyJmsQueueSender.send(message);
                wildFlyJmsQueueSender.close();

            }
        }


    }


    public DocumentVO fillDocumentVO(String datos) throws ParseException {
        DocumentVO documentVO=new DocumentVO ();

        String[] parts = datos.split(",");

        documentVO.setNoRadicado (parts[0].substring (parts[0].indexOf ("=")+1));

        DateFormat format = new SimpleDateFormat ("EEE MMM d HH:mm:ss z yyyy", Locale.ENGLISH);
        Date date=new Date ();
        try {
            log.info(parts[1].substring (parts[1].indexOf ("=")+1));
            date = format.parse(parts[1].substring (parts[1].indexOf ("=")+1));
        }catch  (ParseException e){
            log.info("Error dando formato a la fecha");
        }

        documentVO.setFechaRadicacion (date);
        documentVO.setTipoComunicacion (parts[2].substring (parts[2].indexOf ("=")+1));
        documentVO.setTipologiaDocumental (parts[3].substring (parts[3].indexOf ("=")+1));

        double noFolios = Double.parseDouble(parts[4].substring (parts[4].indexOf ("=")+1));
        documentVO.setNoFolios (noFolios);

        double noAnexos = Double.parseDouble(parts[5].substring (parts[5].indexOf ("=")+1));
        documentVO.setNoAnexos (noAnexos);
        documentVO.setAsunto (parts[6].substring (parts[6].indexOf ("=")+1));
        documentVO.setRequiereDigitalizar (parts[7].substring (parts[7].indexOf ("=")+1));
        documentVO.setRequiereDistribucionFisica (parts[8].substring (parts[8].indexOf ("=")+1));
        documentVO.setPersonaRemite (parts[9].substring (parts[9].indexOf ("=")+1));
        documentVO.setRazonSocial (parts[10].substring (parts[10].indexOf ("=")+1));
        documentVO.setNombre (parts[11].substring (parts[11].indexOf ("=")+1));
        documentVO.setSedeAdministrativaRemitenteInterno (parts[12].substring (parts[12].indexOf ("=")+1));
        documentVO.setDependenciaRemitenteInterno (parts[13].substring (parts[13].indexOf ("=")+1));
        documentVO.setSedeAdministrativaDestinatario (parts[14].substring (parts[14].indexOf ("=")+1));
        documentVO.setDependenciaDestinatario (parts[15].substring (parts[15].indexOf ("=")+1));

        return documentVO;
    }

    @Transactional
    protected void actualizarEstadoExito(int id) {
        log.info("Iniciando actualizarEstado con ESTADO = " + RegistroCargaMasivaStatus.COMPLETADO_CORRECTAMENTE);
        StatusMassiveLoaderProcessResponseDTO response;

        em.createNamedQuery("CmRegistroCargaMasiva.updateEstadoRegistroCargaMasiva")
                .setParameter("ESTADO", RegistroCargaMasivaStatus.COMPLETADO_CORRECTAMENTE)
                .setParameter ("ID",Long.valueOf(id))
                .executeUpdate ();

    }
    protected List<RegistroCargaMasivaDTO> obtenerDataEstadoCargaMasivaCOmpletadoConErrores() {
        log.info("Iniciando obtenerDataEstadoCargaMasivabyEstado con ESTADO = " + RegistroCargaMasivaStatus.COMPLETADO_CON_ERRORES);
        StatusMassiveLoaderProcessResponseDTO response;
        List<RegistroCargaMasivaDTO> listreponse=new ArrayList<> ();

        List<CmRegistroCargaMasiva> cmRegistroCargaMasivas = em.createNamedQuery("CmRegistroCargaMasiva.findbyEstado", CmRegistroCargaMasiva.class)
                .setParameter("ESTADO", RegistroCargaMasivaStatus.COMPLETADO_CON_ERRORES)
                .getResultList();

        for (CmRegistroCargaMasiva cmRegistroCargaMasiva: cmRegistroCargaMasivas)
        {
            RegistroCargaMasivaDTO registroCargaMasivaDT=new RegistroCargaMasivaDTO ();
            log.info("$$$$$$$$$$$$$$ "+cmRegistroCargaMasiva.getId ().intValue ());
            registroCargaMasivaDT.setId (cmRegistroCargaMasiva.getId ().intValue ());
            registroCargaMasivaDT.setContenido (cmRegistroCargaMasiva.getContenido ());
            registroCargaMasivaDT.setEstado (cmRegistroCargaMasiva.getEstado ());
            registroCargaMasivaDT.setMensajes (cmRegistroCargaMasiva.getMensajes ());
            listreponse.add (registroCargaMasivaDT);
        }

        if (listreponse != null && listreponse.get (0) != null) {
            log.info("Fin obtenerDataEstadoCargaMasivaCOmpletadoConErrores con total de registros = " + listreponse.size ());
        } else {
            log.info("Fin obtenerDataEstadoCargaMasivabyEstado con total de registros = 0");
        }

        return listreponse;
    }
    protected String obtenerDataEstadoCargaMasivabyIDAA(int idCarga) throws SystemException, JMSException, BusinessException, ParseException, NamingException {
        log.info ("Iniciando obtenerDataEstadoCargaMasivabyID con ID = " + idCarga);
        retryCall ();
        return "AAAAA";
    }

}
