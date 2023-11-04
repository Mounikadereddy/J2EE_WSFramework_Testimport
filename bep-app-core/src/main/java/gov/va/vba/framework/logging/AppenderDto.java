/*
 * AppenderDto.java
 *
 * Copyright 2008 U.S. Dept Of Veterans Affairs.
 * U.S. Government PROPRIETARY/CONFIDENTIAL. Use is subject to security terms.
 */

package gov.va.vba.framework.logging;

/**
 * @author vbahinmutumr
 *
 */
public class AppenderDto {

	/**
	 * 
	 */
	public AppenderDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	private String name;
	private String layout;
	
	public String getLayout() {
		return layout;
	}
	public void setLayout(String layout) {
		this.layout = layout;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	

}
