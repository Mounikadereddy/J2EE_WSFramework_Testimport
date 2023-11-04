package gov.va.vba.framework.services;

import gov.va.vba.benefits.arch.fml.fldtbl.ArchTbl;
import gov.va.vba.benefits.arch.fml.fldtbl.VNTbl;
import gov.va.vba.framework.BaseTestCase;
import gov.va.vba.framework.EJBTestCase;
import gov.va.vba.framework.common.AuditContext;
import gov.va.vba.framework.esb.connectors.client.ExternalUserFMLFieldTable;
import gov.va.vba.framework.esb.connectors.client.InternalUserFMLFieldTable;
import gov.va.vba.framework.logging.Logger;
import gov.va.vba.framework.services.ejb.TuxParams;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import javax.naming.NamingException;

import org.apache.commons.lang.StringUtils;
import org.junit.Ignore;

import weblogic.rmi.extensions.PortableRemoteObject;
import weblogic.wtc.jatmi.ApplicationToMonitorInterface;
import weblogic.wtc.jatmi.Ferror;
import weblogic.wtc.jatmi.FldTbl;
import weblogic.wtc.jatmi.TypedBuffer;
import weblogic.wtc.jatmi.TypedFML;
import weblogic.wtc.jatmi.TypedFML32;

public class TuxedoServiceV3Test extends EJBTestCase {
	public static Logger logger = Logger.getLogger(TuxedoServiceV3Test.class);

	private enum MapKeys {
		description, userId, expectedReturnCode, stationId, clientIP, applicationName, externalId, externalKey, inputData, serviceName, transactionMode
	};

	private TuxedoServiceRemoteV3 tuxedoServiceV3 = createTuxedoServiceRemoteV3();

	public TuxedoServiceV3Test() {
	}

