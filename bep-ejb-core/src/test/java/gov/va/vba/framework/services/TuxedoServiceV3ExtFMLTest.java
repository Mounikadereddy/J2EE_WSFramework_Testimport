package gov.va.vba.framework.services;

import gov.va.vba.benefits.arch.fml.fldtbl.ArchTbl;
import gov.va.vba.benefits.arch.fml.fldtbl.VNTbl;
import gov.va.vba.framework.BaseTestCase;
import gov.va.vba.framework.EJBTestCase;
import gov.va.vba.framework.common.AuditContext;
import gov.va.vba.framework.logging.Logger;
import gov.va.vba.framework.services.ejb.TuxParams;

import java.io.BufferedReader;
import java.util.HashMap;

import javax.naming.NamingException;

import org.apache.commons.lang.StringUtils;

import weblogic.rmi.extensions.PortableRemoteObject;
import weblogic.wtc.jatmi.Ferror;
import weblogic.wtc.jatmi.FldTbl;
import weblogic.wtc.jatmi.TypedFML;
import weblogic.wtc.jatmi.TypedFML32;

public class TuxedoServiceV3ExtFMLTest extends EJBTestCase {

	public static Logger logger = Logger
			.getLogger(TuxedoServiceV3ExtFMLTest.class);

	private enum MapKeys {
		description, userId, expectedReturnCode, stationId, clientIP, applicationName, externalId, externalKey, inputData, serviceName, transactionMode
	};

	private TuxedoServiceRemoteV3 tuxedoServiceV3 = createTuxedoServiceRemoteV3();

	public TuxedoServiceV3ExtFMLTest() {
	}

	
	public void testWtcSvcCall_processRequest16_longComputerName()
			throws Exception {
		try {
			logger.info("testWtcSvcCall_processRequest16_longComputerName");
			BufferedReader inputReader = getBufferedReader("901_error-v3-input-"
					+ BaseTestCase.env + ".txt");
			String input = inputReader.readLine();
			while (input != null) {
				HashMap<MapKeys, String> inputMap = parseInputExpanded(input);
				AuditContext auditContext = createAuditContext(
						inputMap.get(MapKeys.userId),
						inputMap.get(MapKeys.stationId), 
						"Long Computer Na",
						inputMap.get(MapKeys.applicationName),
						inputMap.get(MapKeys.externalId),
						inputMap.get(MapKeys.externalKey));

				int transactionMode = Integer.parseInt(inputMap
						.get(MapKeys.transactionMode));
				TuxParams tuxParams = new TuxParams(
						getTuxedoService(inputMap.get(MapKeys.serviceName)),
						inputMap.get(MapKeys.inputData), transactionMode, null);
				tuxedoServiceV3.processRequest(tuxParams, auditContext, null);
			}
			assertTrue(false); //It should error out
		} catch (Exception te) {
			if (te instanceof TuxedoException) {
				TuxedoException tex = (TuxedoException)te;
				logger.debug("Tuxedo Exception - Base ErrorCode ["+tex.getErrorCode() +
						"], Base ErrorId ["+tex.getExceptionId() +
						"], TuxedoExcpetion ErrorCode ["+tex.getTuxedoErrorCode() + 
						"], TuxedoException Error Message ["+tex.getTuxedoErrorMessage() + 
						"]");
			}
			assertTrue(te instanceof TuxedoException);
			assertTrue(((TuxedoException) te)
					.getMessage()
					.contains(
							"cannot be null or empty, and length cannot be greater than  15"));
		}
	}

