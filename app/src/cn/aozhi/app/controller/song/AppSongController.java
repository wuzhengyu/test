package cn.aozhi.app.controller.song;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import cn.aozhi.app.domain.key_song.KeySong;
import cn.aozhi.app.domain.record.Record;
import cn.aozhi.app.domain.song.Song;
import cn.aozhi.app.service.key_song.KeySongService;
import cn.aozhi.app.service.record.RecordService;
import cn.aozhi.app.service.song.SongService;
import cn.aozhi.app.service.temp.TempService;
import cn.aozhi.app.util.CommonUtil;
import cn.aozhi.app.util.DateUtil;
import cn.aozhi.app.util.FileUtil;
import cn.aozhi.app.util.HttpClientUtil;
import cn.aozhi.app.util.JsonUtil;
import cn.aozhi.app.util.Page;
import cn.aozhi.app.util.PropertiesUtil;

import com.google.common.collect.Maps;

@Controller
@RequestMapping("/appSong")
public class AppSongController {

	/**
	 * 文件路径分割符
	 */
	private final static String SEPARATOR_STR = "/";

	@Autowired
	private SongService songService;

	@Autowired
	private RecordService recordService;

	@Autowired
	private TempService tempService;

	@Autowired
	private KeySongService keySongService;

	Logger logger = Logger.getLogger(AppSongController.class);

