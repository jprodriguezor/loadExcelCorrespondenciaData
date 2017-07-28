package com.foundation.soaint.massiveloader.deprecated.web.mvc.trd;

import co.com.foundation.soaint.documentmanager.business.tablaretenciondisposicion.interfaces.VersionesTabRetDocManagerProxy;
import co.com.foundation.soaint.documentmanager.domain.DataReportTrdVO;
import co.com.foundation.soaint.documentmanager.domain.TRDTableVO;
import co.com.foundation.soaint.documentmanager.persistence.entity.AdmVersionTrd;
import com.foundation.soaint.massiveloader.deprecated.web.infrastructure.builder.generic.SelectItemBuilder;
import com.foundation.soaint.massiveloader.web.infrastructure.common.SelectItem;
import com.foundation.soaint.massiveloader.deprecated.web.reports.ReportProcessor;
import co.com.foundation.soaint.infrastructure.exceptions.BusinessException;
import co.com.foundation.soaint.infrastructure.exceptions.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by jrodriguez on 26/11/2016.
 */

@Controller
@Scope("request")
@RequestMapping(value = "/versionTrd")
public class VersionesTrdController {

    @Autowired
    private TrdModel model;

    @Autowired
    private VersionesTabRetDocManagerProxy versionesTrdBoundary;

    @Autowired
    private ReportProcessor<DataReportTrdVO> reportProcessor;

    @Autowired
    ServletContext context;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String init() throws BusinessException, SystemException {
        return "trd/versionTrd-ui";
    }


    @ResponseBody
    @RequestMapping(value = "/versionesTrdOfcProd/{idOfcProd:.+}", method = RequestMethod.GET)
    public List<SelectItem> getVersionCcdList(@PathVariable String idOfcProd) throws SystemException, BusinessException {
        model.getVersionesTrdOfcProd().clear();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy h:mm a");

        for (AdmVersionTrd version : versionesTrdBoundary.findAllVersionPorOfcProd(idOfcProd)) {

            Date fechaVersion = version.getFecVersion();
            model.getVersionesTrdOfcProd().addAll(SelectItemBuilder.newBuilder()
                    .addItem(version.getValVersion() + "-" + formatoFecha.format(fechaVersion),
                            version.getIdeVersion()).build());
        }
        return model.getVersionesTrdOfcProd();

    }

    @ResponseBody
    @RequestMapping(value = "/versionTableTRDPorOfcProd/{ideOfcProd:.+}/{version:.+}/{valVersionOrg:.+}", method = RequestMethod.GET)
    public TRDTableVO versionTableTRDPorOfcProd(@PathVariable String ideOfcProd, @PathVariable BigInteger version, @PathVariable String valVersionOrg) throws SystemException, BusinessException {
        return versionesTrdBoundary.versionTrdByOfcProdList(ideOfcProd, version,valVersionOrg);
    }

    @RequestMapping(value = "/generateVersionTrdPDF", method = RequestMethod.GET)
    public void generateVersionTrdPDF( HttpServletRequest request, HttpServletResponse response) throws BusinessException, SystemException {

        String ideOfcProd =request.getParameter("codOfcProd");
        BigInteger version =new BigInteger(request.getParameter("idVersionOfcProd"));
        String valVersionOrg =request.getParameter("codVersionOrg");

        List<DataReportTrdVO> dataReport =new ArrayList<>();

        Map<String, Object> params = new HashMap<String, Object>();
        String pathImagen =System.getProperty("docmanger.app.resources").concat("/img/colpensiones.png");
                params.put("IMAGEN", pathImagen);

        DataReportTrdVO data =versionesTrdBoundary.dataTrdByOfcProdList(ideOfcProd,version,valVersionOrg);
        dataReport.add(data);
        if(request.getParameter("reportType").equals("pdf")){
            reportProcessor.generateReportPdf(response, "RptVersionesTRD.jrxml", dataReport, params, "TRD" + ideOfcProd + ".pdf");
        }else if(request.getParameter("reportType").equals("xls")){
            reportProcessor.generateReportExcel(response, "RptVersionesTRDExcel.jrxml", dataReport, params, "TRD" + ideOfcProd + ".xls");
        }

    }

}