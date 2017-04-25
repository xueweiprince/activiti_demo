<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.util.List,org.activiti.engine.repository.ProcessDefinition" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	String requestPath=request.getContextPath();
%>
<!-- 
<style type="text/css">
	td{
		text-align: center;
		width: 15%;
		border: 1px;
		padding:0;
		background-color:#ff0000;
	}
	
</style>
 -->
<link href="bootstrap/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="bootstrap/bootstrap.min.js"></script>
<script type="text/javascript" src="js/jquery-1.9.1.min.js"></script>
<script type="text/javascript">
	
	$(document).ready(
		function(){
			debugger;
			alert("----------");
			this.location="<%=requestPath %>/activiti/process/list.do";
		}
	)

	function del(deployId){
		this.location="<%=requestPath %>/activiti/operation/del.do?deploymentId="+deployId;
	}
</script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div>------------activitList-----------</div>
	<c:if test="${definitionList!=null}">
		<c:if test="${definitionList.size()!=0}">
		<table class="table">
			<thead>
				<th>流程定义id</th>
				<th>部署id</th>
				<th>流程定义名称</th>
				<th>流程定义key</th>
				<th>版本号</th>
				<th>xml资源名称</th>
				<th>图片资源名称</th>
				<th>操作</th>
			</thead>

		<tbody>
			<c:forEach item=${definitionList } >
			<tr>
				<td>${id}</td>
				<td>${deploymentId}</td>
				<td>${name}</td>
				<td>${key}</td>
				<td>${version}</td>
				<td><a target="_blank" href="/activiti/process/read.do?pid=${id}&resourceName=${ResourceName}">${ResourceName}</a></td>
				<td><a target="_blank" href="/activiti/process/read.do?pid=${id}&resourceName=${diagramResourceName}">${diagramResourceName}</a></td>
				<td><input type="button" value="del" onclick="del(${deploymentId})"/><input type="button" value="handle"/></td>
			</tr>
			</c:forEach>
		</tbody>
	
		</table>
		</c:if>
	</c:if>
</body>
</html>