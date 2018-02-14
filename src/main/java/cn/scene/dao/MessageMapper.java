package cn.scene.dao;

import cn.scene.model.Message;
import cn.scene.model.Scene;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MessageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Message record);

    int insertSelective(Message record);

    Message selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Message record);

    int updateByPrimaryKey(Message record);

    //查询通知信息
    List<Message> selectByUserId(Integer userId);

}