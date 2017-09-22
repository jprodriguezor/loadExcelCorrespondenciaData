package com.foundation.soaint.massiveloader.web.infrastructure.massiveloader;

import co.com.foundation.soaint.documentmanager.business.comunicacionoficial.ComunicacionOficialManager;
import co.com.foundation.soaint.documentmanager.business.comunicacionoficial.CorrespondenciaClient;
import co.com.foundation.soaint.documentmanager.domain.ComunicacionOficialContainerDTO;
import co.com.foundation.soaint.documentmanager.infrastructure.massiveloader.LoaderAsyncWorker;
import co.com.foundation.soaint.documentmanager.infrastructure.massiveloader.MassiveLoaderType;
import co.com.foundation.soaint.documentmanager.infrastructure.massiveloader.domain.CallerContext;
import co.com.foundation.soaint.documentmanager.infrastructure.massiveloader.domain.MassiveRecordContext;
import co.com.foundation.soaint.documentmanager.infrastructure.massiveloader.executor.LoaderExecutor;
import co.com.foundation.soaint.documentmanager.jms.WildFlyJmsQueueSender;
import co.com.foundation.soaint.documentmanager.persistence.entity.CmCargaMasiva;
import co.com.foundation.soaint.documentmanager.persistence.entity.CmRegistroCargaMasiva;
import co.com.foundation.soaint.documentmanager.persistence.entity.constants.CargaMasivaStatus;
import co.com.foundation.soaint.documentmanager.persistence.entity.constants.RegistroCargaMasivaStatus;
import co.com.foundation.soaint.infrastructure.common.MessageUtil;
import co.com.foundation.soaint.infrastructure.exceptions.BusinessException;
import co.com.foundation.soaint.infrastructure.exceptions.SystemException;
import co.com.foundation.soaint.infrastructure.transformer.Transformer;
import com.foundation.soaint.massiveloader.web.domain.DocumentVO;
import com.foundation.soaint.massiveloader.web.infrastructure.common.*;
import com.foundation.soaint.massiveloader.web.infrastructure.parser.DocumentParser;
import com.foundation.soaint.massiveloader.web.infrastructure.parser.DocumentParserFactory;
import com.foundation.soaint.massiveloader.web.infrastructure.transformer.massiveloader.DocumentToComunicacionOficialTransformer;
import com.google.gson.Gson;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.jms.JMSException;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.jgroups.conf.ProtocolConfiguration.log;

/**
 * Created by administrador_1 on 01/10/2016.
 */
@Log4j2
public abstract class MassiveLoaderController<O, E> {


    @Autowired
    protected LoaderAsyncWorker<E> loaderAsyncWorker;

    @PersistenceContext
    protected EntityManager em;

    @Autowired
    protected CorrespondenciaClient correspondenciaClient;

    @Autowired
    protected DocumentToComunicacionOficialTransformer documentToComunicacionOficialTransformer;

    @Autowired
    protected ComunicacionOficialManager comunicacionOficialManager;

    @Autowired
    WildFlyJmsQueueSender wildFlyJmsQueueSender;

//    @Autowired
//    MassiveLoaderRetry massiveLoaderRetry;

    static final String NOT_FOUND="not_found";
    static final String FAILED_TO_CONNECT="Failed to connect to any server. Servers tried:";
    static final String JMS_MESSAGE="jms fail";
    //[generic load processing] ------------------------------

