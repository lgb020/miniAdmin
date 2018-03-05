package cn.scene.dao;

import cn.scene.model.Sysfile;

import java.util.List;

public interface SysfileMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Sysfile record);

    int insertSelective(Sysfile record);

    Sysfile selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Sysfile record);

    int updateByPrimaryKey(Sysfile record);

    //素材查询
    List<Sysfile> selectInfoByType(String type);

    //查询素材总数
    int selectInfoCounts(String type);
}