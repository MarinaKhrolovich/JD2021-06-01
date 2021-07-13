<%@page import="by.ftp.projectnews.bean.News"%>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>News Portal</title>
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
	List<News> newses = (List<News>)request.getAttribute("newses");
	
	if(newses!=null){
		for(News news:newses)
		{
			out.println("<h1>"+news.getTitle()+"</h1>");
			out.println("<h2>"+news.getBrief()+"</h2>");
			
		}
	}
	%>
	
</body>
</html>