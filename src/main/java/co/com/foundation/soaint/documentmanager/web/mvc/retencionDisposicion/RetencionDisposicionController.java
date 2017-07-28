package co.com.foundation.soaint.documentmanager.web.mvc.retencionDisposicion;


import co.com.foundation.soaint.documentmanager.business.asosersubtpg.interfaces.AsoSerSubTpglManagerProxy;
import co.com.foundation.soaint.documentmanager.business.organigrama.OrganigramaManager;
import co.com.foundation.soaint.documentmanager.business.organigrama.interfaces.OrganigramaManagerProxy;
import co.com.foundation.soaint.documentmanager.business.tablaretenciondisposicion.interfaces.TabRetencionDiposicionManagerProxy;
import co.com.foundation.soaint.documentmanager.domain.DataTabRetDispVO;
import co.com.foundation.soaint.documentmanager.domain.OrganigramaItemVO;
import co.com.foundation.soaint.documentmanager.infrastructure.builder.entity.EntityAdmDisposicionFinalBuilder;
import co.com.foundation.soaint.documentmanager.infrastructure.builder.entity.EntityAdmSerieBuilder;
import co.com.foundation.soaint.documentmanager.infrastructure.builder.entity.EntityAdmSubserieBuilder;
import co.com.foundation.soaint.documentmanager.infrastructure.builder.entity.EntityAdmTabRetDocBuilder;
import co.com.foundation.soaint.documentmanager.persistence.entity.AdmDisFinal;
import co.com.foundation.soaint.documentmanager.persistence.entity.AdmSerie;
import co.com.foundation.soaint.documentmanager.persistence.entity.AdmSubserie;
import co.com.foundation.soaint.documentmanager.persistence.entity.AdmTabRetDoc;
import co.com.foundation.soaint.documentmanager.web.domain.RetencionDisposicionVO;
import co.com.foundation.soaint.documentmanager.web.infrastructure.builder.generic.HTTPResponseBuilder;
import co.com.foundation.soaint.documentmanager.web.infrastructure.builder.generic.RetencionDisposicionVOBuilder;
import co.com.foundation.soaint.documentmanager.web.infrastructure.common.HTTPResponse;
import co.com.foundation.soaint.documentmanager.web.infrastructure.util.constants.EstadoTabRepDocEmun;
import co.com.foundation.soaint.infrastructure.common.MessageUtil;
import co.com.foundation.soaint.infrastructure.exceptions.BusinessException;
import co.com.foundation.soaint.infrastructure.exceptions.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * Created by sarias on 19/10/2016.
 */
@Controller
@Scope("request")
@RequestMapping(value = "/retencionDisposicion")
public class RetencionDisposicionController {

    @Autowired
    private TabRetencionDiposicionManagerProxy boundary;

    @Autowired
    private AsoSerSubTpglManagerProxy asoSerSubTpglBoundary;

    @Autowired
    private OrganigramaManagerProxy organigramaBoundary;


    @RequestMapping(value = "", method = RequestMethod.GET)
    public String init() throws BusinessException, SystemException {
        return "retencionDisposicion/retencionDisposicion-crud";
    }

