package cn.aozhi.app.persistence.dao.user;

import java.util.Map;

import cn.aozhi.app.domain.user.User;
import cn.aozhi.app.persistence.dao.common.CommonDao;


public interface UserDao extends CommonDao<User>{
	/**
	 * 根据用户名查询
	 * @param sqlId
	 * @param username
	 * @return
	 */
	User checkUser(String sqlId,String username);
	
	
	/**
	 * 根据用户名和密码查询
	 * @param sqlId
	 * @param username
	 * @param password
	 * @return
	 */
	User loginInCheck(String sqlId,Map<String,String> params);
}
