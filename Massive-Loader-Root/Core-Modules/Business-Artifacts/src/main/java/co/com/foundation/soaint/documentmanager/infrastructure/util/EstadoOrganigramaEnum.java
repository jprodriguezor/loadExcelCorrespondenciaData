package co.com.foundation.soaint.documentmanager.infrastructure.util;

/**
 * Created by jrodriguez on 9/11/2016.
 */
public enum EstadoOrganigramaEnum {
    ACTIVO(1, "1"),
    INACTIVO(0, "0"), ;

    private final String name;
    private final int id;

    private EstadoOrganigramaEnum(int id, String name) {
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
