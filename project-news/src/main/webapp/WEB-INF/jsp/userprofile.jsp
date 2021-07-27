<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>YOUR PRIFILE</title>
<link href= "css/style.css" type = "text/css" rel="stylesheet"/> 
</head>
<body>
<h1>YOUR PRIFILE</h1>

	<blockquote>	
		<form action="Controller" method="get" onchange="submit()">
			<input type="hidden" name="command" value="change_local">
			
				<select name="local">
					<option value = "en" ${local == 'en' ? 'selected' : ''}>ENG</option>
					<option value = "ru" ${local == 'ru' ? 'selected' : ''}>RU</option>
				</select>
		</form>	
	</blockquote>
	
	<br /> 
	
	<a>Your photo could be here</a>
	
	<br /> 

</body>
</html>