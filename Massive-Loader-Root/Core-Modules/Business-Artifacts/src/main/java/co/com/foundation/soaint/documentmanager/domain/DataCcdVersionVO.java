package co.com.foundation.soaint.documentmanager.domain;

import java.math.BigInteger;
import java.util.Date;

/**
 * Created by jrodriguez on 14/12/2016.
 */
public class DataCcdVersionVO {

    private Long ideCcd;
    private Date fecCreacion;
    private String valVersion;
    private String ideUnidadAdministrativa ;
    private String NombreUnidadAdministrativa;
    private String ideOfcProdCodOrganigrama;
    private String NombreOfcProdOrganigrama;
    private BigInteger ideSerie;
    private String codSerie;
    private String nombreSerie;
    private BigInteger idSubSerie;
    private String codSubSerie;
    private String nombreSubSerie;
    private String  nombreTipologia;
    private BigInteger numVersionOrg;

    public DataCcdVersionVO(){}


    public DataCcdVersionVO(Long ideCcd, Date fecCreacion, String valVersion, String ideUnidadAdministrativa,
                            String nombreUnidadAdministrativa, String ideOfcProdCodOrganigrama,
                            String nombreOfcProdOrganigrama, BigInteger ideSerie, String codSerie,
                            String nombreSerie, BigInteger idSubSerie, String codSubSerie, String nombreSubSerie,
                            String nombreTipologia) {
        this.ideCcd = ideCcd;
        this.fecCreacion = fecCreacion;
        this.valVersion = valVersion;
        this.ideUnidadAdministrativa = ideUnidadAdministrativa;
        NombreUnidadAdministrativa = nombreUnidadAdministrativa;
        this.ideOfcProdCodOrganigrama = ideOfcProdCodOrganigrama;
        NombreOfcProdOrganigrama = nombreOfcProdOrganigrama;
        this.ideSerie = ideSerie;
        this.codSerie = codSerie;
        this.nombreSerie = nombreSerie;
        this.idSubSerie = idSubSerie;
        this.codSubSerie = codSubSerie;
        this.nombreSubSerie = nombreSubSerie;
        this.nombreTipologia = nombreTipologia;
    }

    public DataCcdVersionVO(BigInteger numVersionOrg) {
        this.numVersionOrg = numVersionOrg;
    }

    public Long getIdeCcd() {
        return ideCcd;
    }

    public void setIdeCcd(Long ideCcd) {
        this.ideCcd = ideCcd;
    }

    public Date getFecCreacion() {
        return fecCreacion;
    }

    public void setFecCreacion(Date fecCreacion) {
        this.fecCreacion = fecCreacion;
    }

    public String getValVersion() {
        return valVersion;
    }

    public void setValVersion(String valVersion) {
        this.valVersion = valVersion;
    }

    public String getIdeUnidadAdministrativa() {
        return ideUnidadAdministrativa;
    }

    public void setIdeUnidadAdministrativa(String ideUnidadAdministrativa) {
        this.ideUnidadAdministrativa = ideUnidadAdministrativa;
    }

    public String getNombreUnidadAdministrativa() {
        return NombreUnidadAdministrativa;
    }

    public void setNombreUnidadAdministrativa(String nombreUnidadAdministrativa) {
        NombreUnidadAdministrativa = nombreUnidadAdministrativa;
    }

    public String getIdeOfcProdCodOrganigrama() {
        return ideOfcProdCodOrganigrama;
    }

    public void setIdeOfcProdCodOrganigrama(String ideOfcProdCodOrganigrama) {
        this.ideOfcProdCodOrganigrama = ideOfcProdCodOrganigrama;
    }

    public String getNombreOfcProdOrganigrama() {
        return NombreOfcProdOrganigrama;
    }

    public void setNombreOfcProdOrganigrama(String nombreOfcProdOrganigrama) {
        NombreOfcProdOrganigrama = nombreOfcProdOrganigrama;
    }

    public BigInteger getIdeSerie() {
        return ideSerie;
    }

    public void setIdeSerie(BigInteger ideSerie) {
        this.ideSerie = ideSerie;
    }

    public String getCodSerie() {
        return codSerie;
    }

    public void setCodSerie(String codSerie) {
        this.codSerie = codSerie;
    }

    public String getNombreSerie() {
        return nombreSerie;
    }

    public void setNombreSerie(String nombreSerie) {
        this.nombreSerie = nombreSerie;
    }

    public BigInteger getIdSubSerie() {
        return idSubSerie;
    }

    public void setIdSubSerie(BigInteger idSubSerie) {
        this.idSubSerie = idSubSerie;
    }

    public String getCodSubSerie() {
        return codSubSerie;
    }

    public void setCodSubSerie(String codSubSerie) {
        this.codSubSerie = codSubSerie;
    }

    public String getNombreSubSerie() {
        return nombreSubSerie;
    }

    public void setNombreSubSerie(String nombreSubSerie) {
        this.nombreSubSerie = nombreSubSerie;
    }

    public String getNombreTipologia() {
        return nombreTipologia;
    }

    public void setNombreTipologia(String nombreTipologia) {
        this.nombreTipologia = nombreTipologia;
    }

    public BigInteger getNumVersionOrg() {
        return numVersionOrg;
    }

    public void setNumVersionOrg(BigInteger numVersionOrg) {
        this.numVersionOrg = numVersionOrg;
    }

    @Override
    public String toString() {
        return "DataCcdVersionVO{" +
                "ideCcd=" + ideCcd +
                ", fecCreacion=" + fecCreacion +
                ", valVersion='" + valVersion + '\'' +
                ", ideUnidadAdministrativa='" + ideUnidadAdministrativa + '\'' +
                ", NombreUnidadAdministrativa='" + NombreUnidadAdministrativa + '\'' +
                ", ideOfcProdCodOrganigrama='" + ideOfcProdCodOrganigrama + '\'' +
                ", NombreOfcProdOrganigrama='" + NombreOfcProdOrganigrama + '\'' +
                ", ideSerie=" + ideSerie +
                ", codSerie='" + codSerie + '\'' +
                ", nombreSerie='" + nombreSerie + '\'' +
                ", idSubSerie=" + idSubSerie +
                ", codSubSerie='" + codSubSerie + '\'' +
                ", nombreSubSerie='" + nombreSubSerie + '\'' +
                ", nombreTipologia='" + nombreTipologia + '\'' +
                ", numVersionOrg=" + numVersionOrg +
                '}';
    }
}
