package cn.aozhi.app.util;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public class CommonUtil {
	
	
	public static int getStartIndex(int currentPage,int pageSize){
		return (currentPage-1)<0?0:(currentPage-1)*pageSize;
	}  
	
	/**
	 * 获取分页需要的参数map集合
	 * @param startIndex
	 * @param pageSize
	 * @param currentPage
	 * @param sqlList
	 * @param sqlCount
	 * @return
	 */
	public static Map<String,Object> getPageParams(int startIndex,int pageSize,int currentPage,String sqlList,String sqlCount){
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("startIndex", startIndex);
		map.put("pageSize", pageSize);
		map.put("currentPage", currentPage);
		map.put("sqlList", sqlList);
		map.put("sqlCount", sqlCount);
		return map;
	}
	
	/**
	 * 文件名获取
	 * @param str
	 * @return
	 */
	public static String getFileName(String str){
		int lastIndex = str.lastIndexOf(".");
		String resultStr = str.substring(0, lastIndex);
		return resultStr;
	}
	
	/**
	 * 获取原文件名
	 * @param str
	 * @return
	 */
	public static String getRealFileName(String str){
		int startIndex = str.indexOf("_");
		String resultStr = str.substring(startIndex+1, str.length());
		return resultStr;
	}
	
	/**
	 * 生成32位UUID
	 * @return
	 */
	public static String uuidGenerate(){
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replaceAll("-", "").toUpperCase();
	}
	
	
	public static String getUuid(){
		return UUID.randomUUID().toString();
	}
	
}
