/*
 * ValueObject.java
 *
 * Copyright 2006 U.S. Dept Of Veterans Affairs.
 * U.S. Government PROPRIETARY/CONFIDENTIAL. Use is subject to security terms.
 */
package gov.va.vba.framework.domain.vo;

import gov.va.vba.framework.logging.Logger;

import java.io.*;
import java.lang.reflect.*;

/**
 * <p>
 * Base domain object that conforms to the JavaBean spec. Provides useful
 * functionality for all subclasses such as mapping common attributes, and
 * performing comparisons and reflective string output.
 * </p>
 * This class forms the base data carrier class for all VBA value objects and
 * should be sub classed so that common functionality can be implemented across
 * the data layer
 * 
 * TODO: implement Comparable
 * 
 * @author Mario Rodrigues
 * @see
 * @since March 23, 2006
 */
public class ValueObject implements Serializable {

	private static final long serialVersionUID = -4265606672159161070L;
	private String stationId;
	private String userId;
	private String applicationName;
	private String clientIPAddress;
	private static Logger logger=Logger.getLogger(ValueObject.class.getName());
	
	
	/**
	 * Provided as a convenience. If using this constructor it is recommended that all
	 * setXX() methods be executed immediately after construction.
	 * 
	 * @param
	 * @return
	 * @exception
	 * @since
	 */
	protected ValueObject() {
		
	}
	
	
	/**
	 * Constructor that enforces proper initialization of this object. This is
	 * the <b>preferred</b> way to initialize this object.
	 * 
	 * @since	Mar 23, 2006
	 * @version
	 */
	protected ValueObject(String userId, String stationId, String applicationName, String clientIPAddress) {

		this.userId = userId;
		this.stationId = stationId;
		this.applicationName = applicationName;
		this.clientIPAddress = clientIPAddress;
	}

	
	/**
	 * @return Returns the applicationName.
	 */
	public String getApplicationName() {
	
		return applicationName;
	}


	
	/**
	 * @param applicationName The applicationName to set.
	 */
	public void setApplicationName(String applicationName) {
	
		this.applicationName = applicationName;
	}


	
	/**
	 * @return Returns the clientIPAddress.
	 */
	public String getClientIPAddress() {
	
		return clientIPAddress;
	}


	
	/**
	 * @param clientIPAddress The clientIPAddress to set.
	 */
	public void setClientIPAddress(String clientIPAddress) {
	
		this.clientIPAddress = clientIPAddress;
	}


	
	/**
	 * @return Returns the stationId.
	 */
	public String getStationId() {
	
		return stationId;
	}


	
	/**
	 * @param stationId The stationId to set.
	 */
	public void setStationId(String stationId) {
	
		this.stationId = stationId;
	}


	
	/**
	 * @return Returns the userId.
	 */
	public String getUserId() {
	
		return userId;
	}


	
	/**
	 * @param userId The userId to set.
	 */
	public void setUserId(String userId) {
	
		this.userId = userId;
	}


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
		
	/**
	 * Implementation of java.lang.Object contract. Subclasses should override this 
	 * method and <b>also</b> call super.equals(o)
	 * 
	 * @param java.lang.Object
	 * @return
	 * @throws
	 * @see java.lang.Object
	 */
	public boolean equals(Object o) {
		
		ValueObject vo = null;
		
		if (o == this)
			return true;
		if (o == null)
			return false;
		if (this.getClass() != o.getClass())
			return false;
		vo = (ValueObject)o;
		return (userId == vo.userId
				&& stationId == vo.stationId && 
				(applicationName == vo.applicationName || 
				(clientIPAddress != null && clientIPAddress.equals(vo.clientIPAddress))));
	}

	/**
	 * Implementation of java.lang.Object contract. It is strongly recommended that
	 * Subclasses override this method and <b>also</b> call super.hashCode(), otherwise
	 * a call to this method is okay temporarily as long as all 4 attributes are not
	 * null
	 * <p>
	 * This method follows a simple hashing algorithm that produces unequal hash codes
	 * for unequal objects. This guarantees that unequal instances of this class get 
	 * hashed in an optimal fashion while equal ones get hashed in the <b>same</>
	 * bucket thereby improving performance.
	 * </p>
	 * @param
	 * @return a guaranteed integer value thats same for all objects in a JVM
	 * @throws
	 * @see java.lang.Object#hashcode()
	 */
	public int hashCode() {
		int result = 3;//just an arbitrary number
		
		result = 11*result + stationId.hashCode();//TODO: look into this for alphanumeric
		result = 11*result + userId.hashCode();
		result = 11*result + applicationName.hashCode();
		result = 11*result + clientIPAddress.hashCode();
		return result;
	}
}
