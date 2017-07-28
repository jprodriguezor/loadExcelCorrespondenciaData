package co.com.foundation.soaint.documentmanager.web.infrastructure.builder.generic;

import co.com.foundation.soaint.documentmanager.persistence.entity.AdmSerie;
import co.com.foundation.soaint.documentmanager.web.domain.RelEquiVO;
import co.com.foundation.soaint.infrastructure.builder.Builder;

import java.math.BigInteger;
import java.util.Date;
import java.util.stream.DoubleStream;

/**
 * Created by ADMIN on 02/12/2016.
 */
public class RelEquiVoBuilder implements Builder<RelEquiVO> {

    private String ideRelOrigen;
    private String ideUniAmtOr;
    private String nombreUAdminO;
    private String ideOfcProdOr;
    private String nombreOProd;
    private BigInteger ideSerieOr;
    private String codSerieOr;
    private String nomSerieOr;
    private BigInteger ideSubserieOr;
    private String codSubserieOr;
    private String nomSubserieOr;


    private String labelCodNomSerieOr;
    private String labelCodNomUAdminOr;
    private String labelCodNomOProOr;
    private String labelCodNomSubSerieOr;

    private Date fecCreacionOr;

    private BigInteger ideRelDestino;
    private String ideUniAmtDe;
    private String nombreUAdminD;
    private String ideOfcProdDe;
    private String nombreOProdD;
    private BigInteger ideSerieDe;
    private String codSerieDe;
    private String nomSerieDe;

    private BigInteger ideSubserieDe;
    private String codSubserieDe;
    private String nomSubserieDe;

    private String labelCodNomSerieD;
    private String labelCodNomUAdminD;
    private String labelCodNomOProD;
    private String labelCodNomSubSerieD;

    private Date fecCreacionDe;

    public RelEquiVoBuilder() {
    }

    public static RelEquiVoBuilder newBuilder() {
        return new RelEquiVoBuilder();
    }

    public RelEquiVoBuilder withIdeRelOrigen(String ideRelOrigen) {
        this.ideRelOrigen = ideRelOrigen;
        return this;
    }

    public RelEquiVoBuilder withIdeUniAmtOr(String ideUniAmtOr) {
        this.ideUniAmtOr = ideUniAmtOr;
        return this;
    }

    public  RelEquiVoBuilder withIdeOfcProdOr(String ideOfcProdOr) {
        this.ideOfcProdOr = ideOfcProdOr;
        return this;
    }

    public RelEquiVoBuilder withIdeSerieOr(BigInteger ideSerieOr) {
        this.ideSerieOr = ideSerieOr;
        return this;
    }

    public RelEquiVoBuilder withCodSerieOr(String codSerieOr) {
        this.codSerieOr = codSerieOr;
        return this;
    }

    public RelEquiVoBuilder withNomSerieOr(String nomSerieOr) {
        this.nomSerieOr = nomSerieOr;
        return this;
    }

    public  RelEquiVoBuilder withIdeSubserieOr(BigInteger ideSubserieOr) {
        this.ideSubserieOr = ideSubserieOr;
        return this;
    }

    public  RelEquiVoBuilder withFecCreacionOr(Date fecCreacionOr) {
        this.fecCreacionOr = fecCreacionOr;
        return this;
    }

    public  RelEquiVoBuilder withIdeRelDestino(BigInteger ideRelDestino) {
        this.ideRelDestino = ideRelDestino;
        return this;
    }

    public  RelEquiVoBuilder withIdeUniAmtDe(String ideUniAmtDe) {
        this.ideUniAmtDe = ideUniAmtDe;
        return this;
    }

    public  RelEquiVoBuilder withIdeOfcProdDe(String ideOfcProdDe) {
        this.ideOfcProdDe = ideOfcProdDe;
        return this;
    }

    public  RelEquiVoBuilder withIdeSerieDe(BigInteger ideSerieDe) {
        this.ideSerieDe = ideSerieDe;
        return this;
    }

    public  RelEquiVoBuilder withIdeSubserieDe(BigInteger ideSubserieDe) {
        this.ideSubserieDe = ideSubserieDe;
        return this;
    }

