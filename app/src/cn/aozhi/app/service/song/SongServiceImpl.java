package cn.aozhi.app.service.song;

import java.io.File;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.aozhi.app.domain.phone_song.PhoneSong;
import cn.aozhi.app.domain.song.Song;
import cn.aozhi.app.domain.temp.Temp;
import cn.aozhi.app.service.common.CommonServiceImpl;
import cn.aozhi.app.service.phone_song.PhoneSongService;
import cn.aozhi.app.service.temp.TempService;
import cn.aozhi.app.util.CommonUtil;
import cn.aozhi.app.util.DateUtil;
import cn.aozhi.app.util.FileUtil;
import cn.aozhi.app.util.PropertiesUtil;

import com.google.common.collect.Maps;

@Service("songService")
public class SongServiceImpl extends CommonServiceImpl<Song> implements SongService<Song>{
	
	@Autowired
	private PhoneSongService phoneSongService;
	
	@Autowired
	private TempService tempService;
	/**
	 * 保存合成歌曲(将临时目录中的文件复制合成歌曲目录下)
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public Map<String, Object> save(String songName, String phoneKey,String tempId,String sperator) {
			Map<String,Object> resultMap = Maps.newHashMap();
			int status = 1;
			String msg = "保存成功";
			try{
				Temp temp = (Temp) tempService.getById("Temp.selectById", tempId);
				String date = DateUtil.getDateFilePath();
				String rootDir = PropertiesUtil.getProperty("app.http");//取得服务器的域名和端口号：http//128.244.128.50:8080
				String subDir = PropertiesUtil.getProperty("upload.song")+sperator+date;
				String virtualDir = PropertiesUtil.getProperty("file.upload.virtual.dir");//取得虚拟目录 
	         	//String fileName = HttpClientUtil.downloadFileByUrl(songUrl, subDir);//下载歌曲文件到服务器
				String tempUrl = temp.getTempRootDir()+temp.getTempPath();
				File srcFile = new File(tempUrl);
				String fileName = srcFile.getName();
				File destFile = new File(subDir,fileName);
				FileUtils.copyFile(srcFile, destFile);//将临时目录下的文件复制到song目录下
		        String typeDir = PropertiesUtil.getProperty("type.song");
		 		String songHost = rootDir+virtualDir;
		 		String songPath = typeDir+sperator+date+sperator+fileName;
		 		
		         //设置合成歌曲对象的值
		         Song song = new Song();
		         song.setId(CommonUtil.uuidGenerate());
		         song.setSongName(songName);
		         song.setSongHost(songHost);
		         song.setSongPath(songPath);
		         song.setContentType(FileUtil.getNoPointExtension(fileName));
		         song = (Song) save("Song.insert", song);//将合成歌曲对象信息保存到数据库
		         if(song==null){
		        	 status = 0;
		        	 msg = "保存失败";
		         }
		         //设置用户手机合成歌曲的关联关系
		         PhoneSong phoneSong = new PhoneSong();
		         phoneSong.setId(CommonUtil.uuidGenerate());
		         phoneSong.setPhoneId(phoneKey);
		         phoneSong.setSongId(song.getId());
		         phoneSongService.save("PhoneSong.insert", phoneSong);
			}catch(Exception e){
				e.printStackTrace();
				status = 0;
				msg = "保存失败";
			}finally{
				resultMap.put("status", status);
				resultMap.put("msg", msg);
			}
			return resultMap;
		}
	
	
	
	@Override
	public Map<String, Object> clickPraise(String id, String phoneKey) {
		int status = 1;
		String msg = "操作成功";
		Map<String,Object> result = Maps.newHashMap();
		try{
			Song song = (Song) getById("Song.selectById", id);
			int clickNumber = song.getClickNum();
			song.setClickNum(clickNumber+1);
			int affectRow = update("Song.update", song);
			if(affectRow<=0){
				status = 0;
				msg = "操作失败";
			}
		}catch(Exception e){
			status = 0;
			msg = "操作失败";
		}finally{
			result.put("status", status);
			result.put("msg", msg);
		}
		return result;
	}


	/**
	 * 删除合成歌曲
	 */
	@Override
	public Map<String, Object> deleteSong(String id, String phoneKey) {
		Map<String,Object> map = Maps.newHashMap();
		int status = 1;
		String msg = "操作成功";
		try{
			Song song = (Song) getById("Song.selectById", id); 
			String songPath = song.getSongPath();
		    int affectRow = delete("Song.delete", id);//删除数据库中的数据
		    if(affectRow<=0){
		    	status = 0;
				msg = "操作失败";
		    }else{
		    	String root = PropertiesUtil.getProperty("delete.song");
		    	File file = new File(root+songPath);
		    	file.delete(); //删除磁盘上的文件
		    }
		}catch(Exception e){
			status = 0;
			msg = "操作失败";
		}finally{
			map.put("status", status);
			map.put("msg", msg);
		}
	    return map;
	}

}
