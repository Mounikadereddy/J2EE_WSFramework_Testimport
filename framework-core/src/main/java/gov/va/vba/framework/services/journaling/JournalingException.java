package gov.va.vba.framework.services.journaling;

import gov.va.vba.framework.exceptions.BaseRuntimeException;

/**
 * 
 * Exception thrown when issues encountered while enabling Journaling.
 * 
 * @author Manmohan Yeruva
 * 
 */
public class JournalingException extends BaseRuntimeException {

	private static final long serialVersionUID = -8001059231378697979L;

	public JournalingException() {
		super();
	}

	public JournalingException(String errorMessage, Throwable cause) {
		super(errorMessage, cause);
	}

	public JournalingException(String errorMessage) {
		super(errorMessage);
	}

	public JournalingException(Throwable cause) {
		super(cause);
	}
}
