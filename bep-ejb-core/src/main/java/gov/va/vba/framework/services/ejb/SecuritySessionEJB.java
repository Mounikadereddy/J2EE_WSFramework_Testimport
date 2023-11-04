package gov.va.vba.framework.services.ejb;


import gov.va.vba.framework.auditing.LegacyEJBAuditer;
import gov.va.vba.framework.domain.vo.ServiceVO;
import gov.va.vba.framework.domain.vo.ValueObject;
import gov.va.vba.framework.esb.connectors.client.TuxedoConnector;
import gov.va.vba.framework.esb.connectors.client.TuxedoConnectorException;
import gov.va.vba.framework.esb.transformers.TuxedoSecurityProfile;
import gov.va.vba.framework.esb.transformers.TuxedoSecurityProfileParser;
import gov.va.vba.framework.logging.Logger;
import gov.va.vba.framework.services.CommonSecurityException;
import gov.va.vba.framework.services.CommonSecurityService;
import gov.va.vba.framework.services.CommonSecurityServiceRemote;
import gov.va.vba.framework.services.GenericService;
import gov.va.vba.framework.services.TuxedoException;

import java.rmi.RemoteException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.sql.DataSource;

import weblogic.javaee.CallByReference;

/**
 * Main security EJB that handles all CSS updates
 *  
 * @since	Mar 31, 2006
 * @version	
 * @author	Mario Rodrigues
 */
@Stateless(name="SecurityService", mappedName="VbaSecurityService")
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@Local(CommonSecurityService.class)
@Remote(CommonSecurityServiceRemote.class)
@CallByReference
public class SecuritySessionEJB extends GenericService implements CommonSecurityService {
	
	@Resource(name=JNDI_CORP_DB) 
	private DataSource _corpDatasource;
	@Resource 
	private SessionContext ctx;
	private static Logger logger=Logger.getLogger(SecuritySessionEJB.class);
	
	public SecuritySessionEJB() throws CommonSecurityException {
		logger.debug("SecuritySessionEJB::constructor");
	}

	/**
	 * Retrieves a raw TuxedoSecurityProfile object for a specific user 
	 * 
	 * 
	 * @param 	vo
	 * @return	gov.va.vba.framework.esb.transformers.TuxedoSecurityProfile
	 * @throws 	Exception	
	 * @throws	
	 * @return	
	 * @since	Jan 3, 2007
	 * 
	 * @deprecated use SecuritySessionEJBV2.getSecurityProfile
	 */
	@Deprecated
	public TuxedoSecurityProfile getSecurityProfile(ServiceVO vo) throws TuxedoException {
		new LegacyEJBAuditer().audit(SecuritySessionEJB.class.getName(), "getSecurityProfile");
				
		TuxedoSecurityProfileParser parser = null;

		parser = new TuxedoSecurityProfileParser();
		try {
			//tmp code for CHP33: bec. applcn table has CSS_RFRNC_CNTRL_TXT value set for VB apps.
			if (vo.getApplicationName().equalsIgnoreCase("chp33")) 
				vo.setData(rightPad(rightPad(vo.getApplicationName(), 30)+"000100000000", 45));
			else
				vo.setData(rightPad(vo.getApplicationName(), 45));
			logger.debug("SecuritySessionEJB: Tuxedo Connector execute - "+vo.getServiceName()+" by "+this.ctx.getCallerPrincipal().getName());
			logger.debug("Warning: SecuritySessionEJB.getSecurityProfile is Deprecated. Change your reference to use SecuritySessionEJBV2.getSecurityProfile.");
			parser.parse(new TuxedoConnector().execute(vo));
		}
		catch (TuxedoConnectorException tce)
		{
	    	throw new TuxedoException(tce, tce.getTuxedoErrorMessage(),tce.getTuxedoErrorCode());
		}
		catch (Exception e) {
			throw new TuxedoException(e);
		}				
		return parser.getProfile();
	}

