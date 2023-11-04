package gov.va.vba.framework.server;

import gov.va.vba.framework.logging.Logger;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.naming.InitialContext;

public class ServerUtil {
	private static Logger logger = Logger.getLogger(ServerUtil.class);
	protected static String instanceName = "Unknown";

	// Static initializer block that looks up Server Runtime using
	// <code>MBeanServer</code> and identifies WebLogic instance name.
	static {
		try {
			InitialContext ctx = new InitialContext();
			MBeanServer server = (MBeanServer) ctx
					.lookup("java:comp/env/jmx/runtime");
			ObjectName serverRT = (ObjectName) server
					.getAttribute(
							new ObjectName(
									"com.bea:Name=RuntimeService,Type=weblogic.management.mbeanservers.runtime.RuntimeServiceMBean"),
							"ServerRuntime");
			instanceName = (String) server.getAttribute(serverRT, "Name");
		} catch (Exception e) {
			logger.error("Error while reading weblogic instance information", e);
		}
	}

	/**
	 * Return weblogic instance name.
	 * 
	 * @return weblogic instance name.
	 */
	public static String getInstanceName() {
		return instanceName;
	}
}
