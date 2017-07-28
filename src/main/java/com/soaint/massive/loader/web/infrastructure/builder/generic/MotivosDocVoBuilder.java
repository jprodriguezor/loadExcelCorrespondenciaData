/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soaint.massive.loader.web.infrastructure.builder.generic;

import com.soaint.massive.loader.web.domain.MotivosDocVO;
import java.math.BigInteger;
import java.util.Date;
import co.com.foundation.soaint.infrastructure.builder.Builder;

/**
 *
 * @author ADMIN
 */
public class MotivosDocVoBuilder implements Builder<MotivosDocVO>{
    
    private BigInteger ideMotCreacion;
    private Date fecCambio;
    private Date fecCreacion;
    private BigInteger ideUsuarioCambio;
    private String ideUuid;
    private Integer nivEscritura;
    private Integer nivLectura;
    private String nomMotCreacion;

    private MotivosDocVoBuilder() {
    }
    
    public static MotivosDocVoBuilder newBuilder() {
        return new MotivosDocVoBuilder();
    }

    public MotivosDocVoBuilder withIdeMotCreacion(BigInteger ideMotCreacion) {
        this.ideMotCreacion = ideMotCreacion;
    return this;
    }

    public MotivosDocVoBuilder withFecCambio(Date fecCambio) {
        this.fecCambio = fecCambio;
        return this;
    }

    public MotivosDocVoBuilder withFecCreacion(Date fecCreacion) {
        this.fecCreacion = fecCreacion;
        return this;
    }

    public MotivosDocVoBuilder withIdeUsuarioCambio(BigInteger ideUsuarioCambio) {
        this.ideUsuarioCambio = ideUsuarioCambio;
        return this;
    }

    public MotivosDocVoBuilder withIdeUuid(String ideUuid) {
        this.ideUuid = ideUuid;
        return this;
    }

    public MotivosDocVoBuilder withNivEscritura(Integer nivEscritura) {
        this.nivEscritura = nivEscritura;
        return this;
    }

    public MotivosDocVoBuilder withNivLectura(Integer nivLectura) {
        this.nivLectura = nivLectura;
        return this;
    }

    public MotivosDocVoBuilder withNomMotCreacion(String nomMotCreacion) {
        this.nomMotCreacion = nomMotCreacion;
        return this;
    }
    
    public MotivosDocVO build() {
        return new MotivosDocVO(ideMotCreacion, fecCambio, fecCreacion, 
                ideUsuarioCambio, ideUuid, nivEscritura, nivLectura,  
                nomMotCreacion);
    }

}
