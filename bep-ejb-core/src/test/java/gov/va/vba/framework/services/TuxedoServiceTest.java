package gov.va.vba.framework.services;

import gov.va.vba.framework.BaseTestCase;
import gov.va.vba.framework.EJBTestCase;
import gov.va.vba.framework.domain.vo.ServiceVO;
import gov.va.vba.framework.esb.connectors.client.ExternalUserFMLFieldTable;
import gov.va.vba.framework.logging.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;

import javax.naming.NamingException;

import org.apache.commons.lang.StringUtils;

import weblogic.rmi.extensions.PortableRemoteObject;
import weblogic.wtc.jatmi.Ferror;
import weblogic.wtc.jatmi.TypedBuffer;
import weblogic.wtc.jatmi.TypedFML;

public class TuxedoServiceTest extends EJBTestCase {
	private TuxedoServiceRemote tuxedoService = createTuxedoServiceRemote();

	private enum MapKeys {
		userId, expectedReturnCode, stationId, clientIP, applicationName, externalId, externalKey, inputData, serviceName
	};

	public TuxedoServiceTest() {
		super();
	}
	
	public TuxedoServiceTest(String arg0)
	{
		super(arg0);
	}

	private HashMap<MapKeys, String> parseInput(String input) {
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

	public void testCreateDependent() throws TuxedoException, IOException {
		System.out.println("testCreateDependent");
		BufferedReader inputReader = getBufferedReader("CDEP-input-"
				+ BaseTestCase.env + ".txt");
		BufferedReader outputReader = getBufferedReader("CDEP-output-"
				+ BaseTestCase.env + ".txt");
		String input = inputReader.readLine();
		while (input != null) {
			HashMap<MapKeys, String> inputMap = parseInput(input);
			String expectedOutput = outputReader.readLine();
			System.out.println("input  >" + input + "<");
			String actualOutput = createDependents(
					inputMap.get(MapKeys.userId),
					inputMap.get(MapKeys.stationId),
					inputMap.get(MapKeys.clientIP),
					inputMap.get(MapKeys.inputData));
			System.out.println("output >" + actualOutput + "<");
			assertEquals(expectedOutput, actualOutput);
			input = inputReader.readLine();
		}
	}

	public String createDependents(String userId, String stationId,
			String clientIP, String inputData) throws TuxedoException {
		return tuxedoService.createDependents(userId, stationId, clientIP,
				inputData);
	}

	public void testCreateVeteran() throws TuxedoException, IOException {
		try {

			System.out.println("testCreateVeteran");
			BufferedReader inputReader = getBufferedReader("BUPD-input-"
					+ BaseTestCase.env + ".txt");
			BufferedReader outputReader = getBufferedReader("BUPD-output-"
					+ BaseTestCase.env + ".txt");
			String input = inputReader.readLine();
			while (input != null) {
				HashMap<MapKeys, String> inputMap = parseInput(input);
				String expectedOutput = outputReader.readLine();
				System.out.println("input  >" + input + "<");
				String actualOutput = createVeteran(
						inputMap.get(MapKeys.userId),
						inputMap.get(MapKeys.stationId),
						inputMap.get(MapKeys.clientIP),
						inputMap.get(MapKeys.inputData));
				System.out.println("output >" + actualOutput + "<");
				assertTrue(actualOutput.startsWith(expectedOutput.substring(0,20)));
				input = inputReader.readLine();
			}
		} catch (Exception e) {
			if (e instanceof TuxedoException) {
				TuxedoException tex = (TuxedoException)e;
				System.out.println("Tuxedo Exception - Base ErrorCode ["+tex.getErrorCode() +
						"], Base ErrorId ["+tex.getExceptionId() +
						"], TuxedoExcpetion ErrorCode ["+tex.getTuxedoErrorCode() + 
						"], TuxedoException Error Message ["+tex.getTuxedoErrorMessage() + 
						"]");
			}
			e.printStackTrace();
		}
	}

	private String createVeteran(String userId, String stationId,
			String clientIP, String inputData) throws TuxedoException {
		return tuxedoService.createVeteran(userId, stationId, clientIP,
				inputData);
	}

	public void testGetVeteranBIRLSData() throws TuxedoException, IOException {
		try {
			System.out.println("testGetVeteranBIRLSData");
			BufferedReader inputReader = getBufferedReader("BINQ-input-"
					+ BaseTestCase.env + ".txt");
			BufferedReader outputReader = getBufferedReader("BINQ-output-"
					+ BaseTestCase.env + ".txt");
			String input = inputReader.readLine();
			while (input != null) {
				HashMap<MapKeys, String> inputMap = null;
				inputMap = parseInput(input);
				String expectedOutput = outputReader.readLine();
				String actualOutput = getVeteranBIRLSData(
						inputMap.get(MapKeys.userId),
						inputMap.get(MapKeys.stationId),
						inputMap.get(MapKeys.clientIP),
						inputMap.get(MapKeys.inputData));
				System.out.println("input  >" + input + "<");
				System.out.println("output >" + actualOutput + "<");
				
				String expectedReturnCode = inputMap
						.get(MapKeys.expectedReturnCode);
				if (!StringUtils.isEmpty(expectedReturnCode)) {
					assertTrue(actualOutput.indexOf(expectedReturnCode) >= 0);
				} else {
					assertEquals(expectedOutput, actualOutput);
				}
				
				input = inputReader.readLine();
			}
		} catch (Exception e) {
			if (e instanceof TuxedoException) {
				TuxedoException tex = (TuxedoException)e;
				System.out.println("Tuxedo Exception - Base ErrorCode ["+tex.getErrorCode() +
						"], Base ErrorId ["+tex.getExceptionId() +
						"], TuxedoExcpetion ErrorCode ["+tex.getTuxedoErrorCode() + 
						"], TuxedoException Error Message ["+tex.getTuxedoErrorMessage() + 
						"]");
			}

			e.printStackTrace();
		}
	}

	public void testTuxedoErrorDetail1() throws TuxedoException, IOException {
		try {

			System.out.println("testCreateVeteran");
			BufferedReader inputReader = getBufferedReader("BUPD-input-"
					+ BaseTestCase.env + ".txt");
			BufferedReader outputReader = getBufferedReader("BUPD-output-"
					+ BaseTestCase.env + ".txt");
			String input = inputReader.readLine();
			while (input != null) {
				HashMap<MapKeys, String> inputMap = parseInput(input);
				String expectedOutput = outputReader.readLine();
				System.out.println("input  >" + input + "<");
				String actualOutput = createVeteran(
						inputMap.get(MapKeys.userId),
						inputMap.get(MapKeys.stationId),
						inputMap.get(MapKeys.clientIP),
						inputMap.get(MapKeys.inputData));
				System.out.println("output >" + actualOutput + "<");
				assertTrue(actualOutput.startsWith(expectedOutput.substring(0,20)));
				input = inputReader.readLine();
			}
		} catch (Exception e) {
			if (e instanceof TuxedoException) {
				TuxedoException tex = (TuxedoException)e;
				System.out.println("Tuxedo Exception - Base ErrorCode ["+tex.getErrorCode() +
						"], Base ErrorId ["+tex.getExceptionId() +
						"], TuxedoExcpetion ErrorCode ["+tex.getTuxedoErrorCode() + 
						"], TuxedoException Error Message ["+tex.getTuxedoErrorMessage() + 
						"]");
			}
			e.printStackTrace();
		}
	}

	private TypedBuffer getTypedBuffer(String userId, String stationId,
			String clientIP, String applicationName, String externalId,
			String externalKey, String serviceName, String inputData)
			throws Ferror {

		ServiceVO valueObject = new ServiceVO(userId, stationId, clientIP,
				applicationName, getService(serviceName));
		valueObject.setData(inputData);
		valueObject.setExternalId(externalId);
		valueObject.setExternalKey(externalKey);

		TypedFML inpBuff = new TypedFML(new ExternalUserFMLFieldTable());
		inpBuff.Fchg(ExternalUserFMLFieldTable.FML_CLIENT_MODULE_NAME, 0,
				valueObject.getServiceName().getBytes());
		inpBuff.Fchg(ExternalUserFMLFieldTable.FML_USERID, 0, valueObject
				.getUserId().getBytes());

		inpBuff.Fchg(ExternalUserFMLFieldTable.FML_REPLY_DEST_IND, 0, 'N');
		inpBuff.Fchg(ExternalUserFMLFieldTable.FML_COMPUTER_NAME, 0,
				valueObject.getClientIPAddress().getBytes());

		inpBuff.Fchg(ExternalUserFMLFieldTable.FML_PRIOR_SERVICE_NAME, 0,
				"NA".getBytes());
		inpBuff.Fchg(ExternalUserFMLFieldTable.FML_STATION_TO_ROUTE_TO, 0,
				valueObject.getStationId().getBytes());

		inpBuff.Fchg(ExternalUserFMLFieldTable.FML_APPL_DATA, 0, valueObject
				.getData().getBytes());
		inpBuff.Fchg(16679, 0, 'N'); // COPY_EXTINFO 295 type
		inpBuff.Fchg(ExternalUserFMLFieldTable.APPNAME, 0,
				valueObject.getApplicationName());
		inpBuff.Fchg(ExternalUserFMLFieldTable.SVCNAME, 0, valueObject
				.getService().toString().toLowerCase());

		// if (copyEXTUID)
		inpBuff.Fchg(ExternalUserFMLFieldTable.EXTUID, 0,
				valueObject.getExternalId());
		inpBuff.Fchg(ExternalUserFMLFieldTable.EXTKEY, 0,
				valueObject.getExternalKey());
		return inpBuff;
	}

	private ServiceVO getServiceVO(String userId, String stationId,
			String clientIP, String applicationName, String externalId,
			String externalKey, String serviceName, String inputData) {
		ServiceVO valueObject = new ServiceVO(userId, stationId, clientIP,
				applicationName, getService(serviceName));
		valueObject.setData(inputData);
		valueObject.setExternalId(externalId);
		valueObject.setExternalKey(externalKey);
		return valueObject;
	}

	private String getVeteranBIRLSData(String userId, String stationId,
			String clientIP, String inputData) throws TuxedoException {
		return tuxedoService.getVeteranBIRLSData(userId, stationId, clientIP,
				inputData);
	}

	public void testExecuteServiceVO() throws TuxedoException, IOException {
		System.out.println("testExecuteServiceVO");
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
			System.out.println("input  >" + input + "<");
			System.out.println("output >" + actualOutput + "<");
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

	private String getExecuteServiceVO(String userId, String stationId,
			String clientIP, String applicationName, String externalId,
			String externalKey, String serviceName, String inputData)
			throws TuxedoException {
		ServiceVO valueObject = new ServiceVO(userId, stationId, clientIP,
				applicationName, getService(serviceName));
		valueObject.setData(inputData);
		valueObject.setExternalId(externalId);
		valueObject.setExternalKey(externalKey);

		return tuxedoService.execute(valueObject);
	}

	public void testExecuteTypedBuffer() throws Ferror, TuxedoException,
			IOException {
		System.out.println("testExecuteTypedBuffer");
		BufferedReader inputReader = getBufferedReader("BINQ-input-"
				+ BaseTestCase.env + ".txt");
		BufferedReader outputReader = getBufferedReader("BINQ-output-"
				+ BaseTestCase.env + ".txt");
		String input = inputReader.readLine();
		while (input != null) {
			HashMap<MapKeys, String> inputMap = parseInput(input);
			String expectedOutput = outputReader.readLine();
			String actualOutput = getExecuteTypedBuffer(
					inputMap.get(MapKeys.userId),
					inputMap.get(MapKeys.stationId),
					inputMap.get(MapKeys.clientIP),
					inputMap.get(MapKeys.applicationName),
					inputMap.get(MapKeys.externalId),
					inputMap.get(MapKeys.externalKey),
					inputMap.get(MapKeys.serviceName),
					inputMap.get(MapKeys.inputData), true);
			System.out.println("input  >" + input + "<");
			System.out.println("output >" + actualOutput + "<");

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

	public void testWtcSvcCallSuccess() {
		try {
			System.out
					.println(getExecuteTypedBuffer(
							"VADIRMSDS",
							"281",
							"11.220.4.125",
							"MSDS",
							"externalId",
							"12345678901234567890",
							"VAAUSIBM",
							"SHARIBPNQ         503034483                    SPIVEAUD            LARRY                                      00  07111922                                               281MSDS      BIRLS Inquiry",
							true));
		} catch (Exception e) {
			if (e instanceof TuxedoException) {
				TuxedoException tex = (TuxedoException)e;
				System.out.println("Tuxedo Exception - Base ErrorCode ["+tex.getErrorCode() +
						"], Base ErrorId ["+tex.getExceptionId() +
						"], TuxedoExcpetion ErrorCode ["+tex.getTuxedoErrorCode() + 
						"], TuxedoException Error Message ["+tex.getTuxedoErrorMessage() + 
						"]");
			}

			e.printStackTrace();
		}
	}

	public void testWtcSvcCall901() {
		try {
			getExecuteTypedBuffer(
					"101ASHINO",
					"101",
					"10.222.154.65",
					"WSMS",
					"externalId",
					"12345678901234567890",
					"VAAUSIBM",
					"SHARIBPNQ         503034483                                                                                   00                                                         101SHARE     BIRLS Inquiry",
					true);
		} catch (TuxedoException e) {
			System.out.println("Tuxedo Exception - Base ErrorCode ["+e.getErrorCode() +
					"], Base ErrorId ["+e.getExceptionId() +
					"], TuxedoExcpetion ErrorCode ["+e.getTuxedoErrorCode() + 
					"], TuxedoException Error Message ["+e.getTuxedoErrorMessage() + 
					"]");
			assertTrue(
					"TuxedoErrorDescription is not TuxedoServiceFoundButWrongApp",
					e.getTuxedoErrorMessage().equals(
							"TuxedoServiceFoundButWrongApp"));
			assertTrue("TuxedoErrorCode is not 901",
					e.getTuxedoErrorCode() == 901);
			// e.printStackTrace();
		} catch (Ferror e) {
			e.printStackTrace();
			assertNull("Ferror thrown", null);
		}
	}

	public void testWtcSvcCall902() {
		try {
			getExecuteTypedBuffer(
					"VADIRMSDS",
					"101",
					"10.222.154.65",
					"WSMSx",
					"externalId",
					"12345678901234567890",
					"VAAUSIBM",
					"SHARIBPNQ         503034483                                                                                   00                                                         101SHARE     BIRLS Inquiry",
					true);
		} catch (TuxedoException te) {
			System.out.println("Tuxedo Exception - Base ErrorCode ["+te.getErrorCode() +
					"], Base ErrorId ["+te.getExceptionId() +
					"], TuxedoExcpetion ErrorCode ["+te.getTuxedoErrorCode() + 
					"], TuxedoException Error Message ["+te.getTuxedoErrorMessage() + 
					"]");
			assertTrue("TuxedoErrorDescription is not TuxedoAppNameNotFound",
					te.getTuxedoErrorMessage().equals("TuxedoAppNameNotFound"));
			assertTrue("TuxedoErrorCode is not 902",
					te.getTuxedoErrorCode() == 902);
			// e.printStackTrace();
		} catch (Ferror e) {
			e.printStackTrace();
			assertNull("Ferror thrown", null);
		}
	}

	// reproducible only with change to main code
	/*
	 * public void testWtcSvcCall903() { try {
	 * getExecuteTypedBuffer("VADIRMSDS",
	 * "101","10.222.154.65","WSMS","externalId"
	 * ,"12345678901234567890","VAAUSIBMx",
	 * "SHARIBPNQ         503034483                                                                                   00                                                         101SHARE     BIRLS Inquiry"
	 * , true); } catch (Exception e) { e.printStackTrace(); } }
	 */
	/*
	 * // not needed, because already throws an exception public void
	 * testWtcSvcCallMinus1() { try {
	 * getExecuteTypedBuffer("VADIRMSDS","281","11.220.4.125"
	 * ,"MSDS","","12345678901234567890","VAAUSIBM",
	 * "SHARIBPNQ                                                                                                                                                                                          "
	 * ); } catch (Exception e) { e.printStackTrace(); } }
	 * 
	 * // not reproducible // not needed, because already throws an exception
	 * public void testWtcSvcCallMinus2() { try {
	 * getExecuteTypedBuffer("VADIRMSDS"
	 * ,"101","10.222.154.65","WSMS","externalId"
	 * ,"12345678901234567890","VAAUSIBM",
	 * "SHARIBPNQ         503034483                                                                                   00                                                         101SHARE     BIRLS Inquiry"
	 * ); } catch (Exception e) { e.printStackTrace(); } }
	 */
	private String getExecuteTypedBuffer(String userId, String stationId,
			String clientIP, String applicationName, String externalId,
			String externalKey, String serviceName, String inputData,
			boolean copyEXTUID) throws Ferror, TuxedoException {

		// TODO: what to do with this?
		String proxyServiceForExternallyAuthnUsers = "wtcsvccall";

		TypedFML inpBuff = convertToFMLBuffer(copyEXTUID, userId, stationId,
				clientIP, applicationName, externalId, externalKey,
				serviceName, inputData);
		TypedBuffer to = tuxedoService.execute(
				proxyServiceForExternallyAuthnUsers, inpBuff);
		TypedFML typedFML = (TypedFML) to;
		return new String((byte[]) typedFML.Fget(
				ExternalUserFMLFieldTable.FML_APPL_DATA, 0));
	}

	public TuxedoServiceRemote createTuxedoServiceRemote()
		{
		setupEJBClient();
		Object ref = null;
		try {
			ref = getJNDIContext().lookup("VbaTuxedoService#"
					+ TuxedoServiceRemote.class.getName());
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return (TuxedoServiceRemote) PortableRemoteObject
				.narrow(ref, TuxedoServiceRemote.class);
	}

}
