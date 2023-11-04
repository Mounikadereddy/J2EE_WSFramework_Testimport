/*
 * TuxedoSessionEJB.java
 *
 * Copyright 2007 U.S. Dept Of Veterans Affairs.
 * U.S. Government PROPRIETARY/CONFIDENTIAL. Use is subject to security terms.
 */
package gov.va.vba.framework.services.ejb;

import gov.va.vba.framework.auditing.AuditedTuxedoService;
import gov.va.vba.framework.common.AuditContext;
import gov.va.vba.framework.common.TuxedoErrorUtils;
import gov.va.vba.framework.domain.vo.ParticipantType;
import gov.va.vba.framework.domain.vo.ServiceVO;
import gov.va.vba.framework.esb.connectors.client.ExternalUserFMLFieldTable;
import gov.va.vba.framework.esb.connectors.client.TuxedoConnector;
import gov.va.vba.framework.esb.connectors.client.TuxedoConnectorException;
import gov.va.vba.framework.logging.Logger;
import gov.va.vba.framework.services.CommonSecurityService;
import gov.va.vba.framework.services.GenericService;
import gov.va.vba.framework.services.TuxedoException;
import gov.va.vba.framework.services.TuxedoServiceRemoteV2;
import gov.va.vba.framework.services.TuxedoServiceV2;

import java.util.Map;

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
import weblogic.wtc.jatmi.Ferror;
import weblogic.wtc.jatmi.TPReplyException;
import weblogic.wtc.jatmi.TypedBuffer;
import weblogic.wtc.jatmi.TypedFML;


/**
 * Main TuxedoService EJB that handles execution of all service requests to underlying
 * TuxedoService services. 
 *  
 * @since	Dec 9, 2010
 * @version 2	
 * @author	Joshua Glickman
 */

@Stateless(name="TuxedoServiceV2", mappedName="VbaTuxedoServiceV2")
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@Local(TuxedoServiceV2.class)
@Remote(TuxedoServiceRemoteV2.class)
@CallByReference
@TransactionTimeoutSeconds(3600)
@EJB(name="ejb/SecurityServiceV2", beanInterface=CommonSecurityService.class)
public class TuxedoSessionEJBV2 extends GenericService implements TuxedoServiceV2 {
	
	@Resource
	private String constructed = null;	
	@Resource 
	private SessionContext ctx;
	private int cnt;
	private static Logger logger=Logger.getLogger(TuxedoSessionEJBV2.class);
	
