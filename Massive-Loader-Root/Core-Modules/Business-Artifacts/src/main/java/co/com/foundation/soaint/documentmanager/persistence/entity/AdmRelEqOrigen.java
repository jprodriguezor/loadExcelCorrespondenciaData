/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.foundation.soaint.documentmanager.persistence.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ADMIN
 */
@Entity
@Table(name = "ADM_REL_EQ_ORIGEN")
@NamedQueries({
        @NamedQuery(name = "AdmRelEqOrigen.findAllRelEqui",
                query = "SELECT NEW co.com.foundation.soaint.documentmanager.persistence.entity.AdmRelEqOrigen " +
                        "(ro.ideRelOrigen, ro.ideUniAmt, uao.nomOrg, ro.ideOfcProd, opo.nomOrg, so.ideSerie, so.codSerie, so.nomSerie, sso.ideSubserie, sso.codSubserie, sso.nomSubserie, ro.fecCreacion, " +
                        "rd.ideRelDestino, rd.ideUniAmt, uad.nomOrg, rd.ideOfcProd, opd.nomOrg, sd.ideSerie, sd.codSerie, sd.nomSerie, ssd.ideSubserie, ssd.codSubserie, ssd.nomSubserie, rd.fecCreacion" +
                        " ) FROM AdmRelEqOrigen ro " +
                        "INNER JOIN ro.admRelEqDestinoList rd " +
                        "LEFT OUTER JOIN ro.admSerie so " +
                        "LEFT OUTER JOIN rd.admSerie sd " +
                        "INNER JOIN TvsOrganigramaAdministrativo uao on ro.ideUniAmt = uao.codOrg " +
                        "INNER JOIN TvsOrganigramaAdministrativo opo on ro.ideOfcProd = opo.codOrg " +
                        "LEFT OUTER JOIN ro.admSubserie sso "+
                        "LEFT OUTER JOIN rd.admSubserie ssd "+
                        "INNER JOIN TvsOrganigramaAdministrativo uad on rd.ideUniAmt = uad.codOrg " +
                        "INNER JOIN TvsOrganigramaAdministrativo opd on rd.ideOfcProd = opd.codOrg "+
                        "GROUP BY ro.ideRelOrigen, ro.ideUniAmt, uao.nomOrg, ro.ideOfcProd, opo.nomOrg, so.ideSerie, so.codSerie, " +
                        "so.nomSerie, sso.ideSubserie, sso.codSubserie, sso.nomSubserie, ro.fecCreacion,rd.ideRelDestino, rd.ideUniAmt, " +
                        "uad.nomOrg, rd.ideOfcProd, opd.nomOrg, sd.ideSerie, sd.codSerie, sd.nomSerie, ssd.ideSubserie, ssd.codSubserie, " +
                        "ssd.nomSubserie, rd.fecCreacion"),

        @NamedQuery(name = "AdmRelEqOrigen.consultarUnidadAdministrativaExistByOrigen",
                query = "SELECT NEW co.com.foundation.soaint.documentmanager.domain.RelEquiItemVO (o.ideUniAmt, tv.nomOrg , tv.valVersion) " +
                        "FROM AdmRelEqOrigen o " +
                        "INNER JOIN AdmCcd c on c.numVersionOrg = :NUM_VERSION_ORG  "+
                        "INNER JOIN TvsOrganigramaAdministrativo tv on o.ideUniAmt = tv.codOrg  "+
                        "WHERE tv.valVersion = c.valVersionOrg group by o.ideUniAmt, tv.nomOrg, tv.valVersion"),

        @NamedQuery(name = "AdmRelEqOrigen.consultarOficinaProdExistInOrigenByCodUniAmt",
                query = "SELECT NEW co.com.foundation.soaint.documentmanager.domain.RelEquiItemVO (c.ideOfcProd, ua.nomOrg ) " +
                        "FROM AdmRelEqOrigen c " +
                        "INNER JOIN TvsOrganigramaAdministrativo ua on c.ideOfcProd = ua.codOrg  "+
                        "WHERE c.ideUniAmt =:COD_UNI_ADMIN  " +
                        " group by c.ideOfcProd, ua.nomOrg"),

        @NamedQuery(name = "AdmRelEqOrigen.serieExistInOrigen",
                query = "SELECT NEW co.com.foundation.soaint.documentmanager.domain.RelEquiItemVO ( s.codSerie , s.nomSerie, s.ideSerie ) " +
                        "FROM AdmRelEqOrigen o " +
                        "LEFT OUTER JOIN o.admSerie s "+
                        "WHERE o.ideUniAmt = :COD_UNI_ADMIN  and  o.ideOfcProd = :COD_OFI_PROD " +
                        "group by s.codSerie, s.nomSerie, s.ideSerie"),

        @NamedQuery(name = "AdmRelEqOrigen.subSerieExistInOrigenBySerie",
                query = "SELECT NEW co.com.foundation.soaint.documentmanager.domain.RelEquiItemVO (ss.codSubserie, ss.nomSubserie, ss.ideSubserie ) " +
                        "FROM AdmRelEqOrigen c " +
                        "LEFT OUTER JOIN c.admSubserie ss "+
                        "WHERE c.admSerie.codSerie = :SERIE and c.ideUniAmt = :COD_UNI_ADMIN  and  c.ideOfcProd = :COD_OFI_PROD  " +
                        " group by ss.codSubserie, ss.nomSubserie, ss.ideSubserie "),

        @NamedQuery(name = "AdmRelEqOrigen.deleteRelEquiOrigenById",
                query = "DELETE FROM AdmRelEqOrigen ro WHERE ro.ideRelOrigen = :ID_REL_ORIGEN"),

})
@javax.persistence.TableGenerator(name = "ADM_REL_EQ_OR_SEQ", table = "TABLE_GENERATOR", pkColumnName = "SEQ_NAME",
        valueColumnName = "SEQ_VALUE", pkColumnValue = "ADM_REL_EQ_OR_SEQ", allocationSize = 1)

