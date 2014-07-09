package cn.aozhi.app.domain.temp;

import java.util.Date;

/**
 * 临时文件实体
 * @author Luxh
 *
 */
public class Temp {
	private String id;
	private String tempName;
	private String tempHost;
	private String tempPath;
	private String tempRootDir;
	private Date createDate;
	private String contentType;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTempName() {
		return tempName;
	}
	public void setTempName(String tempName) {
		this.tempName = tempName;
	}
	public String getTempHost() {
		return tempHost;
	}
	public void setTempHost(String tempHost) {
		this.tempHost = tempHost;
	}
	public String getTempPath() {
		return tempPath;
	}
	public void setTempPath(String tempPath) {
		this.tempPath = tempPath;
	}
	
	public String getTempRootDir() {
		return tempRootDir;
	}
	public void setTempRootDir(String tempRootDir) {
		this.tempRootDir = tempRootDir;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	
}
