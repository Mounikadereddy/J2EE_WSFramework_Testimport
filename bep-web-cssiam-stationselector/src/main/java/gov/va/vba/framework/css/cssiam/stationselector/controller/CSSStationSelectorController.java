package gov.va.vba.framework.css.cssiam.stationselector.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import javax.annotation.PostConstruct;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import gov.va.vba.framework.auditing.AuditIDGenerator;
import gov.va.vba.framework.common.AuditContext;
import gov.va.vba.framework.common.SystemUtils;
import gov.va.vba.framework.css.cssiam.domain.entities.CssUser;
import gov.va.vba.framework.css.cssiam.domain.entities.UserStation;
import gov.va.vba.framework.css.cssiam.security.AuthorizationContext;
import gov.va.vba.framework.css.cssiam.security.BepHostedEnvs;
import gov.va.vba.framework.css.cssiam.security.CSSIAMFilterConfig;
import gov.va.vba.framework.css.cssiam.security.FilterBase;
import gov.va.vba.framework.css.cssiam.security.UnrecognizedApplicationException;
import gov.va.vba.framework.logging.Logger;
import gov.va.vba.framework.services.CommonSecurityServiceRemoteV2;
import gov.va.vba.framework.services.CommonSecurityServiceV2;
import gov.va.vba.framework.services.CssUserRepositoryException;

/**
 * This class controls the Web Pages related to the selection of a CSS Station for a user. Contains error pages controls
 * that inform the user of error encounter during the CSS Authorization Process. Also, contains the page the allows the user
 * to select the single station that he/she wants to use for authorization to access an application. After the user selects 
 * the desired station, he/she will sent to the CSS IAM Authorization Filter for loading the CSS Profile based on the selected
 * station.
 * @author Ivan Vanegas <VHAISPVANEGI>
 * 
 */
@Controller
@PropertySource("classpath:cssiam-stationselector.properties")
public class CSSStationSelectorController {
	
	private final static Logger logger = Logger.getLogger(CSSStationSelectorController.class);	

	/**
	 * WebLogic Context Factory
	 */
	public static final String WLS_CONTEXT_FACTORY = "weblogic.jndi.WLInitialContextFactory";

	private static final String LOGOUT_MSG = "You have been successfully logged out.";

	@Autowired
	Environment env;
	

	private CommonSecurityServiceV2 _securityService;
	private BepHostedEnvs bepHostedEnvs;

	
	@InitBinder
	/**
	 * Bind the formValidator
	 * @param binder
	 */
	protected void initBinder(WebDataBinder binder) {
		//binder.setValidator(formValidator);
	}
	
	@PostConstruct
	public void init() throws ServletException {
		Context ctx = null;
		    try {
				logger.debug(SystemUtils.Key.JNDI_INTERFACE_TYPE+"="+SystemUtils.getProperty(SystemUtils.Key.JNDI_INTERFACE_TYPE));
		    	if (SystemUtils.getProperty(SystemUtils.Key.JNDI_INTERFACE_TYPE).equals(SystemUtils.REMOTE_INTERFACE)){  //remote interface
		    		logger.debug("Using EJB remote interfaces.");
					Properties props = new Properties();
					props.put(Context.INITIAL_CONTEXT_FACTORY, WLS_CONTEXT_FACTORY);
					props.put(Context.PROVIDER_URL, SystemUtils.getProperty(SystemUtils.Key.JNDI_PROVIDER_URL));					
			    	ctx = new InitialContext(props);				
					_securityService = (CommonSecurityServiceV2)ctx.lookup("VbaSecurityServiceV2#"+CommonSecurityServiceRemoteV2.class.getName());
			    }
			    else {

					try {
						logger.debug("init(): Using EJB local interfaces.");
						ctx = new InitialContext();
						_securityService = (CommonSecurityServiceV2)ctx.lookup("java:comp/env/ejb/SecurityServiceV2Local");
					} catch (NamingException e) {
			    		logger.debug("init(): Local EJBs not available. Using EJB remote interfaces.");
				    	ctx = new InitialContext();
						_securityService = (CommonSecurityServiceV2)ctx.lookup("VbaSecurityServiceV2#"+CommonSecurityServiceRemoteV2.class.getName());
					}		    	
			    }
			} catch (Exception e) {
				logger.error("", e);
				throw new IllegalStateException("Error looking up EJB's. ", e);
			}
		    
		    
			// Get the Filter Configuration from the XML file (cssiam-filter-config.xml)
			try {
				CSSIAMFilterConfig cssIAMFilterConfig = AuthorizationContext.loadFilterConfig(this.getClass().getClassLoader());
				bepHostedEnvs = cssIAMFilterConfig.getBepHostedEnvs();
			} catch (JAXBException e) {
				throw new ServletException(
						"Exception loading configuration from the CSS IAM Filter Configuration File: cssiam-filter-config.xml",e);
			}
		    
	}

