package gov.va.vba.framework.web.security;

import gov.va.vba.framework.auditing.AuditIDGenerator;
import gov.va.vba.framework.common.AuditContext;
import gov.va.vba.framework.common.StringUtils;
import gov.va.vba.framework.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.Serializable;




public class BaseFilter {
	public static final String USER_CONTEXT = "userContext";
	public static final String SM_WLS_PROXY_CLIENT_IP_HDR = "WL-Proxy-Client-IP";
	public static final String SM_PROXY_CLIENT_IP_HDR = "Proxy-Client-IP";
	public static final String WLS_CONTEXT_FACTORY = "weblogic.jndi.WLInitialContextFactory";
	public static final String SM_MESSAGE_HDR = "SM_USRMSG";
	public static final String SM_USER = "SM_USER";
	public static final String SM_UNIVERSAL_ID = "SM_UNIVERSALID";
	public static final String LOGOUT = "logout";
	public static final String LOGOFF = "logoff";
	public static final String FRAMEWORK_APPL = "framework";
	public static final String SSO_SERVLET = "sso";
	public static final String SM_SESSION_COOKIE = "SMSESSION";
	public static final String SM_MESSAGE_COOKIE = "ERRORMSG";
	public static final String SM_SSO_PORTAL_STATE = "SMPORTALSTATE";
	public static final String POPUP_MESSAGE_ATTRIB = "message";
	public static final String SM_PORTAL_URL = "SMPORTALURL";
	public static final String SAML_REQUEST_PARAM = "SAMLRequest";
	public static final String COMMAND = "SMASSERTIONREF=QUERY";
	public static final String PARAMETER_DELIMITER = "&";
	public static final String SAML_APP_INDICATOR = "IDPSAML";
	public static final String NAME_VALUE_DELIMITER = "=";
	public static final String SM_STATIONID = "SM_BEP_STATIONID";
	public static final String SM_APPLICATION_NAME = "SM_BEP_APPLICATION_NAME";
	public static final String SM_PROFILEID = "SM_BEP_PROFILEID";
	public static final String SM_ALLOWUSEROVERWRITE = "SM_BEP_ALLOWUSEROVERWRITE";
	public static final String FRAMEWORK_TEST_URL = "/framework/test.do";
	public static final String REQ_APPID_PARAM = "appid";
	public static final String FRAMEWORK_TEST_CSS_APP = "FRAMEWORK";
	public static final String AUTH_CONTEXT = "AuthContext";
	public static final String URLPARAM_NAME_STNID = "stationId";
	public static final String URLPARAM_NAME_PROFILEID = "profileId";
	
	private static final Logger logger = Logger.getLogger(BaseFilter.class);
	private String regExpr = "[:]";
	private char fieldSeparator = '=';


	protected AuthenticationContext getAuthContextFromCache(HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		return (AuthenticationContext)session.getAttribute(AUTH_CONTEXT);		
	}

	protected boolean isSessionOrUserContextNull(HttpServletRequest req) {
		HttpSession session = req.getSession(false); 
		return (session == null ||(session != null && session.getAttribute(USER_CONTEXT)== null));
	}
	
	protected UserContext getUserContext(HttpServletRequest req) {		
		return isSessionOrUserContextNull(req)?null:(UserContext)req.getSession(false).getAttribute(USER_CONTEXT);
	}

	protected boolean isAuthContextCached (HttpServletRequest req){
		HttpSession session = req.getSession(false); 
		return (session != null && session.getAttribute(AUTH_CONTEXT)!= null);
	}
	
	protected void cacheAuthContext(HttpSession session,  UserContext userCtx){

		session.setAttribute(USER_CONTEXT, (Serializable)userCtx);
		//session.setAttribute(AUTH_CONTEXT, authCtx);
	}
	
	protected boolean isLogoutRequest(HttpServletRequest req) {
		
		return ((req.getRequestURI().toLowerCase().contains(LOGOUT)||req.getParameterMap().containsKey(LOGOUT)||  
				  (req.getQueryString() != null && req.getQueryString().toLowerCase().contains(LOGOUT)))||
				  (req.getRequestURI().toLowerCase().contains(LOGOFF)||req.getParameterMap().containsKey(LOGOFF)|| 
						  (req.getQueryString() != null && req.getQueryString().toLowerCase().contains(LOGOFF))));
	}
	
