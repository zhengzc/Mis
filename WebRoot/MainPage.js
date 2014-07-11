Ext.require([
	//'Ext.data.*'
	//,'Ext.tree.*'
	'Ext.data.TreeStore'
	,'Ext.tree.Panel'
	,'Ext.tip.QuickTip'
	,'Ext.window.MessageBox'
	,'Ext.toolbar.Toolbar'
]);

//菜单模型，添加 url text属性
Ext.define("treeModel",{
	extend:"Ext.data.Model",
	fields:['url','text']
})

Ext.onReady(function(){
	Ext.QuickTips.init();
	
	//顶部工具栏
	Ext.Ajax.request({
		url:root_path+"",
		//params:{},
		//jsonData:getJson(),
		success: function(response){
			
		}
	});
	
	var topBar = Ext.create("Ext.toolbar.Toolbar",{
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
	
	//左侧菜单
	var leftMenuStore = Ext.create('Ext.data.TreeStore', {
		model:"treeModel",
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
	});
	
	var leftMenu = Ext.create("Ext.tree.Panel",{
		//左侧树菜单
		region:"west",//左侧
		title:"菜单",//显示
		width:200,
		store:leftMenuStore,//使用已经加载的Ext.data.TreeStore
		rootVisible:false,//不显示根节点
		listeners:{
			itemclick:function(view,record){//定义树节点的双击事件
				var node = record.getData();//获取节点值
				//Ext.MessageBox.alert("11",node.text);
				if(node.leaf == true){//如果节点为叶子节点
					var content = Ext.getCmp("content");//获取页面主题框架组件
					//var test = content.getComponent("tab_"+node.id);
					if(Ext.isEmpty(content.getComponent("tab_"+node.id))){//如果主体框架中不存在编号为 node.id的tab页
						var tab_id = content.add({//为主体框架添加tab页
							id:"tab_"+node.id,
							iconCls:node.iconCls,
							title:node.text,
							//html:"<iframe scrolling='auto' frameborder='0' width='100%' height='100%' src='"+root_path+node.url+"'></iframe>"
							loader:{//加载器
			               		url: root_path+node.url,//加载页面地址
			               		//添加参数
			               		params:{
			               			tabMainId:"subZzcTab_"+node.id
			               		},
			                    contentType: 'html',//类型
			               		//autoLoad:true,
			                    loadMask: "正在加载....",//加载的时候弹出屏蔽层
			                    target:"tab_"+node.id,
			                    scripts:true,
			                    scope:this
			               	},
			               	listeners: {//定义tab页的监听器
			                    /*activate: function(tab) {//激活时候动作
			                        tab.loader.load();//激活时候加载
			                    }*/
			               		close:function(panel){//关闭的时候的动作
			               			panel.destroy();
			               		}
			               		/*destroy:function(){
			               			alert(1111);
			               		}*/
			                }
						}).show();
						
						//加载页面
						tab_id.loader.load();
					}else{//如果主体框架中node.id这个tab也已经存在，则激活此页面
						content.setActiveTab("tab_"+node.id);
					}
				}
			}
		}
	});
	
	//主显示区
	var mainContent = Ext.create("Ext.tab.Panel",{
		region:"center",//中间
		id:"content",
		//frame:true,
		plain:true,//是否显示工具栏背景条
		collapsible:false,//滚动条
		activeTab:0,//默认显示第一个
		defaults:{//默认属性，添加到子组件中
			autoScroll:true,//全部滚动条
			bodyPadding: 5,//边距
			closable:true//关闭按钮
		},
		items:[{
			id:"tab_0",
			title:"首页",
			closable:false,//关闭按钮
			iconCls:"Applicationhome",
			//加载
			//html:"<iframe scrolling='auto' frameborder='0' width='100%' height='100%' src='"+root_path+"/test.jsp'></iframe>"
			//使用这种方式加载页面，无需重新加载extjs文件，非常方便！  另外如果子页面重新引入，会导致奇怪的错误！
			loader: {
                url: root_path+'/test.jsp',//地址
                contentType: 'html',//类型
                loadMask: "正在加载....",//加载的时候弹出屏蔽层
                target:"tab_0",
                autoLoad:true,//自动加载
                scripts:true,//允许运行脚本
                scope:this
            }
            
		}/*,{
			id:"tab_test",
            title: '测试1',
            //html:"<iframe scrolling='auto' frameborder='0' width='100%' height='100%' src='"+root_path+"/test.jsp'></iframe>"
           	loader:{
           		url: root_path+'/test.jsp',
                //contentType: 'html',
                loadMask: "正在加载....",//加载的时候弹出屏蔽层      需要target参数才行
                //target:"content-body",//目前这个参数不好用
                scope:this
           	},
           	listeners: {
                activate: function(tab) {//激活时候动作
                    tab.loader.load();//激活时候加载
                }
            }
        }*/]
	})
	
	//构建页面
	Ext.create("Ext.container.Viewport",{
		layout:"border",//布局模式
		renderTo:Ext.getBody(),//显示位置
		defaults:{//设置默认属性，这些属性都会copy到其下面的子组件中
			frame:false,//框架面板  不显示
			collapsible:true//是否有显示隐藏按钮
		},
		items:[mainContent,leftMenu,topBar]
	});
	
	
})