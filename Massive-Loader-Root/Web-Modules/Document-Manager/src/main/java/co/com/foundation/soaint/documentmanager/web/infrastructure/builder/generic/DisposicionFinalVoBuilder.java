/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.foundation.soaint.documentmanager.web.infrastructure.builder.generic;

import co.com.foundation.soaint.documentmanager.web.domain.DisposicionFinalVO;
import java.math.BigInteger;
import java.util.Date;
import co.com.foundation.soaint.infrastructure.builder.Builder;

/**
 *
 * @author ADMIN
 */
public class DisposicionFinalVoBuilder implements Builder<DisposicionFinalVO> {
    
    private BigInteger ideDisFinal;
    private String nomDisFinal;
    private String desDisFinal;
    private String staDisFinal;
    private Long ideUsuarioCambio;
    private Date fecCambio;
    private Date fecCreacion;
    private Integer nivLectura;
    private Integer nivEscritura;
    private String ideUuid;
    

    public DisposicionFinalVoBuilder() {
    }
    
    public static DisposicionFinalVoBuilder newBuilder() {
        return new DisposicionFinalVoBuilder();
    }

    public DisposicionFinalVoBuilder withIdeDisFinal(BigInteger ideDisFinal) {
        this.ideDisFinal = ideDisFinal;
        return this;
    }

    public DisposicionFinalVoBuilder withNomDisFinal(String nomDisFinal) {
        this.nomDisFinal = nomDisFinal;
        return this;
    }

    public DisposicionFinalVoBuilder withDesDisFinal(String desDisFinal) {
        this.desDisFinal = desDisFinal;
        return this;
    }

    public DisposicionFinalVoBuilder withStaDisFinal(String staDisFinal) {
        this.staDisFinal = staDisFinal;
        return this;
    }

    public DisposicionFinalVoBuilder withIdeUsuarioCambio(Long ideUsuarioCambio) {
        this.ideUsuarioCambio = ideUsuarioCambio;
        return this;
    }

    public DisposicionFinalVoBuilder withFecCambio(Date fecCambio) {
        this.fecCambio = fecCambio;
        return this;
    }

    public DisposicionFinalVoBuilder withNivLectura(Integer nivLectura) {
        this.nivLectura = nivLectura;
        return this;
    }

    public DisposicionFinalVoBuilder withNivEscritura(Integer nivEscritura) {
        this.nivEscritura = nivEscritura;
        return this;
    }

    public DisposicionFinalVoBuilder withIdeUuid(String ideUuid) {
        this.ideUuid = ideUuid;
        return this;
    }
    
    public DisposicionFinalVoBuilder withFecCreacion(Date fecCreacion) {
        this.fecCreacion = fecCreacion;
        return this;
    }
    
        public DisposicionFinalVO build() {
        return new DisposicionFinalVO(ideDisFinal, nomDisFinal, desDisFinal, staDisFinal, ideUsuarioCambio, fecCambio, nivLectura, nivEscritura, ideUuid, fecCreacion);
    }
    
}
