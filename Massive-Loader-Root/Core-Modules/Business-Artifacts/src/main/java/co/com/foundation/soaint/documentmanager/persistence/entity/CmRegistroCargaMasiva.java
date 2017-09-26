package co.com.foundation.soaint.documentmanager.persistence.entity;

import co.com.foundation.soaint.documentmanager.persistence.entity.constants.RegistroCargaMasivaStatus;

import javax.persistence.*;
import javax.persistence.TableGenerator;
import java.io.Serializable;

/**
 * Created by administrador_1 on 02/10/2016.
 */

@Entity
@Table(name = "CM_REGISTRO_CARGA_MASIVA")
@NamedQueries({
                @NamedQuery(name= "CmRegistroCargaMasiva.findAll",
                        query = "Select NEW co.com.foundation.soaint.documentmanager.persistence.entity.CmRegistroCargaMasiva("
                                  + "c.id, c.contenido, c.mensajes, c.estado)"
                                  + "From CmRegistroCargaMasiva c where c.cargaMasiva.id =:ID_CARGA"),
                @NamedQuery(name= "CmRegistroCargaMasiva.findbyIDCarga",
                        query = "Select NEW co.com.foundation.soaint.documentmanager.persistence.entity.CmRegistroCargaMasiva("
                                + "c.id, c.contenido, c.mensajes, c.estado)"
                                + "From CmRegistroCargaMasiva c where c.cargaMasiva.id =:ID_CARGA"),
                @NamedQuery(name= "CmRegistroCargaMasiva.findbyEstado",
                        query = "Select NEW co.com.foundation.soaint.documentmanager.persistence.entity.CmRegistroCargaMasiva("
                                + "c.id, c.contenido, c.mensajes, c.estado)"
                                + "From CmRegistroCargaMasiva c where c.estado =:ESTADO and "
                                + "Not c.mensajes=:MENSAJE"),
                @NamedQuery(name= "CmRegistroCargaMasiva.updateEstadoRegistroCargaMasiva",
                        query = "Update CmRegistroCargaMasiva c SET"
                                        + " c.estado=:ESTADO"
                                        + " Where c.id=:ID")

})
@TableGenerator(name = "CM_REGISTRO_CARGA_MASIVA_GENERATOR", table = "TABLE_GENERATOR", pkColumnName = "SEQ_NAME",
        valueColumnName = "SEQ_VALUE", pkColumnValue = "CM_REGISTRO_CARGA_MASIVA_SEQ", allocationSize = 1)
public class CmRegistroCargaMasiva implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "CM_REGISTRO_CARGA_MASIVA_GENERATOR")
    @Column(name = "ID")
    private Long id;

    @JoinColumn(name = "CARGA_MASIVA_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private CmCargaMasiva cargaMasiva;

    @Column(name = "CONTENIDO",nullable = false)
    private String contenido;

    @Column(name = "MENSAJES",nullable = true)
    private String mensajes;

    @Enumerated(EnumType.STRING)
    @Column(name = "ESTADO", nullable = false)
    private RegistroCargaMasivaStatus estado;

    public CmRegistroCargaMasiva() {
    }

    public CmRegistroCargaMasiva(CmCargaMasiva cargaMasiva, String contenido, String mensajes, RegistroCargaMasivaStatus estado) {
        this.cargaMasiva = cargaMasiva;
        this.contenido = contenido;
        this.mensajes = mensajes;
        this.estado = estado;
    }

    public CmRegistroCargaMasiva(Long id, String contenido, String mensajes, RegistroCargaMasivaStatus estado) {
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

    public CmCargaMasiva getCargaMasiva() {
        return cargaMasiva;
    }

    public void setCargaMasiva(CmCargaMasiva cargaMasiva) {
        this.cargaMasiva = cargaMasiva;
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

    public RegistroCargaMasivaStatus getEstado() {
        return estado;
    }

    public void setEstado(RegistroCargaMasivaStatus estado) {
        this.estado = estado;
    }
}
