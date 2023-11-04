package gov.va.vba.framework.css.cssiam.security;

/**
 * Class UnrecognizedApplicationException indictaes that the application has catched a condition where an application that the user
 * is trying to reach is not supported by the filter 
 * @author VHAISPVANEGI
 *
 */
public class UnrecognizedApplicationException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a new exception with the specified detail message. 
	 * @param message Message for the exception
	 */
	public UnrecognizedApplicationException(String message) {
		super(message);
	}
}
