package gov.va.vba.framework.css.cssiam.security;

/**
 * Class  MissingHeaderException indicates that the application has caught a condition where a header is missing 
 * @author VHAISPVANEGI
 *
 */
public class MissingHeaderException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a new exception with the specified detail message. 
	 * @param message Message for the exception
	 */
	public MissingHeaderException (String message) {
		super(message);
	}
}
