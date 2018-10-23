<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" 
    prefix="th" %> 
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
 
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
    xmlns:th="http://www.thymeleaf.org"
    xmlns:sec="http://www.thymeleaf.org">
<head>
  <meta http-equiv="Content-Type" content="text/html;" charset=UTF-8">
  <script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js">
  </script>
  
  <style>
	.error {
	 color: #ff0000;
	}
  </style>
  
</head>
<body>
<h1 th:text="${label.form.title}">Order confirmation page</h1>

<form:form method="post" action="/OnlineMovieStore/api/orderConfirmation/title=${movieTitle}/id=${movieId}/submitOrder" modelAttribute="theMovie">
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