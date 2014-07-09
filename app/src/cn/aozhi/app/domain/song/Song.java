package cn.aozhi.app.domain.song;

import java.util.Date;

import cn.aozhi.app.util.FileUtil;

/**
 *变音歌曲实体
 * @author luxh
 *
 */
public class Song {
	/**
	 * 主键
	 */
	private String id;
	
	/**
	 * 变音歌曲名称
	 */
	private String songName;
	
	
	/**
	 * 变音歌曲简介
	 */
	private String description;
	
	
	/**
	 * 变音歌曲点赞数
	 */
	private int clickNum;
	
	/**
	 * 变音歌曲设置铃声数
	 */
	private int ringtoneNum;
	
	
	/**
	 * 变音歌曲设置彩铃数
	 */
	private int colorRingToneNum;
	
	/**
	 *  主机名和端口号
	 */
	private String songHost;
	
	/**
	 *  存放的路径
	 */
	private String songPath;
	
	/**
	 * 变音歌曲创建日期
	 */
	private Date createDate;
	
	/**
	 * 变音歌曲类型
	 */
	private String songType;
	
	
	private String songUrl;
	
	
	
	


	/**
	 * 变音歌曲内容类型
	 */
	private String contentType;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSongName() {
		return songName;
	}

	public void setSongName(String songName) {
		this.songName = songName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getClickNum() {
		return clickNum;
	}

	public void setClickNum(int clickNum) {
		this.clickNum = clickNum;
	}

	public int getRingtoneNum() {
		return ringtoneNum;
	}

	public void setRingtoneNum(int ringtoneNum) {
		this.ringtoneNum = ringtoneNum;
	}

	public int getColorRingToneNum() {
		return colorRingToneNum;
	}

	public void setColorRingToneNum(int colorRingToneNum) {
		this.colorRingToneNum = colorRingToneNum;
	}

	public String getSongHost() {
		return songHost;
	}

	public void setSongHost(String songHost) {
		this.songHost = songHost;
	}

	public String getSongPath() {
		return songPath;
	}

	public void setSongPath(String songPath) {
		this.songPath = songPath;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getSongType() {
		return songType;
	}

	public void setSongType(String songType) {
		this.songType = songType;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	
	public String getSongUrl() {
		return this.songHost+this.songPath;
	}
	
}
