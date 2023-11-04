<!--
	=======================================================================================================
	Pass-through page that submits to a redirect URL. The reason this is used is so that FW security code is 
	not cluttered with redirect logic especially when it pertains to external URL's.
	
	Author: Mario Rodrigues
	Date: 	9/24/2012
	=======================================================================================================	
-->
<%@ page import="java.util.Enumeration,  java.util.HashMap,  java.util.Map, java.util.Set, java.util.Iterator, 
					java.net.URLEncoder, java.net.URI, java.security.*" 
		 session="false" %>			
	
<html>
   <head>
      <title>Auto Redirector</title>
   </head>
   <body onLoad="document.forms[0].submit()">
   	   <%
   	   	System.out.println("Auto Redirector JSP: Redirecting to: "+request.getParameter("redirect"));
   	   	%>      
      <form method="GET" action="<%=request.getParameter("redirect")%>">
      	<input type="hidden" name="redirect" value="<%=request.getParameter("redirect")%>"/>
      <form/>
   </body>
</html>