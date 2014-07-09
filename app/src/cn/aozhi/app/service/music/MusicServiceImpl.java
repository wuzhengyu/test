package cn.aozhi.app.service.music;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.aozhi.app.domain.music.Music;
import cn.aozhi.app.persistence.dao.music.MusicDao;
import cn.aozhi.app.service.common.CommonServiceImpl;
import cn.aozhi.app.util.Page;

@Service("musicService")
public class MusicServiceImpl extends CommonServiceImpl<Music> implements MusicService<Music> {

	/**
	 * 取得曲库数据库中的第一行记录
	 */
	@Override
	public Music getDefalutMusic(int currentPage,int pageSize,Map<String,Object> params) {
		Music music=null;
		Page<Music> page = getPage(currentPage,pageSize,params);
		List<Music> musicList = page.getResult();
		if(musicList.size()>0&&musicList!=null){
			music = musicList.get(0);
		}
		return music;
	}

}
