package co.com.foundation.soaint.documentmanager.web.domain;

/**
 * Created by jrodriguez on 27/10/2016.
 */
public class ConfiguracionInstrumentoVO {

    private String instrumento;
    private String   estado;

    public ConfiguracionInstrumentoVO() {
    }

    public ConfiguracionInstrumentoVO(String instrumento, String estado) {
        this.instrumento = instrumento;
        this.estado = estado;
    }

    public String getInstrumento() {
        return instrumento;
    }

    public String getEstado() {
        return estado;
    }

    @Override
    public String toString() {
        return "ConfiguracionInstrumentoVO{" +
                "instrumento='" + instrumento + '\'' +
                ", estado=" + estado +
                '}';
    }
}
