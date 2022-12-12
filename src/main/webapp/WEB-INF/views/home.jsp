<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MVC Film Site</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">

<style><%@include file="../../main.css"%></style>

</head>
<body>
	<div class="col-10 mx-auto" >
	<h1 class="text-center">Welcome to the SDVid Database Management System</h1>
		<h3 >Look up a film by ID:</h3>
		<form  action="output.do" method="GET">
		<input  type="text" name="filmid" size="4" /> 
		<input class="btn btn-dark" type="submit" value="Submit" />
		</form>
	<br>
	<br>
	<h3>Search By Key Word:</h3>
	<form action="keyword.do" method="GET">
		<input type="text" name="keyword" />
	 	<input class ="btn btn-dark" type="submit"value="Submit" />
	</form>
		<br>
		<br>
	<h3>Update a film with new information</h3>
		<form  action="update.do" method="GET">
		<input type ="hidden" name = "filmid" value = 0 />
		<input class="btn btn-dark" type="submit" value="Update a film" />
		</form>
	<br>
	<br>
	<h3>Add a new film to the database:</h3>
	<form action="createfilm.do" method="POST">
		<label for="title">Film Title</label> <input type="text" name="title" required />
		<label for="description">Description</label> 
		<input type="text" name="description" /> 
		<br> <br>
			<label for="releaseYear">Release Year</label> 
			<input type="text" name="releaseYear" /> 
			<label for="rentalDuration">Rental Duration</label> 
			<input type="text" name="rentalDuration" />  <br><br>
			<label for="rentalRate">Rental Rate</label>
		<input type="text" name="rentalRate" /> 
		<label for="length">Length</label>
		<input type="text" name="length" />  <br><br>
		<label for="replacementCost">Replacement Cost</label> 
		<input type="text" name="replacementCost" /> 
		<label for="rating">Rating</label> 
		<select name="rating" id="rating">
			<option value="G">G</option>
			<option value="PG">PG</option>
			<option value="PG13">PG13</option>
			<option value="R">R</option>
			<option value="NC17">NC17</option>
		</select>  <br><br>
		<label for="features">Features</label> 
		<select name="features" id="features">
			<option value=""></option>
			<option value="Behind the Scenes">Behind the Scenes</option>
			<option value="Commentaries">Commentaries</option>
			<option value="Deleted Scenes">Deleted Scenes</option>
			<option value="Trailers">Trailers</option>
		</select>
		<label for="languageID">Language</label> 
		<select name="languageID" id="languageID">
			<option value="1">English</option>
			<option value="2">Italian</option>
			<option value="3">Japanese</option>
			<option value="4">Mandarin</option>
			<option value="5">French</option>
			<option value="6">German</option>
		</select>
		 <br> <br> 
			<input class="btn btn-dark" type="submit" value="Submit" /> 
	</form>
	<br>
	<br>
	
	</div>
	
</body>
</html>