<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>PORTAL NEWS</title>
</head>
<body>
<h1>PORTAL NEWS</h1>

	<br/>

	<form action="Registration" method="get">
		<input type="hidden" name="command" value="autho" /> 
		
		<input type="submit" value="Authorization" /><br />
	</form>
		
	<br/>

	<form action="Registration" method="post">		
		<input type="hidden" name="command" value="reg" />
		<input type="submit" value="Registration" /><br />
	</form>
	
	
</body>
</html>