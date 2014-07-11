package com.zzc.service.VipMrg;
/**
 * 提供获取唯一索引的服务
 * @author zhengzhichao
 *
 */
public interface SequenceService {
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
}
