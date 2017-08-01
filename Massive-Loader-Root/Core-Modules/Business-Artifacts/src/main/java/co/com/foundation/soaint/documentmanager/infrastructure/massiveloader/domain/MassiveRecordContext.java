package co.com.foundation.soaint.documentmanager.infrastructure.massiveloader.domain;

public class MassiveRecordContext<T> {

    private final String raw;
    private final T domainItem;

    public MassiveRecordContext(final String raw, final T domainItem) {
        this.raw = raw;
        this.domainItem = domainItem;
    }

    public String getRaw() {
        return raw;
    }

    public T getDomainItem() {
        return domainItem;
    }

}
