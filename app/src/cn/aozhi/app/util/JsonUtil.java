package cn.aozhi.app.util;

import java.util.Map;

import com.google.common.collect.Maps;

import net.sf.json.JSONObject;

public class JsonUtil {
	
	/**
	 *上载用户语音返回值josn转换
	 * @param json
	 * @return
	 */
	public static Map<String,String> getUploadJson2Map(String str){
		Map<String,String> map = Maps.newHashMap();
		JSONObject jsonObject  = new JSONObject().fromObject(str);
		map.put("status", jsonObject.get("status").toString());
		map.put("msg",jsonObject.get("msg").toString());
		map.put("recordId",jsonObject.get("speechid").toString());
		return map;
	}
	
	
	/**
	 * 提交合成返回值json转换
	 * @param json
	 * @return
	 */
	public static Map<String,String> getCompoundJson2Map(String str){
		Map<String,String> map = Maps.newHashMap();
		JSONObject jsonObject  = new JSONObject().fromObject(str);
		map.put("status", jsonObject.get("status").toString());
		map.put("msg",jsonObject.get("msg").toString());
		map.put("songId",jsonObject.get("remixid").toString());
		return map;
	}
	
	
	/**
	 * 查询合成结果返回值json转换
	 * @param str
	 * @return
	 */
	public static Map<String,String> getQueryStatus(String str){
		Map<String,String> map = Maps.newHashMap();
		JSONObject jsonObject  = new JSONObject().fromObject(str);
		map.put("status", jsonObject.get("status").toString());
		map.put("msg",jsonObject.get("msg").toString());
		map.put("songUrl",jsonObject.get("url").toString());
		map.put("songfmt",jsonObject.get("songfmt").toString());
		return map;
	}
	
	/**
	 * 将map转为json
	 * @param json
	 * @return
	 */
	public static String map2Json(Map<String,String> map){
		String jsonStr="";
		if(map.size()>0&&map!=null){
			JSONObject jsonObject  = new JSONObject().fromObject(map);
			jsonStr =  jsonObject.toString();
		}
		return jsonStr;
	}
}
