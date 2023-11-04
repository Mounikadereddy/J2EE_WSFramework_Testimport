/*
 * BaseAction.java
 *
 * Copyright 2006 U.S. Dept Of Veterans Affairs.
 * U.S. Government PROPRIETARY/CONFIDENTIAL. Use is subject to security terms.
 */
package gov.va.vba.framework.web.actions;

import gov.va.vba.framework.exceptions.BaseException;
import gov.va.vba.framework.logging.Logger;
import gov.va.vba.framework.web.security.SecurityFilter;
import gov.va.vba.framework.web.security.UserContext;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * <p>
 * An Abstract Base action that all VBA actions extend. The main purposes
 * of BaseAction are: (1) to create a common routing and exception
 * handling mechanism so that concrete Actions can deal with the task at
 * hand and not worry about chaining and propagating exceptions
 * individually (otherwise this would lead to a lot of redundant code).
 * (2) to easily pass the UserContext object to all Actions in a uniform
 * manner. 
 * </p>
 * Sub classes need to implement {@link gov.va.vba.framework.web.actions#executeAction()}
 * @see org.apache.struts.action#Action Struts Action package link
 * @see <a href="http://struts.apache.org/1.2.9/api/index.html">Struts API</a>
 * 
 * @author Mario Rodrigues
 * @since  February 26, 2006
 * <img src="doc-files/Security.gif">
 */
public abstract class BaseAction extends Action {
	
	private static Logger logger=Logger.getLogger(BaseAction.class.getName());
		
	/**
	 * The default <code>execute()</code> method that all actions must implement. All exception handling 
	 * from client Action invocations is done here. Descendents of this class will throw exceptions of 
	 * <code>BaseException</code> so that they can be properly handled here.
	 * An Exception is either processed as a BaseException type OR treated as a system error 
	 * and made available to the system error page. This is accomplished by using a double-try-catch so
	 * that exceptions don't get propagated to the servlet container, but are instead processed right
	 * here.
	 * Note: The check for the "/processLogin" mapping will be removed after SiteMinder integration
	 * 
	 * @param	ActionMapping - ActionMapping for current Action
	 * @param	ActionForm - DynaActionForm for current page
	 * @param	HttpServletRequest - current request obj
	 * @param	HttpServletResponse - current response obj
	 * @return	an ActionForward to the next Action/jsp 
	 * @throws	a java.lang.Exception
	 */
	public ActionForward execute(ActionMapping mapping,
								 ActionForm form,
								 HttpServletRequest req,
								 HttpServletResponse resp) throws Exception {

		ActionForward forwardPage = null;

		try {			
			log("Framework BaseAction: Executing mapping: "+mapping);
			forwardPage = executeAction(mapping, form, req, resp, 
					(UserContext)req.getSession(false).getAttribute(SecurityFilter.USER_CONTEXT));
		}
		catch (BaseException ex) {
			log("BaseAction ERROR: "+ex);
			//forwardPage = processBaseExceptions(req, mapping, ex);
			//forwardPage = mapping.findForward("ERROR");	
			throw ex;
		}
		return forwardPage;
	}

	/**
	 * All descendents of BaseAction must implement this method. 
	 * Note: Subclasses don't have to worry about providing a catch block, unless they 
	 * are providing specialized behavior for exceptions.	 
	 * 
	 * @param	ActionMapping 
	 * @param	ActionForm 
	 * @param	HttpServletRequest 
	 * @param	HttpServletResponse 
	 * @param	AuditContext
	 * @return	ActionForward
	 * @throws	Exception
	 */
	public abstract ActionForward executeAction(ActionMapping mapping,
												ActionForm form,
												HttpServletRequest req,
												HttpServletResponse resp,
												UserContext userCtx) throws BaseException;

	/**
	 * <p>
	 * Processes the top-level exception and determines if there are sub exceptions. If so, it 
	 * processes each one and saves all of the ActionMessages that were created.
	 * Returns control back to either the input resource or to a "Failure" ActionForward. 
	 * (if Locale support is needed pass <code>locale</code> to <code>processBaseException()</code> 
	 * and format params in that method)
	 * </p>
	 * @param	user request obj.
	 * @param	action mapping
	 * @param	BaseException from Action
	 * @return	ActionForward (back to resource identified in <code>input</code> attrib. of action or to a 
	 * 			"Failure" that has been configured for the action)
	 * @throws	
	 
	private ActionForward processBaseExceptions(HttpServletRequest req,
												  ActionMapping mapping,
												  BaseException ex) {

	}*/



	/**
	 * Forces a user back to the SiteMinder login page
	 * TODO: need to get SM API details.
	 * 
	 * @param	HttpServletRequest
	 * @return	
	 */
	protected void invalidateSession(HttpServletRequest req) throws SecurityException {

		HttpSession session = null;

		if ((session = req.getSession(false)) != null) {
			session.invalidate();
			//redirect to SiteMinder login
		}
		else
			throw new SecurityException("Session is null. Cannot invalidate");
	}


	
	/**
	 * Retrieves an object stored in the user's HttpSession based on the request and 
	 * attribute name
	 * 
	 * @param	HttpServletRequest
	 * @param	String - attribute name
	 * @return	Object - must be case to a specific type
	 * @throws	
	 */
	public Object getSessionObject (HttpServletRequest req, String attribName) {
		
		Object sessionObj = null;
		
		HttpSession session = req.getSession(false);
		if (session != null)
			sessionObj = session.getAttribute(attribName);
			
		return sessionObj;
	}


	/**
  	 * <p>
  	 * Convience method to retrieve an object from application scope by its name. 
  	 * </p>
  	 * @param	name of attribute that's in appl. scope
  	 * @return	an <code>Object</code> that should be cast into a concrete type by caller.
 	 */
	public Object getApplicationObject(String attrName) {
		return servlet.getServletContext().getAttribute(attrName);
	}	
	
   /**
     * Logs each thread (request)
     * 	
     * @throws	
     * @return	
     * @since	Mar 31, 2006
     */
    private void log(String msg) {
    	//perform log4j logging
    	String now = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(new Date());
    	logger.debug("BaseAction("+now+"): "+msg);
    }

}