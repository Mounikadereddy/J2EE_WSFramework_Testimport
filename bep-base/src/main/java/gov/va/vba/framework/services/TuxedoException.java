/*
 * CommonSecurityException.java
 *
 * Copyright 2008 U.S. Dept Of Veterans Affairs.
 * U.S. Government PROPRIETARY/CONFIDENTIAL. Use is subject to security terms.
 */
package gov.va.vba.framework.services;

import gov.va.vba.framework.common.TuxedoErrorUtils;
import gov.va.vba.framework.exceptions.BaseException;


/**
 * TODO: implement constructors
 *
 * @since	Feb 28, 2006
 * @version	
 * @author	Mario Rodrigues
 */
public class TuxedoException extends BaseException {

	private static final long serialVersionUID = -98837878489466949L;
	private String tuxedoErrorMessage="";
	private int tuxedoErrorCode=-1;

	/**
	 * @param message
	 * @param cause
	 *
	 * @since	Feb 28, 2006
	 * @version	
	 */
	public TuxedoException(String message, Throwable cause) {
		super(message, cause);
	}

	public TuxedoException(Exception cause, String tuxedoErrorMessage, int tuxedoErrorCode) {
		this(TuxedoErrorUtils.combineStringAndCode(tuxedoErrorMessage, tuxedoErrorCode), cause);
		this.tuxedoErrorMessage=tuxedoErrorMessage;
		this.tuxedoErrorCode=tuxedoErrorCode;
	}

	/**
	 * @param message
	 *
	 * @since	Feb 28, 2006
	 * @version	
	 */
	public TuxedoException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 *
	 * @since	Feb 28, 2006
	 * @version	
	 */
	public TuxedoException(Throwable cause) {

		super(cause);
	}

	/**
	 * 
	 *
	 * @since	Feb 28, 2006
	 * @version	
	 */
	public TuxedoException() {

		super();
	}

	public String getTuxedoErrorMessage() {
		return tuxedoErrorMessage;
	}

	public void setTuxedoErrorMessage(String tuxedoErrorMessage) {
		this.tuxedoErrorMessage = tuxedoErrorMessage;
	}

	public int getTuxedoErrorCode() {
		return tuxedoErrorCode;
	}

	public void setTuxedoErrorCode(int tuxedoErrorCode) {
		this.tuxedoErrorCode = tuxedoErrorCode;
	}


}
