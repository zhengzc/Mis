package com.zzc.service.VipMrg.Query.Impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zzc.core.serivce.MyBaseService;
import com.zzc.service.VipMrg.Query.QueryTradeInfoService;

public class QueryTradeInfoServiceImpl extends MyBaseService implements QueryTradeInfoService{

	@Override
	public List<Map<String, Object>> queryTradeNumByDate(Date startDate,
			Date endDate, String mUserId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("startDate", startDate);
		param.put("endDate", endDate);
		param.put("mUserId", mUserId);
		return this.getSqlDao().queryForList("tf_b_trade", "qry_tradenum_by_date", param);
	}

}
