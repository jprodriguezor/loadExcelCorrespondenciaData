package co.com.foundation.soaint.documentmanager.persistence.entity;

import javax.persistence.*;
import javax.persistence.TableGenerator;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "TVS_ORGANIGRAMA_ADMINISTRATIVO")
@NamedQueries({

        @NamedQuery(name = "TvsOrganigramaAdministrativo.updateVersion",
                query = "UPDATE TvsOrganigramaAdministrativo t SET t.valVersion = :VAL_VERSION  WHERE t.valVersion = 'TOP'"),

        @NamedQuery(name = "TvsOrganigramaAdministrativo.consultarElementoRayz",
                query = "SELECT NEW co.com.foundation.soaint.documentmanager.domain.OrganigramaItemVO(t.ideOrgaAdmin," +
                        "t.codOrg, t.nomOrg, t.indEsActivo, t.descOrg, t.codNivel)FROM TvsOrganigramaAdministrativo t " +
                        "WHERE t.ideOrgaAdminPadre IS NULL AND t.valVersion ='TOP' AND t.indEsActivo =:STATUS ORDER BY t.nomOrg ASC"),

        @NamedQuery(name = "TvsOrganigramaAdministrativo.consultarElementoRayzByVersion",
                query = "SELECT NEW co.com.foundation.soaint.documentmanager.domain.OrganigramaItemVO(t.ideOrgaAdmin," +
                        "t.codOrg, t.nomOrg, t.indEsActivo, t.descOrg, t.codNivel)FROM TvsOrganigramaAdministrativo t " +
                        "WHERE t.ideOrgaAdminPadre IS NULL AND t.valVersion = :VERSION AND t.indEsActivo =:STATUS ORDER BY t.nomOrg ASC"),

        @NamedQuery(name = "TvsOrganigramaAdministrativo.consultarDescendientesDirectos",
                query = "SELECT NEW co.com.foundation.soaint.documentmanager.domain.OrganigramaItemVO(t.ideOrgaAdmin," +
                        "t.codOrg, t.nomOrg, t.ideOrgaAdminPadre.ideOrgaAdmin, t.ideOrgaAdminPadre.nomOrg, t.indEsActivo, " +
                        "t.codNivel, t.descOrg, t.ideOrgaAdminPadre.codNivel )FROM TvsOrganigramaAdministrativo t " +
                        "WHERE t.ideOrgaAdminPadre.ideOrgaAdmin = :ID_PADRE AND t.valVersion ='TOP' AND t.indEsActivo =:STATUS ORDER BY t.nomOrg ASC"),

        @NamedQuery(name = "TvsOrganigramaAdministrativo.consultarDescendientesDirectosVersion",
                query = "SELECT NEW co.com.foundation.soaint.documentmanager.domain.OrganigramaItemVO(t.ideOrgaAdmin," +
                        "t.codOrg, t.nomOrg, t.ideOrgaAdminPadre.ideOrgaAdmin, t.ideOrgaAdminPadre.nomOrg, t.indEsActivo, " +
                        "t.codNivel, t.descOrg, t.ideOrgaAdminPadre.codNivel )FROM TvsOrganigramaAdministrativo t " +
                        "WHERE t.ideOrgaAdminPadre.ideOrgaAdmin = :ID_PADRE AND t.valVersion = :VAL_VERSION AND t.indEsActivo = '1'"),

        @NamedQuery(name = "TvsOrganigramaAdministrativo.consultarElementoPorId",
                query = "SELECT t FROM TvsOrganigramaAdministrativo t WHERE t.ideOrgaAdmin = :ID AND t.valVersion ='TOP'"),

        @NamedQuery(name = "TvsOrganigramaAdministrativo.consultarElemetosOrganigrama",
                query = "SELECT NEW co.com.foundation.soaint.documentmanager.domain.OrganigramaItemVO(t.ideOrgaAdmin," +
                        "t.codOrg, t.nomOrg, t.ideOrgaAdminPadre.ideOrgaAdmin, t.ideOrgaAdminPadre.nomOrg, t.indEsActivo, t.codNivel, t.descOrg, t.ideOrgaAdminPadre.codNivel )" +
                        "FROM TvsOrganigramaAdministrativo t WHERE t.valVersion ='TOP' AND t.indEsActivo =:STATUS ORDER BY t.nomOrg ASC "),

        @NamedQuery(name ="TvsOrganigramaAdministrativo.consultarElemetosOrganigramaPorNombre",
                query = "SELECT NEW co.com.foundation.soaint.documentmanager.domain.OrganigramaItemVO(t.ideOrgaAdmin," +
                        "t.codOrg, t.nomOrg, t.ideOrgaAdminPadre.ideOrgaAdmin, t.ideOrgaAdminPadre.nomOrg, t.indEsActivo, " +
                        "t.codNivel, t.descOrg, t.ideOrgaAdminPadre.codNivel )FROM TvsOrganigramaAdministrativo t " +
                        "WHERE t.codOrg =:CODIGO_ORGA AND t.valVersion ='TOP'"),

        @NamedQuery(name = "TvsOrganigramaAdministrativo.findAllUniAmdTrd",
                query = "SELECT DISTINCT NEW co.com.foundation.soaint.documentmanager.persistence.entity.TvsOrganigramaAdministrativo(a.ideOrgaAdmin, a.codOrg, a.nomOrg)" +
                        "FROM AdmCcd c " +
                        "INNER JOIN TvsOrganigramaAdministrativo a on a.codOrg = c.ideUniAmt " +
                        "AND a.valVersion  = 'TOP'" +
                        "AND c.valVersion  = 'TOP'" +
                        "AND c.estCcd =1"),


        @NamedQuery(name = "TvsOrganigramaAdministrativo.findAllUniAmdTrdOrg",
                query = "SELECT DISTINCT NEW co.com.foundation.soaint.documentmanager.persistence.entity.TvsOrganigramaAdministrativo(a.ideOrgaAdmin, a.codOrg, a.nomOrg)" +
                        "FROM AdmCcd c " +
                        "INNER JOIN TvsOrganigramaAdministrativo a on a.codOrg = c.ideUniAmt " +
                        "AND a.valVersion  = c.valVersionOrg " +
                        "AND a.valVersion  =:VAL_VERSION_ORG "),


        @NamedQuery(name = "TvsOrganigramaAdministrativo.findAllUniAmdCddOrg",
                query = "SELECT DISTINCT NEW co.com.foundation.soaint.documentmanager.persistence.entity.TvsOrganigramaAdministrativo(a.ideOrgaAdmin, a.codOrg, a.nomOrg)" +
                        "FROM AdmCcd c " +
                        "INNER JOIN TvsOrganigramaAdministrativo a on a.codOrg = c.ideUniAmt " +
                        "AND a.valVersion  = c.valVersionOrg " +
                        "AND c.valVersion  =:VERSION_CCD "),
                       // "AND c.estCcd =1"),

        @NamedQuery(name = "TvsOrganigramaAdministrativo.findAllOfcProdTrd",
                query = "SELECT DISTINCT NEW co.com.foundation.soaint.documentmanager.persistence.entity.TvsOrganigramaAdministrativo(o.ideOrgaAdmin, o.codOrg, o.nomOrg)" +
                        "FROM AdmCcd c " +
                        "INNER JOIN TvsOrganigramaAdministrativo o on o.codOrg = c.ideOfcProd " +
                        "AND o.valVersion  = 'TOP'" +
                        "AND c.valVersion  = 'TOP'" +
                        "AND c.estCcd =1" +
                        "AND c.ideUniAmt =:ID_UNI_AMT "),


        @NamedQuery(name = "TvsOrganigramaAdministrativo.findAllOfcProdTrdOrg",
                query = "SELECT DISTINCT NEW co.com.foundation.soaint.documentmanager.persistence.entity.TvsOrganigramaAdministrativo(o.ideOrgaAdmin, o.codOrg, o.nomOrg)" +
                        "FROM AdmCcd c " +
                        "INNER JOIN TvsOrganigramaAdministrativo o on o.codOrg = c.ideOfcProd " +
                        "AND o.valVersion =:VAL_VERSION_ORG " +
                        "AND c.ideUniAmt  =:ID_UNI_AMT "),


        @NamedQuery(name = "TvsOrganigramaAdministrativo.findAllOfcProdCcdOrg",
                query = "SELECT DISTINCT NEW co.com.foundation.soaint.documentmanager.persistence.entity.TvsOrganigramaAdministrativo(o.ideOrgaAdmin, o.codOrg, o.nomOrg)" +
                        "FROM AdmCcd c " +
                        "INNER JOIN TvsOrganigramaAdministrativo o on o.codOrg = c.ideOfcProd " +
                        "AND o.valVersion  = c.valVersionOrg " +
                        "AND c.valVersion  =:VERSION_CCD " +
                       // "AND c.estCcd =1" +
                        "AND c.ideUniAmt =:ID_UNI_AMT "),


        @NamedQuery(name = "TvsOrganigramaAdministrativo.findAllOfcProdTrdECMCdd",
                query = "SELECT DISTINCT NEW co.com.foundation.soaint.documentmanager.persistence.entity.TvsOrganigramaAdministrativo(o.ideOrgaAdmin, o.codOrg, o.nomOrg)" +
                        "FROM AdmCcd c " +
                        "INNER JOIN TvsOrganigramaAdministrativo o on o.codOrg = c.ideOfcProd " +
                        "AND o.valVersion  = 'TOP'" +
                        "AND c.valVersion  = 'TOP'" +
                        "AND c.estCcd =1"),

        @NamedQuery(name = "TvsOrganigramaAdministrativo.findAllOfcProdTrdECM",
                query = "SELECT DISTINCT NEW co.com.foundation.soaint.documentmanager.persistence.entity.TvsOrganigramaAdministrativo(o.ideOrgaAdmin, o.codOrg, o.nomOrg)" +
                        "FROM AdmTabRetDocOrg t " +
                        "INNER JOIN TvsOrganigramaAdministrativo o on o.codOrg = t.ideOfcProd " +
                        "AND o.valVersion  = 'TOP'" ),

        @NamedQuery(name = "TvsOrganigramaAdministrativo.consultarVersionesOrganigrama",
                query = " SELECT NEW co.com.foundation.soaint.documentmanager.persistence.entity.TvsOrganigramaAdministrativo "
                        + " (t.valVersion, MAX(t.fecCreacion)) "
                        + " FROM TvsOrganigramaAdministrativo t"
                        + " GROUP BY t.valVersion "
                        + " ORDER BY t.valVersion ASC "),

        @NamedQuery(name = "TvsOrganigramaAdministrativo.consultarElementoRayzVersion",
                query = "SELECT NEW co.com.foundation.soaint.documentmanager.domain.OrganigramaItemVO(t.ideOrgaAdmin," +
                        "t.codOrg, t.nomOrg, t.indEsActivo, t.descOrg, t.codNivel )" +
                        "FROM TvsOrganigramaAdministrativo t WHERE t.ideOrgaAdminPadre IS NULL AND t.valVersion = :VAL_VERSION  ORDER BY t.nomOrg ASC"),


})
@Cacheable(true)
@TableGenerator(name = "TVS_ORGANIGRAMA_GENERATOR", table = "TABLE_GENERATOR", pkColumnName = "SEQ_NAME",
        valueColumnName = "SEQ_VALUE", pkColumnValue = "TVS_ORGANIGRAMA_SEQ", allocationSize = 1)
