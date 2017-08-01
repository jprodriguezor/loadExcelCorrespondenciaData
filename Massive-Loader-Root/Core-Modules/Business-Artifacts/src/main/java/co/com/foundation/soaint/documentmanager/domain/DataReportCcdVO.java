package co.com.foundation.soaint.documentmanager.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jrodriguez on 13/12/2016.
 */
public class DataReportCcdVO {

    private String fechaVersion;
    private String version;
    private ArrayList<DataTableCcdVO> rows;

    public DataReportCcdVO(String fechaVersion, String version, ArrayList<DataTableCcdVO> rows) {
        super();
        this.fechaVersion = fechaVersion;
        this.version = version;
        this.rows = rows;
    }

    public String getFechaVersion() {
        return fechaVersion;
    }

    public void setFechaVersion(String fechaVersion) {
        this.fechaVersion = fechaVersion;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public ArrayList<DataTableCcdVO> getRows() {
        return rows;
    }

    public void setRows(ArrayList<DataTableCcdVO> rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "DataReportCcdVO{" +
                "fechaVersion='" + fechaVersion + '\'' +
                ", version='" + version + '\'' +
                ", rows=" + rows +
                '}';
    }
}
