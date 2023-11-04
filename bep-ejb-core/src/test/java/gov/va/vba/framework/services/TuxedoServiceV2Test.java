package gov.va.vba.framework.services;


import gov.va.vba.framework.BaseTestCase;
import gov.va.vba.framework.EJBTestCase;
import gov.va.vba.framework.common.AuditContext;
import gov.va.vba.framework.domain.vo.ServiceVO;
import gov.va.vba.framework.esb.connectors.client.ExternalUserFMLFieldTable;
import gov.va.vba.framework.logging.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;

import javax.naming.NamingException;

import org.apache.commons.lang.StringUtils;
import org.junit.Ignore;

import weblogic.rmi.extensions.PortableRemoteObject;
import weblogic.wtc.gwt.XmlFmlCnv;
import weblogic.wtc.jatmi.ApplicationToMonitorInterface;
import weblogic.wtc.jatmi.Ferror;
import weblogic.wtc.jatmi.TypedBuffer;
import weblogic.wtc.jatmi.TypedFML;

public class TuxedoServiceV2Test extends EJBTestCase {
	public static Logger logger = Logger.getLogger(TuxedoServiceV2Test.class);

	private enum MapKeys {
		description, copyEXTUID, userId, expectedReturnCode, stationId, clientIP, applicationName, externalId, externalKey, inputData, serviceName, transaction
	};
	private TuxedoServiceRemoteV2 tuxedoServiceV2 = createTuxedoServiceRemoteV2();

	public TuxedoServiceV2Test() {
	}

	public void testWtcSvcCall901() throws IOException {
		try {
			logger.info("testWtcSvcCall901");
			BufferedReader inputReader = getBufferedReader("901_error-input-"
					+ BaseTestCase.env + ".txt");
			String input=inputReader.readLine();
			while (input != null) {
				HashMap<MapKeys, String> inputMap = parseInputExpanded(input);
				getExecuteTypedBuffer(
						Boolean.valueOf(inputMap.get(MapKeys.copyEXTUID)),
						inputMap.get(MapKeys.userId),
						inputMap.get(MapKeys.stationId),
						inputMap.get(MapKeys.clientIP),
						inputMap.get(MapKeys.applicationName),
						inputMap.get(MapKeys.externalId),
						inputMap.get(MapKeys.externalKey),
						inputMap.get(MapKeys.serviceName),
						inputMap.get(MapKeys.inputData),
						Boolean.valueOf(inputMap.get(MapKeys.transaction)));
				input = inputReader.readLine();
			}
		} catch (TuxedoException  te) {
			logger.debug("Tuxedo Exception - Base ErrorCode ["+te.getErrorCode() +
					"], Base ErrorId ["+te.getExceptionId() +
					"], TuxedoExcpetion ErrorCode ["+te.getTuxedoErrorCode() + 
					"], TuxedoException Error Message ["+te.getTuxedoErrorMessage() + 
					"]");
			assertTrue(
					"TuxedoErrorDescription is not "+TuxedoServiceFoundButWrongAppException.TUXEDO_ERROR_MESAGE+" but "+te.getTuxedoErrorMessage(),
					te.getTuxedoErrorMessage().equals(
							TuxedoServiceFoundButWrongAppException.TUXEDO_ERROR_MESAGE));
			assertTrue("TuxedoErrorCode is not "+TuxedoServiceFoundButWrongAppException.TUXEDO_ERROR_CODE+" but "+te.getTuxedoErrorCode(),
					te.getTuxedoErrorCode() == TuxedoServiceFoundButWrongAppException.TUXEDO_ERROR_CODE);
			//te.printStackTrace();
		} catch (Ferror e) {
			e.printStackTrace();
			assertNull("Ferror thrown", null);
		}

	}

