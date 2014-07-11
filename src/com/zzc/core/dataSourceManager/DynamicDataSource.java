package com.zzc.core.dataSourceManager;

import org.apache.log4j.Logger;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 动态切换数据源的类 继承AbstractRoutingDataSource类
 * @author ying
 *
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
	private static Logger log = Logger.getRootLogger();
	@Override
	protected Object determineCurrentLookupKey() {
		log.info("--------------------->当前数据库为："+DbContextHolder.getDataSource());
		return DbContextHolder.getDataSource();
	}

}
