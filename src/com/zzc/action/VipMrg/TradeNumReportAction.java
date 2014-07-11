package com.zzc.action.VipMrg;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.struts2.json.annotations.JSON;

import com.zzc.core.action.MyBaseAction;
import com.zzc.service.VipMrg.Query.QueryTradeInfoService;

/**
 * 业务查询统计action
 * 因为此页面只只涉及到查询，不需要走统一提交的方式，所以直接继承myBaseAction即可，不需要继承BasePageAction
 * @author zhengzc
 *
 */
public class TradeNumReportAction extends MyBaseAction {
	private QueryTradeInfoService queryTradeInfoService;
	
	private Date startDate;//开始时间
	private Date endDate;//结束时间
	
	public String queryTradeNum(){
		//调用查询
		List<Map<String, Object>> ret = this.queryTradeInfoService.queryTradeNumByDate(startDate, endDate, this.getMySession().getUserId());
		//设置返回值
		this.putRetJsonVaule(ret);
		
		return this.jsonResult;
	}

	//set get
	public QueryTradeInfoService getQueryTradeInfoService() {
		return queryTradeInfoService;
	}

	public void setQueryTradeInfoService(QueryTradeInfoService queryTradeInfoService) {
		this.queryTradeInfoService = queryTradeInfoService;
	}

	public Date getStartDate() {
		return startDate;
	}

	@JSON(format="yyyy-MM-dd HH:mm:ss")
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}
	
	@JSON(format="yyyy-MM-dd HH:mm:ss")
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
}