    @ResponseBody
    @RequestMapping(value = "getTRDBySerieAndSubserieCargarFrmEdit", method = RequestMethod.POST)
    public ResponseEntity getTRDBySerieAndSubserieCargarFrmEdit(@RequestBody DataTabRetDispVO retDispVO) throws BusinessException, SystemException {
        try {
            AdmTabRetDoc tabRetDoc = boundary.searchByUniAdminAndOfcProdAndIdSerieAndSubSerie(retDispVO);
            RetencionDisposicionVOBuilder build = RetencionDisposicionVOBuilder.newBuilder()
                    .withCodUniAmt(tabRetDoc.getCodUniAmt() + "-" + tabRetDoc.getIdeUniAmt())
                    .withCodOfcProd(tabRetDoc.getCodOfcProd() + "-" + tabRetDoc.getIdeOfcProd())
                    .withIdSerie(tabRetDoc.getIdeSerie().getIdeSerie())
                    .withIdeDisFinal(tabRetDoc.getAdmDisFinal().getIdeDisFinal())
                    .withAgTrd(tabRetDoc.getAgTrd())
                    .withAcTrd(tabRetDoc.getAcTrd())
                    .withProTrd(tabRetDoc.getProTrd())
                    .withIdeTabRetDoc(tabRetDoc.getIdeTabRetDoc());

            boolean flujoSubserie = tabRetDoc.getIdeSubserie() != null;
            System.out.println("FLUJO SERIE" + flujoSubserie);
            RetencionDisposicionVO retencionDisposicionVO;
            if (flujoSubserie) {
                build.withIdSubserie(tabRetDoc.getIdeSubserie().getIdeSubserie());
                retencionDisposicionVO = build.build();
            } else {
                retencionDisposicionVO = build.build();
            }

            return ResponseEntity.status(HttpStatus.OK)
                    .body(retencionDisposicionVO);
        } catch (BusinessException b) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.POST)
    public HTTPResponse createRetencionDocumental(@RequestBody RetencionDisposicionVO retencionDocumental) throws BusinessException, SystemException {

        String mensaje;
        boolean estado = true;

        if (retencionDocumental.getCodUniAmt().equals("TODAS") && retencionDocumental.getCodOfcProd() == null) {
            return asignacionMasivaRetencionDisposicion(retencionDocumental);
        } else if (retencionDocumental.getCodUniAmt() != null && retencionDocumental.getCodOfcProd().equals("TODAS")) {
            return asignacionMasivaRetencionDisposicionPorUniAmd(retencionDocumental);
        } else {

            String[] dataUniAdmin = retencionDocumental.getCodUniAmt().split("-");
            String[] dataOfcProd = retencionDocumental.getCodOfcProd().split("-");

            boolean validar = asoSerSubTpglBoundary.serieOrSubserieExistById(retencionDocumental.getIdSerie(), retencionDocumental.getIdSubserie());
            if (validar) {

                AdmSerie serie = EntityAdmSerieBuilder.newInstance()
                        .withIdeSerie(retencionDocumental.getIdSerie())
                        .build();


                AdmDisFinal disFinal = EntityAdmDisposicionFinalBuilder.newBuilder()
                        .withIdeDisFinal(retencionDocumental.getIdeDisFinal())
                        .build();


                EntityAdmTabRetDocBuilder builder = EntityAdmTabRetDocBuilder.newInstance()
                        .withIdeTabRetDoc(retencionDocumental.getIdeTabRetDoc())
                        .withAgTrd(retencionDocumental.getAgTrd())
                        .withAcTrd(retencionDocumental.getAcTrd())
                        .withIdeOfcProd(dataOfcProd[1])
                        .withIdeUniAmt(dataUniAdmin[1])
                        .withProTrd(retencionDocumental.getProTrd())
                        .withEstTabRetDoc(EstadoTabRepDocEmun.ACTIVO.getId())
                        .withIdeSerie(serie)
                        .withIdeDisFinal(disFinal);


                if (retencionDocumental.getIdSubserie() != null) {
                    AdmSubserie subserie = EntityAdmSubserieBuilder.newInstance()
                            .withIdeSubserie(retencionDocumental.getIdSubserie())
                            .build();
                    builder.withIdeSubserie(subserie);

                }
                boolean flujoActualizacion = retencionDocumental.getIdeTabRetDoc() != null;

                if (flujoActualizacion) {
                    mensaje = MessageUtil.getMessage("retencion.documental.update_successfully");
                    builder.withFecCambio(new Date());
                    boundary.updateTabRetencionDisposicion(builder.build());

                } else {
                    mensaje = MessageUtil.getMessage("retencion.documental.created_successfully");
                    builder.withFecCreacion(new Date());
                    boundary.createTabRetencionDisposicion(builder.build());
                }

            } else {
                mensaje = MessageUtil.getMessage("retencion.documental.validate.asosesubtip");
                estado = false;
            }
            return HTTPResponseBuilder.newBuilder()
                    .withSuccess(estado)
                    .withMessage(mensaje)
                    .build();
        }
    }


    public HTTPResponse asignacionMasivaRetencionDisposicion(RetencionDisposicionVO retencionDocumental) throws BusinessException, SystemException {

        String mensaje = "";
        boolean estado = true;
        AdmTabRetDoc tabRetDoc;

        boolean validar = asoSerSubTpglBoundary.serieOrSubserieExistById(retencionDocumental.getIdSerie(), retencionDocumental.getIdSubserie());
        if (validar) {
            for (OrganigramaItemVO padre : organigramaBoundary.listarElementosDeSegundoNivel()) {
                for (OrganigramaItemVO hijos : organigramaBoundary.listarElementosDeNivelInferior(padre.getIdeOrgaAdmin())) {

                    DataTabRetDispVO retDispVO = new DataTabRetDispVO();
                    retDispVO.setCodUniAmt(padre.getCodOrg());
                    retDispVO.setCodOfcProd(hijos.getCodOrg());
                    retDispVO.setIdSerie(retencionDocumental.getIdSerie());
                    retDispVO.setIdSubserie(retencionDocumental.getIdSubserie());


                    AdmSerie serie = EntityAdmSerieBuilder.newInstance()
                            .withIdeSerie(retencionDocumental.getIdSerie())
                            .build();

                    AdmDisFinal disFinal = EntityAdmDisposicionFinalBuilder.newBuilder()
                            .withIdeDisFinal(retencionDocumental.getIdeDisFinal())
                            .build();


                    EntityAdmTabRetDocBuilder builder = EntityAdmTabRetDocBuilder.newInstance()
                            .withAgTrd(retencionDocumental.getAgTrd())
                            .withAcTrd(retencionDocumental.getAcTrd())
                            .withIdeOfcProd(hijos.getCodOrg())
                            .withIdeUniAmt(padre.getCodOrg())
                            .withProTrd(retencionDocumental.getProTrd())
                            .withEstTabRetDoc(EstadoTabRepDocEmun.ACTIVO.getId())
                            .withIdeSerie(serie)
                            .withIdeDisFinal(disFinal);

                    try {
                        tabRetDoc = boundary.searchByUniAdminAndOfcProdAndIdSerieAndSubSerie(retDispVO);
                        if (tabRetDoc != null)
                            retencionDocumental.setIdeTabRetDoc(tabRetDoc.getIdeTabRetDoc());
                    } catch (BusinessException b) {
                        tabRetDoc = new AdmTabRetDoc();
                        System.out.println(b.toString());
                    }

                    if (retencionDocumental.getIdSubserie() != null) {
                        AdmSubserie subserie = EntityAdmSubserieBuilder.newInstance()
                                .withIdeSubserie(retencionDocumental.getIdSubserie())
                                .build();
                        builder.withIdeSubserie(subserie);
                    }


                    boolean flujoActualizacion = retencionDocumental.getIdeTabRetDoc() != null;

                    if (flujoActualizacion) {
                        builder.withFecCambio(new Date());
                        builder.withIdeTabRetDoc(retencionDocumental.getIdeTabRetDoc());
                        boundary.updateTabRetencionDisposicion(builder.build());
                    } else {
                        builder.withFecCreacion(new Date());
                        boundary.createTabRetencionDisposicion(builder.build());
                    }
                }
                mensaje = MessageUtil.getMessage("retencion.documental.created_successfully");
            }

        } else {
            mensaje = MessageUtil.getMessage("retencion.documental.validate.asosesubtip");
            estado = false;
        }
        return HTTPResponseBuilder.newBuilder()
                .withSuccess(estado)
                .withMessage(mensaje)
                .build();
    }

