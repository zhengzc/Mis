package com.zzc.service.VipMrg.impl;

import java.util.HashMap;
import java.util.Map;

import com.zzc.entity.VipMrg.TradeInfo;
import com.zzc.service.VipMrg.AbstractCommonSubmitService;

public class ChargeFeeServiceImpl extends AbstractCommonSubmitService {

	@Override
	public void execute(TradeInfo tradeInfo) {
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("userId", tradeInfo.getUserId());
		param.put("chargeFee", tradeInfo.getTradePay().getFee());
		this.userInfoMapper.updateUserFeeInfo(param);
	}
}
