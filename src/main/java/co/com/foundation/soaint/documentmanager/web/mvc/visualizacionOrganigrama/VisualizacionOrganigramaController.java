package co.com.foundation.soaint.documentmanager.web.mvc.visualizacionOrganigrama;

import co.com.foundation.soaint.documentmanager.business.organigrama.interfaces.OrganigramaManagerProxy;
import co.com.foundation.soaint.documentmanager.domain.OrganigramaTreeVO;
import co.com.foundation.soaint.documentmanager.web.mvc.organigrama.OrganigramaModel;
import co.com.foundation.soaint.infrastructure.exceptions.BusinessException;
import co.com.foundation.soaint.infrastructure.exceptions.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by sarias on 15/11/2016.
 */

@Controller
@Scope("request")
@RequestMapping(value = "/visualOrganigrama")
public class VisualizacionOrganigramaController {

    @Autowired
    private OrganigramaModel model;
    @Autowired
    private OrganigramaManagerProxy boundary;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String init() throws BusinessException, SystemException {
        return "visualizacionOrganigrama/visualizacionOrganigrama-ui";
    }

    @ResponseBody
    @RequestMapping(value = "/listOrganigramaTreeVersion", method = RequestMethod.POST)
    public OrganigramaTreeVO organigramaTreeVersion(@RequestParam String valVersion) throws SystemException, BusinessException {
        model.setTree(OrganigramaTreeVO.newInstance(boundary.consultarOrganigramaPorVersion(valVersion)));
        return model.getTree();
    }








}
