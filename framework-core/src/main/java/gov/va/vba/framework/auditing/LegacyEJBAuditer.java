package gov.va.vba.framework.auditing;

import gov.va.vba.framework.common.AuditContext;

public class LegacyEJBAuditer extends FrameworkCommonAuditer {

	private String ejb = null;
	private String method = null;

	public void audit(String ejb, String method) {
		AuditContext auditContext = new AuditContext();
		auditContext.setUserId("unknown");
		auditContext.setApplicationName("framework");
		auditContext.setClientIPAddress("unknown");
		auditContext.setStationID("unknown");
		this.ejb = ejb;
		this.method = method;
		super.audit(auditContext);
	}

	protected StringBuilder getAuditString(AuditContext auditContext,
			String success) {
		StringBuilder auditString = super.getAuditString(auditContext,
				AuditEventCode.LEGACY_EJB, success);
		auditString.append(AUDIT_DELIMITER);
		auditString.append(ejb);
		auditString.append(AUDIT_DELIMITER);
		auditString.append(method);
		return auditString;
	}

}
