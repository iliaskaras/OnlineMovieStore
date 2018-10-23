<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

<head>
	<title>Movies</title>
</head>
<body>

<h2>Select a movie and enjoy!</h2>

<form:form action="/OnlineMovieStore/api/movies/all/genreType/" method="get" modelAttribute="genreType" enctype="utf8">

	 <div><p>Select Genre Type</p></div> 
	 <form:select   path="genreType">
    	<form:options name="genreType"  items="${genreTypesList}"></form:options>
     </form:select>
     
     <input type="submit" value="Filter" />
</form:form>
     
<form:form method="post" modelAttribute="movie">
	<table width="50%">
	<tr>
		<th>No.</th>
		<th>Movie Title</th>
		<th>Release Year</th>
		<th>Description</th>
	</tr>
	<c:forEach items="${movies}" var="movies" varStatus="status">
		<tr>
			<td>${movies.id}</td>
			<td><a href="/OnlineMovieStore/api/movies/title=${movies.movieTitle}/id=${movies.id}">${movies.movieTitle}</a></td>
			<td>${movies.releaseYear}</td>
			<td>${movies.movieDescription}</td>
		</tr>
	</c:forEach>
	</table>	
<br/>
</form:form>

<a href="/OnlineMovieStore/api/customer/loggedIn">Back to Your Home Page</a>

</body>
</html>