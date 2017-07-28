package com.foundation.soaint.massiveloader.deprecated.web.domain;

import com.foundation.soaint.massiveloader.deprecated.web.infrastructure.util.constants.EstadoCaracteristicaEnum;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigInteger;

/**
 * Created by jrodriguez on 22/09/2016.
 */
public class TipologiaDocVO {

    private BigInteger ideTpgDoc;
    @NotNull
    @NotEmpty
    @Pattern(regexp="\\d+",message = "Debe ser un número")
    private String carAdministrativa;
    @NotNull
    @NotEmpty
    @Pattern(regexp="\\d+",message = "Debe ser un número")
    private String carLegal;
    @NotNull
    @NotEmpty
    @Pattern(regexp="\\d+",message = "Debe ser un número")
    private String carTecnica;

    private String codTpgDoc;
    @NotNull
    @NotEmpty
    private String estTpgDoc;
    @NotNull
    @NotEmpty
    private String nomTpgDoc;

    private String notAlcance;

    private String fueBibliografica;

    @NotNull
    private BigInteger idSoporte;
    private String nomSoport;
    @NotNull
    private BigInteger idTraDocumental;
    private String nomTraDocumental;
    private int estadoTpgDocValue;

    public TipologiaDocVO(){}
    
    public TipologiaDocVO(Integer ideTpgDoc){
        this.ideTpgDoc = BigInteger.valueOf(ideTpgDoc.longValue());
    }

    public TipologiaDocVO(BigInteger ideTpgDoc, String nomTpgDoc,String notAlcance, String fueBibliografica,BigInteger idTraDocumental,
                          String nomTraDocumental, BigInteger idSoporte, String nomSoport,  String estTpgDoc, String carTecnica,  String carLegal,
                          String carAdministrativa, String codTpgDoc,int estadoTpgDocValue) {
        this.ideTpgDoc = ideTpgDoc;
        this.carAdministrativa = carAdministrativa;
        this.carLegal = carLegal;
        this.carTecnica = carTecnica;
        this.codTpgDoc = codTpgDoc;
        this.estTpgDoc = estTpgDoc;
        this.nomTpgDoc = nomTpgDoc;
        this.notAlcance = notAlcance;
        this.fueBibliografica = fueBibliografica;
        this.idSoporte = idSoporte;
        this.nomSoport = nomSoport;
        this.idTraDocumental = idTraDocumental;
        this.nomTraDocumental = nomTraDocumental;
        this.estadoTpgDocValue = estadoTpgDocValue;
    }



    public BigInteger getIdeTpgDoc() {return ideTpgDoc;}

    public Long getCarAdministrativa() {
        return StringUtils.equals(carAdministrativa, EstadoCaracteristicaEnum.ON.getName()) ?
                EstadoCaracteristicaEnum.ON.getId() : EstadoCaracteristicaEnum.OFF.getId();
    }

    public String getCarAdministrativaValue() { return carAdministrativa;}

    public Long getCarLegal() {
        return StringUtils.equals(carLegal, EstadoCaracteristicaEnum.ON.getName()) ?
                EstadoCaracteristicaEnum.ON.getId() : EstadoCaracteristicaEnum.OFF.getId();
    }

    public String getCarLegalValue() {return carLegal;}

    public Long getCarTecnica() {
        return StringUtils.equals(carTecnica, EstadoCaracteristicaEnum.ON.getName()) ?
                EstadoCaracteristicaEnum.ON.getId() : EstadoCaracteristicaEnum.OFF.getId();
    }

    public String getCarTecnicaValue() {return carTecnica;}

    public String getCodTpgDoc() {
        return codTpgDoc;
    }

    public String getEstTpgDoc() {
        return estTpgDoc;
    }

    public String getNomTpgDoc() {
        return nomTpgDoc;
    }

    public String getNotAlcance() {
        return notAlcance;
    }

    public String getFueBibliografica() {
        return fueBibliografica;
    }

    public BigInteger getIdSoporte() {
        return idSoporte;
    }

    public BigInteger getIdTraDocumental() {
        return idTraDocumental;
    }

    public String getNomSoport() {
        return nomSoport;
    }

    public String getNomTraDocumental() {
        return nomTraDocumental;
    }

    public int getEstadoTpgDocValue() {
        return estadoTpgDocValue;
    }
    

    @Override
    public String toString() {
        return "TipologiaDocVO{" +
                "ideTpgDoc=" + ideTpgDoc +
                ", carAdministrativa=" + carAdministrativa +
                ", carLegal=" + carLegal +
                ", carTecnica=" + carTecnica +
                ", codTpgDoc='" + codTpgDoc + '\'' +
                ", estTpgDoc=" + estTpgDoc +
                ", nomTpgDoc='" + nomTpgDoc + '\'' +
                ", notAlcance='" + notAlcance + '\'' +
                ", fueBibliografica='" + fueBibliografica + '\'' +
                ", idSoporte=" + idSoporte +
                ", nomSoport='" + nomSoport + '\'' +
                ", idTraDocumental=" + idTraDocumental +
                ", nomTraDocumental='" + nomTraDocumental + '\'' +
                ", estadoTpgDocValue='" + estadoTpgDocValue + '\'' +                
                '}';
    }
}
