/**
 * 创建会员
 * @author zhengzhichao
 */
Ext.define("WebApp.view.userManage.CreateVipView",{
	extend:"Ext.form.Panel",
	alias:"widget.userManage_createVipView",
	layout:"anchor",
	/*layout:{
		type:"table",
		columns:3
	},*/
	
	initComponent:function(){
		this.callParent(arguments);
	},
	
	fieldDefaults:{
		//labelWidth:70,//标签宽度
		labelAlign:"right",//标签位置
		width:300,//宽度
		msgTarget:"qtip"//提示信息显示策略
	},
	items:[{
		//分组显示  会员信息
		xtype:"fieldset",
		title:"会员信息",
		items:[{
			//文本框 姓名
			xtype:"textfield",
			name:"tradeInfo.tradeUser.userName",
			fieldLabel:"姓名",
			allowBlank:false,//不能为空
			blankText:"姓名不能为空！"
		},{
			//单选按钮组  性别
			xtype:"radiogroup",
			name:"sexGroup",
			fieldLabel:"性别",
			allowBlank:false,//不能为空
			blankText:"请选择性别",
			items:[{
				boxLabel:"男",
				name:"tradeInfo.tradeUser.sex",
				inputValue:"M"
			},{
				boxLabel:"女",
				name:"tradeInfo.tradeUser.sex",
				inputValue:"F"
			}]
		},{
			//文本框 手机号码
			xtype:"textfield",
			name:"tradeInfo.tradeUser.serialNumber",
			fieldLabel:"手机号码",
			allowBlank:false,//不能为空
			blankText:"请输入手机号码"
		},{
			//文本框  地址
			xtype:"textfield",
			name:"tradeInfo.tradeUser.address",
			fieldLabel:"地址",
			allowBlank:false,//不能为空
			blankText:"请输入地址"
		},{
			//文本框 所在学校
			xtype:"textfield",
			name:"tradeInfo.tradeUser.school",
			fieldLabel:"所在学校"
		}]
	},{
		//分组显示 会员卡信息
		xtype:"fieldset",
		title:"会员卡信息",
		items:[{
			//文本框  卡号
			xtype:"textfield",
			name:"tradeInfo.tradeUser.cardId",
			fieldLabel:"卡号",
			allowBlank:false,//不能为空
			blankText:"请输入卡号"
		},{
			//下拉框  卡类型
			xtype:"combo",
			name:"tradeInfo.tradeUser.cardType",
			fieldLabel:"卡类型",
			queryMode: 'local',
			valueField: 'cardTypeCode',
			displayField:"cardName",
			store:"userManage.CardTypeStore"
		},{//文本框  充值金额
			xtype:"textfield",
			name:"tradeInfo.tradePay.fee",
			fieldLabel:"充值金额"
		},{//字段容器  有效期
			xtype:"fieldcontainer",
			name:"valid_date",
			fieldLabel:"有效期",
			layout:"hbox",
			/*,fieldDefaults:{
				labelWidth:70
			}*/
			items:[{
				xtype:"datefield",
				name:"tradeInfo.tradeUser.cardStartDate",
				//value:new Date(),
				format:"y-m-d",
				flex:1
			},{
				xtype:"splitter"
			},{
				xtype:"datefield",
				name:"tradeInfo.tradeUser.cardEndDate",
				format:"y-m-d",
				flex:1
			}]
		}]
	}/*,{
		xtype:"button",
		text:"提交111"
	}*/],
	//按钮
	buttonAlign:"center",//按钮居中显示
	buttons:[{
		//提交按钮
		action:"submit",
		text:"提交"
	},{
		//重置按钮
		action:"reset",
		text:"重置"
	}]
	/*,tbar:[{
		xtype:"button",
		text:"submit"
	}]*/
})