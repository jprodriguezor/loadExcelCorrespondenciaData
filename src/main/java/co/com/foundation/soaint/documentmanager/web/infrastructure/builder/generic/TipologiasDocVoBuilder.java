package co.com.foundation.soaint.documentmanager.web.infrastructure.builder.generic;

import co.com.foundation.soaint.documentmanager.web.domain.TipologiaDocVO;
import co.com.foundation.soaint.infrastructure.builder.Builder;

import java.math.BigInteger;

/**
 * Created by jrodriguez on 22/09/2016.
 */
public class TipologiasDocVoBuilder implements Builder<TipologiaDocVO> {

    private BigInteger ideTpgDoc;
    private String carAdministrativa;
    private String carLegal;
    private String carTecnica;
    private String codTpgDoc;
    private String estTpgDoc;
    private String nomTpgDoc;
    private String notAlcance;
    private String fueBibliografica;
    private BigInteger idSoporte;
    private String nomSoport;
    private BigInteger idTraDocumental;
    private String nomTraDocumental;
    private int estadoTpgDocValue;

    private TipologiasDocVoBuilder() {
    }

    public static TipologiasDocVoBuilder newBuilder() {
        return new TipologiasDocVoBuilder();
    }

    public TipologiasDocVoBuilder withIdeTpgDoc(BigInteger ideTpgDoc) {
        this.ideTpgDoc = ideTpgDoc;
        return this;
    }

    public TipologiasDocVoBuilder withCarAdministrativa(String carAdministrativa) {
        this.carAdministrativa = carAdministrativa;
        return this;
    }

    public TipologiasDocVoBuilder withCarLegal(String carLegal) {
        this.carLegal = carLegal;
        return this;
    }

    public TipologiasDocVoBuilder withCarTecnica(String carTecnica) {
        this.carTecnica = carTecnica;
        return this;
    }

    public TipologiasDocVoBuilder withCodTpgDoc(String codTpgDoc) {
        this.codTpgDoc = codTpgDoc;
        return this;
    }

    public TipologiasDocVoBuilder withEstTpgDoc(String estTpgDoc) {
        this.estTpgDoc = estTpgDoc;
        return this;
    }

    public TipologiasDocVoBuilder withNomTpgDoc(String nomTpgDoc) {
        this.nomTpgDoc = nomTpgDoc;
        return this;
    }

    public TipologiasDocVoBuilder withNotAlcance(String notAlcance) {
        this.notAlcance = notAlcance;
        return this;
    }

    public TipologiasDocVoBuilder withFueBibliografica(String fueBibliografica) {
        this.fueBibliografica = fueBibliografica;
        return this;
    }

    public TipologiasDocVoBuilder withIdSoporte(BigInteger idSoporte) {
        this.idSoporte = idSoporte;
        return this;
    }

    public TipologiasDocVoBuilder withNomSoport(String nomSoport) {
        this.nomSoport = nomSoport;
        return this;
    }

    public TipologiasDocVoBuilder withIdTraDocumental(BigInteger idTraDocumental) {
        this.idTraDocumental = idTraDocumental;
        return this;
    }

    public TipologiasDocVoBuilder withNomTraDocumental(String nomTraDocumental) {
        this.nomTraDocumental = nomTraDocumental;
        return this;
    }

    public TipologiasDocVoBuilder withEstadoTpgDocValue(int estadoTpgDocValue) {
        this.estadoTpgDocValue = estadoTpgDocValue;
        return this;
    }


    public TipologiaDocVO build() {
        return new TipologiaDocVO(ideTpgDoc, nomTpgDoc, notAlcance, fueBibliografica, idTraDocumental, nomTraDocumental,
                idSoporte, nomSoport, estTpgDoc, carTecnica, carLegal, carAdministrativa, nomTraDocumental, estadoTpgDocValue);
    }


}
