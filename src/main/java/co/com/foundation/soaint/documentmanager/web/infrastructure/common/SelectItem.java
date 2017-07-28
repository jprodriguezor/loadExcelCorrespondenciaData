package co.com.foundation.soaint.documentmanager.web.infrastructure.common;

import java.io.Serializable;

/**
 * Created by administrador_1 on 03/09/2016.
 */
public class SelectItem implements Serializable {

    private String label;
    private Object value;

    public SelectItem(String label, Object value) {
        this.label = label;
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public Object getValue() {
        return value;
    }
}
