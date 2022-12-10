<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Film Details</title>
</head>
<body><p>${film}</p>
<p>${film.replacementCost}</p>
<p>${film.id}</p>
${film.languageId}

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