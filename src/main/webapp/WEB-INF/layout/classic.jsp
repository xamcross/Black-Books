<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/taglib.jsp"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>

<!DOCTYPE html>
<html>
<head>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">


<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">


<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><tiles:getAsString name="title" /></title>
</head>
<body>


	<extras:useAttribute name="current" />
	<div class="container">
		<!-- Static navbar -->
		<nav class="navbar navbar-default">
			<div class="container-fluid">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed"
						data-toggle="collapse" data-target="#navbar" aria-expanded="false"
						aria-controls="navbar">
						<span class="sr-only">Toggle navigation</span> <span
							class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="<spring:url value="/index"/>">Black
						Books</a>
				</div>
				<div id="navbar" class="navbar-collapse collapse">
					<ul class="nav navbar-nav">
						<li class="${current == 'index' ? 'active' : ''}"><a
							href='<spring:url value="/index"/>'>Home</a></li>
						<security:authorize access="hasRole('ADMIN')">
							<li class="${current == 'customers' ? 'active' : ''}"><a
								href='<spring:url value="/customers"/>'>Customers</a></li>
							<li class="${current == 'addBook' ? 'active' : ''}"><a
								href='<spring:url value="/addBook"/>'>Add Book</a></li>
						</security:authorize>
						<li class="${current == 'books' ? 'active' : ''}"><a
							href='<spring:url value="/books"/>'>Books</a></li>
						<security:authorize access="isAuthenticated()">
							<li class="${current == 'checkout' ? 'active' : ''}"><a
								href='<spring:url value="/checkout"/>'>Shopping Cart</a></li>
						</security:authorize>
					</ul>
					<ul class="nav navbar-nav navbar-right">
						<security:authorize access="!isAuthenticated()">
							<li class="${current == 'registration' ? 'active' : ''}"><a
								href='<spring:url value="/register"/>'>Register</a></li>
							<li class="${current == 'login' ? 'active' : ''}"><a
								href='<spring:url value="/login"/>'>Login</a></li>
						</security:authorize>
						<security:authorize access="isAuthenticated()">
							<li class="${current == 'cabinet' ? 'active' : ''}"><a
								href='<spring:url value="/cabinet"/>'>Cabinet</a></li>
							<li><a href='<spring:url value="/logout"/>'>Logout</a></li>
						</security:authorize>
					</ul>
				</div>
			</div>
			<!--/.nav-collapse -->
		</nav>
		<tiles:insertAttribute name="body" />
		<tiles:insertAttribute name="footer" />
	</div>

</body>
</html>