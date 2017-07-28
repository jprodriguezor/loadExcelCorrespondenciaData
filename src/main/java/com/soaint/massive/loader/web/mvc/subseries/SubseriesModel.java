package com.soaint.massive.loader.web.mvc.subseries;

import com.soaint.massive.loader.web.domain.SubserieVO;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jrodriguez on 20/09/2016.
 */
@Component
@Scope("session")
public class SubseriesModel implements Serializable {

    private List<SubserieVO> subserieList;

    public SubseriesModel() {
        subserieList = new ArrayList<>();
    }

    public List<SubserieVO> getSubserieList() {
        return subserieList;
    }

    public void setSubserieList(List<SubserieVO> subserieList) {
        this.subserieList = subserieList;
    }

    public void clear() {
        subserieList.clear();
    }
}
