/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.foundation.soaint.documentmanager.web.infrastructure.builder.generic;

import co.com.foundation.soaint.infrastructure.builder.Builder;
import co.com.foundation.soaint.documentmanager.web.domain.CargaMasivaVO;
import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class CargaMasivaVoBuilder implements Builder<CargaMasivaVO>{
    
    private Long id;
    private String nombre;
    private Date fechaCreacion;
    private int totalRegistros;
    private int totalRegistrosExitosos;
    private int totalRegistrosError;
    private String estado;
    private String fechaText;

    private CargaMasivaVoBuilder() {
    }
    
    
     public static CargaMasivaVoBuilder newBuilder() {
        return new CargaMasivaVoBuilder();
    }

    public CargaMasivaVoBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public CargaMasivaVoBuilder withNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public CargaMasivaVoBuilder withFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
        return this;
    }

    public CargaMasivaVoBuilder withTotalRegistros(int totalRegistros) {
        this.totalRegistros = totalRegistros;
        return this;
    }

    public CargaMasivaVoBuilder withTotalRegistrosExitosos(int totalRegistrosExitosos) {
        this.totalRegistrosExitosos = totalRegistrosExitosos;
        return this;
    }

    public CargaMasivaVoBuilder withTotalRegistrosError(int totalRegistrosError) {
        this.totalRegistrosError = totalRegistrosError;
        return this;
    }

    public CargaMasivaVoBuilder withEstado(String estado) {
        this.estado = estado;
        return this;
    }

    public CargaMasivaVoBuilder withFechaText(String fechaText) {
        this.fechaText = fechaText;
        return this;
    }

    public CargaMasivaVO build() {
         return new CargaMasivaVO(id, nombre,fechaCreacion, totalRegistros,totalRegistrosExitosos,totalRegistrosError, estado, fechaText);
    
     }
    
}