	protected boolean isNonSAMLRequest(HttpServletRequest req, AuthenticationContext authCtx) {
		if (!SAML_APP_INDICATOR.equalsIgnoreCase(authCtx.getAuthZApplicationName())){
			return true;
		}else if(SAML_APP_INDICATOR.equalsIgnoreCase(authCtx.getAuthZApplicationName()) &&
				 isFrameworkTestRequest(req)){
			//since both SAML based and /framework/test.do are deployed on the same context root, 
			//than in case if it is framework test, treat as nonsaml for testing purposes
			return true; 
		}else {
			return false;
		}		
	}
	protected boolean isSAMLRequest(HttpServletRequest req, AuthenticationContext authCtx) {
		return !isNonSAMLRequest(req, authCtx);
	}
	
	protected boolean isFrameworkTestRequest(HttpServletRequest req) {
		return (req.getRequestURL().toString().contains(FRAMEWORK_TEST_URL));
	}
	

	protected Cookie getCookie(HttpServletRequest req, String cookieName) {

		Cookie[] cookies = req.getCookies();
		Cookie retCookie = null;
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				if (cookies[i].getName().equalsIgnoreCase(cookieName)) {
					retCookie = cookies[i];
					break;
				}
			}
		}

		return retCookie;
	}

	protected void deleteCookie(String name, String value, int expiry, HttpServletResponse resp) {
		
		Cookie cookie = null;

		
		if (name != null && value != null ) {
			String normalizedName =  StringUtils.normalizeSpace(name);
			String normalizedValue = StringUtils.normalizeSpace(value);
			cookie = new Cookie(normalizedName, normalizedValue);
			cookie.setMaxAge(expiry);
			cookie.setPath("/");//until 8/4/2006 this was causing a failure.
			cookie.setDomain(".vba.va.gov");//until 8/4/2006 this was causing a failure.
			resp.addCookie(cookie);
			//System.out.println("Added cookie: "+name+" with expiry: "+expiry);
		}
	}
	
	protected void getApplicationName(HttpServletRequest req, AuthenticationContext authCtx) {
		if (isNonSAMLRequest(req, authCtx)){
			if (isFrameworkTestRequest(req)){
				authCtx.setCssApplicationName(FRAMEWORK_TEST_CSS_APP);
			}else{
				authCtx.setCssApplicationName(authCtx.getAuthZApplicationName());
			}
		}else{
			if (isLogoutRequest(req)){//use the header application name, not specific saml css app name
				authCtx.setCssApplicationName(authCtx.getAuthZApplicationName());
			}else{
				authCtx.setCssApplicationName(getAppID(req));
			}
		}	
		
	}
	
	private String[] getContextPath(HttpServletRequest req)
	{
		String[] contextPath=req.getContextPath().split("[\\/]");
		return contextPath;
	}
	
	protected String getAppID(HttpServletRequest req) {

		//String appID=req.getQueryString().split("[&]")[0].split("[=]")[1];
		String appID = req.getParameter(REQ_APPID_PARAM);
		return appID;
	}
	
	/* Populates authentication context
	 * <AuthZ...> attributes are received via HTTP HEADERS from Siteminder 
	 *            upon successful authentication during authorization phase
	 * <AuthN...> attributes are received from Siteminder authentication phase
	 *            via Siteminder generated AUTH headers. This are the attributes
	 *            that user used to authenticate with (e.g AD user name)
	 */
	protected AuthenticationContext processHeaders(HttpServletRequest req){
		AuthenticationContext authCtx = new AuthenticationContext();
		//authCtx.setAuthNUserName(getUserNameFromReq(req));
		//authCtx.setAuthZStationId(getStationIdFromReq(req));
		//authCtx.setCssApplicationName(getApplicationNameFromReq(req));
		//authCtx.setClientIPAddress(getClientIPAddressFromReq(req));
		
		authCtx.setAuthNUserName(req.getHeader(SM_UNIVERSAL_ID));
		authCtx.setAuthZStationId(req.getHeader(SM_STATIONID));
		authCtx.setAuthZApplicationName(req.getHeader(SM_APPLICATION_NAME));
		authCtx.setAuthZProfileId(req.getHeader(SM_PROFILEID));
		authCtx.setAllowOverwrite(req.getHeader(SM_ALLOWUSEROVERWRITE));
		authCtx.setClientIPAddress(getClientIPAddress(req));
		getApplicationName(req, authCtx);
		
		return authCtx;
	
	}

	protected void validateHeaders(HttpServletRequest req)
			throws ServletException {

		//if (req.getHeader(SM_MESSAGE_HDR) == null || 
			//req.getHeader(SM_USER) == null || 
			//(req.getHeader(SM_PROXY_CLIENT_IP_HDR) == null && 
			 //req.getHeader(SM_WLS_PROXY_CLIENT_IP_HDR) == null)||
			 //req.getHeader(SM_MESSAGE_HDR).length() <5)
		
		String nullEmptyServletException = "Required SiteMinder headers are null or missing.";
		if (StringUtils.isEmpty(req.getHeader(SM_UNIVERSAL_ID)) ) {
			logger.error("Required SiteMinder header "+SM_UNIVERSAL_ID+" is null or missing.");	
			throw new ServletException(nullEmptyServletException);
			
		} else if (StringUtils.isEmpty(req.getHeader(SM_STATIONID))	) {
			logger.error("Required SiteMinder header "+SM_STATIONID+" is null or missing.");
			throw new ServletException(nullEmptyServletException);
			
		} else if (StringUtils.isEmpty(req.getHeader(SM_APPLICATION_NAME)) ) {
			logger.error("Required SiteMinder header "+SM_APPLICATION_NAME+" is null or missing.");
			throw new ServletException(nullEmptyServletException);
			
		} else if ( (req.getHeader(SM_PROXY_CLIENT_IP_HDR) == null && 
			         req.getHeader(SM_WLS_PROXY_CLIENT_IP_HDR) == null)) {		
			logger.error("Required SiteMinder headers "+SM_PROXY_CLIENT_IP_HDR+" and "+
					SM_WLS_PROXY_CLIENT_IP_HDR+" are both null. At least one must be present.");
			throw new ServletException(nullEmptyServletException);
		}
	}
	
	
	protected String getClientIPAddress(HttpServletRequest req) {

		 String clientIPAddress=(req.getHeader(SM_WLS_PROXY_CLIENT_IP_HDR) == null || 
				  (req.getHeader(SM_WLS_PROXY_CLIENT_IP_HDR)).length() == 0 ? 
				   req.getHeader(SM_PROXY_CLIENT_IP_HDR) : req.getHeader(SM_WLS_PROXY_CLIENT_IP_HDR));
		 
		 
		 int i = clientIPAddress.indexOf(","); 
		 if (i == -1) i = clientIPAddress.length();
		 clientIPAddress = StringUtils.trimToEmpty(clientIPAddress).substring(0, i);
		 
		 return clientIPAddress;
	}



	protected AuditContext createAuditContext(AuthenticationContext authCtx) {
		return createAuditContext(authCtx.getCssApplicationName(),authCtx.getClientIPAddress(),
				                  authCtx.getUserNameForProfileRetrieval(), authCtx.getAuthZStationId());
	}

	protected AuditContext createAuditContext(String applicationName,
			String clientIPAddress, String userId, String stationId) {
		AuditContext auditContext = 
			new AuditContext(userId, 
							 clientIPAddress,
							 applicationName,
							 AuditIDGenerator.generateAuditID(),
							 null,
							 null,
							 stationId);
		return auditContext;
	}

	protected boolean isDifferentIdentityRequested(HttpServletRequest req, AuthenticationContext authCtx) {
		UserContext uContext = getUserContext(req);
		if (uContext==null) return true;
		
		// Compare authCtx to stored Uctx values. Note: authCtx values are populated based 
		// on headers set by Siteminder during authentication
		if (!uContext.getStationId().equals                (authCtx.getAuthZStationId())||
			!uContext.getUserId().equalsIgnoreCase         (authCtx.getUserNameForProfileRetrieval()) ||
			!uContext.getApplicationName().equalsIgnoreCase(authCtx.getCssApplicationName())){
			return true;
		}
			
		
		return false;
	}
				
	protected String getClientIPAddressFromReq(HttpServletRequest req) {

		 String clientIPAddress=(req.getHeader(SM_WLS_PROXY_CLIENT_IP_HDR) == null || 
				  (req.getHeader(SM_WLS_PROXY_CLIENT_IP_HDR)).length() == 0 ? 
				   req.getHeader(SM_PROXY_CLIENT_IP_HDR) : req.getHeader(SM_WLS_PROXY_CLIENT_IP_HDR));
		 
		 int i = clientIPAddress.indexOf(","); 
		 if (i == -1) i = clientIPAddress.length();
		 clientIPAddress = StringUtils.trimToEmpty(clientIPAddress).substring(0, i);
		 
		 return clientIPAddress;
	}
	
	protected String[] getSmMessageFields(HttpServletRequest req) {
		String rawSmMessages = req.getHeader(SM_MESSAGE_HDR);
		String[] smMessages = null;
		if (!StringUtils.isEmpty(rawSmMessages))
			smMessages = rawSmMessages.split(regExpr);
		return smMessages;
	}
}
