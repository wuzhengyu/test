package cn.aozhi.app.service.menu;

import java.util.List;

import cn.aozhi.app.domain.menu.Menu;
import cn.aozhi.app.service.common.CommonService;

public interface MenuService extends CommonService<Menu>{
	/**
	 * 批量查询
	 * @param sqlId
	 * @param list
	 * @return
	 */
	public List<Menu> batchSelect(String sqlId,List<String> list);
}
