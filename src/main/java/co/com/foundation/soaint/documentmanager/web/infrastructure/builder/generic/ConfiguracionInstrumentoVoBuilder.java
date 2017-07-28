package co.com.foundation.soaint.documentmanager.web.infrastructure.builder.generic;

import co.com.foundation.soaint.infrastructure.builder.Builder;
import co.com.foundation.soaint.documentmanager.web.domain.ConfiguracionInstrumentoVO;

/**
 * Created by jrodriguez on 27/10/2016.
 */
public class ConfiguracionInstrumentoVoBuilder implements Builder<ConfiguracionInstrumentoVO> {

    private String instrumento;
    private String estado;

    public ConfiguracionInstrumentoVoBuilder() {
    }

    public static ConfiguracionInstrumentoVoBuilder newBuilder() {
        return new ConfiguracionInstrumentoVoBuilder();
    }

    public ConfiguracionInstrumentoVoBuilder withInstrumento(String instrumento) {
        this.instrumento = instrumento;
        return this;
    }

    public ConfiguracionInstrumentoVoBuilder wihtEstado(String estado) {
        this.estado = estado;
        return this;
    }

    public ConfiguracionInstrumentoVO build() {
        return new ConfiguracionInstrumentoVO(instrumento, estado);
    }
}
