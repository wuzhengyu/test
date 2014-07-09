package cn.aozhi.app.persistence.dao.phone;

import org.springframework.stereotype.Repository;

import cn.aozhi.app.domain.phone.Phone;
import cn.aozhi.app.persistence.dao.common.CommonDaoImpl;

@Repository("phoneDao")
public class PhoneDaoImpl extends CommonDaoImpl implements PhoneDao{

	@Override
	public Phone selectByPhoneKey(String sqlId, String phoneKey) {
		return sqlSession.selectOne(sqlId, phoneKey);
	}

}
