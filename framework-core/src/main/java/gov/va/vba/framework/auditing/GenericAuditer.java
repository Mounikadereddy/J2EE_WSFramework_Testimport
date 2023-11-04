package gov.va.vba.framework.auditing;

import gov.va.vba.framework.common.AuditContext;
import gov.va.vba.framework.logging.Logger;
import gov.va.vba.framework.server.ServerUtil;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.apache.commons.lang.StringUtils;

public abstract class GenericAuditer {

	private final static String AUDIT_FORMAT_VERSION = "V5";
	private static Logger logger = Logger.getLogger(GenericAuditer.class);
	protected final static char AUDIT_DELIMITER=getDelimiter();
	protected final String UNKNOWN="unknown";

	private static char getDelimiter()
	{
		char delimiter='|';
		try
		{
			ResourceBundle resourceBundle = ResourceBundle.getBundle("BEP");
			delimiter = ((String) resourceBundle.getObject("audit.delimiter")).charAt(0);
		}
		catch (MissingResourceException mre)
		{
			logger.error(mre);
		}
		return delimiter;
	}
	
	protected Logger getAuditLogger(String nameSpace)
	{
		String namespace = new StringBuffer("audit.").append(
				nameSpace
				).toString();
		Logger auditingLogger = Logger.getLogger(namespace);
		return auditingLogger;
	}

	protected StringBuilder getAuditString(AuditContext auditContext, AuditEventCode auditEventCode, String success) {
		AuditContext.validate(auditContext);
		if (StringUtils.isEmpty(auditContext.getAuditID()))
			auditContext.setAuditID(AuditIDGenerator.generateAuditID());
		StringBuilder auditString = new StringBuilder(AUDIT_FORMAT_VERSION);
		auditString.append(AUDIT_DELIMITER);
		auditString.append(auditEventCode);
		auditString.append(AUDIT_DELIMITER);
		auditString.append(auditContext.getAuditID());
		auditString.append(AUDIT_DELIMITER);
		if (auditContext.getUserId() != null)
			auditString.append(auditContext.getUserId());
		auditString.append(AUDIT_DELIMITER);
		if (auditContext.getClientUniqueID() != null)
			auditString.append(auditContext.getClientUniqueID());  //external user ID
		auditString.append(AUDIT_DELIMITER);
		if (auditContext.getClientUniqueKey() != null)
			auditString.append(auditContext.getClientUniqueKey());
		auditString.append(AUDIT_DELIMITER);
		if (auditContext.getApplicationName() != null)
			auditString.append(auditContext.getApplicationName());
		auditString.append(AUDIT_DELIMITER);
		if (auditContext.getStationID() != null)
			auditString.append(auditContext.getStationID());
		auditString.append(AUDIT_DELIMITER);
		if (auditContext.getClientIPAddress() != null)
			auditString.append(auditContext.getClientIPAddress());
		auditString.append(AUDIT_DELIMITER);
		if (ServerUtil.getInstanceName() != null)
			auditString.append(ServerUtil.getInstanceName());
		auditString.append(AUDIT_DELIMITER);
		if (success != null)
			auditString.append(success);
		return auditString;
	}
	
	public void audit(String nameSpace, String auditString)
	{
		Logger auditLogger=getAuditLogger(nameSpace);
		auditLogger.info(auditString);
	}
	

}
