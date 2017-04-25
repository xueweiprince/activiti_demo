<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.util.List,org.activiti.engine.repository.ProcessDefinition" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	String requestPath=request.getContextPath();
	System.out.println(requestPath);
	List<ProcessDefinition> definitionList=(List<ProcessDefinition>)request.getAttribute("definitionList");
%>
<style type="text/css">
	td{
		text-align: center;
		width: 15%;
		border: 1px;
	}
</style>

<script type="text/javascript">
	function del(deployId){
		this.location="<%=requestPath %>/activiti/operation/del.do?deploymentId="+deployId;
	}
</script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body border="1" style="border-collapse: collapse; border-color: blue;">
	<div>------------activitList-----------</div>
	<%
		if(definitionList!=null){
			if(definitionList.size()!=0){
	%>
		<table>
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
	<%
		for(ProcessDefinition p:definitionList){
	%>
		<tbody>
			<tr>
				<td><%=p.getId() %></td>
				<td><%=p.getDeploymentId() %></td>
				<td><%=p.getName() %></td>
				<td><%=p.getKey() %></td>
				<td><%=p.getVersion() %></td>
				<td><a target="_blank" href="<%=requestPath %>/activiti/process/read.do?pid=<%=p.getId() %>&resourceName=<%=p.getResourceName() %>"><%=p.getResourceName() %></a></td>
				<td><a target="_blank" href="<%=requestPath %>/activiti/process/read.do?pid=<%=p.getId() %>&resourceName=<%=p.getDiagramResourceName() %>"><%=p.getDiagramResourceName() %></a></td>
				<td><input type="button" value="del" onclick="del(<%=p.getDeploymentId() %>)"/><input type="button" value="handle"/></td>
			</tr>
		</tbody>
	<%		
		}
	%>
	
		</table>
	<%
			}
		}
	%>
</body>
</html>