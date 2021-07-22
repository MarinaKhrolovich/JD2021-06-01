<%@page import="by.ftp.projectnews.bean.News"%>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>News Portal</title>
 
<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />
<fmt:message bundle="${loc}" key="local.registrationbutton" var="reg_button" />
<fmt:message bundle="${loc}" key="local.authorizationbutton" var="auth_button" />
<fmt:message bundle="${loc}" key="local.title" var="title" />
<fmt:message bundle="${loc}" key="local.changelanguage" var="change_lang" />
</head>
<body>
<h1>${title}</h1>
	<form action="Controller" method="post">
		<input type="hidden" name="command" value="registration"/>
		<input type="hidden" name="local" value="en"/>
		<input type="submit" value="${reg_button}" />
	</form>
	<br />
	<form action="Controller" method="post">
	    <input type="hidden" name="command" value="authorization"/>
	    <input type="hidden" name="local" value="ru"/>
		<input type="submit" value="${auth_button}" />
	</form>
	
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
		
	<%
	List<News> newses = (List<News>)request.getAttribute("newses");
	
	if(newses!=null){
		for(News news:newses)
		{
			out.println("<h1>"+news.getTitle()+"</h1>");
			out.println("<h2>"+news.getBrief()+"</h2>");
			out.println("----------------------------");
			
		}
	}
	%>
	
</body>
</html>