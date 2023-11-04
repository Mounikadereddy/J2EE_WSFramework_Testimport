/*
 * UserContext.java
 *
 * Copyright 2006 U.S. Dept Of Veterans Affairs.
 * U.S. Government PROPRIETARY/CONFIDENTIAL. Use is subject to security terms.
 */
//this package is traditionally use in frameworkweb.jar and was kept even though this class was moved to framework.jar 
package gov.va.vba.framework.web.security;

import gov.va.vba.framework.services.CommonSecurityException;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;


/**
 * An immutable class that holds login, security and other user-specific information.
 * Information is not scattered throughout the HttpSession since this is the only
 * object thats stored in this user's session. 
 * 
 * This class used to implement HttpSessionBindingListener 
 * so that it can be notified of session timeout and perform the proper cleanup,
 * but that has been removed to remove dependencies on servlet
 * 
 * @author Mario Rodrigues
 * @since  March 10, 2006
 */
public final class UserContext implements Serializable {

	private static final long serialVersionUID = 1L;
	private String siteMinderSessionID;
	private Locale locale;
	private Function[] appFunctions;
	private String userId;
	private String stationId;
	private String clientIPAddress;
	private String role;
	private String applicationName;
	private Long participantId;
	private String firstName;
	private String middleInitial;
	private String lastName;
	private List<String> poaCodes;
	private String vaOrganization;
	private String emailAddress;
	private char secOfficeInd;
	private String phAreaCode;
	private String phNum;
	private String phExt;
	private String stationName;	
	private String ssn;
	private Map<String, Object> data; //(for MW ViewData) - add methods: addData()
	private String bdnNumber;
	private short securityLevel;
	
	/**
	 * Constructor that initializes this user's profile. This is a temporary
	 * constructor that will be refactored once the CSS/TuxedoService/WebService API is
	 * completed
	 * 
	 * @param	String - user ID from whom this context is created
	 * @return	
	 * @throws	CommonSecurityException. Since the method does not do a whole lot other than
	 * 			invoke methods on the SecurityManager, it is appropriate to simply re-throw
	 * 			any exception it encounters from the SecurityManager
	 */
	public UserContext() {//TODO throws CommonSecurityException
		 
		if (appFunctions != null && appFunctions.length >0)Arrays.sort(appFunctions);
	}


	/**
	 * This is the only mutator method that's public. This is where application-specific
	 * arbitrary data is allowed in to a usercontext. 
	 * 
	 * @param key
	 * @param o	
	 * @throws	
	 * @return	
	 * @since	Apr 2, 2007
	 */
	public void addDataObject(String key, Object o) {
		
		if (this.data == null)
			this.data = new HashMap<String, Object>();
		this.data.put(key, o);
	}

	/**
	 * 
	 * @param	String - key that maps to the object that needs to be removed	
	 * @throws	
	 * @return	null or Object
	 * @since	Apr 2, 2007
	 */
	public Object removeDataObject(String key) {
		return (this.data != null ? this.data.remove(key) : null);
	}
	
	/**
	 * 
	 * @param key
	 * @return	
	 * @throws	
	 * @return	
	 * @since	Apr 2, 2007
	 */
	public Object getDataObject(String key) {
		return (this.data != null ? this.data.get(key) : null);
	}
	

	/**
	 * Determines if the current action that this user requested is legitimate
	 * within the context of their role profile
	 * 
	 * @param	int - the application action that this user wants to perform
	 * @return	true or false
	 * @throws	
	 */
	public boolean isValidAction(int appAction) {

		return appFunctions != null && Arrays.binarySearch(appFunctions, appAction) >= 0;
	}

	
	/**
	 * @return Returns the _appActions.
	 */
	public Function[] getAppFunctions() {
	
		return appFunctions;
	}

		
	/**
	 * @return Returns the _firstName.
	 */
	public String getFirstName() {
	
		return firstName;
	}
	
	/**
	 * @return Returns the _lastName.
	 */
	public String getLastName() {
	
		return lastName;
	}

	
	/**
	 * @return Returns the _locale.
	 */
	public Locale getLocale() {
	
		return locale;
	}
	
	/**
	 * @return Returns the _participantId.
	 */
	public Long getParticipantId() {
	
		return participantId;
	}
	
	/**
	 * @return Returns the _stationNum.
	 */
	public String getStationId() {
	
		return stationId;
	}
	
