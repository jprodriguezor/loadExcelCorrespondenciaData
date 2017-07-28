package com.soaint.massive.loader.web.infrastructure.builder.generic;

import com.soaint.massive.loader.web.domain.SubserieVO;
import co.com.foundation.soaint.infrastructure.builder.Builder;

import java.math.BigInteger;

/**
 * Created by jrodriguez on 20/09/2016.
 */
public class SubserieVoBuilder implements Builder<SubserieVO> {

    private BigInteger ideSubserie;
    private String actAdministrativo;
    private String carAdministrativa;
    private String carLegal;
    private String carTecnica;
    private String codSubserie;
    private String estSubserie;
    private String fueBibliografica;
    private String nomSubserie;
    private String notAlcance;
    private BigInteger idMotivo;
    private String nombreMotCreacion;
    private BigInteger idSerie;
    private String codSerie;
    private String nomSerie;
    private int estSubserieValue;

    private SubserieVoBuilder() {
    }

    public static SubserieVoBuilder newBuilder() {
        return new SubserieVoBuilder();
    }

    public SubserieVoBuilder withIdeSubserie(BigInteger ideSubserie) {
        this.ideSubserie = ideSubserie;
        return this;
    }

    public SubserieVoBuilder withActAdministrativo(String actAdministrativo) {
        this.actAdministrativo = actAdministrativo;
        return this;
    }

    public SubserieVoBuilder withCarAdministrativa(String carAdministrativa) {
        this.carAdministrativa = carAdministrativa;
        return this;
    }

    public SubserieVoBuilder withCarLegal(String carLegal) {
        this.carLegal = carLegal;
        return this;
    }

    public SubserieVoBuilder withCarTecnica(String carTecnica) {
        this.carTecnica = carTecnica;
        return this;
    }

    public SubserieVoBuilder withCodSubserie(String codSubserie) {
        this.codSubserie = codSubserie;
        return this;
    }

    public SubserieVoBuilder withEstSubserie(String estSubserie) {
        this.estSubserie = estSubserie;
        return this;
    }

    public SubserieVoBuilder withFueBibliografica(String fueBibliografica) {
        this.fueBibliografica = fueBibliografica;
        return this;
    }

    public SubserieVoBuilder withNomSubserie(String nomSubserie) {
        this.nomSubserie = nomSubserie;
        return this;
    }

    public SubserieVoBuilder withNotAlcance(String notAlcance) {
        this.notAlcance = notAlcance;
        return this;
    }

    public SubserieVoBuilder withIdMotivo(BigInteger idMotivo) {
        this.idMotivo = idMotivo;
        return this;
    }

    public SubserieVoBuilder withNombreMotCreacion(String nombreMotCreacion) {
        this.nombreMotCreacion = nombreMotCreacion;
        return this;
    }

    public SubserieVoBuilder withIdSerie(BigInteger idSerie) {
        this.idSerie = idSerie;
        return this;
    }


    public SubserieVoBuilder withCodSerie(String codSerie) {
        this.codSerie = codSerie;
        return this;
    }

    public SubserieVoBuilder withNomSerie(String nomSerie) {
        this.nomSerie = nomSerie;
        return this;
    }

    public SubserieVoBuilder withEstSubserieValue(int estSubserieValue) {
        this.estSubserieValue = estSubserieValue;
        return this;
    }

    public SubserieVO build() {
        return new SubserieVO(ideSubserie, codSerie, codSubserie, nomSubserie, actAdministrativo, idMotivo,
                nombreMotCreacion, notAlcance, fueBibliografica, estSubserie, carTecnica, carLegal, carAdministrativa,
                idSerie, nomSerie, estSubserieValue);
    }

}
