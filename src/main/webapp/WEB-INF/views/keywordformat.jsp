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
	<c:forEach var="keyword" items="${FilmList}">
      <br>ID: ${keyword.id} ${keyword} 
          
      <p>Delete Film:<p>
      <div>
      <form action="delete.do" method="GET">
    
      <input type ="hidden" name = "filmid" value ="${film.id}"/><input type="submit" value="Delete"/>
      </form>
      
      <form action="update.do" method="POST">
	<label for="update"></label>
	<input type="Submit" value="update"/>
	
	</form>
      </div>
      <br>
    </c:forEach>

</body>
</html>