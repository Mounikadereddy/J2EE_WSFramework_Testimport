<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=iso-8859-1" language="java"
	errorPage=""%>
<%@ page session="false"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>VBA SiteMinder Prototype</title>
<link rel="stylesheet" href="styles/emx_nav_left.css" type="text/css" />
<script type="text/javascript">
	
</script>
</head>
<body>
	<div id="masthead">
		<h1 id="siteName">VBA SiteMinder Emulator</h1>
	</div>
	<div id="pagecell1">
		<!--pagecell1-->
		<div id="pageName">
			<!--<img alt="small logo" src="images/va_seal.gif"/> -->
		</div>
		<html:errors />
		<div id="content">
			<div class="story">
				<html:form method="post" action="siteMinderEmulator.do">
					<table align="center" bgcolor="#EAEAEA" width="90%" border=0
						cellpadding="8" cellspacing="0">
						<tr>
							<td width="30%"><html:radio property="requesttype" value="saml" /> SAML</td>
						</tr>
						<tr>
							<td width="30%"><html:radio property="requesttype" value="nonsaml" /> Non-SAML</td>
						</tr>
						<tr>
							<td align="center"><html:submit
									value="Submit Choice" /></td>
						</tr>
					</table>
				</html:form>
			</div>
		</div>
	</div>
</body>
</html:html>
