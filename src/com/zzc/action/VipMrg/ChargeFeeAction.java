package com.zzc.action.VipMrg;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zzc.action.BasePageAction;
import com.zzc.entity.VipMrg.TradePay;
import com.zzc.entity.VipMrg.UserInfo;
import com.zzc.service.VipMrg.Query.QueryUserInfoService;

public class ChargeFeeAction extends BasePageAction {
	private QueryUserInfoService queryUserInfoService;

	@Override
	public void beforeSubmit() {
		String tradeId = this.tradeService.getTradeIdSeq();//tradeId
		
		TradePay tradePay = this.tradeInfo.getTradePay();
		tradePay.setTradeId(tradeId);
		tradePay.setModifyTag("0");
		
		this.tradeInfo.setTradeTypeCode("20");//设置业务类型
		this.tradeInfo.setTradeId(tradeId);//设置tradeId
		this.tradeInfo.setmUserId(this.getMySession().getUserId());//muserId
	}
	
	public String queryUserInfo(){
		String cardId = this.getPageParameter("cardId");
		String userName = this.getPageParameter("userName");
		String serialNumber = this.getPageParameter("serialNumber");
		String start = this.getPageParameter("start");
		String limit = this.getPageParameter("limit");
		String mUserId = this.getMySession().getUserId();
//		List<UserInfo> userInfos = this.queryUserInfoService.queryUserList(cardId, userName, serialNumber, mUserId);
		
		UserInfo userInfo = new UserInfo();
		userInfo.setCardId(cardId);
		userInfo.setUserName(userName);
		userInfo.setSerialNumber(serialNumber);
		userInfo.setStart(Integer.valueOf(start));
		userInfo.setLimit(Integer.valueOf(limit));
		userInfo.setmUserId(mUserId);
		List<UserInfo> userInfos = this.queryUserInfoService.queryUserList(userInfo);
		
		//分页
		Map<String,Object> rows = new HashMap<String, Object>();
		rows.put("total", 1000);//不统计总数，最多显示1000条数据
		rows.put("rows", userInfos);
		
		
//		Map<String, Object> retList = new HashMap<String, Object>();
//		retList.put("userList", rows);
		
//		this.putRetJsonVaule(retList);
		this.putRetJsonVaule(rows);
		return this.jsonResult;
	}

	public QueryUserInfoService getQueryUserInfoService() {
		return queryUserInfoService;
	}

	public void setQueryUserInfoService(QueryUserInfoService queryUserInfoService) {
		this.queryUserInfoService = queryUserInfoService;
	}
}
