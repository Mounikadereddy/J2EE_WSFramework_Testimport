/*
 * AuditLogVO.java
 *
 * Copyright 2006 U.S. Dept Of Veterans Affairs.
 * U.S. Government PROPRIETARY/CONFIDENTIAL. Use is subject to security terms.
 */
package gov.va.vba.framework.domain.vo;



/**
 * 
 *
 * @since	Mar 28, 2006
 * @version	
 * @author	Mario Rodrigues
 */
public class GenericVO extends ValueObject {
	

	private static final long serialVersionUID = 5141101965538876545L;
	private String url;
	private String message; 
	
	/**
	 * 
	 *
	 * @since	Mar 28, 2006
	 * @version	
	 */
	public GenericVO() {
		super();
	}

	/**
	 * 
	 * 
	 * @param userId
	 * @param stationId
	 * @param applicationName
	 * @param clientIPAddress
	 *
	 * @since	Mar 28, 2006
	 * @version	
	 */
	public GenericVO(String userId, String stationId, String applicationName,
			String clientIPAddress) {
		
		super(userId, stationId, applicationName, clientIPAddress);		
	}
	
	/**
	 * Implementation of java.lang.Object contract. 
	 * 
	 * @param
	 * @return
	 * @exception
	 * @since
	 * @see	gov.va.vba.framework.domain.vo#toString() 
	 */
	/* public String toString() {
		return super.toString();
	}*/
	
	/**
	 * Implementation of java.lang.Object contract. 
	 * 
	 * @param
	 * @return
	 * @exception
	 * @since
	 * @see	gov.va.vba.framework.domain.vo#hashCode()
	 */
	public int hashCode() {
		return super.hashCode()+url.hashCode()+message.hashCode();
	}

	
	/**
	 * Implementation of java.lang.Object contract. 
	 * 
	 * @param java.lang.Object
	 * @return
	 * @throws
	 * @see gov.va.vba.framework.domain.vo#equals(Object)
	 */
	public boolean equals(Object o) {
		
		GenericVO vo = null;
		
		if (o == this)
			return true;
		if (o == null)
			return false;
		if (this.getClass() != o.getClass())
			return false;
		vo = (GenericVO)o;
		return (super.equals(this) &&
				url != null && url.equals(vo.url)&&
				message != null && message.equals(vo.message));
	}
	
	/**
	 * @return Returns the message.
	 */
	public String getMessage() {
	
		return message;
	}

	
	/**
	 * @param message The message to set.
	 */
	public void setMessage(String message) {
	
		this.message = message;
	}

	
	/**
	 * @return Returns the url.
	 */
	public String getUrl() {
	
		return url;
	}

	
	/**
	 * @param url The url to set.
	 */
	public void setUrl(String url) {
	
		this.url = url;
	}
	
}
