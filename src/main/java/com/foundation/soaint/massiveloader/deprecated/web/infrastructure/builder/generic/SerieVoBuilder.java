package com.foundation.soaint.massiveloader.deprecated.web.infrastructure.builder.generic;

import com.foundation.soaint.massiveloader.deprecated.web.domain.SerieVO;
import co.com.foundation.soaint.infrastructure.builder.Builder;

import java.math.BigInteger;

/**
 * Created by jrodriguez on 11/09/2016.
 */
public class SerieVoBuilder implements Builder<SerieVO> {

    private BigInteger ideSerie;
    private String actAdministrativo;
    private String carAdministrativa;
    private String carLegal;
    private String carTecnica;
    private String codSerie;
    private String estSerie;
    private String fueBibliografica;
    private String nomSerie;
    private String notAlcance;
    private BigInteger idMotivo;
    private String nombreMotCreacion;
    private int estSerieValue;

    private SerieVoBuilder() {
    }

    public static SerieVoBuilder newBuilder() {
        return new SerieVoBuilder();
    }

    public SerieVoBuilder withIdeSerie(BigInteger ideSerie) {
        this.ideSerie = ideSerie;
        return this;
    }

    public SerieVoBuilder withActAdministrativo(String actAdministrativo) {
        this.actAdministrativo = actAdministrativo;
        return this;
    }

    public SerieVoBuilder withCarAdministrativa(String carAdministrativa) {
        this.carAdministrativa = carAdministrativa;
        return this;
    }

    public SerieVoBuilder withCarLegal(String carLegal) {
        this.carLegal = carLegal;
        return this;
    }

    public SerieVoBuilder withCarTecnica(String carTecnica) {
        this.carTecnica = carTecnica;
        return this;
    }

    public SerieVoBuilder withCodSerie(String codSerie) {
        this.codSerie = codSerie;
        return this;
    }

    public SerieVoBuilder withEstSerie(String estSerie) {
        this.estSerie = estSerie;
        return this;
    }

    public SerieVoBuilder withFueBibliografica(String fueBibliografica) {
        this.fueBibliografica = fueBibliografica;
        return this;
    }

    public SerieVoBuilder withNomSerie(String nomSerie) {
        this.nomSerie = nomSerie;
        return this;
    }

    public SerieVoBuilder withNotAlcance(String notAlcance) {
        this.notAlcance = notAlcance;
        return this;
    }

    public SerieVoBuilder withIdMotivo(BigInteger idMotivo) {
        this.idMotivo = idMotivo;
        return this;
    }

    public SerieVoBuilder withNombreMotCreacion(String nombreMotCreacion) {
        this.nombreMotCreacion = nombreMotCreacion;
        return this;
    }

    public SerieVoBuilder withEstSerieValue(int estSerieValue) {
        this.estSerieValue = estSerieValue;
        return this;
    }

    public SerieVO build() {
        return new SerieVO(codSerie, nomSerie, actAdministrativo, idMotivo, nombreMotCreacion, notAlcance,
                fueBibliografica, estSerie, carTecnica, carLegal, carAdministrativa, ideSerie, estSerieValue);
    }
}
