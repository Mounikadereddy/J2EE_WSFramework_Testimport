/*
 * TuxedoSessionEJB.java
 *
 * Copyright 2007 U.S. Dept Of Veterans Affairs.
 * U.S. Government PROPRIETARY/CONFIDENTIAL. Use is subject to security terms.
 */
package gov.va.vba.framework.services.ejb;

import gov.va.vba.framework.auditing.LegacyEJBAuditer;
import gov.va.vba.framework.common.TuxedoErrorUtils;
import gov.va.vba.framework.domain.vo.ParticipantType;
import gov.va.vba.framework.domain.vo.ServiceVO;
import gov.va.vba.framework.esb.connectors.client.TuxedoConnector;
import gov.va.vba.framework.esb.connectors.client.TuxedoConnectorException;
import gov.va.vba.framework.logging.Logger;
import gov.va.vba.framework.services.CommonSecurityService;
import gov.va.vba.framework.services.GenericService;
import gov.va.vba.framework.services.TuxedoException;
import gov.va.vba.framework.services.TuxedoService;
import gov.va.vba.framework.services.TuxedoServiceRemote;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.apache.commons.lang.StringUtils;

import weblogic.javaee.CallByReference;
import weblogic.javaee.TransactionTimeoutSeconds;
import weblogic.wtc.jatmi.TPReplyException;
import weblogic.wtc.jatmi.TypedBuffer;


/**
 * Main TuxedoService EJB that handles execution of all service requests to underlying
 * TuxedoService services. 
 *  
 * @since	Mar 31, 2006
 * @version	
 * @author	Mario Rodrigues
 */
@Stateless(name="TuxedoService", mappedName="VbaTuxedoService")
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@Local(TuxedoService.class)
@Remote(TuxedoServiceRemote.class)
@CallByReference
@TransactionTimeoutSeconds(3600)
@EJB(name="ejb/SecurityService", beanInterface=CommonSecurityService.class)
public class TuxedoSessionEJB extends GenericService implements TuxedoService {
	
	@Resource
	private String constructed = null;	
	@Resource 
	private SessionContext ctx;
	private int cnt;
	private static Logger logger=Logger.getLogger(TuxedoSessionEJB.class);

	/**
	 * Default constructor - per EJB 3.0 spec.
	 * 
	 * @since	Oct 14, 2008
	 * @version
	 */
	public TuxedoSessionEJB() {
		   	
	}

	@Override
	public String execute(ServiceVO serviceVo, boolean transaction)
			throws TuxedoException {
		new LegacyEJBAuditer().audit(TuxedoSessionEJB.class.getName(), "execute(serviceVo, transaction");
		logger.debug("TuxedoSessionEJB::execute(). Invoked Bus Interf: "+ctx.getInvokedBusinessInterface().getSimpleName());
		logger.debug("TuxedoSessionEJB: Tuxedo Connector execute - "+serviceVo.getServiceName()+" by "+this.ctx.getCallerPrincipal().getName());
		try {
			return new TuxedoConnector().invokeTUXservice(serviceVo, transaction);
		}
		catch (TPReplyException tpr)
		{
	    	throw new TuxedoException(tpr, TuxedoErrorUtils.getTuxedoErrorMessage(tpr),TuxedoErrorUtils.getTuxedoErrorCode(tpr));
		}
		catch (TuxedoException te)
		{
			throw te;
		}
		catch (Exception e) {
			throw new TuxedoException(e);
		}		
	}
	
