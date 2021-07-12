<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>AUTHORIZATION</title>
</head>
<body>

	<font color="red" size="18">
	<%
	   String mes = (String)request.getParameter("message");
	 if(mes != null){
		 out.print(mes);
	 }
	
	%>
	</font>
	<form action="Controller" method="get">
		<input type="hidden" name="command" value="authorization_user" /> 
		<fieldset>
		<legend>AUTHORIZATION</legend>
		Enter login:<br />
		<input type="text" name="login" value="" /><br /> 
		
		<br /> 
		Enter password:<br />
		<input type="password" name="password" value="" /><br /> 
		
		</fieldset>
		<br />
		<input type="submit" value="Enter" /><br />
	</form>
</body>
</html>