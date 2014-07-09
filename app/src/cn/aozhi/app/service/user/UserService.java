package cn.aozhi.app.service.user;

import java.util.Map;

import cn.aozhi.app.domain.user.User;
import cn.aozhi.app.service.common.CommonService;


public interface UserService extends CommonService<User>{
	User selectByUsername(String sqlId,String username);
	User loginInCheck(String sqlId,Map<String,String> params);
}
