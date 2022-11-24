<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
Hello World JSP from S12_T128_About_JSP

<p>
<strong>
<%= pageContext.getServletContext().getContextPath() %>
</strong>
</p>

<p>
<strong>
${pageContext.servletContext.contextPath}
</strong>
</p>

</body>
</html>