package com.zzc.core.serivce;

import org.apache.log4j.Logger;

import com.zzc.core.SpringAppContext;
import com.zzc.core.dao.SqlDao;
/**
 * 此类可作为每个服务的基类，服务层目前有许多共同之处
 * @author ying
 *
 */
public class MyBaseService {
	public Logger log = Logger.getRootLogger();//日志
	
	/**
	 * 根据beanId直接获取相应的服务！
	 * 这里使用泛型完整自动转换
	 * @param beanId
	 * @return
	 */
	public <T>T getService(String beanId){
		return (T)SpringAppContext.getBean(beanId);
	}
	
	public <T>T getService(Class arg){
		return (T)SpringAppContext.getBean(arg);
	}
	
	/**
	 * 获取sqlDao的统一方法！
	 * @return
	 */
	public SqlDao getSqlDao(){
		return (SqlDao)this.getService("sqlDao");
	}
}
