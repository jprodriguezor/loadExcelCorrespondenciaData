package co.com.foundation.soaint.documentmanager.persistence.entity;

/**
 * Created by jrodriguez on 19/10/2016.
 */

import co.com.foundation.soaint.documentmanager.domain.TiposDocCCDVO;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "ADM_CCD")
@NamedQueries({
        @NamedQuery(name = "AdmCcd.updateVersion",
                query = "UPDATE AdmCcd c SET c.valVersion = :VAL_VERSION  WHERE c.valVersion =:VERSION"),

        @NamedQuery(name = "AdmCcd.maxVersionOrgByCdd",
                query = "SELECT CASE WHEN(MAX(c.numVersionOrg) IS NULL) THEN 0  ELSE (MAX(c.numVersionOrg)) END FROM AdmCcd c "),

        @NamedQuery(name = "AdmCcd.updateVersionOrg",
                query = "UPDATE AdmCcd c SET c.valVersionOrg = :VAL_VERSION , c.numVersionOrg =:NUM_VERSION  WHERE c.valVersionOrg = 'TOP'"),

        @NamedQuery(name = "AdmCcd.versionCcd",
                query = " SELECT NEW co.com.foundation.soaint.documentmanager.persistence.entity.AdmCcd "
                        + " (c.valVersion, MAX(c.fecCreacion)) "
                        + " FROM AdmCcd c"
                        + " GROUP BY c.valVersion "
                        + " ORDER BY c.valVersion ASC "),

        @NamedQuery(name = "AdmCcd.versionCcdNum",
                query = " SELECT  NEW co.com.foundation.soaint.documentmanager.domain.RelEquiItemVO "
                        + " (c.valVersion,c.numVersionOrg, MAX(c.fecCreacion)) "
                        + " FROM AdmCcd c"
                        + " GROUP BY c.numVersionOrg,c.valVersion "
                        + " ORDER BY c.numVersionOrg ASC "),

        @NamedQuery(name = "AdmCcd.countSerieExistByIdInCcd",
                query = "SELECT COUNT(*) FROM AdmCcd s WHERE s.ideSerie.ideSerie = :ID_SERIE"),

        @NamedQuery(name = "AdmCcd.countSubserieExistByIdInCcd",
                query = "SELECT COUNT(*) FROM AdmCcd s WHERE s.ideSerie.ideSerie = :ID_SUBSERIE"),

        @NamedQuery(name = "AdmCcd.consultarUnidadAdministrativaExistByCcd",
                query = "SELECT NEW co.com.foundation.soaint.documentmanager.domain.CcdItemVO (c.ideUniAmt, ua.ideOrgaAdmin, ua.nomOrg ) " +
                        "FROM AdmCcd c " +
                        "INNER JOIN TvsOrganigramaAdministrativo ua on c.ideUniAmt = ua.codOrg  " +
                        "WHERE c.estCcd = :STATUS and c.valVersion = :VERSION AND  ua.valVersion = c.valVersionOrg " +
                        "group by c.ideUniAmt, ua.ideOrgaAdmin, ua.nomOrg "),

        @NamedQuery(name = "AdmCcd.consultarUnidadAdministrativaExistByCcdDes",
                query = "SELECT NEW co.com.foundation.soaint.documentmanager.domain.CcdItemVO (c.ideUniAmt, ua.ideOrgaAdmin, ua.nomOrg ) " +
                        "FROM AdmCcd c " +
                        "INNER JOIN TvsOrganigramaAdministrativo ua on c.ideUniAmt = ua.codOrg  " +
                        "WHERE c.estCcd = :STATUS and c.valVersion = 'TOP' AND  ua.valVersion = 'TOP' " +
                        "group by c.ideUniAmt, ua.ideOrgaAdmin, ua.nomOrg "),


        @NamedQuery(name = "AdmCcd.consultarOficinaProdExistByCcd",
                query = "SELECT NEW co.com.foundation.soaint.documentmanager.domain.CcdItemVO (c.ideOfcProd, ua.nomOrg ) " +
                        "FROM AdmCcd c " +
                        "INNER JOIN TvsOrganigramaAdministrativo ua on c.ideOfcProd = ua.codOrg  " +
                        "WHERE c.ideUniAmt =:COD_UNI_ADMIN AND c.estCcd = :STATUS AND c.valVersion = :VERSION AND  ua.valVersion = c.valVersionOrg " +
                        " group by c.ideOfcProd, ua.nomOrg"),

        @NamedQuery(name = "AdmCcd.consultarOficinaProdExistByCcdDe",
                query = "SELECT NEW co.com.foundation.soaint.documentmanager.domain.CcdItemVO (c.ideOfcProd, ua.nomOrg ) " +
                        "FROM AdmCcd c " +
                        "INNER JOIN TvsOrganigramaAdministrativo ua on c.ideOfcProd = ua.codOrg  " +
                        "WHERE c.ideUniAmt =:COD_UNI_ADMIN AND c.estCcd = :STATUS AND c.valVersion = 'TOP' AND  ua.valVersion = 'TOP' " +
                        " group by c.ideOfcProd, ua.nomOrg"),

        @NamedQuery(name = "AdmCcd.serieExistByCcd",
                query = "SELECT NEW co.com.foundation.soaint.documentmanager.persistence.entity.AdmCcd (s.ideSerie, s.codSerie, s.nomSerie ) " +
                        "FROM AdmCcd c " +
                        "INNER JOIN c.ideSerie s " +
                        "WHERE c.estCcd = :STATUS " +
                        "AND c.ideUniAmt = :IDEUNIADMIN " +
                        "AND  c.ideOfcProd = :IDEOFIPROD " +
                        "AND  c.valVersion = :VERSION " +
                        " group by s.ideSerie, s.codSerie, s.nomSerie "),

        @NamedQuery(name = "AdmCcd.serieExistByCcdDe",
                query = "SELECT NEW co.com.foundation.soaint.documentmanager.persistence.entity.AdmCcd (s.ideSerie, s.codSerie, s.nomSerie ) " +
                        "FROM AdmCcd c " +
                        "INNER JOIN c.ideSerie s " +
                        "WHERE c.estCcd = :STATUS " +
                        "AND c.ideUniAmt = :IDEUNIADMIN " +
                        "AND  c.ideOfcProd = :IDEOFIPROD " +
                        "AND  c.valVersion = 'TOP' " +
                        " group by s.ideSerie, s.codSerie, s.nomSerie "),

        @NamedQuery(name = "AdmCcd.subSerieExistByCcd",
                query = "SELECT NEW co.com.foundation.soaint.documentmanager.persistence.entity.AdmCcd (ss.ideSubserie, ss.codSubserie, ss.nomSubserie, ss.fecCreacion ) " +
                        "FROM AdmCcd c " +
                        "INNER JOIN c.ideSubserie ss " +
                        "WHERE c.estCcd = :STATUS and ss.ideSerie.ideSerie = :SERIE " +
                        "AND c.ideUniAmt = :IDEUNIADMIN " +
                        "AND  c.ideOfcProd = :IDEOFIPROD " +
                        "AND  c.valVersion = :VERSION " +
                        " group by ss.ideSubserie, ss.codSubserie, ss.nomSubserie, ss.fecCreacion "),



        @NamedQuery(name = "AdmCcd.subSerieExistByCcdDe",
                query = "SELECT NEW co.com.foundation.soaint.documentmanager.persistence.entity.AdmCcd (ss.ideSubserie, ss.codSubserie, ss.nomSubserie, ss.fecCreacion ) " +
                        "FROM AdmCcd c " +
                        "INNER JOIN c.ideSubserie ss " +
                        "WHERE c.estCcd = :STATUS and ss.ideSerie.ideSerie = :SERIE " +
                        "AND c.ideUniAmt = :IDEUNIADMIN " +
                        "AND  c.ideOfcProd = :IDEOFIPROD " +
                        "AND  c.valVersion = 'TOP' " +
                        " group by ss.ideSubserie, ss.codSubserie, ss.nomSubserie, ss.fecCreacion "),

        @NamedQuery(name = "AdmCcd.findAllAdmCcdByValVersion",
                query = "SELECT NEW co.com.foundation.soaint.documentmanager.persistence.entity.AdmCcd "
                        + "(c.ideCcd, ua.ideOrgaAdmin, op.ideOrgaAdmin, s.ideSerie, ss.ideSubserie,"
                        + "ua.nomOrg, ua.codOrg, op.nomOrg, op.codOrg, s.codSerie, s.nomSerie, ss.codSubserie, "
                        + "ss.nomSubserie, c.estCcd, c.fecCambio, c.fecCreacion) "
                        + "FROM  AdmCcd c "
                        + "INNER JOIN TvsOrganigramaAdministrativo ua on ua.codOrg = c.ideUniAmt "
                        + "INNER JOIN TvsOrganigramaAdministrativo op  on op.codOrg = c.ideOfcProd "
                        + "INNER JOIN c.ideSerie s "
                        + "LEFT OUTER JOIN c.ideSubserie ss where c.valVersion  = :VAL_VERSION AND ua.valVersion ='TOP' AND op.valVersion = 'TOP'  "),

        @NamedQuery(name = "AdmCcd.findVersionCcdByValVersion",
                query = "SELECT NEW co.com.foundation.soaint.documentmanager.domain.DataCcdVersionVO "
                        + "(c.ideCcd, c.fecCreacion, c.valVersion, ua.codOrg, ua.nomOrg , op.codOrg, op.nomOrg, "
                        + "s.ideSerie, s.codSerie, s.nomSerie,  ss.ideSubserie, ss.codSubserie, ss.nomSubserie, td.nomTpgDoc ) "
                        + "FROM  AdmCcd c "
                        + "INNER JOIN TvsOrganigramaAdministrativo ua on ua.codOrg = c.ideUniAmt "
                        + "INNER JOIN TvsOrganigramaAdministrativo op on op.codOrg = c.ideOfcProd "
                        + "INNER JOIN c.ideSerie s "
                        + "LEFT OUTER JOIN c.ideSubserie ss "
                        + "inner join AdmSerSubserTpg aso on aso.ideSerie.ideSerie = s.ideSerie.ideSerie and aso.ideSubserie.ideSubserie = ss.ideSubserie "
                        + "inner join AdmTpgDoc td on td.ideTpgDoc = aso.ideTpgDoc.ideTpgDoc "
                        + "where ua.valVersion =c.valVersionOrg AND op.valVersion =c.valVersionOrg AND c.valVersion =:VAL_VERSION ORDER BY c.ideCcd, s.ideSerie,ss.ideSubserie "),

        @NamedQuery(name = "AdmCcd.findVersionCcdSerieByValVersion",
                query = "SELECT NEW co.com.foundation.soaint.documentmanager.domain.DataCcdVersionVO "
                        + "(c.ideCcd, c.fecCreacion, c.valVersion, ua.codOrg, ua.nomOrg , op.codOrg, op.nomOrg, "
                        + "s.ideSerie, s.codSerie, s.nomSerie,  ss.ideSubserie, ss.codSubserie, ss.nomSubserie, td.nomTpgDoc ) "
                        + "FROM  AdmCcd c "
                        + "INNER JOIN TvsOrganigramaAdministrativo ua on ua.codOrg = c.ideUniAmt "
                        + "INNER JOIN TvsOrganigramaAdministrativo op on op.codOrg = c.ideOfcProd "
                        + "INNER JOIN c.ideSerie s "
                        + "LEFT OUTER JOIN c.ideSubserie ss "
                        + "inner join AdmSerSubserTpg aso on aso.ideSerie.ideSerie = s.ideSerie.ideSerie and aso.ideSubserie.ideSubserie IS NULL "
                        + "inner join AdmTpgDoc td on td.ideTpgDoc = aso.ideTpgDoc.ideTpgDoc "
                        + "where ua.valVersion =c.valVersionOrg AND op.valVersion = c.valVersionOrg AND c.valVersion =:VAL_VERSION ORDER BY c.ideCcd, s.ideSerie,ss.ideSubserie "),

        @NamedQuery(name = "AdmCcd.findVersionOrgByValVersion",
                query = "SELECT NEW co.com.foundation.soaint.documentmanager.domain.DataCcdVersionVO "
                        + "(c.numVersionOrg) "
                        + "FROM  AdmCcd c "
                        + "where c.valVersion = :VAL_VERSION GROUP BY c.numVersionOrg ")

})

