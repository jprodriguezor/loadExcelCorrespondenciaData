package com.foundation.soaint.massiveloader.web.mvc.global;

import co.com.foundation.soaint.documentmanager.business.configuracion.interfaces.*;
import co.com.foundation.soaint.documentmanager.business.cuadroclasificaciondoc.interfaces.CuadroClasificacionDocManagerProxy;
import co.com.foundation.soaint.documentmanager.business.organigrama.interfaces.OrganigramaManagerProxy;
import co.com.foundation.soaint.documentmanager.business.series.interfaces.SeriesManagerProxy;
import co.com.foundation.soaint.documentmanager.business.subserie.interfaces.SubserieManagerProxy;
import co.com.foundation.soaint.documentmanager.business.tipologiadocumental.interfaces.TipologiasDocManagerProxy;
import co.com.foundation.soaint.documentmanager.domain.CcdItemVO;
import co.com.foundation.soaint.documentmanager.domain.ItemVO;
import co.com.foundation.soaint.documentmanager.domain.OrganigramaItemVO;

import co.com.foundation.soaint.documentmanager.domain.RelEquiItemVO;
import com.foundation.soaint.massiveloader.deprecated.web.infrastructure.util.constants.EstadoSubserieEnum;
import co.com.foundation.soaint.infrastructure.common.MessageUtil;
import co.com.foundation.soaint.infrastructure.exceptions.BusinessException;
import co.com.foundation.soaint.infrastructure.exceptions.SystemException;
import co.com.foundation.soaint.documentmanager.persistence.entity.*;
import co.com.foundation.soaint.documentmanager.persistence.entity.constants.EstadoInstrumentoEnum;
import com.foundation.soaint.massiveloader.deprecated.web.infrastructure.builder.generic.CheckBoxItemBuilder;
import com.foundation.soaint.massiveloader.deprecated.web.infrastructure.builder.generic.HTTPResponseBuilder;
import com.foundation.soaint.massiveloader.deprecated.web.infrastructure.builder.generic.SelectItemBuilder;
import com.foundation.soaint.massiveloader.web.infrastructure.common.CheckBoxItem;
import com.foundation.soaint.massiveloader.web.infrastructure.common.HTTPResponse;
import com.foundation.soaint.massiveloader.web.infrastructure.common.SelectItem;
import com.foundation.soaint.massiveloader.web.infrastructure.util.constants.EstadoSeriesEmun;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by jrodriguez on 20/09/2016.
 */
@Controller
@Scope("request")
@RequestMapping(value = "/util")
public class GlobalController {

    @Autowired
    private GlobalModel model;

    @Autowired
    private MotivosCreacionManagerProxy motivosCreacionBoundary;

    @Autowired
    private SeriesManagerProxy seriesBoundary;

    @Autowired
    private SubserieManagerProxy subSeriesBoundary;

    @Autowired
    private SoporteManagerProxy soporteBoundary;

    @Autowired
    private TradicionDocManagerProxy tradicionDocBoundary;

    @Autowired
    private TipologiasDocManagerProxy tipologiasDocBoundary;

    @Autowired
    private OrganigramaManagerProxy organigramaBoundary;

    @Autowired
    private DisposicionFinalManagerProxy disposicionBoundary;

    @Autowired
    private ConfiguracionInstrumentosMangerProxy configInstrumentoBoundary;

    @Autowired
    private CuadroClasificacionDocManagerProxy cuadroClasificacionDocManagerBoundary;


    //[ lista de motivos ]
    @ResponseBody
    @RequestMapping(value = "/motivosCreacionList", method = RequestMethod.GET)
    public List<SelectItem> getMotivosCreacionList() throws SystemException, BusinessException {
        model.getMotivosCreacionList().clear();
        for (AdmMotCreacion motCreacion : motivosCreacionBoundary.findAllMotivosCreacion()) {
            model.getMotivosCreacionList().addAll(SelectItemBuilder.newBuilder()
                    .addItem(motCreacion.getNomMotCreacion().toUpperCase(), motCreacion.getIdeMotCreacion())
                    .build());
        }
        return model.getMotivosCreacionList();
    }

