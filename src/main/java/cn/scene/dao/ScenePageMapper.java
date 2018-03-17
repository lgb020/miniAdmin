package cn.scene.dao;

import cn.scene.model.ScenePage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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

    //根据sceneId查询场景
    List<ScenePage> selectInfoBySceneId(Integer sceneId);
}