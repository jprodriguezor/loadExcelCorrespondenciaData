package co.com.foundation.soaint.documentmanager.web.mvc.relEquivalencia;

import co.com.foundation.soaint.documentmanager.business.cuadroclasificaciondoc.interfaces.CuadroClasificacionDocManagerProxy;
import co.com.foundation.soaint.documentmanager.business.relEquivalencia.interfaces.RelEquivalenciaDocManagerProxy;
import co.com.foundation.soaint.documentmanager.domain.CcdItemVO;
import co.com.foundation.soaint.documentmanager.infrastructure.builder.entity.EntityAdmRelEqDestinoBuilder;
import co.com.foundation.soaint.documentmanager.infrastructure.builder.entity.EntityAdmRelEqOrigenBuilder;
import co.com.foundation.soaint.documentmanager.persistence.entity.AdmRelEqDestino;
import co.com.foundation.soaint.documentmanager.persistence.entity.AdmRelEqOrigen;
import co.com.foundation.soaint.documentmanager.web.domain.RelEquiVO;
import co.com.foundation.soaint.documentmanager.web.infrastructure.builder.generic.HTTPResponseBuilder;
import co.com.foundation.soaint.documentmanager.web.infrastructure.builder.generic.RelEquiVoBuilder;
import co.com.foundation.soaint.documentmanager.web.infrastructure.builder.generic.TableResponseBuilder;
import co.com.foundation.soaint.documentmanager.web.infrastructure.common.HTTPResponse;
import co.com.foundation.soaint.documentmanager.web.infrastructure.common.TableResponse;
import co.com.foundation.soaint.infrastructure.common.MessageUtil;
import co.com.foundation.soaint.infrastructure.exceptions.BusinessException;
import co.com.foundation.soaint.infrastructure.exceptions.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.Date;

/**
 * Created by ADMIN on 01/12/2016.
 */
@Controller
@Scope("request")
@RequestMapping(value = "/relEquivalencia")
public class RelacionEquivalenciaController {

    @Autowired
    private RelacionEquivalenciaModel model;

    @Autowired
    private RelEquivalenciaDocManagerProxy boundary;


    @Autowired
    private CuadroClasificacionDocManagerProxy boundaryCcd;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String init() throws BusinessException, SystemException {

        model.clear();
        for (AdmRelEqOrigen admRelEqOrigen : boundary.findAllRelEqui()) {
            RelEquiVO vo = new RelEquiVoBuilder()
                    .withIdeRelOrigen(admRelEqOrigen.getIdeRelOrigen().toString())

                    .withIdeUniAmtOr(admRelEqOrigen.getIdeUniAmt())
                    .withNombreUAdminO(admRelEqOrigen.getNombreUAdminO())

                    .withIdeOfcProdOr(admRelEqOrigen.getIdeOfcProd())
                    .withNombreOProd(admRelEqOrigen.getNombreOProd())

                    .withIdeSerieOr(admRelEqOrigen.getAdmSerie().getIdeSerie())
                    .withCodSerieOr(admRelEqOrigen.getAdmSerie().getCodSerie())
                    .withNomSerieOr(admRelEqOrigen.getAdmSerie().getNomSerie())

                    .withIdeSubserieOr(admRelEqOrigen.getAdmSubserie().getIdeSubserie())
                    .withCodSubserieOr(admRelEqOrigen.getAdmSubserie().getCodSubserie())
                    .withNomSubserieOr(admRelEqOrigen.getAdmSubserie().getNomSubserie())

                    .withFecCreacionOr(admRelEqOrigen.getFecCreacion())

                    .withIdeRelDestino(admRelEqOrigen.getAdmRelEqDestinoList().get(0).getIdeRelDestino())

                    .withIdeUniAmtDe(admRelEqOrigen.getAdmRelEqDestinoList().get(0).getIdeUniAmt())
                    .withNombreUAdminD(admRelEqOrigen.getAdmRelEqDestinoList().get(0).getNombreUAdminD())
                    .withIdeOfcProdDe(admRelEqOrigen.getAdmRelEqDestinoList().get(0).getIdeOfcProd())
                    .withNombreOProdD(admRelEqOrigen.getAdmRelEqDestinoList().get(0).getNombreOProdD())

                    .withIdeSerieDe(admRelEqOrigen.getAdmRelEqDestinoList().get(0).getAdmSerie().getIdeSerie())
                    .withCodSerieDe(admRelEqOrigen.getAdmRelEqDestinoList().get(0).getAdmSerie().getCodSerie())
                    .withNomSerieDe(admRelEqOrigen.getAdmRelEqDestinoList().get(0).getAdmSerie().getNomSerie())

                    .withIdeSubserieDe(admRelEqOrigen.getAdmRelEqDestinoList().get(0).getAdmSubserie().getIdeSubserie())
                    .withCodSubserieDe(admRelEqOrigen.getAdmRelEqDestinoList().get(0).getAdmSubserie().getCodSubserie())
                    .withNomSubserieDe(admRelEqOrigen.getAdmRelEqDestinoList().get(0).getAdmSubserie().getNomSubserie())

                    .build();

            model.getRelEquiList().add(vo);
        }

        return "relEqui/relEqui-crud";
    }

