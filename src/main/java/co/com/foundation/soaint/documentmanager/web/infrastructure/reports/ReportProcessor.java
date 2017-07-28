package co.com.foundation.soaint.documentmanager.web.infrastructure.reports;

import co.com.foundation.soaint.infrastructure.exceptions.SystemException;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by jrodriguez on 30/11/2016.
 */


public interface ReportProcessor<R> {

    void generateReportPdf(HttpServletResponse response, String reportId,List<R> dataSource, Map<String, Object> params, String reportName) throws SystemException;

    void generateReportExcel(HttpServletResponse response, String reportId,List<R> dataSource, Map<String, Object> params, String reportName) throws SystemException;
}
