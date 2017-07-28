package com.foundation.soaint.massiveloader.main;

import com.foundation.soaint.massiveloader.web.domain.DocumentVO;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Main {

    public static final int NO_RADICADO = 0;
    public static final int FECHA_RADICACION = 1;
    public static final int TIPO_COMUNICACION = 2;
    public static final int TIPOLOGIA_DOCUMENTAL = 3;
    public static final int NO_FOLIOS = 4;
    public static final int NO_ANEXOS = 5;
    public static final int ASUNTO = 6;
    public static final int REQUIERE_DIGITALIZAR = 7;
    public static final int REQUIERE_DISTRIBUCIONFISICA = 8;

    public static void main(String[] args) throws Exception {

        parseExcel();

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

    public static DocumentVO transformExcelRowToDocument(Row row) {
        //For each row, iterate through all the columns

        Iterator<Cell> cellIterator = row.cellIterator();
        int pos = 0;
        DocumentVO document = new DocumentVO();
        while (cellIterator.hasNext()) {
            Cell cell = cellIterator.next();
            switch (pos) {
                case NO_RADICADO:
                    document.setNoRadicado(cell.getStringCellValue());
                    break;
                case FECHA_RADICACION:
                    document.setFechaRadicacion(cell.getDateCellValue());
                    break;
                case TIPO_COMUNICACION:
                    document.setTipoComunicacion(cell.getStringCellValue());
                    break;
                case TIPOLOGIA_DOCUMENTAL:
                    document.setTipologiaDocumental(cell.getStringCellValue());
                    break;
                case NO_FOLIOS:
                    document.setNoFolios(cell.getNumericCellValue());
                    break;
                case NO_ANEXOS:
                    document.setNoAnexos(cell.getNumericCellValue());
                    break;
                case ASUNTO:
                    document.setAsunto(cell.getStringCellValue());
                    break;
                case REQUIERE_DIGITALIZAR:
                    document.setRequiereDigitalizar(cell.getStringCellValue().equalsIgnoreCase("TRUE"));
                    break;
                case REQUIERE_DISTRIBUCIONFISICA:
                    document.setRequiereDistribucionFisica(cell.getStringCellValue().equalsIgnoreCase("TRUE"));
                    break;
            }
            pos++;
        }
        return document;
    }
}