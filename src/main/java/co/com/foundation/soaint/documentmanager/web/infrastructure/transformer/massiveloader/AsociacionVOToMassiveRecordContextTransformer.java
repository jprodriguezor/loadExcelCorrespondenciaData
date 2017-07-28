package co.com.foundation.soaint.documentmanager.web.infrastructure.transformer.massiveloader;



import co.com.foundation.soaint.documentmanager.infrastructure.massiveloader.domain.MassiveRecordContext;
import co.com.foundation.soaint.documentmanager.persistence.entity.AdmSerSubserTpg;
import co.com.foundation.soaint.documentmanager.persistence.entity.AdmSerie;
import co.com.foundation.soaint.documentmanager.persistence.entity.AdmSubserie;
import co.com.foundation.soaint.documentmanager.persistence.entity.AdmTpgDoc;
import co.com.foundation.soaint.documentmanager.web.domain.AsociacionVO;
import co.com.foundation.soaint.documentmanager.infrastructure.builder.entity.EntityAdmSerSubserTpgBuilder;
import co.com.foundation.soaint.documentmanager.infrastructure.builder.entity.EntityAdmSerieBuilder;
import co.com.foundation.soaint.documentmanager.infrastructure.builder.entity.EntityAdmSubserieBuilder;
import co.com.foundation.soaint.documentmanager.infrastructure.builder.entity.EntityAdmTpgDocBuilder;
import co.com.foundation.soaint.infrastructure.transformer.Transformer;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by sarias on 13/10/2016.
 */

@Component
public class AsociacionVOToMassiveRecordContextTransformer implements Transformer<AsociacionVO, MassiveRecordContext<AdmSerSubserTpg>> {


    @Override
    public MassiveRecordContext<AdmSerSubserTpg> transform(AsociacionVO asociacion) {

        AdmSerie serie = EntityAdmSerieBuilder.newInstance()
        .withIdeSerie(asociacion.getIdeSerie()).build();

        AdmSubserie subserie = EntityAdmSubserieBuilder.newInstance()
                .withIdeSubserie(asociacion.getIdeSubserie()).build();

        AdmTpgDoc tipologia = EntityAdmTpgDocBuilder.newBuilder()
                .withetIdeTpgDoc(asociacion.getIdeTpgDoc()).build();

        EntityAdmSerSubserTpgBuilder builder = EntityAdmSerSubserTpgBuilder.newInstance()
                .withFecCreacion(new Date())
                .withIdeSerie(serie)
                .withIdeSubserie(subserie)
                .withIdeTpgDoc(tipologia);

        MassiveRecordContext<AdmSerSubserTpg> recordContext = new MassiveRecordContext<>(asociacion.toString(), builder.build());
        return recordContext;
    }
}
