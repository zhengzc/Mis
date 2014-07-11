package com.zzc.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.zzc.core.dao.SqlDao;
import com.zzc.dao.MUserDao;

public class MUserDaoImpl implements MUserDao {
	private Logger log;
	private SqlDao sqlDao;

	@Override
	public Map<String, String> queryUserByNameAndPassword(String userId, String userPassword) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("USER_ID", userId);
		param.put("USER_PASSWORD", userPassword);
		param.put("IS_VALID", "1");
		return this.sqlDao.queryForStrMap("td_m_user", "qry_user_by_name_and_pw",param);
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
