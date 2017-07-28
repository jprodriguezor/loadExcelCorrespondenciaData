package com.foundation.soaint.massiveloader.deprecated.web.domain;

import com.foundation.soaint.massiveloader.deprecated.web.infrastructure.util.constants.EstadoCaracteristicaEnum;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.math.BigInteger;

/**
 * Created by jrodriguez on 11/09/2016.
 */
public class SerieVO implements Serializable {

    private BigInteger ideSerie;
    @NotNull
    @NotEmpty
    @Pattern(regexp="\\d+",message = "Debe ser un número")
    private String codSerie;
    @NotNull
    @NotEmpty
    private String nomSerie;

    @NotNull
    @NotEmpty
    private String actAdministrativo;
    @NotNull
    private BigInteger idMotivo;
    @NotNull
    @NotEmpty
    private String notAlcance;
    @NotNull
    @NotEmpty
    private String fueBibliografica;
    @NotNull
    @NotEmpty
    private String estSerie;
    @NotNull
    @NotEmpty
    @Pattern(regexp="\\d+",message = "Debe ser un número")
    private String carTecnica;
    @NotNull
    @NotEmpty
    @Pattern(regexp="\\d+",message = "Debe ser un número")
    private String carLegal;
    @NotNull
    @NotEmpty
    @Pattern(regexp="\\d+",message = "Debe ser un número")
    private String carAdministrativa;

    private String nombreMotCreacion;
    private int estSerieValue;

    public SerieVO() {
    }
    
    public SerieVO(String ideSerie) {
        this.ideSerie = BigInteger.valueOf(Long.parseLong(ideSerie));
    }

    public SerieVO(String codSerie, String nomSerie, BigInteger ideSerie) {
        this.codSerie = codSerie;
        this.nomSerie = nomSerie;
        this.ideSerie = ideSerie;
    }
    public SerieVO(String codSerie, String nomSerie, String actAdministrativo,BigInteger idMotivo, String nombreMotCreacion,
                   String notAlcance, String fueBibliografica, String estSerie, String carTecnica, String carLegal,
                   String carAdministrativa, BigInteger ideSerie, int estSerieValue) {
        this.actAdministrativo = actAdministrativo;
        this.carAdministrativa = carAdministrativa;
        this.carLegal = carLegal;
        this.carTecnica = carTecnica;
        this.codSerie = codSerie;
        this.estSerie = estSerie;
        this.fueBibliografica = fueBibliografica;
        this.nomSerie = nomSerie;
        this.notAlcance = notAlcance;
        this.idMotivo = idMotivo;
        this.nombreMotCreacion = nombreMotCreacion;
        this.ideSerie = ideSerie;
        this.estSerieValue =estSerieValue;
    }



    public BigInteger getIdeSerie() {
        return ideSerie;
    }

    public String getActAdministrativo() {
        return actAdministrativo;
    }

    public String getCarLegalValue() {return carLegal;}

    public Long getCarLegal() {
        return StringUtils.equals(carLegal, EstadoCaracteristicaEnum.ON.getName())
                ? EstadoCaracteristicaEnum.ON.getId() : EstadoCaracteristicaEnum.OFF.getId();
    }

    public String getCarAdministrativaValue() {return carAdministrativa;}

    public Long getCarAdministrativa() {
        return StringUtils.equals(carAdministrativa, EstadoCaracteristicaEnum.ON.getName())
                ? EstadoCaracteristicaEnum.ON.getId() : EstadoCaracteristicaEnum.OFF.getId();
    }

    public String getCarTecnicaValue() {return carTecnica;}

    public Long getCarTecnica() {
        return StringUtils.equals(carTecnica, EstadoCaracteristicaEnum.ON.getName())
                ? EstadoCaracteristicaEnum.ON.getId() : EstadoCaracteristicaEnum.OFF.getId();
    }

    public int getEstSerieValue() {return estSerieValue;}

    public String getCodSerie() {
        return codSerie;
    }

    public String getEstSerie() {
        return estSerie;
    }

    public String getFueBibliografica() {
        return fueBibliografica;
    }

    public String getNomSerie() {
        return nomSerie;
    }

    public String getNotAlcance() {
        return notAlcance;
    }

    public BigInteger getIdMotivo() {
        return idMotivo;
    }

    public String getNombreMotCreacion() {
        return nombreMotCreacion;
    }

    @Override
    public String toString() {
        return "SerieVO{"
                + "ideSerie=" + ideSerie
                + ", actAdministrativo='" + actAdministrativo + '\''
                + ", carAdministrativa=" + carAdministrativa
                + ", carLegal=" + carLegal
                + ", carTecnica=" + carTecnica
                + ", codSerie='" + codSerie + '\''
                + ", estSerie=" + estSerie
                + ", fueBibliografica='" + fueBibliografica + '\''
                + ", nomSerie='" + nomSerie + '\''
                + ", notAlcance='" + notAlcance + '\''
                + ", idMotivo=" + idMotivo
                + ", nombreMotCreacion='" + nombreMotCreacion + '\''
                + '}';
    }
}
