/*
 * CommonSecurityException.java 
 *
 * Copyright 2008 U.S. Dept Of Veterans Affairs.
 * U.S. Government PROPRIETARY/CONFIDENTIAL. Use is subject to security terms.
 */
package gov.va.vba.framework.domain.services;

import gov.va.vba.framework.exceptions.*;


/**
 * Exception thrown by all persistence services and interfaces. 
 *
 * @since	Feb 28, 2006
 * @version	
 * @author	Mario Rodrigues
 */
public class DataAccessException extends BaseException {

	private static final long serialVersionUID = -988378784894669L;

	/**
	 * @param message
	 * @param cause
	 *
	 * @since	Feb 28, 2006
	 * @version	
	 */
	public DataAccessException(String message, Throwable cause) {

		super(message, cause);
	}

	/**
	 * @param message
	 *
	 * @since	Feb 28, 2006
	 * @version	
	 */
	public DataAccessException(String message) {

		super(message);
	}

	/**
	 * @param cause
	 *
	 * @since	Feb 28, 2006
	 * @version	
	 */
	public DataAccessException(Throwable cause) {

		super(cause);
	}

	/**
	 * 
	 *
	 * @since	Feb 28, 2006
	 * @version	
	 */
	public DataAccessException() {

		super();
	}

}
