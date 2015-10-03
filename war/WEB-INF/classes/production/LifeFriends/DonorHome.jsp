<%@ page import="com.csuf.base.LoginManager" %>
<%@ page import="com.csuf.bean.User" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<% User user = LoginManager.getCurrentUser(request); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Donor Home</title>
</head>
<body>
<a href="/Logout">Logout</a>
<p>Hello: <%= user != null ? user.getFirstName() : "" %></p>

</body>
</html>