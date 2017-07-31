/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foundation.soaint.massiveloader.web.infrastructure.builder.generic;

import co.com.foundation.soaint.infrastructure.builder.Builder;
import com.foundation.soaint.massiveloader.web.domain.CorrespondenciaVO;
import com.foundation.soaint.massiveloader.web.domain.DocumentVO;

import java.util.Date;

/**
 * @author malzate on 11/09/2016.
 */
public class CorrespondenciaVoBuilder implements Builder<CorrespondenciaVO> {

    String noRadicado;
    Date fechaRadicacion;
    String tipoComunicacion;
    String tipologiaDocumental;
    Double noFolios;
    Double noAnexos;
    String asunto;
    Boolean requiereDigitalizar;
    Boolean requiereDistribucionFisica;

    public CorrespondenciaVoBuilder withNoRadicado(String noRadicado) {
        this.noRadicado = noRadicado;
        return this;
    }

    public CorrespondenciaVoBuilder withFechaRadicacion(Date fechaRadicacion) {
        this.fechaRadicacion = fechaRadicacion;
        return this;
    }

    public CorrespondenciaVoBuilder withTipoComunicacion(String tipoComunicacion) {
        this.tipoComunicacion = tipoComunicacion;
        return this;
    }

    public CorrespondenciaVoBuilder withTipologiaDocumental(String tipologiaDocumental) {
        this.tipologiaDocumental = tipologiaDocumental;
        return this;
    }

    public CorrespondenciaVoBuilder withNoFolios(Double noFolios) {
        this.noFolios = noFolios;
        return this;
    }

    public CorrespondenciaVoBuilder withNoAnexos(Double noAnexos) {
        this.noAnexos = noAnexos;
        return this;
    }

    public CorrespondenciaVoBuilder withAsunto(String asunto) {
        this.asunto = asunto;
        return this;
    }

    public CorrespondenciaVoBuilder withRequiereDigitalizar(Boolean requiereDigitalizar) {
        this.requiereDigitalizar = requiereDigitalizar;
        return this;
    }

    public CorrespondenciaVoBuilder withRequiereDistribucionFisica(Boolean requiereDistribucionFisica) {
        this.requiereDistribucionFisica = requiereDistribucionFisica;
        return this;
    }

    public CorrespondenciaVoBuilder() {
    }

    public static CorrespondenciaVoBuilder newBuilder() {
        return new CorrespondenciaVoBuilder();
    }

    @Override
    public CorrespondenciaVO build() {
        return new CorrespondenciaVO(noRadicado, fechaRadicacion, tipoComunicacion, tipologiaDocumental,
                noFolios, noAnexos, asunto, requiereDigitalizar,
                requiereDistribucionFisica);
    }

}