package gov.va.vba.framework.exceptions;

public class ServiceLocatorException extends RuntimeException{
	
	private static final long serialVersionUID = -737554074800525501L;

	public ServiceLocatorException() {
		super();
	}

	public ServiceLocatorException(String errorMessage) {
		super(errorMessage);
	}
	
	public ServiceLocatorException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServiceLocatorException(Throwable cause) {
		super(cause);
	}

}
