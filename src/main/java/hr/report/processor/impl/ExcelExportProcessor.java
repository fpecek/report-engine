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
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;

/**
 * Export report to excel file.
 *
 * @author frano.pecek
 */
@Component
public class ExcelExportProcessor implements ExportProcessor {

    @Override
    public ByteArrayOutputStream exportReport(final JRDataSource reportData, final JasperReport jasperReport) {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            final JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap<>(), reportData);

            final JRXlsExporter xlsExporter = new JRXlsExporter();
            final SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
            configuration.setDetectCellType(true);
            configuration.setCollapseRowSpan(false);
            configuration.setWrapText(true);
            configuration.setIgnorePageMargins(true); // remove top and bottom margins
            configuration.setRemoveEmptySpaceBetweenColumns(true); // remove left and right margins

            xlsExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            xlsExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
            xlsExporter.setConfiguration(configuration);
            xlsExporter.exportReport();
            return outputStream;
        } catch (JRException | IOException | JRRuntimeException ex) {
            throw new ReportException("Error occurred during report export", ex);
        }
    }

    @Override
    public ReportFileType getType() {
        return ReportFileType.EXCEL;
    }
}
