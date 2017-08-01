package co.com.foundation.soaint.documentmanager.web.mvc.trd;

import co.com.foundation.soaint.documentmanager.persistence.entity.TvsOrganigramaAdministrativo;
import co.com.foundation.soaint.documentmanager.web.infrastructure.common.SelectItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jrodriguez on 20/11/2016.
 */
@Component
@Scope("session")
public class TrdModel {

    private List<SelectItem> versionesTrdOfcProd;

    public  TrdModel(){
        versionesTrdOfcProd =new ArrayList<>();

    }

    public List<SelectItem> getVersionesTrdOfcProd() {
        return versionesTrdOfcProd;
    }

    public void setVersionesTrdOfcProd(List<SelectItem> versionesTrdOfcProd) {
        this.versionesTrdOfcProd = versionesTrdOfcProd;
    }
}
