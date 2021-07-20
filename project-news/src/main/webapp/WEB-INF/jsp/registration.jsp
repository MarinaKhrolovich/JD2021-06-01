<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Registration</title>
<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />
<fmt:message bundle="${loc}" key="local.registrationbutton" var="reg_button" />
<fmt:message bundle="${loc}" key="local.login" var="login" />
<fmt:message bundle="${loc}" key="local.password" var="password" />
<fmt:message bundle="${loc}" key="local.changelanguage" var="change_lang" />
<fmt:message bundle="${loc}" key="local.enter" var="enter" />
<fmt:message bundle="${loc}" key="local.name" var="name" />
<fmt:message bundle="${loc}" key="local.surname" var="surname" />
<fmt:message bundle="${loc}" key="local.yearBirthday" var="yearBirthday" />
<fmt:message bundle="${loc}" key="local.sex" var="sex" />
<fmt:message bundle="${loc}" key="local.female" var="female" />
<fmt:message bundle="${loc}" key="local.male" var="male" />

</head>
<body>
	<form action="Controller" method="post">
		<input type="hidden" name="command" value="registration_new_user" /> 
		<fieldset>
		<legend>${reg_button}</legend>
		${login}:<input type="text" name="login" value="" required/><br /> 
		<br /> 
		${password}:<input type="password" name="password" value="" required/><br />
		<br />  
		${name}:<input type="text" name="name" value="" required/><br /> 
		<br /> 
		${surname}:<input type="text" name="surname" value="" /><br /> 
		<br /> 
		${sex}:
		<input id="female" type="radio" name="sex" value="f"> 
		<label for= "female">${female}</label>
		<input id="male" type= "radio" name="sex" value= "m">
		<label for= "male">${male}</label>	<br />
		<br /> 
		${yearBirthday}:<input type="text" name="yearBirthday" minlength = "4" maxlength = "4" pattern ="[0-9]*" title ="You should enter only numbers" value="" /><br /> 
		
		</fieldset>
		<br /> 
		<input type="submit" value="${enter}" /><br />
		</form>
	
		<br /> 

	<form action="Controller" method="post">
		<input type="hidden" name="command" value="change_local">
		<input id="en" type= "radio" name="local" value= "en" checked="checked">
		<label for="en">ENG</label>	
		<input id="ru" type="radio" name="local" value="ru" >
		<label for= "ru">RU</label>
		<br />
		<input type="submit" value="${change_lang}" />
	</form>	
		
	<br /> 
</body>
</html>