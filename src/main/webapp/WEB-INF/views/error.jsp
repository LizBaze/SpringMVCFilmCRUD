<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>error</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">

<style><%@include file="../../main.css"%></style>

</head>
<body>
<div class="col-10 mx-auto">
<a class = "link-dark" href="home.do">Home</a>
<h3 class="text-center"> ${outputMessage} 

</h3>
</div>
</body>
</html>