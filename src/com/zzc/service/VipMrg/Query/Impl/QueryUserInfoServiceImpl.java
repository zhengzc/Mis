package com.zzc.service.VipMrg.Query.Impl;

import java.util.List;

import com.zzc.core.Exception.ZzcRollbackException;
import com.zzc.core.serivce.MyBaseService;
import com.zzc.dao.VipMrg.UserInfoMapper;
import com.zzc.entity.VipMrg.UserInfo;
import com.zzc.service.VipMrg.Query.QueryUserInfoService;

public class QueryUserInfoServiceImpl extends MyBaseService implements QueryUserInfoService{
	private UserInfoMapper userInfoMapper = this.getService(UserInfoMapper.class);

	@Override
	public List<UserInfo> queryUserList(String cardId, String userName,
			String serialNumber, String mUserId) {
		try{
			UserInfo userInfo = new UserInfo();
			userInfo.setCardId(cardId);
			userInfo.setUserName(userName);
			userInfo.setSerialNumber(serialNumber);
			userInfo.setmUserId(mUserId);
			return this.userInfoMapper.queryUserInfoCommon(userInfo);
		}catch (Exception e) {
			log.error("queryUserList执行失败！",e);
			throw new ZzcRollbackException("queryUserList执行失败！",e);
		}
	}

	@Override
	public List<UserInfo> queryUserList(UserInfo userInfo) {
		try {
			return this.userInfoMapper.queryUserInfoCommon(userInfo);
		} catch (Exception e) {
			log.error("queryUserList执行失败！",e);
			throw new ZzcRollbackException("queryUserList执行失败！",e);
		}
	}
}
