/*
 * CoversAction.java
 *
 * Copyright 2006 U.S. Dept Of Veterans Affairs.
 * U.S. Government PROPRIETARY/CONFIDENTIAL. Use is subject to security terms.
 */
package gov.va.vba.framework.web.actions;

import gov.va.vba.framework.exceptions.BaseException;
import gov.va.vba.framework.logging.Logger;
import gov.va.vba.framework.web.security.UserContext;
import javax.servlet.http.*;
import org.apache.struts.action.*;

/**
 * 
 * @author Mario Rodrigues
 * @since  February 26, 2006
 */
public class SecurityAction extends BaseAction  {

	private static Logger logger=Logger.getLogger(SecurityAction.class.getName());
		
	/**
	 * Main method that must be implemented from BaseAction
	 * 
	 * @param	ActionMapping - ActionMapping for current Action
	 * @param	ActionForm - DynaActionForm for current page
	 * @param	HttpServletRequest - current request obj
	 * @param	HttpServletResponse - current response obj
	 * @param	AuditContext	- the current user's context (logged in user)
	 * @return	an ActionForward to the next Action/jsp 
	 * @throws	a BaseException that gets processed in Base and propagated to the UI
	 */
	public ActionForward executeAction(ActionMapping mapping,
									   ActionForm form,
									   HttpServletRequest req,
									   HttpServletResponse resp,
									   UserContext userCtx) throws BaseException {
		logger.debug("SecurityAction::Module Prefix:- " + mapping.getModuleConfig().getPrefix());
		logger.debug("SecurityAction::Mapping path:- " + mapping.getPath());	
		return mapping.findForward("success");
	}
}

