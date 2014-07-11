package com.zzc.core;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.zzc.service.common.TimerTaskService;

/**
 * 此类实现Job接口，所有存储过程调用统一由此类执行
 * 此类根据所传参数，调用指定的服务(TimerTaskService接口)，完成存储过程调用
 * 此类不由spring管理，保证所有java调用都是一个新的实例！这也是实现job接口的作用，由quartz框架运用反射原理实例化，不需要我们关心
 * @author ying
 *
 */
public class TaskForProcedure implements Job {
	private Logger log = Logger.getRootLogger();

	@Override
	public void execute(JobExecutionContext jobexecutioncontext)
			throws JobExecutionException {
		//获取参数
		JobDataMap param = jobexecutioncontext.getJobDetail().getJobDataMap();
		String dbName = param.getString("pro_ds");
		String procedureName = param.getString("pro_name");
		log.info("||||||||||||||||||||||||||||||||||||||");
		log.info("|||TaskForProcedure begin execute|||||");
		log.info("||||||||||||||||||||||||||||||||||||||");
		log.info("jobName:"+jobexecutioncontext.getJobDetail().getName()+" jobGroup:"+jobexecutioncontext.getJobDetail().getGroup());
		log.info("procedureName:"+procedureName+" dbName"+dbName);
		//从spring获取服务
		TimerTaskService timerTaskService = (TimerTaskService)SpringAppContext.getBean("timerTaskService");
		//执行
		String v_resultinfo = timerTaskService.callProcedure(procedureName, dbName);
		log.info("execute resultInfo:"+v_resultinfo);
		log.info("||||||||||||||||||||||||||||||||||||||");
		log.info("||TaskForProcedure execute complete|||");
		log.info("||||||||||||||||||||||||||||||||||||||");
	}
}
