package cn.aozhi.app.persistence.dao.temp;

import java.util.List;

import cn.aozhi.app.domain.temp.Temp;
import cn.aozhi.app.persistence.dao.common.CommonDao;

public interface TempDao extends CommonDao<Temp>{
	/**
	 * 批量删除
	 * @param sqlId
	 * @param list
	 * @return
	 */
	public int batchDelete(String sqlId, List<String> list);
	
}
