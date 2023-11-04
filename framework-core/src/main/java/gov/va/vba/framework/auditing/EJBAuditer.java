package gov.va.vba.framework.auditing;

import gov.va.vba.framework.common.AuditContext;
import gov.va.vba.framework.services.ejb.TuxParams;

public class EJBAuditer extends EJBWSCommonAuditer{

	@Deprecated
	public void audit(AuditContext auditContext, String ejb, String method, long executionTime)
	{
		audit(auditContext, UNKNOWN, null, ejb, method, executionTime);
	}
	
	@Deprecated
	public void audit(AuditContext auditContext, TuxParams tuxParams, String ejb, String method, long executionTime)
	{
		audit(auditContext, UNKNOWN, tuxParams, ejb, method, executionTime);
	}
	
	public void audit(AuditContext auditContext, boolean success, String ejb, String method, long executionTime)
	{
		audit(auditContext, String.valueOf(success), null, ejb, method, executionTime);
	}
	
	public void audit(AuditContext auditContext, boolean success, TuxParams tuxParams, String ejb, String method, long executionTime)
	{
		String auditString=getAuditString(auditContext, String.valueOf(success), tuxParams, ejb, method, null, executionTime).toString();
		getAuditLogger(ejb).info(auditString);
	}
	
	public void audit(AuditContext auditContext, boolean success, TuxParams tuxParams, String ejb, String method, String tuxedoServiceName, long executionTime)
	{
		String auditString=getAuditString(auditContext, String.valueOf(success), tuxParams, ejb, method, tuxedoServiceName, executionTime).toString();
		getAuditLogger(ejb).info(auditString);
	}

	private void audit(AuditContext auditContext, String success, TuxParams tuxParams, String ejb, String method, long executionTime)
	{
		String auditString=getAuditString(auditContext, success, tuxParams, ejb, method, null, executionTime).toString();
		getAuditLogger(ejb).info(auditString);
	}
	
	protected StringBuilder getAuditString(AuditContext auditContext, String success, TuxParams tuxParams, String ejb, String method, String tuxedoServiceName, long executionTime)
	{
		StringBuilder auditStringBuilder=super.getAuditString(auditContext, AuditEventCode.EJB, success, executionTime, isTuxedo(auditContext, tuxParams, tuxedoServiceName));
		auditStringBuilder.append(AUDIT_DELIMITER);
		auditStringBuilder.append(ejb);
		auditStringBuilder.append(AUDIT_DELIMITER);
		auditStringBuilder.append(method);
		if (isTuxedo(auditContext, tuxParams, tuxedoServiceName))
			auditStringBuilder.append(getTuxedoAuditString(auditContext, tuxParams, tuxedoServiceName));
		return auditStringBuilder;
	}
}
