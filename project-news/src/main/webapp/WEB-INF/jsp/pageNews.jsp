<%@ page import="by.ftp.projectnews.bean.News"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<style type="text/css">
</style>

<link href="css/style.css" type="text/css" rel="stylesheet" />
<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />
<fmt:message bundle="${loc}" key="local.news" var="title_news" />
<fmt:message bundle="${loc}" key="local.message.photo" var="photo" />
<fmt:message bundle="${loc}" key="local.button.gomain" var="gomain" />
<title>${title_news}</title>
</head>
<body>

	<blockquote>
		<form action="Controller" method="get" onchange="submit()">
			<input type="hidden" name="command" value="change_local"> <select
				name="local">
				<option value="en" ${local == 'en' ? 'selected' : ''}>ENG</option>
				<option value="be" ${local == 'be' ? 'selected' : ''}>BY</option>
				<option value="ru" ${local == 'ru' ? 'selected' : ''}>RU</option>
			</select>
		</form>
	</blockquote>
	
	<br />
	<br />
	
	<font color="red" size="3"> <c:if test="${param.message!=null}">
			<c:out value="${param.message}" />
		</c:if>
	</font>
	
	<div class="page">
		<h1>${requestScope.news.getTitle()}</h1>

		<br />

		<p>${requestScope.news.getContent()}</p>

		<br />
	</div>

	<br />

	<a href="Controller?command=go_to_main_page">${gomain}</a>
	
</body>
</html>