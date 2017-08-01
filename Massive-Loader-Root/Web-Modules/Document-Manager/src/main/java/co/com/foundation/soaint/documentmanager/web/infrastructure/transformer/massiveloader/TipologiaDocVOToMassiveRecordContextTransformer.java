package co.com.foundation.soaint.documentmanager.web.infrastructure.transformer.massiveloader;

import co.com.foundation.soaint.documentmanager.infrastructure.builder.entity.EntityAdmSoporteBuilder;
import co.com.foundation.soaint.documentmanager.infrastructure.builder.entity.EntityAdmTpgDocBuilder;
import co.com.foundation.soaint.documentmanager.infrastructure.builder.entity.EntityAdmTradDocBuilder;
import co.com.foundation.soaint.documentmanager.infrastructure.massiveloader.domain.MassiveRecordContext;
import co.com.foundation.soaint.documentmanager.persistence.entity.AdmSoporte;
import co.com.foundation.soaint.documentmanager.persistence.entity.AdmTpgDoc;
import co.com.foundation.soaint.documentmanager.persistence.entity.AdmTradDoc;
import co.com.foundation.soaint.documentmanager.web.domain.TipologiaDocVO;

import co.com.foundation.soaint.infrastructure.transformer.Transformer;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TipologiaDocVOToMassiveRecordContextTransformer implements Transformer<TipologiaDocVO, MassiveRecordContext<AdmTpgDoc>> {

    @Override
    public MassiveRecordContext<AdmTpgDoc> transform(TipologiaDocVO tipologiaDoc) {

        AdmTradDoc tradicionDoc = EntityAdmTradDocBuilder.newBuilder()
                .withIdeTradDoc(tipologiaDoc.getIdTraDocumental())
                .build();

        AdmSoporte soporte = EntityAdmSoporteBuilder.newBuilder()
                .withIdeSoporte(tipologiaDoc.getIdSoporte())
                .build();

        EntityAdmTpgDocBuilder builder = EntityAdmTpgDocBuilder.newBuilder()
                .withetCarLegal(Long.parseLong(tipologiaDoc.getCarLegalValue()))
                .withetCarTecnica(Long.parseLong(tipologiaDoc.getCarTecnicaValue()))
                .withetCarAdministrativa(Long.parseLong(tipologiaDoc.getCarAdministrativaValue()))
                .withetNotAlcance(tipologiaDoc.getNotAlcance())
                .withetFueBibliografica(tipologiaDoc.getFueBibliografica())
                .withetIdeTraDocumental(tradicionDoc)
                .withetIdeSoporte(soporte)
                .withetEstTpgDoc(Integer.valueOf(tipologiaDoc.getEstTpgDoc()))
                .withetNomTpgDoc(tipologiaDoc.getNomTpgDoc().trim())
                .withetFecCreacion(new Date());

        MassiveRecordContext<AdmTpgDoc> recordContext = new MassiveRecordContext<>(tipologiaDoc.toString(), builder.build());
        return recordContext;
    }
}
