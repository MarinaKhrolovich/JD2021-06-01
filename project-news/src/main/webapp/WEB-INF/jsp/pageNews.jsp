<%@ page import="by.ftp.projectnews.bean.News"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<style type="text/css">
h1{
	width: 570рх ;
	padding : 15px ;
	margin: 0рх auto 0рх auto;
	border-top: 2рх solid #000;
	border-bottom: 2px solid #000;
	text-align: center; 
}

p{
	width: 570рх ;
	padding : 15px ;
	margin: 0рх auto 0рх auto;
}
</style>

<link href= "css/style.css" type = "text/css" rel="stylesheet"/> 
<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />
<fmt:message bundle="${loc}" key="local.news" var="title_news" />
<fmt:message bundle="${loc}" key="local.message.photo" var="photo" />
<title>${title_news}</title>
</head>
<body>

<div class="page">
	<h1>${news.getTitle()}</h1>

	<br /> 
	
	<p>${news.getContent()}</p>
	
	<br /> 
</div>

</body>
</html>