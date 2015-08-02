<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>

<html>
<head>

<title>Search</title>
<script src="http://code.jquery.com/jquery-latest.js"
	type="text/javascript"></script>
<script src="<c:url value="/resources/js/globals.js"/>"
	type="text/javascript"></script>
<script src="<c:url value="/resources/js/am-util.js"/>"
	type="text/javascript"></script>
<script src="<c:url value="/resources/js/am-login.js"/>"
	type="text/javascript"></script>
<script language="JavaScript" type="text/javascript"
	src="<c:url value="/resources/js/protoplasm.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/datepicker/datepicker_src.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/timepicker/timepicker.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/datepicker/locales/en_US.js"/>"></script>

<link href="<c:url value="/resources/css/moderndrummer.css"/>" type="text/css" href="resources/css/moderndrummer.css"
	rel="stylesheet" />
<link type="text/css" href="<c:url value="/resources/css/moderndrummer-basic.css"/>"
	rel="stylesheet" />
<link type="text/css" href="<c:url value="/resources/css/moderndrummer-mediaqueries.css"/>"
	rel="stylesheet" />
<link type="text/css" href="<c:url value="/resources/css/moderndrummer-menu.css"/>"
	rel="stylesheet" />

<link href="<c:url value="/resources/css/plugins/tabs/jstabs.css"/>" rel="stylesheet" type="text/css"
	href="resources/css/plugins/tabs/jstabs.css" />
	

<script language="javascript">
  Protoplasm.use('datepicker').transform('input.datepicker').transform(
          'input.datepicker_en', {
            'locale': 'en_US'
          });
</script>
<script language="javascript">
  Protoplasm.use('timepicker').transform('input.timepicker', {
    use24hrs: true
  });
</script>
</head>
<body>



	<div id="pagewrap" class="pagewrap-width-95 ">
		<header id="header"> </header>
		<div id="content">
			<article class="post clearfix">
				<header>
					<figure class="post-image">
					</figure>
					<div id="wrapper">
						<div id="search" class="animate form" style="height: 15em;">
							<!--<form name="searchForm" ACTION="SearchPeriods" METHOD="POST">-->
							<form:form commandName="searchModel" id="search">

								<div align="center">
									<div style="width: 50%;" class="containerDiv-plain"
										align=center>
										<div id="rowDiv">
											<div id="cellDiv">
												<h3 align=center class="font-layout"> <spring:message code="searchable.title" /></h3>
											</div>
										</div>

									</div>
									<div class="errormessage"></div>
									<div style="width: 50%;" class="containerDiv rounded-10"
										align=center>

										<div class="rowDiv">
											<div class="cellDiv" style="float: left; padding-left: 15px;">
												<h5 class="font-layout">Search
											</div>
											<div class="cellDiv"></div>
											<div class="cellDiv"></div>
											<div class="cellDiv"></div>
										</div>
										<div class="rowDiv">
											<div class="cellDiv">
												<h5 class="font-layout">From:
											</div>
											<div class="cellDiv">

												<form:input path="fromDate" id="fromDate"
													cssClass="datepicker datepickerinputhandler-classic-rnd" />
											</div>
											<div class="cellDiv">
												<h5 class="font-layout">To:
											</div>
											<div class="cellDiv">
												<form:input path="toDate" id="toDate"
													cssClass="datepicker inputhandler-classic-rnd" />
											</div>
										</div>

										<div class="rowDiv">
											<div class="cellDiv" id="search-center"></div>
											<div class="cellDiv" id="search-center"></div>
											<div class="cellDiv" id="search-center"></div>
											<div class="cellDiv" id="search-center">
												<div class=button-right>
													<input type="submit" class="assign-button"
														style="margin-left: 25%;" name="createreport"
														value="Create Report">
												</div>
											</div>
										</div>
							</form:form>
						</div>



						<c:choose>
							<c:when test="${members.size()==0}">
								<em></em>
							</c:when>
							<c:otherwise>
								<div style="width: 50%;margin-top:2%;" class="containerDiv rounded-10"
									align=center>

									<div class="rowDiv">
										<div class="cellDiv" id="search-center">
											<table class="simpletablestyle">
												<thead>
													<tr>
														<th>Id</th>
														<th>Name</th>
														<th>Email</th>
														<th>Phone #</th>
														<th>Added to Members</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach items="${members}" var="member">
														<tr>
															<td>${member.id}</td>
															<td>${member.name}</td>
															<td>${member.email}</td>
															<td>${member.phoneNumber}</td>
															<td>${member.createdDate}</td>
														</tr>
													</c:forEach>
												</tbody>
											</table>



										</div>
										<div class="cellDiv" id="search-center"></div>
										<div class="cellDiv" id="search-center"></div>
										<div class="cellDiv" id="search-center"></div>
									</div>
								</div>
							</c:otherwise>
						</c:choose>

					</div>
		</div>


		<!--</form>-->
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