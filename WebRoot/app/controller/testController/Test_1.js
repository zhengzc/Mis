Ext.define("WebApp.controller.testController.Test_1",{
	extend:"Ext.app.Controller",
	//视图
	views:[
		'testView.Test_1'
	],
	
	init:function(){
		console.log("test1!!!!");//日志
		this.control({
			"test_1 button[action=test_1_submit]":{//组件 test_1 中的button
				click:function(){
					alert("test_1 button click!");
				}
			}
		});
	}
})