package co.com.foundation.soaint.documentmanager.web.domain;

import co.com.foundation.soaint.documentmanager.persistence.entity.AdmSerie;
import co.com.foundation.soaint.documentmanager.persistence.entity.AdmSubserie;

import java.math.BigInteger;
import java.util.Date;

/**
 * Created by ADMIN on 02/12/2016.
 */
public class RelEquiVO {

    private String valVersionCCD;
    private String ideRelOrigen;
    private String ideOrgaAdminUniAmtOr;
    private String nombreUAdminO;
    private String ideOrgaAdminOfProdOr;
    private String nombreOProd;
    private BigInteger ideSerieOr;
    private String codSerieOr;
    private String nomSerieOr;
    private BigInteger ideSubserieOr;
    private String codSubserieOr;
    private String nomSubserieOr;
    private Date fecCreacionOr;

    private String labelCodNomUAdminOr;
    private String labelCodNomOProOr;
    private String labelCodNomSerieOr;
    private String labelCodNomSubSerieOr;

    private BigInteger ideRelDestino;
    private String ideOrgaAdminUniAmtDes;
    private String nombreUAdminD;
    private String ideOrgaAdminOfProdDes;
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

    public RelEquiVO() {
    }

    public RelEquiVO(BigInteger String, String ideOrgaAdminUniAmtOr, String ideOrgaAdminOfProdOr, BigInteger ideSerieOr, BigInteger ideSubserieOr, Date fecCreacionOr, BigInteger ideRelDestino, String ideOrgaAdminUniAmtDes, String ideOrgaAdminOfProdDes, BigInteger ideSerieDe, BigInteger ideSubserieDe) {
        this.ideRelOrigen = ideRelOrigen;
        this.ideOrgaAdminUniAmtOr = ideOrgaAdminUniAmtOr;
        this.ideOrgaAdminOfProdOr = ideOrgaAdminOfProdOr;
        this.ideSerieOr = ideSerieOr;
        this.ideSubserieOr = ideSubserieOr;
        this.fecCreacionOr = fecCreacionOr;
        this.ideRelDestino = ideRelDestino;
        this.ideOrgaAdminUniAmtDes = ideOrgaAdminUniAmtDes;
        this.ideOrgaAdminOfProdDes = ideOrgaAdminOfProdDes;
        this.ideSerieDe = ideSerieDe;
        this.ideSubserieDe = ideSubserieDe;
    }

    public RelEquiVO(String valVersionCCD,String ideRelOrigen, String ideOrgaAdminUniAmtOr, String ideOrgaAdminOfProdOr, BigInteger ideSerieOr, BigInteger ideSubserieOr, Date fecCreacionOr, BigInteger ideRelDestino, String ideOrgaAdminUniAmtDes, String ideOrgaAdminOfProdDes, BigInteger ideSerieDe, BigInteger ideSubserieDe) {
        this.ideRelOrigen = ideRelOrigen;
        this.ideOrgaAdminUniAmtOr = ideOrgaAdminUniAmtOr;
        this.ideOrgaAdminOfProdOr = ideOrgaAdminOfProdOr;
        this.ideSerieOr = ideSerieOr;
        this.ideSubserieOr = ideSubserieOr;
        this.fecCreacionOr = fecCreacionOr;
        this.ideRelDestino = ideRelDestino;
        this.ideOrgaAdminUniAmtDes = ideOrgaAdminUniAmtDes;
        this.ideOrgaAdminOfProdDes = ideOrgaAdminOfProdDes;
        this.ideSerieDe = ideSerieDe;
        this.ideSubserieDe = ideSubserieDe;
        this.valVersionCCD = valVersionCCD;
    }

