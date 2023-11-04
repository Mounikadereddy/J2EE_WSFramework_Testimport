package gov.va.vba.framework.esb.connectors.client;

// /* Date: 03/14/08 */
// /* VBA TP generic FML buffer for Tuxedo Gateway Services */
// /* advertised to non-css authenticated systems */
// /* 1. Route Station (used for data-dependent routing) - 3 chars */
// /* 2. Reply destination ind  - 1 character. */
// /* 3. Client module name  - up to 15 chars  */
// /* 4. Prior service name  - up to 15 chars  */
// /* 5. User name           - up to 15 chars  */
// /* 6. Computer name       - up to 15 chars  */
// /* 7. Appl data - variable length -  */
// /* 8. Application name    - upto 15 characters */
// /* 9. Service name - service to be called after validation of */
// /*    application name. this service must appear in the CSS */
// /*    tables as being authorized for this application */
// /*10. External user id - user id for this user in the external */
// /*    system for audit purposes */
// /*11. External key representing this user [aka. factless key] */
import java.io.*;
import java.util.*;
import weblogic.wtc.jatmi.*;

/**
 * This following steps were taken to generate this class
 * 
 *   1) Get the raw FML field table from the Tuxedo group
 *   2) Generate this file using the following command:
 *   java -cp C:/bea/weblogic10.0/server/lib/weblogic.jar;[directory of raw FML field 
 *   		table] weblogic.wtc.jatmi.mkfldclass [ExternalUserFMLFieldTable / FML field file name] 
 *
 * @since	May 1, 2008
 * @version	
 * @author	Mario Rodrigues
 */
public final class ExternalUserFMLFieldTable implements FldTbl {

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

	/** number: 275 type: string */
	public final static int APPNAME = 41235;

	/** number: 280 type: string */
	public final static int SVCNAME = 41240;

	/** number: 285 type: string */
	public final static int EXTUID = 41245;

	/** number: 290 type: string */
	public final static int EXTKEY = 41250;

	public String Fldid_to_name(int fldid) {

		if (fieldtonameHashTable == null) {
			fieldtonameHashTable = new Hashtable();
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
			fieldtonameHashTable.put(new Integer(APPNAME), "APPNAME");
			fieldtonameHashTable.put(new Integer(SVCNAME), "SVCNAME");
			fieldtonameHashTable.put(new Integer(EXTUID), "EXTUID");
			fieldtonameHashTable.put(new Integer(EXTKEY), "EXTKEY");
		}

		return ((String)fieldtonameHashTable.get(new Integer(fldid)));
	}

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
			nametofieldHashTable.put("APPNAME", new Integer(APPNAME));
			nametofieldHashTable.put("SVCNAME", new Integer(SVCNAME));
			nametofieldHashTable.put("EXTUID", new Integer(EXTUID));
			nametofieldHashTable.put("EXTKEY", new Integer(EXTKEY));
		}

		Integer fld = (Integer)nametofieldHashTable.get(name);
		if (fld == null)
			return (-1);
		else
			return (fld.intValue());
	}

	public String[] getFldNames() {

		String retval[] = new String[11];
		retval[0] = new String("FML_STATION_TO_ROUTE_TO");
		retval[1] = new String("FML_REPLY_DEST_IND");
		retval[2] = new String("FML_CLIENT_MODULE_NAME");
		retval[3] = new String("FML_PRIOR_SERVICE_NAME");
		retval[4] = new String("FML_USERID");
		retval[5] = new String("FML_COMPUTER_NAME");
		retval[6] = new String("FML_APPL_DATA");
		retval[7] = new String("APPNAME");
		retval[8] = new String("SVCNAME");
		retval[9] = new String("EXTUID");
		retval[10] = new String("EXTKEY");
		return retval;
	}
}
