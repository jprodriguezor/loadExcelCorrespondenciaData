package com.foundation.soaint.massiveloader.web.infrastructure.transformer.massiveloader;

import co.com.foundation.soaint.infrastructure.transformer.Transformer;
import com.foundation.soaint.massiveloader.web.domain.CorrespondenciaVO;
import com.foundation.soaint.massiveloader.web.domain.DocumentVO;
import com.foundation.soaint.massiveloader.web.infrastructure.builder.generic.CorrespondenciaVoBuilder;
import com.foundation.soaint.massiveloader.web.infrastructure.builder.generic.DocumentVoBuilder;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Component;

/**
 * Created by administrador_1 on 06/10/2016.
 */

@Component
public class DocumentVOToCorrespondenciaVOTransformer implements Transformer<DocumentVO, CorrespondenciaVO> {

    @Override
    public CorrespondenciaVO transform(DocumentVO documentVO) {
        return CorrespondenciaVoBuilder.newBuilder()
                .withNoRadicado(documentVO.getNoRadicado())
                .withFechaRadicacion(documentVO.getFechaRadicacion())
                .withTipoComunicacion(documentVO.getTipoComunicacion())
                .withTipologiaDocumental(documentVO.getTipologiaDocumental())
                .withNoFolios(documentVO.getNoFolios())
                .withNoAnexos(documentVO.getNoAnexos())
                .withAsunto(documentVO.getAsunto())
                .withRequiereDigitalizar(documentVO.getRequiereDigitalizar())
                .withRequiereDistribucionFisica(documentVO.getRequiereDistribucionFisica())
                .build();
    }

}