	/**
	 * Generic execute method that executes the named TuxedoService service in
	 * gov.va.vba.framework.domain.vo.ServiceVO$TuxedoService.
	 * 
	 * Only handles external user requests
	 * 
	 * 
	 * @param 	ServiceVO
	 * @return	
	 * @throws 	Exception 
	 * @throws	
	 * @return	
	 * @see		gov.va.vba.framework.domain.vo.ServiceVO
	 * @since	Jan 10, 2007
	 */
	public String execute(ServiceVO serviceVo) throws TuxedoException {
	
		new LegacyEJBAuditer().audit(TuxedoSessionEJB.class.getName(), "execute(ServiceVO)");
		logger.debug("TuxedoSessionEJB::execute(). Invoked Bus Interf: "+ctx.getInvokedBusinessInterface().getSimpleName());
		try {
			return new TuxedoConnector().invokeTUXservice(serviceVo);
		}
		catch (TPReplyException tpr)
		{
	    	throw new TuxedoException(tpr, TuxedoErrorUtils.getTuxedoErrorMessage(tpr),TuxedoErrorUtils.getTuxedoErrorCode(tpr));
		}
		catch (TuxedoException te)
		{
			throw te;
		}
		catch (Exception e) {
			throw new TuxedoException(e);
		}		
	}

	
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
	public String getVeteranData(String stationId, String portalId, long key, String ipAddress, String fileNum, boolean veteranSelf, boolean serviceNumIndicator) throws TuxedoException {
		
		new LegacyEJBAuditer().audit(TuxedoSessionEJB.class.getName(), "getVeteranData");
		ServiceVO vo = null;
	
		logger.debug("TuxedoSessionEJB::getVeteranData(). Invoked Bus Interf: "+ctx.getInvokedBusinessInterface().getSimpleName());
		vo = new ServiceVO(StringUtils.substring(portalId, 0, 15), stationId, ipAddress, "WEBLGY", ServiceVO.LGYService.WTCCTRLHINQ);		
		vo.setExternalId(portalId);
		vo.setExternalKey(String.valueOf(key));
		vo.setData(fileNum+(veteranSelf? "Y" : "N")+(serviceNumIndicator? "Y" : "N"));

		try {
			return new TuxedoConnector().invokeTUXservice(vo);
		}
		catch (TPReplyException tpr)
		{
	    	throw new TuxedoException(tpr, TuxedoErrorUtils.getTuxedoErrorMessage(tpr),TuxedoErrorUtils.getTuxedoErrorCode(tpr));
		}
		catch (TuxedoException te)
		{
			throw te;
		}
		catch (Exception e) {
			throw new TuxedoException(e);
		}		
	}

	
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
	public String createCorpClaim(String stationId, String portalId, long key, String ipAddress, String inputData) throws TuxedoException {
		
		new LegacyEJBAuditer().audit(TuxedoSessionEJB.class.getName(), "createCorpClaim");
		ServiceVO vo = null;
		
		logger.debug("TuxedoSessionEJB::createCorpClaim(). Invoked Bus Interf: "+ctx.getInvokedBusinessInterface().getSimpleName());
		logger.debug("TuxedoSessionEJB::createCorpClaim(). cnt: "+cnt);

		vo = new ServiceVO(StringUtils.substring(portalId, 0, 15), stationId, ipAddress, "WEBLGY", ServiceVO.CommonService.CESTROUTER);		
		vo.setExternalId(portalId);
		vo.setExternalKey(String.valueOf(key));
		vo.setData(inputData);
		try {
			return new TuxedoConnector().invokeTUXservice(vo);
		}
		catch (TPReplyException tpr)
		{
	    	throw new TuxedoException(tpr, TuxedoErrorUtils.getTuxedoErrorMessage(tpr),TuxedoErrorUtils.getTuxedoErrorCode(tpr));
		}
		catch (TuxedoException te)
		{
			throw te;
		}
		catch (Exception e) {
			throw new TuxedoException(e);		
		}
	}

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
	public String executePIFinquiry(String stationId, String portalId, long key, String ipAddress, String fileNum, boolean veteranSelf) 
		throws TuxedoException {
		new LegacyEJBAuditer().audit(TuxedoSessionEJB.class.getName(), "executePIFinquiry");
		
		ServiceVO vo = null;
		
		logger.debug("TuxedoSessionEJB::executePIFInquiry(). Invoked Bus Interf: "+ctx.getInvokedBusinessInterface().getSimpleName());
		logger.debug("TuxedoSessionEJB::executePIFInquiry(). cnt: "+cnt);
		vo = new ServiceVO(StringUtils.substring(portalId, 0, 15), stationId, ipAddress, "WEBLGY", ServiceVO.LGYService.WTCHINRDP);		
		vo.setExternalId(portalId);
		vo.setExternalKey(String.valueOf(key));
		vo.setData(fileNum.concat(veteranSelf? "Y":"N"));

		try {
			return new TuxedoConnector().invokeTUXservice(vo);
		}
		catch (TPReplyException tpr)
		{
	    	throw new TuxedoException(tpr, TuxedoErrorUtils.getTuxedoErrorMessage(tpr),TuxedoErrorUtils.getTuxedoErrorCode(tpr));
		}
		catch (TuxedoException te)
		{
			throw te;
		}
		catch (Exception e) {
			throw new TuxedoException(e);
		}
	}
	
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
	public String executeSurvivingSpouseInquiry(String stationId, String portalId, long key, String ipAddress, String fileNum) 
		throws TuxedoException {
		new LegacyEJBAuditer().audit(TuxedoSessionEJB.class.getName(), "executeSurvivingSpouseInquiry");
		
		ServiceVO vo = null;
	
		logger.debug("TuxedoSessionEJB::executeSurvivingSpouseInquiry(). Invoked Bus Interf: "+ctx.getInvokedBusinessInterface().getSimpleName());
		logger.debug("TuxedoSessionEJB::executeSurvivingSpouseInquiry(). cnt: "+cnt);
		vo = new ServiceVO(StringUtils.substring(portalId, 0, 15), stationId, ipAddress, "WEBLGY", ServiceVO.LGYService.WTCHINBDN);		
		vo.setExternalId(portalId);
		vo.setExternalKey(String.valueOf(key));
		vo.setData(fileNum);

		try {
			return new TuxedoConnector().invokeTUXservice(vo);
		}
		catch (TPReplyException tpr)
		{
	    	throw new TuxedoException(tpr, TuxedoErrorUtils.getTuxedoErrorMessage(tpr),TuxedoErrorUtils.getTuxedoErrorCode(tpr));
		}
		catch (TuxedoException te)
		{
			throw te;
		}
		catch (Exception e) {
			throw new TuxedoException(e);
		}				
	}
	
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
	public String getCorpFlashes(String stationId, String portalId, long key, String ipAddress, String fileNum) 
		throws TuxedoException {
		new LegacyEJBAuditer().audit(TuxedoSessionEJB.class.getName(), "getCorpFlashes");
		
		ServiceVO vo = null;

		logger.debug("TuxedoSessionEJB::getCorpFlashes. Invoked Bus Interf: "+ctx.getInvokedBusinessInterface().getSimpleName());
		logger.debug("TuxedoSessionEJB::getCorpFlashes(). cnt: "+cnt);
		vo = new ServiceVO(StringUtils.substring(portalId, 0, 15), stationId, ipAddress, "WEBLGY", ServiceVO.LGYService.WTCFLASH);		
		vo.setExternalId(portalId);
		vo.setExternalKey(String.valueOf(key));
		vo.setData(new StringBuilder("IFLASH").append(rightPad(fileNum, 15)).toString());

		try {
			return new TuxedoConnector().invokeTUXservice(vo);
		}
		catch (TPReplyException tpr)
		{
	    	throw new TuxedoException(tpr, TuxedoErrorUtils.getTuxedoErrorMessage(tpr),TuxedoErrorUtils.getTuxedoErrorCode(tpr));
		}
		catch (TuxedoException te)
		{
			throw te;
		}
		catch (Exception e) {
			throw new TuxedoException(e);
		}		
	}

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
	public String createCorpFlashes(String stationId, String portalId, long key, String ipAddress, String participantId, String[] flashes) 
		throws TuxedoException {
		
		new LegacyEJBAuditer().audit(TuxedoSessionEJB.class.getName(), "createCorpFlashes");
		ServiceVO vo = null;

		logger.debug("TuxedoSessionEJB::createCorpFlashes(). Invoked Bus Interf: "+ctx.getInvokedBusinessInterface().getSimpleName());
		vo = new ServiceVO(StringUtils.substring(portalId, 0, 15), stationId, ipAddress, "WEBLGY", ServiceVO.LGYService.WTCFLASH);		
		vo.setExternalId(portalId);
		vo.setExternalKey(String.valueOf(key));
		vo.setData(new StringBuilder("UFLASH").append(rightPad(participantId, 15)).
					append(rightPad(Integer.toString(flashes.length), 4)).
					append(getArrayValuesAsString(flashes, 50)).toString());

		try {
			return new TuxedoConnector().invokeTUXservice(vo);
		}
		catch (TPReplyException tpr)
		{
	    	throw new TuxedoException(tpr, TuxedoErrorUtils.getTuxedoErrorMessage(tpr),TuxedoErrorUtils.getTuxedoErrorCode(tpr));
		}
		catch (TuxedoException te)
		{
			throw te;
		}
		catch (Exception e) {
			throw new TuxedoException(e);
		}
	}
	
