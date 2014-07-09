package cn.aozhi.app.persistence.dao.role;

import org.springframework.stereotype.Repository;

import cn.aozhi.app.domain.role.Role;
import cn.aozhi.app.persistence.dao.common.CommonDaoImpl;

@Repository("roleDao")
public class RoleDaoImpl extends CommonDaoImpl<Role> implements RoleDao{
	
}
