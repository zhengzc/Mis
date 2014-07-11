package com.zzc.service.common.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.zzc.core.Exception.ZzcRollbackException;
import com.zzc.core.dataSourceManager.ContextHolder;
import com.zzc.dao.common.TimerCallProcedureMapper;
import com.zzc.service.common.TimerTaskService;

public class TimerTaskServiceImpl implements TimerTaskService {
	private Logger log = Logger.getRootLogger();
	private TimerCallProcedureMapper timerCallProcedureMapper;

	@Override
	public String callProcedure(String procedureName, String dbName) {
		Map<String,String> map = new HashMap<String, String>();
		try{
			ContextHolder.setContext(dbName);//切换到指定数据库
			//执行存储过程
			map.put("v_resultcode", "-1");
			map.put("v_resultinfo", "");
			this.timerCallProcedureMapper.executeProcedure(procedureName,map);
			//判断执行情况
			if(!"0".equals(map.get("v_resultcode"))){
				throw new ZzcRollbackException(map.get("v_resultinfo"));
			}
		}catch (ZzcRollbackException e) {
			log.error("callProcedure执行失败", e);
		}
		//返回结果
		return map.get("v_resultinfo");
	}

	public TimerCallProcedureMapper getTimerCallProcedureMapper() {
		return timerCallProcedureMapper;
	}

	public void setTimerCallProcedureMapper(
			TimerCallProcedureMapper timerCallProcedureMapper) {
		this.timerCallProcedureMapper = timerCallProcedureMapper;
	}
}
