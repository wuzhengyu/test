package cn.aozhi.app.domain.record;

import java.util.Date;
import java.util.Map;

import com.google.common.collect.Maps;

/**
 * 录音文件实体
 * @author Luxh
 *
 */
public class Record {
	/**
	 * 主键
	 */
	private String id;
	
	/**
	 * 录音文件名称
	 */
	private String recordName;
	
	/**
	 * 第三方接口返回的录音文件id
	 */
	private String recordId;
	
	
	/**
	 * 第三方接口返回的变音后的文件id
	 */
	private String songId;
	
	
	/**
	 * 录音文件简介
	 */
	private String description;
	
	
	/**
	 * 录音文件存放的url地址
	 */
	private String recordHost;
	
	
	private String recordPath;
	
	/**
	 * 录音文件创建日期
	 */
	private Date createDate;
	
	/**
	 * 录音文件内容类型
	 */
	private String contentType;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRecordName() {
		return recordName;
	}

	public String getRecordId() {
		return recordId;
	}

	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}
	
	public String getSongId() {
		return songId;
	}

	public void setSongId(String songId) {
		this.songId = songId;
	}

	public void setRecordName(String recordName) {
		this.recordName = recordName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRecordHost() {
		return recordHost;
	}

	public void setRecordHost(String recordHost) {
		this.recordHost = recordHost;
	}

	public String getRecordPath() {
		return recordPath;
	}

	public void setRecordPath(String recordPath) {
		this.recordPath = recordPath;
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
