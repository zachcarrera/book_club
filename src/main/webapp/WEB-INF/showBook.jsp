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
		<h1>
			<c:out value="${book.title }" />
		</h1>
		<a href="/books">back to the shelves</a>
		<h2>
			<c:choose>
				<c:when test="${userId eq book.user.id}">
					You read
					<c:out value="${book.title}" />
					by
					<c:out value="${book.author}" />
				</c:when>
				<c:otherwise>
					<c:out value="${book.user.name}" />
					read
					<c:out value="${book.title}" />
					by
					<c:out value="${book.author}" />
				</c:otherwise>
			</c:choose>
		</h2>
		
		<h3>
			<c:choose>
				<c:when test="${userId eq book.user.id}">
					Here are your thoughts:
				</c:when>
				<c:otherwise>
					Here are
					<c:out value="${book.user.name}" />'s
					thoughts:
				</c:otherwise>
			</c:choose>
		</h3>
		<p class="border border-start-0 border-end-0 py-3"><c:out value="${book.thoughts}"/></p>
		
		<c:if test="${userId eq book.user.id}" >
			<a href="/books/${book.id}/edit" class="btn btn-success">Edit</a>
			<form action="/books/${book.id}" method="post" class="d-inline-block">
				<input type="hidden" name="_method" value="delete"/>
				<input type="submit" value="Delete" class="btn btn-danger"/>
			</form>
		
		</c:if>
	</div>
</body>
</html>