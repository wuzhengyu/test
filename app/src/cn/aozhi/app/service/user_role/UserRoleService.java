package cn.aozhi.app.service.user_role;

import java.util.List;

import cn.aozhi.app.domain.user.User;
import cn.aozhi.app.domain.user_role.UserRole;
import cn.aozhi.app.service.common.CommonService;

public interface UserRoleService extends CommonService<UserRole>{
	/**
	 * 批量删除
	 * @param sqlId
	 * @param list
	 * @return
	 */
	public int batchDelete(String sqlId,List<String> list);
	
	/**
	 * 根据userId查询
	 * @param sqlId
	 * @param userId
	 * @return
	 */
	List<UserRole> selectByUserId(String sqlId,String userId);
	
	
	/**
	 * 删除并保存
	 * @param roleIds
	 * @param list
	 * @param user
	 * @return
	 */
	boolean setRelations(String[] roleIds,List<UserRole> list,User user);
		
}
