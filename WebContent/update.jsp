<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="headerOneTag"%>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User Profile Update</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
	integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp"
	crossorigin="anonymous">
<link rel="stylesheet" href="public/css/carpoolingStyle.css">
<link rel="stylesheet" href="public/css/updateStyle.css">

</head>
<body>

	<header class="header">

	<div class="top-menu">

		<section class="container">
		<div class="row">

			<div class="col-md-4 col-sm-4 col-xs-12">
				<div class="user-log">
					<h1></h1>
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

					<section class="container"> <%
 	String successMessage = (String) request.getAttribute("updateSuccess");
 	if (successMessage != null) {
 		out.println(request.getAttribute("updateSuccess"));
 %> <a href="homepage.jsp">Home</a> <%
 	}
 %>
 					<c:header1 color='black' clas='well' text='Update Your Profile' />
					<!--  <h1 class="well">Update Your Profile</h1> -->
					<div class="col-lg-12 well">
						<div class="row">
							<form action="UpdateProfile" method="post" id="formreg"
								data-toggle="validator" role="form">
								<div class="col-sm-12">
									<div class="form-group">
										<label or="inputName" class="control-label">Full Name</label>
										<input type="text" name="txtFullName" required="required"
											placeholder="Enter FullName " class="inputField"
											value="${fullname}">
									</div>
									<div class="form-group">
										<label>Gender</label><br>
										<%
											String g = (String) request.getAttribute("gender");
											if (g.equals("Male")) {
										%>
										<input type="radio" name="radioGender" value="Male" checked>
										Male <input type="radio" name="radioGender" value="Female">
										Female
										<%
											} else {
										%>
										<input type="radio" name="radioGender" value="Male">
										Male <input type="radio" name="radioGender" value="Female"
											checked> Female
										<%
											}
										%>
									</div>
									<div class="form-group">
										<label>Address</label>
										<!-- <textarea placeholder="Enter Address Here.." rows="3"
								class="form-control"></textarea> -->
									</div>
									<div class="row">
										<div class="col-sm-3 form-group">
											<label>State</label> <input type="text" name="txtState"
												required="required" placeholder="Enter State Name Here.."
												class="inputField" value="${state}">
										</div>
										<div class="col-sm-3 form-group">
											<label>City</label> <input type="text" name="txtCity"
												required="required" placeholder="Enter City Name Here.."
												class="inputField" value="${city}">
										</div>
										<div class="col-sm-3 form-group">
											<label>Street</label> <input type="text" name="txtStreet"
												required="required" placeholder="Enter Street Code Here.."
												class="inputField" value="${street}">
										</div>
										<div class="col-sm-3 form-group">
											<label>Zip-Code</label> <input type="text" name="txtZipCode"
												required="required" pattern="[\d]{5}"
												title="Five Digit Number Only" placeholder="Ex: 52557"
												class="inputField" value="${zipcode}"
												data-error="Five Digit Number Only">
											<div class="help-block with-errors"></div>
										</div>
									</div>

									<div class="form-group">
										<label>Birth Year</label> <input type="text"
											name="txtBirthYear" required="required"
											pattern="[1][\d][0-8][\d]|[1][\d][9][0-8]"
											title="Invalid Year: Four Digit Number or Must be Greater Than 18 Years"
											placeholder="Ex: 1990" class="inputField"
											value="${birthyear}">
									</div>
									<div class="form-group">
										<label>Email Address</label> <input type="email"
											name="txtEmail" placeholder="example@example.com"
											required="required" class="inputField" value="${email}">
									</div>
									<div class="form-group">
										<label>Password</label> <input type="password"
											required="required" name="txtPassword"
											pattern="((?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,})"
											placeholder="Enter Password Name Here.."
											title="Password should be minimum 6 char and one number one capital and one small letter required"
											class="inputField">
									</div>
									<button type="submit" id="btnRegister"
										class="btn btn-lg btn-info">Update Profile</button>
								</div>
							</form>
						</div>
					</div>


					</section>


				</div>
				<!--   end .second-parallax-content-->

			</div>
			<!--  end .main-parallax-content -->

		</div>
		<!-- end .background .parallax -->

	</div>
	<!-- end .main-baner --> </header>
	<!-- end .header -->

	<script src="https://code.jquery.com/jquery-2.2.4.min.js"
		integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44="
		crossorigin="anonymous"></script>
	<script src="public/js/update.js" type="text/javascript"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
		integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
		crossorigin="anonymous"></script>
</body>
</html>