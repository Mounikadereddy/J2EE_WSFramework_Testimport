package gov.va.vba.framework.domain.entities.jaxb;


import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.example.vadir package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    private final static QName _PtcpntId_QNAME = new QName("http://vonapps.vba.va.gov/", "ptcpnt_id");
    private final static QName _GetVeteranDataResponse_QNAME = new QName("http://vonapps.vba.va.gov/", "getVeteranDataResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: gov.va.vba.vetsnet.client.jaxb
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link NameType }
     * 
     */
    public NameType createNameType() {
        return new NameType();
    }

    /**
     * Create an instance of {@link ChildNameType }
     * 
     */
    public ChildNameType createChildNameType() {
        return new ChildNameType();
    }

    /**
     * Create an instance of {@link ChildrenType }
     * 
     */
    public ChildrenType createChildrenType() {
        return new ChildrenType();
    }

    /**
     * Create an instance of {@link PhoneNumberType }
     * 
     */
    public PhoneNumberType createPhoneNumberType() {
        return new PhoneNumberType();
    }

    /**
     * Create an instance of {@link DisabilityType }
     * 
     */
    public DisabilityType createDisabilityType() {
        return new DisabilityType();
    }

    /**
     * Create an instance of {@link ChildType }
     * 
     */
    public ChildType createChildType() {
        return new ChildType();
    }

    /**
     * Create an instance of {@link MaritalStatusType }
     * 
     */
    public MaritalStatusType createMaritalStatusType() {
        return new MaritalStatusType();
    }

    /**
     * Create an instance of {@link NursingHomeType }
     * 
     */
    public NursingHomeType createNursingHomeType() {
        return new NursingHomeType();
    }

    /**
     * Create an instance of {@link MilitaryServiceType.ServiceBranchName }
     * 
     */
    public MilitaryServiceType.ServiceBranchName createMilitaryServiceTypeServiceBranchName() {
        return new MilitaryServiceType.ServiceBranchName();
    }

    /**
     * Create an instance of {@link GetVeteranData }
     * 
     */
    public GetVeteranData createGetVeteranData() {
        return new GetVeteranData();
    }

    /**
     * Create an instance of {@link TreatmentType }
     * 
     */
    public TreatmentType createTreatmentType() {
        return new TreatmentType();
    }

    /**
     * Create an instance of {@link AddressType }
     * 
     */
    public AddressType createAddressType() {
        return new AddressType();
    }

    /**
     * Create an instance of {@link SpouseType }
     * 
     */
    public SpouseType createSpouseType() {
        return new SpouseType();
    }

    /**
     * Create an instance of {@link MarriedType }
     * 
     */
    public MarriedType createMarriedType() {
        return new MarriedType();
    }

    /**
     * Create an instance of {@link MilitaryServiceType }
     * 
     */
    public MilitaryServiceType createMilitaryServiceType() {
        return new MilitaryServiceType();
    }

    /**
     * Create an instance of {@link ChildType.SocialSecurityNum }
     * 
     */
    public ChildType.SocialSecurityNum createChildTypeSocialSecurityNum() {
        return new ChildType.SocialSecurityNum();
    }

    /**
     * Create an instance of {@link MilitaryServiceType.MilitaryServiceNumber }
     * 
     */
    public MilitaryServiceType.MilitaryServiceNumber createMilitaryServiceTypeMilitaryServiceNumber() {
        return new MilitaryServiceType.MilitaryServiceNumber();
    }

    /**
     * Create an instance of {@link DeathInformationType }
     * 
     */
    public DeathInformationType createDeathInformationType() {
        return new DeathInformationType();
    }

    /**
     * Create an instance of {@link EmailType }
     * 
     */
    public EmailType createEmailType() {
        return new EmailType();
    }

    /**
     * Create an instance of {@link CemetryType }
     * 
     */
    public CemetryType createCemetryType() {
        return new CemetryType();
    }

    /**
     * Create an instance of {@link BirthInformationType }
     * 
     */
    public BirthInformationType createBirthInformationType() {
        return new BirthInformationType();
    }

    /**
     * Create an instance of {@link PostalType }
     * 
     */
    public PostalType createPostalType() {
        return new PostalType();
    }

    /**
     * Create an instance of {@link VeteranDataType }
     * 
     */
    public VeteranDataType createVeteranDataType() {
        return new VeteranDataType();
    }

    /**
     * Create an instance of {@link IncomeType }
     * 
     */
    public IncomeType createIncomeType() {
        return new IncomeType();
    }

    /**
     * Create an instance of {@link IdenitifiersType }
     * 
     */
    public IdenitifiersType createIdenitifiersType() {
        return new IdenitifiersType();
    }

    /**
     * Create an instance of {@link MilitaryServiceType.VAOfficeRecordsLocation }
     * 
     */
    public MilitaryServiceType.VAOfficeRecordsLocation createMilitaryServiceTypeVAOfficeRecordsLocation() {
        return new MilitaryServiceType.VAOfficeRecordsLocation();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://vonapps.vba.va.gov/", name = "ptcpnt_id")
    public JAXBElement<String> createPtcpntId(String value) {
        return new JAXBElement<String>(_PtcpntId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VeteranDataType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://vonapps.vba.va.gov/", name = "getVeteranDataResponse")
    public JAXBElement<VeteranDataType> createGetVeteranDataResponse(VeteranDataType value) {
        return new JAXBElement<VeteranDataType>(_GetVeteranDataResponse_QNAME, VeteranDataType.class, null, value);
    }
 
    /**
     * Create an instance of {@link PERSONNELST }
     * 
     */
    public PERSONNELST createPERSONNELST() {
        return new PERSONNELST();
    }

    /**
     * Create an instance of {@link PERSONV }
     * 
     */
    public PERSONV createPERSONV() {
        return new PERSONV();
    }

    /**
     * Create an instance of {@link PAGE }
     * 
     */
    public PAGE createPAGE() {
        return new PAGE();
    }

    /**
     * Create an instance of {@link TELEPHONENUMBER }
     * 
     */
    public TELEPHONENUMBER createTELEPHONENUMBER() {
        return new TELEPHONENUMBER();
    }

    /**
     * Create an instance of {@link PERSONNEL }
     * 
     */
    public PERSONNEL createPERSONNEL() {
        return new PERSONNEL();
    }

    /**
     * Create an instance of {@link VADIR }
     * 
     */
    public VADIR createVADIR() {
        return new VADIR();
    }

    /**
     * Create an instance of {@link ALIASNAME }
     * 
     */
    public ALIASNAME createALIASNAME() {
        return new ALIASNAME();
    }

    /**
     * Create an instance of {@link MAILINGADDRESS }
     * 
     */
    public MAILINGADDRESS createMAILINGADDRESS() {
        return new MAILINGADDRESS();
    }

    /**
     * Create an instance of {@link EDUCATION }
     * 
     */
    public EDUCATION createEDUCATION() {
        return new EDUCATION();
    }
    
    

}
