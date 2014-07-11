/**
 * 订单受理量统计，折线图形式统计！
 */
Ext.define("WebApp.store.userManage.TradeNumStore",{
	extend:"Ext.data.Store",
	fields:["acceptDate","num"],
	
	proxy:{
		type:"ajax",
		url:root_path+"/web/tradeNumReportAction_queryTradeNum.action",
		actionMethods: {  
            read: 'POST'  
        },  
		reader:{
			type:"json",
			root:"data"
		}
	}
	/*data:[
		{"acceptDate":"201301","num":10},
		{"acceptDate":"201302","num":15},
		{"acceptDate":"201303","num":40},
		{"acceptDate":"201304","num":30},
		{"acceptDate":"201305","num":21},
		{"acceptDate":"201306","num":8}
	]*/
})