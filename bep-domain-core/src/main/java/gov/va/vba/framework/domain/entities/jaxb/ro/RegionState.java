//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.06.29 at 04:19:56 PM MDT 
//


package gov.va.vba.framework.domain.entities.jaxb.ro;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RegionName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="StateCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ROName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="RONumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "regionName",
    "stateCode",
    "roName",
    "roNumber"
})
@XmlRootElement(name = "RegionState")
public class RegionState {

    @XmlElement(name = "RegionName", required = true)
    protected String regionName;
    @XmlElement(name = "StateCode", required = true)
    protected String stateCode;
    @XmlElement(name = "ROName", required = true)
    protected String roName;
    @XmlElement(name = "RONumber", required = true)
    protected String roNumber;

    /**
     * Gets the value of the regionName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegionName() {
        return regionName;
    }

    /**
     * Sets the value of the regionName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegionName(String value) {
        this.regionName = value;
    }

    /**
     * Gets the value of the stateCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStateCode() {
        return stateCode;
    }

    /**
     * Sets the value of the stateCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStateCode(String value) {
        this.stateCode = value;
    }

    /**
     * Gets the value of the roName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getROName() {
        return roName;
    }

    /**
     * Sets the value of the roName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setROName(String value) {
        this.roName = value;
    }

    /**
     * Gets the value of the roNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRONumber() {
        return roNumber;
    }

    /**
     * Sets the value of the roNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRONumber(String value) {
        this.roNumber = value;
    }

}