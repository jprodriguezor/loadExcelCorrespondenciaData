package co.com.foundation.soaint.documentmanager.web.mvc.visualizacionOrganigrama;

import co.com.foundation.soaint.documentmanager.domain.OrganigramaTreeVO;
import co.com.foundation.soaint.documentmanager.web.domain.ConfiguracionInstrumentoVO;
import co.com.foundation.soaint.documentmanager.web.infrastructure.common.SelectItem;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sarias on 15/11/2016.
 */

@Component
@Scope("session")
public class VisualizacionOrganigramaModel {

    private OrganigramaTreeVO tree;
    private ConfiguracionInstrumentoVO instrumento;
    private String estado;
    private List<SelectItem> organigramaNodeList;

    public VisualizacionOrganigramaModel(){
        instrumento =new ConfiguracionInstrumentoVO();
        organigramaNodeList =new ArrayList<>();
    }

    public OrganigramaTreeVO getTree() {
        return tree;
    }

    public void setTree(OrganigramaTreeVO tree) {
        this.tree = tree;
    }

    public ConfiguracionInstrumentoVO getInstrumento() {
        return instrumento;
    }

    public void setInstrumento(ConfiguracionInstrumentoVO instrumento) {
        this.instrumento = instrumento;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<SelectItem> getOrganigramaNodeList() {
        return organigramaNodeList;
    }

    public void setOrganigramaNodeList(List<SelectItem> organigramaNodeList) {
        this.organigramaNodeList = organigramaNodeList;
    }

}
