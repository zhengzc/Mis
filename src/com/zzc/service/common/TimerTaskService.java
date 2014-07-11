package com.zzc.service.common;

/**
 * 此接口主要为TimerTask服务
 * @author ying
 *
 */
public interface TimerTaskService {
	/**
	 * 根据存储过程名字和数据库名字调用存储过程
	 * @param procedureName
	 * @param dbName
	 * @return
	 */
	public String callProcedure(String procedureName,String dbName);
}
