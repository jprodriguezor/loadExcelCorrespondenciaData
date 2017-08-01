package co.com.foundation.soaint.documentmanager.web.infrastructure.builder.generic;

import co.com.foundation.soaint.documentmanager.web.domain.CategoryVO;
import co.com.foundation.soaint.infrastructure.builder.Builder;
/**
 * Created by administrador_1 on 01/09/2016.
 */

public class CategoryBuilder implements Builder<CategoryVO> {

    private String name;
    private String serial;
    private int level;
    private boolean isPrivate;

    private CategoryBuilder() {
    }

    public static CategoryBuilder newBuilder() {
        return new CategoryBuilder();
    }

    public CategoryBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public CategoryBuilder withSerial(String serial) {
        this.serial = serial;
        return this;
    }

    public CategoryBuilder withLevel(int level) {
        this.level = level;
        return this;
    }

    public CategoryBuilder isPrivate(boolean isPrivate) {
        this.isPrivate = isPrivate;
        return this;
    }

    public CategoryVO build() {
        return new CategoryVO(name, serial, level, isPrivate);
    }

}


