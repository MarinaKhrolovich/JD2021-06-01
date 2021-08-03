<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>AUTHORIZATION</title>
<link href= "css/style.css" type = "text/css" rel="stylesheet"/> 
<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />
<fmt:message bundle="${loc}" key="local.authorizationbutton" var="auth_button" />
<fmt:message bundle="${loc}" key="local.login" var="login" />
<fmt:message bundle="${loc}" key="local.password" var="password" />
<fmt:message bundle="${loc}" key="local.changelanguage" var="change_lang" />
<fmt:message bundle="${loc}" key="local.enter" var="enter" />
</head>
<body>

	<blockquote>	
		<form action="Controller" method="get" onchange="submit()">
			<input type="hidden" name="command" value="change_local">
			
				<select name="local">
					<option value = "en" ${local == 'en' ? 'selected' : ''}>ENG</option>
					<option value = "be" ${local == 'be' ? 'selected' : ''}>BY</option>
					<option value = "ru" ${local == 'ru' ? 'selected' : ''}>RU</option>
				</select>
		</form>	
	</blockquote>
	
	<br /> 
	
	<font color="red" size="18">
	<%
	   String mes = (String)request.getParameter("message");
	 if(mes != null){
		 out.print(mes);
	 }
	
	%>
	</font>
	<form action="Controller" method="post">
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
</body>
</html>