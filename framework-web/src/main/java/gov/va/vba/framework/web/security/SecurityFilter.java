/* 
 * SecurityFilter.java
 *
 * Copyright 2006 U.S. Dept Of Veterans Affairs.
 * U.S. Government PROPRIETARY/CONFIDENTIAL. Use is subject to security terms.
 */
package gov.va.vba.framework.web.security;


import gov.va.vba.framework.auditing.LoginAuditer;
import gov.va.vba.framework.auditing.LogoutAuditer;
import gov.va.vba.framework.common.SystemUtils;
import gov.va.vba.framework.domain.vo.ServiceVO;
import gov.va.vba.framework.esb.transformers.*;
import gov.va.vba.framework.exceptions.AuditException;
import gov.va.vba.framework.exceptions.handler.WebExceptionHandler;
import gov.va.vba.framework.logging.*;
import gov.va.vba.framework.services.*;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.StringTokenizer;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import gov.va.vba.framework.common.StringUtils;

/**
 * <p>
 * Main filter that intercepts all requests coming into VBA WebLogic Platform.
 * All requests coming into the VBA J2EE platform are intercepted by SiteMinder
 * and proxied to WebLogic. This security filter intercepts the proxied request
 * and controls a user's session and logout functionality
 * </p>
 * As part of the build process, each application will have a reference to this
 * filter in their web app.
 * 
 * @since Feb 21, 2005
 * @version
 * @author BEP-SE Team
 * 
 * <img src="doc-files/Security.gif">
 * 
 * <img src="doc-files/SiteMinder_Logic.gif">
 * 
 * <img src="doc-files/Security_UML.gif"> 
 */
public final class SecurityFilter extends BaseFilter implements Filter {
	
	private final static Logger logger=Logger.getLogger(SecurityFilter.class.getName());
	private static final String SUCCESS_CSS_PROFILE_MESSAGE = "Success"; 
	private FilterConfig _filterConfig = null;
	private static int _calls;
	// @EJB
	private CommonSecurityServiceV2 _securityService;
	// @EJB
	private List<String> excludedURLs = null;	
	private String _useGlobalErrorPage =  null;
	private String _loggerPackage = null;

	/**
	 * Per servlet 2.5 spec - blank constructor must be provided.
	 * 
	 * @param
	 * @return
	 * @exception 
	 */
	public SecurityFilter() {
	}

	/**
	 * All instance scoped object initializations done here.
	 *  
	 * @param	FilterConfig
	 * @return	
	 * @exception ServletException
	 */
	public void init(FilterConfig filterConfig) throws ServletException {
		
		Context ctx = null;
		if (filterConfig != null) {
			this._filterConfig = filterConfig;
	    	excludedURLs = new ArrayList<String>();
			String initParameter = null;
			try {
			    initParameter = filterConfig.getInitParameter("excludedURLs");
			} finally {		
				if (initParameter != null) {
					String[] excluded = initParameter.split(";");
		        	for (int i = 0; i < excluded.length; i++) {
		            	excludedURLs.add(excluded[i]);
		            	logger.debug("Excluding "+excluded[i]+" from securityFilter");
		        	}
				} else {
					logger.error("initParameter excludedURLs is null. Check the web.xml for filter securityFilter");
				}
		    				
			}
	    	
			_useGlobalErrorPage = _filterConfig.getInitParameter("useGlobalErrorPage");
			_loggerPackage = _filterConfig.getInitParameter("loggerPackage");
			logger.debug("loggerPackage [" + _loggerPackage + "]  useGlobalErrorPage [" + _useGlobalErrorPage + "]");
	    
		    try {
				logger.debug(SystemUtils.Key.JNDI_INTERFACE_TYPE+"="+SystemUtils.getProperty(SystemUtils.Key.JNDI_INTERFACE_TYPE));
		    	if (SystemUtils.getProperty(SystemUtils.Key.JNDI_INTERFACE_TYPE).equals(SystemUtils.REMOTE_INTERFACE)){  //remote interface
		    		logger.debug("SecurityFilter::init(): Using EJB remote interfaces.");
					Properties props = new Properties();
					props.put(Context.INITIAL_CONTEXT_FACTORY, WLS_CONTEXT_FACTORY);
					props.put(Context.PROVIDER_URL, SystemUtils.getProperty(SystemUtils.Key.JNDI_PROVIDER_URL));					
			    	ctx = new InitialContext(props);				
					_securityService = (CommonSecurityServiceV2)ctx.lookup("VbaSecurityServiceV2#"+CommonSecurityServiceRemoteV2.class.getName());
			    }
			    else {

					try {
						logger.debug("SecurityFilter::init(): Using EJB local interfaces.");
						ctx = new InitialContext();
						_securityService = (CommonSecurityServiceV2)ctx.lookup("java:comp/env/ejb/SecurityServiceV2Local");
					} catch (NamingException e) {
			    		logger.debug("SecurityFilter::init(): Local EJBs not available. Using EJB remote interfaces.");
				    	ctx = new InitialContext();
						_securityService = (CommonSecurityServiceV2)ctx.lookup("VbaSecurityServiceV2#"+CommonSecurityServiceRemoteV2.class.getName());
					}		    	
			    }
			} catch (Exception e) {
				logger.error("", e);
				throw new ServletException("Error looking up EJB's. ", e);
			}

		} else {
			logger.error("filterConfig is null for securityfilter");
		}
	}

