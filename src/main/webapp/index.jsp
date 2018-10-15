<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" 
    prefix="th" %> 
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>

<html xmlns="http://www.w3.org/1999/xhtml"
    xmlns:th="http://www.thymeleaf.org"
    xmlns:sec="http://www.thymeleaf.org">
    <head>
  <meta http-equiv="Content-Type" content="text/html;" charset=UTF-8">

  
</head>
<body>

<h3>Spring REST Demo</h3>

<hr>

<a href="${pageContext.request.contextPath}/test/hello">Hello</a>
<br><br>

<a href="${pageContext.request.contextPath}/api/customers">Get all customers</a>
<br><br>

<a href="${pageContext.request.contextPath}/api/account/registration">Register new Customer</a>
<br><br>

<a href="${pageContext.request.contextPath}/account/testajax">Tessst Ajax CRUD calls</a>
<br><br>

<form id='formDelete' action="/OnlineMovieStore/api/accounts/delete/5"  method="post"  enctype="utf8">
<table>
	    <tr>
			<td colspan='2'><input name="submit" type="submit" value="submit" /></td>
		</tr>
	</table>
</form>
<br><br>

<a href="${pageContext.request.contextPath}/validatePhone">Contact validation</a>

</body>
</html>