	public void testWtcSvcCall902() throws IOException {
		try {
			logger.info("testWtcSvcCall902");
			BufferedReader inputReader = getBufferedReader("902_error-input-"
					+ BaseTestCase.env + ".txt");
			String input=inputReader.readLine();
			while (input != null) {
				HashMap<MapKeys, String> inputMap = parseInputExpanded(input);
				getExecuteTypedBuffer(
						Boolean.valueOf(inputMap.get(MapKeys.copyEXTUID)),
						inputMap.get(MapKeys.userId),
						inputMap.get(MapKeys.stationId),
						inputMap.get(MapKeys.clientIP),
						inputMap.get(MapKeys.applicationName),
						inputMap.get(MapKeys.externalId),
						inputMap.get(MapKeys.externalKey),
						inputMap.get(MapKeys.serviceName),
						inputMap.get(MapKeys.inputData),
						Boolean.valueOf(inputMap.get(MapKeys.transaction)));
				input = inputReader.readLine();
			}
		} catch (TuxedoException te) {
			logger.debug("Tuxedo Exception - Base ErrorCode ["+te.getErrorCode() +
					"], Base ErrorId ["+te.getExceptionId() +
					"], TuxedoExcpetion ErrorCode ["+te.getTuxedoErrorCode() + 
					"], TuxedoException Error Message ["+te.getTuxedoErrorMessage() + 
					"]");
			assertTrue(
					"TuxedoErrorDescription is not "+TuxedoAppNameNotFoundException.TUXEDO_ERROR_MESAGE+" but "+te.getTuxedoErrorMessage(),
					te.getTuxedoErrorMessage().equals(
							TuxedoAppNameNotFoundException.TUXEDO_ERROR_MESAGE));
			assertTrue("TuxedoErrorCode is not "+TuxedoAppNameNotFoundException.TUXEDO_ERROR_CODE+" but "+te.getTuxedoErrorCode(),
					te.getTuxedoErrorCode() == TuxedoAppNameNotFoundException.TUXEDO_ERROR_CODE);
			// e.printStackTrace();
		} catch (Ferror e) {
			e.printStackTrace();
			assertNull("Ferror thrown", null);
		}
	}

	public void testTransactionType() throws IOException {
		try {
			logger.info("testTransactionType");
			BufferedReader inputReader = getBufferedReader("int_transactiontype-input-"
					+ BaseTestCase.env + ".txt");
			String input=inputReader.readLine();
			while (input != null) {
				HashMap<MapKeys, String> inputMap = parseInputExpanded(input);
				getExecuteTypedBuffer(
						Boolean.valueOf(inputMap.get(MapKeys.copyEXTUID)),
						inputMap.get(MapKeys.userId),
						inputMap.get(MapKeys.stationId),
						inputMap.get(MapKeys.clientIP),
						inputMap.get(MapKeys.applicationName),
						inputMap.get(MapKeys.externalId),
						inputMap.get(MapKeys.externalKey),
						inputMap.get(MapKeys.serviceName),
						inputMap.get(MapKeys.inputData),
						Integer.parseInt(inputMap.get(MapKeys.transaction)));
				input = inputReader.readLine();
			}
		} catch (TuxedoException e) {
			logger.debug("Tuxedo Exception - Base ErrorCode ["+e.getErrorCode() +
					"], Base ErrorId ["+e.getExceptionId() +
					"], TuxedoExcpetion ErrorCode ["+e.getTuxedoErrorCode() + 
					"], TuxedoException Error Message ["+e.getTuxedoErrorMessage() + 
					"]");
			e.printStackTrace();
			assertNull("TuxedoException thrown", null);
		} catch (Ferror e) {
			e.printStackTrace();
			assertNull("Ferror thrown", null);
		}
	}

	
	public void testTransactional() throws IOException {
		try {
			logger.info("testTransactional");
			BufferedReader inputReader = getBufferedReader("boolean_transactional-input-"
					+ BaseTestCase.env + ".txt");
			String input=inputReader.readLine();
			while (input != null) {
				HashMap<MapKeys, String> inputMap = parseInputExpanded(input);
				getExecuteTypedBuffer(
						Boolean.valueOf(inputMap.get(MapKeys.copyEXTUID)),
						inputMap.get(MapKeys.userId),
						inputMap.get(MapKeys.stationId),
						inputMap.get(MapKeys.clientIP),
						inputMap.get(MapKeys.applicationName),
						inputMap.get(MapKeys.externalId),
						inputMap.get(MapKeys.externalKey),
						inputMap.get(MapKeys.serviceName),
						inputMap.get(MapKeys.inputData),
						Boolean.valueOf(inputMap.get(MapKeys.transaction)));
				input = inputReader.readLine();
			}
		} catch (TuxedoException e) {
			logger.debug("Tuxedo Exception - Base ErrorCode ["+e.getErrorCode() +
					"], Base ErrorId ["+e.getExceptionId() +
					"], TuxedoExcpetion ErrorCode ["+e.getTuxedoErrorCode() + 
					"], TuxedoException Error Message ["+e.getTuxedoErrorMessage() + 
					"]");
			/* e.printStackTrace();
			assertNull("TuxedoException thrown", null); */
		} catch (Ferror e) {
			/* e.printStackTrace();
			assertNull("Ferror thrown", null); */
		}
	}

