package com.zzc.core;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.util.StopWatch;

/**
 * 执行计时器，此类与spring AOP结合使用，用来打印代码执行时间！
 * @author ying
 *
 */
@Aspect
public class ExecuteTimer {
	private Logger log;
	/**
	 * 拦截com.zzc下面所有子包中的所有Impl结尾的类中的方法，统计方法执行时间
	 * @param pjp
	 * @return
	 * @throws Throwable
	 */
	@Around ("execution(* com.zzc.service..*.*(..))")
	public Object invoke(ProceedingJoinPoint pjp) throws Throwable {
		//秒表
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();//启动秒表
		Object obj = pjp.proceed();//调用方法
		stopWatch.stop();//停止秒表
		pjp.getSignature().getName();
		log.info("======="+pjp.getSignature().getName()+"方法执行时间========>"+stopWatch.getTotalTimeSeconds());//输出执行时间！！
		return obj;//注意方法的返回值，容器会帮我们自动转型，不必担心
	}

	public Logger getLog() {
		return log;
	}

	public void setLog(Logger log) {
		this.log = log;
	}

}
