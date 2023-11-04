<!--
=======================================================================================================
This page redirects the user back to the URL specified by SMPORTALURL query parameter. This page is secured
by the PS so that the user gets authenticated when redirected to this page by the WA.

Author: Mario Rodrigues, EO/SDE SiteMinder
Date:     1/27/2017
=======================================================================================================               
-->

<%@ page session="false" %>
<%@ page import="java.util.Enumeration, java.util.*, java.net.*, java.nio.charset.*, org.apache.http.*, org.apache.http.client.utils.*, org.apache.http.message.*, gov.va.vba.framework.logging.*" %>
<%!
	static Logger logger=Logger.getLogger("gov.va.vba.framework.REDIRECT_JSP"); 	
	
	private static final String PORTAL_URL = "SMPORTALURL";
	
	String decodedTarget  = null;
	String target = null;
	String fullPortalReqURL  = null;
	
	private void logDebug(String message) {
		if (logger.isDebugEnabled()) {
			logger.debug(message);
		}
	}
%>
<%
	logDebug("Redirect.jsp::Req query string: "+request.getQueryString());   
	//Accesed without a Query String   
	if((null == request.getQueryString()) || request.getQueryString().trim().length() == 0) {
		logDebug("Redirect.jsp: No query string. Exiting with: "+HttpServletResponse.SC_BAD_REQUEST);  
	    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "<H2> Bad Request </H2><BR><BR>This page should not be accessed directly");
	}
	else {
		target = new StringBuffer(request.getParameter("TARGET")).append("&").append("stationId=").append(request.getParameter("stationId")).
			append("&").append("profileId=").append(request.getParameter("profileId")).toString();//need to clean this up?
	    logDebug("\nRedirect.jsp:SM TARGET: "+target);                   
	    if(target == null || target.trim().length() == 0) {
			logDebug("\nRedirect.jsp: SM_TARGET not found. Exiting with: "+HttpServletResponse.SC_BAD_REQUEST);  
	        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "<H2> Bad Request </H2><BR><BR>SM TARGET query parameter could not be found.");
	    }
	    else {//process target type
	    	logDebug("Redirecting to bep framework....");
	    	decodedTarget = URLDecoder.decode(target, "UTF-8");
		    logDebug("Redirect.jsp: SUCCESS. Redirecting to target: "+decodedTarget);           	
		    response.sendRedirect(target);

	    }
	}
%>