	public void testDefaultTransactional() throws IOException {
		try {
			logger.info("testDefaultTransactional");
			BufferedReader inputReader = getBufferedReader("default_transactional-input-"
					+ BaseTestCase.env + ".txt");
			String input=inputReader.readLine();
			while (input != null) {
				HashMap<MapKeys, String> inputMap = parseInputExpanded(input);
				getExecuteTypedBuffer(
						Boolean.valueOf(inputMap.get(MapKeys.copyEXTUID)),
						inputMap.get(MapKeys.userId),
						inputMap.get(MapKeys.stationId),
						inputMap.get(MapKeys.clientIP),
						inputMap.get(MapKeys.applicationName),
						inputMap.get(MapKeys.externalId),
						inputMap.get(MapKeys.externalKey),
						inputMap.get(MapKeys.serviceName),
						inputMap.get(MapKeys.inputData));
				input = inputReader.readLine();
			}
		} catch (TuxedoException e) {
			logger.debug("Tuxedo Exception - Base ErrorCode ["+e.getErrorCode() +
					"], Base ErrorId ["+e.getExceptionId() +
					"], TuxedoExcpetion ErrorCode ["+e.getTuxedoErrorCode() + 
					"], TuxedoException Error Message ["+e.getTuxedoErrorMessage() + 
					"]");
			e.printStackTrace();
			assertNull("TuxedoException thrown", null);
		} catch (Ferror e) {
			e.printStackTrace();
			assertNull("Ferror thrown", null);
		}
	}

	public HashMap<MapKeys, String> parseInput(String input) {
		logger.debug("input  >" + input + "<");
		String[] fields = StringUtils.split(input, "~");
		HashMap<MapKeys, String> map = new HashMap<MapKeys, String>();
		map.put(MapKeys.userId, fields[0]);
		map.put(MapKeys.expectedReturnCode, fields[1]);
		map.put(MapKeys.stationId, fields[2]);
		map.put(MapKeys.clientIP, fields[3]);
		map.put(MapKeys.applicationName, fields[4]);
		map.put(MapKeys.externalId, fields[5]);
		map.put(MapKeys.externalKey, fields[6]);
		map.put(MapKeys.serviceName, fields[7]);
		map.put(MapKeys.inputData, fields[8]);
		return map;
	}

