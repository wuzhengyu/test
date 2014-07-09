package cn.aozhi.app.service.song;

import java.util.Map;

import cn.aozhi.app.service.common.CommonService;

public interface SongService<Song> extends CommonService<Song>{
	/**
	 * 保存合成歌曲
	 * @param songName
	 * @param phoneKey
	 * @param tempId
	 * @param sperator
	 * @return
	 */
	public Map<String,Object> save(String songName,String phoneKey,String tempId,String sperator);
	
	/**
	 * 点赞
	 * @param id
	 * @param phoneKey
	 * @return
	 */
	public Map<String,Object> clickPraise(String id,String phoneKey);
	

	/**
	 * 删除合成歌曲
	 * @param id
	 * @param phoneKey
	 * @return
	 */
	public Map<String,Object> deleteSong(String id,String phoneKey);
}
