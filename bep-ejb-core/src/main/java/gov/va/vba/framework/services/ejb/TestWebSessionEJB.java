package gov.va.vba.framework.services.ejb;

import gov.va.vba.framework.services.TestWebService;
import gov.va.vba.framework.services.TuxedoService;
import gov.va.vba.framework.services.TuxedoServiceRemote;
import gov.va.vba.framework.services.VadirSessionRemote;
import gov.va.vba.framework.services.VeteranSessionRemote;
import gov.va.vba.framework.services.ROProfileSessionRemote;
/*
import gov.va.vba.framework.domain.entities.jaxb.CorpVeteranDataType;
import gov.va.vba.framework.domain.entities.jaxb.VadirVeteranDataType;
import gov.va.vba.framework.domain.entities.jaxb.ro.ROMappingProfileType;
*/
import java.util.Properties;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.naming.Context;
import javax.naming.InitialContext;

@WebService
@Stateless(name="TestWebSessionEJB")
public class TestWebSessionEJB implements TestWebService {
	
	/**
	 * <p>
	 * Web method to provide marshalled XML String representation of 
	 * ROMappingProfileType for city/county/state data queried from 
	 * ro_routing.GETCITYCNTYSTATE stored function in CORP.
	 * </p>
	 * 
	 * @return String representation of marshalled ROMappingProfileType.
	 * @param pZip Zip code input for query to the underlying database function.
	 */
	@WebMethod
	public String getROCityCntyStateXml(String pZip) throws Exception {
		String reader = null;
		Context initialContext = new InitialContext();
		ROProfileSessionRemote roProfileSession = (ROProfileSessionRemote) initialContext
				.lookup("ROProfileSessionEJB#" + ROProfileSessionRemote.class.getName());
		reader = roProfileSession.getROCityCntyStateXml(pZip);
		return reader;
	}
	
	/**
	 * <p>
	 * Web method to provide unmarshalled ROMappingProfileType for city/county/state data
	 * queried from ro_routing.GETCITYCNTYSTATE stored function in CORP.
	 * </p>
	 * 
	 * @return ROMappingProfileType Schema type defined in ROMappingProfile schema.
	 * @param pZip Zip code input for query to the underlying database function.
	 */
	/*@WebMethod
	public ROMappingProfileType getROCityCntyStateType(String pZip) throws Exception {
		Context initialContext = new InitialContext();
		ROProfileSessionRemote roProfileSession = (ROProfileSessionRemote) initialContext
				.lookup("ROProfileSessionEJB#" + ROProfileSessionRemote.class.getName());
		ROMappingProfileType roType = roProfileSession.getROCityCntyStateType(pZip);
		return roType;
	}*/
	
	/**
	 * <p>
	 * Web method to provide marshalled XML String representation of ROMappingProfileType for RO Foreign
	 * Countries data queried from ro_routing.GETFOREIGNCNTRIES stored function in CORP.
	 * </p>
	 * 
	 * @return String representation of marshalled ROMappingProfileType.
	 * @param pLob LOB input for query to the underlying database function.
	 */
	@WebMethod
	public String getROForeignCntriesXml(String pLob) throws Exception {
		String reader = null;
		Context initialContext = new InitialContext();
		ROProfileSessionRemote roProfileSession = (ROProfileSessionRemote) initialContext
				.lookup("ROProfileSessionEJB#" + ROProfileSessionRemote.class.getName());
		reader = roProfileSession.getROForeignCntriesXml(pLob);
		return reader;
	}
	
	/**
	 * <p>
	 * Web method to provide unmarshalled ROMappingProfileType for foreign countries data queried from 
	 * ro_routing.GETFOREIGNCNTRIES stored function in CORP.
	 * </p>
	 * 
	 * @return ROMappingProfileType Schema type defined in ROMappingProfile schema.
	 * @param pLob LOB input for query to the underlying database function.
	 */
	/*@WebMethod
	public ROMappingProfileType getROForeignCntriesType(String pLob) throws Exception {
		Context initialContext = new InitialContext();
		ROProfileSessionRemote roProfileSession = (ROProfileSessionRemote) initialContext
				.lookup("ROProfileSessionEJB#" + ROProfileSessionRemote.class.getName());
		ROMappingProfileType roType = roProfileSession.getROForeignCntriesType(pLob);
		return roType;
	}*/
	
	/**
	 * <p>
	 * Web method to provide marshalled XML String representation of ROMappingProfileType for RO
	 * Zip Codes data queried from ro_routing.GETZIPCODES stored function in CORP.
	 * </p>
	 * 
	 * @return String representation of marshalled ROMappingProfileType.
	 * @param roNum RO number input for query to the underlying database function.
	 * @param lob LOB input for query to the underlying database function.
	 */
	@WebMethod
	public String getROZipCodesXml(String roNum, String lob) throws Exception {
		String reader = null;
		Context initialContext = new InitialContext();
		ROProfileSessionRemote roProfileSession = (ROProfileSessionRemote) initialContext
				.lookup("ROProfileSessionEJB#" + ROProfileSessionRemote.class.getName());
		reader = roProfileSession.getROZipCodesXml(roNum, lob);
		return reader;
	}
	
