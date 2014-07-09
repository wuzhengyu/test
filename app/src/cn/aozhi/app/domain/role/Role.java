package cn.aozhi.app.domain.role;

import java.util.Date;

/**
 * 角色实体
 * @author Luxh
 *
 */
public class Role {
	
	
	/**
	 * 主键
	 */
	private String id;
	
	/**
	 * 角色名称
	 */
	private String roleName;
	
	/**
	 * 创建时间
	 */
	private Date createDate;
	
	
	/**
	 * 描述
	 */
	private String description;


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getRoleName() {
		return roleName;
	}


	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}


	public Date getCreateDate() {
		return createDate;
	}


	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}
	
}
