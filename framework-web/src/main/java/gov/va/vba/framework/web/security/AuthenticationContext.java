package gov.va.vba.framework.web.security;

import gov.va.vba.framework.common.AuditContext;

import java.io.Serializable;


public class AuthenticationContext implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	//User authenticated with
	private String authNUserName = "";
	//test account specified
	private String authZProfileId = "";
	private String authZApplicationName = "";
	private String authZStationId = "";
	private AuditContext auditContext = null;
	private String allowOverwrite = null;
	private String clientIPAddress = null;
	private String cssApplicationName = null;

	
	public String getCssApplicationName() {
		return cssApplicationName;
	}
	public void setCssApplicationName(String cssApplicationName) {
		this.cssApplicationName = cssApplicationName;
	}
	public String getAllowOverwrite() {
		return allowOverwrite;
	}
	public void setAllowOverwrite(String allowOverwrite) {
		this.allowOverwrite = allowOverwrite;
	}
	public String getClientIPAddress() {
		return clientIPAddress;
	}
	public void setClientIPAddress(String clientIPAddress) {
		this.clientIPAddress = clientIPAddress;
	}
	public String getAuthNUserName() {
		return authNUserName;
	}
	public void setAuthNUserName(String authNUserName) {
		if (authNUserName != null) this.authNUserName = authNUserName.toUpperCase();
	}
	public String getAuthZProfileId() {
		return authZProfileId;
	}
	public void setAuthZProfileId(String authZProfileId) {
		this.authZProfileId = authZProfileId;
	}
	public String getAuthZApplicationName() {
		return authZApplicationName;
	}
	public void setAuthZApplicationName(String authzApplicationName) {
		this.authZApplicationName = authzApplicationName;
	}
	public String getAuthZStationId() {
		return authZStationId;
	}
	public void setAuthZStationId(String authzStationId) {
		this.authZStationId = authzStationId;
	}
	public AuditContext getAuditContext() {
		return auditContext;
	}
	public void setAuditContext(AuditContext auditContext) {
		this.auditContext = auditContext;
	}
	
	public String getUserNameForProfileRetrieval(){
		if ("yes".equalsIgnoreCase(allowOverwrite) &&
				authZProfileId!=null && !"".equals(authZProfileId.trim()) ){
			return authZProfileId;
			
		}else{
			return authNUserName;
		}
	}
	
	
	
}
