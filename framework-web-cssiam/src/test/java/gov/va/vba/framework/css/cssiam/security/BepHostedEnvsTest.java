package gov.va.vba.framework.css.cssiam.security;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BepHostedEnvsTest {
	
	BepHostedEnvs bepHostedEnvsInstance;
	
	private ArrayList<BepHostedEnv> bepHostedEnvs;

	private String envId = "NEWREL";
	private String url = "bepnrel.vba.va.gov";
	private boolean testEnv = true;
	
	@Before
	public void setUp() {
		bepHostedEnvsInstance = new BepHostedEnvs();
		bepHostedEnvs = new ArrayList<BepHostedEnv>();
		 
		BepHostedEnv bepEnv = new BepHostedEnv();
		bepEnv.setEnvId(envId);
		bepEnv.setUrl(url);
		bepEnv.setTestEnv(testEnv);
		 
		bepHostedEnvs.add(bepEnv);
		bepHostedEnvsInstance.setBepHostedEnvs(bepHostedEnvs);
	}
	
	@After
	public void tearDown() {
		bepHostedEnvsInstance = null;
		bepHostedEnvs = null;
	}
	
	@Test
	public void testSetters() {
		assertEquals(envId, bepHostedEnvsInstance.getBepHostedEnvs().get(0).getEnvId());
		assertEquals(testEnv, bepHostedEnvsInstance.getBepHostedEnvs().get(0).isTestEnv());
		assertEquals(url, bepHostedEnvsInstance.getBepHostedEnvs().get(0).getUrl());
	}
}
