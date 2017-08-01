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
import java.util.List;

/**
 * @author jrodriguez
 */
@Entity
@Table(name = "ADM_TRAD_DOC")
@NamedQueries({
        @NamedQuery(name = "AdmTradDoc.findAll",
                query = "SELECT NEW co.com.foundation.soaint.documentmanager.persistence.entity.AdmTradDoc(t.ideTradDoc, " +
                        "t.desTradDoc, t.nomTradDoc) FROM AdmTradDoc t")})

public class AdmTradDoc implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "IDE_TRAD_DOC")
    private BigInteger ideTradDoc;
    @Basic(optional = false)
    @Column(name = "DES_TRAD_DOC")
    private String desTradDoc;
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
    @Column(name = "NOM_TRAD_DOC")
    private String nomTradDoc;
    @OneToMany(mappedBy = "ideTraDocumental")
    private List<AdmTpgDoc> admTpgDocList;

    public AdmTradDoc() {
    }

    public AdmTradDoc(BigInteger ideTradDoc) {
        this.ideTradDoc = ideTradDoc;
    }

    //[ AdmTpgDoc.findAll ]
    public AdmTradDoc(BigInteger ideTradDoc, String nomTradDoc) {
        this.ideTradDoc = ideTradDoc;
        this.nomTradDoc = nomTradDoc;
    }

    //[ AdmTradDoc.findAll ]
    public AdmTradDoc(BigInteger ideTradDoc, String desTradDoc, String nomTradDoc) {
        this.ideTradDoc = ideTradDoc;
        this.desTradDoc = desTradDoc;
        this.nomTradDoc = nomTradDoc;
    }

    public BigInteger getIdeTradDoc() {
        return ideTradDoc;
    }

    public void setIdeTradDoc(BigInteger ideTradDoc) {
        this.ideTradDoc = ideTradDoc;
    }

    public String getDesTradDoc() {
        return desTradDoc;
    }

    public void setDesTradDoc(String desTradDoc) {
        this.desTradDoc = desTradDoc;
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

    public String getNomTradDoc() {
        return nomTradDoc;
    }

    public void setNomTradDoc(String nomTradDoc) {
        this.nomTradDoc = nomTradDoc;
    }

    public List<AdmTpgDoc> getAdmTpgDocList() {
        return admTpgDocList;
    }

    public void setAdmTpgDocList(List<AdmTpgDoc> admTpgDocList) {
        this.admTpgDocList = admTpgDocList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ideTradDoc != null ? ideTradDoc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AdmTradDoc)) {
            return false;
        }
        AdmTradDoc other = (AdmTradDoc) object;
        if ((this.ideTradDoc == null && other.ideTradDoc != null) || (this.ideTradDoc != null && !this.ideTradDoc.equals(other.ideTradDoc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AdmTradDoc[ ideTradDoc=" + ideTradDoc + " ]";
    }

}
