/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.foundation.soaint.documentmanager.infrastructure.builder.entity;

import co.com.foundation.soaint.documentmanager.persistence.entity.AdmDisFinal;
import co.com.foundation.soaint.infrastructure.builder.Builder;

import java.math.BigInteger;
import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class EntityAdmDisposicionFinalBuilder implements Builder<AdmDisFinal> {
    
    private BigInteger ideDisFinal;
    private String nomDisFinal;
    private String desDisFinal;
    private String staDisFinal;
    private Long ideUsuarioCambio;
    private Date fecCambio;
    private Integer nivLectura;
    private Integer nivEscritura;
    private String ideUuid;
    private Date fecCreacion;

    public EntityAdmDisposicionFinalBuilder() {
    }
    
    public static EntityAdmDisposicionFinalBuilder newBuilder() {
        return new EntityAdmDisposicionFinalBuilder();
    }

    public EntityAdmDisposicionFinalBuilder withIdeDisFinal(BigInteger ideDisFinal) {
        this.ideDisFinal = ideDisFinal;
        return this;
    }

    public EntityAdmDisposicionFinalBuilder withNomDisFinal(String nomDisFinal) {
        this.nomDisFinal = nomDisFinal;
        return this;
    }

    public EntityAdmDisposicionFinalBuilder withDesDisFinal(String desDisFinal) {
        this.desDisFinal = desDisFinal;
        return this;
    }

    public EntityAdmDisposicionFinalBuilder withStaDisFinal(String staDisFinal) {
        this.staDisFinal = staDisFinal;
        return this;
    }

    public EntityAdmDisposicionFinalBuilder withIdeUsuarioCambio(Long ideUsuarioCambio) {
        this.ideUsuarioCambio = ideUsuarioCambio;
        return this;
    }

    public EntityAdmDisposicionFinalBuilder withFecCambio(Date fecCambio) {
        this.fecCambio = fecCambio;
        return this;
    }

    public EntityAdmDisposicionFinalBuilder withNivLectura(Integer nivLectura) {
        this.nivLectura = nivLectura;
        return this;
    }

    public EntityAdmDisposicionFinalBuilder withNivEscritura(Integer nivEscritura) {
        this.nivEscritura = nivEscritura;
        return this;
    }

    public EntityAdmDisposicionFinalBuilder withIdeUuid(String ideUuid) {
        this.ideUuid = ideUuid;
        return this;
    }
    
    public EntityAdmDisposicionFinalBuilder withFecCreacion(Date fecCreacion) {
        this.fecCreacion = fecCreacion;
        return this;
    }
    
        public AdmDisFinal build() {
        return new AdmDisFinal(ideDisFinal, nomDisFinal, desDisFinal, staDisFinal, fecCambio, fecCreacion);
    }
    
}
