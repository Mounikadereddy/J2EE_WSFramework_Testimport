package gov.va.vba.framework.auditing;


import gov.va.vba.framework.common.AuditContext;
import gov.va.vba.framework.common.StringUtils;
import gov.va.vba.framework.services.ejb.TuxParams;

public abstract class EJBWSCommonAuditer extends GenericAuditer {
	
	protected StringBuilder getAuditString(AuditContext auditContext, AuditEventCode auditEventCode, String success, long executionTime, boolean tuxedoIndicator) {
		StringBuilder auditString = super.getAuditString(auditContext, auditEventCode, success);
		auditString.append(AUDIT_DELIMITER);
		auditString.append(executionTime);
		auditString.append(AUDIT_DELIMITER);
		auditString.append(tuxedoIndicator);
		return auditString;
	}
	
	protected static boolean isTuxedo(AuditContext auditContext, TuxParams tuxParams, String tuxedoServiceName)
	{
		boolean tuxedo=false;
		
		//check for tuxedo service name in audit context for V2
		tuxedo=!StringUtils.isEmpty(auditContext.getTuxedoServiceName());
		
		//check for tuxedo service name in tuxParams for V3
		tuxedo=tuxedo || tuxParams!=null;
		
		//check if a service name was inferred from the method
		tuxedo = tuxedo || tuxedoServiceName!=null && !"".equals(tuxedoServiceName);
		
		return tuxedo;
	}
	
	protected StringBuilder getTuxedoAuditString(AuditContext auditContext, TuxParams tuxParams, String tuxedoServiceName) {
		StringBuilder auditString = new StringBuilder();
		auditString.append(AUDIT_DELIMITER);
		if (tuxParams!=null)
			auditString.append(tuxParams.getActualServiceName());
		else if (!StringUtils.isEmpty(auditContext.getTuxedoServiceName())) 
			auditString.append(auditContext.getTuxedoServiceName());
		else
			auditString.append(tuxedoServiceName);
		return auditString;
	}
	
}
