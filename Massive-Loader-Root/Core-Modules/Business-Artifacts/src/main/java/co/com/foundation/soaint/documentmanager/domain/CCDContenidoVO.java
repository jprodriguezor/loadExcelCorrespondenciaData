package co.com.foundation.soaint.documentmanager.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ADMIN on 30/11/2016.
 */
public class CCDContenidoVO {
    private String unidadAdministrativa;
    private String oficinaProductora;
    private String codNombreSerie;
    private String codNombreSubserie;
    private List<TiposDocCCDVO> listTiposDoc;

    public CCDContenidoVO(String unidadAdministrativa, String oficinaProductora, String codNombreSerie, String codNombreSubserie, List<TiposDocCCDVO> rows) {
        this.unidadAdministrativa = unidadAdministrativa;
        this.oficinaProductora = oficinaProductora;
        this.codNombreSerie = codNombreSerie;
        this.codNombreSubserie = codNombreSubserie;
        this.listTiposDoc = listTiposDoc;
    }

    public CCDContenidoVO() {
         this.listTiposDoc = new ArrayList<>();
    }

    public String getUnidadAdministrativa() {
        return unidadAdministrativa;
    }

    public void setUnidadAdministrativa(String unidadAdministrativa) {
        this.unidadAdministrativa = unidadAdministrativa;
    }

    public String getOficinaProductora() {
        return oficinaProductora;
    }

    public void setOficinaProductora(String oficinaProductora) {
        this.oficinaProductora = oficinaProductora;
    }

    public String getCodNombreSerie() {
        return codNombreSerie;
    }

    public void setCodNombreSerie(String codNombreSerie) {
        this.codNombreSerie = codNombreSerie;
    }

    public String getCodNombreSubserie() {
        return codNombreSubserie;
    }

    public void setCodNombreSubserie(String codNombreSubserie) {
        this.codNombreSubserie = codNombreSubserie;
    }

    public List<TiposDocCCDVO> getRows() {
        return listTiposDoc;
    }

    public void setRows(TiposDocCCDVO rows) {
        this.listTiposDoc.add(rows);
    }

    @Override
    public String toString() {
        return "CCDContenidoVO{" + "unidadAdministrativa=" + unidadAdministrativa + ", oficinaProductora=" + oficinaProductora + ", codNombreSerie=" + codNombreSerie + ", codNombreSubserie=" + codNombreSubserie + ", listTiposDoc=" + listTiposDoc + '}';
    }

   
}
