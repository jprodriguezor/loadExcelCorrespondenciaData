package com.soaint.massive.loader.web.mvc.tipologiadocumental;

import co.com.foundation.soaint.documentmanager.business.tipologiadocumental.interfaces.TipologiasDocManagerProxy;
import co.com.foundation.soaint.documentmanager.infrastructure.builder.entity.EntityAdmSoporteBuilder;
import co.com.foundation.soaint.documentmanager.infrastructure.builder.entity.EntityAdmTpgDocBuilder;
import co.com.foundation.soaint.documentmanager.infrastructure.builder.entity.EntityAdmTradDocBuilder;
import com.soaint.massive.loader.web.infrastructure.builder.generic.HTTPResponseBuilder;
import com.soaint.massive.loader.web.infrastructure.builder.generic.TableResponseBuilder;
import com.soaint.massive.loader.web.infrastructure.builder.generic.TipologiasDocVoBuilder;
import co.com.foundation.soaint.documentmanager.persistence.entity.AdmSoporte;
import co.com.foundation.soaint.documentmanager.persistence.entity.AdmTpgDoc;
import co.com.foundation.soaint.documentmanager.persistence.entity.AdmTradDoc;
import co.com.foundation.soaint.infrastructure.exceptions.BusinessException;
import co.com.foundation.soaint.infrastructure.exceptions.SystemException;
import co.com.foundation.soaint.infrastructure.common.MessageUtil;
import com.soaint.massive.loader.web.infrastructure.common.HTTPResponse;
import com.soaint.massive.loader.web.infrastructure.common.HTTPValidResponse;
import com.soaint.massive.loader.web.infrastructure.common.TableResponse;
import com.soaint.massive.loader.web.domain.TipologiaDocVO;
import com.soaint.massive.loader.web.infrastructure.util.HTMLUtil;
import com.soaint.massive.loader.web.infrastructure.util.constants.EstadoCaracteristicaEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.Date;
import java.util.Map;

/**
 * Created by jrodriguez on 22/09/2016.
 */
@Controller
@Scope("request")
@RequestMapping(value = "/tipologiasdoc")
public class TipologiaDocController {

    @Autowired
    private TipologiaDocModel model;

    @Autowired
    private TipologiasDocManagerProxy boundary;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String init() throws BusinessException, SystemException {

        model.clear();

        for (AdmTpgDoc tpgDoc : boundary.findAllTipologiasDoc()) {

            TipologiaDocVO tipologiaDocVO = TipologiasDocVoBuilder.newBuilder()
                    .withIdeTpgDoc(tpgDoc.getIdeTpgDoc())
                    .withCarAdministrativa(tpgDoc.getCarAdministrativa().equals(EstadoCaracteristicaEnum.ON.getId())?
                            EstadoCaracteristicaEnum.ON.getName():EstadoCaracteristicaEnum.OFF.getName())
                    .withCarLegal(tpgDoc.getCarLegal().equals(EstadoCaracteristicaEnum.ON.getId())?
                            EstadoCaracteristicaEnum.ON.getName():EstadoCaracteristicaEnum.OFF.getName())
                    .withCarTecnica(tpgDoc.getCarTecnica().equals(EstadoCaracteristicaEnum.ON.getId())?
                            EstadoCaracteristicaEnum.ON.getName():EstadoCaracteristicaEnum.OFF.getName())
                    .withCodTpgDoc(tpgDoc.getCodTpgDoc())
                    .withEstTpgDoc(HTMLUtil.generateStatusColumn(tpgDoc.getEstTpgDoc()))
                    .withNomTpgDoc(tpgDoc.getNomTpgDoc())
                    .withNotAlcance(tpgDoc.getNotAlcance())
                    .withFueBibliografica(tpgDoc.getFueBibliografica())
                    .withIdSoporte(tpgDoc.getIdeSoporte().getIdeSoporte())
                    .withNomSoport(tpgDoc.getIdeSoporte().getNomSoporte())
                    .withIdTraDocumental(tpgDoc.getIdeTraDocumental().getIdeTradDoc())
                    .withNomTraDocumental(tpgDoc.getIdeTraDocumental().getNomTradDoc())
                    .withEstadoTpgDocValue(tpgDoc.getEstTpgDoc())
                    .build();
            model.getTipologiaDocList().add(tipologiaDocVO);
        }
        return "tipologiasdoc/tipologiadoc-crud";
    }

