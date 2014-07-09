package cn.aozhi.app.controller.user;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.aozhi.app.domain.role.Role;
import cn.aozhi.app.domain.user.User;
import cn.aozhi.app.domain.user_role.UserRole;
import cn.aozhi.app.service.role.RoleService;
import cn.aozhi.app.service.user.UserService;
import cn.aozhi.app.service.user_role.UserRoleService;
import cn.aozhi.app.util.CommonUtil;
import cn.aozhi.app.util.EntryptionUtil;
import cn.aozhi.app.util.Page;


@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private UserRoleService userRoleService;

	
	/**
	 * 保存
	 * @param user
	 * @return
	 */
	@RequestMapping("/save")
	@ResponseBody
	public boolean save(User user) {
		boolean flag = false;
		try{
			user.setId(CommonUtil.uuidGenerate());
			user.setPassword(EntryptionUtil.generatePassword("000000"));
			User u = (User) userService.save("User.insert",user);
			if(u!=null){
				flag = true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
		}
		return flag;
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public boolean delete(String id){
		boolean flag = false;
		int affect = userService.delete("User.delete", id);
		if(affect>0){
			flag = true;
		}
		return flag;
	}
	
	/**
	 * 更新
	 * @param user
	 * @return
	 */
	@RequestMapping("/update")
	@ResponseBody
	public boolean update(HttpServletRequest request,User user){
		String[] roleIds = request.getParameterValues("checkRoles");
		boolean flag = false;
		try{
			int affect = userService.update("User.update", user);
			if(affect>0){
				flag = true;
				if(roleIds.length>0){
					List<UserRole> userRoleList = userRoleService.selectByUserId("UserRole.selectByUserId", user.getId());
					userRoleService.setRelations(roleIds, userRoleList, user);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
		}
		return flag;
	}
	
	/**
	 * 根据id获取对象
	 * @param id
	 * @return
	 */
	@RequestMapping("/getById")
	public ModelAndView getById(String id){
		ModelAndView result = new ModelAndView("user/user_edit");
		try{
			User user = (User) userService.getById("User.selectById",id);
			List<Role> roleList = roleService.list("Role.list", null);
			List<UserRole> userRoleList = userRoleService.selectByUserId("UserRole.selectByUserId", id);
			result.addObject("user",user);
			result.addObject("roleList",roleList);
			result.addObject("userRoleList",userRoleList);
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 数据列表
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/findUserPage")
	public ModelAndView findPage(HttpServletRequest request){
		ModelAndView result = new ModelAndView("user/user_list");
		try{
			int currentPage = request.getParameter("currentPage")==null?1:Integer.parseInt(request.getParameter("currentPage"));
			int pageSize = request.getParameter("pageSize")==null?10:Integer.parseInt(request.getParameter("pageSize"));
			int startIndex = CommonUtil.getStartIndex(currentPage, pageSize);
			Map<String,Object> params =CommonUtil.getPageParams(startIndex, pageSize, currentPage, "User.findPage", "User.findPageCount");
			params.put("sortColumn", "createDate");//按照时间的降序排序（默认）
			Page<User> page = userService.getPage(currentPage, pageSize, params);
			result.addObject("page",page);
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 校验用户名
	 * @param username
	 * @return
	 */
	@RequestMapping("/checkUser")
	@ResponseBody
	public boolean checkUser(String username) {
		boolean flag = false;
		try{
			User user = (User) userService.selectByUsername("User.selectByUsername", username);
			if(user!=null){
				flag = true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * 校验密码
	 * @param password
	 * @param userId
	 * @return
	 */
	@RequestMapping("/checkPwd")
	@ResponseBody
	public boolean checkPwd(String password,String userId){
		boolean flag = false;
		try{
			User user = (User) userService.getById("User.selectById", userId);
			if(user!=null){
				String pwd = user.getPassword();
				boolean isEquals = EntryptionUtil.validatePassword(pwd, password);
				if(isEquals){
					flag = true;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * 修改密码
	 * @param newPassword
	 * @param userId
	 * @return
	 */
	@RequestMapping("/changePassword")
	@ResponseBody
	public boolean changePassword(String newPassword,String userId){
		boolean flag = false;
		try{
			User user = (User) userService.getById("User.selectById", userId);
			if(user!=null){
				user.setPassword(EntryptionUtil.generatePassword(newPassword));
				int affectRow = userService.update("User.update", user);
				if(affectRow>0){
					flag = true;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * 重置密码
	 * @param userId
	 * @return
	 */
	@RequestMapping("/resetPwd")
	@ResponseBody
	public boolean resetPwd(String id){
		boolean flag = false;
		try{
			User user = (User) userService.getById("User.selectById", id);
			user.setPassword(EntryptionUtil.generatePassword("000000"));
			int affect = userService.update("User.update",user);
			if(affect>0){
				flag = true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return flag;
	}
	
}
