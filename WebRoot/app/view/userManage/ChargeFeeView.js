/**
 * 充值查询页面
 * @author zhengzhichao
 */
Ext.define("WebApp.view.userManage.ChargeFeeView",{
	extend:"Ext.panel.Panel",
	alias:"widget.userManage_chargeFeeView",
	
	initComponent:function(){
		//在这个方法里面初始化组件
		this.items = [{
			xtype:"gridpanel",//表格组件
			//multiSelect:false,//不支持多选
			//simpleSelect:false,//简单模式启动不需要按ctrl即可进行多选
			enableKeyNav:true,//支持键盘导航
			height:200,
			store:"userManage.QryUserStore",//引用的store
			
			tbar:["卡号：",{
				xtype:"textfield",
				name:"cardId"
			},"姓名：",{
				xtype:"textfield",
				name:"userName"
			},"电话：",{
				xtype:"textfield",
				name:"serialNumber"
			},{
				xtype:"button",
				name:"query",
				text:"查询"
			}],
			
			//列
			columns:[
				{text:"卡号",dataIndex:"cardId"},
				{text:"姓名",dataIndex:"userName"},
				{text:"电话",dataIndex:"serialNumber"}
			],
			
			bbar:[{
				xtype:"pagingtoolbar",
				displayInfo:true,
				store:"userManage.QryUserStore"
				
			}]
		},{
			xtype:"form",
			/*layout:{
				type:"table",
				columns:2
			},*/
			
			bodyPadding:"5",
			//margin:"5 0 0 0",//外边距
			//padding:"5 0 0 0",//设置一下内边距
			
			fieldDefaults:{
				//labelWidth:70,//标签宽度
				labelAlign:"right",//标签位置
				readOnly:true,//都设置为只读
				//readOnlyCls:"x-item-disabled",
				//disabled:true,//不提交
				//width:300,//宽度
				//padding:"5 0 0 0",
				msgTarget:"qtip"//提示信息显示策略
				
			},
			buttonAlign:"center",//按钮居中显示
			buttons:[{
				//提交按钮
				action:"submit",
				text:"提交"
			},{
				//重置按钮
				action:"reset",
				text:"重置"
			}],
			
			items:[{
				//分组显示  会员信息
				xtype:"fieldset",
				title:"会员信息",
				layout:{
					type:"table",
					columns:2
				},
				items:[{
					xtype:"textfield",
					name:"cardId",
					fieldLabel:"卡号"
				},{
					xtype:"textfield",
					name:"userId",
					fieldLabel:"用户名"
				},{
					xtype:"textfield",
					name:"userName",
					fieldLabel:"姓名"
				},{
					xtype:"textfield",
					name:"serialNumber",
					fieldLabel:"电话"
				},{
					xtype:"textfield",
					name:"school",
					fieldLabel:"学校"
				},{
					xtype:"textfield",
					name:"sumFee",
					fieldLabel:"累计消费"
				},{
					xtype:"textfield",
					name:"currentFee",
					fieldLabel:"当前余额"
				},{
					//下拉框  卡类型
					xtype:"combo",
					name:"cardType",
					fieldLabel:"卡类型",
					queryMode: 'local',
					valueField: 'cardTypeCode',
					displayField:"cardName",
					store:"userManage.CardTypeStore"
				},{
					xtype:"datefield",
					name:"cardStartDate",
					fieldLabel:"开始时间",
					//value:new Date(),
					format:"Y-m-d H:i:s"
				},{
					xtype:"datefield",
					name:"cardEndDate",
					fieldLabel:"结束时间",
					format:"Y-m-d H:i:s"
				}]
			},{
				xtype:"fieldset",
				title:"充值信息",
				items:[{
					xtype:"numberfield",
					name:"chargeFee",
					allowDecimals:false,//不能有小数
					fieldLabel:"充值金额",
					colspan:2,//设置跨列
					readOnly:false
				}]
			}]
		}];
		
		this.callParent(arguments);
	}
})