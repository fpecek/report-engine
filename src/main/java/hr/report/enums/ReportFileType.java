package hr.report.enums;

import hr.enums.EnumValue;
/**
 * List of all available file types for generating report.
 *
 * @author frano.pecek
 */
public enum ReportFileType implements EnumValue<ReportFileType, String> {

    /**
     * Export report to PDF file.
     */
    PDF(".pdf"),

    /**
     * Export report to EXCEL file.
     */
    EXCEL(".xls"),

    /**
     * Export report to HTML file.
     */
    HTML(".html"),

    /**
     * Export report to XML file.
     */
    XML(".xml");

    private final String value;

    /**
     * Create new report file type.
     *
     * @param value file type extension
     */
    ReportFileType(final String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }
}
