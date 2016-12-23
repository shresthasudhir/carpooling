<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Rides / Post</title>
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

<link rel="stylesheet" href="public/css/carpoolingStyle.css">
<link rel="stylesheet" href="public/css/rideStyle.css">

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
	<input id="userSession" type="hidden" value="<%= userSession %>" />

	<header class="header">

	<div class="top-menu">

		<section class="container">
		<div class="row">
		
			<div class="col-xs-4">
				<div class="user-log"><a href="homepage.jsp">Home</a></div>
				<!-- end .user-log -->
			</div>
			<!-- end .col-sm-4 -->

			<div class="col-xs-4">
				<div class="user-log"><a href="rides.jsp">Rides</a></div>
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


	<div class="container-fluid">

		<div id="postdiv" class="postDiv">
			<%
				String successMessage = (String) request.getAttribute("successMessage");
				if (successMessage != null) {
					out.println(request.getAttribute("successMessage"));
				}
			%>
			<form action="InsertPost" method="POST" id="insertPostForm">
				<textarea class="txtBoxPost" rows="4" cols="80" name="txtBoxPost"
					placeholder="Want to Offer/Take the Ride..." required></textarea>
					Source:<input type="text" required name="txtBoxSource"/>Destination:<input type="text" required name="txtBoxDestination"/>
					<input type="hidden" id="destinationLat" name="destinationLat" value=""/> <input type="hidden" id="destinationLong" name="destinationLong" value=""/>
				<br> <label class="lblCheckBox"><input type="radio" name="radioType" value="offerride">
				Offer Ride &nbsp&nbsp&nbsp&nbsp&nbsp</label><label class="lblCheckBox"><input type="radio" name="radioType" value="takeride">
				Take Ride </label><br><br> <input type="submit" value=" Post ">

			</form>
		</div>
		<br><br>
		<div class="postNavContainer">
		
			<div class="container">
				<ul class="nav nav-tabs">
					<li class="active"><a data-toggle="tab" href="#offeredPostDiv">Offers</a></li>
					<li><a data-toggle="tab" href="#takerPostDiv">Takers</a></li>
				</ul>
			</div>
			
		    <div class="tab-content">
		    	<div id="offeredPostDiv" class="tab-pane fade in active">
				</div>
				
				<div id="takerPostDiv" class="tab-pane fade">					
				</div>
		    </div>
		    
		    <div id="loader">
            	<img src="public/images/loader.gif" alt="loader gif" />
        	</div>
		    
			
		</div> <!-- postNavContainer end -->
		
	</div> <!-- containter fluid end -->

	</header>
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
	<script src="public/js/rides.js" type="text/javascript"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
		integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
		crossorigin="anonymous"></script>


</body>
</html>