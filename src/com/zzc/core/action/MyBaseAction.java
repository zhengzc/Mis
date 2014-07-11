package com.zzc.core.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.zzc.core.SpringAppContext;
import com.zzc.entity.AjaxResponse;
import com.zzc.entity.MySession;

/**
 * 所有Action应该继承的类，目前封装了处理Ajax请求的一些方法！
 * @author ying
 *
 */
public class MyBaseAction {
	public final String jsonResult = "jsonResult";
	public Logger log = Logger.getRootLogger();//日志
	private ActionContext actionContext= ActionContext.getContext();//actionContext
	private HttpServletRequest myRequest = (HttpServletRequest)this.actionContext.get(ServletActionContext.HTTP_REQUEST);
	private HttpServletResponse myResponse = (HttpServletResponse)this.actionContext.get(ServletActionContext.HTTP_RESPONSE);
	private MySession mySession;
//	private Boolean isAjaxReq = false;
	/**
	 * 当前请求上下文信息
	 */
	public HttpServletRequest getMyRequest() {
		return this.myRequest;
	}
	/**
	 * 获取当前请求中传入参数
	 * @param name
	 * @return
	 */
	public String getPageParameter(String name){
		return this.myRequest.getParameter(name);
	}

	public ActionContext getActionContext() {
		return actionContext;
	}
	
	public void setMySession(MySession mySession){
		this.myRequest.getSession().setAttribute("mySession", mySession);
	}

	/**
	 * 如果MySession存在，直接返回，如果不存在，创建一个新的返回
	 * @return
	 */
	public MySession getMySession() {
		Object mySession = this.myRequest.getSession().getAttribute("mySession");
		if(null == mySession){
			MySession mySess = new MySession();
			this.setMySession(mySess);
			return mySess;
		}
		return (MySession)mySession;
	}
	/**
	 * 移除MySession
	 */
	public void clearMySession(){
		this.myRequest.getSession().removeAttribute("mySession");
	}

	public HttpServletResponse getMyResponse() {
		return this.myResponse;
	}
	
	/**
	 * 根据beanId直接获取相应的服务！
	 * 使用泛型完成自动转换
	 * @param beanId
	 * @return
	 */
	public <T>T getService(String beanId){
		return (T)SpringAppContext.getBean(beanId);
	}
	
	/**
     * 返回JSON
     * 本来这里的参数是Object类型的，但是经测试，直接写入String类型时候（json字符串），前台并不能自动转化为key value形式，会导致extjs无法获取
     * @param map
     */
    public void putRetJsonVaule(Map map) {
//    	JSONObject jsonObject = new net.sf.json.JSONObject();
//    	jsonObject.putAll(map);
        ServletActionContext.getContext().getValueStack().set("CON_JSON_RESULT", map);
    }
    
    public void putRetJsonVaule(List list) {
//    	JSONArray jsonArray = new JSONArray();
//    	jsonArray.addAll(list);
        ServletActionContext.getContext().getValueStack().set("CON_JSON_RESULT", list);
    }
    
    public void putRetJsonVaule(String str) {
//    	JSONArray jsonArray = new JSONArray();
//    	jsonArray.addAll(list);
        ServletActionContext.getContext().getValueStack().set("CON_JSON_RESULT", str);
    }
    /**
     * 用来传递普通的对象
     * @param obj
     */
    public void putRetJsonVaule(Object obj){
        ServletActionContext.getContext().getValueStack().set("CON_JSON_RESULT", obj);
    }
	
	/**
	 * 返回ajax请求，参数为字符串型的！
	 * @param response
	 */
	public void returnAjaxResponse(String response){
		try {
			PrintWriter writer = this.myResponse.getWriter();
			log.debug(response.toString());
			writer.print(response);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			log.error(e);
			e.printStackTrace();
		}
	}
	
	/**
	 * 返回ajax请求，返回数据被组织成json字符串  前台可直接调用 response.getresponseText 方法获得！
	 * 参数类型为 AjaxResponse  关于这个类型请看这个类的说明！
	 * @param response
	 */
	public void returnAjaxResponse(AjaxResponse response){
		try {
//			if(!isAjaxReq){
				PrintWriter writer = this.myResponse.getWriter();
				log.debug(response.toString());
				writer.print(response.toString());
				writer.flush();
				writer.close();
//			}else{
//				this.myResponse.sendRedirect("/global_error_page.jsp");
//			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

//	public Boolean getIsAjaxReq() {
//		return isAjaxReq;
//	}
//
//	public void setIsAjaxReq(Boolean isAjaxReq) {
//		this.isAjaxReq = isAjaxReq;
//	}
}

