package gov.va.vba.framework.services;


import gov.va.vba.framework.BaseTestCase;
import gov.va.vba.framework.EJBTestCase;
import gov.va.vba.framework.common.AuditContext;
import gov.va.vba.framework.domain.vo.ServiceVO;
import gov.va.vba.framework.domain.vo.ServiceVO.TuxedoService;
import gov.va.vba.framework.logging.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;

import javax.naming.NamingException;

import org.apache.commons.lang.StringUtils;

import weblogic.rmi.extensions.PortableRemoteObject;
import weblogic.wtc.jatmi.Ferror;

public class TuxedoServiceV2ExtFMLTest extends EJBTestCase {
	public static Logger logger = Logger.getLogger(TuxedoServiceV2ExtFMLTest.class);

	private enum MapKeys {
		description, copyEXTUID, userId, expectedReturnCode, stationId, clientIP, applicationName, externalId, externalKey, inputData, serviceName, transaction
	};
	private TuxedoServiceRemoteV2 tuxedoServiceV2 = createTuxedoServiceRemoteV2();

	public TuxedoServiceV2ExtFMLTest() {
	}

	public void testWtcSvcCall_execute_longComputerName() throws IOException, Ferror {
		try {
			logger.info("testWtcSvcCall_execute_longComputerName");
			BufferedReader inputReader = getBufferedReader("901_error-input-"
					+ BaseTestCase.env + ".txt");
			String input=inputReader.readLine();
			while (input != null) {
				HashMap<MapKeys, String> inputMap = parseInputExpanded(input);
				
				AuditContext context = createAuditContext(
						inputMap.get(MapKeys.userId),
						inputMap.get(MapKeys.stationId), 
						"Long Computer Na",
						inputMap.get(MapKeys.applicationName),
						inputMap.get(MapKeys.externalId),
						inputMap.get(MapKeys.externalKey));

				
				ServiceVO valueObject = new ServiceVO(context.getUserId(), 
						context.getStationID(), 
						context.getClientIPAddress(),
						context.getApplicationName(), 
						getService(inputMap.get(MapKeys.serviceName)));
				valueObject.setData(inputMap.get(MapKeys.inputData));
				valueObject.setExternalId(context.getClientUniqueID());
				valueObject.setExternalKey(context.getClientUniqueKey());


				tuxedoServiceV2.execute(valueObject,
						context, 
						null);

				input = inputReader.readLine();
			}
			assertTrue(false); //It should error out
		} catch (TuxedoException  te) {
			logger.debug("Tuxedo Exception - Base ErrorCode ["+te.getErrorCode() +
					"], Base ErrorId ["+te.getExceptionId() +
					"], TuxedoExcpetion ErrorCode ["+te.getTuxedoErrorCode() + 
					"], TuxedoException Error Message ["+te.getTuxedoErrorMessage() + 
					"]");
			assertTrue(te instanceof TuxedoException);
			assertTrue(((TuxedoException) te)
					.getMessage()
					.contains(
							"cannot be null or empty, and length cannot be greater than  15"));
		} 	
	}

	public void testWtcSvcCall_execute_longApplicationName() throws IOException, Ferror {
		try {
			logger.info("testWtcSvcCall_execute_longApplicationName");
			BufferedReader inputReader = getBufferedReader("901_error-input-"
					+ BaseTestCase.env + ".txt");
			String input=inputReader.readLine();
			while (input != null) {
				HashMap<MapKeys, String> inputMap = parseInputExpanded(input);
				
				AuditContext context = createAuditContext(
						inputMap.get(MapKeys.userId),
						inputMap.get(MapKeys.stationId), 
						inputMap.get(MapKeys.clientIP),
						"Long application name greater than thirty",//inputMap.get(MapKeys.applicationName),
						inputMap.get(MapKeys.externalId),
						inputMap.get(MapKeys.externalKey));

				
				ServiceVO valueObject = new ServiceVO(context.getUserId(), 
						context.getStationID(), 
						context.getClientIPAddress(),
						context.getApplicationName(), 
						getService(inputMap.get(MapKeys.serviceName)));
				valueObject.setData(inputMap.get(MapKeys.inputData));
				valueObject.setExternalId(context.getClientUniqueID());
				valueObject.setExternalKey(context.getClientUniqueKey());


				tuxedoServiceV2.execute(valueObject,
						context, 
						null);

				input = inputReader.readLine();
			}
			assertTrue(false); //It should error out
		} catch (TuxedoException  te) {
			logger.debug("Tuxedo Exception - Base ErrorCode ["+te.getErrorCode() +
					"], Base ErrorId ["+te.getExceptionId() +
					"], TuxedoExcpetion ErrorCode ["+te.getTuxedoErrorCode() + 
					"], TuxedoException Error Message ["+te.getTuxedoErrorMessage() + 
					"]");
			assertTrue(te instanceof TuxedoException);
			assertTrue(((TuxedoException) te)
					.getMessage()
					.contains(
							"cannot be null or empty, and length cannot be greater than  30"));
		} 	
	}

