/*
 * BaseException.java
 *
 * Copyright 2006 U.S. Dept Of Veterans Affairs.
 * U.S. Government PROPRIETARY/CONFIDENTIAL. Use is subject to security terms.
 */

package gov.va.vba.framework.exceptions;


/**
 * 
 * BEP00000010 Exception Handling Enhancements
 * 
 * Adding the file back for WEAMS.. Not recommended to use
 * 
 * Use BaseCheckedException instead for all checked exceptions and BaseRuntimeException for runtime exceptions.
 *
 * @since	Feb 28, 2006
 * @version	
 * @author	Mario Rodrigues
 */
public class BaseException extends Exception {

	private static final long serialVersionUID = 8068564599369729903L;
	private String[] msgParams = null;
	private String errorCode = null;
	private String exceptionId = null;

	public BaseException() {
		super();
	}

	public BaseException(String message) {
		super(message);
	}

	public BaseException(Throwable cause) {
		super(cause);
	}

	public BaseException(String message, Throwable cause) {
		super(message, cause);
	}

	public BaseException(String errorCode, String errorMessage,
			String[] msgParams) {
		super(errorMessage);
		this.errorCode = errorCode;
		this.msgParams = msgParams;
	}

	public BaseException(String errorCode, String errorMessage,
			Throwable cause, String[] msgParams) {
		super(errorMessage, cause);
		this.errorCode = errorCode;
		this.msgParams = msgParams;
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

	public void setExceptionId(String exceptionId) {
		this.exceptionId = exceptionId;
	}

	public String getExceptionId() {
		return exceptionId;
	}

}
