package cn.scene.dao;

import cn.scene.model.UserAuth;
import org.apache.ibatis.annotations.Param;

public interface UserAuthMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserAuth record);

    int insertSelective(UserAuth record);

    UserAuth selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserAuth record);

    int updateByPrimaryKey(UserAuth record);

    UserAuth selectInfoByAccount(String account);

    int updateByStatus(@Param("account") String account, @Param("token") String token);
}