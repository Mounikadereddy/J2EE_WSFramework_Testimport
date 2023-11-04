/*
 * TuxedoService.java
 *
 * Copyright 2008 U.S. Dept Of Veterans Affairs.
 * U.S. Government PROPRIETARY/CONFIDENTIAL. Use is subject to security terms.
 */
package gov.va.vba.framework.services;

import gov.va.vba.framework.domain.vo.ParticipantType;
import gov.va.vba.framework.domain.vo.ServiceVO;
import weblogic.wtc.jatmi.TypedBuffer;

/**
 * Business interface for TuxedoService service API
 *
 * @since	Aug 21, 2008
 * @version	
 * @author	Mario Rodrigues
 * @param <T>
 */
public interface TuxedoService {
		
	String execute(ServiceVO serviceVo) throws TuxedoException;
	/*
	 * overloads execute adding transaction parameter
	 */
	String execute(ServiceVO serviceVo, boolean transaction) throws TuxedoException;
	
	/**
	 * A convenience method that allows for remote invocation of WTC services without coupling the WTC to client code. 
	 * Used to execute FML32 based VETSNET services
	 * 
	 * @param 	serviceName. The Tuxedo Service 
	 * @param 	TypedFML32 buffer
	 * @return
	 * @throws 	TuxedoException	
	 * @throws	
	 * @return	TypedFML32 buffer
	 * @since	May 3, 2010
	 */
	TypedBuffer execute(String serviceName, TypedBuffer buffer) throws TuxedoException;
	
	/*
	 * overloads execute adding transaction parameter
	 */
	TypedBuffer execute(String serviceName, TypedBuffer buffer, boolean transaction) throws TuxedoException;
	
	/**
	 * Executes the WTCCTRLHINQ Tuxedo service to retreive veteran data
	 * 
	 * @param String - portalId 
	 * @param long - key
	 * @param String - ipAddress
	 * @param String - fileNum
	 * @param boolean - veteranSelf
	 * @param boolean - serviceNumIndicator
	 * @return String (Reply from TuxedoService service)
	 * @throws Exception	
	 * @since	May 27, 2008
	 */
	String getVeteranData(String stationId, String portalId, long key, String ipAddress, String fileNum, boolean veteranSelf, boolean serviceNumIndicator) 
		throws TuxedoException;
	
	/**
	 * Executes the CESTROUTER Tuxedo service to create a claim
	 * 
	 * @param portalId
	 * @param key
	 * @param ipAddress
	 * @param String - data. This arg needs to be a fixed length string delimited according to business logic
	 * @return
	 * @throws Exception	
	 * @since	Jun 24, 2008
	 */
	String createCorpClaim(String stationId, String portalId, long key, String ipAddress, String inputData) throws TuxedoException;

	/**
	 * Executes the WTCHINRDP Tuxedo service to retrieve a PIF record
	 * 
	 * @param portalId
	 * @param key
	 * @param ipAddress
	 * @param fileNum
	 * @param veteranSelf
	 * @return
	 * @throws TuxedoException	
	 * @since	Jun 24, 2008
	 */
	String executePIFinquiry(String stationId, String portalId, long key, String ipAddress, String fileNum, boolean veteranSelf) 
		throws TuxedoException;
	
	/**
	 * Executes the WTCHINBDN Tuxedo service to retrieve a beneficiary record
	 * 
	 * @param portalId
	 * @param key
	 * @param ipAddress
	 * @param fileNum
	 * @return
	 * @throws TuxedoException	
	 * @since	Jun 24, 2008
	 */
	String executeSurvivingSpouseInquiry(String stationId, String portalId, long key, String ipAddress, String fileNum) throws TuxedoException;	

	
	/**
	 * Executes the WTCFLASH Tuxedo service to retrieve flash records from corp db
	 * 
	 * @param portalId
	 * @param key
	 * @param ipAddress
	 * @param fileNum
	 * @throws Exception	
	 * @return	
	 * @since	Jul 22, 2008
	 */
	String getCorpFlashes(String stationId, String portalId, long key, String ipAddress, String fileNum) throws TuxedoException;

	/**
	 * Executes the WTCFLASH Tuxedo service to create/update flash records in corp db
	 * 
	 * @param portalId
	 * @param key
	 * @param ipAddress
	 * @param participant id
	 * @param String array - Number of flashes
	 * @return
	 * @throws Exception	
	 * @since	Jun 24, 2008
	 */
	String createCorpFlashes(String stationId, String portalId, long key, String ipAddress, String fileNum, String[] flashes) 
		throws TuxedoException;
	
