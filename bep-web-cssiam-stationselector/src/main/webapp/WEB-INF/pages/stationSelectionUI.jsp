<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<!--	=======================================================================================================
	Author: Ivan Vanegas
	Date: 04/13/2017
	Error Page for CSS Authorization Errors. When an error reaches this page usually the IAM authentication has been
	successful, but an authorization error has been found for the user trying to access the intended application. 
	=======================================================================================================-->

<html lang="en" xmlns="http://www.w3.org/1999/xhtml">

<head>
<title>CSS Station Selection Veterans Benefits Administration (VBA) (U.S. Department
	of Veterans Affairs)</title>
<script>
	window.history.forward(1);
	if ((typeof (window.opener) != "undefined") && !(window.opener.closed)) {
		window.close();
		window.opener.location.replace("/");
	}
</script>
<script>
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
							alt="V A seal" />
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col-md-12">
                    <div class="breadcrumb i5breadcrumb">
                      <p style="color: white;  padding-right: 60px; display:inline; " >VA Single Sign-On: CSS Authorization</p>
                      <a style="color: white;  padding-right: 60px; display:inline;" href="#" role="button" data-toggle="modal" data-target="#about-modal">User Registration</a>
                      <p style="color: white;  padding-right: 60px; display:inline;" >${date},Version ${version}</p>
                     <div style =" float:right; ">
                         ${isTestEnv ? '<p style="color: white;  padding-right: 200px; display:inline;" >TESTING ENVIRONMENT</p>':''}
                     </div>
                    </div>
				</div>
			</div>
		</div>


				<spring:url value="/stationSelectionUI" var="userActionUrl" />

				<div id="maincontent">
					<div id="ContentBody">

						<div class="panel-body">
							<div class="row">
								<div class="col-md-12">
									<br/>
									<p>
										<div class="info_header" style="text-align:center;">CSS Station Selection</div>
										<br/>
									</p>
								</div>
							</div>
							
							<div class="row">
								<div class="col-md-6 col-md-offset-3">
									<form:form class="form-horizontal" method="post"
										modelAttribute="cssUser" action="${userActionUrl}">

										<spring:bind path="networkLoginName">
											<div class="form-group ${status.error ? 'has-error' : ''}">
												<label class="col-md-4">User Id :</label>
												<div class="col-md-7 col-md-offset-1">
													<form:input path="networkLoginName"
														type="text" class="form-control" id="networkLoginName" readonly="true"
													 />
													<form:errors path="networkLoginName" class="control-label" />
												</div>
											</div>
										</spring:bind>
										
										<input type="text" id="cssiam_target" hidden="true" value="${targetUrl}" name="cssiam_target">

										<div class="form-group" aria-live="polite">
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
										<!-- Disable the submit button when enabledUserStations is empty. 
											When not empty, it defaults to first option and so submit is possible. -->
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

							<c:if test="${not empty errorMsg}">
								<div class="row">
									<div class="col-md-12">
										<b class="red-error-text">Application Errors:</b> ${errorMsg}
									</div>
								</div>
							</c:if>
						</div>

						<p>&nbsp;</p>
						<!-- Legal Warning-->
						<div class="row  mstr-legal-warning-row">
							<div class="col-xs-3 col-sm-2 col-md-2 col-lg-2">
								<h3 class="text-center">WARNING:</h3>
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
                                    <p class="style-guide-footer-hyperlink" style=" padding-right: 40px; display:inline; "><a class="style-guide-footer-hyperlink" href="http://www.va.gov" >VA HOME</a></p>
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
								alt="V A seal">
						</div>
					</div>
				</div>

				<div class="modal-body contact-body">
							<h3 class="text-center">In order to gain access to VBA Enterprise Applications, please follow these instructions:</h3>
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

</body>
</html>
