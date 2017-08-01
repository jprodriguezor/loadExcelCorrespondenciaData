/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.foundation.soaint.documentmanager.persistence.entity;

import javax.persistence.*;
import javax.persistence.TableGenerator;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

/**
 * @author jrodriguez
 */
@Entity
@Table(name = "ADM_TAB_RET_DOC")
@NamedQueries({

        @NamedQuery(name = "AdmTabRetDoc.searchByUniAdminAndOfcProdAndIdSerieAndSubSerie",
                query = "SELECT NEW co.com.foundation.soaint.documentmanager.persistence.entity.AdmTabRetDoc(a.ideTabRetDoc, " +
                        "a.acTrd,a.agTrd,a.estTabRetDoc,a.proTrd,  d.ideDisFinal ,d.nomDisFinal, a.ideOfcProd,  a.ideUniAmt,  s.ideSerie,  " +
                        "s.nomSerie,  sub.ideSubserie,  sub.nomSubserie , ua.ideOrgaAdmin , op.ideOrgaAdmin )" +
                        "FROM AdmTabRetDoc a " +
                        "INNER JOIN  a.admDisFinal d " +
                        "INNER JOIN a.ideSerie s " +
                        "INNER JOIN a.ideSubserie sub " +
                        "INNER JOIN TvsOrganigramaAdministrativo ua on ua.codOrg = a.ideUniAmt " +
                        "INNER JOIN TvsOrganigramaAdministrativo op on op.codOrg = a.ideOfcProd " +
                        "AND ua.codOrg =:ID_UNI_AMT AND op.codOrg =:ID_OFC_PROD AND s.ideSerie =:ID_SERIE AND sub.ideSubserie =:ID_SUBSERIE " +
                        "AND ua.valVersion = 'TOP' AND op.valVersion ='TOP'"),


        @NamedQuery(name = "AdmTabRetDoc.searchByUniAdminAndOfcProdAndIdSerie",
                query = "SELECT NEW co.com.foundation.soaint.documentmanager.persistence.entity.AdmTabRetDoc(a.ideTabRetDoc, " +
                        "a.acTrd,a.agTrd,a.estTabRetDoc,a.proTrd,  d.ideDisFinal ,d.nomDisFinal, a.ideOfcProd,  a.ideUniAmt,  s.ideSerie,   " +
                        "s.nomSerie, ua.ideOrgaAdmin , op.ideOrgaAdmin )" +
                        "FROM AdmTabRetDoc a " +
                        "INNER JOIN  a.admDisFinal d " +
                        "INNER JOIN a.ideSerie s " +
                        "INNER JOIN TvsOrganigramaAdministrativo ua on ua.codOrg = a.ideUniAmt " +
                        "INNER JOIN TvsOrganigramaAdministrativo op on op.codOrg = a.ideOfcProd " +
                        "AND ua.codOrg =:ID_UNI_AMT AND op.codOrg =:ID_OFC_PROD AND s.ideSerie =:ID_SERIE AND ua.valVersion = 'TOP' AND op.valVersion ='TOP'"),

        @NamedQuery(name = "AdmTabRetDoc.countByTabRecDocBySerieAndSubSerie",
                query = "SELECT COUNT(a)FROM AdmTabRetDoc a "
                        + "WHERE a.ideSerie.ideSerie =:ID_SERIE "
                        + "and a.ideSubserie.ideSubserie =:ID_SUBSERIE  "
                        + "and a.ideUniAmt =:ID_UNIAMT "
                        + "and a.ideOfcProd =:ID_OFIPRO"),

        @NamedQuery(name = "AdmTabRetDoc.countByTabRecDocBySerie",
                query = "SELECT COUNT(a)FROM AdmTabRetDoc a "
                        + "WHERE a.ideSerie.ideSerie =:ID_SERIE "
                        + "and a.ideUniAmt =:ID_UNIAMT "
                        + "and a.ideOfcProd =:ID_OFIPRO"),

        @NamedQuery(name = "AdmTabRetDoc.deleteSerieById",
                query = "DELETE FROM AdmTabRetDoc s WHERE s.ideSerie.ideSerie = :ID_SERIE"),

        @NamedQuery(name = "AdmTabRetDoc.deleteSubserieById",
                query = "DELETE FROM AdmTabRetDoc s WHERE s.ideSubserie.ideSubserie = :ID_SUBSERIE"),

        @NamedQuery(name = "AdmTabRetDoc.dataVersionTrdPorOfcProdSeSub",
                query = "SELECT NEW co.com.foundation.soaint.documentmanager.domain.DataVersionTrdVO(t.ideUniAmt, t.ideOfcProd, " +
                        "t.ideTabRetDoc, t.ideSerie.ideSerie , t.ideSubserie.ideSubserie , t.agTrd, t.acTrd, t.admDisFinal.ideDisFinal," +
                        "t.proTrd)FROM AdmTabRetDoc t " +
                        "INNER JOIN AdmCcd c on t.ideUniAmt = c.ideUniAmt " +
                        "AND t.ideOfcProd =c.ideOfcProd " +
                        "AND t.ideSerie.ideSerie = c.ideSerie.ideSerie " +
                        "AND t.ideSubserie.ideSubserie =c.ideSubserie.ideSubserie " +
                        "INNER JOIN TvsOrganigramaAdministrativo ua on ua.codOrg = c.ideUniAmt " +
                        "INNER JOIN TvsOrganigramaAdministrativo op on op.codOrg = c.ideOfcProd " +
                        "AND c.ideUniAmt =:ID_UNI_AMT AND c.ideOfcProd =:ID_OFC_PROD " +
                        "AND c.valVersion='TOP' AND c.estCcd =1 AND ua.valVersion = 'TOP' AND op.valVersion ='TOP'"),

        @NamedQuery(name = "AdmTabRetDoc.dataVersionTrdPorOfcProdSe",
                query = "SELECT NEW co.com.foundation.soaint.documentmanager.domain.DataVersionTrdVO(t.ideUniAmt, t.ideOfcProd, " +
                        "t.ideTabRetDoc, t.ideSerie.ideSerie , t.ideSubserie.ideSubserie , t.agTrd, t.acTrd, t.admDisFinal.ideDisFinal," +
                        "t.proTrd)FROM AdmTabRetDoc t " +
                        "INNER JOIN AdmCcd c on t.ideUniAmt = c.ideUniAmt " +
                        "AND t.ideOfcProd =c.ideOfcProd " +
                        "AND t.ideSerie.ideSerie = c.ideSerie.ideSerie " +
                        "AND t.ideSubserie.ideSubserie IS NULL " +
                        "INNER JOIN TvsOrganigramaAdministrativo ua on ua.codOrg = c.ideUniAmt " +
                        "INNER JOIN TvsOrganigramaAdministrativo op on op.codOrg = c.ideOfcProd " +
                        "AND c.ideUniAmt =:ID_UNI_AMT AND c.ideOfcProd =:ID_OFC_PROD " +
                        "AND c.valVersion='TOP' AND c.estCcd =1  AND ua.valVersion = 'TOP' AND op.valVersion ='TOP'")
})

