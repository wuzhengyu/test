package cn.aozhi.app.controller.phone;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Maps;

import cn.aozhi.app.domain.music.Music;
import cn.aozhi.app.domain.phone.Phone;
import cn.aozhi.app.service.music.MusicService;
import cn.aozhi.app.service.phone.PhoneService;
import cn.aozhi.app.util.CommonUtil;

@Controller
@RequestMapping("/phone")
public class PhoneCotroller {
	
	@Autowired
	private PhoneService phoneService;
	
	@Autowired
	private MusicService musicService;
	
	
	/**
	 * 用户登录(用户打开客户端是调用)
	 * @param phoneKey
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/getMusicId",produces="application/json;charset=UTF-8")
	@ResponseBody
	public Map<String,Object> getMusicId(String phoneKey,HttpServletResponse response,HttpServletRequest request){
		Map<String,Object> map = Maps.newHashMap();
		int status = 1;
		String msg = "处理成功";
		int currentPage = 1;
		int pageSize = 10;
		String musicId = "";
		Music music = null;
		if("".equals(phoneKey)||phoneKey==null){
			status = 0;
			msg = "处理失败";
			map.put("status", status);
			map.put("msg", msg);
			return map;
		}
		
		//根据当前页和每页大小取得分页的开始索引
		int startIndex = CommonUtil.getStartIndex(currentPage, pageSize);
		try{
			Phone p = (Phone) phoneService.getById("Phone.selectById", phoneKey);
			if(p==null){
				Phone phone = new Phone();
				phone.setId(phoneKey);
				phone.setCreateDate(new Date());
				phoneService.save("Phone.insert", phone);
			}
			Map<String,Object> params =	CommonUtil.getPageParams(startIndex, pageSize, currentPage, "Music.findPage", "Music.findPageCount");
			params.put("sortColumn", "createDate");
			//获取曲库数据库中的第一行记录
		   music = (Music) musicService.getDefalutMusic(currentPage, pageSize, params);
		}catch(Exception e){
			status = 0;
			msg = "处理失败";
		}finally{
			if(music!=null){
				musicId = music.getId();
				map.put("musicId", musicId);
				map.put("status", status);
				map.put("msg", msg);
				map.put("musicName",music.getMusicName());
			}else{
				status = 0;
				msg = "处理失败";
			}
		}
		return map;
	}
}
