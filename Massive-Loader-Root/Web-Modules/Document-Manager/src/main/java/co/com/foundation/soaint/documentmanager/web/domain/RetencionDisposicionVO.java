package co.com.foundation.soaint.documentmanager.web.domain;


import java.io.Serializable;
import java.math.BigInteger;

public class RetencionDisposicionVO implements Serializable {

    private BigInteger ideTabRetDoc;
    private Long acTrd;
    private Long agTrd;
    private String proTrd;
    private BigInteger ideDisFinal;
    private String codOfcProd;
    private String codUniAmt;
    private BigInteger idSerie;
    private BigInteger idSubserie;

    public RetencionDisposicionVO(){

    }

    public RetencionDisposicionVO(BigInteger ideTabRetDoc, Long acTrd, Long agTrd, String proTrd, BigInteger ideDisFinal, String codOfcProd, String codUniAmt, BigInteger idSerie, BigInteger idSubserie) {
        this.ideTabRetDoc = ideTabRetDoc;
        this.acTrd = acTrd;
        this.agTrd = agTrd;
        this.proTrd = proTrd;
        this.ideDisFinal = ideDisFinal;
        this.codOfcProd = codOfcProd;
        this.codUniAmt = codUniAmt;
        this.idSerie = idSerie;
        this.idSubserie = idSubserie;
    }

    public BigInteger getIdeTabRetDoc() {
        return ideTabRetDoc;
    }

    public void setIdeTabRetDoc(BigInteger ideTabRetDoc) {
        this.ideTabRetDoc = ideTabRetDoc;
    }

    public Long getAcTrd() {
        return acTrd;
    }

    public Long getAgTrd() {
        return agTrd;
    }

    public String getProTrd() {
        return proTrd;
    }

    public BigInteger getIdeDisFinal() {
        return ideDisFinal;
    }

    public String getCodOfcProd() {
        return codOfcProd;
    }

    public String getCodUniAmt() {
        return codUniAmt;
    }

    public BigInteger getIdSerie() {
        return idSerie;
    }

    public BigInteger getIdSubserie() {
        return idSubserie;
    }

    @Override
    public String toString() {
        return "RetencionDisposicionVO{" +
                "ideTabRetDoc=" + ideTabRetDoc +
                ", acTrd=" + acTrd +
                ", agTrd=" + agTrd +
                ", proTrd='" + proTrd + '\'' +
                ", ideDisFinal=" + ideDisFinal +
                ", codOfcProd='" + codOfcProd + '\'' +
                ", codUniAmt='" + codUniAmt + '\'' +
                ", idSerie=" + idSerie +
                ", idSubserie=" + idSubserie +
                '}';
    }
}
