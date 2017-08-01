package co.com.foundation.soaint.documentmanager.persistence.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;

/**
 * @author jrodriguez
 */
@Entity
@Table(name = "ADM_CONFIG_INSTRUMENTO")
@NamedQueries({
        @NamedQuery(name = "AdmConfigInstrumento.findByStatus",
                    query = "SELECT NEW co.com.foundation.soaint.documentmanager.persistence.entity.AdmConfigInstrumento" +
                        "(a.estInstrumento)FROM AdmConfigInstrumento a WHERE a.ideInstrumento = :INSTRUMENTO"),

        @NamedQuery(name = "AdmConfigInstrumento.findAll",
                query = "SELECT NEW co.com.foundation.soaint.documentmanager.persistence.entity.AdmConfigInstrumento" +
                        "(a.ideInstrumento, a.estInstrumento)FROM AdmConfigInstrumento a WHERE a.ideInstrumento NOT IN(:INSTRUMENTO)")


})
public class AdmConfigInstrumento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "IDE_INSTRUMENTO")
    private String ideInstrumento;
    @Column(name = "EST_INSTRUMENTO")
    private int estInstrumento;

    public AdmConfigInstrumento() {
    }
    //[ -- AdmConfigInstrumento.findByStatus  --]
    public AdmConfigInstrumento(int estInstrumento) {
        this.estInstrumento = estInstrumento;
    }

    //[ -- AdmConfigInstrumento.findAll  --]
    public AdmConfigInstrumento(String ideInstrumento, int estInstrumento) {
        this.ideInstrumento = ideInstrumento;
        this.estInstrumento = estInstrumento;
    }

    public AdmConfigInstrumento(String ideInstrumento) {
        this.ideInstrumento = ideInstrumento;
    }

    public String getIdeInstrumento() {
        return ideInstrumento;
    }

    public void setIdeInstrumento(String ideInstrumento) {
        this.ideInstrumento = ideInstrumento;
    }

    public int getEstInstrumento() {
        return estInstrumento;
    }

    public void setEstInstrumento(int estInstrumento) {
        this.estInstrumento = estInstrumento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ideInstrumento != null ? ideInstrumento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AdmConfigInstrumento)) {
            return false;
        }
        AdmConfigInstrumento other = (AdmConfigInstrumento) object;
        if ((this.ideInstrumento == null && other.ideInstrumento != null) || (this.ideInstrumento != null && !this.ideInstrumento.equals(other.ideInstrumento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AdmConfigInstrumento{" +
                "ideInstrumento='" + ideInstrumento + '\'' +
                ", estInstrumento=" + estInstrumento +
                '}';
    }
}