	public void testWtcSvcCall_processRequest16_longAppName()
			throws Exception {
		try {
			logger.info("testWtcSvcCall_processRequest16_longAppName");
			BufferedReader inputReader = getBufferedReader("901_error-v3-input-"
					+ BaseTestCase.env + ".txt");
			String input = inputReader.readLine();
			while (input != null) {
				HashMap<MapKeys, String> inputMap = parseInputExpanded(input);
				AuditContext auditContext = createAuditContext(
						inputMap.get(MapKeys.userId),
						inputMap.get(MapKeys.stationId),
						inputMap.get(MapKeys.clientIP),
						"Long Application Name Grater Than Thrity",
						inputMap.get(MapKeys.externalId),
						inputMap.get(MapKeys.externalKey));

				int transactionMode = Integer.parseInt(inputMap
						.get(MapKeys.transactionMode));
				TuxParams tuxParams = new TuxParams(
						getTuxedoService(inputMap.get(MapKeys.serviceName)),
						inputMap.get(MapKeys.inputData), transactionMode, null);
				tuxedoServiceV3.processRequest(tuxParams, auditContext, null);
			}
			assertTrue(false); //It should error out
		} catch (Exception te) {
			if (te instanceof TuxedoException) {
				TuxedoException tex = (TuxedoException)te;
				logger.debug("Tuxedo Exception - Base ErrorCode ["+tex.getErrorCode() +
						"], Base ErrorId ["+tex.getExceptionId() +
						"], TuxedoExcpetion ErrorCode ["+tex.getTuxedoErrorCode() + 
						"], TuxedoException Error Message ["+tex.getTuxedoErrorMessage() + 
						"]");
			}
			assertTrue(te instanceof TuxedoException);
			assertTrue(((TuxedoException) te)
					.getMessage()
					.contains(
							"cannot be null or empty, and length cannot be greater than  30"));
		}
	}

	public void testWtcSvcCall_processRequest16_longExternalUID()
			throws Exception {
		try {
			logger.info("testWtcSvcCall_processRequest16_longExternalUID");
			BufferedReader inputReader = getBufferedReader("901_error-v3-input-"
					+ BaseTestCase.env + ".txt");
			String input = inputReader.readLine();
			while (input != null) {
				HashMap<MapKeys, String> inputMap = parseInputExpanded(input);
				AuditContext auditContext = createAuditContext(
						inputMap.get(MapKeys.userId),
						inputMap.get(MapKeys.stationId),
						inputMap.get(MapKeys.clientIP),
						inputMap.get(MapKeys.applicationName),
						"Long External UID Grater Than Forty Characters",// inputMap.get(MapKeys.externalId),
						inputMap.get(MapKeys.externalKey));

				int transactionMode = Integer.parseInt(inputMap
						.get(MapKeys.transactionMode));
				TuxParams tuxParams = new TuxParams(
						getTuxedoService(inputMap.get(MapKeys.serviceName)),
						inputMap.get(MapKeys.inputData), transactionMode, null);
				tuxedoServiceV3.processRequest(tuxParams, auditContext, null);
			}
			assertTrue(false); //It should error out
		} catch (Exception te) {
			if (te instanceof TuxedoException) {
				TuxedoException tex = (TuxedoException)te;
				logger.debug("Tuxedo Exception - Base ErrorCode ["+tex.getErrorCode() +
						"], Base ErrorId ["+tex.getExceptionId() +
						"], TuxedoExcpetion ErrorCode ["+tex.getTuxedoErrorCode() + 
						"], TuxedoException Error Message ["+tex.getTuxedoErrorMessage() + 
						"]");
			}
			assertTrue(te instanceof TuxedoException);
			assertTrue(((TuxedoException) te)
					.getMessage()
					.contains(
							"cannot be null or empty, and length cannot be greater than  40"));
		}
	}