    public RelEquiVO(String ideRelOrigen, String ideOrgaAdminUniAmtOr, String nombreUAdminO,  String ideOrgaAdminOfProdOr, String nombreOProd ,
                     BigInteger ideSerieOr, String codSerieOr,String nomSerieOr, String labelCodNomSerieOr, String labelCodNomSubSerieOr, String labelCodNomUAdminOr, String labelCodNomOProOr ,
                     BigInteger ideSubserieOr,String codSubserieOr, String nomSubserieOr, Date fecCreacionOr,
                     BigInteger ideRelDestino, String ideOrgaAdminUniAmtDes, String nombreUAdminD, String ideOrgaAdminOfProdDes, String nombreOProdD,
                     BigInteger ideSerieDe, String codSerieDe,String nomSerieDe,
                     BigInteger ideSubserieDe, String codSubserieDe, String nomSubserieDe,
                     String labelCodNomSerieD, String labelCodNomUAdminD, String labelCodNomOProD, String labelCodNomSubSerieD,
                     Date fecCreacionDe) {
        this.ideRelOrigen = ideRelOrigen;

        this.ideOrgaAdminUniAmtOr = ideOrgaAdminUniAmtOr;
        this.nombreUAdminO = nombreUAdminO;
        this.ideOrgaAdminOfProdOr = ideOrgaAdminOfProdOr;
        this.nombreOProd =nombreOProd;

        this.ideSerieOr = ideSerieOr;
        this.codSerieOr =codSerieOr == null ? "": codSerieOr ;
        this.nomSerieOr =nomSerieOr == null ? "N/A-": nomSerieOr;

        this.ideSubserieOr = ideSubserieOr;
        this.codSubserieOr =codSubserieOr == null ? "":  codSubserieOr;
        this.nomSubserieOr = nomSubserieOr == null ? "N/A-":  nomSubserieOr;

        this.labelCodNomSerieOr = labelCodNomSerieOr;
        this.labelCodNomSubSerieOr = labelCodNomSubSerieOr;
        this.labelCodNomUAdminOr = labelCodNomUAdminOr;
        this.labelCodNomOProOr = labelCodNomOProOr;

        this.fecCreacionOr = fecCreacionOr;

        this.ideRelDestino = ideRelDestino;

        this.ideOrgaAdminUniAmtDes = ideOrgaAdminUniAmtDes;
        this.nombreUAdminD = nombreUAdminD;
        this.ideOrgaAdminOfProdDes = ideOrgaAdminOfProdDes;
        this.nombreOProdD = nombreOProdD;

        this.ideSerieDe = ideSerieDe;
        this.codSerieDe = codSerieDe == null ? "": codSerieDe ;
        this.nomSerieDe = nomSerieDe== null ? "N/A-": nomSerieDe ;

        this.ideSubserieDe = ideSubserieDe  ;
        this.codSubserieDe = codSubserieDe == null ? "": codSubserieDe ;
        this.nomSubserieDe = nomSubserieDe == null ? "N/A-": nomSubserieDe ;

        this.labelCodNomSerieD =labelCodNomSerieD;
        this.labelCodNomUAdminD =labelCodNomUAdminD;
        this.labelCodNomOProD =labelCodNomOProD;
        this.labelCodNomSubSerieD = labelCodNomSubSerieD;

        this.fecCreacionDe = fecCreacionDe;

    }

    public String getValVersionCCD() {
        return valVersionCCD;
    }

    public void setValVersionCCD(String valVersionCCD) {
        this.valVersionCCD = valVersionCCD;
    }

    public String getIdeRelOrigen() {
        return ideRelOrigen;
    }

    public void setIdeRelOrigen(String ideRelOrigen) {
        this.ideRelOrigen = ideRelOrigen;
    }

    public String getIdeOrgaAdminUniAmtOr() {
        return ideOrgaAdminUniAmtOr;
    }


    public String getIdeOrgaAdminOfProdOr() {
        return ideOrgaAdminOfProdOr;
    }


    public BigInteger getIdeSerieOr() {
        return ideSerieOr;
    }

    public void setIdeSerieOr(BigInteger ideSerieOr) {
        this.ideSerieOr = ideSerieOr;
    }

    public String getCodSerieOr() {
        return codSerieOr;
    }

    public void setCodSerieOr(String codSerieOr) {
        this.codSerieOr = codSerieOr;
    }

    public String getNomSerieOr() {
        return nomSerieOr;
    }

    public void setNomSerieOr(String nomSerieOr) {
        this.nomSerieOr = nomSerieOr;
    }

    public BigInteger getIdeSubserieOr() {
        return ideSubserieOr;
    }

    public void setIdeSubserieOr(BigInteger ideSubserieOr) {
        this.ideSubserieOr = ideSubserieOr;
    }

