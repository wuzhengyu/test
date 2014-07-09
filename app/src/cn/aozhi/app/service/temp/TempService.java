package cn.aozhi.app.service.temp;

import java.util.List;
import java.util.Map;

import cn.aozhi.app.domain.temp.Temp;
import cn.aozhi.app.service.common.CommonService;

public interface TempService extends CommonService<Temp>{
	public Map<String,String>saveTemp(String phoneKey,String srcUrl,String SEPARATOR_STR);
	
	/**
	 * 批量删除
	 * @param sqlId
	 * @param list
	 * @return
	 */
	public int batchDelete(String sqlId,List<String> list);
}