	public void testWtcSvcCall_processRequest16_longExternalKey()
			throws Exception {
		try {
			logger.info("testWtcSvcCall_processRequest16_longExternalKey");
			BufferedReader inputReader = getBufferedReader("901_error-v3-input-"
					+ BaseTestCase.env + ".txt");
			String input = inputReader.readLine();
			while (input != null) {
				HashMap<MapKeys, String> inputMap = parseInputExpanded(input);
				AuditContext auditContext = createAuditContext(
						inputMap.get(MapKeys.userId),
						inputMap.get(MapKeys.stationId),
						inputMap.get(MapKeys.clientIP),
						inputMap.get(MapKeys.applicationName),
						inputMap.get(MapKeys.externalId),
						"Long External UID Grater Than Forty Characters");// inputMap.get(MapKeys.externalKey));

				int transactionMode = Integer.parseInt(inputMap
						.get(MapKeys.transactionMode));
				TuxParams tuxParams = new TuxParams(
						getTuxedoService(inputMap.get(MapKeys.serviceName)),
						inputMap.get(MapKeys.inputData), transactionMode, null);
				tuxedoServiceV3.processRequest(tuxParams, auditContext, null);
			}
			assertTrue(false); //It should error out
		} catch (Exception te) {
			if (te instanceof TuxedoException) {
				TuxedoException tex = (TuxedoException)te;
				logger.debug("Tuxedo Exception - Base ErrorCode ["+tex.getErrorCode() +
						"], Base ErrorId ["+tex.getExceptionId() +
						"], TuxedoExcpetion ErrorCode ["+tex.getTuxedoErrorCode() + 
						"], TuxedoException Error Message ["+tex.getTuxedoErrorMessage() + 
						"]");
			}
			assertTrue(te instanceof TuxedoException);
			assertTrue(((TuxedoException) te)
					.getMessage()
					.contains(
							"cannot be null or empty, and length cannot be greater than  40"));
		}
	}

	public void testWtcSvcCall_execute16_longComputerName() throws Exception,
			Ferror {

		try {
			logger.info("testWtcSvcCall_execute16_longComputerName");
			BufferedReader inputReader = getBufferedReader("901_error-v3-input-"
					+ BaseTestCase.env + ".txt");
			String input = inputReader.readLine();
			while (input != null) {
				HashMap<MapKeys, String> inputMap = parseInputExpanded(input);

				AuditContext auditContext = createAuditContext(
						inputMap.get(MapKeys.userId),
						inputMap.get(MapKeys.stationId), "Long Computer Na",
						inputMap.get(MapKeys.applicationName),
						inputMap.get(MapKeys.externalId),
						inputMap.get(MapKeys.externalKey));

				TypedFML inpBuff = convertToFMLBuffer(true,
						auditContext.getUserId(), auditContext.getStationID(),
						auditContext.getClientIPAddress(),
						auditContext.getApplicationName(),
						auditContext.getClientUniqueID(),
						auditContext.getClientUniqueKey(),
						inputMap.get(MapKeys.serviceName),
						inputMap.get(MapKeys.inputData));
				tuxedoServiceV3.execute(inputMap.get(MapKeys.serviceName),
						inpBuff, auditContext, null);
			}
			assertTrue(false); //It should error out
		} catch (TuxedoException te) {
			if (te instanceof TuxedoException) {
				TuxedoException tex = (TuxedoException)te;
				logger.debug("Tuxedo Exception - Base ErrorCode ["+tex.getErrorCode() +
						"], Base ErrorId ["+tex.getExceptionId() +
						"], TuxedoExcpetion ErrorCode ["+tex.getTuxedoErrorCode() + 
						"], TuxedoException Error Message ["+tex.getTuxedoErrorMessage() + 
						"]");
			}
			assertTrue(te instanceof TuxedoException);
			assertTrue(((TuxedoException) te)
					.getMessage()
					.contains(
							"cannot be null or empty, and length cannot be greater than  15"));
		}
	}

