package cn.aozhi.app.service.common;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import cn.aozhi.app.persistence.dao.common.CommonDao;
import cn.aozhi.app.util.Page;

@Service("commonService")
public class CommonServiceImpl<T> implements CommonService<T>{
	
	@Autowired
	protected CommonDao<T> commonDao;
	
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public Object save(String sqlId, Object obj) {
		return commonDao.insert(sqlId, obj);
	}


	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public int delete(String sqlId, Object id) {
		return commonDao.delete(sqlId, id);
	}


	@Override
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public Object getById(String sqlId, Object id) {
		return commonDao.getById(sqlId, id);
	}


	@Override
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public List<T> list(String sqlId,Map<String,Object> params) {
		return commonDao.list(sqlId,params);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public long findPageCount(String sqlId, Map<String,Object> params) {
		return commonDao.findPageCount(sqlId, params);
		
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public Page<T> getPage(int currentPage, int pageSize,Map<String, Object> params) {
		List<T> list = commonDao.findPageList(params.get("sqlList").toString(), params);
		int totalCount = (int) commonDao.findPageCount(params.get("sqlCount").toString(), params);
		Page<T> p = new Page<T>(currentPage, pageSize, totalCount, list);
		return p;
	}


	@Override
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public List<T> findPageList(String sqlId, Map<String, Object> params) {
		return commonDao.findPageList(sqlId, params);
	}

	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public int update(String sqlId, Object obj) {
		return commonDao.update(sqlId, obj);
	}

}