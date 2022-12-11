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

	<c:forEach var="keyword" items="${FilmList}">
      <br>${keyword}<br>
    </c:forEach>

</body>
</html>