package cn.aozhi.app.domain.phone;

import java.util.Date;

/**
 * 用户手机实体
 * @author Luxh
 *
 */
public class Phone {
	
	/**
	 * 主键id(手机客户端生成的手机唯一标识)
	 */
	private String id;
	
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

	public Date getCreateDate() {
		return createDate;
	}
	
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
}
