package co.com.foundation.soaint.documentmanager.web.infrastructure.common;

import java.io.Serializable;

/**
 * Created by administrador_1 on 03/09/2016.
 */
public class CheckBoxItem implements Serializable {

    private final String name;
    private final String label;
    private final int tabIndex;

    public CheckBoxItem(String name, String label, int tabIndex) {
        this.name = name;
        this.label = label;
        this.tabIndex = tabIndex;
    }

    public String getName() {
        return name;
    }

    public String getLabel() {
        return label;
    }

    public int getTabIndex() {
        return tabIndex;
    }
}
