/*
 * CommonSecurityException.java
 *
 * Copyright 2008 U.S. Dept Of Veterans Affairs.
 * U.S. Government PROPRIETARY/CONFIDENTIAL. Use is subject to security terms.
 */
package gov.va.vba.framework.services;

import gov.va.vba.framework.exceptions.BaseException;


/**
 * TODO: implement constructors
 *
 * @since	Feb 28, 2006
 * @version	
 * @author	Mario Rodrigues
 */
public class CommonSecurityException extends BaseException {

	private static final long serialVersionUID = -98837878489466949L;

	/**
	 * @param message
	 * @param cause
	 *
	 * @since	Feb 28, 2006
	 * @version	
	 */
	public CommonSecurityException(String message, Throwable cause) {

		super(message, cause);
	}

	/**
	 * @param message
	 *
	 * @since	Feb 28, 2006
	 * @version	
	 */
	public CommonSecurityException(String message) {

		super(message);
	}

	/**
	 * @param cause
	 *
	 * @since	Feb 28, 2006
	 * @version	
	 */
	public CommonSecurityException(Throwable cause) {

		super(cause);
	}

	/**
	 * 
	 *
	 * @since	Feb 28, 2006
	 * @version	
	 */
	public CommonSecurityException() {

		super();
	}

}
