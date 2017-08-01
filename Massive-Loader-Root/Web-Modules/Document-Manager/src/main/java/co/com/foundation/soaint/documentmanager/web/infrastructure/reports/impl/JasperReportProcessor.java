package co.com.foundation.soaint.documentmanager.web.infrastructure.reports.impl;

import co.com.foundation.soaint.documentmanager.web.infrastructure.reports.ReportProcessor;
import co.com.foundation.soaint.infrastructure.annotations.InfrastructureService;
import co.com.foundation.soaint.infrastructure.exceptions.ExceptionBuilder;
import co.com.foundation.soaint.infrastructure.exceptions.SystemException;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by jrodriguez on 4/12/2016.
 */
@InfrastructureService
public class JasperReportProcessor<R> implements ReportProcessor<R> {

    private static Logger LOGGER = LogManager.getLogger(JasperReportProcessor.class.getName());
    @Autowired
    private ServletContext context;

    @Override
    public void generateReportPdf(HttpServletResponse response, String reportId, List<R> dataSource, Map<String, Object> params, String reportName) throws SystemException {

        response.setContentType("application/pdf;charset=UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=" + reportName);
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            StringBuilder fileName = new StringBuilder();
            fileName.append(context.getRealPath("/"))
                    .append("/WEB-INF/jasper/")
                    .append(reportId);

            JRBeanCollectionDataSource data = new JRBeanCollectionDataSource(dataSource);
            JasperReport jasperReport = JasperCompileManager.compileReport(fileName.toString());
            byte[] array = JasperRunManager.runReportToPdf(jasperReport, params, data);
            response.getOutputStream().write(array);
            response.getOutputStream().close();
        } catch (JRException | IOException e) {
            LOGGER.error("Report jasper error - a system error has occurred", e);
            throw ExceptionBuilder.newBuilder()
                    .withMessage("system.generic.error")
                    .withRootException(e)
                    .buildSystemException();
        }

    }

    @Override
    public void generateReportExcel(HttpServletResponse response, String reportId, List<R> dataSource, Map<String, Object> params, String reportName) throws SystemException {

        response.setContentType("application/vnd.ms-excel");

        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            StringBuilder fileName = new StringBuilder();
            fileName.append(context.getRealPath("/"))
                    .append("/WEB-INF/jasper/")
                    .append(reportId);

            JRBeanCollectionDataSource data = new JRBeanCollectionDataSource(dataSource);
            JasperReport jasperReport = JasperCompileManager.compileReport(fileName.toString());
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, data);

            JRXlsExporter exporterExcel = new JRXlsExporter();
            exporterExcel.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporterExcel.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);

            //Exportamos para el stream declarado previamente
            exporterExcel.exportReport();
            response.getOutputStream().write(baos.toByteArray());
            response.getOutputStream().close();

        } catch (JRException | IOException e) {
            LOGGER.error("Report jasper error - a system error has occurred", e);
            throw ExceptionBuilder.newBuilder()
                    .withMessage("system.generic.error")
                    .withRootException(e)
                    .buildSystemException();
        }

    }

}
