/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.foundation.soaint.documentmanager.persistence.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author jrodriguez
 */
@Entity
@Table(name = "ADM_SER_SUBSER_TPG")
@NamedQueries({
        @NamedQuery(name = "AdmSerSubserTpg.findAll",
                query = "SELECT a FROM AdmSerSubserTpg a"),
        @NamedQuery(name = "AdmSerSubserTpg.findTipBySerAndSubSer",
                query = "SELECT NEW co.com.foundation.soaint.documentmanager.persistence.entity.AdmTpgDoc(t.ideTpgDoc,"
                        + "t.codTpgDoc,t.nomTpgDoc) FROM AdmSerSubserTpg a INNER JOIN a.ideTpgDoc t "
                        + "WHERE a.ideSerie.ideSerie = :IDSERIE AND a.ideSubserie.ideSubserie = :IDSUBSERIE"),
        @NamedQuery(name = "AdmSerSubserTpg.findTipBySer",
                query = "SELECT NEW co.com.foundation.soaint.documentmanager.persistence.entity.AdmTpgDoc(t.ideTpgDoc,"
                        + "t.codTpgDoc,t.nomTpgDoc) FROM AdmSerSubserTpg a INNER JOIN a.ideTpgDoc t "
                        + "WHERE a.ideSerie.ideSerie = :IDSERIE AND  a.ideSubserie.ideSubserie IS NULL"),
        @NamedQuery(name = "AdmSerSubserTpg.deleteAsocById",
                query = "DELETE FROM AdmSerSubserTpg a "
                        + "WHERE a.ideRelSst = :IDE_REL_SST"),

        @NamedQuery(name = "AdmSerSubserTpg.findAsocBySerAndSubSer",
                query = "SELECT NEW co.com.foundation.soaint.documentmanager.persistence.entity.AdmSerSubserTpg(a.fecCambio, "
                        + "a.fecCreacion, a.ideUsuarioCambio, a.ideUuid, a.nivEscritura, a.nivLectura, a.ideRelSst, s.ideSerie, s.nomSerie, sub.ideSubserie, sub.nomSubserie, "
                        + "t.ideTpgDoc, t.nomTpgDoc) "
                        + "FROM AdmSerSubserTpg a INNER JOIN a.ideTpgDoc t INNER JOIN a.ideSerie s INNER JOIN a.ideSubserie sub "
                        + "WHERE a.ideSerie.ideSerie = :IDSERIE AND a.ideSubserie.ideSubserie = :IDSUBSERIE"),

        @NamedQuery(name = "AdmSerSubserTpg.findAsocBySerAndSubServ",
                query = "SELECT NEW co.com.foundation.soaint.documentmanager.persistence.entity.AdmSerSubserTpg(a.fecCambio, "
                        + "a.fecCreacion, a.ideUsuarioCambio, a.ideUuid, a.nivEscritura, a.nivLectura, a.ideRelSst, s.ideSerie, s.nomSerie, "
                        + "t.ideTpgDoc, t.nomTpgDoc) "
                        + "FROM AdmSerSubserTpg a INNER JOIN a.ideTpgDoc t INNER JOIN a.ideSerie s "
                        + "WHERE a.ideSerie.ideSerie = :IDSERIE"),

        @NamedQuery(name = "AdmSerSubserTpg.findAllAsocGroup",
                query ="select NEW co.com.foundation.soaint.documentmanager.persistence.entity.AdmSerSubserTpg("
                        + " ser.ideSerie, ser.nomSerie, sub.ideSubserie, sub.nomSubserie, t.ideTpgDoc, t.nomTpgDoc) "
                        + " FROM AdmSerSubserTpg a"
                        + " LEFT JOIN a.ideSerie ser"
                        + " LEFT JOIN a.ideSubserie sub"
                        + " INNER JOIN a.ideTpgDoc t"),

        @NamedQuery(name = "AdmSerSubserTpg.countByCodAsocSerieAndSubserie",
                query = "SELECT COUNT(s)FROM AdmSerSubserTpg s WHERE s.ideSerie.ideSerie = :COD_SERIE AND s.ideSubserie.ideSubserie = :COD_SUBSERIE " +
                        "AND s.ideTpgDoc.ideTpgDoc = :COD_TPG" ),

        @NamedQuery(name = "AdmSerSubserTpg.countByCodAsocSerie",
                query = "SELECT COUNT(s)FROM AdmSerSubserTpg s " +
                        " WHERE s.ideSerie.ideSerie = :COD_SERIE " +
                        " AND s.ideTpgDoc.ideTpgDoc = :COD_TPG" ),

        @NamedQuery(name = "AdmSerSubserTpg.countBySerie",
                query = "SELECT COUNT(a)FROM AdmSerSubserTpg a INNER JOIN a.ideTpgDoc t INNER JOIN a.ideSerie s WHERE s.ideSerie = :ID_SERIE" ),

        @NamedQuery(name = "AdmSerSubserTpg.countBySerieAndSubserie",
                query = "SELECT COUNT(a)FROM AdmSerSubserTpg a INNER JOIN a.ideTpgDoc t INNER JOIN a.ideSerie s INNER JOIN a.ideSubserie sub " +
                        "WHERE s.ideSerie = :ID_SERIE AND sub.ideSubserie = :ID_SUBSERIE" ),
        @NamedQuery(name = "AdmSerSubserTpg.deleteSerieById",
                query = "DELETE FROM AdmSerSubserTpg s WHERE s.ideSerie.ideSerie = :ID_SERIE"),
        @NamedQuery(name = "AdmSerSubserTpg.deleteSubserieById",
                query = "DELETE FROM AdmSerSubserTpg s WHERE s.ideSubserie.ideSubserie = :ID_SUBSERIE"),
        @NamedQuery(name = "AdmSerSubserTpg.deleteByIdTpgDoc",
                query = "DELETE FROM AdmSerSubserTpg t WHERE t.ideTpgDoc.ideTpgDoc = :ID_TPGDOC "),

})