	public void testWtcSvcCall_execute16_longAppName() throws Exception,
			Ferror {

		try {
			logger.info("testWtcSvcCall_execute16_longAppName");
			BufferedReader inputReader = getBufferedReader("901_error-v3-input-"
					+ BaseTestCase.env + ".txt");
			String input = inputReader.readLine();
			while (input != null) {
				HashMap<MapKeys, String> inputMap = parseInputExpanded(input);

				AuditContext auditContext = createAuditContext(
						inputMap.get(MapKeys.userId),
						inputMap.get(MapKeys.stationId),
						inputMap.get(MapKeys.clientIP),
						"Long Application Name greater thann Thirty",// inputMap.get(MapKeys.applicationName),
						inputMap.get(MapKeys.externalId),
						inputMap.get(MapKeys.externalKey));

				TypedFML inpBuff = convertToFMLBuffer(true,
						auditContext.getUserId(), auditContext.getStationID(),
						auditContext.getClientIPAddress(),
						auditContext.getApplicationName(),
						auditContext.getClientUniqueID(),
						auditContext.getClientUniqueKey(),
						inputMap.get(MapKeys.serviceName),
						inputMap.get(MapKeys.inputData));
				tuxedoServiceV3.execute(inputMap.get(MapKeys.serviceName),
						inpBuff, auditContext, null);
			}
			assertTrue(false); //It should error out
		} catch (TuxedoException te) {
			if (te instanceof TuxedoException) {
				TuxedoException tex = (TuxedoException)te;
				logger.debug("Tuxedo Exception - Base ErrorCode ["+tex.getErrorCode() +
						"], Base ErrorId ["+tex.getExceptionId() +
						"], TuxedoExcpetion ErrorCode ["+tex.getTuxedoErrorCode() + 
						"], TuxedoException Error Message ["+tex.getTuxedoErrorMessage() + 
						"]");
			}
			assertTrue(te instanceof TuxedoException);
			assertTrue(((TuxedoException) te)
					.getMessage()
					.contains(
							"cannot be null or empty, and length cannot be greater than  30"));
		}
	}

	public void testWtcSvcCall_execute16_longExternalUID() throws Exception,
			Ferror {

		try {
			logger.info("testWtcSvcCall_execute16_longExternalUID");
			BufferedReader inputReader = getBufferedReader("901_error-v3-input-"
					+ BaseTestCase.env + ".txt");
			String input = inputReader.readLine();
			while (input != null) {
				HashMap<MapKeys, String> inputMap = parseInputExpanded(input);

				AuditContext auditContext = createAuditContext(
						inputMap.get(MapKeys.userId),
						inputMap.get(MapKeys.stationId),
						inputMap.get(MapKeys.clientIP),
						inputMap.get(MapKeys.applicationName),
						"Long External UID greater than Forty Characters",// inputMap.get(MapKeys.externalId),
						inputMap.get(MapKeys.externalKey));

				TypedFML inpBuff = convertToFMLBuffer(true,
						auditContext.getUserId(), auditContext.getStationID(),
						auditContext.getClientIPAddress(),
						auditContext.getApplicationName(),
						auditContext.getClientUniqueID(),
						auditContext.getClientUniqueKey(),
						inputMap.get(MapKeys.serviceName),
						inputMap.get(MapKeys.inputData));
				tuxedoServiceV3.execute(inputMap.get(MapKeys.serviceName),
						inpBuff, auditContext, null);
			}
			assertTrue(false); //It should error out
		} catch (TuxedoException te) {
			if (te instanceof TuxedoException) {
				TuxedoException tex = (TuxedoException)te;
				logger.debug("Tuxedo Exception - Base ErrorCode ["+tex.getErrorCode() +
						"], Base ErrorId ["+tex.getExceptionId() +
						"], TuxedoExcpetion ErrorCode ["+tex.getTuxedoErrorCode() + 
						"], TuxedoException Error Message ["+tex.getTuxedoErrorMessage() + 
						"]");
			}
			assertTrue(te instanceof TuxedoException);
			assertTrue(((TuxedoException) te)
					.getMessage()
					.contains(
							"cannot be null or empty, and length cannot be greater than  40"));
		}
	}