	/**
	 * Method that maps GET request to the '/logoutCSSAuthorization' URL. Is used to show  a user
	 * that is been logged out of the session.
	 * A property 'vba.apps.homepage.relative.url' is used to load a URL where the user can go after seeing the error.
	 * @param model variable to send info to the jsp page
	 * @param logoutMsg The message to be displayed to the user
	 * @return logoutCSSAuthz
	 */
	@RequestMapping(value = "/logoutCSSAuthorization", params = {"logoutMsg"})
	public String loggoffCSSAuthorization(Model model, 
			@RequestParam(value = "logoutMsg") String logoutMsg, 
			@RequestParam(name = "errorMsg", required = false) String errorMsg, 
			HttpServletRequest request) {
		logger.debug("GET logoutCSSAuthorization");
		
		String isTestEnv;
		
		//load isTestEnv. If unable defaults to false
		try {
			isTestEnv = Boolean.toString((AuthorizationContext.isRequestURLMatchedToTestEnv(request.getRequestURL().toString(), bepHostedEnvs)));
		} catch (UnrecognizedApplicationException e) {
			logger.error("Unable to determine if the environment is for testing/production from the request. Defaulting to Production ",e);
			isTestEnv = Boolean.toString(false);
		}
		
		HttpSession session;
		if ((session = request.getSession(false)) != null) {
			session.invalidate();
		}
		
		String ssoiLogoutURL = SystemUtils.getProperty("ssoi.logout.url");
		
		if (errorMsg != null && !errorMsg.isEmpty()) {
			model.addAttribute("errorMsg", errorMsg);
		}
		model.addAttribute("logoutMessage", logoutMsg);
		model.addAttribute("isTestEnv", isTestEnv);
		model.addAttribute("ssoiLogoutURL", ssoiLogoutURL);
		return "logoutCSSAuthz";
	}
	
	/**
	 * Method that maps GET request to the '/failCSSAuthorization' URL. Is used to show error to the user.
	 * A property 'vba.apps.homepage.relative.url' is used to load a URL where the user can go after seeing the error.
	 * @param model variable to send info to the jsp page
	 * @param errorMsg The error to be displayed to the user
	 * @return failedCSSAuthz
	 */
	@RequestMapping(value = "/failCSSAuthorization", params = {"errorMsg"})
	public String loadCSSAuthoeizationError(Model model, @RequestParam(value = "errorMsg") String errorMsg, HttpServletRequest request) {
		logger.debug("GET failCSSAuthorization");
		
		String isTestEnv;
		
		//load isTestEnv. If unable defaults to false
		try {
			isTestEnv = Boolean.toString((AuthorizationContext.isRequestURLMatchedToTestEnv(request.getRequestURL().toString(), bepHostedEnvs)));
		} catch (UnrecognizedApplicationException e) {
			logger.error("Unable to determine if the environment is for testing/production from the request. Defaulting to Production ",e);
			isTestEnv = Boolean.toString(false);
		}
		
		model.addAttribute("errorMessage",errorMsg);
		model.addAttribute("isTestEnv",isTestEnv);
		model.addAttribute("tryAgainURL", env.getProperty("vba.apps.homepage.relative.url"));
		return "failedCSSAuthz";
	}

	
	