	/**
	 * Release resources; speed up garbage collection.
	 * 
	 * @param
	 * @return
	 * @exception
	 */
	public void destroy() {

		this._filterConfig = null;
		_securityService = null;
	}

	/**
	 * Main service method that handles/intercepts all client requests. Checks to
	 * see if a logout is requested. If so, all sessions and SiteMinder/Java cookies 
	 * are killed and the client is redirected to the VBA home page. If a valid
	 * SiteMinder & HttpSessions exist, the request is forwarded to the next chain.
	 * If no valid session exists and no error conditions exist in SiteMinder, the
	 * user profile is retrieved from TuxedoService/CSS   
	 * 
	 * @param
	 * @return
	 * @exception
	 */
	public void doFilter(ServletRequest sreq, ServletResponse sresp,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest)sreq;
		HttpServletResponse resp = (HttpServletResponse)sresp;
		// TODO - validate for functional scenario
		_calls++;
		
        boolean isExcludedURL = false;
        for (int i = 0; i < excludedURLs.size(); i++) {
            if (req.getRequestURL().indexOf(excludedURLs.get(i)) > -1) {
                isExcludedURL = true;
                break;
            }
        }

        if (isExcludedURL) {
            chain.doFilter(req, resp);
        } else {
									
			AuthenticationContext authCtx = new AuthenticationContext();
			try{
				authCtx = processHeaders(req);
				authCtx.setAuditContext(createAuditContext(authCtx));
				
			}
			catch (Throwable t){
				logger.error("",t);
			}
			logRequest(req);
			try {		
				if (_filterConfig == null)
					return;
				else if (isLogoutRequest(req)) {
					logout(req, resp, req.getParameter(LOGOUT)==null ? null : new String[]{req.getParameter(LOGOUT)}, true, authCtx);
				}
				else {
					if (isSessionOrUserContextNull(req)) {
						logger.debug("Session and/or UserContext null. Populating UserContext.....");
						validateHeaders(req);
						processLogin(req, resp, chain, authCtx);
					}
					else if (isDifferentIdentityRequested(req, authCtx) && isSAMLRequest(req, authCtx)) {
							logger.debug("Session is not null, but the values from the request are not the same that the context:");
							logger.debug("User Context from Req: "+getUserContext(req).toString());
							logger.debug("AuthCtx from the incomming request: " +
									"StationId: "+authCtx.getAuthZStationId()+
									", UserName: "+authCtx.getAuthNUserName()+
									", AppName: "+authCtx.getCssApplicationName());
							validateHeaders(req);
							processLogin(req, resp, chain, authCtx);
					} else {
						logger.debug("A valid session exists for this user..");
						completeRequest(req, resp, chain, authCtx);
					}
				}
			}
			catch (Throwable t) {
	
				WebExceptionHandler handler = new WebExceptionHandler(_loggerPackage);
				handler.logStackTrace(t);
				req.setAttribute("ErrorTrackingId", handler.getErrorTrackingId());
				
				if("true".equals(_useGlobalErrorPage)){
					String redirectUrl = logout(req, resp, new String[] {
							"System Error$",
							"Internal VA employees: Please contact your local system administrator for assistance.",
							"Other users: Please email ITSC@va.gov or call 215-381-3087 for assistance."}, false, authCtx);				
					req.setAttribute("RedirectURL", redirectUrl);
					throw new ServletException(t);
				}
				else {
					logout(req, resp, new String[] {
						"System Error$",
						"Internal VA employees: Please contact your local system administrator for assistance.",
						"Other users: Please email ITSC@va.gov or call 215-381-3087 for assistance."}, true, authCtx);
				}
			}
		}
	}
	/**
	 * Logs the content of the HttpServletRequest to show the values that
	 * SiteMinder put on the request 
	 * 
	 * @throws
	 * @return
	 * @since October 14, 2016
	 */
	@SuppressWarnings("unchecked")
	static void logRequest(HttpServletRequest req) {
		String sessionId = req.getSession().getId();
		logger.debug("Framework Security Filter is processing a "+req.getMethod()+
				" request from "+req.getRequestURL().toString()+
				". Session ID: "+sessionId);
		String queryString = req.getQueryString();
		if (queryString == null) {
			logger.debug("This request has no query string.");
		} else {
			logger.debug("Request query string : "+queryString);
		}
		Map<String,String[]> parameterMap =  (Map<String,String[]>)req.getParameterMap();
		if (parameterMap != null && !parameterMap.isEmpty()) {
			Set<String> parameterKeys = parameterMap.keySet();
			for (String parameterKey : parameterKeys) {
				String parameterValue = req.getParameter(parameterKey);
				logger.debug("Request parameter "+parameterKey+" has value : "+parameterValue);
			}
		} else {
			logger.debug("This request has no parameters.");			
		}
		Enumeration<String> attributeNames = req.getAttributeNames();
		if (attributeNames.hasMoreElements()) {
			while (attributeNames.hasMoreElements()) {
				String attributeName = attributeNames.nextElement();
				logger.debug("Request attribute "+attributeName+" has value : "+req.getAttribute(attributeName));
			}
		} else {
			logger.debug("This request has no attributes.");			
		}

		Enumeration<String> headerNames = req.getHeaderNames();
		if (headerNames.hasMoreElements()) {
			while (headerNames.hasMoreElements()) {
				String headerName = headerNames.nextElement();
				logger.debug("Request header "+headerName+" has value : "+req.getHeader(headerName));
			}
		} else {
			logger.debug("This request has no headers.");			
		}

		Cookie[] cookies = req.getCookies();
		if (cookies == null) {
			logger.debug("This request has no cookies.");						
		} else {
			for (Cookie cookie : cookies) {
				logger.debug("Request cookie "+ cookie.getName()+" information follows:");
				logger.debug("Cookie Value   : "+cookie.getValue());
				logger.debug("Cookie Domain  : "+cookie.getDomain());
				logger.debug("Cookie Path    : "+cookie.getPath());
				logger.debug("Cookie Max Age : "+cookie.getMaxAge());
				logger.debug("Cookie Version : "+cookie.getVersion());
				logger.debug("Cookie Comment : "+cookie.getComment());
			}
		}
	}
	
	/**
	 * Retrieves SiteMinder headers and processes a login request. All exceptions are 
	 * propagated as friendly messages to the user on the login screen
	 * 
	 * @param 	req
	 * @param 	resp
	 * @throws 	ServletException
	 * @throws 	IOException
	 * @throws 	Exception	
	 * @return	
	 * @since	Aug 15, 2006
	 */
	private void processLogin(HttpServletRequest req, HttpServletResponse resp,	FilterChain chain, AuthenticationContext authCtx)
	throws ServletException, IOException, Exception {

		TuxedoSecurityProfile tuxUserProfile = null;
		StringBuilder message = null;
		boolean profileError = false;
		
		

		tuxUserProfile = getSecurityProfile(req, authCtx);			

		
		switch (tuxUserProfile.getRetCode()) {
		case '0'://success
			new LoginAuditer().audit(authCtx.getAuditContext(),true);
			
			//If a message different of Success is returned together with the 0 return code populate message so can be Pop up.
			if (!SUCCESS_CSS_PROFILE_MESSAGE.equals(tuxUserProfile.getMessage())) {
				message = new StringBuilder("WARNING! "+tuxUserProfile.getMessage()+".");
			}
			break;

		case '1': //Authorization error in CSSPROFILE			
			new LoginAuditer().audit(authCtx.getAuditContext(),false);
			profileError = true;
			break;			

		case '2'://Exception caught in the EJB Framework Layer
			new LoginAuditer().audit(authCtx.getAuditContext(),false);
			profileError = true;
			break;
			
		case '3'://Expired password. This should not happen after AD and is considered and Error
			new LoginAuditer().audit(authCtx.getAuditContext(),false);
			throw new TuxedoParserException("TuxedoService Parser Error. Unexpected return (3): "+tuxUserProfile.getMessage());
			
		default:
			new LoginAuditer().audit(authCtx.getAuditContext(),false);
			throw new TuxedoParserException("TuxedoService Parser Error: "+tuxUserProfile.getMessage());
		}
		
		if (profileError)
			logout(req, resp, new String[]{tuxUserProfile.getMessage()}, true, authCtx);
		else if (message != null) {//this will be hit if password warning is coming from CSS - should be removed...from wlsauthn 
			deleteCookie(SM_MESSAGE_COOKIE, "", 0, resp);
			req.setAttribute(POPUP_MESSAGE_ATTRIB, message);
			_filterConfig.getServletContext().getContext("/bep").getRequestDispatcher("/security/Popup.jsp").forward(req, resp);				
		}			
		else {
			deleteCookie(SM_MESSAGE_COOKIE, "", 0, resp);
			completeRequest(req, resp, chain, authCtx);
		}
	} 
	

	
	/**
	 * Destroys SiteMinder cookies for logout and error messages. This functions per the 
	 * J2EE 1.4 spec. for destroying cookies. 
	 * 
	 * Due to the static nature of the SiteMinder login page, all messages have to be 
	 * passed via cookie, so multiple messages have to be delimited with a special 
	 * character and then split on within the html code in the JSP/FCC. 
	 * 
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @param String - error message. User a "$" symbol to send a caption like "System Error"
	 * @throws ServletException
	 * @throws IOException
	 * @throws
	 * @return
	 * @since Jul 12, 2006
	 */
	private String logout(HttpServletRequest req, HttpServletResponse resp, String errorMsgs[], boolean redirect, AuthenticationContext authCtx)
			throws ServletException, IOException {

		Cookie originalCookie = null;
		HttpSession session = null;
		boolean showError = false;
		StringBuilder msgStr = null;
		String redirectURL = null;

		try{
			validateHeaders(req);
			new LogoutAuditer().audit(authCtx.getAuditContext(), true);
		}catch(ServletException e){
			logger.warn(new StringBuilder("The mandatory SiteMinder Headers are absent!").toString());
		}catch(AuditException ae){
			logger.warn(new StringBuilder("The authncontext is empty!").toString());
		}
			
		logger.debug("Logging out with message: "+Arrays.toString(errorMsgs));		
		if ((originalCookie = getCookie(req, SM_SESSION_COOKIE)) != null)
			deleteCookie(originalCookie.getName(), "LOGGEDOFF", 0, resp);
		else 
			logger.error("FATAL ERROR! : Could not find '"+SM_SESSION_COOKIE+"' cookie in this user's request.");
		
		if (errorMsgs != null && errorMsgs.length > 0) {
			msgStr = new StringBuilder("[CSS] ");
			for (String errMsg : errorMsgs) 
				msgStr.append(errMsg).append("|");			
			deleteCookie(SM_MESSAGE_COOKIE, msgStr.deleteCharAt(msgStr.length()-1).toString(), 6, resp);
			showError = true;
		}

		if ((session = req.getSession(false)) != null)
			session.invalidate();
		else
			logger.debug("WARNING! : User is logging out without a valid HttpSession/UserContext.");
		
		if (!showError)
			redirectURL = getRemoteServerHomeURL(req);
		else 
			redirectURL = req.getRequestURL().append(req.getQueryString() != null ? "?" + req.getQueryString(): "").toString();			
		logger.debug("Redirecting to: "+redirectURL);

		if(redirect){
			resp.sendRedirect(redirectURL);			
		}
		return redirectURL;
	}
	
	
	/**
	 * Returns the originating or proxy server's address. The logout logic for non-BEP client requests is temporary due to constraints
	 * associated with apps deployed at Terremark and other non-BEP/AITC hosted apps. 
	 * 
	 * @param req
	 * @return
	 * @throws
	 * @return
	 * @since Jul 20, 2006
	 */
	private String getRemoteServerHomeURL(HttpServletRequest req) {
		
		StringBuilder strBuilder = null;

		if (null != req.getParameter("redirect")) {
			logger.debug("Processing External/SSO client logout req: '"+req.getRequestURL()+"' to redirect URL: "+req.getParameter("redirect"));
			strBuilder = new StringBuilder(req.getParameter("redirect"));
		}
		else {
			logger.debug("Processing intranet logout req: '"+req.getRequestURL());
			strBuilder = new StringBuilder(req.getScheme()+"://").append(
					   req.getServerName()).append((req.getServerPort() != 0 ? ":"+ req.getServerPort() : ""));
		}
		logger.debug("Redirecting to: "+strBuilder);
		return (strBuilder.toString());
	}

	/**
	 * Populates the Http user context with data from the TuxedoService profile.
	 * 
	 * @throws Exception 
	 * @throws Ferror 
	 * @throws TPException 
	 * @throws TPReplyException 
	 * @throws TuxedoParserException 
	 * @throws
	 * @return	TuxedoSecurityProfile
	 * @since May 10, 2006
	 */
	private TuxedoSecurityProfile getSecurityProfile(HttpServletRequest request, AuthenticationContext authCtx) throws Exception {

		UserContext.Function[] functions = null;
		UserContext userCtx = null;
		HttpSession session = null;
		TuxedoSecurityProfile tuxProfile = null;
		
		tuxProfile = _securityService.getSecurityProfile(
				new ServiceVO(authCtx.getUserNameForProfileRetrieval(), authCtx.getAuthZStationId(), authCtx.getClientIPAddress(), authCtx.getCssApplicationName(),
						ServiceVO.SecurityService.CSSPROFILE), authCtx.getAuditContext(), null);
		userCtx = new UserContext();
		userCtx.setUserId(authCtx.getUserNameForProfileRetrieval());
		userCtx.setClientIPAddress(authCtx.getClientIPAddress());
		userCtx.setStationId(authCtx.getAuthZStationId());
		userCtx.setApplicationName(authCtx.getCssApplicationName());
		if (tuxProfile == null)
			throw new ServletException("Security Profile is null. Can't populate UserContext");
		else if (tuxProfile.getRetCode()=='0') {
			if (tuxProfile.getFunctions() != null && tuxProfile.getFunctions().size() > 0) {
				functions = new UserContext.Function[tuxProfile.getFunctions().size()];
				int idx = 0;
				for (Iterator<TuxedoSecurityProfile.Function> i = 
					tuxProfile.getFunctions().iterator(); i.hasNext();) {
					TuxedoSecurityProfile.Function tuxedoProfFunction = i.next();
					UserContext.Function function = userCtx.new Function();
					function.setName(tuxedoProfFunction.getName());
					function.setDisableInd(tuxedoProfFunction.getDisableInd());
					function.setAssignedValue(tuxedoProfFunction.getAssignedValue());
					functions[idx++] = function;
				}
				userCtx.setFunctions(functions);
			}
			userCtx.setPoaCodes(tuxProfile.getPoaCodes());
			userCtx.setRole(tuxProfile.getApplRole());
			userCtx.setEmailAddress(tuxProfile.getEmailAddress());
			userCtx.setFirstName(tuxProfile.getFirstName());
			userCtx.setLastName(tuxProfile.getLastName());
			userCtx.setMiddleInitial(tuxProfile.getMiddleName());
			userCtx.setParticipantId(Long.valueOf(tuxProfile.getParticipantId()));
			userCtx.setPhAreaCode(tuxProfile.getPhAreaCode());
			userCtx.setPhExt(tuxProfile.getPhExt());
			userCtx.setPhNum(tuxProfile.getPhNum());
			userCtx.setRole(tuxProfile.getApplRole());
			userCtx.setSecOfficeInd(tuxProfile.getSecOfficeInd());
			userCtx.setStationName(tuxProfile.getStationName());
			userCtx.setVaOrganization(tuxProfile.getVaOrganization());
			userCtx.setSsn(tuxProfile.getSsn());	
			userCtx.setBdnNumber(tuxProfile.getBdnNum());
			userCtx.setSecurityLevel(Short.parseShort(tuxProfile.getSecLevel()));
			session = request.getSession(true);

			cacheAuthContext(session, userCtx);
			logger.debug("UserContext created: " + userCtx.toString());
		}
		return tuxProfile;
	}

	/**
	 * Processes requests for both SSO and non-SSO clients
	 * 
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @param FilterChain
	 * @throws ServletException
	 * @throws IOException
	 * @since Jul 2, 2006, Apr 30, 2011
	 */
	private void completeRequest(HttpServletRequest req, HttpServletResponse resp, FilterChain chain, AuthenticationContext authCtx) throws IOException, 
			ServletException {

		resp.addHeader("Cache-Control", "no-cache");
		resp.addHeader("Cache-Control", "no-store");		

		resp.addHeader("Pragma", "no-cache");
		resp.addDateHeader("Expires", 0);
		if (req.isRequestedSessionIdValid() && StringUtils.trimToEmpty(req.getHeader("referer")).contains("login.fcc1"))// login.fcc
			logout(req, resp, new String[] { "Session has been terminated. Use of back button is prohibited." }, true, authCtx);
		else { 
			if (isNonSAMLRequest(req, authCtx)) {
				chain.doFilter(req, resp);
				logger.debug("Request completed");
			}
			else if (req.getParameter(SM_SSO_PORTAL_STATE)==null) {
				StringTokenizer tokenizer = new StringTokenizer(req.getQueryString(), PARAMETER_DELIMITER, false);				
				StringBuffer rearrangedQueryStr = new StringBuffer(URLPARAM_NAME_STNID).append("=").append(authCtx.getAuthZStationId()).append("&").append(URLPARAM_NAME_PROFILEID).append("=").append(authCtx.getAuthZProfileId()).append("&").append(COMMAND);
				String nameValuePair = "";
				while(tokenizer.hasMoreTokens()) {
					nameValuePair = tokenizer.nextToken();	
					/* as we prepare the query string for redirection in cases of SAML request
					 * we need to exclude the station id and profile id from original url 
					 * and use the values we get through headers for consistency and to prevent any tampering 
					 * with urls and mentioned above parameters				 
					 */
					if (!SM_PORTAL_URL.equals(nameValuePair.substring(0, nameValuePair.indexOf(NAME_VALUE_DELIMITER))) &&
							!URLPARAM_NAME_STNID.equals(nameValuePair.substring(0, nameValuePair.indexOf(NAME_VALUE_DELIMITER))) &&
							!URLPARAM_NAME_PROFILEID.equals(nameValuePair.substring(0, nameValuePair.indexOf(NAME_VALUE_DELIMITER)))   ) 
						rearrangedQueryStr = rearrangedQueryStr.append("&").append(nameValuePair);	
				}
				String smportalurl=req.getParameter(SM_PORTAL_URL);
				String finalQueryString = new StringBuffer(URLDecoder.decode(smportalurl, "UTF-8")).
												append("?").append(rearrangedQueryStr).toString();
				logger.debug("Redirecting to FSS/SSO URL: "+finalQueryString);
				resp.sendRedirect(finalQueryString);
			}
			else if (req.getParameter(SM_SSO_PORTAL_STATE)!=null) {				
				String stationID="";
				String profileId = "";
				if (StringUtils.isEmpty(authCtx.getAuthZStationId())){
					logger.debug("Authentication Context authCtx.getAuthzStationId() is empty");
					stationID="unknown";
				}
				else
					stationID = authCtx.getAuthZStationId();
					profileId = authCtx.getAuthZProfileId();
				logger.debug("Processing SSO back channel submit for stationID: "+stationID+" and profileId: " + profileId + " to: "+req.getRequestURL());				
				req.setAttribute("stationId", stationID);
				req.setAttribute("profileId", profileId);
				chain.doFilter(req, resp);
			}
		}
	}


	public CommonSecurityServiceV2 get_securityService() {
		return _securityService;
	}

	public void set_securityService(CommonSecurityServiceV2 _securityService) {
		this._securityService = _securityService;
	}
}