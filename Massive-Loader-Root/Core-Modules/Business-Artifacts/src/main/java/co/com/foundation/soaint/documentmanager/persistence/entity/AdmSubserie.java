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
@Table(name = "ADM_SUBSERIE")
@NamedQueries({
        @NamedQuery(name = "AdmSubserie.findAll",
                query = "SELECT NEW co.com.foundation.soaint.documentmanager.persistence.entity.AdmSubserie("
                        + "sub.ideSubserie," +
                        "sub.actAdministrativo, sub.carAdministrativa, sub.carLegal, sub.carTecnica, sub.codSubserie," +
                        "sub.estSubserie, sub.fueBibliografica, sub.nomSubserie, sub.notAlcance,m.ideMotCreacion, " +
                        "m.nomMotCreacion, s.ideSerie , s.codSerie ,s.nomSerie) FROM AdmSubserie sub "
                        + "INNER JOIN sub.ideMotCreacion m " +
                        "INNER JOIN  sub.ideSerie s "),

        @NamedQuery(name = "AdmSubserie.searchSubserieById",
                query = "SELECT NEW co.com.foundation.soaint.documentmanager.persistence.entity.AdmSubserie(sub.ideSubserie," +
                        "sub.actAdministrativo, sub.carAdministrativa, sub.carLegal, sub.carTecnica, sub.codSubserie," +
                        "sub.estSubserie, sub.fueBibliografica, sub.nomSubserie, sub.notAlcance,m.ideMotCreacion, " +
                        "m.nomMotCreacion, s.ideSerie ,s.nomSerie, sub.fecCreacion) FROM AdmSubserie sub "
                        + "INNER JOIN sub.ideMotCreacion m " +
                        "INNER JOIN  sub.ideSerie s WHERE sub.ideSubserie =:ID_SUBSERIE  "),

        @NamedQuery(name = "AdmSubserie.findByEstado",
                query = "SELECT NEW co.com.foundation.soaint.documentmanager.persistence.entity.AdmSubserie(s.ideSubserie," +
                        "s.codSubserie, s.nomSubserie, s.ideSerie.ideSerie)FROM AdmSubserie s WHERE s.estSubserie = :ESTADO"),

        @NamedQuery(name = "AdmSubserie.deleteSerieById",
                query = "DELETE FROM AdmSubserie s WHERE s.ideSerie.ideSerie = :ID_SERIE"),

        @NamedQuery(name = "AdmSubserie.deleteSubserieById",
                query = "DELETE FROM AdmSubserie s WHERE s.ideSubserie = :ID_SUBSERIE"),

        @NamedQuery(name = "AdmSubserie.countByNomSubSerie",
                query = "SELECT COUNT(s)FROM AdmSubserie s WHERE s.nomSubserie = :NOM_SUBSERIE"),

        @NamedQuery(name = "AdmSubserie.countSubserieExistByIdSerie",
                query = "SELECT COUNT(s) FROM AdmSubserie s WHERE s.codSubserie = :COD_SUBSERIE AND s.ideSerie.ideSerie = :ID_SERIE "),

        @NamedQuery(name = "AdmSubserie.findSubserieExistByIdSerie",
                query = "SELECT s FROM AdmSubserie s WHERE s.codSubserie = :COD_SUBSERIE AND s.ideSerie.ideSerie = :ID_SERIE "),

        @NamedQuery(name = "AdmSubserie.countCodSubserieExistByIdSerie",
                query = "SELECT COUNT(ss) FROM AdmSubserie ss " +
                        "inner join AdmSerie s on s.ideSerie = ss.ideSerie.ideSerie " +
                        "WHERE ss.codSubserie = :COD_SUBSERIE AND s.codSerie = :ID_SERIE "),
        
        @NamedQuery(name = "AdmSubserie.searchSubserieByIdSerie",
                query = "SELECT NEW co.com.foundation.soaint.documentmanager.persistence.entity.AdmSubserie(sub.ideSubserie, sub.codSubserie," +
                       "sub.nomSubserie, s.ideSerie) FROM AdmSubserie sub INNER JOIN  sub.ideSerie s WHERE s.ideSerie =:ID_SERIE"),

        @NamedQuery(name = "AdmSubserie.countByCodSerieAndSubserie",
                query = "SELECT COUNT(ss) FROM AdmSubserie ss " +
                        "WHERE ss.codSubserie = :COD_SUBSERIE and ss.ideSerie.codSerie = :COD_SERIE "),

})

