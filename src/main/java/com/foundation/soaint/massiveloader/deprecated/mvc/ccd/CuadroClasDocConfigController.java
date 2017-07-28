/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foundation.soaint.massiveloader.deprecated.mvc.ccd;

import co.com.foundation.soaint.documentmanager.business.configuracion.interfaces.ConfiguracionInstrumentosMangerProxy;
import co.com.foundation.soaint.documentmanager.business.cuadroclasificaciondoc.interfaces.CuadroClasificacionDocManagerProxy;
import co.com.foundation.soaint.documentmanager.business.organigrama.interfaces.OrganigramaManagerProxy;
import co.com.foundation.soaint.documentmanager.domain.ItemVO;
import co.com.foundation.soaint.documentmanager.domain.OrganigramaItemVO;
import co.com.foundation.soaint.documentmanager.infrastructure.builder.entity.EntityAdmSerieBuilder;
import co.com.foundation.soaint.documentmanager.infrastructure.builder.entity.EntityAdmSubserieBuilder;
import co.com.foundation.soaint.documentmanager.persistence.entity.AdmSerie;
import co.com.foundation.soaint.documentmanager.persistence.entity.AdmSubserie;
import com.foundation.soaint.massiveloader.web.infrastructure.util.constants.EstadoSeriesEmun;
import co.com.foundation.soaint.infrastructure.common.MessageUtil;
import co.com.foundation.soaint.infrastructure.exceptions.BusinessException;
import co.com.foundation.soaint.infrastructure.exceptions.SystemException;
import co.com.foundation.soaint.documentmanager.persistence.entity.AdmConfigCcd;
import co.com.foundation.soaint.documentmanager.persistence.entity.constants.EstadoInstrumentoEnum;
import com.foundation.soaint.massiveloader.web.domain.CcdVO;
import co.com.foundation.soaint.documentmanager.infrastructure.builder.entity.EntityAdmCcdConfigBuilder;

import com.foundation.soaint.massiveloader.web.infrastructure.builder.generic.CcdVoBuilder;
import com.foundation.soaint.massiveloader.web.infrastructure.builder.generic.HTTPResponseBuilder;
import com.foundation.soaint.massiveloader.web.infrastructure.builder.generic.TableResponseBuilder;
import com.foundation.soaint.massiveloader.web.infrastructure.common.HTTPResponse;
import com.foundation.soaint.massiveloader.web.infrastructure.common.TableResponse;
import com.foundation.soaint.massiveloader.web.infrastructure.util.HTMLUtil;
import com.foundation.soaint.massiveloader.web.infrastructure.util.constants.InstrumentosEnum;

import java.math.BigInteger;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author ADMIN
 */
@Controller
@Scope("request")
@RequestMapping(value = "/ccd")
public class CuadroClasDocConfigController {

    @Autowired
    private CuadroClasDocConfigModel model;

    @Autowired
    private CuadroClasificacionDocManagerProxy boundary;

    @Autowired
    private OrganigramaManagerProxy organigramaBoundary;

    @Autowired
    private ConfiguracionInstrumentosMangerProxy configInstrumentoBoundary;

