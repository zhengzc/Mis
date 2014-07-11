<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML>
<html>
  <head>
    <title>欢迎光临</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<%@ include file="/page/common/public.jsp" %>
		
	<script type="text/javascript">
		var userName = "${userName}";//用户名
		var userRoleName = "${userRoleName}";//用户角色名称
	</script>
	
	<%--	<script type="text/javascript" src="<%=path%>/MainPage.js"></script>--%>
	<script type="text/javascript" src="<%=path%>/appFrame.js"></script>
  </head>
 	<body>
 		
	</body>
</html>
