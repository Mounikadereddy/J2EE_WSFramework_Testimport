package gov.va.vba.framework.esb.transformers;
/*
 * TuxedoSecurityProfileParserTest.java
 *
 * Copyright 2006 U.S. Dept Of Veterans Affairs.
 * U.S. Government PROPRIETARY/CONFIDENTIAL. Use is subject to security terms.
 */

/**
 * A parser that parses data from the incoming TuxedoService reply buffer.
 *
 * @since	May 8, 2006
 * @version	
 * @author	Mario Rodrigues
 */
public class TuxedoSecurityProfileParser {
	
	private final static int MAX_FIELDS_TO_PARSE = 23;
	private TuxedoSecurityProfile _profile;

	/**
	 * 
	 *
	 * @since	May 8, 2006
	 * @version	
	 */
	public TuxedoSecurityProfileParser() {

	}

	/**
	 * Parses a pipe delimitied string
	 * 
	 * @param	String - TuxedoService reply.
	 * @throws	
	 * @return	
	 * @since	May 8, 2006
	 */
	public void parse(String tuxedoReply) throws TuxedoParserException {
			
		String regexp = "[|]";
		String[] fields = null;
		
		fields = tuxedoReply.split(regexp, -1);
		_profile = new TuxedoSecurityProfile();
		for (int i = 0; i < fields.length; i++) 
			copyField(i, fields);
		if (_profile.getRetCode() == '0'||_profile.getRetCode()=='2')
			parseRecurringFields(fields);
		//System.out.println("\nTuxProfile:\n"+_profile.toString());
	}	
	
	public TuxedoSecurityProfile getProfile() {
		return this._profile;
	}
	
	
	/**
	 * Copies field values from the buffered array to the TuxedoSecurityProfile 
	 * object
	 * 
	 * @param	
	 * @param	
	 * @throws	
	 * @return	
	 * @since	Apr 24, 2006
	 */
	private void copyField(int i, String[] fields) {	
		
		//System.out.println("setValue: "+i+", "+fields[i]);
		try {
			switch(i) {
			case 0:
				_profile.setRetCode(fields[i].trim().charAt(0));
				break;
			case 1:
				_profile.setMessage(fields[i]);
				break;
			case 2:
				_profile.setStationName(fields[i]);
				break;
			case 3:
				_profile.setParticipantId(fields[i]);
				break;
			case 4:
				_profile.setBdnNum(fields[i]);
				break;
			case 5:
				_profile.setDiagInd(fields[i].trim().charAt(0));
				break;
			case 6:
				_profile.setSecLevel(fields[i]);
				break;
			case 7:
				_profile.setFirstName(fields[i]);
				break;
			case 8:
				_profile.setMiddleName(fields[i]);
				break;
			case 9:
				_profile.setLastName(fields[i]);
				break;
			case 10:
				_profile.setSuffix(fields[i]);
				break;
			case 11:
				_profile.setFileNum(fields[i]);
				break;
			case 12:
				_profile.setSsn(fields[i]);
				break;
			case 13:
				_profile.setJobTitle(fields[i]);
				break;
			case 14:
				_profile.setVaOrganization(fields[i]);
				break;
			case 15:
				_profile.setEmailAddress(fields[i]);
				break;
			case 16:
				_profile.setSecOfficeInd(fields[i].trim().charAt(0));
				break;
			case 17:
				_profile.setPhAreaCode(fields[i]);
				break;
			case 18:
				_profile.setPhNum(fields[i]);
				break;
			case 19:
				_profile.setPhExt(fields[i]);
				break;
			case 20:
				_profile.setPoaCount(fields[i]);
				break;
			case 21:
				//PoaCodes
				break;
			case 22:
				_profile.setApplRole(fields[i]);				
				break;
			case 23:
				_profile.setNumFunctions(fields[i]);
				break;
			default:
				if (i <= MAX_FIELDS_TO_PARSE)
					throw new IndexOutOfBoundsException("cannot parse field with index: "+i);				
			}
		}
		catch (RuntimeException e) {
			if (e instanceof IndexOutOfBoundsException)
				throw e;
			throw new RuntimeException("Data error encountered while parsing element with index: "+i, e);
		}
	}
	
	/**
	 * Parses 2 sets of fields:
	 * 1) a fixed length POA codes field and 
	 * 2) variable/pipe delimeted functions fields
	 * 
	 * @param	TuxedoSecurityProfileTest
	 * @param	String[]
	 * @return	
	 * @throws		
	 * @since	May 12, 2006
	 */
	private void parseRecurringFields(String[] fields) {
		
		int poaCodeOffset = 3;
		int offset = 0;
		int endIndex = 0;		
		int functionsStartIndex = 24; 
		int poaCodesIndex = 21;
			
		for (int i=1; i<=_profile.getPoaCount();i++) {							
			endIndex = offset+poaCodeOffset;
			_profile.addPoaCode(fields[poaCodesIndex].substring(offset, endIndex));			
			offset = endIndex;
		}
		
		int fieldOffset = 3;
		int limit = (_profile.getNumFunctions()*fieldOffset)+functionsStartIndex;		
		for (int i=functionsStartIndex; i<limit; i=i+fieldOffset) {
			int k = i-1;
			TuxedoSecurityProfile.Function function = _profile.new Function();				
			function.setName(fields[++k]);
			function.setDisableInd(fields[++k].charAt(0));
			function.setAssignedValue(fields[++k]);				
			_profile.addFunction(function);					
		}
	}	
}
