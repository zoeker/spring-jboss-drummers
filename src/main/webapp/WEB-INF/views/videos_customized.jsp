<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ page import="java.io.PrintWriter"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>
<%@ page import="java.lang.*"%>
<%@ page import="com.moderndrummer.menu.DCMenu"%>
<%@ page import="com.moderndrummer.web.components.*"%>
<%@ page import="com.moderndrummer.viewhandlers.*"%>
<%@ page
	import="org.springframework.context.ApplicationContext,org.springframework.web.servlet.support.RequestContextUtils"%>

<!DOCTYPE html>

<html>
<head>
<!--[if lt IE 9]>
	<script src="http://css3-mediaqueries-js.googlecode.com/files/css3-mediaqueries.js"></script>
	<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
<![endif]-->

<title>Blogs by members</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<link
	href="http://netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.css"
	rel="stylesheet">

<script src="http://code.jquery.com/jquery-latest.js"
	type="text/javascript"></script>
<script src="<c:url value="/static/resources/js/globals.js"/>"
	type="text/javascript"></script>
<script src="<c:url value="/static/resources/js/am-util.js"/>"
	type="text/javascript"></script>
<script
	src="<c:url value="/static/resources/js/plugins/jquery.preimage.js"/>"
	type="text/javascript"></script>
<script src="<c:url value="/static/resources/js/blogs.js"/>"
	type="text/javascript"></script>

<script
	href="<c:url value="/resources/js/plugins/tabs/jquery.hashchange.min.js"/>"
	src="resources/js/plugins/tabs/jquery.hashchange.min.js"
	type="text/javascript"></script>
<script
	href="<c:url value="/resources/js/plugins/tabs/jquery.easytabs.min.js"/>"
	src="resources/js/plugins/tabs/jquery.easytabs.min.js"
	type="text/javascript"></script>
<script
	href="<c:url value="/resources/js/plugins/jquery-ui-1.8.22.custom.min.js"/>"
	src="resources/js/plugins/jquery-ui-1.8.22.custom.min.js"
	type="text/javascript"></script>
<script href="<c:url value="/resources/js/plugins/video/video.js"/>"
	src="resources/js/plugins/video/video.js" type="text/javascript"></script>
<script href="<c:url value="/resources/js/plugins/bigvideo.js"/>"
	src="resources/js/plugins/bigvideo.js" type="text/javascript"></script>
<script href="<c:url value="/resources/js/plugins/videocontroller.js"/>"
	src="resources/js/plugins/videocontroller.js" type="text/javascript"></script>

<script
	href="<c:url value="/resources/js/plugins/modernizr-2.5.3.min.js"/>"
	src="resources/js/plugins/modernizr-2.5.3.min.js"
	type="text/javascript"></script>

<link href="<c:url value="/resources/css/moderndrummer.css"/>"
	type="text/css" href="resources/css/moderndrummer.css" rel="stylesheet" />
<link type="text/css"
	href="<c:url value="/resources/css/moderndrummer-basic.css"/>"
	rel="stylesheet" />
<link type="text/css"
	href="<c:url value="/resources/css/moderndrummer-mediaqueries.css"/>"
	rel="stylesheet" />
<link type="text/css"
	href="<c:url value="/resources/css/moderndrummer-menu.css"/>"
	rel="stylesheet" />
<link href="<c:url value="/resources/css/plugins/tabs/jstabs.css"/>"
	rel="stylesheet" type="text/css"
	href="resources/css/plugins/tabs/jstabs.css" />

<link href="<c:url value="/resources/css/videos.css"/>" rel="stylesheet"
	type="text/css" href="resources/css/videos.css" />
<link href="<c:url value="/resources/css/video/video-js.min.css"/>"
	rel="stylesheet" type="text/css"
	href="resources/css/video/video-js.min.css" />

</head>
<body class="w-100-percent">
	<div id="pagewrap" class="pagewrap-width-95 w-100-percent">
		<header id="header">
			<%
				out.println(DCMenu.buildMenu());
			%>
		</header>
		<div id="content" class="w-100-percent">
			<article class="post clearfix w-100-percent">
				<header>
					<figure class="post-image">
					</figure>
					<div id="wrapper" class="w-100-percent">
						<div id="search" class="animate form w-90-percent">


							<form:form commandName="videosViewHandler" id="videosForm"
								enctype="multipart/form-data">
								<div align="center">
									<div class="errormessage">
										<form:errors path="*"></form:errors>
										<c:if test="${not empty errorMessage}">
  												${errorMessage}
												</c:if>
									</div>

									<div class="video-wrapper">
										<div class="video-screen" id="screen-1"
											data-video="files/brushes.MP4">
											<img src="files/tama_drum.jpg" class="video-big-image img-auto bg-img-drums" />
											<h1 class="video-title">Title of video</h1>
										</div>
									</div>


								</div>
							</form:form>

						</div>

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