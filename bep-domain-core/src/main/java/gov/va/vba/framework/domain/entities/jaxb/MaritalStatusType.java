//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.1.6-2.1.6-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.01.13 at 01:54:05 PM CST 
//


package gov.va.vba.framework.domain.entities.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MaritalStatusType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MaritalStatusType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element name="Married" type="{http://vonapps.vba.va.gov/}MarriedType"/>
 *         &lt;element name="Unmarried" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MaritalStatusType", propOrder = {
    "married",
    "unmarried"
})
public class MaritalStatusType {

    @XmlElement(name = "Married")
    protected MarriedType married;
    @XmlElement(name = "Unmarried")
    protected String unmarried;

    /**
     * Gets the value of the married property.
     * 
     * @return
     *     possible object is
     *     {@link MarriedType }
     *     
     */
    public MarriedType getMarried() {
        return married;
    }

    /**
     * Sets the value of the married property.
     * 
     * @param value
     *     allowed object is
     *     {@link MarriedType }
     *     
     */
    public void setMarried(MarriedType value) {
        this.married = value;
    }

    /**
     * Gets the value of the unmarried property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnmarried() {
        return unmarried;
    }

    /**
     * Sets the value of the unmarried property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnmarried(String value) {
        this.unmarried = value;
    }

}