    public CuadroClasDocConfigController() {
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String init() throws BusinessException, SystemException {
        model.clear();

        for (AdmConfigCcd ccdConfig : boundary.findAllCcdConfig()) {

            CcdVO ccdVO = CcdVoBuilder.newBuilder()
                    .withIdeCcd(ccdConfig.getIdeCcd())
                    .withIdeSerie(ccdConfig.getIdeSerie().getIdeSerie())
                    .withIdeSubserie(ccdConfig.getIdeSubserie().getIdeSubserie())

                    .withIdeorgaadminUniAmt(ccdConfig.getIdeUniAmt())
                    .withIdeorgaadminOfProd(ccdConfig.getIdeOfcProd())

                    .withNomOrgUniAmt(ccdConfig.getNombreUnidadAdministrativa())
                    .withCodOrgUniAmt(ccdConfig.getIdeUnidadAdministrativa())

                    .withNomOrgOfProd(ccdConfig.getNombreOfcProdOrganigrama())
                    .withCodOrgOfProd(ccdConfig.getIdeOfcProdCodOrganigrama())

                    .withCodSerie(ccdConfig.getIdeSerie().getCodSerie())
                    .withNomSerie(ccdConfig.getIdeSerie().getNomSerie())

                    .withCodSubserie(ccdConfig.getIdeSubserie().getCodSubserie())
                    .withNomSubserie(ccdConfig.getIdeSubserie().getNomSubserie())

                    .withEstCcd(HTMLUtil.generateStatusColumn(ccdConfig.getEstCcd()))
                    .withEstCcdValue(ccdConfig.getEstCcd())
                    .build();

            model.getCcdList().add(ccdVO);
        }
        return "ccd/ccd-crud";
    }

    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.POST)
    public HTTPResponse createCCD(@RequestBody CcdVO ccdVO) throws BusinessException, SystemException {


        boolean flujoActualizacion = ccdVO.getIdeCcd() != null;
        String mensaje = null;
        boolean error = false;

        if (flujoActualizacion) {
            mensaje = MessageUtil.getMessage("ccd.cuaclasdoc_update_successfully");
            error = true;
            boundary.updateCuadroClasiDocConfig(ccdVO.getIdeCcd(), Integer.valueOf(ccdVO.getEstCcd()));

        } else {
            if (ccdVO.getIdeorgaadminUniAmt().equals("TODAS") && ccdVO.getIdeorgaadminOfProd() == null) {
                return asignacionMasivaCcd(ccdVO);

            } else if (ccdVO.getIdeorgaadminUniAmt() != null && ccdVO.getIdeorgaadminOfProd().equals("TODAS")) {
                return asignacionCcdPorUniAmd(ccdVO);


            } else {

                String[] arrayDataOficinaAdmin = ccdVO.getIdeorgaadminUniAmt().split("-");
                String[] arrayDataOficinaProd = ccdVO.getIdeorgaadminOfProd().split("-");

                boolean existeTabRecDoc = validateExistTabRecDoc(ccdVO.getIdeSerie(), ccdVO.getIdeSubserie(), arrayDataOficinaProd[1], arrayDataOficinaAdmin[1]);
                boolean existeCcc = validateExistCcd(ccdVO.getIdeSerie(), ccdVO.getIdeSubserie(), arrayDataOficinaProd[1], arrayDataOficinaAdmin[1]);

                if (existeTabRecDoc) {
                    if (!existeCcc) {
                        mensaje = MessageUtil.getMessage("ccd.cuaclasdoc_created_successfully");
                        error = true;

                        AdmSerie serie = EntityAdmSerieBuilder.newInstance()
                                .withIdeSerie(ccdVO.getIdeSerie())
                                .build();

                        EntityAdmCcdConfigBuilder builder = EntityAdmCcdConfigBuilder.newBuilder()
                                .withFecCreacion(new Date())
                                .withIdeSerie(serie)
                                .withIdeOfcProd(arrayDataOficinaProd[1])
                                .withIdeUniAmt(arrayDataOficinaAdmin[1])
                                .withEstCcd(Integer.valueOf(ccdVO.getEstCcd()));

                        if (ccdVO.getIdeSubserie() != null) {
                            AdmSubserie subserie = EntityAdmSubserieBuilder.newInstance()
                                    .withIdeSubserie(ccdVO.getIdeSubserie())
                                    .build();
                            builder.withIdeSubserie(subserie);
                        }

                        boundary.createCuadroClasiDocConfig(builder.build());


                    } else {
                        mensaje = MessageUtil.getMessage("ccd.exits_ccd");
                        error = false;
                    }
                } else {
                    mensaje = MessageUtil.getMessage("ccd.not_exits_retDocumental");
                    error = false;


                }
            }
        }

            init();
            return HTTPResponseBuilder.newBuilder()
                    .withSuccess(error)
                    .withMessage(mensaje)
                    .build();


    }

    @ResponseBody
    @RequestMapping(value = "/ccdList", method = RequestMethod.GET)
    public TableResponse<CcdVO> listCcd() throws SystemException, BusinessException {
        int size = model.getCcdList().size();
        return TableResponseBuilder.newBuilder()
                .withData(model.getCcdList())
                .withiTotalDisplayRecords(size)
                .withiTotalRecords(size)
                .build();
    }

