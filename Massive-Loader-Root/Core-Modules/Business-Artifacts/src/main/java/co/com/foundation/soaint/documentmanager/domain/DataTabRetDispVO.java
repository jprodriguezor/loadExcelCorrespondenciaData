package co.com.foundation.soaint.documentmanager.domain;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * Created by jrodriguez on 14/11/2016.
 */
public class DataTabRetDispVO implements Serializable {

    private String codOfcProd;
    private String codUniAmt;
    private BigInteger idSerie;
    private BigInteger idSubserie;

    public DataTabRetDispVO() {
    }

    public DataTabRetDispVO(String codOfcProd, String codUniAmt, BigInteger idSerie, BigInteger idSubserie) {
        this.codOfcProd = codOfcProd;
        this.codUniAmt = codUniAmt;
        this.idSerie = idSerie;
        this.idSubserie = idSubserie;
    }

    public String getCodUniAmt() {
        return codUniAmt;
    }

    public void setCodUniAmt(String codUniAmt) {
        this.codUniAmt = codUniAmt;
    }

    public String getCodOfcProd() {
        return codOfcProd;
    }

    public void setCodOfcProd(String codOfcProd) {
        this.codOfcProd = codOfcProd;
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
        return "DataTabRetDispVO{" +
                "codOfcProd='" + codOfcProd + '\'' +
                ", codUniAmt='" + codUniAmt + '\'' +
                ", idSerie=" + idSerie +
                ", idSubserie=" + idSubserie +
                '}';
    }
}
