package cn.aozhi.app.controller.login;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.aozhi.app.domain.user.User;
import cn.aozhi.app.domain.verlify.Verlification;
import cn.aozhi.app.service.user.UserService;
import cn.aozhi.app.util.EntryptionUtil;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	/**
	 * 后台管理用户登录
	 * @param username
	 * @param password
	 * @param request
	 * @param resposne
	 * @return
	 */
	@RequestMapping("/toLogin")
	public ModelAndView toLogin(String username,String password,HttpServletRequest request,HttpServletResponse resposne){
		ModelAndView result = null;
		String entryptionPwd = EntryptionUtil.generatePassword(password);
		Map<String,String> params = new HashMap<String,String>();
		params.put("username", username);
		params.put("password", entryptionPwd);
		User user = (User) userService.selectByUsername("User.selectByUsername", username);
		if(user==null){
			user = (User) request.getSession().getAttribute("user");
			if(user!=null){
				username = user.getUsername();
				entryptionPwd = user.getPassword();
			}
		}
		
		//封装登录失败或成功的信息
		Verlification verlification = new Verlification(username, entryptionPwd, user);
		
		if(verlification.isCanLogin()){
			request.getSession().setAttribute("user", user);
			result =  new ModelAndView("redirect:/index");
		}else{
			result = new ModelAndView("login");
			result.addObject("verlification",verlification);
		}
		
		return result;
	}
	
	@RequestMapping("/quit")
	public ModelAndView quit(HttpServletRequest request,HttpServletResponse resposne){
		ModelAndView result = new ModelAndView("login");
		HttpSession session = request.getSession();
		if(session!=null){
			session.invalidate();
		}
		return result;
	}
}
