package com.foundation.soaint.massiveloader.web.infrastructure.massiveloader;

import co.com.foundation.soaint.infrastructure.exceptions.BusinessException;
import co.com.foundation.soaint.infrastructure.exceptions.ExceptionBuilder;
import co.com.foundation.soaint.infrastructure.common.MessageUtil;
import co.com.foundation.soaint.documentmanager.infrastructure.massiveloader.LoaderAsyncWorker;
import co.com.foundation.soaint.documentmanager.infrastructure.massiveloader.MassiveLoaderType;
import co.com.foundation.soaint.documentmanager.infrastructure.massiveloader.domain.CallerContext;
import co.com.foundation.soaint.documentmanager.infrastructure.massiveloader.executor.LoaderExecutor;
import com.foundation.soaint.massiveloader.web.domain.DocumentVO;
import com.foundation.soaint.massiveloader.web.infrastructure.common.MasiveLoaderResponse;
import co.com.foundation.soaint.infrastructure.transformer.Transformer;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
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

    protected List parseExcel1() throws IOException {

        FileInputStream file = new FileInputStream(new File("C:\\Users\\g2o\\Desktop\\Plantilla Cargue Contigencia-Cuba.xlsx"));

        //Create Workbook instance holding reference to .xlsx file
        XSSFWorkbook workbook = new XSSFWorkbook(file);

        //Get first/desired sheet from the workbook
        XSSFSheet sheet = workbook.getSheetAt(0);

        //Iterate through each rows one by one
        Iterator<Row> rowIterator = sheet.iterator();
        while (rowIterator.hasNext())
        {
            Row row = rowIterator.next();
            //For each row, iterate through all the columns
            Iterator<Cell> cellIterator = row.cellIterator();

            while (cellIterator.hasNext())
            {
                Cell cell = cellIterator.next();
                //Check the cell type and format accordingly
                switch (cell.getCellType())
                {
                    case Cell.CELL_TYPE_NUMERIC:
                        System.out.print(cell.getNumericCellValue() + "\t");
                        break;
                    case Cell.CELL_TYPE_STRING:
                        System.out.print(cell.getStringCellValue() + "\t");
                        break;
                }
            }
            System.out.println("");
        }
        file.close();
        return new ArrayList<>();
    }


    public static List parseExcel() throws IOException {

        FileInputStream file = new FileInputStream(new File("C:\\Users\\g2o\\Desktop\\Plantilla Cargue Contigencia-Cuba.xlsx"));

        //Create Workbook instance holding reference to .xlsx file
        XSSFWorkbook workbook = new XSSFWorkbook(file);

        //Get first/desired sheet from the workbook
        XSSFSheet sheet = workbook.getSheetAt(0);

        int startPosition = 2;

        //Positionate
        Iterator<Row> rowIterator = sheet.iterator();
        for (; startPosition > 0; --startPosition) {
            rowIterator.next(); // ignore the first x cell.getStringCellValue()s
        }
        List<DocumentVO> documentList = new ArrayList();
        while (rowIterator.hasNext()) {
            DocumentVO temp = transformExcelRowToDocument(rowIterator.next());
            documentList.add(temp);
        }
        file.close();
        System.out.println(documentList.size());
        return documentList;
    }

    //TODO: delete
    private static DocumentVO transformExcelRowToDocument(Row next) {
        return null;
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


