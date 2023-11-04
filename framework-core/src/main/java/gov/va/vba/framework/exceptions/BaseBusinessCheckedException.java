package gov.va.vba.framework.exceptions;

/**
 * High level Checked Exception for all checked business exceptions.
 * 
 * All the application specific Checked exceptions need to be a subclass of this
 * class or it's child classes.
 * 
 * @author Manmohan Yeruva (manmohan.yeruva@va.gov)
 * 
 */
public class BaseBusinessCheckedException extends BaseException {

	private static final long serialVersionUID = -8431207402195785167L;

	public BaseBusinessCheckedException() {
		super();
	}

	public BaseBusinessCheckedException(String errorCode, String errorMessage,
			String[] msgParams) {
		super(errorCode, errorMessage, msgParams);
	}

	public BaseBusinessCheckedException(String errorCode, String errorMessage,
			Throwable cause, String[] msgParams) {
		super(errorCode, errorMessage, cause, msgParams);
	}

	public BaseBusinessCheckedException(String message, Throwable cause) {
		super(message, cause);
	}

	public BaseBusinessCheckedException(String message) {
		super(message);
	}

	public BaseBusinessCheckedException(Throwable cause) {
		super(cause);
	}
}
