package cn.aozhi.app.domain.key_song;

import java.util.Date;

public class KeySong {
	private String id;
	private String phoneKey;
	private String songId;
	private Date createDate;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPhoneKey() {
		return phoneKey;
	}
	public void setPhoneKey(String phoneKey) {
		this.phoneKey = phoneKey;
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
