package gov.va.vba.framework.exceptions.handler;

import gov.va.vba.framework.logging.Logger;

import java.io.PrintWriter;

import javax.servlet.ServletException;

/**
 * Exception Handler implementation for the Web Layer.
 * 
 * Logs full stack trace of all unhandled Exceptions received by the web module.
 * Provides access methods to retrieve source exception, server, uri and other
 * related information.
 * 
 * Returns unique exception id associated with the exception received and
 * assigns a new one if one not found.
 * 
 * @author Manmohan Yeruva (manmohan.yeruva@va.gov)
 * 
 */
public class WebExceptionHandler extends BaseExceptionHandler {

	/**
	 * Creates the Handler and associates the information provided with the
	 * request object.
	 * 
	 * @param request
	 */
	public WebExceptionHandler() {
		super();
	}

	/**
	 * Creates the Handler and associates the information provided with the
	 * request object. In addition it initializes the logger with appropriate
	 * application package name provided so that the logging can be done into
	 * the application log instead of framework log.
	 * 
	 * @param request
	 */
	public WebExceptionHandler(String loggerPackageName) {
		super();
		
		if(loggerPackageName != null && !loggerPackageName.equals("")){
			logger = Logger.getLogger(loggerPackageName);
		}

		if (loggerPackageName == null || logger == null) {
			logger = Logger.getLogger(this.getClass().getName());
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 */
	@Override
	protected void fillStackTrace(Throwable ex, PrintWriter pw) {
		if (null == ex) {
			return;
		}

		ex.printStackTrace(pw);

		if (ex instanceof ServletException) {
			Throwable cause = ((ServletException) ex).getRootCause();
			if (null != cause) {
				pw.println("Root Cause:");
				fillStackTrace(cause, pw);
			}
		} else {
			Throwable cause = ex.getCause();
			if (null != cause) {
				pw.println("Cause:");
				fillStackTrace(cause, pw);
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getSourceExceptionId(Throwable sourceException) {
		if (sourceException instanceof ServletException) {
			Throwable cause = ((ServletException) sourceException).getCause();
			debug("This SourceException is a ServletException, actual cause is "
					+ cause);

			sourceException = cause;
		}

		errorTrackingId = super.getSourceExceptionId(sourceException);
		if (errorTrackingId == null) {
			errorTrackingId = ExceptionIDGenerator.getUniqueID();
		}

		return errorTrackingId;
	}

	/**
	 * Unique id associated with exception Id. This will be displayed on the
	 * screen when an unknown error raised and the same will be logged in the
	 * log file as well, helps track the errors in the log file with the on
	 * screen error.
	 */
	private String errorTrackingId = null;

	/**
	 * Returns unique exception id.
	 * 
	 * @return
	 */
	public String getErrorTrackingId() {
		return errorTrackingId;
	}

	private void debug(Object o) {
		logger.debug(o);
	}
}
