package gov.va.vba.framework.services.ejb.local;

//change to fix mistake in comment in dimensions
import gov.va.vba.framework.common.AuditContext;
import gov.va.vba.framework.domain.entities.Award;
import gov.va.vba.framework.domain.entities.AwardPK;
import gov.va.vba.framework.domain.entities.BnftClaim;
import gov.va.vba.framework.domain.entities.BurialSumryDecn;
import gov.va.vba.framework.domain.entities.Ch31IntrfcGedDgnstc;
import gov.va.vba.framework.domain.entities.ClaimDvlpmtCntct;
import gov.va.vba.framework.domain.entities.CpmrChildBirthSgmnt;
import gov.va.vba.framework.domain.entities.CpmrStatclSgmnt;
import gov.va.vba.framework.domain.entities.Dsblty;
import gov.va.vba.framework.domain.entities.DsbltyEvaltn;
import gov.va.vba.framework.domain.entities.ExtendStatcl;
import gov.va.vba.framework.domain.entities.IncomeDecn;
import gov.va.vba.framework.domain.entities.MltyPerson;
import gov.va.vba.framework.domain.entities.MltyPersonBranch;
import gov.va.vba.framework.domain.entities.MltyPersonTour;
import gov.va.vba.framework.domain.entities.MltyTheatr;
import gov.va.vba.framework.domain.entities.NetWorthDecn;
import gov.va.vba.framework.domain.entities.NetWorthDecnPK;
import gov.va.vba.framework.domain.entities.Person;
import gov.va.vba.framework.domain.entities.PersonApplcnPrfrnc;
import gov.va.vba.framework.domain.entities.PersonDep;
import gov.va.vba.framework.domain.entities.PersonEduLevel;
import gov.va.vba.framework.domain.entities.PersonEmplyr;
import gov.va.vba.framework.domain.entities.PersonIdfctnHist;
import gov.va.vba.framework.domain.entities.PersonNetWorth;
import gov.va.vba.framework.domain.entities.PersonRace;
import gov.va.vba.framework.domain.entities.PersonScrtyLevel;
import gov.va.vba.framework.domain.entities.PersonScrtyLog;
import gov.va.vba.framework.domain.entities.PersonSpeclStatus;
import gov.va.vba.framework.domain.entities.PstalType;
import gov.va.vba.framework.domain.entities.Ptcpnt;
import gov.va.vba.framework.domain.entities.PtcpntAddr;
import gov.va.vba.framework.domain.entities.PtcpntAlia;
import gov.va.vba.framework.domain.entities.PtcpntInstzn;
import gov.va.vba.framework.domain.entities.PtcpntPhone;
import gov.va.vba.framework.domain.entities.PtcpntRlnshp;
import gov.va.vba.framework.domain.entities.RatingAwardDetail;
import gov.va.vba.framework.domain.entities.RatingDecn;
import gov.va.vba.framework.domain.entities.Stn;
import gov.va.vba.framework.domain.entities.Trtmnt;
import gov.va.vba.framework.domain.entities.jaxb.AddressType;
import gov.va.vba.framework.domain.entities.jaxb.BirthInformationType;
import gov.va.vba.framework.domain.entities.jaxb.ChildType;
import gov.va.vba.framework.domain.entities.jaxb.ChildrenType;
import gov.va.vba.framework.domain.entities.jaxb.DeathInformationType;
import gov.va.vba.framework.domain.entities.jaxb.DisabilityType;
import gov.va.vba.framework.domain.entities.jaxb.EmailType;
import gov.va.vba.framework.domain.entities.jaxb.IdenitifiersType;
import gov.va.vba.framework.domain.entities.jaxb.IncomeType;
import gov.va.vba.framework.domain.entities.jaxb.MaritalStatusType;
import gov.va.vba.framework.domain.entities.jaxb.MarriedType;
import gov.va.vba.framework.domain.entities.jaxb.MilitaryServiceType;
import gov.va.vba.framework.domain.entities.jaxb.NameType;
import gov.va.vba.framework.domain.entities.jaxb.NursingHomeType;
import gov.va.vba.framework.domain.entities.jaxb.ObjectFactory;
import gov.va.vba.framework.domain.entities.jaxb.PhoneNumberType;
import gov.va.vba.framework.domain.entities.jaxb.PostalType;
import gov.va.vba.framework.domain.entities.jaxb.SpouseType;
import gov.va.vba.framework.domain.entities.jaxb.TreatmentType;
import gov.va.vba.framework.domain.entities.jaxb.VeteranDataType;
import gov.va.vba.framework.logging.Logger;
import gov.va.vba.framework.services.VeteranSessionLocalV2;
import gov.va.vba.framework.services.VeteranSessionV2;

import java.io.ByteArrayOutputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import weblogic.javaee.CallByReference;

@Stateless(name="VeteranSessionEJBV2Local")
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@Local(VeteranSessionLocalV2.class)
@CallByReference
public class VeteranSessionEJBV2Local implements VeteranSessionV2 {

	private static Logger log = Logger.getLogger("vetsnet.services");
	private static Logger logger = Logger.getLogger(VeteranSessionEJBV2Local.class);
	
	@PersistenceContext( name="corpDbAdmin", unitName="corpDbAdmin")
	protected EntityManager em;
	
	public VeteranSessionEJBV2Local() {
	}
	
