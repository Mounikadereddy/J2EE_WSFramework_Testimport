/*
 * Document.java
 *
 * Copyright 2009 U.S. Dept Of Veterans Affairs.
 * U.S. Government PROPRIETARY/CONFIDENTIAL. Use is subject to security terms.
 */

package gov.va.vba.framework.esb.documentmanagement;

import gov.va.vba.framework.logging.Logger;

import java.io.Serializable;
import java.lang.reflect.Method;


/**
 * Main document class that describes standard document functionality that all
 * VA documents exhibit
 *
 * @since	Apr 21, 2009
 * @version	
 * @author	Mario Rodrigues
 */
public abstract class Document implements Serializable {
	private static Logger logger=Logger.getLogger(Document.class.getName());
	
	
	/**
	 * Implementation of java.lang.Object contract. Sub classes can simply call
	 * super.toString(this) without any further logic.
	 * <p>
	 * Formats the values of the getters of this object for a readable display.
	 * Exceptions are not rethrown in this class since toString() is not a
	 * critical method and is mainly used in debugging/logging
	 * </p>
	 * 
	 * @param	vo - the concrete class whose attributes will be formatted for output
	 * @return	String - a formatted string displaying the value of all public
	 *         	getters for this object
	 */
	public String toString() {

		Method[] methods = this.getClass().getMethods();
		StringBuffer result = null; 

		try {
			result = new StringBuffer(" Data for ").append(
					this.getClass().getName()).append("(\n");
			for (int i = 0; i < methods.length; i++) {
				String methodName = methods[i].getName();
				if (methodName.startsWith("get") && !methodName.startsWith("getClass")) {
					result.append(methods[i].getName() + ":");
					result.append(methods[i].invoke(this, new Object[]{})+ ";\n");
				}
			}
			result.append(")");
		}
		catch (Exception e) {
			logger.error("",e);
		}
		return result.toString();
	}

}
