/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.foundation.soaint.documentmanager.web.infrastructure.builder.generic;

import co.com.foundation.soaint.documentmanager.web.domain.CcdVO;
import co.com.foundation.soaint.infrastructure.builder.Builder;

import java.math.BigInteger;

import co.com.foundation.soaint.infrastructure.builder.Builder;

/**
 * @author ADMIN
 */

public class CcdVoBuilder implements Builder<CcdVO> {

    private Long ideCcd;
    private String ideorgaadminUniAmt;
    private String ideorgaadminOfProd;
    private BigInteger ideSerie;
    private BigInteger ideSubserie;
    private String nomOrgUniAmt;
    private String codOrgUniAmt;
    private String nomOrgOfProd;
    private String codOrgOfProd;
    private String codSerie;
    private String nomSerie;
    private String codSubserie;
    private String nomSubserie;
    private String estCcd;
    private int estCcdValue;
    private String labelCodNomDependencia;
    private String labelCodNomSerie;
    private String labelCodNomSubSerie;

    public CcdVoBuilder() {
    }

    public static CcdVoBuilder newBuilder() {
        return new CcdVoBuilder();
    }

    public CcdVoBuilder withIdeCcd(Long ideCcd) {
        this.ideCcd = ideCcd;
        return this;
    }


    public CcdVoBuilder withIdeorgaadminUniAmt(String ideorgaadminUniAmt) {
        this.ideorgaadminUniAmt = ideorgaadminUniAmt;
        return this;
    }

    public CcdVoBuilder withIdeorgaadminOfProd(String ideorgaadminOfProd) {
        this.ideorgaadminOfProd = ideorgaadminOfProd;
        return this;
    }

    public CcdVoBuilder withIdeSerie(BigInteger ideSerie) {
        this.ideSerie = ideSerie;
        return this;
    }

    public CcdVoBuilder withIdeSubserie(BigInteger ideSubserie) {
        this.ideSubserie = ideSubserie;
        return this;
    }

    public CcdVoBuilder withCodSerie(String codSerie) {
        this.codSerie = codSerie;
        return this;
    }

    public CcdVoBuilder withNomSerie(String nomSerie) {
        this.nomSerie = nomSerie;
        return this;
    }

    public CcdVoBuilder withCodSubserie(String codSubserie) {
        this.codSubserie = codSubserie;
        return this;
    }

    public CcdVoBuilder withNomSubserie(String nomSubserie) {
        this.nomSubserie = nomSubserie;
        return this;
    }

    public CcdVoBuilder withEstCcd(String estCcd) {
        this.estCcd = estCcd;
        return this;
    }

    public CcdVoBuilder withEstCcdValue(int estCcdValue) {
        this.estCcdValue = estCcdValue;
        return this;
    }

    public CcdVoBuilder withLabelCodNomDependencia(String labelCodNomDependencia) {
        this.labelCodNomDependencia = labelCodNomDependencia;
        return this;
    }

    public CcdVoBuilder withNomOrgOfProd(String nomOrgOfProd) {
        this.nomOrgOfProd = nomOrgOfProd;
        return this;
    }


    public CcdVoBuilder withNomOrgUniAmt(String nomOrgUniAmt) {
        this.nomOrgUniAmt = nomOrgUniAmt;
        return this;
    }

    public CcdVoBuilder withCodOrgUniAmt(String codOrgUniAmt) {
        this.codOrgUniAmt = codOrgUniAmt;
        return this;
    }

    public CcdVoBuilder withCodOrgOfProd(String codOrgOfProd) {
        this.codOrgOfProd = codOrgOfProd;
        return this;
    }


    public CcdVO build() {
        return new CcdVO(ideCcd, ideorgaadminUniAmt, ideorgaadminOfProd, ideSerie, ideSubserie,
                nomOrgUniAmt, codOrgUniAmt, nomOrgOfProd, codOrgOfProd
                , codSerie, nomSerie,
                codSubserie, nomSubserie, estCcd, estCcdValue, labelCodNomDependencia,
                labelCodNomSerie, labelCodNomSubSerie);
    }

}
