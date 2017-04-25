<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<%
	String requestPath=request.getContextPath();
%>

<link rel="stylesheet" type="text/css" href="../../easyui-1.4.5/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="../../easyui-1.4.5/themes/icon.css">

<script type="text/javascript" src="../../js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="../../easyui-1.4.5/jquery.easyui.min.js"></script>

<script type="text/javascript">
	
		function search_(){
			$.ajax({
				url:"<%=requestPath %>/activiti/process/singleuser.do",
				type:"post",
				success:function(data){
					debugger;
					data1={rows:[data.modelMap.user],total:1}
					$('#dg').datagrid('loadData',data1);
				},
				error:function(text){
					debugger;
				}
			});
		}

</script>

<title>Insert title here</title>
</head>
<body>
	<h1>-------------------</h1>
	<table id="dg" title="My Users" class="easyui-datagrid"
		style="width: 550px; height: 250px" toolbar="#toolbar"
		rownumbers="true" fitColumns="true" singleSelect="true" data-options="
				iconCls: 'icon-edit',
				singleSelect: true,
				url: 'datagrid_data1.json',
				method:'get'">
		<thead>
			<tr>
				<th field="username" editor="text" width="50">username</th>
				<th field="password" editor="{type:'validatebox',options:{required:true}}" width="50">password</th>
				<th field="nick" width="50">nick</th>
				<th field="sex" width="50">sex</th>
				<th field="age" width="50">age</th>
				<th field="brithday" width="50">brithday</th>
			</tr>
		</thead>
	</table>
	<div id="toolbar">
		<a href="#" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="javascript:search_()">New User</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newUser()">New User</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editUser()">Edit User</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyUser()">Remove User</a>
	</div>
</body>
</html>