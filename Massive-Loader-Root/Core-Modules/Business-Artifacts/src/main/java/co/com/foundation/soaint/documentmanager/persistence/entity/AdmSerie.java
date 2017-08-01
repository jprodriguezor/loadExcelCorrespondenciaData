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
import javax.persistence.*;
import javax.persistence.TableGenerator;

/**
 * @author jrodriguez
 */
@Entity
@Table(name = "ADM_SERIE")
@NamedQueries({
        @NamedQuery(name = "AdmSerie.findAll",
                query = "SELECT NEW co.com.foundation.soaint.documentmanager.persistence.entity.AdmSerie(s.ideSerie," +
                        "s.actAdministrativo, s.carAdministrativa, s.carLegal,s.carTecnica, s.codSerie, s.estSerie," +
                        "s.fueBibliografica, s.nomSerie, s.notAlcance, m.ideMotCreacion, m.nomMotCreacion ) " +
                        "FROM AdmSerie s INNER JOIN s.ideMotCreacion m "),

        @NamedQuery(name = "AdminSerie.searchSerieById",
                query = "SELECT NEW co.com.foundation.soaint.documentmanager.persistence.entity.AdmSerie(s.ideSerie," +
                        "s.actAdministrativo, s.carAdministrativa, s.carLegal,s.carTecnica, s.codSerie, s.estSerie," +
                        "s.fueBibliografica, s.nomSerie, s.notAlcance, m.ideMotCreacion, m.nomMotCreacion, s.fecCreacion, s.fecCambio ) " +
                        "FROM AdmSerie s INNER JOIN s.ideMotCreacion m WHERE  s.ideSerie =:ID_SERIE"),

        @NamedQuery(name = "AdmSerie.searchIdSerieByCodSerie",
                query = "SELECT NEW co.com.foundation.soaint.documentmanager.persistence.entity.AdmSerie(s.ideSerie," +
                        "s.codSerie, s.nomSerie)FROM AdmSerie s WHERE s.codSerie = :COD_SERIE"),

        @NamedQuery(name = "AdmSerie.findByIdeSerie",
                query = "SELECT s FROM AdmSerie s WHERE s.ideSerie = :ID_SERIE"),

        @NamedQuery(name = "AdmSerie.deleteSerieById",
                query = "DELETE FROM AdmSerie s WHERE s.ideSerie = :ID_SERIE"),

        @NamedQuery(name = "AdmSerie.findByEstado",
                query = "SELECT NEW co.com.foundation.soaint.documentmanager.persistence.entity.AdmSerie(s.ideSerie, " +
                        "s.codSerie, s.nomSerie)FROM AdmSerie s WHERE s.estSerie = :ESTADO"),

        @NamedQuery(name = "AdmSerie.countByCodSerie",
                query = "SELECT COUNT(s)FROM AdmSerie s WHERE s.codSerie = :COD_SERIE"),


        @NamedQuery(name = "AdmSerie.countByNomSerie",
                query = "SELECT COUNT(s)FROM AdmSerie s WHERE s.nomSerie = :NOM_SERIE"),

        @NamedQuery(name = "AdmSerie.countByIdSerie",
                query = "SELECT COUNT(s) FROM AdmSerie s WHERE s.ideSerie = :ID_SERIE"),





})

@TableGenerator(name = "ADM_SERIE_GENERATOR", table = "TABLE_GENERATOR", pkColumnName = "SEQ_NAME",
        valueColumnName = "SEQ_VALUE", pkColumnValue = "ADM_SERIE_SEQ", allocationSize = 1)
