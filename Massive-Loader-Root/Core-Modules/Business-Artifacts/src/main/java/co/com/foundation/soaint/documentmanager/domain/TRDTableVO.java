package co.com.foundation.soaint.documentmanager.domain;

import java.util.List;

public class TRDTableVO {

    private String unidadAdministrativa;
    private String oficinaProductora;
    private List<DataTrdVO> rows;
    private boolean valida;

    public TRDTableVO(String unidadAdministrativa, String oficinaProductora, List<DataTrdVO> rows, boolean valida) {
        this.unidadAdministrativa = unidadAdministrativa;
        this.oficinaProductora = oficinaProductora;
        this.rows = rows;
        this.valida = valida;
    }

    public boolean isValida() {
        return valida;
    }

    public String getUnidadAdministrativa() {
        return unidadAdministrativa;
    }

    public String getOficinaProductora() {
        return oficinaProductora;
    }

    public List<DataTrdVO> getRows() {
        return rows;
    }

    @Override
    public String toString() {
        return "TRDTableVO{" +
                "unidadAdministrativa='" + unidadAdministrativa + '\'' +
                ", oficinaProductora='" + oficinaProductora + '\'' +
                ", rows=" + rows +
                '}';
    }
}
