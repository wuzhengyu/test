package cn.aozhi.app.persistence.dao.song;

import org.springframework.stereotype.Repository;

import cn.aozhi.app.domain.song.Song;
import cn.aozhi.app.persistence.dao.common.CommonDaoImpl;

@Repository("songDao")
public class SongDaoImpl extends CommonDaoImpl<Song> implements SongDao{

}
