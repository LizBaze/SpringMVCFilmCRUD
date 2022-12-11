<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>	
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>keyword</title>
</head>
<body>

	<c:forEach var="film" items="${FilmList}">
      	<br>${film}<br>
      	<p>Actors</p>
		<ul> 
			<c:forEach var="actor" items ="${film.actors}">
			<li>${actor}</li>
			</c:forEach>
		</ul>
    </c:forEach>

</body>
</html>