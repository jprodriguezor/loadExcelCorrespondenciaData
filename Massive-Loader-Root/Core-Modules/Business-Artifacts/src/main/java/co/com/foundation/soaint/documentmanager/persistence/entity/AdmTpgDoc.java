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

/**
 * @author jrodriguez
 */
@Entity
@Table(name = "ADM_TPG_DOC")
@NamedQueries({
        @NamedQuery(name = "AdmTpgDoc.findAll",
                query = "SELECT NEW co.com.foundation.soaint.documentmanager.persistence.entity.AdmTpgDoc(t.ideTpgDoc, t.carAdministrativa," +
                        "t.carLegal, t.carTecnica, t.codTpgDoc, t.estTpgDoc, t.nomTpgDoc, t.notAlcance, t.fueBibliografica," +
                        "s.ideSoporte , s.nomSoporte, d.ideTradDoc, d.nomTradDoc ) FROM AdmTpgDoc t INNER JOIN t.ideSoporte s " +
                        "INNER  JOIN  t.ideTraDocumental d "),

        @NamedQuery(name = "AdmTpgDoc.countByNomTpgDoc",
                query = "SELECT COUNT(t)FROM AdmTpgDoc t WHERE t.nomTpgDoc = :NOM_TPG_DOC"),

        @NamedQuery(name = "AdmTpgDoc.deleteByIdTpgDoc",
                query = "DELETE FROM AdmTpgDoc t WHERE t.ideTpgDoc = :ID_TPGDOC "),
        
        @NamedQuery(name = "AdmTpgDoc.findByEstado",
                query = "SELECT NEW co.com.foundation.soaint.documentmanager.persistence.entity.AdmTpgDoc(t.ideTpgDoc," +
                        "t.codTpgDoc,t.nomTpgDoc)FROM AdmTpgDoc t WHERE t.estTpgDoc = :ESTADO"),

        @NamedQuery(name = "AdmTpgDoc.countTiposDocExistByIdInCcd",
                query = "SELECT COUNT(aso.ideRelSst) FROM AdmTpgDoc a " +
                        "inner join AdmSerSubserTpg aso ON aso.ideTpgDoc.ideTpgDoc = a.ideTpgDoc " +
                        "inner join AdmCcd c ON aso.ideSerie.ideSerie = c.ideSerie.ideSerie " +
                        "LEFT OUTER JOIN AdmCcd cc on  aso.ideSubserie.ideSubserie = c.ideSubserie.ideSubserie " +
                        "WHERE a.ideTpgDoc = :ID_TPG_DOC " +
                        "GROUP BY aso.ideRelSst, aso.ideSerie.ideSerie, aso.ideSubserie.ideSubserie"),
        
        @NamedQuery(name = "AdmTpgDoc.findById",
              query = "SELECT NEW co.com.foundation.soaint.documentmanager.persistence.entity.AdmTpgDoc(t.ideTpgDoc, t.carAdministrativa," +
                        "t.carLegal, t.carTecnica, t.codTpgDoc, t.estTpgDoc, t.nomTpgDoc, t.notAlcance, t.fueBibliografica," +
                        "s.ideSoporte , s.nomSoporte, d.ideTradDoc, d.nomTradDoc ) FROM AdmTpgDoc t INNER JOIN t.ideSoporte s " +
                        "INNER  JOIN  t.ideTraDocumental d WHERE t.ideTpgDoc = :IDTIPO")

})

@javax.persistence.TableGenerator(name = "ADM_TPGDOC_GENERATOR", table = "TABLE_GENERATOR", pkColumnName = "SEQ_NAME",
        valueColumnName = "SEQ_VALUE", pkColumnValue = "ADM_TPGDOC_SEQ", allocationSize = 1)
