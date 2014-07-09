package cn.aozhi.app.timing;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import cn.aozhi.app.domain.temp.Temp;
import cn.aozhi.app.service.temp.TempService;
import cn.aozhi.app.util.DateUtil;
import cn.aozhi.app.util.PropertiesUtil;

import com.google.common.collect.Maps;

/**
 * 定时器（每天凌晨03:30删除昨天临时文件夹中的文件）
 * @author Luxh
 *
 */
public class QuartzJob {
	private final static String SEPARATOR = "/";
	
	@Autowired
	private TempService tempService;
	
	public void deleteFiles(){
		try{
			//要删除的文件所在的根目录
			String fileRootDir = PropertiesUtil.getProperty("delete.temp");
			String fileSubDir = DateUtil.getNowPerDay();
			File file = new File(fileRootDir+SEPARATOR+fileSubDir);
			if(file.exists()){//判断该目录是否存在
				if(file.isDirectory()){
					File[] files = file.listFiles();//获取该目录下的所有文件
					if(files.length>0){//该文件夹下有文件
						for(File f:files){
							f.delete();
						}
					}	
				}
				file.delete();//删除最后一层文件夹
			}
			Map<String,Object> params = Maps.newHashMap();
			List<String> list = new ArrayList<String>();
			String createDate = DateUtil.getNowMinusDay();
			params.put("createDate", createDate);
			params.put("sortColumn","createDate");
			List<Temp> tempList = tempService.list("Temp.list", params);
			if(tempList.size()>0&&tempList!=null){
				for(Temp temp:tempList){
					list.add(temp.getId());
				}
				int affect = tempService.batchDelete("Temp.batchDelete", list);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
		}
	}
}
