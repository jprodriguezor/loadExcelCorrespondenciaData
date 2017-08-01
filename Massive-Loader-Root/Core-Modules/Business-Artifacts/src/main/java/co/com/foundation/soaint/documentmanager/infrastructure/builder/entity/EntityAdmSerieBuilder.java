package co.com.foundation.soaint.documentmanager.infrastructure.builder.entity;

import co.com.foundation.soaint.documentmanager.persistence.entity.AdmMotCreacion;
import co.com.foundation.soaint.documentmanager.persistence.entity.AdmSerie;
import co.com.foundation.soaint.infrastructure.builder.Builder;

import java.math.BigInteger;
import java.util.Date;

/**
 * Created by jrodriguez on 13/09/2016.
 */
public class EntityAdmSerieBuilder implements Builder<AdmSerie> {

    private BigInteger ideSerie;
    private String actAdministrativo;
    private Long carAdministrativa;
    private Long carLegal;
    private Long carTecnica;
    private String codSerie;
    private int estSerie;
    private Date fecCambio;
    private Date fecCreacion;
    private String fueBibliografica;
    private BigInteger ideUsuarioCambio;
    private String ideUuid;
    private Integer nivEscritura;
    private Integer nivLectura;
    private String nomSerie;
    private String notAlcance;
    private AdmMotCreacion ideMotCreacion;

    private EntityAdmSerieBuilder() {

    }

    public static EntityAdmSerieBuilder newInstance() {
        return new EntityAdmSerieBuilder();
    }

    public EntityAdmSerieBuilder withActAdministrativo(final String actAdministrativo) {
        this.actAdministrativo = actAdministrativo;
        return this;

    }

    public EntityAdmSerieBuilder withIdeSerie(final BigInteger ideSerie) {
        this.ideSerie = ideSerie;
        return this;
    }

    public EntityAdmSerieBuilder withCarAdministrativa(final Long carAdministrativa) {
        this.carAdministrativa = carAdministrativa;
        return this;
    }

    public EntityAdmSerieBuilder withCarLegal(final Long carLegal) {
        this.carLegal = carLegal;
        return this;
    }

    public EntityAdmSerieBuilder withCarTecnica(final Long carTecnica) {
        this.carTecnica = carTecnica;
        return this;
    }

    public EntityAdmSerieBuilder withCodSerie(final String codSerie) {
        this.codSerie = codSerie;
        return this;
    }

    public EntityAdmSerieBuilder withEstSerie(final int estSerie) {
        this.estSerie = estSerie;
        return this;
    }

    public EntityAdmSerieBuilder withFecCambio(final Date fecCambio) {
        this.fecCambio = fecCambio;
        return this;
    }

    public EntityAdmSerieBuilder withFecCreacion(final Date fecCreacion) {
        this.fecCreacion = fecCreacion;
        return this;
    }

    public EntityAdmSerieBuilder withFueBibliografica(final String fueBibliografica) {
        this.fueBibliografica = fueBibliografica;
        return this;
    }

    public EntityAdmSerieBuilder withIdeUsuarioCambio(final BigInteger ideUsuarioCambio) {
        this.ideUsuarioCambio = ideUsuarioCambio;
        return this;
    }

    public EntityAdmSerieBuilder withIdeUuid(final String ideUuid) {
        this.ideUuid = ideUuid;
        return this;
    }

    public EntityAdmSerieBuilder withNivEscritura(final Integer nivEscritura) {
        this.nivEscritura = nivEscritura;
        return this;
    }

    public EntityAdmSerieBuilder withNivLectura(final Integer nivLectura) {
        this.nivLectura = nivLectura;
        return this;
    }

    public EntityAdmSerieBuilder withNomSerie(final String nomSerie) {
        this.nomSerie = nomSerie;
        return this;
    }

    public EntityAdmSerieBuilder withNotAlcance(final String notAlcance) {
        this.notAlcance = notAlcance;
        return this;
    }

    public EntityAdmSerieBuilder withIdeMotCreacion(final AdmMotCreacion ideMotCreacion) {
        this.ideMotCreacion = ideMotCreacion;
        return this;
    }

    public AdmSerie build() {
        return new AdmSerie(ideSerie, actAdministrativo, carAdministrativa, carLegal, carTecnica, codSerie, estSerie, fecCreacion,
                fueBibliografica, nomSerie, notAlcance, ideMotCreacion, nivLectura, nivEscritura, ideUuid,
                ideUsuarioCambio, fecCambio);
    }
}
