package com.zzc.dao.VipMrg;

import java.util.List;
import java.util.Map;

import com.zzc.entity.VipMrg.UserInfo;

public interface UserInfoMapper {
	/**
	 * 插入tf_f_user表
	 * @param userInfo
	 * @return
	 */
	public int insertUserInfo(UserInfo userInfo);
	
	/**
	 * 更新用户资料
	 * 此方法对应一个动态sql语句，具体所需参数参考sql
	 * @param userInfo
	 * @return
	 */
	public int updateUserInfo(UserInfo userInfo);
	
	/**
	 * 更新用户费用信息
	 * @param userId
	 * @param chargeFee
	 * @return
	 */
	public int updateUserFeeInfo(Map map);
	
	/**
	 * 通用查询
	 * 作为一个通用查询，此查询可能会生成效率比较低的sql，使用的时候请注意
	 * 通过修改xml中的具体sql，能支持多种查询
	 * @param userInfo
	 * @return
	 */
	public List<UserInfo> queryUserInfoCommon(UserInfo userInfo);
}
