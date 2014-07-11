package com.zzc.entity.VipMrg;

public class TradePay {
	private String tradeId;
	private String modifyTag;
	private int oldFee;
	private int fee;
	public String getTradeId() {
		return tradeId;
	}
	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}
	public String getModifyTag() {
		return modifyTag;
	}
	public void setModifyTag(String modifyTag) {
		this.modifyTag = modifyTag;
	}
	public int getOldFee() {
		return oldFee;
	}
	public void setOldFee(int oldFee) {
		this.oldFee = oldFee;
	}
	public int getFee() {
		return fee;
	}
	public void setFee(int fee) {
		this.fee = fee;
	}
}
