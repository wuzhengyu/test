package cn.aozhi.app.controller.login;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.aozhi.app.domain.menu.Menu;
import cn.aozhi.app.domain.role_menu.RoleMenu;
import cn.aozhi.app.domain.user.User;
import cn.aozhi.app.domain.user_role.UserRole;
import cn.aozhi.app.service.menu.MenuService;
import cn.aozhi.app.service.role_menu.RoleMenuService;
import cn.aozhi.app.service.user_role.UserRoleService;

@Controller
public class IndexController {
	
	@Autowired
	private MenuService menuService;
	
	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
	private RoleMenuService roleMenuService;
	
	@RequestMapping("/index")
	public ModelAndView index(HttpServletRequest request){
		
		ModelAndView result = new ModelAndView("/home_");
		try{
			User user = (User) request.getSession().getAttribute("user");
			//获取用户所属角色
			List<UserRole> userRoleList = userRoleService.selectByUserId("UserRole.selectByUserId", user.getId());
			List<String> roleIds = new ArrayList<String>();
			List<String> menuIds = new ArrayList<String>();
			
			if(userRoleList.size()>0 && userRoleList!=null){
				for(UserRole ur:userRoleList){
					roleIds.add(ur.getRoleId());
				}
			}
			
			//获取角色的菜单资源
			List<RoleMenu> roleMenuList = roleMenuService.batchSelect("RoleMenu.batchSelect", roleIds);
			if(roleMenuList.size()>0 && roleMenuList!=null){
				for(RoleMenu rm:roleMenuList){
					menuIds.add(rm.getMenuId());
				}
			}
			
			List<Menu> menuList = menuService.batchSelect("Menu.batchSelect", menuIds);
			result.addObject("user", user);
			result.addObject("menuList", menuList);
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
}
