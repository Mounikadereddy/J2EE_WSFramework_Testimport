package gov.va.vba.framework.exceptions.handler;

/**
 * Exception handler API providing methods to handle and log the exceptions and
 * errors generated in Web and Ejb layers.
 * 
 * @author psimyeru
 * 
 */
public interface ExceptionHandler {

	/**
	 * Reads the input <code>Throwable</code>, logs the stack trace and rethrows
	 * error as appropriate Checked/Runtime Exception.
	 * 
	 * @param th
	 * @throws Exception
	 */
	void handleException(Throwable th) throws Exception;

	/**
	 * Logs the stack trace of <code>Throwable</code> input.
	 * 
	 * @param th
	 */
	void logStackTrace(Throwable th);
	
	String getStackTrace(Throwable th);
	
	String getSourceExceptionId(Throwable sourceException);

}
