package co.com.foundation.soaint.documentmanager.infrastructure.builder.entity;
import co.com.foundation.soaint.documentmanager.persistence.entity.AdmRelEqDestino;
import co.com.foundation.soaint.documentmanager.persistence.entity.AdmRelEqOrigen;
import co.com.foundation.soaint.documentmanager.persistence.entity.AdmSerie;
import co.com.foundation.soaint.documentmanager.persistence.entity.AdmSubserie;
import co.com.foundation.soaint.infrastructure.builder.Builder;

import java.math.BigInteger;
import java.util.Date;

/**
 * Created by ADMIN on 06/12/2016.
 */
public class EntityAdmRelEqDestinoBuilder  implements  Builder<AdmRelEqDestino> {
    private BigInteger ideRelDestino;
    private String ideUniAmt;
    private String ideOfcProd;
    private Date fecCreacion;
    private BigInteger admRelEqOrigen;
    private BigInteger ideSerie;
    private BigInteger ideSubserie;

    public EntityAdmRelEqDestinoBuilder() {
    }

    public static EntityAdmRelEqDestinoBuilder newBuilder() {
        return new EntityAdmRelEqDestinoBuilder();

    }

    public EntityAdmRelEqDestinoBuilder withIdeRelDestino(BigInteger ideRelDestino) {
        this.ideRelDestino = ideRelDestino;
        return this;
    }

    public EntityAdmRelEqDestinoBuilder withIdeUniAmt(String ideUniAmt) {
        this.ideUniAmt = ideUniAmt;
        return this;
    }

    public EntityAdmRelEqDestinoBuilder withIdeOfcProd(String ideOfcProd) {
        this.ideOfcProd = ideOfcProd;
        return this;
    }

    public EntityAdmRelEqDestinoBuilder withFecCreacion(Date fecCreacion) {
        this.fecCreacion = fecCreacion;
        return this;
    }

    public EntityAdmRelEqDestinoBuilder withAdmRelEqOrigen(BigInteger admRelEqOrigen) {
        this.admRelEqOrigen = admRelEqOrigen;
        return this;
    }


    public  EntityAdmRelEqDestinoBuilder withIdeSerie(BigInteger ideSerie) {
        this.ideSerie = ideSerie;
        return this;
    }

    public  EntityAdmRelEqDestinoBuilder withIdeSubserie(BigInteger ideSubserie) {
        this.ideSubserie = ideSubserie;
        return this;
    }

    public AdmRelEqDestino build() {
        return new AdmRelEqDestino(
          ideRelDestino,
          ideUniAmt,
          ideOfcProd,
          fecCreacion,
          admRelEqOrigen,
          ideSerie,
          ideSubserie);
    }
}