    protected MasiveLoaderResponse processGenericLoad(final MultipartFile file, final LoaderExecutor<E> executor,
                                                      final MassiveLoaderType type,
                                                      final Transformer voTransformer,
                                                      final Transformer<O, E> massiveRecordTransformer,
                                                      final CallerContext callerContext, String codigoSede, String codigoDependencia) {

        MasiveLoaderResponse response;

        DocumentParserFactory<O> documentParserFactory = new DocumentParserFactory<>();
        DocumentParser documentParser = documentParserFactory.getDocumentParser(file);

        try {

            log.info("Iniciando procesamiento masivo del excel: " + file.getName() + " extraccion de informacion");
            List<O> records = documentParser.parse(file, voTransformer);
            response = MasiveLoaderResponse.newInstance(validate(records));
            if (response.isSuccess()) {
                log.info("Datos cargados exitosamente");
                List<E> contextInfoList = new ArrayList<>();
                records.stream().forEach((O vo) -> {
                    MassiveRecordContext<ComunicacionOficialContainerDTO> data = (MassiveRecordContext<ComunicacionOficialContainerDTO>) massiveRecordTransformer.transform(vo);
                    data.getDomainItem().getComunicacionOficialDTO().getCorrespondencia().setCodDependencia(codigoDependencia);
                    data.getDomainItem().getComunicacionOficialDTO().getCorrespondencia().setCodSede(codigoSede);
                    contextInfoList.add((E) data);
                });
                log.info("Inicio del procesamiento en hilos");
                loaderAsyncWorker.process(executor, contextInfoList, type, callerContext);
            }

        } catch (BusinessException be) {
            log.error("Error de negocio en: " + file.getName(), be);
            response = MasiveLoaderResponse.newInstance(be.getMessage());
        } catch (NumberFormatException e) {
            log.error("Excepcion de formato de numero en el fichero: " + file.getName(), e);
            response = MasiveLoaderResponse.newInstance(MessageUtil.getMessage("massiveloader.structure.error"));
        } catch (Exception e) {
            log.error("Excepcion generica en el fichero: " + file.getName(), e);
            response = MasiveLoaderResponse.newInstance(MessageUtil.getMessage("massiveloader.generic.error"));
        }

        log.info("Carga masiva terminada del fichero: " + file.getName());
        return response;
    }

    protected StatusMassiveLoaderProcessResponseDTO obtenerDataEstadoCargaMasiva() {
        log.info("Inicio obtenerDataEstadoCargaMasiva");
        StatusMassiveLoaderProcessResponseDTO response;
        List<CmCargaMasiva> cmcargamasiva = em.createNamedQuery("CmCargaMasiva.obtenerDataEstadoCargaMasiva", CmCargaMasiva.class)
                .setMaxResults(1)
                .getResultList();
        response = getResponse(cmcargamasiva);
        log.info("Fin obtenerDataEstadoCargaMasiva");
        return response;
    }


    protected StatusMassiveLoaderProcessResponseDTO obtenerDataEstadoCargaMasivabyID(int idCarga) {
        log.info("Iniciando obtenerDataEstadoCargaMasivabyID con ID = " + idCarga);
        StatusMassiveLoaderProcessResponseDTO response;
        List<CmCargaMasiva> cmcargamasiva = em.createNamedQuery("CmCargaMasiva.obtenerDataEstadoCargaMasivabyID", CmCargaMasiva.class)
                .setParameter("ID_CARGA", Long.valueOf(idCarga))
                .setMaxResults(1)
                .getResultList();
        response = getResponse(cmcargamasiva);
        if (response != null && response.getCorrespondencia() != null) {
            log.info("Fin obtenerDataEstadoCargaMasivabyID con total de registros = " + response.getCorrespondencia().getTotalRegistrosCargaMasiva());
        } else {
            log.info("Fin obtenerDataEstadoCargaMasivabyID con total de registros = 0");
        }

        return response;
    }

    protected String obtenerDataEstadoCargaMasivabyIDAA(int idCarga) throws SystemException, JMSException, BusinessException, ParseException, NamingException {
        log.info("Iniciando obtenerDataEstadoCargaMasivabyID con ID = " + idCarga);
        retryCall ();

        return "AAAAA";
    }

