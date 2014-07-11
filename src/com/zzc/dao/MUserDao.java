package com.zzc.dao;

import java.util.Map;

public interface MUserDao {
	/**
	 * 根据用户名和密码获取用户资料，获取不到数据返回null
	 * @param userName
	 * @param userPassword
	 * @return
	 */
	public Map<String, String> queryUserByNameAndPassword(String userId,String userPassword);
}
