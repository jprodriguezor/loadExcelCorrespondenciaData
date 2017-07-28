package co.com.foundation.soaint.documentmanager.main;

import java.util.Date;

/**
 * Created by g2o on 24-Jul-17.
 */
public class Document {
    String noRadicado;
    Date fechaRadicacion;
    String tipoComunicacion;
    String tipologiaDocumental;
    Double noFolios;
    Double noAnexos;
    String asunto;
    Boolean requiereDigitalizar;
    Boolean requiereDistribucionFisica;

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

    public Boolean getRequiereDigitalizar() {
        return requiereDigitalizar;
    }

    public void setRequiereDigitalizar(Boolean requiereDigitalizar) {
        this.requiereDigitalizar = requiereDigitalizar;
    }

    public Boolean getRequiereDistribucionFisica() {
        return requiereDistribucionFisica;
    }

    public void setRequiereDistribucionFisica(Boolean requiereDistribucionFisica) {
        this.requiereDistribucionFisica = requiereDistribucionFisica;
    }
}
