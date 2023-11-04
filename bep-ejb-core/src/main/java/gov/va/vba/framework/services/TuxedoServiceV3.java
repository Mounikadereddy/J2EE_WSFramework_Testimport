/**
 * TuxedoService.java
 * 
 * Copyright 2008 U.S. Dept Of Veterans Affairs.
 * U.S. Government PROPRIETARY/CONFIDENTIAL. Use is subject to security terms.
 */
package gov.va.vba.framework.services;

import gov.va.vba.framework.common.AuditContext;
import gov.va.vba.framework.domain.vo.ParticipantType;
import gov.va.vba.framework.services.ejb.TuxParams;

import java.util.Map;
import weblogic.wtc.jatmi.*;

/**
 * Business interface for TuxedoService service API Main TuxedoService EJB that
 * handles execution of all service requests to underlying TuxedoService
 * services.
 * 
 * @since April 16, 2012
 * @version 2
 * @author Joshua Glickman
 */
@SuppressWarnings("rawtypes")
public interface TuxedoServiceV3 {
	/**
	 * @param tuxParams
	 * @param auditContext
	 * @param extensions
	 * @return String
	 * @throws TuxedoException
	 * @since April 16, 2012
	 * 
	 * This API should be used when NOT needing to pass an FMLBuffer. It supports 16 bit FML buffers internally
	 *  
	 * @see TuxedoServiceV3.execute()
	 */
	String processRequest(TuxParams tuxParams,	AuditContext auditContext, Map extensions)throws TuxedoException ;

	/**
	 * Executes the WTCCTRLHINQ Tuxedo service to retreive veteran data
	 * 
	 * @param portalId
	 * @param key
	 * @param String
	 *            - fileNum
	 * @param boolean - veteranSelf
	 * @param boolean - serviceNumIndicator
	 * @param AuditContext
	 *            - auditContext
	 * @param Map
	 *            - extra parameters
	 * @return String (Reply from TuxedoService service)
	 * @throws Exception
	 * @since Dec 9, 2010
	 */
	String getVeteranData(String fileNum,
			boolean veteranSelf, boolean serviceNumIndicator,
			AuditContext auditContext, Map extensions) throws TuxedoException;

	/**
	 * Executes the CESTROUTER Tuxedo service to create a claim
	 * 
	 * @param portalId
	 * @param key
	 * @param ipAddress
	 * @param String
	 *            - data. This arg needs to be a fixed length string delimited
	 *            according to business logic
	 * @param auditContext
	 * @param extensions
	 * @param Map
	 * @return String
	 * @throws Exception
	 * @since Dec 9, 2010
	 */
	String createCorpClaim(String inputData, AuditContext auditContext, Map extensions)
			throws TuxedoException;

	/**
	 * Executes the WTCHINRDP Tuxedo service to retrieve a PIF record
	 * 
	 * @param portalId
	 * @param key
	 * @param ipAddress
	 * @param fileNum
	 * @param veteranSelf
	 * @param auditContext
	 * @param extensions
	 * @return String
	 * @throws TuxedoException
	 * @since Dec 9, 2010
	 */
	String executePIFinquiry(String fileNum,
			boolean veteranSelf, AuditContext auditContext, Map extensions)
			throws TuxedoException;

	/**
	 * Executes the WTCHINBDN Tuxedo service to retrieve a beneficiary record
	 * 
	 * @param portalId
	 * @param key
	 * @param fileNum
	 * @param auditContext
	 * @param extensions
	 * @return String
	 * @throws TuxedoException
	 * @since Dec 9, 2010
	 */
	String executeSurvivingSpouseInquiry(String fileNum, AuditContext auditContext, Map extensions)
			throws TuxedoException;

	/**
	 * Executes the WTCFLASH Tuxedo service to retrieve flash records from corp
	 * db
	 * 
	 * @param portalId
	 * @param key
	 * @param fileNum
	 * @param auditContext
	 * @param extensions
	 * @throws TuxedoException
	 * @return String
	 * @since Dec 9, 2010
	 */
	String getCorpFlashes(String fileNum, AuditContext auditContext, Map extensions) throws TuxedoException;

	/**
	 * Executes the WTCFLASH Tuxedo service to create/update flash records in
	 * corp db
	 * 
	 * @param portalId
	 * @param key
	 * @param participant
	 *            id
	 * @param String
	 *            array - Number of flashes
	 * @param auditContext
	 * @param extensions
	 * @return String
	 * @throws TuxedoException
	 * @since Dec 9, 2010
	 */
	String createCorpFlashes(String fileNum,	String[] flashes, AuditContext auditContext, Map extensions)
			throws TuxedoException;

	/**
	 * Executes the WTCSNTVTY Tuxedo service to retrieve the sensitivity level
	 * of a participant's record
	 * 
	 * @param portalId
	 * @param key
	 * @param ssn
	 * @param pariticipantType
	 * @param auditContext
	 * @param extensions
	 * @return String
	 * @throws TuxedoException
	 * @since Dec 9, 2010
	 */
	String getSensitiveLevel(String ssn,	ParticipantType participant, AuditContext auditContext,	Map extensions) throws TuxedoException;