	/**
	 * Executes the WTCSNTVTY Tuxedo service to retrieve the sensitivity level of a participant's record
	 * 
	 * @return	
	 * @throws 	TuxedoException	
	 * @since	October 18, 2008 
   	 */
	public String getSensitiveLevel(String stationId, String portalId, long key, String ipAddress, String ssn, ParticipantType participant)  
		throws TuxedoException {

		new LegacyEJBAuditer().audit(TuxedoSessionEJB.class.getName(), "getgSensitiveLevel");
		ServiceVO vo = null;

		logger.debug("TuxedoSessionEJB::getSensitiveLevel. Invoked Bus Interf: "+ctx.getInvokedBusinessInterface().getSimpleName());
		vo = new ServiceVO(StringUtils.substring(portalId, 0, 15), stationId, ipAddress, "WEBLGY", ServiceVO.LGYService.WTCSNTVTY);		
		vo.setExternalId(portalId);
		vo.setExternalKey(String.valueOf(key));
		vo.setData(new StringBuilder(ssn).append(participant.getType()).toString());
		//vo.setData(new StringBuilder(ssn).append(participant==TuxedoService.USER? "U":"V").toString());
		try {
			return new TuxedoConnector().invokeTUXservice(vo);
		}
		catch (TPReplyException tpr)
		{
	    	throw new TuxedoException(tpr, TuxedoErrorUtils.getTuxedoErrorMessage(tpr),TuxedoErrorUtils.getTuxedoErrorCode(tpr));
		}
		catch (TuxedoException te)
		{
			throw te;
		}
		catch (Exception e) {
			throw new TuxedoException(e);
		}	
	}

