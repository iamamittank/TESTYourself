<%@page import="org.springframework.web.servlet.ModelAndView;"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<html lang="en">
<head>
<title>Test Youself</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link
	href="//maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css"
	rel="stylesheet">
<link href="../css/sidebar.css" rel="stylesheet">
<link href="../css/sidebar-light.css" rel="stylesheet">
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
	<%
		Object fbData = pageContext.findAttribute("fbdata");
	%>

	<nav class="navbar navbar-default navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<a href="/TESTYourself/user/<%=request.getAttribute("id")%>"
					class="navbar-brand">TEST Youself</a>
			</div>

			<div class="collapse navbar-collapse" id="mainNavBar">

				<ul class="nav navbar-nav navbar-right">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown"> <%=request.getAttribute("fbname")%>&nbsp;&nbsp;&nbsp;&nbsp;<img
							class="img-circle"
							src="https://graph.facebook.com/<%=request.getAttribute("id")%>/picture?height=24&width=24"
							alt="Facebook Profile Pic" align="middle"> <span
							class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="#">My Profile</a></li>
							<li><a href="#">Settings</a></li>
							<li><a onClick="FB.logout()" href="/TESTYourself/welcome">Logout</a></li>
						</ul></li>
					<li><a href="#" id="menu-toggle"><i
							class="fa fa-align-justify"></i></a></li>
				</ul>
			</div>
		</div>
	</nav>

	<div id="wrapper" class="menuDisplayed">

		<!-- Sidebar -->
		<div id="sidebar-wrapper">
			<ul class="sidebar-nav">
				<li><button class="btn btn-success btn-block btn-lg">Create
						New Exam</button></li>
				<li><a href="#"><i class="fa fa-dashboard fa-lg"></i>&nbsp;&nbsp;&nbsp;
						Dashboard</a></li>
				<li><a href="#"><i class="fa fa-pencil-square-o fa-lg"></i>&nbsp;&nbsp;&nbsp;
						My Exams</a></li>
				<li><a href="#"><i class="fa fa-file fa-lg"></i>&nbsp;&nbsp;&nbsp;
						My Results</a></li>
				<li><a href="#"><i class="fa fa-desktop fa-lg"></i>&nbsp;&nbsp;&nbsp;
						Exam Monitoring<sup><font color="red"> Live!</font></sup></a></li>
				<li><a href="#"><i class="fa fa-envelope fa-lg"></i>&nbsp;&nbsp;&nbsp;
						Messages</a></li>
				<li><a href="#"><i class="fa fa-bar-chart fa-lg"></i>&nbsp;&nbsp;&nbsp;
						Charts</a></li>
				<li><a onClick="FB.logout()" href="/TESTYourself/welcome"><i
						class="fa fa-sign-out fa-lg"></i>&nbsp;&nbsp;&nbsp; Logout</a></li>
			</ul>
		</div>

		<!-- Page Content -->

		<div id="page-content-wrapper">
			<div class="container-fluid">

				<div class="col-md-8"></div>
				<div class="col-md-4">
					<aside class="sidebar-light">

						<div class="company-info">

							<h3>Company Inc</h3>

							<p>We are the best company. Our high spirit and good work
								have won us numerous awards. Follow us to stay in touch!</p>

							<div class="social-buttons">

								<a href="#"><i class="fa fa-facebook"></i></a> <a href="#"><i
									class="fa fa-twitter"></i></a> <a href="#"><i
									class="fa fa-linkedin"></i></a> <a href="#"><i
									class="fa fa-github"></i></a>

							</div>

						</div>

						<div class="latest-blog-posts">

							<h3>
								<i class="fa fa-rss"></i> Latest blog posts
							</h3>

							<ul>
								<li><a href="#">Lorem ipsum dolor sit amet. </a><span>2
										days ago</span></li>
								<li><a href="#">Pellentesque quis eros dignis. </a><span>one
										week ago</span></li>
								<li><a href="#">Lorem ipsum dolor sit amet. </a><span>2
										weeks ago</span></li>
								<li><a href="#">Nam tempus turpis viverra, mattis diam
										ac, malesuada mi. </a><span>3 weeks ago</span></li>
							</ul>

						</div>

					</aside>


				</div>
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
