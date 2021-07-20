<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>YOUR PRIFILE</h1>

	<br />
	<a>Your photo could be here</a>
	
		<br /> 

	<form action="Controller" method="post">
		<input type="hidden" name="command" value="change_local"> 
		<input id="ru" type="radio" name="local" value="ru">
		<label for= "ru">RU</label>
		<input id="en" type= "radio" name="local" value= "en">
		<label for= "en">ENG</label>
	</form>
		
	<br /> 

</body>
</html>