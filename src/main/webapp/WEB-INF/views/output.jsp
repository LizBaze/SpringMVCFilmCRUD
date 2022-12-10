<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Film Details</title>
</head>
<body><p>${film}</p>

<br>
<br>
<p>Delete Film:<p>
	<form action="delete.do" method="GET">
		 <input type="text" name="delete" size="4" /> 
		<input type="submit" value="Submit" />
	</form>

</body>
</html>