package gov.va.vba.framework.services.ejb;


import gov.va.vba.framework.common.AuditContext;
import gov.va.vba.framework.common.ObjectUtils;
import gov.va.vba.framework.common.StringUtils;
import gov.va.vba.framework.services.CssUserRepositoryException;
import gov.va.vba.framework.services.CssUserRepositoryUnknownException;
import gov.va.vba.framework.css.cssiam.domain.entities.CssUser;
import gov.va.vba.framework.css.cssiam.domain.entities.UserStation;
import gov.va.vba.framework.domain.vo.ServiceVO;
import gov.va.vba.framework.domain.vo.ValueObject;
import gov.va.vba.framework.esb.transformers.TuxedoSecurityProfile;
import gov.va.vba.framework.esb.transformers.TuxedoSecurityProfile.Function;
import gov.va.vba.framework.logging.Logger;
import gov.va.vba.framework.services.BaseService;
import gov.va.vba.framework.services.CommonSecurityException;
import gov.va.vba.framework.services.CommonSecurityServiceRemoteV2;
import gov.va.vba.framework.services.CommonSecurityServiceV2;
import gov.va.vba.framework.services.GenericService;
import gov.va.vba.framework.services.TuxedoException;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.sql.DataSource;

import oracle.jdbc.OracleTypes;

import org.apache.openjpa.persistence.EntityManagerImpl;

import weblogic.javaee.CallByReference;

/**
 * Main security EJB that handles all CSS updates
 *  
 * @since	Dec 9, 2010
 * @version	
 * @author	Joshua Glickman
 */
@Stateless(name="SecurityServiceV2", mappedName="VbaSecurityServiceV2")
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@Local(CommonSecurityServiceV2.class)
@Remote(CommonSecurityServiceRemoteV2.class)
@CallByReference
public class SecuritySessionEJBV2 extends GenericService implements CommonSecurityServiceV2, BaseService {	
	
	public final static char UNEXPECTEDEXCEPTIONCODE = '2';
	
	@Resource(name=JNDI_CORP_DB) 
	private DataSource _corpDatasource;
	
	@PersistenceContext(unitName = "corpDbAdmin")
	protected EntityManager em;
	
	protected List<EntityManager> ems;
	
	@Resource 
	private SessionContext ctx;
	private static Logger logger=Logger.getLogger(SecuritySessionEJBV2.class);


	public SecuritySessionEJBV2() throws CommonSecurityException {
		logger.debug("SecuritySessionEJB::constructor");
	}
	
	@PostConstruct
	public void init(){
		ems = new ArrayList<EntityManager>();
		ems.add(em);
	}
	
