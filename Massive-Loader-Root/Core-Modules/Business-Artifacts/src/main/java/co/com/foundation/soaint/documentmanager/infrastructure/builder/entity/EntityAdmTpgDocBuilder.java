package co.com.foundation.soaint.documentmanager.infrastructure.builder.entity;

import co.com.foundation.soaint.documentmanager.persistence.entity.AdmSoporte;
import co.com.foundation.soaint.documentmanager.persistence.entity.AdmTpgDoc;
import co.com.foundation.soaint.documentmanager.persistence.entity.AdmTradDoc;
import co.com.foundation.soaint.infrastructure.builder.Builder;

import java.math.BigInteger;
import java.util.Date;

/**
 * Created by jrodriguez on 23/09/2016.
 */
public class EntityAdmTpgDocBuilder implements Builder<AdmTpgDoc> {

    private BigInteger ideTpgDoc;
    private Long carAdministrativa;
    private Long carLegal;
    private Long carTecnica;
    private String codTpgDoc;
    private int estTpgDoc;
    private Date fecCambio;
    private Date fecCreacion;
    private BigInteger ideUsuarioCambio;
    private String ideUuid;
    private Integer nivEscritura;
    private Integer nivLectura;
    private String nomTpgDoc;
    private String notAlcance;
    private String fueBibliografica;
    private AdmSoporte ideSoporte;
    private AdmTradDoc ideTraDocumental;


    private EntityAdmTpgDocBuilder() {
    }

    public static EntityAdmTpgDocBuilder newBuilder() {
        return new EntityAdmTpgDocBuilder();
    }

    public EntityAdmTpgDocBuilder withetIdeTpgDoc(final BigInteger ideTpgDoc) {
        this.ideTpgDoc = ideTpgDoc;
        return this;
    }

    public EntityAdmTpgDocBuilder withetCarAdministrativa(final Long carAdministrativa) {
        this.carAdministrativa = carAdministrativa;
        return this;
    }

    public EntityAdmTpgDocBuilder withetCarLegal(final Long carLegal) {
        this.carLegal = carLegal;
        return this;
    }

    public EntityAdmTpgDocBuilder withetCodTpgDoc(final String codTpgDoc) {
        this.codTpgDoc = codTpgDoc;
        return this;
    }

    public EntityAdmTpgDocBuilder withetCarTecnica(final Long carTecnica) {
        this.carTecnica = carTecnica;
        return this;
    }

    public EntityAdmTpgDocBuilder withetEstTpgDoc(final int estTpgDoc) {
        this.estTpgDoc = estTpgDoc;
        return this;
    }

    public EntityAdmTpgDocBuilder withetFecCambio(final Date fecCambio) {
        this.fecCambio = fecCambio;
        return this;
    }

    public EntityAdmTpgDocBuilder withetFecCreacion(final Date fecCreacion) {
        this.fecCreacion = fecCreacion;
        return this;
    }

    public EntityAdmTpgDocBuilder withetIdeUsuarioCambio(final BigInteger ideUsuarioCambio) {
        this.ideUsuarioCambio = ideUsuarioCambio;
        return this;
    }

    public EntityAdmTpgDocBuilder withetIdeUuid(final String ideUuid) {
        this.ideUuid = ideUuid;
        return this;
    }

    public EntityAdmTpgDocBuilder withetNivLectura(final Integer nivLectura) {
        this.nivLectura = nivLectura;
        return this;
    }

    public EntityAdmTpgDocBuilder withetNivEscritura(final Integer nivEscritura) {
        this.nivEscritura = nivEscritura;
        return this;
    }

    public EntityAdmTpgDocBuilder withetNomTpgDoc(final String nomTpgDoc) {
        this.nomTpgDoc = nomTpgDoc;
        return this;
    }

    public EntityAdmTpgDocBuilder withetNotAlcance(final String notAlcance) {
        this.notAlcance = notAlcance;
        return this;
    }

    public EntityAdmTpgDocBuilder withetFueBibliografica(final String fueBibliografica) {
        this.fueBibliografica = fueBibliografica;
        return this;
    }

    public EntityAdmTpgDocBuilder withetIdeSoporte(final AdmSoporte ideSoporte) {
        this.ideSoporte = ideSoporte;
        return this;
    }

    public EntityAdmTpgDocBuilder withetIdeTraDocumental(final AdmTradDoc ideTraDocumental) {
        this.ideTraDocumental = ideTraDocumental;
        return this;
    }

    public AdmTpgDoc build() {
        return new AdmTpgDoc(ideTpgDoc, carAdministrativa, ideTraDocumental, ideSoporte, notAlcance, fueBibliografica,
                nomTpgDoc, nivLectura, nivEscritura, ideUuid, ideUsuarioCambio, fecCreacion, fecCambio, estTpgDoc,
                codTpgDoc, carTecnica, carLegal);
    }
}