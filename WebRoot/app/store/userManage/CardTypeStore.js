/**
 * 获取卡类型
 * author：zhengzhichao
 */
Ext.define("WebApp.store.userManage.CardTypeStore",{
	extend:"Ext.data.Store",
	fields:["cardName","cardTypeCode","fee"],
	autoLoad:true,
	proxy:{
		type:"ajax",
		url:root_path+"/web/createVipAction_queryCardType.action",
		reader:{
			type:"json"
		}
	}
})