    public HTTPResponse asignacionMasivaCcd(CcdVO ccdVO) throws BusinessException, SystemException {

        String mensaje = "";
        boolean error = false;
        AdmConfigCcd admConfigCcd;
        int contadorExitosos = 0;
        int contadorConErrores = 0;
        for (OrganigramaItemVO padre : organigramaBoundary.listarElementosDeSegundoNivel()) {
            for (OrganigramaItemVO hijos : organigramaBoundary.listarElementosDeNivelInferior(padre.getIdeOrgaAdmin())) {

                boolean existeTabRecDoc = validateExistTabRecDoc(ccdVO.getIdeSerie(), ccdVO.getIdeSubserie(), hijos.getCodOrg(), padre.getCodOrg());
                if (existeTabRecDoc) {
                    AdmSerie serie = EntityAdmSerieBuilder.newInstance()
                            .withIdeSerie(ccdVO.getIdeSerie())
                            .build();

                    EntityAdmCcdConfigBuilder builder = EntityAdmCcdConfigBuilder.newBuilder()
                            .withFecCreacion(new Date())
                            .withIdeSerie(serie)
                            .withIdeOfcProd(hijos.getCodOrg())
                            .withIdeUniAmt(padre.getCodOrg())
                            .withEstCcd(Integer.valueOf(ccdVO.getEstCcd()));

                    if (ccdVO.getIdeSubserie() != null) {
                        AdmSubserie subserie = EntityAdmSubserieBuilder.newInstance()
                                .withIdeSubserie(ccdVO.getIdeSubserie())
                                .build();
                        builder.withIdeSubserie(subserie);
                    }

                    try {
                        admConfigCcd = boundary.searchByUniAdminAndOfcProdAndIdSerieAndSubSerie(ccdVO.getIdeSerie(), ccdVO.getIdeSubserie(), hijos.getCodOrg(), padre.getCodOrg());
                        if (admConfigCcd != null) {
                            ccdVO.setIdeCcd(admConfigCcd.getIdeCcd());
                        }
                    } catch (BusinessException b) {
                        admConfigCcd = new AdmConfigCcd();

                    }

                    boolean flujoActualizacion = admConfigCcd.getIdeCcd() != null;

                    if (flujoActualizacion) {
                        boundary.updateCuadroClasiDocConfig(admConfigCcd.getIdeCcd(), EstadoSeriesEmun.ACTIVO.getId());

                    } else {
                        boundary.createCuadroClasiDocConfig(builder.build());
                    }

                    mensaje = MessageUtil.getMessage("ccd.cuaclasdoc_created_successfully");
                    error = true;
                    contadorExitosos ++;

                }else {
                mensaje = MessageUtil.getMessage("ccd.not_exits_retDocumental");
                error = false;
                contadorConErrores ++;
               }

            }
        }
        if(contadorExitosos > 1 && contadorConErrores > 1){
            mensaje = MessageUtil.getMessage("ccd.cuaclasdoc_created_various");
            error = true;
        }

        init();
        return HTTPResponseBuilder.newBuilder()
                .withSuccess(error)
                .withMessage(mensaje)
                .build();
    }

