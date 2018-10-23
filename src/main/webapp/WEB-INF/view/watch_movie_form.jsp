<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

<head>
	<title>Play Movie page</title>
</head>
<body>

<h2>Choose play to watch the movie!</h2>


<form:form  action="/OnlineMovieStore/api/movie/play/id=${movieId}" modelAttribute="theMovie" method="post" >
	<table width="50%">
	<tr>
		<th>No.</th>
		<th>Movie Title</th>
		<th>Release Year</th>
		<th>Description</th>
	</tr>
	<tr>
		<td>${theMovie.id}</td>
		<td>${theMovie.movieTitle}</td>
		<td>${theMovie.releaseYear}</td>
		<td>${theMovie.movieDescription}</td>
	</tr>
	
	 <tr>
		<td colspan='2'><input name="submit" type="submit" value="Play Video" /></td>
	 </tr>
	 
	</table>	
	
<br/>
	
</form:form>

<a href="/OnlineMovieStore/api/customer/loggedIn">Back to Your Home Page</a>

</body>
</html>