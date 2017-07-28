package co.com.foundation.soaint.documentmanager.web.infrastructure.util.constants;

/**
 * Created by jrodriguez on 9/11/2016.
 */
public enum EstadoSubserieEnum {

    ACTIVO      (1,"Activo"),
    INACTIVO    (0, "Inactivo");

    private final String name;
    private final int id;

    private EstadoSubserieEnum(int id, String name) {
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
