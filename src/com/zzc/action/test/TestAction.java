package com.zzc.action.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.zzc.core.action.MyBaseAction;
import com.zzc.entity.AjaxResponse;
import com.zzc.service.test.TestService;

public class TestAction extends MyBaseAction {
	private TestService testService;

	public String test1(){
		return "success";
	}
	
	public void testLogin(){
		String userId = this.getPageParameter("userId");
		String password = this.getPageParameter("password");
		log.debug("userId:"+userId+" password:"+password);
		
		Map<String,String> retMap = this.testService.testLogin(userId, password);
		
		AjaxResponse ret = new AjaxResponse("callBack");
//		ret.putRetValue("user_id",userId);
//		ret.putRetValue("password",password);
		ret.putRetMap(retMap);
		this.returnAjaxResponse(ret);
	}
	
	public void testMvcRead(){
		this.returnAjaxResponse("{success: true,users: [{id: 1, name: 'Ed',    email: 'ed@sencha.com'},{id: 2, name: 'Tommy', email: 'tommy@sencha.com'}]}");
	}
	
	public String testMvcUpdate(){
		log.debug(this.getPageParameter("_dc"));
		log.debug(this.getPageParameter("users"));
		log.debug(this.getPageParameter("id"));
		log.debug(this.getPageParameter("email"));
		try {
			if("".equals(this.getPageParameter("users"))){
				JSONObject json = new JSONObject(this.getPageParameter("users"));
				log.debug(json.toString());
			}
			
//			JSONObject ret = new JSONObject("{failure: true,users: []}");
			Map<String,Object> ret = new HashMap<String, Object>();
			ret.put("success", false);
			ret.put("msg", "update failure！");
			this.putRetJsonVaule(ret);
		} catch (JSONException e) {
			e.printStackTrace();
		}
//		this.returnAjaxResponse("{success: true,users: []}");
//		this.putRetJsonVaule("{success: true,users: []}");
//		this.putRetJsonVaule(new JSONObject("{failure: true,users: []}"));
//		this.putRetJsonVaule("{success: false,users: []}");
		return this.jsonResult;
	}
	
	public String testTreeStr(){
		List<Object> menuList = new ArrayList<Object>();
		if("-1".equals(this.getPageParameter("menuId"))){
			Map<String,Object> retMap = new HashMap<String, Object>();
			retMap.put("text", "总公司");
			retMap.put("expanded", true);
			retMap.put("id", "0");
			menuList.add(retMap);
		}else if("0".equals(this.getPageParameter("menuId"))) {
			Map<String,Object> retMap = new HashMap<String, Object>();
			retMap.put("text", "部门1");
			retMap.put("leaf", true);
			retMap.put("id", "1");
			menuList.add(retMap);
		}else{
			
		}
		this.putRetJsonVaule(menuList);
		return this.jsonResult;
	}

	public TestService getTestService() {
		return testService;
	}

	public void setTestService(TestService testService) {
		this.testService = testService;
	}
}
