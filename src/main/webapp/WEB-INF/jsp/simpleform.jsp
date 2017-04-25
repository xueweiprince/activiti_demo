<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/easyui-1.4.2/jquery.easyui.min.js"></script>
<title>Insert title here</title>
</head>
<body>
	<h1>-------------------</h1>
	<table id="dg" title="My Users" class="easyui-datagrid"
		style="width: 550px; height: 250px" url="get_users.php"
		toolbar="#toolbar" rownumbers="true" fitColumns="true"
		singleSelect="true">
		<thead>
			<tr>
				<th field="firstname" width="50">First Name</th>
				<th field="lastname" width="50">Last Name</th>
				<th field="phone" width="50">Phone</th>
				<th field="email" width="50">Email</th>
			</tr>
		</thead>
	</table>
	<div id="toolbar">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true"
			onclick="newUser()">New User</a> <a href="#"
			class="easyui-linkbutton" iconCls="icon-edit" plain="true"
			onclick="editUser()">Edit User</a> <a href="#"
			class="easyui-linkbutton" iconCls="icon-remove" plain="true"
			onclick="destroyUser()">Remove User</a>
	</div>
</body>
</html>