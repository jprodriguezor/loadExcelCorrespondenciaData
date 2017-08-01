package co.com.foundation.soaint.documentmanager.web.infrastructure.common;

import java.util.List;

/**
 * Created by administrador_1 on 18/09/2016.
 */
public class TableResponse<T> {

    private List<T> data;
    private int iTotalRecords;
    private int iTotalDisplayRecords;

    public TableResponse(List<T> data, int iTotalRecords, int iTotalDisplayRecords) {
        this.data = data;
        this.iTotalRecords = iTotalRecords;
        this.iTotalDisplayRecords = iTotalDisplayRecords;
    }

    public List<T> getData() {
        return data;
    }

    public int getiTotalRecords() {
        return iTotalRecords;
    }

    public int getiTotalDisplayRecords() {
        return iTotalDisplayRecords;
    }

}
