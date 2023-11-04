/*
 * TuxedoConnectorException.java
 *
 * Copyright 2007 U.S. Dept Of Veterans Affairs.
 * U.S. Government PROPRIETARY/CONFIDENTIAL. Use is subject to security terms.
 */

package gov.va.vba.framework.esb.connectors.client;

import gov.va.vba.framework.exceptions.BaseException;


/**
 * Main Exception that's thrown by the VBA Tuxedo subsystem
 *
 * @since	Jan 19, 2007
 * @version	
 * @author	Mario Rodrigues
 */
public class TuxedoConnectorException extends BaseException {

	private static final long serialVersionUID = 3771807866538645334L;
	private String tuxedoErrorMessage="";
	private int tuxedoErrorCode=-1;

	/**
	 * @param message
	 * @param cause
	 *
	 * @since	Jun 14, 2006
	 * @version	
	 */
	public TuxedoConnectorException(String message, Throwable cause) {

		super(message, cause);
	}

	/**
	 * @param message
	 *
	 * @since	Jun 14, 2006
	 * @version	
	 */
	public TuxedoConnectorException(String message) {

		super(message);
	}


	
	public TuxedoConnectorException(Exception cause, String tuxedoErrorMessage, int tuxedoErrorCode) {
		this(cause);
		this.tuxedoErrorMessage=tuxedoErrorMessage;
		this.tuxedoErrorCode=tuxedoErrorCode;
	}

	/**
	 * @param cause
	 *
	 * @since	Jun 14, 2006
	 * @version	
	 */
	public TuxedoConnectorException(Throwable cause) {

		super(cause);
	}

	/**
	 * 
	 *
	 * @since	Jun 14, 2006
	 * @version	
	 */
	public TuxedoConnectorException() {

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
