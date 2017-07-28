/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soaint.massive.loader.web.domain;

import java.math.BigInteger;

/**
 *
 * @author ADMIN
 */
public class CcdVO {
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
    private String labelCodNomDependencia;
    private String labelCodNomSerie;
    private String labelCodNomSubSerie;
    private int estCcdValue;

    
    public CcdVO() {
    }

    public CcdVO(Long ideCcd) {
        this.ideCcd = ideCcd;
    }

    public CcdVO(String ideorgaadminUniAmt, String ideorgaadminOfProd, BigInteger ideSerie, BigInteger ideSubserie) {
        this.ideorgaadminUniAmt = ideorgaadminUniAmt;
        this.ideorgaadminOfProd = ideorgaadminOfProd;
        this.ideSerie = ideSerie;
        this.ideSubserie = ideSubserie;
    }

   //Constructor de ccdList proveniente de CcdVO
    public CcdVO( Long ideCcd, String ideorgaadminUniAmt, String ideorgaadminOfProd, BigInteger ideSerie, BigInteger ideSubserie,
            String nomOrgUniAmt, String codOrgUniAmt, String nomOrgOfProd, String codOrgOfProd, 
            String codSerie, String nomSerie, String codSubserie, String nomSubserie, String estCcd, int estCcdValue, String labelCodNomDependencia, String labelCodNomSerie, String labelCodNomSubSerie) {
     
        this.ideCcd = ideCcd;
        this.ideorgaadminUniAmt = ideorgaadminUniAmt;
        this.ideorgaadminOfProd = ideorgaadminOfProd;
        this.ideSerie = ideSerie;
        this.ideSubserie = ideSubserie;
        this.nomOrgUniAmt = nomOrgUniAmt;
        this.codOrgUniAmt = codOrgUniAmt;
        this.nomOrgOfProd = nomOrgOfProd;
        this.codOrgOfProd= codOrgOfProd;
        this.codSerie = codSerie;
        this.nomSerie = nomSerie;
        this.codSubserie = codSubserie;
        this.nomSubserie = nomSubserie == null ? "-N/A-":  nomSubserie;
        this.estCcd = estCcd;
        this.labelCodNomDependencia = labelCodNomDependencia;
        this.labelCodNomSerie = labelCodNomSerie;
        this.labelCodNomSubSerie = labelCodNomSubSerie;
        this.estCcdValue = estCcdValue;

       
    }


    public String getLabelCodNomDependencia() {
        return codOrgOfProd+ " - " +nomOrgOfProd;
    }

    public String getLabelCodNomSerie() {
        return codSerie + " - "+ nomSerie ;
    }

    public String getLabelCodNomSubSerie() {
        if(codSubserie == null)
            return  nomSubserie;
        else
         return codSubserie+" - "+ nomSubserie ;
    }
       
    public String getIdeorgaadminUniAmt() {
        return ideorgaadminUniAmt;
    }

    public void setIdeorgaadminUniAmt(String ideorgaadminUniAmt) {
        this.ideorgaadminUniAmt = ideorgaadminUniAmt;
    }

    public String getIdeorgaadminOfProd() {
        return ideorgaadminOfProd;
    }

    public void setIdeorgaadminOfProd(String ideorgaadminOfProd) {
        this.ideorgaadminOfProd = ideorgaadminOfProd;
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

    public String getNomOrgUniAmt() {
        return nomOrgUniAmt;
    }

    public void setNomOrgUniAmt(String nomOrgUniAmt) {
        this.nomOrgUniAmt = nomOrgUniAmt;
    }

    public String getCodOrgUniAmt() {
        return codOrgUniAmt;
    }

    public void setCodOrgUniAmt(String codOrgUniAmt) {
        this.codOrgUniAmt = codOrgUniAmt;
    }

    public String getCodOrgOfProd() {
        return codOrgOfProd;
    }

    public void setCodOrgOfProd(String codOrgOfProd) {
        this.codOrgOfProd = codOrgOfProd;
    }



    public String getNomOrgOfProd() {
        return nomOrgOfProd;
    }

    public void setNomOrgOfProd(String nomOrgOfProd) {
        this.nomOrgOfProd = nomOrgOfProd;
    }

    public String getCodSerie() {
        return codSerie;
    }

    public void setCodSerie(String codSerie) {
        this.codSerie = codSerie;
    }

    public String getNomSerie() {
        return nomSerie;
    }

    public void setNomSerie(String nomSerie) {
        this.nomSerie = nomSerie;
    }

    public String getCodSubserie() {
        return codSubserie;
    }

    public void setCodSubserie(String codSubserie) {
        this.codSubserie = codSubserie;
    }

    public String getNomSubserie() {
        return nomSubserie;
    }

    public void setNomSubserie(String nomSubserie) {
        this.nomSubserie = nomSubserie;
    }

    public String getEstCcd() {
        return estCcd;
    }

    public void setEstCcd(String estCcd) {
        this.estCcd = estCcd;
    }

    public int getEstCcdValue() {
        return estCcdValue;
    }

    public void setEstCcdValue(int estCcdValue) {
        this.estCcdValue = estCcdValue;
    }

    public Long getIdeCcd() {
        return ideCcd;
    }

    public void setIdeCcd(Long ideCcd) {
        this.ideCcd = ideCcd;
    }



    @Override
    public String toString() {
        return "CcdVO{"
                + "ideCcd=" + ideCcd
                + "ideorgaadminUniAmt=" + ideorgaadminUniAmt
                + ", ideorgaadminOfProd='" + ideorgaadminOfProd + '\''
                + ", ideSerie=" + ideSerie
                + ", ideSubserie=" + ideSubserie
                + ", nomOrgUniAmt=" + nomOrgUniAmt
                + ", codOrgUniAmt='" + codOrgUniAmt + '\''
                + ", nomOrgOfProd=" + nomOrgOfProd
                + ", codOrgOfProd='" + codOrgOfProd + '\''
                + ", codSerie='" + codSerie + '\''
                + ", nomSerie='" + nomSerie + '\''
                + ", codSubserie='" + codSubserie + '\''
                + ", nomSubserie=" + nomSubserie
                + ", estCcd='" + estCcd + '\''
                + ", estCcdValue='" + estCcdValue + '\''
                + ", labelCodNomDependencia='" + labelCodNomDependencia + '\''
                + '}';
    }

}
