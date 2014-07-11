package com.zzc.core.Exception;

import org.apache.log4j.Logger;

/**
 * 自定义回滚
 * @author ying
 *
 */
public class ZzcRollbackException extends RuntimeException {

	private Logger log = Logger.getRootLogger();
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ZzcRollbackException(String msg) {
		super(msg);
	}
	
	public ZzcRollbackException(String msg,Exception e) {
		super(msg,e);
		log.error(msg,e);
	}

}
