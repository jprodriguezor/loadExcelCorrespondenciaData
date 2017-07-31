/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foundation.soaint.massiveloader.web.infrastructure.builder.generic;

import co.com.foundation.soaint.infrastructure.builder.Builder;
import com.foundation.soaint.massiveloader.web.domain.DocumentVO;

import java.util.Date;

/**
 * @author malzate on 11/09/2016.
 */
public class DocumentVoBuilder implements Builder<DocumentVO> {

    String noRadicado;
    Date fechaRadicacion;
    String tipoComunicacion;
    String tipologiaDocumental;
    Double noFolios;
    Double noAnexos;
    String asunto;
    String requiereDigitalizar;
    String requiereDistribucionFisica;

    public DocumentVoBuilder withNoRadicado(String noRadicado) {
        this.noRadicado = noRadicado;
        return this;
    }

    public DocumentVoBuilder withFechaRadicacion(Date fechaRadicacion) {
        this.fechaRadicacion = fechaRadicacion;
        return this;
    }

    public DocumentVoBuilder withTipoComunicacion(String tipoComunicacion) {
        this.tipoComunicacion = tipoComunicacion;
        return this;
    }

    public DocumentVoBuilder withTipologiaDocumental(String tipologiaDocumental) {
        this.tipologiaDocumental = tipologiaDocumental;
        return this;
    }

    public DocumentVoBuilder withNoFolios(Double noFolios) {
        this.noFolios = noFolios;
        return this;
    }

    public DocumentVoBuilder withNoAnexos(Double noAnexos) {
        this.noAnexos = noAnexos;
        return this;
    }

    public DocumentVoBuilder withAsunto(String asunto) {
        this.asunto = asunto;
        return this;
    }

    public DocumentVoBuilder withRequiereDigitalizar(String requiereDigitalizar) {
        this.requiereDigitalizar = requiereDigitalizar;
        return this;
    }

    public DocumentVoBuilder withRequiereDistribucionFisica(String requiereDistribucionFisica) {
        this.requiereDistribucionFisica = requiereDistribucionFisica;
        return this;
    }

    public DocumentVoBuilder() {
    }

    public static DocumentVoBuilder newBuilder() {
        return new DocumentVoBuilder();
    }

    @Override
    public DocumentVO build() {
        return new DocumentVO(noRadicado, fechaRadicacion, tipoComunicacion, tipologiaDocumental,
                noFolios, noAnexos, asunto, requiereDigitalizar,
                requiereDistribucionFisica);
    }

}