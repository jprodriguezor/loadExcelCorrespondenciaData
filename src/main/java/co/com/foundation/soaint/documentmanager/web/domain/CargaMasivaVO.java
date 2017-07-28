/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.foundation.soaint.documentmanager.web.domain;

import co.com.foundation.soaint.documentmanager.persistence.entity.constants.CargaMasivaStatus;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class CargaMasivaVO {
    
    private Long id;
    private String nombre;
    private Date fechaCreacion;
    private int totalRegistros;
    private int totalRegistrosExitosos;
    private int totalRegistrosError;
    private String estado;
    private String fechaText;

    public CargaMasivaVO() {
    }

    public CargaMasivaVO(Long id, String nombre, Date fechaCreacion, int totalRegistros, int totalRegistrosExitosos,
                         int totalRegistrosError, String estado, String  fechaText) {
        this.id = id;
        this.nombre = nombre;
        this.fechaCreacion = fechaCreacion;
        this.totalRegistros = totalRegistros;
        this.totalRegistrosExitosos = totalRegistrosExitosos;
        this.totalRegistrosError = totalRegistrosError;
        this.estado = estado;
        this.fechaText=fechaText;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public int getTotalRegistros() {
        return totalRegistros;
    }

    public int getTotalRegistrosExitosos() {
        return totalRegistrosExitosos;
    }

    public int getTotalRegistrosError() {
        return totalRegistrosError;
    }

    public String getEstado() {
        return estado;
    }

    public String getFechaText() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        fechaText= simpleDateFormat.format(fechaCreacion);
        return fechaText;
    }

    @Override
    public String toString() {
        return "CargaMasivaVO{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", fechaCreacion=" + fechaCreacion +
                ", totalRegistros=" + totalRegistros +
                ", totalRegistrosExitosos=" + totalRegistrosExitosos +
                ", totalRegistrosError=" + totalRegistrosError +
                ", estado='" + estado + '\'' +
                ", fechaText='" + fechaText + '\'' +
                '}';
    }


}