	/**
	 * Retrieves veteran data from BIRLS. Calls TPTOCICS internally to access Austin IDMS db's for BIRLS, COVERS, etc.
	 * 
	 * @return	
	 * @throws 	TuxedoException	
	 * @since	February 18, 2009
	 * @see gov.va.vba.framework.services.TuxedoService#getVeteranBIRLSData(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 * TODO: fix null appl name
	 */
	public String getVeteranBIRLSData(String userId, String stationId, String ipAddress, String inputData) throws TuxedoException {
		
		new LegacyEJBAuditer().audit(TuxedoSessionEJB.class.getName(), "getVeteranBIRLSData");
		ServiceVO vo = null;   
		logger.debug("TuxedoSessionEJB::getBIRLSVeteranData(). Invoked Bus Interf: "+ctx.getInvokedBusinessInterface().getSimpleName());
		vo = new ServiceVO(userId, stationId, ipAddress, null, ServiceVO.SHAREService.VAAUSIBM);		
		//? TRANS-TYPE - I?
		//vo.setData(new StringBuilder("SHARI").append("BPNQ").append(inputData).toString());
		vo.setData(inputData);
		try {
			//EJBRefTest e = new EJBRefTest(); //for testing: calling ejb from POJO using "@EJB(name="ejb/SecurityService", beanInterface=CommonSecurityService.class)"
			return new TuxedoConnector().execute(vo);
		}
		catch (TuxedoConnectorException tce)
		{
	    	throw new TuxedoException(tce, tce.getTuxedoErrorMessage(),tce.getTuxedoErrorCode());
		}
		catch (Exception e) {
			throw new TuxedoException(e);
		}		
	}
	
