package cn.scene.service;

import cn.scene.model.JifenRecord;

import java.util.List;

/**
 * 积分记录
 */
public interface JifenRecordService {

    //记录查询,1充值记录,2兑换记录
    List<JifenRecord> selectJifen(int userId,int type);

}
