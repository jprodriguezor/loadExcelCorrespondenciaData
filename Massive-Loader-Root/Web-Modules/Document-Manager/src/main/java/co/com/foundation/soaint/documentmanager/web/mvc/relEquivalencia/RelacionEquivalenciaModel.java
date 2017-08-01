package co.com.foundation.soaint.documentmanager.web.mvc.relEquivalencia;

import co.com.foundation.soaint.documentmanager.web.domain.RelEquiVO;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ADMIN on 01/12/2016.
 */
@Component
@Scope("session")
public class RelacionEquivalenciaModel implements Serializable {
    private List<RelEquiVO> relEquiList;


    public RelacionEquivalenciaModel() {
        relEquiList = new ArrayList<>();
    }

    public List<RelEquiVO> getRelEquiList() {
        return relEquiList;
    }

    public void setRelEquiList(List<RelEquiVO> relEquiList) {
        this.relEquiList = relEquiList;
    }

    public void clear() {
        relEquiList.clear();
    }
}
