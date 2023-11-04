package gov.va.vba.framework.auditing;

import gov.va.vba.framework.common.AuditContext;

public class LogoutAuditer extends FrameworkCommonAuditer {

	@Deprecated
	protected StringBuilder getAuditString(AuditContext auditContext) {
		StringBuilder auditString=super.getAuditString(auditContext, AuditEventCode.LOGOUT, UNKNOWN);
		return auditString;
	}

	@Override
	protected StringBuilder getAuditString(AuditContext auditContext,
			String success) {
		StringBuilder auditString=super.getAuditString(auditContext, AuditEventCode.LOGOUT, success);
		return auditString;
	}
}
