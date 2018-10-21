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
<h1 th:text="${label.form.title}">Login Page</h1>

<form:form id='formLogin' action="${pageContext.request.contextPath}/api/customer/login" method="post" modelAttribute="accountDetails" enctype="utf8">
   
     <div><p>Your username:: <form:input type="text" path="username"/></p></div>
     <form:errors path="username" cssClass="error" />
   	 <div><p>Your password:: <form:input type="text" path="password"/></p></div>
  	 <form:errors path="password" cssClass="error" />
  
  	 <table>
	 <tr>
		<td colspan='2'><input name="submit" type="submit" value="Login" /></td>
	 </tr>
	</table>
	
</form:form>

<br><br>
Not customer? Click here to <a href="${pageContext.request.contextPath}/api/account/registration">register</a>

</body>
</html>