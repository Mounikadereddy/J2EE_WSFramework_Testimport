package gov.va.vba.framework.css.cssiam.security;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BepHostedMappedAppsTest {
	
	BepHostedMappedApps bepHostedApp;
	
	private ArrayList<BepHostedApp> bepHostedMappedApps;

	private String appId = "WEAMS";
	private String url = "/weams/home.do";
	private String CSSId = "WEAMS";
	
	@Before
	public void setUp() {
		 bepHostedApp = new BepHostedMappedApps();
		 bepHostedMappedApps = new ArrayList<BepHostedApp>();
		 
		 BepHostedApp bepApp = new BepHostedApp();
		 bepApp.setAppId(appId);
		 bepApp.setCSSId(CSSId);
		 bepApp.setUrl(url);
		 bepHostedMappedApps.add(bepApp);
		 bepHostedApp.setBepHostedMappedApps(bepHostedMappedApps);
	}
	
	@After
	public void tearDown() {
		bepHostedApp = null;
		bepHostedMappedApps = null;
	}
	
	@Test
	public void testSetters() {
		assertEquals(appId, bepHostedApp.getBepHostedMappedApps().get(0).getAppId());
		assertEquals(CSSId, bepHostedApp.getBepHostedMappedApps().get(0).getCSSId());
		assertEquals(url, bepHostedApp.getBepHostedMappedApps().get(0).getUrl());
	}
}
