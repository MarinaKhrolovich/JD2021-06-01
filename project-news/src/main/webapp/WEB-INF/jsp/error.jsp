<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>ERROR</title>
</head>
<body>
<h1>Error</h1>
	
	<font color="red" size="18">
	<%
	   String mes = (String)request.getParameter("message");
	 if(mes != null){
		 out.print(mes);
	 }
	
	%>
	</font>
	
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