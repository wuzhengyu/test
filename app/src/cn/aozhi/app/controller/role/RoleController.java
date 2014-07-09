package cn.aozhi.app.controller.role;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.aozhi.app.domain.menu.Menu;
import cn.aozhi.app.domain.role.Role;
import cn.aozhi.app.domain.role_menu.RoleMenu;
import cn.aozhi.app.domain.user_role.UserRole;
import cn.aozhi.app.service.menu.MenuService;
import cn.aozhi.app.service.role.RoleService;
import cn.aozhi.app.service.role_menu.RoleMenuService;
import cn.aozhi.app.util.CommonUtil;
import cn.aozhi.app.util.Page;

@Controller
@RequestMapping("/role")
public class RoleController {
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private MenuService menuService;
	
	@Autowired
	private RoleMenuService roleMenuService;
	
	@RequestMapping("/findRolePage")
	public ModelAndView findPage(HttpServletRequest request){
		ModelAndView result = new ModelAndView("role/role_list");
		try{
			int currentPage = request.getParameter("currentPage")==null?1:Integer.parseInt(request.getParameter("currentPage"));
			int pageSize = request.getParameter("pageSize")==null?10:Integer.parseInt(request.getParameter("pageSize"));
			int startIndex = CommonUtil.getStartIndex(currentPage, pageSize);
			Map<String,Object> params =CommonUtil.getPageParams(startIndex, pageSize, currentPage, "Role.findPage", "Role.findPageCount");
			Page<Role> page = roleService.getPage(currentPage, pageSize, params);
			result.addObject("page",page);
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 保存
	 * @param role
	 * @return
	 */
	@RequestMapping("/save")
	@ResponseBody
	public boolean save(Role role) {
		boolean flag = false;
		try{
			role.setId(CommonUtil.uuidGenerate());
			Role r = (Role) roleService.save("Role.insert", role);
			if(r!=null){
				flag = true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return flag;
	}
	
	
	/**
	 * 根据id删除
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public boolean delete(String id){
		boolean flag = false;
		try{
			int affect = roleService.delete("Role.delete", id);
			if(affect>0){
				flag = true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return flag;
	}
	
	
	/**
	 * 根据id获取
	 * @param id
	 * @return
	 */
	@RequestMapping("/getById")
	public ModelAndView getById(String id){
		ModelAndView result = new ModelAndView("role/role_edit");
		try{
			Role role = (Role) roleService.getById("Role.selectById",id);
			List<Menu> menuList = menuService.list("Menu.list", null);
			List<RoleMenu> roleMenuList = roleMenuService.selectByRoleId("RoleMenu.selectByRoleId", id);
			result.addObject("role",role);
			result.addObject("menuList",menuList);
			result.addObject("roleMenuList",roleMenuList);
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	
	
	@RequestMapping("/update")
	@ResponseBody
	public boolean update(HttpServletRequest request,Role role){
		String[] menuIds = request.getParameterValues("checkMenus");
		boolean flag = false;
		try{
			int affect = roleService.update("Role.update", role);
			if(affect>0){
				flag = true;
				if(menuIds.length>0){
					List<RoleMenu> roleMenuList = roleMenuService.selectByRoleId("RoleMenu.selectByRoleId", role.getId());
					roleMenuService.setRelations(menuIds, roleMenuList, role);
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return flag;
	}
}
