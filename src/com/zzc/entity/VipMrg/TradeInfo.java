package com.zzc.entity.VipMrg;

import java.util.Date;

public class TradeInfo {
	private String tradeId;
	private String tradeTypeCode;
	private String userId;
	private String cardId;
	private String partId;
	private Date acceptDate;
	private String remark;
	private String mUserId;
	
	private TradePay tradePay;
	private TradeUser tradeUser;
	
	public String getTradeId() {
		return tradeId;
	}
	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}
	public String getTradeTypeCode() {
		return tradeTypeCode;
	}
	public void setTradeTypeCode(String tradeTypeCode) {
		this.tradeTypeCode = tradeTypeCode;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getCardId() {
		return cardId;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	public String getPartId() {
		return partId;
	}
	public void setPartId(String partId) {
		this.partId = partId;
	}
	public Date getAcceptDate() {
		return acceptDate;
	}
	public void setAcceptDate(Date acceptDate) {
		this.acceptDate = acceptDate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public TradePay getTradePay() {
		return tradePay;
	}
	public void setTradePay(TradePay tradePay) {
		this.tradePay = tradePay;
	}
	public TradeUser getTradeUser() {
		return tradeUser;
	}
	public void setTradeUser(TradeUser tradeUser) {
		this.tradeUser = tradeUser;
	}
	public String getmUserId() {
		return mUserId;
	}
	public void setmUserId(String mUserId) {
		this.mUserId = mUserId;
	}
}
