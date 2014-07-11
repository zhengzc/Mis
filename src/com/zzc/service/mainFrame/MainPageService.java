package com.zzc.service.mainFrame;

import java.util.List;
import java.util.Map;

public interface MainPageService {
	/**
	 * 根据父节点编号和roleId获取 父节点下的子节点
	 * @param roleId
	 * @param parentId
	 * @return
	 */
	public List<Map<String,String>> queryLeftMenuByPId(String roleId,String parentId);
	/**
	 * 获取左侧菜单  根据role_id  获取的是一个完整的菜单
	 * @param role_id
	 * @return
	 */
	public List<Map<String,Object>> queryLeftMenu(String role_id);
	/**
	 * 验证用户名密码
	 * @param userId
	 * @param password
	 * @return
	 */
	public Map<String,String> queryUserByNameAndPassword(String userId,String password);
	/**
	 * 根据角色id获取权限
	 * @param role_id
	 * @return
	 */
	public List<Map<String,String>> queryUserPermByRoid(String role_id);
}
