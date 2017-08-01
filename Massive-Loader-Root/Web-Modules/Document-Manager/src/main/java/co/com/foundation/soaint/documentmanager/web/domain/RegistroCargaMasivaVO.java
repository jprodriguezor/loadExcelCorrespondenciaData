/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.foundation.soaint.documentmanager.web.domain;

/**
 *
 * @author ADMIN
 */
public class RegistroCargaMasivaVO {

    private Long id;
    private String contenido;
    private String mensajes;
    private String estado;

    public RegistroCargaMasivaVO() {
    }

    public RegistroCargaMasivaVO(Long id, String contenido, String mensajes, String estado) {
        this.id = id;
        this.contenido = contenido;
        this.mensajes = mensajes;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getMensajes() {
        return mensajes;
    }

    public void setMensajes(String mensajes) {
        this.mensajes = mensajes;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "CmCargaMasivaVO{"
                + "id=" + id
                + ", contenido='" + contenido + '\''
                + ", mensajes='" + mensajes + '\''
                + ", nomMotCreacion='" + estado + '\''
                + '}';
    }

}
