package cn.scene.dao;

import cn.scene.model.ScenePage;

public interface ScenePageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ScenePage record);

    int insertSelective(ScenePage record);

    ScenePage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ScenePage record);

    int updateByPrimaryKeyWithBLOBs(ScenePage record);

    int updateByPrimaryKey(ScenePage record);
}