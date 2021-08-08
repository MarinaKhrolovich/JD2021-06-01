<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<link href= "css/style.css" type = "text/css" rel="stylesheet"/>
<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />
<fmt:message bundle="${loc}" key="local.error" var="error" />
<title>${error}</title>
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

<h1>${error}</h1>
	
	<br /> 
	
	<font color="red" size="18">
	<%
	   String mes = (String)request.getAttribute("message");
	   if(mes != null){
		  out.print(mes);
	 }
	
	%>
	</font>
	
		
	<br /> 
</body>
</html>