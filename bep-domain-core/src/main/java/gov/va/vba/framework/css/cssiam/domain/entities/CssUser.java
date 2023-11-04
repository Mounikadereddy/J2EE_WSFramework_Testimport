package gov.va.vba.framework.css.cssiam.domain.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that represent a user in the Common Security System for authorization purposes. The CSS User has
 * associated mainly the application that the user is trying to reach, and the list of stations that the 
 * CSS User can choose to access that application.
 * 
 * @author VHAISPVANEGI <Ivan Vanegas>
 *
 */
public class CssUser implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Application that the CSS User is trying to reach for the session
	 */
	private String userApplication;
	
	/**
	 * List of station availables for the CSS User
	 */
	private List<UserStation> userStations;
	
	/**
	 * Holds the Station that the CSS User selects as the one to use for Login
	 */
	private UserStation selectedStation;
	
	/**
	 * Username in CSS
	 */
	private String networkLoginName;
	
	/**
	 * Username in the VA Active Directory
	 */
	private String vaAdUsername;
	
	
	/**
	 * Get the CSS Application name related to the APPLCN -> APPLCN_NM
	 * @return
	 */
	public String getUserApplication() {
		return userApplication;
	}

	/**
	 * Set the CSS Application name related to the APPLCN -> APPLCN_NM
	 * @param userApplication
	 */
	public void setUserApplication(String userApplication) {
		this.userApplication = userApplication;
	}

	/**
	 * Get list of stations
	 * @return if there is not list a new list is created and th reference returned
	 */
	public List<UserStation> getUserStations() {
		return userStations == null ? userStations = new ArrayList<UserStation>() : userStations;
	}

	/**
	 * Set a new userStations list
	 * @param userStations
	 */
	public void setUserStations(List<UserStation> userStations) {
		this.userStations = userStations;
	}

	/**
	 * Get the station selected for the CSS User for Login
	 * @return
	 */
	public UserStation getSelectedStation() {
		return selectedStation;
	}

	/**
	 * Set the station selected for the CSS User for Login
	 * @param selectedStation
	 */
	public void setSelectedStation(UserStation selectedStation) {
		this.selectedStation = selectedStation;
	}

	/**
	 * Get the CSS Network Login Name associated with the user related to PERSON_SCRTY_LOG -> NETWRK_LOGON_NM
	 * @return
	 */
	public String getNetworkLoginName() {
		return networkLoginName;
	}

	/**
	 * Get the CSS Network Login Name associated with the user related to PERSON_SCRTY_LOG -> NETWRK_LOGON_NM
	 * @param networkLoginName
	 */
	public void setNetworkLoginName(String networkLoginName) {
		this.networkLoginName = networkLoginName;
	}

	/**
	 * Get the User Id in the VA Active Directory
	 * @return
	 */
	public String getVaAdUsername() {
		return vaAdUsername;
	}

	/**
	 * Set the User Id in the VA Active Directory
	 * @param vaAdUsername
	 */
	public void setVaAdUsername(String vaAdUsername) {
		this.vaAdUsername = vaAdUsername;
	}
	
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append("CSS Network Login Name: "+this.getNetworkLoginName()+"\n");
		result.append("Application: "+this.getUserApplication()+"\n");
		result.append("Stations: \n");
		for (UserStation st : this.getUserStations()) {
			result.append("Station Id: ["+st.getId()+"], Station Name: ["+st.getName()+"], Station Role: ["+st.getRole()+"]\n");
			result.append("-->isEnable: [");
			result.append(st.isEnabled());
			result.append("] ");
			if (!st.isEnabled()) {
				result.append(", reasonCode: [");
				result.append(st.getReasonCode());
				result.append("]");
			}
			result.append("\n");
		}
		return result.toString();
	}
}
