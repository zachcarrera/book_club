<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Book Share</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<script defer src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container mt-3">
	
		<h1>Change your Entry</h1>
		<a href="/books">back to the shelves</a>
		
		<form:form action="/books/${oneBook.id}/edit" method="put" modelAttribute="oneBook">
			<form:hidden path="user"/>
			<div class="mb-3">
				<form:label path="title" class="form-label">Title</form:label>
				<form:input path="title" type="text" class="form-control"/>
				<p class="form-text text-danger">
					<form:errors path="title"/>
				</p>
			</div>
			<div class="mb-3">
				<form:label path="author" class="form-label">Author</form:label>
				<form:input path="author" type="text" class="form-control"/>
				<p class="form-text text-danger">
					<form:errors path="author"/>
				</p>
			</div>
			<div class="mb-3">
				<form:label path="thoughts" class="form-label">My thoughts</form:label>
				<form:textarea path="thoughts" class="form-control" rows="3"></form:textarea>
				<p class="form-text text-danger">
					<form:errors path="thoughts"/>
				</p>
			</div>
			<button type="submit" class="btn btn-primary">Submit</button>
		
		</form:form>
		
	</div>

</body>
</html>