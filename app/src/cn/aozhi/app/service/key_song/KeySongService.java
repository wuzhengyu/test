package cn.aozhi.app.service.key_song;

import java.util.Map;

import cn.aozhi.app.domain.key_song.KeySong;
import cn.aozhi.app.service.common.CommonService;

public interface KeySongService extends CommonService<KeySong>{
	public KeySong selectByKeyAndId(String sqlId,Map<String,Object> params);
	
	/**
	 * 判断是否可以点赞
	 * @param ks
	 * @return
	 */
	public Map<String,Object> isClick(KeySong keySong,String id,String phoneKey);
	
}
