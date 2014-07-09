package cn.aozhi.app.persistence.dao.role_menu;

import java.util.List;

import cn.aozhi.app.domain.role_menu.RoleMenu;
import cn.aozhi.app.persistence.dao.common.CommonDao;

public interface RoleMenuDao extends CommonDao<RoleMenu>{
	/**
	 * 批量删除
	 * @param sqlId
	 * @param list
	 * @return
	 */
	public int batchDelete(String sqlId, List<String> list);
	
	
	
	/**
	 * 批量查询
	 * @param sqlId
	 * @param list
	 * @return
	 */
	public List<RoleMenu> batchSelect(String sqlId, List<String> list);
	
	/**
	 * 根据roleId获取
	 * @param sqlId
	 * @param userId
	 * @return
	 */
	public List<RoleMenu> selectByRoleId(String sqlId,String roleId);
}