	/**
	 * Executes the WTCSNTVTY Tuxedo service to retrieve the sensitivity level of a participant's record
	 * 
	 * @return	
	 * @throws 	TuxedoException	
	 * @since	October 18, 2008 
   	 */
	String getSensitiveLevel(String stationId, String portalId, long key, String ipAddress, String ssn, ParticipantType participant) throws TuxedoException;

	/**
	 * Executes the SHRINQ3 Tuxedo service to retrieve a veteran's dependents data from the Corporate Db.
	 *  
	 * @return	
	 * @throws 	TuxedoException	
	 * @since	February 18, 2009
	 * @see gov.va.vba.framework.services.TuxedoService#findDependents(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 * TODO: fix null appl name
	 */
	String findDependents(String userId, String stationId, String ipAddress, String veteranId, String claimantId) throws TuxedoException;
	
	/**
	 * Executes the CESTROUTER Tuxedo service to create a veteran in the Corporate Db. CESTROUTER in most cases does validation on Corp, BIRLS, BDN, etc.
	 * and then a BUPD
	 * 
	 * @return	
	 * @throws 	TuxedoException	
	 * @since	February 18, 2009 
	 * @see gov.va.vba.framework.services.TuxedoService#createVeteran(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	String createVeteran(String userId, String stationId, String ipAddress, String inputData) throws TuxedoException; 
	
	/**
	 * Executes the CESTROUTER Tuxedo service to create a veteran's dependents in the Corporate Db. CESTROUTER performs a CDEP transaction
	 *  
	 * @return	
	 * @throws 	TuxedoException	
	 * @since	February 18, 2009 
	 * @see gov.va.vba.framework.services.TuxedoService#createDependents(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	String createDependents(String userId, String stationId, String ipAddress, String inputData) throws TuxedoException;
	
	/**
	 * Sends requests to BDN via TPTODMIV to process payment information.
	 * 
	 * @param userId
	 * @param stationId
	 * @param ipAddress
	 * @param inputData
	 * @return
	 * @throws TuxedoException	
	 * @throws	
	 * @return	
	 * @since	February 18, 2009
	 */
	String processBDNPayments(String userId, String stationId, String ipAddress, String inputData) throws TuxedoException;

	/**
	 * Retrieves veteran data from BIRLS. Calls TPTOCICS internally to access Austin IDMS db's for BIRLS, COVERS, etc.
	 * 
	 * @return	
	 * @throws 	TuxedoException	
	 * @since	February 18, 2009
	 * @see 	gov.va.vba.framework.services.TuxedoService#getVeteranBIRLSData(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 * TODO: fix null appl name
	 */
	String getVeteranBIRLSData(String userId, String stationId, String ipAddress, String inputData) throws TuxedoException;
	
	/**
	 * Provides the functionality to retrieve veteran sensitive data from BIRLS. Calls TPTOCICS directly.
	 * 
	 * @return	
	 * @throws 	TuxedoException	
	 * @since	February 18, 2009
	 * @see 	gov.va.vba.framework.services.TuxedoService#getVeteranBIRLSData(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 * TODO: fix null appl name
	 */
	String getVeteranBIRLSsensitiveData(String userId, String stationId, String ipAddress, String inputData) throws TuxedoException;

	/**
	 * 
	 * @param portalId
	 * @param key
	 * @param stationId
	 * @param ipAddress
	 * @param inputData
	 * @return
	 * @throws TuxedoException	
	 * @throws	
	 * @return	
	 * @since	Mar 2, 2010
	 */
	String getClaimantPaymentHistory(String portalId, String key, String stationId, String ipAddress, String inputData) throws TuxedoException;

	
	/**
	 * Convenience method to allow for generic access to Tuxedo SHRINQM service
	 * 
	 * @param userId
	 * @param stationId
	 * @param ipAddress
	 * @param applName
	 * @param inputData
	 * @return
	 * @throws TuxedoException	
	 * @throws	
	 * @return	
	 * @since	Jul 9, 2010
	
	public String executeSHRINQM(String userId, String stationId, String ipAddress, String applName, String inputData) throws TuxedoException;
	  */
}
