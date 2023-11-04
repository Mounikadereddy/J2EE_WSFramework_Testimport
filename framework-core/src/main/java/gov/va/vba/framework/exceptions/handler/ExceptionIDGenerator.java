package gov.va.vba.framework.exceptions.handler;

import gov.va.vba.framework.server.ServerUtil;



/**
 * Generator class for creating unique exception id based on the managed server
 * name in the cluster and the time exception was raised.
 * 
 * @author psimyeru
 * 
 */
public class ExceptionIDGenerator {

	/**
	 * Generate unique Id based on the weblogic instance name and current time.
	 * 
	 * @return unique Id.
	 */
	public static String getUniqueID() {
		return ServerUtil.getInstanceName() + "." + System.currentTimeMillis();
	}
}
