package cn.aozhi.app.service.role_menu;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.aozhi.app.domain.role.Role;
import cn.aozhi.app.domain.role_menu.RoleMenu;
import cn.aozhi.app.persistence.dao.role_menu.RoleMenuDao;
import cn.aozhi.app.service.common.CommonServiceImpl;
import cn.aozhi.app.util.CommonUtil;

@Service("roleMenuService")
public class RoleMenuServiceImpl extends CommonServiceImpl<RoleMenu> implements RoleMenuService{

	@Autowired
	private RoleMenuDao roleMenuDao;
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public int batchDelete(String sqlId, List<String> list) {
		return roleMenuDao.batchDelete(sqlId, list);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public List<RoleMenu> selectByRoleId(String sqlId, String roleId) {
		return roleMenuDao.selectByRoleId(sqlId, roleId);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean setRelations(String[] menuIds, List<RoleMenu> list,Role role){
		boolean flag = false;
		List<String> menuIdList = new ArrayList<String>();
		for(RoleMenu rm:list){
			menuIdList.add(rm.getId());
		}
		if(menuIdList.size()>0){
			batchDelete("RoleMenu.batchDelete",menuIdList);
		}
		
		for(String menuId:menuIds){
			RoleMenu roleMenu = new RoleMenu();
			roleMenu.setId(CommonUtil.uuidGenerate());
			roleMenu.setRoleId(role.getId());
			roleMenu.setMenuId(menuId);
			RoleMenu roleM= (RoleMenu) save("RoleMenu.insert", roleMenu);
			if(roleM!=null){
				flag = true;
			}
		}
		return flag;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public List<RoleMenu> batchSelect(String sqlId, List<String> list) {
		return roleMenuDao.batchSelect(sqlId, list);
	}

}
