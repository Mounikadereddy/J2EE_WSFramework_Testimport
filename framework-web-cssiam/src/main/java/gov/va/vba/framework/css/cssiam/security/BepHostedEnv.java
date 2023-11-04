package gov.va.vba.framework.css.cssiam.security;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Class that represents an environment where Bep Hosted App being deployed
 * @author vhaisbnguyeq
 *
 */
@XmlRootElement
public class BepHostedEnv {

	/**
	 * Identifier
	 */
	private String envId;
	
	/**
	 * URL associated with the environment
	 */
	private String url;
	
	/**
	 * test env indicator
	 */
	private boolean testEnv;
	
	/**
	 * Get the environment identifier
	 * @return id for the current env
	 */
	public String getEnvId() {
		return envId;
	}
	
	/**
	 * Set the env id
	 * @param envId environment identifier
	 */
	@XmlAttribute
	public void setEnvId(String envId) {
		this.envId = envId;
	}
	
	/**
	 * Get the URL for this env
	 * @return URL associated with the application
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * Set the URL for this env
	 * @param url - URL associated with the environment
	 */
	@XmlElement
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * boolean indicator for TEST/PROD env.
	 * @return boolean indicator for the environment 
	 */
	public boolean isTestEnv() {
		return testEnv;
	}

	/**
	 * set the value to proper env
	 * @param testEnv - true/false if it's test env 
	 */
	@XmlElement
	public void setTestEnv(boolean testEnv) {
		this.testEnv = testEnv;
	}	
}
