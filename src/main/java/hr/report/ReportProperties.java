package hr.report;

import hr.report.enums.ReportFileType;
import hr.report.processor.ExportProcessor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.toMap;

/**
 * Configuration properties for jasper reports.
 *
 * @author frano.pecek
 */
@ConfigurationProperties(prefix = "report")
@Component
public class ReportProperties {

    /**
     * Report default font.
     */
    public static final String REPORT_FONT_NAME = "DejaVu Sans";

    private String jasperPath;

    @Autowired
    private List<ExportProcessor> exportProcessors;

    /**
     * Create map of all processors as bean.
     *
     * @return Map of all processors by file type
     */
    @Bean
    public Map<ReportFileType, ExportProcessor> getExportProcessors() {
        return exportProcessors.stream().collect(toMap(ExportProcessor::getType, Function.identity()));
    }

    public String getJasperPath() {
        return jasperPath;
    }

    public void setJasperPath(final String jasperPath) {
        this.jasperPath = ensureLeadingSlash(jasperPath);
    }

    public String getHeaderSubreportPath() {
        return jasperPath + "header-subreport.jasper";
    }

    private String ensureLeadingSlash(final String url) {
        if (StringUtils.isEmpty(url)) {
            return "";
        }

        return url.endsWith("/") ? url : url + "/";
    }
}
