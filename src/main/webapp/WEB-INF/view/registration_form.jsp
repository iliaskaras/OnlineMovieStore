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
<h1 th:text="${label.form.title}">Customer Registration Form1</h1>

<form id='formRegister' action="/OnlineMovieStore/api/account/registration" method="post" data-th-object="${professionList}" data-th-object="${customer}" data-th-object="${account}" data-th-object="${paymentmethod}" enctype="utf8">
    
    <div>
   	  <p>Your first name:: <input name="firstName" type="text" th:field="*{firstname}" /></p>
    </div>
    <div>
      <p>Your last name:: <input name="lastName" type="text" th:field="*{lastname}" /></p>
    </div>
    <div>
      <p>Your email:: <input name="email"  type="text" th:field="*{email}" /></p>
    </div>
    <div>
      <p>Your phone:: <input name="phone"  type="text" th:field="*{phone}" /></p>
    </div>
     <div>
      <p>Your age:: <input name="age"  type="value" th:field="*{age}" /></p>
    </div>
    
     <div>
   	  <p>Your username:: <input name="username" type="text" th:field="*{username}" /></p>
    </div>
    <div>
      <p>Your password:: <input name="password" type="text" th:field="*{password}" /></p>
    </div>
   <!--   <div>
      <p>Your payment method:: <input name="paymentType"  type="text" th:field="*{paymentType}" /></p>
        
    </div>-->
    <table>
	    <tr>
	       <td>Select Payment Method</td>
	       <td><form:select name="paymentType" items = "${paymentMethodList}" path = "listOfPaymentMethods"  th:field="*{paymentType}"/></td>       
	     </tr>
   	</table>
  	<table>
	    <tr>
			<td colspan='2'><input name="submit" type="submit" value="submit" /></td>
		</tr>
	</table>
</form>


</body>
</html>