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

	<form action="Controller" method="get" onchange="submit()">
		<input type="hidden" name="command" value="change_local">
		<select name="local">
			<option value = "en" ${local == 'en' ? 'selected' : ''}>ENG</option>
			<option value = "ru" ${local == 'ru' ? 'selected' : ''}>RU</option>
		</select>
		<br />
	</form>	
		
	<br /> 

</body>
</html>