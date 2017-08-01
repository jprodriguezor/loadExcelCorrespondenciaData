package co.com.foundation.soaint.documentmanager.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * Created by ADMIN on 05/12/2016.
 */
public class CcdItemVO {

    private String ideUniAmt;
    private Long ideOrgaAdmin;
    private String nombreideUniAmt;

    private String ideOfcProd;
    private String nombreideOfcProd;
    private Date fecCreacion;

    public CcdItemVO(String ideUniAmt, Long ideOrgaAdmin, String nombreideUniAmt) {
        this.ideUniAmt = ideUniAmt;
        this.ideOrgaAdmin =ideOrgaAdmin;
        this.nombreideUniAmt = nombreideUniAmt;
    }

    public CcdItemVO(String ideOfcProd, String nombreideOfcProd) {
        this.ideOfcProd = ideOfcProd;
        this.nombreideOfcProd = nombreideOfcProd;

    }

    public String getIdeUniAmt() {
        return ideUniAmt;
    }

    public void setIdeUniAmt(String ideUniAmt) {
        this.ideUniAmt = ideUniAmt;
    }

    public String getNombreideUniAmt() {
        return nombreideUniAmt;
    }

    public void setNombreideUniAmt(String nombreideUniAmt) {
        this.nombreideUniAmt = nombreideUniAmt;
    }

    public String getIdeOfcProd() {
        return ideOfcProd;
    }

    public void setIdeOfcProd(String ideOfcProd) {
        this.ideOfcProd = ideOfcProd;
    }

    public String getNombreideOfcProd() {
        return nombreideOfcProd;
    }

    public void setNombreideOfcProd(String nombreideOfcProd) {
        this.nombreideOfcProd = nombreideOfcProd;
    }

    public Date getFecCreacion() {
        return fecCreacion;
    }

    public void setFecCreacion(Date fecCreacion) {
        this.fecCreacion = fecCreacion;
    }

    public Long getIdeOrgaAdmin() {
        return ideOrgaAdmin;
    }

    public void setIdeOrgaAdmin(Long ideOrgaAdmin) {
        this.ideOrgaAdmin = ideOrgaAdmin;
    }

    @Override
    public String toString() {
        return "CcdItemVO{" +
                "ideUniAmt='" + ideUniAmt + '\'' +
                ", ideOrgaAdmin=" + ideOrgaAdmin +
                ", nombreideUniAmt='" + nombreideUniAmt + '\'' +
                ", ideOfcProd='" + ideOfcProd + '\'' +
                ", nombreideOfcProd='" + nombreideOfcProd + '\'' +
                ", fecCreacion=" + fecCreacion +
                '}';
    }
}
