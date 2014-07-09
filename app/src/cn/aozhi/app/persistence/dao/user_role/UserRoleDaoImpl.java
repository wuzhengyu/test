package cn.aozhi.app.persistence.dao.user_role;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.aozhi.app.domain.user_role.UserRole;
import cn.aozhi.app.persistence.dao.common.CommonDaoImpl;

@Repository("userRoleDao")
public class UserRoleDaoImpl extends CommonDaoImpl<UserRole> implements UserRoleDao{

	@Override
	public int batchDelete(String sqlId, List<String> list) {
		return sqlSession.delete(sqlId, list);
	}

	@Override
	public List<UserRole> selectByUserId(String sqlId, String userId) {
		return sqlSession.selectList(sqlId, userId);
	}

}
