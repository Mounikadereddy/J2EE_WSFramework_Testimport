/*
 * CommonSecurityException.java
 *
 * Copyright 2006 U.S. Dept Of Veterans Affairs.
 * U.S. Government PROPRIETARY/CONFIDENTIAL. Use is subject to security terms.
 */
package gov.va.vba.framework.web.security;

import gov.va.vba.framework.exceptions.BaseException;


/**
 * TODO: implement constructors
 *
 * @since	Feb 28, 2006
 * @version	
 * @author	Mario Rodrigues
 */
public class SecurityException extends BaseException {

	private static final long serialVersionUID = -988378784894669492L;

	/**
	 * @param message
	 * @param cause
	 *
	 * @since	Feb 28, 2006
	 * @version	
	 */
	public SecurityException(String message, Throwable cause) {

		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 *
	 * @since	Feb 28, 2006
	 * @version	
	 */
	public SecurityException(String message) {

		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 *
	 * @since	Feb 28, 2006
	 * @version	
	 */
	public SecurityException(Throwable cause) {

		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 *
	 * @since	Feb 28, 2006
	 * @version	
	 */
	public SecurityException() {

		super();
		// TODO Auto-generated constructor stub
	}

}