    public HTTPResponse asignacionCcdPorUniAmd(CcdVO ccdVO) throws BusinessException, SystemException {

        String mensaje = "";
        boolean error = false;
        AdmConfigCcd admConfigCcd;
        int contadorExitosos = 0;
        int contadorConErrores = 0;

        String[] arrayDataOficinaAdmin = ccdVO.getIdeorgaadminUniAmt().split("-");

        for (OrganigramaItemVO hijos : organigramaBoundary.listarElementosDeNivelInferior(Long.parseLong(arrayDataOficinaAdmin[0]))) {

            boolean existeTabRecDoc = validateExistTabRecDoc(ccdVO.getIdeSerie(), ccdVO.getIdeSubserie(), hijos.getCodOrg(), arrayDataOficinaAdmin[1]);

            if (existeTabRecDoc) {

                AdmSerie serie = EntityAdmSerieBuilder.newInstance()
                        .withIdeSerie(ccdVO.getIdeSerie())
                        .build();

                EntityAdmCcdConfigBuilder builder = EntityAdmCcdConfigBuilder.newBuilder()
                        .withFecCreacion(new Date())
                        .withIdeSerie(serie)
                        .withIdeOfcProd(hijos.getCodOrg())
                        .withIdeUniAmt(arrayDataOficinaAdmin[1])
                        .withEstCcd(Integer.valueOf(ccdVO.getEstCcd()));

                if (ccdVO.getIdeSubserie() != null) {
                    AdmSubserie subserie = EntityAdmSubserieBuilder.newInstance()
                            .withIdeSubserie(ccdVO.getIdeSubserie())
                            .build();
                    builder.withIdeSubserie(subserie);
                }

                try {
                    admConfigCcd = boundary.searchByUniAdminAndOfcProdAndIdSerieAndSubSerie(ccdVO.getIdeSerie(), ccdVO.getIdeSubserie(), hijos.getCodOrg(), arrayDataOficinaAdmin[1]);
                    if (admConfigCcd != null) {
                        ccdVO.setIdeCcd(admConfigCcd.getIdeCcd());
                    }
                } catch (BusinessException b) {
                    admConfigCcd = new AdmConfigCcd();
                }

                boolean flujoActualizacion = admConfigCcd.getIdeCcd() != null;

                if (flujoActualizacion) {
                    boundary.updateCuadroClasiDocConfig(admConfigCcd.getIdeCcd(), EstadoSeriesEmun.ACTIVO.getId());
                } else {
                    boundary.createCuadroClasiDocConfig(builder.build());
                }
                mensaje = MessageUtil.getMessage("ccd.cuaclasdoc_created_successfully");
                error = true;
                contadorExitosos ++;

            }else {
                mensaje = MessageUtil.getMessage("ccd.not_exits_retDocumental");
                error = false;
                contadorConErrores ++;

            }


        }

        init();
        if(contadorExitosos > 1 && contadorConErrores > 1){
            mensaje = MessageUtil.getMessage("ccd.cuaclasdoc_created_various");
            error = true;
        }
        return HTTPResponseBuilder.newBuilder()
                .withSuccess(error)
                .withMessage(mensaje)
                .build();
    }


    @ResponseBody
    @RequestMapping(value = "/validateExistTabRecDoc", method = RequestMethod.POST)
    public Boolean validateExistTabRecDoc(BigInteger ideSerie, BigInteger ideSubserie, String ideOfcProd, String ideUniAmt) throws BusinessException, SystemException {
        return boundary.existByTabRedDoc(ideSerie, ideSubserie, ideOfcProd, ideUniAmt);

    }

    @ResponseBody
    @RequestMapping(value = "/validateExistCcd", method = RequestMethod.POST)
    public Boolean validateExistCcd(BigInteger ideSerie, BigInteger ideSubserie, String ideOfcProd, String ideUniAmt) throws BusinessException, SystemException {
        return boundary.existCcd(ideSerie, ideSubserie, ideOfcProd, ideUniAmt);

    }

    @ResponseBody
    @RequestMapping(value = "/searchByUniAdminAndOfcProdAndIdSerieAndSubSerie", method = RequestMethod.POST)
    public AdmConfigCcd searchByUniAdminAndOfcProdAndIdSerieAndSubSerie(BigInteger ideSerie, BigInteger ideSubserie, String ideOfcProd, String ideUniAmt) throws BusinessException, SystemException {
        return boundary.searchByUniAdminAndOfcProdAndIdSerieAndSubSerie(ideSerie, ideSubserie, ideOfcProd, ideUniAmt);

    }

    @ResponseBody
    @RequestMapping(value = "/getEstadoCcd", method = RequestMethod.GET)
    public ItemVO getEstadoOrganigrama() throws SystemException, BusinessException {
        return configInstrumentoBoundary.consultarEstadoInstrumento(InstrumentosEnum.CCD.getName());
    }


    @ResponseBody
    @RequestMapping(value = "/publicarVersionCcd/", method = RequestMethod.GET)
    public HTTPResponse publicarCcd() throws BusinessException, SystemException {
        boundary.publicarCcd();
        ItemVO item = new ItemVO(InstrumentosEnum.CCD.getName(), EstadoInstrumentoEnum.PUBLICADO.getId());
        configInstrumentoBoundary.setEstadoInstrumento(item);
        return HTTPResponseBuilder.newBuilder()
                .withSuccess(true)
                .withMessage(MessageUtil.getMessage("ccd.cuaclasdoc_public_successfully"))
                .build();
    }

}
