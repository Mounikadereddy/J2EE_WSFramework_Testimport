package gov.va.vba.framework.css.cssiam.security;

import java.io.InputStream;
import java.io.Serializable;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import gov.va.vba.framework.auditing.AuditIDGenerator;
import gov.va.vba.framework.common.AuditContext;
import gov.va.vba.framework.common.StringUtils;
import gov.va.vba.framework.common.SystemUtils;
import gov.va.vba.framework.css.cssiam.security.FilterBase.IAMSiteMinderHttpHeaders;
import gov.va.vba.framework.logging.Logger;

/**
 * This class contains the values mapped from the HttpRequest from the SiteMinder Headers and the Requested URL. Contains as well 
 * a reference to the AuditContext that is used by the BEP Framework for Auditing Purposes.
 * @author VHAISPVANEGI
 *
 */
public class AuthorizationContext implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private final static Logger logger=Logger.getLogger(AuthorizationContext.class.getName());
	
	/**
	 * Username of the authenticated user
	 */
	public String username;
	
	/**
	 * Application that the user is intending to reach. Mapped from BEPHostedApp.CSSId
	 */
	public String application;
	
	/**
	 * Station Id of that the user selected to Login to.
	 */
	public String station;
	
	/**
	 * IP Address of the Client connecting to the application
	 */
	public String ipAddress;
	
	/**
	 * In case of overwriting, this contains the value to use instead of the username for authorization
	 */
	public String testUserId;
	
	/**
	 * Indicates if the environment in which the application is deployed allows for username overwriting
	 */
	public boolean allowedUserOverwrite = false;
	
	/**
	 * Reference to the Auditing Context
	 */
	public AuditContext auditContext;
	
	
	/**
	 * Traits from IAM after authentication mapped from the request headers
	 */
	public HashMap<IAMSiteMinderHttpHeaders, String> iamSiteMinderTraits;

	/**
	 * 
	 * @return Mapped username from SiteMinder
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Mapped user from SiteMinder
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * 
	 * @return CSS Application Name that user is intending to reach
	 */
	public String getApplication() {
		return application;
	}

	/**
	 * 
	 * @param application CSS Application Name that user is intending to reach
	 */
	public void setApplication(String application) {
		this.application = application;
	}

	/**
	 * 
	 * @return Station Id of that the user selected to Login to.
	 */
	public String getStation() {
		return station;
	}

	/**
	 * 
	 * @param station Station Id of that the user selected to Login to.
	 */
	public void setStation(String station) {
		this.station = station;
		//Update the station in the associated audit context if present
		if (getAuditContext() != null) {
			getAuditContext().setStationID(station);
		}
	}

	/**
	 * 
	 * @return IP Address of the Client connecting to the application
	 */
	public String getIpAddress() {
		return ipAddress;
	}

	/**
	 * 
	 * @param ipAdrdress IP Address of the Client connecting to the application
	 */
	public void setIpAddress(String ipAdrdress) {
		this.ipAddress = ipAdrdress;
	}

	/**
	 * 
	 * @return In case of overwriting, this contains the value to use instead of the username for authorization
	 */
	public String getTestUserId() {
		return testUserId;
	}

	/**
	 * 
	 * @param testUserId In case of overwriting, this sets the value to use instead of the username for authorization
	 */
	public void setTestUserId(String testUserId) {
		this.testUserId = testUserId;
	}

	/**
	 * 
	 * @return Indicates if the environment in which the application is deployed allows for username overwriting
	 */
	public boolean isAllowedUserOverwrite() {
		return allowedUserOverwrite;
	}

	/**
	 * 
	 * @param allowedUserOverwrite Indicates if the environment in which the application is deployed allows for username overwriting
	 */
	public void setAllowedUserOverwrite(boolean allowedUserOverwrite) {
		this.allowedUserOverwrite = allowedUserOverwrite;
	}

	/**
	 * 
	 * @return Reference to the Auditing Context for BEP Framework auditing purposes
	 */
	public AuditContext getAuditContext() {
		return auditContext;
	}

	/**
	 * 
	 * @param auditContext Reference to the Auditing Context for BEP Framework auditing purposes
	 */
	public void setAuditContext(AuditContext auditContext) {
		this.auditContext = auditContext;
	}

	/**
	 * 
	 * @return the mapped IAM SiteMinder Traits from the Headers on the Request
	 */
	public HashMap<IAMSiteMinderHttpHeaders, String> getIamSiteMinderTraits() {
		return iamSiteMinderTraits;
	}

	/**
	 * Maps values from the HttpServletRequest to members of this class. 
	 * @param request The HttpServlet Request to be used in the mapping. 
	 * @param mappedApps List of the supported apps for mapping. This list is compared against the Request URL in the HttpRequest
	 * @param bepHostedEnvs List of support environments. This list is compared against the request URL in the HttpRequest
	 * @param iamMandatoryTraits List of IAM mandatory Traits. This list is compared against the Headers in the HttpRequest 
	 * @throws MissingHeaderException SiteMinder mandatory headers are absent
	 * @throws UnrecognizedApplicationException Requested URL doesn't match any of the supported apps
	 */
	public void mapRequestToAuthorizationCtx(HttpServletRequest request, BepHostedMappedApps mappedApps, BepHostedEnvs bepHostedEnvs, IamMandatoryTraits iamMandatoryTraits) throws MissingHeaderException, UnrecognizedApplicationException  {
		//check mandatory headers are part of the request
		if(request.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_USERNAME.toString()) != null 
				&& !request.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_USERNAME.toString()).isEmpty()) {
			
			String clientIp = getClientIPAddress(request);
			if (clientIp != null && !clientIp.isEmpty()) {
				//map username
				this.username = request.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_USERNAME.toString()).toUpperCase();
				//map ip address
				this.ipAddress = clientIp;
				
			} else {
				logger.error("Missing mandatory header "+FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_CLIENT_IP.toString()+
						" expected as IAM Authentication Traits");
				throw new MissingHeaderException ("Missing mandatory header "+FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_CLIENT_IP.toString()+
						" expected as IAM Authentication Traits");
			}
		} else  {
			logger.error("Missing mandatory header "+FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_USERNAME.toString()+
					" expected as IAM Authentication Traits");
			throw new MissingHeaderException ("Missing mandatory header "+FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_USERNAME.toString()+
					" expected as IAM Authentication Traits");
		}
		
		//map application
		this.application = mapRequestURLToSupportedApps(request.getRequestURL(),mappedApps);
		//turn on the overwrite permission
		this.allowedUserOverwrite = isRequestURLMatchedToTestEnv(request.getRequestURL().toString(), bepHostedEnvs);
		setAuditContext(createAuditContext(application, ipAddress, username));
		
		//map additional headers to expected traits and add them to the iamSiteMinderTraits map
		mapRequestHeadersToSiteMinderTraits(request, iamMandatoryTraits);
	}

	/**
	 * Best effort to map additional headers to expected traits and add them to the iamSiteMinderTraits map.
	 * Also check that all the mandatory headers listed in the configuration are present in the request and different that empty
	 * @param request HttpRequest containing the IAM SiteMinder Trait headers
	 * @param iamMandatoryTraits List of IAM SiteMinder traits that are configured as mandatory 
	 * @throws MissingHeaderException a expected mandatory header was either not present or its value was empty
	 */
	private void mapRequestHeadersToSiteMinderTraits(HttpServletRequest request, IamMandatoryTraits iamMandatoryTraits) throws MissingHeaderException {

		//Is there a definition of mandatory IAM traits in the configuration file
		if(iamMandatoryTraits != null) {
			for (IamTrait trait: iamMandatoryTraits.getIamMandatoryTraits()) {
				//If the header is not on the request or its value is null or empty throw an exception
				if (request.getHeader(trait.getTraitId()) == null || request.getHeader(trait.getTraitId()).isEmpty() ) {
					throw new MissingHeaderException ("Missing mandatory header "+trait.getTraitId()+
							" expected as IAM Authentication Traits");
				}
			}
		}

		//Initialize the map if hasn't been done yet
		if (this.iamSiteMinderTraits == null) {
			this.iamSiteMinderTraits = new HashMap<FilterBase.IAMSiteMinderHttpHeaders, String>();
		}
		
		/**
		 * Traverse the list of expected IAM Traits and try to get them from the request. If expected trait is not in the request
		 * then a place in the map will be added with a null value.
		 */
		
		for (IAMSiteMinderHttpHeaders header :IAMSiteMinderHttpHeaders.values()) {
			this.iamSiteMinderTraits.put(header, request.getHeader(header.toString()));
		}
	}

	/**
	 * Create an AuditContext based on the mapped values in the AuthorizationContext
	 * @param applicationName Application that the user is intending to reach. Mapped from BEPHostedApp.CSSId
	 * @param clientIPAddress IP Address of the Client connecting to the application
	 * @param userId Username of the authenticated user
	 * @return AuditContext based on the mapped values in the AuthorizationContext
	 */
	private AuditContext createAuditContext(String applicationName,
			String clientIPAddress, String userId) {
		AuditContext auditContext = new AuditContext();
		auditContext.setUserId(userId);
		auditContext.setApplicationName(applicationName);
		auditContext.setClientIPAddress(clientIPAddress);
		auditContext.setAuditID(AuditIDGenerator.generateAuditID());
		
		//Station no yet available. 
		//TODO: Update the AuditContext once a Station Selection has been made
		//TODO: In case of overwrite the AuditContext.userID must ver replaced by the Test User Id
		
		return auditContext;
	}
	
	/**
	 * Maps the Requested URL to the Supported Apps
	 * @param requestURL Application URL from the HttpServletRequest
	 * @param mappedApps List of suported apps
	 * @return The CSS Name of the mapped app
	 * @throws UnrecognizedApplicationException The requested application is not part of the supported apps
	 */
	private String mapRequestURLToSupportedApps(StringBuffer requestURL,
			BepHostedMappedApps mappedApps) throws UnrecognizedApplicationException {
		String mappedApp = null;
		for (BepHostedApp bepHostedApp : mappedApps.getBepHostedMappedApps()) {
			if(requestURL.toString().contains(bepHostedApp.getUrl())) {
				mappedApp = bepHostedApp.getCSSId();
			}
		}
		
		if(mappedApp == null) {
			logger.error("Unable to map requested application '"+requestURL+
					"' to a known BEP VBA Application");
			throw new UnrecognizedApplicationException ("Unable to map requested application '"+requestURL+
					"' to a known BEP VBA Application");
		}
		return mappedApp;
	}
	
	/**
	 * Gets the username that should be used to load the CSS Profile for authorization depending 
	 * on the environment where the application is deployed
	 * @return username if production environment; testUserId if a testing environment
	 * 
	 */
	public String getUsernameForCSSProfile() {
		//If testing environment and a testUserId is present
		if(getTestUserId() != null && !getTestUserId().isEmpty()) {
			if (isAllowedUserOverwrite()) {
				return getTestUserId();
			} else {
				logger.error("A Test User Id was passed to the context but the environment doesn't allow for User Overwrite. Using the AD Username for Login");
				return getUsername();
			}
		} else {
		//If production environment or a testUserId is not available
			return getUsername();
		}
	}
	
	/**
	 * Load the configuration information from the configuration file using
	 * JAXB. The file 'cssiam-filter-config.xml' should be in the classpath. The
	 * information loaded is:<BR>
	 * - The list of supported applications: mappedApps<BR>
	 * - The list of configured bep environments: bepHostedEnvs<BR>
	 * 
	 * @throws JAXBException
	 */
	public static CSSIAMFilterConfig loadFilterConfig(ClassLoader classLoader) throws JAXBException {
		CSSIAMFilterConfig cssiamFilterConfig = null;
		try {
			JAXBContext jaxbContext = JAXBContext
					.newInstance(CSSIAMFilterConfig.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			InputStream is = classLoader.getResourceAsStream(FilterBase.CSSIAM_FILTER_CONFIG_FILE_PATH);
			cssiamFilterConfig = (CSSIAMFilterConfig) unmarshaller
					.unmarshal(is);
			return cssiamFilterConfig;
		} catch (JAXBException e) {
			logger.error("Exception loading BEP Supported Apps configuration",
					e);
			e.printStackTrace();
			throw e;
		} catch (IllegalArgumentException e) {
			logger.error("Exception loading BEP Supported Apps configuration",
					e);
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * Check the Requested URL to see if it's match the Supported Environments
	 * @param targetUrl Application URL from the HttpServletRequest
	 * @param bepHostedEnvs List of suported envs
	 * @return The boolean that indicates mapped environment
	 * @throws UnrecognizedApplicationException The requested application is not part of the supported env
	 */
	public static boolean isRequestURLMatchedToTestEnv(String targetUrl, BepHostedEnvs bepHostedEnvs) throws UnrecognizedApplicationException {
		boolean isMatchedToTest = false, found = false;
		
		String env = SystemUtils.getProperty(SystemUtils.Key.ENVIRONMENT);
		
		if (targetUrl != null && bepHostedEnvs != null) {
			for (BepHostedEnv bepHostedEnv : bepHostedEnvs.getBepHostedEnvs()) {
				if (bepHostedEnv != null && targetUrl.toString().contains(bepHostedEnv.getUrl())) {
					isMatchedToTest = bepHostedEnv.isTestEnv();
					found = true;
					break;
				}
			}
		}
		
		if(! found) {
			logger.error("Unable to match requested application '"+targetUrl+
					"' to a known BEP VBA Environment");
			throw new UnrecognizedApplicationException ("Unable to match requested application '"+targetUrl+
					"' to a known BEP VBA Environment");
		}
		
		return isMatchedToTest;
	}
	
	protected String getClientIPAddress(HttpServletRequest req) {
		try {
			String clientIPAddress = (req.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_CLIENT_IP.toString()) == null
					|| (req.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_CLIENT_IP.toString())).length() == 0
						? req.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_PROXY_CLIENT_IP.toString())
						: req.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_CLIENT_IP.toString()));

			int i = clientIPAddress.indexOf(",");
			if (i == -1)
				i = clientIPAddress.length();
			clientIPAddress = StringUtils.trimToEmpty(clientIPAddress).substring(0, i);

			return clientIPAddress;
		} catch (NullPointerException e) {
			return "";
		}
	}

	public boolean hasRequestInSessionChanged(HttpServletRequest request) {
		if(request.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_USERNAME.toString()) != null 
				&& !request.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_USERNAME.toString()).isEmpty()) {
			return !this.username.equalsIgnoreCase(request.getHeader(FilterBase.IAMSiteMinderHttpHeaders.IAM_SM_USERNAME.toString()));
		}
		return false;
	}
}
