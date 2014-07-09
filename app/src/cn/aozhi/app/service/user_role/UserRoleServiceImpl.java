package cn.aozhi.app.service.user_role;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.aozhi.app.domain.user.User;
import cn.aozhi.app.domain.user_role.UserRole;
import cn.aozhi.app.persistence.dao.user_role.UserRoleDao;
import cn.aozhi.app.service.common.CommonServiceImpl;
import cn.aozhi.app.util.CommonUtil;

@Service("userRoleService")
public class UserRoleServiceImpl extends CommonServiceImpl<UserRole> implements UserRoleService{
	
	@Autowired
	private UserRoleDao userRoleDao;

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public int batchDelete(String sqlId, List<String> list) {
		return userRoleDao.batchDelete(sqlId, list);
	}

	@Override
	public List<UserRole> selectByUserId(String sqlId, String userId) {
		return userRoleDao.selectByUserId(sqlId, userId);
	}

	@Override
	public boolean setRelations(String[] roleIds, List<UserRole> list,User user){
		boolean flag = false;
		List<String> roleIdList = new ArrayList<String>();
		for(UserRole ur:list){
			roleIdList.add(ur.getId());
		}
		if(roleIdList.size()>0){
			batchDelete("UserRole.batchDelete",roleIdList);
		}
		
		for(String roleId:roleIds){
			UserRole userRole = new UserRole();
			userRole.setId(CommonUtil.uuidGenerate());
			userRole.setUserId(user.getId());
			userRole.setRoleId(roleId);
			UserRole userR= (UserRole) save("UserRole.insert", userRole);
			if(userR!=null){
				flag = true;
			}
		}
		return flag;
	}
	
}
