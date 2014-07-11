package com.zzc.service.VipMrg;
/**
 * 一般业务通用类
 * @author zhengzhichao
 *
 */
public interface TradeService {
	/**
	 * 主要方法！
	 * 根据seqName获取对应的seq
	 * 此方法获取到的seq统一为如下规则：
	 * 所有的序列生成规则为：  年（后两位）+月份（两位）+日（两位） +6位索引序列
	 * @param seqName
	 * @return
	 */
	public String getSequence(String seqName);
	/**
	 * 获取用户id唯一索引
	 * @return 新生成的用户id
	 */
	public String getUserIdSeq();
	/**
	 * 获取订单id唯一索引
	 * @return 新生成的订单id
	 */
	public String getTradeIdSeq();
	
	/**
	 * 获取业务完工服务名称  根据业务编码 
	 * 从td_s_tradetype表中根据业务类型获取完工服务名称！此方法总是会返回一个期待的值，如果查询不到就会抛出一个运行时异常！
	 * @param tradeTypeCode
	 * @return
	 */
	public String getTradeFinshSerivceName(String tradeTypeCode);
}
