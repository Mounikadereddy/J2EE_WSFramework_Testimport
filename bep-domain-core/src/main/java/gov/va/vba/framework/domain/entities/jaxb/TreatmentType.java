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
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for TreatmentType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TreatmentType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Treatment_Date" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="Treatment_Physician" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Treatment_Facility" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Postal" type="{http://vonapps.vba.va.gov/}PostalType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TreatmentType", propOrder = {
    "treatmentDate",
    "treatmentPhysician",
    "treatmentFacility",
    "postal"
})
public class TreatmentType {

    @XmlElement(name = "Treatment_Date")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar treatmentDate;
    @XmlElement(name = "Treatment_Physician")
    protected String treatmentPhysician;
    @XmlElement(name = "Treatment_Facility")
    protected String treatmentFacility;
    @XmlElement(name = "Postal")
    protected PostalType postal;

    /**
     * Gets the value of the treatmentDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getTreatmentDate() {
        return treatmentDate;
    }

    /**
     * Sets the value of the treatmentDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setTreatmentDate(XMLGregorianCalendar value) {
        this.treatmentDate = value;
    }

    /**
     * Gets the value of the treatmentPhysician property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTreatmentPhysician() {
        return treatmentPhysician;
    }

    /**
     * Sets the value of the treatmentPhysician property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTreatmentPhysician(String value) {
        this.treatmentPhysician = value;
    }

    /**
     * Gets the value of the treatmentFacility property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTreatmentFacility() {
        return treatmentFacility;
    }

    /**
     * Sets the value of the treatmentFacility property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTreatmentFacility(String value) {
        this.treatmentFacility = value;
    }

    /**
     * Gets the value of the postal property.
     * 
     * @return
     *     possible object is
     *     {@link PostalType }
     *     
     */
    public PostalType getPostal() {
        return postal;
    }

    /**
     * Sets the value of the postal property.
     * 
     * @param value
     *     allowed object is
     *     {@link PostalType }
     *     
     */
    public void setPostal(PostalType value) {
        this.postal = value;
    }

}