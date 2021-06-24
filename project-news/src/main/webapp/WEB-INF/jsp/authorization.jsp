<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>AUTHORIZATION</title>
</head>
<body>
<h1>AUTHORIZATION</h1>
	<form action="Registration" method="get">
		<input type="hidden" name="command" value="didautho" /> 
		Enter login:<br />
		<input type="text" name="login" value="" /><br /> 
		Enter password:<br />
		<input type="password" name="password" value="" /><br /> 
		<br />
		<input type="submit" value="Enter" /><br />
	</form>
</body>
</html>