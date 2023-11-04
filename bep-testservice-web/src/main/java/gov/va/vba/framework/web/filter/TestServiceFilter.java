/* 
 * TestServiceFilter.java
 *
 * Copyright 2006 U.S. Dept Of Veterans Affairs.
 * U.S. Government PROPRIETARY/CONFIDENTIAL. Use is subject to security terms.
 */
package gov.va.vba.framework.web.filter;


import gov.va.vba.framework.auditing.LoginAuditer;
import gov.va.vba.framework.auditing.LogoutAuditer;
import gov.va.vba.framework.common.SystemUtils;
import gov.va.vba.framework.domain.vo.ServiceVO;
import gov.va.vba.framework.esb.transformers.*;
import gov.va.vba.framework.exceptions.AuditException;
import gov.va.vba.framework.exceptions.handler.WebExceptionHandler;
import gov.va.vba.framework.logging.*;
import gov.va.vba.framework.services.*;
import gov.va.vba.framework.web.security.BaseFilter;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Arrays;
import java.util.Set;
import java.util.StringTokenizer;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import gov.va.vba.framework.common.StringUtils;

public final class TestServiceFilter extends BaseFilter implements Filter {
	
	private final static Logger logger=Logger.getLogger(TestServiceFilter.class.getName());
	
	private FilterConfig _filterConfig;


	/**
	 * Per servlet 2.5 spec - blank constructor must be provided.
	 * 
	 * @param
	 * @return
	 * @exception 
	 */
	public TestServiceFilter() {
	}

	/**
	 * All instance scoped object initializations done here.
	 *  
	 * @param	FilterConfig
	 * @return	
	 * @exception ServletException
	 */
	public void init(FilterConfig filterConfig) throws ServletException {
		
		Context ctx = null;

		this._filterConfig = filterConfig;
	    	
	}

	/**
	 * Release resources; speed up garbage collection.
	 * 
	 * @param
	 * @return
	 * @exception
	 */
	public void destroy() {

		this._filterConfig = null;
	}

	/**
	 * Main service method that handles/intercepts all client requests. Checks to
	 * see if a logout is requested. If so, all sessions and SiteMinder/Java cookies 
	 * are killed and the client is redirected to the VBA home page
	 * 
	 * @param
	 * @return
	 * @exception
	 */
	public void doFilter(ServletRequest sreq, ServletResponse sresp,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest)sreq;
		HttpServletResponse resp = (HttpServletResponse)sresp;
				

		logRequest(req);
		try {		
			if (_filterConfig == null)
				return;
			else {
				resp.addHeader("Cache-Control", "no-cache");
				resp.addHeader("Cache-Control", "no-store");		

				resp.addHeader("Pragma", "no-cache");
				resp.addDateHeader("Expires", 0);

				chain.doFilter(req, resp);
				logger.debug("Test Service Filter Completed");

			}
		}
		catch (Throwable t) {

		}
	}
	/**
	 * Logs the content of the HttpServletRequest to show the values that
	 * SiteMinder put on the request 
	 * 
	 * @throws
	 * @return
	 * @since October 14, 2016
	 */
	@SuppressWarnings("unchecked")
	static void logRequest(HttpServletRequest req) {
		String sessionId = req.getSession().getId();
		logger.debug("Framework Security Filter is processing a "+req.getMethod()+
				" request from "+req.getRequestURL().toString()+
				". Session ID: "+sessionId);
		String queryString = req.getQueryString();
		if (queryString == null) {
			logger.debug("This request has no query string.");
		} else {
			logger.debug("Request query string : "+queryString);
		}
		Map<String,String[]> parameterMap =  (Map<String,String[]>)req.getParameterMap();
		if (parameterMap != null && !parameterMap.isEmpty()) {
			Set<String> parameterKeys = parameterMap.keySet();
			for (String parameterKey : parameterKeys) {
				String parameterValue = req.getParameter(parameterKey);
				logger.debug("Request parameter "+parameterKey+" has value : "+parameterValue);
			}
		} else {
			logger.debug("This request has no parameters.");			
		}
		Enumeration<String> attributeNames = req.getAttributeNames();
		if (attributeNames.hasMoreElements()) {
			while (attributeNames.hasMoreElements()) {
				String attributeName = attributeNames.nextElement();
				logger.debug("Request attribute "+attributeName+" has value : "+req.getAttribute(attributeName));
			}
		} else {
			logger.debug("This request has no attributes.");			
		}

		Enumeration<String> headerNames = req.getHeaderNames();
		if (headerNames.hasMoreElements()) {
			while (headerNames.hasMoreElements()) {
				String headerName = headerNames.nextElement();
				logger.debug("Request header "+headerName+" has value : "+req.getHeader(headerName));
			}
		} else {
			logger.debug("This request has no headers.");			
		}

		Cookie[] cookies = req.getCookies();
		if (cookies == null) {
			logger.debug("This request has no cookies.");						
		} else {
			for (Cookie cookie : cookies) {
				logger.debug("Request cookie "+ cookie.getName()+" information follows:");
				logger.debug("Cookie Value   : "+cookie.getValue());
				logger.debug("Cookie Domain  : "+cookie.getDomain());
				logger.debug("Cookie Path    : "+cookie.getPath());
				logger.debug("Cookie Max Age : "+cookie.getMaxAge());
				logger.debug("Cookie Version : "+cookie.getVersion());
				logger.debug("Cookie Comment : "+cookie.getComment());
			}
		}
	}
	

}