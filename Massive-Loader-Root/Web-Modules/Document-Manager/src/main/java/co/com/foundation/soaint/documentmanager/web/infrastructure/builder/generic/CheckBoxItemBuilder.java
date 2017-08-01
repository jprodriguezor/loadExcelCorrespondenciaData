package co.com.foundation.soaint.documentmanager.web.infrastructure.builder.generic;

import co.com.foundation.soaint.documentmanager.web.infrastructure.common.CheckBoxItem;
import co.com.foundation.soaint.infrastructure.builder.Builder;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by administrador_1 on 03/09/2016.
 */

public class CheckBoxItemBuilder implements Builder<List<CheckBoxItem>> {

    private List<CheckBoxItem> items;

    private CheckBoxItemBuilder() {
        items = new LinkedList<>();
    }

    public static CheckBoxItemBuilder newBuilder() {
        return new CheckBoxItemBuilder();
    }

    public CheckBoxItemBuilder addItem(String name, String label, int tabIndex) {
        items.add(new CheckBoxItem(name, label, tabIndex));
        return this;
    }

    public List<CheckBoxItem> build() {
        return Collections.unmodifiableList(items);
    }
}
