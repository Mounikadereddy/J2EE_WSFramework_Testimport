package gov.va.vba.framework.services;

import gov.va.vba.framework.domain.entities.jaxb.ro.ROMappingProfileType;

public interface ROProfileSession {
	
	ROMappingProfileType getROCityCntyStateType(String pZip) throws ROMappingException;
	String getROCityCntyStateXml(String pZip) throws ROMappingException;
	ROMappingProfileType getROForeignCntriesType(String pLob) throws ROMappingException;
	String getROForeignCntriesXml(String pLob) throws ROMappingException;
	ROMappingProfileType getROZipCodesType(String roNum, String lob) throws ROMappingException;
	String getROZipCodesXml(String roNum, String lob) throws ROMappingException;
	ROMappingProfileType getRORegionStateType(String region, String lob) throws ROMappingException;
	String getRORegionStateXml(String region, String lob) throws ROMappingException;
	ROMappingProfileType getROType(String pZip, String pCountryName, String provinceName, String pLob) throws ROMappingException;
	String getROXml(String pZip, String pCountryName, String provinceName, String pLob) throws ROMappingException;
}
