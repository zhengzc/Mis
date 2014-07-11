/**
 * 充值页面控制器
 * @author:zhengzhichao
 * @description: 充值页面控制器，注意此页面与开户页面的不同哦
 */
Ext.define("WebApp.controller.userManage.ChargeFeeController",{
	extend:"Ext.app.Controller",
	
	views:[
		"userManage.ChargeFeeView"
	],
	
	stores:[
		"userManage.QryUserStore",
		"userManage.CardTypeStore"
	],
	
	models:[
		"UserModel"
	],
	
	refs:[{
		ref:"chargeFeeView",
		selector:"userManage_chargeFeeView"
	},{
		ref:"userForm",
		selector:"userManage_chargeFeeView form"
	},{
		ref:"userPanel",
		selector:"userManage_chargeFeeView gridpanel"
	}],
	
	init:function(){
		//console.log("chargeFeeController!!!");
		this.control({
			//查询按钮
			"userManage_chargeFeeView button[name=query]":{
				click:function(){//点击事件
					//查询的时候先重置form中的数据
					var form = this.getChargeFeeView().down("form");
					form.getForm().reset();
					
					var userStore = this.getStore("userManage.QryUserStore");
					//alert(this.getChargeFeeView().down("gridpanel").down("textfield[name=cardId]").getValue());
					//执行查询 改为加载第一页  
					userStore.loadPage(1,{
					//userStore.load({
						params:{
							start:0,
							limit:this.pageSize,
							cardId:this.getChargeFeeView().down("gridpanel").down("textfield[name=cardId]").getValue(),
							userName:this.getChargeFeeView().down("gridpanel").down("textfield[name=userName]").getValue(),
							serialNumber:this.getChargeFeeView().down("gridpanel").down("textfield[name=serialNumber]").getValue()
						}
					});
				}
			},
			
			//表格
			"userManage_chargeFeeView gridpanel":{
				itemclick:function(view,record){//单击事件
					//找到form
					var form = this.getChargeFeeView().down("form");
					//加载数据
					form.loadRecord(record);
				}
			},
			
			//提交按钮
			"userManage_chargeFeeView form button[action=submit]":{
				click:function(){//单击事件  这个提交不采用form表单的提交方式，采用ajax的提交方式！
					var ajaxConfig = {
						scope:this,//定义对应的函数的作用域，如果params是调用函数生成的，那么scope就代表此函数的作用域，默认是windows窗口
						url:root_path+"/web/chargeFeeAction.action",
						params:{
							"tradeInfo.userId":this.getUserForm().getForm().findField("userId").getValue(),
							"tradeInfo.cardId":this.getUserForm().getForm().findField("cardId").getValue(),
							"tradeInfo.tradePay.fee":this.getUserForm().getForm().findField("chargeFee").getValue(),
							"tradeInfo.tradePay.oldFee":this.getUserForm().getForm().findField("chargeFee").getValue()
						},
						method:"POST",
						
						//请求正常时候调用的方法
						success:function(response,opts){
							var repJson = Ext.JSON.decode(response.responseText);
							//alert(repJson.success+":"+repJson.msg);
							
							if(repJson.success){//请求成功
								//弹出重置提示
								Ext.MessageBox.confirm("充值成功","充值成功，是否重置页面？",function(id){
									if(id=="yes"){
										this.initPage();
									}
								},this)
							}else{//请求失败
								//this.initPage();
								//弹出提示
								Ext.MessageBox.show({
									title:"充值失败！",
									msg:repJson.msg,
									icon:Ext.MessageBox.ERROR,
									buttons:Ext.MessageBox.OK,
									scope:this
								});
							}
						},
						
						//请求失败的时候
						failure:function(response,opts){
							//弹出提示
							Ext.MessageBox.show({
								title:"服务器无响应,请联系管理员！",
								msg:response.responseText,
								icon:Ext.MessageBox.ERROR,
								buttons:Ext.MessageBox.OK,
								scope:this
							});
						}
					}
					
					//发送ajax请求！
					Ext.Ajax.request(ajaxConfig);
				}
			},
			
			//重置按钮
			"userManage_chargeFeeView form button[action=reset]":{
				"click":function(){
					this.initPage();
				}
			}
		})
	},
		
	//重置页面使用
	initPage:function(){
		//清空搜索框
		this.getUserPanel().down("textfield[name=cardId]").setValue();
		this.getUserPanel().down("textfield[name=userName]").setValue();
		this.getUserPanel().down("textfield[name=serialNumber]").setValue();
		//清空表格
		this.getStore("userManage.QryUserStore").removeAll(false);//removeAll 方法设置为true表示只清除store中的数据，不触动store绑定的表现层数据清空方法！
		//清空表单
		this.getUserForm().getForm().reset();
	}
})