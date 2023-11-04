package gov.va.vba.framework.css.cssiam.security;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BepHostedEnvTest {
	BepHostedEnv bepHostedEnv;
	
	private String envId = "NEWREL";
	private String url = "bepnrel.vba.va.gov";
	private boolean testEnv = true;
	
	@Before
	public void setUp() {
		 bepHostedEnv = new BepHostedEnv();
		 bepHostedEnv.setEnvId(envId);
		 bepHostedEnv.setTestEnv(testEnv);
		 bepHostedEnv.setUrl(url);
	}
	
	@After
	public void tearDown() {
		bepHostedEnv = null;
	}
	
	@Test
	public void testSettersAndGetters() {
		assertEquals(envId, bepHostedEnv.getEnvId());
		assertEquals(url, bepHostedEnv.getUrl());
		assertEquals(testEnv, bepHostedEnv.isTestEnv());
	}
}
