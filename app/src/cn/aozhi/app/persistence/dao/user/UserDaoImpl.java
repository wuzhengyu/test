package cn.aozhi.app.persistence.dao.user;

import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.aozhi.app.domain.user.User;
import cn.aozhi.app.persistence.dao.common.CommonDaoImpl;


@Repository("userDao")
public class UserDaoImpl extends CommonDaoImpl<User> implements UserDao{

	@Override
	public User checkUser(String sqlId, String username) {
		return sqlSession.selectOne(sqlId, username);
	}

	@Override
	public User loginInCheck(String sqlId, Map<String,String> params) {
		return sqlSession.selectOne(sqlId, params);
	}
}
