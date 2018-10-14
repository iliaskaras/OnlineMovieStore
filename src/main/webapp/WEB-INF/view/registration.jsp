<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
    xmlns:th="http://www.thymeleaf.org"
    xmlns:sec="http://www.thymeleaf.org">
<body>
<h1 th:text="${label.form.title}">Customer Registration Form</h1>

<form name='register' action="<c:url value='/api/account/registration' />" data-th-object="${customer}" data-th-object="${account}" data-th-object="${paymentmethod}" method='POST' enctype="utf8">
    
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
      <p>Your age:: <input name="age"  type="text" th:field="*{age}" /></p>
    </div>
    
     <div>
   	  <p>Your username:: <input name="username" type="text" th:field="*{username}" /></p>
    </div>
    <div>
      <p>Your password:: <input name="password" type="text" th:field="*{password}" /></p>
    </div>
    <div>
      <p>Your payment method:: <input name="paymentType"  type="text" th:field="*{paymentType}" /></p>
    </div>
    <div>
  
    <tr>
		<td colspan='2'><input name="submit" type="submit" value="submit" /></td>
	</tr>
</form>
 

</body>
</html>