	/**
	 * @return Returns the _userId.
	 */
	public String getUserId() {
	
		return userId;
	}
	
	/**
	 * @return Returns the role.
	 */
	public String getRole() {
	
		return role;
	}

	/**
	 * @return Returns the applicationName.
	 */
	public String getApplicationName() {
	
		return applicationName;
	}
		
	/**
	 * @return Returns the middleInitial.
	 */
	public String getMiddleInitial() {
	
		return middleInitial;
	}

	/**
	 * @return Returns the clientIPAddress.
	 */
	public String getClientIPAddress() {
	
		return clientIPAddress;
	}
	
	/**
	 * 
	 * @return	
	 * @throws	
	 * @return	
	 * @since	May 12, 2006
	 */
	public List<String> getPoaCodes() {
		return poaCodes;
	}
	
	/**
	 * Adds a POA code to this user's context
	 *  
	 * @return	
	 * @throws	
	 * @return	
	 * @since	May 12, 2006
	 */
	public void addPoaCode(String code) {
		if (this.poaCodes == null)
			this.poaCodes = new ArrayList<String>();
		this.poaCodes.add(code);
	}
	
	/**
	 * 
	 * @return	
	 * @throws	
	 * @return	
	 * @since	May 12, 2006
	 */
	public String getVaOrganization() {
		return vaOrganization;
	}
	
	/**
	 * Returns the SiteMinders Session ID associated with this user's login session
	 * 
	 * @return	String	
	 * @throws	
	 * @return	
	 * @since	May 12, 2006
	 */
	public String getSiteMinderSessionID() {
		return siteMinderSessionID;
	}
	
	/**
	 * @param siteMinderSessionID. The siteMinderSessionID to set.
	 */
	public void setSiteMinderSessionID(String s) {
	
		this.siteMinderSessionID = s;
	}
	
	/**
	 * 
	 * @return	
	 * @throws	
	 * @return	
	 * @since	May 12, 2006
	 */
	public String getEmailAddress() {
		return emailAddress;
	}
	/**
	 * 
	 * @return	
	 * @throws	
	 * @return	
	 * @since	May 12, 2006
	 */
	public char getSecOfficeInd() {
		return secOfficeInd;
	}
	/**
	 * 
	 * @return	
	 * @throws	
	 * @return	
	 * @since	May 12, 2006
	 */
	public String getPhAreaCode() {
		return phAreaCode;
	}
	/**
	 * 
	 * @return	
	 * @throws	
	 * @return	
	 * @since	May 12, 2006
	 */
	public String getPhNum() {
		return phNum;
	}
	/**
	 * 
	 * @return	
	 * @throws	
	 * @return	
	 * @since	May 12, 2006
	 */
	public String getPhExt() {
		return phExt;
	}
	
	/**
	 * @return Returns the ssn.
	 */
	public String getSsn() {
	
		return ssn;
	}

	/**
	 * 
	 */
	public String getStationName() {
		return stationName;
	}

	public void setLocale(Locale locale) {
		this.locale=locale;
	}

	public void setUserId(String userId) {
		this.userId=userId;
	}
	
	public void setFunctions(Function[] functions) {
		this.appFunctions = functions;			
	}
	
	public void setPoaCodes(List<String> poaCodes) {
		this.poaCodes = poaCodes;			
	}

	
	public void setStationId(String stationId) {
		this.stationId=stationId;
	}

	public void setClientIPAddress(String clientIPAddress) {
		this.clientIPAddress=clientIPAddress;
	}
	
	public void setRole(String role) {
		this.role=role;
	}
		
