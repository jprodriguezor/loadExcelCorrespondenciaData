package com.foundation.soaint.massiveloader.web.infrastructure.builder.generic;

import co.com.foundation.soaint.infrastructure.builder.Builder;
import com.foundation.soaint.massiveloader.web.domain.DocumentVO;

import java.util.Date;

public class DocumentVoBuilder implements Builder<DocumentVO> {
    private String noRadicado;
    private Date fechaRadicacion;
    private String tipoComunicacion;
    private String tipologiaDocumental;
    private Double noFolios;
    private Double noAnexos;
    private String asunto;
    private String requiereDigitalizar;
    private String requiereDistribucionFisica;
    private String personaRemite;
    private String razonSocial;
    private String nombre;
    private String sedeAdministrativaRemitenteInterno;
    private String dependenciaRemitenteInterno;
    private String sedeAdministrativaDestinatario;
    private String dependenciaDestinatario;

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

    public DocumentVoBuilder withPersonaRemite(String personaRemite) {
        this.personaRemite = personaRemite;
        return this;
    }

    public DocumentVoBuilder withRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
        return this;
    }

    public DocumentVoBuilder withNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public DocumentVoBuilder withSedeAdministrativaRemitenteInterno(String sedeAdministrativaRemitenteInterno) {
        this.sedeAdministrativaRemitenteInterno = sedeAdministrativaRemitenteInterno;
        return this;
    }

    public DocumentVoBuilder withDependenciaRemitenteInterno(String dependenciaRemitenteInterno) {
        this.dependenciaRemitenteInterno = dependenciaRemitenteInterno;
        return this;
    }

    public DocumentVoBuilder withSedeAdministrativaDestinatario(String sedeAdministrativaDestinatario) {
        this.sedeAdministrativaDestinatario = sedeAdministrativaDestinatario;
        return this;
    }

    public DocumentVoBuilder withDependenciaDestinatario(String dependenciaDestinatario) {
        this.dependenciaDestinatario = dependenciaDestinatario;
        return this;
    }

    public static DocumentVoBuilder newBuilder() {
        return new DocumentVoBuilder();
    }

    @Override
    public DocumentVO build() {
        return new DocumentVO(noRadicado, fechaRadicacion, tipoComunicacion, tipologiaDocumental, noFolios, noAnexos,
                asunto, requiereDigitalizar, requiereDistribucionFisica, personaRemite, razonSocial, nombre,
                sedeAdministrativaRemitenteInterno, dependenciaRemitenteInterno, sedeAdministrativaDestinatario,
                dependenciaDestinatario);
    }
}