    protected List<RegistroCargaMasivaDTO> obtenerDataEstadoCargaMasivaCOmpletadoConErrores() {
        log.info("Iniciando obtenerDataEstadoCargaMasivabyEstado con ESTADO = " + RegistroCargaMasivaStatus.COMPLETADO_CON_ERRORES);
        StatusMassiveLoaderProcessResponseDTO response;
        List<RegistroCargaMasivaDTO> listreponse=new ArrayList <> ();

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

    protected ListadoCargasMasivasDTO obtenerDataListadoCargaMasiva() {
        log.info("Iniciando obtenerDataListadoCargaMasiva");
        ListadoCargasMasivasDTO response;
        List<CmCargaMasiva> cmCargaMasivaList = em.createNamedQuery("CmCargaMasiva.obtenerDataEstadoCargaMasiva", CmCargaMasiva.class)
                .getResultList();
        response = transformCargaMasivaToListadoCargasMasivasDTO(cmCargaMasivaList);
        log.info("Fin obtenerDataListadoCargaMasiva");
        return response;

    }


    //TODO: pendiente determinar si se usa un transform como en los otros casos
    private ListadoCargasMasivasDTO transformCargaMasivaToListadoCargasMasivasDTO(List<CmCargaMasiva> cmCargaMasivaList) {
        ListadoCargasMasivasDTO listadoCargasMasivasDTO = new ListadoCargasMasivasDTO();
        List<CargaMasiva> listado = new ArrayList<>();
        for (CmCargaMasiva cmCargaMasiva : cmCargaMasivaList) {
            CargaMasiva cm = new CargaMasiva(Math.toIntExact(cmCargaMasiva.getId()), cmCargaMasiva.getNombre());
            log.info("======================== >  Carga Masiva ID: " + cm.getId());
            log.info("======================== >  Carga Masiva Nombre: " + cm.getNombreCarga());
            listado.add(cm);
        }
        listadoCargasMasivasDTO.setCargaMasiva(listado);
        return listadoCargasMasivasDTO;
    }

    private List<RegistroCargaMasivaDTO> transformCMRegistrosToRegistroCargaMasivaDTO(List<CmRegistroCargaMasiva> listadoCMRegistros) {
        List<RegistroCargaMasivaDTO> listado = new ArrayList<>();

        for (CmRegistroCargaMasiva cmR : listadoCMRegistros) {
            RegistroCargaMasivaDTO registro = new RegistroCargaMasivaDTO(Math.toIntExact(cmR.getId()), cmR.getContenido(), cmR.getMensajes(), cmR.getEstado());
            log.info("======================== >  Estado del Registro: " + registro.getEstado());
            listado.add(registro);
        }
        return listado;
    }

    private StatusMassiveLoaderProcessResponseDTO transformListCargaMasivaToStatusMassiveLoaderProcessResponseDTO(CmCargaMasiva cmCargaMasiva) {
        StatusMassiveLoaderProcessResponseDTO statusMassiveLoaderProcessResponseDTO = null;
        if (cmCargaMasiva != null) {
            statusMassiveLoaderProcessResponseDTO = new StatusMassiveLoaderProcessResponseDTO();
            CorrespondenciaResponse correspondencia = new CorrespondenciaResponse();
            correspondencia.setIdCargaMasiva(Math.toIntExact(cmCargaMasiva.getId()));
            correspondencia.setNombreCargaMasiva(cmCargaMasiva.getNombre());
            correspondencia.setFechaCreacionCargaMasiva(cmCargaMasiva.getFechaCreacion());
            correspondencia.setEstadoCargaMasiva(cmCargaMasiva.getEstado().name());
            correspondencia.setTotalRegistrosCargaMasiva(cmCargaMasiva.getTotalRegistros());
            correspondencia.setTotalRegistrosExitososCargaMasiva(cmCargaMasiva.getTotalRegistrosExitosos());
            correspondencia.setTotalRegistrosErrorCargaMasiva(cmCargaMasiva.getTotalRegistrosError());
            statusMassiveLoaderProcessResponseDTO.setCorrespondencia(correspondencia);
            log.info("======================== >  Cantidad de registros procesados: " + correspondencia.getTotalRegistrosCargaMasiva());
        }
        return statusMassiveLoaderProcessResponseDTO;
    }

    private StatusMassiveLoaderProcessResponseDTO getResponse(List<CmCargaMasiva> cmcargamasiva) {
        StatusMassiveLoaderProcessResponseDTO response = new StatusMassiveLoaderProcessResponseDTO();
        if (cmcargamasiva != null && !cmcargamasiva.isEmpty() && cmcargamasiva.get(0) != null) {
            response = transformListCargaMasivaToStatusMassiveLoaderProcessResponseDTO(cmcargamasiva.get(0));
            if (response != null) {
                List<CmRegistroCargaMasiva> listadoCMRegistros = em.createNamedQuery("CmRegistroCargaMasiva.findbyIDCarga", CmRegistroCargaMasiva.class)
                        .setParameter("ID_CARGA", Long.valueOf(response.getCorrespondencia().getIdCargaMasiva()))
                        .getResultList();
                List<RegistroCargaMasivaDTO> registros = transformCMRegistrosToRegistroCargaMasivaDTO(listadoCMRegistros);
                response.getCorrespondencia().setRegistrosCargaMasiva(registros);
            }
        }
        return response;
    }
    private StatusMassiveLoaderProcessResponseDTO getResponse(CmCargaMasiva cmcargamasiva) {
        StatusMassiveLoaderProcessResponseDTO response = new StatusMassiveLoaderProcessResponseDTO();
        if (cmcargamasiva != null ) {
            response = transformListCargaMasivaToStatusMassiveLoaderProcessResponseDTO(cmcargamasiva);
            if (response != null) {
                List<CmRegistroCargaMasiva> listadoCMRegistros = em.createNamedQuery("CmRegistroCargaMasiva.findbyIDCarga", CmRegistroCargaMasiva.class)
                        .setParameter("ID_CARGA", Long.valueOf(response.getCorrespondencia().getIdCargaMasiva()))
                        .getResultList();
                List<RegistroCargaMasivaDTO> registros = transformCMRegistrosToRegistroCargaMasivaDTO(listadoCMRegistros);
                response.getCorrespondencia().setRegistrosCargaMasiva(registros);
            }
        }
        return response;
    }
    //[validate] ------------------------------

    protected String validate(List<O> csvDomainList) {

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        StringBuilder messageResponse = new StringBuilder();
        int index = 1;
        boolean errors = false;

        for (O item : csvDomainList) {

            Set<ConstraintViolation<O>> results = validator.validate(item);
            for (ConstraintViolation valResult : results) {
                messageResponse.append("Row " + index + " - " + valResult.getPropertyPath() + " : " + valResult.getMessage()).append("<br/>");
                errors = true;
            }
            index++;
        }

        String baseMessage = errors ? "Hay errores de estructura y/o tipos de datos en el archivo, revise el contenido"/*MessageUtil.getMessage("massiveloader.structure.error")*/ : "";
        return baseMessage + messageResponse.toString();
    }

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
                    comunicacionOficialManager.gestionarComunicacionOficial (comunicacionOficialContainerDTO);
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

//    private void enExito(Long id) {
//
//        CmRegistroCargaMasiva cmRegistroCargaMasiva = new CmRegistroCargaMasiva ( );
//        cmRegistroCargaMasiva.setId (id);
//
//        CmRegistroCargaMasiva cmRegistroCargaMass = em.find (CmRegistroCargaMasiva.class, cmRegistroCargaMasiva.getId ( ));
//        em.getTransaction ( ).begin ( );
//        cmRegistroCargaMass.setEstado (RegistroCargaMasivaStatus.COMPLETADO_CORRECTAMENTE);
//        em.getTransaction ( ).commit ( );
//    }
    //[template] ------------------------------


}


