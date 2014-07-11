package com.zzc.service.VipMrg;

import com.zzc.entity.VipMrg.TradeInfo;

/**
 * 如果想要使用spring aop（CGLIB的代理方式），必需要有接口支持，否则运行时候会报错！
 * 
 * 可以想象，如果接口中没用定义的方法是否能够被aop拦截到，这个有待落实哦
 * @author zhengzhichao
 *
 */
public interface CommonSubmitService {
	/**
	 * 通用提交方法，前台统一调用此方法
	 * @param tradeInfo
	 */
	public void submit(TradeInfo tradeInfo);
	
	/**
	 * 真正的生成用户数据方法
	 * @param tradeInfo
	 */
	public void execute(TradeInfo tradeInfo);
}
