/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foundation.soaint.massiveloader.web.infrastructure.builder.generic;

import co.com.foundation.soaint.infrastructure.builder.Builder;
import com.foundation.soaint.massiveloader.deprecated.web.domain.AsociacionVO;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

/**
 *
 * @author malzate on 11/09/2016.
 */
public class DocumentVoBuilder implements Builder<AsociacionVO> {

    private Date fecCambio;
    private Date fecCreacion;
    private BigInteger ideUsuarioCambio;
    private String ideUuid;
    private Integer nivEscritura;
    private Integer nivLectura;
    private BigDecimal ideRelSst;
    private BigInteger ideSerie;
    private BigInteger ideSubserie;
    private BigInteger ideTpgDoc;


    public DocumentVoBuilder() {
    }
    
    public static DocumentVoBuilder newBuilder() {
        return new DocumentVoBuilder();
    }
    
    public DocumentVoBuilder withFecCambio(Date fecCambio) {
        this.fecCambio = fecCambio;
        return this;
    }
    
    public DocumentVoBuilder withFecCreacion(Date fecCreacion) {
        this.fecCreacion = fecCreacion;
        return this;
    }
    
    public DocumentVoBuilder withIdeUsuarioCambio(BigInteger ideUsuarioCambio) {
        this.ideUsuarioCambio = ideUsuarioCambio;
        return this;
    }
    
    public DocumentVoBuilder withIdeUuid(String ideUuid) {
        this.ideUuid = ideUuid;
        return this;
    }
    
    public DocumentVoBuilder withNivEscritura(Integer nivEscritura) {
        this.nivEscritura = nivEscritura;
        return this;
    }
    
    public DocumentVoBuilder withNivLectura(Integer nivLectura) {
        this.nivLectura = nivLectura;
        return this;
    }
    
    public DocumentVoBuilder withIdeRelSst(BigDecimal ideRelSst) {
        this.ideRelSst = ideRelSst;
        return this;
    }
    
    public DocumentVoBuilder withIdeSerie(BigInteger ideSerie) {
        this.ideSerie = ideSerie;
        return this;
    }
    
    public DocumentVoBuilder withIdeSubserie(BigInteger ideSubserie) {
        this.ideSubserie = ideSubserie;
        return this;
    }
    
    public DocumentVoBuilder withIdeTpgDoc(BigInteger ideTpgDoc) {
        this.ideTpgDoc = ideTpgDoc;
        return this;
    }





    @Override
    public AsociacionVO build() {
        return new AsociacionVO(fecCambio, fecCreacion, ideUsuarioCambio, ideUuid, nivEscritura, nivLectura, ideRelSst, ideSerie, ideSubserie, ideTpgDoc);
    }

    
}