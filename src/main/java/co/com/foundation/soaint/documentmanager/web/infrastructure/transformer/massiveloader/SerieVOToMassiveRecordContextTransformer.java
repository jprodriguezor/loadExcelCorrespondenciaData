package co.com.foundation.soaint.documentmanager.web.infrastructure.transformer.massiveloader;

import co.com.foundation.soaint.documentmanager.infrastructure.builder.entity.EntityAdmMotCreacionBuilder;
import co.com.foundation.soaint.documentmanager.infrastructure.builder.entity.EntityAdmSerieBuilder;
import co.com.foundation.soaint.documentmanager.infrastructure.massiveloader.domain.MassiveRecordContext;
import co.com.foundation.soaint.documentmanager.persistence.entity.AdmMotCreacion;
import co.com.foundation.soaint.documentmanager.persistence.entity.AdmSerie;
import co.com.foundation.soaint.documentmanager.web.domain.SerieVO;
import co.com.foundation.soaint.infrastructure.transformer.Transformer;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class SerieVOToMassiveRecordContextTransformer implements Transformer<SerieVO, MassiveRecordContext<AdmSerie>> {

    @Override
    public MassiveRecordContext<AdmSerie> transform(SerieVO serie) {

        AdmMotCreacion motCreacion = EntityAdmMotCreacionBuilder.newBuilder()
                .withIdeMotCreacion(serie.getIdMotivo())
                .build();

        EntityAdmSerieBuilder builder = EntityAdmSerieBuilder.newInstance()
                .withActAdministrativo(serie.getActAdministrativo())
                .withCarAdministrativa(Long.parseLong(serie.getCarAdministrativaValue()))
                .withCarLegal(Long.parseLong(serie.getCarLegalValue()))
                .withCarTecnica(Long.parseLong(serie.getCarTecnicaValue()))
                .withCodSerie(serie.getCodSerie())
                .withEstSerie(Integer.valueOf(serie.getEstSerie()))
                .withFueBibliografica(serie.getFueBibliografica())
                .withNomSerie(serie.getNomSerie())
                .withNotAlcance(serie.getNotAlcance())
                .withIdeMotCreacion(motCreacion)
                .withFecCreacion(new Date());

        MassiveRecordContext<AdmSerie> recordContext = new MassiveRecordContext<>(serie.toString(), builder.build());
        return recordContext;
    }
}
