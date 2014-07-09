package cn.aozhi.app.persistence.dao.key_song;

import java.util.Map;

import cn.aozhi.app.domain.key_song.KeySong;
import cn.aozhi.app.persistence.dao.common.CommonDao;

public interface KeySongDao extends CommonDao{
	/**
	 * 根据phoneKey和songId获取
	 * @param sqlId
	 * @param params
	 * @return
	 */
	public KeySong selectByKeyAndId(String sqlId,Map<String,Object> params);
}