	/**
	 * 获取合成音乐的json数据列表(我的)
	 * 
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "/getMySongJsonList")
	@ResponseBody
	public Map<String, Object> getMySongJsonList(int currentPage, int pageSize, String phoneKey) {
		Map<String, Object> result = Maps.newHashMap();
		int status = 1;
		String msg = "处理成功";
		if (currentPage < 0 || pageSize < 0) {
			status = 0;
			msg = "处理失败";
			result.put("status", status);
			result.put("msg", msg);
			return result;
		}
		try {
			int startIndex = CommonUtil.getStartIndex(currentPage, pageSize);
			Map<String, Object> params = CommonUtil.getPageParams(startIndex, pageSize, currentPage, "Song.myList",
					"Song.myListCount");// 我的幻音歌曲
			params.put("phoneId", phoneKey);
			params.put("sortColumn", "createDate");// 按照时间的降序排序（默认）
			Page<Song> page = songService.getPage(currentPage, pageSize, params);
			result.put("result", page.getResult());
			result.put("currentPage", page.getCurrentPage());
			result.put("pageSize", page.getPageSize());
			result.put("totalCount", page.getTotalCount());
		} catch (Exception e) {
			status = 0;
			msg = "处理失败";
		} finally {
			result.put("status", status);
			result.put("msg", msg);
		}
		return result;
	}

	/**
	 * 获取合成音乐的json数据列表(发现)
	 * 
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "/getAllSongJsonList")
	@ResponseBody
	public Map<String, Object> getAllSongJsonList(int currentPage, int pageSize, String type, String phoneKey) {
		Map<String, Object> result = Maps.newHashMap();
		int status = 1;
		String msg = "处理成功";
		if (currentPage < 0 || pageSize < 0) {
			status = 0;
			msg = "处理失败";
			result.put("status", status);
			result.put("msg", msg);
			return result;
		}
		try {
			int startIndex = CommonUtil.getStartIndex(currentPage, pageSize);
			Map<String, Object> params = CommonUtil.getPageParams(startIndex, pageSize, currentPage, "Song.findPage",
					"Song.findPageCount");// 所有的幻音歌曲
			params.put("phoneId", phoneKey);
			if ("0".equals(type)) {
				params.put("sortColumn", "createDate");// 按照时间的降序排序（默认）
			} else if ("1".equals(type)) {
				params.put("sortColumn", "clickNum");// 按照点赞数量的降序排序
			} else {
				params.put("sortColumn", "createDate");
			}
			Page<Song> page = songService.getPage(currentPage, pageSize, params);
			result.put("result", page.getResult());
			result.put("currentPage", page.getCurrentPage());
			result.put("pageSize", page.getPageSize());
			result.put("totalCount", page.getTotalCount());
		} catch (Exception e) {
			status = 0;
			msg = "处理失败";
		} finally {
			result.put("status", status);
			result.put("msg", msg);
		}
		return result;
	}

	/**
	 * 设置点赞数
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/setClickPraiseNum")
	@ResponseBody
	public Map<String, Object> setClickPraiseNum(String id, String phoneKey) {
		Map<String, Object> result = Maps.newHashMap();
		Map<String, Object> params = Maps.newHashMap();
		try {
			params.put("phoneKey", phoneKey);
			params.put("songId", id);
			params.put("sortColumn", "createDate");
			KeySong keySong = keySongService.selectByKeyAndId("KeySong.selectByKeyAndId", params);
			result = keySongService.isClick(keySong, id, phoneKey);// 判断是否可以点赞
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 删除合成歌曲
	 * 
	 * @param response
	 * @param request
	 * @param id
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/delete")
	@ResponseBody
	public Map<String, Object> delete(String id, String phoneKey) {
		return songService.deleteSong(id, phoneKey);
	}

	/**
	 * 语音文件上载与提交歌曲合成请求
	 * 
	 * @param file
	 * @param req
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/compoundSong")
	@ResponseBody
	public Map<String, String> compoundSong(@RequestParam("file") CommonsMultipartFile file, String musicId,
			String phoneKey, String appid, String appkey, String speechfmt, HttpServletRequest req) throws IOException {
		logger.info("1、================>上传用户语音文件至web服务器开始");
		Map<String, String> map = Maps.newHashMap();
		long start = System.currentTimeMillis();
		try {
			String date = DateUtil.getDateFilePath();
			String rootDir = PropertiesUtil.getProperty("app.http");// 取得服务器的域名和端口号：http//128.244.128.50:8080
			String subDir = PropertiesUtil.getProperty("upload.record") + SEPARATOR_STR + date;
			String extendName = FileUtil.getExtension(file.getOriginalFilename());// 取得文件的扩展名
			String newFileName = CommonUtil.getUuid() + extendName;// 拼接新的文件名（避免上传重复的被覆盖）
			String virtualDir = PropertiesUtil.getProperty("file.upload.virtual.dir");// 取得虚拟目录

			String recordHost = rootDir + virtualDir;//

			String typeDir = PropertiesUtil.getProperty("type.record");//

			String recordPath = typeDir + SEPARATOR_STR + date + SEPARATOR_STR + newFileName;//

			File f = new File(subDir);
			if (!f.exists()) {
				f.mkdirs();
			}
			File newFile = new File(f, newFileName);
			// 通过CommonsMultipartFile的方法直接写文件（注意这个时候）
			file.transferTo(newFile);
			long end = System.currentTimeMillis();
			logger.info("  ================>上传用户语音文件至web服务器完成,用時:" + (end - start) + "ms");

			start = System.currentTimeMillis();
			/** 上载用户语音start */
			// String uploadRecordUrl = "http://muse.yun.fm/api/upload";
			String uploadRecordUrl = PropertiesUtil.getProperty("interface.uploadRecord");// 取得上载用户语音调用的接口
			Map<String, ContentBody> params = Maps.newHashMap();

