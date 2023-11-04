/*
 * TuxedoSessionEJB.java
 *
 * Copyright 2007 U.S. Dept Of Veterans Affairs.
 * U.S. Government PROPRIETARY/CONFIDENTIAL. Use is subject to security terms.
 */
package gov.va.vba.framework.services.ejb;

import gov.va.vba.framework.auditing.AuditedTuxedoService;
import gov.va.vba.framework.common.AuditContext;
import gov.va.vba.framework.common.AuthenticationHelper;
import gov.va.vba.framework.domain.vo.ParticipantType;
import gov.va.vba.framework.domain.vo.ServiceVO;
import gov.va.vba.framework.esb.connectors.client.ExternalUserFMLFieldTable;
import gov.va.vba.framework.esb.connectors.client.TuxedoConnectorV3;
import gov.va.vba.framework.logging.Logger;
import gov.va.vba.framework.services.GenericService;
import gov.va.vba.framework.services.TuxedoException;
import gov.va.vba.framework.services.TuxedoServiceRemoteV3;
import gov.va.vba.framework.services.TuxedoServiceV3;
import gov.va.vba.framework.services.ejb.TuxParams;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import weblogic.javaee.CallByReference;
import weblogic.javaee.TransactionTimeoutSeconds;
import weblogic.wtc.jatmi.*;

/**
 * Main TuxedoService EJB that handles execution of all service requests to
 * underlying TuxedoService services.
 * 
 * @since Dec 9, 2010
 * @version 2
 * @author Joshua Glickman
 */

@Stateless(name = "TuxedoServiceV3", mappedName = "VbaTuxedoServiceV3")
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@Local(TuxedoServiceV3.class)
@Remote(TuxedoServiceRemoteV3.class)
@CallByReference
@TransactionTimeoutSeconds(3600)
public class TuxedoSessionEJBV3 extends GenericService implements TuxedoServiceV3 {

	@Resource
	private String constructed = null;

	@Resource
	private SessionContext ctx;

	private int cnt;

	private static Logger logger = Logger.getLogger(TuxedoSessionEJBV3.class);

