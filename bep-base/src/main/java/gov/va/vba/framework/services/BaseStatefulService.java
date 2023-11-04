package gov.va.vba.framework.services;

import gov.va.vba.framework.common.AuditContext;

/**
 * 
 * Service interface to be implemented all Stateful Beans if
 * <code>JournalingInterceptor</code> need to be applied.
 * 
 * @see gov.va.vba.bep.journaling.JournalingInterceptor
 * 
 * @author psimyeru
 * 
 * 
 */
public interface BaseStatefulService extends BaseService {

	/**
	 * Set AuditContext
	 * 
	 * @param auditContext
	 */
	void setAuditContext(AuditContext auditContext);

	/**
	 * 
	 * Return AuditContext.
	 * 
	 * @return
	 */
	 AuditContext getAuditContext();
}
