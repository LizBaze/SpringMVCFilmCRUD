<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>	
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
<style><%@include file="../../main.css"%></style>

<meta charset="UTF-8">
<title>keyword</title>
</head>
<body>

	<div class = " col-10 mx-auto">
		
		<!-- TODO put both buttons on the same line.-->
	<a class="link-dark" href="home.do">Home</a>
<br>
<br>
	<c:forEach var="film" items="${FilmList}">
      	<br>ID: ${film.id} ${film} Category: ${film.category} }<br>
      	<p>Actors</p>
		<ul> 
			<c:forEach var="actor" items ="${film.actors}">
			<li>${actor}</li>
			</c:forEach>
		</ul>
      <table>
      	<tr>
      		<td>
     			 <form action="delete.do" method="GET">
      			<input type ="hidden" name = "filmid" value ="${film.id}"/><input class="btn btn-dark" type="submit" value="Delete"/>
      			</form>
      		</td>
      		<td>
    			  <form action="update.do" method="POST">
    			  <input type ="hidden" name = "filmid" value ="${film.id}"/>
				
				<input class="btn btn-dark" type="Submit" value="Update"/>
				</form>
			</td>
	</table>
    </c:forEach>
          
      
      <br>

	</div>

</body>
</html>