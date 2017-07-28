package co.com.foundation.soaint.documentmanager.web.infrastructure.util.constants;

/**
 * Created by jrodriguez on 21/09/2016.
 */
public enum EstadoCaracteristicaEnum {
    ON(1L, "on"),
    OFF(0L, "off");

    private final String name;
    private final Long id;

    private EstadoCaracteristicaEnum(Long id, String name) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;

    }
}

