<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<p>
	<c:out value="S12_T133_Creating_a_Form" />
	</p>
	
	<p>
	<c:out value = "{pageContext.servletContext.contextPath}" />
	</p>
	
	<form>

	<label for="name">Name:</label><input type="text" id="name" name="name" />
	<label for="password">Password:</label><input type="password" id="password" name="password" />
	<input type="submit" value="Submit">

	</form>

</body>
</html>