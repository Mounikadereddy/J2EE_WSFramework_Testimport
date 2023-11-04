package gov.va.vba.framework.services;

import gov.va.vba.framework.common.AuditContext;
import gov.va.vba.framework.domain.entities.jaxb.ro.ROMappingProfileType;

import java.util.Map;
/**
 * <p>
 * A SB to perform queries on CORP for the VONAPP application by calls
 * to four underlying stored functions in the ro_routing package. After that the 
 * returns are bound to jaxb objects as described in the ROMappingProfile schema. This
 * SB is wrapped by the DataAccessService web service.
 * </p>
 * 
 * @author Joshua Glickman
 * @since December 9, 2010
 * @Todo Complete error handling to be more thorough and efficient
 * @version 2
 */
@SuppressWarnings("rawtypes")
public interface ROProfileSessionV2 {
	
	/**
	 * <p>
	 * Provide unmarshalled ROMappingProfileType for city/county/state data
	 * queried from ro_routing.GETCITYCNTYSTATE stored function in CORP.
	 * </p>
	 * 
	 * @return ROMappingProfileType Schema type defined in ROMappingProfile schema.
	 * @param pZip Zip code input for query to the underlying database function.
	 * @param auditContext
	 * @param extensions
	 * @throws ROMappingException
	 * @since	Dec 9, 2010
	 */
	ROMappingProfileType getROCityCntyStateType(String pZip, AuditContext auditContext, Map extensions) throws ROMappingException;
	/**
	 * <p>
	 * Provide marshalled XML String representation of 
	 * ROMappingProfileType for city/county/state data queried from 
	 * ro_routing.GETCITYCNTYSTATE stored function in CORP.
	 * </p>
	 * 
	 * @return String representation of marshalled ROMappingProfileType.
	 * @param pZip Zip code input for query to the underlying database function.
	 * @param auditContext
	 * @param extensions
	 * @throws ROMappingException
	 * @since	Dec 9, 2010
	 */
	String getROCityCntyStateXml(String pZip, AuditContext auditContext, Map extensions) throws ROMappingException;
	
	/**
	 * <p>
	 * Provide unmarshalled ROMappingProfileType for foreign countries data queried from 
	 * ro_routing.GETFOREIGNCNTRIES stored function in CORP.
	 * </p>
	 * 
	 * @return ROMappingProfileType Schema type defined in ROMappingProfile schema.
	 * @param pLob LOB input for query to the underlying database function.
	 * @param auditContext
	 * @param extensions
	 * @throws ROMappingException
	 * @since	Dec 9, 2010
	 */
	ROMappingProfileType getROForeignCntriesType(String pLob, AuditContext auditContext, Map extensions) throws ROMappingException;
	/**
	 * <p>
	 * Provide marshalled XML String representation of ROMappingProfileType for RO Foreign
	 * Countries data queried from ro_routing.GETFOREIGNCNTRIES stored function in CORP.
	 * </p>
	 * 
	 * @return String representation of marshalled ROMappingProfileType.
	 * @param pLob LOB input for query to the underlying database function.
	 * @param auditContext
	 * @param extensions
	 * @throws ROMappingException
	 * @since	Dec 9, 2010
	 */
	String getROForeignCntriesXml(String pLob, AuditContext auditContext, Map extensions) throws ROMappingException;
	/**
	 * <p>
	 * Provide unmarshalled ROMappingProfileType for RO zip codes data queried from 
	 * ro_routing.GETZIPCODES stored function in CORP.
	 * </p>
	 * 
	 * @return ROMappingProfileType Schema type defined in ROMappingProfile schema.
	 * @param roNum RO number input for query to the underlying database function.
	 * @param lob LOB input for query to the underlying database function.
	 * @param auditContext
	 * @param extensions
	 * @throws ROMappingException
	 * @since	Dec 9, 2010
	 */
	ROMappingProfileType getROZipCodesType(String roNum, String lob, AuditContext auditContext, Map extensions) throws ROMappingException;
	/**
	 * <p>
	 * Provide marshalled XML String representation of ROMappingProfileType for RO
	 * Zip Codes data queried from ro_routing.GETZIPCODES stored function in CORP.
	 * </p>
	 * 
	 * @return String representation of marshalled ROMappingProfileType.
	 * @param roNum RO number input for query to the underlying database function.
	 * @param lob LOB input for query to the underlying database function.
	 * @param auditContext
	 * @param extensions
	 * @throws ROMappingException
	 * @since	Dec 9, 2010
	 */
	String getROZipCodesXml(String roNum, String lob, AuditContext auditContext, Map extensions) throws ROMappingException;
	/**
	 * <p>
	 * Provide unmarshalled ROMappingProfileType for RO Region and State data queried from 
	 * ro_routing.GETREGION_STATERO stored function in CORP.
	 * </p>
	 * 
	 * @return ROMappingProfileType Schema type defined in ROMappingProfile schema.
	 * @param region Region input for query to the underlying database function. Method expects one of the 
	 * following regions--WESTERN, CENTRAL, EASTERN, SOUTHERN.
	 * @param lob LOB input for query to the underlying database function. Method expects one of the following
	 * LOBs--CP, EDU, VRE.
	 * @param auditContext
	 * @param extensions
	 * @throws ROMappingException
	 * @since	Dec 9, 2010
	 */
	ROMappingProfileType getRORegionStateType(String region, String lob, AuditContext auditContext, Map extensions) throws ROMappingException;
	/**
	 * <p>
	 * Provide marshalled XML String representation of ROMappingProfileType for RO
	 * Region and State data queried from ro_routing.GETREGION_STATERO stored function in CORP.
	 * </p>
	 * 
	 * @return String representation of marshalled ROMappingProfileType.
	 * @param region Region input for query to the underlying database function.
	 * @param lob LOB input for query to the underlying database function.
	 * @param auditContext
	 * @param extensions
	 * @throws ROMappingException
	 * @since	Dec 9, 2010
	 */
	String getRORegionStateXml(String region, String lob, AuditContext auditContext, Map extensions) throws ROMappingException;
	/**
	 * <p>
	 * Provide unmarshalled ROMappingProfileType for RO data queried from 
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
	 * @param auditContext
	 * @param extensions
	 * @throws ROMappingException
	 * @since	Dec 9, 2010
	 */
	ROMappingProfileType getROType(String pZip, String pCountryName, String provinceName, String pLob, AuditContext auditContext, Map extensions) throws ROMappingException;
	/**
	 * <p>
	 * Provide marshalled XML String representation of ROMappingProfileType for RO
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
	 * @param auditContext
	 * @param extensions
	 * @throws ROMappingException
	 * @since	Dec 9, 2010
	 */
	String getROXml(String pZip, String pCountryName, String provinceName, String pLob, AuditContext auditContext, Map extensions) throws ROMappingException;
}
