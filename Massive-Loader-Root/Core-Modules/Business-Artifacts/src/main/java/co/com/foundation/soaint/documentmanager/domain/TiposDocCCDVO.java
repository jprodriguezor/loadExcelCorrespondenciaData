package co.com.foundation.soaint.documentmanager.domain;

/**
 * Created by ADMIN on 30/11/2016.
 */
public class TiposDocCCDVO {

    private String tipologiaDocumental;

    public TiposDocCCDVO() {
    }

    public TiposDocCCDVO(String tipologiaDocumental) {
        this.tipologiaDocumental = tipologiaDocumental;
    }

    public String getTipologiaDocumental() {
        return tipologiaDocumental;
    }

    public void setTipologiaDocumental(String tipologiaDocumental) {
        this.tipologiaDocumental = tipologiaDocumental;
    }

    @Override
    public String toString() {
        return "TiposDocCCDVO{" + "tipologiaDocumental=" + tipologiaDocumental + '}';
    }

    
}
