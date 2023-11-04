package gov.va.vba.framework.css.cssiam.security;

import gov.va.vba.framework.auditing.AuditIDGenerator;
import gov.va.vba.framework.common.SystemUtils;
import gov.va.vba.framework.esb.transformers.TuxedoSecurityProfile;
import gov.va.vba.framework.logging.Logger;
import gov.va.vba.framework.serverconfig.LoggerConfigurator;
import gov.va.vba.framework.web.security.UserContext;

import java.io.IOException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.mock;
import static org.mockito.Matchers.anyString;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest({AuditIDGenerator.class,Logger.class,CSSIAMAuthorizationFilter.class})
public class CSSIAMAuthorizationFilterTest {

	FilterConfig filterConfig = null;
	CSSIAMAuthorizationFilter filter;
	HttpServletRequest sreq;
	HttpServletResponse sresp;
	HttpSession session;
	FilterChain chain;
	ServletContext servletCtx1;
	ServletContext servletCtx2;
	RequestDispatcher requestDispatcher;
	Logger loggerSpy;
	Logger loggerUtils;
	Context context;
	AuthorizationContext authzCtx;
	TuxedoSecurityProfile tuxUserProfile;
	
	@Before
	public void setUp() {
		LoggerConfigurator.firstTimeSetup();
		filterConfig = mock(FilterConfig.class);
		sreq = mock(HttpServletRequest.class);
		sresp = mock(HttpServletResponse.class);
		session = mock(HttpSession.class);
		chain = mock(FilterChain.class);
		servletCtx1 = mock(ServletContext.class);
		servletCtx2 = mock(ServletContext.class);
		requestDispatcher = mock(RequestDispatcher.class);
		loggerSpy = spy(Logger.getLogger(CSSIAMAuthorizationFilter.class.getName()));
		loggerUtils = mock(Logger.class);
		context = mock(Context.class);
		authzCtx = mock(AuthorizationContext.class);
		tuxUserProfile = mock(TuxedoSecurityProfile.class);
		
		PowerMockito.mockStatic(AuditIDGenerator.class);
		when(AuditIDGenerator.generateAuditID()).thenReturn("12345678");
		PowerMockito.mockStatic(Logger.class);
		when(Logger.getLogger(SystemUtils.class)).thenReturn(loggerUtils);
		when(Logger.getLogger(anyString())).thenReturn(loggerSpy);

	}

	@After
	public void cleanUp() {
		filterConfig = null;
		sreq = null;
		sresp = null;
		chain = null;
		session = null;
		loggerSpy = null;
		requestDispatcher = null;
		servletCtx1 = null;
		servletCtx2 = null;
		filter.destroy();
	}

	@Test
	public void testLoadAppsToFilter() {
		
			when(filterConfig.getInitParameter(anyString())).thenReturn(null);
			
			filter = spy (new CSSIAMAuthorizationFilter());
			
			try {
				doReturn(context).when(filter).getInitialContext(null);
				filter.init(filterConfig);
			} catch (ServletException e) {
				e.printStackTrace();
				assertTrue(false);
			} catch (NamingException e) {
				e.printStackTrace();
				assertTrue(false);
			}
			BepHostedMappedApps bepHostedMappedApps = filter.getMappedApps();

			// A object was recovered from the file
			assertTrue(bepHostedMappedApps != null);

			ArrayList<BepHostedApp> apps = bepHostedMappedApps
					.getBepHostedMappedApps();

			// There is at least one app loaded from the file
			assertTrue(apps.size() > 0);

			boolean weams, wsms, qaweb, focas;
			weams = wsms = qaweb = focas = false;

			for (BepHostedApp app : apps) {
				// WEAMS
				if ("WEAMS".equals(app.getCSSId())) {
					weams = true;
				} else if ("WSMS".equals(app.getCSSId())) {
					// WSMS
					wsms = true;
				} else if ("QAWEB".equals(app.getCSSId())) {
					// QAWEB
					qaweb = true;
				} else if ("FOCAS".equals(app.getCSSId())) {
					// FOCAS
					focas = true;
				}
			}

			assertTrue(weams && wsms && qaweb && focas);
	}

