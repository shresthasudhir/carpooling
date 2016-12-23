<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login Page</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
	integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp"
	crossorigin="anonymous">
<link rel="stylesheet" href="public/css/carpoolingStyle.css">
<link rel="stylesheet" href="public/css/indexStyle.css">

<!-- Main style for Theme -->
<link href="public/css/theme/style.css" rel="stylesheet">

<!-- Fonts  for Theme-->
<link href="public/css/theme/font-awesome.min.css" rel="stylesheet">
<link href='http://fonts.googleapis.com/css?family=Montserrat'
	rel='stylesheet' type='text/css'>


</head>
<body>	
		<div class="loginfail">
			<%
				if (request.getAttribute("msgLoginFailed") != null)
					out.println(request.getAttribute("msgLoginFailed"));
			%>
		</div>
	


	<header class="header">

	<div class="top-menu">

		<section class="container">
		<div class="row">
		
			<div class="col-xs-8">
				<div class="user-log">
					Welcome To Car Pooling
				</div>
			</div><!-- end .col-sm-8 -->

			<div class="col-md-4 col-sm-4 col-xs-12">
				<div class="user-log">

					<a data-toggle="modal" data-target="#login-modal"> Log in /
						Sign up</a>

					<div class="modal fade" id="login-modal" tabindex="-1"
						role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"
						style="display: none;">
						<div class="modal-dialog">
							<div class="loginmodal-container">
								<h1>Login to Your Account</h1>
								<br>
								<form action="Login" method="POST">
									<input type="text" name="txtUserLoginEmail" placeholder="Email"
										required> <input type="password"
										name="txtLoginPassword" placeholder="Password" required>
									<input type="submit" name="login"
										class="login loginmodal-submit" value="Login">
								</form>

								<div class="login-help">
									<a href="registration.jsp">Register</a> - <a href="#">Forgot
										Password</a>
								</div>
							</div>
						</div>
					</div>

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
	<script src="public/js/index.js" type="text/javascript"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
		integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
		crossorigin="anonymous"></script>
</body>
</html>