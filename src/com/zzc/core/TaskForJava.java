package com.zzc.core;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;


/**
 * 此类实现Job接口，所有java调用统一由此类执行
 * 此类会根据jobDetail所传参数，从spring容器获取所需服务，然后运用反射原理，调用指定方法！
 * 此类不由spring管理，保证所有java调用都是一个新的实例！这也是实现job接口的作用，由quartz框架运用反射原理实例化，不需要我们关心
 * @author ying
 *
 */
public class TaskForJava implements Job {
	private Logger log = Logger.getRootLogger();

	@Override
	public void execute(JobExecutionContext jobexecutioncontext)
			throws JobExecutionException {
		//获取参数
		JobDataMap param = jobexecutioncontext.getJobDetail().getJobDataMap();
		String java_set = param.getString("java_set");
		//解析beanName method
		String[] str = java_set.split("\\|");
		if(str.length != 3){
			throw new JobExecutionException("java调用规则配置不正确");
		}
		String className = str[0];//类名
		String beanName = str[1];//beanName
		String method = str[2];//方法名
		log.info("||||||||||||||||||||||||||||||||||||||");
		log.info("|||TaskForJava begin execute：className="+className+" beanName"+beanName+" method:"+method);
		log.info("||||||||||||||||||||||||||||||||||||||");
		log.info("jobName:"+jobexecutioncontext.getJobDetail().getName()+" jobGroup:"+jobexecutioncontext.getJobDetail().getGroup());
		log.info("className="+className+" beanName"+beanName+" method:"+method);
		//使用反射机制
		Class<?> javaClass;
		try {
			//获取class
			javaClass = Class.forName(className);
		} catch (ClassNotFoundException e1) {
			log.error("找不到配置的类", e1);
			throw new JobExecutionException("找不到配置的类");
		}
//		SpringAppContext.getBean(beanName);
		try {
			//获取方法 目前方法都没有参数
			Method javaMethod = javaClass.getDeclaredMethod(method, null);
			//执行方法
			javaMethod.invoke(SpringAppContext.getBean(beanName), null);
		} catch (Exception e) {
			log.error("找不到配置的方法", e);
			throw new JobExecutionException("找不到配置的方法");
		}
		log.info("||||||||||||||||||||||||||||||||||||||");
		log.info("||||TaskForJava execute complete||||||");
		log.info("||||||||||||||||||||||||||||||||||||||");
	}
}
