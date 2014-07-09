package cn.aozhi.app.persistence.dao.menu;

import java.util.List;

import cn.aozhi.app.domain.menu.Menu;
import cn.aozhi.app.persistence.dao.common.CommonDao;

public interface MenuDao extends CommonDao {
	/**
	 * 批量查询
	 * @param sqlId
	 * @param list
	 * @return
	 */
	public List<Menu> batchSelect(String sqlId,List<String> list);
}
