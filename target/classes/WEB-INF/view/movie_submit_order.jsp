<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

<head>
	<title>Submit Order</title>
</head>
<body>

<h2>Choose the number of days and be a step away from watching the movie!</h2>
<form:form method="post" action="/OnlineMovieStore/api/orderSubmission/title=${movieTitle}/id=${movieId}/submitOrder" modelAttribute="theMovie">
	<table width="50%">
	<tr>
		<th>No.</th>
		<th>Movie Title</th>
		<th>Release Year</th>
		<th>Description</th>
	</tr>
	<tr>
		<td>${theMovie.id}</td>
		<td><a href="/OnlineMovieStore/api/movies/title=${theMovie.movieTitle}/id=${theMovie.id}">${theMovie.movieTitle}</a></td>
		<td>${theMovie.releaseYear}</td>
		<td>${theMovie.movieDescription}</td>
	</tr>
	
	
	</table>	
	
	Type how many days you want to reserve that movie (minimum 1 maximum 5) : <input id="numberOfDaysToWatch" name="numberOfDaysToWatch" type="number" min="1" max="5"/>
	
<br/>
<input type="submit" value="Order" />
	
</form:form>
</body>
</html>