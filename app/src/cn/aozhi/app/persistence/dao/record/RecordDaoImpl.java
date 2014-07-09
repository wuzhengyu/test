package cn.aozhi.app.persistence.dao.record;

import org.springframework.stereotype.Repository;

import cn.aozhi.app.domain.record.Record;
import cn.aozhi.app.persistence.dao.common.CommonDaoImpl;

@Repository("recordDao")
public class RecordDaoImpl extends CommonDaoImpl<Record> implements RecordDao {

}
