package co.com.foundation.soaint.documentmanager.web.infrastructure.builder.generic;

import co.com.foundation.soaint.documentmanager.web.infrastructure.common.SelectItem;
import co.com.foundation.soaint.infrastructure.builder.Builder;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by administrador_1 on 03/09/2016.
 */

public class SelectItemBuilder implements Builder<List<SelectItem>> {

    private List<SelectItem> items;

    private SelectItemBuilder() {
        items = new LinkedList<>();
    }

    public static SelectItemBuilder newBuilder() {
        return new SelectItemBuilder();
    }

    public SelectItemBuilder addItem(String label, Object value) {
        items.add(new SelectItem(label, value));
        return this;
    }

    public List<SelectItem> build() {
        return Collections.unmodifiableList(items);
    }
}
