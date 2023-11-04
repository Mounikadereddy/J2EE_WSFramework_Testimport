package gov.va.vba.framework.services;

/**
 * This exception contains as well the reasonCode that specifies the error identified when trying to load a CSSUser
 * from the repository
 * Possible reasonCodes:<BR>
 *                    css.cssprofile.user.unknown:                            Unknown User for Application: IAM authenticated user is not known in CSS for specified application <BR>
 *                    css.cssprofile.user.application.allUserStationsLocked:  Application is locked for all the User's Stations <BR>
 *                    css.cssprofile.user.station.allStationsLocked:          User is locked for all the User's Stations <BR>
 *                    css.cssprofile.user.application.noAccess:               Application not approved for all User's Stations<BR>
 *                    css.cssprofile.application.unknown:                     Application that the user is trying to access is not know to CSS<BR>
 *                    css.cssprofile.application.globallyLocked:              Application is globally Locked. <BR>
 * @author VHAISPVANEGI
 *
 */
public class CssUserRepositoryException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * Error identified when trying to load a CssUser from the repository
	 */
	private String reasonCode;
	
	/**
	 * Constructs a new CssUserRepositoryException with the specified detail message. 
	 * The cause is not initialized, and may subsequently be initialized by a call to initCause.
	 * @param message
	 */
	public CssUserRepositoryException(String message) {
		super(message);
	}

	/**
	 * Constructs a new CssUserRepositoryException with the specified cause and a detail message of 
	 * (cause==null ? null : cause.toString()) (which typically contains the class and detail message of cause). 
	 * @param cause
	 */
	public CssUserRepositoryException(Throwable cause) {
		super(cause);
		
	}

	/**
	 * Constructs a new CssUserRepositoryException with the specified detail message and cause. <BR> 
	 * Note that the detail message associated with cause is not automatically incorporated in this exception's detail message.
	 * @param message
	 * @param cause
	 */
	public CssUserRepositoryException(String message, Throwable cause) {
		super(message, cause);
	
	}

	/**
	 * Gets the specific error code for this CssUserRepositoryException
	 * @return
	 */
	public String getReasonCode() {
		return reasonCode;
	}

	/**
	 * Sets the specific error code for this CssUserRepositoryException
	 * @param reasonCode
	 */
	public void setReasonCode(String reasonCode) {
		this.reasonCode = reasonCode;
	}
}
