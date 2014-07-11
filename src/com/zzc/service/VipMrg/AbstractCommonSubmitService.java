package com.zzc.service.VipMrg;

import com.zzc.core.Exception.ZzcRollbackException;
import com.zzc.core.serivce.MyBaseService;
import com.zzc.dao.VipMrg.TradeInfoMapper;
import com.zzc.dao.VipMrg.TradePayMapper;
import com.zzc.dao.VipMrg.TradeUserMapper;
import com.zzc.dao.VipMrg.UserInfoMapper;
import com.zzc.entity.VipMrg.TradeInfo;

/**
 * 将comonService作为所有service的父类，由action统一调用，
 * 将写订单的逻辑统一在此处完成，生成实例则由子类重写来完成！
 * 
 * 为了提供一个通用的方法，供前台统一调用
 * @author ying
 *
 */
public abstract class AbstractCommonSubmitService extends MyBaseService implements CommonSubmitService{
	public TradeInfoMapper tradeInfoMapper = this.getService(TradeInfoMapper.class);
	public TradeUserMapper tradeUserMapper = this.getService(TradeUserMapper.class);
	public TradePayMapper tradePayMapper = this.getService(TradePayMapper.class);
	public UserInfoMapper userInfoMapper = this.getService(UserInfoMapper.class);
	/**
	 * 通用提交方法，前台统一调用此方法
	 * @param tradeInfo
	 */
	@Override
	public void submit(TradeInfo tradeInfo){
		log.info("-------->commonSubmintService submit start!");
		try {
			this.generateOrder(tradeInfo);//先生成订单
			this.dealOrder(tradeInfo);//然后再提交订单 沉淀实例
		} catch (Exception e) {
			throw new ZzcRollbackException(e.getMessage(),e);
		}
		log.info("-------->commonSubmintService submit end!");
	}
	
	/**
	 * 生成订单  此方法根据tradeInfo信息生成订单！
	 * 目前所有的业务都是相同的不做任何特殊处理！
	 * @param tradeInfo
	 */
	public void generateOrder(TradeInfo tradeInfo){
		log.info("-------->commonSubmintService gerateOrder start!");
		
		//写tf_b_trade
		this.tradeInfoMapper.insertTradeInfo(tradeInfo);
		//写tf_b_trade_user
		if(null != tradeInfo.getTradeUser()){
			this.tradeUserMapper.insertTradeUser(tradeInfo.getTradeUser());
		}
		//写tf_b_trade_pay
		if(null != tradeInfo.getTradePay()){
			this.tradePayMapper.insertTradePay(tradeInfo.getTradePay());
		}
		
//		tradeUserMapper.insertTradeUser(tradeUser);
		log.info("-------->commonSubmintService generateOrder end!");
	}
	
	/**
	 * 处理订单
	 * 此方法用来调用子类的真正用来生成实例的方法
	 * @param tradeInfo
	 */
	private final void dealOrder(TradeInfo tradeInfo){
		log.info("-------->commonSubmintService dealOrder start!");
//		String serviceName = tradeInfo.getServiceName();//获取服务名称
//		CommonSubmitService commonService = this.getBean(serviceName);
		this.execute(tradeInfo);
		log.info("-------->commonSubmintService dealOrder end!");
	}
	
	/**
	 * 交给子类来实现
	 * @param tradeInfo
	 */
	@Override
	public abstract void execute(TradeInfo tradeInfo);
}
