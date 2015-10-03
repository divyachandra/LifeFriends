<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%
    String errorMessage = (String) request.getAttribute("errorMessage");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>User Login</title>
</head>
<body>
    <% if (errorMessage != null) {  %>
        <p><%= errorMessage %></p>
    <% } %>

    <form action="Login" method="post">
        <input type="text" placeholder="Email" name="email"><br>
        <input type="password" placeholder="Password" name="password"><br><br>
        <input type="submit" value="Login">
    </form>

    <p>New User? <a href="Register.jsp">Register here</a></p>
</body>
</html>