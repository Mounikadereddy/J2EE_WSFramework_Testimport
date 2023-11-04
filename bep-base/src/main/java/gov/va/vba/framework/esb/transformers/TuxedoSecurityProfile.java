package gov.va.vba.framework.esb.transformers;

import gov.va.vba.framework.logging.Logger;

import java.io.*;
import java.lang.reflect.Field;
import java.util.*;

/*
 * TuxedoSecurityProfileTest.java
 *
 * Copyright 2006 U.S. Dept Of Veterans Affairs.
 * U.S. Government PROPRIETARY/CONFIDENTIAL. Use is subject to security terms.
 */

/**
 * Data class that holds raw/parsed data from the TuxedoService FML reply buffer.
 *
 * @since	May 10, 2006
 * @version	
 * @author	Mario Rodrigues
 */
public class TuxedoSecurityProfile implements Serializable{
	
	private static Logger logger=Logger.getLogger(TuxedoSecurityProfile.class.getName());
	private static final long serialVersionUID = 3722398670739870837L;
	private char retCode;
	private String message;
	private String stationName;	
	private String participantId;
	private String username;
	private String bdnNum;
	private char diagInd;
	private String secLevel;	
	private String firstName;
	private String middleName;
	private String lastName;
	private String suffix;
	private String fileNum;
	private String ssn;	
	private String jobTitle;	
	private String vaOrganization;
	private String emailAddress;
	private char secOfficeInd;
	private String phAreaCode;
	private String phNum;
	private String phExt;
	private String poaCount;
	private List<String> poaCodes;
	private String applRole;
	private String numFunctions;
	private List<Function> functions;
		
	/**
	 *
	 * @since	Apr 21, 2006
	 * @version	
	 */
	public TuxedoSecurityProfile() {

	}
		
	
	/**
	 * @return Returns the firstName.
	 */
	public String getFirstName() {
	
		return firstName;
	}
	
	/**
	 * @param firstName The firstName to set.
	 */
	public void setFirstName(String firstName) {
	
		this.firstName = firstName;
	}
	
	/**
	 * @return Returns the lastName.
	 */
	public String getLastName() {
	
		return lastName;
	}
	
	/**
	 * @param lastName The lastName to set.
	 */
	public void setLastName(String lastName) {
	
		this.lastName = lastName;
	}
	
	/**
	 * @return Returns the middleName.
	 */
	public String getMiddleName() {
	
		return middleName;
	}
	
	/**
	 * @param middleName The middleName to set.
	 */
	public void setMiddleName(String middleName) {
	
		this.middleName = middleName;
	}
	
	/**
	 * @return Returns the participantId.
	 */
	public String getParticipantId() {
	
		return participantId;
	}
	
	/**
	 * @param participantId The participantId to set.
	 */
	public void setParticipantId(String participantId) {
	
		this.participantId = participantId;
	}

    public String getUsername() {
        return username;
    }

    /**
     * @param username The username to set.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
	 * @return Returns the retCode.
	 */
	public char getRetCode() {
	
		return retCode;
	}
	
	/**
	 * @param retCode The retCode to set.
	 */
	public void setRetCode(char retCode) {
	
		this.retCode = retCode;
	}
	
	/**
	 * @return Returns the ssn.
	 */
	public String getSsn() {
	
		return ssn;
	}
	
	/**
	 * @param ssn The ssn to set.
	 */
	public void setSsn(String ssn) {
	
		this.ssn = ssn;
	}
	
	/**
	 * @return Returns the applRole.
	 */
	public String getApplRole() {
	
		return applRole;
	}
	
	/**
	 * @param applRole The applRole to set.
	 */
	public void setApplRole(String applRole) {
	
		this.applRole = applRole;
	}
	
	/**
	 * @return Returns the jobTitle.
	 */
	public String getJobTitle() {
	
		return jobTitle;
	}

	/**
	 * @param jobTitle The jobTitle to set.
	 */
	public void setJobTitle(String jobTitle) {
	
		this.jobTitle = jobTitle;
	}
		