	/** 
	 * Convenience method for COVERS/FTS based on management's request for a quick fix.
	 * 
	 * @see gov.va.vba.framework.services.TuxedoService#getVeteranBIRLSsensitiveData(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public String getVeteranBIRLSsensitiveData(String userId, String stationId, String ipAddress, String inputData) throws TuxedoException {

		new LegacyEJBAuditer().audit(TuxedoSessionEJB.class.getName(), "getVeteranBIRLSSensitiveLevel");
		ServiceVO vo = null;   
		logger.debug("TuxedoSessionEJB::getVeteranBIRLSsensitiveData(). Invoked Bus Interf: "+ctx.getInvokedBusinessInterface().getSimpleName());
		vo = new ServiceVO(userId, stationId, ipAddress, null, ServiceVO.CommonService.TPTOCICS);		
		vo.setData(inputData);
		try {
			return new TuxedoConnector().execute(vo);
		}
		catch (TuxedoConnectorException tce)
		{
	    	throw new TuxedoException(tce, tce.getTuxedoErrorMessage(),tce.getTuxedoErrorCode());
		}
		catch (TuxedoException te)
		{
			throw te;
		}
		catch (Exception e) {
			throw new TuxedoException(e);
		}		
	}
	
	/**
	 * Executes the SHRINQ3 Tuxedo service to retrieve a veteran's dependents data from the Corporate Db.
	 *  
	 * @return	
	 * @throws 	TuxedoException	
	 * @since	February 18, 2009
	 * @see gov.va.vba.framework.services.TuxedoService#findDependents(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 * TODO: fix null appl name
	 */
	public String findDependents(String userId, String stationId, String ipAddress, String veteranId, String claimantId) throws TuxedoException {
		
		new LegacyEJBAuditer().audit(TuxedoSessionEJB.class.getName(), "findDependants");
		ServiceVO vo = null;
		logger.debug("TuxedoSessionEJB::findDependents(). Invoked Bus Interf: "+ctx.getInvokedBusinessInterface().getSimpleName());
		vo = new ServiceVO(userId, stationId, ipAddress, null, ServiceVO.SHAREService.SHRINQ3);		
		vo.setData(new StringBuilder("CEST").append(rightPad(veteranId, 15)).append(rightPad(claimantId, 15)).toString());
		try {
			return new TuxedoConnector().execute(vo);
		}
		catch (TuxedoConnectorException tce)
		{
	    	throw new TuxedoException(tce, tce.getTuxedoErrorMessage(),tce.getTuxedoErrorCode());
		}
		catch (TuxedoException te)
		{
			throw te;
		}
		catch (Exception e) {
			throw new TuxedoException(e);
		}		
	}
	
