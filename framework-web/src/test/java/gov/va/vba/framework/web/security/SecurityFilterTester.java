package gov.va.vba.framework.web.security;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.endsWith;
import gov.va.vba.framework.EJBTestCase;
import gov.va.vba.framework.common.SystemUtils;
import gov.va.vba.framework.exceptions.AuditException;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;
import java.util.Vector;

import javax.naming.Context;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(SystemUtils.class)
public class SecurityFilterTester extends EJBTestCase {

/*
 * Server wide logout http://vbaauswebdev1.vba.va.gov/logout
 * servlet logout http://vbaauswebdev1.vba.va.gov/framework/SnoopServlet.jsp?value=logout
 * FW/SM logout http://vbaauswebdev1.vba.va.gov/framework/logout
 * FW/SM logoff  http://vbaauswebdev1.vba.va.gov/framework/logoff
 * test crap/registration http://vbaauswebdev1.vba.va.gov/framework/Registration.html
 * 
 * request headers
 * Accept: image/gif, image/x-xbitmap, image/jpeg, image/pjpeg, application/x-shockwave-flash, application/x-ms-application, application/x-ms-xbap, application/vnd.ms-xpsdocument, application/xaml+xml, application/vnd.ms-excel, application/vnd.ms-powerpoint, application/msword, start/star
Referer: http://vbaauswebdev1.vba.va.gov/vbaapps/security/login.fcc?TYPE=33554433&REALMOID=06-c047def0-5a7b-4e6b-a2b1-12c78bcb08a8&GUID=&SMAUTHREASON=0&METHOD=GET&SMAGENTNAME=-SM-sLPcneAP3JLnEbC5NIrm5WdG%2b%2b70OO2p5GG2uDbthGE6UPmpxCCF0vR9lT8VyAu7&TARGET=-SM-http%3a%2f%2fvbaauswebdev1%2evba%2eva%2egov%2fframework%2ftest%2edo
Accept-Language: en-us
UA-CPU: x86
Accept-Encoding: gzip, deflate
User-Agent: Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; .NET CLR 2.0.50727; .NET CLR 3.0.4506.2152; .NET CLR 3.5.30729; InfoPath.2; MS-RTC LM 8; .NET4.0C; .NET4.0E; AskTbARS/5.11.1.15497)
Host: vbaauswebdev1.vba.va.gov
Cache-Control: no-cache
SM_TRANSACTIONID: 0ae06ce2-017b-4f6b5ee1-0009-9344798c
SM_SDOMAIN: .vba.va.gov
SM_REALM: Framework_realm
SM_REALMOID: 06-c047def0-5a7b-4e6b-a2b1-12c78bcb08a8
SM_AUTHTYPE: Form
SM_AUTHREASON: 0
SM_SESSIONDRIFT: 0
SM_AUTHDIROID: 0e-2e99a51d-fa21-4e87-a33a-a2af9676512d
SM_AUTHDIRNAME: Framework CSS User
SM_AUTHDIRSERVER: siteminder data source
SM_AUTHDIRNAMESPACE: ODBC:
SM_USRMSG: stationid=281
SM_PROXYREQUEST: http://vbaauswebdev1.vba.va.gov/framework/test.do
cookie: SMSESSION=qx90UeZrT4BtCcMQz/u7167NcW81pFO3IKLBeZhOOLZZH1hwnVGyInaTgNm4CAbhq3yYA8YG0H/OqOyuCEdL3iqJPeWHpsBZZnyEjEvC8UzAWzFFWLTD4DAHdlLBqEwn/UPoSs/iNey+E+PDLxvKTulm4xhhXofWsP+sQI80Kk48KAKzk9ev5MDek5s2X1WZ8DMHoRM33DGuYkgCoxDB8TRjjrOyo32OP7DW+AZLKXcyUdAFCOprmO8bDld+v8g2cEoMbPNQN7qg72uCe+ZKd5GXc+iIoghsATJhVYJhF7yXr31HeHOTIE8cEFaSnIOdK6Ba2MIlnme5wTsbu6k24LvsWWTBh1wWw91cCxChC85uVtHZqKMrJ7E+zHSz1Bd7iEs03R7s5Xlczcy5iZmJD1UlygpkDlAFIq2mYwcmab6JjqZpb8rxm7oGI1PBlVRPTSbrIR9SvIfM3jleVUhu7NZcOVspAM0xXZmAc6zMoXhYZZhwmKItNCtmykdnvL9L/UJl4qeYMZG0bGdvHeqVRwOyxrXd0dKkmUTvY/oRiyV1Q5L4uYVNPdBC86LBDBeNcxztF4Y0tVjdlTK27JNkQRvfmNLlIWutG/Abjv3/QgOQYFSgJzRmL1Qd/5TUDAb/9tgOHy5W3FxD3N5urZDES1LeKsFG/8ByoLG7Zqj3ur7JuNwjblHCKhNql71diVSpNXLwVTsVbSAUk6H+wyGjlJYyuKsfN0xxak0H0M6AM2eDgMg+JmuYMyvbGPTGucFwGGqSUFQcX/EnGANsgHT2jVob2hAMHTekbCwh676Ghq9Whi+rNW8vkvvRdKEwvsv0Y/QcBJ5eMcPgfagvUBS5G8rZXGSK5y23KXrSXJVs0zQ0zKAHvW6adnEkp2KqsYuUD/rr7ZM893OR+5XE4Uo1lv1Tsu/LxnD4aSmGJQ4FYnyCdJPo0Rj1VHKJNmG9XStD
SM_USER: 281MRODR
SM_USERDN: 281MRODR
SM_SERVERSESSIONID: p7C4HliuJq2IPdZ7KcTlDE/ZKTs=
SM_SERVERSESSIONSPEC: bihyDCdpeBmlzMF/9sQHNbO+0Egdpy8gBeJfRP53cLo+oDq7O/yhoO6LXo46cU2Md+DNZMo0YaaJJ8o6MJe8Z2KSDAA7cnwcnREv0gVUQ75LKoY5lskHCX1XsVHlQKlhrmhDlE6J6lCUDOSeg2CSW/8Yv/r8rLUg/oyhg7HN7eA/kgUJCQP15SampzV+ZHmfvEFyyTLnTFYqgQAOV6Jsi5JkrMhQkhebpqerhal+UyUZTXZvNgxt1utsyGnHCsd58C7w3aIfAoTOioNJwiDrBdeLKnyL05+1s5lH/Dl0IW/5OJKqbtODdMXL+NejFqo9HwjRstsQL8IL8jgeHsJIlt7+2fkCbkmxGuFsXhs9CBM=
SM_TIMETOEXPIRE: 815046935
SM_SERVERIDENTITYSPEC: 
Connection: Keep-Alive
Proxy-Client-IP: 10.222.154.73
X-Forwarded-For: 10.222.154.73
X-WebLogic-KeepAliveSecs: 25
X-WebLogic-Request-ClusterInfo: true
x-weblogic-cluster-hash: 4K0n6t4HJCybh+LvCnhbaZFQv9k

cookies

Name : SMSESSION
Value : qx90UeZrT4BtCcMQz/u7167NcW81pFO3IKLBeZhOOLZZH1hwnVGyInaTgNm4CAbhq3yYA8YG0H/OqOyuCEdL3iqJPeWHpsBZZnyEjEvC8UzAWzFFWLTD4DAHdlLBqEwn/UPoSs/iNey+E+PDLxvKTulm4xhhXofWsP+sQI80Kk48KAKzk9ev5MDek5s2X1WZ8DMHoRM33DGuYkgCoxDB8TRjjrOyo32OP7DW+AZLKXcyUdAFCOprmO8bDld+v8g2cEoMbPNQN7qg72uCe+ZKd5GXc+iIoghsATJhVYJhF7yXr31HeHOTIE8cEFaSnIOdK6Ba2MIlnme5wTsbu6k24LvsWWTBh1wWw91cCxChC85uVtHZqKMrJ7E+zHSz1Bd7iEs03R7s5Xlczcy5iZmJD1UlygpkDlAFIq2mYwcmab6JjqZpb8rxm7oGI1PBlVRPTSbrIR9SvIfM3jleVUhu7NZcOVspAM0xXZmAc6zMoXhYZZhwmKItNCtmykdnvL9L/UJl4qeYMZG0bGdvHeqVRwOyxrXd0dKkmUTvY/oRiyV1Q5L4uYVNPdBC86LBDBeNcxztF4Y0tVjdlTK27JNkQRvfmNLlIWutG/Abjv3/QgOQYFSgJzRmL1Qd/5TUDAb/9tgOHy5W3FxD3N5urZDES1LeKsFG/8ByoLG7Zqj3ur7JuNwjblHCKhNql71diVSpNXLwVTsVbSAUk6H+wyGjlJYyuKsfN0xxak0H0M6AM2eDgMg+JmuYMyvbGPTGucFwGGqSUFQcX/EnGANsgHT2jVob2hAMHTekbCwh676Ghq9Whi+rNW8vkvvRdKEwvsv0Y/QcBJ5eMcPgfagvUBS5G8rZXGSK5y23KXrSXJVs0zQ0zKAHvW6adnEkp2KqsYuUD/rr7ZM893OR+5XE4Uo1lv1Tsu/LxnD4aSmGJQ4FYnyCdJPo0Rj1VHKJNmG9XStD
Path : null


			public String getRequestURI(){return"logout";} // valid values "logout", "logoff", neither
			public String getHeader(String arg0) {return "something";} //valid keys are SM_MESSAGE_HDR, "referer", SM_USER_ID_HDR, SM_PROXY_CLIENT_IP_HDR, SM_WLS_PROXY_CLIENT_IP_HDR, 
			  															//SM_MESSAGE_HDR must be >= than 5 characters
			public Cookie[] getCookies() {return cookies;}; //valid keys are SM_MESSAGE_COOKIE, SM_SESSION_COOKIE
			public String getContextPath() {return "something/"+SecurityFilter.FRAMEWORK_APPL;}; //only one valid value after slash doesn't matter before slash
			public String getServletPath() {return SecurityFilter.SSO_SERVLET;} // 2 scenarios: one that contains SSO_SERVLET and one that doesn't  
			public String getQueryString() {return "?appID=testAppID&dummy=1";} //first parameter can be any appID. second parameter doesn't matter but must be present
 * 
 */


