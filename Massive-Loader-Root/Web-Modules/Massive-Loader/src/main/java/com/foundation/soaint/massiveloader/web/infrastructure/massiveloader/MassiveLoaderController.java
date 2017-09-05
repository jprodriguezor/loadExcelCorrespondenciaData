package com.foundation.soaint.massiveloader.web.infrastructure.massiveloader;

import co.com.foundation.soaint.documentmanager.domain.ComunicacionOficialContainerDTO;
import co.com.foundation.soaint.documentmanager.infrastructure.massiveloader.LoaderAsyncWorker;
import co.com.foundation.soaint.documentmanager.infrastructure.massiveloader.MassiveLoaderType;
import co.com.foundation.soaint.documentmanager.infrastructure.massiveloader.domain.CallerContext;
import co.com.foundation.soaint.documentmanager.infrastructure.massiveloader.domain.MassiveRecordContext;
import co.com.foundation.soaint.documentmanager.infrastructure.massiveloader.executor.LoaderExecutor;
import co.com.foundation.soaint.infrastructure.common.MessageUtil;
import co.com.foundation.soaint.infrastructure.exceptions.BusinessException;
import co.com.foundation.soaint.infrastructure.transformer.Transformer;
import com.foundation.soaint.massiveloader.web.infrastructure.common.MasiveLoaderResponse;
import com.foundation.soaint.massiveloader.web.infrastructure.parser.DocumentParser;
import com.foundation.soaint.massiveloader.web.infrastructure.parser.DocumentParserFactory;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

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


    //private static Logger LOGGER = LogManager.getLogger(MassiveLoaderController.class.getName());

    @Autowired
    protected LoaderAsyncWorker<E> loaderAsyncWorker;

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


