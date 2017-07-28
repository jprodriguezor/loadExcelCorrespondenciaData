package com.soaint.massive.loader.web.mvc.tipologiadocumental;

import com.soaint.massive.loader.web.domain.TipologiaDocVO;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jrodriguez on 22/09/2016.
 */
@Component
@Scope("session")
public class TipologiaDocModel {

    private List<TipologiaDocVO> tipologiaDocList;

    public TipologiaDocModel(){
        tipologiaDocList =new ArrayList<>();
    }

    public List<TipologiaDocVO> getTipologiaDocList() {
        return tipologiaDocList;
    }

    public void setTipologiaDocList(List<TipologiaDocVO> tipologiaDocList) {
        this.tipologiaDocList = tipologiaDocList;
    }

    public void clear(){
        getTipologiaDocList().clear();
    }
}
