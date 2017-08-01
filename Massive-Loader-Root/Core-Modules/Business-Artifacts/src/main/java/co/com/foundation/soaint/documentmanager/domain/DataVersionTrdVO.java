package co.com.foundation.soaint.documentmanager.domain;

import java.math.BigInteger;

/**
 * Created by jrodriguez on 21/11/2016.
 */
public class DataVersionTrdVO {

    private String codOrgUniAmd;
    private String codOrgOfcProd;
    private BigInteger ideTabRetDoc;
    private BigInteger ideSerie;
    private BigInteger ideSubserie;
    private Long agTrd;
    private Long acTrd;
    private String proTrd;
    private BigInteger ideDisFinal;

    public DataVersionTrdVO(){}

    public DataVersionTrdVO(String codOrgUniAmd, String codOrgOfcProd, BigInteger ideTabRetDoc, BigInteger ideSerie,
                            BigInteger ideSubserie, Long agTrd, Long acTrd, BigInteger ideDisFinal, String proTrd) {
        this.codOrgUniAmd = codOrgUniAmd;
        this.codOrgOfcProd = codOrgOfcProd;
        this.ideTabRetDoc = ideTabRetDoc;
        this.ideSerie = ideSerie;
        this.ideSubserie = ideSubserie;
        this.agTrd = agTrd;
        this.acTrd = acTrd;
        this.ideDisFinal = ideDisFinal;
        this.proTrd = proTrd;
    }

    public String getCodOrgUniAmd() {
        return codOrgUniAmd;
    }

    public String getCodOrgOfcProd() {
        return codOrgOfcProd;
    }

    public BigInteger getIdeTabRetDoc() {
        return ideTabRetDoc;
    }

    public BigInteger getIdeSerie() {
        return ideSerie;
    }

    public BigInteger getIdeSubserie() {
        return ideSubserie;
    }

    public Long getAgTrd() {
        return agTrd;
    }

    public Long getAcTrd() {
        return acTrd;
    }

    public String getProTrd() {
        return proTrd;
    }

    public BigInteger getIdeDisFinal() {
        return ideDisFinal;
    }

    @Override
    public String toString() {
        return "DataVersionTrdVO{" +
                "codOrgUniAmd='" + codOrgUniAmd + '\'' +
                ", codOrgOfcProd='" + codOrgOfcProd + '\'' +
                ", ideTabRetDoc=" + ideTabRetDoc +
                ", ideSerie=" + ideSerie +
                ", ideSubserie=" + ideSubserie +
                ", agTrd=" + agTrd +
                ", acTrd=" + acTrd +
                ", proTrd='" + proTrd + '\'' +
                ", ideDisFinal=" + ideDisFinal +
                '}';
    }
}
