<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html>
	<head>
		<base href="<%=basePath%>">

		<title>请登录</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

		<%--		样式--%>
		<%--<link rel="stylesheet" type="text/css" href="<%basePath%>bootstrap2.3.2/css/bootstrap.min.css" />--%>
		<link rel="stylesheet" type="text/css" href="<%=basePath%>bootstrap2.3.2/css/bootstrap.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>bootstrap2.3.2/css/font-awesome.min.css">
		<%--响应式css--%>
		<%--	<meta name="viewport" content="width=device-width, initial-scale=1.0">--%>
		<%--	<link href="<%=basePath%>bootstrap2.3.2/css/bootstrap-responsive.css" rel="stylesheet">--%>
		<%--js--%>
		<script type="text/javascript" src="<%=basePath%>bootstrap2.3.2/js/jquery-1.10.0.min.js"></script>
		<%--<script type="text/javascript" src="<%=basePath%>bootstrap2.3.2/js/jquery-1.10.0.js"></script>--%>
		<%--<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>--%>
		<script type="text/javascript" src="<%=basePath%>bootstrap2.3.2/js/bootstrap.min.js"></script>
		<%--<script type="text/javascript" src="<%=basePath%>bootstrap2.3.2/js/bootstrap.js"></script>--%>
		<%--	<script type="text/javascript" src="<%=basePath%>bootstrap2.3.2/js/holder.js"></script>--%>


		<style type="text/css">
			.my_login{
				margin-top: 50px;
			}
		</style>
		
		<script type="text/javascript">
		$(document).ready(function(){
			
		})
		
		function login(){
			var rootPath = '<%=basePath%>';
			var userName = $("#userName").val();
			var password = $("#password").val();
			//发送请求 
			$.post(rootPath+"web/loginAction_login.action"
					,{"user_id":userName,"user_password":password}
					,function(data){
						if(data.success){
							window.location.href = rootPath+"web/index.action"
						}else{
							alert("登陆失败:"+data.errors);
						}
					}
					,"json");			
		}
		</script>
	</head>

	<body>
<%--		顶部工具栏--%>
		<div id="top_navbar" class="navbar navbar-fixed-top navbar-inverse">
			<div class="navbar-inner">
				<div class="container">
					<a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
						<span class="icon-bar"></span> 
						<span class="icon-bar"></span> <span class="icon-bar"></span> 
					</a>
					<a class="brand" href="#">MIS</a>
					<div class="nav-collapse">
						
					</div>
					<!-- /.nav-collapse -->
				</div>
			</div>
			<!-- /navbar-inner -->
		</div>
		<!-- /navbar -->
		
		<div class="container">
			<div class="row">
				<div class="span3">
				</div>
				
				<%--		登陆框--%>
				<div class="span6">
					<form class="well my_login">
						<fieldset>
							<legend>请登陆</legend>
<%--						    <div class="control-group">--%>
						     	<label class="control-label" for="userName">用户名：</label>
<%--					            <div class="controls">--%>
					            	<input id="userName" type="text" class="input-xlarge" placeholder="请输入用户名" />
<%--					            </div>--%>
<%--				            </div>--%>
				            
<%--				            <div class="control-group">--%>
						     	<label class="control-label" for="password">密码：</label>
<%--					            <div class="controls">--%>
					            	<input id="password" type="password" class="input-xlarge" placeholder="请输入用户名" />
<%--					            </div>--%>
<%--				            </div>--%>
          
							<div class="form-actions">
							    <button type="button" class="btn btn-primary" onclick="login()">提交</button>
							    <button type="reset" class="btn">重置</button>
						    </div>
					    </fieldset>
				    </form>
				</div>
				
				<div class="span3">
				</div>
			</div>
		</div>
	</body>
</html>
