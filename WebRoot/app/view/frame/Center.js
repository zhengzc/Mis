Ext.define('WebApp.view.frame.Center',{  
        extend:'Ext.tab.Panel',  
        //layout:'fit',  
        //注意 加上widget.  
        alias:'widget.appCenter',  
        region:'center',  
        activeTab:0,//默认显示第一个
        plain:true,//是否显示工具栏背景条
		collapsible:false,//滚动条
		defaults:{//默认属性，添加到子组件中
			autoScroll:true,//全部滚动条
			bodyPadding: 5,//边距
			closable:true//关闭按钮
		},
        items:[  
               {  
                   title:'主页',  
                   html:'<h2>欢迎使用 </h2>'  
               }  
        ],  
        initComponent:function(){  
            this.callParent(arguments);  
        }  
    }  
)