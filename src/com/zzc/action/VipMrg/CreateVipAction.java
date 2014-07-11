package com.zzc.action.VipMrg;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zzc.action.BasePageAction;
import com.zzc.entity.VipMrg.TradePay;
import com.zzc.entity.VipMrg.TradeUser;

public class CreateVipAction extends BasePageAction {
	
	/**
	 * 查询卡类型
	 * @return
	 */
	public String queryCardType(){
		List<Map<String,Object>> retList = new ArrayList<Map<String,Object>>();
		//卡类别
		Map<String,Object> tempMap = new HashMap<String, Object>();
		tempMap.put("cardName", "普通卡");
		tempMap.put("cardTypeCode","0");
		tempMap.put("fee",0);
		Map<String,Object> tempMap2 = new HashMap<String, Object>();
		tempMap2.put("cardName", "月卡");
		tempMap2.put("cardTypeCode","1");
		tempMap2.put("fee",200);
		Map<String,Object> tempMap3 = new HashMap<String, Object>();
		tempMap3.put("cardName", "季卡");
		tempMap3.put("cardTypeCode","2");
		tempMap3.put("fee",500);
		Map<String,Object> tempMap4 = new HashMap<String, Object>();
		tempMap4.put("cardName", "年卡");
		tempMap4.put("cardTypeCode","3");
		tempMap4.put("fee",1500);
		
		retList.add(tempMap);
		retList.add(tempMap2);
		retList.add(tempMap3);
		retList.add(tempMap4);
		
		this.putRetJsonVaule(retList);
		return this.jsonResult;
	}
	
	/**
	 * 创建客户
	 * @return
	 */
	/*public String createVip(){
//		this.tradeInfo.setCardId(this.getPageParameter("card_id"));
		
		VipManageService vipManageService = SpringAppContext.getBean("vipManageService");
		vipManageService.createVip(tradeInfo);
		Map<String,Object> retObj = new HashMap<String, Object>();
		retObj.put("success", true);
		this.putRetJsonVaule(retObj);
		return this.jsonResult;
	}*/
	
	@Override
	public void beforeSubmit() {
		String tradeId = this.tradeService.getTradeIdSeq();//tradeId
		String userId = this.tradeService.getUserIdSeq();//userId
		
		TradeUser tradeUser = this.tradeInfo.getTradeUser();
		tradeUser.setTradeId(tradeId);
		tradeUser.setUserId(userId);
		tradeUser.setmUserId(this.getMySession().getUserId());
		tradeUser.setBirthday(new Date());
		tradeUser.setUserTypeCode("0");
		
		TradePay tradePay = this.tradeInfo.getTradePay();
		tradePay.setTradeId(tradeId);
		tradePay.setModifyTag("0");
		tradePay.setOldFee(tradePay.getFee());
		
		this.tradeInfo.setTradeTypeCode("10");//设置业务类型
		this.tradeInfo.setTradeId(tradeId);//设置tradeId
		this.tradeInfo.setUserId(userId);//设置userId
		this.tradeInfo.setCardId(tradeInfo.getTradeUser().getCardId());//卡号
		this.tradeInfo.setmUserId(this.getMySession().getUserId());//muserId
	}

	public String createVipIndex(){
		return "success";
	}
}