@javax.persistence.TableGenerator(name = "ADM_CCD_SEQ", table = "TABLE_GENERATOR", pkColumnName = "SEQ_NAME",
        valueColumnName = "SEQ_VALUE", pkColumnValue = "ADM_CCD_SEQ", allocationSize = 1)

public class AdmCcd implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "IDE_CCD")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ADM_CCD_SEQ")
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

    @Column(name = "EST_CCD")
    private Integer estCcd;

    @Basic(optional = false)
    @Column(name = "IDE_OFC_PROD")
    private String ideOfcProd;

    @Basic(optional = false)
    @Column(name = "IDE_UNI_AMT")
    private String ideUniAmt;

    @Column(name = "VAL_VERSION")
    private String valVersion;

    @Column(name = "VAL_VERSION_ORG")
    private String valVersionOrg;

    @Column(name = "NUM_VERSION_ORG")
    private BigInteger numVersionOrg;

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

    @Transient
    String nombreTipologia;

    @Transient
    List<TiposDocCCDVO> listTiposDoc;


    public AdmCcd() {
    }

    //AdmCcd.versionCcd
    public AdmCcd(String valVersion, Date fecCreacion) {
        this.fecCreacion = fecCreacion;
        this.valVersion = valVersion;
    }


    public AdmCcd(String valVersion, BigInteger numVersionOrg, Date fecCreacion) {
        this.fecCreacion = fecCreacion;
        this.numVersionOrg = numVersionOrg;
        this.valVersion = valVersion;
    }

    public AdmCcd(Long ideCcd,
                  Long idUnidAdm,
                  Long idOficProd,

                  BigInteger idSerie,
                  BigInteger idSubSerie,

                  String NombreUnidadAdministrativa,
                  String ideUnidadAdministrativa,
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
        this.ideSubserie = (idSubSerie == null ? null : new AdmSubserie(idSubSerie, codSubSerie, nombreSubSerie));
        this.estCcd = estCcd;
        this.fecCambio = fecCambio;
        this.fecCreacion = fecCreacion;

    }


    public AdmCcd(Long ideCcd, Date fecCambio, Date fecCreacion, BigInteger ideUsuarioCambio,
                  String ideUuid, Integer nivEscritura, Integer nivLectura, Integer estCcd, BigInteger ideSerie,
                  BigInteger ideSubserie, String ideOfcProd, String ideUniAmt, String valVersion) {
        this.ideCcd = ideCcd;
        this.fecCambio = fecCambio;
        this.fecCreacion = fecCreacion;
        this.ideUsuarioCambio = ideUsuarioCambio;
        this.ideUuid = ideUuid;
        this.nivEscritura = nivEscritura;
        this.nivLectura = nivLectura;
        this.estCcd = estCcd;
        this.ideSerie = new AdmSerie(ideSerie);
        this.ideSubserie = new AdmSubserie(ideSubserie);
        this.ideOfcProd = ideOfcProd;
        this.ideUniAmt = ideUniAmt;
        this.valVersion = valVersion;

    }

    public AdmCcd(Long ideCcd, Date fecCreacion, String valVersion,
                  String ideUnidadAdministrativa, String NombreUnidadAdministrativa,
                  String ideOfcProdCodOrganigrama, String NombreOfcProdOrganigrama, BigInteger ideSerie, String codSerie, String nombreSerie,
                  BigInteger idSubSerie, String codSubSerie, String nombreSubSerie, String nombreTipologia) {
        this.ideCcd = ideCcd;
        this.fecCreacion = fecCreacion;
        this.valVersion = valVersion;
        this.nombreUnidadAdministrativa = NombreUnidadAdministrativa;
        this.ideUnidadAdministrativa = ideUnidadAdministrativa;
        this.nombreOfcProdOrganigrama = NombreOfcProdOrganigrama;
        this.ideOfcProdCodOrganigrama = ideOfcProdCodOrganigrama;
        this.ideSerie = new AdmSerie(ideSerie, codSerie, nombreSerie);
        this.ideSubserie = (idSubSerie == null ? new AdmSubserie() : new AdmSubserie(idSubSerie, codSubSerie, nombreSubSerie));
        this.nombreTipologia = nombreTipologia;
        //this.listTiposDoc = d.;


    }

    public AdmCcd(BigInteger ideSerie, String codSerie, String nomSerie) {

        this.ideSerie = new AdmSerie(ideSerie, codSerie, nomSerie);
    }

    public AdmCcd(BigInteger idSubSerie, String codSubSerie, String nombreSubSerie, Date fecCreacion) {

        this.ideSubserie = new AdmSubserie(idSubSerie, codSubSerie, nombreSubSerie);
    }

    public String getValVersionOrg() {
        return valVersionOrg;
    }

    public void setValVersionOrg(String valVersionOrg) {
        this.valVersionOrg = valVersionOrg;
    }

    public BigInteger getNumVersionOrg() {
        return numVersionOrg;
    }

    public void setNumVersionOrg(BigInteger numVersionOrg) {
        this.numVersionOrg = numVersionOrg;
    }

    public AdmCcd(Long ideCcd) {
        this.ideCcd = ideCcd;
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

    public Integer getEstCcd() {
        return estCcd;
    }

    public void setEstCcd(Integer estCcd) {
        this.estCcd = estCcd;
    }

    public void setIdeOfcProd(String ideOfcProd) {
        this.ideOfcProd = ideOfcProd;
    }

    public void setIdeUniAmt(String ideUniAmt) {
        this.ideUniAmt = ideUniAmt;
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

    public String getNombreUnidadAdministrativa() {
        return nombreUnidadAdministrativa;
    }

    public void setNombreUnidadAdministrativa(String nombreUnidadAdministrativa) {
        this.nombreUnidadAdministrativa = nombreUnidadAdministrativa;
    }

    public String getIdeUnidadAdministrativa() {
        return ideUnidadAdministrativa;
    }

    public void setIdeUnidadAdministrativa(String ideUnidadAdministrativa) {
        this.ideUnidadAdministrativa = ideUnidadAdministrativa;
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

    public String getNombreTipologia() {
        return nombreTipologia;
    }

    public void setNombreTipologia(String nombreTipologia) {
        this.nombreTipologia = nombreTipologia;
    }


    public List<TiposDocCCDVO> getListTiposDoc() {
        return listTiposDoc;
    }

    public void setListTiposDoc(List<TiposDocCCDVO> listTiposDoc) {
        this.listTiposDoc = listTiposDoc;
    }

    public String getValVersion() {
        return valVersion;
    }

    public void setValVersion(String valVersion) {
        this.valVersion = valVersion;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ideCcd != null ? ideCcd.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AdmCcd)) {
            return false;
        }
        AdmCcd other = (AdmCcd) object;
        if ((this.ideCcd == null && other.ideCcd != null) || (this.ideCcd != null && !this.ideCcd.equals(other.ideCcd))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AdmCcd{" + "ideCcd=" + ideCcd + ", fecCambio=" + fecCambio + ", fecCreacion=" + fecCreacion + ", ideUsuarioCambio=" + ideUsuarioCambio + ", ideUuid=" + ideUuid + ", nivEscritura=" + nivEscritura + ", nivLectura=" + nivLectura + ", estCcd=" + estCcd + ", ideOfcProd=" + ideOfcProd + ", ideUniAmt=" + ideUniAmt + ", valVersion=" + valVersion + ", ideSerie=" + ideSerie + ", ideSubserie=" + ideSubserie + ", nombreUnidadAdministrativa=" + nombreUnidadAdministrativa + ", ideUnidadAdministrativa=" + ideUnidadAdministrativa + ", nombreOfcProdOrganigrama=" + nombreOfcProdOrganigrama + ", ideOfcProdCodOrganigrama=" + ideOfcProdCodOrganigrama + ", nombreTipologia=" + nombreTipologia + '}';
    }


}