	@Test
	public void testAuthzCtxToSessionWEAMS() {
		
		final ArgumentCaptor<AuthorizationContext> captor = ArgumentCaptor.forClass(AuthorizationContext.class);
		
		StringBuffer sbuff = new StringBuffer();
		sbuff.append("http://bepnrel.vba.va.gov/weams/home.do");
		
		when(sreq.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_USERNAME.toString())).thenReturn("VHAISPSMITHJ");
		when(sreq.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_CLIENT_IP.toString())).thenReturn("192.168.1.101");
		when(sreq.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_TRANSACTIONID.toString())).thenReturn("0123456789");
		when(sreq.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_ISSUEINSTANT.toString())).thenReturn("017258369");
		when(sreq.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_AD_DOMAIN.toString())).thenReturn("vhamaster");
		when(sreq.getRequestURL()).thenReturn(sbuff);
		when(sreq.getRequestURI()).thenReturn(sbuff.toString());
		when(sreq.getSession(true)).thenReturn(session);	
		when(sreq.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);
		when(filterConfig.getServletContext()).thenReturn(servletCtx1);
		when(servletCtx1.getContext(anyString())).thenReturn(servletCtx2);
		when(servletCtx2.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);

		filter = spy (new CSSIAMAuthorizationFilter());
		
		try {
			doReturn(context).when(filter).getInitialContext(null);
			filter.init(filterConfig);
		} catch (ServletException e) {
			e.printStackTrace();
			assertTrue(false);
		} catch (NamingException e) {
			e.printStackTrace();
			assertTrue(false);
		}
		
		try {
			filter.doFilter(sreq, sresp, chain);

			verify(session).setAttribute(anyString(), captor.capture());

			AuthorizationContext authzCtx =captor.getValue();
			
			assertTrue(authzCtx != null);
			
			assertTrue("WEAMS".equals(authzCtx.getApplication()));
			assertTrue("VHAISPSMITHJ".equals(authzCtx.getUsername()));
			assertTrue("192.168.1.101".equals(authzCtx.getIpAddress()));
			assertTrue("12345678".equals(authzCtx.getAuditContext().getAuditID()));
			
		} catch (ServletException e) {
			e.printStackTrace();
			assertTrue(false);
		} catch (IOException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testAuthzCtxToSessionWSMS() {
		
		final ArgumentCaptor<AuthorizationContext> captor = ArgumentCaptor.forClass(AuthorizationContext.class);
		
		StringBuffer sbuff = new StringBuffer();
		sbuff.append("http://bepnrel.vba.va.gov/wsms/home.do");
		
		when(sreq.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_USERNAME.toString())).thenReturn("VHAISPSMITHJ");
		when(sreq.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_CLIENT_IP.toString())).thenReturn("192.168.1.101");
		when(sreq.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_TRANSACTIONID.toString())).thenReturn("0123456789");
		when(sreq.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_ISSUEINSTANT.toString())).thenReturn("017258369");
		when(sreq.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_AD_DOMAIN.toString())).thenReturn("vhamaster");
		when(sreq.getRequestURL()).thenReturn(sbuff);
		when(sreq.getRequestURI()).thenReturn(sbuff.toString());
		when(sreq.getSession(true)).thenReturn(session);
		when(sreq.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);
		
		when(filterConfig.getServletContext()).thenReturn(servletCtx1);
		when(servletCtx1.getContext(anyString())).thenReturn(servletCtx2);
		when(servletCtx2.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);
		
		filter = spy (new CSSIAMAuthorizationFilter());

		try {
			doReturn(context).when(filter).getInitialContext(null);
			filter.init(filterConfig);
			filter.doFilter(sreq, sresp, chain);

			verify(session).setAttribute(anyString(), captor.capture());

			AuthorizationContext authzCtx =captor.getValue();
			
			assertTrue(authzCtx != null);
			
			assertTrue("WSMS".equals(authzCtx.getApplication()));
			assertTrue("VHAISPSMITHJ".equals(authzCtx.getUsername()));
			assertTrue("192.168.1.101".equals(authzCtx.getIpAddress()));
			assertTrue("12345678".equals(authzCtx.getAuditContext().getAuditID()));
			assertTrue("vhamaster".equals(authzCtx.getIamSiteMinderTraits().get(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_AD_DOMAIN)));
		} catch (ServletException e) {
			e.printStackTrace();
			assertTrue(false);
		} catch (IOException e) {
			e.printStackTrace();
			assertTrue(false);
		} catch (NamingException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	@Test
	public void testAuthzCtxToSessionFOCAS() {
		
		final ArgumentCaptor<AuthorizationContext> captor = ArgumentCaptor.forClass(AuthorizationContext.class);
		
		StringBuffer sbuff = new StringBuffer();
		sbuff.append("http://bepnrel.vba.va.gov/focas/home.do");
		
		when(sreq.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_USERNAME.toString())).thenReturn("VHAISPSMITHJ");
		when(sreq.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_CLIENT_IP.toString())).thenReturn("192.168.1.101");
		when(sreq.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_TRANSACTIONID.toString())).thenReturn("0123456789");
		when(sreq.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_ISSUEINSTANT.toString())).thenReturn("017258369");
		when(sreq.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_AD_DOMAIN.toString())).thenReturn("vhamaster");
		when(sreq.getRequestURL()).thenReturn(sbuff);
		when(sreq.getRequestURI()).thenReturn(sbuff.toString());
		when(sreq.getSession(true)).thenReturn(session);	
		when(sreq.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);
		when(filterConfig.getServletContext()).thenReturn(servletCtx1);
		when(servletCtx1.getContext(anyString())).thenReturn(servletCtx2);
		when(servletCtx2.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);

		filter = spy (new CSSIAMAuthorizationFilter());
		
		try {
			doReturn(context).when(filter).getInitialContext(null);
			filter.init(filterConfig);
			filter.doFilter(sreq, sresp, chain);

			verify(session).setAttribute(anyString(), captor.capture());

			AuthorizationContext authzCtx =captor.getValue();
			
			assertTrue(authzCtx != null);
			
			assertTrue("FOCAS".equals(authzCtx.getApplication()));
			assertTrue("VHAISPSMITHJ".equals(authzCtx.getUsername()));
			assertTrue("192.168.1.101".equals(authzCtx.getIpAddress()));
			assertTrue("12345678".equals(authzCtx.getAuditContext().getAuditID()));
		} catch (ServletException e) {
			e.printStackTrace();
			assertTrue(false);
		} catch (IOException e) {
			e.printStackTrace();
			assertTrue(false);
		} catch (NamingException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	@Test
	public void testAuthzCtxToSessionQAWEB() {
		
		final ArgumentCaptor<AuthorizationContext> captor = ArgumentCaptor.forClass(AuthorizationContext.class);
		
		StringBuffer sbuff = new StringBuffer();
		sbuff.append("http://bepnrel.vba.va.gov/QAWeb/onWelcomePage.do");
		
		when(sreq.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_USERNAME.toString())).thenReturn("VHAISPSMITHJ");
		when(sreq.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_CLIENT_IP.toString())).thenReturn("192.168.1.101");
		when(sreq.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_TRANSACTIONID.toString())).thenReturn("0123456789");
		when(sreq.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_ISSUEINSTANT.toString())).thenReturn("017258369");
		when(sreq.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_AD_DOMAIN.toString())).thenReturn("vhamaster");
		when(sreq.getRequestURL()).thenReturn(sbuff);
		when(sreq.getRequestURI()).thenReturn(sbuff.toString());
		when(sreq.getSession(true)).thenReturn(session);	
		when(sreq.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);
		when(filterConfig.getServletContext()).thenReturn(servletCtx1);
		when(servletCtx1.getContext(anyString())).thenReturn(servletCtx2);
		when(servletCtx2.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);
		
		filter = spy (new CSSIAMAuthorizationFilter());
	
		try {
			doReturn(context).when(filter).getInitialContext(null);
			filter.init(filterConfig);
			filter.doFilter(sreq, sresp, chain);

			verify(session).setAttribute(anyString(), captor.capture());

			AuthorizationContext authzCtx =captor.getValue();
			
			assertTrue(authzCtx != null);
			
			assertTrue("QAWEB".equals(authzCtx.getApplication()));
			assertTrue("VHAISPSMITHJ".equals(authzCtx.getUsername()));
			assertTrue("192.168.1.101".equals(authzCtx.getIpAddress()));
			assertTrue("12345678".equals(authzCtx.getAuditContext().getAuditID()));
		} catch (ServletException e) {
			e.printStackTrace();
			assertTrue(false);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test(expected = ServletException.class)
	public void testAuthzCtxMissingIPAddress() throws ServletException {
		
		StringBuffer sbuff = new StringBuffer();
		sbuff.append("http://bepnrel.vba.va.gov/QAWeb/onWelcomePage.do");
		
		when(sreq.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_USERNAME.toString())).thenReturn("VHAISPSMITHJ");
		when(sreq.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_CLIENT_IP.toString())).thenReturn(null);
		when(sreq.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_TRANSACTIONID.toString())).thenReturn("0123456789");
		when(sreq.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_ISSUEINSTANT.toString())).thenReturn("017258369");
		when(sreq.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_AD_DOMAIN.toString())).thenReturn("vhamaster");
		when(sreq.getRequestURL()).thenReturn(sbuff);
		when(sreq.getRequestURI()).thenReturn(sbuff.toString());
		when(sreq.getSession(true)).thenReturn(session);	
		when(filterConfig.getServletContext()).thenReturn(servletCtx1);
		when(servletCtx1.getContext(anyString())).thenReturn(servletCtx2);
		when(servletCtx2.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);
		
		filter = spy (new CSSIAMAuthorizationFilter());
		
		try {
			doReturn(context).when(filter).getInitialContext(null);
			filter.init(filterConfig);
			filter.doFilter(sreq, sresp, chain);
			verify(loggerSpy).error("Missing mandatory header REMOTE_ADDR expected as IAM Authentication Traits");
		} catch (ServletException e) {
			assertTrue(true);
			throw e;
		} catch (IOException e) {
			e.printStackTrace();
			assertTrue(false);
		} catch (NamingException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	@Test(expected = ServletException.class)
	public void testAuthzCtxMissingUsername() throws ServletException {
		
		StringBuffer sbuff = new StringBuffer();
		sbuff.append("http://bepnrel.vba.va.gov/QAWeb/onWelcomePage.do");
		
		when(sreq.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_USERNAME.toString())).thenReturn(null);
		when(sreq.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_CLIENT_IP.toString())).thenReturn("192.168.1.101");
		when(sreq.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_TRANSACTIONID.toString())).thenReturn("0123456789");
		when(sreq.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_ISSUEINSTANT.toString())).thenReturn("017258369");
		when(sreq.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_AD_DOMAIN.toString())).thenReturn("vhamaster");
		when(sreq.getRequestURL()).thenReturn(sbuff);
		when(sreq.getRequestURI()).thenReturn(sbuff.toString());
		when(sreq.getSession(true)).thenReturn(session);	
		when(filterConfig.getServletContext()).thenReturn(servletCtx1);
		when(servletCtx1.getContext(anyString())).thenReturn(servletCtx2);
		when(servletCtx2.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);

		filter = spy (new CSSIAMAuthorizationFilter());
		
		try {
			doReturn(context).when(filter).getInitialContext(null);
			filter.init(filterConfig);
			filter.doFilter(sreq, sresp, chain);
			verify(loggerSpy).error("Missing mandatory header adSamAccountName expected as IAM Authentication Traits");
		} catch (ServletException e) {
			assertTrue(true);
			throw e;
		} catch (IOException e) {
			e.printStackTrace();
			assertTrue(false);
		} catch (NamingException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	@Test(expected = ServletException.class)
	public void testAuthzCtxEmptyIPAddress() throws ServletException {
		
		StringBuffer sbuff = new StringBuffer();
		sbuff.append("http://bepnrel.vba.va.gov/QAWeb/onWelcomePage.do");
		
		when(sreq.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_USERNAME.toString())).thenReturn("VHAISPSMITHJ");
		when(sreq.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_CLIENT_IP.toString())).thenReturn("");
		when(sreq.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_TRANSACTIONID.toString())).thenReturn("0123456789");
		when(sreq.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_ISSUEINSTANT.toString())).thenReturn("017258369");
		when(sreq.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_AD_DOMAIN.toString())).thenReturn("vhamaster");
		when(sreq.getRequestURL()).thenReturn(sbuff);
		when(sreq.getRequestURI()).thenReturn(sbuff.toString());
		when(sreq.getSession(true)).thenReturn(session);	
		when(filterConfig.getServletContext()).thenReturn(servletCtx1);
		when(servletCtx1.getContext(anyString())).thenReturn(servletCtx2);
		when(servletCtx2.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);
		
		filter = spy (new CSSIAMAuthorizationFilter());
		
		try {
			doReturn(context).when(filter).getInitialContext(null);
			filter.init(filterConfig);
			filter.doFilter(sreq, sresp, chain);
			verify(loggerSpy).error("Missing mandatory header REMOTE_ADDR expected as IAM Authentication Traits");
		} catch (ServletException e) {
			assertTrue(true);
			throw e;
		} catch (IOException e) {
			e.printStackTrace();
			assertTrue(false);
		} catch (NamingException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	@Test(expected = ServletException.class)
	public void testAuthzCtxEmptyUsername() throws ServletException {
		
		StringBuffer sbuff = new StringBuffer();
		sbuff.append("http://bepnrel.vba.va.gov/QAWeb/onWelcomePage.do");
		
		when(sreq.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_USERNAME.toString())).thenReturn("");
		when(sreq.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_CLIENT_IP.toString())).thenReturn("192.168.1.101");
		when(sreq.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_TRANSACTIONID.toString())).thenReturn("0123456789");
		when(sreq.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_ISSUEINSTANT.toString())).thenReturn("017258369");
		when(sreq.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_AD_DOMAIN.toString())).thenReturn("vhamaster");
		when(sreq.getRequestURL()).thenReturn(sbuff);
		when(sreq.getRequestURI()).thenReturn(sbuff.toString());
		when(sreq.getSession(true)).thenReturn(session);	

		filter = spy (new CSSIAMAuthorizationFilter());
		
		try {
			doReturn(context).when(filter).getInitialContext(null);
			filter.init(filterConfig);
			filter.doFilter(sreq, sresp, chain);
			verify(loggerSpy).error("Missing mandatory header adSamAccountName expected as IAM Authentication Traits");
		} catch (ServletException e) {
			assertTrue(true);
			throw e;
		} catch (IOException e) {
			e.printStackTrace();
			assertTrue(false);
		} catch (NamingException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test(expected = ServletException.class)
	public void testAuthzCtxUnknownApp() throws ServletException {
		
		StringBuffer sbuff = new StringBuffer();
		sbuff.append("http://bepnrel.vba.va.gov/Unknown/onWelcomePage.do");
		
		when(sreq.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_USERNAME.toString())).thenReturn("VHAISPSMITHJ");
		when(sreq.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_CLIENT_IP.toString())).thenReturn("192.168.1.101");
		when(sreq.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_TRANSACTIONID.toString())).thenReturn("0123456789");
		when(sreq.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_ISSUEINSTANT.toString())).thenReturn("017258369");
		when(sreq.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_AD_DOMAIN.toString())).thenReturn("vhamaster");
		when(sreq.getRequestURL()).thenReturn(sbuff);
		when(sreq.getRequestURI()).thenReturn(sbuff.toString());
		when(sreq.getSession(true)).thenReturn(session);	
		
		filter = spy (new CSSIAMAuthorizationFilter());
		
		try {
			doReturn(context).when(filter).getInitialContext(null);
			filter.init(filterConfig);
			filter.doFilter(sreq, sresp, chain);
			verify(loggerSpy).error("Unable to map requested application 'http://bepnrel.vba.va.gov/Unknown/onWelcomePage.do' "
					+ "to a known BEP VBA Application");
		} catch (ServletException e) {
			assertTrue(true);
			throw e;
		} catch (IOException e) {
			e.printStackTrace();
			assertTrue(false);
		} catch (NamingException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testLogout() throws ServletException {
		
		StringBuffer sbuff = new StringBuffer();
		sbuff.append("http://bepnrel.vba.va.gov/cssintg/bephostedapp/iam/logout");
		
		when(sreq.getRequestURL()).thenReturn(sbuff);
		when(sreq.getRequestURI()).thenReturn(sbuff.toString());
		when(sreq.getSession(false)).thenReturn(session);	
		when(sreq.getParameter("errorMsg")).thenReturn("Log out with message");
		
		filter = spy (new CSSIAMAuthorizationFilter());
		
		try {
			doReturn(context).when(filter).getInitialContext(null);
			filter.init(filterConfig);
			filter.doFilter(sreq, sresp, chain);
			verify(filter).logout(sreq, sresp, "Log out with message");
			verify(session).invalidate();
		} catch (ServletException e) {
			assertTrue(false);
			throw e;
		} catch (IOException e) {
			e.printStackTrace();
			assertTrue(false);
		} catch (NamingException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	@Test
	public void testRedirectToSelector() throws ServletException {
		StringBuffer sbuff = new StringBuffer();
		sbuff.append("http://bepnrel.vba.va.gov/cssintg/bephostedapp/iam");
		
		when(sreq.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_USERNAME.toString())).thenReturn("VHAISDMELACL");
		when(sreq.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_CLIENT_IP.toString())).thenReturn("192.168.1.101");
		when(sreq.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_TRANSACTIONID.toString())).thenReturn("0123456789");
		when(sreq.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_ISSUEINSTANT.toString())).thenReturn("017258369");
		when(sreq.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_AD_DOMAIN.toString())).thenReturn("vhamaster");
		when(sreq.getRequestURL()).thenReturn(sbuff);
		when(sreq.getRequestURI()).thenReturn(sbuff.toString());
		when(sreq.getSession(false)).thenReturn(null);	
		when(sreq.getSession(true)).thenReturn(session);
		when(sreq.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);
		
		filter = spy (new CSSIAMAuthorizationFilter());
		
		try {
			doReturn(context).when(filter).getInitialContext(null);
			filter.init(filterConfig);
			filter.doFilter(sreq, sresp, chain);
			verify(sresp).sendRedirect("/cssiam/stationSelection/index.html?profileId=VHAISDMELACL&appId=FRAMEWORK&ipAddress=192.168.1.101&cssiam_target=http%3A%2F%2Fbepnrel.vba.va.gov%2Fcssintg%2Fbephostedapp%2Fiam&errorMessage=");
		} catch (ServletException e) {
			assertTrue(false);
			throw e;
		} catch (IOException e) {
			e.printStackTrace();
			assertTrue(false);
		} catch (NamingException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	
	@Test
	public void testStationSelectedBackFromSelector() throws ServletException, CSSProfileSecurityFrameworkException {
		StringBuffer sbuff = new StringBuffer();
		sbuff.append("http://bepnrel.vba.va.gov/cssintg/bephostedapp/iam");
		
		when(sreq.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_USERNAME.toString())).thenReturn("VHAISDMELACL");
		when(sreq.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_CLIENT_IP.toString())).thenReturn("192.168.1.101");
		when(sreq.getRequestURL()).thenReturn(sbuff);
		when(sreq.getRequestURI()).thenReturn(sbuff.toString());
		when(sreq.getSession(false)).thenReturn(session);
		when(sreq.getSession(true)).thenReturn(session);
		when(session.getAttribute(FilterBase.SESSION_AUTHZ_CONTEXT)).thenReturn(authzCtx);
		when(sreq.getParameter(FilterBase.SELECTOR_STATION)).thenReturn("281");
		
		filter = spy (new CSSIAMAuthorizationFilter());

		doReturn(tuxUserProfile).when(filter).getSecurityProfile(sreq, authzCtx);
		when(tuxUserProfile.getRetCode()).thenReturn('0');
		doNothing().when(filter).auditLoginAttempt(authzCtx, true);
		doNothing().when(filter).auditLoginAttempt(authzCtx, false);
		
		try {
			doReturn(context).when(filter).getInitialContext(null);
			filter.init(filterConfig);
			filter.doFilter(sreq, sresp, chain);
			verify(filter).processAuthorizationRequestToEJB(sreq, sresp, authzCtx);
		} catch (ServletException e) {
			assertTrue(false);
			throw e;
		} catch (IOException e) {
			e.printStackTrace();
			assertTrue(false);
		} catch (NamingException e) {
			e.printStackTrace();
			assertTrue(false);
		} 
	}

	@Test
	public void testUserAndAuthzContextInTheSession() throws ServletException {
		StringBuffer sbuff = new StringBuffer();
		sbuff.append("http://bepnrel.vba.va.gov/cssintg/bephostedapp/iam");
		
		when(sreq.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_USERNAME.toString())).thenReturn("VHAISDMELACL");
		when(sreq.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_CLIENT_IP.toString())).thenReturn("192.168.1.101");
		when(sreq.getRequestURL()).thenReturn(sbuff);
		when(sreq.getRequestURI()).thenReturn(sbuff.toString());
		when(sreq.getSession(false)).thenReturn(session);
		when(sreq.getSession(true)).thenReturn(session);
		when(session.getAttribute(FilterBase.SESSION_AUTHZ_CONTEXT)).thenReturn(authzCtx);
		when(session.getAttribute(FilterBase.SESSION_USER_CONTEXT)).thenReturn(new UserContext());
		when(sreq.getParameter(FilterBase.SELECTOR_STATION)).thenReturn("281");
		
		filter = spy (new CSSIAMAuthorizationFilter());
		
		try {
			doReturn(context).when(filter).getInitialContext(null);
			filter.init(filterConfig);
			filter.doFilter(sreq, sresp, chain);
			verify(chain).doFilter(sreq, sresp);
		} catch (ServletException e) {
			assertTrue(false);
			throw e;
		} catch (IOException e) {
			e.printStackTrace();
			assertTrue(false);
		} catch (NamingException e) {
			e.printStackTrace();
			assertTrue(false);
		} 
	}
}
