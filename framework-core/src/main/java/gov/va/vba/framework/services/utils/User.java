package gov.va.vba.framework.services.utils;

import java.util.ArrayList;

public class User {

	private String userId;
	private String stationId;
	private String appName;
	private ArrayList<String> allowedFunctions;
	
	public User(){
		allowedFunctions = new ArrayList<String>();
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getStationId() {
		return stationId;
	}
	public void setStationId(String stationId) {
		this.stationId = stationId;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public ArrayList<String> getAllowedFunctions() {
		return allowedFunctions;
	}
	public void setAllowedFunctions(ArrayList<String> allowedFunctions) {
		this.allowedFunctions = allowedFunctions;
	}

}
