package com.zzc.entity.VipMrg;

import java.util.Date;

public class BaseTrade {
	private String tradeId;
	private String tradeTypeCode;
	private String userId;
	private String cardId;
	private String partId;
	private Date acceptDate;
	private String remark;
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
	public String getMuser_id() {
		return muser_id;
	}
	public void setMuser_id(String muserId) {
		muser_id = muserId;
	}
	private String muser_id;
}
