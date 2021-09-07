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
<fmt:message bundle="${loc}" key="local.update_news" var="update_news" />
<fmt:message bundle="${loc}" key="local.button.gomain" var="gomain" />
<style type="text/css">
</style>
<link href="css/style.css" type="text/css" rel="stylesheet" />
<title>${update_news}</title>
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


	<h1>${update_news}</h1>

	<br />

	<form action="Controller?id_news=${requestScope.news.getId()}"
		method="post">
		<input type="hidden" name="command" value="update_news" />
		<fieldset>
			<legend>${add_news}</legend>
			<br /> 
				${title}:<input type="text" name="title"
				value="${requestScope.news.getTitle()}" size="100" maxlength="45"
				required /><br /> <br /> 
				${brief}:<input type="text" name="brief"
				value="${requestScope.news.getBrief()}" size="100" maxlength="200"
				required /> <br /> <br /> 
				${content}: <br />
			<textarea name="content" cols=" 80" rows="15" maxlength="1000"
				required>
				${requestScope.news.getContent()}
			</textarea>
			<br /> <br /> <input type="submit" value="${submit}" /><br /> <br />
		</fieldset>

	</form>

	<br />

	<form action="Controller" method="post">
		<input type="hidden" name="command" value="go_to_main_page" /> <input
			type="submit" value="${gomain}" /><br />
	</form>

	<br />

</body>
</html>