	public HashMap<MapKeys, String> parseInputExpanded(String input) {
		logger.debug("input  >" + input + "<");
		String[] fields = StringUtils.split(input, "~");
		HashMap<MapKeys, String> map = new HashMap<MapKeys, String>();
		map.put(MapKeys.description, fields[0]);
		map.put(MapKeys.copyEXTUID, fields[1]);
		map.put(MapKeys.userId, fields[2]);
		map.put(MapKeys.expectedReturnCode, fields[3]);
		map.put(MapKeys.stationId, fields[4]);
		map.put(MapKeys.clientIP, fields[5]);
		map.put(MapKeys.applicationName, fields[6]);
		map.put(MapKeys.externalId, fields[7]);
		map.put(MapKeys.externalKey, fields[8]);
		map.put(MapKeys.serviceName, fields[9]);
		map.put(MapKeys.inputData, fields[10]);
		map.put(MapKeys.transaction, fields[11]);
		return map;
	}

	public void testCreateDependent() throws TuxedoException, IOException {
		logger.info("testCreateDependent");
		BufferedReader inputReader = getBufferedReader("CDEP-input-"
				+ BaseTestCase.env + ".txt");
		BufferedReader outputReader = getBufferedReader("CDEP-output-"
				+ BaseTestCase.env + ".txt");
		String input = inputReader.readLine();
		while (input != null) {
			HashMap<MapKeys, String> inputMap = parseInput(input);
			String expectedOutput = outputReader.readLine();
			String actualOutput = createDependents(
					inputMap.get(MapKeys.userId),
					inputMap.get(MapKeys.stationId),
					inputMap.get(MapKeys.clientIP),
					inputMap.get(MapKeys.inputData));
			logger.debug("output >" + actualOutput + "<");
			assertEquals(expectedOutput, actualOutput);
			input = inputReader.readLine();
		}
	}

	public String createDependents(String userId, String stationId,
			String clientIP, String inputData) throws TuxedoException {
		AuditContext context = new AuditContext();
		context.setUserId(userId);
		context.setStationID(stationId);
		context.setClientIPAddress(clientIP);
		context.setApplicationName("junit");
		String ret = null;
		try {
			ret = tuxedoServiceV2.createDependents(inputData, context,
					null);

		} catch (Throwable e) {
			if (e instanceof TuxedoException) {
				TuxedoException tex = (TuxedoException)e;
				logger.debug("Tuxedo Exception - Base ErrorCode ["+tex.getErrorCode() +
						"], Base ErrorId ["+tex.getExceptionId() +
						"], TuxedoExcpetion ErrorCode ["+tex.getTuxedoErrorCode() + 
						"], TuxedoException Error Message ["+tex.getTuxedoErrorMessage() + 
						"]");
			}
			e.printStackTrace();
		}
		return ret;
	}

	public void testCreateVeteran() throws TuxedoException, IOException {
		logger.info("testCreateVeteran");
		BufferedReader inputReader = getBufferedReader("BUPD-input-"
				+ BaseTestCase.env + ".txt");
		BufferedReader outputReader = getBufferedReader("BUPD-output-"
				+ BaseTestCase.env + ".txt");
		String input = inputReader.readLine();
		while (input != null) {
			HashMap<MapKeys, String> inputMap = parseInput(input);
			String expectedOutput = outputReader.readLine();
			String actualOutput = createVeteran(inputMap.get(MapKeys.userId),
					inputMap.get(MapKeys.stationId),
					inputMap.get(MapKeys.clientIP),
					inputMap.get(MapKeys.inputData));
			logger.debug("output >" + actualOutput + "<");
			assertTrue(actualOutput.startsWith(expectedOutput.substring(0,20)));
			input = inputReader.readLine();
		}
	}

	public String createVeteran(String userId, String stationId,
			String clientIP, String inputData) throws TuxedoException {
		AuditContext context = new AuditContext();
		context.setUserId(userId);
		context.setStationID(stationId);
		context.setClientIPAddress(clientIP);
		context.setApplicationName("junit");
		return tuxedoServiceV2.createVeteran(inputData, context, null);
	}

