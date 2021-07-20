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
		Enter login:<input type="text" name="login" value="" required/><br /> 
		<br /> 
		Enter password:<input type="password" name="password" value="" required/><br />
		<br />  
		Enter name:<input type="text" name="name" value="" required/><br /> 
		<br /> 
		Enter surname:<input type="text" name="surname" value="" /><br /> 
		<br /> 
		Enter sex:
		<input id="female" type="radio" name="sex" value="f"> 
		<label for= "female">female</label>
		<input id="male" type= "radio" name="sex" value= "m">
		<label for= "male">male</label>	<br />
		<br /> 
		Enter yearBirthday:<input type="text" name="yearBirthday" minlength = "4" maxlength = "4" pattern ="[0-9]*" title ="You should enter only numbers" value="" /><br /> 
		
		</fieldset>
		<br /> 
		<input type="submit" value="Enter" /><br />
	</form>
	
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