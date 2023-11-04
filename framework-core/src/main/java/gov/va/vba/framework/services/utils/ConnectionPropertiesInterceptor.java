package gov.va.vba.framework.services.utils;

import gov.va.vba.framework.common.ServiceLocator;
import gov.va.vba.framework.logging.Logger;

import java.sql.CallableStatement;
import java.sql.Connection;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import javax.sql.DataSource;





public class ConnectionPropertiesInterceptor {
	/**
	 * This is an EJB 3 interceptor that sets the variables necessary
	 * for audit purposes on the connection.  It is meant to be used on the 
	 * service layer.
	 * 
	 * This interceptor was taken from the WSMS project the original author was
	 * @author Andrew Shinohara
	 *
	 */
	
		private Logger logger = Logger.getLogger(ConnectionPropertiesInterceptor.class.getName());
		protected String dsn = "jdbc/framework/corpDb/admin";
		
		
		public ConnectionPropertiesInterceptor() {
		}
		  
		@AroundInvoke
		public Object setGlobalVars(InvocationContext ctx) throws Exception {
			logger.debug("ConnectionPropertiesInterceptor.audit() Started");
			logger.debug("*** ConnectionPropertiesInterceptor intercepting "
					+ ctx.getMethod().getName());
			CallableStatement stmt = null;
			//User currentUser = null;
			logger.debug("ConnectionPropertiesInterceptor");
			try {
				DataSource dataSource = ServiceLocator.getInstance().getDataSource(dsn);
				Connection connection = dataSource.getConnection();
				
//				currentUser = UserData.get();
//				if (currentUser == null) throw new SecurityException ("User Data not found or is missing !!");
				
				stmt = connection.prepareCall("begin global_vars.g_lctn_id := ?;" +
						"	  global_vars.g_user_id := ?;"+
						"	  global_vars.g_obj_id  := ?;" +
						"end;");
				
				stmt.setString(1, "281");
				stmt.setString(2, "tester");
				stmt.setString(3, "framework");
//				stmt.setString(1, currentUser.getStationId());      
//				stmt.setString(2, currentUser.getUserId());  
//				stmt.setString(3, currentUser.getAppName());
//				logger.debug("Executing audit log with: "+currentUser.getStationId()+", "+currentUser.getUserId()+", "+currentUser.getAppName());

				stmt.execute();
				return ctx.proceed();
			} catch (SecurityException se) {
				throw new SecurityException(se);
			} catch (Exception e) {
				throw e;
			} finally {
				if (stmt != null) 
					stmt.close();
//				currentUser = null;
			}
		}

}