    @ResponseBody
    @RequestMapping(value = "/caracteristicasList", method = RequestMethod.GET)
    public List<CheckBoxItem> getCaracteristicasList() {
        return CheckBoxItemBuilder.newBuilder()
                .addItem("carTecnica", "T&eacute;cnica", 8)
                .addItem("carLegal", "Legal", 9)
                .addItem("carAdministrativa", "Administrativa", 10)
                .build();
    }

    @ResponseBody
    @RequestMapping(value = "/seriesList", method = RequestMethod.GET)
    public List<SelectItem> getSeriesList() throws SystemException, BusinessException {
        model.getSeriesList().clear();
        for (AdmSerie serie : seriesBoundary.findByEstado(EstadoSeriesEmun.ACTIVO.getId())) {
            model.getSeriesList().addAll(SelectItemBuilder.newBuilder()
                    .addItem(serie.getCodSerie() + " - " + serie.getNomSerie().toUpperCase(), serie.getIdeSerie())
                    .build());
        }
        return model.getSeriesList();
    }


    @ResponseBody
    @RequestMapping(value = "/soportesList", method = RequestMethod.GET)
    public List<SelectItem> getSoportesList() throws SystemException, BusinessException {
        model.getSoportesList().clear();
        for (AdmSoporte soporte : soporteBoundary.findAllSoportes()) {
            model.getSoportesList().addAll(SelectItemBuilder.newBuilder()
                    .addItem(soporte.getNomSoporte(), soporte.getIdeSoporte())
                    .build());
        }
        return model.getSoportesList();
    }


    @ResponseBody
    @RequestMapping(value = "/tradicionDocList", method = RequestMethod.GET)
    public List<SelectItem> getTradicionDocList() throws SystemException, BusinessException {
        model.getTradicionDocList().clear();
        for (AdmTradDoc tradDoc : tradicionDocBoundary.findAllTradicionDoc()) {
            model.getTradicionDocList().addAll(SelectItemBuilder.newBuilder()
                    .addItem(tradDoc.getNomTradDoc(), tradDoc.getIdeTradDoc())
                    .build());
        }
        return model.getTradicionDocList();
    }

    @ResponseBody
    @RequestMapping(value = "/subSeriesList", method = RequestMethod.GET)
    public List<SelectItem> getSubseriesList() throws SystemException, BusinessException {
        model.getSubSeriesList().clear();
        for (AdmSubserie subSerie : subSeriesBoundary.findByEstado(EstadoSubserieEnum.ACTIVO.getId())) {
            model.getSubSeriesList().addAll(SelectItemBuilder.newBuilder()
                    .addItem(subSerie.getCodSubserie() + " - " + subSerie.getNomSubserie().toUpperCase(), subSerie.getIdeSubserie())
                    .build());
        }
        return model.getSubSeriesList();
    }

    @ResponseBody
    @RequestMapping(value = "/subSeriesByIdSerieList/{idSerie}", method = RequestMethod.GET)
    public List<SelectItem> getSubseriesByIdSerieList(@PathVariable long idSerie) throws SystemException, BusinessException {
        model.getSubSeriesList().clear();
        for (AdmSubserie subSerie : subSeriesBoundary.searchSubseriesByIdSerie(BigInteger.valueOf(idSerie))) {
            model.getSubSeriesList().addAll(SelectItemBuilder.newBuilder()
                    .addItem(subSerie.getCodSubserie() + " - " + subSerie.getNomSubserie().toUpperCase(), subSerie.getIdeSubserie())
                    .build());
        }
        return model.getSubSeriesList();
    }

    @ResponseBody
    @RequestMapping(value = "/tipologiasDocList", method = RequestMethod.GET)
    public List<SelectItem> getTipologiasDocList() throws SystemException, BusinessException {
        model.getTipologiasDocList().clear();
        for (AdmTpgDoc tipologia : tipologiasDocBoundary.findByEstado(EstadoSeriesEmun.ACTIVO.getId())) {
            model.getTipologiasDocList().addAll(SelectItemBuilder.newBuilder()
                    .addItem(tipologia.getNomTpgDoc().toUpperCase(), tipologia.getIdeTpgDoc())
                    .build());
        }
        return model.getTipologiasDocList();
    }