public class TvsOrganigramaAdministrativo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "IDE_ORGA_ADMIN")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "TVS_ORGANIGRAMA_GENERATOR")
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
    private long ideUsuarioCambio;

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

    @Column(name = "VAL_VERSION")
    private String valVersion;

    @OneToMany(mappedBy = "ideOrgaAdminPadre",fetch = FetchType.LAZY)
    private List<TvsOrganigramaAdministrativo> tvsOrganigramaAdministrativoList;

    @JoinColumn(name = "IDE_ORGA_ADMIN_PADRE", referencedColumnName = "IDE_ORGA_ADMIN")
    @ManyToOne(fetch = FetchType.LAZY)
    private TvsOrganigramaAdministrativo ideOrgaAdminPadre;


    public TvsOrganigramaAdministrativo() {
    }

    public TvsOrganigramaAdministrativo(Long ideOrgaAdmin) {
        this.ideOrgaAdmin = ideOrgaAdmin;
    }

    public TvsOrganigramaAdministrativo(Long ideOrgaAdmin, String nomOrg) {
        this.ideOrgaAdmin = ideOrgaAdmin;
        this.nomOrg = nomOrg;
    }
    public TvsOrganigramaAdministrativo(Long ideOrgaAdmin, String codOrg, String nomOrg) {

        this.ideOrgaAdmin = ideOrgaAdmin;
        this.codOrg = codOrg;
        this.nomOrg = nomOrg;
    }




    public TvsOrganigramaAdministrativo(Long ideOrgaAdmin, String codOrg, String nomOrg, String indEsActivo, String descOrg,
                                        Integer codNivel, Date fecCreacion, Date fecCambio, String valSistema, String valVersion,
                                        TvsOrganigramaAdministrativo ideOrgaAdminPadre) {
        this.ideOrgaAdmin = ideOrgaAdmin;
        this.codOrg = codOrg;
        this.nomOrg = nomOrg;
        this.indEsActivo = indEsActivo;
        this.descOrg = descOrg;
        this.codNivel = codNivel;
        this.fecCreacion = fecCreacion;
        this.fecCambio = fecCambio;
        this.valSistema = valSistema;
        this.valVersion = valVersion;
        this.ideOrgaAdminPadre = ideOrgaAdminPadre;
    }

    public TvsOrganigramaAdministrativo(String valVersion, Date fecCreacion) {
        this.valVersion = valVersion;
        this.fecCreacion = fecCreacion;
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

    public long getIdeUsuarioCambio() {
        return ideUsuarioCambio;
    }

    public void setIdeUsuarioCambio(long ideUsuarioCambio) {
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

    public String getValVersion() {
        return valVersion;
    }

    public void setValVersion(String valVersion) {
        this.valVersion = valVersion;
    }

    public List<TvsOrganigramaAdministrativo> getTvsOrganigramaAdministrativoList() {
        return tvsOrganigramaAdministrativoList;
    }
    public void setTvsOrganigramaAdministrativoList(List<TvsOrganigramaAdministrativo> tvsOrganigramaAdministrativoList) {
        this.tvsOrganigramaAdministrativoList = tvsOrganigramaAdministrativoList;
    }

    public TvsOrganigramaAdministrativo getIdeOrgaAdminPadre() {
        return ideOrgaAdminPadre;
    }

    public void setIdeOrgaAdminPadre(TvsOrganigramaAdministrativo ideOrgaAdminPadre) {
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
        if (!(object instanceof TvsOrganigramaAdministrativo)) {
            return false;
        }
        TvsOrganigramaAdministrativo other = (TvsOrganigramaAdministrativo) object;
        if ((this.ideOrgaAdmin == null && other.ideOrgaAdmin != null) || (this.ideOrgaAdmin != null && !this.ideOrgaAdmin.equals(other.ideOrgaAdmin))) {
            return false;
        }
        return true;
    }


    @Override
    public String toString() {
        return "TvsOrganigramaAdministrativo{" +
                "ideOrgaAdmin=" + ideOrgaAdmin +
                ", codOrg='" + codOrg + '\'' +
                ", nomOrg='" + nomOrg + '\'' +
                ", descOrg='" + descOrg + '\'' +
                ", indEsActivo='" + indEsActivo + '\'' +
                ", codNivel=" + codNivel +
                ", valSistema='" + valSistema + '\'' +
                ", valVersion='" + valVersion + '\'' +
                ", ideOrgaAdminPadre=" + ideOrgaAdminPadre +
                '}';
    }
}
