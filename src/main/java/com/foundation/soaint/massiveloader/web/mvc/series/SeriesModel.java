package com.foundation.soaint.massiveloader.web.mvc.series;

import com.foundation.soaint.massiveloader.web.infrastructure.common.SelectItem;
import com.foundation.soaint.massiveloader.deprecated.web.domain.SerieVO;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jprodriguez on 02/09/2016.
 */


@Component
@Scope("session")
public class SeriesModel implements Serializable {

    private List<SerieVO> seriesList;
    private List<SelectItem> motivosCreacionList;

    public SeriesModel() {

        seriesList = new ArrayList<>();
        motivosCreacionList = new ArrayList<>();
    }

    public List<SerieVO> getSeriesList() {
        return seriesList;
    }

    public void setSeriesList(List<SerieVO> seriesList) {
        this.seriesList = seriesList;
    }

    public List<SelectItem> getMotivosCreacionList() {
        return motivosCreacionList;
    }

    public void setMotivosCreacionList(List<SelectItem> motivosCreacionList) {
        this.motivosCreacionList = motivosCreacionList;
    }

    public void clear() {
        seriesList.clear();
        motivosCreacionList.clear();
    }
}