    public  RelEquiVoBuilder withFecCreacionDe(Date fecCreacionDe) {
        this.fecCreacionDe = fecCreacionDe;
        return this;
    }

    public  RelEquiVoBuilder withLabelCodNomSerieOr(String labelCodNomSerieOr) {
        this.labelCodNomSerieOr = labelCodNomSerieOr;
        return this;
    }

    public RelEquiVoBuilder withLabelCodNomUAdminOr(String labelCodNomUAdminOr) {
        this.labelCodNomUAdminOr = labelCodNomUAdminOr;
        return this;
    }

    public RelEquiVoBuilder withLabelCodNomOProOr(String labelCodNomOProOr) {
        this.labelCodNomOProOr = labelCodNomOProOr;
        return this;
    }

    public RelEquiVoBuilder withNombreUAdminO(String nombreUAdminO) {
        this.nombreUAdminO = nombreUAdminO;
        return this;
    }

    public RelEquiVoBuilder withNombreOProd(String nombreOProd) {
        this.nombreOProd = nombreOProd;
        return this;
    }

    public RelEquiVoBuilder withCodSubserieOr(String codSubserieOr) {
        this.codSubserieOr = codSubserieOr;
        return this;
    }

    public RelEquiVoBuilder withNomSubserieOr(String nomSubserieOr) {
        this.nomSubserieOr = nomSubserieOr;
        return this;
    }

    public RelEquiVoBuilder withCodSerieDe(String codSerieDe) {
        this.codSerieDe = codSerieDe;
        return this;
    }

    public RelEquiVoBuilder withNomSerieDe(String nomSerieDe) {
        this.nomSerieDe = nomSerieDe;
        return this;
    }

    public RelEquiVoBuilder withCodSubserieDe(String codSubserieDe) {
        this.codSubserieDe = codSubserieDe;
        return this;
    }

    public RelEquiVoBuilder withNomSubserieDe(String nomSubserieDe) {
        this.nomSubserieDe = nomSubserieDe;
        return this;
    }

    public RelEquiVoBuilder withLabelCodNomSubSerieOr(String labelCodNomSubSerieOr) {
        this.labelCodNomSubSerieOr = labelCodNomSubSerieOr;
        return this;
    }

    public RelEquiVoBuilder withLabelCodNomSerieD(String labelCodNomSerieD) {
        this.labelCodNomSerieD = labelCodNomSerieD;
        return this;
    }

    public RelEquiVoBuilder withLabelCodNomUAdminD(String labelCodNomUAdminD) {
        this.labelCodNomUAdminD = labelCodNomUAdminD;
        return this;
    }

    public RelEquiVoBuilder withLabelCodNomOProD(String labelCodNomOProD) {
        this.labelCodNomOProD = labelCodNomOProD;
        return this;
    }

    public RelEquiVoBuilder withLabelCodNomSubSerieD(String labelCodNomSubSerieD) {
        this.labelCodNomSubSerieD = labelCodNomSubSerieD;
        return this;
    }

    public RelEquiVoBuilder withNombreUAdminD(String nombreUAdminD) {
        this.nombreUAdminD = nombreUAdminD;
        return this;
    }

    public RelEquiVoBuilder withNombreOProdD(String nombreOProdD) {
        this.nombreOProdD = nombreOProdD;
        return this;
    }

    public RelEquiVO build() {
        return new RelEquiVO(ideRelOrigen, ideUniAmtOr, nombreUAdminO, ideOfcProdOr, nombreOProd,
                                ideSerieOr, codSerieOr, nomSerieOr,
                                labelCodNomSerieOr , labelCodNomSubSerieOr, labelCodNomUAdminOr ,labelCodNomOProOr,
                                ideSubserieOr, codSubserieOr, nomSubserieOr, fecCreacionOr,
                                ideRelDestino, ideUniAmtDe, nombreUAdminD, ideOfcProdDe, nombreOProdD,
                                ideSerieDe, codSerieDe, nomSerieDe,
                                ideSubserieDe, codSubserieDe, nomSubserieDe,
                                labelCodNomSerieD , labelCodNomUAdminD, labelCodNomOProD ,labelCodNomSubSerieD
                                ,fecCreacionDe);
    }
}
