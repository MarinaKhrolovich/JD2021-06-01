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