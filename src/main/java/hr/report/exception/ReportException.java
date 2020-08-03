package hr.report.exception;

import hr.enums.ExceptionSeverityLevel;
import hr.exception.AppRuntimeException;
import hr.validation.i18n.MessageCode;

/**
 * Report exception.
 *
 * @author frano.pecek
 */
public class ReportException extends AppRuntimeException {

    private static final long serialVersionUID = -5263089639794686921L;

    /**
     * Create exception with message code.
     *
     * @param exceptionMessageCode exception message code
     */
    public ReportException(final MessageCode exceptionMessageCode) {
        super(exceptionMessageCode);
    }

    /**
     * Create report exception with given message code and severity level.
     *
     * @param exceptionMessageCode message code enum
     * @param severityLevel exception severity level (WARN, INFO, ERROR)
     */
    public ReportException(final MessageCode exceptionMessageCode, final ExceptionSeverityLevel severityLevel) {
        super(exceptionMessageCode, severityLevel);
    }

    /**
     * Create report exception with message code and throwable.
     *
     * @param exceptionMessageCode message code enum
     * @param cause throwable
     */
    public ReportException(final MessageCode exceptionMessageCode, final Throwable cause) {
        super(exceptionMessageCode, cause);
    }

    /**
     * Create report exception with string message.
     *
     * @param message string message
     */
    public ReportException(final String message) {
        super(message);
    }

    /**
     * Create report exception with throwable.
     *
     * @param cause throwable
     */
    public ReportException(final Throwable cause) {
        super(cause);
    }

    /**
     * Create report exception with string message and throwable.
     *
     * @param message string message
     * @param cause throwable
     */
    public ReportException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * Create report exception with string message and throwable.
     *
     * @param message string message
     * @param cause throwable
     * @param enableSuppression should enable suppression
     * @param writableStackTrace should write stack trace
     */
    public ReportException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
