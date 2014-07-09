package cn.aozhi.app.service.phone;

import org.springframework.stereotype.Service;

import cn.aozhi.app.domain.phone.Phone;
import cn.aozhi.app.service.common.CommonServiceImpl;

@Service("phoneService")
public class PhoneServiceImpl extends CommonServiceImpl<Phone> implements PhoneService{

}
