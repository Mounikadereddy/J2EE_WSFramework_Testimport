package gov.va.vba.framework.css.cssiam.domain.entities;

import java.io.Serializable;

/**
 * Class that represent a userStation in the Common Security System for authorization purposes. The Station 
 * is identified by its id and stationName. If the station is no enable (isEnable == false), the reasonCode
 * will be populated with the reason that explain why the station is Locked: <BR>
 *  -css.cssprofile.user.application.notApproved<BR>
 *  -css.cssprofile.user.station.disable<BR>
 *  -css.cssprofile.user.application.locked <BR> 
 * @author VHAISPVANEGI <Ivan Vanegas>
 *
 */
public class UserStation implements Serializable {
	
	private static final long serialVersionUID = 1L;


	/**
	 * Station Id
	 */
	private String id;
	
	/**
	 * Station Name
	 */
	private String name;
	
	/**
	 * Role of the user in this station
	 */
	private String role;

	/**
	 * Is this station available to be picked to login
	 */
	private boolean isEnabled;
	
	/**
	 * If the isEnabled=false the reasonCode will show the reason for disabled:
	 * css.cssprofile.user.application.notApproved
     * css.cssprofile.user.station.disable
     * css.cssprofile.user.application.locked  
	 */
	private String reasonCode;

	/**
	 * Get the Station Id related to the STN -> FCLTY_TYPE_CD | CD
	 * @return
	 */
	public String getId() {
		return id;
	}

	/**
	 * Set the Station Id related to the STN -> FCLTY_TYPE_CD | CD
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Get Station Name related to the STN -> NM
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set Station Name related to the STN -> NM
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Get the reasonCode associted to the station in the isEnable flag is false:<BR>
     *  -css.cssprofile.user.application.notApproved<BR>
     *  -css.cssprofile.user.station.disable<BR>
     *  -css.cssprofile.user.application.locked <BR> 
	 * 
	 * @return
	 */
	public String getReasonCode() {
		return reasonCode;
	}

	/**
	 * Set the reasonCode associted to the station in the isEnable flag is false:<BR>
     *  -css.cssprofile.user.application.notApproved<BR>
     *  -css.cssprofile.user.station.disable<BR>
     *  -css.cssprofile.user.application.locked <BR> 
	 * @param reasonCode
	 */
	public void setReasonCode(String reasonCode) {
		this.reasonCode = reasonCode;
	}

	/**
	 * Get Role Name associted with this station for login purposes related to APPLCN_PERSON_ROLE -> ROLE_TYPE_NM
	 * @return
	 */
	public String getRole() {
		return role;
	}

	/**
	 * Set Role Name associted with this station for login purposes related to APPLCN_PERSON_ROLE -> ROLE_TYPE_NM
	 * @param role
	 */
	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * Get the flag that shows if this station is enable for Login
	 * @return
	 */
	public boolean isEnabled() {
		return isEnabled;
	}

	/**
	 * Set the flag that shows if this station is enable for Login
	 * @param isEnabled
	 */
	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}
}
