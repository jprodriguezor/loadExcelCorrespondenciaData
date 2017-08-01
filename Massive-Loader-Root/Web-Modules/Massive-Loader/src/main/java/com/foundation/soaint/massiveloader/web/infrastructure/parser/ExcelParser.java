package com.foundation.soaint.massiveloader.web.infrastructure.parser;

import co.com.foundation.soaint.infrastructure.exceptions.BusinessException;
import co.com.foundation.soaint.infrastructure.transformer.Transformer;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by g2o on 31-Jul-17.
 */
public class ExcelParser<O> extends DocumentParser<O, Row> {

    @Value("${excel.parser.start.position}")
    private Integer startPosition = 2;

    @Override
    public List<O> parse(MultipartFile file, Transformer<Row, O> transformer) throws IOException, BusinessException {
        //FileInputStream file = new FileInputStream(new File("C:\\Users\\g2o\\Desktop\\Plantilla Cargue Contigencia-Cuba.xlsx"));

        //Create Workbook instance holding reference to .xlsx file
        XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());

        //Get first/desired sheet from the workbook
        XSSFSheet sheet = workbook.getSheetAt(0);

        //Positionate
        Iterator<Row> rowIterator = sheet.iterator();
        for (; startPosition > 0; --startPosition) {
            rowIterator.next(); // ignore the first x cell.getStringCellValue()s
        }
        List<O> excelDomainList = new ArrayList();
        while (rowIterator.hasNext())
            excelDomainList.add(transformer.transform(rowIterator.next()));

        /*if (excelDomainList.size() != excelDomainList.size())
            throw ExceptionBuilder.newBuilder()
                    .withMessage("massiveloader.structure.error")
                    .buildBusinessException();*/

        return excelDomainList;
    }
}