    @ResponseBody
    @RequestMapping(value = "/unidadAdministrativaList", method = RequestMethod.GET)
    public List<SelectItem> getUnidadAdministrativaList() throws SystemException, BusinessException {
        model.getUnidadAdministrativoList().clear();
        for (OrganigramaItemVO lugarAdminlist : organigramaBoundary.listarElementosDeSegundoNivel()) {

            model.getUnidadAdministrativoList().addAll(SelectItemBuilder.newBuilder()
                    .addItem(lugarAdminlist.getNomOrg().toUpperCase(), lugarAdminlist.getIdeOrgaAdmin() + "-" + lugarAdminlist.getCodOrg()).build());
        }
        return model.getUnidadAdministrativoList();
    }

    @ResponseBody
    @RequestMapping(value = "/oficinaProductoraList/{idOrgAdmin}", method = RequestMethod.GET)
    public List<SelectItem> getOficinaProductoraList(@PathVariable long idOrgAdmin) throws SystemException, BusinessException {
        model.getOficinaProductoraList().clear();
        for (OrganigramaItemVO unidadProductoraList : organigramaBoundary.listarElementosDeNivelInferior(idOrgAdmin)) {
            model.getOficinaProductoraList().addAll(SelectItemBuilder.newBuilder()
                    .addItem(unidadProductoraList.getNomOrg().toUpperCase(), unidadProductoraList.getIdeOrgaAdmin() + "-" + unidadProductoraList.getCodOrg()).build());
        }
        return model.getOficinaProductoraList();
    }


    @ResponseBody
    @RequestMapping(value = "/unidadAdministrativoRetencionList", method = RequestMethod.GET)
    public List<SelectItem> getUnidadAdministrativoRetencionList() throws SystemException, BusinessException {
        model.getUnidadAdministrativoList().clear();
        for (OrganigramaItemVO lugarAdminlist : organigramaBoundary.listarElementosDeSegundoNivel()) {
            model.getUnidadAdministrativoList().addAll(SelectItemBuilder.newBuilder()
                    .addItem(lugarAdminlist.getNomOrg().toUpperCase(), lugarAdminlist.getIdeOrgaAdmin() + "-" + lugarAdminlist.getCodOrg()).build());
        }
        SelectItem item = new SelectItem("TODAS", "TODAS");
        model.getUnidadAdministrativoList().add(item);

        return model.getUnidadAdministrativoList();
    }

    @ResponseBody
    @RequestMapping(value = "/oficinaProductoraRetencionList/{idOrgAdmin}", method = RequestMethod.GET)
    public List<SelectItem> getOficinaProductoraRetencionList(@PathVariable long idOrgAdmin) throws SystemException, BusinessException {
        model.getOficinaProductoraList().clear();
        for (OrganigramaItemVO unidadProductoraList : organigramaBoundary.listarElementosDeNivelInferior(idOrgAdmin)) {
            model.getOficinaProductoraList().addAll(SelectItemBuilder.newBuilder()
                    .addItem(unidadProductoraList.getNomOrg().toUpperCase(), unidadProductoraList.getIdeOrgaAdmin() + "-" + unidadProductoraList.getCodOrg()).build());
        }
        SelectItem item = new SelectItem("TODAS", "TODAS");
        model.getOficinaProductoraList().add(item);
        return model.getOficinaProductoraList();
    }


    @ResponseBody
    @RequestMapping(value = "/disposicionFinalList", method = RequestMethod.GET)
    public List<SelectItem> getDisposicionFinal() throws SystemException, BusinessException {

        model.getDisposicionFinal().clear();
        for (AdmDisFinal disposicionFinalList : disposicionBoundary.findAllDisposicionFinal()) {

            model.getDisposicionFinal().addAll(SelectItemBuilder.newBuilder()
                    .addItem(disposicionFinalList.getNomDisFinal().toUpperCase(), disposicionFinalList.getIdeDisFinal()).build());
        }
        return model.getDisposicionFinal();
    }


