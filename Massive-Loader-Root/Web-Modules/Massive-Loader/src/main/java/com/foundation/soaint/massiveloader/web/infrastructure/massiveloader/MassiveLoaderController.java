package com.foundation.soaint.massiveloader.web.infrastructure.massiveloader;

import co.com.foundation.soaint.documentmanager.domain.ComunicacionOficialContainerDTO;
import co.com.foundation.soaint.documentmanager.infrastructure.massiveloader.LoaderAsyncWorker;
import co.com.foundation.soaint.documentmanager.infrastructure.massiveloader.MassiveLoaderType;
import co.com.foundation.soaint.documentmanager.infrastructure.massiveloader.domain.CallerContext;
import co.com.foundation.soaint.documentmanager.infrastructure.massiveloader.domain.MassiveRecordContext;
import co.com.foundation.soaint.documentmanager.infrastructure.massiveloader.executor.LoaderExecutor;
import co.com.foundation.soaint.documentmanager.persistence.entity.constants.RegistroCargaMasivaStatus;
import co.com.foundation.soaint.infrastructure.common.MessageUtil;
import co.com.foundation.soaint.infrastructure.exceptions.BusinessException;
import co.com.foundation.soaint.infrastructure.transformer.Transformer;
import com.foundation.soaint.massiveloader.web.infrastructure.common.*;
import com.foundation.soaint.massiveloader.web.infrastructure.parser.DocumentParser;
import com.foundation.soaint.massiveloader.web.infrastructure.parser.DocumentParserFactory;
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
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by administrador_1 on 01/10/2016.
 */
@Log4j2
public abstract class MassiveLoaderController<O, E> {


    //private static Logger LOGGER = LogManager.getLogger(MassiveLoaderController.class.getName());

    @Autowired
    protected LoaderAsyncWorker<E> loaderAsyncWorker;

    @PersistenceContext
    protected EntityManager em;

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

    protected StatusMassiveLoaderProcessResponseDTO obtenerDataEstadoCargaMasiva(){
        StatusMassiveLoaderProcessResponseDTO response = new StatusMassiveLoaderProcessResponseDTO();
        response.setIdCargaMasiva(1);
        response.setEstadoCargaMasiva("estado");
        response.setFechaCreacionCargaMasiva(new Date());
        response.setNombreCargaMasiva("carga 1");
        response.setTotalRegistrosCargaMasiva(8);
        response.setTotalRegistrosExitososCargaMasiva(5);
        response.setTotalRegistrosErrorCargaMasiva(3);
        List<RegistroCargaMasivaDTO> registros = new ArrayList<>();
        RegistroCargaMasivaDTO registro = new RegistroCargaMasivaDTO();
        registro.setId(1);
        registro.setContenido("contenido");
        registro.setEstado(RegistroCargaMasivaStatus.COMPLETADO_CORRECTAMENTE);
        registros.add(0,registro);
        response.setRegistrosCargaMasiva(registros);
        return response;

    }

    protected StatusMassiveLoaderProcessResponseDTO obtenerDataEstadoCargaMasivabyID(int idCarga){
        StatusMassiveLoaderProcessResponseDTO response = new StatusMassiveLoaderProcessResponseDTO();
        if (idCarga == 1){
            response.setIdCargaMasiva(1);
            response.setNombreCargaMasiva("carga 1");
        }else{
            response.setIdCargaMasiva(2);
            response.setNombreCargaMasiva("carga 2");
        }

        response.setEstadoCargaMasiva("estado");
        response.setFechaCreacionCargaMasiva(new Date());
        response.setTotalRegistrosCargaMasiva(8);
        response.setTotalRegistrosExitososCargaMasiva(5);
        response.setTotalRegistrosErrorCargaMasiva(3);
        List<RegistroCargaMasivaDTO> registros = new ArrayList<>();
        RegistroCargaMasivaDTO registro = new RegistroCargaMasivaDTO();
        registro.setId(1);
        registro.setContenido("contenido");
        registro.setEstado(RegistroCargaMasivaStatus.COMPLETADO_CORRECTAMENTE);
        registros.add(registro);
        response.setRegistrosCargaMasiva(registros);
        return response;

    }

    protected ListadoCargasMasivasDTO obtenerDataListadoCargaMasiva(){
        ListadoCargasMasivasDTO response = new ListadoCargasMasivasDTO();
        List<CargaMasiva> carga = new ArrayList<>();
        CargaMasiva cargamasiva = new CargaMasiva();
        cargamasiva.setId(1);
        cargamasiva.setNombreCarga("carga 1");
        carga.add(cargamasiva);
        CargaMasiva cargamasiva2 = new CargaMasiva();
        cargamasiva2.setId(2);
        cargamasiva2.setNombreCarga("carga 2");
        carga.add(cargamasiva2);
        response.setCargaMasiva(carga);
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


    //[template] ------------------------------

    //public abstract String[] getHeaderTemplate();

}