	/**
	 * Executes the CESTROUTER Tuxedo service to create a veteran in the Corporate Db. CESTROUTER in most cases does validation on Corp, BIRLS, BDN, etc.
	 * and then a BUPD
	 * 
	 * @return	
	 * @throws 	TuxedoException	
	 * @since	February 18, 2009 
	 * @see gov.va.vba.framework.services.TuxedoService#createVeteran(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public String createVeteran(String userId, String stationId, String ipAddress, String inputData) throws TuxedoException {
		
		new LegacyEJBAuditer().audit(TuxedoSessionEJB.class.getName(), "createVeteran");
		ServiceVO vo = null;
	
		logger.debug("TuxedoSessionEJB::createVeteran(). Invoked Bus Interf: "+ctx.getInvokedBusinessInterface().getSimpleName());
		vo = new ServiceVO(userId, stationId, ipAddress, null, ServiceVO.CommonService.CESTROUTER);		
		vo.setData(new StringBuilder("BUPD").append(rightPad("11", 5)).append(inputData.substring(9)).toString());
		try {
			return new TuxedoConnector().execute(vo);
		}
		catch (TuxedoConnectorException tce)
		{
	    	throw new TuxedoException(tce, tce.getTuxedoErrorMessage(),tce.getTuxedoErrorCode());
		}
		catch (TuxedoException te)
		{
			throw te;
		}
		catch (Exception e) {
			throw new TuxedoException(e);
		}		
	}
	
	/**
	 * Executes the CESTROUTER Tuxedo service to create a veteran's dependents in the Corporate Db. CESTROUTER performs a CDEP transaction
	 *  
	 * @return	
	 * @throws 	TuxedoException	
	 * @since	February 18, 2009 
	 * @see gov.va.vba.framework.services.TuxedoService#createDependents(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public String createDependents(String userId, String stationId, String ipAddress, String inputData) throws TuxedoException {
		
		new LegacyEJBAuditer().audit(TuxedoSessionEJB.class.getName(), "createDependents");
		ServiceVO vo = null;
	
		vo = new ServiceVO(userId, stationId, ipAddress, null, ServiceVO.CommonService.CESTROUTER);		
		vo.setData(new StringBuilder("CDEP").append(inputData.substring(4)).toString());
		try {
			return new TuxedoConnector().execute(vo);
		}
		catch (TuxedoConnectorException tce)
		{
	    	throw new TuxedoException(tce, tce.getTuxedoErrorMessage(),tce.getTuxedoErrorCode());
		}
		catch (TuxedoException te)
		{
			throw te;
		}
		catch (Exception e) {
			throw new TuxedoException(e);
		}		
	}

	/**
	 * Sends requests to BDN via TPTODMIV to process payment information.
	 */
	public String processBDNPayments(String userId, String stationId, String ipAddress, String inputData) throws TuxedoException {
		
		new LegacyEJBAuditer().audit(TuxedoSessionEJB.class.getName(), "processBDNPayments");
		ServiceVO vo = null;
				
		vo = new ServiceVO(userId, stationId, ipAddress, null, ServiceVO.CommonService.TPTODMIV);		
		vo.setData(inputData);
		try {
			return new TuxedoConnector().execute(vo);
		}
		catch (TuxedoConnectorException tce)
		{
	    	throw new TuxedoException(tce, tce.getTuxedoErrorMessage(),tce.getTuxedoErrorCode());
		}
		catch (TuxedoException te)
		{
			throw te;
		}
		catch (Exception e) {
			throw new TuxedoException(e);
		}				
	}
	
	/**
	 * A SHARE service that executes the SHRINQ3 Tuxedo service and returns a claimant's payment history. Used by eBenefits C&P Claims.
	 * 
	 * 
	 */
	public String getClaimantPaymentHistory(String portalId, String key, String stationId, String ipAddress, String inputData) throws TuxedoException {
		
		new LegacyEJBAuditer().audit(TuxedoSessionEJB.class.getName(), "getClaimantPaymentHistory");
		ServiceVO vo = null;
	
		vo = new ServiceVO(StringUtils.substring(portalId, 0, 15), stationId, ipAddress, "CLMSTATUS", ServiceVO.SHAREService.SHRINQ4);		
		vo.setExternalId(portalId);
		vo.setExternalKey(String.valueOf(key));		
		vo.setData(rightPad(new StringBuilder("TINQ").append(inputData).toString(), 84));
		try {
			return new TuxedoConnector().invokeTUXservice(vo);
		}
		catch (TPReplyException tpr)
		{
	    	throw new TuxedoException(tpr, TuxedoErrorUtils.getTuxedoErrorMessage(tpr), TuxedoErrorUtils.getTuxedoErrorCode(tpr));
		}
		catch (TuxedoException te)
		{
			throw te;
		}
		catch (Exception e) {
			throw new TuxedoException(e);
		}				
	}

	
	/**
	 * Pads the String <code>str</code> with <code>size</code> spaces. Apache's StringUtils
	 * does not truncate the input string if it's smaller than the size.
	 * 
	 * @param 	str
	 * @param 	size
	 * @return	
	 * @throws	
	 * @return	
	 * @since	Jul 22, 2008
	 */
	private String rightPad(String str, int size) {
		
    	StringBuilder strBldr = new StringBuilder(str==null? "":str);
    	for (int i=strBldr.length(); i<size;i++) 
    		strBldr.append('\u0020');
    	return strBldr.substring(0, size);
	}
		
