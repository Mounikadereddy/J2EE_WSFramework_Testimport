/*
 * ServiceVO.java
 *
 * Copyright 2007 U.S. Dept Of Veterans Affairs.
 * U.S. Government PROPRIETARY/CONFIDENTIAL. Use is subject to security terms.
 */
package gov.va.vba.framework.domain.vo;

import java.io.Serializable;

/**
 * Value object class that serves the purpose of main data carrier and utility
 * interface for calling TuxedoService services. Clients instantiate objects of this class
 * and pass it as an argument to the underlying EJB
 *
 * @since	Jan 12, 2007
 * @version	
 * @author	Mario Rodrigues
 */
public class ServiceVO extends ValueObject {

	private static final long serialVersionUID = -7030967621362134065L;
	private static final String SERVICE_PREFIX = "WebLogic-";
	private TuxedoService service;
	private String data;
	private String externalId;
	private String externalKey;
	
	
	/**
	 * 
	 *
	 * @since	Jan 12, 2007
	 * @version	
	 */
	public ServiceVO() {

		super();
	}
	
	/*wlappc error ("Unable to set the transaction attribute for method..."): Inner enums don't work as method args. Enum needs to be top-level
	public enum ParticipantType implements Serializable {
		VETERAN("V"), USER("U");
		private String participantType;
		
		ParticipantType(String participantType) {
			this.participantType = participantType;
		}
		
		public char getType() {
			return this.participantType.charAt(0);
		}
	}*/

	/**
	 * For backwards compatibility. Will be removed in subsequent version 
	 * 
	 * @param userId
	 * @param stationId
	 * @param clientIPAddress
	 * @param service. gov.va.vba.framework.domain.vo.ServiceVO$TuxedoService
	 *
	 * @since	Feb 2, 2007
	 * @since	Feb 9, 2010
	 * @version
	 * @deprecated  
	 */
	public ServiceVO(String userId, String stationId, String clientIPAddress, TuxedoService service) {

		super(userId, stationId, null, clientIPAddress);
		this.service = service;
		super.setApplicationName(constructAppName());
	}

	/**
	 * For internal users
	 *  
	 * @param userId
	 * @param stationId
	 * @param clientIPAddress
	 * @param service data 
	 * @param service. gov.va.vba.framework.domain.vo.ServiceVO$TuxedoService
	 *
	 * @since	Sept 19, 2008
	 * @version
	 */
	public ServiceVO(String userId, String stationId, String clientIPAddress, String applName, TuxedoService service) {

		super(userId, stationId, null, clientIPAddress);
		this.service = service;
		if (applName != null)
			super.setApplicationName(applName);
		else
			super.setApplicationName(constructAppName());
	}
	
	
	/**
	 * @return Returns the TuxedoService type. A toString() on the returned type can be used to get the concrete enum type of this service 
	 */
	public TuxedoService getService() {
	
		return service;
	}


	/**
	 * @return Returns the application domain together with the service name identifier
	 */
	public String getServiceName() {
	
		return constructAppName();
	}

	
	
	/**
	 * @return the externalId
	 */
	public String getExternalId() {
	
		return externalId;
	}

	
	/**
	 * @param externalId the externalId to set
	 */
	public void setExternalId(String externalId) {
	
		this.externalId = externalId;
	}

	
	/**
	 * @return the externalKey
	 */
	public String getExternalKey() {
	
		return externalKey;
	}

	
	/**
	 * @param externalKey the externalKey to set
	 */
	public void setExternalKey(String externalKey) {
	
		this.externalKey = externalKey;
	}

	/**
	 * @return Returns the data.
	 */
	public String getData() {
	
		return data;
	}

	
	/**
	 * @param data The data to set.
	 */
	public void setData(String data) {
	
		this.data = data;
	}

	/**
	 * @param service The service to set.
	 */
	public void setService(TuxedoService service) {
	
		this.service = service;
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
	} */
	
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
		return super.hashCode()+service.hashCode();
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
		
		ServiceVO vo = null;
		
		if (o == this)
			return true;
		if (o == null)
			return false;
		if (this.getClass() != o.getClass())
			return false;
		vo = (ServiceVO)o;
		return (super.equals(this) &&
				service != null && service.equals(vo.service));
	}

	/**
	 * Concatenates the Service name with a fixed prefix for use in the TuxedoService API
	 * 
	 * @return	
	 * @throws	
	 * @return	String - Application Name
	 * @since	Jan 20, 2007
	 */
	private String constructAppName() {

		StringBuilder strBldr = new StringBuilder(SERVICE_PREFIX);
		int len = 0;
		String svcStr = this.service.getClass().getSimpleName();
		
		strBldr.append(
				svcStr.substring(0, (len=svcStr.toLowerCase().indexOf("service"))== -1? svcStr.length():len));
		if (strBldr.length()>15)
			strBldr.setLength(15);

		return strBldr.toString();
	}

	/**
	 * Marker interface that all TuxedoService Service types must implement. 
	 * 
	 * This is the main interface that all service enum's must implement and 
	 * guarantees a common behavior that the TuxedoConnector expects.
	 *
	 * @see	gov.va.vba.framework.esb.connectors.client.TuxedoConnector
	 * @since	Jan 12, 2007
	 * @version	
	 */
	public interface TuxedoService extends Serializable {}
	
	/**
	 * An Enumeration that describes Security Services 
	 *
	 * @since	Jan 12, 2007
	 * @version	
	 */
	public enum SecurityService implements TuxedoService {
		WLSAUTHEN, CSSPROFILE
	}

	public enum CommonService implements TuxedoService {
		CESTROUTER, TPTODMIV, TPTOCICS 
		//CESTROUTER trx types: BUPD, CDEP
	}

	/**
	 * An Enumeration that describes SHARE Services 
	 *
	 * @since	Jan 12, 2007
	 * @version	
	 */
	public enum SHAREService implements TuxedoService {
		VAAUSIBM, VAHINRDP, VAHINBDN, SHRINQ1, SHRINQ2, SHRINQ3, SHRINQ4, SHRINQ5, SHRINQ6, SHRINQ7, SHRINQ8, SHRINQA, SHRINQF, SHRINQBC, SHRINQM
	}	

	/**
	 * An Enumeration that defines LGY Services and participants
	 *
	 * @since	Jan 12, 2007
	 * @version	
	 */
	public enum LGYService implements TuxedoService {
		WTCCTRLHINQ, WTCFLASH, WTCHINRDP, WTCHINBDN, WTCSNTVTY;
	}

	
	/**
	 * An Enumeration that describes Covers Services 
	 *
	 * @since	Jan 12, 2007
	 * @version	
	 */
	public enum CoversService implements Serializable, TuxedoService {
		CORECEIV
	}
	
	/**
	 * An Enumeration that describes VETSNET Services 
	 *
	 * @since	May 3, 2010
	 * @version	
	 */
	public enum VETSNETService implements Serializable, TuxedoService {
		XVNSSNFILESRCH, XVNRLNSHPSRCH, XCPRDPTCPNTAWDS, SCPAWARD, RCPAWARD, XCDREADCNTNCFTN;
	}
	
}
