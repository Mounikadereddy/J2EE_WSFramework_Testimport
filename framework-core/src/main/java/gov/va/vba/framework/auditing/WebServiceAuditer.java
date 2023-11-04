package gov.va.vba.framework.auditing;

import gov.va.vba.framework.common.AuditContext;
import gov.va.vba.framework.services.ejb.TuxParams;

public class WebServiceAuditer extends EJBWSCommonAuditer{


	@Deprecated
	public void audit(AuditContext auditContext,  String webService, String operation, long executionTime)
	{
		audit(auditContext, UNKNOWN, null, webService, operation, executionTime);
	}
	@Deprecated
	public void audit(AuditContext auditContext, TuxParams tuxParams, String webService, String operation, long executionTime)
	{
		audit(auditContext, UNKNOWN, tuxParams, webService, operation, executionTime);
	}
	
	public void audit(AuditContext auditContext, boolean success, String webService, String operation, long executionTime)
	{
		audit(auditContext, ""+success, null, webService, operation, executionTime);
	}
	
	public void audit(AuditContext auditContext, String success, TuxParams tuxParams, String webService, String operation, long executionTime)
	{
		audit(auditContext, success, tuxParams, webService, operation, null ,executionTime);
	}
	
	public void audit(AuditContext auditContext, String success, TuxParams tuxParams, String webService, String operation, String tuxServiceName, long executionTime)
	{
		if (webService==null)
			webService="";
		if (operation==null)
			operation="";
		String auditString=getAuditString(auditContext, success, tuxParams, webService, operation, tuxServiceName, executionTime).toString();
		getAuditLogger(webService).info(auditString);
	}
	
	protected StringBuilder getAuditString(AuditContext auditContext, String success, TuxParams tuxParams, String webService, String operation, String tuxServiceName, long executionTime)
	{
		StringBuilder auditStringBuilder=super.getAuditString(auditContext, AuditEventCode.WEB_SERVICE, success, executionTime, isTuxedo(auditContext, tuxParams, tuxServiceName));
		auditStringBuilder.append(AUDIT_DELIMITER);
		auditStringBuilder.append(webService);
		auditStringBuilder.append(AUDIT_DELIMITER);
		auditStringBuilder.append(operation);
		if (isTuxedo(auditContext, tuxParams, tuxServiceName))
			auditStringBuilder.append(getTuxedoAuditString(auditContext, tuxParams, tuxServiceName));
		return auditStringBuilder;
	}
}
