package gov.va.vba.framework.services.ejb;

import gov.va.vba.framework.auditing.LegacyEJBAuditer;
import gov.va.vba.framework.domain.entities.jaxb.ro.CityCntyState;
import gov.va.vba.framework.domain.entities.jaxb.ro.ForeignCntries;
import gov.va.vba.framework.domain.entities.jaxb.ro.ObjectFactory;
import gov.va.vba.framework.domain.entities.jaxb.ro.RO;
import gov.va.vba.framework.domain.entities.jaxb.ro.ROMappingProfileType;
import gov.va.vba.framework.domain.entities.jaxb.ro.RegionState;
import gov.va.vba.framework.domain.entities.jaxb.ro.ZipCodes;
import gov.va.vba.framework.logging.Logger;
import gov.va.vba.framework.services.ROMappingException;
import gov.va.vba.framework.services.ROMappingFault;
import gov.va.vba.framework.services.ROProfileSession;
import gov.va.vba.framework.services.ROProfileSessionLocal;
import gov.va.vba.framework.services.ROProfileSessionRemote;

import java.io.IOException;
import java.io.StringWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;

import oracle.jdbc.OracleTypes;
import weblogic.javaee.TransactionTimeoutSeconds;

/**
 * <p>
 * A SB to perform queries on CORP for the VONAPP application by calls
 * to four underlying stored fucntions in the ro_routing package. After that the 
 * returns are bound to jaxb objects as described in the ROMappingProfile schema. This
 * SB is wrapped by the DataAccessService web service.
 * </p>
 * 
 * @author Brodie Jensen</a>
 * @since June 19, 2010
 * @Todo Complete error handling to be more thorough and efficient
 */

@Stateless(mappedName="ROProfileSessionEJB")
@TransactionTimeoutSeconds(90)
@Local(ROProfileSessionLocal.class)
@Remote(ROProfileSessionRemote.class)
public class ROProfileSessionEJB implements ROProfileSession {
	
	static final String GET_CCS = "{call ?:= ro_routing.GETCITYCNTYSTATE(?)}";
	static final String GET_ROS = "{call ?:= ro_routing.GETRO(?,?,?,?)}";
	static final String GET_FCS = "{call ?:= ro_routing.GETFOREIGNCNTRIES(?)}";
	static final String GET_ZCS = "{call ?:= ro_routing.GETZIPCODES(?,?)}";
	static final String GET_RSRO = "{call ?:= ro_routing.GETREGION_STATERO(?,?)}";
	static final String DS_NAME = "jdbc/framework/corpDb/admin-ora";
	static final String JAXB_PKG = "gov.va.vba.framework.domain.entities.jaxb.ro";
	static final String RO_SQL_MSG = "SQLException...Please try your submission again. Error msg: ";
	static final String RO_GEN_MSG = "Exception...Please try your submission again or contact your administrator. Error msg: ";
	static ObjectFactory of = null;
	static CallableStatement cs = null;
	static ResultSet roResultSet = null;
	static ROMappingProfileType roDataType = null;
	static Connection con = null;
	private static Logger logger=Logger.getLogger(ROProfileSessionEJB.class);


	/**
	 * <p>
	 * Provide unmarshalled ROMappingProfileType for city/county/state data
	 * queried from ro_routing.GETCITYCNTYSTATE stored function in CORP.
	 * </p>
	 * 
	 * @return ROMappingProfileType Schema type defined in ROMappingProfile schema.
	 * @param pZip Zip code input for query to the underlying database function.
	 */
	public ROMappingProfileType getROCityCntyStateType(String pZip) throws ROMappingException {
		new LegacyEJBAuditer().audit(ROProfileSessionEJB.class.getName(), "getROCityCntyStateType");
		of = new ObjectFactory();
		roDataType = of.createROMappingProfileType();
		try{
			con = getROConnection();
			cs = con.prepareCall(GET_CCS);
			setParametersAndExecute(cs, pZip);
			roResultSet = (ResultSet)cs.getObject(1);
			populateCityCntyState(of, roResultSet, roDataType);
		} catch(SQLException sqlex) {
			logger.error("",sqlex);
			throwROMappingException(RO_SQL_MSG + sqlex.getLocalizedMessage());
		} catch(Exception e) {
			logger.error("",e);
			throwROMappingException(RO_GEN_MSG + e.getMessage());
		} finally {
			try {
				closeLocals(roResultSet, cs, con);
			} catch (SQLException se) {
				logger.error("",se);
			}
		}	
		return roDataType;
	}

