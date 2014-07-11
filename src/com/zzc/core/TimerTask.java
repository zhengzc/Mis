package com.zzc.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

import com.zzc.core.dao.SqlDao;
/**
 * 定时器，基于quartz框架
 * 
 * 通过读取下面两张表初始化定时器，为触发器绑定任务并添加到定时器中，此类目前并不与spring耦合（但其调用的job目前与spring耦合）
 * 
 * 任务模式
 * 1.java调用，基于TaskForJava类，此类与spring耦合，调用spring管理的bean，根据所传的 （类名|beanName|方法）
 * 完成java调用，一般建议调用service层方法，这样有助于框架的事务管理！
 * 2.存储过程调用，基于TaskForProcedure类，此类同样与spring耦合，调用指定服务，根据所传（存储过程名字，数据库名字）
 * 完成存储过程调用！
 * 
 * 触发器模式
 * 1.corn表达式，关于表达式的描述，请见项目附件
 * 
 * @author ying
 *	任务表：td_s_task
 *		task_id		任务id
 *		task_name	任务名称
 *		task_group	任务组
 *		task_type	任务类型 J：java调用   P：调用存储过程
 *		task_set	任务设置  如果task_type是J 则这里写 （类名|beanName|方法），如果是1写调用存储过程名字
 *		pro_ds		如果任务类型是1，此字段才有意义，此字段指定调用存储过程的数据库名字，目前此名字与spring配置文件的数据源有关
 *		is_valid	是否生效 yes生效  no不生效
 *		task_detail	任务描述
 *		
 *	触发器表：td_s_trigger
 *		trigger_name	触发器名称
 *		trigger_group	触发器组
 *		task_id			任务id
 *		trigger_detail	触发器描述
 *		trigger_type	触发器类型  0：统一使用cron表达式
 *		trigger_set		cron表达式  （原表达式由空格分隔，此处配置修改为用：分隔符）
 *		trigger_explain cron表达式描述
 *
 *	quartz规定，每个任务可以有多个触发器，而一个触发器只能有一个任务！
 *
 *	以触发器为基础，为每个触发器绑定任务，最后添加到容器中，保证一个触发器对应一个任务，这样也允许一个任务绑定多个触发器！
 *
 */
public class TimerTask {
	private Logger log = Logger.getRootLogger();
	private Scheduler scheduler;
	private SchedulerFactory schedulerFactory = new StdSchedulerFactory();
	
	private SqlDao sqlDao;
	private List<Map<String,String>> tasks = new ArrayList<Map<String,String>>();//所有任务
	private List<Map<String,String>> triggers = new ArrayList<Map<String,String>>();//所有触发器
	
