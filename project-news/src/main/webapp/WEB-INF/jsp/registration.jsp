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
<fmt:message bundle="${loc}" key="local.registrationbutton"
	var="reg_button" />
<fmt:message bundle="${loc}" key="local.login" var="login" />
<fmt:message bundle="${loc}" key="local.password" var="password" />
<fmt:message bundle="${loc}" key="local.changelanguage"
	var="change_lang" />
<fmt:message bundle="${loc}" key="local.enter" var="enter" />
<fmt:message bundle="${loc}" key="local.submit" var="submit" />
<fmt:message bundle="${loc}" key="local.name" var="name" />
<fmt:message bundle="${loc}" key="local.surname" var="surname" />
<fmt:message bundle="${loc}" key="local.yearBirthday" var="yearBirthday" />
<fmt:message bundle="${loc}" key="local.sex" var="sex" />
<fmt:message bundle="${loc}" key="local.female" var="female" />
<fmt:message bundle="${loc}" key="local.male" var="male" />
<fmt:message bundle="${loc}" key="local.button.gomain" var="gomain" />
<title>${reg_button}</title>
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

	<font color="red" size="5"> <c:if test="${param.message!=null}">
			<c:out value="${param.message}" />
		</c:if>
	</font>

	<br />
	<br />

	<form action="Controller" method="post">
		<input type="hidden" name="command" value="registration_new_user" />
		<fieldset>
			<legend>${reg_button}</legend>
			<br /> ${login}*:<input type="text" name="login"
				value="${param.login}" required /><br /> <br /> ${password}*:<input
				type="password" name="password" value="" minlength="4" required /><br />
			<br /> ${name}*:<input type="text" name="name" value="${param.name}"
				required /><br /> <br /> ${surname}:<input type="text"
				name="surname" value="${param.surname}" /><br /> <br /> ${sex}: <input
				id="female" type="radio" name="sex" value="f"
				${param.sex == 'f'|| param.sex == null ? 'checked' : ''}> <label
				for="female">${female}</label> <input id="male" type="radio"
				name="sex" value="m" ${param.sex == 'm' ? 'checked' : ''}> <label
				for="male">${male}</label> <br /> <br /> ${yearBirthday}:<input
				type="text" name="yearBirthday" minlength="4" maxlength="4"
				pattern="[0-9]*" title="You should enter only numbers"
				value="${param.yearBirthday}" /><br />
		</fieldset>

		<br /> <input type="submit" value="${submit}" /><br />

	</form>

	<br />

	<a href="Controller?command=go_to_main_page">${gomain}</a>

	<br />

</body>
</html>