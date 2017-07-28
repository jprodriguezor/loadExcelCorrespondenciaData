package co.com.foundation.soaint.documentmanager.web.infrastructure.builder.generic;

import co.com.foundation.soaint.infrastructure.builder.Builder;
import co.com.foundation.soaint.documentmanager.web.domain.RetencionDisposicionVO;

import java.math.BigInteger;

/**
 * Created by jrodriguez on 11/09/2016.
 */
public class RetencionDisposicionVOBuilder implements Builder<RetencionDisposicionVO> {

    private BigInteger ideTabRetDoc;
    private Long acTrd;
    private Long agTrd;
    private String proTrd;
    private BigInteger ideDisFinal;
    private String codOfcProd;
    private String codUniAmt;
    private BigInteger idSerie;
    private BigInteger idSubserie;

    private RetencionDisposicionVOBuilder() {
    }

    public static RetencionDisposicionVOBuilder newBuilder() {
        return new RetencionDisposicionVOBuilder();
    }

    public RetencionDisposicionVOBuilder withAcTrd(Long acTrd) {
        this.acTrd = acTrd;
        return  this;
    }

    public RetencionDisposicionVOBuilder withAgTrd(Long agTrd) {
        this.agTrd = agTrd;
        return  this;
    }

    public RetencionDisposicionVOBuilder withIdeTabRetDoc(BigInteger ideTabRetDoc) {
        this.ideTabRetDoc = ideTabRetDoc;
        return  this;
    }

    public RetencionDisposicionVOBuilder withProTrd(String proTrd) {
        this.proTrd = proTrd;
        return  this;
    }

    public RetencionDisposicionVOBuilder withIdeDisFinal(BigInteger ideDisFinal) {
        this.ideDisFinal = ideDisFinal;
        return  this;
    }

    public RetencionDisposicionVOBuilder withCodOfcProd(String codOfcProd) {
        this.codOfcProd = codOfcProd;
        return  this;
    }

    public RetencionDisposicionVOBuilder withCodUniAmt(String codUniAmt) {
        this.codUniAmt = codUniAmt;
        return  this;
    }

    public RetencionDisposicionVOBuilder withIdSerie(BigInteger idSerie) {
        this.idSerie = idSerie;
        return  this;
    }

    public RetencionDisposicionVOBuilder withIdSubserie(BigInteger idSubserie) {
        this.idSubserie = idSubserie;
        return  this;
    }
    public RetencionDisposicionVO build() {
        return new RetencionDisposicionVO(ideTabRetDoc,acTrd,agTrd, proTrd, ideDisFinal,codOfcProd,
                codUniAmt,idSerie, idSubserie);
    }
}
