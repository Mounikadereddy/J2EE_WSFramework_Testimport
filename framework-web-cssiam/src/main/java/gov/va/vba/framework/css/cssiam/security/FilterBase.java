package gov.va.vba.framework.css.cssiam.security;

/**
 * Class with definitions and common methods to make the CSSIAMAuthorizationFilter more readable
 * @author VHAISPVANEGI
 *
 */
public class FilterBase {

	/**
	 * Name of the Filter Configuration file
	 */
	public static String CSSIAM_FILTER_CONFIG_FILE_PATH ="cssiam-filter-config.xml";
	
	/**
	 * Name of the attribute to be set in the HttpSession as UserContext
	 */
	public static String SESSION_USER_CONTEXT = "userContext";
	
	/**
	 * Name of the attribute to be set in the HttpSession as AuthorizationContext
	 */
	public static String SESSION_AUTHZ_CONTEXT = "authzContext";
	
	/**
	 * Parameter name for the username passed to the Selector UI. The name 'profileId' is used for consistency with 
	 * old filters.
	 */
	public static String SELECTOR_USERNAME = "profileId";
	
	/**
	 * Parameter name for the application passed to the SelectorUI. The name 'appId' is used for consistency with
	 * old filters.
	 */
	public static String SELECTOR_APPLICATION = "appId";
	
	/**
	 * Parameter name for the station received from the SelectorUI. The name 'stationId' is used for consistency with
	 * old filters.
	 */
	public static String SELECTOR_STATION = "stationId";
	
	/**
	 * Parameter name for the station received from the SelectorUI user override modal. 
	 */
	public static String OVERRIDE_SELECTOR_STATION = "overrideStationId";
	
	/**
	 * Parameter name for the station received from the SelectorUI. The name 'stationId' is used for consistency with
	 * old filters.
	 */
	public static String SELECTOR_CLIENT_ADDRESS = "ipAddress";
	
	/**
	 * Parameter name for the boolean that indicates if the current environment allow for Test User Ids to be used instead
	 * of the profileId/ADUsername.
	 */
	public static String SELECTOR_ALLOW_USER_OVERWRITE = "tesUserOverwrite";
	
	/**
	 * Parameter name for the username received from the TestSelectorUI.
	 */
	public static String SELECTOR_TEST_USER_ID = "testUserId";
	
	/**
	 * Parameter name that contains the BEP Hosted Application original TARGET URL
	 */
	public static String SELECTOR_CSSIAM_TARGET = "cssiam_target";
	
	/**
	 * Parameter name that contains the BEP Hosted Application original TARGET URL in the user override modal
	 */
	public static String OVERRIDE_SELECTOR_CSSIAM_TARGET = "override_cssiam_target";
	
	/**
	 * Parameter name that contains an erorr message to display in the Selector UI upon loading
	 */
	public static String SELECTOR_ERROR_MESSAGE = "errorMessage";
	
	/**
	 * URL For Error Pages for redirections
	 */
	public static String ERROR_PAGES_URL = "/cssiam/failCSSAuthorization?errorMsg=";
	
	/**
	 * URL For Logout Page for redirections
	 */
	public static String LOGOUT_PAGE_URL = "/cssiam/logoutCSSAuthorization?logoutMsg=";
	
	/**
	 * URL of the Station Selector UI for use in redirection
	 */
	public static String STATION_SELECTOR_UI_URL = "/cssiam/stationSelection/index.html";
	
	/**
	 * Matching keyword to identify a logout request
	 */
	public static String LOGOUT_URL = "logout";

	/**
	 * Parameter name that indicates that a session needs to be Logged Out
	 */
	public static final String LOGOFF = "logoff";
	
	/**
	 * WebLogic Context Factory
	 */
	public static final String WLS_CONTEXT_FACTORY = "weblogic.jndi.WLInitialContextFactory";

	
	/**
	 * Enumeration of possible IAM SiteMinder Traits
	 * @author VHAISPVANEGI
	 *
	 */
	public enum IAMSiteMinderHttpHeaders {
		//Mandatory Headers for the HttpFilter
		IAM_SM_USERNAME("adSamAccountName"), 
		IAM_SM_CLIENT_IP("WL-Proxy-Client-IP"),
		IAM_SM_PROXY_CLIENT_IP("Proxy-Client-IP"),
		IAM_SM_AD_DOMAIN("adDomain"),
		IAM_SM_SESSIONSCOPE("SessionScope"),
		IAM_SM_TRANSACTIONID("SM_TRANSACTIONID"),
		IAM_SM_ISSUEINSTANT("IssueInstant"),
		IAM_SM_AUTHNTYPE("authntype"),
		IAM_SM_PROOFINGAUTH("proofingAuth"),
		IAM_SM_ASSURLEVEL("assurLevel"),
		IAM_SM_ADUPN("adUpn"),
		IAM_SM_ADEMAIL("adEmail"),
		IAM_SM_VAUID("VAUID"),
		IAM_SM_FIRSTNAME("firstName"),
		IAM_SM_LASTNAME("lastName"),
		IAM_SM_SECID("secid"),
		IAM_SM_MVIICN("HTTP_MVIICN"),
		IAM_SM_VISTAID("HTTP_VISTAID"),
		IAM_SM_CORPID("corpid"),
		IAM_SM_DODEDIPNID("DODEDIPNID"),
		IAM_SM_ROLE("role"),
		IAM_SM_ORGANIZATION("Organization"),
		IAM_SM_ORGANIZATIONID("OrganizationId"),
		IAM_SM_SSOI_LOGGEDOUT_URL("SSOI_LOGGEDOUT_URL"),
		IAM_SM_SSOI_LANDING_URL("SSOI_LANDING_URL"),
		IAM_SM_ACCESSROLES("ACCESSROLES");
		
		/**
		 * Name of the header
		 */
		private String headerName;

		/**
		 * Constructs a member of the header enumeration base of the name of the header
		 * @param headerName Name of the  header
		 */
		private IAMSiteMinderHttpHeaders(String headerName) {
			this.headerName = headerName;
		}
		
		/**
		 * Compare a member of the enumeration based on the name of the header
		 * @param otherHeaderName
		 * @return true if the name of the header of a enumeration value is the same that the parameter, false otherwise.
		 */
	    public boolean equalsName(String otherHeaderName) {
	        return headerName.equals(otherHeaderName);
	    }
		
	    /**
	     * @return Name of the header 
	     */
	    @Override
	    public String toString() {
	        return headerName;
	    }
	    
	    /**
	     * Get an enumeration IAMSiteMinderHttpHeader from the String name of the header in the request
	     * @param header String name of the header in the request
	     * @return Enumeration element
	     */
	    public static IAMSiteMinderHttpHeaders getEnumFromValue(String header) throws IllegalArgumentException {
	    	for (IAMSiteMinderHttpHeaders iamSiteMinderHttpHeader: IAMSiteMinderHttpHeaders.values()) {
	    		if(iamSiteMinderHttpHeader.equalsName(header)) {
	    			return iamSiteMinderHttpHeader;
	    		}
	    	}
			throw new IllegalArgumentException("Unrecognized SiteMinder IAM Trait");
	    }
	}
}
