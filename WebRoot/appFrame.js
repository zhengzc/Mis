//直接一次性加载extjs-all的时候，默认情况下是不开启动态加载的，所以需要手动开启动态加载功能
//而且此段必须放在动态加载的js之前 比如放在这里
Ext.onReady(function(){   
	Ext.Loader.setConfig({enabled:true});  
});  

Ext.application({
    requires: ['Ext.container.Viewport'], 
    //定义应用命名空间 
    name: 'WebApp',
    //定义文件夹
    //使用struts2的时候 这个路径要注意，会根据当前路径请求js。struts配置文件的namespace="/web"会导致找不到对应的js文件
    appFolder: '../app',
	//控制器
    controllers: [
        'frame.FrameController'
    	//,'testController.Test_1'//测试
    	//,"userManage.CreateVipController"//创建会员
    ],

    //页面完全加载后将运行此方法  
    launch: function() {
    	
    	Ext.Loader.setConfig({enabled:true});  
    	
    	/**动态添加控制器，能够增加主页面启动速度，tab页面对应的组件和控制器将在点击菜单的时候动态加载并初始化！
    	 * 
    	 *添加add事件，当添加控制器的时候调用此方法！
    	 *注意，此方法配合主控制（frame.FrameController）中的点击事件，当点击菜单的时候，动态添加子控制器！
    	 *子控制器添加的时候，会自动触发此处的add事件，然后调用子控制器的初始化方法！
    	 *
    	 *另外需要注意的是，上面所述的点击菜单的时候动态添加子控制器，这就需要点击菜单的时候能够知道当前需要动态添加的子控制器
    	 *名称是什么。此问题目前有两种解决方案：
    	 *1.定义命名规则，目前td_m_menu表中只有一个url（path）字段用来识别tab页加载的组件，只要将控制名字与组件名字统一
    	 *即可达到目的。
    	 *2.为td_m_menu表添加一个字段，用来识别对应的组件（path）应该对应加载哪个控制器，此方法相对比较可行，
    	 *@anchor zhengzhichao 
    	 *
    	 *采用第二种方法 添加控制器字段
    	 */
    	this.controllers.addListener('add',this.newControllerAdded,this);
    	
    	//页面布局
        Ext.create('Ext.container.Viewport', {
            layout:'border',//布局模式
            items: [{  
                xtype:'appTop'//这里可以写对应view的alias的属性  
            },{  
                xtype:'appLeft'  
            },{  
                xtype:'appCenter'  
            },{
            	xtype:'appBottom'
            }]  
        });
    },
    
    newControllerAdded:function(idx, ctrlr, token){
		ctrlr.init();
	}
});