			StringBody app_key = new StringBody(appkey);
			StringBody app_id = new StringBody(appid);
			StringBody phone_key = new StringBody(phoneKey);
			StringBody speech_fmt = new StringBody(speechfmt);
			StringBody fmt = new StringBody("json");
			params.put("appid", app_key);
			params.put("appkey", app_id);
			params.put("uuid", phone_key);
			params.put("speechfmt", speech_fmt);
			params.put("fmt", fmt);
			logger.info("2、================>上传用户语音文件至引擎服务器开始");
			String jsonStr = HttpClientUtil.recordFileUpload(newFile, uploadRecordUrl, params);// 上传用户语音到第三方服务器
			end = System.currentTimeMillis();
			if ("".equals(jsonStr)) {
				Map<String, String> m = Maps.newHashMap();
				m.put("status", "0");
				m.put("msg", "响应失败");
				logger.info("  ================>上传用户语音文件至引擎服务器失败,用时" + (end - start) + "ms");
				return m;
			}
			logger.info("  ================>响应內容:" + jsonStr);
			map = JsonUtil.getUploadJson2Map(jsonStr);
			if ("".equals(map.get("recordId")) && "1" != map.get("status")) {
				return map;
			}
			logger.info("  ================>上传用户语音文件至引擎服务器完成,用时" + (end - start) + "ms");

			String recordId = map.get("recordId");
			Record record = new Record();
			record.setId(CommonUtil.uuidGenerate());
			record.setRecordName(newFileName);
			record.setContentType(speechfmt);
			record.setRecordHost(recordHost);
			record.setRecordPath(recordPath);
			record.setRecordId(recordId);
			Record r = (Record) recordService.save("Record.insert", record);

			start = System.currentTimeMillis();
			// 提交歌曲合成请求start */
			// String compoundUrl = "http://muse.yun.fm/api/remixadd";
			String compoundUrl = PropertiesUtil.getProperty("interface.compoundSong");// 取得提交歌曲合成请求的调用的接口
			StringBody speechid = new StringBody(recordId);
			StringBody tplid = new StringBody(musicId);
			params.put("speechid", speechid);
			params.put("tplid", tplid);
			logger.info("3、================>提交合成請求开始");
			jsonStr = HttpClientUtil.post(compoundUrl, params);
			end = System.currentTimeMillis();
			if ("".equals(jsonStr)) {
				Map<String, String> m = Maps.newHashMap();
				m.put("status", "0");
				m.put("msg", "响应失败");
				logger.info("  ================>合成请求响应失败!");
				return m;
			}
			map = JsonUtil.getCompoundJson2Map(jsonStr);
			logger.info("  ================>响应內容:" + jsonStr);
			if ("".equals(map.get("songId")) && "1" != map.get("status")) {
				return map;
			}
			logger.info("  ================>提交合成請求完成,用時:" + (end - start) + "ms");