    public String getCodSubserieOr() {
        return codSubserieOr;
    }

    public void setCodSubserieOr(String codSubserieOr) {
        this.codSubserieOr = codSubserieOr;
    }

    public String getNomSubserieOr() {
        return nomSubserieOr;
    }

    public void setNomSubserieOr(String nomSubserieOr) {
        this.nomSubserieOr = nomSubserieOr;
    }

    public Date getFecCreacionOr() {
        return fecCreacionOr;
    }

    public void setFecCreacionOr(Date fecCreacionOr) {
        this.fecCreacionOr = fecCreacionOr;
    }

    public BigInteger getIdeRelDestino() {
        return ideRelDestino;
    }

    public void setIdeRelDestino(BigInteger ideRelDestino) {
        this.ideRelDestino = ideRelDestino;
    }

    public String getideOrgaAdminUniAmtDes() {
        return ideOrgaAdminUniAmtDes;
    }

    public void setideOrgaAdminUniAmtDes(String ideOrgaAdminUniAmtDes) {
        this.ideOrgaAdminUniAmtDes = ideOrgaAdminUniAmtDes;
    }

    public String getideOrgaAdminOfProdDes() {
        return ideOrgaAdminOfProdDes;
    }

    public void setideOrgaAdminOfProdDes(String ideOrgaAdminOfProdDes) {
        this.ideOrgaAdminOfProdDes = ideOrgaAdminOfProdDes;
    }

    public BigInteger getIdeSerieDe() {
        return ideSerieDe;
    }

    public void setIdeSerieDe(BigInteger ideSerieDe) {
        this.ideSerieDe = ideSerieDe;
    }

    public BigInteger getIdeSubserieDe() {
        return ideSubserieDe;
    }

    public void setIdeSubserieDe(BigInteger ideSubserieDe) {
        this.ideSubserieDe = ideSubserieDe;
    }

    public Date getFecCreacionDe() {
        return fecCreacionDe;
    }

    public void setFecCreacionDe(Date fecCreacionDe) {
        this.fecCreacionDe = fecCreacionDe;
    }


    public String getLabelCodNomSerieOr() {
        return codSerieOr+ " - " +nomSerieOr;
    }

    public String getLabelCodNomSubSerieOr() {
        return codSubserieOr + " - " +nomSubserieOr ;
    }

    public String getLabelCodNomUAdminOr() {
        return ideOrgaAdminUniAmtOr+ " - " +nombreUAdminO;
    }

    public String getLabelCodNomOProOr() { return ideOrgaAdminOfProdOr+ " - " +nombreOProd;  }

    public String getNombreUAdminO() {
        return nombreUAdminO;
    }

    public void setNombreUAdminO(String nombreUAdminO) {
        this.nombreUAdminO = nombreUAdminO;
    }

    public String getNombreOProd() {
        return nombreOProd;
    }

    public void setNombreOProd(String nombreOProd) {
        this.nombreOProd = nombreOProd;
    }

    public void setLabelCodNomUAdminOr(String labelCodNomUAdminOr) {
        this.labelCodNomUAdminOr = labelCodNomUAdminOr;
    }

    public void setLabelCodNomOProOr(String labelCodNomOProOr) {
        this.labelCodNomOProOr = labelCodNomOProOr;
    }

    public void setLabelCodNomSerieOr(String labelCodNomSerieOr) {
        this.labelCodNomSerieOr = labelCodNomSerieOr;
    }

    public void setLabelCodNomSubSerieOr(String labelCodNomSubSerieOr) { this.labelCodNomSubSerieOr = labelCodNomSubSerieOr;  }

    public String getNombreUAdminD() {
        return nombreUAdminD;
    }

    public void setNombreUAdminD(String nombreUAdminD) {
        this.nombreUAdminD = nombreUAdminD;
    }

    public String getNombreOProdD() {
        return nombreOProdD;
    }

    public void setNombreOProdD(String nombreOProdD) {
        this.nombreOProdD = nombreOProdD;
    }

    public void setLabelCodNomSerieD(String labelCodNomSerieD) {
        this.labelCodNomSerieD = labelCodNomSerieD;
    }

