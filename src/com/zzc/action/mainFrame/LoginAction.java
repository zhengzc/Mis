package com.zzc.action.mainFrame;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zzc.core.action.MyBaseAction;
import com.zzc.entity.MySession;
import com.zzc.service.mainFrame.MainPageService;

public class LoginAction extends MyBaseAction{
	//获取要调用的服务
	MainPageService mainPageService = this.getService("mainPageService");
	
	public String login(){
		String userId = this.getPageParameter("user_id");
		String userPassword = this.getPageParameter("user_password");
		
		Map<String,Object> response = new HashMap<String, Object>();
		Map<String, String> mUser = null;
		//数据库验证用户名密码
		try{
			mUser = mainPageService.queryUserByNameAndPassword(userId, userPassword);
		}catch (Exception e) {
			log.error("验证用户名密码失败！",e);
		}
		if(mUser == null){//没有查询到用户
			response.put("success", false);
			response.put("errors", "用户不存在或者密码不正确！");
		}else{
			//用户名密码正确，写入session
			MySession session = this.getMySession();
			session.setUserId(mUser.get("user_id"));
			session.setUserName(mUser.get("user_name"));
			session.setUserRoleId(mUser.get("role_id"));
			session.setUserRoleName(mUser.get("role_name"));
			//获取用户权限列表
			List<Map<String,String>> userPermList = mainPageService.queryUserPermByRoid(session.getUserRoleId());
			session.setUserPermission(userPermList);
			this.setMySession(session);
			
			response.put("success", true);
		}
		
		this.putRetJsonVaule(response);
		return this.jsonResult;
	}
	
	/**
	 * 注销
	 * @return
	 */
	public String logout(){
		this.clearMySession();
		return "success";
	}
}