	/**
	 * Method that maps GET request to the '/failCSSAuthorization' URL. Is used to show error to the user.
	 * A property 'vba.apps.homepage.relative.url' is used to load a URL where the user can go after seeing the error.
	 * @param model variable to send info to the jsp page
	 * @param errorMsg The error to be displayed to the user
	 * @param targetUrl The original target of the request
	 * @return failedCSSAuthz
	 */
	@RequestMapping(value = "/failCSSAuthorization", params = {"errorMsg","cssiam_target"})
	public String loadCSSAuthorizationError(Model model, @RequestParam(value = "errorMsg") String errorMsg, @RequestParam(value = "cssiam_target") String targetUrl, 
			HttpServletRequest request) {
		logger.debug("GET failCSSAuthorization with Target");
		
		String isTestEnv;
		
		//load isTestEnv. If unable defaults to false
		try {
			isTestEnv = Boolean.toString((AuthorizationContext.isRequestURLMatchedToTestEnv(request.getRequestURL().toString(), bepHostedEnvs)));
		} catch (UnrecognizedApplicationException e) {
			logger.error("Unable to determine if the environment is for testing/production from the request. Defaulting to Production ",e);
			isTestEnv = Boolean.toString(false);
		}
		
		model.addAttribute("errorMessage",errorMsg);
		model.addAttribute("isTestEnv",isTestEnv);
		model.addAttribute("tryAgainURL", env.getProperty("vba.apps.homepage.relative.url"));
		return "failedCSSAuthz";
	}

	/**
	 * Method that maps GET request to the '/stationSelection/index.html' URL with no parameters. This is considered 
	 * an unauthorized access an the user will be redirected to the error pages
	 * @param model
	 * @return redirect:/failCSSAuthorization
	 */
	@RequestMapping(value = "/stationSelection/index.html")
	public String loadStationsPerCSSUserApplication(Model model, RedirectAttributes redirectAttributes) {
		logger.error("Unauthenticated access attempt to the Station Selection UI");
		redirectAttributes.addAttribute("errorMsg", "Unauthenticated access attempt to a protected resource");
		return "redirect:/failCSSAuthorization";
	}
	

