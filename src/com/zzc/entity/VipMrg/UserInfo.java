package com.zzc.entity.VipMrg;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

public class UserInfo {
	private String userId;
	private String mUserId;
	private String cardId;
	private String userName;
	private String serialNumber;
	private String address;
	private String school;
	private Date birthday;
	private int currentFee;
	private int sumFee;
	private Date lastCostDate;
	private String cardType;
	private Date cardStartDate;
	private Date cardEndDate;
	private String userTypeCode;
	private Date createDate;
	
	//添加分页参数
	private int start;
	private int limit;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getmUserId() {
		return mUserId;
	}
	public void setmUserId(String mUserId) {
		this.mUserId = mUserId;
	}
	public String getCardId() {
		return cardId;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	@JSON(format="yyyy-MM-dd HH:mm:ss")
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public int getCurrentFee() {
		return currentFee;
	}
	public void setCurrentFee(int currentFee) {
		this.currentFee = currentFee;
	}
	public int getSumFee() {
		return sumFee;
	}
	public void setSumFee(int sumFee) {
		this.sumFee = sumFee;
	}
	@JSON(format="yyyy-MM-dd HH:mm:ss")
	public Date getLastCostDate() {
		return lastCostDate;
	}
	public void setLastCostDate(Date lastCostDate) {
		this.lastCostDate = lastCostDate;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	@JSON(format="yyyy-MM-dd HH:mm:ss")
	public Date getCardStartDate() {
		return cardStartDate;
	}
	public void setCardStartDate(Date cardStartDate) {
		this.cardStartDate = cardStartDate;
	}
	
	@JSON(format="yyyy-MM-dd HH:mm:ss")
	public Date getCardEndDate() {
		return cardEndDate;
	}
	public void setCardEndDate(Date cardEndDate) {
		this.cardEndDate = cardEndDate;
	}
	public String getUserTypeCode() {
		return userTypeCode;
	}
	public void setUserTypeCode(String userTypeCode) {
		this.userTypeCode = userTypeCode;
	}
	@JSON(format="yyyy-MM-dd HH:mm:ss")
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
}
