<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=iso-8859-1" language="java"
	errorPage=""%>
<%@ page session="false"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="tiles"%>
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
			SAML First 
		</div>
		<html:errors />
		<div id="content">
			<div class="story">
				<html:form method="post" action="siteMinderEmulatorSAMLFirst.do">
					<table align="center" bgcolor="#EAEAEA" width="90%" border=0
						cellpadding="8" cellspacing="0">
						<tr>
							<td width="30%">
								<table align="center" bgcolor="#EAEAEA" width="100%" border=0
									cellpadding="8" cellspacing="0">
									<tr>
										<td width="30%" align="right"><b><font size="2.5" color="#336699">VA User
													Id:</font></b></td>
										<td align="left"><html:text property="userid" /></td>
									</tr>
									<tr>
										<td width="30%" align="right"><b><font size="2.5" color="#336699">Station
													Id:</font></b></td>
										<td align="left"><html:text property="station" /></td>
									</tr>
									<tr>
										<td width="30%" align="right"><b><font size="2.5" color="#336699">Appl.
													Name:</font></b></td>
										<td align="left"><html:text property="appName" /></td>
									</tr>
									<tr>
										<td width="30%" align="right"><b><font size="2.5" color="#336699">IP
													Addr:</font></b></td>
										<td align="left"><html:text property="ipAddr" /></td>
									</tr>
									<tr>
										<td width="30%" align="right"><hr></td>
										<td align="left"><hr></td>
									</tr>
									<tr>
										<td width="30%" align="right"><b><font size="2.5" color="#336699">Test User
													Id:</font></b></td>
										<td align="left"><html:text property="testuserid" /></td>
									</tr>
								</table>
							</td>
							<td>
								<table align="center" bgcolor="#EAEAEA" width="100%" border=0
									cellpadding="8" cellspacing="0">
									<tr>
										<td width="10%" align="right"><b><font size="2.5" color="#336699">ERRORMSG:</font></b></td>
										<td align="left"><html:textarea property="smusrmsg" style="background:#FFF8DC"
												readonly="true" rows="2" cols="70" /></td>
									</tr>
									<tr>
										<td width="10%" align="right"><b><font size="2.5" color="#336699">Cookies:</font></b></td>
										<td align="left"><html:textarea property="cookies" style="background:#FFF8DC"
												readonly="true" rows="2" cols="70" /></td>
									</tr>
									<tr>
										<td width="10%" align="right"><b><font size="2.5" color="#336699">Location:</font></b></td>
										<td align="left"><html:textarea property="location" style="background:#FFF8DC"
												readonly="true" rows="2" cols="70" /></td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<table width="90%" border=0 cellpadding="0" align="center">
						<tr>
							<td>
								<table width="100%" border=0 cellpadding="0" align="center">
									<tr>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<th bgcolor="#CC9966">RESPONSE HEADER</th>
									</tr>
									<tr>
										<td valign="top"><html:textarea property="headers" style="background:#FFF8DC"
												readonly="true" rows="10" cols="70" /></td>
									</tr>
									<tr>
										<td>&nbsp;</td>
									</tr>
								</table>
							</td>
							<td>
								<table width="100%" border=0 cellpadding="0" align="center">
									<tr>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<th bgcolor="#CC9966">REPLY MESSAGE</th>
									</tr>
									<tr>
										<td valign="top"><html:textarea property="reply" style="background:#FFF8DC"
												readonly="true" rows="10" cols="70"  /></td>
									</tr>
									<tr>
										<td>&nbsp;</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td align="center"><html:submit
									value="Execute SiteMinder Emulator" /></td>
						</tr>
					</table>
				</html:form>
			</div>
		</div>
	</div>
</body>
</html:html>
