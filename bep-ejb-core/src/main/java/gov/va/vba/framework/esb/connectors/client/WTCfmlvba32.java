
// /* Date: 06/08/12 */
// /* VBA TP generic FML32 buffer for Tuxedo Gateway Services */
// /* advertised to non-css authenticated systems */
// /* 8. Application name    - upto 15 characters */
// /* 9. Service name - service to be called after validation of */
// /*    application name. this service must appear in the CSS */
// /*    tables as being authorized for this application */
// /*10. External user id - user id for this user in the external */
// /*    system for audit purposes */
// /*11. External key representing this user [aka. factless key] */
package gov.va.vba.framework.esb.connectors.client;

import java.io.*;
import java.util.*;
import weblogic.wtc.jatmi.*;

public final class WTCfmlvba32 implements FldTbl {
	
	Hashtable nametofieldHashTable;
	Hashtable fieldtonameHashTable;
	/** number: 59275  type: string */
	public final static int APPNAME32 = 167831435;
	/** number: 59280  type: string */
	public final static int SVCNAME32 = 167831440;
	/** number: 59285  type: string */
	public final static int EXTUID32 = 167831445;
	/** number: 59290  type: string */
	public final static int EXTKEY32 = 167831450;

	public String Fldid_to_name(int fldid)
	{
		if ( fieldtonameHashTable == null ) {
			fieldtonameHashTable = new Hashtable();
			fieldtonameHashTable.put(new Integer(APPNAME32), "APPNAME32");
			fieldtonameHashTable.put(new Integer(SVCNAME32), "SVCNAME32");
			fieldtonameHashTable.put(new Integer(EXTUID32), "EXTUID32");
			fieldtonameHashTable.put(new Integer(EXTKEY32), "EXTKEY32");
		}

		return ((String)fieldtonameHashTable.get(new Integer(fldid)));
	}

	public int name_to_Fldid(String name)
	{
		if ( nametofieldHashTable == null ) {
			nametofieldHashTable = new Hashtable();
			nametofieldHashTable.put("APPNAME32", new Integer(APPNAME32));
			nametofieldHashTable.put("SVCNAME32", new Integer(SVCNAME32));
			nametofieldHashTable.put("EXTUID32", new Integer(EXTUID32));
			nametofieldHashTable.put("EXTKEY32", new Integer(EXTKEY32));
		}

		Integer fld = (Integer)nametofieldHashTable.get(name);
		if (fld == null)
			return (-1);
		else
			return (fld.intValue());
	}

	public String[] getFldNames()
	{
		String retval[] = new String[4];
		retval[0] = new String("APPNAME32");
		retval[1] = new String("SVCNAME32");
		retval[2] = new String("EXTUID32");
		retval[3] = new String("EXTKEY32");
		return retval;
	}
}
