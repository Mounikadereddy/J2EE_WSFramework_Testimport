<html>

  <head>
	  <meta http-equiv="Content-Type" content="text/html;CHARSET=iso-8859-1">
	  <META HTTP-EQUIV="Pragma" CONTENT="no-cache">
	  <META HTTP-EQUIV="Expires" CONTENT="-1">
	  <META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
	  <title>Snoop Servlet</title>
  </head>
  <body bgcolor="#ffffff" link="#3366cc" vlink="#9999cc" alink="#0000cc">

	<%@ page import="java.util.*, java.io.PrintWriter, javax.servlet.http.*, gov.va.vba.framework.web.security.UserContext"%>
	<%@ page session="false"%>
	<%!
	  public String encodeXSS(String str) {
	   return weblogic.servlet.security.Utils.encodeXSS(str);
	  }
	%>
	
	<%
		try {		
	%>
				<p>
					Servlet that returns information about the HTTP request. 
					Created by: Mario Rodrigues, 2/10/2006
	      		</p>
				<br>
				<br>
				<br>
				<a href="/logout">Server-wide Logout</a>
				<br>				
				<a href="SnoopServlet.jsp?value=logout">Servlet Logout</a>
				<br>
				<a href="/framework/logout">FW/SM Logout</a>
				<br>
				<a href="/framework/logoff">FW/SM Logoff</a>
				<br>				
				<a href="/framework/Registration.html">Test crap</a>	
				<br>				
				<a href="/">Home</a>
	
				<h3>
				Servlet Spec Version Implemented
				</h3>
				<pre>
				<%= getServletConfig().getServletContext().getMajorVersion() + "." + getServletConfig().getServletContext().getMinorVersion() %>
				</pre>
				<h3>
				Requested URL
				</h3>
				<pre>
				<%= request.getRequestURL().toString() %>
				</pre>
				<h3>
				Request parameters
				</h3>
				<pre>
	<%
	
				Enumeration enum_ = request.getParameterNames();
				while(enum_.hasMoreElements()){
				  String key = (String)enum_.nextElement();
				  String[] paramValues = request.getParameterValues(key);
				  for(int i=0;i < paramValues.length;i++){
				      out.println(key + " : "  + encodeXSS(paramValues[i]));
				  }
				}
	
	%>
				</pre>
				<h3>
				Request information
				</h3>
				<pre>
				Request Method: <%= request.getMethod() %>
				Request URI: <%= request.getRequestURI() %>
				Request Protocol: <%= request.getProtocol() %>
				Servlet Path: <%= request.getServletPath() %>
				Path Info: <%= request.getPathInfo() %>
				Path Translated: <%= request.getPathTranslated() %>
				Query String: <%= encodeXSS(request.getQueryString()) %>
				Content Length: <%= request.getContentLength() %>
				Content Type: <%= request.getContentType() %>
				Server Name: <%= request.getServerName() %>
				Server Port: <%= request.getServerPort() %>
				Remote User: <%= request.getRemoteUser() %>
				Remote Address: <%= request.getRemoteAddr() %>
				Remote Host: <%= request.getRemoteHost() %>
				Authorization Scheme: <%= request.getAuthType() %>
				</pre>
				<h3>Certificate Information</h3>
				<pre>
	<%
				java.security.cert.X509Certificate certs [];
				certs = (java.security.cert.X509Certificate [])
				request.getAttribute("javax.servlet.request.X509Certificate");
				if ((certs != null) && (certs.length > 0)) {
	%>
					Subject Name : <%= certs[0].getSubjectDN().getName() %> <br>
					Issuer Name :<%= certs[0].getIssuerDN().getName() %> <br>
					Certificate Chain Length : <%= certs.length %> <br>
	<%
	
	      // List the Certificate chain
	      for (int i=0; i<certs.length;i++) {
	%>  Certificate[<%= i %>] : <%= certs[i].toString() %>
	
	<%
					} // end of for loop
	
				}
				else // certs==null
					{
	%>
					Not using SSL or client certificate not required.
	<%
					} // end of else
	%>
				</pre>
				<h3>
				Request headers
				</h3>
				<pre>
	<%
				enum_ = request.getHeaderNames();
				while (enum_.hasMoreElements()) {
					String name = (String)enum_.nextElement();
					out.println(name + ": " +encodeXSS(request.getHeader(name)));
				}
	%>
				</pre>
			</td>
		</tr>
	<%
		}
		catch (Exception ex) {
			ex.printStackTrace(new PrintWriter(out));
		}
	%>
	
	<h3>
	Cookies
	</h3>
	<pre>
	<%
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for(int i=0;i < cookies.length;i++) {
				out.println("Name : "  + encodeXSS(cookies[i].getName()));
				out.println("Value : "  + encodeXSS(cookies[i].getValue()));
				out.println("Path : "  + encodeXSS(cookies[i].getPath()));	
				out.println("<br>");			
			}	
		}
	%>
	</pre>
  </body>
</html>