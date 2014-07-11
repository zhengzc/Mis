
Ext.define('WebApp.view.frame.Top',{  
    extend:'Ext.toolbar.Toolbar',  
    alias:'widget.appTop',//注意不要忘了写“widget”否则会找不到并且报错<a href="http://blog.csdn.net/lc448986375/article/details/8014401" style="color: rgb(0, 0, 0); text-decoration: none; font-family: 'Microsoft YaHei'; line-height: 30px; "><span style="font-size:10px;">Uncaught TypeError: Cannot call method 'substring' of undefined</span></a>
    region:"north",//顶部
	border:false,//取消边框
	//split:true,//可移动分割框架
	height:26,
	items:[{
		xtype : 'button',
        text : "中国联通bss系统",
        iconCls:"Applicationhome"
	},"-",{
		xtype : 'button',
        text : userRoleName,
        iconCls:"Group"
	},"-",{
		xtype : 'button',
        text : userName,
        iconCls:"User"
	},"->","-",{
	  	xtype : "button",
        minWidth : 80,
        text : "注销",
        iconCls:"Cancel",
        handler : function(btn, e) {
        	window.location.href = root_path+"/web/logout.action";
        }
    }]
});