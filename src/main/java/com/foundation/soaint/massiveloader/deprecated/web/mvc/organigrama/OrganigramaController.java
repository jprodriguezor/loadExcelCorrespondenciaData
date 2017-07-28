package com.foundation.soaint.massiveloader.deprecated.web.mvc.organigrama;

import co.com.foundation.soaint.documentmanager.business.configuracion.interfaces.ConfiguracionInstrumentosMangerProxy;
import co.com.foundation.soaint.documentmanager.business.organigrama.interfaces.ConfigOrganigramaManagerProxy;
import co.com.foundation.soaint.documentmanager.business.organigrama.interfaces.OrganigramaManagerProxy;
import co.com.foundation.soaint.documentmanager.domain.ItemVO;
import co.com.foundation.soaint.documentmanager.domain.OrganigramaItemVO;
import co.com.foundation.soaint.documentmanager.domain.OrganigramaTreeVO;
import co.com.foundation.soaint.infrastructure.common.MessageUtil;
import co.com.foundation.soaint.infrastructure.exceptions.BusinessException;
import co.com.foundation.soaint.infrastructure.exceptions.SystemException;
import co.com.foundation.soaint.documentmanager.persistence.entity.TvsConfigOrgAdministrativo;
import co.com.foundation.soaint.documentmanager.persistence.entity.constants.EstadoInstrumentoEnum;
import com.foundation.soaint.massiveloader.web.domain.OrganigramaVO;
import co.com.foundation.soaint.documentmanager.infrastructure.builder.entity.EntityAdmTvsOrganigramaBuilder;
import com.foundation.soaint.massiveloader.web.infrastructure.builder.generic.HTTPResponseBuilder;
import com.foundation.soaint.massiveloader.web.infrastructure.builder.generic.SelectItemBuilder;
import com.foundation.soaint.massiveloader.web.infrastructure.common.HTTPResponse;
import com.foundation.soaint.massiveloader.web.infrastructure.common.HTTPValidResponse;
import com.foundation.soaint.massiveloader.web.infrastructure.common.SelectItem;
import com.foundation.soaint.massiveloader.web.infrastructure.util.constants.InstrumentosEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NoResultException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by jrodriguez on 24/10/2016.
 */

@Controller
@Scope("request")
@RequestMapping(value = "/organigrama")
public class OrganigramaController {

    @Autowired
    private OrganigramaModel model;
    @Autowired
    private OrganigramaManagerProxy boundary;
    @Autowired
    private ConfigOrganigramaManagerProxy configBoundary;
    @Autowired
    private ConfiguracionInstrumentosMangerProxy configInstrumentoBoundary;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String init() throws BusinessException, SystemException {
        try {
            model.setTree(OrganigramaTreeVO.newInstance(configBoundary.consultarOrganigrama()));
            System.out.println(model.getTree().getCore());
            return "organigrama/organigramaAdmin-ui";

        } catch (Throwable ex) {
            return "organigrama/organigramaAdmin-ui";

        }
    }

    @ResponseBody
    @RequestMapping(value = "/getEstadotOrganigrama", method = RequestMethod.GET)
    public ItemVO getEstadoOrganigrama() throws SystemException, BusinessException {
        return configInstrumentoBoundary.consultarEstadoInstrumento(InstrumentosEnum.ORGANIGRAMA.getName());
    }

    @ResponseBody
    @RequestMapping(value = "/listOrganigramaTree", method = RequestMethod.GET)
    public OrganigramaTreeVO OrganigramaTree() throws SystemException, BusinessException {
        return model.getTree();
    }

