package com.foundation.soaint.massiveloader.deprecated.web.mvc.subseries;

import co.com.foundation.soaint.documentmanager.business.series.interfaces.SeriesManagerProxy;
import co.com.foundation.soaint.documentmanager.business.subserie.interfaces.SubserieManagerProxy;
import co.com.foundation.soaint.documentmanager.infrastructure.builder.entity.EntityAdmMotCreacionBuilder;
import co.com.foundation.soaint.documentmanager.infrastructure.builder.entity.EntityAdmSerieBuilder;
import co.com.foundation.soaint.documentmanager.infrastructure.builder.entity.EntityAdmSubserieBuilder;
import co.com.foundation.soaint.documentmanager.persistence.entity.AdmMotCreacion;
import co.com.foundation.soaint.documentmanager.persistence.entity.AdmSerie;
import co.com.foundation.soaint.documentmanager.persistence.entity.AdmSubserie;
import com.foundation.soaint.massiveloader.deprecated.web.domain.SubserieVO;
import com.foundation.soaint.massiveloader.deprecated.web.infrastructure.builder.generic.HTTPResponseBuilder;
import com.foundation.soaint.massiveloader.deprecated.web.infrastructure.builder.generic.SubserieVoBuilder;
import com.foundation.soaint.massiveloader.deprecated.web.infrastructure.builder.generic.TableResponseBuilder;
import com.foundation.soaint.massiveloader.web.infrastructure.common.HTTPResponse;
import com.foundation.soaint.massiveloader.web.infrastructure.common.HTTPValidResponse;
import com.foundation.soaint.massiveloader.web.infrastructure.common.TableResponse;
import com.foundation.soaint.massiveloader.web.infrastructure.util.HTMLUtil;
import com.foundation.soaint.massiveloader.deprecated.web.infrastructure.util.constants.EstadoCaracteristicaEnum;
import co.com.foundation.soaint.infrastructure.common.MessageUtil;
import co.com.foundation.soaint.infrastructure.exceptions.BusinessException;
import co.com.foundation.soaint.infrastructure.exceptions.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.Date;
import java.util.Map;

/**
 * Created by jrodriguez on 20/09/2016.
 */

@Controller
@Scope("request")
@RequestMapping(value = "/subseries")
public class SubseriesController {

    @Autowired
    private SubseriesModel model;

    @Autowired
    private SubserieManagerProxy boundary;

    @Autowired
    private SeriesManagerProxy boundaryserie;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String init() throws BusinessException, SystemException {

        model.clear();
        for (AdmSubserie subserie : boundary.findAllSubseries()) {

            SubserieVO subserieVO = SubserieVoBuilder.newBuilder()
                    .withIdeSubserie(subserie.getIdeSubserie())
                    .withActAdministrativo(subserie.getActAdministrativo())
                    .withCarAdministrativa(subserie.getCarAdministrativa().equals(EstadoCaracteristicaEnum.ON.getId()) ?
                            EstadoCaracteristicaEnum.ON.getName() : EstadoCaracteristicaEnum.OFF.getName())
                    .withCarLegal(subserie.getCarLegal().equals(EstadoCaracteristicaEnum.ON.getId()) ?
                            EstadoCaracteristicaEnum.ON.getName() : EstadoCaracteristicaEnum.OFF.getName())
                    .withCarTecnica(subserie.getCarTecnica().equals(EstadoCaracteristicaEnum.ON.getId()) ?
                            EstadoCaracteristicaEnum.ON.getName() : EstadoCaracteristicaEnum.OFF.getName())
                    .withCodSubserie(subserie.getCodSubserie())
                    .withEstSubserie(HTMLUtil.generateStatusColumn(subserie.getEstSubserie()))
                    .withFueBibliografica(subserie.getFueBibliografica())
                    .withNomSubserie(subserie.getNomSubserie())
                    .withNotAlcance(subserie.getNotAlcance())
                    .withNombreMotCreacion(subserie.getIdeMotCreacion().getNomMotCreacion())
                    .withIdMotivo(subserie.getIdeMotCreacion().getIdeMotCreacion())
                    .withIdSerie(subserie.getIdeSerie().getIdeSerie())
                    .withCodSerie(subserie.getIdeSerie().getCodSerie())
                    .withNomSerie(subserie.getIdeSerie().getNomSerie())
                    .withEstSubserieValue(subserie.getEstSubserie())
                    .build();
            model.getSubserieList().add(subserieVO);
        }
        return "subseries/subseries-crud";

    }

