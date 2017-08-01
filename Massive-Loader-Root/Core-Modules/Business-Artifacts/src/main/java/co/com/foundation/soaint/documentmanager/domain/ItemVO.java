package co.com.foundation.soaint.documentmanager.domain;

import java.io.Serializable;

/**
 * Created by administrador_1 on 03/09/2016.
 */
public class ItemVO implements Serializable {

    private String label;
    private Object value;

    public ItemVO(String label, Object value) {
        this.label = label;
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public Object getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "ItemVO{" +
                "label='" + label + '\'' +
                ", value=" + value +
                '}';
    }
}