	/**
	 * Locates Person object by participant id.
	 * @param Long participant id.
	 */
	public Person findPerson(Long partId, AuditContext auditContext, Map extensions) {
		Person person = new Person();
		String ssn = "";
		Query query = em.createNativeQuery("SELECT SSN_NBR FROM PERSON WHERE PTCPNT_ID = " +partId.toString());
		ssn = (String)query.getSingleResult();
		person = findPerson(ssn, auditContext, extensions);
		return person;
	}
	/**
	 * Locates Person object by social security number.
	 * @param String
	 */
	public Person findPerson(String ssn, AuditContext auditContext, Map extensions){
		Person person = new Person();
		Ptcpnt ptcpnt = new Ptcpnt();
		try{
			Query query = em.createNativeQuery("SELECT * from Person p " +
				"WHERE p.SSN_NBR = "+ ssn, "personMapping");
			person = (Person) query.getSingleResult();
			if(null != person){
				person = populateSets(person, auditContext, extensions);
				ptcpnt = person.getPtcpnt();
			}
		}catch(Exception e){
			logger.error("auditContext="+auditContext,e);
		}
		if(null != ptcpnt.getPtcpntId()){
			ptcpnt = populatePtcpntSets(ptcpnt, auditContext, extensions);
			person.setPtcpnt(ptcpnt);
		}
		return person;
	}
	
	public PtcpntAddr getMailingAddress(Set<PtcpntAddr> ptcpntAddrs, AuditContext auditContext, Map extensions) {
		ArrayList<PtcpntAddr> tempAddr = new ArrayList<PtcpntAddr>();
		PtcpntAddr tempPtcpntAddr = null;
		java.util.Date testDate = new java.util.Date();

		for (PtcpntAddr ptAddr : ptcpntAddrs) {
			if (null != ptAddr.getPtcpntAddrsTypeNm()
					&& ptAddr.getPtcpntAddrsTypeNm()
							.equalsIgnoreCase("Mailing")
					&& (null == ptAddr.getEndDt() || ptAddr.getEndDt().after(
							testDate))) {
				tempAddr.add(ptAddr);
			}
		}

		if (tempAddr.size() > 0) {

			if (null != tempAddr.get(0).getEfctvDt()) {
				testDate = tempAddr.get(0).getEfctvDt();
				tempPtcpntAddr = tempAddr.get(0);
			}

			for (PtcpntAddr ptAd : tempAddr) {
				if (null != testDate) {
					if (null != ptAd.getEfctvDt()
							&& ptAd.getEfctvDt().after(testDate)) {
						tempPtcpntAddr = ptAd;
					}
				}
			}
		}

		return tempPtcpntAddr;
	}

	public Person getSpouse(Person person, AuditContext auditContext, Map extensions){

		Ptcpnt ptcpnt = person.getPtcpnt();
		try {
			Set<PtcpntRlnshp> rlnshps = ptcpnt.getPtcpntRlnshps1();

			// Note: If there is more than 1 spouse, return the 1st spouse read.
			for (PtcpntRlnshp ptcpntRlnshp : rlnshps) {
				String name = ptcpntRlnshp.getCompId().getPtcpntRlnshpTypeNm();
				Date endDate = ptcpntRlnshp.getEndDt();

				if (name.equalsIgnoreCase("spouse") && endDate == null) {
					Person spouse = new Person();
					spouse = em.find(Person.class, ptcpntRlnshp.getPtcpnt1()
							.getPtcpntId());

					return spouse;
				}
			}
		} catch (NullPointerException ex) {

		}

		try {
			Set<PtcpntRlnshp> rlnshps = ptcpnt.getPtcpntRlnshps2();
			for (PtcpntRlnshp ptcpntRlnshp : rlnshps) {
				String name = ptcpntRlnshp.getCompId().getPtcpntRlnshpTypeNm();
				String code = ptcpntRlnshp.getStatusTypeCd();

				if (name.equalsIgnoreCase("spouse")
						&& code.equalsIgnoreCase("CURR")) {
					Person spouse = new Person();
					spouse = em.find(Person.class, ptcpntRlnshp.getPtcpnt1()
							.getPtcpntId());

					return spouse;
				}
			}
		} catch (NullPointerException ex) {

		}

		return null;
	}

	public boolean hasSpouse(Person person, AuditContext auditContext, Map extensions){
		return (getSpouse(person, auditContext, extensions) == null ? false : true);
	}

	/**
	 * Returns BnftClaims
	 * 
	 * @param person
	 * @return ArrayList
	 */
	public ArrayList<BnftClaim> populateBnftClaims(Person person, AuditContext auditContext, Map extensions) {
		ArrayList<BnftClaim> bnftClaims = new ArrayList<BnftClaim>();

		for (BnftClaim claim : person.getBnftClaims()) {
			bnftClaims.add(claim);
		}

		return bnftClaims;
	}

	/**
	 * Populates the HashSets in the MltyPerson object.
	 * 
	 * @param mltyPerson
	 * @return MltyPerson
	 */
	public MltyPerson populateMltyPerson(MltyPerson mltyPerson, AuditContext auditContext, Map extensions) {
		Set<MltyPersonBranch> mltyPersonBranches = mltyPerson
		.getMltyPersonBranches();
		Set<MltyPersonTour> mltyPersonTours = mltyPerson.getMltyPersonTours();
		Set<MltyTheatr> mltyTheatrs = mltyPerson.getMltyTheatrs();
		for (MltyPersonTour mltyPersonTour : mltyPersonTours) {
			Set<MltyTheatr> mts = mltyPersonTour.getMltyTheatrs();
		}

		return mltyPerson;
	}

