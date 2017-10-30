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
		width: 12%;
		border: 1px;
	}
</style>

<script type="text/javascript">
	function del(deployId){
		this.location="<%=requestPath %>/activiti/operation/del.do?deploymentId="+deployId;
	}
</script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<!-- bootstrap -->
<link href="/css/bootstrap/bootstrap.css" rel="stylesheet" />
<link href="/css/bootstrap/bootstrap-responsive.css" rel="stylesheet" />
<link href="/css/bootstrap/bootstrap-overrides.css" type="text/css" rel="stylesheet" />

	<!-- libraries -->
	<link href="/css/lib/jquery-ui-1.10.2.custom.css" rel="stylesheet" type="text/css" />
	<link href="/css/lib/font-awesome.css" type="text/css" rel="stylesheet" />

	<!-- global styles -->
	<link rel="stylesheet" type="text/css" href="/css/layout.css" />
	<link rel="stylesheet" type="text/css" href="/css/elements.css" />
	<link rel="stylesheet" type="text/css" href="/css/icons.css" />

	<!-- this page specific styles -->
	<link rel="stylesheet" href="/css/compiled/index.css" type="text/css" media="screen" />

	<!-- open sans font -->
	<!-- <link href='http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800' rel='stylesheet' type='text/css' /> -->

	<!-- lato font -->
	<!-- <link href='http://fonts.googleapis.com/css?family=Lato:300,400,700,900,300italic,400italic,700italic,900italic' rel='stylesheet' type='text/css' /> -->

	<!--[if lt IE 9]>
	<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
	<![endif]-->

<title>Insert title here</title>
</head>
<body border="1" style="border-collapse: collapse; border-color: blue;">

<!-- navbar -->
<div class="navbar navbar-inverse">
	<div class="navbar-inner">
		<button type="button" class="btn btn-navbar visible-phone" id="menu-toggler">
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
		</button>

		<a class="brand" href="index.html"><img src="/img/logo.png" /></a>

		<ul class="nav pull-right">
			<li class="hidden-phone">
				<input class="search" type="text" />
			</li>
			<li class="notification-dropdown hidden-phone">
				<a href="#" class="trigger">
					<i class="icon-warning-sign"></i>
					<span class="count">8</span>
				</a>
				<div class="pop-dialog">
					<div class="pointer right">
						<div class="arrow"></div>
						<div class="arrow_border"></div>
					</div>
					<div class="body">
						<a href="#" class="close-icon"><i class="icon-remove-sign"></i></a>
						<div class="notifications">
							<h3>You have 6 new notifications</h3>
							<a href="#" class="item">
								<i class="icon-signin"></i> New user registration
								<span class="time"><i class="icon-time"></i> 13 min.</span>
							</a>
							<a href="#" class="item">
								<i class="icon-signin"></i> New user registration
								<span class="time"><i class="icon-time"></i> 18 min.</span>
							</a>
							<a href="#" class="item">
								<i class="icon-envelope-alt"></i> New message from Alejandra
								<span class="time"><i class="icon-time"></i> 28 min.</span>
							</a>
							<a href="#" class="item">
								<i class="icon-signin"></i> New user registration
								<span class="time"><i class="icon-time"></i> 49 min.</span>
							</a>
							<a href="#" class="item">
								<i class="icon-download-alt"></i> New order placed
								<span class="time"><i class="icon-time"></i> 1 day.</span>
							</a>
							<div class="footer">
								<a href="#" class="logout">View all notifications</a>
							</div>
						</div>
					</div>
				</div>
			</li>
			<li class="notification-dropdown hidden-phone">
				<a href="#" class="trigger">
					<i class="icon-envelope-alt"></i>
				</a>
				<div class="pop-dialog">
					<div class="pointer right">
						<div class="arrow"></div>
						<div class="arrow_border"></div>
					</div>
					<div class="body">
						<a href="#" class="close-icon"><i class="icon-remove-sign"></i></a>
						<div class="messages">
							<a href="#" class="item">
								<img src="/img/contact-img.png" class="display" />
								<div class="name">Alejandra Galván</div>
								<div class="msg">
									There are many variations of available, but the majority have suffered alterations.
								</div>
								<span class="time"><i class="icon-time"></i> 13 min.</span>
							</a>
							<a href="#" class="item">
								<img src="/img/contact-img2.png" class="display" />
								<div class="name">Alejandra Galván</div>
								<div class="msg">
									There are many variations of available, have suffered alterations.
								</div>
								<span class="time"><i class="icon-time"></i> 26 min.</span>
							</a>
							<a href="#" class="item last">
								<img src="img/contact-img.png" class="display" />
								<div class="name">Alejandra Galván</div>
								<div class="msg">
									There are many variations of available, but the majority have suffered alterations.
								</div>
								<span class="time"><i class="icon-time"></i> 48 min.</span>
							</a>
							<div class="footer">
								<a href="#" class="logout">View all messages</a>
							</div>
						</div>
					</div>
				</div>
			</li>
			<li class="dropdown">
				<a href="#" class="dropdown-toggle hidden-phone" data-toggle="dropdown">
					Your account
					<b class="caret"></b>
				</a>
				<ul class="dropdown-menu">
					<li><a href="personal-info.html">Personal info</a></li>
					<li><a href="#">Account settings</a></li>
					<li><a href="#">Billing</a></li>
					<li><a href="#">Export your data</a></li>
					<li><a href="#">Send feedback</a></li>
				</ul>
			</li>
			<li class="settings hidden-phone">
				<a href="personal-info.html" role="button">
					<i class="icon-cog"></i>
				</a>
			</li>
			<li class="settings hidden-phone">
				<a href="signin.html" role="button">
					<i class="icon-share-alt"></i>
				</a>
			</li>
		</ul>
	</div>
