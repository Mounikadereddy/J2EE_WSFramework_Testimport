package gov.va.vba.framework.css.cssiam.security;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

/**
 * Class that represents an environment where Bep Hosted App being deployed
 * @author VBACOVANEGI
 *
 */
@XmlRootElement(name="iamTrait")
public class IamTrait {

	/**
	 * Trait identifier
	 */
	private String traitId;

	/**
	 * Getter
	 * @return
	 */
	@XmlValue
	public String getTraitId() {
		return traitId;
	}

	/**
	 * Setter
	 * @param traitId
	 */
	public void setTraitId(String traitId) {
		this.traitId = traitId;
	}
}
