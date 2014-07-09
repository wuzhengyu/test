package cn.aozhi.app.service.common;

import java.util.List;
import java.util.Map;
import cn.aozhi.app.util.Page;

/**
 * 通用service接口
 * @author Luxh
 *
 * @param <T>
 */
public interface CommonService<T> {
	
	/**
	 * 插入一个对象
	 * @param sqlId
	 * @param obj
	 */
	Object save(String sqlId,Object obj);
	
	/**
	 * 删除一个对象
	 * @param sqlId
	 * @param id
	 */
	int delete(String sqlId,Object id);
	
	/**
	 * 更新
	 * @param sqlId
	 * @param obj
	 */
	int update(String sqlId,Object obj);
	
	/**
	 * 根据id查询
	 * @param sqlId
	 * @param id
	 * @return
	 */
	Object getById(String sqlId,Object id);
	
	/**
	 * 数据列表（不带分页）
	 * @param sqlId
	 * @return
	 */
	List<T> list(String sqlId,Map<String,Object> params);
	
	
	/**
	 * 取得分页后的Page对象
	 * @param currentPage
	 * @param pageSize
	 * @param params
	 * @return
	 */
	Page<T> getPage(int currentPage,int pageSize,Map<String,Object> params);
	
	/**
	 * 数据列表（带分页功能）
	 * @param sqlId
	 * @param params
	 * @return
	 */
	List<T> findPageList(String sqlId,Map<String,Object> params);
	
	/**
	 * 统计总数
	 * @param sqlId
	 * @param params
	 * @return
	 */
	long findPageCount(String sqlId,Map<String,Object> params);
	
}
