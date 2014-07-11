package com.zzc.service.mainFrame.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.zzc.core.serivce.MyBaseService;
import com.zzc.dao.MUserDao;
import com.zzc.service.mainFrame.MainPageService;

public class MainPageServiceImpl extends MyBaseService implements MainPageService {
	private MUserDao manageUserDao;

	@Override
	public List<Map<String, String>> queryLeftMenuByPId(String roleId,
			String parentId) {
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("role_id", roleId);
		param.put("parent_id", parentId);
		if("-1".equals(roleId)){
			//param.put("parent_id", parentId);
			return this.getSqlDao().queryForStrList("td_m_menu", "qry_allmenu",param);
		}else{
			return this.getSqlDao().queryForStrList("td_m_menu", "qry_menu_by_pid_and_roleid",param);
		}
	}

	@Override
	public List<Map<String, Object>> queryLeftMenu(String role_id) {
		List<Map<String, String>> retList;
		if("-1".equals(role_id)){
			retList = this.getSqlDao().queryForStrList("td_m_menu", "qry_allmenu");
		}else{
			Map<String,Object> param = new HashMap<String, Object>();
			param.put("role_id", Integer.valueOf(role_id));
			retList = this.getSqlDao().queryForStrList("td_m_menu", "qry_leftmenu_by_roleid",param);
		}
		List<Map<String, Object>> treeList = new ArrayList<Map<String, Object>>();
		//构造树形结构菜单 
		if(!retList.isEmpty()){
			//先来转化一下类型 把 HashMap<String,String>转化为HashMap<String,Object>
			Iterator<Map<String, String>> it = retList.iterator();
			List<Map<String, Object>> tempList = new ArrayList<Map<String,Object>>();
			while(it.hasNext()){
				Map<String, String> map = it.next();
				Map<String, Object> tempMap = new HashMap<String, Object>();
				tempMap.putAll(map);
				tempList.add(tempMap);
			}
			//构造树形结构
			Iterator<Map<String, Object>> it2 = tempList.iterator();
			while(it2.hasNext()){
				Map<String, Object> map = it2.next();
				if("-1".equals(String.valueOf(map.get("parent_id")))){//根节点
//					tempList.remove(map);//删除
					it2.remove();//采用这种方法删除才是安全有效的   考虑到Collection类在使用Iterator遍历时候锁的问题
					//添加之前处理节点为前台esayui Tree结构能够识别的类型
					change2easyuiMap(map);
					treeList.add(parseTree(map, tempList));//添加
				}
			}
		}
		log.debug("treeList----->"+treeList.toString());
		return treeList;
	}
	
	@Override
	public Map<String, String> queryUserByNameAndPassword(String userId,
			String password) {
		return this.manageUserDao.queryUserByNameAndPassword(userId, password);
	}
	@Override
	public List<Map<String, String>> queryUserPermByRoid(String roleId) {
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("role_id", Integer.valueOf(roleId));
		return this.getSqlDao().queryForStrList("td_m_permission", "qry_perm_by_roleid",param);
	}

	/**
	 * 获取某个节点下的所有子节点
	 * @param fatherNode
	 * @param originalList
	 * @return
	 */
	private Map<String, Object> parseTree(Map<String,Object> fatherNode,List<Map<String,Object>> originalList){
		List<Map<String, Object>> childrenList = new ArrayList<Map<String,Object>>();//子节点列表
		if(!fatherNode.isEmpty() && !originalList.isEmpty()){//参数不为空
			Iterator<Map<String, Object>> it = originalList.iterator();
			while(it.hasNext()){//遍历list 寻找子节点 存入childrenList
				Map<String, Object> map = it.next();
				if(String.valueOf(map.get("parent_id")).equals(String.valueOf(fatherNode.get("id")))){//找到子节点
//					it.remove();//移除，采用这种方法才是安全有效的，考虑的Collection类在使用Iterator遍历时候锁的问题
					//添加子节点之前处理节点为easyui中tree的指定数据类型
					change2easyuiMap(map);
					
					childrenList.add(map);//添加到子节点中
				}
			}
			
			//遍历子节点列表
			Iterator<Map<String, Object>> it2 = childrenList.iterator();
			while(it2.hasNext()){
				Map<String, Object> map = it2.next();//获取子节点
//				originalList.remove(map);//移除
				parseTree(map, originalList);//递归获取子节点的子节点
				fatherNode.put("children", childrenList);
			}
		}
		return fatherNode;
	}
	
	/**
	 * 将指定的map转化为easyui的Tree结构能够识别的格式
	 * @param inparam
	 */
	private void change2easyuiMap(Map<String, Object> inparam){
		Map<String, String> attrMap = new HashMap<String, String>();
		attrMap.put("open_page", String.valueOf(inparam.get("open_page")));
//		attrMap.put("permission", String.valueOf(inparam.get("permission")));
		attrMap.put("parent_id", String.valueOf(inparam.get("parent_id")));
		inparam.remove("open_page");
//		inparam.remove("permission");
		inparam.remove("parent_id");
		inparam.put("attributes", attrMap);
	}
	
	public MUserDao getManageUserDao() {
		return manageUserDao;
	}

	public void setManageUserDao(MUserDao manageUserDao) {
		this.manageUserDao = manageUserDao;
	}
}
