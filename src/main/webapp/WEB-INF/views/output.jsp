<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
<link rel="stylesheet" href="../../main.css">
<style><%@include file="../../main.css"%></style>
<meta charset="UTF-8">
<title>Film Details</title>
</head>
<body>
<div class = "col-10 mx-auto">
	<a class="link-dark" href="home.do">Home</a>
	<br> <br>
	<h4>Film</h4>
	<p>ID: ${film.id} ${film} Category: ${film.category} </p>
	<h4>Actors</h4>
	<ul > 
	<c:forEach var="actor" items ="${actors}">
		<li >${actor}</li>
	</c:forEach>
	</ul>

	<br>
	<br>
	<table>
		<tr>
			<td>
				<form action="delete.do" method="GET">
				<input type ="hidden" name = "filmid" value ="${film.id}"/>
				<input class = "btn btn-primary" type="submit" value="Delete"  />
				</form>
			</td>
			<td>
			 	<form action="update.do" method="POST">
    			 <input type ="hidden" name = "filmid" value ="${film.id}"/>
				<label for="update"></label>
				<input class = "btn btn-primary"  type="Submit" value="Update"/>
				</form>
			</td>
		</tr>
	</table>
</div>
</body>
</html>