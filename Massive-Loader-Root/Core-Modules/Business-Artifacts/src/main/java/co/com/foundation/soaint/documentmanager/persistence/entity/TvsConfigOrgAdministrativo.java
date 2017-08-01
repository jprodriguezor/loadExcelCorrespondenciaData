package co.com.foundation.soaint.documentmanager.persistence.entity;

/**
 * Created by jrodriguez on 30/10/2016.
 */

import javax.persistence.*;
import javax.persistence.TableGenerator;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "TVS_CONFIG_ORG_ADMINISTRATIVO")
@NamedQueries({

        @NamedQuery(name = "TvsConfigOrgAdministrativo.consultarElementoRayzConEntity",
                query = "SELECT NEW co.com.foundation.soaint.documentmanager.persistence.entity." +
                        "TvsConfigOrgAdministrativo( t.ideOrgaAdmin,t.codOrg, t.nomOrg, t.descOrg, t.indEsActivo,t.codNivel )" +
                        "FROM TvsConfigOrgAdministrativo t WHERE t.ideOrgaAdminPadre IS NULL"),

        @NamedQuery(name = "TvsConfigOrgAdministrativo.consultarDescendientesDirectosConEntity",
                query = "SELECT NEW co.com.foundation.soaint.documentmanager.persistence.entity." +
                        "TvsConfigOrgAdministrativo( t.ideOrgaAdmin,t.codOrg, t.nomOrg, t.descOrg, t.indEsActivo,t.codNivel )" +
                        "FROM TvsConfigOrgAdministrativo t WHERE t.ideOrgaAdminPadre.ideOrgaAdmin = :ID_PADRE"),

        @NamedQuery(name = "TvsConfigOrgAdministrativo.consultarElementoRayz",
                query = "SELECT NEW co.com.foundation.soaint.documentmanager.domain.OrganigramaItemVO(t.ideOrgaAdmin," +
                        "t.codOrg, t.nomOrg, t.indEsActivo, t.descOrg, t.codNivel )" +
                        "FROM TvsConfigOrgAdministrativo t WHERE t.ideOrgaAdminPadre IS NULL  ORDER BY t.nomOrg ASC"),

        @NamedQuery(name = "TvsConfigOrgAdministrativo.consultarElemetosOrganigrama",
                query = "SELECT NEW co.com.foundation.soaint.documentmanager.domain.OrganigramaItemVO(t.ideOrgaAdmin," +
                        "t.codOrg, t.nomOrg, t.ideOrgaAdminPadre.ideOrgaAdmin, t.ideOrgaAdminPadre.nomOrg,t.indEsActivo, t.codNivel ,t.descOrg, t.ideOrgaAdminPadre.codNivel)" +
                        "FROM TvsConfigOrgAdministrativo t  ORDER BY t.nomOrg ASC "),

        @NamedQuery(name = "TvsConfigOrgAdministrativo.consultarDescendientesDirectos",
                query = "SELECT NEW co.com.foundation.soaint.documentmanager.domain.OrganigramaItemVO(t.ideOrgaAdmin," +
                        "t.codOrg, t.nomOrg, t.ideOrgaAdminPadre.ideOrgaAdmin, t.ideOrgaAdminPadre.nomOrg, t.indEsActivo, t.codNivel,t.descOrg, t.ideOrgaAdminPadre.codNivel)" +
                        "FROM TvsConfigOrgAdministrativo t WHERE t.ideOrgaAdminPadre.ideOrgaAdmin = :ID_PADRE "),

        @NamedQuery(name = "TvsConfigOrgAdministrativo.countByCodOrga",
                query = "SELECT COUNT(t)FROM TvsConfigOrgAdministrativo t WHERE t.codOrg = :COD_ORGA"),

        @NamedQuery(name = "TvsConfigOrgAdministrativo.countByNomOrga",
                query = "SELECT COUNT(t)FROM TvsConfigOrgAdministrativo t WHERE UPPER(t.nomOrg) = UPPER(:NOM_ORGA)"),

        @NamedQuery(name = "TvsConfigOrgAdministrativo.countByNomOrgaAndDifferentId",
                query = "SELECT COUNT(t)FROM TvsConfigOrgAdministrativo t WHERE UPPER(t.nomOrg) = UPPER(:NOM_ORGA) AND t.ideOrgaAdmin <> :ID"),

        @NamedQuery(name = "TvsConfigOrgAdministrativo.cleanOrganigrama",
                query = "DELETE FROM TvsConfigOrgAdministrativo t "),

})