	/**
	 * <p>
	 * Provide marshalled XML String representation of 
	 * ROMappingProfileType for city/county/state data queried from 
	 * ro_routing.GETCITYCNTYSTATE stored function in CORP.
	 * </p>
	 * 
	 * @return String representation of marshalled ROMappingProfileType.
	 * @param pZip Zip code input for query to the underlying database function.
	 */
	public String getROCityCntyStateXml(String pZip) throws ROMappingException {
		new LegacyEJBAuditer().audit(ROProfileSessionEJB.class.getName(), "getROCityCntyStateXml");
		String roXmlString = null;
		ROMappingProfileType ro = getROCityCntyStateType(pZip);
		try {
			roXmlString = convertTypeToXml(ro);
		} catch (JAXBException jxbe) {
			logger.error("",jxbe);
		} catch (IOException ioe) {
			logger.error("",ioe);
		}
		return roXmlString;
	}
	
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
	 */
	public ROMappingProfileType getROType(String pZip, String pCountryName, String provinceName, String pLob) throws ROMappingException {
		new LegacyEJBAuditer().audit(ROProfileSessionEJB.class.getName(), "getROType");
		of = new ObjectFactory();
		roDataType = of.createROMappingProfileType();
		try{
			con = getROConnection();
			cs = con.prepareCall(GET_ROS);
			setParametersAndExecute(cs, pZip, pCountryName, provinceName, pLob);
			roResultSet = (ResultSet)cs.getObject(1);
			populateRO(of, roResultSet, roDataType);
		} catch(SQLException sqlex) {
			logger.error("",sqlex);
			throwROMappingException(RO_SQL_MSG + sqlex.getLocalizedMessage());
		} catch(Exception e) {
			logger.error("",e);
			throwROMappingException(RO_GEN_MSG + e.getMessage());
		} finally {
			try {
				closeLocals(roResultSet, cs, con);
			} catch (SQLException se) {
				logger.error("",se);
			}
		}	
		return roDataType;
	}

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
	 */
	public String getROXml(String pZip, String pCountryName, String provinceName, String pLob) throws ROMappingException {
		new LegacyEJBAuditer().audit(ROProfileSessionEJB.class.getName(), "getROXml");
		String roXmlString = null;
		ROMappingProfileType ro = getROType(pZip, pCountryName, provinceName, pLob);
		try {
			roXmlString = convertTypeToXml(ro);
		} catch (JAXBException jxbe) {
			logger.error("",jxbe);
		} catch (IOException ioe) {
			logger.error("",ioe);
		}
		return roXmlString;
	}
	
	/**
	 * <p>
	 * Provide unmarshalled ROMappingProfileType for foreign countries data queried from 
	 * ro_routing.GETFOREIGNCNTRIES stored function in CORP.
	 * </p>
	 * 
	 * @return ROMappingProfileType Schema type defined in ROMappingProfile schema.
	 * @param pLob LOB input for query to the underlying database function.
	 */
	public ROMappingProfileType getROForeignCntriesType(String pLob) throws ROMappingException {
		new LegacyEJBAuditer().audit(ROProfileSessionEJB.class.getName(), "getROForeignCntriesType");
		of = new ObjectFactory();
		roDataType = of.createROMappingProfileType();
		try{
			con = getROConnection();
			cs = con.prepareCall(GET_FCS);
			setParametersAndExecute(cs, pLob);
			roResultSet = (ResultSet)cs.getObject(1);
			populateForeignCntries(of, roResultSet, roDataType);
		} catch(SQLException sqlex) {
			logger.error("",sqlex);
			throwROMappingException(RO_SQL_MSG + sqlex.getLocalizedMessage());
		} catch(Exception e) {
			logger.error("",e);
			throwROMappingException(RO_GEN_MSG + e.getMessage());
		} finally {
			try {
				closeLocals(roResultSet, cs, con);
			} catch (SQLException se) {
				logger.error("",se);
			}
		}	
		return roDataType;
	}

	/**
	 * <p>
	 * Provide marshalled XML String representation of ROMappingProfileType for RO Foreign
	 * Countries data queried from ro_routing.GETFOREIGNCNTRIES stored function in CORP.
	 * </p>
	 * 
	 * @return String representation of marshalled ROMappingProfileType.
	 * @param pLob LOB input for query to the underlying database function.
	 */
	public String getROForeignCntriesXml(String pLob) throws ROMappingException {
		new LegacyEJBAuditer().audit(ROProfileSessionEJB.class.getName(), "getROForeignCntriesXml");
		String roXmlString = null;
		ROMappingProfileType ro = getROForeignCntriesType(pLob);
		try {
			roXmlString = convertTypeToXml(ro);
		} catch (JAXBException jxbe) {
			logger.error("",jxbe);
		} catch (IOException ioe) {
			logger.error("",ioe);
		}
		return roXmlString;
	}
	
