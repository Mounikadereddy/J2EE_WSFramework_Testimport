<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<!--	=======================================================================================================
	Author: Pramit Shah
	Date: 06/07/2018
	Error Page for CSS Authorization Errors. When an error reaches this page usually the IAM authentication has been
	successful, but an authorization error has been found for the user trying to access the intended application. 
	=======================================================================================================-->

<html lang="en" xmlns="http://www.w3.org/1999/xhtml">

<head>
<title>Veterans Benefits Administration (VBA) (U.S. Department
	of Veterans Affairs)</title>
<script>
	window.history.forward(1);
	if ((typeof (window.opener) != "undefined") && !(window.opener.closed)) {
		window.close();
		window.opener.location.replace("/");
	}
</script>

<script>

	/**
	 * Does a cursory form validation and then submits if all fields pass validation
	 */
	
	
	function submitForm() {

		var stationId = document.getElementById("stationId").value;
		var overrideStationId = document.getElementById("overrideStationId").value;
		var testUserId = document.getElementById("testUserId").value;

		if (validation()) {// Calling validation function
			document.userOverrideForm.submit();
		}

		function validation() {
			var stationId = document.getElementById("stationId").value;
			var overrideStationId = document.getElementById("overrideStationId").value;
			var testUserId = document.getElementById("testUserId").value;

			if (document.userOverrideForm.overrideStationId.value.length != 3) {
				alert('Station ID must be 3 characters');
				document.userOverrideForm.overrideStationId.focus();
				return false;
			} else if (document.userOverrideForm.testUserId.value.length >= 1 && document.userOverrideForm.testUserId.value.length < 5) {
				alert('User Id must be at least 5 characters');
				document.userOverrideForm.testUserId.focus();
				return false;
			} else {
				return true;
			}
		}
	}
		
		function stationIdSubmit() {
			if (event.keyCode === 13) {
    			event.preventDefault();
    			submitForm();
		  	}
		}
		
	function enableButton() {
		if (btnelem.disabled) {
			var selectelem = document.getElementById('stationId');
			var btnelem = document.getElementById('submitBtn');
			btnelem.disabled = !selectelem.value;
		}
	}	
</script>

<script type="text/javascript"
	src="<c:url value="/resources/core/js/jquery.min.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/resources/core/js/bootstrap.min.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/resources/core/js/bootstrap-select.min.js"/>"></script>

<meta charset="utf-8" />
<meta content="text/html" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta name="TitleAlternative"
	content="VBA Common Security Services Authorization" />
<meta name="Description"
	content="This page displays errors identified after a succesfull authentication due to authorization business rules for the user and application." />
<meta name="DateCreated" content="201704013" />
<meta name="DateReviewed" content="20170413" />

<spring:url value="/resources/core/css/centrallogin.css"
	var="centralLoginCss" />
<spring:url value="/resources/core/css/hello.css" var="coreCss" />
<spring:url value="/resources/core/css/bootstrap.min.css"
	var="bootstrapCss" />
<spring:url value="/resources/core/css/bootstrap-select.min.css"
	var="bootstrapSelectCss" />
<link href="${bootstrapCss}" type="text/css" rel="stylesheet" />
<link href="${bootstrapSelectCss}" type="text/css" rel="stylesheet" />
<link href="${coreCss}" rel="stylesheet" />
<link href="${centralLoginCss}" type="text/css" rel="stylesheet" />

</head>

