package cn.aozhi.app.persistence.dao.key_song;

import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.aozhi.app.domain.key_song.KeySong;
import cn.aozhi.app.domain.phone.Phone;
import cn.aozhi.app.persistence.dao.common.CommonDaoImpl;

@Repository("keySongDao")
public class KeySongDaoImpl extends CommonDaoImpl implements KeySongDao{

	@Override
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public KeySong selectByKeyAndId(String sqlId, Map<String, Object> params) {
		return sqlSession.selectOne(sqlId, params);
	}

}
