package gov.va.vba.framework.exceptions;


/**
 * High level Runtime Exception in the Frameworks Exception hierarchy of runtime
 * exceptions.
 * 
 * All the application specific Runtime exceptions need to be a subclass of this
 * or it's child exceptions.
 * 
 * @author Manmohan Yeruva (manmohan.yeruva@va.gov)
 * 
 */
public class BaseRuntimeException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -737554074800525501L;
	private String exceptionId = null;

	public BaseRuntimeException() {
		super();
	}

	public BaseRuntimeException(String errorMessage) {
		super(errorMessage);
	}

	public BaseRuntimeException(Throwable cause) {
		super(cause);
	}

	public BaseRuntimeException(String errorMessage, Throwable cause) {
		super(errorMessage, cause);
	}

	public void setExceptionId(String exceptionId) {
		this.exceptionId = exceptionId;
	}

	public String getExceptionId() {
		return exceptionId;
	}
}
