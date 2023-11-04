/*
 * 
 *
 * Copyright 2006 U.S. Dept Of Veterans Affairs.
 * U.S. Government PROPRIETARY/CONFIDENTIAL. Use is subject to security terms.
 */
package gov.va.vba.framework.web.actions;

import gov.va.vba.framework.exceptions.BaseException;
import gov.va.vba.framework.logging.Logger;
import gov.va.vba.framework.web.security.BaseFilter;
import gov.va.vba.framework.web.security.UserContext;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
//import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
//import java.net.CookieHandler;
//import java.net.CookieManager;
//import java.net.CookiePolicy;
//import java.net.CookieStore;
//import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.URL;
//import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.*;

import org.apache.http.message.BasicNameValuePair;
import org.apache.http.NameValuePair;
import org.apache.struts.action.*;

/**
 * 
 */
public class SiteMinderEmulatorAction extends BaseAction  {

	private static Logger logger=Logger.getLogger(SiteMinderEmulatorAction.class.getName());
		
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
				
		logger.debug("SiteMinderEmulatorAction::Module Prefix:- " + mapping.getModuleConfig().getPrefix());
		logger.debug("SiteMinderEmulatorAction::Mapping path:- " + mapping.getPath());	
		
		DynaActionForm typeForm = (DynaActionForm)form;
		
		String requestType = typeForm.get("requesttype").toString().trim();
		if (requestType.isEmpty()) {requestType = "success";}
		logger.debug("SiteMinderEmulatorAction::Request Type:- " + requestType);	
		ActionForward forwardAction = mapping.findForward(requestType);
		if (forwardAction != null) {
			logger.debug("SiteMinderEmulatorAction::ActionForward:- " + forwardAction.toString());	
		} else {
			logger.debug("SiteMinderEmulatorAction::ActionForward:- null");	
		}
		return forwardAction;
	}
	
}

