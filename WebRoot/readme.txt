1.注意mysql中返回值是大小写敏感的
2.注意每个请求的权限配置，类名，方法名
3.统一命名

---extjs4----
1.mvc模式能够允许加载的时候只加载一次，其余动态加载，大大提高了响应效率
2.view中尽量少的使用id的方式，因为所有页面均为同一个页面，可能会出现id重复的情况，应使用这种方式替换
	如果必须使用ID属性，建议ID值用父组件ID+子组件ID的形式来指定子组件的ID；

	建议使用
	    FormPanel.getForm().findField('id/name');
	    或者
	    Ext.get('id/name'); 
	    来替代
	    Ext.getCmp('id')获取组件。
	 或者定义组件为变量，然后在FormPanel或者GridPanel中引入。