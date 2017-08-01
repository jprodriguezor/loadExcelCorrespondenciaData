package co.com.foundation.soaint.documentmanager.web.infrastructure.util.constants;

/**
 * Created by jrodriguez on 21/09/2016.
 */
public enum EstadoSeriesEmun {

    ACTIVO      (1,"Activo"),
    INACTIVO    (0, "Inactivo");

    private final String name;
    private final int id;

    private EstadoSeriesEmun(int id, String name) {
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