	public void testWtcSvcCall_execute16_longExternalKey() throws Exception,
			Ferror {

		try {
			logger.info("testWtcSvcCall_execute16_longExternalKey");
			BufferedReader inputReader = getBufferedReader("901_error-v3-input-"
					+ BaseTestCase.env + ".txt");
			String input = inputReader.readLine();
			while (input != null) {
				HashMap<MapKeys, String> inputMap = parseInputExpanded(input);

				AuditContext auditContext = createAuditContext(
						inputMap.get(MapKeys.userId),
						inputMap.get(MapKeys.stationId),
						inputMap.get(MapKeys.clientIP),
						inputMap.get(MapKeys.applicationName),
						inputMap.get(MapKeys.externalId),
						"Long External Key greater than forty characters");// inputMap.get(MapKeys.externalKey));

				TypedFML inpBuff = convertToFMLBuffer(true,
						auditContext.getUserId(), auditContext.getStationID(),
						auditContext.getClientIPAddress(),
						auditContext.getApplicationName(),
						auditContext.getClientUniqueID(),
						auditContext.getClientUniqueKey(),
						inputMap.get(MapKeys.serviceName),
						inputMap.get(MapKeys.inputData));
				tuxedoServiceV3.execute(inputMap.get(MapKeys.serviceName),
						inpBuff, auditContext, null);
			}
			assertTrue(false); //It should error out
		} catch (TuxedoException te) {
			if (te instanceof TuxedoException) {
				TuxedoException tex = (TuxedoException)te;
				logger.debug("Tuxedo Exception - Base ErrorCode ["+tex.getErrorCode() +
						"], Base ErrorId ["+tex.getExceptionId() +
						"], TuxedoExcpetion ErrorCode ["+tex.getTuxedoErrorCode() + 
						"], TuxedoException Error Message ["+tex.getTuxedoErrorMessage() + 
						"]");
			}
			assertTrue(te instanceof TuxedoException);
			assertTrue(((TuxedoException) te)
					.getMessage()
					.contains(
							"cannot be null or empty, and length cannot be greater than  40"));
		}
	}

	public void testWtcSvcCall_execute32_longExternalKey() throws Exception{
		VNTbl vnTbl = null;
		ArchTbl archTbl = null;
		logger.info("testWtcSvcCall_execute32_longExternalKey");
		try {

			AuditContext auditCtx = createAuditContext("281CEASL", "281",
					"Long Computer Na", "EBENEFITS", "x281MARIO",
					"01234567890123456789012345678901234567890");

			archTbl = new ArchTbl();
			vnTbl = new VNTbl();
			FldTbl[] tblArray = { archTbl, vnTbl };
			TypedFML32 fml32 = new TypedFML32(tblArray);
			fml32.Fchg(archTbl.name_to_Fldid("CALL_ID"), 0, new Integer(17));
			fml32.Fchg(archTbl.name_to_Fldid("DBDEBUG"), 0, 0);
			fml32.Fchg(archTbl.name_to_Fldid("CLIENT_NAME"), 0, "MarioTestCL");
			fml32.Fchg(archTbl.name_to_Fldid("CLIENT_MACH"), 0, "MarioPC");
			fml32.Fchg(archTbl.name_to_Fldid("JRN_LCTN_ID"), 0, "100");
			fml32.Fchg(archTbl.name_to_Fldid("LCTN_ID"), 0, "800001");
			fml32.Fchg(archTbl.name_to_Fldid("RTE_STN"), 0, "281");
			fml32.Fchg(archTbl.name_to_Fldid("PTCPNT_ID"), 0, "91320");
			fml32.Fchg(archTbl.name_to_Fldid("CLNT_TIME"), 0, "");
			fml32.Fchg(archTbl.name_to_Fldid("APPLICATION_ID"), 0, "121");
			System.out.println("Executing service....");
			tuxedoServiceV3.execute("SVNTYPE", fml32, false, auditCtx, null);
			assertTrue(false); //It should error out
		} catch (Exception e) {
			if (e instanceof TuxedoException) {
				TuxedoException tex = (TuxedoException)e;
				logger.debug("Tuxedo Exception - Base ErrorCode ["+tex.getErrorCode() +
						"], Base ErrorId ["+tex.getExceptionId() +
						"], TuxedoExcpetion ErrorCode ["+tex.getTuxedoErrorCode() + 
						"], TuxedoException Error Message ["+tex.getTuxedoErrorMessage() + 
						"]");
			}
			assertTrue(e instanceof TuxedoException);
			assertTrue(((TuxedoException) e)
					.getMessage()
					.contains(
							"cannot be null or empty, and length cannot be greater than  40"));
		}
	}

