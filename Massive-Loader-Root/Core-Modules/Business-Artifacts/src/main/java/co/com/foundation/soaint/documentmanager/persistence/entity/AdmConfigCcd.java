/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.foundation.soaint.documentmanager.persistence.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

/**
 * @author jrodriguez
 */
@Entity
@Table(name = "ADM_CONFIG_CCD")
@NamedQueries({

        @NamedQuery(name = "AdmConfigCcd.updateCuadroClasiDocConfig",
                query = "UPDATE AdmConfigCcd t SET t.estCcd = :ESTADO,"
                        + " t.fecCambio =:DATE"
                        + " WHERE t.ideCcd =:IDE_CCD"),

        @NamedQuery(name = "AdmConfigCcd.findAll",
                query = "SELECT NEW co.com.foundation. soaint.documentmanager.persistence.entity.AdmConfigCcd "
                        + "(c.ideCcd, ua.ideOrgaAdmin, op.ideOrgaAdmin, s.ideSerie, ss.ideSubserie,"
                        + "ua.nomOrg, ua.codOrg, op.nomOrg, op.codOrg, s.codSerie, s.nomSerie, ss.codSubserie, "
                        + "ss.nomSubserie, c.estCcd, c.fecCambio, c.fecCreacion) "
                        + "FROM  AdmConfigCcd c "
                        + "INNER JOIN TvsOrganigramaAdministrativo ua on ua.codOrg = c.ideUniAmt "
                        + "INNER JOIN TvsOrganigramaAdministrativo op  on op.codOrg = c.ideOfcProd "
                        + "INNER JOIN c.ideSerie s "
                        + "LEFT OUTER JOIN c.ideSubserie ss WHERE ua.valVersion ='TOP' AND op.valVersion = 'TOP' "),

        @NamedQuery(name = "AdmConfigCcd.countByExistCcdBySerieAndSubserie",
                query = "SELECT COUNT(a) FROM AdmConfigCcd a  "
                        + "WHERE a.ideSerie.ideSerie =:ID_SERIE "
                        + "and a.ideSubserie.ideSubserie =:ID_SUBSERIE  "
                        + "and a.ideUniAmt =:ID_UNIAMT "
                        + "and a.ideOfcProd =:ID_OFIPRO"),

        @NamedQuery(name = "AdmConfigCcd.countByExistCcdBySerie",
                query = "SELECT COUNT(a) FROM AdmConfigCcd a  "
                        + "WHERE a.ideSerie.ideSerie =:ID_SERIE "
                        + "and a.ideUniAmt =:ID_UNIAMT "
                        + "and a.ideOfcProd =:ID_OFIPRO"),

        @NamedQuery(name = "AdmConfigCcd.searchByUniAdminAndOfcProdAndIdSerieAndSubSerie",
                query = "SELECT NEW co.com.foundation. soaint.documentmanager.persistence.entity.AdmConfigCcd "
                        + "(c.ideCcd) "
                        + "FROM  AdmConfigCcd c "
                        + "WHERE c.ideUniAmt =:ID_UNIAMT AND c.ideOfcProd =:ID_OFIPRO AND c.ideSerie.ideSerie =:ID_SERIE AND c.ideSubserie.ideSubserie =:ID_SUBSERIE "),

        @NamedQuery(name = "AdmConfigCcd.searchByUniAdminAndOfcProdAndIdSerie",
                query = "SELECT NEW co.com.foundation. soaint.documentmanager.persistence.entity.AdmConfigCcd "
                        + "(c.ideCcd) "
                        + "FROM  AdmConfigCcd c "
                        + "WHERE c.ideUniAmt =:ID_UNIAMT AND c.ideOfcProd =:ID_OFIPRO AND c.ideSerie.ideSerie =:ID_SERIE"),

        @NamedQuery(name = "AdmConfigCcd.deleteSerieById",
                query = "DELETE FROM AdmConfigCcd c WHERE c.ideSerie.ideSerie = :ID_SERIE"),

        @NamedQuery(name = "AdmConfigCcd.deleteSubserieById",
                query = "DELETE FROM AdmConfigCcd c WHERE c.ideSubserie.ideSubserie = :ID_SUBSERIE")

})

