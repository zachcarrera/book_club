<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Read Share</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<script defer src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container mt-3">
		<h1>Book Club</h1>
		<p>A place for friends to share thoughts on books.</p>
		<div class="d-flex gap-5">
			<!-- register form -->
			<form:form action="/register" method="post" modelAttribute="newUser" class="w-100 p-3">
				<h2>Register</h2>
				<div class="mb-3">
					<form:label path="name" class="form-label">Name</form:label>
					<form:input path="name" type="text" class="form-control" />
					<p class="form-text text-danger">
						<form:errors path="name" />
					</p>
				</div>
				<div class="mb-3">
					<form:label path="email" class="form-label">Email</form:label>
					<form:input path="email" type="email" class="form-control" />
					<p class="form-text text-danger">
						<form:errors path="email" />
					</p>
				</div>
				<div class="mb-3">
					<form:label path="password" class="form-label">Password</form:label>
					<form:input path="password" type="password" class="form-control" />
					<p class="form-text text-danger">
						<form:errors path="password" />
					</p>
				</div>
				<div class="mb-3">
					<form:label path="confirm" class="form-label">Confirm Password</form:label>
					<form:input path="confirm" type="password" class="form-control" />
					<p class="form-text text-danger">
						<form:errors path="confirm" />
					</p>
				</div>
				<button type="submit" class="btn btn-primary">Submit</button>
			</form:form>

			<!-- login form -->
			<form:form action="/login" method="post" modelAttribute="newLogin" class="w-100 p-3">
				<h2>Login</h2>

				<div class="mb-3">
					<form:label path="email" class="form-label">Email</form:label>
					<form:input path="email" type="email" class="form-control" />
					<p class="form-text text-danger">
						<form:errors path="email" />
					</p>
				</div>
				<div class="mb-3">
					<form:label path="password" class="form-label">Password</form:label>
					<form:input path="password" type="password" class="form-control" />
					<p class="form-text text-danger">
						<form:errors path="password" />
					</p>
				</div>
				<button type="submit" class="btn btn-primary">Submit</button>

			</form:form>
		</div>
	</div>
</body>
</html>