	/**
	 * <p>
	 * Provide unmarshalled ROMappingProfileType for RO zip codes data queried from 
	 * ro_routing.GETZIPCODES stored function in CORP.
	 * </p>
	 * 
	 * @return ROMappingProfileType Schema type defined in ROMappingProfile schema.
	 * @param roNum RO number input for query to the underlying database function.
	 * @param lob LOB input for query to the underlying database function.
	 */
	public ROMappingProfileType getROZipCodesType(String roNum, String lob) throws ROMappingException {
		new LegacyEJBAuditer().audit(ROProfileSessionEJB.class.getName(), "getROZipCodesType");
		of = new ObjectFactory();
		roDataType = of.createROMappingProfileType();
		try{
			con = getROConnection();
			cs = con.prepareCall(GET_ZCS);
			setParametersAndExecute(cs, roNum, lob);
			roResultSet = (ResultSet)cs.getObject(1);
			populateZipCodes(of, roResultSet, roDataType);
		} catch(SQLException sqlex) {
			logger.error("",sqlex);
			throwROMappingException(RO_SQL_MSG + sqlex.getLocalizedMessage());
		} catch(Exception e) {
			logger.error("",e);
			throwROMappingException(RO_GEN_MSG + e.getMessage());
		} finally {
			try {
				closeLocals(roResultSet, cs, con);
			} catch (SQLException se) {
				logger.error("",se);
			}
		}	
		return roDataType;
	}

	/**
	 * <p>
	 * Provide marshalled XML String representation of ROMappingProfileType for RO
	 * Zip Codes data queried from ro_routing.GETZIPCODES stored function in CORP.
	 * </p>
	 * 
	 * @return String representation of marshalled ROMappingProfileType.
	 * @param roNum RO number input for query to the underlying database function.
	 * @param lob LOB input for query to the underlying database function.
	 */
	public String getROZipCodesXml(String roNum, String lob) throws ROMappingException {
		new LegacyEJBAuditer().audit(ROProfileSessionEJB.class.getName(), "getROZipCodesXml");
		String roXmlString = null;
		ROMappingProfileType ro = getROZipCodesType(roNum, lob);
		try {
			roXmlString = convertTypeToXml(ro);
		} catch (JAXBException jxbe) {
			logger.error("",jxbe);
		} catch (IOException ioe) {
			logger.error("",ioe);
		}
		return roXmlString;
	}
	
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
	 */
	public ROMappingProfileType getRORegionStateType(String region, String lob) throws ROMappingException {
		new LegacyEJBAuditer().audit(ROProfileSessionEJB.class.getName(), "getRORegionStateType");
		of = new ObjectFactory();
		roDataType = of.createROMappingProfileType();
		try{
			con = getROConnection();
			cs = con.prepareCall(GET_RSRO);
			setParametersAndExecute(cs, region, lob);
			roResultSet = (ResultSet)cs.getObject(1);
			populateRegionState(of, roResultSet, roDataType);
		} catch(SQLException sqlex) {
			logger.error("",sqlex);
			throwROMappingException(RO_SQL_MSG + sqlex.getLocalizedMessage());
		} catch(Exception e) {
			logger.error("",e);
			throwROMappingException(RO_GEN_MSG + e.getMessage());
		} finally {
			try {
				closeLocals(roResultSet, cs, con);
			} catch (SQLException se) {
				logger.error("",se);
			}
		}	
		return roDataType;
	}

	/**
	 * <p>
	 * Provide marshalled XML String representation of ROMappingProfileType for RO
	 * Region and State data queried from ro_routing.GETREGION_STATERO stored function in CORP.
	 * </p>
	 * 
	 * @return String representation of marshalled ROMappingProfileType.
	 * @param region Region input for query to the underlying database function.
	 * @param lob LOB input for query to the underlying database function.
	 */
	public String getRORegionStateXml(String region, String lob) throws ROMappingException {
		new LegacyEJBAuditer().audit(ROProfileSessionEJB.class.getName(), "getRORegionStateXml");
		String roXmlString = null;
		ROMappingProfileType ro = getRORegionStateType(region, lob);
		try {
			roXmlString = convertTypeToXml(ro);
		} catch (JAXBException jxbe) {
			logger.error("",jxbe);
		} catch (IOException ioe) {
			logger.error("",ioe);
		}
		return roXmlString;
	}
	
