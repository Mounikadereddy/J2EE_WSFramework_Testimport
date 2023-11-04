/*
 * Service.java
 *
 * Copyright 2008 U.S. Dept Of Veterans Affairs.
 * U.S. Government PROPRIETARY/CONFIDENTIAL. Use is subject to security terms.
 */

package gov.va.vba.framework.services;

import gov.va.vba.framework.exceptions.BaseException;


/**
 * Root interface for all VBA services
 *
 * @since	Aug 19, 2008
 * @version	
 * @author	Mario Rodrigues
 */
public interface Service<T> {
	
	void log(T o) throws BaseException;
}
