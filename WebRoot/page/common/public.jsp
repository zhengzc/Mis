<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String root = request.getContextPath();
%>

<%--		样式--%>
<link rel="stylesheet" type="text/css" href="<%=root%>/ExtJs4.1.1/resources/css/ext-all.css" />
<link rel="stylesheet" type="text/css" href="<%=root%>/ExtJs4.1.1/resources/css/icon.css" />
<%--    <link rel="stylesheet" type="text/css" href="<%=root%>/page/desktop/css/desktop.css" />--%>
<%--    extjs核心 这里我们不使用动态加载，直接全部加载--%>
<%--<script type="text/javascript" src="<%=root%>/ExtJs4.1.1/ext-all.js"></script>--%>
<%--哈哈 使用官方的cdn服务  让外网加载更省力！--%>
<script type="text/javascript" charset="utf-8" src="http://cdn.sencha.io/ext-4.1.1-gpl/ext-all.js"></script>

<%--<script type="text/javascript" src="<%=root%>/ExtJs4.1.1/ext-all-debug.js"></script>--%>
<%--<script type="text/javascript" src="<%=root%>/ExtJs4.1.1/ext.js"></script>--%>
<%--<script type="text/javascript" src="<%=root%>/ExtJs4.1.1/ext-debug.js"></script>--%>
<script type="text/javascript" src="<%=root%>/ExtJs4.1.1/locale/ext-lang-zh_CN.js"></script>
<script type="text/javascript">
	var root_path = '<%=root%>';
</script>
	

<%--例子--%>
<%--<script type="text/javascript" src="<%=root%>/page/desktop/modules/WallpaperModel.js"></script> --%>
<%--<script type="text/javascript" src="<%=root%>/page/desktop/modules/VideoWindow.js"></script> --%>
<%--<script type="text/javascript" src="<%=root%>/page/desktop/modules/BogusModule.js"></script> --%>
<%--<script type="text/javascript" src="<%=root%>/page/desktop/modules/BogusMenuModule.js"></script> --%>
<%--<script type="text/javascript" src="<%=root%>/page/desktop/modules/TabWindow.js"></script> --%>
<%--<script type="text/javascript" src="<%=root%>/page/desktop/modules/GridWindow.js"></script> --%>
<%--<script type="text/javascript" src="<%=root%>/page/desktop/modules/AccordionWindow.js"></script> --%>
<%--<script type="text/javascript" src="<%=root%>/page/desktop/modules/SystemStatus.js"></script> --%>
<%--<script type="text/javascript" src="<%=root%>/page/desktop/modules/Notepad.js"></script>--%>
<%--app 添加功能！--%>
<%--<script type="text/javascript" src="<%=root%>/page/app/test/TestMenuModule.js"></script>--%>
<%--<script type="text/javascript" src="<%=root%>/page/app/test/TestMenus.js"></script>--%>
<%--<script type="text/javascript" src="<%=root%>/page/app/test/mvc/model/User.js"></script>--%>
<%--<script type="text/javascript" src="<%=root%>/page/app/test/mvc/store/Users.js"></script>--%>
<%--<script type="text/javascript" src="<%=root%>/page/app/test/mvc/MyApp.js"></script>--%>
