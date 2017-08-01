package co.com.foundation.soaint.documentmanager.web.infrastructure.transformer.massiveloader;

import co.com.foundation.soaint.documentmanager.web.domain.TipologiaDocVO;
import co.com.foundation.soaint.documentmanager.web.infrastructure.builder.generic.TipologiasDocVoBuilder;
import co.com.foundation.soaint.infrastructure.transformer.Transformer;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

/**
 * Created by administrador_1 on 06/10/2016.
 */

@Component
public class CsvRecordToTipologiasDocVOTransformer implements Transformer<CSVRecord, TipologiaDocVO> {

    public static final String CAR_ADMINISTRATIVA = "CARACTE_ADMINISTRATIVA";
    public static final String CAR_LEGAL = "CARACTE_LEGAL";
    public static final String CAR_TECNICA = "CARACTE_TECNICA";
    public static final String EST_TPG_DOC = "ESTADO_TIPO";
    public static final String NOM_TPG_DOC = "NOMBRE_TERMINO";
    public static final String IDE_SOPORTE = "SOPORTE";
    public static final String IDE_TRA_DOCUMENTAL = "TRADICION_DOCUMENTAL";
    public static final String NOT_ALCANCE = "NOTA_ALCANCE";
    public static final String FUE_BIBLIOGRAFICA = "FUENTE_BIBLIOGRAFICA";


    @Override
    public TipologiaDocVO transform(CSVRecord record) {

        return TipologiasDocVoBuilder.newBuilder()
                .withCarAdministrativa(record.get(CAR_ADMINISTRATIVA))
                .withCarLegal(record.get(CAR_LEGAL))
                .withCarTecnica(record.get(CAR_TECNICA))
                .withEstTpgDoc(record.get(EST_TPG_DOC))
                .withNomTpgDoc(record.get(NOM_TPG_DOC))
                .withIdSoporte(new BigInteger(record.get(IDE_SOPORTE)))
                .withIdTraDocumental(new BigInteger(record.get(IDE_TRA_DOCUMENTAL)))
                .withNotAlcance(record.get(NOT_ALCANCE))
                .withFueBibliografica(record.get(FUE_BIBLIOGRAFICA))
                .build();
    }

}
