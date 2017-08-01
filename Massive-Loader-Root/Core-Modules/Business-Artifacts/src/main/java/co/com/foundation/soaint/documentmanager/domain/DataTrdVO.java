package co.com.foundation.soaint.documentmanager.domain;

public class DataTrdVO {

    private String codigo;
    private String instrumentos;
    private Long archivoGestion;
    private Long archivoCentral;
    private int disposicionFinal;
    private String procedimientos;

    public DataTrdVO(String codigo, String instrumentos, Long archivoGestion, Long archivoCentral, int disposicionFinal, String procedimientos) {
        this.codigo = codigo;
        this.instrumentos = instrumentos;
        this.archivoGestion = archivoGestion;
        this.archivoCentral = archivoCentral;
        this.disposicionFinal = disposicionFinal;
        this.procedimientos = procedimientos;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getInstrumentos() {
        return instrumentos;
    }

    public Long getArchivoGestion() {
        return archivoGestion;
    }

    public Long getArchivoCentral() {
        return archivoCentral;
    }

    public int getDisposicionFinal() {
        return disposicionFinal;
    }

    public String getProcedimientos() {
        return procedimientos;
    }

    public void addInstrumento(String instrumentos) {
        this.instrumentos = this.instrumentos.concat(instrumentos);
    }

    @Override
    public String toString() {
        return "{" +
                "codigo='" + codigo + '\'' +
                ", instrumentos='" + instrumentos + '\'' +
                ", archivoGestion=" + archivoGestion +
                ", archivoCentral=" + archivoCentral +
                ", disposicionFinal=" + disposicionFinal +
                ", procedimientos='" + procedimientos + '\'' +
                '}';
    }
}
