package co.com.foundation.soaint.documentmanager.web.infrastructure.massiveloader;

import co.com.foundation.soaint.infrastructure.exceptions.BusinessException;
import co.com.foundation.soaint.infrastructure.exceptions.ExceptionBuilder;
import co.com.foundation.soaint.infrastructure.common.MessageUtil;
import co.com.foundation.soaint.documentmanager.infrastructure.massiveloader.LoaderAsyncWorker;
import co.com.foundation.soaint.documentmanager.infrastructure.massiveloader.MassiveLoaderType;
import co.com.foundation.soaint.documentmanager.infrastructure.massiveloader.domain.CallerContext;
import co.com.foundation.soaint.documentmanager.infrastructure.massiveloader.executor.LoaderExecutor;
import co.com.foundation.soaint.documentmanager.web.infrastructure.common.MasiveLoaderResponse;
import co.com.foundation.soaint.infrastructure.transformer.Transformer;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by administrador_1 on 01/10/2016.
 */

public abstract class MassiveLoaderController<O, E> {

    private static final char DELIMITER = ';';

    private static Logger LOGGER = LogManager.getLogger(MassiveLoaderController.class.getName());

    @Autowired
    protected LoaderAsyncWorker<E> asyncWorker;


    //[generic load processing] ------------------------------

    protected MasiveLoaderResponse processGenericLoad(final MultipartFile file, final LoaderExecutor<E> executor,
                                                      final MassiveLoaderType type,
                                                      final Transformer<CSVRecord, O> voTransformer,
                                                      final Transformer<O, E> massiveRecordTransformer,
                                                      final CallerContext callerContext) {

        MasiveLoaderResponse response;

        try {

            LOGGER.info("starting massive loading for file: " + file.getName());
            List<O> records = parse(file, voTransformer);
            response = MasiveLoaderResponse.newInstance(validate(records));

            if (response.isSuccess()) {

                List<E> contextInfoList = new ArrayList<>();
                records.stream().forEach((O vo) -> {
                    contextInfoList.add(massiveRecordTransformer.transform(vo));
                });

                asyncWorker.process(executor, contextInfoList, type, callerContext);
            }

        } catch (BusinessException be) {
            LOGGER.error("business exception in massive loading for file: " + file.getName(), be);
            response = MasiveLoaderResponse.newInstance(be.getMessage());
        } catch (NumberFormatException e) {
            LOGGER.error("number format exception in massive loading for file: " + file.getName(), e);
            response = MasiveLoaderResponse.newInstance(MessageUtil.getMessage("massiveloader.structure.error"));
        } catch (Exception e) {
            LOGGER.error("generic exception in massive loading for file: " + file.getName(), e);
            response = MasiveLoaderResponse.newInstance(MessageUtil.getMessage("massiveloader.generic.error"));
        }

        LOGGER.info("ending massive loading for file: " + file.getName());
        return response;
    }


    //[core] ------------------------------

    protected List<O> parse(MultipartFile file, Transformer<CSVRecord, O> transformer) throws IOException, BusinessException {

        CSVParser parser = CSVFormat.DEFAULT.withFirstRecordAsHeader()
                .withDelimiter(DELIMITER)
                .withHeader(getHeaderTemplate())
                .parse(new StringReader(new String(file.getBytes())));

        List<O> csvDomainList = new ArrayList<>();

        List<CSVRecord> records = parser.getRecords();

        records.stream()
                .filter((CSVRecord item) -> item.isConsistent())
                .forEach((CSVRecord item) -> csvDomainList.add(transformer.transform(item)));

        if (records.size() != csvDomainList.size())
            throw ExceptionBuilder.newBuilder()
                    .withMessage("massiveloader.structure.error")
                    .buildBusinessException();

        return csvDomainList;
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

        String baseMessage = errors ? MessageUtil.getMessage("massiveloader.structure.error") : "";
        return baseMessage + messageResponse.toString();
    }


    //[template] ------------------------------

    public abstract String[] getHeaderTemplate();

}


