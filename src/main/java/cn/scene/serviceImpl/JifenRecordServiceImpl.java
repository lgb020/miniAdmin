package cn.scene.serviceImpl;

import cn.scene.dao.JifenRecordMapper;
import cn.scene.model.JifenRecord;
import cn.scene.service.JifenRecordService;
import cn.scene.util.DateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 积分兑换
 */
@Service("jifenRecordService")
public class JifenRecordServiceImpl implements JifenRecordService{

    @Autowired
    private JifenRecordMapper jifenRecordMapper;

    /**
     * 积分记录
     * @param userId
     * @param type 1:充值 2:兑换
     * @return
     */
    @Override
    public List<JifenRecord> selectJifen(int userId, int type) {
        List<JifenRecord> list =  jifenRecordMapper.selectByUserId(userId,type);
        for(int i=0;i<list.size();i++){
            Date times = list.get(i).getTimes();
            list.get(i).setsTimes(DateFormat.format(times));
        }
        return list;
    }
}
