package cn.aozhi.app.service.music;

import java.util.Map;

import cn.aozhi.app.domain.music.Music;
import cn.aozhi.app.service.common.CommonService;

@SuppressWarnings("hiding")
public interface MusicService<Music> extends CommonService<Music>{
	/**
	 * 获取数据库中的第一行记录
	 */
	Music getDefalutMusic(int currentPage,int pageSize,Map<String,Object> params);

}
