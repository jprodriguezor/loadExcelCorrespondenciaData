package co.com.foundation.soaint.documentmanager.infrastructure.builder.entity;

import co.com.foundation.soaint.documentmanager.persistence.entity.AdmTradDoc;
import co.com.foundation.soaint.infrastructure.builder.Builder;

import java.math.BigInteger;
import java.util.Date;

/**
 * Created by jrodriguez on 23/09/2016.
 */
public class EntityAdmTradDocBuilder implements Builder<AdmTradDoc>{

    private BigInteger ideTradDoc;
    private String desTradDoc;
    private Date fecCambio;
    private Date fecCreacion;
    private String ideUuid;
    private Integer nivEscritura;
    private Integer nivLectura;
    private String nomTradDoc;
    
    private EntityAdmTradDocBuilder(){}

    public static EntityAdmTradDocBuilder newBuilder() {return new EntityAdmTradDocBuilder();}

    public EntityAdmTradDocBuilder withIdeTradDoc(final BigInteger ideTradDoc) {
        this.ideTradDoc = ideTradDoc;
        return  this;
    }

    public EntityAdmTradDocBuilder withDesTradDoc(final String desTradDoc) {
        this.desTradDoc = desTradDoc;
        return  this;
    }

    public EntityAdmTradDocBuilder withFecCreacion(final Date fecCreacion) {
        this.fecCreacion = fecCreacion;
        return  this;
    }

    public EntityAdmTradDocBuilder withFecCambio(final Date fecCambio) {
        this.fecCambio = fecCambio;
        return  this;
    }

    public EntityAdmTradDocBuilder withIdeUuid(final String ideUuid) {
        this.ideUuid = ideUuid;
        return  this;
    }

    public EntityAdmTradDocBuilder withNivEscritura(final Integer nivEscritura) {
        this.nivEscritura = nivEscritura;
        return  this;
    }

    public EntityAdmTradDocBuilder withNivLectura(final Integer nivLectura) {
        this.nivLectura = nivLectura;
        return  this;
    }

    public EntityAdmTradDocBuilder withNomTradDoc(final String nomTradDoc) {
        this.nomTradDoc = nomTradDoc;
        return  this;
    }

    public AdmTradDoc build(){
        return  new AdmTradDoc(ideTradDoc,desTradDoc,nomTradDoc);
    }
}
