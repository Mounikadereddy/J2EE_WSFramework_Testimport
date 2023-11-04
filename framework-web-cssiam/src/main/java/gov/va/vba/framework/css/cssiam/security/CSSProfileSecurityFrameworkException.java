package gov.va.vba.framework.css.cssiam.security;

/**
 * Class CSSProfileSecurityFrameworkException indicates that the application has caught an exception getting the CSS Profile
 * from the security framework EJB 
 * @author VHAISPVANEGI
 *
 */
public class CSSProfileSecurityFrameworkException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a new exception with the specified detail message. 
	 * @param message Message for the exception
	 */
	public CSSProfileSecurityFrameworkException (String message) {
		super(message);
	}
	
	/**
	 * Constructs a new exception with the specified detail message. 
	 * @param message Message for the exception
	 * @param cause Cause for the exception
	 */
	public CSSProfileSecurityFrameworkException (String message, Throwable cause) {
		super(message, cause);
	}
}
