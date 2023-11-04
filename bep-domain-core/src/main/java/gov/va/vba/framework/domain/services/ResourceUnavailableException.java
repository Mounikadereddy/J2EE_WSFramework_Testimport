package gov.va.vba.framework.domain.services;

import gov.va.vba.framework.exceptions.BaseRuntimeException;  

public class ResourceUnavailableException extends BaseRuntimeException {

	private static final long serialVersionUID = -3035313695817627899L;

	public ResourceUnavailableException() {
		super();
	}

	public ResourceUnavailableException(String errorMessage, Throwable cause) {
		super(errorMessage, cause);
	}

	public ResourceUnavailableException(String errorMessage) {
		super(errorMessage);
	}

	public ResourceUnavailableException(Throwable cause) {
		super(cause);
	}

}
