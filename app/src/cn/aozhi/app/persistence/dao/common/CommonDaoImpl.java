package cn.aozhi.app.persistence.dao.common;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;



/**
 * 通用DAO实现
 * @author Luxh
 * 
 */

@Repository("commonDao")
public class CommonDaoImpl<T> implements CommonDao<T>{
	
	@Autowired
	protected SqlSession sqlSession;

	@Override
	public Object insert(String sqlId, Object obj) {
		sqlSession.insert(sqlId, obj);
		return obj;
		
	}

	@Override
	public int delete(String sqlId, Object id) {
		return sqlSession.delete(sqlId, id);
	}

	@Override
	public Object getById(String sqlId, Object id) {
		return sqlSession.selectOne(sqlId, id);
	}

	@Override
	public List<T> list(String sqlId, Map<String,Object> params) {
		return sqlSession.selectList(sqlId,params);
	}
	 
	
	@Override
	public List<T> findPageList(String sqlId, Map<String,Object> params) {
		return sqlSession.selectList(sqlId, params);
	}

	@Override
	public int findPageCount(String sqlId, Map<String,Object> params) {
		return sqlSession.selectOne(sqlId,params);
	}

	@Override
	public int update(String sqlId, Object obj) {
		return sqlSession.update(sqlId, obj);
	}

}
