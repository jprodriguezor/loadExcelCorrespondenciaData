package co.com.foundation.soaint.documentmanager.infrastructure.builder.entity;

import co.com.foundation.soaint.documentmanager.persistence.entity.AdmMotCreacion;
import co.com.foundation.soaint.infrastructure.builder.Builder;

import java.math.BigInteger;
import java.util.Date;

/**
 * Created by jrodriguez on 13/09/2016.
 */
public class EntityAdmMotCreacionBuilder implements Builder<AdmMotCreacion> {

    private BigInteger ideMotCreacion;
    private Date fecCambio;
    private Date fecCreacion;
    private BigInteger ideUsuarioCambio;
    private String ideUuid;
    private Integer nivEscritura;
    private Integer nivLectura;
    private String nomMotCreacion;

    private EntityAdmMotCreacionBuilder() {
    }

    public static EntityAdmMotCreacionBuilder newBuilder() {
        return new EntityAdmMotCreacionBuilder();

    }

    public EntityAdmMotCreacionBuilder withIdeMotCreacion(final BigInteger ideMotCreacion) {
        this.ideMotCreacion = ideMotCreacion;
        return this;
    }

    public EntityAdmMotCreacionBuilder withFecCambio(final Date fecCambio) {
        this.fecCambio = fecCambio;
        return this;
    }

    public EntityAdmMotCreacionBuilder withFecCreacion(final Date fecCreacion) {
        this.fecCreacion = fecCreacion;
        return this;
    }

    public EntityAdmMotCreacionBuilder withIdeUsuarioCambio(final BigInteger ideUsuarioCambio) {
        this.ideUsuarioCambio = ideUsuarioCambio;
        return this;
    }

    public EntityAdmMotCreacionBuilder withIdeUuid(final String ideUuid) {
        this.ideUuid = ideUuid;
        return this;
    }

    public EntityAdmMotCreacionBuilder withNivEscritura(final Integer nivEscritura) {
        this.nivEscritura = nivEscritura;
        return this;
    }

    public EntityAdmMotCreacionBuilder withNivLectura(final Integer nivLectura) {
        this.nivLectura = nivLectura;
        return this;
    }

    public EntityAdmMotCreacionBuilder withNomMotCreacion(final String nomMotCreacion) {
        this.nomMotCreacion = nomMotCreacion;
        return this;
    }

    public AdmMotCreacion build() {
        return new AdmMotCreacion(ideMotCreacion, fecCambio, fecCreacion, ideUsuarioCambio, ideUuid, nivEscritura, nivLectura, nomMotCreacion);
    }
}
