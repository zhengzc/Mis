package com.zzc.core.dataSourceManager;

/**
 * 用来动态切换数据源！
 * @author ying
 *
 */
public class DbContextHolder {
	//必须是线程安全的
	private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();
	/**
	 * 设置数据源
	 * @param dataSource
	 */
	public static void setDataSource (String dataSource) {
		contextHolder.set(dataSource);
	}
	/**
	 * 获取数据源
	 * @return
	 */
	public static String getDataSource() {
		return ((String)contextHolder.get());
	}
	/**
	 * 清除数据源
	 */
	public static void clearDataSource() {
		contextHolder.remove();
	}

}
