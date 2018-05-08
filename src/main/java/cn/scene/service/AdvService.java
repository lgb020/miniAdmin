package cn.scene.service;

import cn.scene.model.Advertise;

import java.util.List;

/**
 * 广告
 */
public interface AdvService {

    //首页广告
    List<Advertise> info();

    //轮播图列表
    List<Advertise> adList();

    //插入轮播图
    int insertImg(String image);

    //删除轮播图
    int deleteAd(int id);
}
