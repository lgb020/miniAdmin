package cn.scene.dao;

import cn.scene.model.DataDetail;

import java.util.List;

public interface DataDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DataDetail record);

    int insertSelective(DataDetail record);

    DataDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DataDetail record);

    int updateByPrimaryKey(DataDetail record);

    //查询场景信息
    List<DataDetail> selectDataInfo(Integer sceneId);

    //场景收集的数据不存在记录则保存，存在则不操作
    int insertInfo(DataDetail data);

    //根据场景查询总人数
    int selectTotalBySceneId(Integer sceneId);

    //根据场景查询出席人数
    int selectAttendanceBySceneId(Integer sceneId);
}