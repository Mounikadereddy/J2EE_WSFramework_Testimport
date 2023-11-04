/*
 * BaseSessionBean.java
 *
 * Copyright 2006 U.S. Dept Of Veterans Affairs.
 * U.S. Government PROPRIETARY/CONFIDENTIAL. Use is subject to security terms.
 */
package gov.va.vba.framework.services;

import gov.va.vba.framework.domain.vo.ValueObject;
import gov.va.vba.framework.exceptions.BaseException;
import gov.va.vba.framework.logging.Logger;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import javax.ejb.SessionContext;


/**
 * Base Session Bean that all VBA service end points must extend. This  bean 
 * currently serves the following purposes
 * <p> 
 * 1) audit logging for VBA security. 
 * 2) tag inheritance. All sub classes inherit this bean's characteristics. Sub
 * 		classes can also over ride these attributes if they have to
 * </p>
 * This base class also controls the runtime behavior of derived beans so that 
 * the average developer need not worry about pool sizes and initial start up
 * parameters.
 *  
 * @since	Mar 23, 2006
 * @version	
 * @author	Mario Rodrigues
 */
public abstract class GenericService implements Service<ValueObject> {
	
	//private static final long serialVersionUID = -1926239479778891560L;
	protected SessionContext _sessionCtx;
	public static final String JNDI_CORP_DB = "jdbc/framework/corpDb/admin";
	private static Logger logger=Logger.getLogger(GenericService.class.getName());
	
	/**
	 * Sets user identification information so that the Corporate database can
	 * perform audit logging. Sub classes should call this method before every
	 * Create, Update, and/or Delete transation. 
	 * 
	 * @param vo
	 * @throws RemoteException
	 * @throws Exception	
	 * @throws	
	 * @return	
	 * @since	Mar 28, 2006
	 */
	public void log(ValueObject vo) throws BaseException {
		
		logger.debug("EJB-Logging: "+vo.toString());		
	}

	/**
	 * Temporary method for WEAMS integration. This method will be deprecated
	 * once WEAMS integration is complete.
	 * 
	 * @param con
	 * @param vo
	 */
	protected void log(Connection con, ValueObject vo) 
		throws BaseException {
		
		CallableStatement stmt = null;
		
		try {     
			stmt = con.prepareCall("begin global_vars.g_lctn_id := ?;" +
									"	  global_vars.g_user_id := ?;"+
									"	  global_vars.g_obj_id  := ?;" +
									"end;");                     
			stmt.setString(1, vo.getStationId());      
			stmt.setString(2, vo.getUserId());  
			stmt.setString(3, vo.getApplicationName());
			logger.debug("Executing audit log with: "+vo.getStationId()+", "+vo.getUserId()+", "+vo.getApplicationName());
			stmt.execute();
			logger.debug("after stmt.execute in genericService.log");
		}
		catch (SQLException se) {
			if (_sessionCtx != null) { _sessionCtx.setRollbackOnly(); }
			throw new BaseException("Error parsing/executing PL/SQL block: ", se);
		}
		finally {
			try {
				if (stmt != null) {
					stmt.close();
					stmt = null;
				}
			} 
			catch (SQLException se) {
				throw new BaseException("Error closing statement. ", se);
			}		
		}		
	}

		
}
