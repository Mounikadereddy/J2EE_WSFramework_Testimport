package gov.va.vba.framework.css.cssiam.security;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Class that contains the list of mandatory IAM SiteMinder traits after a succesfull IAM authetication
 * @author VBACOVANEGI
 *
 */
@XmlRootElement(name = "iamMandatoryTraits")
public class IamMandatoryTraits {

	/**
	 * List of mandatory traits
	 */
	private ArrayList<IamTrait> iamMandatoryTraits;
	
	/**
	 * Set all mandatory IAM StieMinder Traits
	 * @param iamMandatoryTraits List of mandatory IAM traits
	 */
	public void setIamMandatoryTraits(ArrayList<IamTrait> iamMandatoryTraits) {
		this.iamMandatoryTraits = iamMandatoryTraits;
	}

	/**
	 * Get all mandatory IAM Traits
	 * @return List of mandatory IAM Traits
	 */
	@XmlElement(name = "iamTrait")
	public ArrayList<IamTrait> getIamMandatoryTraits() {
		return iamMandatoryTraits;
	}
	
	
}
