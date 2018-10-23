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
</head>
<body>

<h3>Online Movie Store</h3>




<a href="${pageContext.request.contextPath}/api/account/registration">Register new Customer</a>
<br><br>

<a href="${pageContext.request.contextPath}/api/customer/login">Login</a>
<br><br>

<a href="${pageContext.request.contextPath}/api/customers/all/">(Pure service non ui response) Get all customers</a>
<br><br>

<a href="${pageContext.request.contextPath}/api/accounts/all/">(Pure service non ui response) Get all accounts</a>
<br><br>


</body>
</html>