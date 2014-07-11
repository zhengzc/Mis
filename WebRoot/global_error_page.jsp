<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script src="<%=path%>/Script/common/jquery-1.7.2.min.js" language="javascript" ></script>
		
	<script type="text/javascript">
<%--	$(document).ready(function() {  --%>
<%--        function jump(count) {  --%>
<%--            window.setTimeout(function(){  --%>
<%--                count--;  --%>
<%--                if(count > 0) {  --%>
<%--                    $('#num').attr('innerHTML', count);  --%>
<%--                    jump(count);  --%>
<%--                } else {  --%>
<%--                    location.href="web/index.action";  --%>
<%--                }  --%>
<%--            }, 1000);  --%>
<%--        }  --%>
<%--        jump(3);  --%>
<%--    });  --%>
	</script>
  </head>
  
  <body>
<%--    <span style="color:red">请先登录才能使用此功能，谢谢</span><br/>页面将在3秒后跳转到主页...<br/>还剩<span id="num">3</span>秒--%>
	错误！
  </body>
</html>
