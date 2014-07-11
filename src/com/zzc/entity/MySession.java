package com.zzc.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class MySession implements Serializable {
	private String userId;//用户id
	private String userName;//用户姓名
	private String userRoleId;//用户角色id
	private String userRoleName;//用户角色名称
	private List<Map<String,String>> userPermission;//用户权限列表
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserRoleId() {
		return userRoleId;
	}
	public void setUserRoleId(String userRoleId) {
		this.userRoleId = userRoleId;
	}
	public String getUserRoleName() {
		return userRoleName;
	}
	public void setUserRoleName(String userRoleName) {
		this.userRoleName = userRoleName;
	}
	public List<Map<String, String>> getUserPermission() {
		return userPermission;
	}
	public void setUserPermission(List<Map<String, String>> userPermission) {
		this.userPermission = userPermission;
	}

}
