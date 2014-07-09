package cn.aozhi.app.domain.user;

import java.util.Date;

/**
 * 用户实体
 * @author Luxh
 *
 */
public class User {
	
	/**
	 * 用户主键
	 */
	private String id;
	
	/**
	 * 用户名
	 */
	private String username;
	
	/**
	 * 密码
	 */
	private String password;
	
	
	/**
	 * 创建日期
	 */
	private Date createDate;



	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public Date getCreateDate() {
		return createDate;
	}


	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
}
