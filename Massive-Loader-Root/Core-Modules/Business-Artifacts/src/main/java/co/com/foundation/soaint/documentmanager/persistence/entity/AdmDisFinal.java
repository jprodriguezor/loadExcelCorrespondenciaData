/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.foundation.soaint.documentmanager.persistence.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "ADM_DIS_FINAL")
@NamedQueries({
    @NamedQuery(name = "AdmDisFinal.findAll",
            query = "SELECT NEW co.com.foundation.soaint.documentmanager.persistence.entity.AdmDisFinal(a.ideDisFinal, " +
                    "a.nomDisFinal, a.desDisFinal, a.staDisFinal, a.fecCambio, a.fecCreacion) FROM AdmDisFinal a"),
    
    @NamedQuery(name = "AdmDisFinal.countByNomMotDisposicion",
            query = "SELECT COUNT(a) FROM AdmDisFinal a WHERE a.nomDisFinal = :NOM_DIS_FINAL")
})

@javax.persistence.TableGenerator(name = "ADM_DISFINAL_SEQ", table = "TABLE_GENERATOR", pkColumnName = "SEQ_NAME",
        valueColumnName = "SEQ_VALUE", pkColumnValue = "ADM_DISFINAL_SEQ", allocationSize = 1)

public class AdmDisFinal implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "IDE_DIS_FINAL")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ADM_DISFINAL_SEQ")
    private BigInteger ideDisFinal;
    @Basic(optional = false)
    @Column(name = "NOM_DIS_FINAL")
    private String nomDisFinal;
    @Column(name = "DES_DIS_FINAL")
    private String desDisFinal;
    @Column(name = "STA_DIS_FINAL")
    private String staDisFinal;
    @Column(name = "IDE_USUARIO_CAMBIO")
    private Long ideUsuarioCambio;
    @Column(name = "FEC_CAMBIO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecCambio;
    @Column(name = "NIV_LECTURA")
    private Integer nivLectura;
    @Column(name = "NIV_ESCRITURA")
    private Integer nivEscritura;
    @Column(name = "IDE_UUID")
    private String ideUuid;
    @Column(name = "FEC_CREACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecCreacion;

    public AdmDisFinal() {
    }

    public AdmDisFinal(BigInteger ideDisFinal) {
        this.ideDisFinal = ideDisFinal;
    }


    public AdmDisFinal(BigInteger ideDisFinal,String nomDisFinal) {
        this.ideDisFinal=ideDisFinal;
        this.nomDisFinal = nomDisFinal;
    }

    public AdmDisFinal(BigInteger ideDisFinal, String nomDisFinal, String desDisFinal, String staDisFinal) {
        this.ideDisFinal = ideDisFinal;
        this.nomDisFinal = nomDisFinal;
        this.desDisFinal = desDisFinal;
        this.staDisFinal = staDisFinal;
    }

    public AdmDisFinal(BigInteger ideDisFinal, String nomDisFinal, String desDisFinal, String staDisFinal, Date fecCambio, Date fecCreacion) {
        this.ideDisFinal = ideDisFinal;
        this.nomDisFinal = nomDisFinal;
        this.desDisFinal = desDisFinal;
        this.staDisFinal = staDisFinal;
        this.fecCambio = fecCambio;
        this.fecCreacion = fecCreacion;
    }
    
    

    public BigInteger getIdeDisFinal() {
        return ideDisFinal;
    }

    public void setIdeDisFinal(BigInteger ideDisFinal) {
        this.ideDisFinal = ideDisFinal;
    }

    public String getNomDisFinal() {
        return nomDisFinal;
    }

    public void setNomDisFinal(String nomDisFinal) {
        this.nomDisFinal = nomDisFinal;
    }

    public String getDesDisFinal() {
        return desDisFinal;
    }

    public void setDesDisFinal(String desDisFinal) {
        this.desDisFinal = desDisFinal;
    }

    public String getStaDisFinal() {
        return staDisFinal;
    }

    public void setStaDisFinal(String staDisFinal) {
        this.staDisFinal = staDisFinal;
    }

    public Long getIdeUsuarioCambio() {
        return ideUsuarioCambio;
    }

    public void setIdeUsuarioCambio(Long ideUsuarioCambio) {
        this.ideUsuarioCambio = ideUsuarioCambio;
    }

    public Date getFecCambio() {
        return fecCambio;
    }

    public void setFecCambio(Date fecCambio) {
        this.fecCambio = fecCambio;
    }

    public Integer getNivLectura() {
        return nivLectura;
    }

    public void setNivLectura(Integer nivLectura) {
        this.nivLectura = nivLectura;
    }

    public Integer getNivEscritura() {
        return nivEscritura;
    }

    public void setNivEscritura(Integer nivEscritura) {
        this.nivEscritura = nivEscritura;
    }

    public String getIdeUuid() {
        return ideUuid;
    }

    public void setIdeUuid(String ideUuid) {
        this.ideUuid = ideUuid;
    }

    public Date getFecCreacion() {
        return fecCreacion;
    }

    public void setFecCreacion(Date fecCreacion) {
        this.fecCreacion = fecCreacion;
    }

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ideDisFinal != null ? ideDisFinal.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AdmDisFinal)) {
            return false;
        }
        AdmDisFinal other = (AdmDisFinal) object;
        if ((this.ideDisFinal == null && other.ideDisFinal != null) || (this.ideDisFinal != null && !this.ideDisFinal.equals(other.ideDisFinal))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AdmDisFinal{" + "ideDisFinal=" + ideDisFinal + ", nomDisFinal=" + nomDisFinal + ", desDisFinal=" + desDisFinal + ", staDisFinal=" + staDisFinal + ", ideUsuarioCambio=" + ideUsuarioCambio + ", fecCambio=" + fecCambio + ", nivLectura=" + nivLectura + ", nivEscritura=" + nivEscritura + ", ideUuid=" + ideUuid + ", fecCreacion=" + fecCreacion + '}';
    }
    

    
}