	/**
	 * Default constructor - per EJB 3.0 spec.
	 * 
	 * @since Oct 14, 2008
	 * @version
	 */
	public TuxedoSessionEJBV3() {

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
	 * @since May 27, 2008
	 */
	@AuditedTuxedoService(name="WTCCTRLHINQ")
	public String getVeteranData(String fileNum, boolean veteranSelf,
			boolean serviceNumIndicator, AuditContext auditContext,
			Map extensions) throws TuxedoException {

		String response = null;

		logger
				.debug("TuxedoSessionEJBV3::getVeteranData(). Invoked Bus Interf: "
						+ ctx.getInvokedBusinessInterface().getSimpleName());

		TuxParams tuxParams = new TuxParams(ServiceVO.LGYService.WTCCTRLHINQ,
				fileNum + (veteranSelf ? "Y" : "N")
						+ (serviceNumIndicator ? "Y" : "N"), extensions);
		response = processRequest(tuxParams, auditContext, extensions);
		return response;
	}

	/**
	 * Executes the CESTROUTER Tuxedo service to create a claim
	 * 
	 */
	@AuditedTuxedoService(name="CESTROUTER")
	public String createCorpClaim(String inputData, AuditContext auditContext,
			Map extensions) throws TuxedoException {

		String response = null;

		logger
				.debug("TuxedoSessionEJBV3::createCorpClaim(). Invoked Bus Interf: "
						+ ctx.getInvokedBusinessInterface().getSimpleName());
		logger.debug("TuxedoSessionEJBV3::createCorpClaim(). cnt: " + cnt);

		TuxParams tuxParams = new TuxParams();
		tuxParams.setTuxedoService(ServiceVO.CommonService.CESTROUTER);
		tuxParams.setData(inputData);
		response = processRequest(tuxParams, auditContext, extensions);
		return response;
	}

	/**
	 * Executes the WTCHINBDN Tuxedo service to retrieve a beneficiary record
	 */
	@AuditedTuxedoService(name="WTCHINRDP")
	public String executePIFinquiry(String fileNum, boolean veteranSelf,
			AuditContext auditContext, Map extensions) throws TuxedoException {

		String response = null;

		logger
				.debug("TuxedoSessionEJBV3::executePIFInquiry(). Invoked Bus Interf: "
						+ ctx.getInvokedBusinessInterface().getSimpleName());
		logger.debug("TuxedoSessionEJBV3::executePIFInquiry(). cnt: " + cnt);

		TuxParams tuxParams = new TuxParams();
		tuxParams.setData(fileNum.concat((veteranSelf ? "Y" : "N")));
		tuxParams.setExtensions(extensions);
		tuxParams.setTuxedoService(ServiceVO.LGYService.WTCHINRDP);

		response = processRequest(tuxParams, auditContext, extensions);
		return response;
	}

	/**
	 * Executes the WTCHINBDN Tuxedo service to retrieve a beneficiary record
	 * 
	 */
	@AuditedTuxedoService(name="WTCHINBDN")
	public String executeSurvivingSpouseInquiry(String fileNum,
			AuditContext auditContext, Map extensions) throws TuxedoException {

		String response = null;

		logger
				.debug("TuxedoSessionEJBV3::executeSurvivingSpouseInquiry(). Invoked Bus Interf: "
						+ ctx.getInvokedBusinessInterface().getSimpleName());
		logger
				.debug("TuxedoSessionEJBV3::executeSurvivingSpouseInquiry(). cnt: "
						+ cnt);
		TuxParams tuxParams = new TuxParams();
		tuxParams.setData(fileNum);
		tuxParams.setExtensions(extensions);
		tuxParams.setTuxedoService(ServiceVO.LGYService.WTCHINBDN);

		response = processRequest(tuxParams, auditContext, extensions);
		return response;
	}

	/**
	 * Executes the WTCFLASH Tuxedo service to retrieve flash records from corp
	 * db
	 * 
	 */
	@AuditedTuxedoService(name="WTCFLASH")
	public String getCorpFlashes(String fileNum, AuditContext auditContext,
			Map extensions) throws TuxedoException {

		String response = null;

		logger.debug("TuxedoSessionEJBV3::getCorpFlashes. Invoked Bus Interf: "
				+ ctx.getInvokedBusinessInterface().getSimpleName());
		logger.debug("TuxedoSessionEJBV3::getCorpFlashes(). cnt: " + cnt);

		TuxParams tuxParams = new TuxParams();
		tuxParams.setData(new StringBuilder("IFLASH").append(
				gov.va.vba.framework.common.StringUtils.rightPadSubstr(fileNum,
						15)).toString());
		tuxParams.setExtensions(extensions);
		tuxParams.setTuxedoService(ServiceVO.LGYService.WTCFLASH);

		response = processRequest(tuxParams, auditContext, extensions);
		return response;
	}

	/**
	 * Executes the WTCFLASH Tuxedo service to create/update flash records in
	 * corp db
	 * 
	 */
	@AuditedTuxedoService(name="WTCFLASH")
	public String createCorpFlashes(String participantId, String[] flashes,
			AuditContext auditContext, Map extensions) throws TuxedoException {

		String response = null;

		logger
				.debug("TuxedoSessionEJBV3::createCorpFlashes(). Invoked Bus Interf: "
						+ ctx.getInvokedBusinessInterface().getSimpleName());

		TuxParams tuxParams = new TuxParams();
		tuxParams.setData(new StringBuilder("UFLASH").append(
				gov.va.vba.framework.common.StringUtils.rightPadSubstr(
						participantId, 15)).append(
				gov.va.vba.framework.common.StringUtils.rightPadSubstr(Integer
						.toString(flashes.length), 4)).append(
				getArrayValuesAsString(flashes, 50)).toString());
		tuxParams.setExtensions(extensions);
		tuxParams.setTuxedoService(ServiceVO.LGYService.WTCFLASH);

		response = processRequest(tuxParams, auditContext, extensions);
		return response;

	}

	/**
	 * Executes the WTCSNTVTY Tuxedo service to retrieve the sensitivity level
	 * of a participant's record
	 * 
	 */
	@AuditedTuxedoService(name="WTCSNTVTY")
	public String getSensitiveLevel(String ssn, ParticipantType participant,
			AuditContext auditContext, Map extensions) throws TuxedoException {

		String response = null;

		logger
				.debug("TuxedoSessionEJBV3::getSensitiveLevel. Invoked Bus Interf: "
						+ ctx.getInvokedBusinessInterface().getSimpleName());

		TuxParams tuxParams = new TuxParams();
		tuxParams.setData(new StringBuilder(ssn).append(participant.getType())
				.toString());
		tuxParams.setExtensions(extensions);
		tuxParams.setTuxedoService(ServiceVO.LGYService.WTCSNTVTY);

		response = processRequest(tuxParams, auditContext, extensions);
		return response;

	}

	/**
	 * Retrieves veteran data from BIRLS. Calls TPTOCICS internally to access
	 * Austin IDMS db's for BIRLS, COVERS, etc.
	 * 
	 * TODO: fix null appl name
	 */
	@AuditedTuxedoService(name="VAAUSIBM")
	public String getVeteranBIRLSData(String inputData,
			AuditContext auditContext, Map extensions) throws TuxedoException {

		String response = null;
		logger
				.debug("TuxedoSessionEJBV3::getBIRLSVeteranData(). Invoked Bus Interf: "
						+ ctx.getInvokedBusinessInterface().getSimpleName());

		TuxParams tuxParams = new TuxParams();
		tuxParams.setData(inputData);
		tuxParams.setExtensions(extensions);
		tuxParams.setTuxedoService(ServiceVO.SHAREService.VAAUSIBM);

		response = processRequest(tuxParams, auditContext, extensions);
		return response;
	}

	/**
	 * Convenience method for COVERS/FTS based on management's request for a
	 * quick fix.
	 * 
	 */
	@Override
	@AuditedTuxedoService(name="TPTOCICS")
	public String getVeteranBIRLSsensitiveData(String inputData,
			AuditContext auditContext, Map extensions) throws TuxedoException {

		String response = null;
		logger
				.debug("TuxedoSessionEJBV3::getVeteranBIRLSsensitiveData(). Invoked Bus Interf: "
						+ ctx.getInvokedBusinessInterface().getSimpleName());

		TuxParams tuxParams = new TuxParams();
		tuxParams.setData(inputData);
		tuxParams.setExtensions(extensions);
		tuxParams.setTuxedoService(ServiceVO.CommonService.TPTOCICS);

		response = processRequest(tuxParams, auditContext, extensions);
		return response;
	}

	/**
	 * Executes the SHRINQ3 Tuxedo service to retrieve a veteran's dependents
	 * data from the Corporate Db.
	 * 
	 * TODO: fix null appl name
	 */
	@AuditedTuxedoService(name="SHRINQ3")
	public String findDependents(String veteranId, String claimantId,
			AuditContext auditContext, Map extensions) throws TuxedoException {

		String response = null;
		logger
				.debug("TuxedoSessionEJBV3::findDependents(). Invoked Bus Interf: "
						+ ctx.getInvokedBusinessInterface().getSimpleName());

		TuxParams tuxParams = new TuxParams();
		tuxParams.setData(new StringBuilder("CEST").append(
				gov.va.vba.framework.common.StringUtils.rightPadSubstr(
						veteranId, 15)).append(
				gov.va.vba.framework.common.StringUtils.rightPadSubstr(
						claimantId, 15)).toString());
		tuxParams.setExtensions(extensions);
		tuxParams.setTuxedoService(ServiceVO.SHAREService.SHRINQ3);

		response = processRequest(tuxParams, auditContext, extensions);
		return response;
	}

	/**
	 * Executes the CESTROUTER Tuxedo service to create a veteran in the
	 * Corporate Db. CESTROUTER in most cases does validation on Corp, BIRLS,
	 * BDN, etc. and then a BUPD
	 * 
	 */
	@AuditedTuxedoService(name="CESTROUTER")
	public String createVeteran(String inputData, AuditContext auditContext,
			Map extensions) throws TuxedoException {

		String response = null;

		logger
				.debug("TuxedoSessionEJBV3::createVeteran(). Invoked Bus Interf: "
						+ ctx.getInvokedBusinessInterface().getSimpleName());
		TuxParams tuxParams = new TuxParams();
		tuxParams.setData(new StringBuilder("BUPD")
				.append(
						gov.va.vba.framework.common.StringUtils.rightPadSubstr(
								"11", 5)).append(inputData.substring(9))
				.toString());
		tuxParams.setExtensions(extensions);
		tuxParams.setTuxedoService(ServiceVO.CommonService.CESTROUTER);

		response = processRequest(tuxParams, auditContext, extensions);
		return response;
	}

	/**
	 * Executes the CESTROUTER Tuxedo service to create a veteran's dependents
	 * in the Corporate Db. CESTROUTER performs a CDEP transaction
	 * 
	 */
	@AuditedTuxedoService(name="CESTROUTER")
	public String createDependents(String inputData, AuditContext auditContext,
			Map extensions) throws TuxedoException {

		String response = null;

		TuxParams tuxParams = new TuxParams();
		tuxParams.setData(new StringBuilder("CDEP").append(
				inputData.substring(4)).toString());
		tuxParams.setExtensions(extensions);
		tuxParams.setTuxedoService(ServiceVO.CommonService.CESTROUTER);

		response = processRequest(tuxParams, auditContext, extensions);
		return response;
	}

	/**
	 * Sends requests to BDN via TPTODMIV to process payment information.
	 */
	@AuditedTuxedoService(name="TPTODMIV")
	public String processBDNPayments(String inputData,
			AuditContext auditContext, Map extensions) throws TuxedoException {

		String response = null;

		TuxParams tuxParams = new TuxParams();
		tuxParams.setData(inputData);
		tuxParams.setExtensions(extensions);
		tuxParams.setTuxedoService(ServiceVO.CommonService.TPTODMIV);

		response = processRequest(tuxParams, auditContext, extensions);
		return response;
	}

	/**
	 * A SHARE service that executes the SHRINQ3 Tuxedo service and returns a
	 * claimant's payment history. Used by eBenefits C&P Claims.
	 */
	@AuditedTuxedoService(name="SHRINQ4")
	public String getClaimantPaymentHistory(String inputData,
			AuditContext auditContext, Map extensions) throws TuxedoException {

		String response = null;

		TuxParams tuxParams = new TuxParams();
		tuxParams.setData(gov.va.vba.framework.common.StringUtils
				.rightPadSubstr(new StringBuilder("TINQ").append(inputData)
						.toString(), 84));
		tuxParams.setExtensions(extensions);
		tuxParams.setTuxedoService(ServiceVO.SHAREService.SHRINQ4);

		response = processRequest(tuxParams, auditContext, extensions);
		return response;
	}

	/**
	 * Traverses an array and pads each element with spaces of the desired
	 * <code>size</code>.
	 * 
	 * @param array
	 * @param padSize.
	 *            The number of spaces to append to each element. If the length
	 *            of the element happens to be > <code>size</code>, it is
	 *            truncated to size <code>size</code>
	 * @return
	 * @throws
	 * @return
	 * @since Jul 24, 2008
	 */
	private String getArrayValuesAsString(String[] array, int padSize) {

		StringBuilder st = new StringBuilder();
		for (int i = 0; i < array.length; i++) {
			st.append(array[i] == null ? "" : array[i]);
			for (int j = (array[i] == null ? "" : array[i]).length(); j < padSize; j++)
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
	 * @since May 30, 2008
	 */
	public String echo(String echoStr) throws Exception {

		String s = "EJB output: Arg passed in is: " + echoStr;
		logger.debug(s);
		return s;
	}

	@PostConstruct
	public void postConstruct() {

		constructed = "constructed";
	}

	@Override
	public String processRequest(TuxParams tuxParams,
			AuditContext auditContext, Map extensions) throws TuxedoException {

		String response = null;
		logger.debug("inputdata=\""+tuxParams.getData()+"\"");
		response = new TuxedoConnectorV3().processRequest(tuxParams, auditContext);
		return response;
	}

	/**
	 * 
	 */
	@Override	
	public TypedBuffer execute(String serviceName, TypedFML32 buffer, boolean transaction, AuditContext auditContext, 
			Map extensions) throws TuxedoException {

		//logger.debug("fml32Buffer.data="+buffer.Fget(ExternalUserFMLFieldTable.FML_APPL_DATA));
		logger.debug("TuxedoSessionEJBV3::execute(). Invoked Bus Interf: " + ctx.getInvokedBusinessInterface().getSimpleName());
		logger.debug("TuxedoSessionEJBV3: Tuxedo Connector execute - "+serviceName+" by "+this.ctx.getCallerPrincipal().getName());
		return new TuxedoConnectorV3().execute(serviceName, buffer, getTransactionMode(transaction), auditContext);
	}

	/**
	 * 
	 */
	@Override	
	public TypedBuffer execute(String serviceName, TypedBuffer buffer, int transaction, AuditContext auditContext, 
			Map extensions) throws TuxedoException {

		if (buffer instanceof TypedFML)
		{
			TypedFML fmlBuffer=(TypedFML) buffer;
			logger.debug("fmlBuffer.data=\""+getInputData(fmlBuffer)+"\"");
		}
		logger.debug("TuxedoSessionEJBV3::execute(). Invoked Bus Interf: " + ctx.getInvokedBusinessInterface().getSimpleName());
		logger.debug("TuxedoSessionEJBV3: Tuxedo Connector execute - "+serviceName+" by "+this.ctx.getCallerPrincipal().getName());
		return new TuxedoConnectorV3().execute(serviceName, buffer, transaction, auditContext);
	}

	/**
	 * 
	 */
	@Override	
	public TypedBuffer execute(String serviceName, TypedBuffer buffer, AuditContext auditContext, 
			Map extensions) throws TuxedoException {

		if (buffer instanceof TypedFML)
		{
			TypedFML fmlBuffer=(TypedFML) buffer;
			logger.debug("fmlBuffer.data=\""+getInputData(fmlBuffer)+"\"");
		}
		logger.debug("TuxedoSessionEJBV3::execute(). Invoked Bus Interf: " + ctx.getInvokedBusinessInterface().getSimpleName());
		logger.debug("TuxedoSessionEJBV3: Tuxedo Connector execute - "+serviceName+" by "+this.ctx.getCallerPrincipal().getName());
		return new TuxedoConnectorV3().execute(serviceName, buffer, auditContext);
	}

	private int getTransactionMode(boolean transaction) {
		int transactionMode = 0;
		if (transaction)
			transactionMode = ApplicationToMonitorInterface.TPNOAUTH;
		else
			transactionMode = ApplicationToMonitorInterface.TPNOTRAN;
		return transactionMode;
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