    @ResponseBody
    @RequestMapping(value = "/setEstadoInstrumentoConf/{instrumento}", method = RequestMethod.GET)
    public HTTPResponse setEstadoInstrumentoConf(@PathVariable String instrumento) throws BusinessException, SystemException {

        ItemVO item = new ItemVO(instrumento, EstadoInstrumentoEnum.CONFIGURACION.getId());

        String mensaje = configInstrumentoBoundary.consultarEstadosIntrumentos(item);

        if (!mensaje.equals("ok")) {
            return HTTPResponseBuilder.newBuilder()
                    .withSuccess(false)
                    .withMessage(mensaje)
                    .build();
        } else {
            configInstrumentoBoundary.setEstadoInstrumento(item);
            return HTTPResponseBuilder.newBuilder()
                    .withSuccess(true)
                    .withMessage(MessageUtil.getMessage("instrumentos.instrumentos.validate.config"))
                    .build();
        }
    }

    @ResponseBody
    @RequestMapping(value = "/setEstadoInstrumentoCancel/{instrumento}", method = RequestMethod.GET)
    public HTTPResponse setEstadoInstrumentoCancel(@PathVariable String instrumento) throws BusinessException, SystemException {

        ItemVO item = new ItemVO(instrumento, EstadoInstrumentoEnum.PUBLICADO.getId());
        configInstrumentoBoundary.setEstadoInstrumento(item);
        return HTTPResponseBuilder.newBuilder()
                .withSuccess(true)
                .withMessage(MessageUtil.getMessage("instrumentos.instrumentos.cancel.successfully"))
                .build();
    }

    @ResponseBody
    @RequestMapping(value = "/versionCcdList", method = RequestMethod.GET)
    public List<SelectItem> getVersionCcdList() throws SystemException, BusinessException {
        model.getVersionCcdList().clear();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy h:mm a");

        for (AdmCcd admCcd : cuadroClasificacionDocManagerBoundary.listarVersionCcd()) {

            Date fechaCreacion = admCcd.getFecCreacion();
            model.getVersionCcdList().addAll(SelectItemBuilder.newBuilder()
                    .addItem(admCcd.getValVersion() + "-" + formatoFecha.format(fechaCreacion),
                            admCcd.getValVersion()).build());
        }
        return model.getVersionCcdList();
    }

    @ResponseBody
    @RequestMapping(value = "/versionCcdListNum", method = RequestMethod.GET)
    public List<SelectItem> getVersionCcdListNum() throws SystemException, BusinessException {
        model.getVersionCcdList().clear();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy h:mm a");

        for (RelEquiItemVO admCcd : cuadroClasificacionDocManagerBoundary.listarVersionCcdNum()) {
            model.getVersionCcdList().addAll(SelectItemBuilder.newBuilder()
                    .addItem(admCcd.getLabel(),
                            admCcd.getValue()).build());
        }
        return model.getVersionCcdList();
    }

    @ResponseBody
    @RequestMapping(value = "/versionesOrganigramaList", method = RequestMethod.GET)
    public List<SelectItem> getVersionesOrganigrama() throws SystemException, BusinessException {
        model.getVersionesOrganigramaList().clear();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy h:mm a");

        for (TvsOrganigramaAdministrativo organigramItem : organigramaBoundary.listarVersionOrganigrama()) {

            Date fechaCreacion = organigramItem.getFecCreacion();
            model.getVersionesOrganigramaList().addAll(SelectItemBuilder.newBuilder()
                    .addItem(organigramItem.getValVersion() + "-" + formatoFecha.format(fechaCreacion),
                            organigramItem.getValVersion()).build());
        }
        return model.getVersionesOrganigramaList();
    }

    @ResponseBody
    @RequestMapping(value = "/unidadAdministrativaTrdList", method = RequestMethod.GET)
    public List<SelectItem> getUnidadAdministrativaTrdList() throws SystemException, BusinessException {
        model.getTrdUniAmtList().clear();
        for (TvsOrganigramaAdministrativo unidadAdmin : organigramaBoundary.findAllUniAmdTrd()) {
            model.getTrdUniAmtList().addAll(SelectItemBuilder.newBuilder().addItem(unidadAdmin.getNomOrg(), unidadAdmin.getCodOrg()).build());
        }
        return model.getTrdUniAmtList();
    }