	/**
	 * Executes the SHRINQ3 Tuxedo service to retrieve a veteran's dependents
	 * data from the Corporate Db.
	 * 
	 * @param veteranId
	 * @param claimantId
	 * @param auditContext
	 * @param extensions
	 * @return String
	 * @throws TuxedoException
	 * @since Dec 9, 2010
	 * @see gov.va.vba.framework.services.TuxedoService2#findDependents(java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.String,
	 *      java.lang.String)
	 */
	String findDependents(String veteranId, String claimantId,
			AuditContext auditContext, Map extensions) throws TuxedoException;

	/**
	 * Executes the CESTROUTER Tuxedo service to create a veteran in the
	 * Corporate Db. CESTROUTER in most cases does validation on Corp, BIRLS,
	 * BDN, etc. and then a BUPD
	 * 
	 * @param inputData
	 * @param auditContext
	 * @param extensions
	 * @return String
	 * @throws TuxedoException
	 * @since Dec 9, 2010
	 * @see gov.va.vba.framework.services.TuxedoService2#createVeteran(java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.String)
	 */
	String createVeteran(String inputData, AuditContext auditContext,
			Map extensions) throws TuxedoException;

	/**
	 * Executes the CESTROUTER Tuxedo service to create a veteran's dependents
	 * in the Corporate Db. CESTROUTER performs a CDEP transaction
	 * 
	 * @param inputData
	 * @param auditContext
	 * @param extensions
	 * @return String
	 * @throws TuxedoException
	 * @since Dec 9, 2010
	 * @see gov.va.vba.framework.services.TuxedoService2#createDependents(java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.String)
	 */
	String createDependents(String inputData, AuditContext auditContext,
			Map extensions) throws TuxedoException;

	/**
	 * Sends requests to BDN via TPTODMIV to process payment information.
	 * 
	 * @param inputData
	 * @param auditContext
	 * @param extensions
	 * @return String
	 * @throws TuxedoException
	 * @since Dec 9, 2010
	 */
	String processBDNPayments(String inputData,
			AuditContext auditContext, Map extensions) throws TuxedoException;

	/**
	 * Retrieves veteran data from BIRLS. Calls TPTOCICS internally to access
	 * Austin IDMS db's for BIRLS, COVERS, etc.
	 * 
	 * @param inputData
	 * @param auditContext
	 * @param extensions
	 * @return String
	 * @throws TuxedoException
	 * @since Dec 9, 2010
	 * @see gov.va.vba.framework.services.TuxedoService#getVeteranBIRLSData(java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.String,
	 *      java.lang.String)
	 */
	String getVeteranBIRLSData(String inputData,
			AuditContext auditContext, Map extensions) throws TuxedoException;

	/**
	 * Convenience method for COVERS/FTS based on management's request for a
	 * quick fix.
	 * 
	 * @param inputData
	 * @param auditContext
	 * @param extensions
	 * @return String
	 * @throws TuxedoException
	 * @since Dec 9, 2010
	 * @see gov.va.vba.framework.services.TuxedoService2#getVeteranBIRLSsensitiveData(java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.String)
	 */
	String getVeteranBIRLSsensitiveData(String inputData,
			AuditContext auditContext, Map extensions) throws TuxedoException;

	/**
	 * A SHARE service that executes the SHRINQ3 Tuxedo service and returns a
	 * claimant's payment history. Used by eBenefits C&P Claims.
	 * 
	 * @param portalId
	 * @param key
	 * @param inputData
	 * @param auditContext
	 * @param extensions
	 * @return String
	 * @throws TuxedoException
	 * @since Dec 9, 2010
	 */
	String getClaimantPaymentHistory(String inputData, AuditContext auditContext, Map extensions)
			throws TuxedoException;

/**
 * 
 * @param serviceName
 * @param buffer
 * @param transaction
 * @param auditContext
 * @param extensions
 * @return
 * @throws TuxedoException
 * 
 */
	@Deprecated
	TypedBuffer execute(String serviceName, TypedFML32 buffer, boolean transaction, AuditContext auditContext, Map extensions)
	throws TuxedoException;

/**
 * 
 * @param serviceName
 * @param buffer
 * @param transaction
 * @param auditContext
 * @param extensions
 * @return
 * @throws TuxedoException
 * 
 * This API should be used when needing to pass an FMLBuffer. It supports 16 and 32 bit FML buffers.
 * 
 * @see TuxedoServiceV3.processRequest()
 */
	TypedBuffer execute(String serviceName, TypedBuffer buffer, int transaction, AuditContext auditContext, Map extensions)
	throws TuxedoException;
	TypedBuffer execute(String serviceName, TypedBuffer buffer, AuditContext auditContext, Map extensions)
	throws TuxedoException;
}
