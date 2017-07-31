package com.foundation.soaint.massiveloader.web.infrastructure.parser;

import co.com.foundation.soaint.infrastructure.exceptions.BusinessException;
import co.com.foundation.soaint.infrastructure.exceptions.ExceptionBuilder;
import co.com.foundation.soaint.infrastructure.transformer.Transformer;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by g2o on 31-Jul-17.
 */
public class CSVParser<O> extends DocumentParser<O, CSVRecord> {

    private static final char DELIMITER = ';';
    @Override
    public List<O> parse(MultipartFile file, Transformer<CSVRecord, O> transformer) throws IOException, BusinessException {

        org.apache.commons.csv.CSVParser parser = CSVFormat.DEFAULT.withFirstRecordAsHeader()
                .withDelimiter(DELIMITER)
                        //.withHeader(getHeaderTemplate())
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
}
