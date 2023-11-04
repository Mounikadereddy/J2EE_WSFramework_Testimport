package gov.va.vba.framework.css.cssiam.security;

import gov.va.vba.framework.auditing.LoginAuditer;
import gov.va.vba.framework.common.SystemUtils;
import gov.va.vba.framework.domain.vo.ServiceVO;
import gov.va.vba.framework.esb.transformers.TuxedoSecurityProfile;
import gov.va.vba.framework.logging.Logger;
import gov.va.vba.framework.services.CommonSecurityServiceRemoteV2;
import gov.va.vba.framework.services.CommonSecurityServiceV2;
import gov.va.vba.framework.services.TuxedoException;
import gov.va.vba.framework.web.security.UserContext;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

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
import javax.xml.bind.JAXBException;

/**
 * Class that implement the CSS Authorization for VBA Application deployed into
 * the BEP WebLogic Servers.<BR>
 * This class needs to be added to the web.xml application descriptors of all
 * VBA BEP Apps that need to <BR>
 * authorize users to access corporate information.<BR>
 * &#60;filter&#62;<BR>
 * &nbsp;&nbsp;
 * &#60;filter-name&#62;CSSIAMAuthorizationFilter&#60;/filter-name&#62;<BR>
 * &nbsp;&nbsp; &#60;filter-class&#62;gov.va.vba.framework.css.cssiam.security.
 * CSSIAMAuthorizationFilter&#60;/filter-class&#62;<BR>
 * &#60;/filter&#62;<BR>
 * <BR>
 * &#60;filter-mapping&#62;<BR>
 * &nbsp;&nbsp;
 * &#60;filter-name&#62;CSSIAMAuthorizationFilter&#60;/filter-name&#62;<BR>
 * &nbsp;&nbsp; &#60;url-pattern&#62;/bephostedapp/iam/*&#60;/url-pattern&#62;<BR>
 * &nbsp;&nbsp; &#60;dispatcher&#62;FORWARD&#60;/dispatcher&#62;<BR>
 * &nbsp;&nbsp; &#60;dispatcher&#62;REQUEST&#60;/dispatcher&#62;<BR>
 * &#60;/filter-mapping&#62; <BR>
 * 
 * @author VHAISPVANEGI
 * 
 */
public class CSSIAMAuthorizationFilter extends FilterBase implements Filter {

	/**
	 * Supported applications loaded from the configuration file
	 */
	private BepHostedMappedApps mappedApps;

	/**
	 * Supported environments loaded from the configuration file
	 */
	private BepHostedEnvs bepHostedEnvs;
	
	/**
	 * List of mandatory IAM Traits expected in the Http request. Request will fail if not present.
	 */
	private IamMandatoryTraits iamMandatoryTraits;
	
	/**
	 * List of URLs to exclude from filtering
	 */
	private List<String> excludedURLs = null;

	private Logger logger = Logger.getLogger(CSSIAMAuthorizationFilter.class.getName());
	private FilterConfig filterConfig;
	private CommonSecurityServiceV2 _securityService;
	
	private static final String LOGOUT_MSG = "You have been successfully logged out.";

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		setFilterConfig(filterConfig);


    	// Get list of URLs excluded from filtering
		if (filterConfig != null) {
	    	excludedURLs = new ArrayList<String>();
			String initParameter = null;
			initParameter = filterConfig.getInitParameter("excludedURLs");
			if (initParameter != null) {
				String[] excluded = initParameter.split(";");
				for (int i = 0; i < excluded.length; i++) {
					excludedURLs.add(excluded[i]);
					logger.debug("Excluding "+excluded[i]+" from securityFilter");
				}
			} else {
				logger.warn("initParameter excludedURLs is null. Check the web.xml for filter CSSIAMAuthorizationFilter");
			}
		}
    	
