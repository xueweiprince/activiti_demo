<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page
	import="java.util.List,org.activiti.engine.repository.ProcessDefinition"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	String requestPath = request.getContextPath();
%>
<style type="text/css">
	.table th, .table td {
		text-align: center;
		vertical-align: middle;
		valign:"middle";
		width: 12.5%;
	}
	
	ul{
		text-align: center;
	}
</style>

<link href="../bootstrap/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="../js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="../bootstrap/bootstrap.min.js"></script>
<script type="text/javascript">
	
	$(document).ready(function(){
		var option={
				url:"process/list2.do",
				type:"post",
				dataType:"json",
				success:function(data){
					$.each(data,function(i,v){
						debugger;
						var tr="<tr><td>"+v.id+"</td><td>"+
							v.deploymentId+"</td><td>"+
							v.name+"</td><td>"+
							v.key+"</td><td>"+
							v.vrsion+"</td><td>"+
							"<a target='_blank' href='process/read.do?pid="+v.id+"&resourceName="+v.resourceName+"'>"+v.resourceName+"</a></td><td>"+
							"<a target='_blank' href='process/read.do?pid="+v.id+"&resourceName="+v.diagramResourceName+"'>"+v.diagramResourceName+"</a></td>"+
							"<td><div class='btn-group btn-group-xs' style='margin-right: 2px;'> <input type='button' class='btn btn-default' value='del' onclick='del("+v.deploymentId+")' /></div><div class='btn-group btn-group-xs'><input type='button' class='btn btn-default' value='handle' /></div></td></tr>";
						$("tbody").append(tr);
					});
					debugger;
				},
				error:function(){
					alert(">>>>>>>>>>>");
				}

		}
		$.ajax(option);
	});
	
	
	function del(deployId){
		this.location="<%=requestPath%>/activiti/operation/del.do?deploymentId="+deployId;
	}
</script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div class="panel panel-default">
		<div class="panel-heading">
	      	流程部署
	   	</div>
		<form class="form-inline" role="form">
		   <div class="form-group">
		      <label class="sr-only" for="inputfile">文件输入</label>
		      <input type="file" id="inputfile">
		   </div>
		   <button type="submit" class="btn btn-default">提交</button>
		</form>
	</div>
	<table class="table table-bordered table-hover table-condensed">
		<thead align="center">
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
		</tbody>

	</table>
</body>
<ul class="pagination">
  <li><a href="#">&laquo;</a></li>
  <li><a href="#">1</a></li>
  <li><a href="#">2</a></li>
  <li><a href="#">3</a></li>
  <li><a href="#">4</a></li>
  <li><a href="#">5</a></li>
  <li><a href="#">&raquo;</a></li>
</ul>
</html>