	/**
	 * Business method that performs change password functionality by updating the
	 * underlying CSS tables
	 * 
	 * @param vo
	 * @throws RemoteException
	 * @throws Exception	
	 * @throws	
	 * @return	
	 * @since	Mar 28, 2006
	 */	
	public Map<Byte, String> changePassword(ValueObject vo, String[] smMessageFields) 
		throws CommonSecurityException {
		
		new LegacyEJBAuditer().audit(SecuritySessionEJB.class.getName(), "changePassword");
		Connection con = null;
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		PreparedStatement ps3 = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null;
		long participantId, locationId = 0l;
		String oldPassword = null;
		Calendar today = null;
		Map<Byte, String> returnMap = null;//Keys: 0=okay, 1=error			
		String locationSql = 
			"select lctn_id " + 
			"from stn "+ 
			"where fclty_type_cd = ? and cd = ?";

		String userIdSql = 
			"select ptcpnt_id, "+ 
			"		lctn_id, "+ 
			"		pswrd_txt, "+ 
			"		pswrd_atmpt_qty, "+ 
			"		pswrd_efctv_dt, "+ 
			"		pswrd_dsabl_dt, "+ 
			"		pswrd_reason_nm "+ 
			"from person_scrty_log "+ 
			"where lctn_id = ? and netwrk_logon_nm = ? ";

		String passKeylogSql = 
			"select ptcpnt_id, "+ 
			"		lctn_id, "+ 
			"		end_dt, "+ 
			"		prev_pswrd_txt "+ 
			"from person_scrty_prev_pswrd "+ 
			"where ptcpnt_id = ? "+ 
			"	and lctn_id = ? " + 
			"order by end_dt desc";

		try {
			con = _corpDatasource.getConnection();
			super.log(con, vo);
			ps1 = con.prepareStatement(locationSql);
			ps1.setString(1, vo.getStationId().substring(0, 1));
			ps1.setString(2, vo.getStationId().substring(1, 3));
			rs1 = ps1.executeQuery();
			if (rs1.next())
				locationId = rs1.getLong(1);
			ps2 = con.prepareStatement(userIdSql,
										ResultSet.TYPE_SCROLL_SENSITIVE,
										ResultSet.CONCUR_UPDATABLE);
			ps2.setLong(1, locationId);
			ps2.setString(2, vo.getUserId().trim());
			rs2 = ps2.executeQuery();
			returnMap = new HashMap<Byte, String>();
			if (rs2.next()) {
				if (rs2.getInt("pswrd_atmpt_qty") >0 && rs2.getDate("pswrd_dsabl_dt") != null)
					//returnMap.put(Byte.valueOf((byte)1), "Your account is locked. Please contact your Security Officer.");
					returnMap.put(Byte.valueOf((byte)1), "Your account is locked. Please contact the VA National Service Desk at (855) 673-4357 or ITSC@va.gov.");
				else if (!(oldPassword = rs2.getString(3)).equals(smMessageFields[2])) {
					ps3 = con.prepareStatement(passKeylogSql,
												ResultSet.TYPE_SCROLL_SENSITIVE,
												ResultSet.CONCUR_UPDATABLE);
					participantId = rs2.getLong(1);
					ps3.setLong(1, participantId);
					ps3.setLong(2, locationId);
					rs3 = ps3.executeQuery();
					int i = 1;
					boolean passwordPreviouselyUsed = false;
					while (rs3.next() && i <= 3) {
						if (smMessageFields[2].equals(rs3.getString(4)))
							passwordPreviouselyUsed = true;
						i++;
					}
					if (passwordPreviouselyUsed)
						returnMap.put(Byte.valueOf((byte)1), "Sorry, password has been used within the last 3 times. Try again.");
					else {
						if (rs3.absolute(2)) {
							while (rs3.next())
								rs3.deleteRow();
						}
						rs3.moveToInsertRow();
						rs3.updateLong(1, participantId);
						rs3.updateLong(2, locationId);
						today = Calendar.getInstance();
						rs3.updateTimestamp(3, 
								new java.sql.Timestamp(today.getTimeInMillis()));
						rs3.updateString(4, oldPassword);
						rs3.insertRow();
						rs2.absolute(1);
						rs2.updateString(3, smMessageFields[2]);
						rs2.updateInt(4, 0);
						today.add(Calendar.DATE, 90);
						rs2.updateTimestamp(5, new java.sql.Timestamp(today
								.getTimeInMillis()));
						rs2.updateDate(6, null);
						rs2.updateString(7, null);
						rs2.updateRow();
						returnMap.put(Byte.valueOf((byte)0), "Your password has been changed.");						
					}
				}
				else {
					returnMap.put(Byte.valueOf((byte)1), "Sorry, new password is equal to current password. Try again.");
				}
			}
			else {
				returnMap.put(Byte.valueOf((byte)1), "Cannot locate user info based on login credentials.");
			}
			return returnMap;
		}
		catch (Exception e) {
			_sessionCtx.setRollbackOnly();
			throw new CommonSecurityException(e);
		}
		finally {			
			try {
				if (ps1 != null)ps1.close();
				if (ps2 != null)ps2.close();
				if (ps3 != null)ps3.close();
			}
			catch (Exception e) {
				throw new CommonSecurityException("Error closing JDBC connection. ", e);
			}
			try {if (con != null)con.close();}
			catch (Exception e) {
				throw new CommonSecurityException("Error closing JDBC connection. ", e);
			}
		}		
	}

