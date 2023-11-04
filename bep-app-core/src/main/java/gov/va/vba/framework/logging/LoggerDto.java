/*
 * LoggerDto.java
 *
 * Copyright 2008 U.S. Dept Of Veterans Affairs.
 * U.S. Government PROPRIETARY/CONFIDENTIAL. Use is subject to security terms.
 */

package gov.va.vba.framework.logging;

/**
 * @author vbahinmutumr
 *
 */
public class LoggerDto {

	/**
	 * 
	 */
	public LoggerDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	private String name;
	private String level;
	
	private String[] appenderNames;
	private String[] appenderFiles;
	
	public String[] getAppenderFiles() {
		return appenderFiles;
	}
	public void setAppenderFiles(String[] appenderFiles) {
		this.appenderFiles = appenderFiles;
	}
	public String[] getAppenderNames() {
		return appenderNames;
	}
	public void setAppenderNames(String[] appenderNames) {
		this.appenderNames = appenderNames;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	

}
