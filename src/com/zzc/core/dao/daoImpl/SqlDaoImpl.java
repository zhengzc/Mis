package com.zzc.core.dao.daoImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

import com.zzc.core.Exception.ZzcRollbackException;
import com.zzc.core.dao.SqlDao;

public class SqlDaoImpl implements SqlDao {
	private Logger log;
	private SimpleJdbcTemplate simpleJdbcTemplate;
	private String sql;
	
	public SqlDaoImpl(SimpleJdbcTemplate simpleJdbcTemplate,String sql){
		this.simpleJdbcTemplate = simpleJdbcTemplate;
		this.sql = sql;
	}
	/**
	 * 获取数据库中的sql
	 * @param tab_name
	 * @param sql_name
	 * @return
	 * @throws Exception
	 */
	private String getSql(String tab_name, String sql_name) throws DataAccessException{
		HashMap<String, String> inparam = new HashMap<String, String>();
		inparam.put("SQL_NAME", sql_name);
		inparam.put("TAB_NAME", tab_name);
		List<Map<String, Object>> retList = this.simpleJdbcTemplate.queryForList(this.sql, inparam);
		if(retList.isEmpty()){
			throw new ZzcRollbackException("获取sql："+tab_name+"::"+sql_name+"失败，请确认此sql在sql_code表中是否存在");
		}
		log.debug("SQL_NAME:"+sql_name+",TAB_NAME:"+tab_name);
		return (String)retList.get(0).get("SQL_STATEMENT");
	}
	/**
	 * 针对于SimpleJdbcTemplate 中的queryForList方法返回的List<Map<String,Object>>类型转化为List<Map<String,String>>类型
	 * 为了方便使用！
	 * @param param
	 * @return
	 */
	private List<Map<String,String>> Object2String(List<Map<String,Object>> param){
		List<Map<String,String>> retList = new ArrayList<Map<String,String>>();
		Iterator<Map<String, Object>> it = param.iterator();
		while(it.hasNext()){
			Map<String, Object> temp = it.next();
			retList.add(Object2String(temp));
		}
		return retList;
	}
	/**
	 * 将Map<String,Object>强转为Map<String,String>
	 * @param param
	 * @return
	 */
	private Map<String,String> Object2String(Map<String,Object> param){
		Map<String, String> retMap = new HashMap<String, String>();
		Iterator<String> it = param.keySet().iterator();
		String key;
		Object value;
		while(it.hasNext()){
			key = it.next();
			value = param.get(key);
			retMap.put(key, value!=null?value.toString():"");
		}
		return retMap;
	}
	/**
	 * 查询 
	 * @param tab_name
	 * @param sql_name
	 * @param param
	 * @return
	 */
	public List<Map<String,String>> queryForStrList(String tab_name, String sql_name,Map<String,Object> param){
		List<Map<String,String>> retList = Object2String(this.queryForList(tab_name, sql_name, param));
		return retList;
	}
	/**
	 * 查询
	 * @param tab_name
	 * @param sql_name
	 * @return
	 */
	public List<Map<String,String>> queryForStrList(String tab_name, String sql_name){
		Map<String, Object> param = new HashMap<String, Object>();
		List<Map<String,String>> retList = Object2String(this.queryForList(tab_name, sql_name, param));
		return retList;
	}
	/**
	 * 查询
	 * @param tab_name
	 * @param sql_name
	 * @param param
	 * @return
	 */
	public List<Map<String,Object>> queryForList(String tab_name, String sql_name, Map<String,Object> param){
		return this.simpleJdbcTemplate.queryForList(this.getSql(tab_name, sql_name), param);
	}
	/**
	 * 查询
	 * @param tab_name
	 * @param sql_name
	 * @return
	 */
	public List<Map<String,Object>> queryForList(String tab_name, String sql_name){
		Map<String, String> param = new HashMap<String, String>();
		return this.simpleJdbcTemplate.queryForList(this.getSql(tab_name, sql_name), param);
	}
	/**
	 * 用于返回单条数据的查询
	 * @param tab_name
	 * @param sql_name
	 * @param param
	 * @return
	 */
	public Map<String,Object> queryForMap(String tab_name, String sql_name, Map<String,Object> param){
		Map<String,Object> retMap = null;
		try {
			retMap = this.simpleJdbcTemplate.queryForMap(this.getSql(tab_name, sql_name), param);
		} catch (EmptyResultDataAccessException e) {//查询不到数据，返回null
			return retMap;
		}
		return retMap;
	}
	/**
	 * 用于返回单条数据的查询
	 * @param tab_name
	 * @param sql_name
	 * @param param
	 * @return
	 */
	public Map<String, String> queryForStrMap(String tab_name, String sql_name, Map<String,Object> param){
		Map<String,String> retMap = null;
		try {
			retMap = this.Object2String(this.simpleJdbcTemplate.queryForMap(this.getSql(tab_name, sql_name), param));
		} catch (EmptyResultDataAccessException e) {//查询不到数据，返回null
			return retMap;
		}
		return retMap;
	}
	/**
	 * 更新操作！
	 * @param tab_name
	 * @param sql_name
	 * @param param
	 * @return
	 */
	public int updateStrPara(String tab_name, String sql_name, Map<String,Object> param){
		return this.simpleJdbcTemplate.update(this.getSql(tab_name, sql_name), param);
	}
	/**
	 *
	 */
	public int update(String tab_name, String sql_name, Map<String,Object> param){
		return this.simpleJdbcTemplate.update(sql_name, param);
	}
	
	public Logger getLog() {
		return log;
	}
	public void setLog(Logger log) {
		this.log = log;
	}
}