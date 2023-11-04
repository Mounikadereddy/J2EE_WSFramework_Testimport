package gov.va.vba.framework.services;

import gov.va.vba.framework.common.AuditContext;
import gov.va.vba.framework.domain.entities.Person;

import java.util.Map;

/**
 * Main Veteran EJB that handles execution of all service requests 
 * to underlying Veteran services against the corp database 
 *  
 * @since	Dec 9, 2010
 * @version 2	
 * @author	Joshua Glickman
 */
@SuppressWarnings("rawtypes")
public interface VeteranSessionV2 {

	/**
	 * Method that gets Person data 
	 * 
	 * @param partId
	 * @param auditContext
	 * @param extensions
	 * @return Person
	 * @since Dec 9, 2010
	 */
	Person findPerson(Long partId, AuditContext auditContext, Map extensions);
	/**
	 * Method that gets Person data 
	 * 
	 * @param ssn
	 * @param auditContext
	 * @param extensions
	 * @return Person
	 * @since Dec 9, 2010
	 */
	Person findPerson(String ssn, AuditContext auditContext, Map extensions);
	/**
	 * Method that gets a CorpXMLDataStream
	 * 
	 * @param ssn
	 * @param auditContext
	 * @param extensions
	 * @return String
	 * @since Dec 9, 2010
	 */
	String getCorpXMLDataStream(String ssn, AuditContext auditContext, Map extensions);
}