	/**
	 * <p>
	 * Web method to provide unmarshalled ROMappingProfileType for RO zip codes data queried from 
	 * ro_routing.GETZIPCODES stored function in CORP.
	 * </p>
	 * 
	 * @return ROMappingProfileType Schema type defined in ROMappingProfile schema.
	 * @param roNum RO number input for query to the underlying database function.
	 * @param lob LOB input for query to the underlying database function.
	 */
	/*@WebMethod
	public ROMappingProfileType getROZipCodesType(String roNum, String lob) throws Exception {
		Context initialContext = new InitialContext();
		ROProfileSessionRemote roProfileSession = (ROProfileSessionRemote) initialContext
				.lookup("ROProfileSessionEJB#" + ROProfileSessionRemote.class.getName());
		ROMappingProfileType roType = roProfileSession.getROZipCodesType(roNum, lob);
		return roType;
	}*/
	
	/**
	 * <p>
	 * Web method to provide unmarshalled ROMappingProfileType for RO Region and State data queried from 
	 * ro_routing.GETREGION_STATERO stored function in CORP.
	 * </p>
	 * 
	 * @return ROMappingProfileType Schema type defined in ROMappingProfile schema.
	 * @param region Region input for query to the underlying database function. Method expects one of the 
	 * following regions--WESTERN, CENTRAL, EASTERN, SOUTHERN.
	 * @param lob LOB input for query to the underlying database function. Method expects one of the following
	 * LOBs--CP, EDU, VRE.
	 */
	/*@WebMethod
	public ROMappingProfileType getRORegionStateType(String region, String lob) throws Exception {
		Context initialContext = new InitialContext();
		ROProfileSessionRemote roProfileSession = (ROProfileSessionRemote) initialContext
				.lookup("ROProfileSessionEJB#" + ROProfileSessionRemote.class.getName());
		ROMappingProfileType roType = roProfileSession.getRORegionStateType(region, lob);
		return roType;
	}*/
	
	/**
	 * <p>
	 * Web method to provide marshalled XML String representation of ROMappingProfileType for RO
	 * Region and State data queried from ro_routing.GETREGION_STATERO stored function in CORP.
	 * </p>
	 * 
	 * @return String representation of marshalled ROMappingProfileType.
	 * @param region Region input for query to the underlying database function.
	 * @param lob LOB input for query to the underlying database function.
	 */
	@WebMethod
	public String getRORegionStateXml(String region, String lob) throws Exception {
		String reader = null;
		Context initialContext = new InitialContext();
		ROProfileSessionRemote roProfileSession = (ROProfileSessionRemote) initialContext
				.lookup("ROProfileSessionEJB#" + ROProfileSessionRemote.class.getName());
		reader = roProfileSession.getRORegionStateXml(region, lob);
		return reader;
	}
	
	/**
	 * <p>
	 * Web method to provide marshalled XML String representation of ROMappingProfileType for RO
	 * data queried from ro_routing.GETRO stored function in CORP.
	 * </p>
	 * 
	 * @return String representation of marshalled ROMappingProfileType.
	 * @param pZip Zip code input for query to the underlying database function. Do not submit a zipcode
	 * if countryName is outside the U.S.
	 * @param pCountryName Country name input for query to the underlying database function. If zipcode
	 * is passed to this method, this param defaults to USA.
	 * @param provinceName Name of province inside foreign countries. Do not provide a province name when
	 * cntryName is USA.
	 * @param pLob LOB input for query to the underlying database function.
	 */
	@WebMethod
	public String getROXml(String pZip, String pCountryName, String provinceName, String pLob) throws Exception {
		String reader = null;
		Context initialContext = new InitialContext();
		ROProfileSessionRemote roProfileSession = (ROProfileSessionRemote) initialContext
				.lookup("ROProfileSessionEJB#" + ROProfileSessionRemote.class.getName());
		reader = roProfileSession.getROXml(pZip, pCountryName, provinceName, pLob);
		return reader;
	}
	
	/**
	 * <p>
	 * Web method to provide unmarshalled ROMappingProfileType for RO data queried from 
	 * ro_routing.GETRO stored function in CORP.
	 * </p>
	 * 
	 * @return ROMappingProfileType Schema type defined in ROMappingProfile schema.
	 * @param pZip Zip code input for query to the underlying database function. Do not submit a zipcode
	 * if countryName is outside the U.S.
	 * @param pCountryName Country name input for query to the underlying database function. If zipcode
	 * is passed to this method, this param defaults to USA.
	 * @param provinceName Name of province inside foreign countries. Do not provide a province name when
	 * cntryName is USA.
	 * @param pLob LOB input for query to the underlying database function.
	 */
	/*@WebMethod
	public ROMappingProfileType getROType(String pZip, String pCountryName, String provinceName, String pLob) throws Exception {
		Context initialContext = new InitialContext();
		ROProfileSessionRemote roProfileSession = (ROProfileSessionRemote) initialContext
				.lookup("ROProfileSessionEJB#" + ROProfileSessionRemote.class.getName());
		ROMappingProfileType roType = roProfileSession.getROType(pZip, pCountryName, provinceName, pLob);
		return roType;
	}*/