	/**
	 * Populates the HashSets in the Participant object.
	 * 
	 * @param ptcpnt
	 * @return Participant
	 */
	public Ptcpnt populatePtcpntSets(Ptcpnt ptcpnt, AuditContext auditContext, Map extensions) {
		Set<PtcpntAddr> ptcpntAddrses = ptcpnt.getPtcpntAddrs();
		Set<PtcpntPhone> ptcpntPhones = ptcpnt.getPtcpntPhones();
		Set<PtcpntRlnshp> ptcpntRlnshps1 = ptcpnt.getPtcpntRlnshps1();
		Set<PtcpntRlnshp> ptcpntRlnshps2 = ptcpnt.getPtcpntRlnshps2();
		ArrayList<BurialSumryDecn> burialSummary = new ArrayList<BurialSumryDecn>();
		Set<PtcpntAlia> ptcpntAliases = ptcpnt.getPtcpntAlias();

//		for (PtcpntAddr ptcpntAddr : ptcpntAddrses) {
//			PstalType pt = new PstalType();
//			CntryType ct = new CntryType();
//			 pt = ptcpntAddr.getPostalType();
//			 ct = ptcpntAddr.getCntryType();
//		}

		for (PtcpntRlnshp ptcpntRelationship : ptcpntRlnshps1) {
			Ptcpnt part = ptcpntRelationship.getPtcpnt1();
			Ptcpnt part2 = ptcpntRelationship.getPtcpnt2();
		}
		for (PtcpntRlnshp ptcpntRelationship : ptcpntRlnshps2) {
			Ptcpnt part = ptcpntRelationship.getPtcpnt1();
			Ptcpnt part2 = ptcpntRelationship.getPtcpnt2();
		}

		return ptcpnt;
	}

	public Set<IncomeDecn> getIncomeDecisions(Person person, AuditContext auditContext, Map extensions) {
		Ptcpnt ptcpnt = person.getPtcpnt();

		if (ptcpnt == null)
			return new HashSet<IncomeDecn>();

		return ptcpnt.getIncomeDecns();
	}

	/**
	 * Populates the HashSets in the Person object.
	 * 
	 * @param person
	 * @return Person
	 */
	public Person populateSets(Person person, AuditContext auditContext, Map extensions) {
		Set<BnftClaim> bcs = person.getBnftClaims();
		Set<PersonApplcnPrfrnc> paps = person.getPersonApplcnPrfrncs();
		Set<PersonDep> pds = person.getPersonDeps();
		Set<PersonEduLevel> pels = person.getPersonEduLevels();
		Set<PersonEmplyr> pemps = person.getPersonEmplyrs();
		Set<PersonIdfctnHist> pihs = person.getPersonIdfctnHists();
		Set<PtcpntInstzn> pinstns = person.getPtcpntInstzns();
		Set<MltyPerson> mltps = person.getMltyPersons();
		Set<PersonNetWorth> pnws = person.getPersonNetWorths();
		Set<PersonRace> prs = person.getPersonRaces();
		Set<PersonScrtyLevel> psls = person.getPersonScrtyLevels();
		Set<PersonScrtyLog> pslos = person.getPersonScrtyLogs();
		Set<PersonSpeclStatus> psts = person.getPersonSpeclStatuses();
		Set<RatingAwardDetail> radts1 = person.getRatingAwardDetails1();
		Set<RatingAwardDetail> radts2 = person.getRatingAwardDetails2();
		Set<RatingDecn> rads = person.getRatingDecns();
		Set<CpmrStatclSgmnt> cpmrStatclSgmnts = new HashSet<CpmrStatclSgmnt>();
		Set<ExtendStatcl> extendStatcls = new HashSet<ExtendStatcl>();
		Set<CpmrChildBirthSgmnt> cpmrChildBirthSgmnts = new HashSet<CpmrChildBirthSgmnt>();
		Set<Ch31IntrfcGedDgnstc> ch31IntrfcGedDgnstcs = new HashSet<Ch31IntrfcGedDgnstc>();
		PstalType postalType = person.getPostalType();

		if (null != pinstns) {
			logger.debug("not null");
			for (PtcpntInstzn institution : pinstns) {
				String type = institution.getInstznTypeCd();
				if (type.equals("INCPD")) {

				}
			}
		}

		if (null != person.getPtcpntId() && person.getPtcpntId() > 0) {
			String ptcpntId = person.getPtcpntId().toString();
			for (RatingDecn rad : rads) {
				Dsblty ds = rad.getDsblty();
				DsbltyEvaltn de = rad.getDsbltyEvaltn();
			}
		}
		return person;
	}
	
	/**
	 * Method to convert date to an XMLGreorianCalendar.
	 * 
	 * @param date
	 * @return
	 */
	private XMLGregorianCalendar Date2XMLDate(Date date, AuditContext auditContext) {
		XMLGregorianCalendar calendar = null;
		if (null != date) {
			try {
				Calendar c = new GregorianCalendar();
				c.setTime(date);
				int month = c.get(Calendar.MONTH) + 1;
				int day = c.get(Calendar.DAY_OF_MONTH);
				int year = c.get(Calendar.YEAR);
				DatatypeFactory df = DatatypeFactory.newInstance();
				calendar = df.newXMLGregorianCalendarDate(year, month, day, c
						.getTimeZone().getOffset(date.getTime())
						/ (60 * 60 * 1000));
			} catch (DatatypeConfigurationException dcex) {
				logger.error("auditContext="+auditContext,dcex);
			}
		}
		return calendar;
	}

	/**
	 * Method to generate XML stream.
	 * 
	 * @param rootNode
	 * @return XML stream or empty stream.
	 */
	protected ByteArrayOutputStream generateXML(VeteranDataType rootNode, AuditContext auditContext) {
		try {
			JAXBContext jaxb = JAXBContext
					.newInstance("gov.va.vba.framework.domain.entities.jaxb");
			Marshaller marshaller = jaxb.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
					Boolean.TRUE);

			ByteArrayOutputStream os = new ByteArrayOutputStream();
			JAXBElement<VeteranDataType> element = (new ObjectFactory())
					.createGetVeteranDataResponse(rootNode);
			marshaller.marshal(element, os);
			return os;
		} catch (JAXBException ex) {
			logger.error("auditContext="+auditContext,ex);
		}

