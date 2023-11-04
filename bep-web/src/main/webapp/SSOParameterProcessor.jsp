<!--
	=======================================================================================================
	Pass-through page that unpacks SSO params and then resubmits the request to FWS
	user.
	
	Author: Mario Rodrigues
	Date: 	5/1/2011
	=======================================================================================================	
-->
<%@ page import="java.util.Enumeration,  java.util.HashMap,  java.util.Map, java.util.Set, java.util.Iterator, 
					java.net.URLEncoder, java.net.URI, java.security.*" 
		 session="false" %>			
	
<%!
	private static final String PORTAL_URL = "SMPORTALURL";
	private static final String PORTAL_STATE = "SMPORTALSTATE";
%>
<html>
   <head>
      <title>Page for auto submitting SSO parameters</title>
   </head>
   <body onLoad="document.forms[0].submit()">
      <%String portalURL = request.getParameter(PORTAL_URL);%>
      <form method="POST" action="<%=portalURL%>">
      	<%
     	Enumeration paramNames = request.getParameterNames();
     	while(paramNames.hasMoreElements()) {
        	String name = (String)paramNames.nextElement();
         	String value = request.getParameter(name);
         	if (!name.equals(PORTAL_URL)) {
			%>
      		<input type="hidden" name="<%=name%>" value="<%=(name == PORTAL_STATE?URLEncoder.encode(value, "UTF-8"):value)%>" />
			<%
    		}
		}
     	%>   
     	<input type="hidden" name="stationId" value="<%=request.getAttribute("stationId")%>"/>
     	<input type="hidden" name="profileId" value="<%=request.getAttribute("profileId")%>"/>
         <%System.out.println("\nSSO JSP submitting to:"+portalURL);%>
      </form>
   </body>
</html>