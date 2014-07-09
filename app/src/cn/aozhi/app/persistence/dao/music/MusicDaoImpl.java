package cn.aozhi.app.persistence.dao.music;

import org.springframework.stereotype.Repository;

import cn.aozhi.app.domain.music.Music;
import cn.aozhi.app.persistence.dao.common.CommonDaoImpl;

@Repository("musicDao")
public class MusicDaoImpl extends CommonDaoImpl<Music> implements MusicDao {

}
