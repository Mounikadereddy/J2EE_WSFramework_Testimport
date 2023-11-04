package gov.va.vba.framework.services;

import gov.va.vba.framework.BaseTestCase;
import gov.va.vba.framework.domain.vo.ServiceVO;
import gov.va.vba.framework.esb.connectors.client.ExternalUserFMLFieldTable;
import gov.va.vba.framework.esb.connectors.client.TuxedoConnector;
import gov.va.vba.framework.logging.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

import org.apache.commons.lang.StringUtils;

import weblogic.wtc.jatmi.Ferror;
import weblogic.wtc.jatmi.TypedBuffer;
import weblogic.wtc.jatmi.TypedFML;

public class TuxedoErrorDetailTester extends BaseTestCase {
	public static Logger logger = Logger.getLogger(TuxedoServiceTest.class);

	public TuxedoErrorDetailTester() {
	}

/*
	public void testTuxedoErrorDetailEJB1() throws TuxedoException, IOException {
		try {
			logger.info("testGetVeteranBIRLSData");
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
				logger.debug("input  >" + input + "<");
				logger.debug("output >" + actualOutput + "<");
				assertEquals(expectedOutput, actualOutput);
				input = inputReader.readLine();
			}
		} catch (Exception e) {
			logger.error("", e);
		}
	}
	
    public void testExecutePIFInquiry() throws Exception {
		String tuxedoResult=getTuxedoService().executePIFinquiry("101", "x",
    			2, "10.220.4.125", "111220023", false);
    	System.out.println("tuxedoResult="+tuxedoResult);
    }
    public void testGetVeteranBIRLSData() throws Exception {
		String tuxedoResult=getTuxedoService().getVeteranBIRLSData("101", "101", "10.220.4.125", "SHARIBPNQ111220023");
    	System.out.println("tuxedoResult="+tuxedoResult);
		}
    public void testGetVeteranData() throws Exception {
		String tuxedoResult=getTuxedoService().getVeteranData("101", "x", 2L, "10.220.4.125", "111220023", false, true);
    	System.out.println("tuxedoResult="+tuxedoResult);
    }


	public void testTuxedoErrorDetailDirect1() throws TuxedoException, IOException {
		try {
			ServiceVO serviceVO = getServiceVO(
					"VADIRMSDS",
					"101",
					"10.222.154.65",
					"WSMS",
					"externalId",
					"12345678901234567890",
					"VAAUSIBM",
					"SHARIBPNQ         503034483                                                                                   00                                                         101SHARE     BIRLS Inquiry");
			String result = new TuxedoConnector().invokeTUXservice(serviceVO);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void testTuxedoErrorDetailDirect2() throws TuxedoException, IOException {
		try {
			ServiceVO serviceVO = getServiceVO(
					"VADIRMSDS",
					"101",
					"10.222.154.65",
					"WSMS",
					"externalId",
					"12345678901234567890",
					"VAAUSIBM",
					"SHARIBPNQ         503034483                                                                                   00                                                         101SHARE     BIRLS Inquiry");
			String result = new TuxedoConnector().execute(serviceVO);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void testTuxedoErrorDetailDirect3() throws TuxedoException, IOException {
		try {
			TypedBuffer typedBuffer = getTypedBuffer(
					"VADIRMSDS",
					"101",
					"10.222.154.65",
					"WSMS",
					"externalId",
					"12345678901234567890",
					"VAAUSIBM",
					"SHARIBPNQ         503034483                                                                                   00                                                         101SHARE     BIRLS Inquiry");
			TypedBuffer result = new TuxedoConnector().execute("VAAUSIBM",
					typedBuffer);
		} catch (Exception e) {
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
		return getTuxedoService().getVeteranBIRLSData(userId, stationId,
				clientIP, inputData);
	}

	private BufferedReader getBufferedReader(String path)
			throws FileNotFoundException {
		ClassLoader classLoader = this.getClass().getClassLoader();
		logger.info("path=" + path);
		logger.info("classLoader=" + classLoader);
		URL resource = classLoader.getResource(path);
		logger.info("resource=" + resource);
		String path2 = resource.getPath();
		logger.info("path2=" + path2);
		File file = new File(path2);
		logger.info("file=" + file);
		BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
		return bufferedReader;
	}

	private ServiceVO.TuxedoService getService(String serviceName) {
		ServiceVO.TuxedoService service = ServiceVO.SHAREService.valueOf(
				ServiceVO.SHAREService.class, serviceName);
		if (service != null) {
			return service;
		} else {
			throw new RuntimeException(
					"Need to add support in test class for serviceName: "
							+ serviceName);
		}
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

	private enum MapKeys {
		userId, expectedReturnCode, stationId, clientIP, applicationName, externalId, externalKey, inputData, serviceName
	};

*/	
	
}