	//JG
	public void testGetVeteranBIRLSData() throws TuxedoException, IOException {
		logger.info("testGetVeteranBIRLSData");
		BufferedReader inputReader = getBufferedReader("BINQ-input-"
				+ BaseTestCase.env + ".txt");
		BufferedReader outputReader = getBufferedReader("BINQ-output-"
				+ BaseTestCase.env + ".txt");
		String input = inputReader.readLine();
		while (input != null) {
			HashMap<MapKeys, String> inputMap = parseInput(input);
			String expectedOutput = outputReader.readLine();
			String actualOutput = getVeteranBIRLSData(
					inputMap.get(MapKeys.userId),
					inputMap.get(MapKeys.stationId),
					inputMap.get(MapKeys.clientIP),
					inputMap.get(MapKeys.inputData));
			logger.info("  actual output >" + actualOutput + "<");
			logger.info("expected output >" + expectedOutput + "<");
			//assertEquals(expectedOutput, actualOutput);
			input = inputReader.readLine();
		}
	}

	public String getVeteranBIRLSData(String userId, String stationId,
			String clientIP, String inputData) throws TuxedoException {
		AuditContext context = new AuditContext();
		context.setUserId(userId);
		context.setStationID(stationId);
		context.setClientIPAddress(clientIP);
		context.setApplicationName("junit");
		return tuxedoServiceV2.getVeteranBIRLSData(inputData, context,
				null);
	}

