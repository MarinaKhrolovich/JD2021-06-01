<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<link href="css/style.css" type="text/css" rel="stylesheet" />
<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />
<fmt:message bundle="${loc}" key="local.profile" var="profile" />
<fmt:message bundle="${loc}" key="local.message.photo" var="photo" />
<fmt:message bundle="${loc}" key="local.submit" var="submit" />
<fmt:message bundle="${loc}" key="local.title.news" var="title" />
<fmt:message bundle="${loc}" key="local.brief" var="brief" />
<fmt:message bundle="${loc}" key="local.content" var="content" />
<fmt:message bundle="${loc}" key="local.add_news" var="add_news" />
<fmt:message bundle="${loc}" key="local.button.delete_news"
	var="delete_news_button" />
<fmt:message bundle="${loc}" key="local.button.update_news"
	var="update_news_button" />
<fmt:message bundle="${loc}" key="local.button.gomain" var="gomain" />

<style type="text/css">
</style>
<link href="css/style.css" type="text/css" rel="stylesheet" />
<title>${profile}</title>
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

	<font color="red" size="3"> <c:if test="${param.message!=null}">
			<c:out value="${param.message}" />
		</c:if>
	</font>

	<h1>${profile}</h1>

	<br />

	<a href="Controller?command=go_to_add_page_news">${add_news}</a>

	<br />
	<br />
	
	<a href="Controller?command=go_to_main_page">${gomain}</a>
	<br />
	<br />
	<c:if test="${requestScope.newses_author!=null}">
		<c:forEach var="news" items="${requestScope.newses_author}">
			<div class="page">

				<a href="Controller?command=go_to_page_news&id_news=${news.getId()}">

					<h2>${news.getTitle()}</h2>
				</a>
				<p>
					<c:out value="${news.getBrief()}" />
				</p>
				<form action="Controller?id_news=${news.getId()}" method="post">
					<input type="hidden" name="command" value="delete_news" /> <input
						type="submit" value="${delete_news_button}" />
				</form>
				<br />
				<form action="Controller?id_news=${news.getId()}" method="post">
					<input type="hidden" name="command" value="go_to_update_news_page" /> <input
						type="submit" value="${update_news_button}" />
				</form>
			</div>
			<br />
		</c:forEach>
	</c:if>

</body>
</html>