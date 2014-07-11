package com.zzc.service.VipMrg.Query;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface QueryTradeInfoService {
	/**
	 * 根据开始时间 结束时间 查询当前用户受理订单统计信息
	 * @param startDate
	 * @param endDate
	 * @param mUserId
	 * @return
	 */
	public List<Map<String, Object>> queryTradeNumByDate(Date startDate,Date endDate,String mUserId);
}
