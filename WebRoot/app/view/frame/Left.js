Ext.define('WebApp.view.frame.Left',{  
	//左侧树状面板
    extend:'Ext.tree.Panel',  
    title:'菜单',  
    alias:'widget.appLeft',//别名
    //是否可以折叠  
    //collapsible: true,  
    //是否可以通过拖动改变宽度  
    //split:true,  
    rootVisible:false,//不显示根节点
    width:200,  
    region:'west',//左侧
    store:Ext.create('Ext.data.TreeStore', {
		fields:['url','text','controller'],//添加属性
		nodeParam:"menuId",//默认的请求参数名称修改为  menuId，如果不设置此参数，默认为 node
	    root:{
	    	expanded: true,//根节点默认展开   展开的节点会自动发送请求
	    	text:"根节点",//显示
	    	id:"-1"//对应的值
		},
		//代理
	    proxy:{
	    	type:"ajax",
	    	url:root_path+"/web/mainPageAction_leftTreeMenu.action",
	    	//用来解析服务器返回的数据
	        reader: {
	            type: 'json',
	            root: 'leftMenu'
	        }
	    }
	})
});