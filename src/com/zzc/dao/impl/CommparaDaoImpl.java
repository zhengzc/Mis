package com.zzc.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.zzc.core.dao.SqlDao;
import com.zzc.dao.CommparaDao;

public class CommparaDaoImpl implements CommparaDao{
	private Logger log;
	private SqlDao sqlDao;
	
	@Override
	public String queryParaValueByCode(String paraCode){
		String retStr = "";
		
		HashMap<String, Object> inparam = new HashMap<String, Object>();
		inparam.put("PARAM_TYPE", "WEB");
		inparam.put("PARAM_CODE",paraCode);
		List<Map<String, String>> ret = this.sqlDao.queryForStrList("td_s_commparam", "qry_param_value_by_param_code", inparam);
		log.debug(ret.toString());
		if(!ret.isEmpty()){
			retStr = ret.get(0).get("param_value");
		}
		
		return retStr;
	}

	public Logger getLog() {
		return log;
	}

	public void setLog(Logger log) {
		this.log = log;
	}

	public SqlDao getSqlDao() {
		return sqlDao;
	}

	public void setSqlDao(SqlDao sqlDao) {
		this.sqlDao = sqlDao;
	}
}
