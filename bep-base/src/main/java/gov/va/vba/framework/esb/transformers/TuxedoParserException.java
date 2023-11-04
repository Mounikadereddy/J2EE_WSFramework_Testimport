/*
 * TuxedoParserException.java
 *
 * Copyright 2006 U.S. Dept Of Veterans Affairs.
 * U.S. Government PROPRIETARY/CONFIDENTIAL. Use is subject to security terms.
 */
package gov.va.vba.framework.esb.transformers;

import gov.va.vba.framework.exceptions.BaseException;


/**
 * Signifies a TuxedoService Parsing exception 
 *
 * @since	Jun 14, 2006
 * @version	
 * @author	Mario Rodrigues
 */
public class TuxedoParserException extends BaseException {

	private static final long serialVersionUID = 6610181477295131148L;

	/**
	 * @param message
	 * @param cause
	 *
	 * @since	Jun 14, 2006
	 * @version	
	 */
	public TuxedoParserException(String message, Throwable cause) {

		super(message, cause);
	}

	/**
	 * @param message
	 *
	 * @since	Jun 14, 2006
	 * @version	
	 */
	public TuxedoParserException(String message) {

		super(message);
	}

	/**
	 * @param cause
	 *
	 * @since	Jun 14, 2006
	 * @version	
	 */
	public TuxedoParserException(Throwable cause) {

		super(cause);
	}

	/**
	 * 
	 *
	 * @since	Jun 14, 2006
	 * @version	
	 */
	public TuxedoParserException() {

		super();
	}

}
