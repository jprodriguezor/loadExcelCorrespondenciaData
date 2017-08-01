package co.com.foundation.soaint.documentmanager.web.domain;

/**
 * Created by jrodriguez on 26/11/2016.
 */
public class TrdVo {

    private String codUniAmt;
    private String codOfcProd;

    public TrdVo(){}

    public TrdVo(String codUniAmt, String codOfcProd) {
        this.codUniAmt = codUniAmt;
        this.codOfcProd = codOfcProd;
    }

    public String getCodOfcProd() {
        return codOfcProd;
    }

    public void setCodOfcProd(String codOfcProd) {
        this.codOfcProd = codOfcProd;
    }

    public String getCodUniAmt() {
        return codUniAmt;
    }

    public void setCodUniAmt(String codUniAmt) {
        this.codUniAmt = codUniAmt;
    }

    @Override
    public String toString() {
        return "TrdVo{" +
                "codUniAmt='" + codUniAmt + '\'' +
                ", codOfcProd='" + codOfcProd + '\'' +
                '}';
    }
}