	public void testWtcSvcCall_execute_longExternalUID() throws IOException, Ferror {
		try {
			logger.info("testWtcSvcCall_execute_longExternalUID");
			BufferedReader inputReader = getBufferedReader("901_error-input-"
					+ BaseTestCase.env + ".txt");
			String input=inputReader.readLine();
			while (input != null) {
				HashMap<MapKeys, String> inputMap = parseInputExpanded(input);
				
				AuditContext context = createAuditContext(
						inputMap.get(MapKeys.userId),
						inputMap.get(MapKeys.stationId), 
						inputMap.get(MapKeys.clientIP),
						inputMap.get(MapKeys.applicationName),
						"Long External UID grater than forty characters",//inputMap.get(MapKeys.externalId),
						inputMap.get(MapKeys.externalKey));

				
				ServiceVO valueObject = new ServiceVO(context.getUserId(), 
						context.getStationID(), 
						context.getClientIPAddress(),
						context.getApplicationName(), 
						getService(inputMap.get(MapKeys.serviceName)));
				valueObject.setData(inputMap.get(MapKeys.inputData));
				valueObject.setExternalId(context.getClientUniqueID());
				valueObject.setExternalKey(context.getClientUniqueKey());


				tuxedoServiceV2.execute(valueObject,
						context, 
						null);

				input = inputReader.readLine();
			}
			assertTrue(false); //It should error out
		} catch (TuxedoException  te) {
			logger.debug("Tuxedo Exception - Base ErrorCode ["+te.getErrorCode() +
					"], Base ErrorId ["+te.getExceptionId() +
					"], TuxedoExcpetion ErrorCode ["+te.getTuxedoErrorCode() + 
					"], TuxedoException Error Message ["+te.getTuxedoErrorMessage() + 
					"]");
			assertTrue(te instanceof TuxedoException);
			assertTrue(((TuxedoException) te)
					.getMessage()
					.contains(
							"cannot be null or empty, and length cannot be greater than  40"));
		} 	
	}
	
	public void testWtcSvcCall_execute_longExternalKey() throws IOException, Ferror {
		try {
			logger.info("testWtcSvcCall_execute_longExternalKey");
			BufferedReader inputReader = getBufferedReader("901_error-input-"
					+ BaseTestCase.env + ".txt");
			String input=inputReader.readLine();
			while (input != null) {
				HashMap<MapKeys, String> inputMap = parseInputExpanded(input);
				
				AuditContext context = createAuditContext(
						inputMap.get(MapKeys.userId),
						inputMap.get(MapKeys.stationId), 
						inputMap.get(MapKeys.clientIP),
						inputMap.get(MapKeys.applicationName),
						inputMap.get(MapKeys.externalId),
						"Long External Key grater than forty characters");//inputMap.get(MapKeys.externalKey));

				
				ServiceVO valueObject = new ServiceVO(context.getUserId(), 
						context.getStationID(), 
						context.getClientIPAddress(),
						context.getApplicationName(), 
						getService(inputMap.get(MapKeys.serviceName)));
				valueObject.setData(inputMap.get(MapKeys.inputData));
				valueObject.setExternalId(context.getClientUniqueID());
				valueObject.setExternalKey(context.getClientUniqueKey());


				tuxedoServiceV2.execute(valueObject,
						context, 
						null);

				input = inputReader.readLine();
			}
			assertTrue(false); //It should error out
		} catch (TuxedoException  te) {
			logger.debug("Tuxedo Exception - Base ErrorCode ["+te.getErrorCode() +
					"], Base ErrorId ["+te.getExceptionId() +
					"], TuxedoExcpetion ErrorCode ["+te.getTuxedoErrorCode() + 
					"], TuxedoException Error Message ["+te.getTuxedoErrorMessage() + 
					"]");
			assertTrue(te instanceof TuxedoException);
			assertTrue(((TuxedoException) te)
					.getMessage()
					.contains(
							"cannot be null or empty, and length cannot be greater than  40"));
		} 	
	}

