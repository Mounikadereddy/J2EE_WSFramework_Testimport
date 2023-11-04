package gov.va.vba.framework.css.cssiam.security;

import gov.va.vba.framework.auditing.AuditIDGenerator;
import gov.va.vba.framework.common.AuditContext;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest({AuditIDGenerator.class,AuthorizationContext.class})
public class AuthorizationContextTest {

	HttpServletRequest request;
	BepHostedMappedApps bepHostedMappedApps;
	BepHostedEnvs bepHostedEnvs;
	IamMandatoryTraits iamMandatoryTraits;
	
	String username = "VHAISPSMITHJ";
	String application = "WEAMS";
	String station = "101";
	String ipAdrdress = "127.0.0.1";
	String testUserId = "CSSTESTUSR01";
	boolean isAllowedUserOverwrite = true;
	AuditContext auditContext;
	String auditId = "1234556789";
	
	@Before
	public void setUp() {
		request = mock(HttpServletRequest.class);
		bepHostedMappedApps = mock(BepHostedMappedApps.class);
		bepHostedEnvs = mock(BepHostedEnvs.class);
		iamMandatoryTraits = mock(IamMandatoryTraits.class);
		
		PowerMockito.mockStatic(AuditIDGenerator.class);
		when(AuditIDGenerator.generateAuditID()).thenReturn("12345678");
	}
	
	@After
	public void cleanUp() {
		request = null;
		bepHostedMappedApps = null;
	}
	
	@Test
	public void testAuthorizationCtx() {
		AuthorizationContext authzCtx = new AuthorizationContext();
		setDefaultValues(authzCtx);
		assertEquals(authzCtx.getApplication(),application);
		assertEquals(authzCtx.getUsername(),username);
		assertEquals(authzCtx.getIpAddress(), ipAdrdress);
		assertEquals(authzCtx.getStation(),station);
		assertEquals(authzCtx.getTestUserId(),testUserId);
		assertEquals(authzCtx.allowedUserOverwrite, isAllowedUserOverwrite);
		
		assertEquals(authzCtx.getAuditContext().getApplicationName(), application);
		assertEquals(authzCtx.getAuditContext().getUserId(),username);
		assertEquals(authzCtx.getAuditContext().getClientIPAddress(), ipAdrdress);
		assertEquals(authzCtx.getAuditContext().getAuditID(),auditId);
	}
	
	private void setDefaultValues(AuthorizationContext authzCtx) {
		authzCtx.setUsername(username);
		authzCtx.setApplication(application);
		authzCtx.setStation(station);
		authzCtx.setIpAddress(ipAdrdress);
		authzCtx.setTestUserId(testUserId);
		authzCtx.setAllowedUserOverwrite(isAllowedUserOverwrite);
		AuditContext auditContext = new AuditContext();
		auditContext.setApplicationName(application);
		auditContext.setUserId(username);
		auditContext.setClientIPAddress(ipAdrdress);
		auditContext.setAuditID(auditId);
		authzCtx.setAuditContext(auditContext);		
	}

