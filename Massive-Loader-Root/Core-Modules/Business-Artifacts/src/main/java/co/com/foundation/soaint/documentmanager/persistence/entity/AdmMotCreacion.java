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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.TableGenerator;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
/**
 *
 * @author jrodriguez
 */
@Entity
@Table(name = "ADM_MOT_CREACION")
@NamedQueries({
        @NamedQuery(name = "AdmMotCreacion.findAll", query = "SELECT NEW co.com.foundation.soaint.documentmanager.persistence.entity.AdmMotCreacion" +
                "( m.ideMotCreacion, m.nomMotCreacion) FROM AdmMotCreacion m"),

        @NamedQuery(name = "AdmMotCreacion.countByNomMotCreacion",
                query = "SELECT COUNT(a) FROM AdmMotCreacion a WHERE a.nomMotCreacion = :NOM_MOTIVOC")
})

@TableGenerator(name = "ADM_MOTIVODOC_GENERATOR", table = "TABLE_GENERATOR", pkColumnName = "SEQ_NAME",
        valueColumnName = "SEQ_VALUE", pkColumnValue = "ADM_MOTIVODOC_SEQ", allocationSize = 1)
public class AdmMotCreacion implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "IDE_MOT_CREACION")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ADM_MOTIVODOC_GENERATOR")
    private BigInteger ideMotCreacion;

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
    @Column(name = "NOM_MOT_CREACION")
    private String nomMotCreacion;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ideMotCreacion")
    private List<AdmSerie> admSerieList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ideMotCreacion")
    private List<AdmSubserie> admSubserieList;

    public AdmMotCreacion() {
    }

    public AdmMotCreacion(BigInteger ideMotCreacion) {
        this.ideMotCreacion = ideMotCreacion;
    }

    public AdmMotCreacion(BigInteger ideMotCreacion, String nomMotCreacion) {
        this.ideMotCreacion = ideMotCreacion;
        this.nomMotCreacion = nomMotCreacion;
    }

    public AdmMotCreacion(BigInteger ideMotCreacion, Date fecCambio, Date fecCreacion, BigInteger ideUsuarioCambio,
                          String ideUuid, Integer nivEscritura, Integer nivLectura, String nomMotCreacion) {
        this.ideMotCreacion = ideMotCreacion;
        this.fecCambio = fecCambio;
        this.fecCreacion = fecCreacion;
        this.ideUsuarioCambio = ideUsuarioCambio;
        this.ideUuid = ideUuid;
        this.nivEscritura = nivEscritura;
        this.nivLectura = nivLectura;
        this.nomMotCreacion = nomMotCreacion;
    }

    public BigInteger getIdeMotCreacion() {
        return ideMotCreacion;
    }

    public void setIdeMotCreacion(BigInteger ideMotCreacion) {
        this.ideMotCreacion = ideMotCreacion;
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

    public String getNomMotCreacion() {
        return nomMotCreacion;
    }

    public void setNomMotCreacion(String nomMotCreacion) {
        this.nomMotCreacion = nomMotCreacion;
    }

    public List<AdmSerie> getAdmSerieList() {
        return admSerieList;
    }

    public void setAdmSerieList(List<AdmSerie> admSerieList) {
        this.admSerieList = admSerieList;
    }

    public List<AdmSubserie> getAdmSubserieList() {
        return admSubserieList;
    }

    public void setAdmSubserieList(List<AdmSubserie> admSubserieList) {
        this.admSubserieList = admSubserieList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ideMotCreacion != null ? ideMotCreacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AdmMotCreacion)) {
            return false;
        }
        AdmMotCreacion other = (AdmMotCreacion) object;
        if ((this.ideMotCreacion == null && other.ideMotCreacion != null) || (this.ideMotCreacion != null && !this.ideMotCreacion.equals(other.ideMotCreacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AdmMotCreacion[ ideMotCreacion=" + ideMotCreacion + " ]";
    }

}
