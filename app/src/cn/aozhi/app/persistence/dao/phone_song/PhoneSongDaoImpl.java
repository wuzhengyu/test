package cn.aozhi.app.persistence.dao.phone_song;

import org.springframework.stereotype.Repository;

import cn.aozhi.app.domain.phone.Phone;
import cn.aozhi.app.domain.phone_song.PhoneSong;
import cn.aozhi.app.persistence.dao.common.CommonDaoImpl;

@Repository("phoneSongDao")
public class PhoneSongDaoImpl extends CommonDaoImpl<PhoneSong> implements PhoneSongDao{

	@Override
	public Phone selectByPhoneKey(String sqlId, String phoneKey) {
		return sqlSession.selectOne(sqlId, phoneKey);
	}

}
