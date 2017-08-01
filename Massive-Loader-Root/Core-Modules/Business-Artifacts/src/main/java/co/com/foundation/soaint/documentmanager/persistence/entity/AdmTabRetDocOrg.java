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

/**
 * @author jrodriguez
 */
@Entity
@Table(name = "ADM_TAB_RET_DOC_ORG")
@NamedQueries({
        @NamedQuery(name = "AdmTabRetDocOrg.findAll", query = "SELECT a FROM AdmTabRetDocOrg a"),
})

@NamedNativeQueries({

        @NamedNativeQuery(name = "AdmTabRetDocOrg.versionTRDTable",
        query = "SELECT P.IDE_ORGA_ADMIN AS ID_UNI_AMD,P.COD_ORG AS COD_UNI_AMT,P.NOM_ORG AS NOM_UNI_AMT,O.IDE_ORGA_ADMIN AS ID_OFC_PROD, " +
                "O.COD_ORG AS COD_OFC_PROD,O.NOM_ORG AS NOM_OFC_PROD,S.IDE_SERIE,S.COD_SERIE,S.NOM_SERIE ,SS.IDE_SUBSERIE,SS.COD_SUBSERIE, " +
                "SS.NOM_SUBSERIE,TP.NOM_TPG_DOC,T.AG_TRD,T.AC_TRD,T.PRO_TRD,D.IDE_DIS_FINAL ,D.NOM_DIS_FINAL " +
                "FROM ADM_TAB_RET_DOC_ORG t " +
                "INNER JOIN TVS_ORGANIGRAMA_ADMINISTRATIVO O " +
                "ON T.IDE_OFC_PROD =O.COD_ORG " +
                "INNER JOIN TVS_ORGANIGRAMA_ADMINISTRATIVO p " +
                "ON P.IDE_ORGA_ADMIN =O.IDE_ORGA_ADMIN_PADRE " +
                "INNER JOIN ADM_TAB_RET_DOC RT " +
                "ON T.IDE_TAB_RET_DOC =RT.IDE_TAB_RET_DOC " +
                "AND O.VAL_VERSION =:VAL_VERSION_ORG  AND P.VAL_VERSION =:VAL_VERSION_ORG " +
                "INNER JOIN ADM_SERIE S " +
                "ON S.IDE_SERIE =RT.IDE_SERIE " +
                "LEFT OUTER JOIN ADM_SUBSERIE SS " +
                "ON SS.IDE_SUBSERIE =RT.IDE_SUBSERIE " +
                "INNER JOIN ADM_SER_SUBSER_TPG SST " +
                "ON SST.IDE_SERIE = S.IDE_SERIE " +
                "AND SST.IDE_SUBSERIE =SS.IDE_SUBSERIE "+
                "INNER JOIN ADM_TPG_DOC TP " +
                "ON TP.IDE_TPG_DOC =SST.IDE_TPG_DOC " +
                "INNER JOIN ADM_DIS_FINAL D " +
                "ON T.IDE_DIS_FINAL = D.IDE_DIS_FINAL " +
                "AND T.NUM_VERSION =:VAL_VERSION AND T.IDE_OFC_PROD=:ID_OFC_PROD  UNION  "+
                "SELECT P.IDE_ORGA_ADMIN AS ID_UNI_AMD,P.COD_ORG AS COD_UNI_AMT,P.NOM_ORG AS NOM_UNI_AMT,O.IDE_ORGA_ADMIN AS ID_OFC_PROD, " +
                "O.COD_ORG AS COD_OFC_PROD,O.NOM_ORG AS NOM_OFC_PROD,S.IDE_SERIE,S.COD_SERIE,S.NOM_SERIE ,SS.IDE_SUBSERIE,SS.COD_SUBSERIE, " +
                "SS.NOM_SUBSERIE,TP.NOM_TPG_DOC,T.AG_TRD,T.AC_TRD,T.PRO_TRD,D.IDE_DIS_FINAL ,D.NOM_DIS_FINAL " +
                "FROM ADM_TAB_RET_DOC_ORG t " +
                "INNER JOIN TVS_ORGANIGRAMA_ADMINISTRATIVO O " +
                "ON T.IDE_OFC_PROD =O.COD_ORG " +
                "INNER JOIN TVS_ORGANIGRAMA_ADMINISTRATIVO p " +
                "ON P.IDE_ORGA_ADMIN =O.IDE_ORGA_ADMIN_PADRE " +
                "INNER JOIN ADM_TAB_RET_DOC RT " +
                "ON T.IDE_TAB_RET_DOC =RT.IDE_TAB_RET_DOC " +
                "AND O.VAL_VERSION =:VAL_VERSION_ORG  AND P.VAL_VERSION =:VAL_VERSION_ORG " +
                "INNER JOIN ADM_SERIE S " +
                "ON S.IDE_SERIE =RT.IDE_SERIE " +
                "LEFT OUTER JOIN ADM_SUBSERIE SS " +
                "ON SS.IDE_SUBSERIE =RT.IDE_SUBSERIE " +
                "INNER JOIN ADM_SER_SUBSER_TPG SST " +
                "ON SST.IDE_SERIE = S.IDE_SERIE " +
                "AND SST.IDE_SUBSERIE IS NULL " +
                "INNER JOIN ADM_TPG_DOC TP " +
                "ON TP.IDE_TPG_DOC =SST.IDE_TPG_DOC " +
                "INNER JOIN ADM_DIS_FINAL D " +
                "ON T.IDE_DIS_FINAL = D.IDE_DIS_FINAL " +
                "AND T.NUM_VERSION =:VAL_VERSION AND T.IDE_OFC_PROD=:ID_OFC_PROD ORDER BY IDE_SERIE, IDE_SUBSERIE "
                ),

        @NamedNativeQuery(name = "AdmTabRetDocOrg.versionTRDTableECM",
                query = "SELECT P.IDE_ORGA_ADMIN AS ID_UNI_AMD,P.COD_ORG AS COD_UNI_AMT,P.NOM_ORG AS NOM_UNI_AMT,O.IDE_ORGA_ADMIN AS ID_OFC_PROD, " +
                        "O.COD_ORG AS COD_OFC_PROD,O.NOM_ORG AS NOM_OFC_PROD,S.IDE_SERIE,S.COD_SERIE,S.NOM_SERIE ,SS.IDE_SUBSERIE,SS.COD_SUBSERIE, " +
                        "SS.NOM_SUBSERIE,T.AG_TRD,T.AC_TRD,T.PRO_TRD,D.IDE_DIS_FINAL ,D.NOM_DIS_FINAL " +
                        "FROM ADM_TAB_RET_DOC_ORG t " +
                        "INNER JOIN TVS_ORGANIGRAMA_ADMINISTRATIVO O " +
                        "ON T.IDE_OFC_PROD =O.COD_ORG " +
                        "INNER JOIN TVS_ORGANIGRAMA_ADMINISTRATIVO p " +
                        "ON P.IDE_ORGA_ADMIN =O.IDE_ORGA_ADMIN_PADRE " +
                        "INNER JOIN ADM_TAB_RET_DOC RT " +
                        "ON T.IDE_TAB_RET_DOC =RT.IDE_TAB_RET_DOC " +
                        "AND O.VAL_VERSION ='TOP'  AND P.VAL_VERSION ='TOP' " +
                        "INNER JOIN ADM_SERIE S " +
                        "ON S.IDE_SERIE =RT.IDE_SERIE " +
                        "LEFT OUTER JOIN ADM_SUBSERIE SS " +
                        "ON SS.IDE_SUBSERIE =RT.IDE_SUBSERIE " +
                        "INNER JOIN ADM_DIS_FINAL D " +
                        "ON T.IDE_DIS_FINAL = D.IDE_DIS_FINAL " +
                        "AND T.NUM_VERSION =:VAL_VERSION AND T.IDE_OFC_PROD=:ID_OFC_PROD ORDER BY IDE_SERIE, IDE_SUBSERIE")
})


