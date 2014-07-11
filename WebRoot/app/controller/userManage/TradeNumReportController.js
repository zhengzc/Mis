/**
 * 订单受理量统计
 */
Ext.define("WebApp.controller.userManage.TradeNumReportController",{
	extend:"Ext.app.Controller",
	
	views:[
		"userManage.TradeNumReportView"
	],
	
	stores:[
		"userManage.TradeNumStore"
	],
	
	models:[
	],
	
	refs:[{
		ref:"tradeNumReportView",
		selector:"userManage_tradeNumReportView"
	},{
		ref:"tradeNumChart",
		selector:"userManage_tradeNumReportView chart"
	}],
	
	init:function(){
		this.control({
			"userManage_tradeNumReportView button[name=query]":{
				click:function(){//查询按钮点击事件
					var tradeNumStore = this.getStore("userManage.TradeNumStore");//获取store
					
					//获取开始时间 结束时间 输入框
					var startDateField = this.getTradeNumReportView().down("datefield[name=beginTime]");
					var endDateField = this.getTradeNumReportView().down("datefield[name=endTime]");
					
					if(startDateField.validate()&& endDateField.validate())
					{
						//发送请求
						tradeNumStore.load({
							params:{
								startDate:startDateField.getValue(),
								endDate:endDateField.getValue()
							}
						});
					}
				}
			}
		})
	}
})