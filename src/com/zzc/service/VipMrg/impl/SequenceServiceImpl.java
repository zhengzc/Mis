package com.zzc.service.VipMrg.impl;

import java.util.HashMap;
import java.util.Map;

import com.zzc.core.serivce.MyBaseService;
import com.zzc.service.VipMrg.SequenceService;

public class SequenceServiceImpl extends MyBaseService implements SequenceService {
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

}
