package hr.report.dto;

import hr.report.enums.ReportFileType;

/**
 * Report description file.
 *
 * @author frano.pecek
 */
public class ReportDto {

    private final ReportFileType fileType;

    /**
     * Construct new report dto object.
     *
     * @param fileType report file type
     */
    public ReportDto(final ReportFileType fileType) {
        this.fileType = fileType;
    }

    /**
     * Get report file type.
     *
     * @return report file type
     */
    public ReportFileType getFileType() {
        return fileType;
    }

}
