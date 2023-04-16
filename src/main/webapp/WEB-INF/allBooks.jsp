<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
		<nav class="d-flex justify-content-between">
			<div>
				<h1>Welcome, <c:out value="${userName}"/></h1>
				<p>Books from everyone's shelves:</p>
			</div>
			<div class="text-right d-flex flex-column align-items-end">
				<a href="/logout">logout</a>
				<a href="/books/new">+ Add a book to my shelf!</a>
			</div>
		</nav>

		<table class="table">
			<thead>
				<tr>
					<th>ID</th>
					<th>Title</th>
					<th>Author Name</th>
					<th>Posted By</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="oneBook" items="${books}">
					<tr>

						<td><c:out value="${oneBook.id }"/></td>
						<!-- link to details -->
						<td><a href="/books/${oneBook.id}"> <c:out
									value="${oneBook.title}" />
						</a></td>
						<!-- access data -->
						<td><c:out value="${oneBook.author}" /></td>
						<td><c:out value="${oneBook.user.name}" /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

	</div>
</body>
</html>