package com.zzc.service.test.impl;

import java.util.HashMap;
import java.util.Map;

import com.zzc.core.dao.SqlDao;
import com.zzc.service.test.TestService;

public class TestServiceImpl implements TestService {
	private SqlDao sqlDao;

	@Override
	public Map<String, String> testLogin(String userId, String password) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_id", userId);
		map.put("password", password);
		return sqlDao.queryForStrMap("test", "test_queryLogin", map);
	}

	public SqlDao getSqlDao() {
		return sqlDao;
	}

	public void setSqlDao(SqlDao sqlDao) {
		this.sqlDao = sqlDao;
	}
}