	public TimerTask(SqlDao sqlDao) throws Exception{
		log.info("---------------->TimerTask init");
		
		this.sqlDao = sqlDao;
		//定时容器
		this.scheduler = schedulerFactory.getScheduler();
		//获取任务列表
//		this.getTasks();
		//加载
//		this.load();
		
		log.info("---------------->TimerTask init complete");
	}
	/**
	 * 停止旧的定时器，加载任务并启动新的定时器！
	 * @throws Exception
	 */
	public void loadAndStart() throws Exception{
		log.info("---------------->TimerTask load and Start");
		if(this.scheduler.isShutdown()){
			this.scheduler.shutdown(true);//干掉当前定时器
			this.scheduler = this.schedulerFactory.getScheduler();//获取新的定时器
		}
		this.getTasks();//重新获取任务列表
		this.load();//重新加载任务！
		this.scheduler.start();//启动定时器
		
		log.info("---------------->TimerTask start complete");
	}
	/**
	 * 获取任务列表！
	 * @throws Exception
	 */
	private void getTasks() throws Exception{
		log.info("---------------->TimerTask getTasks begin");
		this.tasks = this.sqlDao.queryForStrList("td_s_task", "qry_all_task");
		this.triggers = this.sqlDao.queryForStrList("td_s_trigger", "qry_all_trigger");
		log.info("---------------->TimerTask getTasks complete");
	}
	/**
	 * 加载所有触发器和job到定时容器
	 */
	private void load() throws Exception{
		log.info("---------------->TimerTask load task and trigger begin");
		//遍历任务列表
		for(Map<String,String> map:this.tasks){
			//任务
			if("J".equals(map.get("task_type"))){//java调用
				String java_set = map.get("task_set");//javaBean name
				//jobDetail
				JobDetail jobDetail = new JobDetail();
				jobDetail.setName(map.get("task_name"));
				jobDetail.setGroup(map.get("task_group"));
				jobDetail.setJobClass(TaskForJava.class);
				//为job传递参数
				jobDetail.getJobDataMap().put("java_set", java_set);
				//添加任务
				scheduler.addJob(jobDetail, true);
			}else if("P".equals(map.get("task_type"))){//存储过程调用
				//准备存储过程调用参数
				String procedureName = map.get("task_set");//存储过程名字
				//需要执行的数据库
				String db_name = map.get("pro_ds");
				
				//实例化jobDetail
				JobDetail jobDetail = new JobDetail();
				jobDetail.setName(map.get("task_name"));//存储过程调用，任务名字定义为task_name+"_"+db_name，在sql中完成
				jobDetail.setGroup(map.get("task_group"));
				jobDetail.setJobClass(TaskForProcedure.class);
				//为job传递参数
				jobDetail.getJobDataMap().put("pro_name", procedureName);
				jobDetail.getJobDataMap().put("pro_ds", db_name);
				//添加任务
				scheduler.addJob(jobDetail, true);
			}else{
				throw new Exception("不支持的调用类型！");
			}	
		}
		//遍历触发器列表
		for(Map<String,String> map:this.triggers){
			//添加触发器
			if("0".equals(map.get("trigger_type"))){//cron表达式
				scheduler.scheduleJob(new CronTrigger(
						map.get("trigger_name"),
						map.get("trigger_group"), 
						map.get("task_name"), 
						map.get("task_group"), 
						map.get("trigger_set").replace(":", " ")
					));
			}else{
				throw new Exception("不支持的触发规则！");
			}
		}
		
		log.info("---------------->TimerTask load task and trigger complete");
	}
	/**
	 * 测试方法
	 * @throws Exception
	 */
	public static void createTimerTask() throws Exception{
		//运行容器
		SchedulerFactory schedulerFactory = new StdSchedulerFactory();
		Scheduler scheduler = schedulerFactory.getScheduler();
		scheduler.start();
		//任务
		JobDetail job = new JobDetail("job","group1",JobTest.class);
		JobDetail job2 = new JobDetail("job2","group1",JobTest2.class);
		//触发器
		CronTrigger cronTrigger = new CronTrigger("trigger1","group1");
		cronTrigger.setCronExpression("0 35 15 * * ?");
		
		CronTrigger cronTrigger2 = new CronTrigger("trigger2","group1");
		cronTrigger2.setCronExpression("10 35 15 * * ?");
		
		//指定关联规则
		cronTrigger.setJobGroup("group1");
		cronTrigger.setJobName("job");
		cronTrigger.setJobName("job2");
		
		cronTrigger2.setJobGroup("group1");
		cronTrigger2.setJobName("job2");
		
		scheduler.addJob(job, true);
		scheduler.addJob(job2, true);
		scheduler.scheduleJob(cronTrigger);
		scheduler.scheduleJob(cronTrigger2);
//		scheduler.scheduleJob(job, cronTrigger);
//		scheduler.scheduleJob(job, cronTrigger2);
//		scheduler.scheduleJob(job2,cronTrigger2);
	}
	
//	public TimerTask createTimerTask(SqlDao sqlDao) throws Exception{
//		if(this.timerTask ==null){
//			this.timerTask = new TimerTask(sqlDao);
//		}
//		return this.timerTask;
//	}
	
//	public void start() throws Exception{
//		log.info("---------------->TimerTask start begin");
//		this.reloadAndStart();
//		log.info("---------------->TimerTask start complete");
//	}
	
//	public void shutdown() throws Exception{
//		this.scheduler.shutdown();
//	}
//	
	/**
	 * 停止定时器并释放所有资源
	 */
	public void shutdown(Boolean waitForJobsToComplete) throws Exception{
		log.info("---------------->TimerTask shutdown begin");
		this.scheduler.shutdown(waitForJobsToComplete);
		log.info("---------------->TimerTask shutdown complete");
	}
	/**
	 * 判断当前定时器是否shutdown
	 * @return
	 * @throws Exception
	 */
	public Boolean isShutdown() throws Exception{
		return this.scheduler.isShutdown();
	}
	/**
	 * 判断当前定时器是否started
	 * @return
	 * @throws Exception
	 */
	public Boolean isStarted() throws Exception{
		return this.scheduler.isStarted();
	}
	/**
	 * 当前定时器是否处于StandbyMode （已初始化，等待启动）
	 * @return
	 * @throws Exception
	 */
	public Boolean isInStandbyMode() throws Exception{
		return this.scheduler.isInStandbyMode();
	}
	/**
	 * 获取当前加载的任务信息
	 * @return
	 */
	public List<Map<String,String>> getJobs(){
		List<Map<String,String>> jobs;
		for(Map<String,String> temp:this.triggers){
			if("P".equals(temp.get("task_type"))){
				temp.put("task_type", "存储过程调用");
			}else if("J".equals(temp.get("task_type"))){
				temp.put("task_type", "java调用");
			}
		}
		return this.triggers;
	}
	
	public SqlDao getSqlDao() {
		return sqlDao;
	}

	public void setSqlDao(SqlDao sqlDao) {
		this.sqlDao = sqlDao;
	}

	public static void main(String[] args){
		try {
			TimerTask.createTimerTask();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}