			r.setSongId(map.get("songId"));
			recordService.update("Record.update", r);
			map.put("speechid", r.getRecordId());// 将第三方服务器返回的用户语音id放到map中返回给客户端
			map.put("recordId", r.getId());
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			logger.info("  ================>提交合成請求完成!");
		}

		return map;

	}

	/**
	 * 重新提交合成请求
	 * 
	 * @param musicId
	 * @param phoneKey
	 * @param appid
	 * @param appkey
	 * @param recordId
	 * @param speechid
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/compoundSongAgain")
	@ResponseBody
	public Map<String, String> compoundSongAgain(String musicId, String phoneKey, String appid, String appkey,
			String recordId, String speechid) throws IOException {

		Map<String, ContentBody> params = Maps.newHashMap();
		Map<String, String> map = Maps.newHashMap();
		String returnStr = "";
		try {
			logger.info("3、================>提交重新合成请求开始");
			/** 提交歌曲合成请求start */
			// String compoundUrl = "http://muse.yun.fm/api/remixadd";
			String compoundUrl = PropertiesUtil.getProperty("interface.compoundSong");// 取得提交歌曲合成请求的调用的接口
			StringBody speech_id = new StringBody(speechid);
			StringBody tplid = new StringBody(musicId);
			StringBody app_id = new StringBody(appid);
			StringBody app_key = new StringBody(appkey);
			StringBody uuid = new StringBody(phoneKey);
			StringBody fmt = new StringBody("json");
			StringBody speechfmt = new StringBody("amr");
			params.put("appid", app_key);
			params.put("appkey", app_id);
			params.put("uuid", uuid);
			params.put("fmt", fmt);
			params.put("speechid", speech_id);
			params.put("tplid", tplid);
			params.put("speechfmt", speechfmt);
		
			returnStr = HttpClientUtil.post(compoundUrl, params);
			if ("".equals(returnStr)) {
				Map<String, String> m = Maps.newHashMap();
				m.put("status", "0");
				m.put("msg", "响应失败");
				logger.info("  ================>提交重新合成响应失败");
				return m;
			}
			logger.info("  ================>响应內容:" + returnStr);
			map = JsonUtil.getCompoundJson2Map(returnStr);
			if ("".equals(map.get("songId")) && "1" != map.get("status")) {
				return map;
			}
			/** 提交歌曲合成请求end */
			Record record = (Record) recordService.getById("Record.selectById", recordId);
			record.setSongId(map.get("songId"));
			recordService.update("Record.update", record);
			map.put("speechid", record.getRecordId());// 将第三方服务器返回的用户语音id放到map中返回给客户端
			map.put("recordId", record.getId());// web服务器返回的用户语音id放到map中返回给客户端
		} catch (IOException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}finally {
			logger.info("  ================>提交重新合成请求完成!");

		}
		return map;
	}

	/**
	 * 查询及获取合成歌曲
	 * 
	 * @param songId
	 * @param appkey
	 * @param phoneKey
	 * @param appid
	 * @return
	 */
	@RequestMapping(value = "/getSongResStatus")
	@ResponseBody
	public Map<String, String> getSongResStatus(String songId, String appkey, String phoneKey, String appid,
			HttpServletRequest req) {

		Map<String, String> map = Maps.newHashMap();
		long start = System.currentTimeMillis();
		try {

			Map<String, ContentBody> params = Maps.newHashMap();

			/** String param name */
			// String queryStatusUrl = "http://muse.yun.fm/api/remixchk";
			String queryStatusUrl = PropertiesUtil.getProperty("interface.queryStatus");// 取得查询合唱歌曲状态调用的接口

			StringBody app_id = new StringBody(appid);
			StringBody app_key = new StringBody(appkey);
			StringBody uuid = new StringBody(phoneKey);
			StringBody remixid = new StringBody(songId);
			StringBody fmt = new StringBody("json");
			params.put("appid", app_key);
			params.put("appkey", app_id);
			params.put("uuid", uuid);
			params.put("fmt", fmt);
			params.put("remixid", remixid);

			logger.info("1、================>提交查询及获取合成歌曲请求开始,remixid="+songId);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			String result = HttpClientUtil.post(queryStatusUrl, params);

			if ("".equals(result)) {
				Map<String, String> m = Maps.newHashMap();
				m.put("status", "0");
				m.put("msg", "响应失败");
				logger.info("  ================>提交查询及获取合成歌曲请求响应失败!");
				return m;
			}
			logger.info("  ================>响应內容:" + result);
			long end = System.currentTimeMillis();
			map = JsonUtil.getQueryStatus(result);
			
			if (!"1".equals(map.get("status"))) {
				return map;
			}
			// 将合成歌曲文件存放到临时目录
			logger.info("  ================>下載合成歌曲请求开始");
			map = tempService.saveTemp(phoneKey, map.get("songUrl").toString(), SEPARATOR_STR);
			logger.info("  ================>下載合成歌曲请求完成,用時:" + (end - start) + "ms");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			logger.info("  ===============>提交查询及获取合成歌曲请求完成!");
		}
		return map;
	}

	/**
	 * 保存
	 * 
	 * @param request
	 * @param response
	 * @param songName
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Map<String, Object> save(HttpServletRequest request, HttpServletResponse response, String songName,
			String phoneKey, String tempId) {
		Map<String, Object> map = Maps.newHashMap();
		try {
			String decodeSongName=new String(songName.getBytes("iso-8859-1"),"utf-8");
			map = songService.save(decodeSongName, phoneKey, tempId, SEPARATOR_STR);
			logger.info("================>保存合成歌曲完成,歌曲名称:"+decodeSongName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

}
