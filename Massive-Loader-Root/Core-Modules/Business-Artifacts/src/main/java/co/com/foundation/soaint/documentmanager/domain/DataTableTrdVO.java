package co.com.foundation.soaint.documentmanager.domain;

/**
 * Created by jrodriguez on 3/12/2016.
 */
public class DataTableTrdVO {

    private String codigo;
    private String instrumentos;
    private Long archivoGestion;
    private Long archivoCentral;
    private String ct;
    private String e;
    private String m;
    private String s;
    private String d;
    private String procedimientos;

    public DataTableTrdVO(String codigo, String instrumentos, Long archivoGestion, Long archivoCentral, String ct,
                          String e, String m, String s, String d, String procedimientos) {
        super();
        this.codigo = codigo;
        this.instrumentos = instrumentos;
        this.archivoGestion = archivoGestion;
        this.archivoCentral = archivoCentral;
        this.ct = ct;
        this.e = e;
        this.m = m;
        this.s = s;
        this.d = d;
        this.procedimientos = procedimientos;
    }


    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getInstrumentos() {
        return instrumentos;
    }

    public void setInstrumentos(String instrumentos) {
        this.instrumentos = instrumentos;
    }

    public Long getArchivoGestion() {
        return archivoGestion;
    }

    public void setArchivoGestion(Long archivoGestion) {
        this.archivoGestion = archivoGestion;
    }

    public Long getArchivoCentral() {
        return archivoCentral;
    }

    public void setArchivoCentral(Long archivoCentral) {
        this.archivoCentral = archivoCentral;
    }

    public String getCt() {
        if(ct == null)
            ct ="";
        return ct;
    }

    public void setCt(String ct) {
        this.ct = ct;
    }

    public String getE() {
        if(e == null)
            e ="";
        return e;
    }

    public void setE(String e) {
        this.e = e;
    }

    public String getM() {
        if(m == null)
            m ="";
        return m;
    }

    public void setM(String m) {
        this.m = m;
    }

    public String getS() {
        if(s == null)
            s ="";
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public String getD() {
        if(d == null)
            d ="";
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }

    public String getProcedimientos() {
        return procedimientos;
    }

    public void setProcedimientos(String procedimientos) {
        this.procedimientos = procedimientos;
    }

    public void addInstrumento(String instrumentos) {
        this.instrumentos = this.instrumentos.concat(instrumentos);
    }

    @Override
    public String toString() {
        return "DataTableTrdVO{" +
                "codigo='" + codigo + '\'' +
                ", instrumentos='" + instrumentos + '\'' +
                ", archivoGestion=" + archivoGestion +
                ", archivoCentral=" + archivoCentral +
                ", ct='" + ct + '\'' +
                ", e='" + e + '\'' +
                ", m='" + m + '\'' +
                ", s='" + s + '\'' +
                ", d='" + d + '\'' +
                ", procedimientos='" + procedimientos + '\'' +
                '}';
    }
}
