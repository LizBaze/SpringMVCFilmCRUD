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

	
		
		<!-- TODO put both buttons on the same line.-->
	
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
      <p>Delete Film:<p>
      
      <form action="delete.do" method="GET">
    
      <input type ="hidden" name = "filmid" value ="${film.id}"/><input type="submit" value="Delete"/>
      </form>
      
      <form action="update.do" method="POST">
	<label for="update"></label>
	<input type="Submit" value="update"/>
	
	</form>
    </c:forEach>
          
      
      <br>

	

</body>
</html>