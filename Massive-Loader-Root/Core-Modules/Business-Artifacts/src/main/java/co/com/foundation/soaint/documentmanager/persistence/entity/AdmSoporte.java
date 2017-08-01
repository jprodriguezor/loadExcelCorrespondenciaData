/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.foundation.soaint.documentmanager.persistence.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author jrodriguez
 */
@Entity
@Table(name = "ADM_SOPORTE")
@NamedQueries({
    @NamedQuery(name = "AdmSoporte.findAll",
            query = "SELECT NEW co.com.foundation.soaint.documentmanager.persistence.entity.AdmSoporte(s.ideSoporte, " +
                    "s.desSoporte, s.nomSoporte) FROM AdmSoporte s"),
    @NamedQuery(name = "AdmSoporte.countByIdSoporte",
            query = "SELECT COUNT(s) FROM AdmSoporte s WHERE s.ideSoporte = :IDE_SOPORTE")
})
public class AdmSoporte implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "IDE_SOPORTE")
    private BigInteger ideSoporte;
    @Basic(optional = false)
    @Column(name = "DES_SOPORTE")
    private String desSoporte;
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
    @Column(name = "NOM_SOPORTE")
    private String nomSoporte;
    @OneToMany(mappedBy = "ideSoporte")
    private List<AdmTpgDoc> admTpgDocList;

    public AdmSoporte() {
    }

    public AdmSoporte(BigInteger ideSoporte) {
        this.ideSoporte = ideSoporte;
    }

    //[ AdmTpgDoc.findAll ]
    public AdmSoporte(BigInteger ideSoporte, String nomSoporte) {
        this.ideSoporte = ideSoporte;
        this.nomSoporte = nomSoporte;
    }

    //[ AdmSoporte.findAll ]
    public AdmSoporte(BigInteger ideSoporte, String desSoporte, String nomSoporte) {
        this.ideSoporte = ideSoporte;
        this.desSoporte = desSoporte;
        this.nomSoporte = nomSoporte;
    }


    public BigInteger getIdeSoporte() {
        return ideSoporte;
    }

    public void setIdeSoporte(BigInteger ideSoporte) {
        this.ideSoporte = ideSoporte;
    }

    public String getDesSoporte() {
        return desSoporte;
    }

    public void setDesSoporte(String desSoporte) {
        this.desSoporte = desSoporte;
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

    public String getNomSoporte() {
        return nomSoporte;
    }

    public void setNomSoporte(String nomSoporte) {
        this.nomSoporte = nomSoporte;
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
        hash += (ideSoporte != null ? ideSoporte.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AdmSoporte)) {
            return false;
        }
        AdmSoporte other = (AdmSoporte) object;
        if ((this.ideSoporte == null && other.ideSoporte != null) || (this.ideSoporte != null && !this.ideSoporte.equals(other.ideSoporte))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AdmSoporte{" +
                "ideSoporte=" + ideSoporte +
                ", desSoporte='" + desSoporte + '\'' +
                ", fecCambio=" + fecCambio +
                ", fecCreacion=" + fecCreacion +
                ", ideUsuarioCambio=" + ideUsuarioCambio +
                ", ideUuid='" + ideUuid + '\'' +
                ", nivEscritura=" + nivEscritura +
                ", nivLectura=" + nivLectura +
                ", nomSoporte='" + nomSoporte + '\'' +
                ", admTpgDocList=" + admTpgDocList +
                '}';
    }
}
