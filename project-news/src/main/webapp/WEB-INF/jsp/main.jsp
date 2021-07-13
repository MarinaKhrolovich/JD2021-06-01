<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>News Portal</h1>
	<form action="Controller" method="post">
		<input type="hidden" name="command" value="registration"/>
		<input type="submit" value="Registration" />
	</form>
	<br />
	<form action="Controller" method="post">
	    <input type="hidden" name="command" value="authorization"/>
		<input type="submit" value="Authorization" />
	</form>
	
	<%
	//newses = request.getAttribute("newses");
	
	%>
	
</body>
</html>