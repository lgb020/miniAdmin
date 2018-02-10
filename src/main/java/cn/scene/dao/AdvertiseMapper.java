package cn.scene.dao;

import cn.scene.model.Advertise;

import java.util.List;

public interface AdvertiseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Advertise record);

    int insertSelective(Advertise record);

    Advertise selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Advertise record);

    int updateByPrimaryKey(Advertise record);

    //查询首页的广告
    List<Advertise> selectInfo();
}