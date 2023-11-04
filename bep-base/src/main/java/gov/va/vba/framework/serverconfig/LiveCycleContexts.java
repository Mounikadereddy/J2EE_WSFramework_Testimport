package gov.va.vba.framework.serverconfig;

/*
 * LiveCycleContexts.java
 *
 * Copyright 2009 U.S. Dept Of Veterans Affairs.
 * U.S. Government PROPRIETARY/CONFIDENTIAL. Use is subject to security terms.
 */

/**
 *  An Enumeration for Adobe LiveCycle cluster attributes. These attributes are 
 *  bound to the WebLogic JNDI tree and can be referenced within application code. 
 *
 * @since	Mar 13, 2009
 * @version	
 * @author	Mario Rodrigues
 */
public enum LiveCycleContexts {
	
	BASE_FILESYSTEM("correspondence/liveCycle/fileSystem", false),
	SERVER_URL("correspondence/liveCycle/url", false),
	SERVER_USER_ID("correspondence/liveCycle/userId", true),
	SERVER_PASSWORD("correspondence/liveCycle/password", true),
	//TODO: Temp: For VRE QA project. Need to remove this from here (and enum class) ASAP
	MAIL_SERVER("mail/smtp/host", false);
	
	private String name;
	private boolean sensitivity;
	
	/**
	 * 
	 * @param 	name
	 * @param 	sensitivity - indicates whether this enum value will be echoed in logs
	 * @since	Mar 13, 2009
	 * @version
	 */
	private LiveCycleContexts(String name, boolean sensitivity){
		this.name = name;
		this.sensitivity = sensitivity;
	}
	
	/**
	 * 
	 * @return	
	 * @throws	
	 * @return	
	 * @since	Mar 13, 2009
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Returns a boolean value indicating whether this enum value is sensitive or not
	 * 
	 * @return	
	 * @throws	
	 * @return	
	 * @since	Mar 13, 2009
	 */
	public boolean isSensitive() {
		return this.sensitivity;
	}

}
