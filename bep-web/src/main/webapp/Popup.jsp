<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!--
	=======================================================================================================
	Pass-through page that serves as a temporary placeholder to display messages to the
	user.
	
	Author: Mario Rodrigues
	Date: 	9/15/2006
	=======================================================================================================	
-->
<%@ page session="false"%>
<html>
	<head>
	  <script language="javascript">	  
	  	function showMessage() {
			alert("<%=request.getAttribute("message")%>");
			location.href = document.referrer;
			//document.forms[0].action = document.referrer;
			return document.forms[0].submit();
		}
	  </script>	 	
	</head>
	<body onload="showMessage()">
	  <form method="POST">
 	    <font face="Courier New">	  
			<script language="javascript">
				//document.write(document.referrer);
			</script>
		</font>
	  </form>		
	</body>
</html>