	@Test
	public void testMapRequestToAuthorizationCtxWEAMS() {
		AuthorizationContext authzCtx = new AuthorizationContext();
		StringBuffer sbuff = new StringBuffer();
		sbuff.append("http://bepnrel.vba.va.gov/weams/home.do");
		
		when(request.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_USERNAME.toString())).thenReturn("VHAISPSMITHJ");
		when(request.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_CLIENT_IP.toString())).thenReturn("127.0.0.1");
		when(request.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_TRANSACTIONID.toString())).thenReturn("0123456789");
		when(request.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_ISSUEINSTANT.toString())).thenReturn("017258369");
		when(request.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_AD_DOMAIN.toString())).thenReturn("vhamaster");
		when(request.getRequestURL()).thenReturn(sbuff);
		when(bepHostedMappedApps.getBepHostedMappedApps()).thenReturn(mockBEPHostedAppList());
		when(bepHostedEnvs.getBepHostedEnvs()).thenReturn(mockBEPHostedEnvList());
		when(iamMandatoryTraits.getIamMandatoryTraits()).thenReturn(mockIamMandatoryTraits());

		try {
			authzCtx.mapRequestToAuthorizationCtx(request, bepHostedMappedApps, bepHostedEnvs, iamMandatoryTraits);
			
			assertTrue("WEAMS".equals(authzCtx.getApplication()));
			assertTrue("VHAISPSMITHJ".equals(authzCtx.getUsername()));
			assertTrue("127.0.0.1".equals(authzCtx.getIpAddress()));
			assertTrue("12345678".equals(authzCtx.getAuditContext().getAuditID()));
			assertTrue("VHAISPSMITHJ".equals(authzCtx.getAuditContext().getUserId()));
			assertTrue("127.0.0.1".equals(authzCtx.getAuditContext().getClientIPAddress()));
			assertTrue("WEAMS".equals(authzCtx.getAuditContext().getApplicationName()));
			
		} catch (MissingHeaderException e) {
			System.out.println("***Catching MissingHeader Exception");
			e.printStackTrace();
			assertTrue(false);
		} catch (UnrecognizedApplicationException e) {
			System.out.println("***Catching UnrecognizedApplication Exception");
			e.printStackTrace();
			assertTrue(false);
		}
		
	}

	@Test
	public void testMapRequestToAuthorizationCtxWSMS() {
		AuthorizationContext authzCtx = new AuthorizationContext();
		StringBuffer sbuff = new StringBuffer();
		sbuff.append("http://bepnrel.vba.va.gov/wsms/home.do");
		
		when(request.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_USERNAME.toString())).thenReturn("VHAISPSMITHJ");
		when(request.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_CLIENT_IP.toString())).thenReturn("127.0.0.1");
		when(request.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_TRANSACTIONID.toString())).thenReturn("0123456789");
		when(request.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_ISSUEINSTANT.toString())).thenReturn("017258369");
		when(request.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_AD_DOMAIN.toString())).thenReturn("vhamaster");
		when(request.getRequestURL()).thenReturn(sbuff);
		when(bepHostedMappedApps.getBepHostedMappedApps()).thenReturn(mockBEPHostedAppList());
		when(bepHostedEnvs.getBepHostedEnvs()).thenReturn(mockBEPHostedEnvList());
		when(iamMandatoryTraits.getIamMandatoryTraits()).thenReturn(mockIamMandatoryTraits());

		try {
			authzCtx.mapRequestToAuthorizationCtx(request, bepHostedMappedApps, bepHostedEnvs, iamMandatoryTraits);
			
			assertTrue("WSMS".equals(authzCtx.getApplication()));
			assertTrue("VHAISPSMITHJ".equals(authzCtx.getUsername()));
			assertTrue("127.0.0.1".equals(authzCtx.getIpAddress()));
			assertTrue("12345678".equals(authzCtx.getAuditContext().getAuditID()));
			assertTrue("VHAISPSMITHJ".equals(authzCtx.getAuditContext().getUserId()));
			assertTrue("127.0.0.1".equals(authzCtx.getAuditContext().getClientIPAddress()));
			assertTrue("WSMS".equals(authzCtx.getAuditContext().getApplicationName()));
			
		} catch (MissingHeaderException e) {
			e.printStackTrace();
			assertTrue(false);

		} catch (UnrecognizedApplicationException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testMapRequestToAuthorizationCtxFOCAS() {
		AuthorizationContext authzCtx = new AuthorizationContext();
		StringBuffer sbuff = new StringBuffer();
		sbuff.append("http://bepnrel.vba.va.gov/focas/home.do");
		
		when(request.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_USERNAME.toString())).thenReturn("VHAISPSMITHJ");
		when(request.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_CLIENT_IP.toString())).thenReturn("127.0.0.1");
		when(request.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_TRANSACTIONID.toString())).thenReturn("0123456789");
		when(request.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_ISSUEINSTANT.toString())).thenReturn("017258369");
		when(request.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_AD_DOMAIN.toString())).thenReturn("vhamaster");
		when(request.getRequestURL()).thenReturn(sbuff);
		when(bepHostedMappedApps.getBepHostedMappedApps()).thenReturn(mockBEPHostedAppList());
		when(bepHostedEnvs.getBepHostedEnvs()).thenReturn(mockBEPHostedEnvList());
		when(iamMandatoryTraits.getIamMandatoryTraits()).thenReturn(mockIamMandatoryTraits());

		try {
			authzCtx.mapRequestToAuthorizationCtx(request, bepHostedMappedApps, bepHostedEnvs, iamMandatoryTraits);
			
			assertTrue("FOCAS".equals(authzCtx.getApplication()));
			assertTrue("VHAISPSMITHJ".equals(authzCtx.getUsername()));
			assertTrue("127.0.0.1".equals(authzCtx.getIpAddress()));
			assertTrue("12345678".equals(authzCtx.getAuditContext().getAuditID()));
			assertTrue("VHAISPSMITHJ".equals(authzCtx.getAuditContext().getUserId()));
			assertTrue("127.0.0.1".equals(authzCtx.getAuditContext().getClientIPAddress()));
			assertTrue("FOCAS".equals(authzCtx.getAuditContext().getApplicationName()));
			
		} catch (MissingHeaderException e) {
			e.printStackTrace();
			assertTrue(false);
		} catch (UnrecognizedApplicationException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testMapRequestToAuthorizationCtxQAWEB() {
		AuthorizationContext authzCtx = new AuthorizationContext();
		StringBuffer sbuff = new StringBuffer();
		sbuff.append("http://bepnrel.vba.va.gov/QAWeb/onWelcomePage.do");
		
		when(request.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_USERNAME.toString())).thenReturn("VHAISPSMITHJ");
		when(request.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_CLIENT_IP.toString())).thenReturn("127.0.0.1");
		when(request.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_TRANSACTIONID.toString())).thenReturn("0123456789");
		when(request.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_ISSUEINSTANT.toString())).thenReturn("017258369");
		when(request.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_AD_DOMAIN.toString())).thenReturn("vhamaster");
		when(request.getRequestURL()).thenReturn(sbuff);
		when(bepHostedMappedApps.getBepHostedMappedApps()).thenReturn(mockBEPHostedAppList());
		when(bepHostedEnvs.getBepHostedEnvs()).thenReturn(mockBEPHostedEnvList());
		when(iamMandatoryTraits.getIamMandatoryTraits()).thenReturn(mockIamMandatoryTraits());

		try {
			authzCtx.mapRequestToAuthorizationCtx(request, bepHostedMappedApps, bepHostedEnvs, iamMandatoryTraits);
			
			assertTrue("QAWEB".equals(authzCtx.getApplication()));
			assertTrue("VHAISPSMITHJ".equals(authzCtx.getUsername()));
			assertTrue("127.0.0.1".equals(authzCtx.getIpAddress()));
			assertTrue("12345678".equals(authzCtx.getAuditContext().getAuditID()));
			assertTrue("VHAISPSMITHJ".equals(authzCtx.getAuditContext().getUserId()));
			assertTrue("127.0.0.1".equals(authzCtx.getAuditContext().getClientIPAddress()));
			assertTrue("QAWEB".equals(authzCtx.getAuditContext().getApplicationName()));
			
		} catch (MissingHeaderException e) {
			e.printStackTrace();
			assertTrue(false);
		} catch (UnrecognizedApplicationException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test (expected = MissingHeaderException.class)
	public void testMapRequestToAuthorizationCtxMissingUsername() throws MissingHeaderException {
		AuthorizationContext authzCtx = new AuthorizationContext();
		StringBuffer sbuff = new StringBuffer();
		sbuff.append("http://bepnrel.vba.va.gov/QAWeb/onWelcomePage.do");
		
		when(request.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_USERNAME.toString())).thenReturn(null);
		when(request.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_CLIENT_IP.toString())).thenReturn("127.0.0.1");
		when(request.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_TRANSACTIONID.toString())).thenReturn("0123456789");
		when(request.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_ISSUEINSTANT.toString())).thenReturn("017258369");
		when(request.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_AD_DOMAIN.toString())).thenReturn("vhamaster");
		when(request.getRequestURL()).thenReturn(sbuff);
		when(bepHostedMappedApps.getBepHostedMappedApps()).thenReturn(mockBEPHostedAppList());
		when(bepHostedEnvs.getBepHostedEnvs()).thenReturn(mockBEPHostedEnvList());
		when(iamMandatoryTraits.getIamMandatoryTraits()).thenReturn(mockIamMandatoryTraits());

		try {
			authzCtx.mapRequestToAuthorizationCtx(request, bepHostedMappedApps, bepHostedEnvs, iamMandatoryTraits);

		} catch (MissingHeaderException e) {
			assertTrue(true);
			throw e;
			
		} catch (UnrecognizedApplicationException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	@Test (expected = MissingHeaderException.class)
	public void testMapRequestToAuthorizationCtxMissingIP() throws MissingHeaderException {
		AuthorizationContext authzCtx = new AuthorizationContext();
		StringBuffer sbuff = new StringBuffer();
		sbuff.append("http://bepnrel.vba.va.gov/QAWeb/onWelcomePage.do");
		
		when(request.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_USERNAME.toString())).thenReturn("VHAISPSMITHJ");
		when(request.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_TRANSACTIONID.toString())).thenReturn("0123456789");
		when(request.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_ISSUEINSTANT.toString())).thenReturn("017258369");
		when(request.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_AD_DOMAIN.toString())).thenReturn("vhamaster");
		when(request.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_CLIENT_IP.toString())).thenReturn(null);
		when(request.getRequestURL()).thenReturn(sbuff);
		when(bepHostedMappedApps.getBepHostedMappedApps()).thenReturn(mockBEPHostedAppList());
		when(bepHostedEnvs.getBepHostedEnvs()).thenReturn(mockBEPHostedEnvList());
		when(iamMandatoryTraits.getIamMandatoryTraits()).thenReturn(mockIamMandatoryTraits());

		try {
			authzCtx.mapRequestToAuthorizationCtx(request, bepHostedMappedApps, bepHostedEnvs, iamMandatoryTraits);

		} catch (MissingHeaderException e) {
			assertTrue(true);
			throw e;
			
		} catch (UnrecognizedApplicationException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test (expected = MissingHeaderException.class)
	public void testMapRequestToAuthorizationCtxEmptyUsername() throws MissingHeaderException {
		AuthorizationContext authzCtx = new AuthorizationContext();
		StringBuffer sbuff = new StringBuffer();
		sbuff.append("http://bepnrel.vba.va.gov/QAWeb/onWelcomePage.do");
		
		when(request.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_USERNAME.toString())).thenReturn("");
		when(request.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_CLIENT_IP.toString())).thenReturn("127.0.0.1");
		when(request.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_TRANSACTIONID.toString())).thenReturn("0123456789");
		when(request.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_ISSUEINSTANT.toString())).thenReturn("017258369");
		when(request.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_AD_DOMAIN.toString())).thenReturn("vhamaster");
		when(request.getRequestURL()).thenReturn(sbuff);
		when(bepHostedMappedApps.getBepHostedMappedApps()).thenReturn(mockBEPHostedAppList());
		when(bepHostedEnvs.getBepHostedEnvs()).thenReturn(mockBEPHostedEnvList());
		when(iamMandatoryTraits.getIamMandatoryTraits()).thenReturn(mockIamMandatoryTraits());

		try {
			authzCtx.mapRequestToAuthorizationCtx(request, bepHostedMappedApps, bepHostedEnvs, iamMandatoryTraits);

		} catch (MissingHeaderException e) {
			assertTrue(true);
			throw e;
			
		} catch (UnrecognizedApplicationException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	@Test (expected = MissingHeaderException.class)
	public void testMapRequestToAuthorizationCtxEmptyIP() throws MissingHeaderException {
		AuthorizationContext authzCtx = new AuthorizationContext();
		StringBuffer sbuff = new StringBuffer();
		sbuff.append("http://bepnrel.vba.va.gov/QAWeb/onWelcomePage.do");
		
		when(request.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_USERNAME.toString())).thenReturn("VHAISPSMITHJ");
		when(request.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_TRANSACTIONID.toString())).thenReturn("0123456789");
		when(request.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_ISSUEINSTANT.toString())).thenReturn("017258369");
		when(request.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_AD_DOMAIN.toString())).thenReturn("vhamaster");
		when(request.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_CLIENT_IP.toString())).thenReturn("");
		when(request.getRequestURL()).thenReturn(sbuff);
		when(bepHostedMappedApps.getBepHostedMappedApps()).thenReturn(mockBEPHostedAppList());
		when(bepHostedEnvs.getBepHostedEnvs()).thenReturn(mockBEPHostedEnvList());
		when(iamMandatoryTraits.getIamMandatoryTraits()).thenReturn(mockIamMandatoryTraits());

		try {
			authzCtx.mapRequestToAuthorizationCtx(request, bepHostedMappedApps, bepHostedEnvs, iamMandatoryTraits);

		} catch (MissingHeaderException e) {
			assertTrue(true);
			throw e;
			
		} catch (UnrecognizedApplicationException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test (expected = UnrecognizedApplicationException.class)
	public void testMapRequestToAuthorizationCtxBadApp() throws UnrecognizedApplicationException {
		AuthorizationContext authzCtx = new AuthorizationContext();
		StringBuffer sbuff = new StringBuffer();
		sbuff.append("http://bepnrel.vba.va.gov/Unknown/onWelcomePage.do");
		
		when(request.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_USERNAME.toString())).thenReturn("VHAISPSMITHJ");
		when(request.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_CLIENT_IP.toString())).thenReturn("127.0.0.1");
		when(request.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_TRANSACTIONID.toString())).thenReturn("0123456789");
		when(request.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_ISSUEINSTANT.toString())).thenReturn("017258369");
		when(request.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_AD_DOMAIN.toString())).thenReturn("vhamaster");
		when(request.getRequestURL()).thenReturn(sbuff);
		when(bepHostedMappedApps.getBepHostedMappedApps()).thenReturn(mockBEPHostedAppList());
		when(bepHostedEnvs.getBepHostedEnvs()).thenReturn(mockBEPHostedEnvList());
		when(iamMandatoryTraits.getIamMandatoryTraits()).thenReturn(mockIamMandatoryTraits());

		try {
			authzCtx.mapRequestToAuthorizationCtx(request, bepHostedMappedApps, bepHostedEnvs, iamMandatoryTraits);

		} catch (MissingHeaderException e) {
			e.printStackTrace();
			assertTrue(false);
		} catch (UnrecognizedApplicationException e) {
			assertTrue(true);
			throw e;
		}
	}
	
	@Test
	public void testMapRequestToAuthorizationCtxExtraTraits() {
		AuthorizationContext authzCtx = new AuthorizationContext();
		StringBuffer sbuff = new StringBuffer();
		sbuff.append("http://bepnrel.vba.va.gov/QAWeb/onWelcomePage.do");
		
		when(request.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_USERNAME.toString())).thenReturn("VHAISPSMITHJ");
		when(request.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_CLIENT_IP.toString())).thenReturn("127.0.0.1");
		when(request.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_TRANSACTIONID.toString())).thenReturn("123456789");
		when(request.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_ADEMAIL.toString())).thenReturn(null);
		when(request.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_TRANSACTIONID.toString())).thenReturn("0123456789");
		when(request.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_ISSUEINSTANT.toString())).thenReturn("017258369");
		when(request.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_AD_DOMAIN.toString())).thenReturn("vhamaster");
		when(request.getRequestURL()).thenReturn(sbuff);
		when(bepHostedMappedApps.getBepHostedMappedApps()).thenReturn(mockBEPHostedAppList());
		when(bepHostedEnvs.getBepHostedEnvs()).thenReturn(mockBEPHostedEnvList());
		when(iamMandatoryTraits.getIamMandatoryTraits()).thenReturn(mockIamMandatoryTraits());

		try {
			authzCtx.mapRequestToAuthorizationCtx(request, bepHostedMappedApps, bepHostedEnvs, iamMandatoryTraits);
			
			assertTrue("QAWEB".equals(authzCtx.getApplication()));
			assertTrue("VHAISPSMITHJ".equals(authzCtx.getUsername()));
			assertTrue("127.0.0.1".equals(authzCtx.getIpAddress()));
			assertTrue("12345678".equals(authzCtx.getAuditContext().getAuditID()));
			assertTrue("VHAISPSMITHJ".equals(authzCtx.getAuditContext().getUserId()));
			assertTrue("127.0.0.1".equals(authzCtx.getAuditContext().getClientIPAddress()));
			assertTrue("QAWEB".equals(authzCtx.getAuditContext().getApplicationName()));
			assertTrue("vhamaster".equals(authzCtx.getIamSiteMinderTraits().get(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_AD_DOMAIN)));
			assertTrue(authzCtx.getIamSiteMinderTraits().get(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_ADEMAIL)==null);
			//headers not in the request are mapped to null
			assertTrue(authzCtx.getIamSiteMinderTraits().get(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_DODEDIPNID)==null);
		} catch (MissingHeaderException e) {
			e.printStackTrace();
			assertTrue(false);
		} catch (UnrecognizedApplicationException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test (expected = MissingHeaderException.class)
	public void testMapRequestToAuthorizationCtxMissingMandatoryTrait() throws MissingHeaderException {
		AuthorizationContext authzCtx = new AuthorizationContext();
		StringBuffer sbuff = new StringBuffer();
		sbuff.append("http://bepnrel.vba.va.gov/QAWeb/onWelcomePage.do");
		
		when(request.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_USERNAME.toString())).thenReturn("VHAISPSMITHJ");
		when(request.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_CLIENT_IP.toString())).thenReturn("127.0.0.1");
		when(request.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_ADEMAIL.toString())).thenReturn(null);
		when(request.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_ISSUEINSTANT.toString())).thenReturn("017258369");
		when(request.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_AD_DOMAIN.toString())).thenReturn("vhamaster");
		when(request.getRequestURL()).thenReturn(sbuff);
		when(bepHostedMappedApps.getBepHostedMappedApps()).thenReturn(mockBEPHostedAppList());
		when(bepHostedEnvs.getBepHostedEnvs()).thenReturn(mockBEPHostedEnvList());
		when(iamMandatoryTraits.getIamMandatoryTraits()).thenReturn(mockIamMandatoryTraits());

		try {
			authzCtx.mapRequestToAuthorizationCtx(request, bepHostedMappedApps, bepHostedEnvs, iamMandatoryTraits);
			
			assertTrue("QAWEB".equals(authzCtx.getApplication()));
			assertTrue("VHAISPSMITHJ".equals(authzCtx.getUsername()));
			assertTrue("127.0.0.1".equals(authzCtx.getIpAddress()));
			assertTrue("12345678".equals(authzCtx.getAuditContext().getAuditID()));
			assertTrue("VHAISPSMITHJ".equals(authzCtx.getAuditContext().getUserId()));
			assertTrue("127.0.0.1".equals(authzCtx.getAuditContext().getClientIPAddress()));
			assertTrue("QAWEB".equals(authzCtx.getAuditContext().getApplicationName()));
			assertTrue("vhamaster".equals(authzCtx.getIamSiteMinderTraits().get(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_AD_DOMAIN)));
			assertTrue(authzCtx.getIamSiteMinderTraits().get(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_ADEMAIL)==null);
			//headers not in the request are mapped to null
			assertTrue(authzCtx.getIamSiteMinderTraits().get(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_DODEDIPNID)==null);
		} catch (MissingHeaderException e) {
			e.printStackTrace();
			assertTrue(e.getMessage().contains("SM_TRANSACTIONID"));
			throw e;
		} catch (UnrecognizedApplicationException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	
	@Test (expected = MissingHeaderException.class)
	public void testMapRequestToAuthorizationCtxEmptyMandatoryTrait() throws MissingHeaderException {
		AuthorizationContext authzCtx = new AuthorizationContext();
		StringBuffer sbuff = new StringBuffer();
		sbuff.append("http://bepnrel.vba.va.gov/QAWeb/onWelcomePage.do");
		
		when(request.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_USERNAME.toString())).thenReturn("VHAISPSMITHJ");
		when(request.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_CLIENT_IP.toString())).thenReturn("127.0.0.1");
		when(request.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_ADEMAIL.toString())).thenReturn(null);
		when(request.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_ISSUEINSTANT.toString())).thenReturn("017258369");
		when(request.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_AD_DOMAIN.toString())).thenReturn("vhamaster");
		when(request.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_TRANSACTIONID.toString())).thenReturn("");
		when(request.getRequestURL()).thenReturn(sbuff);
		when(bepHostedMappedApps.getBepHostedMappedApps()).thenReturn(mockBEPHostedAppList());
		when(bepHostedEnvs.getBepHostedEnvs()).thenReturn(mockBEPHostedEnvList());
		when(iamMandatoryTraits.getIamMandatoryTraits()).thenReturn(mockIamMandatoryTraits());

		try {
			authzCtx.mapRequestToAuthorizationCtx(request, bepHostedMappedApps, bepHostedEnvs, iamMandatoryTraits);
			
			assertTrue("QAWEB".equals(authzCtx.getApplication()));
			assertTrue("VHAISPSMITHJ".equals(authzCtx.getUsername()));
			assertTrue("127.0.0.1".equals(authzCtx.getIpAddress()));
			assertTrue("12345678".equals(authzCtx.getAuditContext().getAuditID()));
			assertTrue("VHAISPSMITHJ".equals(authzCtx.getAuditContext().getUserId()));
			assertTrue("127.0.0.1".equals(authzCtx.getAuditContext().getClientIPAddress()));
			assertTrue("QAWEB".equals(authzCtx.getAuditContext().getApplicationName()));
			assertTrue("vhamaster".equals(authzCtx.getIamSiteMinderTraits().get(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_AD_DOMAIN)));
			assertTrue(authzCtx.getIamSiteMinderTraits().get(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_ADEMAIL)==null);
			//headers not in the request are mapped to null
			assertTrue(authzCtx.getIamSiteMinderTraits().get(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_DODEDIPNID)==null);
		} catch (MissingHeaderException e) {
			e.printStackTrace();
			assertTrue(e.getMessage().contains("SM_TRANSACTIONID"));
			throw e;
		} catch (UnrecognizedApplicationException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test (expected = MissingHeaderException.class)
	public void testMapRequestToAuthorizationCtxNullMandatoryTrait() throws MissingHeaderException {
		AuthorizationContext authzCtx = new AuthorizationContext();
		StringBuffer sbuff = new StringBuffer();
		sbuff.append("http://bepnrel.vba.va.gov/QAWeb/onWelcomePage.do");
		
		when(request.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_USERNAME.toString())).thenReturn("VHAISPSMITHJ");
		when(request.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_CLIENT_IP.toString())).thenReturn("127.0.0.1");
		when(request.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_ADEMAIL.toString())).thenReturn(null);
		when(request.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_ISSUEINSTANT.toString())).thenReturn("017258369");
		when(request.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_AD_DOMAIN.toString())).thenReturn("vhamaster");
		when(request.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_TRANSACTIONID.toString())).thenReturn(null);
		when(request.getRequestURL()).thenReturn(sbuff);
		when(bepHostedMappedApps.getBepHostedMappedApps()).thenReturn(mockBEPHostedAppList());
		when(bepHostedEnvs.getBepHostedEnvs()).thenReturn(mockBEPHostedEnvList());
		when(iamMandatoryTraits.getIamMandatoryTraits()).thenReturn(mockIamMandatoryTraits());

		try {
			authzCtx.mapRequestToAuthorizationCtx(request, bepHostedMappedApps, bepHostedEnvs, iamMandatoryTraits);
			
			assertTrue("QAWEB".equals(authzCtx.getApplication()));
			assertTrue("VHAISPSMITHJ".equals(authzCtx.getUsername()));
			assertTrue("127.0.0.1".equals(authzCtx.getIpAddress()));
			assertTrue("12345678".equals(authzCtx.getAuditContext().getAuditID()));
			assertTrue("VHAISPSMITHJ".equals(authzCtx.getAuditContext().getUserId()));
			assertTrue("127.0.0.1".equals(authzCtx.getAuditContext().getClientIPAddress()));
			assertTrue("QAWEB".equals(authzCtx.getAuditContext().getApplicationName()));
			assertTrue("vhamaster".equals(authzCtx.getIamSiteMinderTraits().get(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_AD_DOMAIN)));
			assertTrue(authzCtx.getIamSiteMinderTraits().get(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_ADEMAIL)==null);
			//headers not in the request are mapped to null
			assertTrue(authzCtx.getIamSiteMinderTraits().get(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_DODEDIPNID)==null);
		} catch (MissingHeaderException e) {
			e.printStackTrace();
			assertTrue(e.getMessage().contains("SM_TRANSACTIONID"));
			throw e;
		} catch (UnrecognizedApplicationException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	
	@Test
	public void testMapRequestToAuthorizationCtxNoMandatoryDefined() throws MissingHeaderException {
		AuthorizationContext authzCtx = new AuthorizationContext();
		StringBuffer sbuff = new StringBuffer();
		sbuff.append("http://bepnrel.vba.va.gov/QAWeb/onWelcomePage.do");
		
		when(request.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_USERNAME.toString())).thenReturn("VHAISPSMITHJ");
		when(request.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_CLIENT_IP.toString())).thenReturn("127.0.0.1");
		when(request.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_ADEMAIL.toString())).thenReturn(null);
		when(request.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_ISSUEINSTANT.toString())).thenReturn("017258369");
		when(request.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_AD_DOMAIN.toString())).thenReturn("vhamaster");
		when(request.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_TRANSACTIONID.toString())).thenReturn(null);
		when(request.getRequestURL()).thenReturn(sbuff);
		when(bepHostedMappedApps.getBepHostedMappedApps()).thenReturn(mockBEPHostedAppList());
		when(bepHostedEnvs.getBepHostedEnvs()).thenReturn(mockBEPHostedEnvList());
		iamMandatoryTraits = null;

		try {
			authzCtx.mapRequestToAuthorizationCtx(request, bepHostedMappedApps, bepHostedEnvs, iamMandatoryTraits);
			
			assertTrue("QAWEB".equals(authzCtx.getApplication()));
			assertTrue("VHAISPSMITHJ".equals(authzCtx.getUsername()));
			assertTrue("127.0.0.1".equals(authzCtx.getIpAddress()));
			assertTrue("12345678".equals(authzCtx.getAuditContext().getAuditID()));
			assertTrue("VHAISPSMITHJ".equals(authzCtx.getAuditContext().getUserId()));
			assertTrue("127.0.0.1".equals(authzCtx.getAuditContext().getClientIPAddress()));
			assertTrue("QAWEB".equals(authzCtx.getAuditContext().getApplicationName()));
			assertTrue("vhamaster".equals(authzCtx.getIamSiteMinderTraits().get(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_AD_DOMAIN)));
			assertTrue(authzCtx.getIamSiteMinderTraits().get(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_ADEMAIL)==null);
			//headers not in the request are mapped to null
			assertTrue(authzCtx.getIamSiteMinderTraits().get(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_DODEDIPNID)==null);
		} catch (MissingHeaderException e) {
			e.printStackTrace();
			assertTrue(false);
		} catch (UnrecognizedApplicationException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	private ArrayList<BepHostedApp> mockBEPHostedAppList() {
		
		ArrayList<BepHostedApp> appList = new ArrayList<BepHostedApp>();
		
		BepHostedApp weams = new BepHostedApp();
		weams.setAppId("WEAMS");
		weams.setCSSId("WEAMS");
		weams.setUrl("weams/home.do");
		
		BepHostedApp wsms = new BepHostedApp();
		wsms.setAppId("WSMS");
		wsms.setCSSId("WSMS");
		wsms.setUrl("wsms/home.do");
		
		BepHostedApp focas = new BepHostedApp();
		focas.setAppId("FOCAS");
		focas.setCSSId("FOCAS");
		focas.setUrl("focas/home.do");
		
		BepHostedApp qaweb = new BepHostedApp();
		qaweb.setAppId("QaWeb");
		qaweb.setCSSId("QAWEB");
		qaweb.setUrl("QAWeb/onWelcomePage.do");

		appList.add(weams);
		appList.add(wsms);
		appList.add(focas);
		appList.add(qaweb);
		return appList;
	}
	
	private ArrayList<BepHostedEnv> mockBEPHostedEnvList() {		
		ArrayList<BepHostedEnv> envList = new ArrayList<BepHostedEnv>();
		
		BepHostedEnv newrel = new BepHostedEnv();
		newrel.setEnvId("NEWREL");
		newrel.setTestEnv(true);
		newrel.setUrl("bepnrel.vba.va.gov");
		
		BepHostedEnv dev = new BepHostedEnv();
		dev.setEnvId("DEV");
		dev.setTestEnv(true);
		dev.setUrl("bepdev.vba.va.gov");

		envList.add(newrel);
		envList.add(dev);
		
		return envList;
	}
	
	private ArrayList<IamTrait> mockIamMandatoryTraits() {		
		ArrayList<IamTrait> traitList = new ArrayList<IamTrait>();
		
		IamTrait trait1 = new IamTrait();
		trait1.setTraitId("SM_TRANSACTIONID");
		IamTrait trait2 = new IamTrait();
		trait2.setTraitId("IssueInstant");
		IamTrait trait3 = new IamTrait();
		trait3.setTraitId("adDomain");
		
		traitList.add(trait1);
		traitList.add(trait2);
		traitList.add(trait3);
		
		return traitList;
	}
}
