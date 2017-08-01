/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.foundation.soaint.documentmanager.web.domain;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

/**
 *
 * @author malzate on 27/09/2016
 */
public class AsociacionVO {

    private Date fecCambio;
    private Date fecCreacion;
    private BigInteger ideUsuarioCambio;
    private String ideUuid;
    private Integer nivEscritura;
    private Integer nivLectura;
    private BigDecimal ideRelSst;
    @NotNull
    private BigInteger ideSerie;
    private BigInteger ideSubserie;
    @NotNull
    private BigInteger ideTpgDoc;


    public AsociacionVO() {
    }


    public AsociacionVO(Date fecCambio, Date fecCreacion, BigInteger ideUsuarioCambio, String ideUuid, Integer nivEscritura, Integer nivLectura, BigDecimal ideRelSst, BigInteger ideSerie, BigInteger ideSubserie, BigInteger ideTpgDoc) {
        this.fecCambio = fecCambio;
        this.fecCreacion = fecCreacion;
        this.ideUsuarioCambio = ideUsuarioCambio;
        this.ideUuid = ideUuid;
        this.nivEscritura = nivEscritura;
        this.nivLectura = nivLectura;
        this.ideRelSst = ideRelSst;
        this.ideSerie = ideSerie;
        this.ideSubserie = ideSubserie;
        this.ideTpgDoc = ideTpgDoc;

    }

    public Date getFecCambio() {
        return fecCambio;
    }

    public void setFecCambio(Date fecCambio) {
        this.fecCambio = fecCambio;
    }

    public Date getFecCreacion() {
        return fecCreacion;
    }

    public void setFecCreacion(Date fecCreacion) {
        this.fecCreacion = fecCreacion;
    }

    public BigInteger getIdeUsuarioCambio() {
        return ideUsuarioCambio;
    }

    public void setIdeUsuarioCambio(BigInteger ideUsuarioCambio) {
        this.ideUsuarioCambio = ideUsuarioCambio;
    }

    public String getIdeUuid() {
        return ideUuid;
    }

    public void setIdeUuid(String ideUuid) {
        this.ideUuid = ideUuid;
    }

    public Integer getNivEscritura() {
        return nivEscritura;
    }

    public void setNivEscritura(Integer nivEscritura) {
        this.nivEscritura = nivEscritura;
    }

    public Integer getNivLectura() {
        return nivLectura;
    }

    public void setNivLectura(Integer nivLectura) {
        this.nivLectura = nivLectura;
    }

    public BigDecimal getIdeRelSst() {
        return ideRelSst;
    }

    public void setIdeRelSst(BigDecimal ideRelSst) {
        this.ideRelSst = ideRelSst;
    }

    public BigInteger getIdeSerie() {
        return ideSerie;
    }

    public void setIdeSerie(BigInteger ideSerie) {
        this.ideSerie = ideSerie;
    }

    public BigInteger getIdeSubserie() {
        return ideSubserie;
    }

    public void setIdeSubserie(BigInteger ideSubserie) {
        this.ideSubserie = ideSubserie;
    }

    public BigInteger getIdeTpgDoc() {
        return ideTpgDoc;
    }

    public void setIdeTpgDoc(BigInteger ideTpgDoc) {
        this.ideTpgDoc = ideTpgDoc;
    }


    @Override
    public String toString() {
        return "AsociacionVO{" +
                "fecCambio=" + fecCambio +
                ", fecCreacion=" + fecCreacion +
                ", ideUsuarioCambio=" + ideUsuarioCambio +
                ", ideUuid='" + ideUuid + '\'' +
                ", nivEscritura=" + nivEscritura +
                ", nivLectura=" + nivLectura +
                ", ideRelSst=" + ideRelSst +
                ", ideSerie=" + ideSerie +
                ", ideSubserie=" + ideSubserie +
                ", ideTpgDoc=" + ideTpgDoc +
                '}';
    }
}