	/**
	 * @param applicationName The applicationName to set.
	 */
	public void setApplicationName(String applicationName) {
	
		this.applicationName = applicationName;
	}

	
	/**
	 * @param emailAddress The emailAddress to set.
	 */
	public void setEmailAddress(String emailAddress) {
	
		this.emailAddress = emailAddress;
	}

	
	/**
	 * @param firstName The firstName to set.
	 */
	public void setFirstName(String firstName) {
	
		this.firstName = firstName;
	}

	
	/**
	 * @param lastName The lastName to set.
	 */
	public void setLastName(String lastName) {
	
		this.lastName = lastName;
	}

	
	/**
	 * @param middleInitial The middleInitial to set.
	 */
	public void setMiddleInitial(String middleInitial) {
	
		this.middleInitial = middleInitial;
	}

	
	/**
	 * @param participantId The participantId to set.
	 */
	public void setParticipantId(Long participantId) {
	
		this.participantId = participantId;
	}

	
	/**
	 * @param phAreaCode The phAreaCode to set.
	 */
	public void setPhAreaCode(String phAreaCode) {
	
		this.phAreaCode = phAreaCode;
	}

	
	/**
	 * @param phExt The phExt to set.
	 */
	public void setPhExt(String phExt) {
	
		this.phExt = phExt;
	}

	
	/**
	 * @param phNum The phNum to set.
	 */
	public void setPhNum(String phNum) {
	
		this.phNum = phNum;
	}

	
	/**
	 * @param secOfficeInd The secOfficeInd to set.
	 */
	public void setSecOfficeInd(char secOfficeInd) {
	
		this.secOfficeInd = secOfficeInd;
	}

	
	/**
	 * @param stationName The stationName to set.
	 */
	public void setStationName(String stationName) {
	
		this.stationName = stationName;
	}

	
	/**
	 * @param vaOrganization The vaOrganization to set.
	 */
	public void setVaOrganization(String vaOrganization) {
	
		this.vaOrganization = vaOrganization;
	}
	
	/**
	 * @param ssn The ssn to set.
	 */
	public void setSsn(String ssn) {
	
		this.ssn = ssn;
	}

	/**
	 * @return Returns the bdnNumber.
	 */
	public String getBdnNumber() {
	
		return bdnNumber;
	}

	
	/**
	 * @param bdnNumber The bdnNumber to set.
	 */
	public void setBdnNumber(String bdnNumber) {
	
		this.bdnNumber = bdnNumber;
	}

	/**
	 * @return the securityLevel
	 */
	public short getSecurityLevel() {
	
		return securityLevel;
	}

	
	/**
	 * @param securityLevel the securityLevel to set
	 */
	public void setSecurityLevel(short securityLevel) {
	
		this.securityLevel = securityLevel;
	}
	
	/**
	 * 
	 * @param
	 * @return	String
	 * @exception
	 * @since
	 */
	public String toString() {
		
		Field[] fields = this.getClass().getDeclaredFields();
		StringBuffer result = null; 

		result = new StringBuffer(" (");
		try {			
			for (int i = 0; i < fields.length; i++) 
				result.append(fields[i].getName() + ":").
					   append(fields[i].get(this)).
					   append(", ");
			result.append(")");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return result.toString();
	}	
	
	/**
	 * Nested class that represents this user's functions. Holds all CSUM functions
	 * for the logged in user. 
	 * 
	 * @since	May 10, 2006
	 * @version	
	 * @author	Mario Rodrigues
	 */
	public class Function implements Serializable {
		
		private static final long serialVersionUID = -2506547550873075693L;
		private String name;
		private char disabledInd;
		private String assignedValue;
		
		public Function() {
			
		}
		
		/**
		 * @return Returns the assignedValue.
		 */
		public String getName() {
			return this.name;
		}
		
		/**
		 * @param assignedValue The assignedValue to set.
		 */
		public void setName(String name) {
			this.name = name;
		}
		
		/**
		 * @return Returns the disableInd.
		 */
		public boolean isDisabled() {
			return Character.isWhitespace(this.disabledInd)||
						this.disabledInd == '\u0000'||
						this.disabledInd == 'Y'||
						this.disabledInd != 'N';
		}
		
		/**
		 * @param disableInd The disableInd to set.
		 */
		public void setDisableInd(char disableInd) {
			this.disabledInd = Character.toUpperCase(disableInd);
		}		

		/**
		 * @return Returns the assignedValue.
		 */
		public String getAssignedValue() {
		
			return this.assignedValue;
		}
		
		/**
		 * @param assignedValue The assignedValue to set.
		 */
		public void setAssignedValue(String assignedValue) {
		
			this.assignedValue = assignedValue;
		}

		/**
		 * 
		 */
		public String toString() {
			
			return("name: '"+this.name+"'. Length: "+(this.name != null? this.name.length():0)+"\n"+
				   "disableInd: '"+this.disabledInd+"'. Length: "+(this.disabledInd != '\u0000'? this.disabledInd:0)+"\n"+
				   "assignedValue: '"+this.assignedValue+"'. Length: "+(this.assignedValue != null? this.assignedValue.length():0)+"\n");
		}		
	}
}

