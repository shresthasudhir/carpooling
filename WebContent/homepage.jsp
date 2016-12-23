<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home Page</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
	integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp"
	crossorigin="anonymous">
<link rel="stylesheet" href="public/css/carpoolingStyle.css">
<link rel="stylesheet" href="public/css/homepageStyle.css">

<!-- Main style for Theme -->
<link href="public/css/theme/style.css" rel="stylesheet">

<!-- Fonts  for Theme-->
<link href="public/css/theme/font-awesome.min.css" rel="stylesheet">
<link href='http://fonts.googleapis.com/css?family=Montserrat'
	rel='stylesheet' type='text/css'>

</head>
<body>
	<%
		String userSession = (String) session.getAttribute("userSession");
		if (userSession == null) {
			response.sendRedirect("index.jsp");
		}
	%>

	<header class="header">

	<div class="top-menu">

		<section class="container">
		<div class="row">

			<div class="col-xs-8">
				<div class="user-log">HomePage</div>
				<!-- end .user-log -->
			</div>
			<!-- end .col-sm-8 -->

			<div class="col-xs-4">
				<div class="user-log">
					<a href="Logout">Log Out
					<span class="useremail">(<%=session.getAttribute("userSession")%>) </span>
					</a>
				</div>
				<!-- end .user-log -->
			</div>
			<!-- end .col-sm-4 -->


		</div>
		<!-- end .row --> </section>
		<!-- end .container -->

	</div>
	<!-- end .top-menu -->


	<div class="main-baner">

		<div class="fullscreen background parallax clearfix"
			style="background-image: url('public/images/theme/tumblr_n7yhhvUQtx1st5lhmo1_1280.jpg');"
			data-img-width="1600" data-img-height="1064">

			<div class="main-parallax-content">

				<div class="second-parallax-content">

					<section class="container">
					<div class="row">

						<div class="main-header-container clearfix">

							<div class="col-md-4 col-sm-12 col-xs-12">

								<div class="logo">
									<h1>My ride</h1>
								</div>
								<!-- end .logo -->

							</div>
							<!-- end .col-sm-4 -->

							<div class="col-md-8 col-sm-8 col-xs-12">

								<nav id="nav" class="main-navigation">

								<ul class="navigation">
									<li><a href="homepage.jsp">Home</a></li>
									<li><a href="rides.jsp">Rides</a></li>
									<li><a href="GetUserWeather">Weather</a></li>
									<li><a href="UpdateProfile">Update Profile</a></li>
									<!-- <li><a href="#">Pages</a>
										<ul class="sub-menu">
											<li><a href="events.html">Events</a></li>
											<li><a href="single-post.html">Single post</a></li>
											<li><a href="single-article.html">Single article</a></li>
										</ul></li> -->
								</ul>

								</nav>
								<!-- end .main-navigation -->

							</div>
							<!-- end .col-md-8 -->

						</div>
						<!-- end .main-header-container -->

					</div>
					<!-- end .row --> </section>
					<!-- end .container -->

				</div>
				<!-- end .second-parallax-content -->

			</div>
			<!-- end .main-parallax-content -->

		</div>
		<!-- end .background .parallax -->

	</div>
	<!-- end .main-baner --> </header>
	<!-- end .header -->

	<br>

	<footer id="footer">

	<div class="footer-copyright">

		<div class="container">
			<div class="row">

				<div class="col-md-12 col-sm-12 col-xs-12">Copyright @ G#3
					2016</div>

			</div>
			<!-- end .row -->
		</div>
		<!-- end .container -->

	</div>
	<!-- end .footer-copyright --> </footer>
	<!-- end #footer -->





	<!-- Javascript For Theme-->
	<script
		src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>

	<!-- Main jQuery For Theme-->
	<script type="text/javascript" src="public/js/theme/jquery.main.js"></script>
	<!-- Form  For Theme-->
	<script type="text/javascript"
		src="public/js/theme/jquery.idealforms.min.js"></script>
	<script type="text/javascript"
		src="public/js/theme/jquery.idealselect.min.js"></script>

	<!-- Menu -->
	<!-- <script type="text/javascript" src="public/js/theme/hoverIntent.js"></script> -->
	<script type="text/javascript" src="public/js/theme/superfish.js"></script>

	<!-- Slicknav  -->
	<script type="text/javascript"
		src="public/js/theme/jquery.slicknav.min.js"></script>


	<script src="https://code.jquery.com/jquery-2.2.4.min.js"
		integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44="
		crossorigin="anonymous"></script>
	<script src="public/js/homepage.js" type="text/javascript"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
		integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
		crossorigin="anonymous"></script>
</body>
</html>