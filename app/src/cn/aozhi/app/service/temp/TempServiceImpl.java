package cn.aozhi.app.service.temp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.aozhi.app.domain.temp.Temp;
import cn.aozhi.app.persistence.dao.temp.TempDao;
import cn.aozhi.app.service.common.CommonServiceImpl;
import cn.aozhi.app.util.CommonUtil;
import cn.aozhi.app.util.DateUtil;
import cn.aozhi.app.util.FileUtil;
import cn.aozhi.app.util.HttpClientUtil;
import cn.aozhi.app.util.PropertiesUtil;

import com.google.common.collect.Maps;

@Service("tempService")
public class TempServiceImpl extends CommonServiceImpl<Temp> implements TempService{
	
	@Autowired
	private TempDao tempDao;
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public Map<String, String> saveTemp(String phoneKey, String srcUrl,String SEPARATOR_STR) {
		Map<String,String> m = Maps.newHashMap();
		String status = "1";
		String msg = "操作成功";
		String songUrl = "";
		String tempId="";
		try{
			String date = DateUtil.getDateFilePath();
			String rootDir = PropertiesUtil.getProperty("app.http");//取得服务器的域名和端口号：http//128.244.128.50:8080
			String subDir = PropertiesUtil.getProperty("upload.temp")+SEPARATOR_STR+date;
			String virtualDir = PropertiesUtil.getProperty("file.upload.virtual.dir");//取得虚拟目录 
         	String fileName = HttpClientUtil.downloadFileByUrl(srcUrl, subDir);//下载歌曲文件到服务器
	        String typeDir = PropertiesUtil.getProperty("type.temp");
	 		String tempHost = rootDir+virtualDir;
	 		String tempPath = typeDir+SEPARATOR_STR+date+SEPARATOR_STR+fileName;
	 		String tempRealRootDir = PropertiesUtil.getProperty("common.upload.rootDir");//临时文件保存的真实路径
	 		
	         //设置合成歌曲对象的值
	         Temp temp = new Temp();
	         temp.setId(CommonUtil.uuidGenerate());
	         temp.setTempName(FileUtil.getWithoutExtension(fileName));
	         temp.setTempHost(tempHost);
	         temp.setTempPath(tempPath);
	         temp.setTempRootDir(tempRealRootDir);
	         temp.setContentType(FileUtil.getNoPointExtension(fileName));
	         temp = (Temp) save("Temp.insert", temp);//将合成歌曲对象信息保存到数据库
	         
	         if(temp==null){
	        	status = "0";
	 			msg = "操作失败";
	         }else{
	        	 tempId = temp.getId();
	        	 songUrl = tempHost+tempPath;
	         }
		}catch(Exception e){
			e.printStackTrace();
			status = "0";
			msg = "操作失败";
		}finally{
			m.put("status", status);
			m.put("msg", msg);
			m.put("songUrl",songUrl);
			m.put("tempId", tempId);
		}
		return m;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public int batchDelete(String sqlId, List<String> list) {
		return tempDao.batchDelete(sqlId, list);
	}
	
}
