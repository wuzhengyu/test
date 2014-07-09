package cn.aozhi.app.persistence.dao.temp;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.aozhi.app.domain.temp.Temp;
import cn.aozhi.app.persistence.dao.common.CommonDaoImpl;

@Repository("tempDao")
public class TempDaoImpl extends CommonDaoImpl<Temp> implements TempDao{

	@Override
	public int batchDelete(String sqlId, List<String> list) {
		return sqlSession.delete(sqlId, list);
	}

}
