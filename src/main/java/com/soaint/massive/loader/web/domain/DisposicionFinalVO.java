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
 * @author ADMIN
 */
public class DisposicionFinalVO {
    
    private BigInteger ideDisFinal;
    private String nomDisFinal;
    private String desDisFinal;
    private String staDisFinal;
    private Long ideUsuarioCambio;
    private Date fecCambio;
    private Integer nivLectura;
    private Integer nivEscritura;
    private String ideUuid;
    private Date fecCreacion;

    public DisposicionFinalVO() {
    }

    public DisposicionFinalVO(BigInteger ideDisFinal, String nomDisFinal, String desDisFinal, String staDisFinal, Long ideUsuarioCambio, Date fecCambio, Integer nivLectura, Integer nivEscritura, String ideUuid, Date fecCreacion) {
        this.ideDisFinal = ideDisFinal;
        this.nomDisFinal = nomDisFinal;
        this.desDisFinal = desDisFinal;
        this.staDisFinal = staDisFinal;
        this.ideUsuarioCambio = ideUsuarioCambio;
        this.fecCambio = fecCambio;
        this.nivLectura = nivLectura;
        this.nivEscritura = nivEscritura;
        this.ideUuid = ideUuid;
        this.fecCreacion =  fecCreacion;
    }



    public BigInteger getIdeDisFinal() {
        return ideDisFinal;
    }

    public String getNomDisFinal() {
        return nomDisFinal;
    }

    public String getDesDisFinal() {
        return desDisFinal;
    }

    public String getStaDisFinal() {
        return staDisFinal;
    }

    public Long getIdeUsuarioCambio() {
        return ideUsuarioCambio;
    }

    public Date getFecCambio() {
        return fecCambio;
    }

    public Integer getNivLectura() {
        return nivLectura;
    }

    public Integer getNivEscritura() {
        return nivEscritura;
    }

    public String getIdeUuid() {
        return ideUuid;
    }

    public Date getFecCreacion() {
        return fecCreacion;
    }
    
    
    
      @Override
    public String toString() {
        return "DispisicionFinalVO{" +
                "ideDisFinal=" + ideDisFinal +
                ", nomDisFinal='" + nomDisFinal + '\'' +
                ", desDisFinal='" + desDisFinal + '\'' +                
                ", staDisFinal='" + staDisFinal + '\'' +                
                ", ideUsuarioCambio='" + ideUsuarioCambio + '\'' +
                ", fecCambio='" + fecCambio + '\'' +
                ", nivLectura='" + nivLectura + '\'' +         
                ", nivEscritura='" + nivEscritura + '\'' +    
                ", ideUuid='" + ideUuid + '\'' +
                ", fecCreacion='" + fecCreacion + '\'' +
                '}';
    }
    
}
