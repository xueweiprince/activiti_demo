<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<form action="/springActiviti/basecommand.do" method="get">
		<input name="title" value="test">
		<input type="submit" value="命令控制器">
	</form>
	
	<form action="/springActiviti/simpleform.do" method="get">
		<input name="title" value="test">
		<input type="submit" value="表单控制器">
	</form>
	
	<form action="/springActiviti/multicontrol.do" method="get">
		<input type="submit" name="method" value="insert">
		<input type="submit" name="method" value="update">
		<input type="submit" name="method" value="delete">
	</form>

</body>
</html>