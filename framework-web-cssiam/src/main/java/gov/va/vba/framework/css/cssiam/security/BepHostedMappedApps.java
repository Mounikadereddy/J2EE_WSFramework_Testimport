package gov.va.vba.framework.css.cssiam.security;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Class that contains the list of supported Bep Hosted Apps
 * @author VHAISPVANEGI
 *
 */
@XmlRootElement(name = "bepHostedMappedApps")
public class BepHostedMappedApps {

	/**
	 * List of supported apps
	 */
	private ArrayList<BepHostedApp> bepHostedMappedApps;
	
	/**
	 * 
	 * @param bepHostedMappedApps List of supported apps
	 */
	public void setBepHostedMappedApps(ArrayList<BepHostedApp> bepHostedMappedApps) {
		this.bepHostedMappedApps = bepHostedMappedApps;
	}

	/**
	 * 
	 * @return List of supported apps
	 */
	@XmlElement(name = "bepHostedApp")
	public ArrayList<BepHostedApp> getBepHostedMappedApps() {
		return bepHostedMappedApps;
	}
}