</div>
<!-- end navbar -->

<!-- sidebar -->
<div id="sidebar-nav">
	<ul id="dashboard-menu">
		<li class="active">
			<div class="pointer">
				<div class="arrow"></div>
				<div class="arrow_border"></div>
			</div>
			<a href="index.html">
				<i class="icon-home"></i>
				<span>Home</span>
			</a>
		</li>
		<li>
			<a href="activiti/process/list.do">
				<i class="icon-signal"></i>
				<span>Charts</span>
			</a>
		</li>
		<li>
			<a class="dropdown-toggle" href="#">
				<i class="icon-group"></i>
				<span>Users</span>
				<i class="icon-chevron-down"></i>
			</a>
			<ul class="submenu">
				<li><a href="chahua3219/user-list.html">User list</a></li>
				<li><a href="chahua3219/new-user.html">New user form</a></li>
				<li><a href="chahua3219/user-profile.html">User profile</a></li>
			</ul>
		</li>
		<li>
			<a class="dropdown-toggle" href="#">
				<i class="icon-edit"></i>
				<span>Forms</span>
				<i class="icon-chevron-down"></i>
			</a>
			<ul class="submenu">
				<li><a href="chahua3219/form-showcase.html">Form showcase</a></li>
				<li><a href="form-wizard.html">Form wizard</a></li>
			</ul>
		</li>
		<li>
			<a href="gallery.html">
				<i class="icon-picture"></i>
				<span>Gallery</span>
			</a>
		</li>
		<li>
			<a href="calendar.html">
				<i class="icon-calendar-empty"></i>
				<span>Calendar</span>
			</a>
		</li>
		<li>
			<a href="tables.html">
				<i class="icon-th-large"></i>
				<span>Tables</span>
			</a>
		</li>
		<li>
			<a class="dropdown-toggle ui-elements" href="#">
				<i class="icon-code-fork"></i>
				<span>UI Elements</span>
				<i class="icon-chevron-down"></i>
			</a>
			<ul class="submenu">
				<li><a href="ui-elements.html">UI Elements</a></li>
				<li><a href="icons.html">Icons</a></li>
			</ul>
		</li>
		<li>
			<a href="personal-info.html">
				<i class="icon-cog"></i>
				<span>My Info</span>
			</a>
		</li>
		<li>
			<a class="dropdown-toggle" href="#">
				<i class="icon-share-alt"></i>
				<span>Extras</span>
				<i class="icon-chevron-down"></i>
			</a>
			<ul class="submenu">
				<li><a href="code-editor.html">Code editor</a></li>
				<li><a href="grids.html">Grids</a></li>
				<li><a href="signin.html">Sign in</a></li>
				<li><a href="signup.html">Sign up</a></li>
			</ul>
		</li>
	</ul>
</div>
<!-- end sidebar -->

<!-- main container -->
<div class="content">
	<%
		if(definitionList!=null){
			if(definitionList.size()!=0){
	%>
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
	<%
		for(ProcessDefinition p:definitionList){
	%>
		<tbody>
			<tr>
				<td width="10%"><%=p.getId() %></td>
				<td width="10%"><%=p.getDeploymentId() %></td>
				<td width="10%"><%=p.getName() %></td>
				<td width="10%"><%=p.getKey() %></td>
				<td width="10%"><%=p.getVersion() %></td>
				<td width="10%"><a target="_blank" href="<%=requestPath %>/activiti/process/read.do?pid=<%=p.getId() %>&resourceName=<%=p.getResourceName() %>"><%=p.getResourceName() %></a></td>
				<td width="10%"><a target="_blank" href="<%=requestPath %>/activiti/process/read.do?pid=<%=p.getId() %>&resourceName=<%=p.getDiagramResourceName() %>"><%=p.getDiagramResourceName() %></a></td>
				<td width="10%"><input type="button" value=" del  " class="btn btn-primary btn-xs" onclick="del(<%=p.getDeploymentId() %>)"/><input type="button" class="btn btn-primary btn-xs" value="handle"/></td>
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
</div>
</body>
</html>