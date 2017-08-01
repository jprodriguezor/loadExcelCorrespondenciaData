/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.foundation.soaint.documentmanager.web.mvc.cargaMasiva;

import co.com.foundation.soaint.documentmanager.business.massiveloader.interfaces.MassiveLoaderManagerProxy;
import co.com.foundation.soaint.infrastructure.exceptions.BusinessException;
import co.com.foundation.soaint.infrastructure.exceptions.SystemException;
import co.com.foundation.soaint.documentmanager.persistence.entity.CmCargaMasiva;
import co.com.foundation.soaint.documentmanager.persistence.entity.CmRegistroCargaMasiva;
import co.com.foundation.soaint.documentmanager.web.domain.CargaMasivaVO;
import co.com.foundation.soaint.documentmanager.web.domain.RegistroCargaMasivaVO;
import co.com.foundation.soaint.documentmanager.web.infrastructure.builder.generic.CargaMasivaVoBuilder;
import co.com.foundation.soaint.documentmanager.web.infrastructure.builder.generic.HTTPResponseBuilder;
import co.com.foundation.soaint.documentmanager.web.infrastructure.builder.generic.RegistroCargaMasivaVoBuilder;
import co.com.foundation.soaint.documentmanager.web.infrastructure.builder.generic.TableResponseBuilder;
import co.com.foundation.soaint.documentmanager.web.infrastructure.common.HTTPResponse;
import co.com.foundation.soaint.documentmanager.web.infrastructure.common.TableResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author ADMIN
 */
@Controller
@Scope("request")
@RequestMapping(value = "/cargaMasiva")
public class CargaMasivaController {

    @Autowired
    private CargaMasivaModel model;

    @Autowired
    private MassiveLoaderManagerProxy boundary;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String init() throws BusinessException, SystemException {

        model.getListCargaMasiva().clear();
        boundary.listarCargasMasivas()
                .stream()
                .forEach((CmCargaMasiva cmCargaMasiva) -> {
                            model.getListCargaMasiva().add(CargaMasivaVoBuilder.newBuilder()
                            .withId(cmCargaMasiva.getId())
                            .withNombre(cmCargaMasiva.getNombre())
                            .withFechaText(cmCargaMasiva.getFechaCreacion().toString())
                            .withFechaCreacion(cmCargaMasiva.getFechaCreacion())
                            .withTotalRegistros(cmCargaMasiva.getTotalRegistros())
                            .withTotalRegistrosExitosos(cmCargaMasiva.getTotalRegistrosExitosos())
                            .withTotalRegistrosError(cmCargaMasiva.getTotalRegistrosError())
                            .withEstado(cmCargaMasiva.getEstado().toString()).build());
                });
        return "cargaMasiva/cargaMasiva-ui";
    }

    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public String initCargaMasivaDetail() throws BusinessException, SystemException {
        return "cargaMasiva/cargaMasiva-detail";
    }

    @ResponseBody
    @RequestMapping(value = "/initMassiveLoadDetailById/{id}", method = RequestMethod.GET)
    public HTTPResponse initMassiveLoadDetailById(@PathVariable Long id) throws BusinessException, SystemException {
        model.getListCargaMasivaDetail().clear();
        boundary.listarRegistrosDeCargaMasiva(id)
                .stream()
                .forEach((CmRegistroCargaMasiva cm) -> {
                    model.getListCargaMasivaDetail().add(RegistroCargaMasivaVoBuilder.newBuilder()
                            .withId(cm.getId())
                            .withContenido(cm.getContenido())
                            .withMensajes(cm.getMensajes())
                            .withEstado(cm.getEstado().toString()).build());
                });
        return HTTPResponseBuilder.newBuilder()
                .withSuccess(true)
                .build();
    }

    @ResponseBody
    @RequestMapping(value = "/cargaMasivaList", method = RequestMethod.GET)
    public TableResponse<CargaMasivaVO> listCargaMasiva() throws SystemException, BusinessException {
        int size = model.getListCargaMasiva().size();
        return TableResponseBuilder.newBuilder()
                .withData(model.getListCargaMasiva())
                .withiTotalDisplayRecords(size)
                .withiTotalRecords(size)
                .build();
    }

    @ResponseBody
    @RequestMapping(value = "/listCargaMasivaDetail", method = RequestMethod.GET)
    public TableResponse<RegistroCargaMasivaVO> listLogCargaMasiva() throws SystemException, BusinessException {
        int size = model.getListCargaMasivaDetail().size();
        return TableResponseBuilder.newBuilder()
                .withData(model.getListCargaMasivaDetail())
                .withiTotalDisplayRecords(size)
                .withiTotalRecords(size)
                .build();
    }

}
