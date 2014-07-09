package cn.aozhi.app.persistence.dao.phone;

import cn.aozhi.app.domain.phone.Phone;
import cn.aozhi.app.persistence.dao.common.CommonDao;

public interface PhoneDao extends CommonDao{
	Phone selectByPhoneKey(String sqlId,String phoneKey);
}
