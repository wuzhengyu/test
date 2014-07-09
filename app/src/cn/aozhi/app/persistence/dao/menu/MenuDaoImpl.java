package cn.aozhi.app.persistence.dao.menu;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.aozhi.app.domain.menu.Menu;
import cn.aozhi.app.persistence.dao.common.CommonDaoImpl;

@Repository("menuDao")
public class MenuDaoImpl extends CommonDaoImpl implements MenuDao{
	
	
	@Override
	public List<Menu> batchSelect(String sqlId, List<String> list) {
		return sqlSession.selectList(sqlId, list);
	}

}