    	// Get Persistence and EJB Services References
	    try {
			Context ctx;
			
	    	logger.debug(SystemUtils.Key.JNDI_INTERFACE_TYPE+"="+SystemUtils.getProperty(SystemUtils.Key.JNDI_INTERFACE_TYPE));
			if (SystemUtils.getProperty(SystemUtils.Key.JNDI_INTERFACE_TYPE).equals(SystemUtils.REMOTE_INTERFACE)){  //remote interface
	    		logger.debug("CSSIAMAuthorizationFilter::init(): Trying to initialize EJB interfaces");
				Properties props = new Properties();
				props.put(Context.INITIAL_CONTEXT_FACTORY, WLS_CONTEXT_FACTORY);
				props.put(Context.PROVIDER_URL, SystemUtils.getProperty(SystemUtils.Key.JNDI_PROVIDER_URL));
		    	ctx = getInitialContext(props);			
				_securityService = (CommonSecurityServiceV2)ctx.lookup("VbaSecurityServiceV2#"+CommonSecurityServiceRemoteV2.class.getName());
		    }
		    else {
				try {
					logger.debug("CSSIAMAuthorizationFilter::init(): Trying to use EJB local interfaces.");
					ctx = getInitialContext(null);
					_securityService = (CommonSecurityServiceV2)ctx.lookup("java:comp/env/ejb/SecurityServiceV2Local");
					logger.debug("CSSIAMAuthorizationFilter::init(): EJB local interface established.");
				} catch (NamingException e) {
		    		logger.debug("CSSIAMAuthorizationFilter::init(): Local EJBs not available. Trying to use EJB remote interfaces.");
			    	ctx = getInitialContext(null);
					_securityService = (CommonSecurityServiceV2)ctx.lookup("VbaSecurityServiceV2#"+CommonSecurityServiceRemoteV2.class.getName());
					logger.debug("CSSIAMAuthorizationFilter::init(): EJB remote interface established.");
				}		    	
		    }
		} catch (Exception e) {
			logger.error("", e);
			throw new ServletException("Error looking up EJB's. ", e);
		}
	    
