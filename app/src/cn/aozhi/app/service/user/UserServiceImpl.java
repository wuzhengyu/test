package cn.aozhi.app.service.user;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import cn.aozhi.app.domain.user.User;
import cn.aozhi.app.persistence.dao.user.UserDao;
import cn.aozhi.app.service.common.CommonServiceImpl;



@Service("userService")
public class UserServiceImpl extends CommonServiceImpl<User> implements UserService{
	
	@Autowired
	private UserDao userDao;

	@Override
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public User selectByUsername(String sqlId, String username) {
		return userDao.checkUser(sqlId, username);
	}

	@Override
	public User loginInCheck(String sqlId, Map params) {
		
		return userDao.loginInCheck(sqlId, params);
	}

	
}
