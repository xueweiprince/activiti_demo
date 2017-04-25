<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>

<%
	String name = (String) request.getAttribute("helloWrold");
	String method = (String) request.getAttribute("excute");
%>
</head>
<body>
	<h1>------------------></h1>
	<label name="title"><%=name%></label>
	<label name="title"><%=method%></label>
</body>
</html>