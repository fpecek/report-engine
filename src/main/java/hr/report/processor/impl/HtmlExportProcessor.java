package hr.report.processor.impl;

import hr.report.enums.ReportFileType;
import hr.report.exception.ReportException;
import hr.report.processor.ExportProcessor;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRRuntimeException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;

/**
 * Export report to HTML.
 *
 * @author frano.pecek
 */
@Component
public class HtmlExportProcessor implements ExportProcessor {

    @Override
    public ByteArrayOutputStream exportReport(final JRDataSource reportData, final JasperReport jasperReport) {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            final JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap<>(), reportData);
            final HtmlExporter exporter = new HtmlExporter();
            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            exporter.setExporterOutput(new SimpleHtmlExporterOutput(outputStream));
            exporter.exportReport();
            return outputStream;
        } catch (JRException | IOException | JRRuntimeException ex) {
            throw new ReportException("Error occurred during report export", ex);
        }
    }

    @Override
    public ReportFileType getType() {
        return ReportFileType.HTML;
    }
}