	/**
	 * 
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "/stationSelectionUI", method = RequestMethod.POST)
	//public String stationSelectedPerCSSUserApplication(@ModelAttribute("userForm") CssUser user,
	public String stationSelectedPerCSSUserApplication(@ModelAttribute CssUser user,
			HttpServletRequest request, RedirectAttributes redirectAttributes) {
		
		String action = request.getParameter("action");
		
		String stationId = request.getParameter(FilterBase.SELECTOR_STATION);
		String targetUrl = request.getParameter(FilterBase.SELECTOR_CSSIAM_TARGET);
		
		//Form exit
		if("exit".equals(action)) {
			logger.debug("Action: " + action);
			//Application Logout
			//Redirect to the target with logout for the filter
			redirectAttributes.addAttribute(FilterBase.LOGOFF, "true");
			return "redirect:"+getTargetLogoutURL(targetUrl);
		} 
		
		AuthorizationContext authzCtx = (AuthorizationContext)request.getSession(true).getAttribute(FilterBase.SESSION_AUTHZ_CONTEXT);
		logger.debug("Station selected: " + stationId);
		logger.debug("TargetUrl: " + targetUrl);
		logger.debug("Session authz context: " + authzCtx);
		logger.debug("Station has been selected by the user: " + user.getSelectedStation());
		logger.debug("User: " + user.toString());
		
		targetUrl = removeStationAndTestUserIdFromURL(targetUrl);
		
		redirectAttributes.addAttribute(FilterBase.SELECTOR_STATION, stationId);
		
		logger.debug("Redirect with selected stationId: "+targetUrl);
		return "redirect:"+targetUrl;
		//return "stationSelectionUI";
	}

	/**
	 * 
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "/testStationSelectionUI", method = RequestMethod.POST)
	
	public String testStationSelectedPerCSSUserApplication(@ModelAttribute CssUser user,
			HttpServletRequest request, RedirectAttributes redirectAttributes) {
		
		String username = request.getParameter(FilterBase.SELECTOR_TEST_USER_ID);
		String stationId = request.getParameter(FilterBase.OVERRIDE_SELECTOR_STATION);
		String targetUrl = request.getParameter(FilterBase.OVERRIDE_SELECTOR_CSSIAM_TARGET);
		
		AuthorizationContext authzCtx = (AuthorizationContext)request.getSession(true).getAttribute(FilterBase.SESSION_AUTHZ_CONTEXT);
		
		logger.debug("Test User: " + username);
		logger.debug("Station selected: " + stationId);
		logger.debug("TargetUrl: " + targetUrl);
		logger.debug("Session authz context: " + authzCtx);
		logger.debug("Station has been selected by the user: " + user.getSelectedStation());
		logger.debug("User: " + user.toString());
		
		redirectAttributes.addAttribute(FilterBase.SELECTOR_STATION, stationId);
		redirectAttributes.addAttribute(FilterBase.SELECTOR_TEST_USER_ID, username);
		
		targetUrl = removeStationAndTestUserIdFromURL(targetUrl);
		
		logger.debug("Redirect with selected stationId and testUserId: "+targetUrl);
		return "redirect:"+targetUrl;
		//return "testStationSelectionUI";
		
	}
	
	/**
	 * Method that maps GET request to the '/stationSelection/index.html' URL. Show the selector UI. If an error
	 * is found that prevent the loading of the UI for selection, the user will be redirected to the error pages
	 * indicating the error found.
	 * @param model
	 * @return stationSelectionUI
	 * @return testStationSelectionUI
	 */
	@RequestMapping(value = "/stationSelection/index.html", params = {"profileId","appId","ipAddress","cssiam_target"})
	public String loadStationsPerCSSUserApplication(Model model, @RequestParam(value ="profileId") String username,
			@RequestParam(value ="appId") String application, @RequestParam(value = "ipAddress") String ipAddress, 
			@RequestParam(value ="cssiam_target") String targetUrl, @RequestParam(value ="errorMessage") String errorMessageFromFilter,
			HttpServletRequest request, RedirectAttributes redirectAttributes) {
		logger.debug("GET stationSelectionUI with params.");

		CssUser cssuser = null;
		boolean isTestEnvironment = false;
		String allowedUserOverwrite;
		username = username.toUpperCase();
		
		//load isTestEnv. if unable defaults to false
		try {
			allowedUserOverwrite = Boolean.toString((AuthorizationContext.isRequestURLMatchedToTestEnv(request.getRequestURL().toString(), bepHostedEnvs)));
		} catch (UnrecognizedApplicationException e) {
			logger.error("Unable to determine if the environment is for testing/production from the request. Defaulting to Production ",e);
			allowedUserOverwrite = Boolean.toString(false);
		}
		
		isTestEnvironment = Boolean.valueOf(allowedUserOverwrite);
		AuditContext auditContext= createNewAuditContext(application, username, ipAddress);
		
		try {
			logger.debug("Getting data from database");
			cssuser = _securityService.getCssUserStationsByApplication(username,application, auditContext);
			
			//If this is a production environment and there is only one station and the station is enabled, we should go straight 
			//to the application without passing by the stationSelectionUI
			if (!isTestEnvironment) {
				if(cssuser.getUserStations().size() == 1  
						&& cssuser.getUserStations().get(0).isEnabled()) {
					//If only one station is available and enabled then make it the selected station
					cssuser.setSelectedStation(cssuser.getUserStations().get(0));
					logger.debug("Redirecting to the app directly since only one station was found: "+cssuser.getSelectedStation().getId()+":"+cssuser.getSelectedStation().getName());
					logger.debug("Redirect URL: "+targetUrl);
					redirectAttributes.addAttribute(FilterBase.SELECTOR_STATION,cssuser.getSelectedStation().getId());
					return "redirect:"+targetUrl;
					//redirect back to the filter with the selected station
				} else {
					//Open UI so that the user selects the station
					logger.debug("addAttribute userStations and cssUser");
					List<UserStation> enabledStations = new ArrayList<UserStation>();
					List<UserStation> disabledStations = new ArrayList<UserStation>();
					HashMap<String, String> messageMap = new HashMap<String, String>();
					for(UserStation station: cssuser.getUserStations()) {
						if(station.isEnabled()) {
							enabledStations.add(station);
						} else {
							disabledStations.add(station);
							messageMap.put(station.getReasonCode(), env.getProperty(station.getReasonCode()));
						}
					}
					model.addAttribute("enabledUserStations", enabledStations);
					model.addAttribute("disabledUserStations", disabledStations);
					model.addAttribute("reasonMessages", messageMap);
					model.addAttribute("cssUser", cssuser);
					
					logger.debug("More than 1 stations, get station selection UI");
					
					model.addAttribute("targetUrl", targetUrl);
					logger.debug("Target URL: " + targetUrl);
					
					return "stationSelectionUI";
				}
			}
			// If this is testing environment, then return testStationSelectionUI
			else {
				//Open UI so that the user selects the station
				logger.debug("addAttribute userStations and cssUser");
				List<UserStation> enabledStations = new ArrayList<UserStation>();
				List<UserStation> disabledStations = new ArrayList<UserStation>();
				HashMap<String, String> messageMap = new HashMap<String, String>();
				for(UserStation station: cssuser.getUserStations()) {
					if(station.isEnabled()) {
						enabledStations.add(station);
					} else {
						disabledStations.add(station);
						messageMap.put(station.getReasonCode(), env.getProperty(station.getReasonCode()));
					}
				}
				model.addAttribute("enabledUserStations", enabledStations);
				model.addAttribute("disabledUserStations", disabledStations);
				model.addAttribute("reasonMessages", messageMap);
				model.addAttribute("cssUser", cssuser);
				model.addAttribute("isTestEnv", allowedUserOverwrite);
				
				if ( errorMessageFromFilter != null && !errorMessageFromFilter.isEmpty()) {
					model.addAttribute("errorMsgFromFilter", errorMessageFromFilter);
				}
				
				logger.debug("More than 1 stations, get station selection UI");
				
				model.addAttribute("targetUrl", targetUrl);
				logger.debug("Target URL: " + targetUrl);
				
				return "testStationSelectionUI";
			}
			
		} catch (CssUserRepositoryException cssEx) {
			// Handle the CSS User Repository Exception coming from the Corporate EJB
			// Will either send to error pages or send to the Selector UI with error messages
			return handleCSSUserRepositoryException(model, username,
					allowedUserOverwrite, targetUrl, redirectAttributes,
					cssuser, cssEx, errorMessageFromFilter);
		} catch (Exception cssUnkEx) {
			if (cssUnkEx.getCause() instanceof CssUserRepositoryException){
				// Handle the CSS User Repository Exception coming from the Corporate EJB
				// Will either send to error pages or send to the Selector UI with error messages
				return handleCSSUserRepositoryException(model, username,
						allowedUserOverwrite, targetUrl, redirectAttributes,
						cssuser, (CssUserRepositoryException)cssUnkEx.getCause(), errorMessageFromFilter);
			} else {
				// Pass along unknown error message
				redirectAttributes.addAttribute("errorMsg", cssUnkEx.getMessage());
				redirectAttributes.addAttribute("cssiam_target", targetUrl);
				redirectAttributes.addAttribute(FilterBase.LOGOFF, "true");
				return "redirect:"+getTargetLogoutURL(targetUrl);
			}
		} 
		
		//add env attribute to the model
		/*if ( allowedUserOverwrite != null) {
			model.addAttribute("isTestEnv", allowedUserOverwrite);
		}
		
		return "stationSelectionUI";*/
	}

