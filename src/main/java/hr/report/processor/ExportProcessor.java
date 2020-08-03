package hr.report.processor;

import hr.report.enums.ReportFileType;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperReport;

import java.io.ByteArrayOutputStream;

/**
 * Base interface for all processors.
 *
 * @author frano.pecek
 */
public interface ExportProcessor {

    /**
     * Process and export generated report.
     *
     * @param reportData datasoruce for report
     * @param jasperReport report template
     *
     * @return stream of generated report
     */
    ByteArrayOutputStream exportReport(JRDataSource reportData, JasperReport jasperReport);

    /**
     * Get report file type.
     *
     * @return report file type
     */
    ReportFileType getType();

}
