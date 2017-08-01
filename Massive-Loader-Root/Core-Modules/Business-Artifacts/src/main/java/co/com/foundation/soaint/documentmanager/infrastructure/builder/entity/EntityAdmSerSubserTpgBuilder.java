/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.foundation.soaint.documentmanager.infrastructure.builder.entity;

import co.com.foundation.soaint.documentmanager.persistence.entity.AdmSerSubserTpg;
import co.com.foundation.soaint.documentmanager.persistence.entity.AdmSerie;
import co.com.foundation.soaint.documentmanager.persistence.entity.AdmSubserie;
import co.com.foundation.soaint.documentmanager.persistence.entity.AdmTpgDoc;
import co.com.foundation.soaint.infrastructure.builder.Builder;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

/**
 *
 * @author malzate on 29/09/2016.
 */
public class EntityAdmSerSubserTpgBuilder implements Builder<AdmSerSubserTpg> {

    private Date fecCambio;
    private Date fecCreacion;
    private BigInteger ideUsuarioCambio;
    private String ideUuid;
    private Integer nivEscritura;
    private Integer nivLectura;
    private BigDecimal ideRelSst;
    private AdmSerie ideSerie;
    private AdmSubserie ideSubserie;
    private AdmTpgDoc ideTpgDoc;

    public EntityAdmSerSubserTpgBuilder() {
    }
    
    public static EntityAdmSerSubserTpgBuilder newInstance() {
        return new EntityAdmSerSubserTpgBuilder();
    }
    
    public EntityAdmSerSubserTpgBuilder withFecCambio(final Date fecCambio) {
        this.fecCambio = fecCambio;
        return this;
    }
    
    public EntityAdmSerSubserTpgBuilder withFecCreacion(final Date fecCreacion) {
        this.fecCreacion = fecCreacion;
        return this;
    }
    
    public EntityAdmSerSubserTpgBuilder withIdeUsuarioCambio(final BigInteger ideUsuarioCambio) {
        this.ideUsuarioCambio = ideUsuarioCambio;
        return this;
    }
    
    public EntityAdmSerSubserTpgBuilder withIdeUuid(final String ideUuid) {
        this.ideUuid = ideUuid;
        return this;
    }
    
    public EntityAdmSerSubserTpgBuilder withNivEscritura(final Integer nivEscritura) {
        this.nivEscritura = nivEscritura;
        return this;
    }
    
    public EntityAdmSerSubserTpgBuilder withNivLectura(final Integer nivLectura) {
        this.nivLectura = nivLectura;
        return this;
    }
    
    public EntityAdmSerSubserTpgBuilder withIdeRelSst(final BigDecimal ideRelSst) {
        this.ideRelSst = ideRelSst;
        return this;
    }
    
    public EntityAdmSerSubserTpgBuilder withIdeSerie(final AdmSerie ideSerie) {
        this.ideSerie = ideSerie;
        return this;
    }
    
    public EntityAdmSerSubserTpgBuilder withIdeSubserie(final AdmSubserie ideSubserie) {
        this.ideSubserie = ideSubserie;
        return this;
    }
    
    public EntityAdmSerSubserTpgBuilder withIdeTpgDoc(final AdmTpgDoc ideTpgDoc) {
        this.ideTpgDoc = ideTpgDoc;
        return this;
    }
    
    @Override
    public AdmSerSubserTpg build() {
        return new AdmSerSubserTpg(fecCambio, fecCreacion, ideUsuarioCambio, ideUuid, nivEscritura, nivLectura, ideRelSst, ideSerie, ideSubserie, ideTpgDoc);
    }
    
}