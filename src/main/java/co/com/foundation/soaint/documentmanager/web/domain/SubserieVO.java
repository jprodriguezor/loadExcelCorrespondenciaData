package co.com.foundation.soaint.documentmanager.web.domain;

import co.com.foundation.soaint.documentmanager.web.infrastructure.util.constants.EstadoCaracteristicaEnum;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.math.BigInteger;

/**
 * Created by jrodriguez on 20/09/2016.
 */
public class SubserieVO implements Serializable {

    private BigInteger ideSubserie;
    @NotNull
    @NotEmpty
    private String actAdministrativo;
    @NotNull
    @NotEmpty
    @Pattern(regexp = "\\d+", message = "Debe ser un número")
    private String carAdministrativa;
    @NotNull
    @NotEmpty
    @Pattern(regexp = "\\d+", message = "Debe ser un número")
    private String carLegal;
    @NotNull
    @NotEmpty
    @Pattern(regexp = "\\d+", message = "Debe ser un número")
    private String carTecnica;
    @NotNull
    @NotEmpty
    @Pattern(regexp = "\\d+", message = "Debe ser un número")
    private String codSubserie;
    @NotNull
    @NotEmpty
    private String estSubserie;
    @NotNull
    @NotEmpty
    private String fueBibliografica;
    @NotNull
    @NotEmpty
    private String nomSubserie;
    @NotNull
    @NotEmpty
    private String notAlcance;
    @NotNull
    private BigInteger idMotivo;
    private String nombreMotCreacion;
    @NotNull
    private BigInteger idSerie;
    private String codSerie;
    private String nomSerie;
    private int estSubserieValue;

    public SubserieVO() {
    }

    public SubserieVO(String ideSubserie) {
        if(ideSubserie.equals("")) {
            this.ideSubserie = null;
        }else if (!ideSubserie.equals("null")) {
                this.ideSubserie = BigInteger.valueOf(Long.parseLong(ideSubserie));
        }

    }

    public SubserieVO(BigInteger ideSubserie, String codSerie, String codSubserie, String nomSubserie,String actAdministrativo,
                      BigInteger idMotivo, String nombreMotCreacion,String notAlcance,  String fueBibliografica, String estSubserie,
                      String carTecnica,  String carLegal, String carAdministrativa, BigInteger idSerie, String nomSerie, int estSubserieValue) {
        this.ideSubserie = ideSubserie;
        this.actAdministrativo = actAdministrativo;
        this.carAdministrativa = carAdministrativa;
        this.carLegal = carLegal;
        this.carTecnica = carTecnica;
        this.codSubserie = codSubserie;
        this.estSubserie = estSubserie;
        this.fueBibliografica = fueBibliografica;
        this.nomSubserie = nomSubserie == null ? "-N/A-":  nomSubserie;
        this.notAlcance = notAlcance;
        this.nombreMotCreacion = nombreMotCreacion;
        this.idMotivo = idMotivo;
        this.idSerie = idSerie;
        this.codSerie = codSerie;
        this.nomSerie = nomSerie;
        this.estSubserieValue = estSubserieValue;
    }

    public SubserieVO(String codSerie, String codSubserie, String nomSubserie,String actAdministrativo,
                      BigInteger idMotivo, String nombreMotCreacion,String notAlcance,  String fueBibliografica, String estSubserie,
                      String carTecnica,  String carLegal, String carAdministrativa, BigInteger idSerie, String nomSerie, int estSubserieValue) {
        this.actAdministrativo = actAdministrativo;
        this.carAdministrativa = carAdministrativa;
        this.carLegal = carLegal;
        this.carTecnica = carTecnica;
        this.codSubserie = codSubserie;
        this.estSubserie = estSubserie;
        this.fueBibliografica = fueBibliografica;
        this.nomSubserie = nomSubserie;
        this.notAlcance = notAlcance;
        this.nombreMotCreacion = nombreMotCreacion;
        this.idMotivo = idMotivo;
        this.idSerie = idSerie;
        this.codSerie = codSerie;
        this.nomSerie = nomSerie;
        this.estSubserieValue = estSubserieValue;
    }


    public BigInteger getIdeSubserie() {
        return ideSubserie;
    }

    public String getActAdministrativo() {
        return actAdministrativo;
    }

    public Long getCarAdministrativa() {
        return StringUtils.equals(carAdministrativa, EstadoCaracteristicaEnum.ON.getName())
                ? EstadoCaracteristicaEnum.ON.getId() : EstadoCaracteristicaEnum.OFF.getId();
    }

    public String getCarAdministrativaValue() {
        return carAdministrativa;
    }

    public Long getCarLegal() {
        return StringUtils.equals(carLegal, EstadoCaracteristicaEnum.ON.getName())
                ? EstadoCaracteristicaEnum.ON.getId() : EstadoCaracteristicaEnum.OFF.getId();
    }

    public String getCarLegalValue() {
        return carLegal;
    }

    public Long getCarTecnica() {
        return StringUtils.equals(carTecnica, EstadoCaracteristicaEnum.ON.getName())
                ? EstadoCaracteristicaEnum.ON.getId() : EstadoCaracteristicaEnum.OFF.getId();
    }

    public String getCarTecnicaValue() {
        return carTecnica;
    }

    public String getCodSubserie() {
        return codSubserie;
    }

    public String getEstSubserie() {
        return estSubserie;
    }

    public String getNomSubserie() {
        return nomSubserie;
    }

    public String getFueBibliografica() {
        return fueBibliografica;
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

    public BigInteger getIdSerie() {
        return idSerie;
    }

    public String getCodSerie() {
        return codSerie;
    }

    public String getNomSerie() {
        return nomSerie;
    }

    public int getEstSubserieValue() {
        return estSubserieValue;
    }

    @Override
    public String toString() {
        return "SubserieVO{"
                + "ideSubserie=" + ideSubserie
                + ", actAdministrativo='" + actAdministrativo + '\''
                + ", carAdministrativa='" + carAdministrativa + '\''
                + ", carLegal='" + carLegal + '\''
                + ", carTecnica='" + carTecnica + '\''
                + ", codSubserie='" + codSubserie + '\''
                + ", estSubserie='" + estSubserie + '\''
                + ", estSubserieValue='" + estSubserieValue + '\''
                + ", fueBibliografica='" + fueBibliografica + '\''
                + ", nomSubserie='" + nomSubserie + '\''
                + ", notAlcance='" + notAlcance + '\''
                + ", idMotivo=" + idMotivo
                + ", nombreMotCreacion='" + nombreMotCreacion + '\''
                + ", idSerie=" + idSerie
                + ", codSerie='" + codSerie + '\''
                + ", nomSerie='" + nomSerie + '\''
                + '}';
    }
}