@TableGenerator(name = "ADM_SUBSERIE_GENERATOR", table = "TABLE_GENERATOR", pkColumnName = "SEQ_NAME",
        valueColumnName = "SEQ_VALUE", pkColumnValue = "ADM_SUBSERIE_SEQ", allocationSize = 1)

public class AdmSubserie implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "IDE_SUBSERIE")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ADM_SUBSERIE_GENERATOR")
    private BigInteger ideSubserie;
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
    @Column(name = "COD_SUBSERIE")
    private String codSubserie;
    @Basic(optional = false)
    @Column(name = "EST_SUBSERIE")
    private int estSubserie;
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
    @Column(name = "NOM_SUBSERIE")
    private String nomSubserie;
    @Column(name = "NOT_ALCANCE")
    private String notAlcance;
    @JoinColumn(name = "IDE_MOT_CREACION", referencedColumnName = "IDE_MOT_CREACION")
    @ManyToOne(optional = false)
    private AdmMotCreacion ideMotCreacion;
    @JoinColumn(name = "IDE_SERIE", referencedColumnName = "IDE_SERIE")
    @ManyToOne
    private AdmSerie ideSerie;
    @OneToMany(mappedBy = "ideSubserie")
    private List<AdmSerSubserTpg> admSerSubserTpgList;

    @OneToMany(mappedBy = "ideSubserie")
    private List<AdmCcd> admCcdList;

    @OneToMany(mappedBy = "ideSubserie")
    private List<AdmTabRetDoc> admTabRetDocList;

    public AdmSubserie() {
    }

    public AdmSubserie(BigInteger ideSubserie) {
        this.ideSubserie = ideSubserie;
    }

    //[AdmCcd.findAll]
    public AdmSubserie(BigInteger ideSubserie, String codSubserie, String nomSubserie) {
        this.ideSubserie =ideSubserie;
        this.codSubserie = codSubserie;
        this.nomSubserie = nomSubserie;
    }

    //[  AdmSubserie.findAll  ]
    public AdmSubserie(BigInteger ideSubserie, String actAdministrativo, Long carAdministrativa, Long carLegal,
                       Long carTecnica, String codSubserie, int estSubserie, String fueBibliografica, String nomSubserie,
                       String notAlcance, BigInteger idMotivo, String nombreMotCreacion, BigInteger idSerie, String codSerie, String nomSerie) {
        this.ideSubserie = ideSubserie;
        this.actAdministrativo = actAdministrativo;
        this.carAdministrativa = carAdministrativa;
        this.carLegal = carLegal;
        this.carTecnica = carTecnica;
        this.codSubserie = codSubserie;
        this.estSubserie = estSubserie;
        this.fueBibliografica = fueBibliografica;
        this.nomSubserie = nomSubserie;
        this.notAlcance = notAlcance;
        ideMotCreacion = new AdmMotCreacion(idMotivo, nombreMotCreacion);
        ideSerie = new AdmSerie(idSerie,codSerie, nomSerie);
    }

    //[  AdmSubserie.searchSubserieById  ]
    public AdmSubserie(BigInteger ideSubserie, String actAdministrativo, Long carAdministrativa, Long carLegal,
                       Long carTecnica, String codSubserie, int estSubserie, String fueBibliografica, String nomSubserie,
                       String notAlcance, BigInteger idMotivo, String nombreMotCreacion, BigInteger idSerie, String nomSerie, Date fecCreacion) {
        this.ideSubserie = ideSubserie;
        this.actAdministrativo = actAdministrativo;
        this.carAdministrativa = carAdministrativa;
        this.carLegal = carLegal;
        this.carTecnica = carTecnica;
        this.codSubserie = codSubserie;
        this.estSubserie = estSubserie;
        this.fueBibliografica = fueBibliografica;
        this.nomSubserie = nomSubserie;
        this.notAlcance = notAlcance;
        ideMotCreacion = new AdmMotCreacion(idMotivo, nombreMotCreacion);
        ideSerie = new AdmSerie(idSerie, nomSerie);
        this.fecCreacion = fecCreacion;
    }

    //[ Constructor  EntityAdmSubserieBuilder.class]
    public AdmSubserie(BigInteger ideSubserie, String actAdministrativo, Long carAdministrativa, Long carLegal,
                       Long carTecnica, String codSubserie, int estSubserie, Date fecCambio, Date fecCreacion,
                       String fueBibliografica, BigInteger ideUsuarioCambio, String ideUuid, Integer nivLectura,
                       Integer nivEscritura, String nomSubserie, String notAlcance, AdmMotCreacion ideMotCreacion,
                       AdmSerie ideSerie) {
        this.ideSubserie = ideSubserie;
        this.actAdministrativo = actAdministrativo;
        this.carAdministrativa = carAdministrativa;
        this.carLegal = carLegal;
        this.carTecnica = carTecnica;
        this.codSubserie = codSubserie;
        this.estSubserie = estSubserie;
        this.fecCambio = fecCambio;
        this.fecCreacion = fecCreacion;
        this.fueBibliografica = fueBibliografica;
        this.ideUsuarioCambio = ideUsuarioCambio;
        this.ideUuid = ideUuid;
        this.nivLectura = nivLectura;
        this.nivEscritura = nivEscritura;
        this.nomSubserie = nomSubserie;
        this.notAlcance = notAlcance;
        this.ideMotCreacion = ideMotCreacion;
        this.ideSerie = ideSerie;
    }

    // [ AdmSubserie.findByEstado]
    public AdmSubserie(BigInteger ideSubserie, String codSubserie, String nomSubserie, BigInteger idSerie) {
        this.ideSubserie = ideSubserie;
        this.codSubserie = codSubserie;
        this.nomSubserie = nomSubserie;
    }
    
    public AdmSubserie(BigInteger ideSubserie, String nomSubserie) {
        this.ideSubserie = ideSubserie;
        this.nomSubserie = nomSubserie;
    }

    public AdmSubserie(String codSubserie, String nomSubserie) {
        this.codSubserie = codSubserie;
        this.nomSubserie = nomSubserie;
    }
    
    

    public BigInteger getIdeSubserie() {
        return ideSubserie;
    }

    public void setIdeSubserie(BigInteger ideSubserie) {
        this.ideSubserie = ideSubserie;
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

    public String getCodSubserie() {
        return codSubserie;
    }

    public void setCodSubserie(String codSubserie) {
        this.codSubserie = codSubserie;
    }

    public int getEstSubserie() {
        return estSubserie;
    }

    public void setEstSubserie(int estSubserie) {
        this.estSubserie = estSubserie;
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

    public String getNomSubserie() {
        return nomSubserie;
    }

    public void setNomSubserie(String nomSubserie) {
        this.nomSubserie = nomSubserie;
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

    public AdmSerie getIdeSerie() {
        return ideSerie;
    }

    public void setIdeSerie(AdmSerie ideSerie) {
        this.ideSerie = ideSerie;
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
        hash += (ideSubserie != null ? ideSubserie.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AdmSubserie)) {
            return false;
        }
        AdmSubserie other = (AdmSubserie) object;
        if ((this.ideSubserie == null && other.ideSubserie != null) || (this.ideSubserie != null && !this.ideSubserie.equals(other.ideSubserie))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AdmSubserie{" +
                "ideSubserie=" + ideSubserie +
                ", actAdministrativo='" + actAdministrativo + '\'' +
                ", carAdministrativa=" + carAdministrativa +
                ", carLegal=" + carLegal +
                ", carTecnica=" + carTecnica +
                ", codSubserie='" + codSubserie + '\'' +
                ", estSubserie=" + estSubserie +
                ", fecCambio=" + fecCambio +
                ", fecCreacion=" + fecCreacion +
                ", fueBibliografica='" + fueBibliografica + '\'' +
                ", ideUsuarioCambio=" + ideUsuarioCambio +
                ", ideUuid='" + ideUuid + '\'' +
                ", nivEscritura=" + nivEscritura +
                ", nivLectura=" + nivLectura +
                ", nomSubserie='" + nomSubserie + '\'' +
                ", notAlcance='" + notAlcance + '\'' +
                ", ideMotCreacion=" + ideMotCreacion +
                ", ideSerie=" + ideSerie +
                '}';
    }
}
