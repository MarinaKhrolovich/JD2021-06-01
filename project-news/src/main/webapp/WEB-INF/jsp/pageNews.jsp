<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>${profile}</title>

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
	text-align: center; 
}
</style>

<link href= "css/style.css" type = "text/css" rel="stylesheet"/> 
<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />
<fmt:message bundle="${loc}" key="local.profile" var="profile" />
<fmt:message bundle="${loc}" key="local.message.photo" var="photo" />
</head>
<body>


	<h1>${news.getTitle()}</h1>

	<br /> 
	
	<p>${news.getBrief()}</p>
	
	<br /> 

</body>
</html>