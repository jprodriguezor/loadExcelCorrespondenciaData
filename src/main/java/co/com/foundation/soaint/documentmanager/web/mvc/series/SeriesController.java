package co.com.foundation.soaint.documentmanager.web.mvc.series;

import co.com.foundation.soaint.documentmanager.infrastructure.builder.entity.EntityAdmMotCreacionBuilder;
import co.com.foundation.soaint.documentmanager.infrastructure.builder.entity.EntityAdmSerieBuilder;
import co.com.foundation.soaint.documentmanager.web.infrastructure.builder.generic.HTTPResponseBuilder;
import co.com.foundation.soaint.documentmanager.web.infrastructure.builder.generic.SerieVoBuilder;
import co.com.foundation.soaint.documentmanager.web.infrastructure.builder.generic.TableResponseBuilder;
import co.com.foundation.soaint.documentmanager.web.infrastructure.common.HTTPValidResponse;
import co.com.foundation.soaint.documentmanager.persistence.entity.AdmMotCreacion;
import co.com.foundation.soaint.documentmanager.persistence.entity.AdmSerie;
import co.com.foundation.soaint.documentmanager.business.series.interfaces.SeriesManagerProxy;
import co.com.foundation.soaint.infrastructure.exceptions.BusinessException;
import co.com.foundation.soaint.infrastructure.exceptions.SystemException;
import co.com.foundation.soaint.infrastructure.common.MessageUtil;
import co.com.foundation.soaint.documentmanager.web.infrastructure.common.HTTPResponse;
import co.com.foundation.soaint.documentmanager.web.infrastructure.common.TableResponse;
import co.com.foundation.soaint.documentmanager.web.domain.SerieVO;
import co.com.foundation.soaint.documentmanager.web.infrastructure.util.HTMLUtil;
import co.com.foundation.soaint.documentmanager.web.infrastructure.util.constants.EstadoCaracteristicaEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.Date;
import java.util.Map;

/**
 * Created by jprodriguez on 02/09/2016.
 */
@Controller
@Scope("request")
@RequestMapping(value = "/series")
public class SeriesController {

    @Autowired
    private SeriesModel model;

    @Autowired
    private SeriesManagerProxy boundary;

