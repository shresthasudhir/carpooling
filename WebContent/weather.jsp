<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Weather Forecast</title>
<link href="public/css/weather.css" type="text/css" rel="stylesheet" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<script type="text/javascript"
	src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCtiMx-TubW49qgT_lgULfvzweEu1542NA&libraries=places"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
	integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp"
	crossorigin="anonymous">
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
	<input type="hidden" id="usercity" value="${city}" />
	<input type="hidden" id="userstate" value="${state}" />
	<input type="hidden" id="userzip" value="${zipcode}" />
	
	<div class="top-menu">
		<section class="container">
		<div class="row">
			<div class="col-xs-4">
				<div class="user-log"><a href="homepage.jsp">Home</a></div>
				<!-- end .user-log -->
			</div>
			<!-- end .col-sm-4 -->
		
			<div class="col-xs-4">
				<div class="user-log"><a href="GetUserWeather">Weather</a></div>
				<!-- end .user-log -->
			</div>
			<!-- end .col-sm-4 -->
			<div class="col-xs-4">
				<div class="user-log">
					<a href="Logout">Log Out <span class="useremail">(<%=session.getAttribute("userSession")%>)
					</span>
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
		 <div id="display">
			<div id="search">
				<table class="table table-sm">
					<tr>
						<td><h3>Get Weather Forecast of Your Destination Here</h3></td>
							<!--  &nbsp <input type="submit" id="submit" value="Check"> -->
					</tr>
					<tr><td><input type="text"
							id="origin" placeholder="Origin : ${city}, ${state}"></td>
							<td><input id="alltrips" type="button" value="Show All Trips"></td>
							</tr>
					<tr><td><input type="text"
							id="city" placeholder="Destination : ${city}, ${state}"></td>
							<td><input id="nearbytrips" type="button" value="Show Destination Nearby Trips"></td>
							</tr>
				</table>
			</div>
			<div id="weather" class="table-responsive"></div>
		 </div> 
	<div id="map-canvas"></div>
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
	<script src="public/js/weather.js" type="text/javascript"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
		integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
		crossorigin="anonymous"></script>
</body>
</html>