	@Before
	public void setUp() throws Exception {
		Map<String, String> envMap = convertResourceBundleToMap(resourceBundle);
		String providerURL = (String) envMap.get(Context.PROVIDER_URL);

		PowerMockito.mockStatic(SystemUtils.class);
		when(SystemUtils.getProperty(SystemUtils.Key.JNDI_INTERFACE_TYPE)).thenReturn(SystemUtils.REMOTE_INTERFACE);
		when(SystemUtils.getProperty(SystemUtils.Key.JNDI_PROVIDER_URL)).thenReturn(providerURL);
	}

	@Test
	public void testSomething() {
		boolean testResult=false;
		
		final Cookie cookies[]={new Cookie(SecurityFilter.SM_MESSAGE_COOKIE,"something"),new Cookie(SecurityFilter.SM_SESSION_COOKIE,"something")};

		final StringBuffer strbuffUrl = new StringBuffer("MockedURLStringBuffer");
		Enumeration<String> emptyEnum = new Vector<String>().elements();
		
		ServletRequest sreq = mock(TestHttpServletRequest.class);
		HttpSession httpSession = mock(HttpSession.class);
		
		when(((TestHttpServletRequest) sreq).getRequestURI()).thenReturn("logout");
		when(((TestHttpServletRequest) sreq).getHeader(anyString())).thenReturn("something");
		when(((TestHttpServletRequest) sreq).getCookies()).thenReturn(cookies);
		when(((TestHttpServletRequest) sreq).getContextPath()).thenReturn("something/"+SecurityFilter.FRAMEWORK_APPL);
		when(((TestHttpServletRequest) sreq).getServletPath()).thenReturn(SecurityFilter.SSO_SERVLET);
		when(((TestHttpServletRequest) sreq).getQueryString()).thenReturn("?SMPORTALURL=http%3A%2F%2Fvbaauswebdev1.vba.va.gov%3A80%2Faffwebservices%2Fpublic%2Fsaml2sso&SMPORTALSTATE=U0FNTFJlcXVlc3Q9bFpKTlQlMkJNd0VJYiUyRml1");

		when(((TestHttpServletRequest) sreq).getSession()).thenReturn(httpSession);
		when(httpSession.getId()).thenReturn("ID");
		when(((TestHttpServletRequest) sreq).getMethod()).thenReturn("MocketMethod");
		when(((TestHttpServletRequest) sreq).getRequestURL()).thenReturn(strbuffUrl);
		when(((TestHttpServletRequest) sreq).getAttributeNames()).thenReturn(emptyEnum);
		when(((TestHttpServletRequest) sreq).getHeaderNames()).thenReturn(emptyEnum);

		ServletResponse sresp = new TestHttpServletResponse(); 
		FilterChain chain = new TestFilterChain();
		try {
			SecurityFilter securityFilter=new SecurityFilter();
			FilterConfig filterConfig=new TestFilterConfig(this.getJNDIContext());
			securityFilter.init(filterConfig);
			securityFilter.doFilter(sreq, sresp, chain);
			testResult=true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(testResult);
	}
	
	@Test
	public void testRealistic() {
		boolean testResult=false;
		final Cookie cookies[]={new Cookie(SecurityFilter.SM_MESSAGE_COOKIE,"something"),new Cookie(SecurityFilter.SM_SESSION_COOKIE,"something")};

		final StringBuffer strbuffUrl = new StringBuffer("MockedURLStringBuffer");
		Enumeration<String> emptyEnum = new Vector<String>().elements();
		
		ServletRequest sreq = mock(TestHttpServletRequest.class);
		HttpSession httpSession = mock(HttpSession.class);
		
		when(((TestHttpServletRequest) sreq).getRequestURI()).thenReturn("logout");
		when(((TestHttpServletRequest) sreq).getHeader(anyString())).thenReturn("something");
		when(((TestHttpServletRequest) sreq).getCookies()).thenReturn(cookies);
		when(((TestHttpServletRequest) sreq).getContextPath()).thenReturn("something/"+SecurityFilter.FRAMEWORK_APPL);
		when(((TestHttpServletRequest) sreq).getServletPath()).thenReturn(SecurityFilter.SSO_SERVLET);
		when(((TestHttpServletRequest) sreq).getQueryString()).thenReturn("?appID=testAppID&dummy=1");

		when(((TestHttpServletRequest) sreq).getSession()).thenReturn(httpSession);
		when(httpSession.getId()).thenReturn("ID");
		when(((TestHttpServletRequest) sreq).getMethod()).thenReturn("MocketMethod");
		when(((TestHttpServletRequest) sreq).getRequestURL()).thenReturn(strbuffUrl);
		when(((TestHttpServletRequest) sreq).getAttributeNames()).thenReturn(emptyEnum);
		when(((TestHttpServletRequest) sreq).getHeaderNames()).thenReturn(emptyEnum);

		ServletResponse sresp = new TestHttpServletResponse(); 
		FilterChain chain = new TestFilterChain();
		try {
			SecurityFilter securityFilter=new SecurityFilter();
			FilterConfig filterConfig=new TestFilterConfig(this.getJNDIContext());
			securityFilter.init(filterConfig);
			securityFilter.doFilter(sreq, sresp, chain);
			testResult=true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(testResult);
	}
	
	@Test
	public void testMissingSMSESSIONCookie() {
		boolean testResult=false;
		final Cookie cookies[]={new Cookie(SecurityFilter.SM_MESSAGE_COOKIE,"something")};
		
		final StringBuffer strbuffUrl = new StringBuffer("MockedURLStringBuffer");
		Enumeration<String> emptyEnum = new Vector<String>().elements();
		
		ServletRequest sreq = mock(TestHttpServletRequest.class);
		HttpSession httpSession = mock(HttpSession.class);
		
		when(((TestHttpServletRequest) sreq).getRequestURI()).thenReturn("logout");
		when(((TestHttpServletRequest) sreq).getHeader(anyString())).thenReturn("something");
		when(((TestHttpServletRequest) sreq).getCookies()).thenReturn(cookies);
		when(((TestHttpServletRequest) sreq).getContextPath()).thenReturn("something/"+SecurityFilter.FRAMEWORK_APPL);
		when(((TestHttpServletRequest) sreq).getServletPath()).thenReturn(SecurityFilter.SSO_SERVLET);
		when(((TestHttpServletRequest) sreq).getQueryString()).thenReturn("?appID=testAppID&dummy=1");

		when(((TestHttpServletRequest) sreq).getSession()).thenReturn(httpSession);
		when(httpSession.getId()).thenReturn("ID");
		when(((TestHttpServletRequest) sreq).getMethod()).thenReturn("MocketMethod");
		when(((TestHttpServletRequest) sreq).getRequestURL()).thenReturn(strbuffUrl);
		when(((TestHttpServletRequest) sreq).getAttributeNames()).thenReturn(emptyEnum);
		when(((TestHttpServletRequest) sreq).getHeaderNames()).thenReturn(emptyEnum);

		ServletResponse sresp = new TestHttpServletResponse(); 
		FilterChain chain = new TestFilterChain();
		try {
			SecurityFilter securityFilter=new SecurityFilter();
			FilterConfig filterConfig=new TestFilterConfig(this.getJNDIContext());
			securityFilter.init(filterConfig);
			securityFilter.doFilter(sreq, sresp, chain);
			testResult=true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(testResult);
	}
	
	@Test
	public void testMissingSMMESSAGECookie() {
		boolean testResult=false;
		final Cookie cookies[]={new Cookie(SecurityFilter.SM_SESSION_COOKIE,"something")};
		
		final StringBuffer strbuffUrl = new StringBuffer("MockedURLStringBuffer");
		Enumeration<String> emptyEnum = new Vector<String>().elements();
		
		ServletRequest sreq = mock(TestHttpServletRequest.class);
		HttpSession httpSession = mock(HttpSession.class);
		
		when(((TestHttpServletRequest) sreq).getRequestURI()).thenReturn("logout");
		when(((TestHttpServletRequest) sreq).getHeader(anyString())).thenReturn("something");
		when(((TestHttpServletRequest) sreq).getCookies()).thenReturn(cookies);
		when(((TestHttpServletRequest) sreq).getContextPath()).thenReturn("something/"+SecurityFilter.FRAMEWORK_APPL);
		when(((TestHttpServletRequest) sreq).getServletPath()).thenReturn(SecurityFilter.SSO_SERVLET);
		when(((TestHttpServletRequest) sreq).getQueryString()).thenReturn("?appID=testAppID&dummy=1");

		when(((TestHttpServletRequest) sreq).getSession()).thenReturn(httpSession);
		when(httpSession.getId()).thenReturn("ID");
		when(((TestHttpServletRequest) sreq).getMethod()).thenReturn("MocketMethod");
		when(((TestHttpServletRequest) sreq).getRequestURL()).thenReturn(strbuffUrl);
		when(((TestHttpServletRequest) sreq).getAttributeNames()).thenReturn(emptyEnum);
		when(((TestHttpServletRequest) sreq).getHeaderNames()).thenReturn(emptyEnum);

		ServletResponse sresp = new TestHttpServletResponse(); 
		FilterChain chain = new TestFilterChain();
		try {
			SecurityFilter securityFilter=new SecurityFilter();
			FilterConfig filterConfig=new TestFilterConfig(this.getJNDIContext());
			securityFilter.init(filterConfig);
			securityFilter.doFilter(sreq, sresp, chain);
			testResult=true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(testResult);
	}
	
	@Test
	public void testMissingCookies() {
		boolean testResult=false;
		
		
		final StringBuffer strbuffUrl = new StringBuffer("MockedURLStringBuffer");
		Enumeration<String> emptyEnum = new Vector<String>().elements();
		
		ServletRequest sreq = mock(TestHttpServletRequest.class);
		HttpSession httpSession = mock(HttpSession.class);
		
		when(((TestHttpServletRequest) sreq).getRequestURI()).thenReturn("logout");
		when(((TestHttpServletRequest) sreq).getHeader(anyString())).thenReturn("something");
		when(((TestHttpServletRequest) sreq).getContextPath()).thenReturn("something/"+SecurityFilter.FRAMEWORK_APPL);
		when(((TestHttpServletRequest) sreq).getServletPath()).thenReturn(SecurityFilter.SSO_SERVLET);
		when(((TestHttpServletRequest) sreq).getQueryString()).thenReturn("?appID=testAppID&dummy=1");

		when(((TestHttpServletRequest) sreq).getSession()).thenReturn(httpSession);
		when(httpSession.getId()).thenReturn("ID");
		when(((TestHttpServletRequest) sreq).getMethod()).thenReturn("MocketMethod");
		when(((TestHttpServletRequest) sreq).getRequestURL()).thenReturn(strbuffUrl);
		when(((TestHttpServletRequest) sreq).getAttributeNames()).thenReturn(emptyEnum);
		when(((TestHttpServletRequest) sreq).getHeaderNames()).thenReturn(emptyEnum);
		
		ServletResponse sresp = new TestHttpServletResponse(); 
		FilterChain chain = new TestFilterChain();
		try {
			SecurityFilter securityFilter=new SecurityFilter();
			FilterConfig filterConfig=new TestFilterConfig(this.getJNDIContext());
			securityFilter.init(filterConfig);
			securityFilter.doFilter(sreq, sresp, chain);
			testResult=true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(testResult);
	}

	@Test
	public void testMissingApplicationName()
	{
		boolean testResult=false;
		final Cookie cookies[]={new Cookie(SecurityFilter.SM_MESSAGE_COOKIE,"something"),new Cookie(SecurityFilter.SM_SESSION_COOKIE,"something")};
		
		final StringBuffer strbuffUrl = new StringBuffer("MockedURLStringBuffer");
		Enumeration<String> emptyEnum = new Vector<String>().elements();
		
		ServletRequest sreq = mock(TestHttpServletRequest.class);
		HttpSession httpSession = mock(HttpSession.class);
		
		when(((TestHttpServletRequest) sreq).getRequestURI()).thenReturn("login");
		when(((TestHttpServletRequest) sreq).getHeader(anyString())).thenReturn("something");
		when(((TestHttpServletRequest) sreq).getHeader(SecurityFilter.SM_UNIVERSAL_ID)).thenReturn("USERNAME");
		when(((TestHttpServletRequest) sreq).getHeader(SecurityFilter.SM_APPLICATION_NAME)).thenReturn(null);

		when(((TestHttpServletRequest) sreq).getCookies()).thenReturn(cookies);
		when(((TestHttpServletRequest) sreq).getContextPath()).thenReturn("something/"+SecurityFilter.FRAMEWORK_APPL);
		when(((TestHttpServletRequest) sreq).getServletPath()).thenReturn(SecurityFilter.SSO_SERVLET);
		when(((TestHttpServletRequest) sreq).getQueryString()).thenReturn("?appID=testAppID&dummy=1");

		when(((TestHttpServletRequest) sreq).getSession()).thenReturn(httpSession);
		when(httpSession.getId()).thenReturn("ID");
		when(((TestHttpServletRequest) sreq).getMethod()).thenReturn("MocketMethod");
		when(((TestHttpServletRequest) sreq).getRequestURL()).thenReturn(strbuffUrl);
		when(((TestHttpServletRequest) sreq).getAttributeNames()).thenReturn(emptyEnum);
		when(((TestHttpServletRequest) sreq).getHeaderNames()).thenReturn(emptyEnum);
		
		
		ServletResponse sresp = new TestHttpServletResponse(); 
		FilterChain chain = new TestFilterChain();
		try {
			SecurityFilter securityFilter=new SecurityFilter();
			
			FilterConfig filterConfig = new TestFilterConfig(this.getJNDIContext()) {
				@Override
				public String getInitParameter(String arg0) {
					if("useGlobalErrorPage".equals(arg0)) {
						return "true";
					}
					return null;
				}
			};
			
			securityFilter.init(filterConfig);
			try
			{
				securityFilter.doFilter(sreq, sresp, chain);
			}
			catch (AuditException ae)
			{
				testResult=true;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			testResult=true;
		}
		assertTrue(testResult);		
	}
	
	@Test
	public void testMissingHeader() {
		boolean testResult=false;
		final Cookie cookies[]={new Cookie(SecurityFilter.SM_MESSAGE_COOKIE,"something"),new Cookie(SecurityFilter.SM_SESSION_COOKIE,"something")};
		
		final StringBuffer strbuffUrl = new StringBuffer("MockedURLStringBuffer");
		Enumeration<String> emptyEnum = new Vector<String>().elements();
		
		ServletRequest sreq = mock(TestHttpServletRequest.class);
		HttpSession httpSession = mock(HttpSession.class);
		
		when(((TestHttpServletRequest) sreq).getRequestURI()).thenReturn("logout");
		when(((TestHttpServletRequest) sreq).getCookies()).thenReturn(cookies);
		when(((TestHttpServletRequest) sreq).getContextPath()).thenReturn("something/"+SecurityFilter.FRAMEWORK_APPL);
		when(((TestHttpServletRequest) sreq).getServletPath()).thenReturn(SecurityFilter.SSO_SERVLET);
		when(((TestHttpServletRequest) sreq).getQueryString()).thenReturn("?appID=testAppID&dummy=1");

		when(((TestHttpServletRequest) sreq).getSession()).thenReturn(httpSession);
		when(httpSession.getId()).thenReturn("ID");
		when(((TestHttpServletRequest) sreq).getMethod()).thenReturn("MocketMethod");
		when(((TestHttpServletRequest) sreq).getRequestURL()).thenReturn(strbuffUrl);
		when(((TestHttpServletRequest) sreq).getAttributeNames()).thenReturn(emptyEnum);
		when(((TestHttpServletRequest) sreq).getHeaderNames()).thenReturn(emptyEnum);
		
		ServletResponse sresp = new TestHttpServletResponse(); 
		FilterChain chain = new TestFilterChain();
		try {
			SecurityFilter securityFilter=new SecurityFilter();
			FilterConfig filterConfig=new TestFilterConfig(this.getJNDIContext());
			securityFilter.init(filterConfig);
			securityFilter.doFilter(sreq, sresp, chain);
			testResult=true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(testResult);
	}

	@Test
	public void testMissingIPAddress() {
		boolean testResult=false;
		final Cookie cookies[]={new Cookie(SecurityFilter.SM_MESSAGE_COOKIE,"something"),new Cookie(SecurityFilter.SM_SESSION_COOKIE,"something")};
		
		final StringBuffer strbuffUrl = new StringBuffer("MockedURLStringBuffer");
		Enumeration<String> emptyEnum = new Vector<String>().elements();
		
		ServletRequest sreq = mock(TestHttpServletRequest.class);
		HttpSession httpSession = mock(HttpSession.class);
		
		when(((TestHttpServletRequest) sreq).getRequestURI()).thenReturn("login");
		when(((TestHttpServletRequest) sreq).getHeader(anyString())).thenReturn("something");
		when(((TestHttpServletRequest) sreq).getHeader(endsWith("Client-IP"))).thenReturn(null);

		when(((TestHttpServletRequest) sreq).getCookies()).thenReturn(cookies);
		when(((TestHttpServletRequest) sreq).getContextPath()).thenReturn("something/"+SecurityFilter.FRAMEWORK_APPL);
		when(((TestHttpServletRequest) sreq).getServletPath()).thenReturn(SecurityFilter.SSO_SERVLET);
		when(((TestHttpServletRequest) sreq).getQueryString()).thenReturn("?appID=testAppID&dummy=1");

		when(((TestHttpServletRequest) sreq).getSession()).thenReturn(httpSession);
		when(httpSession.getId()).thenReturn("ID");
		when(((TestHttpServletRequest) sreq).getMethod()).thenReturn("MocketMethod");
		when(((TestHttpServletRequest) sreq).getRequestURL()).thenReturn(strbuffUrl);
		when(((TestHttpServletRequest) sreq).getAttributeNames()).thenReturn(emptyEnum);
		when(((TestHttpServletRequest) sreq).getHeaderNames()).thenReturn(emptyEnum);
		
		ServletResponse sresp = new TestHttpServletResponse(); 
		FilterChain chain = new TestFilterChain();
		try {
			SecurityFilter securityFilter=new SecurityFilter();
			FilterConfig filterConfig = new TestFilterConfig(this.getJNDIContext()) {
				@Override
				public String getInitParameter(String arg0) {
					if("useGlobalErrorPage".equals(arg0)) {
						return "true";
					}
					return null;
				}
			};
			securityFilter.init(filterConfig);
			try
			{
				securityFilter.doFilter(sreq, sresp, chain);
			}
			catch (AuditException ae)
			{
				testResult=true;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			testResult=true;
		}
		assertTrue(testResult);
	}

	@Test
	public void testMissingUserID() {
		boolean testResult=false;
		final Cookie cookies[]={new Cookie(SecurityFilter.SM_MESSAGE_COOKIE,"something"),new Cookie(SecurityFilter.SM_SESSION_COOKIE,"something")};
		
		final StringBuffer strbuffUrl = new StringBuffer("MockedURLStringBuffer");
		Enumeration<String> emptyEnum = new Vector<String>().elements();
		
		ServletRequest sreq = mock(TestHttpServletRequest.class);
		HttpSession httpSession = mock(HttpSession.class);
		
		when(((TestHttpServletRequest) sreq).getRequestURI()).thenReturn("login");
		when(((TestHttpServletRequest) sreq).getHeader(anyString())).thenReturn("something");
		when(((TestHttpServletRequest) sreq).getHeader(SecurityFilter.SM_UNIVERSAL_ID)).thenReturn(null);

		when(((TestHttpServletRequest) sreq).getCookies()).thenReturn(cookies);
		when(((TestHttpServletRequest) sreq).getContextPath()).thenReturn("something/"+SecurityFilter.FRAMEWORK_APPL);
		when(((TestHttpServletRequest) sreq).getServletPath()).thenReturn(SecurityFilter.SSO_SERVLET);
		when(((TestHttpServletRequest) sreq).getQueryString()).thenReturn("?appID=testAppID&dummy=1");

		when(((TestHttpServletRequest) sreq).getSession()).thenReturn(httpSession);
		when(httpSession.getId()).thenReturn("ID");
		when(((TestHttpServletRequest) sreq).getMethod()).thenReturn("MocketMethod");
		when(((TestHttpServletRequest) sreq).getRequestURL()).thenReturn(strbuffUrl);
		when(((TestHttpServletRequest) sreq).getAttributeNames()).thenReturn(emptyEnum);
		when(((TestHttpServletRequest) sreq).getHeaderNames()).thenReturn(emptyEnum);
		
		
		ServletResponse sresp = new TestHttpServletResponse(); 
		FilterChain chain = new TestFilterChain();
		try {
			SecurityFilter securityFilter=new SecurityFilter();
			
			FilterConfig filterConfig = new TestFilterConfig(this.getJNDIContext()) {
				@Override
				public String getInitParameter(String arg0) {
					if("useGlobalErrorPage".equals(arg0)) {
						return "true";
					}
					return null;
				}
			};
			
			securityFilter.init(filterConfig);
			try
			{
				securityFilter.doFilter(sreq, sresp, chain);
			}
			catch (AuditException ae)
			{
				testResult=true;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			testResult=true;
		}
		assertTrue(testResult);
	}

	@Test
	public void testMissingStationID() {
		boolean testResult=false;
		final Cookie cookies[]={new Cookie(SecurityFilter.SM_MESSAGE_COOKIE,"something"),new Cookie(SecurityFilter.SM_SESSION_COOKIE,"something")};
		
		final StringBuffer strbuffUrl = new StringBuffer("MockedURLStringBuffer");
		Enumeration<String> emptyEnum = new Vector<String>().elements();
		
		ServletRequest sreq = mock(TestHttpServletRequest.class);
		HttpSession httpSession = mock(HttpSession.class);
		
		when(((TestHttpServletRequest) sreq).getRequestURI()).thenReturn("logout");
		when(((TestHttpServletRequest) sreq).getHeader(anyString())).thenReturn("something");
		when(((TestHttpServletRequest) sreq).getHeader(SecurityFilter.SM_MESSAGE_HDR)).thenReturn(null);

		when(((TestHttpServletRequest) sreq).getCookies()).thenReturn(cookies);
		when(((TestHttpServletRequest) sreq).getContextPath()).thenReturn("something/"+SecurityFilter.FRAMEWORK_APPL);
		when(((TestHttpServletRequest) sreq).getServletPath()).thenReturn(SecurityFilter.SSO_SERVLET);
		when(((TestHttpServletRequest) sreq).getQueryString()).thenReturn("?appID=testAppID&dummy=1");

		when(((TestHttpServletRequest) sreq).getSession()).thenReturn(httpSession);
		when(httpSession.getId()).thenReturn("ID");
		when(((TestHttpServletRequest) sreq).getMethod()).thenReturn("MocketMethod");
		when(((TestHttpServletRequest) sreq).getRequestURL()).thenReturn(strbuffUrl);
		when(((TestHttpServletRequest) sreq).getAttributeNames()).thenReturn(emptyEnum);
		when(((TestHttpServletRequest) sreq).getHeaderNames()).thenReturn(emptyEnum);
		ServletResponse sresp = new TestHttpServletResponse(); 
		FilterChain chain = new TestFilterChain();
		try {
			SecurityFilter securityFilter=new SecurityFilter();
			FilterConfig filterConfig=new TestFilterConfig(this.getJNDIContext());
			securityFilter.init(filterConfig);
			securityFilter.doFilter(sreq, sresp, chain);
			testResult=true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(testResult);
	}
}