@javax.persistence.TableGenerator(name = "ADM_SER_SUBSER_TPG_GENERATOR", table = "TABLE_GENERATOR", pkColumnName = "SEQ_NAME",
        valueColumnName = "SEQ_VALUE", pkColumnValue = "ADM_SER_SUBSER_TPG_SEQ", allocationSize = 1)
public class AdmSerSubserTpg implements Serializable {

    private static final long serialVersionUID = 1L;
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
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "IDE_REL_SST")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ADM_SER_SUBSER_TPG_GENERATOR")
    private BigDecimal ideRelSst;
    @JoinColumn(name = "IDE_SERIE", referencedColumnName = "IDE_SERIE")
    @ManyToOne(optional = false)
    private AdmSerie ideSerie;
    @JoinColumn(name = "IDE_SUBSERIE", referencedColumnName = "IDE_SUBSERIE")
    @ManyToOne
    private AdmSubserie ideSubserie;
    @JoinColumn(name = "IDE_TPG_DOC", referencedColumnName = "IDE_TPG_DOC")
    @ManyToOne(optional = false)
    private AdmTpgDoc ideTpgDoc;

    public AdmSerSubserTpg() {
    }

    public AdmSerSubserTpg(Date fecCambio, Date fecCreacion, BigInteger ideUsuarioCambio, String ideUuid, Integer nivEscritura, Integer nivLectura, BigDecimal ideRelSst, AdmSerie ideSerie, AdmSubserie ideSubserie, AdmTpgDoc ideTpgDoc) {
        this.fecCambio = fecCambio;
        this.fecCreacion = fecCreacion;
        this.ideUsuarioCambio = ideUsuarioCambio;
        this.ideUuid = ideUuid;
        this.nivEscritura = nivEscritura;
        this.nivLectura = nivLectura;
        this.ideRelSst = ideRelSst;
        this.ideSerie = ideSerie;
        this.ideSubserie = ideSubserie;
        this.ideTpgDoc = ideTpgDoc;
    }

    public AdmSerSubserTpg(BigInteger ideSerie, String nomSerie, BigInteger ideSubserie, String nomSubserie, BigInteger ideTpgDoc, String nomTpgDoc ) {
        this.ideSerie = new AdmSerie(ideSerie, nomSerie);
        this.ideSubserie = new AdmSubserie(ideSubserie, nomSubserie);
        this.ideTpgDoc = new AdmTpgDoc(ideTpgDoc, nomTpgDoc);
    }


    public AdmSerSubserTpg(Date fecCambio, Date fecCreacion, BigInteger ideUsuarioCambio,
                           String ideUuid, Integer nivEscritura, Integer nivLectura, BigDecimal ideRelSst,
                           BigInteger ideSerie, String nomSerie, BigInteger ideSubserie, String nomSubserie,
                           BigInteger ideTpgDoc, String nomTpgDoc) {
        this.fecCambio = fecCambio;
        this.fecCreacion = fecCreacion;
        this.ideUsuarioCambio = ideUsuarioCambio;
        this.ideUuid = ideUuid;
        this.nivEscritura = nivEscritura;
        this.nivLectura = nivLectura;
        this.ideRelSst = ideRelSst;
        this.ideSerie = new AdmSerie(ideSerie, nomSerie);
        this.ideSubserie = new AdmSubserie(ideSubserie, nomSubserie);
        this.ideTpgDoc = new AdmTpgDoc(ideTpgDoc, nomTpgDoc);
    }

    public AdmSerSubserTpg(Date fecCambio, Date fecCreacion, BigInteger ideUsuarioCambio,
                           String ideUuid, Integer nivEscritura, Integer nivLectura, BigDecimal ideRelSst,
                           BigInteger ideSerie, String nomSerie,
                           BigInteger ideTpgDoc, String nomTpgDoc) {
        this.fecCambio = fecCambio;
        this.fecCreacion = fecCreacion;
        this.ideUsuarioCambio = ideUsuarioCambio;
        this.ideUuid = ideUuid;
        this.nivEscritura = nivEscritura;
        this.nivLectura = nivLectura;
        this.ideRelSst = ideRelSst;
        this.ideSerie = new AdmSerie(ideSerie, nomSerie);
        this.ideTpgDoc = new AdmTpgDoc(ideTpgDoc, nomTpgDoc);
    }

    public AdmSerSubserTpg(BigDecimal ideRelSst) {
        this.ideRelSst = ideRelSst;
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

    public BigDecimal getIdeRelSst() {
        return ideRelSst;
    }

    public void setIdeRelSst(BigDecimal ideRelSst) {
        this.ideRelSst = ideRelSst;
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

    public AdmTpgDoc getIdeTpgDoc() {
        return ideTpgDoc;
    }

    public void setIdeTpgDoc(AdmTpgDoc ideTpgDoc) {
        this.ideTpgDoc = ideTpgDoc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ideRelSst != null ? ideRelSst.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AdmSerSubserTpg)) {
            return false;
        }
        AdmSerSubserTpg other = (AdmSerSubserTpg) object;
        if ((this.ideRelSst == null && other.ideRelSst != null) || (this.ideRelSst != null && !this.ideRelSst.equals(other.ideRelSst))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AdmSerSubserTpg[ ideRelSst=" + ideRelSst + " ]";
    }

}
