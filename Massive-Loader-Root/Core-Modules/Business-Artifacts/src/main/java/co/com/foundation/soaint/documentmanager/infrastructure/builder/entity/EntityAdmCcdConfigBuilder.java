/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.foundation.soaint.documentmanager.infrastructure.builder.entity;

import co.com.foundation.soaint.documentmanager.persistence.entity.AdmSerie;
import co.com.foundation.soaint.documentmanager.persistence.entity.AdmSubserie;
import co.com.foundation.soaint.infrastructure.builder.Builder;
import co.com.foundation.soaint.documentmanager.persistence.entity.AdmConfigCcd;

import java.math.BigInteger;
import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class EntityAdmCcdConfigBuilder implements Builder<AdmConfigCcd>{

   
    private Long ideCcd;
    private Date fecCambio;
    private Date fecCreacion;
    private BigInteger ideUsuarioCambio;
    private String ideUuid;
    private Integer nivEscritura;
    private Integer nivLectura;
    private Integer estCcd;
    private AdmSerie ideSerie;
    private AdmSubserie ideSubserie;
    private String ideOfcProd;
    private String ideUniAmt;
    private String codOrgUniAmt;
    private String codOrgOfProd;
    


    private EntityAdmCcdConfigBuilder() {
    }
    
    public static EntityAdmCcdConfigBuilder newBuilder() {
        return new EntityAdmCcdConfigBuilder();
        
    }

    public EntityAdmCcdConfigBuilder withIdeCcd(Long ideCcd) {
        this.ideCcd = ideCcd;
        return this;
    }

    public EntityAdmCcdConfigBuilder withFecCambio(Date fecCambio) {
        this.fecCambio = fecCambio;
        return this;
    }

    public EntityAdmCcdConfigBuilder withFecCreacion(Date fecCreacion) {
        this.fecCreacion = fecCreacion;
        return this;
    }

    public EntityAdmCcdConfigBuilder withIdeUsuarioCambio(BigInteger ideUsuarioCambio) {
        this.ideUsuarioCambio = ideUsuarioCambio;
        return this;
    }

    public EntityAdmCcdConfigBuilder withIdeUuid(String ideUuid) {
        this.ideUuid = ideUuid;
        return this;
    }

    public EntityAdmCcdConfigBuilder withNivEscritura(Integer nivEscritura) {
        this.nivEscritura = nivEscritura;
        return this;
    }

    public EntityAdmCcdConfigBuilder withNivLectura(Integer nivLectura) {
        this.nivLectura = nivLectura;
        return this;
    }

    public EntityAdmCcdConfigBuilder withEstCcd(Integer estCcd) {
        this.estCcd = estCcd;
        return this;
    }

  

    public EntityAdmCcdConfigBuilder withIdeSerie(AdmSerie ideSerie) {
        this.ideSerie = ideSerie;
        return this;
    }

    public EntityAdmCcdConfigBuilder withIdeSubserie(AdmSubserie ideSubserie) {
        this.ideSubserie = ideSubserie;
        return this;
    }



    public EntityAdmCcdConfigBuilder withIdeOfcProd(String ideOfcProd) {
        this.ideOfcProd = ideOfcProd;
        return this;
    }

    public EntityAdmCcdConfigBuilder withIdeUniAmt(String ideUniAmt) {
        this.ideUniAmt = ideUniAmt;
        return this;
    }

    public EntityAdmCcdConfigBuilder withCodOrgUniAmt(String codOrgUniAmt) {
        this.codOrgUniAmt = codOrgUniAmt;
        return this;
    }

    public EntityAdmCcdConfigBuilder withCodOrgOfProd(String codOrgOfProd) {
        this.codOrgOfProd = codOrgOfProd;
        return this;
    }
    
    public AdmConfigCcd build() {
        return new AdmConfigCcd(
                ideCcd, fecCambio,fecCreacion,ideUsuarioCambio,ideUuid,
                nivEscritura,nivLectura,estCcd,ideSerie,ideSubserie,ideOfcProd,
                ideUniAmt,codOrgUniAmt,codOrgOfProd);
    }
    
}
