package hr.report.exception;

import hr.validation.i18n.HexMessage;
import hr.validation.i18n.MessageCode;

import java.util.HashMap;
import java.util.Map;

/**
 * Exception messages for reports.
 *
 * @author frano.pecek
 */
@HexMessage
public enum ReportExceptionMessageCode implements MessageCode {

    /**
     * File not found message.
     */
    FILE_NOT_FOUND("Report template not found on give path: %s"),

    /**
     * Field nod accessible message.
     */
    FIELD_NOT_ACCESSIBLE("Class field is not accessible."),

    /**
     * File creating error message.
     */
    FILE_CREATION("Error while creating report file."),

    /**
     * Error loading report file message.
     */
    ERROR_LOADING_REPORT_FILE("Error occurred during loading report template");

    private static  final Map<String, ReportExceptionMessageCode> MESSAGE_LOOKUP = new HashMap<>();

    private final String message;


    static {
        for (final ReportExceptionMessageCode message : ReportExceptionMessageCode.values()) {
            MESSAGE_LOOKUP.put(message.message, message);
        }
    }

    /**
     * Create report exception message code with given message.
     *
     * @param message exeception message
     */
    ReportExceptionMessageCode(final String message) {
        this.message = message;
    }

    /**
     * Get exception message key by given message.
     *
     * @param message exception message as string
     * @return report message code as enum
     */
    public static ReportExceptionMessageCode get(final String message) {
        return MESSAGE_LOOKUP.get(message);
    }

    @Override
    public String getKey() {
        return this.name();
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
