package co.com.foundation.soaint.documentmanager.domain;

/**
 * Created by jrodriguez on 13/12/2016.
 */
public class DataTableCcdVO {

    private String codigoNombreSeccion;
    private String codigoNombreSubseccion;
    private String codigoNombreSerie;
    private String codigoNombreSubserie;
    private String tiposDocumentales;

    public DataTableCcdVO(String codigoNombreSeccion, String codigoNombreSubseccion, String codigoNombreSerie,
                          String codigoNombreSubserie, String tiposDocumentales) {
        super();
        this.codigoNombreSeccion = codigoNombreSeccion;
        this.codigoNombreSubseccion = codigoNombreSubseccion;
        this.codigoNombreSerie = codigoNombreSerie;
        this.codigoNombreSubserie = codigoNombreSubserie;
        this.tiposDocumentales = tiposDocumentales;
    }

    public String getCodigoNombreSeccion() {
        return codigoNombreSeccion;
    }

    public void setCodigoNombreSeccion(String codigoNombreSeccion) {
        this.codigoNombreSeccion = codigoNombreSeccion;
    }

    public String getCodigoNombreSubseccion() {
        return codigoNombreSubseccion;
    }

    public void setCodigoNombreSubseccion(String codigoNombreSubseccion) {
        this.codigoNombreSubseccion = codigoNombreSubseccion;
    }

    public String getCodigoNombreSubserie() {
        return codigoNombreSubserie;
    }

    public void setCodigoNombreSubserie(String codigoNombreSubserie) {
        this.codigoNombreSubserie = codigoNombreSubserie;
    }

    public String getCodigoNombreSerie() {
        return codigoNombreSerie;
    }

    public void setCodigoNombreSerie(String codigoNombreSerie) {
        this.codigoNombreSerie = codigoNombreSerie;
    }

    public String getTiposDocumentales() {
        return tiposDocumentales;
    }

    public void setTiposDocumentales(String tiposDocumentales) {
        this.tiposDocumentales = tiposDocumentales;
    }

    public void addTipoDocumental(String tiposDocumentales) {
        this.tiposDocumentales = this.tiposDocumentales.concat(tiposDocumentales);
    }

    @Override
    public String toString() {
        return "DataTableCcdVO{" +
                "codigoNombreSeccion='" + codigoNombreSeccion + '\'' +
                ", codigoNombreSubseccion='" + codigoNombreSubseccion + '\'' +
                ", codigoNombreSerie='" + codigoNombreSerie + '\'' +
                ", codigoNombreSubserie='" + codigoNombreSubserie + '\'' +
                ", tiposDocumentales='" + tiposDocumentales + '\'' +
                '}';
    }
}