		// Get the Filter Configuration from the XML file (cssiam-filter-config.xml)
		try {
			CSSIAMFilterConfig cssIAMFilterConfig = AuthorizationContext.loadFilterConfig(this.getClass().getClassLoader());
			this.bepHostedEnvs = cssIAMFilterConfig.getBepHostedEnvs();
			this.mappedApps = cssIAMFilterConfig.getBepHostedMappedApps();
			this.iamMandatoryTraits = cssIAMFilterConfig.getIamMandatoryTraits();
		} catch (JAXBException e) {
			throw new ServletException(
					"Exception loading configuration from the CSS IAM Filter Configuration File: cssiam-filter-config.xml",e);
		}
	}

	/**
	 * Filter Config getter
	 * 
	 * @return the filter config set to the filter at init() time
	 */
	public FilterConfig getFilterConfig() {
		return filterConfig;
	}

	/**
	 * Filter Config setter
	 * 
	 * @param filterConfig
	 */
	public void setFilterConfig(FilterConfig filterConfig) {
		this.filterConfig = filterConfig;
	}

	@Override
	public void destroy() {
		// Destroy EJB Service Referencs

		mappedApps = null;
		bepHostedEnvs = null;
	}

	@Override
	public void doFilter(ServletRequest srequest, ServletResponse sresponse,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) srequest;
		HttpServletResponse response = (HttpServletResponse) sresponse;

		//CHECK IF THE URL IS EXCLUDED
        boolean isExcludedURL = isRequestURLExcluded(request);
        if (isExcludedURL) {
            chain.doFilter(srequest,sresponse);
            logger.debug("Excluded URL, returning");
            return;
        }
		
		HttpSession session = request.getSession(false);

		logger.debug("Received Request: "+request.getRequestURL().toString());
		try {
			logRequest(request);
		} catch (NullPointerException e) {
			logger.error("Null pointer when trying to log request, " + e.getMessage());
		}
		
		// IF IS LOGOUT REQUEST
		if (isLogoutRequest(request)) {
			//Get Error Message for logout if any
			String errorMessage = request.getParameter("errorMsg");
			
			logout(request, response, errorMessage);
			return;
		}

		// ELSE [IS NOT LOGOUT REQUEST]
		// IF IS SESSION NULL OR THERE IS NO AN AUTHORIZATION CONTEXT
		if (session == null || 
				(session != null && session.getAttribute(FilterBase.SESSION_AUTHZ_CONTEXT) == null)) {

			//CREATE A NEW AUTHORIZATION CONTEXT FROM THE IAM SITEMINDER HEADERS,
			//SET THE AUTHORIZATION CONTEXT TO THE SESSION
			//AND REDIRECT THE REQUEST TO THE STATION SELECTOR
			AuthorizationContext authzCtx = new AuthorizationContext();

			processIAMHeaders(request, response, authzCtx);

			logger.debug("No Session or UserContext present in the request...building a new one");
			session = request.getSession(true);
			session.setAttribute(SESSION_AUTHZ_CONTEXT, authzCtx);

			logger.debug("No station has been selected by the user yet...forward request to the UI for selection");

			redirectToSelector(request, response, authzCtx);
			return;
		} else if (session.getAttribute(FilterBase.SESSION_AUTHZ_CONTEXT) != null 
				&& session.getAttribute(FilterBase.SESSION_USER_CONTEXT) == null) {
			//SESSION EXISTS AND CONTAINS AN AUHTORIZATION CONTEXT
			//BUT SESSION DOESNT CONTAINS A USER CONTEXT
			//REQUEST SHOULD BE COMING BACK FROM SELECTOR UI
			//REQUEST SHOULD HAVE A SELECTED STATION ID
			logger.debug("Authorization Context found in the Session");
			AuthorizationContext authzCtx;
			
			String stationId = request.getParameter(SELECTOR_STATION);
			String testUserId = request.getParameter(SELECTOR_TEST_USER_ID);
			authzCtx = (AuthorizationContext)session.getAttribute(FilterBase.SESSION_AUTHZ_CONTEXT);

	        preventNonTestUserOverride(request, response);

			//Look for a selection on the station id
			if (stationId != null && !stationId.isEmpty()) {
				authzCtx.setStation(stationId);
				logger.debug("Station: ["+authzCtx.getStation()+"] selected by the user loading the UserContext");
				
				//Look for a selection of Test User Id
				if(testUserId != null && !testUserId.isEmpty()) {
					authzCtx.setTestUserId(testUserId);
					logger.debug("Test User Id: ["+authzCtx.getTestUserId()+"] selected by the user");
				}
				
				processAuthorizationRequestToEJB(request, response, authzCtx);
				
			} else if (authzCtx.getStation() != null && !authzCtx.getStation().isEmpty()) {
				//Look if an station has already been selected for this authorization context
				logger.debug("Station: ["+authzCtx.getStation()+"] previuosly selected by the user loading the UserContext");
				
				//Look for a selection of Test User Id
				if(testUserId != null && !testUserId.isEmpty()) {
					authzCtx.setTestUserId(testUserId);
					logger.debug("Test User Id: ["+authzCtx.getTestUserId()+"] selected by the user");
				}

				processAuthorizationRequestToEJB(request, response, authzCtx);
				
			} else {
				logger.warn("Missing selected station, reprocess SiteMinder Headers and send to the Station Selector UI");
				
				processIAMHeaders(request, response, authzCtx);
				session.setAttribute(SESSION_AUTHZ_CONTEXT, authzCtx);
				
				redirectToSelector(request, response, authzCtx);
				return;
			}
			chain.doFilter(request, response);
		} else {
			// SESSION EXISTS AND CONTAINS BOTH AN AUTHORIZATION CONTEXT AND A USER CONTEXT
			logger.debug("User Context present in the Session proceed since it has already been authorized");
			
			//compare new user with old user and if different send to Station Selector
			AuthorizationContext authzCtx = (AuthorizationContext)session.getAttribute(FilterBase.SESSION_AUTHZ_CONTEXT);
			if (authzCtx.hasRequestInSessionChanged(request)) {
				//CREATE A NEW AUTHORIZATION CONTEXT FROM THE IAM SITEMINDER HEADERS,
				//SET THE AUTHORIZATION CONTEXT TO THE SESSION
				//AND REDIRECT THE REQUEST TO THE STATION SELECTOR
				
				logger.debug("The username in the authorization context is not the same that the one requested. Sending to Selector UI");
				authzCtx = new AuthorizationContext();

				processIAMHeaders(request, response, authzCtx);

				
				session = request.getSession(true);
				session.setAttribute(SESSION_AUTHZ_CONTEXT, authzCtx);

				logger.debug("No station has been selected by the user yet...forward request to the UI for selection");

				redirectToSelector(request, response, authzCtx);
				return;
			} else {
				chain.doFilter(srequest, sresponse);
			}
		}
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @param authzCtx
	 * @throws IOException
	 * @throws UnsupportedEncodingException
	 * @throws ServletException
	 */
	protected void processAuthorizationRequestToEJB(HttpServletRequest request,
			HttpServletResponse response, AuthorizationContext authzCtx)
			throws IOException, ServletException {
		try {
			StringBuilder returnMessage = new StringBuilder();
			if (getCssUserContextToSession(request, response, authzCtx, returnMessage)) {
				logger.debug("Profile for [station,user] loaded succesfully to the session userContext: ["+authzCtx.getUsernameForCSSProfile()+","+authzCtx.getStation()+"]");
			} else if (authzCtx.isAllowedUserOverwrite()) {
				//If is an environment where User Overwrite is allowed redirect to the selector for retry
				logger.debug("Error loading CSS Profile ["+authzCtx.getUsernameForCSSProfile()+","+authzCtx.getStation()+"], sending to Selector for Retry");
				redirectToSelector(request, response, authzCtx, returnMessage.toString());
			} else {
				logger.debug("Unable to load CSS Profile for ["+authzCtx.getUsernameForCSSProfile()+","+authzCtx.getStation()+"], logout");
				logout(request, response, returnMessage.toString());
			}
		} catch (CSSProfileSecurityFrameworkException e) {
			logout(request, response, e.getMessage());
			throw new ServletException("Error getting the CSS USer Context populated", e);
		}
	}

	/**
	 * Redirect to the selector for gathering of Station Id and optionally Test User Id with no initial error message
	 * @param request HttpRequest
	 * @param response HttpResposne
	 * @param authzCtx AuthorizationContext that contains the parsed parameters
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 */
	private void redirectToSelector(HttpServletRequest request,
			HttpServletResponse response, AuthorizationContext authzCtx)
			throws UnsupportedEncodingException, IOException {
		redirectToSelector(request, response, authzCtx, "");
	}
	
	
	/**
	 * Redirect to the selector for gathering of Station Id and optionally Test User Id with initial error message
	 * @param request HttpRequest
	 * @param response HttpResposne
	 * @param authzCtx AuthorizationContext that contains the parsed parameters
	 * @param errorMessage The error message that the Station Selector page will display on loading
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 */
	private void redirectToSelector(HttpServletRequest request,
			HttpServletResponse response, AuthorizationContext authzCtx, String errorMessage)
			throws UnsupportedEncodingException, IOException {
		// Get the target URL and pass it as a query Parameter
		String targetUrl = getTargetForRedirect(request);

		StringBuffer queryString = appendSelectorParametersToQueryString(
				request.getQueryString(), authzCtx.getUsername(),
				authzCtx.getApplication(), authzCtx.getIpAddress(),targetUrl, errorMessage);
		response.sendRedirect(STATION_SELECTOR_UI_URL + "?"
				+ queryString.toString());
	}

	/**
	 * Determine if the Requested URL is included in the list of the configurable URLs to exclude
	 * @param request HttpServletRequest
	 * @return true if the Requested URL should be excluded, false otherwise
	 */
	private boolean isRequestURLExcluded(HttpServletRequest request) {
		boolean isExcludedURL = false;
        for (int i = 0; i < excludedURLs.size(); i++) {
            if (request.getRequestURL().indexOf(excludedURLs.get(i)) > -1) {
                isExcludedURL = true;
                break;
            }
        }
		return isExcludedURL;
	}

	/**
	 * Process incoming HttpHeaders populating the AuhtorizationContext 
	 * @param request
	 * @param response
	 * @param authzCtx Contains the values populated from the incoming IAM Headers
	 * @throws IOException
	 * @throws ServletException
	 */
	private void processIAMHeaders(HttpServletRequest request,
			HttpServletResponse response, AuthorizationContext authzCtx)
			throws IOException, ServletException {
		//Process Headers
		try {
			authzCtx.mapRequestToAuthorizationCtx(request, mappedApps,
					bepHostedEnvs, iamMandatoryTraits);
			logger.debug("Authorization context succesfully mapped from the request: "
					+ authzCtx);
		} catch (MissingHeaderException e) {
			logger.error(e.getMessage());
			logout(request, response, e.getMessage());
			throw new ServletException(
					"Error during CSS authorization filter", e);

		} catch (UnrecognizedApplicationException e) {
			logger.error(e.getMessage());
			logout(request, response, e.getMessage());
			throw new ServletException(
					"Error during CSS authorization filter", e);
		}
	}

	/**
	 * Append the values pair to the queryString passed as parameter. If the
	 * names in the nameValuePairs are already present in the queryString the
	 * string as paramaters, the values will be overwritten with the ones in
	 * nameValuePairs
	 * 
	 * @param queryString
	 *            The Query String to build
	 * @param targetUrl
	 *            Target URL + Query Params
	 * @return A String with the modified queryString
	 * @throws UnsupportedEncodingException
	 */
	private StringBuffer appendSelectorParametersToQueryString(
			String queryString, String username, String application, 
			String ipAddress, String targetUrl, String errorMessage)
			throws UnsupportedEncodingException {
		StringBuffer rearrangedQueryString = new StringBuffer();

		if (queryString != null) {
			String[] pairs = queryString.split("&");

			// remove any selector parameters in the queryString
			for (String pair : pairs) {
				int idx = pair.indexOf("=");
				String parameterName;
				try {
					parameterName = URLDecoder.decode((pair.substring(0, idx)),
							"UTF-8");
				} catch (UnsupportedEncodingException e) {
					// Unrecognized encoding --this shouldnt happen
					parameterName = pair.substring(0, idx);
				}
				if (!SELECTOR_USERNAME.equals(parameterName)
						&& !SELECTOR_APPLICATION.equals(parameterName)
						&& !SELECTOR_CSSIAM_TARGET.equals(parameterName)
						&& !SELECTOR_CLIENT_ADDRESS.equals(parameterName)
						&& !SELECTOR_ERROR_MESSAGE.equals(parameterName)) {
					rearrangedQueryString
							.append(rearrangedQueryString.length() == 0 ? pair
									: "&" + pair);
				} else {
					logger.debug("Found a selector parameter in the query string. Removing it to use the one from authorization context instead");
				}
			}
		}

		// append the selector parameters
		// username
		rearrangedQueryString
				.append(rearrangedQueryString.length() == 0 ? SELECTOR_USERNAME
						+ "=" + username : "&" + SELECTOR_USERNAME + "="
						+ username);

		// application
		rearrangedQueryString.append("&" + SELECTOR_APPLICATION + "="
				+ application);

		// client address
		rearrangedQueryString.append("&" + SELECTOR_CLIENT_ADDRESS + "="
				+ ipAddress);

		// CSSIAM_TARGET
		rearrangedQueryString.append("&" + SELECTOR_CSSIAM_TARGET + "="
				+ URLEncoder.encode(targetUrl, "UTF-8"));
		
		//ERROR Message
		rearrangedQueryString.append("&" + SELECTOR_ERROR_MESSAGE + "="
				+ URLEncoder.encode(errorMessage, "UTF-8"));

		return rearrangedQueryString;
	}
	
	/**
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	private void preventNonTestUserOverride(
			HttpServletRequest request, HttpServletResponse response) 
					throws IOException, ServletException {
		//Use of testUserId in non-test environment throws error
        try {
			if (!AuthorizationContext.isRequestURLMatchedToTestEnv(request.getRequestURL().toString(), bepHostedEnvs) &&
					request.getParameter(SELECTOR_TEST_USER_ID) != null) {
				String errorMsg = "User provided a testUserId for override in a non-test environment";
				logger.error(errorMsg);
				logout(request, response, errorMsg);
				throw new ServletException(
						"Error during CSS authorization filter. " + errorMsg);
			}
		} catch (UnrecognizedApplicationException e) {
			logger.error("Unable to determine if the environment is for testing/production from the request. ",e);
		}
	}

	/**
	 * Getter for mappedApps
	 * 
	 * @return
	 */
	public BepHostedMappedApps getMappedApps() {
		return mappedApps;
	}

	/**
	 * Setter for mappedApps
	 * 
	 * @param mappedApps
	 */
	public void setMappedApps(BepHostedMappedApps mappedApps) {
		this.mappedApps = mappedApps;
	}

	/**
	 * Get the container of all environments
	 * 
	 * @return container of all bep environments
	 */
	public BepHostedEnvs getBepHostedEnvs() {
		return bepHostedEnvs;
	}

	/**
	 * Set all the possible environments from the configuration
	 * 
	 * @param bepHostedEnvs
	 *            - the class contain the env's
	 */
	public void setBepHostedEnvs(BepHostedEnvs bepHostedEnvs) {
		this.bepHostedEnvs = bepHostedEnvs;
	}
	

	/**
	 * Getter
	 * @return
	 */
	public IamMandatoryTraits getIamMandatoryTraits() {
		return iamMandatoryTraits;
	}

	/**
	 * Setter
	 * @param iamMandatoryTraits
	 */
	public void setIamMandatoryTraits(IamMandatoryTraits iamMandatoryTraits) {
		this.iamMandatoryTraits = iamMandatoryTraits;
	}

	/**
	 * Is the Session or userContext attribute null
	 * 
	 * @param req
	 *            The HttpServletRequest incoming to the BEP Hosted Test
	 *            Application
	 * @return -'true' if the session is null or if the attribute userContext is
	 *         not in the session, or if the authzContext is not in the session <BR>
	 *         - 'false' if the session exist and contains not null userContext
	 *         attribute and a not null authzContext attribute
	 */
	protected boolean isNewUnauthorizedRequest(HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		return (session == null
				|| (session != null && session
						.getAttribute(FilterBase.SESSION_USER_CONTEXT) == null) || (session != null && session
				.getAttribute(FilterBase.SESSION_AUTHZ_CONTEXT) == null));
	}

	/**
	 * Logout method that invalidates the section and send user out of the
	 * application. The user will be redirected to the error pages if the logout
	 * is due to an error.
	 * 
	 * @param request
	 * @param response
	 * @param errorMessage
	 *            The message to be displayed when user is out
	 * @throws IOException
	 */
	protected void logout(HttpServletRequest request,
			HttpServletResponse response, String errorMessage)
			throws IOException {
		HttpSession session;
		
		String targetUrl = getTargetForRedirect(request);
		
		if ((session = request.getSession(false)) != null) {
			session.invalidate();
		}

		if (errorMessage != null && !errorMessage.isEmpty()) {
			StringBuffer errorPageRedirect = new StringBuffer();
			errorPageRedirect.append(ERROR_PAGES_URL + errorMessage);
			errorPageRedirect.append("&" + SELECTOR_CSSIAM_TARGET + "="
					+ URLEncoder.encode(targetUrl, "UTF-8"));
			response.sendRedirect(errorPageRedirect.toString());
		} else {
			StringBuffer logoutPageRedirect = new StringBuffer();
			logoutPageRedirect.append(LOGOUT_PAGE_URL + LOGOUT_MSG);
			logoutPageRedirect.append("&" + SELECTOR_CSSIAM_TARGET + "="
					+ URLEncoder.encode(targetUrl, "UTF-8"));
			response.sendRedirect(logoutPageRedirect.toString());
		}
	}

	/**
	 * Get the URL targeted originally for the filtered request plus its query
	 * parameters
	 * 
	 * @param request
	 *            The request
	 * @return the target
	 */
	private String getTargetForRedirect(HttpServletRequest request) {

		StringBuffer requestURL = request.getRequestURL();
		String queryParams = request.getQueryString();

		// Reconstruct the original request
		String targetUrl = requestURL
				+ (queryParams == null ? "" : "?" + queryParams);
		logger.debug("Request URL:" + request.getRequestURL());
		logger.debug("Query String:" + request.getQueryString());
		logger.debug("TARGET:" + targetUrl);

		return targetUrl;
	}

	
	/**
	 * Get the CSS Profile from the security framework and process the response
	 * 
	 * @param 	req HttpServletRequest
	 * @param 	resp HttpServletResponse
	 * @return 'false' If there was an authorization error loading the CSS Profile.<BR>'true' if the CSS Profile was loaded into the session successfully.  
	 * @throws CSSProfileSecurityFrameworkException 
	 * @throws 	ServletException
	 * @throws 	IOException
	 * @throws 	Exception	
	 */
	protected boolean getCssUserContextToSession(HttpServletRequest req, HttpServletResponse resp, AuthorizationContext authzCtx, StringBuilder returnMessage) throws CSSProfileSecurityFrameworkException, IOException
	 {

		TuxedoSecurityProfile tuxUserProfile = null;
		boolean profileLoaded = true;

		tuxUserProfile = getSecurityProfile(req, authzCtx);			

		switch (tuxUserProfile.getRetCode()) {
		case '0'://Success
			//Auditing a successful login
			auditLoginAttempt(authzCtx,true);
			profileLoaded = true;
			break;

		case '1': //Authorization error in CSSPROFILE
			//Auditing a unsuccessful login
			auditLoginAttempt(authzCtx,false);
			profileLoaded = false;
			break;			

		case '2'://Exception caught in the EJB Framework Layer
			//Auditing a unsuccessful login
			auditLoginAttempt(authzCtx,false);
			profileLoaded = false;
			break;
			
		default:
			auditLoginAttempt(authzCtx,false);
			throw new CSSProfileSecurityFrameworkException("TuxedoService Parser Error: "+tuxUserProfile.getMessage());
		}
		returnMessage.append(tuxUserProfile.getMessage());
		return profileLoaded;
		
	}

	/**
	 * Audit the login operation and its result
	 * @param authzCtx
	 * @param isSuccessAttempt
	 */
	protected void auditLoginAttempt(AuthorizationContext authzCtx, boolean isSuccessAttempt) {
		new LoginAuditer().audit(authzCtx.getAuditContext(), isSuccessAttempt);
	}
	

	private boolean isLogoutRequest(HttpServletRequest req) {
		
		return ((req.getRequestURI().toLowerCase().contains(FilterBase.LOGOUT_URL)||req.getParameterMap().containsKey(FilterBase.LOGOUT_URL)||  
				  (req.getQueryString() != null && req.getQueryString().toLowerCase().contains(FilterBase.LOGOUT_URL)))||
				  (req.getRequestURI().toLowerCase().contains(FilterBase.LOGOFF)||req.getParameterMap().containsKey(FilterBase.LOGOFF)|| 
						  (req.getQueryString() != null && req.getQueryString().toLowerCase().contains(FilterBase.LOGOFF))));
	}
	
	/**
	 * Populates the UserContext with data from the TuxedoService profile.
	 * 
	 * @throws Exception 
	 * @return	TuxedoSecurityProfile
	 * @throws CSSProfileSecurityFrameworkException 
	 */
	protected TuxedoSecurityProfile getSecurityProfile(HttpServletRequest request, AuthorizationContext authzCtx) throws CSSProfileSecurityFrameworkException {

		UserContext.Function[] functions = null;
		TuxedoSecurityProfile tuxProfile = null;
		
		try {
			tuxProfile = _securityService.getSecurityProfile(
					new ServiceVO(authzCtx.getUsernameForCSSProfile(), authzCtx.getStation(), authzCtx.getIpAddress(), 
							authzCtx.getApplication(),ServiceVO.SecurityService.CSSPROFILE), authzCtx.getAuditContext(), null);
		} catch (TuxedoException e) {
			throw new CSSProfileSecurityFrameworkException("Exception calling the security framework",e);
		}
		if (tuxProfile == null)
			throw new CSSProfileSecurityFrameworkException("CSS Security Profile returned from the security framework is null. Can't populate UserContext");
		else if (tuxProfile.getRetCode()=='0') {
			// A successful response was obtained from the security framework
			UserContext userCtx = new UserContext();
			userCtx.setUserId(authzCtx.getUsernameForCSSProfile());
			userCtx.setClientIPAddress(authzCtx.getIpAddress());
			userCtx.setStationId(authzCtx.getStation());
			userCtx.setApplicationName(authzCtx.getApplication());
			if (tuxProfile.getFunctions() != null && tuxProfile.getFunctions().size() > 0) {
				functions = new UserContext.Function[tuxProfile.getFunctions().size()];
				int idx = 0;
				for (Iterator<TuxedoSecurityProfile.Function > i = tuxProfile.getFunctions().iterator(); i.hasNext();) {
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
			logger.debug("UserContext populated: " + userCtx.toString());
			
			//put the User Context in the Session
			HttpSession session = request.getSession(true);
			session.setAttribute(SESSION_USER_CONTEXT, userCtx);
		}
		return tuxProfile;
	}
	
	/**
	 * Get the initial context
	 * @param props Properties for the constructor.
	 * @return
	 * @throws NamingException
	 */
	public Context getInitialContext(Properties props) throws NamingException {
		if (props != null) {
			return new InitialContext(props);
		} else {
			return new InitialContext();
		}
	}
	
	/**
	 * Logs the content of the HttpServletRequest to show the values that
	 * SiteMinder put on the request 
	 * 
	 * @throws
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected void logRequest(HttpServletRequest req) throws NullPointerException {
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
}
