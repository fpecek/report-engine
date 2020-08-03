package hr.report.impl;

import hr.report.ReportEngine;
import hr.report.ReportProperties;
import hr.report.enums.ReportFileType;
import hr.report.exception.ReportException;
import hr.report.exception.ReportExceptionMessageCode;
import hr.report.processor.ExportProcessor;
import net.sf.jasperreports.engine.DefaultJasperReportsContext;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRFont;
import net.sf.jasperreports.engine.JRPropertiesUtil;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Report engine implementation.
 *
 * @author frano.pecek
 */
@Component
public class ReportEngineImpl implements ReportEngine {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReportEngineImpl.class);

    private final Map<ReportFileType, ExportProcessor> exportProcessors;
    private final ResourceLoader resourceLoader;

    /**
     * Create report engine implementation.
     *
     * @param exportProcessors map of export processors
     * @param resourceLoader resource loader for loading report template
     */
    @Autowired
    public ReportEngineImpl(final Map<ReportFileType, ExportProcessor> exportProcessors,
                            final ResourceLoader resourceLoader) {
        this.exportProcessors = exportProcessors;
        this.resourceLoader = resourceLoader;
    }

    @Override
    public <T> ByteArrayOutputStream generateReport(final ReportFileType fileType,
                                                    final List<T> reportData, final String reportFileTemplatePath) {
        Objects.requireNonNull(fileType, "Please specify file type");

        final Resource resource = resourceLoader.getResource(reportFileTemplatePath);

        final InputStream reportSourceFile;
        try {
            reportSourceFile = resource.getInputStream();
        } catch (IOException ex) {
            LOGGER.error("Report template file not found on path: " + reportFileTemplatePath, ex.getMessage());
            throw new ReportException(ReportExceptionMessageCode.FILE_NOT_FOUND, ex).addMessageParameters(reportFileTemplatePath);
        }

        final JasperReport jasperReport;
        try {
            jasperReport = (JasperReport) JRLoader.loadObject(reportSourceFile);
        } catch (final JRException ex) {
            throw new ReportException(ReportExceptionMessageCode.ERROR_LOADING_REPORT_FILE, ex).addMessageParameters(fileType);
        } finally {
            try {
                reportSourceFile.close();
            } catch (IOException e) {
                LOGGER.error("ReportEngineImpl: error closing input stream");
            }
        }

        setDefaultProperties();
        final JRDataSource dataSource = new JRBeanCollectionDataSource(reportData);

        return exportProcessors.get(fileType).exportReport(dataSource, jasperReport);
    }

    private void setDefaultProperties() {
        final JRPropertiesUtil jasperProperties = JRPropertiesUtil.getInstance(DefaultJasperReportsContext.getInstance());
        jasperProperties.setProperty(JRFont.DEFAULT_FONT_NAME, ReportProperties.REPORT_FONT_NAME);
    }

}
