/**
 * 业务受理量统计报表
 */
Ext.define("WebApp.view.userManage.TradeNumReportView",{
	extend:"Ext.panel.Panel",
	alias:"widget.userManage_tradeNumReportView",
	
	//组件初始化
	initComponent:function(){
		this.items = [{
			xtype:"panel",
			//height:500,
			
			//top 工具栏
			tbar:["开始时间",{
				xtype:"datefield",
				//fieldLabel:"开始时间",
				allowBlank:false,//不能为空
				name:"beginTime"
			},"结束时间",{
				xtype:"datefield",
				//fieldLabel:"结束时间",
				allowBlank:false,//不能为空
				name:"endTime"
			},{
				xtype:"button",
				name:"query",
				text:"查询"
			}],
			
			items:[{
				xtype:"chart",
				animate:true,//启用动画效果
				shadow:true,//阴影
				height:400,
				width:600,
				
				store:"userManage.TradeNumStore",//绑定store
				
				axes:[{
					type: 'Numeric',//数值轴
		            position: 'left',
		            fields: ["num"],
		            title: '受理量',
		            grid: true,//控制是否显示分割线
		            minimum: 0
				},{
		            type: 'Category',//分类轴
		            position: 'bottom',
		            grid: true,//控制是否显示分割线
		            fields: ["acceptDate"],
		            title: '时间'
				}],
				
				series:[{
					type: 'line',//折线图
					title:"受理量统计",
		            highlight: {//鼠标经过的时候样式
		                size: 7,
		                radius: 7
		            },
		            axis: 'left',//绑定的坐标位置
		            xField: 'acceptDate',
		            yField: 'num',
		            markerConfig: {//配置折线图的节点风格
		                type: 'cross',//十字形
		                size: 4,//大小
		                radius: 4,//风格
		                'stroke-width': 0
		            }
				}]
			}]
		}];
		
		this.callParent(arguments);
	}
})