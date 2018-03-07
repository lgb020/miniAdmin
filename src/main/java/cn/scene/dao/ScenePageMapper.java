package cn.scene.dao;

import cn.scene.model.ScenePage;
import org.apache.ibatis.annotations.Param;

public interface ScenePageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ScenePage record);

    int insertSelective(ScenePage record);

    ScenePage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ScenePage record);

    int updateByPrimaryKeyWithBLOBs(ScenePage record);

    int updateByPrimaryKey(ScenePage record);

    //查询该场景当前页是否存在
    int selectBySceneId(@Param("sceneId")int sceneId,@Param("currentPage")int currentPage);

}