@NamedNativeQueries({@NamedNativeQuery(name = "AdmTabRetDoc.generateTRDTable",
        query = "SELECT P.IDE_ORGA_ADMIN AS ID_UNI_AMD, P.COD_ORG AS COD_UNI_AMT, P.NOM_ORG AS NOM_UNI_AMT, O.IDE_ORGA_ADMIN AS ID_OFC_PROD, O.COD_ORG AS COD_OFC_PROD, O.NOM_ORG AS NOM_OFC_PROD,"+
                "S.IDE_SERIE, S.COD_SERIE, S.NOM_SERIE , SS.IDE_SUBSERIE, SS.COD_SUBSERIE, SS.NOM_SUBSERIE,"+
                "TP.NOM_TPG_DOC, T.AG_TRD, T.AC_TRD, T.PRO_TRD, T.IDE_DIS_FINAL , D.NOM_DIS_FINAL "+
                "FROM ADM_CCD C "+
                "INNER JOIN TVS_ORGANIGRAMA_ADMINISTRATIVO O ON C.IDE_OFC_PROD =O.COD_ORG "+
                "INNER JOIN TVS_ORGANIGRAMA_ADMINISTRATIVO P ON O.IDE_ORGA_ADMIN_PADRE =P.IDE_ORGA_ADMIN "+
                "INNER JOIN ADM_TAB_RET_DOC T "+
                "ON T.IDE_UNI_AMT   =C.IDE_UNI_AMT AND T.IDE_OFC_PROD =C.IDE_OFC_PROD "+
                "AND T.IDE_SERIE    =C.IDE_SERIE AND  T.IDE_SUBSERIE =C.IDE_SUBSERIE AND C.IDE_OFC_PROD =:ID_OFC_PROD "+
                "AND C.VAL_VERSION  ='TOP' AND C.EST_CCD=1 AND O.VAL_VERSION  ='TOP'  AND P.VAL_VERSION  ='TOP' "+
                "INNER JOIN ADM_SERIE S ON S.IDE_SERIE =T.IDE_SERIE  "+
                "LEFT OUTER JOIN ADM_SUBSERIE SS ON SS.IDE_SUBSERIE =T.IDE_SUBSERIE  "+
                "INNER JOIN ADM_SER_SUBSER_TPG SST ON SST.IDE_SERIE = S.IDE_SERIE AND SST.IDE_SUBSERIE =SS.IDE_SUBSERIE "+
                "INNER JOIN ADM_TPG_DOC TP ON TP.IDE_TPG_DOC =SST.IDE_TPG_DOC "+
                "INNER JOIN ADM_DIS_FINAL D "+
                "ON T.IDE_DIS_FINAL = D.IDE_DIS_FINAL  UNION "+
                "SELECT P.IDE_ORGA_ADMIN AS ID_UNI_AMD, P.COD_ORG AS COD_UNI_AMT, P.NOM_ORG AS NOM_UNI_AMT, O.IDE_ORGA_ADMIN AS ID_OFC_PROD, O.COD_ORG AS COD_OFC_PROD, O.NOM_ORG AS NOM_OFC_PROD,"+
                "S.IDE_SERIE, S.COD_SERIE, S.NOM_SERIE , SS.IDE_SUBSERIE, SS.COD_SUBSERIE, SS.NOM_SUBSERIE,"+
                "TP.NOM_TPG_DOC, T.AG_TRD, T.AC_TRD, T.PRO_TRD, T.IDE_DIS_FINAL , D.NOM_DIS_FINAL "+
                "FROM ADM_CCD C "+
                "INNER JOIN TVS_ORGANIGRAMA_ADMINISTRATIVO O ON C.IDE_OFC_PROD =O.COD_ORG "+
                "INNER JOIN TVS_ORGANIGRAMA_ADMINISTRATIVO P ON O.IDE_ORGA_ADMIN_PADRE =P.IDE_ORGA_ADMIN "+
                "INNER JOIN ADM_TAB_RET_DOC T "+
                "ON T.IDE_UNI_AMT   =C.IDE_UNI_AMT AND T.IDE_OFC_PROD =C.IDE_OFC_PROD "+
                "AND T.IDE_SERIE    =C.IDE_SERIE AND T.IDE_SUBSERIE IS NULL AND C.IDE_OFC_PROD =:ID_OFC_PROD "+
                "AND C.VAL_VERSION  ='TOP' AND C.EST_CCD=1 AND O.VAL_VERSION  ='TOP'  AND P.VAL_VERSION  ='TOP' "+
                "INNER JOIN ADM_SERIE S ON S.IDE_SERIE =T.IDE_SERIE  "+
                "LEFT OUTER JOIN ADM_SUBSERIE SS ON SS.IDE_SUBSERIE =T.IDE_SUBSERIE "+
                "INNER JOIN ADM_SER_SUBSER_TPG SST ON SST.IDE_SERIE = S.IDE_SERIE AND SST.IDE_SUBSERIE IS NULL "+
                "INNER JOIN ADM_TPG_DOC TP ON TP.IDE_TPG_DOC =SST.IDE_TPG_DOC "+
                "INNER JOIN ADM_DIS_FINAL D "+
                "ON T.IDE_DIS_FINAL = D.IDE_DIS_FINAL ORDER BY IDE_SERIE, IDE_SUBSERIE")})

