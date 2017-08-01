package co.com.foundation.soaint.documentmanager.infrastructure.builder.entity;

import co.com.foundation.soaint.documentmanager.persistence.entity.AdmMotCreacion;
import co.com.foundation.soaint.documentmanager.persistence.entity.AdmSerie;
import co.com.foundation.soaint.documentmanager.persistence.entity.AdmSubserie;
import co.com.foundation.soaint.infrastructure.builder.Builder;

import java.math.BigInteger;
import java.util.Date;

/**
 * Created by jrodriguez on 21/09/2016.
 */
public class EntityAdmSubserieBuilder implements Builder<AdmSubserie> {

    private BigInteger ideSubserie;
    private String actAdministrativo;
    private Long carAdministrativa;
    private Long carLegal;
    private Long carTecnica;
    private String codSubserie;
    private int estSubserie;
    private int estSubserieValue;
    private Date fecCambio;
    private Date fecCreacion;
    private String fueBibliografica;
    private BigInteger ideUsuarioCambio;
    private String ideUuid;
    private Integer nivLectura;
    private Integer nivEscritura;
    private String nomSubserie;
    private String notAlcance;
    private AdmMotCreacion ideMotCreacion;
    private AdmSerie ideSerie;

    private EntityAdmSubserieBuilder() {
    }

    public static EntityAdmSubserieBuilder newInstance() {
        return new EntityAdmSubserieBuilder();
    }

    public EntityAdmSubserieBuilder withIdeSubserie(final BigInteger ideSubserie) {
        this.ideSubserie = ideSubserie;
        return this;
    }

    public EntityAdmSubserieBuilder withActAdministrativo(final String actAdministrativo) {
        this.actAdministrativo = actAdministrativo;
        return this;
    }

    public EntityAdmSubserieBuilder withCarLegal(final Long carLegal) {
        this.carLegal = carLegal;
        return this;
    }

    public EntityAdmSubserieBuilder withCarAdministrativa(final Long carAdministrativa) {
        this.carAdministrativa = carAdministrativa;
        return this;
    }

    public EntityAdmSubserieBuilder withCarTecnica(final Long carTecnica) {
        this.carTecnica = carTecnica;
        return this;
    }

    public EntityAdmSubserieBuilder withCodSubserie(final String codSubserie) {
        this.codSubserie = codSubserie;
        return this;
    }

    public EntityAdmSubserieBuilder withEstSubserie(final int estSubserie) {
        this.estSubserie = estSubserie;
        return this;
    }

    public EntityAdmSubserieBuilder withFecCambio(final Date fecCambio) {
        this.fecCambio = fecCambio;
        return this;
    }

    public EntityAdmSubserieBuilder withFecCreacion(final Date fecCreacion) {
        this.fecCreacion = fecCreacion;
        return this;
    }

    public EntityAdmSubserieBuilder withFueBibliografica(final String fueBibliografica) {
        this.fueBibliografica = fueBibliografica;
        return this;
    }

    public EntityAdmSubserieBuilder withIdeUsuarioCambio(final BigInteger ideUsuarioCambio) {
        this.ideUsuarioCambio = ideUsuarioCambio;
        return this;
    }

    public EntityAdmSubserieBuilder withIdeUuid(final String ideUuid) {
        this.ideUuid = ideUuid;
        return this;
    }

    public EntityAdmSubserieBuilder withNivLectura(final Integer nivLectura) {
        this.nivLectura = nivLectura;
        return this;
    }

    public EntityAdmSubserieBuilder withNivEscritura(final Integer nivEscritura) {
        this.nivEscritura = nivEscritura;
        return this;
    }

    public EntityAdmSubserieBuilder withNomSubserie(final String nomSubserie) {
        this.nomSubserie = nomSubserie;
        return this;
    }

    public EntityAdmSubserieBuilder withNotAlcance(final String notAlcance) {
        this.notAlcance = notAlcance;
        return this;
    }

    public EntityAdmSubserieBuilder withIdeMotCreacion(final AdmMotCreacion ideMotCreacion) {
        this.ideMotCreacion = ideMotCreacion;
        return this;
    }

    public EntityAdmSubserieBuilder withIdeSerie(final AdmSerie ideSerie) {
        this.ideSerie = ideSerie;
        return this;
    }

    public EntityAdmSubserieBuilder withEstSubserieValue(int estSubserieValue) {
        this.estSubserieValue = estSubserieValue;
        return this;
    }

    public AdmSubserie build() {
        return new AdmSubserie(
                ideSubserie, actAdministrativo, carAdministrativa, carLegal, carTecnica, codSubserie, estSubserie,
                fecCambio, fecCreacion, fueBibliografica, ideUsuarioCambio, ideUuid, nivLectura, nivEscritura, nomSubserie,
                notAlcance, ideMotCreacion, ideSerie);
    }

}
