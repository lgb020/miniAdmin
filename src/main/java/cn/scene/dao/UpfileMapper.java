package cn.scene.dao;

import cn.scene.model.Upfile;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
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

    //用户上传图片
    int insertInfo(@Param("userId")int userId, @Param("type")String type,@Param("url")String url,@Param("times")Date times);
}