	/**
	 * Business method that increments the retry counter in the underlying CSS tables.
	 * Calls the Oracle stored proc: weblogic.updt_atmpt
	 * 
	 * @param 	vo
	 * @throws 	RemoteException
	 * @throws 	Exception	
	 * @throws	
	 * @return	
	 * @since	Mar 28, 2006
	 */	
	public int updateRetryCount(ValueObject vo) throws CommonSecurityException {
		
		new LegacyEJBAuditer().audit(SecuritySessionEJB.class.getName(), "updateRetryCount");
		CallableStatement cstmt = null;
		Connection con = null;

		try {
			con = _corpDatasource.getConnection();
			super.log(con, vo);
			cstmt = con.prepareCall("{call weblogic.updt_atmpt(?, ?, ?)}") ;                 
			cstmt.setString(1, vo.getUserId());      
			cstmt.setString(2, vo.getStationId());      
			cstmt.registerOutParameter(3, Types.INTEGER);                       
			cstmt.execute();      
			return cstmt.getInt(3);
		}
		catch (Exception e) {
			_sessionCtx.setRollbackOnly();
			throw new CommonSecurityException(e);
		}
		finally {
			try {if (cstmt != null)cstmt.close();}
			catch (Exception e) {
				logger.error("",e);
				}
			try {if (con != null)con.close();}
			catch (Exception e) {
				logger.error("",e);
				}
		}		
	}

	/**
	 * 
	 * @param  ValueObject
	 * @throws RemoteException
	 * @throws Exception	
	 * @throws	
	 * @return	
	 * @since	Feb 2, 2007
	 */
	public void execute(ValueObject vo) throws CommonSecurityException {
				
		new LegacyEJBAuditer().audit(SecuritySessionEJB.class.getName(), "execute");
		try {
			super.log(_corpDatasource.getConnection(), vo);
		}
		catch (Exception e) {
			throw new CommonSecurityException(e);
		}		
	}
	
	/**
	 * Pads the String <code>str</code> with <code>size</code> spaces. Apache's StringUtils
	 * does not truncate the input string if it's smaller than the size.
	 * 
	 * @param 	str
	 * @param 	size
	 * @return	
	 * @throws	
	 * @return	
	 * @since	Jul 22, 2008
	 */
	private String rightPad(String str, int size) {
		

    	StringBuilder strBldr = new StringBuilder(str==null? "":str);
    	for (int i=strBldr.length(); i<size;i++) 
    		strBldr.append('\u0020');
    	return strBldr.substring(0, size);
	}
}
