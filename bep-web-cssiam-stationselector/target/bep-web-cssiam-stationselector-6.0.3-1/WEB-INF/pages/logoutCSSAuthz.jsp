<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!--	=======================================================================================================
	Author: Ivan Vanegas
	Date: 06/14/2018
	Log out Page for CSS Authorization.  
	=======================================================================================================-->

<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Veterans Benefits Administration (VBA) (U.S. Department
	of Veterans Affairs)</title>
<script language=JavaScript>
	window.history.forward(1);
	if ((typeof (window.opener) != "undefined") && !(window.opener.closed)) {
		window.close();
		window.opener.location.replace("/");
	}
</script>

<meta charset="utf-8" />
<meta content="text/html">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta name=TitleAlternative
	content="VBA Common Security Services Auhtorization" />
<meta name=Description
	content="This page is display after a succesfull log out of the CSS Authorization Module." />
<meta scheme="Language Codes" name=Language content=en />
<meta name=DateCreated content=201704013 />
<meta name=DateReviewed content=20170413 />
<meta scheme="Resource Type" name=Type content=Homepage />

<spring:url value="/resources/core/css/bootstrap.min.css"
	var="bootstrapCss" />
<link href="${bootstrapCss}" type="text/css" rel="stylesheet" />

<spring:url value="/resources/core/css/centrallogin.css"
	var="centralLoginCss" />
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
						<img class="img-responsive" role="complementary"
							src="<c:url value="/resources/images/cl-va-logo-transparent.png"/>" alt="VA Logo" />
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col-md-12">
					<div class="breadcrumb i5breadcrumb">
						<font style="color:white; margin-left:15px;">
							VA Single Sign-On: CSS Authorization |
							${date}, Version ${version} |
							${isTestEnv ? '<b>TESTING ENVIRONMENT</b> | ':''}
							<a href="#" role="button" data-toggle="modal" data-target="#about-modal">About</a> |
							<a href="#" role="button" data-toggle="modal" data-target="#contact-us-modal">Contact Us</a>
						</font>
					</div>
				</div>
			</div>
		</div>

		<div id="maincontent">

			<div id="ContentBody">

				<div class="panel-body info_panel_body">

					<div class="row">
						<div class="col-md-12">
							<br />
							<p>
								<div class="info_header">CSS Authorization Module</div>
								<br />
							</p>
						</div>
					</div>

					<c:if test="${not empty errorMsg}">
						<div class="row">
							<div class="col-md-12">
								<b class="red-error-text">Application Errors:</b> ${errorMsg}
							</div>
						</div>
						<br/>
					</c:if>

					<div class="row">
						<div class="col-md-12">
							<c:if test="${not empty logoutMessage}">
								<div class="row">
									<div class="col-md-12">
										${logoutMessage}
									</div>
							</div>
							</c:if>
							In order to logout of the SSOi session, please follow the link below or exit your browser.
							<br /> 
							<br />
							<a href='${ssoiLogoutURL}'>SSOi session logout</a><br />
							<br />
							If you need additional assistance, please contact
							the National Service Desk Support at
							855-673-4357 or email at ITSC@va.gov.
						</div>
					</div>
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
							criminal penalties. This computer and automated systems which run
							on it, are monitored. Individuals are not guaranteed privacy
							while using government computers and should, therefore, not
							expect it. Communications made using this system may be disclosed
							as allowed by federal law.</p>
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
					<ul class="template-link-list">
						<!--style on LI tag for the divider bars and on A tag for the link itself-->
						<li class="style-guide-footer-hyperlink"><a
							class="style-guide-footer-hyperlink" href="http://www.va.gov">VA
								HOME</a></li>
						<li class="style-guide-footer-hyperlink"><a
							class="style-guide-footer-hyperlink"
							href="http://www.va.gov/privacy">PRIVACY</a></li>
						<li class="style-guide-footer-hyperlink"><a
							class="style-guide-footer-hyperlink"
							href="http://www.foia.va.gov">FOIA</a></li>
					</ul>
				</div>
			</div>

			<div class="row template-footer-row2">
				<div class="col-lg-12 template-footer-align">
					<p class="style-guide-body-text-smallest-white">U.S. Department
						of Veterans Affairs | 810 Vermont Avenue, NW Washington DC 20420</p>
				</div>
			</div>
		</div>
		</footer>

	</div>

	<script type="text/javascript" src="<c:url value="/resources/core/js/jquery.min.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/core/js/bootstrap.min.js"/>"></script>

	<!-- Modal Markup kept out of all the div elements -->
	<div class="modal fade" id="contact-us-modal" role="dialog"
		tabindex="-1" aria-hidden="true" aria-labelledby="contactUsModalLabel">
		<div class="modal-dialog modal-dialog-sized">
			<div class="modal-content">
				<!-- Modal Header -->
				<div class="modal-header modal-header-va-blue">
					<button type="button" class="btn btn-default close modal-btn-x"
						data-dismiss="modal">
						<img src="<c:url value="/resources/images/cl-lightweight-modal-close-button.png"/>"
							alt="Close Modal" />
					</button>
					<div class="row">
						<div class="col-xs-6 col-sm-6">
							<img src="<c:url value="/resources/images/cl-va-logo-modal.png"/>"
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
							Some message in here 1
							</div>
					</div>

					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12 col-margin-top">
							Some message in here 2
						</div>
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
						<img src="<c:url value="/resources/images/cl-lightweight-modal-close-button.png"/>"
							alt="Close Modal" />
					</button>
					<div class="row">
						<div class="col-xs-6 col-sm-6">
							<img src="<c:url value="/resources/images/cl-va-logo-modal.png"/>"
								alt="VA Logo Modal">
						</div>
					</div>
				</div>

				<!-- Modal Body -->
				<div class="modal-body contact-body">

					<p>
						<span>Some message about 1</span>
					</p>
					<p>
						<span>Some message about 2</span>
					</p>

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
	
	<c:if test="${not empty errorMsg}">
		<div class="modal fade" id="errorModal" tabindex="-1" role="dialog" style="top: 50px;">
		    <div class="modal-dialog" role="document">
		      <div class="modal-content">
		        <div class="modal-header">
		        	<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        	<h4 class="modal-title">ERROR</h4>
		        </div>
		        <div class="modal-body">
		        	<p>${errorMsg}</p>
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