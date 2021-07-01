<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registration</title>
</head>
<body>
	<form action="Controller" method="post">
		<input type="hidden" name="command" value="registration_new_user" /> 
		<fieldset>
		<legend>REGISTRATION</legend>
		Enter login:<input type="text" name="login" value="" /><br /> 
		<br /> 
		Enter password:<input type="password" name="password" value="" /><br />
		<br />  
		Enter name:<input type="text" name="name" value="" /><br /> 
		<br /> 
		Enter surname:<input type="text" name="surname" value="" /><br /> 
		<br /> 
		Enter sex:
		<input id="female" type="radio" name="sex" value="f"> 
		<label for= "female">female</label>
		<input id="male" type= "radio" name="sex" value= "m">
		<label for= "male">male</label>	<br />
		<br /> 
		Enter yearBirthday:<input type="text" name="yearBirthday" value="" /><br /> 
		
		</fieldset>
		<br /> 
		<input type="submit" value="Enter" /><br />
	</form>
</body>
</html>