    @ResponseBody
    @RequestMapping(value = "/oficinaProductoraTrdList/{codUniAdmin:.+}", method = RequestMethod.GET)
    public List<SelectItem> getOficinaProductoraTrdList(@PathVariable String codUniAdmin) throws SystemException, BusinessException {
        model.getTrdOfcProduc().clear();
        for (TvsOrganigramaAdministrativo unidadAdmin : organigramaBoundary.findAllOfcProdTrd(codUniAdmin)) {
            model.getTrdOfcProduc().addAll(SelectItemBuilder.newBuilder().addItem(unidadAdmin.getNomOrg(), unidadAdmin.getCodOrg()).build());
        }
        return model.getTrdOfcProduc();
    }

    @ResponseBody
    @RequestMapping(value = "/unidadAdministrativoRetencionListExistInCcdOr", method = RequestMethod.POST)
    public List<SelectItem> getUnidadAdministrativoRetencionListExistInCcdOr(@RequestParam String valVersion) throws SystemException, BusinessException {
        model.getUnidadAdministrativoListExistByCcdOr().clear();

        for (CcdItemVO lugarAdminlist : cuadroClasificacionDocManagerBoundary.listarUniAdminExistInCcd(valVersion)) {
            model.getUnidadAdministrativoListExistByCcdOr().addAll(SelectItemBuilder.newBuilder()
                    .addItem(lugarAdminlist.getNombreideUniAmt().toUpperCase(), lugarAdminlist.getIdeOrgaAdmin() + "-" + lugarAdminlist.getIdeUniAmt()).build());

        }

        //SelectItem item = new SelectItem("TODAS", "TODAS");
        //model.getUnidadAdministrativoListExistByCcd().add(item);

        return model.getUnidadAdministrativoListExistByCcdOr();
    }

    @ResponseBody
    @RequestMapping(value = "/unidadAdministrativoRetencionListExistInCcdDe", method = RequestMethod.POST)
    public List<SelectItem> getUnidadAdministrativoRetencionListExistInCcdDe() throws SystemException, BusinessException {
        model.getUnidadAdministrativoListExistByCcdDe().clear();

        for (CcdItemVO lugarAdminlist : cuadroClasificacionDocManagerBoundary.listarUniAdminExistInCcdDes()) {
            model.getUnidadAdministrativoListExistByCcdDe().addAll(SelectItemBuilder.newBuilder()
                    .addItem(lugarAdminlist.getNombreideUniAmt().toUpperCase(), lugarAdminlist.getIdeOrgaAdmin() + "-" + lugarAdminlist.getIdeUniAmt()).build());

        }

        //SelectItem item = new SelectItem("TODAS", "TODAS");
        //model.getUnidadAdministrativoListExistByCcd().add(item);

        return model.getUnidadAdministrativoListExistByCcdDe();
    }

    @ResponseBody
    @RequestMapping(value = "/oficinaProductoraListExistInCcdOr/{idOrgAdmin:.+}/{valVersion}", method = RequestMethod.GET)
    public List<SelectItem> getOficinaProductoraListExistInCcdOr(@PathVariable String idOrgAdmin, @PathVariable String valVersion) throws SystemException, BusinessException {
        model.getOficinaProductoraListExistByCcdOr().clear();
        model.getSeriesListExistInCcdOr().clear();
        model.getSubSeriesListtExistInCcdOr().clear();
        for (CcdItemVO ofiProductoraList : cuadroClasificacionDocManagerBoundary.listarOfiProductoraExistInCcd(idOrgAdmin, valVersion)) {
            model.getOficinaProductoraListExistByCcdOr().addAll(SelectItemBuilder.newBuilder()
                    .addItem(ofiProductoraList.getNombreideOfcProd().toUpperCase(), ofiProductoraList.getIdeOfcProd()).build());
        }

        return model.getOficinaProductoraListExistByCcdOr();
    }