	/**
	 * 
	 * @param model Model to pass to the selectorUI
	 * @param username User id trying to login
	 * @param allowedUserOverwrite Is this in an test or production environment 
	 * @param targetUrl The URL that the User is trying to get to
	 * @param redirectAttributes Container for the attributes on redirect
	 * @param cssuser User entity
	 * @param cssEx Exception to Handle
	 * @param erroMessageFromFilter String that contains an error meessage passed to the UI from the Filter if there is one
	 * @return
	 */
	private String handleCSSUserRepositoryException(Model model,
			String username, String allowedUserOverwrite, String targetUrl,
			RedirectAttributes redirectAttributes, CssUser cssuser,
			CssUserRepositoryException cssEx, String erroMessageFromFilter) {
		// Try getting the cssuser reasonCode and map a message. If unable,
		// just pass the generic exception message to the VIEW
		String errorMessage = env.getProperty(cssEx.getReasonCode());
		boolean isTestEnvironment = Boolean.valueOf(allowedUserOverwrite);
		
		logger.debug("Getting error from DB: " + cssEx.getReasonCode() + ", " + errorMessage);
		logger.debug("allowedUserOverwite: " + allowedUserOverwrite);
		
		/** check to see if the errors are:
		 * 1. Unknown Application
		 * 2. Application is locked
		 * then redirect to the selectorUI
		 */
		
		if (errorMessage == null) {
			errorMessage = cssEx.getMessage();
		}
		// check for UI errors
		if (isTestEnvironment) {
			String reasonCode = cssEx.getReasonCode();
			if (reasonCode.contains("css.cssprofile.application.unknown")
					|| reasonCode.contains("css.cssprofile.application.globallyLocked")) {

				logger.debug("Error matched: " + reasonCode + ", " + errorMessage);

				// send to error page
				redirectAttributes.addAttribute("errorMsg", errorMessage);
				redirectAttributes.addAttribute("cssiam_target", targetUrl);
				redirectAttributes.addAttribute(FilterBase.LOGOFF, "true");
				return "redirect:" + getTargetLogoutURL(targetUrl);
			} else if (reasonCode.contains("css.cssprofile.user.unknown")
					|| reasonCode.contains("css.cssprofile.user.application.allUserStationsLocked")
					|| reasonCode.contains("css.cssprofile.user.station.allStationsLocked")
					|| reasonCode.contains("css.cssprofile.user.application.noAccess")) {

				logger.debug("Error cssuser: " + reasonCode + "\n cssuser: " + cssuser);

				cssuser = new CssUser();
				cssuser.setNetworkLoginName(username);

				// Merge the error from the Filter with the Exception error message
				if (errorMessage != null && !errorMessage.isEmpty()) {
					model.addAttribute("errorMsg", errorMessage);
				}

				if (erroMessageFromFilter != null && !erroMessageFromFilter.isEmpty()) {
					model.addAttribute("errorMsgFromFilter", erroMessageFromFilter);
				}

				model.addAttribute("isTestEnv", allowedUserOverwrite);

				// make sure view has the data.
				model.addAttribute("userStations", null);
				model.addAttribute("cssUser", cssuser);
				model.addAttribute("targetUrl", targetUrl);

				logger.debug("Get testStationSelectionUI with error.");
				// return "stationSelectionUI";
				return "testStationSelectionUI";
			}
		} else { // production environment
			redirectAttributes.addAttribute("cssiam_target", targetUrl);
			redirectAttributes.addAttribute("errorMsg", errorMessage);
		}
			
		redirectAttributes.addAttribute(FilterBase.LOGOFF, "true");
		return "redirect:"+getTargetLogoutURL(targetUrl);
	}
	
	
	/**
	 * Create an auditContext to pass it to the EJB. The EJB have an intercepter that will execute an audit to this EJB call.
	 * @param application Application that a user is intending to login
	 * @param username The user identifier
	 * @param ipAddress IP Address that the user is trying to access the system from
	 * @return a newly create auditContext with the input parameters
	 */
	private AuditContext createNewAuditContext(String application,
			String username, String ipAddress) {
		AuditContext auditContext = new AuditContext();
		auditContext.setAuditID(AuditIDGenerator.generateAuditID());
		auditContext.setApplicationName(application);
		auditContext.setClientIPAddress(ipAddress);
		auditContext.setUserId(username);
		auditContext.setStationID("-1");
		return auditContext;
	}

