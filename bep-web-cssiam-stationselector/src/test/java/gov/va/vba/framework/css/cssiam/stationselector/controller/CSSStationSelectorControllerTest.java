package gov.va.vba.framework.css.cssiam.stationselector.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;

import java.util.ArrayList;
import java.util.List;

import gov.va.vba.framework.auditing.AuditIDGenerator;
import gov.va.vba.framework.common.AuditContext;
import gov.va.vba.framework.css.cssiam.domain.entities.CssUser;
import gov.va.vba.framework.css.cssiam.domain.entities.UserStation;
import gov.va.vba.framework.css.cssiam.security.BepHostedEnv;
import gov.va.vba.framework.css.cssiam.security.BepHostedEnvs;
import gov.va.vba.framework.services.CommonSecurityServiceV2;

import javax.servlet.http.HttpServletRequest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Tests the BaseController class leveraging mock objects.
 * @author VHAISPAPOSTI
 *
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(AuditIDGenerator.class)
public class CSSStationSelectorControllerTest {

	private CSSStationSelectorController baseController = null;
	private String errorMessage = null;
	private String testString1 = null;
	private String modelKey = null;
	private String modelKey2 = null;
	private String vbaURL;
	private String isTest = null;
	
	/**
	 * Setup
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		baseController = mock(CSSStationSelectorController.class);
		errorMessage = "Test Error Message";
		vbaURL = "/vbaapps/security/home.html";
		isTest = "true";
	}

	/**
	 * Closout
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {	
		baseController = null;
		errorMessage = null;
		testString1 = null;
		modelKey = null;
		modelKey2 = null;
		vbaURL = null;
		isTest = null;
	}

	/**
	 * Test method for {@link gov.va.vba.framework.css.cssiam.bephostedtestapp.controller.BaseController#generateCssProfileReportBEP(org.springframework.ui.ModelMap, javax.servlet.http.HttpServletRequest)}.
	 */
	@Test
	public void testGenerateCssProfileReport() {
			
		Model model = mock(Model.class);
		ModelMap map = mock(ModelMap.class);
		HttpServletRequest request = mock(HttpServletRequest.class);
		testString1 = "failedCSSAuthz";		
		
		when(baseController.loadCSSAuthoeizationError(model,errorMessage,request)).thenReturn(testString1);
		assertEquals(baseController.loadCSSAuthoeizationError(model,errorMessage,request),testString1);

		// Essentially these are testing the same interface
		modelKey = "errorMessage";
		modelKey2 = "tryAgainURL";

		when(model.asMap()).thenReturn(map);
		
		when(map.get(modelKey)).thenReturn(errorMessage);
		assertEquals((String) model.asMap().get(modelKey),errorMessage);
		
		when(map.get(modelKey2)).thenReturn(vbaURL);
		assertEquals((String) model.asMap().get(modelKey2),vbaURL);	
	}
	
	@Test
	public void testLoadStationsPerCSSUserApplicationTestEnvOneStation() {
		Model model = mock(Model.class);
		HttpServletRequest request = mock(HttpServletRequest.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		CommonSecurityServiceV2 cssService = mock(CommonSecurityServiceV2.class);
		CssUser cssUser = mock(CssUser.class);
		PowerMockito.mockStatic(AuditIDGenerator.class);
		
		BepHostedEnvs bepHostedEnvs = new BepHostedEnvs();
		
		
		String username = "BGSREG01";
		String application = "WEAMS";
		String ipAddress = "12.0.0.1";
		String target = "http://bepdev.vba.va.gov/weams/home.do";

		BepHostedEnv env = new BepHostedEnv();
		env.setEnvId("DEVL");
		env.setTestEnv(false);
		env.setUrl("http://bepdev.vba.va.gov");
		ArrayList<BepHostedEnv> envs = new ArrayList<BepHostedEnv>();
		envs.add(env);
		bepHostedEnvs.setBepHostedEnvs(envs);
		
		UserStation userStation = new UserStation();
		userStation.setEnabled(true);
		userStation.setId("317");
		userStation.setName("St. Petesburg");
		userStation.setRole("SUPERVISOR");
		List<UserStation> userStations = new ArrayList<UserStation>();
		userStations.add(userStation);

		when(cssService.getCssUserStationsByApplication(anyString() , anyString(), any(AuditContext.class))).thenReturn(cssUser);
		when(cssUser.getUserStations()).thenReturn(userStations);
		when(cssUser.getSelectedStation()).thenReturn(userStation);
		when(AuditIDGenerator.generateAuditID()).thenReturn("123456789");
		
		CSSStationSelectorController controller = new CSSStationSelectorController();
		controller.set_securityService(cssService);
		controller.setBepHostedEnvs(bepHostedEnvs);
		
		String returnedString = controller.loadStationsPerCSSUserApplication(model, username, application, ipAddress, target, errorMessage, request, redirectAttributes);
		assertEquals("redirect:"+target, returnedString);
		verify(cssUser, Mockito.times(1)).setSelectedStation(userStation);;
		verify(redirectAttributes, Mockito.times(1)).addAttribute("stationId","317");
	}

}