	/**
	 * In order to serve the double duty of preserving the sanity of the parsed data 
	 * as well as a convenience for computing the returned value to the parser, the 
	 * <code>numOperations</code> member is always stored as a <code>String</code> 
	 * and returned as an <code>int</code>.
	 *  
	 * @return Returns the numOperations.
	 */
	public int getNumFunctions() {
		
		char[] chars = this.numFunctions.trim().toCharArray();//could throw nullpointer. TODO
		boolean isDigits = false;
		
		for (int i = 0; i < chars.length; i++) {
			if (!Character.isDigit(chars[i])) {
				isDigits = false;
				break;//quick and dirty - not the best approach!
			}
			else
				isDigits = true;
		}
		return (isDigits ? Integer.parseInt(this.numFunctions):0);
	}
	
	/**
	 * @param numFunctions The numFunctions to set.
	 */
	public void setNumFunctions(String numFunctions) {
	
		this.numFunctions = numFunctions;
	}

	
	/**
	 * @return Returns the phAreaCode.
	 */
	public String getPhAreaCode() {
	
		return phAreaCode;
	}
	
	/**
	 * @param phAreaCode The phAreaCode to set.
	 */
	public void setPhAreaCode(String phAreaCode) {
	
		this.phAreaCode = phAreaCode;
	}
	
	/**
	 * @return Returns the phExt.
	 */
	public String getPhExt() {
	
		return phExt;
	}
	
	/**
	 * @param phExt The phExt to set.
	 */
	public void setPhExt(String phExt) {
	
		this.phExt = phExt;
	}

	/**
	 * @return Returns the phNum.
	 */
	public String getPhNum() {
	
		return phNum;
	}
	
	/**
	 * @param phNum The phNum to set.
	 */
	public void setPhNum(String phNum) {
	
		this.phNum = phNum;
	}
	
	/**
	 * @return Returns the poaCode2.
	 */
	public List<String> getPoaCodes() {
	
		return this.poaCodes;
	}
	
	/**
	 * @param poaCode2 The poaCode2 to set.
	 */
	public void setPoaCodes(List<String> poaCodes) {
	
		this.poaCodes = poaCodes;
	}

	/**
	 * @param poaCode2 The poaCode2 to set.
	 */
	public void addPoaCode(String poaCode) {
	
		if (this.poaCodes == null || this.poaCodes.size() == 0)
			this.poaCodes = new ArrayList<String>();
		this.poaCodes.add(poaCode);
	}

	/**
	 * @return Returns the poaCount.
	 */
	public int getPoaCount() {

		char[] chars = null;
		boolean isDigits = false;

		if (this.poaCount != null) {
			this.poaCount = this.poaCount.trim(); 
			chars = this.poaCount.toCharArray();
			for (int i = 0; i < chars.length; i++) {
				if (!Character.isDigit(chars[i])) {
					isDigits = false;
					break;// quick and dirty - not the best approach!
				}
				else
					isDigits = true;
			}
		}
		return (isDigits? Integer.parseInt(this.poaCount):0);
	}
	
	/**
	 * @param poaCount The poaCount to set.
	 */
	public void setPoaCount(String poaCount) {
	
		this.poaCount = poaCount;
	}
	
	/**
	 * @return Returns the secLevel.
	 */
	public String getSecLevel() {
	
		return secLevel;
	}

	/**
	 * @param secLevel The secLevel to set.
	 */
	public void setSecLevel(String secLevel) {
	
		this.secLevel = secLevel;
	}
	
	/**
	 * @return Returns the secOfficeInd.
	 */
	public char getSecOfficeInd() {
	
		return secOfficeInd;
	}

	/**
	 * @param secOfficeInd The secOfficeInd to set.
	 */
	public void setSecOfficeInd(char secOfficeInd) {
	
		this.secOfficeInd = secOfficeInd;
	}
	
	/**
	 * @return Returns the suffix.
	 */
	public String getSuffix() {
	
		return suffix;
	}


