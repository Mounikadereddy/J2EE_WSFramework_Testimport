/*
 * CommonSecurityException.java
 *
 * Copyright 2006 U.S. Dept Of Veterans Affairs.
 * U.S. Government PROPRIETARY/CONFIDENTIAL. Use is subject to security terms.
 */
package gov.va.vba.framework.web.security;

import gov.va.vba.framework.exceptions.*;


/**
 * TODO: implement constructors
 *
 * @since	Feb 28, 2006
 * @version	
 * @author	Mario Rodrigues
 */
public class PasswordStrengthException extends BaseException {

	private static final long serialVersionUID = -988378784894669492L;

	/**
	 * @param message
	 * @param cause
	 *
	 * @since	Feb 28, 2006
	 * @version	
	 */
	public PasswordStrengthException(String message, Throwable cause) {

		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 *
	 * @since	Feb 28, 2006
	 * @version	
	 */
	public PasswordStrengthException(String message) {

		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 *
	 * @since	Feb 28, 2006
	 * @version	
	 */
	public PasswordStrengthException(Throwable cause) {

		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 *
	 * @since	Feb 28, 2006
	 * @version	
	 */
	public PasswordStrengthException() {

		super();
		// TODO Auto-generated constructor stub
	}

}
