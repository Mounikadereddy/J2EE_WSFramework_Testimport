package gov.va.vba.framework.css.cssiam.security;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Class that represents a supported Bep Hosted App
 * @author VHAISPVANEGI
 *
 */
@XmlRootElement
public class BepHostedApp {

	/**
	 * Identifier
	 */
	private String appId;
	
	/**
	 * URL associated with the application
	 */
	private String url;
	
	/**
	 * CSS Application Name
	 */
	private String CSSId;
	
	/**
	 * App identifier
	 * @return
	 */
	public String getAppId() {
		return appId;
	}
	
	/**
	 * 
	 * @param appId App identifier
	 */
	@XmlAttribute
	public void setAppId(String appId) {
		this.appId = appId;
	}
	
	/**
	 * 
	 * @return URL associated with the application
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * 
	 * @param url URL associated with the application
	 */
	@XmlElement
	public void setUrl(String url) {
		this.url = url;
	}
	
	/**
	 * 
	 * @return CSS Application Name
	 */
	public String getCSSId() {
		return CSSId;
	}
	
	/**
	 * 
	 * @param cSSId CSS Application Name
	 */
	@XmlElement
	public void setCSSId(String cSSId) {
		CSSId = cSSId;
	}
}
