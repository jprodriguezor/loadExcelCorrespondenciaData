package co.com.foundation.soaint.documentmanager.web.infrastructure.transformer.massiveloader;

import co.com.foundation.soaint.documentmanager.web.domain.AsociacionVO;
import co.com.foundation.soaint.documentmanager.web.infrastructure.builder.generic.AsociacionVoBuilder;
import co.com.foundation.soaint.infrastructure.transformer.Transformer;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.Date;

/**
 * Created by sarias on 13/10/2016.
 */
@Component
public class CsvRecordToAsociacionVOTransformer  implements Transformer<CSVRecord, AsociacionVO> {

    public static final String IDE_SERIE = "IDE_SERIE";
    public static final String IDE_SUBSERIE = "IDE_SUBSERIE";
    public static final String IDE_TPG_DOC = "IDE_TPG_DOC";



    @Override
    public AsociacionVO transform(CSVRecord record) {

        return AsociacionVoBuilder.newBuilder()

                .withIdeSerie(new BigInteger(record.get(IDE_SERIE)))

                .withIdeSubserie(new BigInteger(record.get(IDE_SUBSERIE)))
                .withIdeTpgDoc(new BigInteger(record.get(IDE_TPG_DOC)))
                .withFecCreacion(new Date())
                .build();
    }
}
