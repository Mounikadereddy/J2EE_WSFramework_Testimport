package gov.va.vba.framework.common;

import gov.va.vba.framework.exceptions.AuditException;
import gov.va.vba.framework.services.TuxedoException;
import gov.va.vba.framework.logging.Logger;

import java.io.Serializable;



public class AuditContext implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userID;
	private String participantID;
	private String clientIPAddress;
	private String applicationName;
	private String tuxedoServiceName;
	private String auditID;
	private String clientUniqueID;
	private String clientUniqueKey;
	private String stationID;
	
	private boolean forceExternal;
	
	public static final String MISSING_APPLICATION_FROM_AUDITCONTEXT="missing application from auditContext";
	public static final String MISSING_CLIENT_IP_ADDRESS_FROM_AUDITCONTEXT="missing client IP address from auditContext";
	public static final String MISSING_STATION_ID_FROM_AUDITCONTEXT="missing station ID from auditContext";
	public static final String MISSING_USER_ID_FROM_AUDITCONTEXT="missing user ID from auditContext";
	public static final String AUDITCONTEXT_IS_NULL="auditContext is null";
	
	private static Logger logger=Logger.getLogger(AuditContext.class);

	
	public AuditContext(){
		forceExternal = false;
	}
	
	public AuditContext(String userID, String clientIPAddress, String applicationName, String auditID, String externalID, String externalKey, String stationID){
		this.userID=userID;
		this.clientIPAddress=clientIPAddress;
		this.applicationName=applicationName;
		this.auditID=auditID;
		this.clientUniqueID=externalID;
		this.clientUniqueKey=externalKey;
		this.stationID=stationID;
		forceExternal = false;
	}
	
	public boolean isValid()
	{
		return StringUtils.isEmpty(getInvalidReason());
	}
	
	public static void validate(AuditContext auditContext) throws AuditException
	{
		if (auditContext==null || !auditContext.isValid())
		{
			String invalidReason="";
			if (auditContext==null)
				invalidReason=AUDITCONTEXT_IS_NULL;
			else if (!auditContext.isValid())
				invalidReason=auditContext.getInvalidReason();
			throw new AuditException(invalidReason);
		}
	}
	
	public String getInvalidReason()
	{
		String reason="";
		if (StringUtils.isEmpty(applicationName))
		{
			reason=MISSING_APPLICATION_FROM_AUDITCONTEXT;
			logger.error(reason);
		}
		if (StringUtils.isEmpty(clientIPAddress)) {
			reason=MISSING_CLIENT_IP_ADDRESS_FROM_AUDITCONTEXT;
			logger.error(reason);
		}
		if (StringUtils.isEmpty(stationID)) {
			reason=MISSING_STATION_ID_FROM_AUDITCONTEXT;
			logger.error(reason);
		}
		if (StringUtils.isEmpty(userID) && StringUtils.isEmpty(participantID)) {
			reason=MISSING_USER_ID_FROM_AUDITCONTEXT;
			logger.error(reason);
		}
		return reason;
	}
	
	public String getUserId() {
		return userID;
	}
	public void setUserId(String userID) {
		this.userID = userID;
	}
	public String getParticipantID() {
	    return participantID;
	}
	public void setParticipantID(String participantID) {
	    this.participantID = participantID;
    }
	public String getClientIPAddress() {
		return clientIPAddress;
	}
	public void setClientIPAddress(String clientIPAddress) {
		this.clientIPAddress = clientIPAddress;
	}
	public String getApplicationName() {
		return applicationName;
	}
	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}
	public String getTuxedoServiceName() {
		return tuxedoServiceName;
	}
	public void setTuxedoServiceName(String tuxedoServiceName) {
		this.tuxedoServiceName = tuxedoServiceName;
	}
	public String getAuditID() {
		return auditID;
	}
	/*
	 * Please do not manually populate this attribute. 
	 * This will be automatically populated during the EBJ method call. 
	 */
	public void setAuditID(String auditID) {
		this.auditID = auditID;
	}
	public String getClientUniqueID() {
		return clientUniqueID;
	}
	public void setClientUniqueID(String clientUniqueID) {
		this.clientUniqueID = clientUniqueID;
	}
	public String getClientUniqueKey() {
		return clientUniqueKey;
	}
	public void setClientUniqueKey(String clientUniqueKey) {
		this.clientUniqueKey = clientUniqueKey;
	}
	public String getStationID() {
		return stationID;
	}
	public void setStationID(String stationID) {
		this.stationID = stationID;
	}
	
	public boolean isRequestExternal() throws TuxedoException 
	{
		String id = getClientUniqueID();
		String key = getClientUniqueKey();
		boolean hasID = id!=null && !StringUtils.isEmpty(id.trim());
		boolean hasKey = key!=null && !StringUtils.isEmpty(key.trim());
		//improved security with checking BEP.properties
		boolean isServerExternal=isExternal();
		
		/**
		 * Check for all possible domain related message header errors.
		 */
		if (isServerExternal && (!hasID || !hasKey))
			throw new TuxedoException("external key and external ID are required fields for Tuxedo service requests in the external domain");
		
		if (!isServerExternal && (hasID || hasKey))
			throw new TuxedoException("Tuxedo service requests with external key and external ID can only be sent through an external domain");
		/**
		 * if no errors, then an external request is identified by having both the ID and Key in the header. 
		 */
		return (hasID && hasKey? true: false);
	}
	
	public boolean isExternal() throws TuxedoException 
	{
		return forceExternal?true:SystemUtils.getBooleanProperty(SystemUtils.Key.EXTERNAL);
	}
	
	public boolean isInternal() throws TuxedoException
	{
		return !isExternal();
	}
	
	/**
	 * Causes the AuditContext to ignore the BEP Property external=? and returns true to the isExternalMethod
	 * @param forceExternal
	 */
	public void setForceExternal(boolean forceExternal) {
		this.forceExternal = forceExternal;
	}
	
	public boolean getForceExternal() {
		return forceExternal;
	}
	
	public String toString()
	{
		String string="";
		StringBuilder sb=new StringBuilder();
		sb.append("AuditContext[");
		sb.append("auditID=\""+auditID+"\",");
		sb.append("userID=\""+userID+"\",");
		sb.append("participantID=\""+participantID+"\",");
		sb.append("clientIPAddress=\""+clientIPAddress+"\",");
		sb.append("applicationName=\""+applicationName+"\",");
		sb.append("clientUniqueID=\""+clientUniqueID+"\",");
		sb.append("clientUniqueKey=\""+clientUniqueKey+"\",");
		sb.append("stationID=\""+stationID+"\"]");
		string=sb.toString();
		return string;
	}
}
