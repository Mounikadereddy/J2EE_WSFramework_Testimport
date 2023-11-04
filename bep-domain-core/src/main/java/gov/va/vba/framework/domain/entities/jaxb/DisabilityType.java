//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.1.6-2.1.6-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.01.13 at 01:54:05 PM CST 
//


package gov.va.vba.framework.domain.entities.jaxb;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for DisabilityType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DisabilityType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="VA_Disability_rating" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Disability_Nature" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="Disability_Begin_Date" type="{http://www.w3.org/2001/XMLSchema}date" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="Treatment" type="{http://vonapps.vba.va.gov/}TreatmentType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="Service_Connected_Disability" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DisabilityType", propOrder = {
    "vaDisabilityRating",
    "disabilityNature",
    "disabilityBeginDate",
    "treatment",
    "serviceConnectedDisability"
})
public class DisabilityType {

    @XmlElement(name = "VA_Disability_rating")
    protected String vaDisabilityRating;
    @XmlElement(name = "Disability_Nature")
    protected List<String> disabilityNature;
    @XmlElement(name = "Disability_Begin_Date")
    @XmlSchemaType(name = "date")
    protected List<XMLGregorianCalendar> disabilityBeginDate;
    @XmlElement(name = "Treatment")
    protected List<TreatmentType> treatment;
    @XmlElement(name = "Service_Connected_Disability")
    protected String serviceConnectedDisability;

    /**
     * Gets the value of the vaDisabilityRating property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVADisabilityRating() {
        return vaDisabilityRating;
    }

    /**
     * Sets the value of the vaDisabilityRating property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVADisabilityRating(String value) {
        this.vaDisabilityRating = value;
    }

    /**
     * Gets the value of the disabilityNature property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the disabilityNature property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDisabilityNature().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getDisabilityNature() {
        if (disabilityNature == null) {
            disabilityNature = new ArrayList<String>();
        }
        return this.disabilityNature;
    }

    /**
     * Gets the value of the disabilityBeginDate property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the disabilityBeginDate property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDisabilityBeginDate().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link XMLGregorianCalendar }
     * 
     * 
     */
    public List<XMLGregorianCalendar> getDisabilityBeginDate() {
        if (disabilityBeginDate == null) {
            disabilityBeginDate = new ArrayList<XMLGregorianCalendar>();
        }
        return this.disabilityBeginDate;
    }

    /**
     * Gets the value of the treatment property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the treatment property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTreatment().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TreatmentType }
     * 
     * 
     */
    public List<TreatmentType> getTreatment() {
        if (treatment == null) {
            treatment = new ArrayList<TreatmentType>();
        }
        return this.treatment;
    }

    /**
     * Gets the value of the serviceConnectedDisability property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceConnectedDisability() {
        return serviceConnectedDisability;
    }

    /**
     * Sets the value of the serviceConnectedDisability property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceConnectedDisability(String value) {
        this.serviceConnectedDisability = value;
    }

}
