package com.zzc.filter.Interceptor;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.zzc.core.dao.SqlDao;
import com.zzc.entity.MySession;

public class LoginInterceptor extends AbstractInterceptor {
	private SqlDao sqlDao;

	@Override
	public String intercept(ActionInvocation actionInvocation) throws Exception {
		//当前请求
		HttpServletRequest request = (HttpServletRequest)ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		//获取session
		Object mySession = request.getSession().getAttribute("mySession");
		//当前请求类名称
//		String currentActionName = actionInvocation.getProxy().getActionName();
		String currentClassName = actionInvocation.getProxy().getAction().getClass().getName();
		//当前请求方法名称
		String currentMethod = actionInvocation.getProxy().getMethod();
		
		//获取无需过滤的权限
		List<Map<String,String>> unfilterPerm;//声明
		if(ActionContext.getContext().getApplication().containsKey("unfilterPerm")){//在Application级别搜寻是否已存在
			unfilterPerm = (List<Map<String,String>>)ActionContext.getContext().getApplication().get("unfilterPerm");
		}else{//如果不存在
			unfilterPerm = this.sqlDao.queryForStrList("td_m_permission", "qry_perm_nofilter");//从数据库获取
			ActionContext.getContext().getApplication().put("unfilterPerm", unfilterPerm);//存入application上下文
		}
		
		//符合规则的不拦截
		for(Map<String,String> map:unfilterPerm){
			if(currentClassName.equals(map.get("class_name"))
				&& (currentMethod.equals(map.get("action_method"))
						|| "*".equals(map.get("action_method")))
			   ){//判断调用actionname和method是否一致，配置上支持method使用通配符*
				return actionInvocation.invoke();
			}
		}
		
		
		//获取用户拥有权限
		if(mySession != null){
			MySession session = (MySession)mySession;
			//用户权限
			List<Map<String, String>> userPermission = session.getUserPermission();
			
			//用户已登录 并且拥有权限不拦截
			if(!"".equals(session.getUserId()) && null!=session.getUserId()){//判断是否登录
				//超级管理员不判断权限
				if("-1".equals(session.getUserRoleId())){
					return actionInvocation.invoke();
				}
				//非超级管理员判断当前权限
				for(Map<String,String> map:userPermission){
					if(currentClassName.equals(map.get("class_name"))
						&& (currentMethod.equals(map.get("action_method"))
								|| "*".equals(map.get("action_method")))
					   ){//判断调用actionname和method是否一致，配置上支持method使用通配符*
						return actionInvocation.invoke();
					}
				}
			}
		}
		
		/*//获取不拦截的方法
		String unFilterUrl = commparaDao.queryParaValueByCode("un_filter_method");
		String[] urlArray = unFilterUrl.split("\\|");
		//当前请求的方法
		String invokeUrl = actionInvocation.getProxy().getNamespace()+"/"+actionInvocation.getProxy().getActionName();
		//匹配处理
		for(int i=0;i<urlArray.length;i++){
			if(urlArray[i].equals(invokeUrl)){//匹配上  不拦截
				return actionInvocation.invoke();
			}
		}
		*/
		//走到这里 说明没有匹配上 也没有登陆  则拦截到首页
		//因为有些表单是以ajax请求发送的，此类请求使用单纯的struts这种拦截机制是不会跳转的，会导致请求被拦截但是用户却不知道的情况
		//begin
		//所以我们先判断此请求是否是ajax请求
//		if(isAjaxRequest(request)){//ajax请求
//			MyBaseAction myBaseAction = (MyBaseAction)actionInvocation.getAction();
//			myBaseAction.setIsAjaxReq(true);//设置ajax请求标志位
//			return actionInvocation.invoke();//不拦截，由ajax请求返回的时候拦截
//		}
		//end
		return "global_error_page";
	}
	/**
	 * 判断是否为ajax请求，本系统前台是jquery发送的ajax请求，所以能使用以下方法判断是否为ajax请求！
	 * @param request
	 * @return
	 */
    private boolean isAjaxRequest(HttpServletRequest request) {  
        String header = request.getHeader("X-Requested-With");  
        if (header != null && "XMLHttpRequest".equals(header))  
            return true;  
        else  
            return false;  
    }  

	public SqlDao getSqlDao() {
		return sqlDao;
	}
	public void setSqlDao(SqlDao sqlDao) {
		this.sqlDao = sqlDao;
	}

}