    //[ lista de motivos ]
    @ResponseBody
    @RequestMapping(value = "/listOrganigrama", method = RequestMethod.GET)
    public List<SelectItem> getOrganigramaList() throws SystemException, BusinessException {
        try {
            model.getOrganigramaNodeList().clear();


            List<OrganigramaItemVO> results = configBoundary.listarElementosOrganigrama();
            results.stream().forEach((OrganigramaItemVO item) -> {
                model.getOrganigramaNodeList()
                        .addAll(SelectItemBuilder.newBuilder()
                                .addItem(item.getCodOrg() + " - " + item.getNomOrg(),
                                        item.getIdeOrgaAdmin() + "-" + item.getNivel()).build());
            });

            SelectItem item = new SelectItem("NO APLICA", "N/A");
            model.getOrganigramaNodeList().add(item);
            return model.getOrganigramaNodeList();
        } catch (NoResultException n) {
            SelectItem item = new SelectItem("NO APLICA", "N/A");
            model.getOrganigramaNodeList().add(item);
            return model.getOrganigramaNodeList();
        }
    }


    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.POST)
    public HTTPResponse processOrganigrama(@RequestBody OrganigramaVO organigrama) throws BusinessException, SystemException {

        TvsConfigOrgAdministrativo padre = null;
        Integer nivel = null;
        if (organigrama.getIdeOrgaAdminPadre().equals("N/A")) {

            System.out.println("Selected " + organigrama.getBotonSelected());
            if (organigrama.getIdeOrgaAdminPadre().equals("N/A") && organigrama.getBotonSelected().equals("Crear")) {
                configBoundary.cleanOrganigrama();
            }

            nivel = 0;

        } else {
            String[] arrayDataPadre = organigrama.getIdeOrgaAdminPadre().split("-");

            padre = EntityAdmTvsOrganigramaBuilder.newBuilder()
                    .withIdeOrgaAdmin(Long.parseLong(arrayDataPadre[0]))
                    .build();

            String valNivel = arrayDataPadre[1].equals("null") ? "0" : arrayDataPadre[1];
            nivel = Integer.parseInt(valNivel) + 1;
        }

        EntityAdmTvsOrganigramaBuilder builder = EntityAdmTvsOrganigramaBuilder.newBuilder()
                .withIdeOrgaAdmin(organigrama.getIdeOrgaAdmin())
                .withCodOrg(organigrama.getCodOrg())
                .withNomOrg(organigrama.getNomOrg().toUpperCase().trim())
                .withIndEsActivo(organigrama.getIndEsActivo())
                .withDescOrg(organigrama.getDescOrg().toUpperCase())
                .withIdeOrgaAdminPadre(padre)
                .withValSistema("l")
                .withCodNivel(nivel);

        boolean flujoActualizacion = organigrama.getIdeOrgaAdmin() != null;
        String mensaje = null;

        if (flujoActualizacion) {
            mensaje = MessageUtil.getMessage("organigrama.organigrama_update_successfully");
            builder.withFecCambio(new Date());

            configBoundary.updateOrganigrama(builder.build());
        } else {
            mensaje = MessageUtil.getMessage("organigrama.organigrama_created_successfully");
            builder.withFecCreacion(new Date());
            configBoundary.createOrganigrama(builder.build());
        }

        init();
        return HTTPResponseBuilder.newBuilder()
                .withSuccess(true)
                .withMessage(mensaje)
                .build();
    }

    @ResponseBody
    @RequestMapping(value = "/publicarVersionOrganigrama/", method = RequestMethod.GET)
    public HTTPResponse publicarVersionOrganigrama() throws BusinessException, SystemException {
        boundary.publicarOrganigrama();
        ItemVO item = new ItemVO(InstrumentosEnum.ORGANIGRAMA.getName(), EstadoInstrumentoEnum.PUBLICADO.getId());
        configInstrumentoBoundary.setEstadoInstrumento(item);
        return HTTPResponseBuilder.newBuilder()
                .withSuccess(true)
                .withMessage(MessageUtil.getMessage("instrumentos.instrumentos.public.successfully"))
                .build();
    }

    @ResponseBody
    @RequestMapping(value = "/validateCodOrganigrama", method = RequestMethod.POST)
    public HTTPValidResponse validateCodOrganigrama(@RequestParam Map<String, String> requestParams) throws BusinessException, SystemException {

        if (requestParams.get("ideOrgaAdmin").equals("")) {
            return HTTPValidResponse.newInstance(!configBoundary.organigramaExistByCode(requestParams.get("codOrg")));
        } else {
            return HTTPValidResponse.newInstance(true);
        }
    }


    @ResponseBody
    @RequestMapping(value = "/validateNomOrganigrama", method = RequestMethod.POST)
    public HTTPValidResponse validateNomOrganigrama(@RequestParam Map<String, String> requestParams) throws BusinessException, SystemException {
        if (!requestParams.get("ideOrgaAdmin").equals("")) {
            return HTTPValidResponse.newInstance(true);
        } else {
            return HTTPValidResponse.newInstance(!configBoundary.organigramaExistByName(requestParams.get("nomOrg").trim()));
        }
    }


}
