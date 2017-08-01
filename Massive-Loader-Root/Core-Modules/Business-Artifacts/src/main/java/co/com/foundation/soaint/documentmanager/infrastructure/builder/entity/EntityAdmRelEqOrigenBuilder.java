package co.com.foundation.soaint.documentmanager.infrastructure.builder.entity;

import co.com.foundation.soaint.documentmanager.persistence.entity.AdmRelEqDestino;
import co.com.foundation.soaint.documentmanager.persistence.entity.AdmRelEqOrigen;
import co.com.foundation.soaint.documentmanager.persistence.entity.AdmSerie;
import co.com.foundation.soaint.documentmanager.persistence.entity.AdmSubserie;
import co.com.foundation.soaint.infrastructure.builder.Builder;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

/**
 * Created by ADMIN on 06/12/2016.
 */
public class EntityAdmRelEqOrigenBuilder implements Builder<AdmRelEqOrigen>{

    private BigInteger ideRelOrigen;
    private String ideUniAmt;
    private String ideOfcProd;
    private Date fecCreacion;
    private String numVersionOrg;
   // private AdmSerie admSerie;
    private BigInteger ideSerie;
   // private AdmSubserie admSubserie;
    private BigInteger ideSubserie;
    //private List<AdmRelEqDestino> admRelEqDestinoList;
   // private AdmRelEqDestino admRelEqDestino;

    public EntityAdmRelEqOrigenBuilder() {
    }

    public static EntityAdmRelEqOrigenBuilder newBuilder() {
        return new EntityAdmRelEqOrigenBuilder();

    }

    public EntityAdmRelEqOrigenBuilder withIdeRelOrigen(BigInteger ideRelOrigen) {
        this.ideRelOrigen = ideRelOrigen;
        return this;
    }

    public EntityAdmRelEqOrigenBuilder withIdeUniAmt(String ideUniAmt) {
        this.ideUniAmt = ideUniAmt;
        return this;
    }

    public EntityAdmRelEqOrigenBuilder withNumVersionOrg(String numVersionOrg) {
        this.numVersionOrg = numVersionOrg;
        return this;
    }
    public EntityAdmRelEqOrigenBuilder withIdeOfcProd(String ideOfcProd) {
        this.ideOfcProd = ideOfcProd;
        return this;
    }

    public EntityAdmRelEqOrigenBuilder withFecCreacion(Date fecCreacion) {
        this.fecCreacion = fecCreacion;
        return this;
    }
/*
    public EntityAdmRelEqOrigenBuilder withAdmSerie(AdmSerie admSerie) {
        this.admSerie = admSerie;
        return this;
    }

    public EntityAdmRelEqOrigenBuilder withAdmSubserie(AdmSubserie admSubserie) {
        this.admSubserie = admSubserie;
        return this;
    }*/

 /*   public EntityAdmRelEqOrigenBuilder withAdmRelEqDestinoList(List<AdmRelEqDestino> admRelEqDestinoList) {
        this.admRelEqDestinoList = admRelEqDestinoList;
        return this;
    }

    public EntityAdmRelEqOrigenBuilder withAdmRelEqDestino(AdmRelEqDestino admRelEqDestino) {
        this.admRelEqDestino = admRelEqDestino;
        return this;
    }*/



    public EntityAdmRelEqOrigenBuilder withIdeSerie(BigInteger ideSerie) {
        this.ideSerie = ideSerie;
        return this;
    }

    public EntityAdmRelEqOrigenBuilder withIdeSubserie(BigInteger ideSubserie) {
        this.ideSubserie = ideSubserie;
        return this;
    }

    public AdmRelEqOrigen build() {
        return new AdmRelEqOrigen(
             ideRelOrigen,  ideUniAmt, ideOfcProd, fecCreacion, ideSerie, ideSubserie, new BigInteger(numVersionOrg));
    }
}
