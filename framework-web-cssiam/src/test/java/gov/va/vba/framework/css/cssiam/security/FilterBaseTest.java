package gov.va.vba.framework.css.cssiam.security;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FilterBaseTest {
	
	String usernameHeader = "adSamAccountName"; 
	String applicationHeader = "HTTP_APPLICATIONNAME";
	String ipAddressHeader = "WL-Proxy-Client-IP";
	
	@Before
	public void setUp() {

	}
	
	@After
	public void tearDown() {

	}
	
	@Test
	public void testEnum() {
		assertEquals(usernameHeader,FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_USERNAME.toString());
		assertEquals(ipAddressHeader,FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_CLIENT_IP.toString());
	}
	
	@Test
	public void testEquals() {
		assertTrue(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_USERNAME.equalsName(usernameHeader));
		assertTrue(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_CLIENT_IP.equalsName(ipAddressHeader));
	}

}