	@Override
	public TuxedoSecurityProfile getSecurityProfile(ServiceVO vo, AuditContext auditContext, Map extensions) throws TuxedoException{
		CallableStatement stmt = null;
		ResultSet rs = null;
		
		//Old version of software that call this EJB use WLSAUTHEN instead of CSSPROFILE
		if(ServiceVO.SecurityService.WLSAUTHEN.equals(vo.getService())) {
			logger.error("Deprecated use of EJB detected trying to call EJB with WLSAUTHEN service: "+auditContext.toString());
		}
		
		TuxedoSecurityProfile profile = new TuxedoSecurityProfile();
		try {
			EntityManagerImpl impl = (EntityManagerImpl)em.getDelegate();
			Connection connection = (Connection)impl.getConnection();
						
			stmt = connection.prepareCall("{ ? = call CSSPROFILE.GET(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			
			stmt.registerOutParameter(1,	Types.NUMERIC);
			stmt.registerOutParameter(6,	Types.VARCHAR);
			stmt.registerOutParameter(7,	Types.NUMERIC);
			stmt.registerOutParameter(8,	Types.NUMERIC);
			stmt.registerOutParameter(9,	Types.VARCHAR);
			stmt.registerOutParameter(10,	Types.CHAR);
			stmt.registerOutParameter(11,	Types.CHAR);
			stmt.registerOutParameter(12,	Types.VARCHAR);
			stmt.registerOutParameter(13,	Types.VARCHAR);
			stmt.registerOutParameter(14,	Types.NUMERIC);
			stmt.registerOutParameter(15,	Types.NUMERIC);
			stmt.registerOutParameter(16,	Types.VARCHAR);
			stmt.registerOutParameter(17,	Types.VARCHAR);
			stmt.registerOutParameter(18,	Types.VARCHAR);
			stmt.registerOutParameter(19,	Types.VARCHAR);
			stmt.registerOutParameter(20,	Types.VARCHAR);
			stmt.registerOutParameter(21,	Types.VARCHAR);
			stmt.registerOutParameter(22,	Types.VARCHAR);
			stmt.registerOutParameter(23,	Types.VARCHAR);
			stmt.registerOutParameter(24,	Types.VARCHAR);
			stmt.registerOutParameter(25,	Types.VARCHAR);
			stmt.registerOutParameter(26,	Types.VARCHAR);
			stmt.registerOutParameter(27,	Types.VARCHAR);
			stmt.registerOutParameter(28,	Types.VARCHAR);
			stmt.registerOutParameter(29,	Types.VARCHAR);
			stmt.registerOutParameter(30,	Types.VARCHAR);
			stmt.registerOutParameter(31,	Types.VARCHAR);
			stmt.registerOutParameter(32,	Types.VARCHAR);
			stmt.registerOutParameter(33,	Types.VARCHAR);
			stmt.registerOutParameter(34,	Types.VARCHAR);
			stmt.registerOutParameter(35,	OracleTypes.CURSOR);
			stmt.registerOutParameter(36,	OracleTypes.CURSOR);



			
			stmt.setString(2, vo.getUserId().toUpperCase());
			stmt.setString(3, vo.getApplicationName());
			stmt.setString(4, vo.getStationId());
			stmt.setString(5, "");
			
			stmt.execute();
			
			/*
			 out parameters not in use by profile:
			 stationLocationId [7]
			 applicationId [8]
			 cssRfrncCntrlText [9]
			 remoteIndicator [10]
			 globalIndicator [11]
			 applicationLocked [12]
             webUrlText [13]
             assignedStationCode [25]
             workLocationName [26]
			*/
			
			//TODO: Do we really need 'char' for return code?
			profile.setRetCode(stmt.getString(1).charAt(0));
			switch (profile.getRetCode()) {
			case '0':
				profile.setStationName(stmt.getString(6));		
				profile.setParticipantId(stmt.getString(14));
				
				BigDecimal bdnNumber = stmt.getBigDecimal(15);
				if(bdnNumber != null){
					profile.setBdnNum(bdnNumber.toString());
				}
				
				//TODO: Check if the mapping is correct
				String diagnosticIdicator = stmt.getString(16);
				if(!StringUtils.isEmpty(diagnosticIdicator)){
					profile.setDiagInd(diagnosticIdicator.charAt(0));
				}
                
				profile.setSecLevel(stmt.getString(17));
				profile.setLastName(stmt.getString(18));
				profile.setFirstName(stmt.getString(19));
				profile.setMiddleName(stmt.getString(20));
				profile.setSuffix(stmt.getString(21));
				profile.setSsn(stmt.getString(22));
				profile.setFileNum(stmt.getString(23));
				profile.setJobTitle(stmt.getString(24));
				profile.setVaOrganization(stmt.getString(27));
				profile.setEmailAddress(stmt.getString(28));
				
				String soIdicator = stmt.getString(29);
				if(!StringUtils.isEmpty(soIdicator)){
					profile.setSecOfficeInd(soIdicator.charAt(0));
				}
						
				profile.setPhNum(stmt.getString(30));
				profile.setPhAreaCode(stmt.getString(31));
				profile.setPhExt(stmt.getString(32));
				
				try {
					rs = (ResultSet)stmt.getObject(35);
					while(rs.next()){
						addFunction(profile, rs);
					}
				} finally { try {rs.close();} catch(Exception e){} finally{} }
				//TODO: What is String(!) function count?
				profile.setNumFunctions((profile.getFunctions() == null) ? "0" : String.valueOf(profile.getFunctions().size()));
				
				try {
					rs = (ResultSet)stmt.getObject(36);
					if(rs.next()) {
						List<String> poaCodes = new ArrayList<String>();
						poaCodes.add(rs.getString(1));
						while(rs.next()){
							poaCodes.add(rs.getString(1));
						}
						profile.setPoaCodes(poaCodes);
						//TODO: What is String(!) poa count?
					}	
				} finally { try {rs.close();} catch(Exception e){} finally{} }
			

				profile.setPoaCount((profile.getPoaCodes() == null) ? "0" : String.valueOf(profile.getPoaCodes().size()));
				
				profile.setApplRole(stmt.getString(33));
				profile.setMessage(stmt.getString(34));
				
				ObjectUtils.defaultFieldsToString(profile, "");
				
				break;
			case '1':
				profile.setMessage(stmt.getString(34));
				break;
			/*
			 * return code 3 for expired password is no longer a requirement due to implementation of 2 factor Authentication.
			 */
			/*case '3':
				profile.setMessage(stmt.getString(34));
				break; */
			default:  // note: this logic should never be executed unless a new return code was added to CSSPROFILE stored procedure 
				handleException (profile, "CSSPROFILE Return Code exception occured","Unknown database return code from CSSPROFILE",null);				
			} 
		} catch (SQLException e) { // The most likely exceptions will be associated with the database (ie. connectivity, etc.)
			handleException (profile, "SQL exception occurred","SQL Exception while loading the CSS User Profile from Data Source",e);
		} catch (PersistenceException e) { //  (ie. datasource driver issues, etc.)
			handleException (profile, "Persistence exception occurred","JPA Exception while loading the CSS User Profile from Data Source",e);
		} catch (Exception e) { // All exceptions, must be caught and handled
			handleException (profile, "Java exception occurred","Java Exception while creating the CSS User Profile.",e);
		} finally {
			try {rs.close();} catch (Exception e) {}
			try {stmt.close();} catch (Exception e) {}
		} 
		return profile;
	}
	
	private void handleException(TuxedoSecurityProfile profile, String returnMessage, String logMessage, Exception e) {
		logger.debug("Exception loading CSS Profile: retCode["+profile.getRetCode()+
				"], securityLevel["+profile.getSecLevel()+
				"], participantId["+profile.getParticipantId()+"]");
		profile.setRetCode(UNEXPECTEDEXCEPTIONCODE); // code 2
		profile.setMessage(returnMessage);
		if (e!=null) {logger.error(logMessage, e);} else {logger.error(logMessage);}
	}
	
	private void addFunction(TuxedoSecurityProfile profile, ResultSet rs) throws SQLException {
		Function func = profile.new Function();
		func = profile.new Function();
		func.setName(rs.getString(1));
		func.setDisableInd(rs.getString(3).charAt(0));
		func.setAssignedValue(rs.getString(2));
		profile.addFunction(func);
	}
	
	@Override
	public CssUser getCssUserStationsByApplication(String userId,
			String applicationName, AuditContext auditContext) throws CssUserRepositoryException,
			CssUserRepositoryUnknownException {
		EntityManagerImpl impl = (EntityManagerImpl)em.getDelegate();
		Connection connection = (Connection)impl.getConnection();
		CallableStatement stmt = null;
		CssUser cssuser = new CssUser();
		
	try {


		cssuser.setUserApplication(applicationName);
		cssuser.setNetworkLoginName(userId.toUpperCase());
				
		stmt = connection
				.prepareCall("{ ? = call CSSPROFILE.getStationsByUserApplication(?,?,?,?) }");
		// Return code 0 if Success, 1 if Error
		stmt.registerOutParameter(1, Types.NUMERIC);
		stmt.setString(2, cssuser.getNetworkLoginName());
		stmt.setString(3, cssuser.getUserApplication());

		// Return Message
		stmt.registerOutParameter(4, Types.VARCHAR);

		// Cursor for the list of station elements
		stmt.registerOutParameter(5, OracleTypes.CURSOR);

		stmt.execute();

		int returnCode = stmt.getInt(1);

		switch (returnCode) {
			// An enable station is returned on the list
			case 0:
				List<UserStation> stationList = cssuser.getUserStations();
				ResultSet rs = null;
				try {
					rs = (ResultSet) stmt.getObject(5);
					while (rs.next()) {
						UserStation station = new UserStation();
						station.setId(rs.getString(2));
						station.setName(rs.getString(3));
						station.setRole(rs.getString(4));
						station.setEnabled(rs.getString(5).charAt(0) == 'Y' ? true
								: false);
						station.setReasonCode(rs.getString(6));
						stationList.add(station);
					}
				} catch (Exception e) {
					e.printStackTrace();
					logger.error("Error getting station list from Corporate for ["+userId+","+applicationName+"]",e);
					CssUserRepositoryUnknownException ex = new CssUserRepositoryUnknownException("Error getting station list from Corporate for for ["+userId+","+applicationName+"]",e);
					throw ex;
				} finally {
					releaseQuietly(rs);
				}
				break;
			// No enabled station is available in the list
			case 1:
				String reasonCode = stmt.getString(4);
				logger.debug("No stations available for login for ["+userId+","+applicationName+"]");
				CssUserRepositoryException ex = new CssUserRepositoryException("Error getting CSS User from Corporate");
				ex.setReasonCode(reasonCode);
				throw ex;
			default:
				logger.error("Unrecognized response from Corporate trying to get CSS User from Corporate for ["+userId+","+applicationName+"]");
				CssUserRepositoryUnknownException exc = new CssUserRepositoryUnknownException("Unrecognized response trying to get CSS User from Corporate for ["+userId+","+applicationName+"]");
				throw exc;
			}
		} catch (CssUserRepositoryException e) {
			//Already catched
			throw e;
		} catch (CssUserRepositoryUnknownException e) {
			//Already catched
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error getting CSS User from Corporate for ["+userId+","+applicationName+"]",e);
			CssUserRepositoryUnknownException ex = new CssUserRepositoryUnknownException("Error getting CSS User from Corporate for ["+userId+","+applicationName+"]",e);
			throw ex;
		}
		finally {
			releaseQuietly(stmt);
		}
		return cssuser;
	}
	
	/**
	 * 
	 * @param  ValueObject
	 * @throws RemoteException
	 * @return void	
	 * @since Dec 9, 2010
	 */
	public void execute(ValueObject vo, AuditContext auditContext, Map extensions, Connection con) throws CommonSecurityException {

		try {
			super.log(_corpDatasource.getConnection(), vo);
		}
		catch (Exception e) {
			throw new CommonSecurityException(e);
		}
	}
	
	/**
	 * Release the Statement quietly
	 * @param statement
	 */
	private void releaseQuietly(Statement statement) {
		if ( statement == null ) {
			return;
		}
		try {
			statement.close();
		}
		catch ( SQLException e ) {
			// ignore
		}
	}

	/**
	 * Release the ResultSet quietly
	 * @param resultSet
	 */
	private void releaseQuietly(ResultSet resultSet) {
		if ( resultSet == null ) {
			return;
		}
		try {
			resultSet.close();
		}
		catch ( SQLException e ) {
			// ignore
		}
	}
	
	@Override
	public List<EntityManager> getEntityManagerListForJournaling() {
		return ems;
	}

}
