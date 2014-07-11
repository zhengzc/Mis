package com.zzc.dao;


public interface CommparaDao {
	/**
	 * 根据para_code获取td_s_commparam表中的paraValue字段
	 * @param paraCode
	 * @return
	 */
	public String queryParaValueByCode(String paraCode);
}
