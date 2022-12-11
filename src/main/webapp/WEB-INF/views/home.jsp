<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MVC Film Site</title>
</head>
<body>

	<h1>Welcome to the SDVid Database Management System</h1>

<h3>Look up a film by ID:</h3>
	<form action="output.do" method="GET">
		 <input type="text" name="filmid" size="4" /> 
		<input type="submit" value="Submit" />
	</form>
	<br>
	<br>
	<h3>Search By Key Word:</h3>
	<form action="keyword.do" method="GET">
		 <input type="text" name="keyword"/> 
		<input type="submit" value="Submit" />
	</form>
	<br>
	<br>
	<h3>Add a new film to the database:</h3>
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
<option value="Deleted Scenes">Deleted Scenes</option>
<option value="railers">Trailers</option>
</select>
	<br>
	<br>
	<input type="submit" value="Submit" />
	
	</form>
	
	<h3>Update a film with new information </h3>
	
	<form action="update.do" method="GET">
		<input type="submit" value="Update a film"/>
	</form>

</body>
</html>