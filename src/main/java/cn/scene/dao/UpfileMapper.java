package cn.scene.dao;

import cn.scene.model.Upfile;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UpfileMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Upfile record);

    int insertSelective(Upfile record);

    Upfile selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Upfile record);

    int updateByPrimaryKey(Upfile record);

    //查询用户素材记录
    List<Upfile> selectInfoByType(@Param("userId")int userId,@Param("type")String type);
}