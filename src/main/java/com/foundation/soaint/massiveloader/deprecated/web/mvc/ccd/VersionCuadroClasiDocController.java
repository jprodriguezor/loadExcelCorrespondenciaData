package com.foundation.soaint.massiveloader.deprecated.web.mvc.ccd;

import co.com.foundation.soaint.documentmanager.business.cuadroclasificaciondoc.interfaces.CuadroClasificacionDocManagerProxy;
import co.com.foundation.soaint.documentmanager.business.cuadroclasificaciondoc.interfaces.VersionCuadroClasificacionDocManagerProxy;
import co.com.foundation.soaint.documentmanager.domain.DataCcdVO;
import co.com.foundation.soaint.documentmanager.domain.DataReportCcdVO;
import co.com.foundation.soaint.documentmanager.infrastructure.builder.entity.EntityAdmSubserieBuilder;
import co.com.foundation.soaint.documentmanager.persistence.entity.AdmCcd;
import co.com.foundation.soaint.documentmanager.persistence.entity.AdmSubserie;
import com.foundation.soaint.massiveloader.deprecated.web.domain.CcdVO;
import com.foundation.soaint.massiveloader.deprecated.web.infrastructure.builder.generic.CcdVoBuilder;
import com.foundation.soaint.massiveloader.deprecated.web.infrastructure.builder.generic.TableResponseBuilder;
import com.foundation.soaint.massiveloader.web.infrastructure.common.TableResponse;
import com.foundation.soaint.massiveloader.deprecated.web.reports.ReportProcessor;
import com.foundation.soaint.massiveloader.web.infrastructure.util.HTMLUtil;
import co.com.foundation.soaint.infrastructure.exceptions.BusinessException;
import co.com.foundation.soaint.infrastructure.exceptions.SystemException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by ADMIN on 10/11/2016.
 */
@Controller
@Scope("request")
@RequestMapping(value = "/versionCcd")
public class VersionCuadroClasiDocController {

    @Autowired
    private CuadroClasificacionDocManagerProxy boundary;

    @Autowired
    private CuadroClasDocConfigModel model;

    @Autowired
    private VersionCuadroClasificacionDocManagerProxy ccdBoundary;

    @Autowired
    private ReportProcessor<DataReportCcdVO> reportProcessor;

    @Autowired
    ServletContext context;


    @RequestMapping(value = "", method = RequestMethod.GET)
    public String init() throws BusinessException, SystemException {

        return "ccd/versionccd-ui";
    }

    @ResponseBody
    @RequestMapping(value = "/getCcdByIdVersionAndUniAmtAndOfiProd", method = RequestMethod.POST)
    public TableResponse<CcdVO> getCcdByIdVersionAndUniAmtAndOfiProd(@RequestParam Map<String, String> requestParams) throws SystemException, BusinessException {

        DataCcdVO data = new DataCcdVO();
        data.setVersion(requestParams.get("idVerCcd"));
        data.setIdUniAmt(requestParams.get("idUniAmd"));
        data.setIdOfcProd(requestParams.get("idOfcProd"));

        datosVersionCcd(data);
        int size = model.getCcdListByVersion().size();

        return TableResponseBuilder.newBuilder()
                .withData(model.getCcdListByVersion())
                .withiTotalDisplayRecords(size)
                .withiTotalRecords(size)
                .build();
    }

    public void datosVersionCcd(DataCcdVO data) throws SystemException, BusinessException {

        model.clearListByVersion();
        for (AdmCcd admCcd : boundary.findAllAdmCcdByValVersion(data)) {

            CcdVoBuilder build = CcdVoBuilder.newBuilder()
                    .withIdeCcd(admCcd.getIdeCcd())
                    .withIdeSerie(admCcd.getIdeSerie().getIdeSerie())
                    .withCodSerie(admCcd.getIdeSerie().getCodSerie())
                    .withNomSerie(admCcd.getIdeSerie().getNomSerie())
                    .withIdeorgaadminUniAmt(admCcd.getIdeUnidadAdministrativa())
                    .withIdeorgaadminOfProd(admCcd.getIdeOfcProdCodOrganigrama())
                    .withNomOrgUniAmt(admCcd.getNombreUnidadAdministrativa())
                    .withCodOrgUniAmt(admCcd.getIdeUnidadAdministrativa())
                    .withNomOrgOfProd(admCcd.getNombreOfcProdOrganigrama())
                    .withCodOrgOfProd(admCcd.getIdeOfcProdCodOrganigrama())
                    .withEstCcd(HTMLUtil.generateStatusColumn(admCcd.getEstCcd()))

                    .withEstCcdValue(admCcd.getEstCcd());

            if (admCcd.getIdeSubserie() != null) {
                AdmSubserie subserie = EntityAdmSubserieBuilder.newInstance()
                        .withIdeSubserie(admCcd.getIdeSubserie().getIdeSubserie())
                        .withCodSubserie(admCcd.getIdeSubserie().getCodSubserie())
                        .withNomSubserie(admCcd.getIdeSubserie().getNomSubserie())
                        .build();
                build.withIdeSubserie(subserie.getIdeSubserie());
                build.withCodSubserie(subserie.getCodSubserie());
                build.withNomSubserie(subserie.getNomSubserie());


            }

            model.getCcdListByVersion().add(build.build());
        }

    }

    @RequestMapping(value = "/generateVersionCDDPDF", method = RequestMethod.GET)
    public void generateVersionCDDPDF( HttpServletRequest request, HttpServletResponse response) throws BusinessException, SystemException {

        String version =request.getParameter("idVerCcd");

        List<DataReportCcdVO> dataReport =new ArrayList<>();
        String realpath =context.getRealPath("/");

        Map<String, Object> params = new HashMap<String, Object>();
        String pathImagen =System.getProperty("docmanger.app.resources").concat("/img/colpensiones.png");
        params.put("IMAGEN",pathImagen);

        DataReportCcdVO data =ccdBoundary.versionCcdByOfcProdList(version);
        dataReport.add(data);
        if(request.getParameter("reportType").equals("pdf")){
            reportProcessor.generateReportPdf(response, "RptVersionesCCD.jrxml", dataReport, params, "CDD-" + version + ".pdf");
        }else if(request.getParameter("reportType").equals("xls")){
            reportProcessor.generateReportExcel(response, "RptVersionesCCDExcel.jrxml", dataReport, params, "CCD-" + version + ".xls");
        }

    }

}