	/**
	 * Is the Session or authzContext attribute null
	 * @param req The HttpServletRequest incoming after the CSSIAMAuthorizationFilter
	 * @return 	'true' if the session is null or if the attribute authzContext is not in the session, or
	 * 			'false' if the session exist and contains not null authzContext attribute
	 */
	protected boolean isSessionOrAuhtzContextNull(HttpServletRequest req) {
		HttpSession session = req.getSession(false); 
		return (session == null ||(session != null && session.getAttribute(FilterBase.SESSION_AUTHZ_CONTEXT)== null));
	}


	/**
	 * Add logout to the TARGET URL so the filter log this session out
	 * @param targetUrl
	 * @return the target logout URL
	 */
	private String getTargetLogoutURL(String targetUrl) {
		
		// Reconstruct the original request
		if (targetUrl != null && !targetUrl.isEmpty()) {
			String targetArray[] = targetUrl.split("\\?");
			StringBuffer targetUrlLogout = new StringBuffer();
			targetUrlLogout = targetUrlLogout.append("/logoutCSSAuthorization");
			targetUrlLogout.append("?logoutMsg=");
			targetUrlLogout.append(LOGOUT_MSG);
			if (targetArray.length>1) {
				targetUrlLogout.append("&");
				targetUrlLogout.append(targetArray[1]);
				logger.debug("Target Logout URL:" + targetUrlLogout);
			}
			return targetUrlLogout.toString();
		}
		logger.error("No Target URL in the request. Sending to /logout");
		return "/logout";
	}
	