	/**
	 * <p>
	 * Web method to provide BIRLS data String queried from stored functions in VADIR db.
	 * </p>
	 * 
	 * @return String return of BIRLS data.
	 * @param userId CSUM security user ID.
	 * @param stationId The station id you use to login to CSUM security.
	 * @param ipAdddress The IP address of your workstation.
	 * @param data The BIRLS file number for accessing a single person in BIRLS db.
	 */
	@WebMethod
	public String getBirlsData(String userId, String stationId, String ipAddress, String data) throws Exception {
		String result = "";
		Properties props = new Properties();
		props.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
		props.put(Context.PROVIDER_URL, "t3://vbaausappdev1.vba.va.gov,vbaausappdev2.vba.va.gov:9015");
		InitialContext _ctx = new InitialContext(props);		
		TuxedoService _tuxedoService = (TuxedoServiceRemote)_ctx.lookup("VbaTuxedoService#"+TuxedoServiceRemote.class.getName());

		result = _tuxedoService.getVeteranBIRLSData(userId, stationId, ipAddress, new StringBuilder("SHARI").append("BPNQ").append(data.trim()).toString());
		return result;
	}
	
	/**
	 * <p>
	 * Web method to provide marshalled XML String representation of VadirVeteranDataType queried from 
	 * VADIR db.
	 * </p>
	 * 
	 * @return String representation of marshalled VadirVeteranDataType defined in VADIR schema.
	 * @param vonAppUser VADIR user ID.
	 * @param vonAppTerm Your VADIR user password.
	 * @param vaIdIn The VADIR file number for accessing a single person in VADIR db.
	 */
	@WebMethod
	public String getVadirVeteranXml(String vonAppUser, String vonAppTerm, String vaIdIn) throws Exception {
		String reader = null;
		Context initialContext = new InitialContext();
		VadirSessionRemote vadirSession = (VadirSessionRemote) initialContext
				.lookup("VadirSessionEJB#" + VadirSessionRemote.class.getName());
		reader = vadirSession.getVadirData(vonAppUser, vonAppTerm, vaIdIn);
		return reader;
	}
	
	/**
	 * <p>
	 * Web method to provide unmarshalled VadirVeteranDataType queried from VADIR db.
	 * </p>
	 * 
	 * @return VadirVeteranDataType Schema type defined in VADIR schema.
	 * @param vonAppUser VADIR user ID.
	 * @param vonAppTerm Your VADIR user password.
	 * @param vaIdIn The VADIR file number for accessing a single person in VADIR db.
	 */
	/*@WebMethod
	public VadirVeteranDataType getVadirVeteranType(String vonAppUser, String vonAppTerm, String vaIdIn) throws Exception {
		Context initialContext = new InitialContext();
		VadirSessionRemote vadirSession = (VadirSessionRemote) initialContext
				.lookup("VadirSessionEJB#" + VadirSessionRemote.class.getName());
		VadirVeteranDataType vadir = vadirSession.getVadirVeteranDataType(vonAppUser, vonAppTerm, vaIdIn);
		return vadir;
	}*/
	
	//TO DO: These two are temporary..need to decide on one return value for the final WS
	/**
	 * <p>
	 * Web method to provide marshalled XML String representation of CorpVeteranDataType queried from 
	 * CORP db.
	 * </p>
	 * 
	 * @return String representation of marshalled CorpVeteranDataType defined in CORP schema.
	 * @param ssn SSN for accessing a single person in CORP db.
	 */
	@WebMethod
	public String getCorpVeteranXml(String ssn)throws Exception{
		String veteranString;
		Context initialContext = new InitialContext();
		VeteranSessionRemote veteranSession = (VeteranSessionRemote) initialContext
				.lookup("VeteranSessionEJB#" + VeteranSessionRemote.class.getName());
		veteranString = veteranSession.getCorpXMLDataStream(ssn);
		return veteranString;
	}
	
	/**
	 * <p>
	 * Web method to provide unmarshalled CorpVeteranDataType queried from CORP db.
	 * </p>
	 * 
	 * @return CorpVeteranDataType Schema type defined in CORP schema.
	 * @param ssn SSN for accessing a single person in CORP db.
	 */
	/*@WebMethod
	public CorpVeteranDataType getCorpVeteranType(String ssn)throws Exception{
		Context initialContext = new InitialContext();
		VeteranSessionRemote veteranSession = (VeteranSessionRemote) initialContext
				.lookup("VeteranSessionEJB#" + VeteranSessionRemote.class.getName());
		CorpVeteranDataType corpVet = veteranSession.getCorpVeteranDataType(ssn);
		return corpVet;
	}*/
}