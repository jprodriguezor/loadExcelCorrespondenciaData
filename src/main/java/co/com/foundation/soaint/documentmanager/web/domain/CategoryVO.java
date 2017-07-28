package co.com.foundation.soaint.documentmanager.web.domain;

/**
 * Created by administrador_1 on 02/09/2016.
 */

public class CategoryVO {

    private final String name;
    private final String serial;
    private final int level;
    private final boolean isPrivate;

    public CategoryVO(String name, String serial, int level, boolean isPrivate) {
        this.name = name;
        this.serial = serial;
        this.level = level;
        this.isPrivate = isPrivate;
    }

    public String getName() {
        return name;
    }

    public String getSerial() {
        return serial;
    }

    public int getLevel() {
        return level;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

}
