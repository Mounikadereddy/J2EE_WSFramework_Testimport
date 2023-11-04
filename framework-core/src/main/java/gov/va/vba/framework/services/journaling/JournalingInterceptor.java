package gov.va.vba.framework.services.journaling;


import gov.va.vba.framework.common.AuditContext;
import gov.va.vba.framework.logging.Logger;
import gov.va.vba.framework.services.BaseService;
import gov.va.vba.framework.services.BaseStatefulService;

import java.util.List;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;

/**
 * Interceptor class that sets the global variables on the database connection
 * associated with the entity manager(s).
 * 
 * Reads the global variable information from the AuditContext provided as method
 * argument or member variable of the bean instance.
 * 
 * Resets the global variable information after the method execution is
 * completed.
 * 
 * @author psimyeru
 * 
 */
public class JournalingInterceptor {

	private static Logger logger=Logger.getLogger(JournalingInterceptor.class);
	
	/**
	 * 
	 * Set the global variables for the all entity managers associated with the
	 * bean instance.
	 * 
	 * Throws <code>JournalingException</code> when interceptor applied on bean
	 * that didn't implement <code>BaseService</code> or when no/blank user
	 * context information found.
	 * 
	 * @param ctx
	 * @return
	 * 
	 */
	@AroundInvoke
	public Object enableJournaling(InvocationContext ctx) throws Exception{

		debug("**** BEGIN Intercepton of " + ctx.getClass().getName() + "."
				+ ctx.getMethod().getName());

		// All the Service objects need to implement BaseService to use
		// JournalingInterceptor.
		//
		if (!(ctx.getTarget() instanceof BaseService)) {
			throw new JournalingException(
					"Service Object need to implement BaseService Interface");
		}

		AuditContext user = null;

		// If Stateful Ejb instance, retrieve the user context from the
		// bean's member variable.
		//
		if (ctx.getTarget() instanceof BaseStatefulService) {
			BaseStatefulService service = (BaseStatefulService) ctx
					.getTarget();
			user = service.getAuditContext();
		}

		// If User context is not found, it is either Stateless Ejb
		// implementation or Stateful Ejb implementation empty user context
		// member variable, check to see if the user context is passed as a
		// method argument
		//
		if (user == null) {
			// Retrieve CSS User information from method parameters
			//
			Object[] params = ctx.getParameters();
			for (Object x : params) {
				if (x instanceof AuditContext) {
					user = (AuditContext) x;
				}
			}
		}

		// If no user context found, throw an error.
		//
		if (user == null) {
			throw new JournalingException(
					"No User Context found, User Context is required to enable Journaling.");
		}

		String userId = user.getUserId();
		String stationId = user.getStationID();
		String appName = user.getApplicationName();

		debug("CSS User Information [UserId: " + userId + " stationId: "
				+ stationId + " applicationName: " + appName + "]");

		// All the three journaling fields required CSS UserId, StationId and
		// Application Name
		// If any are not supplied throw error.
		//
		if (isEmpty(userId) || isEmpty(stationId) || isEmpty(appName)) {
			throw new JournalingException(
					"All of CSS User Information is required (CSS UserId, StationId, Application Name)");
		}

		try {
			// Set global variables for all Entity Managers associated with the
			// service Ejbs.
			//
			setGlobalVarsForAllEMs(ctx, userId, stationId, appName);

			// Proceed with method execution.
			//
			return ctx.proceed();
		} catch (Exception e) {
			throw new JournalingException(e);
		} finally {
			// Reset Global Variables for all the Entity Managers.
			//
			setGlobalVarsForAllEMs(ctx, null, null, null);
			debug("**** END Intercepton of " + ctx.getClass().getName() + "."
					+ ctx.getMethod().getName());
		}
	}

	/**
	 * Retrieves all the entity managers associated with the target service Ejb
	 * and sets global variables provided.
	 * 
	 * @param ctx
	 * @param userId
	 * @param stationId
	 * @param appName
	 */
	private void setGlobalVarsForAllEMs(InvocationContext ctx, String userId,
			String stationId, String appName) {
		BaseService service = (BaseService) ctx.getTarget();
		List<EntityManager> emList = service
				.getEntityManagerListForJournaling();
		for (EntityManager em : emList) {
			em.joinTransaction();
			javax.persistence.Query query = em
					.createNativeQuery("begin global_vars.g_lctn_id:=?; global_vars.g_user_id:=?; global_vars.g_obj_id:=?;end;");
			query.setParameter(1, stationId);
			query.setParameter(2, userId);
			query.setParameter(3, appName);
			query.executeUpdate();
			em.flush();
		}
	}

	// Empty checks
	// -----------------------------------------------------------------------
	public static boolean isEmpty(String str) {
		return str == null || str.length() == 0;
	}

	private void debug(Object o) {
		logger.debug(o);
	}
}
