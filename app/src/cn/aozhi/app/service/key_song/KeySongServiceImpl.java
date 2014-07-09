package cn.aozhi.app.service.key_song;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.aozhi.app.domain.key_song.KeySong;
import cn.aozhi.app.persistence.dao.key_song.KeySongDao;
import cn.aozhi.app.service.common.CommonServiceImpl;
import cn.aozhi.app.service.song.SongService;
import cn.aozhi.app.util.CommonUtil;
import cn.aozhi.app.util.DateUtil;

import com.google.common.collect.Maps;

@Service("keySongService")
public class KeySongServiceImpl extends CommonServiceImpl<KeySong> implements KeySongService{
	
	@Autowired
	private KeySongDao keySongDao;
	
	@Autowired
	private SongService songService;
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public KeySong selectByKeyAndId(String sqlId, Map<String, Object> params) {
		return keySongDao.selectByKeyAndId(sqlId, params);
	}
	
	/**
	 * 判断是否可以点赞
	 */
	public Map<String, Object> isClick(KeySong keySong,String id,String phoneKey) {
		Map<String,Object> result = Maps.newHashMap();
		if(keySong==null){
			keySong = new KeySong();
			keySong.setId(CommonUtil.uuidGenerate());
			keySong.setPhoneKey(phoneKey);
			keySong.setSongId(id);
			keySong.setCreateDate(new Date());
			save("KeySong.insert", keySong);
			result = songService.clickPraise(id, phoneKey);//点赞
		}else{
			String createDate = DateUtil.date2String(keySong.getCreateDate());
			String now = DateUtil.getCurrentDay();
			if(createDate.equals(now)){//如果2个时间相等，用户已经对歌曲点赞
				result.put("status",2);
				result.put("msg", "点赞失败，您已对该歌曲点赞！");
			}else{//不相等则没有点赞
				result = songService.clickPraise(id, phoneKey);//点赞
				keySong.setCreateDate(new Date());
				int affect = update("KeySong.update", keySong);
				if(affect<=0){
					result.put("status", 0);
					result.put("msg", "操作失败");
				}
			}
		}
		return result;
	}

}
