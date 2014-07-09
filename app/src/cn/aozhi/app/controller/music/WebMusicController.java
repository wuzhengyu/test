package cn.aozhi.app.controller.music;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import cn.aozhi.app.domain.music.Music;
import cn.aozhi.app.service.music.MusicService;
import cn.aozhi.app.util.CommonUtil;
import cn.aozhi.app.util.DateUtil;
import cn.aozhi.app.util.FileUtil;
import cn.aozhi.app.util.Page;
import cn.aozhi.app.util.PropertiesUtil;

import com.google.common.collect.Maps;

@Controller
@RequestMapping("/webMusic")
public class WebMusicController {
	
	@Autowired
	private MusicService musicService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/getMusicList")
	public ModelAndView getMusicList(HttpServletRequest request,String type){
		ModelAndView result = new ModelAndView("music/music_list");
		try{
			int currentPage = request.getParameter("currentPage")==null?1:Integer.parseInt(request.getParameter("currentPage"));
			int pageSize = request.getParameter("pageSize")==null?10:Integer.parseInt(request.getParameter("pageSize"));
			int startIndex = CommonUtil.getStartIndex(currentPage, pageSize);
			Map<String,Object> params =	CommonUtil.getPageParams(startIndex, pageSize, currentPage, "Music.findPage", "Music.findPageCount");
			params.put("sortColumn", "createDate");
			if("1".equals(type)){
				params.put("musicType", "1");
			}else if("2".equals(type)){
				params.put("musicType", "2");
			}
			Page<Music> page = musicService.getPage(currentPage, pageSize, params);
			result.addObject("page",page);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/upload")
	@ResponseBody
	public Map<String,Object> upload(@RequestParam("file") CommonsMultipartFile file,HttpServletRequest req){
		boolean flag = false;
		Map<String,Object> map = Maps.newHashMap();
		int status = 1;
		String msg = "上传成功";
		
		try {
			String separator = "/";
			String date = DateUtil.getDateFilePath();
			String rootDir = PropertiesUtil.getProperty("app.http");//取得服务器的域名和端口号：http//128.244.128.50:8080
			
			String subDir = PropertiesUtil.getProperty("upload.music")+separator+date;
			String fileName = FileUtil.getWithoutExtension(file.getOriginalFilename());//取得文件名
			String extendName = FileUtil.getExtension(file.getOriginalFilename());//取得文件的扩展名
			String newFileName = CommonUtil.getUuid()+extendName;//拼接新的文件名（避免上传重复的被覆盖
			
			String virtualDir = PropertiesUtil.getProperty("file.upload.virtual.dir");//取得虚拟目录 
			
			String finalPath = subDir;
			
			String musicHost = rootDir+virtualDir;
			
			String typeDir = PropertiesUtil.getProperty("type.music");
			
			String musicPath = typeDir+separator+date+separator+newFileName;
			
			File f = new File(finalPath);
			if(!f.exists()){
				f.mkdirs();
			}
			File newFile=new File(f,newFileName);
	        //通过CommonsMultipartFile的方法直接写文件（注意这个时候）
				file.transferTo(newFile);
				Music m = new Music();
				m.setId(CommonUtil.uuidGenerate());
				m.setMusicName(fileName);
				m.setMusicHost(musicHost);
				m.setMusicPath(musicPath);
				m.setContentType(file.getContentType());
				Music music = (Music) musicService.save("Music.insert", m);
				if(music!=null){
					flag = true;
				}
		}catch(Exception e){
			e.printStackTrace();
			status = 0;
			msg = "上传失败";
		}finally{
			map.put("flag", flag);
			map.put("status", status);
			map.put("msg", msg);
		}
        
        return map;
	}	
	
	@RequestMapping("/delete")
	@ResponseBody
	public Map<String,Object> delete(String id,HttpServletResponse response,HttpServletRequest request) throws IOException{
		Map<String,Object> map = Maps.newHashMap();
		boolean flag = true;
		String msg = "操作成功";
		try{
			
			Music m = (Music) musicService.getById("Music.selectById", id);
			String musicPath = m.getMusicPath();
			int affectRow = musicService.delete("Music.delete", id);//删除数据库中的数据
		    if(affectRow<=0){
		    	flag = false;
		    	msg = "操作失败";
		    }else{
			    String root = PropertiesUtil.getProperty("delete.music");
			    File file = new File(root+musicPath);
			    file.delete();//删除磁盘上的文件
		    }
		}catch(Exception e){
			flag = false;
	    	msg = "操作失败";
		}finally{
			map.put("flag", flag);
			map.put("msg", msg);
		}
	    return map;
	}
	
	
	@RequestMapping("/getById")
	public ModelAndView getById(String id,HttpServletResponse response,HttpServletRequest request) throws IOException {
		
		ModelAndView result = new ModelAndView("music/music_edit");
		try{
			Music music = (Music) musicService.getById("Music.selectById", id);
			result.addObject("music", music);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
		}
		return result;
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public boolean update(Music music,HttpServletResponse response,HttpServletRequest request) throws IOException{
		boolean flag = false;
		try{
			ModelAndView result = new ModelAndView("music/music_edit");
			int affectRow  =  musicService.update("Music.update", music);
			if(affectRow>0){
				flag = true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
		}
		return flag;
	}
	
}