    @ResponseBody
    @RequestMapping(value = "/oficinaProductoraListExistInCcdDe/{idOrgAdmin}", method = RequestMethod.GET)
    public List<SelectItem> getOficinaProductoraListExistInCcdDe(@PathVariable String idOrgAdmin) throws SystemException, BusinessException {
        model.getOficinaProductoraListExistByCcdDe().clear();
        for (CcdItemVO ofiProductoraList : cuadroClasificacionDocManagerBoundary.listarOfiProductoraExistInCcdDe(idOrgAdmin)) {
            model.getOficinaProductoraListExistByCcdDe().addAll(SelectItemBuilder.newBuilder()
                    .addItem(ofiProductoraList.getNombreideOfcProd().toUpperCase(), ofiProductoraList.getIdeOfcProd()).build());
        }
        return model.getOficinaProductoraListExistByCcdDe();
    }

    @ResponseBody
    @RequestMapping(value = "/seriesListExistInCcdOr/{ideUniAmt},{ideOfcProd:.+},{valVersion}", method = RequestMethod.GET)
    public List<SelectItem> getSeriesListExistInCcdOr(@PathVariable String ideUniAmt, @PathVariable String ideOfcProd,
                                                      @PathVariable String valVersion) throws SystemException, BusinessException {

        model.getSeriesListExistInCcdOr().clear();
        model.getSubSeriesListtExistInCcdOr().clear();
        for (AdmCcd cuadro : cuadroClasificacionDocManagerBoundary.listarSeriesExistInCcd(ideUniAmt, ideOfcProd, valVersion)) {
            model.getSeriesListExistInCcdOr().addAll(SelectItemBuilder.newBuilder()
                    .addItem(cuadro.getIdeSerie().getCodSerie() + "-" + cuadro.getIdeSerie().getNomSerie().toUpperCase(), cuadro.getIdeSerie().getIdeSerie())
                    .build());
        }
        return model.getSeriesListExistInCcdOr();
    }

    @ResponseBody
    @RequestMapping(value = "/subSeriesListExistInCcdOr/{ideUniAmt},{ideOfcProd},{idSerie},{valVersion}", method = RequestMethod.GET)
    public List<SelectItem> getSubseriesListExistInCcdOr(@PathVariable String ideUniAmt, @PathVariable String ideOfcProd,
                                                         @PathVariable BigInteger idSerie, @PathVariable String valVersion) throws SystemException, BusinessException {
        model.getSubSeriesListtExistInCcdOr().clear();
        for (AdmCcd subSerie : cuadroClasificacionDocManagerBoundary.listarSubSeriesExistInCcd(ideUniAmt, ideOfcProd, idSerie, valVersion)) {
            model.getSubSeriesListtExistInCcdOr().addAll(SelectItemBuilder.newBuilder()
                    .addItem(subSerie.getIdeSubserie().getCodSubserie() + "-" + subSerie.getIdeSubserie().getNomSubserie().toUpperCase(), subSerie.getIdeSubserie().getIdeSubserie())
                    .build());
        }
        return model.getSubSeriesListtExistInCcdOr();
    }

    @ResponseBody
    @RequestMapping(value = "/seriesListExistInCcdDe/{ideUniAmt},{ideOfcProd:.+}", method = RequestMethod.GET)
    public List<SelectItem> getSeriesListExistInCcdDe(@PathVariable String ideUniAmt, @PathVariable String ideOfcProd) throws SystemException, BusinessException {
        model.getSeriesListExistInCcdDe().clear();
        for (AdmCcd cuadro : cuadroClasificacionDocManagerBoundary.listarSeriesExistInCcdDe(ideUniAmt, ideOfcProd)) {
            model.getSeriesListExistInCcdDe().addAll(SelectItemBuilder.newBuilder()
                    .addItem(cuadro.getIdeSerie().getCodSerie() + "-" + cuadro.getIdeSerie().getNomSerie().toUpperCase(), cuadro.getIdeSerie().getIdeSerie())
                    .build());
        }
        return model.getSeriesListExistInCcdDe();
    }

