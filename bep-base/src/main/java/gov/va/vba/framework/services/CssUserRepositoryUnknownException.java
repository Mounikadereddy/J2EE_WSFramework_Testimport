package gov.va.vba.framework.services;

/**
 * This exception is raised when an unknown excpetion is catched during the
 * CSSUserRepository operations.
 * @author VHAISPVANEGI
 *
 */
public class CssUserRepositoryUnknownException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructs a new CssUserRepositoryUnknownException with the specified detail message. 
	 * The cause is not initialized, and may subsequently be initialized by a call to initCause.
	 * @param message
	 */
	public CssUserRepositoryUnknownException(String message) {
		super(message);
		
	}

	/**
	 * Constructs a new CssUserRepositoryUnknownException with the specified cause and a detail message of 
	 * (cause==null ? null : cause.toString()) (which typically contains the class and detail message of cause). 
	 * @param cause
	 */
	public CssUserRepositoryUnknownException(Throwable cause) {
		super(cause);
		
	}

	/**
	 * Constructs a new CssUserRepositoryUnknownException with the specified detail message and cause. <BR> 
	 * Note that the detail message associated with cause is not automatically incorporated in this exception's detail message.
	 * @param message
	 * @param cause
	 */
	public CssUserRepositoryUnknownException(String message, Throwable cause) {
		super(message, cause);
	
	}
}
