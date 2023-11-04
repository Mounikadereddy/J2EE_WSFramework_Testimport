package gov.va.vba.framework.exceptions.handler;

import gov.va.vba.framework.exceptions.BaseException;
import gov.va.vba.framework.exceptions.BaseRuntimeException;
import gov.va.vba.framework.logging.Logger;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.ejb.EJBException;

/**
 * Base implementation class of the Exception Handling Framework.
 * 
 * Provides implementation for logging and handling of all frameworks checked
 * and unchecked exceptions. Wraps all the Java exceptions into framework
 * exceptions.
 * 
 * @author Manmohan Yeruva
 * 
 */
public abstract class BaseExceptionHandler implements ExceptionHandler {

	protected static Logger logger = null;

	protected BaseExceptionHandler() {
		logger = Logger.getLogger(this.getClass().getName());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void handleException(Throwable th) throws Exception {

		debug("Entering handleException");

		Exception toThrow = null;

		// 1. Identify the type of exception is being thrown
		if (th instanceof BaseException) {
			// Do nothing as it is application specific exception
			debug("Caught BaseBusinessCheckedException");
			toThrow = getBaseCheckedExceptionWithId((BaseException) th);
		} else if (th instanceof BaseRuntimeException) {
			// Do nothing as it is application specific exception
			debug("Caught BaseBusinessRuntimeException");
			toThrow = getRuntimeExceptionWithId((BaseRuntimeException) th);
		} else if (th instanceof RuntimeException) {
			// Unhandled RuntimException like 'NullPointerException'
			// Wrap it as system runtime exception under Exception handling
			// framework and rethrow it.
			// This way it assigns a unique errorId for tracking in log file.
			debug("Uncaught RuntimeException, Rethrowing as BaseRuntimeException");
			toThrow = getRuntimeExceptionWithId(new BaseRuntimeException(th));
		} else if (th instanceof Exception) {
			// Unhandled Checked Exception.
			// Wrap it as system checked exception under Exception handling
			// framework and rethrow it.
			// This way it assigns a unique errorId for tracking in log file.
			debug("Uncaught Exception (Checked), Rethrowing as BaseBusinessCheckedException");
			toThrow = getBaseCheckedExceptionWithId(new BaseException(th));
		} else {
			// Unknown Error.
			// Wrap it as system runtime exception under Exception handling
			// framework and rethrow it.
			// This way it assigns a unique errorId for tracking in log file.
			debug("Uncaught Throwable, Rethrowing as BaseRuntimeException");
			toThrow = getRuntimeExceptionWithId(new BaseRuntimeException(th));
		}

		// 2. Log Exception
		logStackTrace(toThrow);

		debug("Exiting handleException");
		throw toThrow;
	}

	public void logStackTrace(Throwable th) {
		logger.error(getStackTrace(th));
	}

	/**
	 * Generates the complete stack trace of the input exception and all
	 * exceptions wrapped if any.
	 * 
	 * @param ex
	 *            Exception for which the trace to be generated.
	 * @return Full exception stack trace.
	 */
	public String getStackTrace(Throwable ex) {
		StringWriter writer = new StringWriter();
		PrintWriter pw = new PrintWriter(writer);
		String exceptionId = getSourceExceptionId(ex);
		pw.println("\n================ " + exceptionId + " ================ ");
		fillStackTrace(ex, pw);
		pw.println("================ " + exceptionId + " ================ ");

		return writer.toString();
	}

	/**
	 * Generate stack trace recursively for all source and wrapped exceptions,
	 * populate PrintWriter with the trace.
	 * 
	 * @param ex
	 *            Exception for which the trace to be generated.
	 * @param pw
	 *            PrintWriter to be populated with error trace.
	 */
	protected void fillStackTrace(Throwable ex, PrintWriter pw) {
		if (null == ex) {
			return;
		}

		ex.printStackTrace(pw);

		Throwable cause = ex.getCause();
		if (null != cause) {
			pw.println("Cause:");
			fillStackTrace(cause, pw);
		}
	}

	/**
	 * Checks to see if the source exception is one of the frameworks exception
	 * and returns the unique exception id associated, returns null otherwise.
	 * 
	 * @param sourceException
	 * @return
	 */
	public String getSourceExceptionId(Throwable sourceException) {
		if (sourceException == null) {
			return "Source Exception Not Found";
		}

		String sourceExceptionId = null;
		if (sourceException instanceof BaseException) {
			sourceExceptionId = ((BaseException) sourceException)
					.getExceptionId();
			debug("Found BaseException");
		} else if (sourceException instanceof BaseRuntimeException) {
			sourceExceptionId = ((BaseRuntimeException) sourceException)
					.getExceptionId();
			debug("Found BaseRuntimeException");
		} else if (sourceException instanceof EJBException) {
			Throwable cause = ((EJBException) sourceException).getCause();

			if (cause instanceof BaseRuntimeException) {
				sourceExceptionId = ((BaseRuntimeException) cause)
						.getExceptionId();
			}

			debug("Found EJBException cause " + cause);
		}
		debug("sourceExceptionId " + sourceExceptionId);
		return sourceExceptionId;
	}

	/**
	 * Assign uniqueId to the BaseException if one is not already assigned.
	 * 
	 * @param bce
	 * @return
	 */
	private BaseException getBaseCheckedExceptionWithId(
			BaseException bce) {
		if (bce.getExceptionId() == null) {
			bce.setExceptionId(ExceptionIDGenerator.getUniqueID());
		}

		return bce;
	}

	/**
	 * Assign uniqueId to the BaseRuntimeException if one is not already assigned.
	 * 
	 * @param bre
	 * @return
	 */
	private BaseRuntimeException getRuntimeExceptionWithId(
			BaseRuntimeException bre) {
		if (bre.getExceptionId() == null) {
			bre.setExceptionId(ExceptionIDGenerator.getUniqueID());
		}

		return bre;
	}

	/**
	 * for debug messages using Logging framework.
	 * 
	 * @param o
	 *            message or object to be logged.
	 */
	private void debug(Object o) {
		logger.debug(o);
	}
}
