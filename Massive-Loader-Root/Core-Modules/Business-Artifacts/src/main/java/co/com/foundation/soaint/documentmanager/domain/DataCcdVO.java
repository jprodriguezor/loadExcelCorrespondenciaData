package co.com.foundation.soaint.documentmanager.domain;

import java.math.BigInteger;

/**
 * Created by ADMIN on 15/11/2016.
 */
public class DataCcdVO {
    
    private String version;
    private String idUniAmt;
    private String idOfcProd;
    private BigInteger idSerie;
    private BigInteger idSubserie;
    

    public DataCcdVO() {
    }

    public DataCcdVO(String version, String idUniAmt, String idOfcProd) {
        this.version = version;
        this.idUniAmt = idUniAmt;
        this.idOfcProd = idOfcProd;
    }

    public DataCcdVO(String version, String idUniAmt, String idOfcProd, BigInteger idSerie, BigInteger idSubserie) {
        this.version = version;
        this.idUniAmt = idUniAmt;
        this.idOfcProd = idOfcProd;
        this.idSerie = idSerie;
        this.idSubserie = idSubserie;
    }

    public String getIdUniAmt() {
        return idUniAmt;
    }

    public void setIdUniAmt(String idUniAmt) {
        this.idUniAmt = idUniAmt;
    }

    public String getIdOfcProd() {
        return idOfcProd;
    }

    public void setIdOfcProd(String idOfcProd) {
        this.idOfcProd = idOfcProd;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public BigInteger getIdSerie() {
        return idSerie;
    }

    public void setIdSerie(BigInteger idSerie) {
        this.idSerie = idSerie;
    }

    public BigInteger getIdSubserie() {
        return idSubserie;
    }

    public void setIdSubserie(BigInteger idSubserie) {
        this.idSubserie = idSubserie;
    }

    @Override


    public String toString() {
        return "DataCcdVO{" + "version=" + version + ", idUniAmt=" + idUniAmt + ", idOfcProd=" + idOfcProd + '}';
    }

   

    
}
