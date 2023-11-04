package gov.va.vba.framework;

import gov.va.vba.framework.domain.vo.ServiceVO;
import gov.va.vba.framework.domain.vo.ServiceVO.TuxedoService;
import gov.va.vba.framework.esb.connectors.client.ExternalUserFMLFieldTable;
import gov.va.vba.framework.esb.connectors.client.InternalUserFMLFieldTable;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import junit.framework.TestCase;
import weblogic.wtc.gwt.XmlFmlCnv;
import weblogic.wtc.jatmi.Ferror;
import weblogic.wtc.jatmi.TypedFML;

public class BaseTestCase extends TestCase {

	private static String DEFAULT_ENV = "local";
	public static String env;
	protected static String BATCH_FILE_PATH;
	protected static ResourceBundle resourceBundle = null;

	private ClassLoader classLoader = this.getClass().getClassLoader();

	static {
		env = System.getProperty("env", DEFAULT_ENV);
		BATCH_FILE_PATH="/AdobeDoc/"+env+"/batch";
		System.out.println("env: " + env);
		try
		{
			resourceBundle = ResourceBundle.getBundle(env);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public BaseTestCase() {
		super();
	}

	public BaseTestCase(String arg0) {
		super(arg0);
	}

	protected static Map<String, String> convertResourceBundleToMap(
			ResourceBundle resource) {
		Map<String, String> map = new HashMap<String, String>();
		Enumeration<String> keys = resource.getKeys();
		while (keys.hasMoreElements()) {
			String key = keys.nextElement();
			map.put(key, resource.getString(key));
		}
		return map;
	}

	protected TypedFML convertToInternalFMLBuffer(String userId, String stationId, String clientIP, String applicationName, String serviceName, String inputData) throws Ferror
	{
		TuxedoService tuxedoService=getService(serviceName);
		ServiceVO valueObject = new ServiceVO(userId, stationId, clientIP, applicationName, tuxedoService);
		valueObject.setData(inputData);

		TypedFML inpBuff = new TypedFML(new InternalUserFMLFieldTable());
		inpBuff.Fchg(InternalUserFMLFieldTable.FML_CLIENT_MODULE_NAME, 0, valueObject.getServiceName().getBytes());
		inpBuff.Fchg(InternalUserFMLFieldTable.FML_USERID, 0, valueObject.getUserId().getBytes());

		inpBuff.Fchg(InternalUserFMLFieldTable.FML_REPLY_DEST_IND, 0, 'N');
		inpBuff.Fchg(InternalUserFMLFieldTable.FML_COMPUTER_NAME, 0, valueObject.getClientIPAddress().getBytes());

		inpBuff.Fchg(InternalUserFMLFieldTable.FML_PRIOR_SERVICE_NAME, 0, "NA".getBytes());
		inpBuff.Fchg(InternalUserFMLFieldTable.FML_STATION_TO_ROUTE_TO, 0, valueObject.getStationId().getBytes());

		inpBuff.Fchg(InternalUserFMLFieldTable.FML_APPL_DATA, 0, valueObject.getData().getBytes());

		return inpBuff;
	}

	protected TypedFML convertToFMLBuffer(boolean copyEXTUID, String userId, String stationId, String clientIP, String applicationName, String externalId, String externalKey, String serviceName, String inputData) throws Ferror
	{
		TuxedoService tuxedoService=getService(serviceName);
		ServiceVO valueObject = new ServiceVO(userId, stationId, clientIP, applicationName, tuxedoService);
		valueObject.setData(inputData);
		valueObject.setExternalId(externalId);
		valueObject.setExternalKey(externalKey);

		TypedFML inpBuff = new TypedFML(new ExternalUserFMLFieldTable());
		inpBuff.Fchg(ExternalUserFMLFieldTable.FML_CLIENT_MODULE_NAME, 0, valueObject.getServiceName().getBytes());
		inpBuff.Fchg(ExternalUserFMLFieldTable.FML_USERID, 0, valueObject.getUserId().getBytes());

		inpBuff.Fchg(ExternalUserFMLFieldTable.FML_REPLY_DEST_IND, 0, 'N');
		inpBuff.Fchg(ExternalUserFMLFieldTable.FML_COMPUTER_NAME, 0, valueObject.getClientIPAddress().getBytes());

		inpBuff.Fchg(ExternalUserFMLFieldTable.FML_PRIOR_SERVICE_NAME, 0, "NA".getBytes());
		inpBuff.Fchg(ExternalUserFMLFieldTable.FML_STATION_TO_ROUTE_TO, 0, valueObject.getStationId().getBytes());

		inpBuff.Fchg(ExternalUserFMLFieldTable.FML_APPL_DATA, 0, valueObject.getData().getBytes());
		inpBuff.Fchg(16679, 0, 'N'); // COPY_EXTINFO 295 type
		inpBuff.Fchg(ExternalUserFMLFieldTable.APPNAME, 0, valueObject.getApplicationName());
		inpBuff.Fchg(ExternalUserFMLFieldTable.SVCNAME, 0, valueObject.getService().toString().toLowerCase());

		if (copyEXTUID)
			inpBuff.Fchg(ExternalUserFMLFieldTable.EXTUID, 0, valueObject.getExternalId());
		inpBuff.Fchg(ExternalUserFMLFieldTable.EXTKEY, 0, valueObject.getExternalKey());

		return inpBuff;
	}

	protected ServiceVO.TuxedoService getService(String serviceName) {
		ServiceVO.TuxedoService service = null;
		try {
			service = ServiceVO.SHAREService.valueOf(ServiceVO.SHAREService.class, serviceName.toUpperCase());
		} catch (Exception e1) {
			try {
				service = ServiceVO.CommonService.valueOf(ServiceVO.CommonService.class, serviceName.toUpperCase());
			} catch (Exception e2) {
				service = null;
			}
		}
		if (service != null) {
			return service;
		} else {
			throw new RuntimeException("Need to add support in test class for serviceName: " + serviceName);
		}
	}
	
	protected BufferedReader getBufferedReader(String path)
			throws FileNotFoundException {
		System.out.println("Path="+path);
		String fullPath = classLoader.getResource(path).getPath();
		System.out.println("Full Path="+fullPath);
		File file = new File(fullPath);
		BufferedReader bufferedReader=null;
		if (file.exists()) {
			bufferedReader = new BufferedReader(new FileReader(file));
		} else {
			System.out.println("File "+fullPath+" does not exist.");			
		}
		return bufferedReader;
	}	
	/**
	 * Method to read the XML file which contains the test data
	 * @param fileName
	 * @return string data
	 */
	protected String readFileAsString(String fileName) throws java.io.IOException{
		
		String path = classLoader.getResource(fileName).getPath();
		System.out.println("Reading from file: "+path);
		File file= new File(classLoader.getResource(fileName).getPath());
	    byte[] buffer = new byte[(int) file.length()];
	    BufferedInputStream f = null;
	    try {
	        f = new BufferedInputStream(new FileInputStream(file));
	        f.read(buffer);
	    }
	    finally {
	        if (f != null) 
	        	try { 
	        		f.close(); 
	        	} 
	        catch (IOException ignored) {
	        	ignored.printStackTrace();
	        }
	    }
	    return new String(buffer);
	}
	protected String toXML(TypedFML buff) {
		
		return new XmlFmlCnv().FMLtoXML(buff, true, true, true, true);
	}

}
