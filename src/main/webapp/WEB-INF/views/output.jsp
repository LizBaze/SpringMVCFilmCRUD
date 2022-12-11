<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Film Details</title>
</head>
<body>
	<h4>Film</h4>
	<p>${film}</p>
	<h4>Actors</h4>
	<ul> 
	<c:forEach var="actor" items ="${actors}">
		<li>${actor}</li>
	</c:forEach>
	</ul>

	<br>
	<br>
	<p>Delete Film:<p>
	<form action="delete.do" method="GET">
		<input type ="hidden" name = "filmid" value ="${film.id}"/>
		<input type="submit" value="Delete"  />
	</form>
<!-- TODO make delete button only show up if film is found -->
</body>
</html>