	/**
	 * @param suffix The suffix to set.
	 */
	public void setSuffix(String suffix) {
	
		this.suffix = suffix;
	}
	
	
	/**
	 * @return Returns the emailAddress.
	 */
	public String getEmailAddress() {
	
		return emailAddress;
	}
	
	/**
	 * @param emailAddress The emailAddress to set.
	 */
	public void setEmailAddress(String emailAddress) {
	
		this.emailAddress = emailAddress;
	}
	
	/**
	 * @return Returns the stationName.
	 */
	public String getStationName() {
	
		return stationName;
	}

	
	/**
	 * @param stationName The stationName to set.
	 */
	public void setStationName(String stationName) {
	
		this.stationName = stationName;
	}
		
	/**
	 * @return Returns the bdnNum.
	 */
	public String getBdnNum() {
	
		return bdnNum;
	}

	
	/**
	 * @param bdnNum The bdnNum to set.
	 */
	public void setBdnNum(String bdnNum) {
	
		this.bdnNum = bdnNum;
	}

	
	/**
	 * @return Returns the diagInd.
	 */
	public char getDiagInd() {
	
		return diagInd;
	}

	
	/**
	 * @param diagInd The diagInd to set.
	 */
	public void setDiagInd(char diagInd) {
	
		this.diagInd = diagInd;
	}

	
	/**
	 * @return Returns the fileNum.
	 */
	public String getFileNum() {
	
		return fileNum;
	}

	
	/**
	 * @param fileNum The fileNum to set.
	 */
	public void setFileNum(String fileNum) {
	
		this.fileNum = fileNum;
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
	 * @return Returns the vaOrganization.
	 */
	public String getVaOrganization() {
	
		return vaOrganization;
	}
	
	/**
	 * @param vaOrganization The vaOrganization to set.
	 */
	public void setVaOrganization(String vaOrganization) {
	
		this.vaOrganization = vaOrganization;
	}
	
	/**
	 * @return Returns the operations.
	 */
	public List<Function> getFunctions() {	
		return this.functions;
	}
	
	/**
	 * @param functions The functions to set.
	 */
	public void setFunctions(List<Function> functions) {
	
		this.functions = functions;
	}
	
	/**
	 * 
	 * @param	TuxedoSecurityProfileTest.Function	
	 * @throws	
	 * @return	
	 * @since	May 12, 2006
	 */
	public void addFunction(Function f) {
		
		if (this.functions == null)
			this.functions = new ArrayList<Function>(); 
		this.functions.add(f);
	}

	/**	
	 * Returns a String representation of this class's members
	 * 
	 */
	public String toString() {
		
		Field[] fields = this.getClass().getDeclaredFields();
		StringBuffer result = null; 

		result = new StringBuffer("(");
		try {			
			for (int i = 0; i < fields.length; i++) 
				result.append(fields[i].getName() + ":").
					   append(fields[i].get(this)).append("\n");
			result.append(")");
		}
		catch (Exception e) {
			logger.error("",e);
		}
		return result.toString();

	}
	
	/**
	 * Inner class that represents this class's functions. Holds all CSUM functions
	 * for the logged in user. 
	 * 
	 * @since	Apr 25, 2006
	 * @version	
	 * @author	Mario Rodrigues
	 */
	public class Function implements Serializable {

		private static final long serialVersionUID = -6628523596195531880L;
		private String name;
		private char disableInd;
		private String assignedValue;
		
		/**
		 * @return Returns the assignedValue.
		 */
		public String getName() {
			return name;
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
		public char getDisableInd() {
			return this.disableInd;
		}
		
		/**
		 * @param disableInd The disableInd to set.
		 */
		public void setDisableInd(char disableInd) {
			this.disableInd = disableInd;
		}		

		/**
		 * @return Returns the assignedValue.
		 */
		public String getAssignedValue() {
		
			return assignedValue;
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
				   "disableInd: '"+this.disableInd+"'. Length: "+(this.disableInd != '\u0000'? this.disableInd:0)+"\n"+
				   "assignedValue: '"+this.assignedValue+"'. Length: "+(this.assignedValue != null? this.assignedValue.length():0)+"\n");
		}
	}
}

