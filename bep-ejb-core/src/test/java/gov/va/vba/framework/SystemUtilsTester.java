package gov.va.vba.framework;

import gov.va.vba.framework.common.SystemUtils;
import gov.va.vba.framework.logging.Logger;

public class SystemUtilsTester extends BaseTestCase{
	public static Logger logger = Logger.getLogger(SystemUtilsTester.class);
	
	public void testGetFrameworkVersion()
	{
		String version=SystemUtils.getFrameworkVersion();
		//logger.debug("Framework version="+version);
		System.out.println("Framework version="+version);
	}
	
	public void testWSDLURLByEnvironment()
	{
		String urlString="http://"+SystemUtils.getLoadBalancingHost()+":"+SystemUtils.getLoadBalancingPort()+"/example.wsdl";
		System.out.println("wsdl url="+urlString);
		String urlString2=SystemUtils.getProperty(SystemUtils.Key.LOAD_BALANCING_PROTOCOL)+"://"+SystemUtils.getProperty(SystemUtils.Key.LOAD_BALANCING_HOST)+":"+SystemUtils.getProperty(SystemUtils.Key.LOAD_BALANCING_PORT)+"/example.wsdl";
		System.out.println("wsdl url="+urlString2);
		
	}
	
	public void testEmptyProperty()
	{
//		System.out.println("SystemUtils.getProperty(null)="+SystemUtils.getProperty(null));
		System.out.println("SystemUtils.getProperty(\"\")="+SystemUtils.getProperty(SystemUtils.Key.LOAD_BALANCING_HOST));
	}

	public void testEnvironment()
	{
//		System.out.println("SystemUtils.getProperty(null)="+SystemUtils.getProperty(null));
		System.out.println("SystemUtils.getProperty(\"\")="+SystemUtils.getProperty(SystemUtils.Key.ENVIRONMENT));
	}

	public void testMissingProperty()
	{
		System.out.println("SystemUtils.getProperty(\"not.there\")="+SystemUtils.getProperty(SystemUtils.Key.LOAD_BALANCING_HOST));
	}
	
	public void testBoolean()
	{
		boolean b=SystemUtils.getBooleanProperty(SystemUtils.Key.EXTERNAL);
		System.out.println("SystemUtils.getProperty(\"boolean\")="+b);
	}
	
	public void testAllProperties()
	{
		for (SystemUtils.Key key : SystemUtils.Key.values()) { 
			// do what you want
			System.out.println(key+"="+SystemUtils.getProperty(key));
		}
	}
	
	public void testErrorCondition()
	{
		boolean b=SystemUtils.getBooleanProperty(SystemUtils.Key.LOAD_BALANCING_HOST);
		System.out.println("SystemUtils.getProperty(\"boolean\")="+b);
	}

}
