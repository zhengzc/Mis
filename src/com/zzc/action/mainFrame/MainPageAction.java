package com.zzc.action.mainFrame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.zzc.core.action.MyBaseAction;
import com.zzc.entity.AjaxResponse;
import com.zzc.entity.MySession;
import com.zzc.service.mainFrame.MainPageService;

public class MainPageAction extends MyBaseAction {
	private MainPageService mainPageService;
	//以下是页面参数
	private String userName;
	private String password;
	private String userId;
	private String userRoleName;
	
	/**
	 * 主页面
	 * @return
	 */
	public String indexPage(){
		//设置会话信息
		MySession mySession = this.getMySession();
		if(null == mySession.getUserName()){//用户没有登录
			this.setUserName("");
		}else {//用户已登录
			this.setUserName(mySession.getUserName());
			this.setUserRoleName(mySession.getUserRoleName());
		}
		log.debug(this.getUserName());
		
		return "success";
	}
	
	/**
	 * 左侧树形菜单
	 */
	public String leftTreeMenu(){
		List<Map<String,Object>> retList = new ArrayList<Map<String,Object>>();
		if(null != this.getMySession().getUserId()){
			try{
				List<Map<String,String>> treeList = this.mainPageService.queryLeftMenuByPId(this.getMySession().getUserRoleId(),this.getPageParameter("menuId"));
				for(Map<String,String> map:treeList){
					if(Boolean.valueOf(map.get("leaf"))){//如果为叶子节点
						Map<String,Object> temp = new HashMap<String, Object>();
						temp.put("id", map.get("menu_id"));
						temp.put("leaf",true);
						temp.put("text", map.get("menu_name"));
						temp.put("url",map.get("path"));
						temp.put("iconCls", map.get("icon"));
						temp.put("controller", map.get("controller"));
						retList.add(temp);
					}else{//如果为非叶子节点
						Map<String,Object> temp = new HashMap<String, Object>();
						temp.put("id", map.get("menu_id"));
						temp.put("expanded",true);
						temp.put("text", map.get("menu_name"));
						temp.put("iconCls", map.get("icon"));
	//					temp.put("url",map.get("path"));
						retList.add(temp);
					}
				}
			}catch (Exception e) {
				log.error("获取左侧菜单失败！", e);
			}
		}
		this.putRetJsonVaule(retList);
		return this.jsonResult;
	}
	/**
	 *判断是否登录 
	 */
	public void isLogin(){
		String url = null;
		url = this.getPageParameter("url");
		MySession mySession = this.getMySession();
		//返回对象
		AjaxResponse response = new AjaxResponse("isLogin");
//		response.setOptMethod("isLogin");//设置回调方法
//		Map<String,String> retParam = new HashMap<String, String>();
		String retUrl;
		if(mySession.getUserName() != null){//用户已登录
			retUrl = url;
		}else {//用户未登录
			retUrl = "";
		}
		response.putRetValue("url", retUrl);
//		response.setRetMap(retParam);
		
		this.returnAjaxResponse(response);
		
	}
	
	public MainPageService getMainPageService() {
		return mainPageService;
	}

	public void setMainPageService(MainPageService mainPageService) {
		this.mainPageService = mainPageService;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Logger getLog() {
		return log;
	}

	public void setLog(Logger log) {
		this.log = log;
	}
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserRoleName() {
		return userRoleName;
	}

	public void setUserRoleName(String userRoleName) {
		this.userRoleName = userRoleName;
	}
}
