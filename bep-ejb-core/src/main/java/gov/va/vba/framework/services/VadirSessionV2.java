package gov.va.vba.framework.services;

import gov.va.vba.framework.common.AuditContext;

import java.util.Map;

/**
 * Business interface for Vadir service API Main EJB 
 * 
 * @since Dec 9, 2010
 * @version 2
 * @author Joshua Glickman
 */
@SuppressWarnings("rawtypes")
public interface VadirSessionV2 {
	/**
	 * Method that gets Vadir data 
	 * 
	 * @param vonAppUser
	 * @param vonAppTerm
	 * @param vaIdIn
	 * @param auditContext
	 * @param extensions
	 * @return String
	 * @since Dec 9, 2010
	 */
	String getVadirData(String vonAppUser, String vonAppTerm, String vaIdIn, AuditContext userontext, Map extensions);
}