	public void testWtcSvcCall_executeBooleanTransaction_longExternalKey() throws IOException, Ferror {
		try {
			logger.info("testWtcSvcCall_executeBooleanTransaction_longExternalKey");
			BufferedReader inputReader = getBufferedReader("901_error-input-"
					+ BaseTestCase.env + ".txt");
			String input=inputReader.readLine();
			while (input != null) {
				HashMap<MapKeys, String> inputMap = parseInputExpanded(input);
				
				AuditContext context = createAuditContext(
						inputMap.get(MapKeys.userId),
						inputMap.get(MapKeys.stationId), 
						inputMap.get(MapKeys.clientIP),
						inputMap.get(MapKeys.applicationName),
						inputMap.get(MapKeys.externalId),
						"Long External Key grater than forty characters");//inputMap.get(MapKeys.externalKey));

				
				ServiceVO valueObject = new ServiceVO(context.getUserId(), 
						context.getStationID(), 
						context.getClientIPAddress(),
						context.getApplicationName(), 
						getService(inputMap.get(MapKeys.serviceName)));
				valueObject.setData(inputMap.get(MapKeys.inputData));
				valueObject.setExternalId(context.getClientUniqueID());
				valueObject.setExternalKey(context.getClientUniqueKey());


				tuxedoServiceV2.execute(valueObject,true,
						context, 
						null);

				input = inputReader.readLine();
			}
			assertTrue(false); //It should error out
		} catch (TuxedoException  te) {
			logger.debug("Tuxedo Exception - Base ErrorCode ["+te.getErrorCode() +
					"], Base ErrorId ["+te.getExceptionId() +
					"], TuxedoExcpetion ErrorCode ["+te.getTuxedoErrorCode() + 
					"], TuxedoException Error Message ["+te.getTuxedoErrorMessage() + 
					"]");
			assertTrue(te instanceof TuxedoException);
			assertTrue(((TuxedoException) te)
					.getMessage()
					.contains(
							"cannot be null or empty, and length cannot be greater than  40"));
		} 	
	}
	
	public void testWtcSvcCall_executeIntTransaction_longExternalKey() throws IOException, Ferror {
		try {
			logger.info("testWtcSvcCall_executeIntTransaction_longExternalKey");
			BufferedReader inputReader = getBufferedReader("901_error-input-"
					+ BaseTestCase.env + ".txt");
			String input=inputReader.readLine();
			while (input != null) {
				HashMap<MapKeys, String> inputMap = parseInputExpanded(input);
				
				AuditContext context = createAuditContext(
						inputMap.get(MapKeys.userId),
						inputMap.get(MapKeys.stationId), 
						inputMap.get(MapKeys.clientIP),
						inputMap.get(MapKeys.applicationName),
						inputMap.get(MapKeys.externalId),
						"Long External Key grater than forty characters");//inputMap.get(MapKeys.externalKey));

				
				ServiceVO valueObject = new ServiceVO(context.getUserId(), 
						context.getStationID(), 
						context.getClientIPAddress(),
						context.getApplicationName(), 
						getService(inputMap.get(MapKeys.serviceName)));
				valueObject.setData(inputMap.get(MapKeys.inputData));
				valueObject.setExternalId(context.getClientUniqueID());
				valueObject.setExternalKey(context.getClientUniqueKey());


				tuxedoServiceV2.execute(valueObject,0,
						context, 
						null);

				input = inputReader.readLine();
			}
			assertTrue(false); //It should error out
		} catch (TuxedoException  te) {
			logger.debug("Tuxedo Exception - Base ErrorCode ["+te.getErrorCode() +
					"], Base ErrorId ["+te.getExceptionId() +
					"], TuxedoExcpetion ErrorCode ["+te.getTuxedoErrorCode() + 
					"], TuxedoException Error Message ["+te.getTuxedoErrorMessage() + 
					"]");
			assertTrue(te instanceof TuxedoException);
			assertTrue(((TuxedoException) te)
					.getMessage()
					.contains(
							"cannot be null or empty, and length cannot be greater than  40"));
		} 	
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

	public enum LongNameService implements TuxedoService {
		LONGERTHAN15SERVICENAME
	}
}
