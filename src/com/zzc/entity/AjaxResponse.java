package com.zzc.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 用来组织Ajax返回数据，目前只支持返回json字符串格式的数据类型！
 * optMethod这个字段是一定要初始化的，这个字段关系着这个请求（json字符串）返回后，调用哪个回调方法来处理！ 和myAjax.register(flagStr,method)中的flagStr对应
 * @author ying
 *
 */
public class AjaxResponse{
//	private JSONObject jsonObject;//需要返回的数据放在这个字段里面
	private String optMethod;//标志着请求返回后，由哪个回调方法来处理
	private Map retMap;//需要返回的数据放在这个字段里面
	
	public AjaxResponse(String optMethod){
		this.optMethod = optMethod;
		this.retMap = new HashMap<String, Object>();
	}
	
	public static void main(String[] args){
		AjaxResponse res = new AjaxResponse("test");
		Map map = new HashMap();
		map.put("test2", "test2");
		map.put("test3", "test3");
		
		Map map1 = new HashMap();
		map1.put("test2", "22222");
		map1.put("test5", "test5");
		
		List list = new ArrayList();
		list.add(map);
		list.add(map1);
		
		res.setOptMethod("test");
//		res.setRetColl(list);
		res.putRetMap(map);
		res.putRetMap(map1);
//		try {
//			res.put("test1", "test2");
//			res.put("map", map);
//			res.put("list",list);
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		System.out.println(res.toString());
	}
	/**
	 * 直接设置返回值
	 * @param key
	 * @param value
	 */
	public AjaxResponse putRetValue(String key,Object value){
		this.retMap.put(key, value);
		return this;
	}
	/**
	 * 设置map返回值，此map不会覆盖原来的map，而是将他们两个相加
	 * 但是注意，新的map中如果与之前map中有重复的属性，将会被新的map所覆盖掉！
	 * @param map
	 */
	public AjaxResponse putRetMap(Map map){
		this.retMap.putAll(map);
		return this;
	}
	/**
	 * 返回json字符串
	 */
	public String toString(){
		JSONObject temp = new JSONObject();
		try {
			temp.put("optMethod", this.getOptMethod());
			temp.put("params", this.retMap);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return temp.toString();
	}
	
	/*
	public void put(String key,Collection coll){
		try {
			this.jsonObject.putOpt(key,coll);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void put(String key,String value){
		try {
			this.jsonObject.putOpt(key, value);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void put(String key,Map map){
		try {
			this.jsonObject.putOpt(key, map);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}*/
	

	public void setOptMethod(String optMethod) {
		this.optMethod = optMethod;
	}
	
	public String getOptMethod() {
		return optMethod;
	}

//	public Map getRetMessage() {
//		return retMap;
//	}
}
