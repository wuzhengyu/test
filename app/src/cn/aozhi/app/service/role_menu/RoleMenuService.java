package cn.aozhi.app.service.role_menu;

import java.util.List;

import cn.aozhi.app.domain.role.Role;
import cn.aozhi.app.domain.role_menu.RoleMenu;
import cn.aozhi.app.domain.user_role.UserRole;
import cn.aozhi.app.service.common.CommonService;

public interface RoleMenuService extends CommonService<RoleMenu>{
	/**
	 * 批量删除
	 * @param sqlId
	 * @param list
	 * @return
	 */
	public int batchDelete(String sqlId,List<String> list);
	
	
	/**
	 * 批量查询
	 * @param sqlId
	 * @param list
	 * @return
	 */
	public List<RoleMenu> batchSelect(String sqlId,List<String> list);
	
	/**
	 * 根据roleId查询
	 * @param sqlId
	 * @param userId
	 * @return
	 */
	List<RoleMenu> selectByRoleId(String sqlId,String roleId);
	
	
	/**
	 * 删除并保存
	 * @param roleIds
	 * @param list
	 * @param user
	 * @return
	 */
	boolean setRelations(String[] menuIds,List<RoleMenu> list,Role role);
		
}