public class AdmTpgDoc implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "IDE_TPG_DOC")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ADM_TPGDOC_GENERATOR")
    private BigInteger ideTpgDoc;
    @Column(name = "CAR_ADMINISTRATIVA")
    private Long carAdministrativa;
    @Column(name = "CAR_LEGAL")
    private Long carLegal;
    @Column(name = "CAR_TECNICA")
    private Long carTecnica;
    @Column(name = "COD_TPG_DOC")
    private String codTpgDoc;
    @Basic(optional = false)
    @Column(name = "EST_TPG_DOC")
    private int estTpgDoc;
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
    @Column(name = "NOM_TPG_DOC")
    private String nomTpgDoc;
    @Column(name = "NOT_ALCANCE")
    private String notAlcance;
    @Column(name = "FUE_BIBLIOGRAFICA")
    private String fueBibliografica;
    @JoinColumn(name = "IDE_SOPORTE", referencedColumnName = "IDE_SOPORTE")
    @ManyToOne
    private AdmSoporte ideSoporte;
    @JoinColumn(name = "IDE_TRA_DOCUMENTAL", referencedColumnName = "IDE_TRAD_DOC")
    @ManyToOne
    private AdmTradDoc ideTraDocumental;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ideTpgDoc")
    private List<AdmSerSubserTpg> admSerSubserTpgList;

    public AdmTpgDoc() {
    }

    public AdmTpgDoc(BigInteger ideTpgDoc) {
        this.ideTpgDoc = ideTpgDoc;
    }


    //[ AdmTpgDoc.findAll  ]
    public AdmTpgDoc(BigInteger ideTpgDoc, Long carAdministrativa, Long carLegal, Long carTecnica, String codTpgDoc,
                     int estTpgDoc, String nomTpgDoc, String notAlcance, String fueBibliografica, BigInteger idSoporte,
                     String nomSoporte, BigInteger idTraDocumental, String nomTraDocumental) {
        this.ideTpgDoc = ideTpgDoc;
        this.carAdministrativa = carAdministrativa;
        this.carLegal = carLegal;
        this.carTecnica = carTecnica;
        this.codTpgDoc = codTpgDoc;
        this.estTpgDoc = estTpgDoc;
        this.nomTpgDoc = nomTpgDoc;
        this.notAlcance = notAlcance;
        this.fueBibliografica = fueBibliografica;
        this.ideSoporte = new AdmSoporte(idSoporte, nomSoporte);
        this.ideTraDocumental = new AdmTradDoc(idTraDocumental, nomTraDocumental);
    }

    //[ EntityAdmTpgDocBuilder ]
    public AdmTpgDoc(BigInteger ideTpgDoc, Long carAdministrativa, AdmTradDoc ideTraDocumental, AdmSoporte ideSoporte, String notAlcance,
                     String fueBibliografica, String nomTpgDoc, Integer nivLectura, Integer nivEscritura, String ideUuid,
                     BigInteger ideUsuarioCambio, Date fecCreacion, Date fecCambio, int estTpgDoc, String codTpgDoc,
                     Long carTecnica, Long carLegal) {
        this.ideTpgDoc =ideTpgDoc;
        this.carAdministrativa = carAdministrativa;
        this.ideTraDocumental = ideTraDocumental;
        this.ideSoporte = ideSoporte;
        this.notAlcance = notAlcance;
        this.fueBibliografica = fueBibliografica;
        this.nomTpgDoc = nomTpgDoc;
        this.nivLectura = nivLectura;
        this.nivEscritura = nivEscritura;
        this.ideUuid = ideUuid;
        this.ideUsuarioCambio = ideUsuarioCambio;
        this.fecCreacion = fecCreacion;
        this.fecCambio = fecCambio;
        this.estTpgDoc = estTpgDoc;
        this.codTpgDoc = codTpgDoc;
        this.carTecnica = carTecnica;
        this.carLegal = carLegal;
    }

    public AdmTpgDoc(BigInteger ideTpgDoc, String codTpgDoc, String nomTpgDoc) {
        this.ideTpgDoc = ideTpgDoc;
        this.codTpgDoc = codTpgDoc;
        this.nomTpgDoc = nomTpgDoc;
    }
    
     public AdmTpgDoc(BigInteger ideTpgDoc, String nomTpgDoc) {
        this.ideTpgDoc = ideTpgDoc;
        this.nomTpgDoc = nomTpgDoc;
    }
    
    public BigInteger getIdeTpgDoc() {
        return ideTpgDoc;
    }

    public void setIdeTpgDoc(BigInteger ideTpgDoc) {
        this.ideTpgDoc = ideTpgDoc;
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

    public String getCodTpgDoc() {
        return codTpgDoc;
    }

    public void setCodTpgDoc(String codTpgDoc) {
        this.codTpgDoc = codTpgDoc;
    }

    public int getEstTpgDoc() {
        return estTpgDoc;
    }

    public void setEstTpgDoc(int estTpgDoc) {
        this.estTpgDoc = estTpgDoc;
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

    public String getNomTpgDoc() {
        return nomTpgDoc;
    }

    public void setNomTpgDoc(String nomTpgDoc) {
        this.nomTpgDoc = nomTpgDoc;
    }

    public String getNotAlcance() {
        return notAlcance;
    }

    public void setNotAlcance(String notAlcance) {
        this.notAlcance = notAlcance;
    }

    public String getFueBibliografica() {
        return fueBibliografica;
    }

    public void setFueBibliografica(String fueBibliografica) {
        this.fueBibliografica = fueBibliografica;
    }

    public AdmSoporte getIdeSoporte() {
        return ideSoporte;
    }

    public void setIdeSoporte(AdmSoporte ideSoporte) {
        this.ideSoporte = ideSoporte;
    }

    public AdmTradDoc getIdeTraDocumental() {
        return ideTraDocumental;
    }

    public void setIdeTraDocumental(AdmTradDoc ideTraDocumental) {
        this.ideTraDocumental = ideTraDocumental;
    }

    public List<AdmSerSubserTpg> getAdmSerSubserTpgList() {
        return admSerSubserTpgList;
    }

    public void setAdmSerSubserTpgList(List<AdmSerSubserTpg> admSerSubserTpgList) {
        this.admSerSubserTpgList = admSerSubserTpgList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ideTpgDoc != null ? ideTpgDoc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AdmTpgDoc)) {
            return false;
        }
        AdmTpgDoc other = (AdmTpgDoc) object;
        if ((this.ideTpgDoc == null && other.ideTpgDoc != null) || (this.ideTpgDoc != null && !this.ideTpgDoc.equals(other.ideTpgDoc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AdmTpgDoc[ ideTpgDoc=" + ideTpgDoc + " ]";
    }

}
