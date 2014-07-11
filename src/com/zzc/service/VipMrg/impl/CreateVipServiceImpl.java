package com.zzc.service.VipMrg.impl;

import java.util.Date;

import com.zzc.core.Exception.ZzcRollbackException;
import com.zzc.entity.VipMrg.TradeInfo;
import com.zzc.entity.VipMrg.TradePay;
import com.zzc.entity.VipMrg.TradeUser;
import com.zzc.entity.VipMrg.UserInfo;
import com.zzc.service.VipMrg.AbstractCommonSubmitService;

public class CreateVipServiceImpl extends AbstractCommonSubmitService {

	@Override
	public void execute(TradeInfo tradeInfo) {
		TradeUser tradeUser = tradeInfo.getTradeUser();
		TradePay tradePay = tradeInfo.getTradePay();
		
		UserInfo userInfo = new UserInfo();
		
		userInfo.setUserId(tradeUser.getUserId());
		userInfo.setmUserId(tradeUser.getmUserId());
		userInfo.setCardId(tradeUser.getCardId());
		userInfo.setUserName(tradeUser.getUserName());
		userInfo.setSerialNumber(tradeUser.getSerialNumber());
		userInfo.setAddress(tradeUser.getAddress());
		userInfo.setSchool(tradeUser.getSchool());
		userInfo.setBirthday(tradeUser.getBirthday());
		userInfo.setCurrentFee(tradePay.getFee());
		userInfo.setSumFee(tradePay.getFee());
		userInfo.setLastCostDate(new Date());
		userInfo.setCardType(tradeUser.getCardType());
		userInfo.setCardStartDate(tradeUser.getCardStartDate());
		userInfo.setCardEndDate(tradeUser.getCardEndDate());
		userInfo.setUserTypeCode(tradeUser.getUserTypeCode());
		userInfo.setCreateDate(tradeInfo.getAcceptDate());
		
		int count = this.userInfoMapper.insertUserInfo(userInfo);
		
		if(count != 1){
			throw new ZzcRollbackException("生成用户实例失败！");
		}
	}

}
