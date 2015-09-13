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

<html lang="en">
<head >
<!--[if lt IE 9]>
	<script src="http://css3-mediaqueries-js.googlecode.com/files/css3-mediaqueries.js"></script>
	<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
<![endif]-->
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width">
<title>Blog videos by members</title>

<link
	href="http://netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.css"
	rel="stylesheet">
<link rel="stylesheet" type="text/css" href="http://fonts.googleapis.com/css?family=Nunito:400,700">

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
<link href="<c:url value="/resources/css/plugins/video/bigvideo.css"/>" rel="stylesheet" type="text/css" media="all">
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.9.2/jquery-ui.min.js"></script>
  
<script src="<c:url value="/static/resources/js/globals.js"/>" type="text/javascript"></script>
<script src="<c:url value="/static/resources/js/am-util.js"/>"	type="text/javascript"></script>
<scriptsrc="<c:url value="/static/resources/js/plugins/jquery.preimage.js"/>"	type="text/javascript"></script>
<script src="<c:url value="/static/resources/js/blogs.js"/>"	type="text/javascript"></script>

<script
	href="<c:url value="/resources/js/plugins/tabs/jquery.hashchange.min.js"/>"
	src="resources/js/plugins/tabs/jquery.hashchange.min.js"
	type="text/javascript"></script>
<script
	href="<c:url value="/resources/js/plugins/tabs/jquery.easytabs.min.js"/>"
	src="resources/js/plugins/tabs/jquery.easytabs.min.js"
	type="text/javascript"></script>

<script type="text/javascript" src="http://vjs.zencdn.net/c/video.js"></script>
<script type="text/javascript" href="<c:url value="/resources/js/plugins/video/jquery.imagesloaded.min.js"/>"	src="resources/js/plugins/video/jquery.imagesloaded.min.js"></script>
<script type="text/javascript"href="<c:url value="/resources/js/plugins/video/bigvideo.js"/>" src="resources/js/plugins/video/bigvideo.js"></script>
<script href="<c:url value="/resources/js/plugins/video/videocontroller.js"/>" 	src="resources/js/plugins/video/videocontroller.js" type="text/javascript"></script>

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
											data-video="http://localhost:8080/ModernDrummer/files/brushes.MP4">
											<img src="files/tama_drum.jpg"
												class="video-big-image img-auto bg-img-drums" />
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

	 <script type="text/javascript">
	 jq(function() {
	     VideoController.getVideoControllerWithSource( jq("#screen-1").attr('data-video') );
	 });
    </script>

</body>
</html>