	/**
	 * Default constructor - per EJB 3.0 spec.
	 * 
	 * @since	Oct 14, 2008
	 * @version
	 */
	public TuxedoSessionEJBV2() {
		   	
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
	public String execute(ServiceVO serviceVo, AuditContext auditContext, Map extensions) throws TuxedoException {
	
		logger.debug("TuxedoSessionEJBV2::execute(). Invoked Bus Interf: "+ctx.getInvokedBusinessInterface().getSimpleName());
		try {
			return new TuxedoConnector().invokeTUXservice(serviceVo);
		}
		catch (TPReplyException tpr)
		{
			handleTuxedoErrors(tpr, auditContext);
	    	throw new TuxedoException(tpr, TuxedoErrorUtils.getTuxedoErrorMessage(tpr), TuxedoErrorUtils.getTuxedoErrorCode(tpr));
		}
		catch (TuxedoException te)
		{
			handleTuxedoErrors(te, auditContext);
			throw te;
		}
		catch (Exception e) {
			handleTuxedoErrors(e, auditContext);
			throw new TuxedoException(e);
		}		
	}

	
	/**
	 * Executes the WTCCTRLHINQ Tuxedo service to retreive veteran data
	 * 
	 * @param portalId 
	 * @param key
	 * @param ipAddress
	 * @param fileNum
	 * @param veteranSelf
	 * @param serviceNumIndicator
	 * @return String
	 * @throws Exception	
	 * @since	May 27, 2008
	 */
	@AuditedTuxedoService(name="WTCCTRLHINQ")
	public String getVeteranData(String portalId, long key, String fileNum, boolean veteranSelf, boolean serviceNumIndicator, AuditContext auditContext, Map extensions) throws TuxedoException {
		
		ServiceVO vo = null;
	
		logger.debug("TuxedoSessionEJBV2::getVeteranData(). Invoked Bus Interf: "+ctx.getInvokedBusinessInterface().getSimpleName());
		vo = new ServiceVO(StringUtils.substring(portalId, 0, 15), auditContext.getStationID(), auditContext.getClientIPAddress(), "WEBLGY", ServiceVO.LGYService.WTCCTRLHINQ);		
		vo.setExternalId(portalId);
		vo.setExternalKey(String.valueOf(key));
		vo.setData(fileNum+(veteranSelf? "Y" : "N") + (serviceNumIndicator? "Y" : "N"));

		try {
			return new TuxedoConnector().invokeTUXservice(vo);
		}
		catch (TPReplyException tpr)
		{
			handleTuxedoErrors(tpr, auditContext);
	    	throw new TuxedoException(tpr, TuxedoErrorUtils.getTuxedoErrorMessage(tpr), TuxedoErrorUtils.getTuxedoErrorCode(tpr));
		}
		catch (TuxedoException te)
		{
			handleTuxedoErrors(te, auditContext);
			throw te;
		}
		catch (Exception e) {
			handleTuxedoErrors(e, auditContext);
			throw new TuxedoException(e);
		}		
	}

	
	/**
	 * Executes the CESTROUTER Tuxedo service to create a claim
	 * 
	 */
	@AuditedTuxedoService(name="CESTROUTER")
	public String createCorpClaim(String stationId, String portalId, long key, String inputData, AuditContext auditContext, Map extensions) throws TuxedoException {
		
		ServiceVO vo = null;
		
		logger.debug("inputdata="+inputData);
		logger.debug("TuxedoSessionEJBV2::createCorpClaim(). Invoked Bus Interf: "+ctx.getInvokedBusinessInterface().getSimpleName());
		logger.debug("TuxedoSessionEJBV2::createCorpClaim(). cnt: "+cnt);

		vo = new ServiceVO(StringUtils.substring(portalId, 0, 15), stationId, auditContext.getClientIPAddress(), "WEBLGY", ServiceVO.CommonService.CESTROUTER);		
		vo.setExternalId(portalId);
		vo.setExternalKey(String.valueOf(key));
		vo.setData(inputData);
		try {
			return new TuxedoConnector().invokeTUXservice(vo);
		}
		catch (TPReplyException tpr)
		{
			handleTuxedoErrors(tpr, auditContext);
	    	throw new TuxedoException(tpr, TuxedoErrorUtils.getTuxedoErrorMessage(tpr), TuxedoErrorUtils.getTuxedoErrorCode(tpr));
		}
		catch (TuxedoException te)
		{
			handleTuxedoErrors(te, auditContext);
			throw te;
		}
		catch (Exception e) {
			handleTuxedoErrors(e, auditContext);
			throw new TuxedoException(e);		
		}
	}

	/**
	 * Executes the WTCHINBDN Tuxedo service to retrieve a beneficiary record
	 */ 
	@AuditedTuxedoService(name="WTCHINRDP")
	public String executePIFinquiry(String portalId, long key, String fileNum, boolean veteranSelf, AuditContext auditContext, Map extensions) 
		throws TuxedoException {
		
		ServiceVO vo = null;
		
		logger.debug("TuxedoSessionEJBV2::executePIFInquiry(). Invoked Bus Interf: "+ctx.getInvokedBusinessInterface().getSimpleName());
		logger.debug("TuxedoSessionEJBV2::executePIFInquiry(). cnt: "+cnt);
		vo = new ServiceVO(StringUtils.substring(portalId, 0, 15), auditContext.getStationID(), auditContext.getClientIPAddress(), "WEBLGY", ServiceVO.LGYService.WTCHINRDP);		
		vo.setExternalId(portalId);
		vo.setExternalKey(String.valueOf(key));
		vo.setData(fileNum.concat((veteranSelf? "Y":"N")));

		try {
			return new TuxedoConnector().invokeTUXservice(vo);
		}
		catch (TPReplyException tpr)
		{
			handleTuxedoErrors(tpr, auditContext);
	    	throw new TuxedoException(tpr, TuxedoErrorUtils.getTuxedoErrorMessage(tpr), TuxedoErrorUtils.getTuxedoErrorCode(tpr));
		}
		catch (TuxedoException te)
		{
			handleTuxedoErrors(te, auditContext);
			throw te;
		}
		catch (Exception e) {
			handleTuxedoErrors(e, auditContext);
			throw new TuxedoException(e);
		}
	}
	
	/**
	 * Executes the WTCHINBDN Tuxedo service to retrieve a beneficiary record
	 * 
	 */
	@AuditedTuxedoService(name="WTCHINBDN")
	public String executeSurvivingSpouseInquiry(String portalId, long key, String fileNum, AuditContext auditContext, Map extensions) 
		throws TuxedoException {
		
		ServiceVO vo = null;
	
		logger.debug("TuxedoSessionEJBV2::executeSurvivingSpouseInquiry(). Invoked Bus Interf: "+ctx.getInvokedBusinessInterface().getSimpleName());
		logger.debug("TuxedoSessionEJBV2::executeSurvivingSpouseInquiry(). cnt: "+cnt);
		vo = new ServiceVO(StringUtils.substring(portalId, 0, 15), auditContext.getStationID(), auditContext.getClientIPAddress(), "WEBLGY", ServiceVO.LGYService.WTCHINBDN);		
		vo.setExternalId(portalId);
		vo.setExternalKey(String.valueOf(key));
		vo.setData(fileNum);

		try {
			return new TuxedoConnector().invokeTUXservice(vo);
		}
		catch (TPReplyException tpr)
		{
			handleTuxedoErrors(tpr, auditContext);
	    	throw new TuxedoException(tpr, TuxedoErrorUtils.getTuxedoErrorMessage(tpr), TuxedoErrorUtils.getTuxedoErrorCode(tpr));
		}
		catch (TuxedoException te)
		{
			handleTuxedoErrors(te, auditContext);
			throw te;
		}
		catch (Exception e) {
			handleTuxedoErrors(e, auditContext);
			throw new TuxedoException(e);
		}				
	}
	
	/**
	 * Executes the WTCFLASH Tuxedo service to retrieve flash records from corp db
	 * 
	 */
	@AuditedTuxedoService(name="WTCFLASH")
	public String getCorpFlashes(String portalId, long key, String fileNum, AuditContext auditContext, Map extensions) 
		throws TuxedoException {
		
		ServiceVO vo = null;

		logger.debug("TuxedoSessionEJBV2::getCorpFlashes. Invoked Bus Interf: "+ctx.getInvokedBusinessInterface().getSimpleName());
		logger.debug("TuxedoSessionEJBV2::getCorpFlashes(). cnt: "+cnt);
		vo = new ServiceVO(StringUtils.substring(portalId, 0, 15), auditContext.getStationID(), auditContext.getClientIPAddress(), "WEBLGY", ServiceVO.LGYService.WTCFLASH);		
		vo.setExternalId(portalId);
		vo.setExternalKey(String.valueOf(key));
		vo.setData(new StringBuilder("IFLASH").append(gov.va.vba.framework.common.StringUtils.rightPadSubstr(fileNum, 15)).toString());

		try {
			return new TuxedoConnector().invokeTUXservice(vo);
		}
		catch (TPReplyException tpr)
		{
			handleTuxedoErrors(tpr, auditContext);
	    	throw new TuxedoException(tpr, TuxedoErrorUtils.getTuxedoErrorMessage(tpr), TuxedoErrorUtils.getTuxedoErrorCode(tpr));
		}
		catch (TuxedoException te)
		{
			handleTuxedoErrors(te, auditContext);
			throw te;
		}
		catch (Exception e) {
			handleTuxedoErrors(e, auditContext);
			throw new TuxedoException(e);
		}		
	}

	/**
	 * Executes the WTCFLASH Tuxedo service to create/update flash records in corp db
	 * 
	 */
	@AuditedTuxedoService(name="WTCFLASH")
	public String createCorpFlashes(String portalId, long key, String participantId, String[] flashes, AuditContext auditContext, Map extensions) 
		throws TuxedoException{
		
		ServiceVO vo = null;

		logger.debug("TuxedoSessionEJBV2::createCorpFlashes(). Invoked Bus Interf: "+ctx.getInvokedBusinessInterface().getSimpleName());
		vo = new ServiceVO(StringUtils.substring(portalId, 0, 15), auditContext.getStationID(), auditContext.getClientIPAddress(), "WEBLGY", ServiceVO.LGYService.WTCFLASH);		
		vo.setExternalId(portalId);
		vo.setExternalKey(String.valueOf(key));
		vo.setData(new StringBuilder("UFLASH").append(gov.va.vba.framework.common.StringUtils.rightPadSubstr(participantId, 15)).
					append(gov.va.vba.framework.common.StringUtils.rightPadSubstr(Integer.toString(flashes.length), 4)).
					append(getArrayValuesAsString(flashes, 50)).toString());

		try {
			return new TuxedoConnector().invokeTUXservice(vo);
		}
		catch (TPReplyException tpr)
		{
			handleTuxedoErrors(tpr, auditContext);
	    	throw new TuxedoException(tpr, TuxedoErrorUtils.getTuxedoErrorMessage(tpr), TuxedoErrorUtils.getTuxedoErrorCode(tpr));
		}
		catch (TuxedoException te)
		{
			handleTuxedoErrors(te, auditContext);
			throw te;
		}
		catch (Exception e) {
			handleTuxedoErrors(e, auditContext);
			throw new TuxedoException(e);
		}
	}
	
	/**
	 * Executes the WTCSNTVTY Tuxedo service to retrieve the sensitivity level of a participant's record
	 * 
   	 */
	@AuditedTuxedoService(name="WTCSNTVTY")
	public String getSensitiveLevel(String portalId, long key, String ssn, ParticipantType participant, AuditContext auditContext, Map extensions)  
		throws TuxedoException{

		ServiceVO vo = null;

		logger.debug("TuxedoSessionEJBV2::getSensitiveLevel. Invoked Bus Interf: "+ctx.getInvokedBusinessInterface().getSimpleName());
		vo = new ServiceVO(StringUtils.substring(portalId, 0, 15), auditContext.getStationID(), auditContext.getClientIPAddress(), "WEBLGY", ServiceVO.LGYService.WTCSNTVTY);		
		vo.setExternalId(portalId);
		vo.setExternalKey(String.valueOf(key));
		vo.setData(new StringBuilder(ssn).append(participant.getType()).toString());
		//vo.setData(new StringBuilder(ssn).append(participant==TuxedoService.USER? "U":"V").toString());
		try {
			return new TuxedoConnector().invokeTUXservice(vo);
		}
		catch (TPReplyException tpr)
		{
			handleTuxedoErrors(tpr, auditContext);
	    	throw new TuxedoException(tpr, TuxedoErrorUtils.getTuxedoErrorMessage(tpr), TuxedoErrorUtils.getTuxedoErrorCode(tpr));
		}
		catch (TuxedoException te)
		{
			handleTuxedoErrors(te, auditContext);
			throw te;
		}
		catch (Exception e) {
			handleTuxedoErrors(e, auditContext);
			throw new TuxedoException(e);
		}	
	}

	/**
	 * Retrieves veteran data from BIRLS. Calls TPTOCICS internally to access Austin IDMS db's for BIRLS, COVERS, etc.
	 * 
	 * TODO: fix null appl name
	 */
	@AuditedTuxedoService(name="VAAUSIBM")
	public String getVeteranBIRLSData(String inputData, AuditContext auditContext, Map extensions) throws TuxedoException {
		
		ServiceVO vo = null;   
		logger.debug("inputdata="+inputData);
		logger.debug("TuxedoSessionEJBV2::getBIRLSVeteranData(). Invoked Bus Interf: "+ctx.getInvokedBusinessInterface().getSimpleName());
		vo = new ServiceVO(auditContext.getUserId(), auditContext.getStationID(), auditContext.getClientIPAddress(), null, ServiceVO.SHAREService.VAAUSIBM);		
		//? TRANS-TYPE - I?
		//vo.setData(new StringBuilder("SHARI").append("BPNQ").append(inputData).toString());
		vo.setData(inputData);
		try {
			//EJBRefTest e = new EJBRefTest(); //for testing: calling ejb from POJO using "@EJB(name="ejb/SecurityService", beanInterface=CommonSecurityService.class)"
			return new TuxedoConnector().execute(vo);
		}
		catch (TuxedoConnectorException tce)
		{
			handleTuxedoErrors(tce, auditContext);
	    	throw new TuxedoException(tce, tce.getTuxedoErrorMessage(),tce.getTuxedoErrorCode());
		}
		catch (TuxedoException te)
		{
			handleTuxedoErrors(te, auditContext);
			throw te;
		}
		catch (Exception e) {
			handleTuxedoErrors(e, auditContext);
			throw new TuxedoException(e);
		}		
	}
	
	/** 
	 * Convenience method for COVERS/FTS based on management's request for a quick fix.
	 * 
	 */
	@Override
	@AuditedTuxedoService(name="TPTOCICS")
	public String getVeteranBIRLSsensitiveData(String inputData, AuditContext auditContext, Map extensions) throws TuxedoException {

		ServiceVO vo = null;   
		logger.debug("inputdata="+inputData);
		logger.debug("TuxedoSessionEJBV2::getVeteranBIRLSsensitiveData(). Invoked Bus Interf: "+ctx.getInvokedBusinessInterface().getSimpleName());
		vo = new ServiceVO(auditContext.getUserId(), auditContext.getStationID(), auditContext.getClientIPAddress(), null, ServiceVO.CommonService.TPTOCICS);		
		vo.setData(inputData);
		try {
			return new TuxedoConnector().execute(vo);
		}
		catch (TuxedoConnectorException tce)
		{
			handleTuxedoErrors(tce, auditContext);
	    	throw new TuxedoException(tce, tce.getTuxedoErrorMessage(),tce.getTuxedoErrorCode());
		}
		catch (TuxedoException te)
		{
			handleTuxedoErrors(te, auditContext);
			throw te;
		}
		catch (Exception e) {
			handleTuxedoErrors(e, auditContext);
			throw new TuxedoException(e);
		}		
	}
	
	/**
	 * Executes the SHRINQ3 Tuxedo service to retrieve a veteran's dependents data from the Corporate Db.
	 *  
	 * TODO: fix null appl name
	 */
	@AuditedTuxedoService(name="SHRINQ3")
	public String findDependents(String veteranId, String claimantId, AuditContext auditContext, Map extensions) throws TuxedoException {
		
		ServiceVO vo = null;
		logger.debug("TuxedoSessionEJBV2::findDependents(). Invoked Bus Interf: "+ctx.getInvokedBusinessInterface().getSimpleName());
		vo = new ServiceVO(auditContext.getUserId(), auditContext.getStationID(), auditContext.getClientIPAddress(), null, ServiceVO.SHAREService.SHRINQ3);		
		vo.setData(new StringBuilder("CEST").append(gov.va.vba.framework.common.StringUtils.rightPadSubstr(veteranId, 15)).append(gov.va.vba.framework.common.StringUtils.rightPadSubstr(claimantId, 15)).toString());
		try {
			return new TuxedoConnector().execute(vo);
		}
		catch (TuxedoConnectorException tce)
		{
			handleTuxedoErrors(tce, auditContext);
	    	throw new TuxedoException(tce, tce.getTuxedoErrorMessage(),tce.getTuxedoErrorCode());
		}
		catch (TuxedoException te)
		{
			handleTuxedoErrors(te, auditContext);
			throw te;
		}
		catch (Exception e) {
			handleTuxedoErrors(e, auditContext);
			throw new TuxedoException(e);
		}		
	}
	
	/**
	 * Executes the CESTROUTER Tuxedo service to create a veteran in the Corporate Db. CESTROUTER in most cases does validation on Corp, BIRLS, BDN, etc.
	 * and then a BUPD
	 * 
	 */
	@AuditedTuxedoService(name="CESTROUTER")
	public String createVeteran(String inputData, AuditContext auditContext, Map extensions) throws TuxedoException{
		
		ServiceVO vo = null;
	
		logger.debug("inputdata="+inputData);
		logger.debug("TuxedoSessionEJBV2::createVeteran(). Invoked Bus Interf: "+ctx.getInvokedBusinessInterface().getSimpleName());
		vo = new ServiceVO(auditContext.getUserId(), auditContext.getStationID(), auditContext.getClientIPAddress(), null, ServiceVO.CommonService.CESTROUTER);		
		vo.setData(new StringBuilder("BUPD").append(gov.va.vba.framework.common.StringUtils.rightPadSubstr("11", 5)).append(inputData.substring(9)).toString());
		try {
			return new TuxedoConnector().execute(vo);
		}
		catch (TuxedoConnectorException tce)
		{
			handleTuxedoErrors(tce, auditContext);
	    	throw new TuxedoException(tce, tce.getTuxedoErrorMessage(),tce.getTuxedoErrorCode());
		}
		catch (TuxedoException te)
		{
			handleTuxedoErrors(te, auditContext);
			throw te;
		}
		catch (Exception e) {
			handleTuxedoErrors(e, auditContext);
			throw new TuxedoException(e);
		}		
	}
	
	/**
	 * Executes the CESTROUTER Tuxedo service to create a veteran's dependents in the Corporate Db. CESTROUTER performs a CDEP transaction
	 *  
	 */
	@AuditedTuxedoService(name="CESTROUTER")
	public String createDependents(String inputData, AuditContext auditContext, Map extensions) throws TuxedoException {
		
		logger.debug("inputdata="+inputData);
		ServiceVO vo = null;
	
		vo = new ServiceVO(auditContext.getUserId(), auditContext.getStationID(), auditContext.getClientIPAddress(), null, ServiceVO.CommonService.CESTROUTER);		
		vo.setData(new StringBuilder("CDEP").append(inputData.substring(4)).toString());
		try {
			return new TuxedoConnector().execute(vo);
		}
		catch (TuxedoConnectorException tce)
		{
			handleTuxedoErrors(tce, auditContext);
	    	throw new TuxedoException(tce, tce.getTuxedoErrorMessage(),tce.getTuxedoErrorCode());
		}
		catch (TuxedoException te)
		{
			handleTuxedoErrors(te, auditContext);
			throw te;
		}
		catch (Exception e) {
			handleTuxedoErrors(e, auditContext);
			throw new TuxedoException(e);
		}		
	}

	/**
	 * Sends requests to BDN via TPTODMIV to process payment information.
	 */
	@AuditedTuxedoService(name="TPTODMIV")
	public String processBDNPayments(String inputData, AuditContext auditContext, Map extensions) throws TuxedoException {
		
		logger.debug("inputdata="+inputData);
		ServiceVO vo = null;
				
		vo = new ServiceVO(auditContext.getUserId(), auditContext.getStationID(), auditContext.getClientIPAddress(), null, ServiceVO.CommonService.TPTODMIV);		
		vo.setData(inputData);
		try {
			return new TuxedoConnector().execute(vo);
		}
		catch (TuxedoConnectorException tce)
		{
			handleTuxedoErrors(tce, auditContext);
	    	throw new TuxedoException(tce, tce.getTuxedoErrorMessage(),tce.getTuxedoErrorCode());
		}
		catch (TuxedoException te)
		{
			handleTuxedoErrors(te, auditContext);
			throw te;
		}
		catch (Exception e) {
			handleTuxedoErrors(e, auditContext);
			throw new TuxedoException(e);
		}				
	}
	
	/**
	 * A SHARE service that executes the SHRINQ3 Tuxedo service and returns a claimant's payment history. Used by eBenefits C&P Claims.
	 */
	@AuditedTuxedoService(name="SHRINQ4")
	public String getClaimantPaymentHistory(String portalId, String key, String inputData, AuditContext auditContext, Map extensions) throws TuxedoException {
		
		ServiceVO vo = null;
	
		logger.debug("inputdata="+inputData);
		vo = new ServiceVO(StringUtils.substring(portalId, 0, 15), auditContext.getStationID(), auditContext.getClientIPAddress(), "CLMSTATUS", ServiceVO.SHAREService.SHRINQ4);		
		vo.setExternalId(portalId);
		vo.setExternalKey(String.valueOf(key));		
		vo.setData(gov.va.vba.framework.common.StringUtils.rightPadSubstr(new StringBuilder("TINQ").append(inputData).toString(), 84));
		try {
			return new TuxedoConnector().invokeTUXservice(vo);
		}
		catch (TPReplyException tpr)
		{
			handleTuxedoErrors(tpr, auditContext);
	    	throw new TuxedoException(tpr, TuxedoErrorUtils.getTuxedoErrorMessage(tpr), TuxedoErrorUtils.getTuxedoErrorCode(tpr));
		}
		catch (TuxedoException te)
		{
			handleTuxedoErrors(te, auditContext);
			throw te;
		}
		catch (Exception e) {
			handleTuxedoErrors(e, auditContext);
			throw new TuxedoException(e);
		}				
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
	public TypedBuffer execute(String serviceName, TypedBuffer buffer, AuditContext auditContext, Map extensions) throws TuxedoException{

		if (buffer instanceof TypedFML)
		{
			TypedFML fmlBuffer=(TypedFML) buffer;
			logger.debug("fmlBuffer.data="+getInputData(fmlBuffer));
		}
		logger.debug("TuxedoSessionEJBV2::execute(). Invoked Bus Interf: "+ctx.getInvokedBusinessInterface().getSimpleName());
		logger.debug("TuxedoSessionEJBV2: Tuxedo Connector execute - "+serviceName+" by "+this.ctx.getCallerPrincipal().getName());
		try {
			return new TuxedoConnector().execute(serviceName, buffer);
		}
		catch (TuxedoConnectorException tce)
		{
			handleTuxedoErrors(tce, auditContext);
	    	throw new TuxedoException(tce, tce.getTuxedoErrorMessage(),tce.getTuxedoErrorCode());
		}
		catch (TuxedoException te)
		{
			handleTuxedoErrors(te, auditContext);
			throw te;
		}
		catch (Exception e) {
			handleTuxedoErrors(e, auditContext);
			throw new TuxedoException(e);
		}		
	}

	@Override
	public TypedBuffer execute(String serviceName, TypedBuffer buffer,
			boolean transaction, AuditContext auditContext, Map extensions)
			throws TuxedoException {
		if (buffer instanceof TypedFML)
		{
			TypedFML fmlBuffer=(TypedFML) buffer;
			logger.debug("fmlBuffer.data="+getInputData(fmlBuffer));
		}
		logger.debug("TuxedoSessionEJBV2::execute(). Invoked Bus Interf: "+ctx.getInvokedBusinessInterface().getSimpleName());
		logger.debug("TuxedoSessionEJBV2: Tuxedo Connector execute - "+serviceName+" by "+this.ctx.getCallerPrincipal().getName());
		try {
			return new TuxedoConnector().execute(serviceName, buffer, transaction);
		}
		catch (TuxedoConnectorException tce)
		{
			handleTuxedoErrors(tce, auditContext);
	    	throw new TuxedoException(tce, tce.getTuxedoErrorMessage(),tce.getTuxedoErrorCode());
		}
		catch (TuxedoException te)
		{
			handleTuxedoErrors(te, auditContext);
			throw te;
		}
		catch (Exception e) {
			handleTuxedoErrors(e, auditContext);
			throw new TuxedoException(e);
		}		
	}
	
	@Override
	public String execute(ServiceVO serviceVo, boolean transaction, AuditContext auditContext, Map extensions)
			throws TuxedoException {
		
		logger.debug("TuxedoSessionEJBV2::execute(). Invoked Bus Interf: "+ctx.getInvokedBusinessInterface().getSimpleName());
		logger.debug("TuxedoSessionEJBV2: Tuxedo Connector execute - "+serviceVo.getServiceName()+" by "+this.ctx.getCallerPrincipal().getName());
		try {
			return new TuxedoConnector().invokeTUXservice(serviceVo, transaction);
		}
		catch (TPReplyException tpr)
		{
			handleTuxedoErrors(tpr, auditContext);
	    	throw new TuxedoException(tpr, TuxedoErrorUtils.getTuxedoErrorMessage(tpr), TuxedoErrorUtils.getTuxedoErrorCode(tpr));
		}
		catch (TuxedoException te)
		{
			handleTuxedoErrors(te, auditContext);
			throw te;
		}
		catch (Exception e) {
			handleTuxedoErrors(e, auditContext);
			throw new TuxedoException(e);
		}		
	}
	
	@Override
	public String execute(ServiceVO serviceVo, int transactionMode,
			AuditContext auditContext, Map extensions) throws TuxedoException {
		logger.debug("TuxedoSessionEJBV2::execute(). Invoked Bus Interf: "+ctx.getInvokedBusinessInterface().getSimpleName());
		logger.debug("TuxedoSessionEJBV2: Tuxedo Connector execute - "+serviceVo.getServiceName()+" by "+this.ctx.getCallerPrincipal().getName());
		try {
			return new TuxedoConnector().invokeTUXservice(serviceVo, transactionMode);
		}
		catch (TPReplyException tpr)
		{
			handleTuxedoErrors(tpr, auditContext);
	    	throw new TuxedoException(tpr, TuxedoErrorUtils.getTuxedoErrorMessage(tpr), TuxedoErrorUtils.getTuxedoErrorCode(tpr));
		}
		catch (TuxedoException te)
		{
			handleTuxedoErrors(te, auditContext);
			throw te;
		}
		catch (Exception e) {
			handleTuxedoErrors(e, auditContext);
			throw new TuxedoException(e);
		}		
	}

	@Override
	public TypedBuffer execute(String serviceName, TypedBuffer buffer,
			int transactionMode, AuditContext auditContext, Map extensions)
			throws TuxedoException {
		if (buffer instanceof TypedFML)
		{
			TypedFML fmlBuffer=(TypedFML) buffer;
			logger.debug("fmlBuffer.data="+getInputData(fmlBuffer));
		}
		logger.debug("TuxedoSessionEJBV2::execute(). Invoked Bus Interf: "+ctx.getInvokedBusinessInterface().getSimpleName());
		logger.debug("TuxedoSessionEJBV2: Tuxedo Connector execute - "+serviceName+" by "+this.ctx.getCallerPrincipal().getName());
		try {
			return new TuxedoConnector().execute(serviceName, buffer, transactionMode);
		}
		catch (TuxedoConnectorException tce)
		{
			handleTuxedoErrors(tce, auditContext);
	    	throw new TuxedoException(tce, tce.getTuxedoErrorMessage(),tce.getTuxedoErrorCode());
		}
		catch (TuxedoException te)
		{
			handleTuxedoErrors(te, auditContext);
			throw te;
		}
		catch (Exception e) {
			handleTuxedoErrors(e, auditContext);
			throw new TuxedoException(e);
		}		
	}
	/**
	 * 
	 
	@Override
	public String executeSHRINQM(String userId, String stationId, String ipAddress, String applName, String inputData) throws TuxedoException {

		logger.debug("TuxedoSessionEJB::execute(). Invoked Bus Interf: "+ctx.getInvokedBusinessInterface().getSimpleName());
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

	private void handleTuxedoErrors(Throwable t, AuditContext auditContext)
	{
		if (auditContext!=null)
			logger.error("auditContext="+auditContext, t);
	}
	
	private String getInputData(TypedFML fmlBuffer)
	{
		byte[] dataBa=null;
		try {
			dataBa = (byte[])fmlBuffer.Fget(ExternalUserFMLFieldTable.FML_APPL_DATA,0);
		} catch (Ferror e) {
			// TODO Auto-generated catch block
			logger.error("",e);
		}
		String data=new String(dataBa);
		return data;
	}

}