    @ResponseBody
    @RequestMapping(value = "/tipologiadocumentalList", method = RequestMethod.GET)
    public TableResponse<TipologiaDocVO> listSeries() throws SystemException, BusinessException {
        int size = model.getTipologiaDocList().size();
        return TableResponseBuilder.newBuilder()
                .withData(model.getTipologiaDocList())
                .withiTotalDisplayRecords(size)
                .withiTotalRecords(size)
                .build();
    }

    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.POST)
    public HTTPResponse createTipologiaDoc(@RequestBody TipologiaDocVO tipologiaDoc) throws BusinessException, SystemException {

        AdmTradDoc  tradicionDoc = EntityAdmTradDocBuilder.newBuilder()
                .withIdeTradDoc(tipologiaDoc.getIdTraDocumental())
                .build();

        AdmSoporte  soporte = EntityAdmSoporteBuilder.newBuilder()
                .withIdeSoporte(tipologiaDoc.getIdSoporte())
                .build();

        EntityAdmTpgDocBuilder builder = EntityAdmTpgDocBuilder.newBuilder()
                .withetIdeTpgDoc(tipologiaDoc.getIdeTpgDoc())
                .withetCarLegal(tipologiaDoc.getCarLegal())
                .withetCarTecnica(tipologiaDoc.getCarTecnica())
                .withetCarAdministrativa(tipologiaDoc.getCarAdministrativa())
                .withetNotAlcance(tipologiaDoc.getNotAlcance())
                .withetFueBibliografica(tipologiaDoc.getFueBibliografica())
                .withetIdeTraDocumental(tradicionDoc)
                .withetIdeSoporte(soporte)
                .withetEstTpgDoc(Integer.valueOf(tipologiaDoc.getEstTpgDoc()))
                .withetNomTpgDoc(tipologiaDoc.getNomTpgDoc().trim());

        if(tipologiaDoc.getIdeTpgDoc() !=null) {

            AdmTpgDoc entityUpdate = builder
                    .withetFecCambio(new Date())
                    .withetIdeTpgDoc(tipologiaDoc.getIdeTpgDoc())
                    .build();

            boundary.updateTpgDoc(entityUpdate);
            init();
            return HTTPResponseBuilder.newBuilder()
                    .withSuccess(true)
                    .withMessage(MessageUtil.getMessage("tipologias.tpgdoc_update_successfully"))
                    .build();
        }else {

            AdmTpgDoc entity = builder
                    .withetFecCreacion(new Date())
                    .build();

            boundary.createTpgDoc(entity);
            init();
            return HTTPResponseBuilder.newBuilder()
                    .withSuccess(true)
                    .withMessage(MessageUtil.getMessage("tipologias.tpgdoc_created_successfully"))
                    .build();

        }
    }

    @ResponseBody
    @RequestMapping(value = "/removetpgdoc/{idTpgdoc}", method = RequestMethod.DELETE)
    public HTTPResponse removeTipoDoc(@PathVariable long idTpgdoc) throws BusinessException, SystemException {

        String mensaje = null;
        boolean error = false;

        boolean tipoDocExistInCcd = boundary.tipoDocExistByIdInCcd(BigInteger.valueOf(idTpgdoc));
        if (!tipoDocExistInCcd) {

        boundary.removeTpgDoc(BigInteger.valueOf(idTpgdoc));
            mensaje = MessageUtil.getMessage("tipologias.tpgdoc_remote_successfully");
            error = true;
        } else {
            mensaje = MessageUtil.getMessage("tipologias.tpgdoc_exist_version");
            error = false;
        }

        //Para refrescar la lista al eliminar
        init();
        return HTTPResponseBuilder.newBuilder()
                .withSuccess(error)
                .withMessage(mensaje)
                .build();
    }
    /**
     * Validar que el nombre de la tipologia no se encuentre repetido en base de datos
     * version 0.1
     * mlara
     * *
     */
    @ResponseBody
    @RequestMapping(value = "/validateNomTpcDoc", method = RequestMethod.POST)
    public HTTPValidResponse validateNomTpcDoc(@RequestParam Map<String, String> requestParams) throws BusinessException, SystemException {

        if (requestParams.get("ideTpgDoc").trim().equals("")) {
            return HTTPValidResponse.newInstance(!boundary.tpcDocExistByName(requestParams.get("nomTpgDoc").trim()));
        } else {
            return HTTPValidResponse.newInstance(true);
        }
    }
}
