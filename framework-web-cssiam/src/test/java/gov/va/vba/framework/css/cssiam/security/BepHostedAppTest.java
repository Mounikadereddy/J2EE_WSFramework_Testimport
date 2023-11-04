package gov.va.vba.framework.css.cssiam.security;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BepHostedAppTest {

	BepHostedApp bepHostedApp;
	
	private String appId = "WEAMS";
	private String url = "/weams/home.do";
	private String CSSId = "WEAMS";
	
	@Before
	public void setUp() {
		 bepHostedApp = new BepHostedApp();
		 bepHostedApp.setAppId(appId);
		 bepHostedApp.setCSSId(CSSId);
		 bepHostedApp.setUrl(url);
	}
	
	@After
	public void tearDown() {
		bepHostedApp = null;
	}
	
	@Test
	public void testSetters() {
		assertEquals(appId, bepHostedApp.getAppId());
		assertEquals(CSSId, bepHostedApp.getCSSId());
		assertEquals(url, bepHostedApp.getUrl());
	}
}