@TableGenerator(name = "ADM_TAB_RET_DISP_GENERATOR", table = "TABLE_GENERATOR", pkColumnName = "SEQ_NAME",
        valueColumnName = "SEQ_VALUE", pkColumnValue = "ADM_TAB_RET_DOC_SEQ", allocationSize = 1)
public class AdmTabRetDoc implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "IDE_TAB_RET_DOC")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ADM_TAB_RET_DISP_GENERATOR")
    private BigInteger ideTabRetDoc;

    @Basic(optional = false)
    @Column(name = "AC_TRD")
    private Long acTrd;

    @Basic(optional = false)
    @Column(name = "AG_TRD")
    private Long agTrd;

    @Column(name = "EST_TAB_RET_DOC")
    private Integer estTabRetDoc;

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
    @Column(name = "PRO_TRD")
    private String proTrd;

    @JoinColumn(name = "IDE_DIS_FINAL", referencedColumnName = "IDE_DIS_FINAL")
    @ManyToOne(optional = false)
    private AdmDisFinal admDisFinal;

    @Basic(optional = false)
    @Column(name = "IDE_OFC_PROD")
    private String ideOfcProd;

    @Basic(optional = false)
    @Column(name = "IDE_UNI_AMT")
    private String ideUniAmt;

    @JoinColumn(name = "IDE_SERIE", referencedColumnName = "IDE_SERIE")
    @ManyToOne(optional = false)
    private AdmSerie ideSerie;

    @JoinColumn(name = "IDE_SUBSERIE", referencedColumnName = "IDE_SUBSERIE")
    @ManyToOne
    private AdmSubserie ideSubserie;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ideTabRetDoc")
    private List<AdmTabRetDocOrg> admTabRetDocOrgList;

    @Transient
    private Long codUniAmt;
    @Transient
    private Long codOfcProd;

    public AdmTabRetDoc() {
    }

    public AdmTabRetDoc(BigInteger ideTabRetDoc) {
        this.ideTabRetDoc = ideTabRetDoc;
    }

    public AdmTabRetDoc(BigInteger ideTabRetDoc, Long acTrd, Long agTrd, Integer estTabRetDoc, Date fecCambio, Date fecCreacion,
                        BigInteger ideUsuarioCambio, String ideUuid, Integer nivEscritura, Integer nivLectura, String proTrd,
                        AdmDisFinal admDisFinal, String ideOfcProd, String ideUniAmt, AdmSerie ideSerie, AdmSubserie ideSubserie) {
        this.ideTabRetDoc = ideTabRetDoc;
        this.acTrd = acTrd;
        this.agTrd = agTrd;
        this.estTabRetDoc = estTabRetDoc;
        this.fecCambio = fecCambio;
        this.fecCreacion = fecCreacion;
        this.ideUsuarioCambio = ideUsuarioCambio;
        this.ideUuid = ideUuid;
        this.nivEscritura = nivEscritura;
        this.nivLectura = nivLectura;
        this.proTrd = proTrd;
        this.admDisFinal = admDisFinal;
        this.ideOfcProd = ideOfcProd;
        this.ideUniAmt = ideUniAmt;
        this.ideSerie = ideSerie;
        this.ideSubserie = ideSubserie;
    }

    //[AdmTabRetDoc.searchByUniAdminAndOfcProdAndIdSerieAndSubSerie]
    public AdmTabRetDoc(BigInteger ideTabRetDoc,Long acTrd, Long agTrd, Integer estTabRetDoc, String proTrd, BigInteger idDisFinal,
                        String nomDisFinal, String ideOfcProd, String ideUniAmt, BigInteger idSerie, String nomSerie, BigInteger idSubserie,
                        String nomSubserie,  Long codUniAmt ,Long codOfcProd) {
        this.ideTabRetDoc =ideTabRetDoc;
        this.acTrd = acTrd;
        this.agTrd = agTrd;
        this.estTabRetDoc = estTabRetDoc;
        this.proTrd = proTrd;
        this.admDisFinal = new AdmDisFinal(idDisFinal,nomDisFinal);
        this.ideOfcProd = ideOfcProd;
        this.ideUniAmt = ideUniAmt;
        this.ideSerie = new AdmSerie(idSerie,nomSerie);
        this.ideSubserie = new AdmSubserie(idSubserie,nomSubserie);
        this.codUniAmt =codUniAmt;
        this.codOfcProd =codOfcProd;
    }

    //[AdmTabRetDoc.searchByUniAdminAndOfcProdAndIdSerie]
    public AdmTabRetDoc(BigInteger ideTabRetDoc,Long acTrd, Long agTrd, Integer estTabRetDoc, String proTrd, BigInteger idDisFinal,
                        String nomDisFinal, String ideOfcProd, String ideUniAmt, BigInteger idSerie, String nomSerie, Long codUniAmt ,Long codOfcProd) {
        this.ideTabRetDoc =ideTabRetDoc;
        this.acTrd = acTrd;
        this.agTrd = agTrd;
        this.estTabRetDoc = estTabRetDoc;
        this.proTrd = proTrd;
        this.admDisFinal = new AdmDisFinal(idDisFinal,nomDisFinal);
        this.ideOfcProd = ideOfcProd;
        this.ideUniAmt = ideUniAmt;
        this.ideSerie = new AdmSerie(idSerie,nomSerie);
        this.codUniAmt =codUniAmt;
        this.codOfcProd =codOfcProd;
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

    public void setAcTrd(Long acTrd) {
        this.acTrd = acTrd;
    }

    public Long getAgTrd() {
        return agTrd;
    }

    public void setAgTrd(Long agTrd) {
        this.agTrd = agTrd;
    }

    public Integer getEstTabRetDoc() {
        return estTabRetDoc;
    }

    public void setEstTabRetDoc(Integer estTabRetDoc) {
        this.estTabRetDoc = estTabRetDoc;
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

    public String getProTrd() {
        return proTrd;
    }

    public void setProTrd(String proTrd) {
        this.proTrd = proTrd;
    }

    public AdmDisFinal getAdmDisFinal() {
        return admDisFinal;
    }

    public void setAdmDisFinal(AdmDisFinal admDisFinal) {
        this.admDisFinal = admDisFinal;
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

    public Long getCodOfcProd() {
        return codOfcProd;
    }

    public void setCodOfcProd(Long codOfcProd) {
        this.codOfcProd = codOfcProd;
    }

    public Long getCodUniAmt() {
        return codUniAmt;
    }

    public void setCodUniAmt(Long codUniAmt) {
        this.codUniAmt = codUniAmt;
    }

    public List<AdmTabRetDocOrg> getAdmTabRetDocOrgList() {
        return admTabRetDocOrgList;
    }

    public void setAdmTabRetDocOrgList(List<AdmTabRetDocOrg> admTabRetDocOrgList) {
        this.admTabRetDocOrgList = admTabRetDocOrgList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ideTabRetDoc != null ? ideTabRetDoc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AdmTabRetDoc)) {
            return false;
        }
        AdmTabRetDoc other = (AdmTabRetDoc) object;
        if ((this.ideTabRetDoc == null && other.ideTabRetDoc != null) || (this.ideTabRetDoc != null && !this.ideTabRetDoc.equals(other.ideTabRetDoc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AdmTabRetDoc{" +
                "ideTabRetDoc=" + ideTabRetDoc +
                ", acTrd=" + acTrd +
                ", agTrd=" + agTrd +
                ", estTabRetDoc=" + estTabRetDoc +
                ", fecCambio=" + fecCambio +
                ", fecCreacion=" + fecCreacion +
                ", ideUsuarioCambio=" + ideUsuarioCambio +
                ", ideUuid='" + ideUuid + '\'' +
                ", nivEscritura=" + nivEscritura +
                ", nivLectura=" + nivLectura +
                ", proTrd='" + proTrd + '\'' +
                ", admDisFinal=" + admDisFinal +
                ", ideOfcProd='" + ideOfcProd + '\'' +
                ", ideUniAmt='" + ideUniAmt + '\'' +
                ", ideSerie=" + ideSerie +
                ", ideSubserie=" + ideSubserie +
                '}';
    }
}
