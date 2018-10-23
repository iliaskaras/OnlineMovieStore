<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

<head>
	<title>Movies</title>
</head>
<body>

<h2>Select a movie and enjoy!</h2>

     
<form:form method="post" modelAttribute="transaction">
	<table width="50%">
	<tr>
		<th>No.</th>
		<th>Paid</th>
		<th>Total Amount</th>
		<th>Number of days you can watch</th>
		<th>Order creation date</th>
		<th>Order end date</th>
	    <th>Movie title</th>
	    <th>Transaction Comment</th>
	    <th>Pay</th>
	    <th>Watch</th>
	</tr>
	
	<c:forEach items="${transactionWrapper}" var="transactionWrapper" varStatus="status">
		<tr>
			<td>${transactionWrapper.transaction.transactionId}</td>
			<td>${transactionWrapper.paid}</td>
			<td>${transactionWrapper.transaction.transactionAmount}</td>
			<td>${transactionWrapper.transaction.numberOfOrderDays}</td>
			<td>${transactionWrapper.transaction.transactionDate}</td>
			<td>${transactionWrapper.transaction.rentalEndDate}</td>
			<td>${transactionWrapper.movie.movieTitle}</td>
			<td>${transactionWrapper.transaction.transactionComment}</td>
			<td><a href="/OnlineMovieStore/api/orderSubmission/payorder/transactionId=${transactionWrapper.transaction.transactionId}">Pay</a></td>
			<td><a href="/OnlineMovieStore/api/movie/watch/movieId=${transactionWrapper.movie.id}/${transactionWrapper.paid}">Watch</a></td>
		</tr>
	</c:forEach>

	</table>	
<br/>
</form:form>

<a href="/OnlineMovieStore/api/customer/loggedIn">Back to Your Home Page</a>

</body>
</html>