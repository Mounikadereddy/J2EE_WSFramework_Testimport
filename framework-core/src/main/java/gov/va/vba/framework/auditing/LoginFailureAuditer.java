package gov.va.vba.framework.auditing;

import gov.va.vba.framework.common.AuditContext;

@Deprecated
public class LoginFailureAuditer extends FrameworkCommonAuditer {

	protected StringBuilder getAuditString(AuditContext auditContext) {
		StringBuilder auditString=super.getAuditString(auditContext, AuditEventCode.LOGIN, ""+false);
		return auditString;
	}

	@Override
	protected StringBuilder getAuditString(AuditContext auditContext,
			String success) {
		StringBuilder auditString=super.getAuditString(auditContext, AuditEventCode.LOGIN, ""+false);
		return auditString;
	}
	
}
