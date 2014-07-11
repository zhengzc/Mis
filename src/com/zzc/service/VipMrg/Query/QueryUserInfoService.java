package com.zzc.service.VipMrg.Query;

import java.util.List;

import com.zzc.entity.VipMrg.UserInfo;

public interface QueryUserInfoService {
	/**
	 * 查询用户列表
	 * 根据 卡号 姓名 手机号 中的一种或者多种进行查询
	 * @param cardId 卡号
	 * @param userName 姓名
	 * @param serialNumber 手机号
	 * @param mUserId 归属用户
	 * @return
	 */
	public List<UserInfo> queryUserList(String cardId,String userName,String serialNumber,String mUserId);
	
	/**
	 * 查询用户列表
	 * @return
	 */
	public List<UserInfo> queryUserList(UserInfo userInfo);
}