    public HTTPResponse asignacionMasivaRetencionDisposicionPorUniAmd(RetencionDisposicionVO retencionDocumental) throws BusinessException, SystemException {

        String mensaje = "";
        boolean estado = true;
        AdmTabRetDoc tabRetDoc;

        boolean validar = asoSerSubTpglBoundary.serieOrSubserieExistById(retencionDocumental.getIdSerie(), retencionDocumental.getIdSubserie());

        if (validar) {
            String[] dataUniAdmin = retencionDocumental.getCodUniAmt().split("-");

            for (OrganigramaItemVO hijos : organigramaBoundary.listarElementosDeNivelInferior(Long.parseLong(dataUniAdmin[0]))) {
                DataTabRetDispVO retDispVO = new DataTabRetDispVO();
                retDispVO.setCodUniAmt(dataUniAdmin[1]);
                retDispVO.setCodOfcProd(hijos.getCodOrg());
                retDispVO.setIdSerie(retencionDocumental.getIdSerie());
                retDispVO.setIdSubserie(retencionDocumental.getIdSubserie());


                AdmSerie serie = EntityAdmSerieBuilder.newInstance()
                        .withIdeSerie(retencionDocumental.getIdSerie())
                        .build();

                AdmDisFinal disFinal = EntityAdmDisposicionFinalBuilder.newBuilder()
                        .withIdeDisFinal(retencionDocumental.getIdeDisFinal())
                        .build();

                EntityAdmTabRetDocBuilder builder = EntityAdmTabRetDocBuilder.newInstance()
                        .withAgTrd(retencionDocumental.getAgTrd())
                        .withAcTrd(retencionDocumental.getAcTrd())
                        .withIdeOfcProd(hijos.getCodOrg())
                        .withIdeUniAmt(dataUniAdmin[1])
                        .withProTrd(retencionDocumental.getProTrd())
                        .withEstTabRetDoc(EstadoTabRepDocEmun.ACTIVO.getId())
                        .withIdeSerie(serie)
                        .withIdeDisFinal(disFinal);

                try {
                    tabRetDoc = boundary.searchByUniAdminAndOfcProdAndIdSerieAndSubSerie(retDispVO);
                    if (tabRetDoc != null)
                        retencionDocumental.setIdeTabRetDoc(tabRetDoc.getIdeTabRetDoc());
                } catch (BusinessException b) {
                    tabRetDoc = new AdmTabRetDoc();
                    System.out.println(b.toString());
                }

                if (retencionDocumental.getIdSubserie() != null) {
                    AdmSubserie subserie = EntityAdmSubserieBuilder.newInstance()
                            .withIdeSubserie(retencionDocumental.getIdSubserie())
                            .build();
                    builder.withIdeSubserie(subserie);
                }

                boolean flujoActualizacion = retencionDocumental.getIdeTabRetDoc() != null;

                if (flujoActualizacion) {
                    builder.withFecCambio(new Date());
                    builder.withIdeTabRetDoc(retencionDocumental.getIdeTabRetDoc());
                    boundary.updateTabRetencionDisposicion(builder.build());
                } else {
                    builder.withFecCreacion(new Date());
                    boundary.createTabRetencionDisposicion(builder.build());
                }
            }
            mensaje = MessageUtil.getMessage("retencion.documental.created_successfully");

        } else {
            mensaje = MessageUtil.getMessage("retencion.documental.validate.asosesubtip");
            estado = false;
        }
        return HTTPResponseBuilder.newBuilder()
                .withSuccess(estado)
                .withMessage(mensaje)
                .build();
    }


}
