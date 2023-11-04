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
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for IdenitifiersType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="IdenitifiersType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Name" type="{http://vonapps.vba.va.gov/}NameType"/>
 *         &lt;element name="Social_Security_Num" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="File_Number" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Other_Name" type="{http://vonapps.vba.va.gov/}NameType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IdenitifiersType", propOrder = {
    "name",
    "socialSecurityNum",
    "fileNumber",
    "otherName"
})
public class IdenitifiersType {

    @XmlElement(name = "Name", required = true)
    protected NameType name;
    @XmlElement(name = "Social_Security_Num", required = true)
    protected String socialSecurityNum;
    @XmlElement(name = "File_Number", required = true)
    protected String fileNumber;
    @XmlElement(name = "Other_Name")
    protected List<NameType> otherName;

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link NameType }
     *     
     */
    public NameType getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link NameType }
     *     
     */
    public void setName(NameType value) {
        this.name = value;
    }

    /**
     * Gets the value of the socialSecurityNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSocialSecurityNum() {
        return socialSecurityNum;
    }

    /**
     * Sets the value of the socialSecurityNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSocialSecurityNum(String value) {
        this.socialSecurityNum = value;
    }

    /**
     * Gets the value of the fileNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFileNumber() {
        return fileNumber;
    }

    /**
     * Sets the value of the fileNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFileNumber(String value) {
        this.fileNumber = value;
    }

    /**
     * Gets the value of the otherName property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the otherName property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOtherName().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link NameType }
     * 
     * 
     */
    public List<NameType> getOtherName() {
        if (otherName == null) {
            otherName = new ArrayList<NameType>();
        }
        return this.otherName;
    }

}