    @ResponseBody
    @RequestMapping(value = "/subseriesList", method = RequestMethod.GET)
    public TableResponse<SubserieVO> listSeries() throws SystemException, BusinessException {
        int size = model.getSubserieList().size();
        return TableResponseBuilder.newBuilder()
                .withData(model.getSubserieList())
                .withiTotalDisplayRecords(size)
                .withiTotalRecords(size)
                .build();
    }


    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.POST)
    public HTTPResponse createSubseries(@RequestBody SubserieVO subserie) throws BusinessException, SystemException {


        AdmMotCreacion motCreacion = EntityAdmMotCreacionBuilder.newBuilder()
                .withIdeMotCreacion(subserie.getIdMotivo())
                .build();

        AdmSerie serie = EntityAdmSerieBuilder.newInstance()
                .withIdeSerie(subserie.getIdSerie())
                .build();

        EntityAdmSubserieBuilder builder = EntityAdmSubserieBuilder.newInstance()
                .withCodSubserie(subserie.getCodSubserie())
                .withNomSubserie(subserie.getNomSubserie().trim())
                .withActAdministrativo(subserie.getActAdministrativo())
                .withNotAlcance(subserie.getNotAlcance())
                .withCarAdministrativa(subserie.getCarAdministrativa())
                .withCarLegal(subserie.getCarLegal())
                .withCarTecnica(subserie.getCarTecnica())
                .withEstSubserie(Integer.valueOf(subserie.getEstSubserie()))
                .withFueBibliografica(subserie.getFueBibliografica())
                .withIdeSerie(serie)
                .withIdeMotCreacion(motCreacion)
                .withFecCambio(new Date())
                .withIdeSubserie(subserie.getIdeSubserie());

        boolean flujoActualizacion = subserie.getIdeSubserie() != null;
        String mensaje;

        if (flujoActualizacion) {
            mensaje = MessageUtil.getMessage("subseries.subseries_update_successfully");
            builder.withFecCambio(new Date());
            boundary.updateSubserie(builder.build());
        } else {
            mensaje = MessageUtil.getMessage("subseries.subseries_created_successfully");
            builder.withFecCreacion(new Date());
            boundary.createSubseries(builder.build());
        }
        init();
        return HTTPResponseBuilder.newBuilder()
                .withSuccess(true)
                .withMessage(mensaje)
                .build();
    }


    @ResponseBody
    @RequestMapping(value = "/removesubserie/{idSubserie}", method = RequestMethod.DELETE)
    public HTTPResponse removeSubserie(@PathVariable long idSubserie) throws BusinessException, SystemException {

        String mensaje = null;
        boolean error = false;

        boolean subserieExistInCcd = boundary.subserieExistByIdInCcd(BigInteger.valueOf(idSubserie));

        if (!subserieExistInCcd) {
            boundary.removeSubserie(BigInteger.valueOf(idSubserie));
            mensaje = MessageUtil.getMessage("subseries.subseries_remote_successfully");
            error = true;
        } else {
            mensaje = MessageUtil.getMessage("subseries.subseries_exist_version");
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
     * Validar que el nombre de la subserie no se encuentre repetido en base de datos
     * version 0.1
     * mlara
     * *
     */
    @ResponseBody
    @RequestMapping(value = "/validateNomSubSerie", method = RequestMethod.POST)
    public HTTPValidResponse validateNomSubSerie(@RequestParam Map<String, String> requestParams) throws BusinessException, SystemException {

        if (requestParams.get("codSubserie").equals("")) {
            return HTTPValidResponse.newInstance(!boundary.subSerieExistByName(requestParams.get("nomSubserie").trim()));
        } else {
            return HTTPValidResponse.newInstance(true);
        }
    }


}