@javax.persistence.TableGenerator(name = "ADM_CONFIG_CCD_SEQ", table = "TABLE_GENERATOR", pkColumnName = "SEQ_NAME",
        valueColumnName = "SEQ_VALUE", pkColumnValue = "ADM_CONFIG_CCD_SEQ", allocationSize = 1)

public class AdmConfigCcd implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "IDE_CCD")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ADM_CONFIG_CCD_SEQ")
    private Long ideCcd;
    @Column(name = "FEC_CAMBIO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecCambio;
    @Column(name = "FEC_CREACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecCreacion;
    @Column(name = "IDE_USUARIO_CAMBIO")
    private BigInteger ideUsuarioCambio;
    @Column(name = "IDE_UUID")
    private String ideUuid;
    @Column(name = "NIV_ESCRITURA")
    private Integer nivEscritura;
    @Column(name = "NIV_LECTURA")
    private Integer nivLectura;
    @Basic(optional = false)
    @Column(name = "IDE_OFC_PROD")
    private String ideOfcProd;
    @Basic(optional = false)
    @Column(name = "IDE_UNI_AMT")
    private String ideUniAmt;
    @Column(name = "EST_CCD")
    private Integer estCcd;
    @JoinColumn(name = "IDE_SERIE", referencedColumnName = "IDE_SERIE")
    @ManyToOne(optional = false)
    private AdmSerie ideSerie;
    @JoinColumn(name = "IDE_SUBSERIE", referencedColumnName = "IDE_SUBSERIE")
    @ManyToOne
    private AdmSubserie ideSubserie;

    @Transient
    String nombreUnidadAdministrativa;
    @Transient
    String ideUnidadAdministrativa;
    @Transient
    String nombreOfcProdOrganigrama;
    @Transient
    String ideOfcProdCodOrganigrama;


    public AdmConfigCcd() {
    }

    public AdmConfigCcd(Long ideCcd) {
        this.ideCcd = ideCcd;
    }

    public AdmConfigCcd(Long ideCcd, String ideOfcProd, String ideUniAmt) {
        this.ideCcd = ideCcd;
        this.ideOfcProd = ideOfcProd;
        this.ideUniAmt = ideUniAmt;
    }




    //AdmCcdConfig.findAll
    public AdmConfigCcd(Long ideCcd,
                        Long idUnidAdm,
                        Long idOficProd,

                        BigInteger idSerie,
                        BigInteger idSubSerie,

                        String NombreUnidadAdministrativa,
                        String ideUnidadAdministrativa ,
                        String NombreOfcProdOrganigrama,
                        String ideOfcProdCodOrganigrama,

                        String ideSerieCod, String ideSerieNombre, String codSubSerie,
                        String nombreSubSerie, Integer estCcd, Date fecCambio, Date fecCreacion) {

        this.ideCcd = ideCcd;
        this.ideUniAmt = idUnidAdm.toString();
        this.ideOfcProd = idOficProd.toString();

        this.nombreUnidadAdministrativa = NombreUnidadAdministrativa;
        this.ideUnidadAdministrativa = ideUnidadAdministrativa;
        this.nombreOfcProdOrganigrama = NombreOfcProdOrganigrama;
        this.ideOfcProdCodOrganigrama = ideOfcProdCodOrganigrama;


        this.ideSerie = new AdmSerie(idSerie, ideSerieCod, ideSerieNombre);
        this.ideSubserie = new AdmSubserie(idSubSerie, codSubSerie, nombreSubSerie);
        this.estCcd = estCcd;
        this.fecCambio = fecCambio;
        this.fecCreacion = fecCreacion;

    }


    //Crear Ccd Config
    public AdmConfigCcd(Long ideCcd, Date fecCambio, Date fecCreacion, BigInteger ideUsuarioCambio,
                        String ideUuid, Integer nivEscritura, Integer nivLectura, Integer estCcd, AdmSerie ideSerie,
                        AdmSubserie ideSubserie, String ideOfcProd, String ideUniAmt, String codOrgUniAmt, String codOrgOfProd) {

        this.ideCcd = ideCcd;
        this.fecCambio = fecCambio;
        this.fecCreacion = fecCreacion;
        this.ideUsuarioCambio = ideUsuarioCambio;
        this.ideUuid = ideUuid;
        this.nivEscritura = nivEscritura;
        this.nivLectura = nivLectura;
        this.estCcd = estCcd;
        this.ideSerie = ideSerie;
        this.ideSubserie = ideSubserie;
    //    String valideUniAmt[] = ideUniAmt.split("-");
        this.ideUniAmt = ideUniAmt;
      //  String valideOfcProd[] = ideOfcProd.split("-");
        this.ideOfcProd = ideOfcProd;
    }

    public Long getIdeCcd() {
        return ideCcd;
    }

    public void setIdeCcd(Long ideCcd) {
        this.ideCcd = ideCcd;
    }

    public Date getFecCambio() {
        return fecCambio;
    }

    public void setFecCambio(Date fecCambio) {
        this.fecCambio = fecCambio;
    }

    public Date getFecCreacion() {
        return fecCreacion;
    }

    public void setFecCreacion(Date fecCreacion) {
        this.fecCreacion = fecCreacion;
    }

    public BigInteger getIdeUsuarioCambio() {
        return ideUsuarioCambio;
    }

    public void setIdeUsuarioCambio(BigInteger ideUsuarioCambio) {
        this.ideUsuarioCambio = ideUsuarioCambio;
    }

    public String getIdeUuid() {
        return ideUuid;
    }

    public void setIdeUuid(String ideUuid) {
        this.ideUuid = ideUuid;
    }

    public Integer getNivEscritura() {
        return nivEscritura;
    }

    public void setNivEscritura(Integer nivEscritura) {
        this.nivEscritura = nivEscritura;
    }

    public Integer getNivLectura() {
        return nivLectura;
    }

    public void setNivLectura(Integer nivLectura) {
        this.nivLectura = nivLectura;
    }

    public String getIdeOfcProd() {
        return ideOfcProd;
    }

    public void setIdeOfcProd(String ideOfcProd) {
        this.ideOfcProd = ideOfcProd;
    }

    public String getIdeUniAmt() {
        return ideUniAmt;
    }

    public void setIdeUniAmt(String ideUniAmt) {
        this.ideUniAmt = ideUniAmt;
    }

    public Integer getEstCcd() {
        return estCcd;
    }

    public void setEstCcd(Integer estCcd) {
        this.estCcd = estCcd;
    }

    public AdmSerie getIdeSerie() {
        return ideSerie;
    }

    public void setIdeSerie(AdmSerie ideSerie) {
        this.ideSerie = ideSerie;
    }

    public AdmSubserie getIdeSubserie() {
        return ideSubserie;
    }

    public void setIdeSubserie(AdmSubserie ideSubserie) {
        this.ideSubserie = ideSubserie;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ideCcd != null ? ideCcd.hashCode() : 0);
        return hash;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }



    public String getIdeUnidadAdministrativa() {
        return ideUnidadAdministrativa;
    }

    public void setIdeUnidadAdministrativa(String ideUnidadAdministrativa) {
        this.ideUnidadAdministrativa = ideUnidadAdministrativa;
    }

    public String getNombreUnidadAdministrativa() {
        return nombreUnidadAdministrativa;
    }

    public void setNombreUnidadAdministrativa(String nombreUnidadAdministrativa) {
        this.nombreUnidadAdministrativa = nombreUnidadAdministrativa;
    }

    public String getNombreOfcProdOrganigrama() {
        return nombreOfcProdOrganigrama;
    }

    public void setNombreOfcProdOrganigrama(String nombreOfcProdOrganigrama) {
        this.nombreOfcProdOrganigrama = nombreOfcProdOrganigrama;
    }

    public String getIdeOfcProdCodOrganigrama() {
        return ideOfcProdCodOrganigrama;
    }

    public void setIdeOfcProdCodOrganigrama(String ideOfcProdCodOrganigrama) {
        this.ideOfcProdCodOrganigrama = ideOfcProdCodOrganigrama;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AdmConfigCcd)) {
            return false;
        }
        AdmConfigCcd other = (AdmConfigCcd) object;
        if ((this.ideCcd == null && other.ideCcd != null) || (this.ideCcd != null && !this.ideCcd.equals(other.ideCcd))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.foundation.soaint.documentmanager.persistence.entity.AdmConfigCcd[ ideCcd=" + ideCcd + " ]";
    }

}