		return new ByteArrayOutputStream();
	}
	/**
	 * Method to get veteran data for a given social security number.
	 * 
	 * @param ssn
	 *            Veteran social security number
	 * @return Veteran data as a XML string.
	 */
	public String getCorpXMLDataStream(String ssn, AuditContext auditContext, Map extensions) {
		log.info("Getting veteran information from corp.");

		try {
			Person person = findPerson(ssn, auditContext, extensions);

			if (person != null) {
				VeteranDataType veteranNode = setRootNode(person, auditContext, extensions);
				ByteArrayOutputStream os = generateXML(veteranNode, auditContext);

				logger.debug(os);
				return os.toString();
			}
		} catch (Exception ex) {
			logger.error("auditContext="+auditContext,ex);
		}
		return new String();
	}

	/**
	 * Method to set the JAXB Address node.
	 * 
	 * @param person
	 * @param rootNode
	 */
	private void setAddressNode(Person person, VeteranDataType rootNode, AuditContext auditContext, Map extensions) {

		Ptcpnt ptcpnt = person.getPtcpnt();
		if (ptcpnt != null) {
			Set<PtcpntAddr> addresses = ptcpnt.getPtcpntAddrs();
			if (addresses.isEmpty())
				return;

			PostalType postalNode = new PostalType();

			PtcpntAddr mailingAddress = getMailingAddress(addresses, auditContext, extensions);
			if (mailingAddress != null) {
				AddressType addressNode = new AddressType();

				if (null != mailingAddress.getTrsuryAddrsOneTxt()) {
					postalNode.setAddressOne(mailingAddress
							.getTrsuryAddrsOneTxt());
				} else if (null != mailingAddress.getAddrsOneTxt()) {
					postalNode.setAddressOne(mailingAddress.getAddrsOneTxt());
				}
				if (null != mailingAddress.getTrsuryAddrsTwoTxt()) {
					postalNode.setAddressTwo(mailingAddress
							.getTrsuryAddrsTwoTxt());
				} else if (null != mailingAddress.getAddrsTwoTxt()) {
					postalNode.setAddressTwo(mailingAddress.getAddrsTwoTxt());
				}

				postalNode.setCityName(mailingAddress.getCityNm());
				postalNode.setZIPPrefix(mailingAddress.getZipPrefixNbr());
				postalNode.setZIPSuffix(mailingAddress.getZipFirstSuffixNbr());
				postalNode.setStatePostalCode(mailingAddress.getPostalType()
						.getPostalCd());

				addressNode.setPostal(postalNode);

				PhoneNumberType phoneNumberNode = setPhoneNumberType(person
						.getPtcpnt());
				if (phoneNumberNode != null)
					addressNode.setPhoneNumbers(phoneNumberNode);

				if (null != mailingAddress.getEmailAddrsTxt()) {
					EmailType email = new EmailType();
					addressNode.setEmail(email);
				}

				rootNode.setAddresses(addressNode);
			}
		}
	}

	/**
	 * 
	 * @param person
	 * @param rootNode
	 */
	private void setBirthInfoNode(Person person, VeteranDataType rootNode, AuditContext auditContext, Map extensions) {
		BirthInformationType birthInformationNode = null;

		if (null != person.getBrthdyDt()) {
			birthInformationNode = new BirthInformationType();

			birthInformationNode
					.setBirthDate(Date2XMLDate(person.getBrthdyDt(), auditContext));

			if (null != person.getBirthCityNm()) {
				birthInformationNode.setCityName(person.getBirthCityNm());
			}

			if (null != person.getPostalType()) {
				if (null != person.getPostalType().getStateInd()) {
					birthInformationNode.setStatePostalCode(person
							.getPostalType().getStateInd());
				}
			}

			if (null != person.getPtcpnt()) {
				if (null != person.getPtcpnt().getPtcpntAddrs()
						&& !person.getPtcpnt().getPtcpntAddrs().isEmpty()) {
					PtcpntAddr tempAddr = new PtcpntAddr();
					tempAddr = getMailingAddress(person.getPtcpnt()
							.getPtcpntAddrs(), auditContext, extensions);
					if (null != tempAddr.getCntryType()) {
						if (null != tempAddr.getCntryType().getCntryTypeCd()) {
							birthInformationNode.setCountryCode(tempAddr
									.getCntryType().getCntryTypeCd());
						}
					}
				}
			}

			rootNode.setBirthInformation(birthInformationNode);
		}
	}

	/**
	 * 
	 * @param ptcpntRlnshp
	 * @return
	 */
	private ChildType setChildNode(PtcpntRlnshp ptcpntRlnshp, AuditContext auditContext, Map extensions) {
		ChildType childNode = new ChildType();
		NameType childNameNode = new NameType();

		Person tempPerson = em.find(Person.class, ptcpntRlnshp.getCompId()
				.getPtcpntIdB());

		if (null != tempPerson.getFirstNm()) {
			childNameNode.setFirst(tempPerson.getFirstNm());
		}
		if (null != tempPerson.getMiddleNm()) {
			childNameNode.setMiddle(tempPerson.getMiddleNm());
		}
		if (null != tempPerson.getLastNm()) {
			childNameNode.setLast(tempPerson.getLastNm());
		}
		if (null != childNameNode) {
			childNode.setChildName(childNameNode);
		}
		if (null != tempPerson.getBrthdyDt()) {
			childNode.setBirthDate(Date2XMLDate(tempPerson.getBrthdyDt(), auditContext));
		}
		if (null != ptcpntRlnshp.getProofDepncyInd()
				&& !ptcpntRlnshp.getProofDepncyInd().isEmpty()) {
			childNode.setDependencyStatus(ptcpntRlnshp.getProofDepncyInd());
		}
		if (null != tempPerson.getGenderCd()
				&& !tempPerson.getGenderCd().isEmpty()) {
			childNode.setGender(tempPerson.getGenderCd());
		}
		if (null != tempPerson.getPostalType()) {
			PostalType postal = new PostalType();
			if (null != tempPerson.getPtcpnt()) {
				if (null != tempPerson.getPtcpnt().getPtcpntAddrs()
						&& !tempPerson.getPtcpnt().getPtcpntAddrs().isEmpty()) {
					PtcpntAddr ptcpntAddr = getMailingAddress(tempPerson.getPtcpnt()
									.getPtcpntAddrs(), auditContext, extensions);
					if (null != ptcpntAddr) {
						if (null != ptcpntAddr.getAddrsOneTxt()
								&& !ptcpntAddr.getAddrsOneTxt().isEmpty()) {
							postal.setAddressOne(ptcpntAddr.getAddrsOneTxt());
						}
						if (null != ptcpntAddr.getAddrsTwoTxt()
								&& !ptcpntAddr.getAddrsTwoTxt().isEmpty()) {
							postal.setAddressTwo(ptcpntAddr.getAddrsTwoTxt());
						}

						if (null != ptcpntAddr.getAddrsThreeTxt()
								&& !ptcpntAddr.getAddrsThreeTxt().isEmpty()) {
							postal.setAddressThree(ptcpntAddr
									.getAddrsThreeTxt());
						}
						if (null != ptcpntAddr.getCityNm()
								&& !ptcpntAddr.getCityNm().isEmpty()) {
							postal.setCityName(ptcpntAddr.getCityNm());
						}
						if (null != ptcpntAddr.getPostalType()
								&& !ptcpntAddr.getPostalType().getPostalCd()
										.isEmpty()) {
							postal.setStatePostalCode(ptcpntAddr
									.getPostalType().getPostalCd());
						}
						if (null != ptcpntAddr.getZipPrefixNbr()
								&& !ptcpntAddr.getZipPrefixNbr().isEmpty()) {
							postal.setZIPPrefix(ptcpntAddr.getZipPrefixNbr());
						}
						if (null != ptcpntAddr.getZipFirstSuffixNbr()
								&& !ptcpntAddr.getZipFirstSuffixNbr().isEmpty()) {
							postal.setZIPSuffix(ptcpntAddr
									.getZipFirstSuffixNbr());
						}
						if (null != postal) {
							childNode.setPostal(postal);
						}
					}
				}
			}
		}

		if (null != tempPerson.getSsnNbr() && !tempPerson.getSsnNbr().isEmpty()) {
			ChildType.SocialSecurityNum ssn = new ChildType.SocialSecurityNum();
			ssn.setValue(tempPerson.getSsnNbr());
			childNode.setSocialSecurityNum(ssn);
		}

		return childNode;
	}

	/**
	 * 
	 * @param person
	 * @param rootNode
	 */
	private void setChildNodes(Person person, VeteranDataType rootNode, AuditContext auditContext, Map extensions) {

		Ptcpnt ptcpnt = person.getPtcpnt();
		Set<PtcpntRlnshp> relationships = ptcpnt.getPtcpntRlnshps2();

		if (relationships == null)
			return;

		for (PtcpntRlnshp relationship : relationships) {
			if (relationship.getCompId().getPtcpntRlnshpTypeNm()
					.equalsIgnoreCase("Child")) {
				log.info("found child");
				ChildType child = setChildNode(relationship, auditContext, extensions);

				if (null != child) {
					ChildrenType children = new ChildrenType();
					children.setChild(child);
					rootNode.getChildren().add(children);
				}
			}
		}
	}

	/**
	 * 
	 * @param person
	 * @param rootNode
	 */
	private void setDeathInfoNode(Person person, VeteranDataType rootNode, AuditContext auditContext) {
		if (null != person.getDeathDt()) {
			DeathInformationType deathInformationNode = new DeathInformationType();
			deathInformationNode
					.setDeathDate(Date2XMLDate(person.getDeathDt(), auditContext));

			Ptcpnt ptcpnt = person.getPtcpnt();
			Set<Award> awards = ptcpnt.getAwards1();
			for (Award award : awards) {
				Set<BurialSumryDecn> decisions = award.getBurialSumryDecns();
				if (decisions != null) {
					for (BurialSumryDecn decision : decisions) {
						if (decision.getInvldDecnDt() == null) {
							deathInformationNode
									.setBurialDate(Date2XMLDate(decision
											.getFinalDisptnDt(), auditContext));
						}
					}
				}
			}
			rootNode.setDeathInformation(deathInformationNode);
		}
	}

	/**
	 * 
	 * @param ratingDecn
	 * @param rootNode
	 */
	private void setDisabilityInfoNode(RatingDecn ratingDecn,
			VeteranDataType rootNode, AuditContext auditContext) {

		// TODO fill out data

		DisabilityType disabilityNode = new DisabilityType();

		if (ratingDecn.getPerson().getPtcpntId() > 0) {
			ClaimDvlpmtCntct cdc = new ClaimDvlpmtCntct();
			cdc = em.find(ClaimDvlpmtCntct.class, ratingDecn.getPerson()
					.getPtcpntId());
			if (null != cdc) {
				if (null != cdc.getTrtmnts() && !cdc.getTrtmnts().isEmpty()) {
					for (Trtmnt trtmnt : cdc.getTrtmnts()) {
						TreatmentType treatmentNode = new TreatmentType();

						if (null != trtmnt.getBeginDt()) {
							treatmentNode.setTreatmentDate(Date2XMLDate(trtmnt
									.getBeginDt(), auditContext));
						}
						if (null != trtmnt.getClaimDvlpmtCntct().getFirstNm()) {
							StringBuffer name = new StringBuffer();
							name.append(trtmnt.getClaimDvlpmtCntct()
									.getFirstNm());
							if (null != trtmnt.getClaimDvlpmtCntct()
									.getMiddleNm()) {
								name.append(", ");
								name.append(trtmnt.getClaimDvlpmtCntct()
										.getMiddleNm());
							}
							if (null != trtmnt.getClaimDvlpmtCntct()
									.getLastNm()) {
								name.append(", ");
								name.append(trtmnt.getClaimDvlpmtCntct()
										.getLastNm());
							}
							treatmentNode.setTreatmentFacility(name.toString());
						} else if (null != cdc.getOrgNm()) {
							treatmentNode.setTreatmentFacility(cdc.getOrgNm());
						}
						disabilityNode.getTreatment().add(treatmentNode);
					}
				}
			}
		}

		if (null != ratingDecn.getDsblty()) {
			if (null != ratingDecn.getDsblty().getDsbltyDecnTypeCd()
					&& (ratingDecn.getDsblty().getDsbltyDecnTypeCd()
							.equalsIgnoreCase("SVCCONNCTED")
							|| ratingDecn.getDsblty().getDsbltyDecnTypeCd()
									.equalsIgnoreCase("DESSVCCON") || ratingDecn
							.getDsblty().getDsbltyDecnTypeCd()
							.equalsIgnoreCase("PEBSVCCON"))) {
				disabilityNode.setServiceConnectedDisability(ratingDecn
						.getDsblty().getDsbltyDecnTypeCd());
			}

		}

		DsbltyEvaltn evaluation = ratingDecn.getDsbltyEvaltn();
		if (null != evaluation) {
			disabilityNode.getDisabilityNature().add(evaluation.getDgnstcTxt());
		}

		rootNode.getDisability().add(disabilityNode);
	}

	/**
	 * Converts the gender string to the Veteran.Gender object
	 * 
	 * @param person
	 * @return Veteran.Gender
	 */
	private void setGenderNode(Person person, VeteranDataType rootNode) {
		String gender = person.getGenderCd();
		if (gender != null && gender.equals("") == false)
			rootNode.setGender(gender);
	}

	/**
	 * 
	 * @param person
	 * @param rootNode
	 */
	private void setIdentifiersNode(Person person, VeteranDataType rootNode) {
		IdenitifiersType identifierNode = new IdenitifiersType();
		NameType veteranNameNode = new NameType();

		veteranNameNode.setFirst(person.getFirstNm());
		veteranNameNode.setMiddle(person.getMiddleNm());
		veteranNameNode.setLast(person.getLastNm());
		identifierNode.setName(veteranNameNode);

		identifierNode.setFileNumber(person.getFileNbr());
		identifierNode.setSocialSecurityNum(person.getSsnNbr());

		Ptcpnt ptcpnt = person.getPtcpnt();
		if (null != ptcpnt) {
			Set<PtcpntAlia> aliases = ptcpnt.getPtcpntAlias();
			if (null != aliases) {
				for (PtcpntAlia alias : aliases) {
					NameType otherNameNode = new NameType();

					otherNameNode.setFirst(alias.getFirstNm());
					otherNameNode.setLast(alias.getLastNm());
					otherNameNode.setMiddle(alias.getMiddleNm());

					identifierNode.getOtherName().add(otherNameNode);
				}
			}
		}

		rootNode.setIdenitifiers(identifierNode);
	}

	/**
	 * 
	 * @param person
	 * @param rootNode
	 */
	private void setIncomeInfoNode(Person person, VeteranDataType rootNode, AuditContext auditContext, Map extensions) {
		Set<IncomeDecn> decisions = getIncomeDecisions(person, auditContext, extensions);

		SortedMap<Timestamp, IncomeDecn> beginDates = new TreeMap<Timestamp, IncomeDecn>();
		for (IncomeDecn decision : decisions) {
			if (decision.getInvldDecnDt() == null) {
				Award award = decision.getAward();
				if (award != null) {
					AwardPK awardPk = award.getCompId();
					String awardType = awardPk.getAwardTypeCd();
					if ("CPL".equals(awardType)) {
						log.info("Found income decisons.");
						beginDates.put(decision.getCompId().getBeginDt(),
								decision);
					}
				}
			}
		}

		if (beginDates.isEmpty())
			return;

		Timestamp key = beginDates.lastKey();
		IncomeDecn decision = beginDates.get(key);

		log.info(decision.toString());

		IncomeType incomeNode = new IncomeType();
		NetWorthDecn tempNetWorthDecn = new NetWorthDecn();
		NetWorthDecnPK tempNetWorthDecnPK = new NetWorthDecnPK();
		tempNetWorthDecn.setCompId(tempNetWorthDecnPK);

		if (null == decision.getEndDt()) {
			log.info("Decison end date is null.");
			incomeNode.setAnnualIncome(decision.getAnnualAmt());
			incomeNode.setMonthlyIncome(decision.getMthlyReprtdAmt());
		} else {
			// TODO: Add end date >= today processing.
			incomeNode.setAnnualIncome(decision.getAnnualAmt());
			incomeNode.setMonthlyIncome(decision.getMthlyReprtdAmt());
		}

		rootNode.setIncome(incomeNode);
	}

	/**
	 * 
	 * @param person
	 * @param rootNode
	 */
	private void setMaritalStatusNode(Person person, VeteranDataType rootNode, AuditContext auditContext, Map extensions) {
		if (hasSpouse(person, auditContext, extensions)) {
			String ssn = new String();

			MaritalStatusType maritalStatusNode = new MaritalStatusType();
			MarriedType marriedNode = new MarriedType();
			SpouseType spouseNode = new SpouseType();
			NameType spouseNameNode = new NameType();
			PostalType postalNode = new PostalType();

			Person spouse = getSpouse(person, auditContext, extensions);
			if (null != spouse) {
				if (null != spouse.getFirstNm()) {
					spouseNameNode.setFirst(spouse.getFirstNm());
				}
				if (null != spouse.getLastNm()) {
					spouseNameNode.setLast(spouse.getLastNm());
				}
				if (null != spouse.getMiddleNm()) {
					spouseNameNode.setMiddle(spouse.getMiddleNm());
				}
				if (null != spouseNameNode) {
					spouseNode.setSpouseName(spouseNameNode);
				}
				if (null != spouse.getSsnNbr() && !spouse.getSsnNbr().isEmpty()) {
					spouseNode.setSocialSecurityNum(ssn);
				}
				if (null != spouse.getBrthdyDt()) {
					spouseNode.setBirthDate(Date2XMLDate(spouse.getBrthdyDt(), auditContext));
				}
				if (null != spouse.getPtcpntId() && spouse.getPtcpntId() > 0) {
					spouseNode.setFileNumber(spouse.getPtcpntId().toString());
				}
				if (null != spouse.getPtcpnt()) {
					if (null != spouse.getPtcpnt().getPtcpntAddrs()
							&& !spouse.getPtcpnt().getPtcpntAddrs().isEmpty()) {
						PtcpntAddr ptcpntAddr = getMailingAddress(spouse.getPtcpnt()
										.getPtcpntAddrs(), auditContext, extensions);
						if (null != ptcpntAddr.getAddrsOneTxt()
								&& !ptcpntAddr.getAddrsOneTxt().isEmpty()) {
							postalNode.setAddressOne(ptcpntAddr
									.getAddrsOneTxt());
						}
						if (null != ptcpntAddr.getAddrsTwoTxt()
								&& !ptcpntAddr.getAddrsTwoTxt().isEmpty()) {
							postalNode.setAddressTwo(ptcpntAddr
									.getAddrsTwoTxt());
						}

						if (null != ptcpntAddr.getAddrsThreeTxt()
								&& !ptcpntAddr.getAddrsThreeTxt().isEmpty()) {
							postalNode.setAddressThree(ptcpntAddr
									.getAddrsThreeTxt());
						}
						if (null != ptcpntAddr.getCityNm()
								&& !ptcpntAddr.getCityNm().isEmpty()) {
							postalNode.setCityName(ptcpntAddr.getCityNm());
						}
						if (null != ptcpntAddr.getPostalType()
								&& !ptcpntAddr.getPostalType().getPostalCd()
										.isEmpty()) {
							postalNode.setStatePostalCode(ptcpntAddr
									.getPostalType().getPostalCd());
						}
						if (null != ptcpntAddr.getZipPrefixNbr()
								&& !ptcpntAddr.getZipPrefixNbr().isEmpty()) {
							postalNode.setZIPPrefix(ptcpntAddr
									.getZipPrefixNbr());
						}
						if (null != ptcpntAddr.getZipFirstSuffixNbr()
								&& !ptcpntAddr.getZipFirstSuffixNbr().isEmpty()) {
							postalNode.setZIPSuffix(ptcpntAddr
									.getZipFirstSuffixNbr());
						}
						spouseNode.setPostal(postalNode);
					}
				}
			}
			if (null != spouseNode) {
				marriedNode.setSpouse(spouseNode);
			}
			if (null != marriedNode) {
				maritalStatusNode.setMarried(marriedNode);
			}
			rootNode.setMaritalStatus(maritalStatusNode);
		}
	}

	/**
	 * 
	 * @param person
	 * @param rootNode
	 */
	private void setMilitaryServiceNode(Person person, VeteranDataType rootNode, AuditContext auditContext) {
		MilitaryServiceType militaryServiceNode = null;
		MilitaryServiceType.ServiceBranchName serviceBranchNameNode = new MilitaryServiceType.ServiceBranchName();
		MilitaryServiceType.MilitaryServiceNumber militaryServiceNumberNode = new MilitaryServiceType.MilitaryServiceNumber();

		Set<MltyPerson> militaryPersons = person.getMltyPersons();
		if (null != militaryPersons) {
			for (MltyPerson mltyPerson : militaryPersons) {
				if (null != mltyPerson.getMltyTheatrs()) {
					militaryServiceNode = new MilitaryServiceType();
					rootNode.setMilitaryService(militaryServiceNode);

					for (MltyPersonTour tour : mltyPerson.getMltyPersonTours()) {

						String branchInd = tour.getMltyBranchInd();
						if (branchInd != null) {
							serviceBranchNameNode.setValue(branchInd);
							militaryServiceNode
									.setServiceBranchName(serviceBranchNameNode);
						}

						String serviceNbr = tour.getSvcNbr();
						if (serviceNbr != null) {
							militaryServiceNumberNode.setValue(serviceNbr);
							militaryServiceNode
									.setMilitaryServiceNumber(militaryServiceNumberNode);
						}

						militaryServiceNode.setSeparatedFromService(tour
								.getMltySeprtnReasonTypeNm());

						militaryServiceNode.setPayGradeTypeName(tour
								.getDschrgPayGradeNm());

						militaryServiceNode.setTypeOfSeparationOrDischarge(tour
								.getMltySeprtnNarrtvTypeCd());

						if (null != tour.getMltyTheatrs()) {
							for (MltyTheatr mltyTheat : tour.getMltyTheatrs()) {
								militaryServiceNode.getMilitaryTheatersServed()
										.add(mltyTheat.getMltyTheatrTypeNm());
							}
						}

						militaryServiceNode
								.setDateEnteredActiveDuty(Date2XMLDate(tour
										.getEodDt(), auditContext));

						if (null != mltyPerson.getMltyPersonBranches()
								&& !mltyPerson.getMltyPersonBranches()
										.isEmpty()) {
							for (MltyPersonBranch branch : mltyPerson
									.getMltyPersonBranches()) {

								militaryServiceNode
										.setServiceBranchName(serviceBranchNameNode);

								militaryServiceNumberNode.setValue(branch
										.getSvcNbr());
								militaryServiceNode
										.setMilitaryServiceNumber(militaryServiceNumberNode);
							}
						}
					}
				}
			}
		}

		if (null != person.getBnftClaims() && !person.getBnftClaims().isEmpty()) {
			MilitaryServiceType.VAOfficeRecordsLocation vaOfficeRecordsLocationNode = null;

			// TODO: Fix. This will return the last record.
			for (BnftClaim bnftClaim : person.getBnftClaims()) {
				vaOfficeRecordsLocationNode = new MilitaryServiceType.VAOfficeRecordsLocation();

				Stn station1 = bnftClaim.getStn1();
				Stn station2 = bnftClaim.getStn2();

				if (null != station1) {
					vaOfficeRecordsLocationNode.setValue(station1.getNm());
				}

				if (null != station2) {
					vaOfficeRecordsLocationNode.setValue(station2.getNm());
				}
			}

			militaryServiceNode
					.setVAOfficeRecordsLocation(vaOfficeRecordsLocationNode);
		}
	}

	/**
	 * 
	 * @param person
	 * @param rootNode
	 */
	private void setNursingHomeInfoNode(Person person, VeteranDataType rootNode, AuditContext auditContext) {
		NursingHomeType nursingHome = new NursingHomeType();
		Set<PtcpntInstzn> collection = new HashSet<PtcpntInstzn>();

		for (PtcpntInstzn institution : person.getPtcpntInstzns()) {
			String type = institution.getInstznTypeCd();

			if (null != type && type.equals("INCPD") == false) {
				Date now = new Date();
				Timestamp beginDate = institution.getBeginDt();
				Timestamp endDate = institution.getEndDt();

				if (beginDate == null || beginDate.compareTo(now) <= 0) {
					if (endDate == null || endDate.compareTo(now) >= 0) {
						logger.debug("not null");
						collection.add(institution);
					}
				}
			}
		}

		for (PtcpntInstzn institution : collection) {
			nursingHome.setDateEntered(Date2XMLDate(institution.getBeginDt(), auditContext));
			nursingHome.setCurrentPatient(institution.getInstznTypeCd());
			nursingHome.setNursingHomeName(institution.getInstznNm());
		}

		if (collection.size() > 0) {
			rootNode.setNursingHome(nursingHome);
		}
	}

	/**
	 * 
	 * @param ptcpnt
	 * @return
	 */
	private PhoneNumberType setPhoneNumberType(Ptcpnt ptcpnt) {
		PhoneNumberType phoneNumberNode = null;

		Set<PtcpntPhone> phoneNumbers = ptcpnt.getPtcpntPhones();
		if (null == phoneNumbers)
			return null;

		// TODO: Return last phone number????
		for (PtcpntPhone phoneNumber : phoneNumbers) {
			phoneNumberNode = new PhoneNumberType();
			String type = new String(phoneNumber.getCompId().getPhoneTypeNm());

			if (type.equalsIgnoreCase("International")) {
				phoneNumberNode.setEveningPhoneNumber(new Long(phoneNumber
						.getPhoneNbr()).toString());
			}

			if (type.equalsIgnoreCase("Fax")) {
				phoneNumberNode.setEveningPhoneNumber(new Long(phoneNumber
						.getPhoneNbr()).toString());
			}

			if (type.equalsIgnoreCase("Pager")) {
				phoneNumberNode.setDayPhoneNumber(new Long(phoneNumber
						.getPhoneNbr()).toString());
			}

			if (type.equalsIgnoreCase("Cellular")) {
				phoneNumberNode.setDayPhoneNumber(new Long(phoneNumber
						.getPhoneNbr()).toString());
				phoneNumberNode.setEveningPhoneNumber(new Long(phoneNumber
						.getPhoneNbr()).toString());
			}

			if (type.equalsIgnoreCase("Daytime")) {
				phoneNumberNode.setDayPhoneNumber(new Long(phoneNumber
						.getPhoneNbr()).toString());
			}

			if (type.equalsIgnoreCase("Nighttime")) {
				phoneNumberNode.setEveningPhoneNumber(new Long(phoneNumber
						.getPhoneNbr()).toString());
			}
		}

		return phoneNumberNode;
	}

	/**
	 * 
	 * @param person
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private VeteranDataType setRootNode(Person person, AuditContext auditContext, Map extensions){
		VeteranDataType veteranNode = new VeteranDataType();

		if (null != person) {
			setIdentifiersNode(person, veteranNode);
			setAddressNode(person, veteranNode, auditContext, extensions);

			setGenderNode(person, veteranNode);
			setMilitaryServiceNode(person, veteranNode, auditContext);
			setBirthInfoNode(person, veteranNode, auditContext, extensions);
			setNursingHomeInfoNode(person, veteranNode, auditContext);
			setMaritalStatusNode(person, veteranNode, auditContext, extensions);
			setDeathInfoNode(person, veteranNode, auditContext);
			setIncomeInfoNode(person, veteranNode, auditContext, extensions);
			setChildNodes(person, veteranNode, auditContext, extensions);
		}

		if (null != person.getRatingDecns()
				&& !person.getRatingDecns().isEmpty()) {
			log.debug("Processing rating decisions.");

			for (RatingDecn ratingDecn : person.getRatingDecns()) {
				if (null != ratingDecn.getDsbltyEvaltn()) {
					setDisabilityInfoNode(ratingDecn, veteranNode, auditContext);
				}
			}
		}

		return veteranNode;
	}
}
