package gov.va.vba.framework.auditing;

import gov.va.vba.framework.common.AuditContext;

public abstract class FrameworkCommonAuditer extends GenericAuditer {
	public static final String AUDIT_NAME_SPACE = "Framework";

	protected StringBuilder getAuditString(AuditContext auditContext,
			boolean success) {
		return getAuditString(auditContext, "" + success);
	}

	protected abstract StringBuilder getAuditString(AuditContext auditContext,
			String success);

	public void audit(AuditContext auditContext, boolean success) {
		String auditString = getAuditString(auditContext, success).toString();
		super.audit(AUDIT_NAME_SPACE, auditString);
	}

	public void audit(AuditContext auditContext) {
		String auditString = getAuditString(auditContext, UNKNOWN).toString();
		super.audit(AUDIT_NAME_SPACE, auditString);
	}

}
