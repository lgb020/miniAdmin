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

    //查询所有轮播图
    List<Advertise> selectAd();

    //插入轮播图
    int insertImg(Advertise advertise);

    //删除轮播图
    int deleteAd(Integer id);
}