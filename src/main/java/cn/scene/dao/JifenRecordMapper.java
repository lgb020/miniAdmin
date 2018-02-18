package cn.scene.dao;

import cn.scene.model.JifenRecord;

import java.util.List;

public interface JifenRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(JifenRecord record);

    int insertSelective(JifenRecord record);

    JifenRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(JifenRecord record);

    int updateByPrimaryKey(JifenRecord record);

    //根据用户id查询积分记录
    List<JifenRecord> selectByUserId(Integer userId);
}