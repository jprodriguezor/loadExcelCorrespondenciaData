package co.com.soaint.correspondencia.massiveloader.web.infrastructure.massiveloader;

import co.com.soaint.correspondencia.massiveloader.web.domain.DocumentVO;
import co.com.soaint.correspondencia.massiveloader.web.infrastructure.common.*;
import co.com.soaint.correspondencia.massiveloader.web.infrastructure.parser.DocumentParser;
import co.com.soaint.correspondencia.massiveloader.web.infrastructure.parser.DocumentParserFactory;
import co.com.soaint.correspondencia.massiveloader.web.infrastructure.transformer.massiveloader.DocToComOficTransf;
import co.com.soaint.foundation.documentmanager.business.comunicacionoficial.CorrespondenciaClient;
import co.com.soaint.foundation.documentmanager.domain.ComunicacionOficialContainerDTO;
import co.com.soaint.foundation.documentmanager.infrastructure.massiveloader.LoaderAsyncWorker;
import co.com.soaint.foundation.documentmanager.infrastructure.massiveloader.MassiveLoaderType;
import co.com.soaint.foundation.documentmanager.infrastructure.massiveloader.domain.CallerContext;
import co.com.soaint.foundation.documentmanager.infrastructure.massiveloader.domain.MassiveRecordContext;
import co.com.soaint.foundation.documentmanager.infrastructure.massiveloader.executor.LoaderExecutor;
import co.com.soaint.foundation.documentmanager.jms.WildFlyJmsQueueSender;
import co.com.soaint.foundation.documentmanager.persistence.entity.CmCargaMasiva;
import co.com.soaint.foundation.documentmanager.persistence.entity.CmRegistroCargaMasiva;
import co.com.soaint.foundation.infrastructure.common.MessageUtil;
import co.com.soaint.foundation.infrastructure.exceptions.BusinessException;
import co.com.soaint.foundation.infrastructure.transformer.Transformer;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
    protected DocToComOficTransf documentToComunicacionOficialTransformer;

    @Autowired
    WildFlyJmsQueueSender wildFlyJmsQueueSender;

    //[generic load processing] ------------------------------

    protected MasiveLoaderResponse processGenericLoad(final MultipartFile file, final LoaderExecutor<E> executor,
                                                      final MassiveLoaderType type,
                                                      final Transformer voTransformer,
                                                      final Transformer <O, E> massiveRecordTransformer,
                                                      final CallerContext callerContext, String codigoSede, String codigoDependencia, String codfunRadica) {

        MasiveLoaderResponse response;

        DocumentParserFactory<O> documentParserFactory = new DocumentParserFactory <> ( );
        DocumentParser documentParser = documentParserFactory.getDocumentParser (file);

        try {

            log.info ("Iniciando procesamiento masivo del excel: " + file.getName ( ) + " extraccion de informacion");
            List <O> records = documentParser.parse (file, voTransformer);
            response = MasiveLoaderResponse.newInstance (validate (records));
            if (response.isSuccess ( )) {
                log.info ("Datos cargados exitosamente");
                List <E> contextInfoList = new ArrayList <> ( );
                records.stream ( ).forEach ((O vo) -> {
                    DocumentVO documentoSedDep=(DocumentVO) vo;
                    documentoSedDep.setDependencia (codigoDependencia);
                    documentoSedDep.setSede (codigoSede);
                    MassiveRecordContext<ComunicacionOficialContainerDTO> data = (MassiveRecordContext <ComunicacionOficialContainerDTO>) massiveRecordTransformer.transform ((O)documentoSedDep);
                    data.getDomainItem ( ).getComunicacionOficialDTO ( ).getCorrespondencia ( ).setCodDependencia (codigoDependencia);
                    data.getDomainItem ( ).getComunicacionOficialDTO ( ).getCorrespondencia ( ).setCodSede (codigoSede);
                    data.getDomainItem ( ).getComunicacionOficialDTO ( ).getCorrespondencia ( ).setCodFuncRadica (codfunRadica);
                    contextInfoList.add ((E) data);
                });
                log.info ("Inicio del procesamiento en hilos");
                loaderAsyncWorker.process (executor, contextInfoList, type, callerContext);
            }

        } catch (BusinessException be) {
            log.error ("Error de negocio en: " + file.getName ( ), be);
            response = MasiveLoaderResponse.newInstance (be.getMessage ( ));
        } catch (NumberFormatException e) {
            log.error ("Excepcion de formato de numero en el fichero: " + file.getName ( ), e);
            response = MasiveLoaderResponse.newInstance (MessageUtil.getMessage ("massiveloader.structure.error"));
        } catch (Exception e) {
            log.error ("Excepcion generica en el fichero: " + file.getName ( ), e);
            response = MasiveLoaderResponse.newInstance (MessageUtil.getMessage ("massiveloader.generic.error"));
        }

        log.info ("Carga masiva terminada del fichero: " + file.getName ( ));
        return response;
    }

    protected StatusMassiveLoaderProcessResponseDTO obtenerDataEstadoCargaMasiva() {
        log.info ("Inicio obtenerDataEstadoCargaMasiva");
        StatusMassiveLoaderProcessResponseDTO response;
        List <CmCargaMasiva> cmcargamasiva = em.createNamedQuery ("CmCargaMasiva.obtenerDataEstadoCargaMasiva", CmCargaMasiva.class)
                .setMaxResults (1)
                .getResultList ( );
        response = getResponse (cmcargamasiva);
        log.info ("Fin obtenerDataEstadoCargaMasiva");
        return response;
    }


    protected StatusMassiveLoaderProcessResponseDTO obtenerDataEstadoCargaMasivabyID(int idCarga) {
        log.info ("Iniciando obtenerDataEstadoCargaMasivabyID con ID = " + idCarga);
        StatusMassiveLoaderProcessResponseDTO response;
        List <CmCargaMasiva> cmcargamasiva = em.createNamedQuery ("CmCargaMasiva.obtenerDataEstadoCargaMasivabyID", CmCargaMasiva.class)
                .setParameter ("ID_CARGA", Long.valueOf (idCarga))
                .setMaxResults (1)
                .getResultList ( );
        response = getResponse (cmcargamasiva);
        if (response != null && response.getCorrespondencia ( ) != null) {
            log.info ("Fin obtenerDataEstadoCargaMasivabyID con total de registros = " + response.getCorrespondencia ( ).getTotalRegistrosCargaMasiva ( ));
        } else {
            log.info ("Fin obtenerDataEstadoCargaMasivabyID con total de registros = 0");
        }

        return response;
    }


    protected ListadoCargasMasivasDTO obtenerDataListadoCargaMasiva() {
        log.info ("Iniciando obtenerDataListadoCargaMasiva");
        ListadoCargasMasivasDTO response;
        List <CmCargaMasiva> cmCargaMasivaList = em.createNamedQuery ("CmCargaMasiva.obtenerDataEstadoCargaMasiva", CmCargaMasiva.class)
                .getResultList ( );
        response = transformCargaMasivaToListadoCargasMasivasDTO (cmCargaMasivaList);
        log.info ("Fin obtenerDataListadoCargaMasiva");
        return response;

    }


    //TODO: pendiente determinar si se usa un transform como en los otros casos
    private ListadoCargasMasivasDTO transformCargaMasivaToListadoCargasMasivasDTO(List <CmCargaMasiva> cmCargaMasivaList) {
        ListadoCargasMasivasDTO listadoCargasMasivasDTO = new ListadoCargasMasivasDTO ( );
        List <CargaMasiva> listado = new ArrayList <> ( );
        for (CmCargaMasiva cmCargaMasiva : cmCargaMasivaList) {
            CargaMasiva cm = new CargaMasiva (Math.toIntExact (cmCargaMasiva.getId ( )), cmCargaMasiva.getNombre ( ));
            log.info ("======================== >  Carga Masiva ID: " + cm.getId ( ));
            log.info ("======================== >  Carga Masiva Nombre: " + cm.getNombreCarga ( ));
            listado.add (cm);
        }
        listadoCargasMasivasDTO.setCargaMasiva (listado);
        return listadoCargasMasivasDTO;
    }

    private List <RegistroCargaMasivaDTO> transformCMRegistrosToRegistroCargaMasivaDTO(List <CmRegistroCargaMasiva> listadoCMRegistros) {
        List <RegistroCargaMasivaDTO> listado = new ArrayList <> ( );

        for (CmRegistroCargaMasiva cmR : listadoCMRegistros) {
            RegistroCargaMasivaDTO registro = new RegistroCargaMasivaDTO (Math.toIntExact (cmR.getId ( )), cmR.getContenido ( ), cmR.getMensajes ( ), cmR.getEstado ( ));
            log.info ("======================== >  Estado del Registro: " + registro.getEstado ( ));
            listado.add (registro);
        }
        return listado;
    }

    private StatusMassiveLoaderProcessResponseDTO transformListCargaMasivaToStatusMassiveLoaderProcessResponseDTO(CmCargaMasiva cmCargaMasiva) {
        StatusMassiveLoaderProcessResponseDTO statusMassiveLoaderProcessResponseDTO = null;
        if (cmCargaMasiva != null) {
            statusMassiveLoaderProcessResponseDTO = new StatusMassiveLoaderProcessResponseDTO ( );
            CorrespondenciaResponse correspondencia = new CorrespondenciaResponse ( );
            correspondencia.setIdCargaMasiva (Math.toIntExact (cmCargaMasiva.getId ( )));
            correspondencia.setNombreCargaMasiva (cmCargaMasiva.getNombre ( ));
            correspondencia.setFechaCreacionCargaMasiva (cmCargaMasiva.getFechaCreacion ( ));
            correspondencia.setEstadoCargaMasiva (cmCargaMasiva.getEstado ( ).name ( ));
            correspondencia.setTotalRegistrosCargaMasiva (cmCargaMasiva.getTotalRegistros ( ));
            correspondencia.setTotalRegistrosExitososCargaMasiva (cmCargaMasiva.getTotalRegistrosExitosos ( ));
            correspondencia.setTotalRegistrosErrorCargaMasiva (cmCargaMasiva.getTotalRegistrosError ( ));
            statusMassiveLoaderProcessResponseDTO.setCorrespondencia (correspondencia);
            log.info ("======================== >  Cantidad de registros procesados: " + correspondencia.getTotalRegistrosCargaMasiva ( ));
        }
        return statusMassiveLoaderProcessResponseDTO;
    }

    private StatusMassiveLoaderProcessResponseDTO getResponse(List <CmCargaMasiva> cmcargamasiva) {
        StatusMassiveLoaderProcessResponseDTO response = new StatusMassiveLoaderProcessResponseDTO ( );
        if (cmcargamasiva != null && !cmcargamasiva.isEmpty ( ) && cmcargamasiva.get (0) != null) {
            response = transformListCargaMasivaToStatusMassiveLoaderProcessResponseDTO (cmcargamasiva.get (0));
            if (response != null) {
                List <CmRegistroCargaMasiva> listadoCMRegistros = em.createNamedQuery ("CmRegistroCargaMasiva.findbyIDCarga", CmRegistroCargaMasiva.class)
                        .setParameter ("ID_CARGA", Long.valueOf (response.getCorrespondencia ( ).getIdCargaMasiva ( )))
                        .getResultList ( );
                List <RegistroCargaMasivaDTO> registros = transformCMRegistrosToRegistroCargaMasivaDTO (listadoCMRegistros);
                response.getCorrespondencia ( ).setRegistrosCargaMasiva (registros);
            }
        }
        return response;
    }

    //[validate] ------------------------------

    protected String validate(List <O> csvDomainList) {

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory ( );
        Validator validator = factory.getValidator ( );
        StringBuilder messageResponse = new StringBuilder ( );
        int index = 1;
        boolean errors = false;

        for (O item : csvDomainList) {

            Set <ConstraintViolation <O>> results = validator.validate (item);
            for (ConstraintViolation valResult : results) {
                messageResponse.append ("Row " + index + " - " + valResult.getPropertyPath ( ) + " : " + valResult.getMessage ( )).append ("<br/>");
                errors = true;
            }
            index++;
        }

        String baseMessage = errors ? "Hay errores de estructura y/o tipos de datos en el archivo, revise el contenido"/*MessageUtil.getMessage("massiveloader.structure.error")*/ : "";
        return baseMessage + messageResponse.toString ( );
    }
}


