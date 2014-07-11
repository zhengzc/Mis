package com.zzc.core.dao;

import java.util.List;
import java.util.Map;

public interface SqlDao {
	/**
	 * 查询  返回List<Map<String,String>>
	 * @param tab_name 表标识
	 * @param sql_name sql名称
	 * @param param    参数
	 * @return
	 */
	public List<Map<String,String>> queryForStrList(String tab_name, String sql_name,Map<String,Object> param);
	/**
	 * 查询  返回List<Map<String,String>>
	 * @param tab_name 表标识
	 * @param sql_name sql名称
	 * @return
	 */
	public List<Map<String,String>> queryForStrList(String tab_name, String sql_name);
	/**
	 * 查询  返回List<Map<String,Object>>
	 * @param tab_name 表标识
	 * @param sql_name sql名称
	 * @param param    参数
	 * @return
	 */
	public List<Map<String,Object>> queryForList(String tab_name, String sql_name, Map<String,Object> param);
	/**
	 * 查询  返回List<Map<String,String>>
	 * @param tab_name 表标识
	 * @param sql_name sql名称
	 * @return
	 */
	public List<Map<String,Object>> queryForList(String tab_name, String sql_name);
	/**
	 * 查询  返回Map<String,Object>
	 * @param tab_name 表标识
	 * @param sql_name sql名称
	 * @param param    参数
	 * @return  查询不到数据的时候返回null
	 */
	public Map<String,Object> queryForMap(String tab_name, String sql_name, Map<String,Object> param);
	/**
	 * 查询  返回List<Map<String,String>>
	 * @param tab_name 表标识
	 * @param sql_name sql名称
	 * @param param    参数
	 * @return 查询不到数据的时候返回null
	 */
	public Map<String, String> queryForStrMap(String tab_name, String sql_name, Map<String,Object> param);
	/**
	 * 更新
	 * @param tab_name 表标识
	 * @param sql_name sql名称
	 * @param param    参数
	 * @return
	 */
	public int updateStrPara(String tab_name, String sql_name, Map<String,Object> param);
	/**
	 * 更新
	 * @param tab_name
	 * @param sql_name
	 * @param param
	 * @return
	 */
	public int update(String tab_name, String sql_name, Map<String,Object> param);
}
