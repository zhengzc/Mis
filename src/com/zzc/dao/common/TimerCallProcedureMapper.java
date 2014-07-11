package com.zzc.dao.common;

import java.util.Map;

/**
 * 此接口主要提供动态存储过程调用，目前主要由TimerTask使用
 * @author ying
 *
 */
public interface TimerCallProcedureMapper {
	/**
	 * 动态调用存储过程
	 * 要求存储过程不需要参数，返回结果目前有两个v_resultcode v_resultinfo
	 * @param procedureName
	 */
	public void executeProcedure(String procedureName,Map<String,String> map);
}
