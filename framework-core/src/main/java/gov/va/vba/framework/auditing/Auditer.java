package gov.va.vba.framework.auditing;

import gov.va.vba.framework.common.AuditContext;
import gov.va.vba.framework.logging.Logger;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.naming.InitialContext;

import org.apache.commons.lang.StringUtils;

public class Auditer {
	private final static String AUDIT_FORMAT_VERSION = "V3";
	private Logger auditingLogger;
	public static String weblogicInstanceID = " ";
	private static Logger logger = Logger.getLogger(Auditer.class.getName());
	static {
		/*
		 * try { ResourceBundle resourceBundle =
		 * ResourceBundle.getBundle("BEP"); weblogicInstanceID = (String)
		 * resourceBundle .getObject("weblogic.instance.id"); } catch
		 * (MissingResourceException rnfe) {
		 * logger.error("error initializing weblogic instance ID", rnfe); }
		 */
		try {
			InitialContext ctx = new InitialContext();
			MBeanServer server = (MBeanServer) ctx
					.lookup("java:comp/env/jmx/runtime");
			ObjectName serverRT = (ObjectName) server
					.getAttribute(
							new ObjectName(
									"com.bea:Name=RuntimeService,Type=weblogic.management.mbeanservers.runtime.RuntimeServiceMBean"),
							"ServerRuntime");
			weblogicInstanceID = (String) server.getAttribute(serverRT, "Name");
		} catch (Exception e) {
			logger.error("Could not get server instance name", e);
		}

	}

	public static String getWeblogicInstanceID() {
		return weblogicInstanceID;
	}

	public void audit(AuditContext auditContext, String ejb, String method, long executionTime) {

		AuditContext.validate(auditContext);
		String namespace = new StringBuffer("audit.").append(
				ejb
				).toString();
		auditingLogger = Logger.getLogger(namespace);
		if (StringUtils.isEmpty(auditContext.getAuditID()))
			auditContext.setAuditID(AuditIDGenerator.generateAuditID());
		StringBuilder message = new StringBuilder(AUDIT_FORMAT_VERSION);
		message.append(':');
		message.append(ejb);
		message.append('.');
		message.append(method);
		message.append(':');
		message.append(auditContext.getAuditID());
		message.append(':');
		if (auditContext.getUserId() != null)
			message.append(auditContext.getUserId());
		message.append(':');
		if (auditContext.getApplicationName() != null)
			message.append(auditContext.getApplicationName());
		message.append(':');
		if (auditContext.getClientUniqueID() != null)
			message.append(auditContext.getClientUniqueID());
		message.append(':');
		if (auditContext.getClientUniqueKey() != null)
			message.append(auditContext.getClientUniqueKey());
		message.append(':');
		if (auditContext.getClientIPAddress() != null)
			message.append(auditContext.getClientIPAddress());
		message.append(':');
		if (auditContext.getStationID() != null)
			message.append(auditContext.getStationID());
		message.append(':');
		if (weblogicInstanceID != null)
			message.append(weblogicInstanceID);
		message.append(':');
		if (auditContext.getTuxedoServiceName() != null)
			message.append(auditContext.getTuxedoServiceName()).toString();
		message.append(':');
		message.append(executionTime);
		auditingLogger.info(message);

	}

}