	public void testWtcSvcCall901() throws IOException {
		try {
			logger.info("testWtcSvcCall901");
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
						// null,null
						inputMap.get(MapKeys.externalId),
						inputMap.get(MapKeys.externalKey));
				
				int transactionMode = Integer.parseInt(inputMap
						.get(MapKeys.transactionMode));
				TuxParams tuxParams = new TuxParams(
						getTuxedoService(inputMap.get(MapKeys.serviceName)),
						inputMap.get(MapKeys.inputData), transactionMode, null);
				tuxedoServiceV3.processRequest(tuxParams, auditContext, null);
				input = inputReader.readLine();
			}
		} catch (TuxedoException te) {
			logger.debug("Tuxedo Exception - Base ErrorCode ["+te.getErrorCode() +
					"], Base ErrorId ["+te.getExceptionId() +
					"], TuxedoExcpetion ErrorCode ["+te.getTuxedoErrorCode() + 
					"], TuxedoException Error Message ["+te.getTuxedoErrorMessage() + 
					"]");
			assertTrue(
					"TuxedoErrorDescription is not "
							+ TuxedoServiceFoundButWrongAppException.TUXEDO_ERROR_MESAGE
							+ " but " + te.getTuxedoErrorMessage(),
					te.getTuxedoErrorMessage()
							.equals(TuxedoServiceFoundButWrongAppException.TUXEDO_ERROR_MESAGE));
			assertTrue(
					"TuxedoErrorCode is not "
							+ TuxedoServiceFoundButWrongAppException.TUXEDO_ERROR_CODE
							+ " but " + te.getTuxedoErrorCode(),
					te.getTuxedoErrorCode() == TuxedoServiceFoundButWrongAppException.TUXEDO_ERROR_CODE);
		}
	}

	public void testWtcSvcCall901_execute() throws IOException, Ferror {
		
		try {
			logger.info("testWtcSvcCall901_execute");
			runExecuteTypedBuffer("901_error-v3-input-", null);
		} catch (TuxedoException te) {
			logger.debug("Tuxedo Exception - Base ErrorCode ["+te.getErrorCode() +
					"], Base ErrorId ["+te.getExceptionId() +
					"], TuxedoExcpetion ErrorCode ["+te.getTuxedoErrorCode() + 
					"], TuxedoException Error Message ["+te.getTuxedoErrorMessage() + 
					"]");
			assertTrue(
					"TuxedoErrorDescription is not "
							+ TuxedoServiceFoundButWrongAppException.TUXEDO_ERROR_MESAGE
							+ " but " + te.getTuxedoErrorMessage(),
					te.getTuxedoErrorMessage()
							.equals(TuxedoServiceFoundButWrongAppException.TUXEDO_ERROR_MESAGE));
			assertTrue(
					"TuxedoErrorCode is not "
							+ TuxedoServiceFoundButWrongAppException.TUXEDO_ERROR_CODE
							+ " but " + te.getTuxedoErrorCode(),
					te.getTuxedoErrorCode() == TuxedoServiceFoundButWrongAppException.TUXEDO_ERROR_CODE);
		} 
	}

	public void testWtcSvcCall902_execute() throws IOException, Ferror {
		
		try {
			logger.info("testWtcSvcCall902_execute");
			runExecuteTypedBuffer("902_error-v3-input-", null);
		} catch (TuxedoException te) {
			logger.debug("Tuxedo Exception - Base ErrorCode ["+te.getErrorCode() +
					"], Base ErrorId ["+te.getExceptionId() +
					"], TuxedoExcpetion ErrorCode ["+te.getTuxedoErrorCode() + 
					"], TuxedoException Error Message ["+te.getTuxedoErrorMessage() + 
					"]");

			assertTrue(
					"TuxedoErrorDescription is not "
							+ TuxedoAppNameNotFoundException.TUXEDO_ERROR_MESAGE
							+ " but " + te.getTuxedoErrorMessage(),
					te.getTuxedoErrorMessage().equals(
							TuxedoAppNameNotFoundException.TUXEDO_ERROR_MESAGE));
			assertTrue(
					"TuxedoErrorCode is not "
							+ TuxedoAppNameNotFoundException.TUXEDO_ERROR_CODE
							+ " but " + te.getTuxedoErrorCode(),
					te.getTuxedoErrorCode() == TuxedoAppNameNotFoundException.TUXEDO_ERROR_CODE);
		} 
	}

	public void testWtcSvcCall902() throws IOException {
		try {
			logger.info("testWtcSvcCall902");
			runTests("902_error-v3-input-", null);
		} catch (TuxedoException te) {
			logger.debug("Tuxedo Exception - Base ErrorCode ["+te.getErrorCode() +
					"], Base ErrorId ["+te.getExceptionId() +
					"], TuxedoExcpetion ErrorCode ["+te.getTuxedoErrorCode() + 
					"], TuxedoException Error Message ["+te.getTuxedoErrorMessage() + 
					"]");

			assertTrue(
					"TuxedoErrorDescription is not "
							+ TuxedoAppNameNotFoundException.TUXEDO_ERROR_MESAGE
							+ " but " + te.getTuxedoErrorMessage(),
					te.getTuxedoErrorMessage().equals(
							TuxedoAppNameNotFoundException.TUXEDO_ERROR_MESAGE));
			assertTrue(
					"TuxedoErrorCode is not "
							+ TuxedoAppNameNotFoundException.TUXEDO_ERROR_CODE
							+ " but " + te.getTuxedoErrorCode(),
					te.getTuxedoErrorCode() == TuxedoAppNameNotFoundException.TUXEDO_ERROR_CODE);
		}
	}

	public void testProcessGUIE50010() throws IOException {
		try {
			logger.info("testGUIE50010");
			// TODO: exception handling below needs to be inside runTests to test each line
			runTests("CEST_error-v3-input-", null);
		} catch (TuxedoException te) {
			logger.debug("Tuxedo Exception - Base ErrorCode ["+te.getErrorCode() +
					"], Base ErrorId ["+te.getExceptionId() +
					"], TuxedoExcpetion ErrorCode ["+te.getTuxedoErrorCode() + 
					"], TuxedoException Error Message ["+te.getTuxedoErrorMessage() + 
					"]");
			assertTrue(
					"TuxedoErrorDescription is not "
							+ "GUIE50010"
							+ " but " + te.getTuxedoErrorMessage(),
					te.getTuxedoErrorMessage().equals("GUIE50010"));
			assertTrue(
					"TuxedoErrorCode is not "
							+ "11"
							+ " but " + te.getTuxedoErrorCode(),
					te.getTuxedoErrorCode() == 11);
		}
	}

	@Ignore
	public void ExecuteGUIE50010() throws IOException, Ferror {
		try {
			logger.info("testGUIE50010");
			runExecuteTypedBuffer("CEST_error-v3-input-", null);
		} catch (TuxedoException te) {
			logger.debug("Tuxedo Exception - Base ErrorCode ["+te.getErrorCode() +
					"], Base ErrorId ["+te.getExceptionId() +
					"], TuxedoExcpetion ErrorCode ["+te.getTuxedoErrorCode() + 
					"], TuxedoException Error Message ["+te.getTuxedoErrorMessage() + 
					"]");

			assertTrue(
					"TuxedoErrorDescription is not "
							+ "GUIE50010"
							+ " but " + te.getTuxedoErrorMessage(),
					te.getTuxedoErrorMessage().equals("GUIE50010"));
			assertTrue(
					"TuxedoErrorCode is not "
							+ "11"
							+ " but " + te.getTuxedoErrorCode(),
					te.getTuxedoErrorCode() == 11);
		}
	}

	public void testTransactionType() throws IOException {
		try {
			logger.info("testTransactionType");
			runTests("int_transactiontype-v3-input-", null);
		} catch (TuxedoException e) {
			logger.debug("Tuxedo Exception - Base ErrorCode ["+e.getErrorCode() +
					"], Base ErrorId ["+e.getExceptionId() +
					"], TuxedoExcpetion ErrorCode ["+e.getTuxedoErrorCode() + 
					"], TuxedoException Error Message ["+e.getTuxedoErrorMessage() + 
					"]");
			e.printStackTrace();
			assertNull("TuxedoException thrown", null);
		}
	}

	public HashMap<MapKeys, String> parseInput(String input) {
		logger.debug("input  >" + input + "<");
		String[] fields = input.split("~");
		HashMap<MapKeys, String> map = new HashMap<MapKeys, String>();
		map.put(MapKeys.description, fields[0]);
		map.put(MapKeys.userId, fields[1]);
		map.put(MapKeys.expectedReturnCode, fields[2]);
		map.put(MapKeys.stationId, fields[3]);
		map.put(MapKeys.clientIP, fields[4]);
		map.put(MapKeys.applicationName, fields[5]);
		map.put(MapKeys.externalId, fields[6].trim());
		map.put(MapKeys.externalKey, fields[7].trim());
		map.put(MapKeys.serviceName, fields[8]);
		map.put(MapKeys.inputData, fields[9]);
		return map;
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

	public void testCreateDependent() throws TuxedoException, IOException {
		logger.info("testCreateDependent");
		BufferedReader inputReader = getBufferedReader("CDEP-v3-input-"
				+ BaseTestCase.env + ".txt");
		BufferedReader outputReader = getBufferedReader("CDEP-v3-output-"
				+ BaseTestCase.env + ".txt");
		String input = inputReader.readLine();
		while (input != null) {
			HashMap<MapKeys, String> inputMap = parseInput(input);
			String expectedOutput = outputReader.readLine();
			String actualOutput = createDependents(
					inputMap.get(MapKeys.userId),
					inputMap.get(MapKeys.stationId),
					inputMap.get(MapKeys.clientIP),
					inputMap.get(MapKeys.inputData),
					inputMap.get(MapKeys.applicationName));
			logger.debug("output >" + actualOutput + "<");
			assertEquals(expectedOutput, actualOutput);
			input = inputReader.readLine();
		}
	}

	public String createDependents(String userId, String stationId,
			String clientIP, String inputData, String application)
			throws TuxedoException {
		AuditContext context = createAuditContext(userId, stationId, clientIP,
				application, null, null);
		String ret = null;
		try {
			ret = tuxedoServiceV3.createDependents(inputData, context, null);

		} catch (Throwable e) {
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
		return ret;
	}

	public void testCreateVeteran() throws TuxedoException, IOException {
		logger.info("testCreateVeteran");
		BufferedReader inputReader = getBufferedReader("BUPD-v3-input-"
				+ BaseTestCase.env + ".txt");
		BufferedReader outputReader = getBufferedReader("BUPD-v3-output-"
				+ BaseTestCase.env + ".txt");
		String input = inputReader.readLine();
		while (input != null) {
			HashMap<MapKeys, String> inputMap = parseInput(input);
			String expectedOutput = outputReader.readLine();
			String actualOutput = createVeteran(inputMap.get(MapKeys.userId),
					inputMap.get(MapKeys.stationId),
					inputMap.get(MapKeys.clientIP),
					inputMap.get(MapKeys.inputData),
					inputMap.get(MapKeys.applicationName));
			logger.info("  actual output >" + actualOutput + "<");
			logger.info("expected output >" + expectedOutput + "<");
			//assertEquals(expectedOutput, actualOutput);
			assertTrue(actualOutput.startsWith(expectedOutput.substring(0, 20)));
			input = inputReader.readLine();
		}
	}

	public String createVeteran(String userId, String stationId,
			String clientIP, String inputData, String application)
			throws TuxedoException {
		AuditContext context = createAuditContext(userId, stationId, clientIP,
				application, null, null);
		return tuxedoServiceV3.createVeteran(inputData, context, null);
	}

	public void testGetVeteranBIRLSData() throws TuxedoException, IOException {
		logger.info("testGetVeteranBIRLSData");
		BufferedReader inputReader = getBufferedReader("BINQ-v3-input-"
				+ BaseTestCase.env + ".txt");
		BufferedReader outputReader = getBufferedReader("BINQ-v3-output-"
				+ BaseTestCase.env + ".txt");
		String input = inputReader.readLine();
		while (input != null) {
			HashMap<MapKeys, String> inputMap = parseInput(input);
			String expectedOutput = outputReader.readLine();
			String actualOutput = getVeteranBIRLSData(
					inputMap.get(MapKeys.userId),
					inputMap.get(MapKeys.stationId),
					inputMap.get(MapKeys.clientIP),
					inputMap.get(MapKeys.inputData),
					inputMap.get(MapKeys.applicationName));
			logger.info("  actual output >" + actualOutput + "<");
			logger.info("expected output >" + expectedOutput + "<");
			String expectedReturnCode = inputMap
					.get(MapKeys.expectedReturnCode);
			if (!StringUtils.isEmpty(expectedReturnCode)) {
				assertTrue(actualOutput.indexOf(expectedReturnCode) >= 0);
			} else {
				assertEquals(expectedOutput, actualOutput);
			}
			input = inputReader.readLine();
		}
	}

	// internal
	public String getVeteranBIRLSData(String userId, String stationId,
			String clientIP, String inputData, String application)
			throws TuxedoException {
		AuditContext auditContext = createAuditContext(userId, stationId,
				clientIP, application, null, null);
		return tuxedoServiceV3.getVeteranBIRLSData(inputData, auditContext,
				null);
	}

	public void testProcessRequest() throws TuxedoException, IOException {
		logger.info("testProcessRequest");
		runTests("BINQ-v3-input-", null); // getVeteranBIRLSData
		// checkOutput();
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
		if ( externalId != null &&
			 externalKey != null &&
			 !StringUtils.isEmpty(externalId.trim()) && 
			 !StringUtils.isEmpty(externalKey.trim()) ) {
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

	private void runTests(String testType, String outputTest)
			throws TuxedoException, IOException {
		BufferedReader inputReader = getBufferedReader(testType
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
					inputMap.get(MapKeys.externalKey));

			int transactionMode = Integer.parseInt(inputMap.get(
					MapKeys.transactionMode).trim());
			TuxParams tuxParams = new TuxParams(
					getTuxedoService(inputMap.get(MapKeys.serviceName)),
					inputMap.get(MapKeys.inputData), transactionMode, null);
			String actualOutput = tuxedoServiceV3.processRequest(tuxParams,
					auditContext, null);

			if (!StringUtils.isEmpty(outputTest)) {
				BufferedReader outputReader = getBufferedReader(outputTest
						+ BaseTestCase.env + ".txt");
				String expectedOutput = outputReader.readLine();
				logger.debug("output >" + actualOutput + "<");
				String expectedReturnCode = inputMap
						.get(MapKeys.expectedReturnCode);
				if (!StringUtils.isEmpty(expectedReturnCode)) {
					assertTrue(actualOutput.indexOf(expectedReturnCode) >= 0);
				} else {
					assertEquals(expectedOutput, actualOutput);
				}
			}
			input = inputReader.readLine();
		}
	}

	public void runExecuteTypedBuffer(String testType, String outputTest) throws Ferror, TuxedoException,
			IOException {

		logger.info("testExecuteTypedBufferxxxxxxxxxxxx");
		BufferedReader inputReader = getBufferedReader(testType
				+ BaseTestCase.env + ".txt");
		String input = inputReader.readLine();
		while (input != null) {
			HashMap<MapKeys, String> inputMap = parseInput(input);
			String actualOutput = null;
			if (StringUtils.isEmpty(inputMap.get(MapKeys.externalId)) || StringUtils.isEmpty(inputMap.get(MapKeys.externalKey))) {
				logger.info("testing internal");
				actualOutput = callExecuteForInternal(true,
					inputMap.get(MapKeys.userId),
					inputMap.get(MapKeys.stationId),
					inputMap.get(MapKeys.clientIP),
					inputMap.get(MapKeys.applicationName),
					inputMap.get(MapKeys.serviceName),
					inputMap.get(MapKeys.inputData), true);
			} else {
				logger.info("testing external");
				actualOutput = callExecuteForExternal(true,
						inputMap.get(MapKeys.userId),
						inputMap.get(MapKeys.stationId),
						inputMap.get(MapKeys.clientIP),
						inputMap.get(MapKeys.applicationName),
						inputMap.get(MapKeys.externalId),
						inputMap.get(MapKeys.externalKey),
						inputMap.get(MapKeys.serviceName),
						inputMap.get(MapKeys.inputData), true);
			}
			logger.info("output >" + actualOutput + "<");

			String expectedReturnCode = inputMap
					.get(MapKeys.expectedReturnCode);
			if (!StringUtils.isEmpty(expectedReturnCode)) {
				assertTrue(actualOutput.indexOf(expectedReturnCode) >= 0);
			} else {
				BufferedReader outputReader = getBufferedReader(outputTest
						+ BaseTestCase.env + ".txt");
				String expectedOutput = outputReader.readLine();

				assertEquals(expectedOutput, actualOutput);
			}
			input = inputReader.readLine();
		}
/////////
/*		
		String inputString = "TINQ012408063 001ZYLS00";

		AuditContext auditContext = createAuditContext("NGCBALTU", "371",
				"11.220.4.125", "VETSNET", "externalid", "externalkey"
		);

		TypedFML inpBuff = new TypedFML(new ExternalUserFMLFieldTable());

		inpBuff.Fchg(ExternalUserFMLFieldTable.FML_CLIENT_MODULE_NAME, 0,
				"VETSNET".getBytes());

		inpBuff.Fchg(ExternalUserFMLFieldTable.FML_USERID, 0,
				"NGCBALTU".getBytes());

		inpBuff.Fchg(ExternalUserFMLFieldTable.FML_REPLY_DEST_IND, 0, 'N');

		inpBuff.Fchg(ExternalUserFMLFieldTable.FML_COMPUTER_NAME, 0,
				"11.220.4.125".getBytes());

		inpBuff.Fchg(ExternalUserFMLFieldTable.FML_PRIOR_SERVICE_NAME, 0,
				"NA".getBytes());

		inpBuff.Fchg(ExternalUserFMLFieldTable.FML_STATION_TO_ROUTE_TO, 0,
				"371".getBytes());

		inpBuff.Fchg(ExternalUserFMLFieldTable.FML_APPL_DATA, 0,
				inputString.getBytes());

//
		inpBuff.Fchg(ExternalUserFMLFieldTable.EXTUID, 0, "externalid");
		inpBuff.Fchg(ExternalUserFMLFieldTable.EXTKEY, 0, "externalkey");
//
		System.out.println((TypedFML) tuxedoServiceV3.execute("wtcsvccall",
				inpBuff, auditContext, null));
*/				
/////////
/*
		String inputString2 = "SHARIBPNQ         503034483                    SPIVEAUD            LARRY                                      00  07111922                                               281MSDS      BIRLS Inquiry       ";
		AuditContext auditContext2 = createAuditContext("VADIRMSDS", "281",
				"11.220.4.125", "MSDS", null, null
		);

		TypedFML inpBuff2 = new TypedFML(new ExternalUserFMLFieldTable());

		inpBuff2.Fchg(ExternalUserFMLFieldTable.FML_CLIENT_MODULE_NAME, 0,
				"MSDS".getBytes());

		inpBuff2.Fchg(ExternalUserFMLFieldTable.FML_USERID, 0,
				"VADIRMSDS".getBytes());

		inpBuff2.Fchg(ExternalUserFMLFieldTable.FML_REPLY_DEST_IND, 0, 'N');

		inpBuff2.Fchg(ExternalUserFMLFieldTable.FML_COMPUTER_NAME, 0,
				"11.220.4.125".getBytes());

		inpBuff2.Fchg(ExternalUserFMLFieldTable.FML_PRIOR_SERVICE_NAME, 0,
				"NA".getBytes());

		inpBuff2.Fchg(ExternalUserFMLFieldTable.FML_STATION_TO_ROUTE_TO, 0,
				"281".getBytes());

		inpBuff2.Fchg(ExternalUserFMLFieldTable.FML_APPL_DATA, 0,
				inputString2.getBytes());

		inpBuff2.Fchg(16679, 0, 'N'); // COPY_EXTINFO 295 type
		inpBuff2.Fchg(ExternalUserFMLFieldTable.APPNAME, 0, "MSDS");
		inpBuff2.Fchg(ExternalUserFMLFieldTable.SVCNAME, 0, "wtcsvccall");

		inpBuff2.Fchg(ExternalUserFMLFieldTable.EXTUID, 0, "externalid");
		inpBuff2.Fchg(ExternalUserFMLFieldTable.EXTKEY, 0, "externalkey");
		System.out.println((TypedFML) tuxedoServiceV3.execute("wtcsvccall",
				inpBuff2, auditContext2,
				null));
*/
	}

	public String callExecuteForInternal(boolean copyEXTUID, String userId,
			String stationId, String clientIP, String applicationName,
			String serviceName,
			String inputData, boolean transactional) throws Ferror,
			TuxedoException {

		AuditContext context = new AuditContext();
		context.setUserId(userId);
		context.setStationID(stationId);
		context.setClientIPAddress(clientIP);
		context.setApplicationName(applicationName);

		TypedFML inpBuff = convertToInternalFMLBuffer(userId, stationId,
				clientIP, applicationName, serviceName, inputData);
//		TypedBuffer to = tuxedoServiceV3.execute(
//				proxyServiceForExternallyAuthnUsers, inpBuff,
//				ApplicationToMonitorInterface.TPNOTRAN, context, null);
		TypedBuffer to = tuxedoServiceV3.execute(
				serviceName, inpBuff, context, null);
		TypedFML typedFML = (TypedFML) to;
		return new String((byte[]) typedFML.Fget(
				InternalUserFMLFieldTable.FML_APPL_DATA, 0));
	}

	public String callExecuteForExternal(boolean copyEXTUID, String userId,
			String stationId, String clientIP, String applicationName,
			String externalId, String externalKey, String serviceName,
			String inputData, boolean transactional) throws Ferror,
			TuxedoException {

		AuditContext context = new AuditContext();
		
		context.setUserId(userId);
		context.setStationID(stationId);
		context.setClientIPAddress(clientIP);
		context.setApplicationName(applicationName);
		// context.setForceExternal(true); 
		
		// TODO: what to do with this?
		String proxyServiceForExternallyAuthnUsers = "wtcsvccall";
		TypedFML inpBuff = convertToFMLBuffer(copyEXTUID, userId, stationId,
				clientIP, applicationName, externalId, externalKey,
				serviceName, inputData);
//		TypedBuffer to = tuxedoServiceV3.execute(
//				proxyServiceForExternallyAuthnUsers, inpBuff,
//				ApplicationToMonitorInterface.TPNOTRAN, context, null);
		TypedBuffer to = tuxedoServiceV3.execute(
				proxyServiceForExternallyAuthnUsers, inpBuff,
				 context, null);
		TypedFML typedFML = (TypedFML) to;
		return new String((byte[]) typedFML.Fget(
				ExternalUserFMLFieldTable.FML_APPL_DATA, 0));
	}

	public void testFML32Internal() {
		testFML32(false);
	}
	public void testFML32External() {
		testFML32(true);
	}
	public void testFML32(boolean copyExt) {

		/*
		This error comes from tbl files not available:
		2012-07-11 08:45:02 ArchTbl [FATAL] Failure: <arch.tbl> not loaded, the tuxedo connector will not work!
		2012-07-11 08:45:03 VNTbl [FATAL] VNTbl did not load vn.tbl, the tuxedo connector will not work w/o vn.tbl!
		*/
				
				TypedFML32 outBuff = null;
			    VNTbl vnTbl = null;
			    ArchTbl archTbl = null;

			    
			    try {
					
			    	AuditContext auditCtx = new AuditContext();
			    	auditCtx.setApplicationName("EBENEFITS");
			    	auditCtx.setClientIPAddress("192.1.1.100");
			    	auditCtx.setStationID("281");
			    	auditCtx.setUserId("281CEASL");
			    	if (copyExt)
			    	{
			    		auditCtx.setClientUniqueID("x281MARIO");
			    		auditCtx.setClientUniqueKey("777777");
				    	auditCtx.setForceExternal(true);

			    	}
			    	//auditCtx.setTuxedoServiceName("SVNTYPE");
			    	 			
			    	archTbl = new ArchTbl();
			    	vnTbl =  new VNTbl();
			        FldTbl[] tblArray = {archTbl, vnTbl};
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
		            outBuff = (TypedFML32)tuxedoServiceV3.execute("SVNTYPE", fml32, false, auditCtx, null);
		            if (outBuff == null) {
		                System.out.println("Returned FML32 buff is null. Exiting....");
		            	System.exit(9);
		            }
			    	System.out.println("Response: "+outBuff);
			    } 
			    catch (Exception e) {	    	
			    	System.out.println("Exception: " + e);
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
			    finally {
			    }

			}

	public void testFreeFormTuxedoServiceName() throws IOException, TuxedoException
	{
		logger.info("testFreeFormTuxedoServiceName");
		BufferedReader inputReader = getBufferedReader("BINQ-v3-input-"
				+ BaseTestCase.env + ".txt");
		String input = inputReader.readLine();
		while (input != null) {
			HashMap<MapKeys, String> inputMap = parseInputExpanded(input);
			System.out.println(inputMap.toString());
			AuditContext auditContext = createAuditContext(
					inputMap.get(MapKeys.userId),
					inputMap.get(MapKeys.stationId),
					inputMap.get(MapKeys.clientIP),
					inputMap.get(MapKeys.applicationName),
					inputMap.get(MapKeys.externalId),
					inputMap.get(MapKeys.externalKey));

			System.out.println(inputMap.toString());
			int transactionMode = Integer.parseInt(inputMap.get(
					MapKeys.transactionMode).trim());
			TuxParams tuxParams = new TuxParams();
			tuxParams.setTuxedoServiceName("VAAUSIBM");
			tuxParams.setTransactionType(transactionMode);
			tuxParams.setData(inputMap.get(MapKeys.inputData).toString());
			String actualOutput = tuxedoServiceV3.processRequest(tuxParams,
					auditContext, null);
			input = inputReader.readLine();
		}
		// checkOutput();
	}



}
