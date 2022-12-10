<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MVC Film Site</title>
</head>
<body>

	<h1>Welcome to the SDVid Database</h1>

<p>Look up a film by ID:<p>
	<form action="output.do" method="GET">
		 <input type="text" name="filmid" size="4" /> 
		<input type="submit" value="Submit" />
	</form>
	<br>
	<br>
	<p>Add a new film to the database:</p>
	<form action="createfilm.do" method="POST">
	<label for="title">Film Title</label>
	<input type="text" name="title"/>
	<label for="description">Description</label>
	<input type="text" name="description"/>
	<label for="releaseYear">Release Year</label>
	<input type="text" name="releaseYear"/>
	<label for="languageID">Language ID</label>
	<input type="text" name="languageID"/>
	<br>
	<br>
	<label for="rentalDuration">Rental Duration</label>
	<input type="text" name="rentalDuration"/>
	<label for="rentalRate">Rental Rate</label>
	<input type="text" name="rentalRate"/>
	
	<label for="length">Length</label>
	<input type="text" name="length"/>
	<label for="replacementCost">Replacement Cost</label>
	<input type="text" name="replacementCost"/>
	<br>
	<br>
	<label for="rating">Rating</label>
	<input type="text" name="rating"/>
	<label for="features">Features</label>
	<input type="text" name="features"/>
	<br>
	<br>
	<input type="submit" value="Submit" />
	
	</form>
	
	<p>Update a film with new information </p>
	
	<form action="updateFilm.do" method="POST">
	
	<label for="id">Film ID</label>
	<input type="text" name="id"/>
	<br>
	<br>
	<label for="title">Film Title</label>
	<input type="text" name="title"/>
	<label for="description">Description</label>
	<input type="text" name="description"/>
	<label for="releaseYear">Release Year</label>
	<input type="text" name="releaseYear"/>
	<label for="languageID">Language ID</label>
	<input type="text" name="languageID"/>
	<br>
	<br>
	<label for="rentalDuration">Rental Duration</label>
	<input type="text" name="rentalDuration"/>
	<label for="rentalRate">Rental Rate</label>
	<input type="text" name="rentalRate"/>
	
	<label for="length">Length</label>
	<input type="text" name="length"/>
	<label for="replacementCost">Replacement Cost</label>
	<input type="text" name="replacementCost"/>
	<br>
	<br>
	<label for="rating">Rating</label>
	<input type="text" name="rating"/>
	<label for="features">Features</label>
	<input type="text" name="features"/>
	<br>
	<br>
	<input type="submit" value="Submit" />
	
	</form>

</body>
</html>