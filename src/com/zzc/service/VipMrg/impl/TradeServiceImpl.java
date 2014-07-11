package com.zzc.service.VipMrg.impl;

import java.util.HashMap;
import java.util.Map;

import com.zzc.core.Exception.ZzcRollbackException;
import com.zzc.core.serivce.MyBaseService;
import com.zzc.service.VipMrg.TradeService;

public class TradeServiceImpl extends MyBaseService implements TradeService {
	private final String tradeIdSeq = "user_id_seq";
	private final String userIdSeq = "trade_id_seq";
	
	@Override
	public String getSequence(String seqName) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("SEQNAME", seqName);
		Map<String, String> ret = this.getSqlDao().queryForStrMap("sequence", "get_sequence",param);
		return ret.get("seq");
	}

	@Override
	public String getTradeIdSeq() {
		return this.getSequence(this.tradeIdSeq);
	}

	@Override
	public String getUserIdSeq() {
		return this.getSequence(this.userIdSeq);
	}

	@Override
	public String getTradeFinshSerivceName(String tradeTypeCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tradeTypeCode", tradeTypeCode);
		Map<String,Object> retMap = this.getSqlDao().queryForMap("td_s_tradetype", "qry_by_tradetypecode", map);
		if(retMap == null){
			throw new ZzcRollbackException("找不到业务类型编码为"+tradeTypeCode+"的参数配置，请检查参数是否正确！");
		}
		String serviceName = (String)retMap.get("finish_service");
		if(serviceName == null || "".equals(serviceName)){
			throw new ZzcRollbackException("业务类型编码为"+tradeTypeCode+"的业务无法获取到完工流程，请检查参数是否正确！");
		}
		return serviceName;
	}
}
