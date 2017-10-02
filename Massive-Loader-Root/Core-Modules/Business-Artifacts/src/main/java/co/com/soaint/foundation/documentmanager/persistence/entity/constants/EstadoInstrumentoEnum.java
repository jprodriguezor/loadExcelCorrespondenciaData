package co.com.soaint.foundation.documentmanager.persistence.entity.constants;

/**
 * Created by jrodriguez on 27/10/2016.
 */
public enum EstadoInstrumentoEnum {

    SIN_ESTDO (0,"SIN_ESTADO"),
    CONFIGURACION(1, "CONFIGURACION"),
    PUBLICADO(2, "PUBLICADO");

    private final String name;
    private final int id;

    private EstadoInstrumentoEnum(int id, String name) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;

    }
}
