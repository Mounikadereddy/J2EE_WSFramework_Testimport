package gov.va.vba.framework.css.cssiam.security;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Class that contains different Filter configurations loaded from a configuration file
 * @author VHAISPVANEGI
 *
 */
@XmlRootElement
public class CSSIAMFilterConfig {

	/**
	 * Container for the list of supported BEP Hosted Apps
	 */
	private BepHostedMappedApps bepHostedMappedApps;
	
	/** Container for hosted environments */
	private BepHostedEnvs bepHostedEnvs;
	
	/** Container for IAM mandatory Traits */
	private IamMandatoryTraits iamMandatoryTraits;
	
	/**
	 * 
	 * @return Container for the list of supported BEP Hosted Apps
	 */
	@XmlElement (name = "bepHostedMappedApps")
	public BepHostedMappedApps getBepHostedMappedApps() {
		return bepHostedMappedApps;
	}

	/**
	 * 
	 * @param bepHostedMappedApps Container for the list of supported BEP Hosted Apps
	 */
	public void setBepHostedMappedApps(BepHostedMappedApps bepHostedMappedApps) {
		this.bepHostedMappedApps = bepHostedMappedApps;
	}

	/**
	 * Get the container of all environments
	 * @return Container for the list of supported environments
	 */
	@XmlElement (name = "bepHostedEnvs")
	public BepHostedEnvs getBepHostedEnvs() {
		return bepHostedEnvs;
	}

	/**
	 * Set the container class of all environments
	 * @param bepHostedEnvs Container for the list of supported environments
	 */
	public void setBepHostedEnvs(BepHostedEnvs bepHostedEnvs) {
		this.bepHostedEnvs = bepHostedEnvs;
	}
	
	/**
	 * Get the container of all mandatory IAM Traits
	 * @return iamMandatoryTraits COntainer for the list of mandatory IAM Traits
	 */
	@XmlElement (name = "iamMandatoryTraits")
	public IamMandatoryTraits getIamMandatoryTraits() {
		return iamMandatoryTraits;
	}

	/**
	 * Set the container class of all mandatory IAM Traits
	 * @param iamMandatoryTraits
	 */
	public void setIamMandatoryTraits(IamMandatoryTraits iamMandatoryTraits) {
		this.iamMandatoryTraits = iamMandatoryTraits;
	}	
}
