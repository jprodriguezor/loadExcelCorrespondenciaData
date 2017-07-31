package com.foundation.soaint.massiveloader.web.domain;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by g2o on 24-Jul-17.
 */
@Data
public class DocumentVO {
    @NotNull
    String noRadicado;
    @NotNull
    Date fechaRadicacion;
    @NotNull
    String tipoComunicacion;
    @NotNull
    String tipologiaDocumental;
    @NotNull
    Double noFolios;
    @NotNull
    Double noAnexos;
    @NotNull
    String asunto;
    @NotNull
    String requiereDigitalizar;
    @NotNull
    String requiereDistribucionFisica;

    //Remitente externo
    String personaRemite;
    String razonSocial;
    String nombre;

    //Remitente interno
    String sedeAdministrativaRemitenteInterno;
    String dependenciaRemitenteInterno;

    //Destinatario
    String sedeAdministrativaDestinatario;
    String dependenciaDestinatario;

    public DocumentVO() {
    }

    public DocumentVO(String noRadicado, Date fechaRadicacion, String tipoComunicacion, String tipologiaDocumental,
                      Double noFolios, Double noAnexos, String asunto, String requiereDigitalizar,
                      String requiereDistribucionFisica) {
        this.noRadicado = noRadicado;
        this.fechaRadicacion = fechaRadicacion;
        this.tipoComunicacion = tipoComunicacion;
        this.tipologiaDocumental = tipologiaDocumental;
        this.noFolios = noFolios;
        this.noAnexos = noAnexos;
        this.asunto = asunto;
        this.requiereDigitalizar = requiereDigitalizar;
        this.requiereDistribucionFisica = requiereDistribucionFisica;
    }

    public DocumentVO(String noRadicado, Date fechaRadicacion, String tipoComunicacion, String tipologiaDocumental,
                      Double noFolios, Double noAnexos, String asunto, String requiereDigitalizar,
                      String requiereDistribucionFisica, String personaRemite, String razonSocial, String nombre,
                      String sedeAdministrativaRemitenteInterno, String dependenciaRemitenteInterno, String sedeAdministrativaDestinatario, String dependenciaDestinatario) {
        this.noRadicado = noRadicado;
        this.fechaRadicacion = fechaRadicacion;
        this.tipoComunicacion = tipoComunicacion;
        this.tipologiaDocumental = tipologiaDocumental;
        this.noFolios = noFolios;
        this.noAnexos = noAnexos;
        this.asunto = asunto;
        this.requiereDigitalizar = requiereDigitalizar;
        this.requiereDistribucionFisica = requiereDistribucionFisica;
        this.personaRemite = personaRemite;
        this.razonSocial = razonSocial;
        this.nombre = nombre;
        this.sedeAdministrativaRemitenteInterno = sedeAdministrativaRemitenteInterno;
        this.dependenciaRemitenteInterno = dependenciaRemitenteInterno;
        this.sedeAdministrativaDestinatario = sedeAdministrativaDestinatario;
        this.dependenciaDestinatario = dependenciaDestinatario;
    }

    public String getNoRadicado() {
        return noRadicado;
    }

    public void setNoRadicado(String noRadicado) {
        this.noRadicado = noRadicado;
    }

    public Date getFechaRadicacion() {
        return fechaRadicacion;
    }

    public void setFechaRadicacion(Date fechaRadicacion) {
        this.fechaRadicacion = fechaRadicacion;
    }

    public String getTipoComunicacion() {
        return tipoComunicacion;
    }

    public void setTipoComunicacion(String tipoComunicacion) {
        this.tipoComunicacion = tipoComunicacion;
    }

    public String getTipologiaDocumental() {
        return tipologiaDocumental;
    }

    public void setTipologiaDocumental(String tipologiaDocumental) {
        this.tipologiaDocumental = tipologiaDocumental;
    }

    public Double getNoFolios() {
        return noFolios;
    }

    public void setNoFolios(Double noFolios) {
        this.noFolios = noFolios;
    }

    public Double getNoAnexos() {
        return noAnexos;
    }

    public void setNoAnexos(Double noAnexos) {
        this.noAnexos = noAnexos;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getRequiereDigitalizar() {
        return requiereDigitalizar;
    }

    public void setRequiereDigitalizar(String requiereDigitalizar) {
        this.requiereDigitalizar = requiereDigitalizar;
    }

    public String getRequiereDistribucionFisica() {
        return requiereDistribucionFisica;
    }

    public void setRequiereDistribucionFisica(String requiereDistribucionFisica) {
        this.requiereDistribucionFisica = requiereDistribucionFisica;
    }

    public String getPersonaRemite() {
        return personaRemite;
    }

    public void setPersonaRemite(String personaRemite) {
        this.personaRemite = personaRemite;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSedeAdministrativaRemitenteInterno() {
        return sedeAdministrativaRemitenteInterno;
    }

    public void setSedeAdministrativaRemitenteInterno(String sedeAdministrativaRemitenteInterno) {
        this.sedeAdministrativaRemitenteInterno = sedeAdministrativaRemitenteInterno;
    }

    public String getDependenciaRemitenteInterno() {
        return dependenciaRemitenteInterno;
    }

    public void setDependenciaRemitenteInterno(String dependenciaRemitenteInterno) {
        this.dependenciaRemitenteInterno = dependenciaRemitenteInterno;
    }

    public String getSedeAdministrativaDestinatario() {
        return sedeAdministrativaDestinatario;
    }

    public void setSedeAdministrativaDestinatario(String sedeAdministrativaDestinatario) {
        this.sedeAdministrativaDestinatario = sedeAdministrativaDestinatario;
    }

    public String getDependenciaDestinatario() {
        return dependenciaDestinatario;
    }

    public void setDependenciaDestinatario(String dependenciaDestinatario) {
        this.dependenciaDestinatario = dependenciaDestinatario;
    }
}
