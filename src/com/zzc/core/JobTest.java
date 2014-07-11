package com.zzc.core;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class JobTest implements Job{

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		System.out.println("------------job1-------------");
	}
}