package co.com.foundation.soaint.documentmanager.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ADMIN on 30/11/2016.
 */
public class CCDForVersionVO {
    private Date fechaVersion;
    private String valVersion;
    private List<CCDContenidoVO> ContenidoCcd;


    public CCDForVersionVO() {
        this.ContenidoCcd = new ArrayList<>();
    }


    public Date getFechaVersion() {
        return fechaVersion;
    }

    public void setFechaVersion(Date fechaVersion) {
        this.fechaVersion = fechaVersion;
    }

    public String getValVersion() {
        return valVersion;
    }

    public void setValVersion(String valVersion) {
        this.valVersion = valVersion;
    }

    public List<CCDContenidoVO> getDataCcd() {
        return ContenidoCcd;
    }

    public void setDataCcd(CCDContenidoVO ccdtcvo) {
        this.ContenidoCcd.add(ccdtcvo);
    }

    @Override
    public String toString() {
        return "CCDForVersionVO{" + "fechaVersion=" + fechaVersion + ", valVersion=" + valVersion + ", ContenidoCcd=" + ContenidoCcd + '}';
    }

    
}
