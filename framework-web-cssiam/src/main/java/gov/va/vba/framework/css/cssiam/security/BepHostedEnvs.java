package gov.va.vba.framework.css.cssiam.security;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Class that contains the list of deployed environments
 * @author vhaisbnguyeq
 *
 */
@XmlRootElement(name = "bepHostedEnvs")
public class BepHostedEnvs {

	/**
	 * List of supported environments
	 */
	private ArrayList<BepHostedEnv> bepHostedEnvs;
	
	/**
	 * set all the possible deploy-able environments.
	 * @param bepHostedEnvs List of supported environments
	 */
	public void setBepHostedEnvs(ArrayList<BepHostedEnv> bepHostedEnvs) {
		this.bepHostedEnvs = bepHostedEnvs;
	}

	/**
	 * get all the possible environments from the configuration
	 * @return List of available environments
	 */
	@XmlElement(name = "bepHostedEnv")
	public ArrayList<BepHostedEnv> getBepHostedEnvs() {
		return bepHostedEnvs;
	}
}