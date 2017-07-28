package co.com.foundation.soaint.documentmanager.web.infrastructure.transformer.massiveloader;

import co.com.foundation.soaint.documentmanager.web.domain.SubserieVO;
import co.com.foundation.soaint.documentmanager.web.infrastructure.builder.generic.SubserieVoBuilder;
import co.com.foundation.soaint.infrastructure.transformer.Transformer;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.Date;

/**
 * Created by administrador_1 on 06/10/2016.
 */

@Component
public class CsvRecordToSubserieVOTransformer implements Transformer<CSVRecord, SubserieVO> {

    public static final String ACT_ADMINISTRATIVO = "ACTO_ADMINISTRATIVO_CREACION";
    public static final String CAR_ADMINISTRATIVA = "CARACTE_ADMINISTRATIVA";
    public static final String CAR_LEGAL = "CARACTE_LEGAL";
    public static final String CAR_TECNICA = "CARACTE_TECNICA";
    public static final String COD_SUBSERIE = "CODIGO";
    public static final String EST_SUBSERIE = "ESTADO_SUBSERIE";
    public static final String FUE_BIBLIOGRAFICA = "FUENTE_BIBLIOGRAFICA";
    public static final String NOM_SUBSERIE = "NOMBRE_SUBSERIE";
    public static final String NOT_ALCANCE = "NOTA_ALCANCE";
    public static final String ID_MOTIVO = "MOTIVO_CREACION";
    public static final String COD_SERIE = "CODIGO_SERIE_DOCUMENTAL";
    //public static final String ID_SERIE = "ID_SERIE";


    @Override
    public SubserieVO transform(CSVRecord record) {


        return SubserieVoBuilder.newBuilder()
                .withCodSubserie(record.get(COD_SUBSERIE))
                .withNomSubserie(record.get(NOM_SUBSERIE))
                .withActAdministrativo(record.get(ACT_ADMINISTRATIVO))
                .withNotAlcance(record.get(NOT_ALCANCE))
                .withCarAdministrativa(record.get(CAR_ADMINISTRATIVA))
                .withCarLegal(record.get(CAR_LEGAL))
                .withCarTecnica(record.get(CAR_TECNICA))
                .withEstSubserie(record.get(EST_SUBSERIE))
                .withFueBibliografica(record.get(FUE_BIBLIOGRAFICA))
                .withIdSerie( new BigInteger(record.get(COD_SERIE)))
                .withIdMotivo(new BigInteger(record.get(ID_MOTIVO)))
                .build();
    }



}
