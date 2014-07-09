package cn.aozhi.app.persistence.dao.user_role;

import java.util.List;

import cn.aozhi.app.domain.user_role.UserRole;
import cn.aozhi.app.persistence.dao.common.CommonDao;

public interface UserRoleDao extends CommonDao<UserRole>{
	/**
	 * 批量删除
	 * @param sqlId
	 * @param list
	 * @return
	 */
	public int batchDelete(String sqlId, List<String> list);
	
	/**
	 * 根据userId获取
	 * @param sqlId
	 * @param userId
	 * @return
	 */
	public List<UserRole> selectByUserId(String sqlId,String userId);
}
