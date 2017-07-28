package co.com.foundation.soaint.documentmanager.web.infrastructure.util.constants;

/**
 * Created by jrodriguez on 10/11/2016.
 */
public enum EstadoTabRepDocEmun {
    ACTIVO      (1,"Activo"),
    INACTIVO    (0, "Inactivo");

    private final String name;
    private final int id;

    private EstadoTabRepDocEmun(int id, String name) {
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
