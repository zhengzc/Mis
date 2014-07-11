package com.zzc.service.test;

import java.util.Map;

public interface TestService {
	/**
	 * 这个打发的发啊飞
	 * @param userId
	 * @param password
	 * @return
	 */
	public Map<String,String> testLogin(String userId,String password);
}
