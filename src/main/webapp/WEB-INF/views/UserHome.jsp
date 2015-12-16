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
<link href="../css/Page-Content.css" rel="stylesheet">
<link href='https://fonts.googleapis.com/css?family=Oswald:700'
	rel='stylesheet' type='text/css'>
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
		<div class="container-fluid"
			style="margin-left: 30px; margin-right: 40px">
			<ul class="nav navbar-nav">
				<li class="active" style="margin-top: 2px"><button
						class="navbar-toggle collapse in" data-toggle="collapse"
						id="menu-toggle-2">
						<span class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button></li>
			</ul>
			<div class="navbar-header fixed-brand">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" id="menu-toggle">
					<span class="glyphicon glyphicon-th-large" aria-hidden="true"></span>
				</button>
				<a class="navbar-brand"
					href="/TESTYourself/user/<%=request.getAttribute("id")%>"
					class="navbar-brand">TEST Youself</a>
			</div>
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">


				<ul class="nav navbar-nav navbar-right">
					<li><a href="#">Tour</a></li>
					<li><a href="#">Support</a></li>
					<li class="dropdown active"><a href="#"
						class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
							<%=request.getAttribute("fbname")%>&nbsp;&nbsp;&nbsp;&nbsp;<img
							class="img-circle"
							src="https://graph.facebook.com/<%=request.getAttribute("id")%>/picture?height=24&width=24"
							alt="Facebook Profile Pic" align="middle"> <span
							class="caret"></span>
					</a>
						<ul class="dropdown-menu">
							<li><a href="#" id="myProfile">My Profile</a></li>
							<li><a href="#">Settings</a></li>
							<li><a onClick="FB.logout()" href="/TESTYourself/welcome">Logout</a></li>
						</ul></li>
				</ul>
			</div>
		</div>
	</nav>

	<div id="wrapper" class="menuDisplayed">

		<!-- Sidebar -->
		<div id="sidebar-wrapper">
			<ul class="sidebar-nav nav-pills nav-stacked" id="menu">
				<li class="active"><a href="#"><span
						class="fa-stack fa-lg pull-left"><i
							class="fa fa-dashboard fa-stck-1x" style="margin-top: 10px"></i></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Dashboard</a></li>
				<li><a href="#"><span class="fa-stack fa-lg pull-left"><i
							class="fa fa-pencil-square-o fa-stck-1x" style="margin-top: 10px"></i></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;My
						Exams</a></li>
				<li><a href="#"><span class="fa-stack fa-lg pull-left"><i
							class="fa fa-file fa-stck-1x" style="margin-top: 10px"></i></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;My
						Results</a></li>
				<li><a href="#"><span class="fa-stack fa-lg pull-left"><i
							class="fa fa-desktop fa-stck-1x" style="margin-top: 10px"></i></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Exam
						Monitoring<sup><font color="red"> Live!</font></sup></a></li>
				<li><a href="#"><span class="fa-stack fa-lg pull-left"><i
							class="fa fa-envelope fa-stck-1x" style="margin-top: 10px"></i></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Messages</a></li>
				<li><a href="#"><span class="fa-stack fa-lg pull-left"><i
							class="fa fa-bar-chart fa-stck-1x" style="margin-top: 10px"></i></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Charts</a></li>
				<li><a onClick="FB.logout()" href="/TESTYourself/welcome"><span
						class="fa-stack fa-lg pull-left"><i
							class="fa fa-power-off fa-stck-1x" style="margin-top: 10px"></i></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Logout</a></li>
			</ul>
		</div>

		<!-- Page Content -->

		<div id="page-content-wrapper">
			<div class="container-fluid">

				<div class="col-md-8" id="mainContent">

					<div class="content">
						<img class="img-thumbnail"
							src="https://graph.facebook.com/<%=request.getAttribute("id")%>/picture?height=100&width=100"
							alt="Facebook Profile Pic">
						<h2
							style="font-family: 'Oswald', sans-serif; color: #2C3E50; opacity: 0.9"><%=request.getAttribute("fbname")%></h2>
						<h4 style="font-family: 'Oswald', sans-serif; color: #868686">
							<i class="fa fa-envelope"></i>&nbsp;
							<%=request.getAttribute("email")%></h4>
					</div>

				</div>
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
			$("#wrapper").toggleClass("toggled");
		});
		$("#menu-toggle-2").click(function(e) {
			e.preventDefault();
			$("#wrapper").toggleClass("toggled-2");
			$('#menu ul').hide();
		});

		function initMenu() {
			$('#menu ul').hide();
			$('#menu ul').children('.current').parent().show();
			//$('#menu ul:first').show();
			$('#menu li a').click(function() {
				var checkElement = $(this).next();
				if ((checkElement.is('ul')) && (checkElement.is(':visible'))) {
					return false;
				}
				if ((checkElement.is('ul')) && (!checkElement.is(':visible'))) {
					$('#menu ul:visible').slideUp('normal');
					checkElement.slideDown('normal');
					return false;
				}
			});
		}
		$(document).ready(function() {
			initMenu();
		});
		$(document).ready(function() {
			$("#myProfile").click(function(e) {
				e.preventDefault();
				$("#mainContent").load("UserProfile.jsp #content");
			});
		});
	</script>
</body>
</html>
