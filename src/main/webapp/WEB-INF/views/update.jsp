<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Film Information</title>
</head>
<body>
	<a href="home.do">Home</a>

	<h3>Update a film with new information </h3>
	<form action="updateFilm.do" method="POST">
	
	<label for="id">Film ID</label>
	<input type="text" name="id" required/>
	<br>
	<br>
	<label for="title">Film Title</label>
	<input type="text" name="title" required/>
	<label for="description">Description</label>
	<input type="text" name="description" required/>
	<label for="releaseYear">Release Year</label>
	<input type="text" name="releaseYear" required/>
	
	<br>
	<br>
	<label for="rentalDuration">Rental Duration</label>
	<input type="text" name="rentalDuration" required/>
	<label for="rentalRate">Rental Rate</label>
	<input type="text" name="rentalRate" required/>
	
	<label for="length">Length</label>
	<input type="text" name="length" required/>
	<label for="replacementCost">Replacement Cost</label>
	<input type="text" name="replacementCost" required/>
	<br>
	<br>
	<label for="rating">Rating</label>
	<select name="rating" id="rating">
<option value=""></option>
<option value="G">G</option>
<option value="PG">PG</option>
<option value="PG13">PG13</option>
<option value="R">R</option>
<option value="NC17">NC17</option>
</select>
	
	
	<label for="features">Features</label>
	<select name="features" id="features">
<option value=""></option>
<option value="Behind the Scenes">Behind the Scenes</option>
<option value="Commentaries">Commentaries</option>
<option value="Deleted scenes">Deleted Scenes</option>
<option value="railers">Trailers</option>
	</select>
	
	<label for="languageID">Language</label>
	<select name="languageID" id="languageID">
			<option value=""></option>
			<option value="1">English</option>
			<option value="2">Italian</option>
			<option value="3">Japanese</option>
			<option value="4">Mandarin</option>
			<option value="5">French</option>
			<option value="6">German</option>
		</select>
	
	<br>
	<br>
	<input type="submit" value="Submit" />
	</form>

</body>
</html>