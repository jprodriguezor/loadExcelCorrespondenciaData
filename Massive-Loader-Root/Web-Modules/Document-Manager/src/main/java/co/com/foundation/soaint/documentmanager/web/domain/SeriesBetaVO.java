package co.com.foundation.soaint.documentmanager.web.domain;

/**
 * Created by administrador_1 on 04/09/2016.
 */

public class SeriesBetaVO {

    private String name;
    private String domain;
    private String alias;
    private boolean isPrivate;
    private String description;
    private int priority;

    public SeriesBetaVO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setIsPrivate(boolean isPrivate) {
        this.isPrivate = isPrivate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "SeriesBetaVO{" +
                "name='" + name + '\'' +
                ", domain='" + domain + '\'' +
                ", alias='" + alias + '\'' +
                ", isPrivate=" + isPrivate +
                ", description='" + description + '\'' +
                ", priority=" + priority +
                '}';
    }
}
