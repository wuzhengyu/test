package cn.aozhi.app.persistence.dao.role_menu;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.aozhi.app.domain.role_menu.RoleMenu;
import cn.aozhi.app.persistence.dao.common.CommonDaoImpl;

@Repository("roleMenuDao")
public class RoleMenuDaoImpl extends CommonDaoImpl<RoleMenu> implements RoleMenuDao{

	@Override
	public int batchDelete(String sqlId, List<String> list) {
		return sqlSession.delete(sqlId, list);
	}

	@Override
	public List<RoleMenu> selectByRoleId(String sqlId, String roleId) {
		return sqlSession.selectList(sqlId, roleId);
	}

	@Override
	public List<RoleMenu> batchSelect(String sqlId, List<String> list) {
		return sqlSession.selectList(sqlId, list);
	}

}
