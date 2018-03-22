package cn.scene.dao;

import cn.scene.model.SceneReport;
import org.apache.ibatis.annotations.Param;

public interface SceneReportMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SceneReport record);

    int insertSelective(SceneReport record);

    SceneReport selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SceneReport record);

    int updateByPrimaryKey(SceneReport record);

    //查询投诉ip
    SceneReport selectIdBySceneId(@Param("sceneId")int sceneId,@Param("ip")String ip);

    //提交投诉
    int insertReportInfo(SceneReport report);
}