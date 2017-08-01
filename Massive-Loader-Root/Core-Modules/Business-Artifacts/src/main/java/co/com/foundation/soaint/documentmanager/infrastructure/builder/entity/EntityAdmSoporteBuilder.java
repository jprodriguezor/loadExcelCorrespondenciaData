package co.com.foundation.soaint.documentmanager.infrastructure.builder.entity;

import co.com.foundation.soaint.documentmanager.persistence.entity.AdmSoporte;
import co.com.foundation.soaint.infrastructure.builder.Builder;

import java.math.BigInteger;
import java.util.Date;

/**
 * Created by jrodriguez on 23/09/2016.
 */
public class EntityAdmSoporteBuilder implements Builder<AdmSoporte> {

    private BigInteger ideSoporte;
    private String desSoporte;
    private Date fecCambio;
    private Date fecCreacion;
    private BigInteger ideUsuarioCambio;
    private String ideUuid;
    private Integer nivEscritura;
    private Integer nivLectura;
    private String nomSoporte;

    private EntityAdmSoporteBuilder(){}

    public static EntityAdmSoporteBuilder newBuilder() {return new EntityAdmSoporteBuilder();}

    public EntityAdmSoporteBuilder withIdeSoporte(final BigInteger ideSoporte) {
        this.ideSoporte = ideSoporte;
        return this;
    }

    public EntityAdmSoporteBuilder withDesSoporte(final String desSoporte) {
        this.desSoporte = desSoporte;
        return this;
    }

    public EntityAdmSoporteBuilder withFecCambio(final Date fecCambio) {
        this.fecCambio = fecCambio;
        return this;
    }

    public EntityAdmSoporteBuilder withFecCreacion(final Date fecCreacion) {
        this.fecCreacion = fecCreacion;
        return this;
    }

    public EntityAdmSoporteBuilder withIdeUsuarioCambio(final BigInteger ideUsuarioCambio) {
        this.ideUsuarioCambio = ideUsuarioCambio;
        return this;
    }

    public EntityAdmSoporteBuilder withIdeUuid(final String ideUuid) {
        this.ideUuid = ideUuid;
        return this;
    }

    public EntityAdmSoporteBuilder withNivEscritura(final Integer nivEscritura) {
        this.nivEscritura = nivEscritura;
        return this;
    }

    public EntityAdmSoporteBuilder withNivLectura(final Integer nivLectura) {
        this.nivLectura = nivLectura;
        return this;
    }

    public EntityAdmSoporteBuilder withNomSoporte(final String nomSoporte) {
        this.nomSoporte = nomSoporte;
        return this;
    }

    public AdmSoporte build(){
        return  new AdmSoporte(ideSoporte,desSoporte,nomSoporte);
    }

}