    @ResponseBody
    @RequestMapping(value = "/subSeriesListExistInCcdDe/{ideUniAmt},{ideOfcProd:.+},{idSerie}", method = RequestMethod.GET)
    public List<SelectItem> getSubseriesListExistInCcdDe(@PathVariable String ideUniAmt, @PathVariable String ideOfcProd, @PathVariable BigInteger idSerie) throws SystemException, BusinessException {
        model.getSubSeriesListtExistInCcdDe().clear();
        for (AdmCcd subSerie : cuadroClasificacionDocManagerBoundary.listarSubSeriesExistInCcdDe(ideUniAmt, ideOfcProd, idSerie)) {
            model.getSubSeriesListtExistInCcdDe().addAll(SelectItemBuilder.newBuilder()
                    .addItem(subSerie.getIdeSubserie().getCodSubserie() + "-" + subSerie.getIdeSubserie().getNomSubserie().toUpperCase(), subSerie.getIdeSubserie().getIdeSubserie())
                    .build());
        }
        return model.getSubSeriesListtExistInCcdDe();
    }


    @ResponseBody
    @RequestMapping(value = "/unidadAdministrativaCcdOrgList/{versionCdd:.+}", method = RequestMethod.GET)
    public List<SelectItem> getUnidadAdministrativaCddOrgList(@PathVariable String versionCdd) throws SystemException, BusinessException {
        model.getTrdUniAmtList().clear();
        for (TvsOrganigramaAdministrativo unidadAdmin : organigramaBoundary.findAllUniAmdCddOrg(versionCdd)) {
            model.getTrdUniAmtList().addAll(SelectItemBuilder.newBuilder().addItem(unidadAdmin.getNomOrg(), unidadAdmin.getCodOrg()).build());
        }
        return model.getTrdUniAmtList();
    }

    @ResponseBody
    @RequestMapping(value = "/oficinaProductoraCcdOrgList/{versionCdd:.+},{codUniAdmin}", method = RequestMethod.GET)
    public List<SelectItem> getOficinaProductoraCcdOrgList(@PathVariable String versionCdd, @PathVariable String codUniAdmin) throws SystemException, BusinessException {
        model.getTrdOfcProduc().clear();
        for (TvsOrganigramaAdministrativo unidadAdmin : organigramaBoundary.findAllOfcProdCcdOrg(versionCdd, codUniAdmin)) {
            model.getTrdOfcProduc().addAll(SelectItemBuilder.newBuilder().addItem(unidadAdmin.getNomOrg(), unidadAdmin.getCodOrg()).build());
        }
        return model.getTrdOfcProduc();
    }


    @ResponseBody
    @RequestMapping(value = "/unidadAdministrativaTrdOrgList/{codVersionOrg:.+}", method = RequestMethod.GET)
    public List<SelectItem> getUnidadAdministrativaTrdOrgList(@PathVariable String codVersionOrg) throws SystemException, BusinessException {
        model.getTrdUniAmtList().clear();
        for (TvsOrganigramaAdministrativo unidadAdmin : organigramaBoundary.findAllUniAmdTrdOrg(codVersionOrg)) {
            model.getTrdUniAmtList().addAll(SelectItemBuilder.newBuilder().addItem(unidadAdmin.getNomOrg(), unidadAdmin.getCodOrg()).build());
        }
        return model.getTrdUniAmtList();
    }

    @ResponseBody
    @RequestMapping(value = "/oficinaProductoraTrdOrgList/{codUniAdmin},{codVersionOrg:.+}", method = RequestMethod.GET)
    public List<SelectItem> getOficinaProductoraTrdOrgList(@PathVariable String codUniAdmin, @PathVariable String codVersionOrg ) throws SystemException, BusinessException {
        model.getTrdOfcProduc().clear();
        for (TvsOrganigramaAdministrativo unidadAdmin : organigramaBoundary.findAllOfcProdTrdOrg(codUniAdmin, codVersionOrg)) {
            model.getTrdOfcProduc().addAll(SelectItemBuilder.newBuilder().addItem(unidadAdmin.getNomOrg(), unidadAdmin.getCodOrg()).build());
        }
        return model.getTrdOfcProduc();
    }
}
