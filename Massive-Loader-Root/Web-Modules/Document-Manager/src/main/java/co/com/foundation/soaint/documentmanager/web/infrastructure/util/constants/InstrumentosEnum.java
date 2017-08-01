package co.com.foundation.soaint.documentmanager.web.infrastructure.util.constants;

/**
 * Created by jrodriguez on 27/10/2016.
 */
public enum InstrumentosEnum {

    ORGANIGRAMA ("ORGANIGRAMA"),
    CCD("CCD"),
    TRD("TRD");

    private final String name;

    private InstrumentosEnum( String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

}
