<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>AUTHORIZATION</title>
<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />
<fmt:message bundle="${loc}" key="local.authorizationbutton" var="auth_button" />
<fmt:message bundle="${loc}" key="local.login" var="login" />
<fmt:message bundle="${loc}" key="local.password" var="password" />
<fmt:message bundle="${loc}" key="local.changelanguage" var="change_lang" />
<fmt:message bundle="${loc}" key="local.enter" var="enter" />
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
		<legend>${auth_button}</legend>
		${login}:<br />
		<input type="text" name="login" value="" /><br /> 
		
		<br /> 
		${password}:<br />
		<input type="password" name="password" value="" /><br /> 
		
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