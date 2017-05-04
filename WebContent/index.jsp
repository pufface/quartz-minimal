<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="sk.fillo.quartz.Cron" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>J8J6</title>
</head>
<body>
	<p>
		<% String result = new Cron().schedule(request); %>
		<%= result %>
	</p>
</body>
</html>