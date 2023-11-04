package gov.va.vba.framework.css.cssiam.security;

/**
 * Class MissingParameterException indicates that the application has catch a condition where a parameter is missing 
 * @author VHAISPVANEGI
 *
 */
public class MissingParameterException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a new exception with the specified detail message. 
	 * @param message Message for the exception
	 */
	public MissingParameterException (String message) {
		super(message);
	}
}