	/**
	 * <p>
	 * Return a Connection object to CORP DS for local methods.
	 * </p>
	 * 
	 * @return Connection Connection object.
	 */
	private Connection getROConnection() {
		try{
			InitialContext ctx = new InitialContext();
			DataSource ds = (DataSource)ctx.lookup(DS_NAME);
			con = ds.getConnection();	
		} catch(Exception excp) {
			logger.error("Error: getROCOnnection Failed", excp);
		}
		return con;
	}
	
	/**
	 * <p>
	 * Populate a jaxb object for this type as described in ROMappingProfile schema.
	 * </p>
	 * 
	 * @param of ObjectFactory to create the needed jaxb object.
	 * @param roResultSet ResultSet from which to populate the jaxb object.
	 * @param ro The ROMappingProfileType to which this jaxb object will be added.
	 */
	private void populateCityCntyState(ObjectFactory of, ResultSet roResultSet, ROMappingProfileType ro) 
	throws SQLException, ROMappingException {
		while(roResultSet.next()){
			CityCntyState roCCs = of.createCityCntyState();
			roCCs.setZipCode((null != roResultSet.getString("ZIP_CODE")) ? roResultSet.getString("ZIP_CODE") : "");
			roCCs.setCityName((null != roResultSet.getString("CITY_NM")) ? roResultSet.getString("CITY_NM") : "");
			roCCs.setCountyName((null != roResultSet.getString("COUNTY_NM")) ? roResultSet.getString("COUNTY_NM") : "");
			roCCs.setStateCode((null != roResultSet.getString("STATE_CD")) ? roResultSet.getString("STATE_CD") : "");
			ro.getCityCntyState().add(roCCs);
		}
	}
	
	/**
	 * <p>
	 * Populate a jaxb object for this type as described in ROMappingProfile schema.
	 * </p>
	 * 
	 * @param of ObjectFactory to create the needed jaxb object.
	 * @param roResultSet ResultSet from which to populate the jaxb object.
	 * @param ro The ROMappingProfileType to which this jaxb object will be added.
	 */
	private void populateRO(ObjectFactory of, ResultSet roResultSet, ROMappingProfileType ro) 
	throws SQLException, ROMappingException {
		while(roResultSet.next()){
			RO roROs = of.createRO();
			roROs.setRONum((null != roResultSet.getString("RO_NUM")) ? roResultSet.getString("RO_NUM") : "");
			roROs.setROName((null != roResultSet.getString("RO_NM")) ? roResultSet.getString("RO_NM") : "");
			ro.getRO().add(roROs);
		}
	}
	
	/**
	 * <p>
	 * Populate a jaxb object for this type as described in ROMappingProfile schema.
	 * </p>
	 * 
	 * @param of ObjectFactory to create the needed jaxb object.
	 * @param roResultSet ResultSet from which to populate the jaxb object.
	 * @param ro The ROMappingProfileType to which this jaxb object will be added.
	 */
	private void populateZipCodes(ObjectFactory of, ResultSet roResultSet, ROMappingProfileType ro) 
	throws SQLException, ROMappingException {
		while(roResultSet.next()){
			ZipCodes roZCs = of.createZipCodes();
			roZCs.setZip((null != roResultSet.getString("ZIP_CODE")) ? roResultSet.getString("ZIP_CODE") : "");
			roZCs.setStateCode((null != roResultSet.getString("STATE_CODE")) ? roResultSet.getString("STATE_CODE") : "");
			ro.getZipCodes().add(roZCs);
		}
	}
	
	/**
	 * <p>
	 * Populate a jaxb object for this type as described in ROMappingProfile schema.
	 * </p>
	 * 
	 * @param of ObjectFactory to create the needed jaxb object.
	 * @param roResultSet ResultSet from which to populate the jaxb object.
	 * @param ro The ROMappingProfileType to which this jaxb object will be added.
	 */
	private void populateRegionState(ObjectFactory of, ResultSet roResultSet, ROMappingProfileType ro) 
	throws SQLException, ROMappingException {
		while(roResultSet.next()){
			RegionState roRSs = of.createRegionState();
			roRSs.setRegionName((null != roResultSet.getString("REGION_NM")) ? roResultSet.getString("REGION_NM") : "");
			roRSs.setROName((null != roResultSet.getString("RO_NM")) ? roResultSet.getString("RO_NM") : "");
			roRSs.setStateCode((null != roResultSet.getString(2)) ? roResultSet.getString(2) : "");
			roRSs.setRONumber((null != roResultSet.getString("RO_NBR")) ? roResultSet.getString("RO_NBR") : "");
			ro.getRegionState().add(roRSs);
		}
	}
	
