package cn.aozhi.app.domain.music;

import java.util.Date;

import cn.aozhi.app.util.FileUtil;

/**
 * 歌曲实体(曲库)
 * @author Luxh
 *
 */
public class Music {
	/**
	 * 主键
	 */
	private String id;
	
	/**
	 * 歌曲名称
	 */
	private String musicName;
	
	/**
	 * 歌曲简介
	 */
	private String description;
	
	
	/**
	 * 歌曲存放的主机名
	 */
	private String musicHost;
	
	
	/**
	 * 歌曲存放的路径
	 */
	private String musicPath;
	
	/**
	 * 歌曲创建日期
	 */
	private Date createDate;
	
	/**
	 * 歌曲类型
	 */
	private String musicType;
	
	
	private String musicUrl;
	/**
	 * 歌曲内容类型
	 */
	private String contentType;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMusicName() {
		return musicName;
	}

	public void setMusicName(String musicName) {
		this.musicName = musicName;
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMusicHost() {
		return musicHost;
	}

	public void setMusicHost(String musicHost) {
		this.musicHost = musicHost;
	}

	public String getMusicPath() {
		return musicPath;
	}

	public void setMusicPath(String musicPath) {
		this.musicPath = musicPath;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getMusicType() {
		return musicType;
	}

	public void setMusicType(String musicType) {
		this.musicType = musicType;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getMusicUrl() {
		return this.musicHost+this.musicPath;
	}
	
	
}