	public void testWtcSvcCall_execute32_longExternalUID() throws Exception {
		VNTbl vnTbl = null;
		ArchTbl archTbl = null;
		
		logger.info("testWtcSvcCall_execute32_longExternalUID");
		try {

			AuditContext auditCtx = createAuditContext("281CEASL", "281",
					"Long Computer Na", "EBENEFITS", "01234567890123456789012345678901234567890",
					"ExternalKey");

			archTbl = new ArchTbl();
			vnTbl = new VNTbl();
			FldTbl[] tblArray = { archTbl, vnTbl };
			TypedFML32 fml32 = new TypedFML32(tblArray);
			fml32.Fchg(archTbl.name_to_Fldid("CALL_ID"), 0, new Integer(17));
			fml32.Fchg(archTbl.name_to_Fldid("DBDEBUG"), 0, 0);
			fml32.Fchg(archTbl.name_to_Fldid("CLIENT_NAME"), 0, "MarioTestCL");
			fml32.Fchg(archTbl.name_to_Fldid("CLIENT_MACH"), 0, "MarioPC");
			fml32.Fchg(archTbl.name_to_Fldid("JRN_LCTN_ID"), 0, "100");
			fml32.Fchg(archTbl.name_to_Fldid("LCTN_ID"), 0, "800001");
			fml32.Fchg(archTbl.name_to_Fldid("RTE_STN"), 0, "281");
			fml32.Fchg(archTbl.name_to_Fldid("PTCPNT_ID"), 0, "91320");
			fml32.Fchg(archTbl.name_to_Fldid("CLNT_TIME"), 0, "");
			fml32.Fchg(archTbl.name_to_Fldid("APPLICATION_ID"), 0, "121");
			System.out.println("Executing service....");
			tuxedoServiceV3.execute("SVNTYPE", fml32, false, auditCtx, null);
			assertTrue(false); //It should error out
		} catch (Exception e) {
			if (e instanceof TuxedoException) {
				TuxedoException tex = (TuxedoException)e;
				logger.debug("Tuxedo Exception - Base ErrorCode ["+tex.getErrorCode() +
						"], Base ErrorId ["+tex.getExceptionId() +
						"], TuxedoExcpetion ErrorCode ["+tex.getTuxedoErrorCode() + 
						"], TuxedoException Error Message ["+tex.getTuxedoErrorMessage() + 
						"]");
			}
			assertTrue(e instanceof TuxedoException);
			assertTrue(((TuxedoException) e)
					.getMessage()
					.contains(
							"cannot be null or empty, and length cannot be greater than  40"));
		}
	}

	public HashMap<MapKeys, String> parseInputExpanded(String input) {
		logger.debug("input  >" + input + "<");
		String[] fields = input.split("~");
		HashMap<MapKeys, String> map = new HashMap<MapKeys, String>();
		map.put(MapKeys.description, fields[0]);
		map.put(MapKeys.userId, fields[1]);
		map.put(MapKeys.expectedReturnCode, fields[2]);
		map.put(MapKeys.stationId, fields[3]);
		map.put(MapKeys.clientIP, fields[4]);
		map.put(MapKeys.applicationName, fields[5]);
		map.put(MapKeys.externalId, fields[6]);
		map.put(MapKeys.externalKey, fields[7]);
		map.put(MapKeys.serviceName, fields[8]);
		map.put(MapKeys.inputData, fields[9]);
		map.put(MapKeys.transactionMode, fields[10]);
		return map;
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
		if (externalId != null && externalKey != null
				&& !StringUtils.isEmpty(externalId.trim())
				&& !StringUtils.isEmpty(externalKey.trim())) {
			context.setForceExternal(true);
		}
		return context;
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

	private gov.va.vba.framework.domain.vo.ServiceVO.TuxedoService getTuxedoService(
			String tuxedoServiceName) {
		gov.va.vba.framework.domain.vo.ServiceVO.TuxedoService tuxedoService = null;
		tuxedoService = getService(tuxedoServiceName);
		return tuxedoService;
	}
}
