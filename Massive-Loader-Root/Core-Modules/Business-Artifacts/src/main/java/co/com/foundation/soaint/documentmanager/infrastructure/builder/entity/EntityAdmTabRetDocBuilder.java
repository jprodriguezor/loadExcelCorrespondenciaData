package co.com.foundation.soaint.documentmanager.infrastructure.builder.entity;

import co.com.foundation.soaint.documentmanager.persistence.entity.AdmDisFinal;
import co.com.foundation.soaint.documentmanager.persistence.entity.AdmSerie;
import co.com.foundation.soaint.documentmanager.persistence.entity.AdmSubserie;
import co.com.foundation.soaint.documentmanager.persistence.entity.AdmTabRetDoc;
import co.com.foundation.soaint.infrastructure.builder.Builder;

import java.math.BigInteger;
import java.util.Date;

/**
 * Created by jrodriguez on 10/11/2016.
 */
public class EntityAdmTabRetDocBuilder implements Builder<AdmTabRetDoc> {


    private BigInteger ideTabRetDoc;
    private Long acTrd;
    private Long agTrd;
    private Integer estTabRetDoc;
    private Date fecCambio;
    private Date fecCreacion;
    private BigInteger ideUsuarioCambio;
    private String ideUuid;
    private Integer nivEscritura;
    private Integer nivLectura;
    private String proTrd;
    private AdmDisFinal admDisFinal;
    private String ideOfcProd;
    private String ideUniAmt;
    private AdmSerie ideSerie;
    private AdmSubserie ideSubserie;

    private EntityAdmTabRetDocBuilder() {
    }


    public static EntityAdmTabRetDocBuilder newInstance() {
        return new EntityAdmTabRetDocBuilder();
    }


    public EntityAdmTabRetDocBuilder withAgTrd(final Long agTrd) {
        this.agTrd = agTrd;
        return this;
    }

    public EntityAdmTabRetDocBuilder withIdeTabRetDoc(final BigInteger ideTabRetDoc) {
        this.ideTabRetDoc = ideTabRetDoc;
        return this;
    }

    public EntityAdmTabRetDocBuilder withAcTrd(final Long acTrd) {
        this.acTrd = acTrd;
        return this;
    }

    public EntityAdmTabRetDocBuilder withEstTabRetDoc(final Integer estTabRetDoc) {
        this.estTabRetDoc = estTabRetDoc;
        return this;
    }

    public EntityAdmTabRetDocBuilder withFecCambio(final Date fecCambio) {
        this.fecCambio = fecCambio;
        return this;
    }

    public EntityAdmTabRetDocBuilder withFecCreacion(final Date fecCreacion) {
        this.fecCreacion = fecCreacion;
        return this;
    }

    public EntityAdmTabRetDocBuilder withIdeUsuarioCambio(final BigInteger ideUsuarioCambio) {
        this.ideUsuarioCambio = ideUsuarioCambio;
        return this;
    }

    public EntityAdmTabRetDocBuilder withIdeUuid(final String ideUuid) {
        this.ideUuid = ideUuid;
        return this;
    }

    public EntityAdmTabRetDocBuilder withNivEscritura(final Integer nivEscritura) {
        this.nivEscritura = nivEscritura;
        return this;
    }

    public EntityAdmTabRetDocBuilder withNivLectura(final Integer nivLectura) {
        this.nivLectura = nivLectura;
        return this;
    }

    public EntityAdmTabRetDocBuilder withProTrd(final String proTrd) {
        this.proTrd = proTrd;
        return this;
    }

    public EntityAdmTabRetDocBuilder withIdeDisFinal(final AdmDisFinal admDisFinal) {
        this.admDisFinal = admDisFinal;
        return this;
    }

    public EntityAdmTabRetDocBuilder withIdeOfcProd(final String ideOfcProd) {
        this.ideOfcProd = ideOfcProd;
        return this;
    }

    public EntityAdmTabRetDocBuilder withIdeUniAmt(final String ideUniAmt) {
        this.ideUniAmt = ideUniAmt;
        return this;
    }

    public EntityAdmTabRetDocBuilder withIdeSerie(final AdmSerie ideSerie) {
        this.ideSerie = ideSerie;
        return this;
    }

    public EntityAdmTabRetDocBuilder withIdeSubserie(final AdmSubserie ideSubserie) {
        this.ideSubserie = ideSubserie;
        return this;
    }

    public AdmTabRetDoc build() {
        return new AdmTabRetDoc(ideTabRetDoc, acTrd, agTrd, estTabRetDoc, fecCambio, fecCreacion,
                ideUsuarioCambio, ideUuid, nivEscritura, nivLectura, proTrd,
                admDisFinal, ideOfcProd, ideUniAmt, ideSerie, ideSubserie
        );
    }
}
