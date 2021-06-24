<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registration</title>
</head>
<body>
<h1>REGISTRATION</h1>
	<form action="Registration" method="get">
		<input type="hidden" name="command" value="didreg" /> 
		Enter login:<br />
		<input type="text" name="login" value="" /><br /> 
		Enter password:<br />
		<input type="password" name="password" value="" /><br /> 
		<br />
		<input type="submit" value="Enter" /><br />
	</form>
</body>
</html>