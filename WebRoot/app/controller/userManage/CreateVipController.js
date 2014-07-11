Ext.define("WebApp.controller.userManage.CreateVipController",{
	extend:"Ext.app.Controller",
	
	views:[
		"userManage.CreateVipView"
	],
	
	stores:[
		"userManage.CardTypeStore"
	],
	
	refs:[{
		ref:"createVipForm",
		selector:"userManage_createVipView"
	}],
	
	init:function(){
		//console.log("createVipController!!!");
		//此方法是在页面初始化之前完成的，所以无法这么写
		//this.getCreateVipForm().down("fieldcontainer[name=valid_date]").hide();//显示有效期
		this.control({
			//表单面板
			"userManage_createVipView":{
				//展示完成后事件  此方法每次显示都会调用，不应该用于隐藏有效期，因为切标签的时候也会触发！
				/*show:function(comp){
					comp.down("fieldcontainer[name=valid_date]").hide();//隐藏有效期
				}*/
				
				//此事件可以作为组件初始化方法来进行调用
				//Fires after the component markup is rendered.
				render:function(comp){
					comp.down("fieldcontainer[name=valid_date]").hide();//隐藏有效期
				}
			},
			//提交按钮
			"userManage_createVipView button[action=submit]":{
				//点击事件
				click:function(){
					this.getCreateVipForm().submit({
						url:root_path+"/web/createVipAction.action",
						method:"POST",
						scope:this,
						success:function(form,action){//创建成功
							//弹出重置提示
							Ext.MessageBox.confirm("创建成功","会员创建成功，是否重置页面？",function(id){
								if(id=="yes"){
									this.getCreateVipForm().getForm().reset();
								}
							},this)
						},
						failure:function(form,action){//创建失败
							//弹出提示
							Ext.MessageBox.show({
								title:"会员创建失败",
								msg:action.result.msg,
								icon:Ext.MessageBox.ERROR,
								buttons:Ext.MessageBox.OK
							});
						}
					});
				}
			},
			
			//重置按钮
			"userManage_createVipView button[action=reset]":{
				//点击事件
				click:function(){
					this.getCreateVipForm().getForm().reset();
				}
			},
			
			//卡类型复选框
			"userManage_createVipView textfield[name=tradeInfo.tradeUser.cardType]":{
				//当值发生变化的时候
				change:function(field,newValue,oldValue,eOpts){
					//获取当前复选框绑定的对象
					if(newValue != null){
						var cardTypeObj = field.findRecordByValue(newValue).getData();
						if(cardTypeObj.cardTypeCode == "0"){//普通卡
							//尽量少的使用id获取组件!使用这种方式将会是一个很好的选择
							this.getCreateVipForm().down("fieldcontainer[name=valid_date]").hide();//隐藏有效期
							this.getCreateVipForm().getForm().findField("tradeInfo.tradePay.fee").setValue(0);//设置金额
						}else{//非普通卡
							//尽量少的使用id获取组件!使用这种方式将会是一个很好的选择
							this.getCreateVipForm().down("fieldcontainer[name=valid_date]").show();//显示有效期
							this.getCreateVipForm().getForm().findField("tradeInfo.tradePay.fee").setValue(cardTypeObj.fee);//设置金额
							
							//设置有效期开始时间
							var startDate = this.getCreateVipForm().getForm().findField("tradeInfo.tradeUser.cardStartDate");
							startDate.setValue(new Date());
							var num_month;
							//不同卡类型偏移量
							if(cardTypeObj.cardTypeCode=="1"){
								num_month=1;
							}else if(cardTypeObj.cardTypeCode=="2"){
								num_month=3;
							}else{
								num_month=12;
							}
							//设置有效期结束时间
							var end_date = Ext.Date.add(startDate.getValue(),Ext.Date.MONTH,num_month)
							this.getCreateVipForm().getForm().findField("tradeInfo.tradeUser.cardEndDate").setValue(end_date);//设置有效期结束时间
						}
					}else{
						this.getCreateVipForm().down("fieldcontainer[name=valid_date]").hide();//隐藏有效期
					}
				}
			}
		});
	}
})