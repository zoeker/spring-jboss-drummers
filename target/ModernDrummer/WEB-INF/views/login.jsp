<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<!DOCTYPE html>

<html>
<head>
<title>Login</title>
<spring:url value="/static/resources/js/globals.js" var="globals" />
<spring:url value="/static/resources/js/am-util.js" var="utils" />
<spring:url value="/static/resources/css/moderndrummer.css" var="assignee" />
<spring:url value="/static/resources/css/drumchops.css" var="drumchops" />
<spring:url value="/static/resources/css/plugins/tabs/jstabs.css" var="jstabs" />
<link rel="stylesheet" type="text/css" href="<c:url value="/static/resources/css/moderndrummer.css"/>"/>


<link href="${drumchops}" rel="stylesheet" />
<link href="${jstabs}" rel="stylesheet" />
<link href="${assignee}" rel="stylesheet" />
<script src="${globals}"></script>
<script src="${utils}"></script>

<script src="http://code.jquery.com/jquery-latest.js"
	type="text/javascript"></script>
<script  href="<c:url value="/resources/js/globals.js"/>" src="resources/js/globals.js" type="text/javascript"></script>
<script href="<c:url value="/resources/js/am-util.js"/>" src="resources/js/am-util.js" type="text/javascript"></script>
<script  href="<c:url value="/resources/js/am-login.js"/>" src="resources/js/am-login.js" type="text/javascript"></script>
<script  href="<c:url value="/resources/js/plugins/tabs/jquery.hashchange.min.js"/>" src="resources/js/plugins/tabs/jquery.hashchange.min.js"
	type="text/javascript"></script>
<script href="<c:url value="/resources/js/plugins/tabs/jquery.easytabs.min.js"/>" src="resources/js/plugins/tabs/jquery.easytabs.min.js"
	type="text/javascript"></script>
<link href="<c:url value="/resources/css/moderndrummer.css"/>" type="text/css" href="resources/css/moderndrummer.css"
	rel="stylesheet" />
<link href="<c:url value="/resources/css/drumchops.css"/>" type="text/css" href="resources/css/drumchops.css"
	rel="stylesheet" />
<link href="<c:url value="/resources/css/plugins/tabs/jstabs.css"/>" rel="stylesheet" type="text/css"
	href="resources/css/plugins/tabs/jstabs.css" />
</head>
<body>



	<div id="pagewrap" class="pagewrap-width-95 ">
		<header id="header"> </header>
		<div id="content">

			<article class="post clearfix">
				<header>
					<figure class="post-image">
					</figure>
					<div id="wrapper" class="bg-img-drummer">
						<div id="login" class="animate form" style="height: 15em;">
							<!--<form name="loginForm" ACTION="Login" METHOD="POST">-->

							<form:form commandName="loginMember" id="log">


								<div align="center">
								
									<!--<div id=tab-container class=tab-container style="width: 100%;">
										<ul class='etabs'>
											<li class='tab'><a href="login#tabs1-login">Login</a></li>
											<li class='tab'><a href="register#tabs1-register">Register</a></li>
										</ul>
										<div id='tabs1-login' style="width: 100%;">-->

											<div class="errormessage">
												<form:errors path="*"></form:errors>
												<c:if test="${not empty errorMessage}">
  												${errorMessage}
												</c:if>
											</div>

											<div style="width:20%;margin-left:80%;" class="containerDiv-plain"
												align=center>
												<div id="rowDiv">
													<div id="cellDiv">
														<h3 align=center class="font-layout">Modern drummers with drumchops</h3>
													</div>
												</div>
												<div id="rowDiv">
													<div id="cellDiv">
														<h5 align=center class="font-layout" align=center>Keep
															grooving</h5>
													</div>
												</div>
											</div>
											<div class="errormessage"></div>
											<div style="width:20%;margin-left: 80%;" class="containerDiv rounded-10"
												align=center>

												<div class="rowDiv">
													<div class="cellDiv"
														style="float: left; padding-left: 15px;">
														<h5 class="font-layout">Login
													</div>
													<div class="cellDiv"></div>
												</div>
												<div class="rowDiv">
													<div class="cellDiv">
														<h5 class="font-layout">Username:
													</div>
													<div class="cellDiv">
														<form:input path="name" id="name"
															cssClass="inputhandler-classic-rnd" />

													</div>
												</div>
												<div class="rowDiv">
													<div class="cellDiv">
														<h5 class="font-layout">Password:
													</div>
													<div class="cellDiv">
														<!--<input type="password" name="password"
													class="inputhandler-classic-rnd" size=25>-->
														<form:password path="password" id="password"
															cssClass="inputhandler-classic-rnd" />
													</div>
												</div>
												<div class="rowDiv">
													<div class="cellDiv" id="login-center"></div>
													<div class="cellDiv" id="login-center">
														
															<input type="submit" class="assign-button"
																style="margin-left: 45%;" name="login" id="login"
																value="Log in">
														
													</div>
												</div>
											</div>

									<!-- </div>
										

									</div>-->
									<!-- end login tabs -->
									<!-- end tabs -->
								</div>
								<!-- end center -->


								<!--</form>-->
							</form:form>
						</div>
					</div>
				</header>
			</article>
		</div>
		<aside id="sidebar"></aside>
		<footer id="footer">
			<p></p>
		</footer>
	</div>



</body>
</html>
