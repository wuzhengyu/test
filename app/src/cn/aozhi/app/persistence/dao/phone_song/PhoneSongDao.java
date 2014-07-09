package cn.aozhi.app.persistence.dao.phone_song;

import cn.aozhi.app.domain.phone.Phone;
import cn.aozhi.app.domain.phone_song.PhoneSong;
import cn.aozhi.app.persistence.dao.common.CommonDao;

public interface PhoneSongDao extends CommonDao<PhoneSong>{
	Phone selectByPhoneKey(String sqlId,String phoneKey);
}
