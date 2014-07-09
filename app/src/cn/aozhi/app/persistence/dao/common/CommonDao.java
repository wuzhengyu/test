package cn.aozhi.app.persistence.dao.common;

import java.util.List;
import java.util.Map;


/**
 * 通用DAO接口
 * @author Luxh
 *
 */
public interface CommonDao<T> {
	
	/**
	 * 插入对象
	 * @param sqlId	映射文件中的sql
	 * @param obj
	 */
	Object insert(String sqlId,Object obj);
	
	/**
	 * 删除对象
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
	 * 数据列表(不带分页功能)
	 * @param sqlId
	 * @return
	 */
	List<T> list(String sqlId,Map<String,Object> params);
	
	/**
	 * 数据列表(带分页功能)
	 * @param sqlId
	 * @param obj
	 * @return
	 */
	List<T> findPageList(String sqlId,Map<String,Object> params);
	
	/**
	 * 统计总数
	 * @param sqlId
	 * @return
	 */
	int findPageCount(String sqlId,Map<String,Object> params);
}
