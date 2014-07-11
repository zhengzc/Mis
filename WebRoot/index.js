Ext.require([
	"Ext.form.Panel"
	,"Ext.tip.QuickTip"
]);

Ext.onReady(function(){
	Ext.QuickTips.init();
	var loginForm = Ext.create("Ext.form.Panel",{
		xtype:"form",
		title:"请登录",
		renderTo:"login",
		defaults:{
			padding:"2 0 0 0",
			labelAlign:"right"
		},
		items:[{
			xtype:"textfield",
			name:"user_id",
			fieldLabel:"用户名"
		},{
			xtype:"textfield",
			name:"user_password",
			inputType:"password",
			fieldLabel:"密码"
		}],
		buttons:[{
			text:"登陆",
			handler:login
		},{
			text:"重置",
			handler:function(){
				loginForm.getForm().reset();
			}
		}]
	});
	
	//登陆函数
	function login(){
		loginForm.getForm().submit({
			clientValidation:true,
			url:root_path+"/web/loginAction_login.action",
			method:"POST",
			success:function(form,action){
				//console.log("登陆成功");
				window.location.href = root_path+"/web/index.action"
			},
			failure:function(form,action){
				Ext.MessageBox.alert("错误",action.result.errors);
			}
		})
	}
})