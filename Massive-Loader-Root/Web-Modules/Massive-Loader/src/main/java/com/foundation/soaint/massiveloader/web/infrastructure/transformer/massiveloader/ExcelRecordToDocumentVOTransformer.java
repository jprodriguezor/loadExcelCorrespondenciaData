package com.foundation.soaint.massiveloader.web.infrastructure.transformer.massiveloader;

import co.com.foundation.soaint.infrastructure.transformer.Transformer;
import com.foundation.soaint.massiveloader.web.domain.DocumentVO;
import com.foundation.soaint.massiveloader.web.infrastructure.builder.generic.DocumentVoBuilder;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Component;

/**
 * Created by administrador_1 on 06/10/2016.
 */

@Component
public class ExcelRecordToDocumentVOTransformer implements Transformer<Row, DocumentVO> {

    public static final int NO_RADICADO = 0;
    public static final int FECHA_RADICACION = 1;
    public static final int TIPO_COMUNICACION = 2;
    public static final int TIPOLOGIA_DOCUMENTAL = 3;
    public static final int NO_FOLIOS = 4;
    public static final int NO_ANEXOS = 5;
    public static final int ASUNTO = 6;
    public static final int REQUIERE_DIGITALIZAR = 7;
    public static final int REQUIERE_DISTRIBUCIONFISICA = 8;

    @Override
    public DocumentVO transform(Row row) {
        return DocumentVoBuilder.newBuilder()
                .withNoRadicado(row.getCell(NO_RADICADO).getStringCellValue())
                .withFechaRadicacion(row.getCell(FECHA_RADICACION).getDateCellValue())
                .withTipoComunicacion(row.getCell(TIPO_COMUNICACION).getStringCellValue())
                .withTipologiaDocumental(row.getCell(TIPOLOGIA_DOCUMENTAL).getStringCellValue())
                .withNoFolios(row.getCell(NO_FOLIOS).getNumericCellValue())
                .withNoAnexos(row.getCell(NO_ANEXOS).getNumericCellValue())
                .withAsunto(row.getCell(ASUNTO).getStringCellValue())
                .withRequiereDigitalizar(row.getCell(REQUIERE_DIGITALIZAR).getStringCellValue())
                .withRequiereDistribucionFisica(row.getCell(REQUIERE_DISTRIBUCIONFISICA).getStringCellValue())
                .build();
    }

}
