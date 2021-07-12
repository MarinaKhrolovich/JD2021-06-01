<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>PORTAL NEWS</title>
  <style type="text/css">
   H1 { 
    font-size: 120%; /* Размер шрифта */
    font-family: Verdana, Arial, Helvetica, sans-serif; /* Семейство шрифта */
    color: #336; /* Цвет текста */
   }
  </style>
</head>
<body>
<h1>PORTAL NEWS</h1>

	<hr/>
	<br/>

	<form action="Controller" method="post">
		<input type="hidden" name="command" value="authorization" /> 
		
		<input type="submit" value="Authorization" /><br />
	</form>
		
	<br/>

	<form action="Controller" method="post">		
		<input type="hidden" name="command" value="registration" />
		<input type="submit" value="Registration" /><br />
	</form>
	
	
	<%
		response.sendRedirect("Controller?command=go_to_main_page");
	%>
	
</body>
</html>