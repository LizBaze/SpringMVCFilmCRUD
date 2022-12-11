<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Film Information</title>
</head>
<body>

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
	<label for="languageID">Language ID</label>
	<input type="text" name="languageID" required/>
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
	<input type="text" name="rating" required/>
	<label for="features">Features</label>
	<input type="text" name="features" required/>
	<br>
	<br>
	<input type="submit" value="Submit" />
	
	</form>

</body>
</html>