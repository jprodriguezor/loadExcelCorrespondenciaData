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
 *
 * @author ADMIN
 */
@Entity
@Table(name = "ADM_REL_EQ_DESTINO")
@NamedQueries({
        @NamedQuery(name = "AdmRelEqDestino.findAll", query = "SELECT a FROM AdmRelEqDestino a"),

        @NamedQuery(name = "AdmRelEqDestino.consultarUnidadAdministrativaExistByDestino",
                query = "SELECT NEW co.com.foundation.soaint.documentmanager.domain.RelEquiItemVO (d.ideUniAmt, tv.nomOrg ) " +
                        "FROM AdmRelEqDestino d " +
                        "INNER JOIN TvsOrganigramaAdministrativo tv on d.ideUniAmt = tv.codOrg  "+
                        "group by d.ideUniAmt, tv.nomOrg"),

        @NamedQuery(name = "AdmRelEqDestino.deleteRelEquiOrigenByIdOrigen",
                query = "DELETE FROM AdmRelEqDestino rd WHERE rd.admRelEqOrigen.ideRelOrigen = :ID_REL_ORIGEN"),
})

@javax.persistence.TableGenerator(name = "ADM_REL_EQ_DES_SEQ", table = "TABLE_GENERATOR", pkColumnName = "SEQ_NAME",
        valueColumnName = "SEQ_VALUE", pkColumnValue = "ADM_REL_EQ_DES_SEQ", allocationSize = 1)

public class AdmRelEqDestino implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "IDE_REL_DESTINO")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ADM_REL_EQ_DES_SEQ")
    private BigInteger ideRelDestino;
    @Basic(optional = false)
    @Column(name = "IDE_UNI_AMT")
    private String ideUniAmt;
    @Basic(optional = false)
    @Column(name = "IDE_OFC_PROD")
    private String ideOfcProd;
    @Column(name = "FEC_CREACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecCreacion;
    @JoinColumn(name = "IDE_REL_ORIGEN", referencedColumnName = "IDE_REL_ORIGEN")
    @ManyToOne
    private AdmRelEqOrigen admRelEqOrigen;
    @JoinColumn(name = "IDE_SERIE", referencedColumnName = "IDE_SERIE")
    @ManyToOne
    private AdmSerie admSerie;
    @JoinColumn(name = "IDE_SUBSERIE", referencedColumnName = "IDE_SUBSERIE")
    @ManyToOne
    private AdmSubserie admSubserie;

    @Transient
    String nombreUAdminD;
    @Transient
    String nombreOProdD;

    public AdmRelEqDestino() {
    }

    public AdmRelEqDestino(BigInteger ideRelDestino) {
        this.ideRelDestino = ideRelDestino;
    }

    public AdmRelEqDestino(BigInteger ideRelDestino, String ideUniAmt, String nombreUAdminD ,String ideOfcProd, String nombreOProdD,
                           BigInteger ideSerieDe, String codSerieDe, String nomSerieDe,
                           BigInteger ideSubserieDe , String codSubserieDe, String nomSubserieDe, Date fecCreacion) {
        this.ideRelDestino = ideRelDestino;
        this.ideUniAmt = ideUniAmt;
        this.nombreUAdminD = nombreUAdminD;
        this.ideOfcProd = ideOfcProd;
        this.nombreOProdD = nombreOProdD;
        this.admSerie= new AdmSerie(ideSerieDe,codSerieDe, nomSerieDe);
        this.admSubserie = new AdmSubserie(ideSubserieDe, codSubserieDe, nomSubserieDe);
        this.fecCreacion = fecCreacion;
    }

    public AdmRelEqDestino(BigInteger ideRelDestino, String ideUniAmt, String ideOfcProd, Date fecCreacion, BigInteger admRelEqOrigen, BigInteger ideSerie, BigInteger ideSubserie) {
        this.ideRelDestino = ideRelDestino;
        this.ideUniAmt = ideUniAmt;
        this.ideOfcProd = ideOfcProd;
        this.fecCreacion = fecCreacion;
        this.admRelEqOrigen = new AdmRelEqOrigen(admRelEqOrigen);
        this.admSerie =  ideSerie == null ? null : new AdmSerie(ideSerie);
        this.admSubserie = ideSubserie == null ? null :  new AdmSubserie(ideSubserie);
    }

    public BigInteger getIdeRelDestino() {
        return ideRelDestino;
    }

    public void setIdeRelDestino(BigInteger ideRelDestino) {
        this.ideRelDestino = ideRelDestino;
    }

    public String getIdeUniAmt() {
        return ideUniAmt;
    }

    public void setIdeUniAmt(String ideUniAmt) {
        this.ideUniAmt = ideUniAmt;
    }

    public String getIdeOfcProd() {
        return ideOfcProd;
    }

    public void setIdeOfcProd(String ideOfcProd) {
        this.ideOfcProd = ideOfcProd;
    }

    public Date getFecCreacion() {
        return fecCreacion;
    }

    public void setFecCreacion(Date fecCreacion) {
        this.fecCreacion = fecCreacion;
    }

    public AdmRelEqOrigen getAdmRelEqOrigen() {
        return admRelEqOrigen;
    }

    public void setAdmRelEqOrigen(AdmRelEqOrigen admRelEqOrigen) {
        this.admRelEqOrigen = admRelEqOrigen;
    }

    public AdmSerie getAdmSerie() {
        return admSerie;
    }

    public void setAdmSerie(AdmSerie admSerie) {
        this.admSerie = admSerie;
    }

    public AdmSubserie getAdmSubserie() {
        return admSubserie;
    }

    public void setAdmSubserie(AdmSubserie admSubserie) {
        this.admSubserie = admSubserie;
    }

    public String getNombreUAdminD() {
        return nombreUAdminD;
    }

    public void setNombreUAdminD(String nombreUAdminD) {
        this.nombreUAdminD = nombreUAdminD;
    }

    public String getNombreOProdD() {
        return nombreOProdD;
    }

    public void setNombreOProdD(String nombreOProdD) {
        this.nombreOProdD = nombreOProdD;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ideRelDestino != null ? ideRelDestino.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AdmRelEqDestino)) {
            return false;
        }
        AdmRelEqDestino other = (AdmRelEqDestino) object;
        if ((this.ideRelDestino == null && other.ideRelDestino != null) || (this.ideRelDestino != null && !this.ideRelDestino.equals(other.ideRelDestino))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AdmRelEqDestino{" +
                "ideRelDestino=" + ideRelDestino +
                ", ideUniAmt='" + ideUniAmt + '\'' +
                ", ideOfcProd='" + ideOfcProd + '\'' +
                ", fecCreacion=" + fecCreacion +
                ", admRelEqOrigen=" + admRelEqOrigen +
                ", admSerie=" + admSerie +
                ", admSubserie=" + admSubserie +
                '}';
    }
}
