package hr.report;

import hr.report.enums.ReportFileType;

import java.io.ByteArrayOutputStream;
import java.util.List;

/**
 * Engine for generating reports.
 *
 * @author frano.pecek
 */
public interface ReportEngine {

    /**
     * Method for generating jasper report file.
     * Method currently support four type of files (PDF, EXCEL, HTML OR XML)
     *
     * @param fileType Type of exported file (PDF, EXCEL, HTML OR XML)
     * @param reportData Data that will be shown on report
     * @param reportFileTemplatePath Path to jasper template file
     * @param <T> - Data type
     * @return - Byte array stream of generated report
     */
    <T> ByteArrayOutputStream generateReport(ReportFileType fileType, List<T> reportData, String reportFileTemplatePath);

}