    public String getLabelCodNomSerieD() {
        return codSerieDe+ " - " +nomSerieDe;
    }

    public String getLabelCodNomUAdminD() {
        return ideOrgaAdminUniAmtDes+ " - " +nombreUAdminD;
    }

    public String getLabelCodNomOProD() {
        return ideOrgaAdminOfProdDes+ " - " +nombreOProdD;
    }

    public String getLabelCodNomSubSerieD() {
        return  codSubserieDe+ " - " +nomSubserieDe;
    }

    public void setLabelCodNomUAdminD(String labelCodNomUAdminD) {
        this.labelCodNomUAdminD = labelCodNomUAdminD;
    }

    public void setLabelCodNomOProD(String labelCodNomOProD) {
        this.labelCodNomOProD = labelCodNomOProD;
    }

    public void setLabelCodNomSubSerieD(String labelCodNomSubSerieD) { this.labelCodNomSubSerieD = labelCodNomSubSerieD;  }

    public String getCodSerieDe() {
        return codSerieDe;
    }

    public void setCodSerieDe(String codSerieDe) {
        this.codSerieDe = codSerieDe;
    }

    public String getNomSerieDe() {
        return nomSerieDe;
    }

    public void setNomSerieDe(String nomSerieDe) {
        this.nomSerieDe = nomSerieDe;
    }

    public String getCodSubserieDe() {
        return codSubserieDe;
    }

    public void setCodSubserieDe(String codSubserieDe) {
        this.codSubserieDe = codSubserieDe;
    }

    public String getNomSubserieDe() {
        return nomSubserieDe;
    }

    public void setNomSubserieDe(String nomSubserieDe) {
        this.nomSubserieDe = nomSubserieDe;
    }

    @Override
    public String toString() {
        return "RelEquiVO{" +
                "ideRelOrigen=" + ideRelOrigen +
                ", ideOrgaAdminUniAmtOr='" + ideOrgaAdminUniAmtOr + '\'' +
                ", nombreUAdminO='" + nombreUAdminO + '\'' +
                ", ideOrgaAdminOfProdOr='" + ideOrgaAdminOfProdOr + '\'' +
                ", nombreOProd='" + nombreOProd + '\'' +
                ", ideSerieOr=" + ideSerieOr +
                ", codSerieOr='" + codSerieOr + '\'' +
                ", nomSerieOr='" + nomSerieOr + '\'' +
                ", ideSubserieOr=" + ideSubserieOr +
                ", codSubserieOr='" + codSubserieOr + '\'' +
                ", nomSubserieOr='" + nomSubserieOr + '\'' +
                ", fecCreacionOr=" + fecCreacionOr +
                ", labelCodNomUAdminOr='" + labelCodNomUAdminOr + '\'' +
                ", labelCodNomOProOr='" + labelCodNomOProOr + '\'' +
                ", labelCodNomSerieOr='" + labelCodNomSerieOr + '\'' +
                ", labelCodNomSubSerieOr='" + labelCodNomSubSerieOr + '\'' +
                ", ideRelDestino=" + ideRelDestino +
                ", ideOrgaAdminUniAmtDes='" + ideOrgaAdminUniAmtDes + '\'' +
                ", nombreUAdminD='" + nombreUAdminD + '\'' +
                ", ideOrgaAdminOfProdDes='" + ideOrgaAdminOfProdDes + '\'' +
                ", nombreOProdD='" + nombreOProdD + '\'' +
                ", ideSerieDe=" + ideSerieDe +
                ", codSerieDe='" + codSerieDe + '\'' +
                ", nomSerieDe='" + nomSerieDe + '\'' +
                ", ideSubserieDe=" + ideSubserieDe +
                ", codSubserieDe='" + codSubserieDe + '\'' +
                ", nomSubserieDe='" + nomSubserieDe + '\'' +
                ", labelCodNomSerieD='" + labelCodNomSerieD + '\'' +
                ", labelCodNomUAdminD='" + labelCodNomUAdminD + '\'' +
                ", labelCodNomOProD='" + labelCodNomOProD + '\'' +
                ", labelCodNomSubSerieD='" + labelCodNomSubSerieD + '\'' +
                ", fecCreacionDe=" + fecCreacionDe +
                '}';
    }
}