	/**
	 * <p>
	 * Populate a jaxb object for this type as described in ROMappingProfile schema.
	 * </p>
	 * 
	 * @param of ObjectFactory to create the needed jaxb object.
	 * @param roResultSet ResultSet from which to populate the jaxb object.
	 * @param ro The ROMappingProfileType to which this jaxb object will be added.
	 */
	private void populateForeignCntries(ObjectFactory of, ResultSet roResultSet, ROMappingProfileType ro) 
	throws SQLException, ROMappingException {
		while(roResultSet.next()){
			ForeignCntries roFCs = of.createForeignCntries();
			roFCs.setCountryName((null != roResultSet.getString("CNTRY_NM")) ? roResultSet.getString("CNTRY_NM") : "");
			roFCs.setProvinceName((null != roResultSet.getString("PRVNC_NM")) ? roResultSet.getString("PRVNC_NM") : "");
			ro.getForeignCntries().add(roFCs);
		}
	}

	/**
	 * <p>
	 * Marshall a ROMappingProfileType to produce an XML String representation.
	 * </p>
	 * 
	 * @return String XML String representation of the marshalled ROMappingProfileType. 
	 * @param ro The ROMappingProfileType that will be marshalled out to XML.
	 */
	private String convertTypeToXml(ROMappingProfileType ro) throws JAXBException, PropertyException, IOException {
		StringWriter writer = new StringWriter();
		String reader; 
		JAXBContext jaxb = JAXBContext.newInstance(JAXB_PKG);
		Marshaller marshaller = jaxb.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		marshaller.marshal(ro, writer);
		reader = writer.toString();
		if(writer != null)
			writer.close();
		return reader;
	}
	
	/**
	 * <p>
	 * Throws a custom error and renders a soap fault indicating an empty return.
	 * </p>
	 * 
	 * @param msg String to initialize fault object message
	 */
	private void throwROMappingException(String msg) throws ROMappingException {
		ROMappingFault fault = new ROMappingFault();
		fault.setMessage(msg);
		throw new ROMappingException(msg, fault);
	}
	
	/**
	 * <p>
	 * Close objects that should be closed.
	 * </p>
	 * 
	 * @param rs The ResultSet that needs to be closed.
	 * @param cs The CallableStatement that needs to be closed.
	 * @param con The Connection that needs to be closed.
	 */
	private void closeLocals(ResultSet rs, CallableStatement cs, Connection con) throws SQLException {
		if(rs != null)
			rs.close();
		if(cs != null)
			cs.close();
		if(con != null)
			con.close();
	}
	
	/**
	 * <p>
	 * This method will set parameters for the CallableStatement as needed and execute.
	 * </p>
	 * 
	 * @param cs The CallableStatement to be set and executed.
	 * @param p1 First parameter used to set the CallableStatement parameters.
	 */
	protected void setParametersAndExecute(CallableStatement cs, String p1) throws SQLException {
		cs.registerOutParameter(1, OracleTypes.CURSOR);
		cs.setString(2, p1);
		cs.execute();
	}
	
	/**
	 * <p>
	 * This method will set parameters for the CallableStatement as needed and execute.
	 * </p>
	 * 
	 * @param cs The CallableStatement to be set and executed.
	 * @param p1 First parameter used to set the CallableStatement parameters.
	 * @param p2 Second parameter used to set the CallableStatement parameters.
	 */
	protected void setParametersAndExecute(CallableStatement cs, String p1, String p2) throws SQLException {
		cs.registerOutParameter(1, OracleTypes.CURSOR);
		cs.setString(2, p1);
		cs.setString(3, p2);
		cs.execute();
	}
	
	/**
	 * <p>
	 * This method will set parameters for the CallableStatement as needed and execute.
	 * </p>
	 * 
	 * @param cs The CallableStatement to be set and executed.
	 * @param p1 First parameter used to set the CallableStatement parameters.
	 * @param p2 Second parameter used to set the CallableStatement parameters.
	 * @param p3 Third parameter used to set the CallableStatement parameters.
	 * @param p4 Fourth parameter used to set the CallableStatement parameters.
	 */
	protected void setParametersAndExecute(CallableStatement cs, String p1, String p2, String p3, String p4) throws SQLException {
		cs.registerOutParameter(1, OracleTypes.CURSOR);
		cs.setString(2, p1);
		cs.setString(3, p2);
		cs.setString(4, p3);
		cs.setString(5, p4);
		cs.execute();
	}

}