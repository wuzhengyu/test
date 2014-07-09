package cn.aozhi.app.domain.phone_song;

import java.util.Date;


/**
 * 用户歌曲实体
 * @author Luxh
 *
 */
public class PhoneSong {
	/**
	 * 主键id
	 */
	private String id;
	
	/**
	 * 用户手机id
	 */
	private String phoneId;
	
	/**
	 *  和音歌曲id
	 */
	private String songId;
	private Date createDate;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPhoneId() {
		return phoneId;
	}
	public void setPhoneId(String phoneId) {
		this.phoneId = phoneId;
	}
	public String getSongId() {
		return songId;
	}
	public void setSongId(String songId) {
		this.songId = songId;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
}