<body>

	<div class="container-fluid">

		<div id="skiptocontent" role="navigation">
			<a href="#maincontent" class="skiptocontent">skip to main content</a>
		</div>

		<!--Page Header-->
		<div id="page-header">
			<div class="row mstr-header-logo-row">
				<div class="col-md-6">
					<div>
						<img class="img-responsive"
							src="<c:url value="/resources/images/cl-va-logo-transparent.png"/>"
							alt="VA Logo" />
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col-md-12">
					<nav class="breadcrumb i5breadcrumb" role="navigation" >
                      <p style="color: white;  padding-right: 60px; display:inline; " >VA Single Sign-On: CSS Authorization</p>
                      <a style="color: white;  padding-right: 60px; display:inline;" href="#" role="button" data-toggle="modal" data-target="#about-modal">User Registration</a>
                      <p style="color: white;  padding-right: 60px; display:inline;" >${date},Version ${version}</p>
                      ${isTestEnv ? '<p style="color: white;  padding-right: 60px; display:inline;" >TESTING ENVIRONMENT</p>':''}
                    </nav>
				</div>
			</div>
		</div>


				<spring:url value="/stationSelectionUI" var="userActionUrl" />
				<spring:url value="/testStationSelectionUI" var="testUserActionUrl" />

				<div id="maincontent">
					<div id="ContentBody">

						<div class="panel-body">
							
							<div class="row">
								<div class="col-md-6 col-md-offset-3">
									<h1 class="text-center">CSS Station Selection</h1>
									<br/> <br/>
									<form:form class="form-horizontal" method="post"
										modelAttribute="cssUser" action="${userActionUrl}">
										
										<spring:bind path="networkLoginName">
											<div class="form-group ${status.error ? 'has-error' : ''}">
												<label id="userIdLabel" class="col-md-4">User Id :</label>
												<div class="col-sm-5 col-md-offset-1">
													<form:input title="adSamAccountName" path="networkLoginName"
														type="text" class="form-control" id="networkLoginName" readonly="true"
													 />
													<form:errors path="networkLoginName" class="control-label" />
												</div>
												<div class="col-md-2">
								                    <button type="button" class="btn btn-default pull-right" data-toggle="modal" data-target="#userOverride">User Override</button>
								                </div>
											</div>
										</spring:bind>
										
										<input type="text" id="cssiam_target" hidden="true" value="${targetUrl}" name="cssiam_target">

										<div class="form-group">
											<label id="stationSelectLabel" class="col-md-4">Stations :</label>
											<div class="col-md-7 col-md-offset-1">
												<select aria-labelledby="stationSelectLabel" id="stationId" name="stationId" class="selectpicker" data-size="5" data-live-search="true" data-width="100%" onchange="enableButton()">
													<c:forEach var="station" items="${enabledUserStations}">
														<option value="${station.id}">${station.id} - ${station.name}</option>
													</c:forEach>
												</select>
											</div>
										</div>
										<c:if test="${!empty disabledUserStations}">
										<div class="form-group">
											<label class="col-md-4" style="color:#990000;">Unavailable Stations :</label>
										</div>
										
										<div class="row">
											<div class="col-md-12">
												<table class="table table-fixed">
													<!-- <thead>
														<tr>
															<th class="col-md-5">Stations</th>
															<th class="col-md-7">Messages</th>
														</tr>
													</thead> -->
													<tbody>
														<c:forEach var="station" items="${disabledUserStations}">
															<tr>
																<td class="col-md-5">${station.id} - ${station.name}</td>
																<td class="col-md-7"><c:out value="${reasonMessages[station.reasonCode]}" /></td>
															</tr>
														</c:forEach>
													</tbody>
												</table>
											</div>
										</div>
										</c:if>

										<div class="row">
											<c:choose>
											  <c:when test="${empty enabledUserStations}">
											    <button id="submitBtn" disabled="disabled" type="submit" class="col-md-offset-6 col-md-2 btn btn-default pull-right" style="margin-left:5px;"
												name="action" value="test">Submit</button>
											  </c:when>
											  <c:otherwise>
											    <button id="submitBtn" type="submit" class="col-md-offset-6 col-md-2 btn btn-default pull-right" style="margin-left:5px;"
													name="action" value="test">Submit</button>
											  </c:otherwise>
											</c:choose>
											<button type="submit" class="col-md-2 btn btn-default pull-right" 
											    name="action" value="exit">Exit</button>
										</div>
									</form:form>

								</div>
							</div>
							
							<p>&nbsp;</p>

							 <c:if test="${not empty errorMsg}">
								<div class="row">
									<div class="col-md-12" style="left: 470px;">
										<b class="red-error-text">Application Errors:</b> ${errorMsg}
									</div>
								</div>
							</c:if> 
						</div>

						<p>&nbsp;</p>
						<!-- Legal Warning-->
						<div class="row  mstr-legal-warning-row">
							<div class="col-xs-3 col-sm-2 col-md-2 col-lg-2">
								<p class="mstr-warning" role="contentinfo">WARNING:</p>
							</div>
							<div class="col-xs-7 col-sm-10 col-md-10 col-lg-10">
								<p role="contentinfo">You have accessed a United States
									Government computer. Unauthorized use of this computer is a
									violation of federal law and may subject you to civil and
									criminal penalties. This computer and automated systems which
									run on it, are monitored. Individuals are not guaranteed
									privacy while using government computers and should, therefore,
									not expect it. Communications made using this system may be
									disclosed as allowed by federal law.</p>
							</div>
						</div>
					</div>

					<p>&nbsp;</p>

					<!--Links-->
				</div>

				<!--Page Footer-->

				<footer id="footer">
					<div class="page-footer mstr-footer-div">
						<div class="row template-footer-row1">
							<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
								 <nav class="template-link-list" role="navigation" style="text-align: center; " >
                                    <p class="style-guide-footer-hyperlink" style=" padding-right: 40px; display:inline; "><a class="style-guide-footer-hyperlink" href="http://www.va.gov" >VAHOME</a></p>
                                    <p class="style-guide-footer-hyperlink" style=" padding-right: 40px; display:inline; "><a class="style-guide-footer-hyperlink" href="https://benefits.va.gov" >About VBA</a></p>
                                    <p class="style-guide-footer-hyperlink" style=" padding-right: 40px; display:inline; "><a class="style-guide-footer-hyperlink" href="http://www.va.gov/privacy" >PRIVACY</a></p>
                                    <p class="style-guide-footer-hyperlink" style=" padding-right: 40px; display:inline; "><a class="style-guide-footer-hyperlink" href="https://www.va.gov/disclaim.htm" >Disclaimer</a></p>
                                    <p class="style-guide-footer-hyperlink" style=" padding-right: 40px; display:inline; "><a class="style-guide-footer-hyperlink" href="http://www.foia.va.gov" >FOIA</a></p>
                                </nav>
							</div>
						</div>

						<div class="row template-footer-row2">
							<div class="col-lg-12 template-footer-align">
								<p class="style-guide-body-text-smallest-white">U.S.
									Department of Veterans Affairs | 810 Vermont Avenue, NW
									Washington DC 20420</p>
							</div>
						</div>
					</div>
				</footer>
	</div>

	<!-- Modal Markup kept out of all the div elements -->
	<div class="modal fade" id="contact-us-modal" role="dialog"
		tabindex="-1" aria-hidden="true" aria-labelledby="contactUsModalLabel">
		<div class="modal-dialog modal-dialog-sized">
			<div class="modal-content">
				<!-- Modal Header -->
				<div class="modal-header modal-header-va-blue">
					<button type="button" class="btn btn-default close modal-btn-x"
						data-dismiss="modal">
						<img
							src="<c:url value="/resources/images/cl-lightweight-modal-close-button.png"/>"
							alt="Close Modal" />
					</button>
					<div class="row">
						<div class="col-xs-6 col-sm-6">
							<img
								src="<c:url value="/resources/images/cl-va-logo-modal.png"/>"
								alt="VA Logo Modal">
						</div>
					</div>

				</div>

				<!-- Modal Body -->
				<div class="modal-body contact-body">

					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12">
							<b>Contact method:</b>
						</div>
					</div>

					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12">
							<span>- </span><b>Email:</b> <span>some_email@va.gov</span>
						</div>
					</div>
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12">
							<span>- </span><b>Phone:</b> <span>Some Phone Number</span>
						</div>
					</div>
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12 col-margin-top">
							Some message in here 1</div>
					</div>

					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12 col-margin-top">
							Some message in here 2</div>
					</div>

				</div>
				<!-- Modal Footer -->
				<div class="modal-footer modal-footer-padding">
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12 col-margin-top">
							<button type="button"
								class="btn-default widget-confirm-redirect-cancel-button"
								style="float: left;" data-dismiss="modal">Back</button>
						</div>
					</div>
				</div>

			</div>
		</div>
	</div>

	<!-- Modal Markup kept out of all the div elements -->
	<div class="modal fade" id="about-modal" role="dialog" tabindex="-1"
		aria-hidden="true" aria-labelledby="aboutModalLabel">
		<div class="modal-dialog modal-dialog-sized">
			<div class="modal-content">
				<!-- Modal Header -->
				<div class="modal-header modal-header-va-blue">
					<button type="button" class="btn btn-default close modal-btn-x"
						data-dismiss="modal">
						<img
							src="<c:url value="/resources/images/cl-lightweight-modal-close-button.png"/>"
							alt="Close Modal" />
					</button>
					<div class="row">
						<div class="col-xs-6 col-sm-6">
							<img
								src="<c:url value="/resources/images/cl-va-logo-modal.png"/>"
								alt="VA Logo Modal">
						</div>
					</div>
				</div>

				<div class="modal-body contact-body">
							<b>In order to gain access to VBA Enterprise Applications, please follow these instructions:</b>
							<ul>
								<li>
									For internal employees:
									<ol>
										<li>A designated Requesting Official (supervisor) should complete and sign a VAF20-8824e Common Security Services (CSS) User Access Request form and submit it to the local ISO for processing.</li>
										<li>A designated Approving Official and/or ISO must sign the access request form to approve access to the application.</li>
										<li>The user must complete all the mandated security awareness and privacy training, and sign a Rules of Behavior (ROB) and a Statement of Commitment and Understanding prior to accessing the system.</li>
									</ol>
								</li>
								<li>
									For external users:
									<ul>
										<li>See your sponsor for specific instructions.</li>
									</ul>
								</li>
							</ul>
				</div>
					
					
				<!-- Modal Footer -->
				<div class="modal-footer modal-footer-padding">
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12 col-margin-top">
							<button type="button"
								class="btn-default widget-confirm-redirect-cancel-button"
								style="float: left;" data-dismiss="modal">Back</button>
						</div>
					</div>
				</div>

			</div>
		</div>
	</div>
	
    <div class="modal fade" id="userOverride" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" style="top: 50px;">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title" id="myModalLabel">Login with a Different CSS User</h4>
          </div>
          <form:form name="userOverrideForm" id="userOverrideForm" class="form-horizontal" method="post" modelAttribute="cssUser" action="${testUserActionUrl}">
	          <div class="modal-body">
	            <div class="row">
	              <div class="col-md-8 col-md-offset-2">
	                  <div class="form-group">
	                    <label id="overrideUserIdLabel" class="col-md-4">User ID :</label>
	                    <div class="col-md-7 col-md-offset-1">
	                      <form:input title="adSamAccountName" path="networkLoginName" type="text" class="form-control" id="overrideNetworkLoginName" readonly="true" />
	                    </div>
	                  </div>
	                  <div class="form-group">
	                    <label id="overrideTestUserIdLabel" class="col-md-4">Test User ID :</label>
	                    <div class="col-md-7 col-md-offset-1">
	                      <input aria-labelledby="overrideTestUserIdLabel" type="text" class="form-control" id="testUserId" name="testUserId" onkeyup="this.value = this.value.toUpperCase();"/>
	                    </div>
	                  </div>
	                  <div class="form-group">
	                    <label id="overrideStationIdLabel" class="col-md-4">Station ID :</label>
	                    <div class="col-md-3 col-md-offset-1">
	                      <input aria-labelledby="overrideStationIdLabel" type="text" class="form-control" id="overrideStationId" maxlength="3" name="overrideStationId" onkeyup="stationIdSubmit()" onfocusout="stationIdSubmit()" onfocusin="stationIdSubmit()"/>
	                    </div>
	                  </div>
	                  <input type="text" id="override_cssiam_target" hidden="true" value="${targetUrl}" name="override_cssiam_target" />
	                  <form:hidden path="userApplication" />
	              </div>
	            </div>
	          </div>
	          <div class="modal-footer">
	            <button type="button" class="btn btn-default" data-dismiss="modal">Back</button>
	            <button type="button" class="btn btn-default" value ="submit" onClick="submitForm();">Submit</button>
	          </div>
          </form:form>
        </div>
      </div>
    </div>
    
    <c:if test="${not empty errorMsgFromFilter}">
		<div class="modal fade" id="errorModal" tabindex="-1" role="dialog" style="top: 50px;">
		    <div class="modal-dialog" role="document">
		      <div class="modal-content">
		        <div class="modal-header">
		        	<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        	<h4 class="modal-title">ERROR</h4>
		        </div>
		        <div class="modal-body">
		        	<p>${errorMsgFromFilter}</p>
		        </div>
		        <div class="modal-footer">
		        	<button type="button" class="btn btn-default" data-dismiss="modal">OK</button>
		        </div>
		      </div>
		    </div>
		</div>
		<script type="text/javascript">
			$('#errorModal').modal('show');
		</script>
	</c:if>

</body>
</html>