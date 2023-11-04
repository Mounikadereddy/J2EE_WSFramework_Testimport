package gov.va.vba.framework.services;

import gov.va.vba.framework.BaseTestCase;
import gov.va.vba.framework.EJBTestCase;
import gov.va.vba.framework.common.AuditContext;
import gov.va.vba.framework.domain.vo.ServiceVO;
import gov.va.vba.framework.domain.vo.ServiceVO.TuxedoService;
import gov.va.vba.framework.esb.connectors.client.ExternalUserFMLFieldTable;
import gov.va.vba.framework.logging.Logger;
import gov.va.vba.framework.services.ejb.TuxParams;

import java.io.IOException;

import javax.naming.NamingException;

import org.codehaus.plexus.util.StringUtils;

import weblogic.rmi.extensions.PortableRemoteObject;
import weblogic.wtc.jatmi.Ferror;
import weblogic.wtc.jatmi.TypedBuffer;
import weblogic.wtc.jatmi.TypedFML;

public class TuxedoServiceV3SpecialTest extends EJBTestCase {
	public static Logger logger = Logger
			.getLogger(TuxedoServiceV3SpecialTest.class);

	private enum MapKeys {
		description, userId, expectedReturnCode, stationId, clientIP, applicationName, externalId, externalKey, inputData, serviceName, transactionMode
	};

	private TuxedoServiceRemoteV3 tuxedoServiceV3 = createTuxedoServiceRemoteV3();

	public TuxedoServiceV3SpecialTest() {
	}

	public void testExecuteTypedBuffer() throws Ferror, TuxedoException,
			IOException {

		logger.info("special");
		try {
			String actualOutput = getExecuteTypedBuffer(true, "VAEBENEFITS",
					"281", "10.224.105.47", "eBenefits", "1234150223",
					"20130307-13:27:23:294", "shrinq4",
					"TINQ517961109 001ZYLS00                               ",
					true);
			System.out.println("output >" + actualOutput + "<");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String getExecuteTypedBuffer(boolean copyEXTUID, String userId,
			String stationId, String clientIP, String applicationName,
			String externalId, String externalKey, String serviceName,
			String inputData, boolean transactional) throws Ferror,
			TuxedoException {
		TypedFML typedFML = null;
		try {
			AuditContext context = new AuditContext();
			context.setUserId(userId);
			context.setStationID(stationId);
			context.setClientIPAddress(clientIP);
			context.setApplicationName(applicationName);
			context.setClientUniqueID(externalId);
			context.setClientUniqueKey(externalKey);
			if ( !StringUtils.isEmpty(externalId) && 
				 !StringUtils.isEmpty(externalKey) ) {
					context.setForceExternal(true);
			}

			// TODO: what to do with this?
			String proxyServiceForExternallyAuthnUsers = "wtcsvccall";
			TypedFML inpBuff = convertToFMLBuffer(copyEXTUID, userId,
					stationId, clientIP, applicationName, externalId,
					externalKey, serviceName, inputData);
			TypedBuffer to = tuxedoServiceV3.execute(serviceName, inpBuff,
					context, null);
			typedFML = (TypedFML) to;
		} catch (Exception e) {
			if (e instanceof TuxedoException) {
				TuxedoException te = (TuxedoException)e;
				logger.debug("Tuxedo Exception - Base ErrorCode ["+te.getErrorCode() +
						"], Base ErrorId ["+te.getExceptionId() +
						"], TuxedoExcpetion ErrorCode ["+te.getTuxedoErrorCode() + 
						"], TuxedoException Error Message ["+te.getTuxedoErrorMessage() + 
						"]");
			}
			e.printStackTrace();
		}
		return new String((byte[]) typedFML.Fget(
				ExternalUserFMLFieldTable.FML_APPL_DATA, 0));
	}

	public void testProcessRequest() throws IOException,
			TuxedoException {
		AuditContext auditContext = createAuditContext("VAEBENEFITS", "281",
				"10.224.105.47", "eBenefits", "1234150223",
				"20130307-13:27:23:294");

		TuxParams tuxParams = new TuxParams();
		tuxParams.setTuxedoServiceName("SHRINQ8");
		tuxParams.setTransactionType(8);
		tuxParams
				.setData("TINQ517961109 001ZYLS00                               ");
		try {
			String actualOutput = tuxedoServiceV3.processRequest(tuxParams,
					auditContext, null);
		} catch (Exception e) {
			if (e instanceof TuxedoException) {
				TuxedoException te = (TuxedoException)e;
				logger.debug("Tuxedo Exception - Base ErrorCode ["+te.getErrorCode() +
						"], Base ErrorId ["+te.getExceptionId() +
						"], TuxedoExcpetion ErrorCode ["+te.getTuxedoErrorCode() + 
						"], TuxedoException Error Message ["+te.getTuxedoErrorMessage() + 
						"]");
			}
			e.printStackTrace();
		}
	}

	private TuxedoServiceRemoteV3 createTuxedoServiceRemoteV3() {
		setupEJBClient();
		Object ref = null;
		TuxedoServiceRemoteV3 tuxedoServiceRemoteV3 = null;
		try {
			ref = getJNDIContext().lookup(
					"VbaTuxedoServiceV3#"
							+ TuxedoServiceRemoteV3.class.getName());
		} catch (NamingException e) {
			e.printStackTrace();
		}
		tuxedoServiceRemoteV3 = (TuxedoServiceRemoteV3) PortableRemoteObject
				.narrow(ref, TuxedoServiceRemoteV3.class);
		return tuxedoServiceRemoteV3;
	}

	private AuditContext createAuditContext(String userId, String stationId,
			String clientIP, String applicationName, String externalId,
			String externalKey) {
		AuditContext context = new AuditContext();
		context.setUserId(userId);
		context.setStationID(stationId);
		context.setClientIPAddress(clientIP);
		context.setApplicationName(applicationName);
		context.setClientUniqueID(externalId);
		context.setClientUniqueKey(externalKey);
		if ( !StringUtils.isEmpty(externalId) && 
			 !StringUtils.isEmpty(externalKey) ) {
			context.setForceExternal(true);
		}
		return context;
	}

	protected TypedFML convertToFMLBuffer(boolean copyEXTUID, String userName,
			String stationId, String clientIP, String applicationName,
			String externalId, String externalKey, String serviceName,
			String inputData) throws Ferror {
		TypedFML inpBuff = new TypedFML(new ExternalUserFMLFieldTable());

		inpBuff.Fchg(ExternalUserFMLFieldTable.FML_CLIENT_MODULE_NAME, 0,
				applicationName.toUpperCase().getBytes());
		inpBuff.Fchg(ExternalUserFMLFieldTable.FML_USERID, 0, userName
				.toUpperCase().getBytes());
		inpBuff.Fchg(ExternalUserFMLFieldTable.FML_REPLY_DEST_IND, 0, 'N');
		inpBuff.Fchg(ExternalUserFMLFieldTable.FML_COMPUTER_NAME, 0,
				clientIP.getBytes());
		inpBuff.Fchg(ExternalUserFMLFieldTable.FML_PRIOR_SERVICE_NAME, 0,
				"NA".getBytes());
		inpBuff.Fchg(ExternalUserFMLFieldTable.FML_STATION_TO_ROUTE_TO, 0,
				stationId.getBytes());
		inpBuff.Fchg(ExternalUserFMLFieldTable.FML_APPL_DATA, 0,
				inputData.getBytes());

		return inpBuff;
	}

}
