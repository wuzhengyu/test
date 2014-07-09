package cn.aozhi.app.domain.verlify;

import cn.aozhi.app.domain.user.User;

public class Verlification {
	
	/**
	 * 登录用户
	 */
	private User user;
	
	/**
	 * 提示信息
	 */
	private String message;
	
	
	/**
	 * 是否可以登录      true：可以登录   false：不能登录(验证失败)
	 */
	private boolean canLogin = false;
	
	
	public Verlification(){
		
	}
	
	public Verlification(String username,String password,User user){
		this.user = user;
		if(user!=null){
			if(!username.equals(user.getUsername())){
				this.message = "用户名不存在";
			}else{
				if(!password.equals(user.getPassword())){
					this.message = "密码不正确";
				}
			}
			
			if(username.equals(user.getUsername())&&password.equals(user.getPassword())){
				this.canLogin = true;
			}
		}else{
			this.message = "用户名不存在";
		}
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isCanLogin() {
		return canLogin;
	}

	public void setCanLogin(boolean canLogin) {
		this.canLogin = canLogin;
	}
	
}
