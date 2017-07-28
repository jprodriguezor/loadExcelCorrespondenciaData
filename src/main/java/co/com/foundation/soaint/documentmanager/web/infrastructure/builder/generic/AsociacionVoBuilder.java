/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.foundation.soaint.documentmanager.web.infrastructure.builder.generic;

import co.com.foundation.soaint.infrastructure.builder.Builder;
import co.com.foundation.soaint.documentmanager.web.domain.AsociacionVO;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

/**
 *
 * @author malzate on 11/09/2016.
 */
public class AsociacionVoBuilder implements Builder<AsociacionVO> {
    
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


    public AsociacionVoBuilder() {
    }
    
    public static AsociacionVoBuilder newBuilder() {
        return new AsociacionVoBuilder();
    }
    
    public AsociacionVoBuilder withFecCambio(Date fecCambio) {
        this.fecCambio = fecCambio;
        return this;
    }
    
    public AsociacionVoBuilder withFecCreacion(Date fecCreacion) {
        this.fecCreacion = fecCreacion;
        return this;
    }
    
    public AsociacionVoBuilder withIdeUsuarioCambio(BigInteger ideUsuarioCambio) {
        this.ideUsuarioCambio = ideUsuarioCambio;
        return this;
    }
    
    public AsociacionVoBuilder withIdeUuid(String ideUuid) {
        this.ideUuid = ideUuid;
        return this;
    }
    
    public AsociacionVoBuilder withNivEscritura(Integer nivEscritura) {
        this.nivEscritura = nivEscritura;
        return this;
    }
    
    public AsociacionVoBuilder withNivLectura(Integer nivLectura) {
        this.nivLectura = nivLectura;
        return this;
    }
    
    public AsociacionVoBuilder withIdeRelSst(BigDecimal ideRelSst) {
        this.ideRelSst = ideRelSst;
        return this;
    }
    
    public AsociacionVoBuilder withIdeSerie(BigInteger ideSerie) {
        this.ideSerie = ideSerie;
        return this;
    }
    
    public AsociacionVoBuilder withIdeSubserie(BigInteger ideSubserie) {
        this.ideSubserie = ideSubserie;
        return this;
    }
    
    public AsociacionVoBuilder withIdeTpgDoc(BigInteger ideTpgDoc) {
        this.ideTpgDoc = ideTpgDoc;
        return this;
    }





    @Override
    public AsociacionVO build() {
        return new AsociacionVO(fecCambio, fecCreacion, ideUsuarioCambio, ideUuid, nivEscritura, nivLectura, ideRelSst, ideSerie, ideSubserie, ideTpgDoc);
    }

    
}