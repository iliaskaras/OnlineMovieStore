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
<h1 th:text="${label.form.title}">Customer Registration Form</h1>


<form:form id='formRegister' action="/OnlineMovieStore/api/account/registration" method="post" modelAttribute="registrationWrapper" enctype="utf8">
   
     <div><p>Your first name:<form:input type="text" path="customer.firstName"/> </p></div>
     <form:errors path="customer.firstName" cssClass="error" />
     <div><p>Your last name: <form:input type="text" path="customer.lastName"/> </p></div>
     <form:errors path="customer.lastName" cssClass="error" />
   	 <div><p>Your email: <form:input type="text" path="customer.email"/></p></div>
   	 <form:errors name="email_error" path="customer.email" cssClass="error" />
   	 
   	 <div><p>Your phone: <form:input type="text" path="customer.phone"/></p></div>
   	 <form:errors path="customer.phone" cssClass="error" />
   	 <div><p>Your age: <form:input type="text" path="customer.age"/></p></div>
   	 <form:errors path="customer.age" cssClass="error" />
     <div><p>Your username: <form:input type="text" path="account.username"/></p></div>
     <form:errors path="account.username" cssClass="error" />
   	 <div><p>Your password: <form:input type="text" path="account.password"/></p></div>
  	 <form:errors path="account.password" cssClass="error" />
  	 
 	 <div><p>Select PaymentMethod</p></div> 
   	 <form:select path="paymentMethod.paymentType">
    	<form:options name="paymentType"  items="${paymentMethodList}"></form:options>
     </form:select>
     
  	 <table>
	 <tr>
		<td colspan='2'><input name="submit" type="submit" value="submit" /></td>
	 </tr>
	</table>
	
</form:form>


</body>
</html>