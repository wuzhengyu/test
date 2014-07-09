package cn.aozhi.app.controller.song;


import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.aozhi.app.domain.song.Song;
import cn.aozhi.app.service.song.SongService;
import cn.aozhi.app.util.CommonUtil;
import cn.aozhi.app.util.Page;
import cn.aozhi.app.util.PropertiesUtil;

import com.google.common.collect.Maps;

@Controller
@RequestMapping("/webSong")
public class WebSongController {
	
	@Autowired
	private SongService songService;
	
	
	/**
	 * 获取变音歌曲的数据列表
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/getSongList")
	public ModelAndView getSongList(HttpServletRequest request){
		ModelAndView result = new ModelAndView("song/song_list");
		try{
			int currentPage = request.getParameter("currentPage")==null?1:Integer.parseInt(request.getParameter("currentPage"));
			int pageSize = request.getParameter("pageSize")==null?10:Integer.parseInt(request.getParameter("pageSize"));
			int startIndex = CommonUtil.getStartIndex(currentPage, pageSize);
			Map<String,Object> params =	CommonUtil.getPageParams(startIndex, pageSize, currentPage, "Song.findPage", "Song.findPageCount");
			params.put("sortColumn", "createDate");//按照时间的降序降排序
			Page<Song> page = songService.getPage(currentPage, pageSize, params);
			result.addObject("page",page);
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 根据id删除变音歌曲对象
	 * @param response
	 * @param request
	 * @param id
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/deleteSong")
	@ResponseBody
	public Map<String,Object> delete(HttpServletResponse response,HttpServletRequest request,String id){
		Map<String,Object> map = Maps.newHashMap();
		boolean flag = true;
		String msg = "操作成功";
		try{
			Song song  = (Song) songService.getById("Song.selectById", id);
			String songPath = song.getSongPath();
		    int affectRow = songService.delete("Song.delete", id);//删除数据库中的数据
		    if(affectRow<=0){
		    	flag = false;
		    	msg = "操作失败";
		    }else{
		    	String root = PropertiesUtil.getProperty("delete.song");
			    File file = new File(root+songPath);
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
	
	
	
	/**
	 * 根据id查询变音歌曲对象
	 * @param id
	 * @return
	 */
	@RequestMapping("/getById")
	public ModelAndView getById(String id){
		ModelAndView result = new ModelAndView("song/song_edit");
		try{
			Song song = (Song) songService.getById("Song.selectById", id); 
			result.addObject("song", song);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
		}
		return result;
	}
	
	
	/**
	 * 更新
	 * @param song
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping("/update")
	@ResponseBody
	public boolean update(Song song,HttpServletResponse response,HttpServletRequest request) {
		boolean flag = false;
		try{
			int affect = songService.update("Song.update", song);
			if(affect>0){
				flag = true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
		}
		return flag;
	}   

}