public class AdmRelEqOrigen implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "IDE_REL_ORIGEN")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ADM_REL_EQ_OR_SEQ")
    private BigInteger ideRelOrigen;
    @Basic(optional = false)
    @Column(name = "IDE_UNI_AMT")
    private String ideUniAmt;
    @Basic(optional = false)
    @Column(name = "IDE_OFC_PROD")
    private String ideOfcProd;
    @Column(name = "NUM_VERSION_ORG")
    private BigInteger numVersionOrg;
    @Column(name = "FEC_CREACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecCreacion;
    @JoinColumn(name = "IDE_SERIE", referencedColumnName = "IDE_SERIE")
    @ManyToOne
    private AdmSerie admSerie;
    @JoinColumn(name = "IDE_SUBSERIE", referencedColumnName = "IDE_SUBSERIE")
    @ManyToOne
    private AdmSubserie admSubserie;
    //@OneToMany(cascade = CascadeType.ALL, mappedBy = "admRelEqOrigen")
    @OneToMany(mappedBy = "admRelEqOrigen")
    private List<AdmRelEqDestino> admRelEqDestinoList;


    @Transient
    String nombreUAdminO;
    @Transient
    String nombreOProd;


    public AdmRelEqOrigen() {
    }

    public AdmRelEqOrigen(BigInteger ideRelOrigen) {
        this.ideRelOrigen = ideRelOrigen;
    }

    public AdmRelEqOrigen(BigInteger ideRelOrigen, String ideUniAmtOr, String nombreUAdminO, String ideOfcProdOr, String nombreOProd ,BigInteger ideSerieOr, String codSerieOr, String nomSerieOr, BigInteger ideSubserieOr, String codSubserieOr, String nomSubserieOr, Date fecCreacionOr,
                          BigInteger ideRelDestino, String ideUniAmtDe, String nombreUAdminD ,String ideOfcProdDe, String nombreOProdD, BigInteger ideSerieDe, String codSerieDe, String nomSerieDe, BigInteger ideSubserieDe, String codSubserieDe, String nomSubserieDe, Date fecCreacionDe) {
        this.ideRelOrigen = ideRelOrigen;
        this.ideUniAmt = ideUniAmtOr;
        this.nombreUAdminO = nombreUAdminO;
        this.ideOfcProd = ideOfcProdOr;
        this.nombreOProd = nombreOProd;
        this.admSerie = new AdmSerie(ideSerieOr, codSerieOr, nomSerieOr);
        this.admSubserie = new AdmSubserie(ideSubserieOr, codSubserieOr, nomSubserieOr);
        this.fecCreacion = fecCreacionOr;
        AdmRelEqDestino  destino = new AdmRelEqDestino(ideRelDestino, ideUniAmtDe, nombreUAdminD, ideOfcProdDe, nombreOProdD, ideSerieDe, codSerieDe, nomSerieDe, ideSubserieDe, codSubserieDe, nomSubserieDe, fecCreacionDe);
        admRelEqDestinoList = new ArrayList<>();
        this.admRelEqDestinoList.add(destino);


    }

    public AdmRelEqOrigen(String ideUniAmt, String ideOfcProd, BigInteger numVersionOrg, Date fecCreacion, AdmSerie admSerie, AdmSubserie admSubserie, List<AdmRelEqDestino> admRelEqDestinoList, String nombreUAdminO, String nombreOProd) {
        this.ideUniAmt = ideUniAmt;
        this.ideOfcProd = ideOfcProd;
        this.numVersionOrg = numVersionOrg;
        this.fecCreacion = fecCreacion;
        this.admSerie = admSerie;
        this.admSubserie = admSubserie;
        this.admRelEqDestinoList = admRelEqDestinoList;
        this.nombreUAdminO = nombreUAdminO;
        this.nombreOProd = nombreOProd;
    }

    public AdmRelEqOrigen(BigInteger ideRelOrigen, String ideUniAmt, String ideOfcProd, Date fecCreacion, BigInteger ideSerie, BigInteger ideSubserie) {
        this.ideRelOrigen = ideRelOrigen;
        this.ideUniAmt = ideUniAmt;
        this.ideOfcProd = ideOfcProd;
        this.fecCreacion = fecCreacion;
        this.admSerie = ideSerie == null ? null : new  AdmSerie(ideSerie);
        this.admSubserie = ideSubserie == null ? null : new AdmSubserie(ideSubserie);

    }

    public AdmRelEqOrigen(BigInteger ideRelOrigen, String ideUniAmt, String ideOfcProd, Date fecCreacion, BigInteger ideSerie, BigInteger ideSubserie, BigInteger numVersionOrg) {
        this.ideRelOrigen = ideRelOrigen;
        this.ideUniAmt = ideUniAmt;
        this.ideOfcProd = ideOfcProd;
        this.fecCreacion = fecCreacion;
        this.admSerie = ideSerie == null ? null : new  AdmSerie(ideSerie);
        this.admSubserie = ideSubserie == null ? null : new AdmSubserie(ideSubserie);
        this.numVersionOrg = numVersionOrg;

    }

    public AdmRelEqOrigen(String codSerieOr, String nomSerieOr){
        this.admSerie = new AdmSerie(codSerieOr, nomSerieOr);
    }





    public BigInteger getIdeRelOrigen() {
        return ideRelOrigen;
    }

    public void setIdeRelOrigen(BigInteger ideRelOrigen) {
        this.ideRelOrigen = ideRelOrigen;
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

    public List<AdmRelEqDestino> getAdmRelEqDestinoList() {
        return admRelEqDestinoList;
    }

    public void setAdmRelEqDestinoList(List<AdmRelEqDestino> admRelEqDestinoList) {
        this.admRelEqDestinoList = admRelEqDestinoList;
    }

    public void setNombreUAdminO(String nombreUAdminO) {
        this.nombreUAdminO = nombreUAdminO;
    }

    public String getNombreUAdminO() {
        return nombreUAdminO;
    }

    public String getNombreOProd() {
        return nombreOProd;
    }

    public void setNombreOProd(String nombreOProd) {
        this.nombreOProd = nombreOProd;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public BigInteger getNumVersionOrg() {
        return numVersionOrg;
    }

    public void setNumVersionOrg(BigInteger numVersionOrg) {
        this.numVersionOrg = numVersionOrg;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ideRelOrigen != null ? ideRelOrigen.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AdmRelEqOrigen)) {
            return false;
        }
        AdmRelEqOrigen other = (AdmRelEqOrigen) object;
        if ((this.ideRelOrigen == null && other.ideRelOrigen != null) || (this.ideRelOrigen != null && !this.ideRelOrigen.equals(other.ideRelOrigen))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AdmRelEqOrigen{" +
                "ideRelOrigen=" + ideRelOrigen +
                ", ideUniAmt='" + ideUniAmt + '\'' +
                ", ideOfcProd='" + ideOfcProd + '\'' +
                ", numVersionOrg=" + numVersionOrg +
                ", fecCreacion=" + fecCreacion +
                ", admSerie=" + admSerie +
                ", admSubserie=" + admSubserie +
                ", admRelEqDestinoList=" + admRelEqDestinoList +
                ", nombreUAdminO='" + nombreUAdminO + '\'' +
                ", nombreOProd='" + nombreOProd + '\'' +
                '}';
    }
}