@TableGenerator(name = "ADM_TAB_RET_DISP_ORG_GENERATOR", table = "TABLE_GENERATOR", pkColumnName = "SEQ_NAME",
        valueColumnName = "SEQ_VALUE", pkColumnValue = "ADM_TAB_RET_DOC_ORG_SEQ", allocationSize = 1)

public class AdmTabRetDocOrg implements Serializable {

    private static final long serialVersionUID = 1L;

    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "IDE_TAB_RET_DOC_ORG")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ADM_TAB_RET_DISP_ORG_GENERATOR")
    private BigInteger ideTabRetDocOrg;

    @Basic(optional = false)
    @Column(name = "IDE_OFC_PROD")
    private String ideOfcProd;

    @Basic(optional = false)
    @Column(name = "IDE_UNI_AMT")
    private String ideUniAmt;

    @Basic(optional = false)
    @Column(name = "AC_TRD")
    private Long acTrd;

    @Basic(optional = false)
    @Column(name = "AG_TRD")
    private Long agTrd;

    @Basic(optional = false)
    @Column(name = "PRO_TRD")
    private String proTrd;

    @Basic(optional = false)
    @Column(name = "IDE_DIS_FINAL")
    private BigInteger ideDisFinal;

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
    @Column(name = "NUM_VERSION")
    private BigInteger numVersion;

    @JoinColumn(name = "IDE_TAB_RET_DOC", referencedColumnName = "IDE_TAB_RET_DOC")
    @ManyToOne(optional = false)
    private AdmTabRetDoc ideTabRetDoc;

    public AdmTabRetDocOrg() {
    }

    public AdmTabRetDocOrg(BigInteger ideTabRetDocOrg) {
        this.ideTabRetDocOrg = ideTabRetDocOrg;
    }


    public AdmTabRetDocOrg(String ideOfcProd, String ideUniAmt, Long acTrd, String proTrd, Long agTrd, BigInteger ideDisFinal,
                           Date fecCambio, BigInteger ideUsuarioCambio, Date fecCreacion, String ideUuid, Integer nivEscritura,
                           Integer nivLectura, BigInteger numVersion, AdmTabRetDoc ideTabRetDoc) {
        this.ideOfcProd = ideOfcProd;
        this.ideUniAmt = ideUniAmt;
        this.acTrd = acTrd;
        this.proTrd = proTrd;
        this.agTrd = agTrd;
        this.ideDisFinal = ideDisFinal;
        this.fecCambio = fecCambio;
        this.ideUsuarioCambio = ideUsuarioCambio;
        this.fecCreacion = fecCreacion;
        this.ideUuid = ideUuid;
        this.nivEscritura = nivEscritura;
        this.nivLectura = nivLectura;
        this.numVersion = numVersion;
        this.ideTabRetDoc = ideTabRetDoc;
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

    public BigInteger getIdeTabRetDocOrg() {
        return ideTabRetDocOrg;
    }

    public void setIdeTabRetDocOrg(BigInteger ideTabRetDocOrg) {
        this.ideTabRetDocOrg = ideTabRetDocOrg;
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

    public String getProTrd() {
        return proTrd;
    }

    public void setProTrd(String proTrd) {
        this.proTrd = proTrd;
    }

    public BigInteger getIdeDisFinal() {
        return ideDisFinal;
    }

    public void setIdeDisFinal(BigInteger ideDisFinal) {
        this.ideDisFinal = ideDisFinal;
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

    public BigInteger getNumVersion() {
        return numVersion;
    }

    public void setNumVersion(BigInteger numVersion) {
        this.numVersion = numVersion;
    }

    public AdmTabRetDoc getIdeTabRetDoc() {
        return ideTabRetDoc;
    }

    public void setIdeTabRetDoc(AdmTabRetDoc ideTabRetDoc) {
        this.ideTabRetDoc = ideTabRetDoc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ideTabRetDocOrg != null ? ideTabRetDocOrg.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AdmTabRetDocOrg)) {
            return false;
        }
        AdmTabRetDocOrg other = (AdmTabRetDocOrg) object;
        if ((this.ideTabRetDocOrg == null && other.ideTabRetDocOrg != null) || (this.ideTabRetDocOrg != null && !this.ideTabRetDocOrg.equals(other.ideTabRetDocOrg))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.foundation.soaint.documentmanager.persistence.entity.AdmTabRetDocOrg[ ideTabRetDocOrg=" + ideTabRetDocOrg + " ]";
    }

}
