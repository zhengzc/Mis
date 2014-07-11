//主体框架控制器
Ext.define('WebApp.controller.frame.FrameController',{//定义类  
    extend:'Ext.app.Controller',//一定要继承Controller  
    //添加views，让控制器找到  
    views:[  
        'frame.Left',  
        'frame.Center',  
        'frame.Top',
        "frame.Bottom"        
        //,'testView.Test_1'
    ],  
    //查找到组件
	refs : [{
	    ref : 'tab',//会自动添加 getTab方法 此方法返回下面选择器获取到的组件
	    selector : 'appCenter'//找到center  根据组件名称
	}],
	//界面渲染完成后执行此方法
	init : function () {
		//console.log("FrameController!!");
        var instance = this;
        this.control({
            //使用类似选择器的方式，获取左侧树组件，这里使用的是组件的名字
            'appLeft' : {
                itemclick : function (view, record, item, index, e, opts) {
                    //获取当前点击的节点
                    var treeNode = record.getData();
                    
                    if(treeNode.leaf  && treeNode.controller!=""){
	                    /**
	                     * 使用此方式来完成动态添加控制器，当点击菜单的时候，才加载对应的控制器
	                     */
	                	this.getController(treeNode.controller);
	                	
	                	//获取点击的树节点相同的tab标签
	                    //通过ref查找到并且进行操作
	                    var tab = this.getTab().getComponent("tab_"+treeNode.id);
	                    if (!tab){//如果此tab页面不存在  添加tab页面
	                    	//alert(1);
	                    	this.getTab().add({
	                    		id:"tab_"+treeNode.id,//对应的id
								iconCls:treeNode.iconCls,//图标
								//title
								title:treeNode.text,
								//这个比较重要，url这时候表示要加载的组件名称！
	                    		xtype:treeNode.url,
	                    		//自动销毁
	                            autoDestroy : true,
	                            //关闭时候执行
	                            closeAction : 'destory'
	                    	}).show();
	                    }else{//此tab页面已经存在，则直接激活此页面
	                    	this.getTab().setActiveTab("tab_"+treeNode.id) //Active
	                    }
                    }
                }
            }
        });
    }
});