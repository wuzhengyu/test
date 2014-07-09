package cn.aozhi.app.controller.music;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.aozhi.app.domain.music.Music;
import cn.aozhi.app.service.music.MusicService;
import cn.aozhi.app.util.CommonUtil;
import cn.aozhi.app.util.Page;

import com.google.common.collect.Maps;

@Controller
@RequestMapping("/appMusic")
public class AppMusicController {
	
	@Autowired
	private MusicService musicService;
	
	Logger logger = Logger.getLogger(AppMusicController.class);
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/getMusicJsonList")
	@ResponseBody
	public Map<String,Object> getMusicJsonList(int currentPage,int pageSize,String type){
		logger.info("==========>曲库：page:"+currentPage+" size:"+pageSize);
		Map<String,Object> result = Maps.newHashMap();
		int status = 1;
		String msg = "处理成功";
		if(currentPage<0||pageSize<0){
			status = 0;
			msg = "处理失败";
			result.put("status", status);
			result.put("msg", msg);
			return result;
		}
		try{
			int startIndex = CommonUtil.getStartIndex(currentPage, pageSize);
			Map<String,Object> params =	CommonUtil.getPageParams(startIndex, pageSize, currentPage, "Music.findPage", "Music.findPageCount");
			params.put("sortColumn", "createDate");
			if("1".equals(type)){
				params.put("musicType", "1");//热门歌曲
			}else if("2".equals(type)){
				params.put("musicType", "2");//经典歌曲
			}
			Page<Music> page = musicService.getPage(currentPage, pageSize, params);
			result.put("result", page.getResult());
			result.put("pageSize", page.getPageSize());
			result.put("totalCount", page.getTotalCount());
			result.put("currentPage", page.getCurrentPage());
		}catch(Exception e){
			status = 0;
			msg = "处理失败";
		}finally{
			result.put("status", status);
			result.put("msg", msg);
		}
		return result;
	}
	
}
