package cn.aozhi.app.service.menu;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.aozhi.app.domain.menu.Menu;
import cn.aozhi.app.persistence.dao.menu.MenuDao;
import cn.aozhi.app.service.common.CommonServiceImpl;

@Service("menuService")
public class MenuServiceImpl extends CommonServiceImpl<Menu> implements MenuService {

	@Autowired
	private MenuDao menuDao;
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public List<Menu> batchSelect(String sqlId, List<String> list) {
		return menuDao.batchSelect(sqlId, list);
	}

}
