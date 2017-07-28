package com.soaint.massive.loader.web.infrastructure.transformer.massiveloader;

import co.com.foundation.soaint.documentmanager.infrastructure.builder.entity.EntityAdmMotCreacionBuilder;
import co.com.foundation.soaint.documentmanager.infrastructure.builder.entity.EntityAdmSerieBuilder;
import co.com.foundation.soaint.documentmanager.infrastructure.builder.entity.EntityAdmSubserieBuilder;
import co.com.foundation.soaint.documentmanager.infrastructure.massiveloader.domain.MassiveRecordContext;
import co.com.foundation.soaint.documentmanager.persistence.entity.AdmMotCreacion;
import co.com.foundation.soaint.documentmanager.persistence.entity.AdmSerie;
import co.com.foundation.soaint.documentmanager.persistence.entity.AdmSubserie;
import com.soaint.massive.loader.web.domain.SubserieVO;

import co.com.foundation.soaint.infrastructure.transformer.Transformer;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class SubserieVOToMassiveRecordContextTransformer implements Transformer<SubserieVO, MassiveRecordContext<AdmSubserie>> {

    @Override
    public MassiveRecordContext<AdmSubserie> transform(SubserieVO subserie) {

        AdmMotCreacion motCreacion = EntityAdmMotCreacionBuilder.newBuilder()
                .withIdeMotCreacion(subserie.getIdMotivo())
                .build();

        AdmSerie serie = EntityAdmSerieBuilder.newInstance()
                .withIdeSerie(subserie.getIdSerie())
                .build();

        EntityAdmSubserieBuilder builder = EntityAdmSubserieBuilder.newInstance()
                .withCodSubserie(subserie.getCodSubserie())
                .withNomSubserie(subserie.getNomSubserie())
                .withActAdministrativo(subserie.getActAdministrativo())
                .withNotAlcance(subserie.getNotAlcance())
                .withCarAdministrativa(Long.parseLong(subserie.getCarAdministrativaValue()))
                .withCarLegal(Long.parseLong(subserie.getCarLegalValue()))
                .withCarTecnica(Long.parseLong(subserie.getCarTecnicaValue()))
                .withEstSubserie(Integer.valueOf(subserie.getEstSubserie()))
                .withFueBibliografica(subserie.getFueBibliografica())
                .withIdeSerie(serie)
                .withIdeMotCreacion(motCreacion)
                .withFecCreacion(new Date());


        MassiveRecordContext<AdmSubserie> recordContext = new MassiveRecordContext<>(subserie.toString(), builder.build());
        return recordContext;
    }
}
