<%@page import="org.springframework.web.servlet.ModelAndView;"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<html lang="en">
<head>
<title>Test Youself</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="<c:url value="/resources/css/sidebar.css" />" rel="stylesheet">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"
	integrity="sha512-dTfge/zgoMYpP7QbHy4gWMEGsbsdZeCXz7irItjcC3sPUFtf0kuFbDz/ixG7ArTxmDjLXDmezHubeNikyKGVyQ=="
	crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css"
	integrity="sha384-aUGj/X2zp5rLCbBxumKTCw2Z50WgIr1vs/PFN4praOTvYXWlVyh2UtNUU0KAUhAX"
	crossorigin="anonymous">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js">
	
</script>
<!-- Latest compiled and minified JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"
	integrity="sha512-K1qjQ+NcF2TYO/eI3M6v8EiNYZfA95pQumfvcVrTHtwQVDG+aHRqLi/ETn2uB+1JqwYqVG3LIvdm9lj6imS/pQ=="
	crossorigin="anonymous">
	
</script>
<script>
	$(document).ready(function() {
		$.ajaxSetup({
			cache : true
		});
		$.getScript('//connect.facebook.net/en_US/sdk.js', function() {
			FB.init({
				appId : '472869032891039',
				version : 'v2.4' // or v2.0, v2.1, v2.2, v2.3
			});
			$('#loginbutton,#feedbutton').removeAttr('disabled');
			FB.getLoginStatus(updateStatusCallback);
		});
	});
</script>
</head>
<body>
	<script>
		FB.logout(function(response) {
			// Person is now logged out
			window.location.replace("/TESTYourself/welcome");
		});
	</script>
	<nav class="navbar navbar-default">
		<div class="container">
			<div class="navbar-header">
				<button id="menu-toggle" type="button" class="btn btn-success">Menu</button>
				<a href="#" class="navbar-brand">TEST Youself</a>
			</div>

			<div class="collapse navbar-collapse" id="mainNavBar">
				<ul class="nav navbar-nav">
					<li class="active"><a href="#">Home</a></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">How it works <span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="#">Take a tour</a></li>
							<li><a href="#">Step-by-step</a></li>
							<li><a href="#">Ask for help</a></li>
						</ul></li>
					<li><a href="#">About</a></li>
					<li><a href="#">Contact us</a></li>

				</ul>

				<%
					Object fbData = pageContext.findAttribute("fbdata");
				%>

				<ul class="nav navbar-nav navbar-right">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown"><%=request.getAttribute("fbname")%><span
							class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="#">Statistics</a></li>
							<li><a href="#">Settings</a></li>
							<li><a onClick="FB.logout();" href="/TESTYourself/welcome">Logout</a></li>
						</ul></li>
				</ul>
			</div>
		</div>
	</nav>

	<div id="wrapper">

		<!-- Sidebar -->
		<div id="sidebar-wrapper">
			<ul class="sidebar-nav">
				<li><a href="#">My Exams</a></li>
				<li><a href="#">My Results</a></li>
				<li><a href="#">Statistics</a></li>
			</ul>
		</div>

		<!-- Page Content -->

		<div id="page-content-wrapper">
			<div class="container-fluid">
				
					<div class="col-md-4"></div>
					<div class="col-md-4"></div>
					<div class="col-md-4"></div>
			</div>
		</div>
	</div>

	<script>
		$("#menu-toggle").click(function(e) {
			e.preventDefault();
			$("#wrapper").toggleClass("menuDisplayed");
		});
	</script>
</body>
</html>
