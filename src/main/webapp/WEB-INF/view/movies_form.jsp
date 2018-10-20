<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

<head>
	<title>Spring 3 MVC Multipe Row Submit - viralpatel.net</title>
</head>
<body>

<h2>Select a movie and enjoy!</h2>
<form:form method="post" action="save.html" modelAttribute="moviesForm">
	<table width="50%">
	<tr>
		<th>No.</th>
		<th>Movie Title</th>
		<th>Release Year</th>
		<th>Description</th>
	</tr>
	<c:forEach items="${moviesForm}" var="movies" varStatus="status">
		<tr>
			<td>${movies.id}</td>
			<td><a href="/OnlineMovieStore/api/movies/title=${movies.movieTitle}/id=${movies.id}">${movies.movieTitle}</a></td>
			<td>${movies.releaseYear}</td>
			<td>${movies.movieDescription}</td>
		
		<!--  	<td><input type="text" name="moviesForm[${status.index}].id" value="${movies.movieTitle}"/></td>
			<td><input type="text" name="moviesForm[${status.index}].movieTitle" value="${movies.releaseYear}"/></td>
			<td><input type="text" name="moviesForm[${status.index}].releaseYear" value="${movies.movieDescription}"/></td>-->
		</tr>
	</c:forEach>
</table>	
<br/>
<input type="submit" value="Save" />
	
</form:form>
</body>
</html>