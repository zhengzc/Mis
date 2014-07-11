/**
 * 查询用户信息
 * author：zhengzhichao
 */
Ext.define("WebApp.store.userManage.QryUserStore",{
	extend:"Ext.data.Store",
	
	model:"WebApp.model.UserModel",
	pageSize:6,
	
	proxy:{
		type:"ajax",
		url:root_path+"/web/chargeFeeAction_queryUserInfo.action",
	    actionMethods: {  
            read: 'POST'  
        },  
		reader:{
			type:"json",
			root:"rows",
			totalProperty: 'total'//总页数
		}
	}
})