package com.foundation.soaint.massiveloader.deprecated.web.infrastructure.builder.generic;

import com.foundation.soaint.massiveloader.web.infrastructure.common.TableResponse;
import co.com.foundation.soaint.infrastructure.builder.Builder;

import java.util.List;

/**
 * Created by administrador_1 on 18/09/2016.
 */
public class TableResponseBuilder<D> implements Builder<TableResponse> {

    private List<D> data;
    private int iTotalRecords;
    private int iTotalDisplayRecords;

    private TableResponseBuilder() {
    }

    public static TableResponseBuilder newBuilder() {
        return new TableResponseBuilder();
    }

    public TableResponseBuilder withData(List<D> data) {
        this.data = data;
        return this;
    }

    public TableResponseBuilder withiTotalRecords(int iTotalRecords) {
        this.iTotalRecords = iTotalRecords;
        return this;
    }

    public TableResponseBuilder withiTotalDisplayRecords(int iTotalDisplayRecords) {
        this.iTotalDisplayRecords = iTotalDisplayRecords;
        return this;
    }

    @Override
    public TableResponse build() {
        return new TableResponse(data, iTotalRecords, iTotalDisplayRecords);
    }

}
