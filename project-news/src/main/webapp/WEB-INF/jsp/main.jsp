<%@ page import="by.ftp.projectnews.bean.News"%>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />
<fmt:message bundle="${loc}" key="local.registrationbutton" var="reg_button" />
<fmt:message bundle="${loc}" key="local.authorizationbutton" var="auth_button" />
<fmt:message bundle="${loc}" key="local.title" var="title" />
<fmt:message bundle="${loc}" key="local.changelanguage" var="change_lang" />
<style type="text/css">
h1{
	width: 570рх ;
	padding : 15px ;
	margin: 0рх auto 0рх auto;
	border-top: 2рх solid #000;
	border-bottom: 2px solid #000;
}

h2{
	width: 570рх ;
	margin: 0рх auto 0рх auto;
}
</style>
<link href= "css/style.css" type = "text/css" rel="stylesheet" /> 
<title>${title}</title>
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

	<br /> 
	<c:if test="${newses!=null}">	
		<c:forEach var="news" items="${newses}">
       		<div class="page">

       			<a href = "Controller?command=go_to_page_news&id_news=${news.getId()}">
        			
        			<h2>${news.getTitle()}</h2>
        		</a>
        		<p><c:out value="${news.getBrief()}" /></p>
       			
       		</div>	
           	<br />
    	</c:forEach>
	</c:if>
	
</body>
</html>