@Cacheable(true)
@TableGenerator(name = "TVS_CONFIG_ORG_GENERATOR", table = "TABLE_GENERATOR", pkColumnName = "SEQ_NAME",
        valueColumnName = "SEQ_VALUE", pkColumnValue = "TVS_CONFIG_ORGANIGRAMA_SEQ", allocationSize = 1)
public class TvsConfigOrgAdministrativo implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "IDE_ORGA_ADMIN")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "TVS_CONFIG_ORG_GENERATOR")
    private Long ideOrgaAdmin;

    @Basic(optional = false)
    @Column(name = "COD_ORG")
    private String codOrg;

    @Basic(optional = false)
    @Column(name = "NOM_ORG")
    private String nomOrg;

    @Basic(optional = false)
    @Column(name = "DESC_ORG")
    private String descOrg;

    @Basic(optional = false)
    @Column(name = "IND_ES_ACTIVO")
    private String indEsActivo;

    @Column(name = "IDE_DIRECCION")
    private BigInteger ideDireccion;

    @Column(name = "IDE_PLAN_RESPONSABLE")
    private BigInteger idePlanResponsable;

    @Column(name = "COD_NIVEL")
    private Integer codNivel;

    @Column(name = "FEC_CREACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecCreacion;

    @Column(name = "IDE_USUARIO_CREO")
    private Long ideUsuarioCreo;

    @Column(name = "IDE_USUARIO_CAMBIO")
    private Long ideUsuarioCambio;

    @Column(name = "FEC_CAMBIO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecCambio;

    @Column(name = "NIV_LECTURA")
    private int nivLectura;

    @Column(name = "NIV_ESCRITURA")
    private int nivEscritura;

    @Column(name = "IDE_UUID")
    private String ideUuid;

    @Column(name = "VAL_SISTEMA")
    private String valSistema;

    @OneToMany(mappedBy = "ideOrgaAdminPadre", fetch = FetchType.LAZY)
    private List<TvsConfigOrgAdministrativo> tvsConfigOrgAdministrativoList;

    @JoinColumn(name = "IDE_ORGA_ADMIN_PADRE", referencedColumnName = "IDE_ORGA_ADMIN")
    @ManyToOne(fetch = FetchType.LAZY)
    private TvsConfigOrgAdministrativo ideOrgaAdminPadre;

    public TvsConfigOrgAdministrativo() {
    }

    public TvsConfigOrgAdministrativo(Long ideOrgaAdmin,String codOrg, String nomOrg, String descOrg, String indEsActivo,Integer codNivel) {

        this.ideOrgaAdmin = ideOrgaAdmin;
        this.codOrg = codOrg;
        this.nomOrg = nomOrg;
        this.descOrg = descOrg;
        this.indEsActivo = indEsActivo;
        this.codNivel = codNivel;
    }

    public TvsConfigOrgAdministrativo(Long ideOrgaAdmin) {
        this.ideOrgaAdmin = ideOrgaAdmin;
    }

    public TvsConfigOrgAdministrativo(Long ideOrgaAdmin, String nomOrg) {
        this.ideOrgaAdmin = ideOrgaAdmin;
        this.nomOrg = nomOrg;
    }

    public TvsConfigOrgAdministrativo(Long ideOrgaAdmin, String codOrg, String nomOrg, String indEsActivo, String descOrg,
                                        Integer codNivel, Date fecCreacion, Date fecCambio, String valSistema,
                                        TvsConfigOrgAdministrativo ideOrgaAdminPadre) {
        this.ideOrgaAdmin = ideOrgaAdmin;
        this.codOrg = codOrg;
        this.nomOrg = nomOrg;
        this.indEsActivo = indEsActivo;
        this.descOrg = descOrg;
        this.codNivel = codNivel;
        this.fecCreacion = fecCreacion;
        this.fecCambio = fecCambio;
        this.valSistema = valSistema;
        this.ideOrgaAdminPadre = ideOrgaAdminPadre;
    }

    public Long getIdeOrgaAdmin() {
        return ideOrgaAdmin;
    }

    public void setIdeOrgaAdmin(Long ideOrgaAdmin) {
        this.ideOrgaAdmin = ideOrgaAdmin;
    }

    public String getCodOrg() {
        return codOrg;
    }

    public void setCodOrg(String codOrg) {
        this.codOrg = codOrg;
    }

    public String getNomOrg() {
        return nomOrg;
    }

    public void setNomOrg(String nomOrg) {
        this.nomOrg = nomOrg;
    }

    public String getDescOrg() {
        return descOrg;
    }

    public void setDescOrg(String descOrg) {
        this.descOrg = descOrg;
    }

    public String getIndEsActivo() {
        return indEsActivo;
    }

    public void setIndEsActivo(String indEsActivo) {
        this.indEsActivo = indEsActivo;
    }

    public BigInteger getIdeDireccion() {
        return ideDireccion;
    }

    public void setIdeDireccion(BigInteger ideDireccion) {
        this.ideDireccion = ideDireccion;
    }

    public BigInteger getIdePlanResponsable() {
        return idePlanResponsable;
    }

    public void setIdePlanResponsable(BigInteger idePlanResponsable) {
        this.idePlanResponsable = idePlanResponsable;
    }

    public Integer getCodNivel() {
        return codNivel;
    }

    public void setCodNivel(Integer codNivel) {
        this.codNivel = codNivel;
    }

    public Date getFecCreacion() {
        return fecCreacion;
    }

    public void setFecCreacion(Date fecCreacion) {
        this.fecCreacion = fecCreacion;
    }

    public Long getIdeUsuarioCreo() {
        return ideUsuarioCreo;
    }

    public void setIdeUsuarioCreo(Long ideUsuarioCreo) {
        this.ideUsuarioCreo = ideUsuarioCreo;
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

    public int getNivLectura() {
        return nivLectura;
    }

    public void setNivLectura(int nivLectura) {
        this.nivLectura = nivLectura;
    }

    public int getNivEscritura() {
        return nivEscritura;
    }

    public void setNivEscritura(int nivEscritura) {
        this.nivEscritura = nivEscritura;
    }

    public String getIdeUuid() {
        return ideUuid;
    }

    public void setIdeUuid(String ideUuid) {
        this.ideUuid = ideUuid;
    }

    public String getValSistema() {
        return valSistema;
    }

    public void setValSistema(String valSistema) {
        this.valSistema = valSistema;
    }

    public List<TvsConfigOrgAdministrativo> getTvsConfigOrgAdministrativoList() {
        return tvsConfigOrgAdministrativoList;
    }

    public void setTvsConfigOrgAdministrativoList(List<TvsConfigOrgAdministrativo> tvsConfigOrgAdministrativoList) {
        this.tvsConfigOrgAdministrativoList = tvsConfigOrgAdministrativoList;
    }

    public TvsConfigOrgAdministrativo getIdeOrgaAdminPadre() {
        return ideOrgaAdminPadre;
    }

    public void setIdeOrgaAdminPadre(TvsConfigOrgAdministrativo ideOrgaAdminPadre) {
        this.ideOrgaAdminPadre = ideOrgaAdminPadre;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ideOrgaAdmin != null ? ideOrgaAdmin.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TvsConfigOrgAdministrativo)) {
            return false;
        }
        TvsConfigOrgAdministrativo other = (TvsConfigOrgAdministrativo) object;
        if ((this.ideOrgaAdmin == null && other.ideOrgaAdmin != null) || (this.ideOrgaAdmin != null && !this.ideOrgaAdmin.equals(other.ideOrgaAdmin))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.foundation.soaint.documentmanager.business.entity.TvsConfigOrgAdministrativo[ ideOrgaAdmin=" + ideOrgaAdmin + " ]";
    }

}