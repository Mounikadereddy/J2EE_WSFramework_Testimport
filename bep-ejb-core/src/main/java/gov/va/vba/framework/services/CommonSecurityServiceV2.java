package gov.va.vba.framework.services;


import gov.va.vba.framework.common.AuditContext;
import gov.va.vba.framework.services.CssUserRepositoryException;
import gov.va.vba.framework.services.CssUserRepositoryUnknownException;
import gov.va.vba.framework.css.cssiam.domain.entities.CssUser;
import gov.va.vba.framework.domain.vo.ServiceVO;
import gov.va.vba.framework.esb.transformers.TuxedoSecurityProfile;

import java.util.Map;


/*
* Main security EJB that handles all CSS updates
*  
* @since	Dec 9, 2010
* @version	2
* @author	Joshua Glickman
*/
@SuppressWarnings("rawtypes")
public interface CommonSecurityServiceV2 {	
	
	/**
	 * Retrieves a raw TuxedoSecurityProfile object for a specific user 
	 * 
	 * @param vo
	 * @param auditContext
	 * @param extensions
	 * @return	gov.va.vba.framework.esb.transformers.TuxedoSecurityProfile
	 * @throws 	TuxedoException
	 * @since	Dec 9, 2010
	 */
	TuxedoSecurityProfile getSecurityProfile(ServiceVO vo, AuditContext auditContext, Map extensions) throws TuxedoException;
	
	/**
	 * Get the CssUser information from the DataSource, specifically the stations associated to the User and Application
	 * @param userId CSS User
	 * @param applicationName CSS Application name
	 * @param auditContext Audit Context to run audit into this EJB operation
	 * @return CssUser associated to the userId and applicationName
	 * @throws CssUserRepositoryException 
	 */
	CssUser getCssUserStationsByApplication(String userId, String applicationName, AuditContext auditContext)  throws CssUserRepositoryException, CssUserRepositoryUnknownException;
	
}
