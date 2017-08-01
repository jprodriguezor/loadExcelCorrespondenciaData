package co.com.foundation.soaint.documentmanager.persistence.entity;

/**
 * Created by jrodriguez on 24/11/2016.
 */


import javax.persistence.*;
import javax.persistence.TableGenerator;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

/**
 * @author jrodriguez
 */
@Entity


@Table(name = "ADM_VERSION_TRD")
@NamedQueries({
        @NamedQuery(name = "AdmVersionTrd.consulVersionActualPorOfcProd",
                query = "SELECT MAX (v.numVersion) FROM AdmVersionTrd v WHERE v.ideUniAmt =:ID_UNI_AMT AND v.ideOfcProd =:ID_OFC_PROD "),

        @NamedQuery(name = "AdmVersionTrd.findAllVersionPorOfcProd",
                query = "SELECT NEW co.com.foundation.soaint.documentmanager.persistence.entity.AdmVersionTrd(v.ideVersion, " +
                        "v.valVersion, v.fecVersion ) FROM AdmVersionTrd v WHERE v.ideOfcProd =:ID_OFC_PROD ORDER BY v.ideVersion DESC "),

        @NamedQuery(name = "AdmVersionTrd.consulVersionOfcProdTop",
                query = "SELECT NEW co.com.foundation.soaint.documentmanager.persistence.entity.AdmVersionTrd(v.ideVersion, " +
                        "v.valVersion, v.fecVersion ) FROM AdmVersionTrd v WHERE v.ideOfcProd =:ID_OFC_PROD AND v.valVersion = 'TOP' "),

        @NamedQuery(name = "AdmVersionTrd.consultVersionPorOfcProd",
                query = "SELECT NEW co.com.foundation.soaint.documentmanager.persistence.entity.AdmVersionTrd(v.numVersion, " +
                        " v.fecVersion ) FROM AdmVersionTrd v WHERE v.ideOfcProd =:ID_OFC_PROD AND v.ideVersion =:ID_VERSION "),

        @NamedQuery(name = "AdmVersionTrd.updateVersion",
                query = "UPDATE AdmVersionTrd v SET v.valVersion =:VAL_VERSION  WHERE v.ideUniAmt =:ID_UNI_AMT " +
                        "AND v.ideOfcProd =:ID_OFC_PROD AND v.numVersion =:NUM_VERSION ")

})

@TableGenerator(name = "ADM_VERSION_TRD_GENERATOR", table = "TABLE_GENERATOR", pkColumnName = "SEQ_NAME",
        valueColumnName = "SEQ_VALUE", pkColumnValue = "ADM_VERSION_TRD_SEQ", allocationSize = 1)
public class AdmVersionTrd implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "IDE_VERSION")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ADM_VERSION_TRD_GENERATOR")
    private BigInteger ideVersion;

    @Column(name = "NUM_VERSION")
    private BigInteger numVersion;

    @Column(name = "VAL_VERSION")
    private String valVersion;

    @Column(name = "FEC_VERSION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecVersion;

    @Column(name = "IDE_USUARIO")
    private BigInteger ideUsuario;

    @Basic(optional = false)
    @Column(name = "IDE_OFC_PROD")
    private String ideOfcProd;

    @Basic(optional = false)
    @Column(name = "IDE_UNI_AMT")
    private String ideUniAmt;


    public AdmVersionTrd() {
    }

    //[ AdmVersionTrd.findAllVersionPorOfcProd s]
    public AdmVersionTrd(BigInteger ideVersion,String valVersion, Date fecVersion) {
        this.ideVersion =ideVersion;
        this.valVersion = valVersion;
        this.fecVersion = fecVersion;
    }

    public AdmVersionTrd(BigInteger numVersion, Date fecVersion) {
        this.numVersion = numVersion;
        this.fecVersion = fecVersion;
    }

    public BigInteger getIdeVersion() {
        return ideVersion;
    }

    public void setIdeVersion(BigInteger ideVersion) {
        this.ideVersion = ideVersion;
    }

    public BigInteger getNumVersion() {
        return numVersion;
    }

    public void setNumVersion(BigInteger numVersion) {
        this.numVersion = numVersion;
    }

    public String getValVersion() {
        return valVersion;
    }

    public void setValVersion(String valVersion) {
        this.valVersion = valVersion;
    }

    public Date getFecVersion() {
        return fecVersion;
    }

    public void setFecVersion(Date fecVersion) {
        this.fecVersion = fecVersion;
    }

    public BigInteger getIdeUsuario() {
        return ideUsuario;
    }

    public void setIdeUsuario(BigInteger ideUsuario) {
        this.ideUsuario = ideUsuario;
    }

    public String getIdeOfcProd() {
        return ideOfcProd;
    }

    public void setIdeOfcProd(String ideOfcProd) {
        this.ideOfcProd = ideOfcProd;
    }

    public String getIdeUniAmt() {
        return ideUniAmt;
    }

    public void setIdeUniAmt(String ideUniAmt) {
        this.ideUniAmt = ideUniAmt;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ideVersion != null ? ideVersion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AdmVersionTrd)) {
            return false;
        }
        AdmVersionTrd other = (AdmVersionTrd) object;
        if ((this.ideVersion == null && other.ideVersion != null) || (this.ideVersion != null && !this.ideVersion.equals(other.ideVersion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.foundation.soaint.documentmanager.persistence.entity.AdmVersionTrd[ ideVersion=" + ideVersion + " ]";
    }

}