	/**
	 * Traverses an array and pads each element with spaces of the desired 
	 * <code>size</code>.
	 * 
	 * @param 	array
	 * @param 	padSize. The number of spaces to append to each element. If the
	 * 			length of the element happens to be > <code>size</code>, it is 
	 * 			truncated to size <code>size</code>
	 * @return	
	 * @throws	
	 * @return	
	 * @since	Jul 24, 2008
	 */
	private String getArrayValuesAsString(String[] array, int padSize) {
		
		StringBuilder st = new StringBuilder();
		for (int i = 0; i < array.length; i++) {
			st.append(array[i]==null?"":array[i]);			
			for (int j=(array[i]==null?"":array[i]).length(); j<padSize;j++) 
				st.append('\u0020');
		}
		return st.toString();
	}
	
	/**
	 * 
	 * @param str
	 * @return
	 * @throws Exception	
	 * @throws	
	 * @return	
	 * @since	May 30, 2008
	 */
	public String echo(String echoStr) throws Exception {
		
		String s = "EJB output: Arg passed in is: "+echoStr;
		logger.debug(s);
		return s;
	}
	
	@PostConstruct
	public void postConstruct() {
		constructed = "constructed";
	}

	/**
	 * 
	 * @see gov.va.vba.framework.services.TuxedoService#execute(gov.va.vba.framework.domain.vo.ServiceVO.TuxedoService, weblogic.wtc.jatmi.TypedFML32)
	 */
	@Override
	public TypedBuffer execute(String serviceName, TypedBuffer buffer) throws TuxedoException {

		new LegacyEJBAuditer().audit(TuxedoSessionEJB.class.getName(), "execute(serviceName, buffer");
		logger.debug("TuxedoSessionEJB::execute(). Invoked Bus Interf: "+ctx.getInvokedBusinessInterface().getSimpleName());
		logger.debug("TuxedoSessionEJB: Tuxedo Connector execute - "+serviceName+" by "+this.ctx.getCallerPrincipal().getName());
		try {
			return new TuxedoConnector().execute(serviceName, buffer);
		}
		catch (TuxedoConnectorException tce)
		{
	    	throw new TuxedoException(tce, tce.getTuxedoErrorMessage(),tce.getTuxedoErrorCode());
		}
		catch (TuxedoException te)
		{
			throw te;
		}
		catch (Exception e) {
			throw new TuxedoException(e);
		}		
	}

	@Override
	public TypedBuffer execute(String serviceName, TypedBuffer buffer,
			boolean transaction) throws TuxedoException {
		new LegacyEJBAuditer().audit(TuxedoSessionEJB.class.getName(), "execute(serviceName, buffer, transaction)");
		logger.debug("TuxedoSessionEJB::execute(). Invoked Bus Interf: "+ctx.getInvokedBusinessInterface().getSimpleName());
		logger.debug("TuxedoSessionEJB: Tuxedo Connector execute - "+serviceName+" by "+this.ctx.getCallerPrincipal().getName());
		try {
			return new TuxedoConnector().execute(serviceName, buffer, transaction);
		}
		catch (TuxedoConnectorException tce)
		{
	    	throw new TuxedoException(tce, tce.getTuxedoErrorMessage(),tce.getTuxedoErrorCode());
		}
		catch (TuxedoException te)
		{
			throw te;
		}
		catch (Exception e) {
			throw new TuxedoException(e);
		}		
	}

	/**
	 * 
	 
	@Override
	public String executeSHRINQM(String userId, String stationId, String ipAddress, String applName, String inputData) throws TuxedoException {

		System.out.println("TuxedoSessionEJB::execute(). Invoked Bus Interf: "+ctx.getInvokedBusinessInterface().getSimpleName());
		ServiceVO vo = new ServiceVO(userId, stationId, ipAddress, applName, ServiceVO.SHAREService.SHRINQM);
		vo.setData(inputData);
		try {
			return new TuxedoConnector().execute(vo);
		}
		catch (Exception e) {
			throw new TuxedoException(e);
		}		
	}
	*/
}

