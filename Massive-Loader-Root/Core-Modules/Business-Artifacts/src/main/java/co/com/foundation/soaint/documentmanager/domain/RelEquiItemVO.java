package co.com.foundation.soaint.documentmanager.domain;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ADMIN on 12/12/2016.
 */
public class RelEquiItemVO {

    private String value;
    private String label;
    private BigInteger aux;
    private String version;


    public RelEquiItemVO() {
    }

    public RelEquiItemVO(String cod, String nombreCod) {

        this.value = cod;
        this.label = nombreCod;
    }

    public RelEquiItemVO(String cod, String nombreCod, String version){
        this.value = cod;
        this.label = nombreCod;
        this.version = version;
    }

    public RelEquiItemVO(String value, String label, BigInteger aux) {
        this.value = value;
        this.label = label;
        this.aux = aux;
    }

    public RelEquiItemVO(String valVersion, BigInteger numVersion, Date fecha){
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy h:mm a");
        this.label = valVersion+"-"+formatoFecha.format(fecha);
        this.value =  numVersion.toString();


    }

    public RelEquiItemVO(BigInteger idUnitAdmin, String nomOrg, String  valVersion){
        this.label = nomOrg;
        this.version = valVersion;
        this.aux = idUnitAdmin;

    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public BigInteger getAux() {
        return aux;
    }

    public void setAux(BigInteger aux) {
        this.aux = aux;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "RelEquiItemVO{" +
                "value='" + value + '\'' +
                ", label='" + label + '\'' +
                ", aux=" + aux +
                ", version='" + version + '\'' +
                '}';
    }
}