    public SeriesController() {
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String init() throws BusinessException, SystemException {
        model.clear();

        for (AdmSerie series : boundary.findAllSeries()) {

            SerieVO vo = SerieVoBuilder.newBuilder()
                    .withIdeSerie(series.getIdeSerie())
                    .withCodSerie(series.getCodSerie())
                    .withNomSerie(series.getNomSerie())
                    .withCarLegal(series.getCarLegal().equals(EstadoCaracteristicaEnum.ON.getId())
                            ? EstadoCaracteristicaEnum.ON.getName() : EstadoCaracteristicaEnum.OFF.getName())
                    .withCarAdministrativa(series.getCarAdministrativa().equals(EstadoCaracteristicaEnum.ON.getId())
                            ? EstadoCaracteristicaEnum.ON.getName() : EstadoCaracteristicaEnum.OFF.getName())
                    .withCarTecnica(series.getCarTecnica().equals(EstadoCaracteristicaEnum.ON.getId())
                            ? EstadoCaracteristicaEnum.ON.getName() : EstadoCaracteristicaEnum.OFF.getName())
                    .withActAdministrativo(series.getActAdministrativo())
                    .withIdMotivo(series.getIdeMotCreacion().getIdeMotCreacion())
                    .withNombreMotCreacion(series.getIdeMotCreacion().getNomMotCreacion())
                    .withNotAlcance(series.getNotAlcance())
                    .withFueBibliografica(series.getFueBibliografica())
                    .withEstSerie(HTMLUtil.generateStatusColumn(series.getEstSerie()))
                    .withEstSerieValue(series.getEstSerie())
                    .build();
            model.getSeriesList().add(vo);
        }
        return "series/series-crud";
    }

    @ResponseBody
    @RequestMapping(value = "/seriesList", method = RequestMethod.GET)
    public TableResponse<SerieVO> listSeries() throws SystemException, BusinessException {
        int size = model.getSeriesList().size();
        return TableResponseBuilder.newBuilder()
                .withData(model.getSeriesList())
                .withiTotalDisplayRecords(size)
                .withiTotalRecords(size)
                .build();
    }

    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.POST)
    public HTTPResponse processSeries(@RequestBody SerieVO serie) throws BusinessException, SystemException {

        AdmMotCreacion motCreacion = EntityAdmMotCreacionBuilder.newBuilder()
                .withIdeMotCreacion(serie.getIdMotivo())
                .build();

        EntityAdmSerieBuilder builder = EntityAdmSerieBuilder.newInstance()
                .withActAdministrativo(serie.getActAdministrativo())
                .withCarAdministrativa(serie.getCarAdministrativa())
                .withCarLegal(serie.getCarLegal())
                .withCarTecnica(serie.getCarTecnica())
                .withCodSerie(serie.getCodSerie())
                .withEstSerie(Integer.valueOf(serie.getEstSerie()))
                .withFueBibliografica(serie.getFueBibliografica())
                .withNomSerie(serie.getNomSerie().trim())
                .withNotAlcance(serie.getNotAlcance())
                .withIdeMotCreacion(motCreacion);


        if (serie.getIdeSerie() != null) {
            AdmSerie entityUpdate = builder
                    .withFecCambio(new Date())
                    .withIdeSerie(serie.getIdeSerie())
                    .build();
                boundary.updateSerie(entityUpdate);
            init();
            return HTTPResponseBuilder.newBuilder()
                    .withSuccess(true)
                    .withMessage(MessageUtil.getMessage("series.series_update_successfully"))
                    .build();
        } else {
            AdmSerie entity = builder
                    .withFecCreacion(new Date())
                    .build();

            boundary.createSeries(entity);
            init();
            return HTTPResponseBuilder.newBuilder()
                    .withSuccess(true)
                    .withMessage(MessageUtil.getMessage("series.series_created_successfully"))
                    .build();
        }

    }

    @ResponseBody
    @RequestMapping(value = "/removeSerie/{ideSerie}", method = RequestMethod.DELETE)
    public HTTPResponse removeSerie(@PathVariable long ideSerie) throws BusinessException, SystemException {

        String mensaje = null;
        boolean error = false;

        boolean serieExistInCcd = boundary.serieExistByIdInCcd(BigInteger.valueOf(ideSerie));

        if (!serieExistInCcd) {
            boundary.removeSerie(BigInteger.valueOf(ideSerie));
            mensaje = MessageUtil.getMessage("series.series_remote_successfully");
            error = true;
        } else {
            mensaje = MessageUtil.getMessage("series.series_exist_version");
            error = false;
        }

        //Para refrescar la lista al eliminar
        init();
        return HTTPResponseBuilder.newBuilder()
                .withSuccess(error)
                .withMessage(mensaje)
                .build();
    }

    @ResponseBody
    @RequestMapping(value = "/validateCodSerie", method = RequestMethod.POST)
    public HTTPValidResponse validateCodSerie(@RequestParam Map<String, String> requestParams) throws BusinessException, SystemException {

        if (requestParams.get("ideSerie").equals("")) {
            return HTTPValidResponse.newInstance(!boundary.serieExistByCode(requestParams.get("codSerie")));
        } else {
            return HTTPValidResponse.newInstance(true);
        }
    }

    /**
     * Validar que el nombre de la serie no se encuentre repetido en base de datos
     * version 0.1
     * mlara
     * *
     */
    @ResponseBody
    @RequestMapping(value = "/validateNomSerie", method = RequestMethod.POST)
    public HTTPValidResponse validateNomSerie(@RequestParam Map<String, String> requestParams) throws BusinessException, SystemException {
        if (!requestParams.get("ideSerie").equals("")) {
            return HTTPValidResponse.newInstance(true);
        } else {
            return HTTPValidResponse.newInstance(!boundary.serieExistByName(requestParams.get("nomSerie").trim()));
        }
    }
}