	public void testExecuteServiceVO() throws TuxedoException, IOException {
		logger.info("testExecuteServiceVO");
		BufferedReader inputReader = getBufferedReader("BINQ-input-"
				+ BaseTestCase.env + ".txt");
		BufferedReader outputReader = getBufferedReader("BINQ-output-"
				+ BaseTestCase.env + ".txt");
		String input = inputReader.readLine();
		while (input != null) {
			HashMap<MapKeys, String> inputMap = parseInput(input);
			String expectedOutput = outputReader.readLine();
			String actualOutput = getExecuteServiceVO(
					inputMap.get(MapKeys.userId),
					inputMap.get(MapKeys.stationId),
					inputMap.get(MapKeys.clientIP),
					inputMap.get(MapKeys.applicationName),
					inputMap.get(MapKeys.externalId),
					inputMap.get(MapKeys.externalKey),
					inputMap.get(MapKeys.serviceName),
					inputMap.get(MapKeys.inputData));
			logger.debug("output >" + actualOutput + "<");
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

	public String getExecuteServiceVO(String userId, String stationId,
			String clientIP, String applicationName, String externalId,
			String externalKey, String serviceName, String inputData)
			throws TuxedoException {
		ServiceVO valueObject = new ServiceVO(userId, stationId, clientIP,
				applicationName, getService(serviceName));
		valueObject.setData(inputData);
		valueObject.setExternalId(externalId);
		valueObject.setExternalKey(externalKey);

		AuditContext context = new AuditContext();
		context.setUserId(userId);
		context.setStationID(stationId);
		context.setClientIPAddress(clientIP);
		context.setApplicationName("junit");

		return tuxedoServiceV2.execute(valueObject, ApplicationToMonitorInterface.TPNOTRAN, context, null);
	}

	public void testExecuteTypedBuffer() throws Ferror, TuxedoException,
			IOException {
		logger.info("testExecuteTypedBuffer");
		BufferedReader inputReader = getBufferedReader("BINQ-input-"
				+ BaseTestCase.env + ".txt");
		BufferedReader outputReader = getBufferedReader("BINQ-output-"
				+ BaseTestCase.env + ".txt");
		String input = inputReader.readLine();
		while (input != null) {
			HashMap<MapKeys, String> inputMap = parseInput(input);
			String expectedOutput = outputReader.readLine();
			String actualOutput = getExecuteTypedBuffer(true,
					inputMap.get(MapKeys.userId),
					inputMap.get(MapKeys.stationId),
					inputMap.get(MapKeys.clientIP),
					inputMap.get(MapKeys.applicationName),
					inputMap.get(MapKeys.externalId),
					inputMap.get(MapKeys.externalKey),
					inputMap.get(MapKeys.serviceName),
					inputMap.get(MapKeys.inputData), false);
			logger.debug("output >" + actualOutput + "<");

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

	public String getExecuteTypedBuffer(boolean copyEXTUID, String userId,
			String stationId, String clientIP, String applicationName,
			String externalId, String externalKey, String serviceName,
			String inputData) throws Ferror, TuxedoException {

		AuditContext context = new AuditContext();
		context.setUserId(userId);
		context.setStationID(stationId);
		context.setClientIPAddress(clientIP);
		context.setApplicationName("junit");

		// TODO: what to do with this?
		String proxyServiceForExternallyAuthnUsers = "wtcsvccall";
		TypedFML inpBuff = convertToFMLBuffer(copyEXTUID, userId, stationId,
				clientIP, applicationName, externalId, externalKey,
				serviceName, inputData);
		TypedBuffer to = tuxedoServiceV2.execute(
				proxyServiceForExternallyAuthnUsers, inpBuff, context, null);
		TypedFML typedFML = (TypedFML) to;
		return new String((byte[]) typedFML.Fget(
				ExternalUserFMLFieldTable.FML_APPL_DATA, 0));
	}

	public String getExecuteTypedBuffer(boolean copyEXTUID, String userId,
			String stationId, String clientIP, String applicationName,
			String externalId, String externalKey, String serviceName,
			String inputData, boolean transactional) throws Ferror,
			TuxedoException {

		AuditContext context = new AuditContext();
		context.setUserId(userId);
		context.setStationID(stationId);
		context.setClientIPAddress(clientIP);
		context.setApplicationName("junit");

		// TODO: what to do with this?
		String proxyServiceForExternallyAuthnUsers = "wtcsvccall";
		TypedFML inpBuff = convertToFMLBuffer(copyEXTUID, userId, stationId,
				clientIP, applicationName, externalId, externalKey,
				serviceName, inputData);
		TypedBuffer to = tuxedoServiceV2.execute(
				proxyServiceForExternallyAuthnUsers, inpBuff, transactional,
				context, null);
		TypedFML typedFML = (TypedFML) to;
		return new String((byte[]) typedFML.Fget(
				ExternalUserFMLFieldTable.FML_APPL_DATA, 0));
	}

	public String getExecuteTypedBuffer(boolean copyEXTUID, String userId,
			String stationId, String clientIP, String applicationName,
			String externalId, String externalKey, String serviceName,
			String inputData, int transactionMode) throws Ferror,
			TuxedoException {

		AuditContext context = new AuditContext();
		context.setUserId(userId);
		context.setStationID(stationId);
		context.setClientIPAddress(clientIP);
		context.setApplicationName("junit");

		// TODO: what to do with this?
		String proxyServiceForExternallyAuthnUsers = "wtcsvccall";
		TypedFML inpBuff = convertToFMLBuffer(copyEXTUID, userId, stationId,
				clientIP, applicationName, externalId, externalKey,
				serviceName, inputData);
		TypedBuffer to = tuxedoServiceV2.execute(
				proxyServiceForExternallyAuthnUsers, inpBuff, transactionMode,
				context, null);
		TypedFML typedFML = (TypedFML) to;
		return new String((byte[]) typedFML.Fget(
				ExternalUserFMLFieldTable.FML_APPL_DATA, 0));
	}

	public TuxedoServiceRemoteV2 createTuxedoServiceRemoteV2(){
		setupEJBClient();
		Object ref = null;
		TuxedoServiceRemoteV2 tuxedoServiceRemoteV2 = null;
		try {
			ref = getJNDIContext().lookup("VbaTuxedoServiceV2#"
					+ TuxedoServiceRemoteV2.class.getName());
		} catch (NamingException e) {
			e.printStackTrace();
		}
		tuxedoServiceRemoteV2 = (TuxedoServiceRemoteV2) PortableRemoteObject
				.narrow(ref, TuxedoServiceRemoteV2.class);
		return tuxedoServiceRemoteV2;
	}

}
