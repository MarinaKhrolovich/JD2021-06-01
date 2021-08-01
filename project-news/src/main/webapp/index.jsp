<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>PORTAL NEWS</title>
	<fmt:setLocale value="${sessionScope.local}" />
	<fmt:setBundle basename="localization.local" var="loc" />

  <style type="text/css">
   H1 { 
    font-size: 120%; /* Размер шрифта */
    font-family: Verdana, Arial, Helvetica, sans-serif; /* Семейство шрифта */
    color: #336; /* Цвет текста */
   }
  </style>
</head>
<body>

	<%
		response.sendRedirect("Controller?command=go_to_main_page");
	%>
 

</body>
</html>