package com.foundation.soaint.massiveloader.web.infrastructure.parser;

import co.com.foundation.soaint.infrastructure.exceptions.BusinessException;
import co.com.foundation.soaint.infrastructure.transformer.Transformer;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.format.CellFormatType;
import org.apache.poi.ss.usermodel.Cell;
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
        while (rowIterator.hasNext()) {
            Row next = rowIterator.next();
            if (!checkIfRowIsEmpty(next))
                excelDomainList.add(transformer.transform(next));
        }


        return excelDomainList;
    }

    private boolean checkIfRowIsEmpty(Row row) {
        if (row == null) {
            return true;
        }
        if (row.getLastCellNum() <= 0) {
            return true;
        }
        for (int cellNum = row.getFirstCellNum(); cellNum < row.getLastCellNum(); cellNum++) {
            Cell cell = row.getCell(cellNum);
            if (cell != null && cell.getCellType() != Cell.CELL_TYPE_BLANK  && StringUtils.isNotBlank(cell.toString())) {
                return false;
            }
        }
        return true;
    }
}