    @ResponseBody
    @RequestMapping(value = "/relEquiList", method = RequestMethod.GET)
    public TableResponse<RelEquiVO> listRelEqui() throws SystemException, BusinessException {
        int size = model.getRelEquiList().size();
        return TableResponseBuilder.newBuilder()
                .withData(model.getRelEquiList())
                .withiTotalDisplayRecords(size)
                .withiTotalRecords(size)
                .build();
    }

    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.POST)
    public HTTPResponse createRelEquivalencia(@RequestBody RelEquiVO relEquiVO) throws BusinessException, SystemException {

        String mensaje = "";
        boolean error = false;

        boolean iguales = validateEquals(relEquiVO.getIdeOrgaAdminUniAmtOr(), relEquiVO.getIdeOrgaAdminOfProdOr(), relEquiVO.getIdeSerieOr(), relEquiVO.getIdeSubserieOr(),
                relEquiVO.getideOrgaAdminUniAmtDes(), relEquiVO.getideOrgaAdminOfProdDes(), relEquiVO.getIdeSerieDe(), relEquiVO.getIdeSubserieDe());
        BigInteger numVersionOrg = boundaryCcd.getNumVersionOrgByValVersion(relEquiVO.getValVersionCCD());


        if (!iguales) {

            String[] arrayDataOficinaAdminOr = relEquiVO.getIdeOrgaAdminUniAmtOr().split("-");
            String[] arrayDataOficinaAdminDe = relEquiVO.getideOrgaAdminUniAmtDes().split("-");
            boolean existRelEqui = validateExistRelEqui(arrayDataOficinaAdminOr[1], relEquiVO.getIdeOrgaAdminOfProdOr(), relEquiVO.getIdeSerieOr(), relEquiVO.getIdeSubserieOr(), arrayDataOficinaAdminDe[1], relEquiVO.getideOrgaAdminOfProdDes(), relEquiVO.getIdeSerieDe(), relEquiVO.getIdeSubserieDe());

            if (!existRelEqui) {

                EntityAdmRelEqOrigenBuilder admRelEqOrigen = EntityAdmRelEqOrigenBuilder.newBuilder()
                        .withIdeUniAmt(arrayDataOficinaAdminOr[1])
                        .withIdeOfcProd(relEquiVO.getIdeOrgaAdminOfProdOr())
                        .withFecCreacion(new Date())
                        .withIdeSerie(relEquiVO.getIdeSerieOr())
                        .withIdeSubserie(relEquiVO.getIdeSubserieOr())
                        .withNumVersionOrg(numVersionOrg.toString());


                AdmRelEqOrigen origen = boundary.createRelEquivaleniaOrigen(admRelEqOrigen.build());


                EntityAdmRelEqDestinoBuilder admRelEqDestino = EntityAdmRelEqDestinoBuilder.newBuilder()
                        .withIdeSerie(relEquiVO.getIdeSerieDe())
                        .withFecCreacion(new Date())
                        .withIdeSubserie(relEquiVO.getIdeSubserieDe())
                        .withIdeUniAmt(arrayDataOficinaAdminDe[1])
                        .withIdeOfcProd(relEquiVO.getideOrgaAdminOfProdDes());

                boundary.createRelEquivaleniaDestino(admRelEqDestino.build(), origen.getIdeRelOrigen());
                mensaje = MessageUtil.getMessage("relEqui.relEqui_created_successfully");
                error = true;
            } else {
                mensaje = MessageUtil.getMessage("relEqui.relEqui_exits");
                error = false;
            }

        } else {

            mensaje = MessageUtil.getMessage("relEqui.relEqui_equals");
            error = false;
        }

        init();
        return HTTPResponseBuilder.newBuilder()
                .withSuccess(error)
                .withMessage(mensaje)
                .build();
    }

    @ResponseBody
    @RequestMapping(value = "/removeRelEqui/{ideRelOrigen}", method = RequestMethod.DELETE)
    public HTTPResponse removeRelEquivalencia(@PathVariable BigInteger ideRelOrigen) throws BusinessException, SystemException {

        boundary.removeRelEquivalencia(ideRelOrigen);
        //Para refrescar la lista al eliminar
        init();
        return HTTPResponseBuilder.newBuilder()
                .withSuccess(true)
                .withMessage(MessageUtil.getMessage("relEqui.relEqui_remote_successfully"))
                .build();
    }


    public Boolean validateExistRelEqui(String ideUniAmtOr, String ideOfcProdOr, BigInteger ideSerieOr, BigInteger ideSubserieOr,
                                        String ideUniAmtDe, String ideOfcProdDe, BigInteger ideSerieDe, BigInteger ideSubserieDe) throws BusinessException, SystemException {
        return boundary.validateExistRelEqui(ideUniAmtOr, ideOfcProdOr, ideSerieOr, ideSubserieOr,
                ideUniAmtDe, ideOfcProdDe, ideSerieDe, ideSubserieDe);

    }

    public Boolean validateEquals(String ideUniAmtOr, String ideOfcProdOr, BigInteger ideSerieOr, BigInteger ideSubserieOr,
                                  String ideUniAmtDe, String ideOfcProdDe, BigInteger ideSerieDe, BigInteger ideSubserieDe) throws BusinessException, SystemException {
        Boolean iguales = false;
        Boolean serieIguales = false;
        Boolean subserieIguales = false;

        if (ideUniAmtOr.equalsIgnoreCase(ideUniAmtDe) && ideOfcProdOr.equalsIgnoreCase(ideOfcProdDe)) {

            iguales = true;

            if (ideSerieOr != null && ideSerieDe != null) {
                if (ideSerieOr.toString().equalsIgnoreCase(ideSerieDe.toString())) {
                    serieIguales = true;
                } else {
                    iguales = false;
                }
            }
            if (ideSubserieOr != null && ideSubserieDe != null) {
                if (ideSubserieOr.toString().equalsIgnoreCase(ideSubserieDe.toString())) {
                    subserieIguales = true;
                } else {
                    iguales = false;
                }
            }

        } else {
            iguales = false;
        }

        return iguales;

    }


}
