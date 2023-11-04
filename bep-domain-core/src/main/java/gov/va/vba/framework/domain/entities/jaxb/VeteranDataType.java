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
 * <p>Java class for VeteranDataType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="VeteranDataType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Idenitifiers" type="{http://vonapps.vba.va.gov/}IdenitifiersType" minOccurs="0"/>
 *         &lt;element name="Addresses" type="{http://vonapps.vba.va.gov/}AddressType" minOccurs="0"/>
 *         &lt;element name="Gender" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Military_Service" type="{http://vonapps.vba.va.gov/}MilitaryServiceType" minOccurs="0"/>
 *         &lt;element name="Birth_Information" type="{http://vonapps.vba.va.gov/}BirthInformationType" minOccurs="0"/>
 *         &lt;element name="Marital_Status" type="{http://vonapps.vba.va.gov/}MaritalStatusType" minOccurs="0"/>
 *         &lt;element name="Children" type="{http://vonapps.vba.va.gov/}ChildrenType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="Disability" type="{http://vonapps.vba.va.gov/}DisabilityType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="Income" type="{http://vonapps.vba.va.gov/}IncomeType" minOccurs="0"/>
 *         &lt;element name="Nursing_Home" type="{http://vonapps.vba.va.gov/}NursingHomeType" minOccurs="0"/>
 *         &lt;element name="Death_Information" type="{http://vonapps.vba.va.gov/}DeathInformationType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "VeteranDataType", propOrder = {
    "idenitifiers",
    "addresses",
    "gender",
    "militaryService",
    "birthInformation",
    "maritalStatus",
    "children",
    "disability",
    "income",
    "nursingHome",
    "deathInformation"
})
public class VeteranDataType {

    @XmlElement(name = "Idenitifiers")
    protected IdenitifiersType idenitifiers;
    @XmlElement(name = "Addresses")
    protected AddressType addresses;
    @XmlElement(name = "Gender")
    protected String gender;
    @XmlElement(name = "Military_Service")
    protected MilitaryServiceType militaryService;
    @XmlElement(name = "Birth_Information")
    protected BirthInformationType birthInformation;
    @XmlElement(name = "Marital_Status")
    protected MaritalStatusType maritalStatus;
    @XmlElement(name = "Children")
    protected List<ChildrenType> children;
    @XmlElement(name = "Disability")
    protected List<DisabilityType> disability;
    @XmlElement(name = "Income")
    protected IncomeType income;
    @XmlElement(name = "Nursing_Home")
    protected NursingHomeType nursingHome;
    @XmlElement(name = "Death_Information")
    protected DeathInformationType deathInformation;

    /**
     * Gets the value of the idenitifiers property.
     * 
     * @return
     *     possible object is
     *     {@link IdenitifiersType }
     *     
     */
    public IdenitifiersType getIdenitifiers() {
        return idenitifiers;
    }

    /**
     * Sets the value of the idenitifiers property.
     * 
     * @param value
     *     allowed object is
     *     {@link IdenitifiersType }
     *     
     */
    public void setIdenitifiers(IdenitifiersType value) {
        this.idenitifiers = value;
    }

    /**
     * Gets the value of the addresses property.
     * 
     * @return
     *     possible object is
     *     {@link AddressType }
     *     
     */
    public AddressType getAddresses() {
        return addresses;
    }

    /**
     * Sets the value of the addresses property.
     * 
     * @param value
     *     allowed object is
     *     {@link AddressType }
     *     
     */
    public void setAddresses(AddressType value) {
        this.addresses = value;
    }

    /**
     * Gets the value of the gender property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGender() {
        return gender;
    }

    /**
     * Sets the value of the gender property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGender(String value) {
        this.gender = value;
    }

    /**
     * Gets the value of the militaryService property.
     * 
     * @return
     *     possible object is
     *     {@link MilitaryServiceType }
     *     
     */
    public MilitaryServiceType getMilitaryService() {
        return militaryService;
    }

    /**
     * Sets the value of the militaryService property.
     * 
     * @param value
     *     allowed object is
     *     {@link MilitaryServiceType }
     *     
     */
    public void setMilitaryService(MilitaryServiceType value) {
        this.militaryService = value;
    }

    /**
     * Gets the value of the birthInformation property.
     * 
     * @return
     *     possible object is
     *     {@link BirthInformationType }
     *     
     */
    public BirthInformationType getBirthInformation() {
        return birthInformation;
    }

    /**
     * Sets the value of the birthInformation property.
     * 
     * @param value
     *     allowed object is
     *     {@link BirthInformationType }
     *     
     */
    public void setBirthInformation(BirthInformationType value) {
        this.birthInformation = value;
    }

    /**
     * Gets the value of the maritalStatus property.
     * 
     * @return
     *     possible object is
     *     {@link MaritalStatusType }
     *     
     */
    public MaritalStatusType getMaritalStatus() {
        return maritalStatus;
    }

    /**
     * Sets the value of the maritalStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link MaritalStatusType }
     *     
     */
    public void setMaritalStatus(MaritalStatusType value) {
        this.maritalStatus = value;
    }

    /**
     * Gets the value of the children property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the children property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getChildren().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ChildrenType }
     * 
     * 
     */
    public List<ChildrenType> getChildren() {
        if (children == null) {
            children = new ArrayList<ChildrenType>();
        }
        return this.children;
    }

    /**
     * Gets the value of the disability property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the disability property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDisability().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DisabilityType }
     * 
     * 
     */
    public List<DisabilityType> getDisability() {
        if (disability == null) {
            disability = new ArrayList<DisabilityType>();
        }
        return this.disability;
    }

    /**
     * Gets the value of the income property.
     * 
     * @return
     *     possible object is
     *     {@link IncomeType }
     *     
     */
    public IncomeType getIncome() {
        return income;
    }

    /**
     * Sets the value of the income property.
     * 
     * @param value
     *     allowed object is
     *     {@link IncomeType }
     *     
     */
    public void setIncome(IncomeType value) {
        this.income = value;
    }

    /**
     * Gets the value of the nursingHome property.
     * 
     * @return
     *     possible object is
     *     {@link NursingHomeType }
     *     
     */
    public NursingHomeType getNursingHome() {
        return nursingHome;
    }

    /**
     * Sets the value of the nursingHome property.
     * 
     * @param value
     *     allowed object is
     *     {@link NursingHomeType }
     *     
     */
    public void setNursingHome(NursingHomeType value) {
        this.nursingHome = value;
    }

    /**
     * Gets the value of the deathInformation property.
     * 
     * @return
     *     possible object is
     *     {@link DeathInformationType }
     *     
     */
    public DeathInformationType getDeathInformation() {
        return deathInformation;
    }

    /**
     * Sets the value of the deathInformation property.
     * 
     * @param value
     *     allowed object is
     *     {@link DeathInformationType }
     *     
     */
    public void setDeathInformation(DeathInformationType value) {
        this.deathInformation = value;
    }

}