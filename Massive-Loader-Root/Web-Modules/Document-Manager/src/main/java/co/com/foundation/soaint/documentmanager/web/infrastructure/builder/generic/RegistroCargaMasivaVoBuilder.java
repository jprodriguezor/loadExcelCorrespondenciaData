/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.foundation.soaint.documentmanager.web.infrastructure.builder.generic;

import co.com.foundation.soaint.infrastructure.builder.Builder;
import co.com.foundation.soaint.documentmanager.web.domain.RegistroCargaMasivaVO;

/**
 *
 * @author ADMIN
 */
public class RegistroCargaMasivaVoBuilder implements Builder<RegistroCargaMasivaVO>{
    
    private Long id;
    private String contenido;
    private String mensajes;
    private String estado;

    public RegistroCargaMasivaVoBuilder() {
    }
    
      public static RegistroCargaMasivaVoBuilder newBuilder() {
        return new RegistroCargaMasivaVoBuilder();
    }

    public RegistroCargaMasivaVoBuilder withId(Long id) {
        this.id = id;
         return this;
    }

    public RegistroCargaMasivaVoBuilder withContenido(String contenido) {
        this.contenido = contenido;
         return this;
    }

    public RegistroCargaMasivaVoBuilder withMensajes(String mensajes) {
        this.mensajes = mensajes;
         return this;
    }

    public RegistroCargaMasivaVoBuilder withEstado(String estado) {
        this.estado = estado;
         return this;
    }
      
   public RegistroCargaMasivaVO build() { 
        return new RegistroCargaMasivaVO(id, contenido, mensajes, estado );
    
      }
}
