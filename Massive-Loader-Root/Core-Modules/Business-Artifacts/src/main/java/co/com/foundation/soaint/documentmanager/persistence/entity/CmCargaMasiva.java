package co.com.foundation.soaint.documentmanager.persistence.entity;

import co.com.foundation.soaint.documentmanager.persistence.entity.constants.CargaMasivaStatus;

import javax.persistence.*;
import javax.persistence.TableGenerator;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Created by administrador_1 on 02/10/2016.
 */

@Entity
@Table(name = "CM_CARGA_MASIVA")
@NamedQueries({
    @NamedQuery(name= "CmCargaMasiva.findAll",
            query = "Select NEW co.com.foundation.soaint.documentmanager.persistence.entity.CmCargaMasiva("
                  + "c.id, c.nombre, c.fechaCreacion, c.totalRegistros, c.totalRegistrosExitosos, c.totalRegistrosError, c.estado) "
                  + "From CmCargaMasiva c"),
        @NamedQuery(name= "CmCargaMasiva.obtenerDataEstadoCargaMasiva",
                query = "Select NEW co.com.foundation.soaint.documentmanager.persistence.entity.CmCargaMasiva("
                        + "c.id, c.nombre, c.fechaCreacion, c.totalRegistros, c.totalRegistrosExitosos, c.totalRegistrosError, c.estado) "
                        + "From CmCargaMasiva c order by c.id desc"),
        @NamedQuery(name= "CmCargaMasiva.obtenerDataEstadoCargaMasivabyID",
                query = "Select NEW co.com.foundation.soaint.documentmanager.persistence.entity.CmCargaMasiva("
                        + "c.id, c.nombre, c.fechaCreacion, c.totalRegistros, c.totalRegistrosExitosos, c.totalRegistrosError, c.estado) "
                        + "From CmCargaMasiva c where c.id =:ID_CARGA  order by c.id desc"),
        @NamedQuery(name= "CmCargaMasiva.obtenerDataListadoCargaMasiva",
                query = "Select NEW co.com.foundation.soaint.documentmanager.persistence.entity.CmCargaMasiva("
                        + "c.id, c.nombre) "
                        + "From CmCargaMasiva c")
    
})
@TableGenerator(name = "CM_CARGA_MASIVA_GENERATOR", table = "TABLE_GENERATOR", pkColumnName = "SEQ_NAME",
        valueColumnName = "SEQ_VALUE", pkColumnValue = "CM_CARGA_MASIVA_SEQ", allocationSize = 1)
public class CmCargaMasiva implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "CM_CARGA_MASIVA_GENERATOR")
    @Column(name = "ID")
    private Long id;

    @Column(name = "NOMBRE",nullable = false)
    private String nombre;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "FECHA_CREACION",nullable = false)
    private Date fechaCreacion;

    @Column(name = "TOTAL_REGISTROS",nullable = false)
    private int totalRegistros;

    @Column(name = "TOTAL_REGISTROS_EXITOSOS",nullable = false)
    private int totalRegistrosExitosos;

    @Column(name = "TOTAL_REGISTROS_ERROR",nullable = false)
    private int totalRegistrosError;

    @Enumerated(EnumType.STRING)
    @Column(name = "ESTADO", nullable = false)
    private CargaMasivaStatus estado;

    public CmCargaMasiva() {
    }

    public CmCargaMasiva(String nombre, Date fechaCreacion, int totalRegistros, int totalRegistrosExitosos, int totalRegistrosError, CargaMasivaStatus estado) {
        this.nombre = nombre;
        this.fechaCreacion = fechaCreacion;
        this.totalRegistros = totalRegistros;
        this.totalRegistrosExitosos = totalRegistrosExitosos;
        this.totalRegistrosError = totalRegistrosError;
        this.estado = estado;
    }

    public CmCargaMasiva(Long id, String nombre, Date fechaCreacion, int totalRegistros, int totalRegistrosExitosos, int totalRegistrosError, CargaMasivaStatus estado) {
        this.id = id;
        this.nombre = nombre;
        this.totalRegistros = totalRegistros;
        this.fechaCreacion = fechaCreacion;
        this.totalRegistrosExitosos = totalRegistrosExitosos;
        this.totalRegistrosError = totalRegistrosError;
        this.estado = estado;
    }

    public CmCargaMasiva(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getTotalRegistrosExitosos() {
        return totalRegistrosExitosos;
    }

    public void setTotalRegistrosExitosos(int totalRegistrosExitosos) {
        this.totalRegistrosExitosos = totalRegistrosExitosos;
    }

    public int getTotalRegistrosError() {
        return totalRegistrosError;
    }

    public void setTotalRegistrosError(int totalRegistrosError) {
        this.totalRegistrosError = totalRegistrosError;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public int getTotalRegistros() {
        return totalRegistros;
    }

    public void setTotalRegistros(int totalRegistros) {
        this.totalRegistros = totalRegistros;
    }

    public CargaMasivaStatus getEstado() {
        return estado;
    }

    public void setEstado(CargaMasivaStatus estado) {
        this.estado = estado;
    }

    public void increaseTotalRegistrosExitosos(){
        totalRegistrosExitosos++;
    }

}