	/**
	 * Remove stationId and testUserId from the targetURL so it can be added again on redirect.
	 * @param targetUrl
	 * @return
	 */
	private String removeStationAndTestUserIdFromURL(String targetUrl) {
		String[] urlparts = targetUrl.split("\\?");
		StringBuffer queryResult = new StringBuffer();
		
		
		if (urlparts.length >= 2) {
			String urlBase = urlparts[0]; // get first part, and remove from array
			String queryString = urlparts[1]; // join it back up

			String stationPrefix = FilterBase.SELECTOR_STATION + "=";
			String testUserIdPrefix = FilterBase.SELECTOR_TEST_USER_ID + "=";
			
			String[] pairsArray = queryString.split("&");
			
			for (String pair : pairsArray) { 
				if (!pair.startsWith(stationPrefix) && !pair.startsWith(testUserIdPrefix)) {
					if (queryResult.toString().isEmpty()) {
						queryResult.append(pair);
					} else {
						queryResult.append("&").append(pair);
					}
				}
			}

			targetUrl = urlBase + (queryResult.toString().isEmpty() ? "" : "?" + queryResult);
		}
		return targetUrl;
	}
	
	public CommonSecurityServiceV2 get_securityService() {
		return _securityService;
	}

	public void set_securityService(CommonSecurityServiceV2 _securityService) {
		this._securityService = _securityService;
	}
	
	public BepHostedEnvs getBepHostedEnvs() {
		return bepHostedEnvs;
	}

	public void setBepHostedEnvs(BepHostedEnvs bepHostedEnvs) {
		this.bepHostedEnvs = bepHostedEnvs;
	}
}
