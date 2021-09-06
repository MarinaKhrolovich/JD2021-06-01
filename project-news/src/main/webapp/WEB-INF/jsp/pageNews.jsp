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
<fmt:message bundle="${loc}" key="local.go_main_page" var="go_main" />
<title>${title_news}</title>
</head>
<body>

	<div class="page">
		<h1>${news.getTitle()}</h1>

		<br />

		<p>${news.getContent()}</p>

		<br />
	</div>

	<br />

	<form action="Controller" method="post">
		<input type="hidden" name="command" value="go_to_main_page" /> <input
			type="submit" value="${go_main}" /><br />
	</form>

</body>
</html>