package gov.va.vba.framework.exceptions;

public class BaseBusinessRuntimeException extends BaseRuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6606126689118880090L;

	private String[] msgParams = null;
	private String errorCode = null;

	public BaseBusinessRuntimeException() {
		super();
	}

	public BaseBusinessRuntimeException(String errorCode, String errorMessage,
			String[] msgParams) {
		super(errorMessage);
		this.errorCode = errorCode;
		this.msgParams = msgParams;
	}

	public BaseBusinessRuntimeException(String errorCode, String errorMessage,
			Throwable cause, String[] msgParams) {
		super(errorMessage, cause);
		this.errorCode = errorCode;
		this.msgParams = msgParams;
	}

	public BaseBusinessRuntimeException(String errorMessage, Throwable cause) {
		super(errorMessage, cause);
	}

	public BaseBusinessRuntimeException(String errorMessage) {
		super(errorMessage);
	}

	public BaseBusinessRuntimeException(Throwable cause) {
		super(cause);
	}

	public String[] getMsgParams() {
		return msgParams;
	}

	public void setMsgParams(String[] msgParams) {
		this.msgParams = msgParams;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

}
