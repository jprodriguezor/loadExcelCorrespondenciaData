/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soaint.massive.loader.web.domain;

import java.math.BigInteger;
import java.util.Date;

/**
 *
 * @author DIEGO SANCHEZ
 */
public class MotivosDocVO {
    
    private BigInteger ideMotCreacion;
    private Date fecCambio;
    private Date fecCreacion;
    private BigInteger ideUsuarioCambio;
    private String ideUuid;
    private Integer nivEscritura;
    private Integer nivLectura;
    private String nomMotCreacion;

    public MotivosDocVO() {
    }

    public MotivosDocVO(BigInteger ideMotCreacion, Date fecCambio, Date fecCreacion, BigInteger ideUsuarioCambio, String ideUuid, Integer nivEscritura, Integer nivLectura, String nomMotCreacion) {
        this.ideMotCreacion = ideMotCreacion;
        this.fecCambio = fecCambio;
        this.fecCreacion = fecCreacion;
        this.ideUsuarioCambio = ideUsuarioCambio;
        this.ideUuid = ideUuid;
        this.nivEscritura = nivEscritura;
        this.nivLectura = nivLectura;
        this.nomMotCreacion = nomMotCreacion;
    }

    
    
    public BigInteger getIdeMotCreacion() {
        return ideMotCreacion;
    }

    public Date getFecCambio() {
        return fecCambio;
    }

    public Date getFecCreacion() {
        return fecCreacion;
    }

    public BigInteger getIdeUsuarioCambio() {
        return ideUsuarioCambio;
    }

    public String getIdeUuid() {
        return ideUuid;
    }

    public Integer getNivEscritura() {
        return nivEscritura;
    }

    public Integer getNivLectura() {
        return nivLectura;
    }

    public String getNomMotCreacion() {
        return nomMotCreacion;
    }

    
    

  @Override
    public String toString() {
        return "MotivosDocumentalesVO{" +
                "ideMotCreacion=" + ideMotCreacion +
                ", fecCambio='" + fecCambio + '\'' +
                ", fecCreacion='" + fecCreacion + '\'' +                
                ", ideUsuarioCambio='" + ideUsuarioCambio + '\'' +                
                ", ideUuid='" + ideUuid + '\'' +
                ", nivEscritura='" + nivEscritura + '\'' +
                ", nivLectura='" + nivLectura + '\'' +            
                ", nomMotCreacion='" + nomMotCreacion + '\'' +
                '}';
    }

}
