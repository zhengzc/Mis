<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML>
<html>
  <head>
    <title>asiainfo-linkage</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
		
	<%--		样式--%>
 	<link rel="stylesheet" type="text/css" href="<%=path%>/ExtJs4.1.1/resources/css/ext-all.css" />
<%--    <link rel="stylesheet" type="text/css" href="<%=path%>/page/desktop/css/desktop.css" />--%>
<%--    extjs核心 这里我们不使用动态加载，直接全部加载--%>
<%--    <script type="text/javascript" src="<%=path%>/ExtJs4.1.1/ext-all-debug.js"></script>--%>
<%--    <script type="text/javascript" src="<%=path%>/ExtJs4.1.1/ext.js"></script>--%>
    <script type="text/javascript" src="<%=path%>/ExtJs4.1.1/ext-debug.js"></script>
    <script type="text/javascript" src="<%=path%>/ExtJs4.1.1/locale/ext-lang-zh_CN.js"></script>
	
	<script type="text/javascript">

	Ext.onReady(function(){
		var store = Ext.create('Ext.data.TreeStore',{  
		    proxy:{  
		        type:'ajax',  
		        url:'page/web/testAction_testTreeStr.action',  
		        reader:{  
		            type:'json',  
		            root:'treeList'  
		        }  
		    },  
		    root:{  
		        text:'根节点',  
		        expanded:true  
		    }  
		});
		
	})
	
	</script>
  </head>
 	<body>
	</body>
</html>
