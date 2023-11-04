package gov.va.vba.framework.services;

/*
import gov.va.vba.framework.domain.entities.jaxb.CorpVeteranDataType;
import gov.va.vba.framework.domain.entities.jaxb.VadirVeteranDataType;
import gov.va.vba.framework.domain.entities.jaxb.ro.ROMappingProfileType;
*/

public interface TestWebService {
	
	String getROCityCntyStateXml(String pZip) throws Exception;
	//ROMappingProfileType getROCityCntyStateType(String pZip) throws Exception;
	String getROForeignCntriesXml(String pLob) throws Exception;
	//ROMappingProfileType getROForeignCntriesType(String pLob) throws Exception;
	String getROZipCodesXml(String roNum, String lob) throws Exception;
	//ROMappingProfileType getROZipCodesType(String roNum, String lob) throws Exception;
	String getRORegionStateXml(String region, String lob) throws Exception;
	//ROMappingProfileType getRORegionStateType(String region, String lob) throws Exception;	
	String getROXml(String pZip, String pCountryName, String provinceName, String pLob) throws Exception;
	//ROMappingProfileType getROType(String pZip, String pCountryName, String provinceName, String pLob) throws Exception;
	
	String getBirlsData(String userId, String stationId, String ipAddress, String data)throws Exception;
	String getVadirVeteranXml(String vonAppUser, String vonAppTerm, String vaIdIn)throws Exception;
	//VadirVeteranDataType getVadirVeteranType(String vonAppUser, String vonAppTerm, String vaIdIn)throws Exception;
	String getCorpVeteranXml(String ssn)throws Exception;
	//CorpVeteranDataType getCorpVeteranType(String ssn) throws Exception;
}