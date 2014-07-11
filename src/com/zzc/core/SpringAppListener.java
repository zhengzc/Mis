package com.zzc.core;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
/**
 * 实现ApplicationListener，此方法将会在spring容器实例化完毕之后自动调用
 * 需要在spring启动后初始化的事情可以在这里做！
 * @author zhengzhichao
 *
 */
public class SpringAppListener implements ApplicationListener {
	public void onApplicationEvent(ApplicationEvent applicationevent){
		
		//手动指定mybatis使用log4j打印日志！ 解决日志实现冲突问题
		org.apache.ibatis.logging.LogFactory.useLog4JLogging(); 
	}
}
