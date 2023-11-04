package gov.va.vba.framework.auditing;

import gov.va.vba.framework.common.AuditContext;

public class LoginAuditer extends FrameworkCommonAuditer {

	@Override
	protected StringBuilder getAuditString(AuditContext auditContext,
			String success) {
		StringBuilder auditString=super.getAuditString(auditContext, AuditEventCode.LOGIN, ""+success);
		return auditString;
	}
	
}
