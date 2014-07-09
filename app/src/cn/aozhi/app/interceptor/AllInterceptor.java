package cn.aozhi.app.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.aozhi.app.domain.phone.Phone;
import cn.aozhi.app.domain.user.User;
import cn.aozhi.app.service.phone.PhoneService;

public class AllInterceptor implements HandlerInterceptor{
	
	@Autowired
	private PhoneService phoneService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object obj) throws Exception {
		response.setContentType("text/html");
		response.setHeader("Cache-Control","no-cache");
		response.setCharacterEncoding("UTF-8");
		boolean flag = false;
		String url = request.getRequestURI();
		String ctx = request.getContextPath();
		if(url.indexOf("/login/toLogin")!=-1||url.indexOf("jsp/login.jsp")!=-1||url.indexOf("/appMusic")!=-1||url.indexOf("/upload")!=-1||url.indexOf("/song")!=-1||url.indexOf("/music")!=-1||url.endsWith(".js")||url.endsWith(".css")){//登陆
			flag =  true;
		}else{
			String phonekey = request.getParameter("phoneKey");
			if(phonekey==null||"".equals(phonekey)){
				User user = (User)request.getSession().getAttribute("user");
				if(user==null){
					PrintWriter out = response.getWriter();
		    		response.setStatus(HttpStatus.SC_FORBIDDEN);
		    		out.write("您未登录或登录信息已过期！");
				}else if(url.indexOf("/appSong")!=-1){
					PrintWriter out = response.getWriter();
		    		out = response.getWriter();
		    		response.setStatus(HttpStatus.SC_FORBIDDEN);
		    		out.write("您未登录或登录信息已过期！");
				}else{
					flag = true;
				}
			}else{
				if(phonekey!=null&&!"".equals(phonekey)){
					if(url.indexOf("/phone/getMusicId")!=-1){//手机app注册
						flag = true;
					}else{
						if(url.indexOf("/appSong")!=-1){//手机app请求
							Phone phone = (Phone)phoneService.getById("Phone.selectById", phonekey);
					    	if(phone==null){
					    		PrintWriter out = response.getWriter();
					    		out = response.getWriter();
					    		response.setStatus(HttpStatus.SC_FORBIDDEN);
					    		out.write("您未登录或登录信息已过期！");
					    	}else{
					    		flag = true;
					    	}
						}else{//web浏览器请求
							User user = (User)request.getSession().getAttribute("user");
							if(user==null){
								PrintWriter out = response.getWriter();
					    		response.setStatus(HttpStatus.SC_FORBIDDEN);
					    		out.write("您未登录或登录信息已过期！");
							}else{
								flag = true;
							}
						}
					}
				}
			}	
		}
		return flag;
	}


	@Override
	public void postHandle(HttpServletRequest request,HttpServletResponse response, Object obj, ModelAndView model)throws Exception {
	
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request,HttpServletResponse response, Object obj, Exception e)throws Exception {
	
	}

}
