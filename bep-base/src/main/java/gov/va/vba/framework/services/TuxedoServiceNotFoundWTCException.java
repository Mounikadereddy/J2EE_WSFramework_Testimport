/*
 * the service was not found
 */
package gov.va.vba.framework.services;

import weblogic.wtc.jatmi.TPException;

public class TuxedoServiceNotFoundWTCException extends TuxedoException {

	private static final long serialVersionUID = -1;
	public static final String TUXEDO_ERROR_MESAGE="TuxedoServiceNotFoundWTCException";
	public static final int TUXEDO_ERROR_CODE=6;
	public static final String TUXEDO_MESSAGE_REGEXP = "^(Could not find service)\\s\\w+$";

	public TuxedoServiceNotFoundWTCException() {
		super(null, TUXEDO_ERROR_MESAGE, TUXEDO_ERROR_CODE);
	}
	
	public TuxedoServiceNotFoundWTCException(String message, Exception cause) {
		super(cause, TUXEDO_ERROR_MESAGE + ": " + message, TUXEDO_ERROR_CODE);
	}
	
	public static boolean isMessageMatchingTuxedoServiceNotFoundWTCException(TPException ex){
		return (ex == null || ex.getMessage() == null) ? false : ex.getMessage().matches(TUXEDO_MESSAGE_REGEXP) && ex.gettperrno() == TUXEDO_ERROR_CODE;
	}
}
