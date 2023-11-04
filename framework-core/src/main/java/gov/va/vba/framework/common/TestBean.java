package gov.va.vba.framework.common;


import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class TestBean implements Serializable {

    private String station;
    private String userid;
    private String applData;
    private String reply;
    //LGY specific fields
    private String appName;
    private String serviceName;
    private String portalId;
    private String key;
	
	public TestBean() {

	}




	/**
	 * @return Returns the station.
	 */
	public String getStation() {
		return station;
	}


	/**
	 * @return Returns the userid.
	 */
	public String getUserid() {
		return userid;
	}

	/**
	 * @param station The station to set.
	 */
	public void setStation(String station) {
		this.station = station;
	}


	/**
	 * @param userid The userid to set.
	 */
	public void setUserid(String userid) {
		this.userid = userid;
	}


	/**
	 * @return Returns the applData.
	 */
	public String getApplData() {
		return applData;
	}


	/**
	 * @param applData The applData to set.
	 */
	public void setApplData(String applData) {
		this.applData = applData;
	}
	
	/**
    private String ;
    private String ;
    private String ;    
    private String ;
    private String ;
    private String ;
    private String ;	    
    private String ;
	 */
	public String toString() {
		return(	"station: '"+this.station+"'. Length: "+(this.station != null? this.station.length():0)+"\n"+
			   "userid: '"+this.userid+"'. Length: "+(this.userid != null? this.userid.length():0)+"\n"+
			   "appName: '"+this.appName+"'. Length: "+(this.appName != null? this.appName.length():0)+"\n"+
			   "serviceName: '"+this.serviceName+"'. Length: "+(this.serviceName != null? this.serviceName.length():0)+"\n"+
			   "portalId: '"+this.portalId+"'. Length: "+(this.portalId != null? this.portalId.length():0)+"\n"+
			   "key: '"+this.key+"'. Length: "+(this.key != null? this.key.length():0)+"\n"+		   
			   "applData: '"+this.applData+"'. Length: "+(this.applData != null? this.applData.length():0));
	}


	
	/**
	 * @return the appName
	 */
	public String getAppName() {
	
		return appName;
	}


	
	/**
	 * @param appName the appName to set
	 */
	public void setAppName(String appName) {
	
		this.appName = appName;
	}


	
	/**
	 * @return the serviceName
	 */
	public String getServiceName() {
	
		return serviceName;
	}


	
	/**
	 * @param serviceName the serviceName to set
	 */
	public void setServiceName(String serviceName) {
	
		this.serviceName = serviceName;
	}


	
	/**
	 * @return the portalId
	 */
	public String getPortalId() {
	
		return portalId;
	}


	
	/**
	 * @param portalId the portalId to set
	 */
	public void setPortalId(String portalId) {
	
		this.portalId = portalId;
	}


	
	/**
	 * @return the key
	 */
	public String getKey() {
	
		return key;
	}


	
	/**
	 * @param key the key to set
	 */
	public void setKey(String key) {
	
		this.key = key;
	}

	/**
	 * @return the reply
	 */
	public String getReply() {
	
		return reply;
	}




	
	/**
	 * @param reply the reply to set
	 */
	public void setReply(String reply) {
	
		this.reply = reply;
	}

}
