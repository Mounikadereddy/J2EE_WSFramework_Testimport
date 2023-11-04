package gov.va.vba.framework.esb.connectors.client;
// /* VBA TP generic FML buffer */
// /* 1. Route Station (used for data-dependent routing) - 3 chars */
// /* 2. Reply destination ind  - 1 character. */
// /* 3. Client module name  - up to 15 chars  */
// /* 4. Prior service name  - up to 15 chars  */
// /* 5. User name           - up to 15 chars  */
// /* 6. Computer name       - up to 15 chars  */
// /* 7. Appl data - variable length -  */
import java.io.*;
import java.util.*;
import weblogic.wtc.jatmi.*;


public final class InternalUserFMLFieldTable implements FldTbl {
	
	Hashtable nametofieldHashTable;
	Hashtable fieldtonameHashTable;

	/** number: 200 type: carray */
	public final static int FML_STATION_TO_ROUTE_TO = 49352;

	/** number: 205 type: char */
	public final static int FML_REPLY_DEST_IND = 16589;

	/** number: 210 type: carray */
	public final static int FML_CLIENT_MODULE_NAME = 49362;

	/** number: 215 type: carray */
	public final static int FML_PRIOR_SERVICE_NAME = 49367;

	/** number: 220 type: carray */
	public final static int FML_USERID = 49372;

	/** number: 225 type: carray */
	public final static int FML_COMPUTER_NAME = 49377;

	/** number: 250 type: carray */
	public final static int FML_APPL_DATA = 49402;
	
	/**
	 * 
	 * @param
	 * @return
	 * @exception
	 * @since
	 */
	public String Fldid_to_name(int fldid) {
		
		if (fieldtonameHashTable == null) {
			fieldtonameHashTable = new Hashtable<Integer, String>();
			fieldtonameHashTable.put(new Integer(FML_STATION_TO_ROUTE_TO),
					"FML_STATION_TO_ROUTE_TO");
			fieldtonameHashTable.put(new Integer(FML_REPLY_DEST_IND),
					"FML_REPLY_DEST_IND");
			fieldtonameHashTable.put(new Integer(FML_CLIENT_MODULE_NAME),
					"FML_CLIENT_MODULE_NAME");
			fieldtonameHashTable.put(new Integer(FML_PRIOR_SERVICE_NAME),
					"FML_PRIOR_SERVICE_NAME");
			fieldtonameHashTable.put(new Integer(FML_USERID), "FML_USERID");
			fieldtonameHashTable.put(new Integer(FML_COMPUTER_NAME),
					"FML_COMPUTER_NAME");
			fieldtonameHashTable.put(new Integer(FML_APPL_DATA),
					"FML_APPL_DATA");
		}
		return ((String) fieldtonameHashTable.get(new Integer(fldid)));
	}
	
	/**
	 * 
	 * @param
	 * @return
	 * @exception
	 * @since
	 */
	public int name_to_Fldid(String name) {
		if (nametofieldHashTable == null) {
			nametofieldHashTable = new Hashtable();
			nametofieldHashTable.put("FML_STATION_TO_ROUTE_TO", new Integer(
					FML_STATION_TO_ROUTE_TO));
			nametofieldHashTable.put("FML_REPLY_DEST_IND", new Integer(
					FML_REPLY_DEST_IND));
			nametofieldHashTable.put("FML_CLIENT_MODULE_NAME", new Integer(
					FML_CLIENT_MODULE_NAME));
			nametofieldHashTable.put("FML_PRIOR_SERVICE_NAME", new Integer(
					FML_PRIOR_SERVICE_NAME));
			nametofieldHashTable.put("FML_USERID", new Integer(FML_USERID));
			nametofieldHashTable.put("FML_COMPUTER_NAME", new Integer(
					FML_COMPUTER_NAME));
			nametofieldHashTable.put("FML_APPL_DATA",
					new Integer(FML_APPL_DATA));
		}

		Integer fld = (Integer) nametofieldHashTable.get(name);
		if (fld == null)
			return (-1);
		else
			return (fld.intValue());
	}
	
	/**
	 * 
	 * @param
	 * @return
	 * @exception
	 * @since
	 */
	public String[] getFldNames() {
		String retval[] = new String[7];
		retval[0] = new String("FML_STATION_TO_ROUTE_TO");
		retval[1] = new String("FML_REPLY_DEST_IND");
		retval[2] = new String("FML_CLIENT_MODULE_NAME");
		retval[3] = new String("FML_PRIOR_SERVICE_NAME");
		retval[4] = new String("FML_USERID");
		retval[5] = new String("FML_COMPUTER_NAME");
		retval[6] = new String("FML_APPL_DATA");
		return retval;
	}
}