public class AdmSerie implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "IDE_SERIE")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ADM_SERIE_GENERATOR")
    private BigInteger ideSerie;

    @Basic(optional = false)
    @Column(name = "ACT_ADMINISTRATIVO")
    private String actAdministrativo;

    @Column(name = "CAR_ADMINISTRATIVA")
    private Long carAdministrativa;

    @Column(name = "CAR_LEGAL")
    private Long carLegal;

    @Column(name = "CAR_TECNICA")
    private Long carTecnica;

    @Basic(optional = false)
    @Column(name = "COD_SERIE")
    private String codSerie;

    @Basic(optional = false)
    @Column(name = "EST_SERIE")
    private int estSerie;

    @Column(name = "FEC_CAMBIO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecCambio;

    @Column(name = "FEC_CREACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecCreacion;

    @Column(name = "FUE_BIBLIOGRAFICA")
    private String fueBibliografica;

    @Column(name = "IDE_USUARIO_CAMBIO")
    private BigInteger ideUsuarioCambio;

    @Column(name = "IDE_UUID")
    private String ideUuid;

    @Column(name = "NIV_ESCRITURA")
    private Integer nivEscritura;

    @Column(name = "NIV_LECTURA")
    private Integer nivLectura;

    @Basic(optional = false)
    @Column(name = "NOM_SERIE")
    private String nomSerie;

    @Column(name = "NOT_ALCANCE")
    private String notAlcance;

    @JoinColumn(name = "IDE_MOT_CREACION", referencedColumnName = "IDE_MOT_CREACION")
    @ManyToOne(optional = false)
    private AdmMotCreacion ideMotCreacion;

    @OneToMany(mappedBy = "ideSerie", cascade = CascadeType.ALL)
    private List<AdmSubserie> admSubserieList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ideSerie")
    private List<AdmSerSubserTpg> admSerSubserTpgList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ideSerie")
    private List<AdmCcd> admCcdList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ideSerie")
    private List<AdmTabRetDoc> admTabRetDocList;

    public AdmSerie() {
    }

    public AdmSerie(BigInteger ideSerie, String nomSerie) {
        this.ideSerie = ideSerie;
        this.nomSerie = nomSerie;
    }

    public AdmSerie(BigInteger ideSerie) {
        this.ideSerie = ideSerie;
    }

    // [ AdmSerie.findByEstado ]
    public AdmSerie(BigInteger ideSerie, String codSerie, String nomSerie) {
        this.ideSerie = ideSerie;
        this.codSerie = codSerie;
        this.nomSerie = nomSerie;
    }

    public AdmSerie(String codSerie, String nomSerie) {
        this.codSerie = codSerie;
        this.nomSerie = nomSerie;
    }
    

    // [ AdmSerie.findAll ]
    public AdmSerie(BigInteger ideSerie, String actAdministrativo, Long carAdministrativa, Long carLegal,
                    Long carTecnica, String codSerie, int estSerie, String fueBibliografica, String nomSerie,
                    String notAlcance, BigInteger idMotivo, String nombreMotCreacion) {
        this.ideSerie = ideSerie;
        this.actAdministrativo = actAdministrativo;
        this.carAdministrativa = carAdministrativa;
        this.carLegal = carLegal;
        this.carTecnica = carTecnica;
        this.codSerie = codSerie;
        this.estSerie = estSerie;
        this.fueBibliografica = fueBibliografica;
        this.nomSerie = nomSerie;
        this.notAlcance = notAlcance;
        ideMotCreacion = new AdmMotCreacion(idMotivo, nombreMotCreacion);
    }

    // [ AdminSerie.searchSerieById ]
    public AdmSerie(BigInteger ideSerie, String actAdministrativo, Long carAdministrativa, Long carLegal,
                    Long carTecnica, String codSerie, int estSerie, String fueBibliografica, String nomSerie,
                    String notAlcance, BigInteger idMotivo, String nombreMotCreacion, Date fecCambio, Date fecCreacion) {
        this.ideSerie = ideSerie;
        this.actAdministrativo = actAdministrativo;
        this.carAdministrativa = carAdministrativa;
        this.carLegal = carLegal;
        this.carTecnica = carTecnica;
        this.codSerie = codSerie;
        this.estSerie = estSerie;
        this.fueBibliografica = fueBibliografica;
        this.nomSerie = nomSerie;
        this.notAlcance = notAlcance;
        ideMotCreacion = new AdmMotCreacion(idMotivo, nombreMotCreacion);
        this.fecCambio = fecCambio;
        this.fecCreacion = fecCreacion;
    }

    //[ Constructor  EntityAdmSerieBuilder.class]
    public AdmSerie(BigInteger ideSerie, String actAdministrativo, Long carAdministrativa, Long carLegal, Long carTecnica,
                    String codSerie, int estSerie, Date fecCreacion, String fueBibliografica, String nomSerie, String notAlcance,
                    AdmMotCreacion ideMotCreacion, Integer nivLectura, Integer nivEscritura, String ideUuid, BigInteger ideUsuarioCambio, Date fecCambio) {
        this.ideSerie = ideSerie;
        this.actAdministrativo = actAdministrativo;
        this.carAdministrativa = carAdministrativa;
        this.carLegal = carLegal;
        this.carTecnica = carTecnica;
        this.codSerie = codSerie;
        this.estSerie = estSerie;
        this.fecCreacion = fecCreacion;
        this.fueBibliografica = fueBibliografica;
        this.nomSerie = nomSerie;
        this.notAlcance = notAlcance;
        this.ideMotCreacion = ideMotCreacion;
        this.nivLectura = nivLectura;
        this.nivEscritura = nivEscritura;
        this.ideUuid = ideUuid;
        this.ideUsuarioCambio = ideUsuarioCambio;
        this.fecCambio = fecCambio;
    }

    public BigInteger getIdeSerie() {
        return ideSerie;
    }

    public void setIdeSerie(BigInteger ideSerie) {
        this.ideSerie = ideSerie;
    }

    public String getActAdministrativo() {
        return actAdministrativo;
    }

    public void setActAdministrativo(String actAdministrativo) {
        this.actAdministrativo = actAdministrativo;
    }

    public Long getCarAdministrativa() {
        return carAdministrativa;
    }

    public void setCarAdministrativa(Long carAdministrativa) {
        this.carAdministrativa = carAdministrativa;
    }

    public Long getCarLegal() {
        return carLegal;
    }

    public void setCarLegal(Long carLegal) {
        this.carLegal = carLegal;
    }

    public Long getCarTecnica() {
        return carTecnica;
    }

    public void setCarTecnica(Long carTecnica) {
        this.carTecnica = carTecnica;
    }

    public String getCodSerie() {
        return codSerie;
    }

    public void setCodSerie(String codSerie) {
        this.codSerie = codSerie;
    }

    public int getEstSerie() {
        return estSerie;
    }

    public void setEstSerie(int estSerie) {
        this.estSerie = estSerie;
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

    public String getFueBibliografica() {
        return fueBibliografica;
    }

    public void setFueBibliografica(String fueBibliografica) {
        this.fueBibliografica = fueBibliografica;
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

    public String getNomSerie() {
        return nomSerie;
    }

    public void setNomSerie(String nomSerie) {
        this.nomSerie = nomSerie;
    }

    public String getNotAlcance() {
        return notAlcance;
    }

    public void setNotAlcance(String notAlcance) {
        this.notAlcance = notAlcance;
    }

    public AdmMotCreacion getIdeMotCreacion() {
        return ideMotCreacion;
    }

    public void setIdeMotCreacion(AdmMotCreacion ideMotCreacion) {
        this.ideMotCreacion = ideMotCreacion;
    }

    public List<AdmSubserie> getAdmSubserieList() {
        return admSubserieList;
    }

    public void setAdmSubserieList(List<AdmSubserie> admSubserieList) {
        this.admSubserieList = admSubserieList;
    }

    public List<AdmSerSubserTpg> getAdmSerSubserTpgList() {
        return admSerSubserTpgList;
    }

    public void setAdmSerSubserTpgList(List<AdmSerSubserTpg> admSerSubserTpgList) {
        this.admSerSubserTpgList = admSerSubserTpgList;
    }

    public List<AdmCcd> getAdmCcdList() {
        return admCcdList;
    }

    public void setAdmCcdList(List<AdmCcd> admCcdList) {
        this.admCcdList = admCcdList;
    }

    public List<AdmTabRetDoc> getAdmTabRetDocList() {
        return admTabRetDocList;
    }

    public void setAdmTabRetDocList(List<AdmTabRetDoc> admTabRetDocList) {
        this.admTabRetDocList = admTabRetDocList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ideSerie != null ? ideSerie.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AdmSerie)) {
            return false;
        }
        AdmSerie other = (AdmSerie) object;
        if ((this.ideSerie == null && other.ideSerie != null) || (this.ideSerie != null && !this.ideSerie.equals(other.ideSerie))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AdmSerie{" +
                "ideSerie=" + ideSerie +
                ", actAdministrativo='" + actAdministrativo + '\'' +
                ", carAdministrativa=" + carAdministrativa +
                ", carLegal=" + carLegal +
                ", carTecnica=" + carTecnica +
                ", codSerie='" + codSerie + '\'' +
                ", estSerie=" + estSerie +
                ", fecCambio=" + fecCambio +
                ", fecCreacion=" + fecCreacion +
                ", fueBibliografica='" + fueBibliografica + '\'' +
                ", ideUsuarioCambio=" + ideUsuarioCambio +
                ", ideUuid='" + ideUuid + '\'' +
                ", nivEscritura=" + nivEscritura +
                ", nivLectura=" + nivLectura +
                ", nomSerie='" + nomSerie + '\'' +
                ", notAlcance='" + notAlcance + '\'' +
                ", ideMotCreacion=" + ideMotCreacion +
                '}';
    }
}
